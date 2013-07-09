package net.minecraft.server;

import java.util.List;
import java.util.Random;
import net.canarymod.hook.world.BlockDropXpHook;

public class Block {

    private CreativeTabs a;
    protected String f;
    public static final StepSound g = new StepSound("stone", 1.0F, 1.0F);
    public static final StepSound h = new StepSound("wood", 1.0F, 1.0F);
    public static final StepSound i = new StepSound("gravel", 1.0F, 1.0F);
    public static final StepSound j = new StepSound("grass", 1.0F, 1.0F);
    public static final StepSound k = new StepSound("stone", 1.0F, 1.0F);
    public static final StepSound l = new StepSound("stone", 1.0F, 1.5F);
    public static final StepSound m = new StepSoundStone("stone", 1.0F, 1.0F);
    public static final StepSound n = new StepSound("cloth", 1.0F, 1.0F);
    public static final StepSound o = new StepSound("sand", 1.0F, 1.0F);
    public static final StepSound p = new StepSound("snow", 1.0F, 1.0F);
    public static final StepSound q = new StepSoundSand("ladder", 1.0F, 1.0F);
    public static final StepSound r = new StepSoundAnvil("anvil", 0.3F, 1.0F);
    public static final Block[] s = new Block[4096];
    public static final boolean[] t = new boolean[4096];
    public static final int[] u = new int[4096];
    public static final boolean[] v = new boolean[4096];
    public static final int[] w = new int[4096];
    public static boolean[] x = new boolean[4096];
    public static final Block y = (new BlockStone(1)).c(1.5F).b(10.0F).a(k).c("stone").d("stone");
    public static final BlockGrass z = (BlockGrass) (new BlockGrass(2)).c(0.6F).a(j).c("grass").d("grass");
    public static final Block A = (new BlockDirt(3)).c(0.5F).a(i).c("dirt").d("dirt");
    public static final Block B = (new Block(4, Material.e)).c(2.0F).b(10.0F).a(k).c("stonebrick").a(CreativeTabs.b).d("cobblestone");
    public static final Block C = (new BlockWood(5)).c(2.0F).b(5.0F).a(h).c("wood").d("planks");
    public static final Block D = (new BlockSapling(6)).c(0.0F).a(j).c("sapling").d("sapling");
    public static final Block E = (new Block(7, Material.e)).r().b(6000000.0F).a(k).c("bedrock").C().a(CreativeTabs.b).d("bedrock");
    public static final BlockFluid F = (BlockFluid) (new BlockFlowing(8, Material.h)).c(100.0F).k(3).c("water").C().d("water_flow");
    public static final Block G = (new BlockStationary(9, Material.h)).c(100.0F).k(3).c("water").C().d("water_still");
    public static final BlockFluid H = (BlockFluid) (new BlockFlowing(10, Material.i)).c(0.0F).a(1.0F).c("lava").C().d("lava_flow");
    public static final Block I = (new BlockStationary(11, Material.i)).c(100.0F).a(1.0F).c("lava").C().d("lava_still");
    public static final Block J = (new BlockSand(12)).c(0.5F).a(o).c("sand").d("sand");
    public static final Block K = (new BlockGravel(13)).c(0.6F).a(i).c("gravel").d("gravel");
    public static final Block L = (new BlockOre(14)).c(3.0F).b(5.0F).a(k).c("oreGold").d("gold_ore");
    public static final Block M = (new BlockOre(15)).c(3.0F).b(5.0F).a(k).c("oreIron").d("iron_ore");
    public static final Block N = (new BlockOre(16)).c(3.0F).b(5.0F).a(k).c("oreCoal").d("coal_ore");
    public static final Block O = (new BlockLog(17)).c(2.0F).a(h).c("log").d("log");
    public static final BlockLeaves P = (BlockLeaves) (new BlockLeaves(18)).c(0.2F).k(1).a(j).c("leaves").d("leaves");
    public static final Block Q = (new BlockSponge(19)).c(0.6F).a(j).c("sponge").d("sponge");
    public static final Block R = (new BlockGlass(20, Material.s, false)).c(0.3F).a(m).c("glass").d("glass");
    public static final Block S = (new BlockOre(21)).c(3.0F).b(5.0F).a(k).c("oreLapis").d("lapis_ore");
    public static final Block T = (new Block(22, Material.e)).c(3.0F).b(5.0F).a(k).c("blockLapis").a(CreativeTabs.b).d("lapis_block");
    public static final Block U = (new BlockDispenser(23)).c(3.5F).a(k).c("dispenser").d("dispenser");
    public static final Block V = (new BlockSandStone(24)).a(k).c(0.8F).c("sandStone").d("sandstone");
    public static final Block W = (new BlockNote(25)).c(0.8F).c("musicBlock").d("noteblock");
    public static final Block X = (new BlockBed(26)).c(0.2F).c("bed").C().d("bed");
    public static final Block Y = (new BlockRailPowered(27)).c(0.7F).a(l).c("goldenRail").d("rail_golden");
    public static final Block Z = (new BlockDetectorRail(28)).c(0.7F).a(l).c("detectorRail").d("rail_detector");
    public static final BlockPistonBase aa = (BlockPistonBase) (new BlockPistonBase(29, true)).c("pistonStickyBase");
    public static final Block ab = (new BlockWeb(30)).k(1).c(4.0F).c("web").d("web");
    public static final BlockTallGrass ac = (BlockTallGrass) (new BlockTallGrass(31)).c(0.0F).a(j).c("tallgrass");
    public static final BlockDeadBush ad = (BlockDeadBush) (new BlockDeadBush(32)).c(0.0F).a(j).c("deadbush").d("deadbush");
    public static final BlockPistonBase ae = (BlockPistonBase) (new BlockPistonBase(33, false)).c("pistonBase");
    public static final BlockPistonExtension af = new BlockPistonExtension(34);
    public static final Block ag = (new BlockColored(35, Material.n)).c(0.8F).a(n).c("cloth").d("wool_colored");
    public static final BlockPistonMoving ah = new BlockPistonMoving(36);
    public static final BlockFlower ai = (BlockFlower) (new BlockFlower(37)).c(0.0F).a(j).c("flower").d("flower_dandelion");
    public static final BlockFlower aj = (BlockFlower) (new BlockFlower(38)).c(0.0F).a(j).c("rose").d("flower_rose");
    public static final BlockFlower ak = (BlockFlower) (new BlockMushroom(39)).c(0.0F).a(j).a(0.125F).c("mushroom").d("mushroom_brown");
    public static final BlockFlower al = (BlockFlower) (new BlockMushroom(40)).c(0.0F).a(j).c("mushroom").d("mushroom_red");
    public static final Block am = (new BlockOreStorage(41)).c(3.0F).b(10.0F).a(l).c("blockGold").d("gold_block");
    public static final Block an = (new BlockOreStorage(42)).c(5.0F).b(10.0F).a(l).c("blockIron").d("iron_block");
    public static final BlockHalfSlab ao = (BlockHalfSlab) (new BlockStep(43, true)).c(2.0F).b(10.0F).a(k).c("stoneSlab");
    public static final BlockHalfSlab ap = (BlockHalfSlab) (new BlockStep(44, false)).c(2.0F).b(10.0F).a(k).c("stoneSlab");
    public static final Block aq = (new Block(45, Material.e)).c(2.0F).b(10.0F).a(k).c("brick").a(CreativeTabs.b).d("brick");
    public static final Block ar = (new BlockTNT(46)).c(0.0F).a(j).c("tnt").d("tnt");
    public static final Block as = (new BlockBookshelf(47)).c(1.5F).a(h).c("bookshelf").d("bookshelf");
    public static final Block at = (new Block(48, Material.e)).c(2.0F).b(10.0F).a(k).c("stoneMoss").a(CreativeTabs.b).d("cobblestone_mossy");
    public static final Block au = (new BlockObsidian(49)).c(50.0F).b(2000.0F).a(k).c("obsidian").d("obsidian");
    public static final Block av = (new BlockTorch(50)).c(0.0F).a(0.9375F).a(h).c("torch").d("torch_on");
    public static final BlockFire aw = (BlockFire) (new BlockFire(51)).c(0.0F).a(1.0F).a(h).c("fire").C().d("fire");
    public static final Block ax = (new BlockMobSpawner(52)).c(5.0F).a(l).c("mobSpawner").C().d("mob_spawner");
    public static final Block ay = (new BlockStairs(53, C, 0)).c("stairsWood");
    public static final BlockChest az = (BlockChest) (new BlockChest(54, 0)).c(2.5F).a(h).c("chest");
    public static final BlockRedstoneWire aA = (BlockRedstoneWire) (new BlockRedstoneWire(55)).c(0.0F).a(g).c("redstoneDust").C().d("redstone_dust");
    public static final Block aB = (new BlockOre(56)).c(3.0F).b(5.0F).a(k).c("oreDiamond").d("diamond_ore");
    public static final Block aC = (new BlockOreStorage(57)).c(5.0F).b(10.0F).a(l).c("blockDiamond").d("diamond_block");
    public static final Block aD = (new BlockWorkbench(58)).c(2.5F).a(h).c("workbench").d("crafting_table");
    public static final Block aE = (new BlockCrops(59)).c("crops").d("wheat");
    public static final Block aF = (new BlockFarmland(60)).c(0.6F).a(i).c("farmland").d("farmland");
    public static final Block aG = (new BlockFurnace(61, false)).c(3.5F).a(k).c("furnace").a(CreativeTabs.c);
    public static final Block aH = (new BlockFurnace(62, true)).c(3.5F).a(k).a(0.875F).c("furnace");
    public static final Block aI = (new BlockSign(63, TileEntitySign.class, true)).c(1.0F).a(h).c("sign").C();
    public static final Block aJ = (new BlockDoor(64, Material.d)).c(3.0F).a(h).c("doorWood").C().d("door_wood");
    public static final Block aK = (new BlockLadder(65)).c(0.4F).a(q).c("ladder").d("ladder");
    public static final Block aL = (new BlockRail(66)).c(0.7F).a(l).c("rail").d("rail_normal");
    public static final Block aM = (new BlockStairs(67, B, 0)).c("stairsStone");
    public static final Block aN = (new BlockSign(68, TileEntitySign.class, false)).c(1.0F).a(h).c("sign").C();
    public static final Block aO = (new BlockLever(69)).c(0.5F).a(h).c("lever").d("lever");
    public static final Block aP = (new BlockPressurePlate(70, "stone", Material.e, EnumMobType.b)).c(0.5F).a(k).c("pressurePlate");
    public static final Block aQ = (new BlockDoor(71, Material.f)).c(5.0F).a(l).c("doorIron").C().d("door_iron");
    public static final Block aR = (new BlockPressurePlate(72, "planks_oak", Material.d, EnumMobType.a)).c(0.5F).a(h).c("pressurePlate");
    public static final Block aS = (new BlockRedstoneOre(73, false)).c(3.0F).b(5.0F).a(k).c("oreRedstone").a(CreativeTabs.b).d("redstone_ore");
    public static final Block aT = (new BlockRedstoneOre(74, true)).a(0.625F).c(3.0F).b(5.0F).a(k).c("oreRedstone").d("redstone_ore");
    public static final Block aU = (new BlockRedstoneTorch(75, false)).c(0.0F).a(h).c("notGate").d("redstone_torch_off");
    public static final Block aV = (new BlockRedstoneTorch(76, true)).c(0.0F).a(0.5F).a(h).c("notGate").a(CreativeTabs.d).d("redstone_torch_on");
    public static final Block aW = (new BlockButtonStone(77)).c(0.5F).a(k).c("button");
    public static final Block aX = (new BlockSnow(78)).c(0.1F).a(p).c("snow").k(0).d("snow");
    public static final Block aY = (new BlockIce(79)).c(0.5F).k(3).a(m).c("ice").d("ice");
    public static final Block aZ = (new BlockSnowBlock(80)).c(0.2F).a(p).c("snow").d("snow");
    public static final Block ba = (new BlockCactus(81)).c(0.4F).a(n).c("cactus").d("cactus");
    public static final Block bb = (new BlockClay(82)).c(0.6F).a(i).c("clay").d("clay");
    public static final Block bc = (new BlockReed(83)).c(0.0F).a(j).c("reeds").C().d("reeds");
    public static final Block bd = (new BlockJukeBox(84)).c(2.0F).b(10.0F).a(k).c("jukebox").d("jukebox");
    public static final Block be = (new BlockFence(85, "planks_oak", Material.d)).c(2.0F).b(5.0F).a(h).c("fence");
    public static final Block bf = (new BlockPumpkin(86, false)).c(1.0F).a(h).c("pumpkin").d("pumpkin");
    public static final Block bg = (new BlockNetherrack(87)).c(0.4F).a(k).c("hellrock").d("netherrack");
    public static final Block bh = (new BlockSoulSand(88)).c(0.5F).a(o).c("hellsand").d("soul_sand");
    public static final Block bi = (new BlockGlowStone(89, Material.s)).c(0.3F).a(m).a(1.0F).c("lightgem").d("glowstone");
    public static final BlockPortal bj = (BlockPortal) (new BlockPortal(90)).c(-1.0F).a(m).a(0.75F).c("portal").d("portal");
    public static final Block bk = (new BlockPumpkin(91, true)).c(1.0F).a(h).a(1.0F).c("litpumpkin").d("pumpkin");
    public static final Block bl = (new BlockCake(92)).c(0.5F).a(n).c("cake").C().d("cake");
    public static final BlockRedstoneRepeater bm = (BlockRedstoneRepeater) (new BlockRedstoneRepeater(93, false)).c(0.0F).a(h).c("diode").C().d("repeater_off");
    public static final BlockRedstoneRepeater bn = (BlockRedstoneRepeater) (new BlockRedstoneRepeater(94, true)).c(0.0F).a(0.625F).a(h).c("diode").C().d("repeater_on");
    public static final Block bo = (new BlockLockedChest(95)).c(0.0F).a(1.0F).a(h).c("lockedchest").b(true);
    public static final Block bp = (new BlockTrapDoor(96, Material.d)).c(3.0F).a(h).c("trapdoor").C().d("trapdoor");
    public static final Block bq = (new BlockSilverfish(97)).c(0.75F).c("monsterStoneEgg");
    public static final Block br = (new BlockStoneBrick(98)).c(1.5F).b(10.0F).a(k).c("stonebricksmooth").d("stonebrick");
    public static final Block bs = (new BlockMushroomCap(99, Material.d, 0)).c(0.2F).a(h).c("mushroom").d("mushroom_block");
    public static final Block bt = (new BlockMushroomCap(100, Material.d, 1)).c(0.2F).a(h).c("mushroom").d("mushroom_block");
    public static final Block bu = (new BlockPane(101, "iron_bars", "iron_bars", Material.f, true)).c(5.0F).b(10.0F).a(l).c("fenceIron");
    public static final Block bv = (new BlockPane(102, "glass", "glass_pane_top", Material.s, false)).c(0.3F).a(m).c("thinGlass");
    public static final Block bw = (new BlockMelon(103)).c(1.0F).a(h).c("melon").d("melon");
    public static final Block bx = (new BlockStem(104, bf)).c(0.0F).a(h).c("pumpkinStem").d("pumpkin_stem");
    public static final Block by = (new BlockStem(105, bw)).c(0.0F).a(h).c("pumpkinStem").d("melon_stem");
    public static final Block bz = (new BlockVine(106)).c(0.2F).a(j).c("vine").d("vine");
    public static final Block bA = (new BlockFenceGate(107)).c(2.0F).b(5.0F).a(h).c("fenceGate");
    public static final Block bB = (new BlockStairs(108, aq, 0)).c("stairsBrick");
    public static final Block bC = (new BlockStairs(109, br, 0)).c("stairsStoneBrickSmooth");
    public static final BlockMycelium bD = (BlockMycelium) (new BlockMycelium(110)).c(0.6F).a(j).c("mycel").d("mycelium");
    public static final Block bE = (new BlockLilyPad(111)).c(0.0F).a(j).c("waterlily").d("waterlily");
    public static final Block bF = (new Block(112, Material.e)).c(2.0F).b(10.0F).a(k).c("netherBrick").a(CreativeTabs.b).d("nether_brick");
    public static final Block bG = (new BlockFence(113, "nether_brick", Material.e)).c(2.0F).b(10.0F).a(k).c("netherFence");
    public static final Block bH = (new BlockStairs(114, bF, 0)).c("stairsNetherBrick");
    public static final Block bI = (new BlockNetherStalk(115)).c("netherStalk").d("nether_wart");
    public static final Block bJ = (new BlockEnchantmentTable(116)).c(5.0F).b(2000.0F).c("enchantmentTable").d("enchanting_table");
    public static final Block bK = (new BlockBrewingStand(117)).c(0.5F).a(0.125F).c("brewingStand").d("brewing_stand");
    public static final BlockCauldron bL = (BlockCauldron) (new BlockCauldron(118)).c(2.0F).c("cauldron").d("cauldron");
    public static final Block bM = (new BlockEndPortal(119, Material.D)).c(-1.0F).b(6000000.0F);
    public static final Block bN = (new BlockEndPortalFrame(120)).a(m).a(0.125F).c(-1.0F).c("endPortalFrame").b(6000000.0F).a(CreativeTabs.c).d("endframe");
    public static final Block bO = (new Block(121, Material.e)).c(3.0F).b(15.0F).a(k).c("whiteStone").a(CreativeTabs.b).d("end_stone");
    public static final Block bP = (new BlockDragonEgg(122)).c(3.0F).b(15.0F).a(k).a(0.125F).c("dragonEgg").d("dragon_egg");
    public static final Block bQ = (new BlockRedstoneLight(123, false)).c(0.3F).a(m).c("redstoneLight").a(CreativeTabs.d).d("redstone_lamp_off");
    public static final Block bR = (new BlockRedstoneLight(124, true)).c(0.3F).a(m).c("redstoneLight").d("redstone_lamp_on");
    public static final BlockHalfSlab bS = (BlockHalfSlab) (new BlockWoodSlab(125, true)).c(2.0F).b(5.0F).a(h).c("woodSlab");
    public static final BlockHalfSlab bT = (BlockHalfSlab) (new BlockWoodSlab(126, false)).c(2.0F).b(5.0F).a(h).c("woodSlab");
    public static final Block bU = (new BlockCocoa(127)).c(0.2F).b(5.0F).a(h).c("cocoa").d("cocoa");
    public static final Block bV = (new BlockStairs(128, V, 0)).c("stairsSandStone");
    public static final Block bW = (new BlockOre(129)).c(3.0F).b(5.0F).a(k).c("oreEmerald").d("emerald_ore");
    public static final Block bX = (new BlockEnderChest(130)).c(22.5F).b(1000.0F).a(k).c("enderChest").a(0.5F);
    public static final BlockTripWireSource bY = (BlockTripWireSource) (new BlockTripWireSource(131)).c("tripWireSource").d("trip_wire_source");
    public static final Block bZ = (new BlockTripWire(132)).c("tripWire").d("trip_wire");
    public static final Block ca = (new BlockOreStorage(133)).c(5.0F).b(10.0F).a(l).c("blockEmerald").d("emerald_block");
    public static final Block cb = (new BlockStairs(134, C, 1)).c("stairsWoodSpruce");
    public static final Block cc = (new BlockStairs(135, C, 2)).c("stairsWoodBirch");
    public static final Block cd = (new BlockStairs(136, C, 3)).c("stairsWoodJungle");
    public static final Block ce = (new BlockCommandBlock(137)).r().b(6000000.0F).c("commandBlock").d("command_block");
    public static final BlockBeacon cf = (BlockBeacon) (new BlockBeacon(138)).c("beacon").a(1.0F).d("beacon");
    public static final Block cg = (new BlockWall(139, B)).c("cobbleWall");
    public static final Block ch = (new BlockFlowerPot(140)).c(0.0F).a(g).c("flowerPot").d("flower_pot");
    public static final Block ci = (new BlockCarrot(141)).c("carrots").d("carrots");
    public static final Block cj = (new BlockPotato(142)).c("potatoes").d("potatoes");
    public static final Block ck = (new BlockButtonWood(143)).c(0.5F).a(h).c("button");
    public static final Block cl = (new BlockSkull(144)).c(1.0F).a(k).c("skull").d("skull");
    public static final Block cm = (new BlockAnvil(145)).c(5.0F).a(r).b(2000.0F).c("anvil");
    public static final Block cn = (new BlockChest(146, 1)).c(2.5F).a(h).c("chestTrap");
    public static final Block co = (new BlockPressurePlateWeighted(147, "gold_block", Material.f, 64)).c(0.5F).a(h).c("weightedPlate_light");
    public static final Block cp = (new BlockPressurePlateWeighted(148, "iron_block", Material.f, 640)).c(0.5F).a(h).c("weightedPlate_heavy");
    public static final BlockComparator cq = (BlockComparator) (new BlockComparator(149, false)).c(0.0F).a(h).c("comparator").C().d("comparator_off");
    public static final BlockComparator cr = (BlockComparator) (new BlockComparator(150, true)).c(0.0F).a(0.625F).a(h).c("comparator").C().d("comparator_on");
    public static final BlockDaylightDetector cs = (BlockDaylightDetector) (new BlockDaylightDetector(151)).c(0.2F).a(h).c("daylightDetector").d("daylight_detector");
    public static final Block ct = (new BlockPoweredOre(152)).c(5.0F).b(10.0F).a(l).c("blockRedstone").d("redstone_block");
    public static final Block cu = (new BlockOre(153)).c(3.0F).b(5.0F).a(k).c("netherquartz").d("quartz_ore");
    public static final BlockHopper cv = (BlockHopper) (new BlockHopper(154)).c(3.0F).b(8.0F).a(h).c("hopper").d("hopper");
    public static final Block cw = (new BlockQuartz(155)).a(k).c(0.8F).c("quartzBlock").d("quartz_block");
    public static final Block cx = (new BlockStairs(156, cw, 0)).c("stairsQuartz");
    public static final Block cy = (new BlockRailPowered(157)).c(0.7F).a(l).c("activatorRail").d("rail_activator");
    public static final Block cz = (new BlockDropper(158)).c(3.5F).a(k).c("dropper").d("dropper");
    public static final Block cA = (new BlockColored(159, Material.e)).c(1.25F).b(7.0F).a(k).c("clayHardenedStained").d("hardened_clay_stained");
    public static final Block cB = (new BlockHay(170)).c(0.5F).a(j).c("hayBlock").a(CreativeTabs.b).d("hay_block");
    public static final Block cC = (new BlockCarpet(171)).c(0.1F).a(n).c("woolCarpet").k(0);
    public static final Block cD = (new Block(172, Material.e)).c(1.25F).b(7.0F).a(k).c("clayHardened").a(CreativeTabs.b).d("hardened_clay");
    public static final Block cE = (new Block(173, Material.e)).c(5.0F).b(10.0F).a(k).c("blockCoal").a(CreativeTabs.b).d("coal_block");
    public final int cF;
    protected float cG;
    protected float cH;
    protected boolean cI = true;
    protected boolean cJ = true;
    protected boolean cK;
    protected boolean cL;
    protected double cM;
    protected double cN;
    protected double cO;
    protected double cP;
    protected double cQ;
    protected double cR;
    public StepSound cS;
    public float cT;
    public final Material cU;
    public float cV;
    private String b;

