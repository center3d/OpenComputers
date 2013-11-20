package li.cil.oc

import cpw.mods.fml.common.registry.GameRegistry
import li.cil.oc.common.block._
import li.cil.oc.common.tileentity

object Blocks {
  var blockSimple: SimpleDelegator = null
  var blockSpecial: SpecialDelegator = null

  var adapter: Adapter = null
  var cable: Cable = null
  var capacitor: Capacitor = null
  var computer: Case = null
  var diskDrive: DiskDrive = null
  var keyboard: Keyboard = null
  var powerDistributor: PowerDistributor = null
  var powerSupply: PowerConverter = null
  var robot: Robot = null
  var robotAfterimage: RobotAfterimage = null
  var screen1, screen2, screen3: Screen = null

  def init() {
    blockSimple = new SimpleDelegator(Config.blockId, "simple")
    blockSpecial = new SpecialDelegator(Config.blockSpecialId, "special")

    GameRegistry.registerBlock(blockSimple, classOf[Item], Config.namespace + "block_simple")
    GameRegistry.registerBlock(blockSpecial, classOf[Item], Config.namespace + "block_special")

    GameRegistry.registerTileEntity(classOf[tileentity.Adapter], Config.namespace + "adapter")
    GameRegistry.registerTileEntity(classOf[tileentity.Cable], Config.namespace + "cable")
    GameRegistry.registerTileEntity(classOf[tileentity.Capacitor], Config.namespace + "capacitor")
    GameRegistry.registerTileEntity(classOf[tileentity.Case], Config.namespace + "case")
    GameRegistry.registerTileEntity(classOf[tileentity.DiskDrive], Config.namespace + "disk_drive")
    GameRegistry.registerTileEntity(classOf[tileentity.Keyboard], Config.namespace + "keyboard")
    GameRegistry.registerTileEntity(classOf[tileentity.PowerConverter], Config.namespace + "power_converter")
    GameRegistry.registerTileEntity(classOf[tileentity.PowerDistributor], Config.namespace + "power_distributor")
    GameRegistry.registerTileEntity(classOf[tileentity.Robot], Config.namespace + "robot")
    GameRegistry.registerTileEntity(classOf[tileentity.Screen], Config.namespace + "screen")

    // IMPORTANT: the multi block must come first, since the sub blocks will
    // try to register with it. Also, the order the sub blocks are created in
    // must not be changed since that order determines their actual IDs.
    adapter = new Adapter(blockSimple)
    cable = new Cable(blockSpecial)
    computer = new Case(blockSimple)
    diskDrive = new DiskDrive(blockSimple)
    keyboard = new Keyboard(blockSpecial)
    powerDistributor = new PowerDistributor(blockSimple)
    powerSupply = new PowerConverter(blockSimple)
    screen1 = new Screen.Tier1(blockSimple)
    screen2 = new Screen.Tier2(blockSimple)
    screen3 = new Screen.Tier3(blockSimple)

    capacitor = new Capacitor(blockSimple)
    robot = new Robot(blockSpecial)
    robotAfterimage = new RobotAfterimage(blockSpecial)
  }
}