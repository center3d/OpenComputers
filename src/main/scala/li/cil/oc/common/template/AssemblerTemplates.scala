package li.cil.oc.common.template

import java.lang.reflect.{Method, Modifier}
import java.util.logging.Level

import li.cil.oc.common.{Slot, Tier}
import li.cil.oc.util.ExtendedNBT._
import li.cil.oc.{OpenComputers, api}
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ChatMessageComponent

import scala.collection.mutable

object AssemblerTemplates {
  val NoSlot = new Slot(Slot.None, Tier.None, None)

  val templates = mutable.ArrayBuffer.empty[Template]

  def add(template: NBTTagCompound): Unit = try {
    val selector = getStaticMethod(template.getString("select"), classOf[ItemStack])
    val validator = getStaticMethod(template.getString("validate"), classOf[IInventory])
    val assembler = getStaticMethod(template.getString("assemble"), classOf[IInventory])
    val containerSlots = template.getTagList("containerSlots").map(parseSlot(Some(Slot.Container))).take(3).padTo(3, NoSlot).toArray
    val upgradeSlots = template.getTagList("upgradeSlots").map(parseSlot(Some(Slot.Upgrade))).take(9).padTo(9, NoSlot).toArray
    val componentSlots = template.getTagList("componentSlots").map(parseSlot()).take(9).padTo(9, NoSlot).toArray

    templates += new Template(selector, validator, assembler, containerSlots, upgradeSlots, componentSlots)
  }
  catch {
    case t: Throwable => OpenComputers.log.log(Level.WARNING, "Failed registering assembler template.", t)
  }

  def select(stack: ItemStack) = templates.find(_.select(stack))

  class Template(val selector: Method,
                 val validator: Method,
                 val assembler: Method,
                 val containerSlots: Array[Slot],
                 val upgradeSlots: Array[Slot],
                 val componentSlots: Array[Slot]) {
    def select(stack: ItemStack) = tryInvokeStatic(selector, stack)(false)

    def validate(inventory: IInventory) = tryInvokeStatic(validator, inventory)(null: Array[AnyRef]) match {
      case Array(valid: java.lang.Boolean, progress: ChatMessageComponent, warnings: Array[ChatMessageComponent]) => (valid: Boolean, progress, warnings)
      case Array(valid: java.lang.Boolean, progress: ChatMessageComponent) => (valid: Boolean, progress, Array.empty[ChatMessageComponent])
      case Array(valid: java.lang.Boolean) => (valid: Boolean, null, Array.empty[ChatMessageComponent])
      case _ => (false, null, Array.empty[ChatMessageComponent])
    }

    def assemble(inventory: IInventory) = tryInvokeStatic(assembler, inventory)(null: Array[AnyRef]) match {
      case Array(stack: ItemStack, energy: java.lang.Double) => (stack, energy: Double)
      case Array(stack: ItemStack) => (stack, 0.0)
      case _ => (null, 0.0)
    }
  }

  class Slot(val kind: String, val tier: Int, val validator: Option[Method]) {
    def validate(inventory: IInventory, slot: Int, stack: ItemStack) = validator match {
      case Some(method) => tryInvokeStatic(method, inventory, slot.underlying(), tier.underlying(), stack)(false)
      case _ => Option(api.Driver.driverFor(stack)) match {
        case Some(driver) => Slot(driver, stack) == kind && driver.tier(stack) <= tier
        case _ => false
      }
    }
  }

  private def parseSlot(kindOverride: Option[String] = None)(nbt: NBTTagCompound) = {
    val kind = kindOverride.getOrElse(if (nbt.hasKey("type")) nbt.getString("type") else Slot.None)
    val tier = if (nbt.hasKey("tier")) nbt.getInteger("tier") else Tier.Any
    val validator = if (nbt.hasKey("validate")) Option(getStaticMethod(nbt.getString("validate"), classOf[IInventory], classOf[Int], classOf[Int], classOf[ItemStack])) else None
    new Slot(kind, tier, validator)
  }

  private def getStaticMethod(name: String, signature: Class[_]*) = {
    val nameSplit = name.lastIndexOf('.')
    val className = name.substring(0, nameSplit)
    val methodName = name.substring(nameSplit + 1)
    val clazz = Class.forName(className)
    val method = clazz.getDeclaredMethod(methodName, signature: _*)
    if (!Modifier.isStatic(method.getModifiers)) throw new IllegalArgumentException(s"Method $name is not static.")
    method
  }

  private def tryInvokeStatic[T](method: Method, args: AnyRef*)(default: T): T = try method.invoke(null, args: _*).asInstanceOf[T] catch {
    case t: Throwable =>
      OpenComputers.log.log(Level.WARNING, s"Error invoking callback ${method.getDeclaringClass.getCanonicalName + "." + method.getName}.", t)
      default
  }
}