    protected Block(int i0, Material material) {
        this.cS = g;
        this.cT = 1.0F;
        this.cV = 0.6F;
        if (s[i0] != null) {
            throw new IllegalArgumentException("Slot " + i0 + " is already occupied by " + s[i0] + " when adding " + this);
        } else {
            this.cU = material;
            s[i0] = this;
            this.cF = i0;
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            t[i0] = this.c();
            u[i0] = this.c() ? 255 : 0;
            v[i0] = !material.b();
        }
    }

    protected void s_() {}

    protected Block a(StepSound stepsound) {
        this.cS = stepsound;
        return this;
    }

    protected Block k(int i0) {
        u[this.cF] = i0;
        return this;
    }

    protected Block a(float f0) {
        w[this.cF] = (int) (15.0F * f0);
        return this;
    }

    protected Block b(float f0) {
        this.cH = f0 * 3.0F;
        return this;
    }

    public static boolean l(int i0) {
        Block block = s[i0];

        return block == null ? false : block.cU.k() && block.b() && !block.f();
    }

    public boolean b() {
        return true;
    }

    public boolean b(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        return !this.cU.c();
    }

    public int d() {
        return 0;
    }

    protected Block c(float f0) {
        this.cG = f0;
        if (this.cH < f0 * 5.0F) {
            this.cH = f0 * 5.0F;
        }

        return this;
    }

