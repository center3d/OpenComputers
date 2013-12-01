package li.cil.oc

import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.block.Block
import net.minecraft.item.{Item, ItemStack}
import net.minecraftforge.oredict.{ShapelessOreRecipe, ShapedOreRecipe, OreDictionary}
import net.minecraft.item.crafting.FurnaceRecipes

object Recipes {
  def init() {
    val ironIngot = new ItemStack(Item.ingotIron)
    val goldIngot = new ItemStack(Item.ingotGold)
    val ironBars = new ItemStack(Block.fenceIron)
    val dirt = new ItemStack(Block.dirt)
    val lapis = new ItemStack(Item.dyePowder, 1, 4)
    val cactusGreen = new ItemStack(Item.dyePowder, 1, 2)
    val diamond = new ItemStack(Item.diamond)
    val glowstoneDust = new ItemStack(Item.glowstone)
    val redstoneDust = new ItemStack(Item.redstone)
    val redstoneTorch = new ItemStack(Block.torchRedstoneActive)
    val comparator = new ItemStack(Item.comparator)
    val repeater = new ItemStack(Item.redstoneRepeater)
    val emerald = new ItemStack(Item.emerald)
    val goldNugget = new ItemStack(Item.goldNugget)
    val clock = new ItemStack(Item.pocketSundial)
    val enderPearl = new ItemStack(Item.enderPearl)
    val piston = new ItemStack(Block.pistonStickyBase)
    val lever = new ItemStack(Block.lever)
    val paper = new ItemStack(Item.paper)

    val chip1 = Items.chip1.createItemStack()
    val chip2 = Items.chip2.createItemStack()
    val chip3 = Items.chip3.createItemStack()
    val transistor = Items.transistor.createItemStack()
    val circuitBoard = Items.circuitBoardBody.createItemStack()
    val rawCircuitBoard = Items.rawCircuitBoard.createItemStack()
    val printedCircuitBoard = Items.printedCircuitBoard.createItemStack()
    val card = Items.card.createItemStack()
    val cpu = Items.cpu.createItemStack()
    val alu = Items.alu.createItemStack()
    val cu = Items.cu.createItemStack()
    val ironNugget = Items.ironNugget.createItemStack()
    val ram1 = Items.ram1.createItemStack()
    val ram2 = Items.ram2.createItemStack()
    val ram3 = Items.ram3.createItemStack()
    val gpu1 = Items.gpu1.createItemStack()
    val gpu2 = Items.gpu2.createItemStack()
    val gpu3 = Items.gpu3.createItemStack()
    val hdd1 = Items.hdd1.createItemStack()
    val hdd2 = Items.hdd2.createItemStack()
    val hdd3 = Items.hdd3.createItemStack()
    val redstoneCard = Items.rs.createItemStack()
    val lanCard = Items.lan.createItemStack()
    val wlanCard = Items.wlan.createItemStack()
    val cable = Blocks.cable.createItemStack()
    val analyzer = Items.analyzer.createItemStack()
    val disc = Items.disc.createItemStack()
    val floppy = Items.floppyDisk.createItemStack()

    GameRegistry.addRecipe(Blocks.adapter.createItemStack(),
      "x  ",
      "   ",
      "xxx", 'x': Character, dirt)

    GameRegistry.addRecipe(Blocks.capacitor.createItemStack(),
      "x  ",
      "   ",
      "xxx", 'x': Character, dirt)

    GameRegistry.addRecipe(Blocks.diskDrive.createItemStack(),
      "x  ",
      "   ",
      "xxx", 'x': Character, dirt)

    GameRegistry.addRecipe(Blocks.powerDistributor.createItemStack(),
      "x  ",
      "   ",
      "xxx", 'x': Character, dirt)

    GameRegistry.addRecipe(Blocks.powerSupply.createItemStack(),
      "x  ",
      "   ",
      "xxx", 'x': Character, dirt)

    addRecipe(Blocks.screen1.createItemStack(),
      "x  ",
      "   ",
      "xxx", 'x': Character, dirt)

    addRecipe(Blocks.screen2.createItemStack(),
      "x  ",
      "   ",
      "xxx", 'x': Character, dirt)

    addRecipe(Blocks.screen3.createItemStack(),
      "x  ",
      "   ",
      "xxx", 'x': Character, dirt)

    addRecipe(Blocks.router.createItemStack(),
      "x  ",
      "   ",
      "xxx", 'x': Character, dirt)

    addRecipe(Blocks.cable.createItemStack(),
      "x  ",
      "   ",
      "xxx", 'x': Character, dirt)


    addRecipe(Blocks.robotProxy.createItemStack(),
      "x  ",
      "   ",
      "xxx", 'x': Character, dirt)

    addRecipe(floppy,
      "ili",
      "pdp",
      "iai", 'd', disc,'i',ironNugget,'l',lever,'p',printedCircuitBoard,'a',paper)

    addRecipe(hdd1,
      "cdi",
      "bdp",
      "cdi", 'b', printedCircuitBoard, 'i', ironIngot, 'p', piston, 'd', disc, 'c', chip1)

    addRecipe(hdd2,
      "gdg",
      "cbc",
      "gdg", 'g', goldIngot,'d',hdd1,'c',chip1,'b',printedCircuitBoard)

    addRecipe(hdd3,
      "cdc",
      "rbr",
      "cdc", 'c', chip3,'d',hdd2,'r',ram1,'b',printedCircuitBoard)
    addRecipe(disc,
      " i ",
      "i i",
      " i ", 'i', ironNugget)






    addRecipe(Blocks.case1.createItemStack(),
      "xxx",
      "x x",
      "xxx", 'x': Character, ironIngot)
    addRecipe(Blocks.keyboard.createItemStack(),
      "xxx",
      "xan", 'x': Character, Items.buttonGroup.createItemStack(), 'a': Character, Items.arrowKeys.createItemStack(), 'n': Character, Items.numPad.createItemStack())






    addRecipe(card,
      "ict",
      "ibb",
      "inn", 'i', ironNugget, 'c', chip1, 't', transistor, 'b', printedCircuitBoard, 'n', goldNugget)


    addRecipe(gpu1,
      "car",
      " d ", 'c', chip1, 'r', ram1, 'd', card, 'a', alu)

    addRecipe(gpu2,
      "ccr",
      " d ", 'c', chip2, 'r', ram2, 'd', gpu1)

    addRecipe(gpu3,
      "ccr",
      " d ", 'c', chip3, 'r', ram3, 'd', gpu2)


    addRecipe(lanCard,
      "tc ",
      " d ", 'c', chip1, 't', cable, 'd', card)

    addRecipe(wlanCard,
      "tc ",
      " d ", 'c', chip2, 't', enderPearl, 'd', lanCard)

    GameRegistry.addRecipe(ram1,
      "xxx",
      "yyy", 'x': Character, chip1, 'y': Character, Items.printedCircuitBoard.createItemStack())

    GameRegistry.addRecipe(ram2,
      "xxx",
      "yyy", 'x': Character, chip2, 'y': Character, Items.printedCircuitBoard.createItemStack())

    GameRegistry.addRecipe(ram3,
      "xxx",
      "yyy", 'x': Character, chip3, 'y': Character, Items.printedCircuitBoard.createItemStack())

    addRecipe(redstoneCard,
      "tc ",
      " d ", 'c', chip1, 't', redstoneTorch, 'd', card)

    GameRegistry.addRecipe(Items.numPad.createItemStack(),
      "xxx",
      "xxx",
      "xxx", 'x': Character, new ItemStack(Block.stoneButton))

    GameRegistry.addRecipe(Items.arrowKeys.createItemStack(),
      " x ",
      "xxx", 'x': Character, new ItemStack(Block.stoneButton))

    GameRegistry.addRecipe(Items.buttonGroup.createItemStack(),
      "xxx",
      "xxx", 'x': Character, new ItemStack(Block.stoneButton))

    addRecipe(transistor,
      "ttt",
      "drd",
      " d ", 'r', repeater, 'd', redstoneDust, 't', redstoneTorch)

    addRecipe(analyzer,
      " r ",
      "tcg",
      "tpg", 'p', printedCircuitBoard, 'c', chip1, 'r', redstoneTorch, 't', transistor, 'g', goldNugget)

    addRecipe(cpu,
      "crc",
      "bub",
      "cac", 'b', ironBars, 'r', redstoneDust, 'c', chip1, 'a', alu, 'u', cu)

    addRecipe(alu,
      "rtr",
      "sss",
      "bdb", 'r', repeater, 's', transistor, 't', redstoneTorch,
      'b', ironNugget, 'd', redstoneDust)

    addRecipe(cu,
      "gtg",
      "scs",
      "grg", 'r', redstoneDust, 's', transistor, 't', redstoneTorch,
      'c', clock, 'g', goldNugget)

    addRecipe(chip1,
      "ibi",
      "rtr",
      "ibi", 'i', "nuggetIron", 'b', ironBars, 'r', redstoneDust, 't', transistor)

    addRecipe(chip2,
      "glg",
      "cdc",
      "glg", 'g', goldNugget, 'l', lapis, 'c', chip1, 'd', diamond)

    addRecipe(chip3,
      "dmd",
      "cec",
      "dmd", 'd', glowstoneDust, 'm', comparator, 'c', chip2, 'e', emerald)



    addRecipe(ironIngot,
      "xxx",
      "xxx",
      "xxx", 'x', "nuggetIron")

    GameRegistry.addShapelessRecipe(Items.ironCutter.createItemStack(1), new ItemStack(Item.shears, 1, OreDictionary.WILDCARD_VALUE), ironNugget, new ItemStack(Item.stick))
    GameRegistry.addShapelessRecipe(rawCircuitBoard, Items.ironCutter.createItemStack(), new ItemStack(Block.blockClay), cactusGreen)
    FurnaceRecipes.smelting().addSmelting(rawCircuitBoard.itemID, rawCircuitBoard.getItemDamage, circuitBoard, 1)
    GameRegistry.addRecipe(new ShapelessOreRecipe(Items.printedCircuitBoard.createItemStack(), "potionPoison", Item.goldNugget, circuitBoard))
    GameRegistry.addShapelessRecipe(new ItemStack(Item.potion), Item.bucketWater, Item.glassBottle)
    GameRegistry.addRecipe(new ShapelessOreRecipe(Items.ironNugget.createItemStack(9), ironIngot))
  }

  def addRecipe(output: ItemStack, args: Any*) = {
    GameRegistry.addRecipe(new ShapedOreRecipe(output, args.map(_.asInstanceOf[AnyRef]): _*))
  }
}