    protected Block r() {
        this.c(-1.0F);
        return this;
    }

    public float l(World world, int i0, int i1, int i2) {
        return this.cG;
    }

    protected Block b(boolean flag0) {
        this.cK = flag0;
        return this;
    }

    public boolean s() {
        return this.cK;
    }

    public boolean t() {
        return this.cL;
    }

    protected final void a(float f0, float f1, float f2, float f3, float f4, float f5) {
        this.cM = (double) f0;
        this.cN = (double) f1;
        this.cO = (double) f2;
        this.cP = (double) f3;
        this.cQ = (double) f4;
        this.cR = (double) f5;
    }

    public boolean a_(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        return iblockaccess.g(i0, i1, i2).a();
    }

    public void a(World world, int i0, int i1, int i2, AxisAlignedBB axisalignedbb, List list, Entity entity) {
        AxisAlignedBB axisalignedbb1 = this.b(world, i0, i1, i2);

        if (axisalignedbb1 != null && axisalignedbb.b(axisalignedbb1)) {
            list.add(axisalignedbb1);
        }
    }

    public AxisAlignedBB b(World world, int i0, int i1, int i2) {
        return AxisAlignedBB.a().a((double) i0 + this.cM, (double) i1 + this.cN, (double) i2 + this.cO, (double) i0 + this.cP, (double) i1 + this.cQ, (double) i2 + this.cR);
    }

    public boolean c() {
        return true;
    }

    public boolean a(int i0, boolean flag0) {
        return this.m();
    }

    public boolean m() {
        return true;
    }

    public void a(World world, int i0, int i1, int i2, Random random) {}

    public void g(World world, int i0, int i1, int i2, int i3) {}

    public void a(World world, int i0, int i1, int i2, int i3) {}

    public int a(World world) {
        return 10;
    }

    public void a(World world, int i0, int i1, int i2) {}

    public void a(World world, int i0, int i1, int i2, int i3, int i4) {}

    public int a(Random random) {
        return 1;
    }

    public int a(int i0, Random random, int i1) {
        return this.cF;
    }

    public float a(EntityPlayer entityplayer, World world, int i0, int i1, int i2) {
        float f0 = this.l(world, i0, i1, i2);

        return f0 < 0.0F ? 0.0F : (!entityplayer.a(this) ? entityplayer.a(this, false) / f0 / 100.0F : entityplayer.a(this, true) / f0 / 30.0F);
    }

    public final void c(World world, int i0, int i1, int i2, int i3, int i4) {
        this.a(world, i0, i1, i2, i3, 1.0F, i4);
    }

    public void a(World world, int i0, int i1, int i2, int i3, float f0, int i4) {
        if (!world.I) {
            int i5 = this.a(i4, world.s);

            for (int i6 = 0; i6 < i5; ++i6) {
                if (world.s.nextFloat() <= f0) {
                    int i7 = this.a(i3, world.s, i4);

                    if (i7 > 0) {
                        this.b(world, i0, i1, i2, new ItemStack(i7, 1, this.a(i3)));
                    }
                }
            }
        }
    }

    protected void b(World world, int i0, int i1, int i2, ItemStack itemstack) {
        if (!world.I && world.O().b("doTileDrops")) {
            float f0 = 0.7F;
            double d0 = (double) (world.s.nextFloat() * f0) + (double) (1.0F - f0) * 0.5D;
            double d1 = (double) (world.s.nextFloat() * f0) + (double) (1.0F - f0) * 0.5D;
            double d2 = (double) (world.s.nextFloat() * f0) + (double) (1.0F - f0) * 0.5D;
            EntityItem entityitem = new EntityItem(world, (double) i0 + d0, (double) i1 + d1, (double) i2 + d2, itemstack);

            entityitem.b = 10;
            world.d((Entity) entityitem);
        }
    }

    protected void j(World world, int i0, int i1, int i2, int i3) {
        if (!world.I) {
            // CanaryMod: BlockDropXpHook
            net.canarymod.api.world.blocks.Block block = world.getCanaryWorld().getBlockAt(i0, i1, i2);
            BlockDropXpHook hook = (BlockDropXpHook) new BlockDropXpHook(block, i3).call();
            if (hook.isCanceled()) {
                return;
            }
            i3 = hook.getXp();
            //

            while (i3 > 0) {
                int i4 = EntityXPOrb.a(i3);

                i3 -= i4;
                world.d((Entity) (new EntityXPOrb(world, (double) i0 + 0.5D, (double) i1 + 0.5D, (double) i2 + 0.5D, i4)));
            }
        }
    }

    public int a(int i0) {
        return 0;
    }

    public float a(Entity entity) {
        return this.cH / 5.0F;
    }

    public MovingObjectPosition a(World world, int i0, int i1, int i2, Vec3 vec3, Vec3 vec31) {
        this.a((IBlockAccess) world, i0, i1, i2);
        vec3 = vec3.c((double) (-i0), (double) (-i1), (double) (-i2));
        vec31 = vec31.c((double) (-i0), (double) (-i1), (double) (-i2));
        Vec3 vec32 = vec3.b(vec31, this.cM);
        Vec3 vec33 = vec3.b(vec31, this.cP);
        Vec3 vec34 = vec3.c(vec31, this.cN);
        Vec3 vec35 = vec3.c(vec31, this.cQ);
        Vec3 vec36 = vec3.d(vec31, this.cO);
        Vec3 vec37 = vec3.d(vec31, this.cR);

        if (!this.a(vec32)) {
            vec32 = null;
        }

        if (!this.a(vec33)) {
            vec33 = null;
        }

        if (!this.b(vec34)) {
            vec34 = null;
        }

        if (!this.b(vec35)) {
            vec35 = null;
        }

        if (!this.c(vec36)) {
            vec36 = null;
        }

        if (!this.c(vec37)) {
            vec37 = null;
        }

        Vec3 vec38 = null;

        if (vec32 != null && (vec38 == null || vec3.e(vec32) < vec3.e(vec38))) {
            vec38 = vec32;
        }

        if (vec33 != null && (vec38 == null || vec3.e(vec33) < vec3.e(vec38))) {
            vec38 = vec33;
        }

        if (vec34 != null && (vec38 == null || vec3.e(vec34) < vec3.e(vec38))) {
            vec38 = vec34;
        }

        if (vec35 != null && (vec38 == null || vec3.e(vec35) < vec3.e(vec38))) {
            vec38 = vec35;
        }

        if (vec36 != null && (vec38 == null || vec3.e(vec36) < vec3.e(vec38))) {
            vec38 = vec36;
        }

        if (vec37 != null && (vec38 == null || vec3.e(vec37) < vec3.e(vec38))) {
            vec38 = vec37;
        }

        if (vec38 == null) {
            return null;
        } else {
            byte b0 = -1;

            if (vec38 == vec32) {
                b0 = 4;
            }

            if (vec38 == vec33) {
                b0 = 5;
            }

            if (vec38 == vec34) {
                b0 = 0;
            }

            if (vec38 == vec35) {
                b0 = 1;
            }

            if (vec38 == vec36) {
                b0 = 2;
            }

            if (vec38 == vec37) {
                b0 = 3;
            }

            return new MovingObjectPosition(i0, i1, i2, b0, vec38.c((double) i0, (double) i1, (double) i2));
        }
    }

    private boolean a(Vec3 vec3) {
        return vec3 == null ? false : vec3.d >= this.cN && vec3.d <= this.cQ && vec3.e >= this.cO && vec3.e <= this.cR;
    }

    private boolean b(Vec3 vec3) {
        return vec3 == null ? false : vec3.c >= this.cM && vec3.c <= this.cP && vec3.e >= this.cO && vec3.e <= this.cR;
    }

    private boolean c(Vec3 vec3) {
        return vec3 == null ? false : vec3.c >= this.cM && vec3.c <= this.cP && vec3.d >= this.cN && vec3.d <= this.cQ;
    }

    public void a(World world, int i0, int i1, int i2, Explosion explosion) {}

    public boolean a(World world, int i0, int i1, int i2, int i3, ItemStack itemstack) {
        return this.c(world, i0, i1, i2, i3);
    }

    public boolean c(World world, int i0, int i1, int i2, int i3) {
        return this.c(world, i0, i1, i2);
    }

    public boolean c(World world, int i0, int i1, int i2) {
        int i3 = world.a(i0, i1, i2);

        return i3 == 0 || s[i3].cU.j();
    }

    public boolean a(World world, int i0, int i1, int i2, EntityPlayer entityplayer, int i3, float f0, float f1, float f2) {
        return false;
    }

    public void b(World world, int i0, int i1, int i2, Entity entity) {}

    public int a(World world, int i0, int i1, int i2, int i3, float f0, float f1, float f2, int i4) {
        return i4;
    }

    public void a(World world, int i0, int i1, int i2, EntityPlayer entityplayer) {}

    public void a(World world, int i0, int i1, int i2, Entity entity, Vec3 vec3) {}

    public void a(IBlockAccess iblockaccess, int i0, int i1, int i2) {}

    public final double u() {
        return this.cM;
    }

    public final double v() {
        return this.cP;
    }

    public final double w() {
        return this.cN;
    }

    public final double x() {
        return this.cQ;
    }

    public final double y() {
        return this.cO;
    }

    public final double z() {
        return this.cR;
    }

    public int b(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        return 0;
    }

    public boolean f() {
        return false;
    }

    public void a(World world, int i0, int i1, int i2, Entity entity) {}

    public int c(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        return 0;
    }

    public void g() {}

    public void a(World world, EntityPlayer entityplayer, int i0, int i1, int i2, int i3) {
        entityplayer.a(StatList.C[this.cF], 1);
        entityplayer.a(0.025F);
        if (this.r_() && EnchantmentHelper.e(entityplayer)) {
            ItemStack itemstack = this.d_(i3);

            if (itemstack != null) {
                this.b(world, i0, i1, i2, itemstack);
            }
        } else {
            int i4 = EnchantmentHelper.f(entityplayer);

            this.c(world, i0, i1, i2, i3, i4);
        }
    }

    protected boolean r_() {
        return this.b() && !this.cL;
    }

    protected ItemStack d_(int i0) {
        int i1 = 0;

        if (this.cF >= 0 && this.cF < Item.g.length && Item.g[this.cF].n()) {
            i1 = i0;
        }

        return new ItemStack(this.cF, 1, i1);
    }

    public int a(int i0, Random random) {
        return this.a(random);
    }

    public boolean f(World world, int i0, int i1, int i2) {
        return true;
    }

    public void a(World world, int i0, int i1, int i2, EntityLivingBase entitylivingbase, ItemStack itemstack) {}

    public void k(World world, int i0, int i1, int i2, int i3) {}

    public Block c(String s0) {
        this.b = s0;
        return this;
    }

    public String A() {
        return StatCollector.a(this.a() + ".name");
    }

    public String a() {
        return "tile." + this.b;
    }

    public boolean b(World world, int i0, int i1, int i2, int i3, int i4) {
        return false;
    }

    public boolean B() {
        return this.cJ;
    }

    protected Block C() {
        this.cJ = false;
        return this;
    }

    public int h() {
        return this.cU.m();
    }

    public void a(World world, int i0, int i1, int i2, Entity entity, float f0) {}

    public int h(World world, int i0, int i1, int i2) {
        return this.a(world.h(i0, i1, i2));
    }

    public Block a(CreativeTabs creativetabs) {
        this.a = creativetabs;
        return this;
    }

    public void a(World world, int i0, int i1, int i2, int i3,
            EntityPlayer entityplayer) {}

    public void l(World world, int i0, int i1, int i2, int i3) {}

    public void g(World world, int i0, int i1, int i2) {}

    public boolean l() {
        return true;
    }

    public boolean a(Explosion explosion) {
        return true;
    }

    public boolean i(int i0) {
        return this.cF == i0;
    }

    public static boolean b(int i0, int i1) {
        return i0 == i1 ? true : (i0 != 0 && i1 != 0 && s[i0] != null && s[i1] != null ? s[i0].i(i1) : false);
    }

    public boolean q_() {
        return false;
    }

    public int b_(World world, int i0, int i1, int i2, int i3) {
        return 0;
    }

    protected Block d(String s0) {
        this.f = s0;
        return this;
    }

    static {
        Item.g[ag.cF] = (new ItemCloth(ag.cF - 256)).b("cloth");
        Item.g[cA.cF] = (new ItemCloth(cA.cF - 256)).b("clayHardenedStained");
        Item.g[cC.cF] = (new ItemCloth(cC.cF - 256)).b("woolCarpet");
        Item.g[O.cF] = (new ItemMultiTextureTile(O.cF - 256, O, BlockLog.b)).b("log");
        Item.g[C.cF] = (new ItemMultiTextureTile(C.cF - 256, C, BlockWood.a)).b("wood");
        Item.g[bq.cF] = (new ItemMultiTextureTile(bq.cF - 256, bq, BlockSilverfish.a)).b("monsterStoneEgg");
        Item.g[br.cF] = (new ItemMultiTextureTile(br.cF - 256, br, BlockStoneBrick.a)).b("stonebricksmooth");
        Item.g[V.cF] = (new ItemMultiTextureTile(V.cF - 256, V, BlockSandStone.a)).b("sandStone");
        Item.g[cw.cF] = (new ItemMultiTextureTile(cw.cF - 256, cw, BlockQuartz.a)).b("quartzBlock");
        Item.g[ap.cF] = (new ItemSlab(ap.cF - 256, ap, ao, false)).b("stoneSlab");
        Item.g[ao.cF] = (new ItemSlab(ao.cF - 256, ap, ao, true)).b("stoneSlab");
        Item.g[bT.cF] = (new ItemSlab(bT.cF - 256, bT, bS, false)).b("woodSlab");
        Item.g[bS.cF] = (new ItemSlab(bS.cF - 256, bT, bS, true)).b("woodSlab");
        Item.g[D.cF] = (new ItemMultiTextureTile(D.cF - 256, D, BlockSapling.a)).b("sapling");
        Item.g[P.cF] = (new ItemLeaves(P.cF - 256)).b("leaves");
        Item.g[bz.cF] = new ItemColored(bz.cF - 256, false);
        Item.g[ac.cF] = (new ItemColored(ac.cF - 256, true)).a(new String[]{
                "shrub", "grass", "fern" });
        Item.g[aX.cF] = new ItemSnow(aX.cF - 256, aX);
        Item.g[bE.cF] = new ItemLilyPad(bE.cF - 256);
        Item.g[ae.cF] = new ItemPiston(ae.cF - 256);
        Item.g[aa.cF] = new ItemPiston(aa.cF - 256);
        Item.g[cg.cF] = (new ItemMultiTextureTile(cg.cF - 256, cg, BlockWall.a)).b("cobbleWall");
        Item.g[cm.cF] = (new ItemAnvilBlock(cm)).b("anvil");

        for (int i0 = 0; i0 < 256; ++i0) {
            if (s[i0] != null) {
                if (Item.g[i0] == null) {
                    Item.g[i0] = new ItemBlock(i0 - 256);
                    s[i0].s_();
                }

                boolean flag0 = false;

                if (i0 > 0 && s[i0].d() == 10) {
                    flag0 = true;
                }

                if (i0 > 0 && s[i0] instanceof BlockHalfSlab) {
                    flag0 = true;
                }

                if (i0 == aF.cF) {
                    flag0 = true;
                }

                if (v[i0]) {
                    flag0 = true;
                }

                if (u[i0] == 0) {
                    flag0 = true;
                }

                x[i0] = flag0;
            }
        }

        v[0] = true;
        StatList.b();
    }
}
