package net.minecraft.server;

import java.util.List;
import java.util.Random;

import net.canarymod.Canary;
import net.canarymod.hook.world.BlockDropXpHook;

public class Block {
    private CreativeTabs a;
    public static final StepSound f = new StepSound("stone", 1.0F, 1.0F);
    public static final StepSound g = new StepSound("wood", 1.0F, 1.0F);
    public static final StepSound h = new StepSound("gravel", 1.0F, 1.0F);
    public static final StepSound i = new StepSound("grass", 1.0F, 1.0F);
    public static final StepSound j = new StepSound("stone", 1.0F, 1.0F);
    public static final StepSound k = new StepSound("stone", 1.0F, 1.5F);
    public static final StepSound l = new StepSoundStone("stone", 1.0F, 1.0F);
    public static final StepSound m = new StepSound("cloth", 1.0F, 1.0F);
    public static final StepSound n = new StepSound("sand", 1.0F, 1.0F);
    public static final StepSound o = new StepSound("snow", 1.0F, 1.0F);
    public static final StepSound p = new StepSoundSand("ladder", 1.0F, 1.0F);
    public static final StepSound q = new StepSoundAnvil("anvil", 0.3F, 1.0F);
    public static final Block[] r = new Block[4096];
    public static final boolean[] s = new boolean[4096];
    public static final int[] t = new int[4096];
    public static final boolean[] u = new boolean[4096];
    public static final int[] v = new int[4096];
    public static boolean[] w = new boolean[4096];
    public static final Block x = (new BlockStone(1)).c(1.5F).b(10.0F).a(j).c("stone");
    public static final BlockGrass y = (BlockGrass) (new BlockGrass(2)).c(0.6F).a(i).c("grass");
    public static final Block z = (new BlockDirt(3)).c(0.5F).a(h).c("dirt");
    public static final Block A = (new Block(4, Material.e)).c(2.0F).b(10.0F).a(j).c("stonebrick").a(CreativeTabs.b);
    public static final Block B = (new BlockWood(5)).c(2.0F).b(5.0F).a(g).c("wood");
    public static final Block C = (new BlockSapling(6)).c(0.0F).a(i).c("sapling");
    public static final Block D = (new Block(7, Material.e)).r().b(6000000.0F).a(j).c("bedrock").D().a(CreativeTabs.b);
    public static final BlockFluid E = (BlockFluid) (new BlockFlowing(8, Material.h)).c(100.0F).k(3).c("water").D();
    public static final Block F = (new BlockStationary(9, Material.h)).c(100.0F).k(3).c("water").D();
    public static final BlockFluid G = (BlockFluid) (new BlockFlowing(10, Material.i)).c(0.0F).a(1.0F).c("lava").D();
    public static final Block H = (new BlockStationary(11, Material.i)).c(100.0F).a(1.0F).c("lava").D();
    public static final Block I = (new BlockSand(12)).c(0.5F).a(n).c("sand");
    public static final Block J = (new BlockGravel(13)).c(0.6F).a(h).c("gravel");
    public static final Block K = (new BlockOre(14)).c(3.0F).b(5.0F).a(j).c("oreGold");
    public static final Block L = (new BlockOre(15)).c(3.0F).b(5.0F).a(j).c("oreIron");
    public static final Block M = (new BlockOre(16)).c(3.0F).b(5.0F).a(j).c("oreCoal");
    public static final Block N = (new BlockLog(17)).c(2.0F).a(g).c("log");
    public static final BlockLeaves O = (BlockLeaves) (new BlockLeaves(18)).c(0.2F).k(1).a(i).c("leaves");
    public static final Block P = (new BlockSponge(19)).c(0.6F).a(i).c("sponge");
    public static final Block Q = (new BlockGlass(20, Material.r, false)).c(0.3F).a(l).c("glass");
    public static final Block R = (new BlockOre(21)).c(3.0F).b(5.0F).a(j).c("oreLapis");
    public static final Block S = (new Block(22, Material.e)).c(3.0F).b(5.0F).a(j).c("blockLapis").a(CreativeTabs.b);
    public static final Block T = (new BlockDispenser(23)).c(3.5F).a(j).c("dispenser");
    public static final Block U = (new BlockSandStone(24)).a(j).c(0.8F).c("sandStone");
    public static final Block V = (new BlockNote(25)).c(0.8F).c("musicBlock");
    public static final Block W = (new BlockBed(26)).c(0.2F).c("bed").D();
    public static final Block X = (new BlockRailPowered(27)).c(0.7F).a(k).c("goldenRail");
    public static final Block Y = (new BlockDetectorRail(28)).c(0.7F).a(k).c("detectorRail");
    public static final BlockPistonBase Z = (BlockPistonBase) (new BlockPistonBase(29, true)).c("pistonStickyBase");
    public static final Block aa = (new BlockWeb(30)).k(1).c(4.0F).c("web");
    public static final BlockTallGrass ab = (BlockTallGrass) (new BlockTallGrass(31)).c(0.0F).a(i).c("tallgrass");
    public static final BlockDeadBush ac = (BlockDeadBush) (new BlockDeadBush(32)).c(0.0F).a(i).c("deadbush");
    public static final BlockPistonBase ad = (BlockPistonBase) (new BlockPistonBase(33, false)).c("pistonBase");
    public static final BlockPistonExtension ae = new BlockPistonExtension(34);
    public static final Block af = (new BlockCloth()).c(0.8F).a(m).c("cloth");
    public static final BlockPistonMoving ag = new BlockPistonMoving(36);
    public static final BlockFlower ah = (BlockFlower) (new BlockFlower(37)).c(0.0F).a(i).c("flower");
    public static final BlockFlower ai = (BlockFlower) (new BlockFlower(38)).c(0.0F).a(i).c("rose");
    public static final BlockFlower aj = (BlockFlower) (new BlockMushroom(39, "mushroom_brown")).c(0.0F).a(i).a(0.125F).c("mushroom");
    public static final BlockFlower ak = (BlockFlower) (new BlockMushroom(40, "mushroom_red")).c(0.0F).a(i).c("mushroom");
    public static final Block al = (new BlockOreStorage(41)).c(3.0F).b(10.0F).a(k).c("blockGold");
    public static final Block am = (new BlockOreStorage(42)).c(5.0F).b(10.0F).a(k).c("blockIron");
    public static final BlockHalfSlab an = (BlockHalfSlab) (new BlockStep(43, true)).c(2.0F).b(10.0F).a(j).c("stoneSlab");
    public static final BlockHalfSlab ao = (BlockHalfSlab) (new BlockStep(44, false)).c(2.0F).b(10.0F).a(j).c("stoneSlab");
    public static final Block ap = (new Block(45, Material.e)).c(2.0F).b(10.0F).a(j).c("brick").a(CreativeTabs.b);
    public static final Block aq = (new BlockTNT(46)).c(0.0F).a(i).c("tnt");
    public static final Block ar = (new BlockBookshelf(47)).c(1.5F).a(g).c("bookshelf");
    public static final Block as = (new Block(48, Material.e)).c(2.0F).b(10.0F).a(j).c("stoneMoss").a(CreativeTabs.b);
    public static final Block at = (new BlockObsidian(49)).c(50.0F).b(2000.0F).a(j).c("obsidian");
    public static final Block au = (new BlockTorch(50)).c(0.0F).a(0.9375F).a(g).c("torch");
    public static final BlockFire av = (BlockFire) (new BlockFire(51)).c(0.0F).a(1.0F).a(g).c("fire").D();
    public static final Block aw = (new BlockMobSpawner(52)).c(5.0F).a(k).c("mobSpawner").D();
    public static final Block ax = (new BlockStairs(53, B, 0)).c("stairsWood");
    public static final BlockChest ay = (BlockChest) (new BlockChest(54, 0)).c(2.5F).a(g).c("chest");
    public static final BlockRedstoneWire az = (BlockRedstoneWire) (new BlockRedstoneWire(55)).c(0.0F).a(f).c("redstoneDust").D();
    public static final Block aA = (new BlockOre(56)).c(3.0F).b(5.0F).a(j).c("oreDiamond");
    public static final Block aB = (new BlockOreStorage(57)).c(5.0F).b(10.0F).a(k).c("blockDiamond");
    public static final Block aC = (new BlockWorkbench(58)).c(2.5F).a(g).c("workbench");
    public static final Block aD = (new BlockCrops(59)).c("crops");
    public static final Block aE = (new BlockFarmland(60)).c(0.6F).a(h).c("farmland");
    public static final Block aF = (new BlockFurnace(61, false)).c(3.5F).a(j).c("furnace").a(CreativeTabs.c);
    public static final Block aG = (new BlockFurnace(62, true)).c(3.5F).a(j).a(0.875F).c("furnace");
    public static final Block aH = (new BlockSign(63, TileEntitySign.class, true)).c(1.0F).a(g).c("sign").D();
    public static final Block aI = (new BlockDoor(64, Material.d)).c(3.0F).a(g).c("doorWood").D();
    public static final Block aJ = (new BlockLadder(65)).c(0.4F).a(p).c("ladder");
    public static final Block aK = (new BlockRail(66)).c(0.7F).a(k).c("rail");
    public static final Block aL = (new BlockStairs(67, A, 0)).c("stairsStone");
    public static final Block aM = (new BlockSign(68, TileEntitySign.class, false)).c(1.0F).a(g).c("sign").D();
    public static final Block aN = (new BlockLever(69)).c(0.5F).a(g).c("lever");
    public static final Block aO = (new BlockPressurePlate(70, "stone", Material.e, EnumMobType.b)).c(0.5F).a(j).c("pressurePlate");
    public static final Block aP = (new BlockDoor(71, Material.f)).c(5.0F).a(k).c("doorIron").D();
    public static final Block aQ = (new BlockPressurePlate(72, "wood", Material.d, EnumMobType.a)).c(0.5F).a(g).c("pressurePlate");
    public static final Block aR = (new BlockRedstoneOre(73, false)).c(3.0F).b(5.0F).a(j).c("oreRedstone").a(CreativeTabs.b);
    public static final Block aS = (new BlockRedstoneOre(74, true)).a(0.625F).c(3.0F).b(5.0F).a(j).c("oreRedstone");
    public static final Block aT = (new BlockRedstoneTorch(75, false)).c(0.0F).a(g).c("notGate");
    public static final Block aU = (new BlockRedstoneTorch(76, true)).c(0.0F).a(0.5F).a(g).c("notGate").a(CreativeTabs.d);
    public static final Block aV = (new BlockButtonStone(77)).c(0.5F).a(j).c("button");
    public static final Block aW = (new BlockSnow(78)).c(0.1F).a(o).c("snow").k(0);
    public static final Block aX = (new BlockIce(79)).c(0.5F).k(3).a(l).c("ice");
    public static final Block aY = (new BlockSnowBlock(80)).c(0.2F).a(o).c("snow");
    public static final Block aZ = (new BlockCactus(81)).c(0.4F).a(m).c("cactus");
    public static final Block ba = (new BlockClay(82)).c(0.6F).a(h).c("clay");
    public static final Block bb = (new BlockReed(83)).c(0.0F).a(i).c("reeds").D();
    public static final Block bc = (new BlockJukeBox(84)).c(2.0F).b(10.0F).a(j).c("jukebox");
    public static final Block bd = (new BlockFence(85, "wood", Material.d)).c(2.0F).b(5.0F).a(g).c("fence");
    public static final Block be = (new BlockPumpkin(86, false)).c(1.0F).a(g).c("pumpkin");
    public static final Block bf = (new BlockNetherrack(87)).c(0.4F).a(j).c("hellrock");
    public static final Block bg = (new BlockSoulSand(88)).c(0.5F).a(n).c("hellsand");
    public static final Block bh = (new BlockGlowStone(89, Material.r)).c(0.3F).a(l).a(1.0F).c("lightgem");
    public static final BlockPortal bi = (BlockPortal) (new BlockPortal(90)).c(-1.0F).a(l).a(0.75F).c("portal");
    public static final Block bj = (new BlockPumpkin(91, true)).c(1.0F).a(g).a(1.0F).c("litpumpkin");
    public static final Block bk = (new BlockCake(92)).c(0.5F).a(m).c("cake").D();
    public static final BlockRedstoneRepeater bl = (BlockRedstoneRepeater) (new BlockRedstoneRepeater(93, false)).c(0.0F).a(g).c("diode").D();
    public static final BlockRedstoneRepeater bm = (BlockRedstoneRepeater) (new BlockRedstoneRepeater(94, true)).c(0.0F).a(0.625F).a(g).c("diode").D();
    public static final Block bn = (new BlockLockedChest(95)).c(0.0F).a(1.0F).a(g).c("lockedchest").b(true);
    public static final Block bo = (new BlockTrapDoor(96, Material.d)).c(3.0F).a(g).c("trapdoor").D();
    public static final Block bp = (new BlockSilverfish(97)).c(0.75F).c("monsterStoneEgg");
    public static final Block bq = (new BlockStoneBrick(98)).c(1.5F).b(10.0F).a(j).c("stonebricksmooth");
    public static final Block br = (new BlockMushroomCap(99, Material.d, 0)).c(0.2F).a(g).c("mushroom");
    public static final Block bs = (new BlockMushroomCap(100, Material.d, 1)).c(0.2F).a(g).c("mushroom");
    public static final Block bt = (new BlockPane(101, "fenceIron", "fenceIron", Material.f, true)).c(5.0F).b(10.0F).a(k).c("fenceIron");
    public static final Block bu = (new BlockPane(102, "glass", "thinglass_top", Material.r, false)).c(0.3F).a(l).c("thinGlass");
    public static final Block bv = (new BlockMelon(103)).c(1.0F).a(g).c("melon");
    public static final Block bw = (new BlockStem(104, be)).c(0.0F).a(g).c("pumpkinStem");
    public static final Block bx = (new BlockStem(105, bv)).c(0.0F).a(g).c("pumpkinStem");
    public static final Block by = (new BlockVine(106)).c(0.2F).a(i).c("vine");
    public static final Block bz = (new BlockFenceGate(107)).c(2.0F).b(5.0F).a(g).c("fenceGate");
    public static final Block bA = (new BlockStairs(108, ap, 0)).c("stairsBrick");
    public static final Block bB = (new BlockStairs(109, bq, 0)).c("stairsStoneBrickSmooth");
    public static final BlockMycelium bC = (BlockMycelium) (new BlockMycelium(110)).c(0.6F).a(i).c("mycel");
    public static final Block bD = (new BlockLilyPad(111)).c(0.0F).a(i).c("waterlily");
    public static final Block bE = (new Block(112, Material.e)).c(2.0F).b(10.0F).a(j).c("netherBrick").a(CreativeTabs.b);
    public static final Block bF = (new BlockFence(113, "netherBrick", Material.e)).c(2.0F).b(10.0F).a(j).c("netherFence");
    public static final Block bG = (new BlockStairs(114, bE, 0)).c("stairsNetherBrick");
    public static final Block bH = (new BlockNetherStalk(115)).c("netherStalk");
    public static final Block bI = (new BlockEnchantmentTable(116)).c(5.0F).b(2000.0F).c("enchantmentTable");
    public static final Block bJ = (new BlockBrewingStand(117)).c(0.5F).a(0.125F).c("brewingStand");
    public static final BlockCauldron bK = (BlockCauldron) (new BlockCauldron(118)).c(2.0F).c("cauldron");
    public static final Block bL = (new BlockEndPortal(119, Material.C)).c(-1.0F).b(6000000.0F);
    public static final Block bM = (new BlockEndPortalFrame(120)).a(l).a(0.125F).c(-1.0F).c("endPortalFrame").b(6000000.0F).a(CreativeTabs.c);
    public static final Block bN = (new Block(121, Material.e)).c(3.0F).b(15.0F).a(j).c("whiteStone").a(CreativeTabs.b);
    public static final Block bO = (new BlockDragonEgg(122)).c(3.0F).b(15.0F).a(j).a(0.125F).c("dragonEgg");
    public static final Block bP = (new BlockRedstoneLight(123, false)).c(0.3F).a(l).c("redstoneLight").a(CreativeTabs.d);
    public static final Block bQ = (new BlockRedstoneLight(124, true)).c(0.3F).a(l).c("redstoneLight");
    public static final BlockHalfSlab bR = (BlockHalfSlab) (new BlockWoodSlab(125, true)).c(2.0F).b(5.0F).a(g).c("woodSlab");
    public static final BlockHalfSlab bS = (BlockHalfSlab) (new BlockWoodSlab(126, false)).c(2.0F).b(5.0F).a(g).c("woodSlab");
    public static final Block bT = (new BlockCocoa(127)).c(0.2F).b(5.0F).a(g).c("cocoa");
    public static final Block bU = (new BlockStairs(128, U, 0)).c("stairsSandStone");
    public static final Block bV = (new BlockOre(129)).c(3.0F).b(5.0F).a(j).c("oreEmerald");
    public static final Block bW = (new BlockEnderChest(130)).c(22.5F).b(1000.0F).a(j).c("enderChest").a(0.5F);
    public static final BlockTripWireSource bX = (BlockTripWireSource) (new BlockTripWireSource(131)).c("tripWireSource");
    public static final Block bY = (new BlockTripWire(132)).c("tripWire");
    public static final Block bZ = (new BlockOreStorage(133)).c(5.0F).b(10.0F).a(k).c("blockEmerald");
    public static final Block ca = (new BlockStairs(134, B, 1)).c("stairsWoodSpruce");
    public static final Block cb = (new BlockStairs(135, B, 2)).c("stairsWoodBirch");
    public static final Block cc = (new BlockStairs(136, B, 3)).c("stairsWoodJungle");
    public static final Block cd = (new BlockCommandBlock(137)).c("commandBlock");
    public static final BlockBeacon ce = (BlockBeacon) (new BlockBeacon(138)).c("beacon").a(1.0F);
    public static final Block cf = (new BlockWall(139, A)).c("cobbleWall");
    public static final Block cg = (new BlockFlowerPot(140)).c(0.0F).a(f).c("flowerPot");
    public static final Block ch = (new BlockCarrot(141)).c("carrots");
    public static final Block ci = (new BlockPotato(142)).c("potatoes");
    public static final Block cj = (new BlockButtonWood(143)).c(0.5F).a(g).c("button");
    public static final Block ck = (new BlockSkull(144)).c(1.0F).a(j).c("skull");
    public static final Block cl = (new BlockAnvil(145)).c(5.0F).a(q).b(2000.0F).c("anvil");
    public static final Block cm = (new BlockChest(146, 1)).c(2.5F).a(g).c("chestTrap");
    public static final Block cn = (new BlockPressurePlateWeighted(147, "blockGold", Material.f, 64)).c(0.5F).a(g).c("weightedPlate_light");
    public static final Block co = (new BlockPressurePlateWeighted(148, "blockIron", Material.f, 640)).c(0.5F).a(g).c("weightedPlate_heavy");
    public static final BlockComparator cp = (BlockComparator) (new BlockComparator(149, false)).c(0.0F).a(g).c("comparator").D();
    public static final BlockComparator cq = (BlockComparator) (new BlockComparator(150, true)).c(0.0F).a(0.625F).a(g).c("comparator").D();
    public static final BlockDaylightDetector cr = (BlockDaylightDetector) (new BlockDaylightDetector(151)).c(0.2F).a(g).c("daylightDetector");
    public static final Block cs = (new BlockPoweredOre(152)).c(5.0F).b(10.0F).a(k).c("blockRedstone");
    public static final Block ct = (new BlockOre(153)).c(3.0F).b(5.0F).a(j).c("netherquartz");
    public static final BlockHopper cu = (BlockHopper) (new BlockHopper(154)).c(3.0F).b(8.0F).a(g).c("hopper");
    public static final Block cv = (new BlockQuartz(155)).a(j).c(0.8F).c("quartzBlock");
    public static final Block cw = (new BlockStairs(156, cv, 0)).c("stairsQuartz");
    public static final Block cx = (new BlockRailPowered(157)).c(0.7F).a(k).c("activatorRail");
    public static final Block cy = (new BlockDropper(158)).c(3.5F).a(j).c("dropper");
    public final int cz;
    protected float cA;
    protected float cB;
    protected boolean cC = true;
    protected boolean cD = true;
    protected boolean cE;
    protected boolean cF;
    protected double cG;
    protected double cH;
    protected double cI;
    protected double cJ;
    protected double cK;
    protected double cL;
    public StepSound cM;
    public float cN;
    public final Material cO;
    public float cP;
    private String b;

    protected Block(int i0, Material material) {
        this.cM = f;
        this.cN = 1.0F;
        this.cP = 0.6F;
        if (r[i0] != null) {
            throw new IllegalArgumentException("Slot " + i0 + " is already occupied by " + r[i0] + " when adding " + this);
        } else {
            this.cO = material;
            r[i0] = this;
            this.cz = i0;
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            s[i0] = this.c();
            t[i0] = this.c() ? 255 : 0;
            u[i0] = !material.b();
        }
    }

    protected void s_() {
    }

    protected Block a(StepSound stepsound) {
        this.cM = stepsound;
        return this;
    }

    protected Block k(int i0) {
        t[this.cz] = i0;
        return this;
    }

    protected Block a(float f0) {
        v[this.cz] = (int) (15.0F * f0);
        return this;
    }

    protected Block b(float f0) {
        this.cB = f0 * 3.0F;
        return this;
    }

    public static boolean l(int i0) {
        Block block = r[i0];

        return block == null ? false : block.cO.k() && block.b() && !block.f();
    }

    public boolean b() {
        return true;
    }

    public boolean b(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        return !this.cO.c();
    }

    public int d() {
        return 0;
    }

    protected Block c(float f0) {
        this.cA = f0;
        if (this.cB < f0 * 5.0F) {
            this.cB = f0 * 5.0F;
        }

        return this;
    }

    protected Block r() {
        this.c(-1.0F);
        return this;
    }

    public float l(World world, int i0, int i1, int i2) {
        return this.cA;
    }

    protected Block b(boolean flag0) {
        this.cE = flag0;
        return this;
    }

    public boolean s() {
        return this.cE;
    }

    public boolean t() {
        return this.cF;
    }

    protected final void a(float f0, float f1, float f2, float f3, float f4, float f5) {
        this.cG = (double) f0;
        this.cH = (double) f1;
        this.cI = (double) f2;
        this.cJ = (double) f3;
        this.cK = (double) f4;
        this.cL = (double) f5;
    }

    public boolean a_(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        return iblockaccess.g(i0, i1, i2).a();
    }

    public void a(World world, int i0, int i1, int i2, AxisAlignedBB axisalignedbb, List list, Entity entity) {
        AxisAlignedBB axisalignedbb1 = this.b(world, i0, i1, i2);

        if (axisalignedbb1 != null && axisalignedbb.a(axisalignedbb1)) {
            list.add(axisalignedbb1);
        }
    }

    public AxisAlignedBB b(World world, int i0, int i1, int i2) {
        return AxisAlignedBB.a().a((double) i0 + this.cG, (double) i1 + this.cH, (double) i2 + this.cI, (double) i0 + this.cJ, (double) i1 + this.cK, (double) i2 + this.cL);
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

    public void a(World world, int i0, int i1, int i2, Random random) {
    }

    public void g(World world, int i0, int i1, int i2, int i3) {
    }

    public void a(World world, int i0, int i1, int i2, int i3) {
    }

    public int a(World world) {
        return 10;
    }

    public void a(World world, int i0, int i1, int i2) {
    }

    public void a(World world, int i0, int i1, int i2, int i3, int i4) {
    }

    public int a(Random random) {
        return 1;
    }

    public int a(int i0, Random random, int i1) {
        return this.cz;
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
        if (!world.I && world.N().b("doTileDrops")) {
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
            BlockDropXpHook hook = new BlockDropXpHook(block, i3);
            Canary.hooks().callHook(hook);
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
        return this.cB / 5.0F;
    }

    public MovingObjectPosition a(World world, int i0, int i1, int i2, Vec3 vec3, Vec3 vec31) {
        this.a((IBlockAccess) world, i0, i1, i2);
        vec3 = vec3.c((double) (-i0), (double) (-i1), (double) (-i2));
        vec31 = vec31.c((double) (-i0), (double) (-i1), (double) (-i2));
        Vec3 vec32 = vec3.b(vec31, this.cG);
        Vec3 vec33 = vec3.b(vec31, this.cJ);
        Vec3 vec34 = vec3.c(vec31, this.cH);
        Vec3 vec35 = vec3.c(vec31, this.cK);
        Vec3 vec36 = vec3.d(vec31, this.cI);
        Vec3 vec37 = vec3.d(vec31, this.cL);

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
        return vec3 == null ? false : vec3.d >= this.cH && vec3.d <= this.cK && vec3.e >= this.cI && vec3.e <= this.cL;
    }

    private boolean b(Vec3 vec3) {
        return vec3 == null ? false : vec3.c >= this.cG && vec3.c <= this.cJ && vec3.e >= this.cI && vec3.e <= this.cL;
    }

    private boolean c(Vec3 vec3) {
        return vec3 == null ? false : vec3.c >= this.cG && vec3.c <= this.cJ && vec3.d >= this.cH && vec3.d <= this.cK;
    }

    public void a(World world, int i0, int i1, int i2, Explosion explosion) {
    }

    public boolean a(World world, int i0, int i1, int i2, int i3, ItemStack itemstack) {
        return this.c(world, i0, i1, i2, i3);
    }

    public boolean c(World world, int i0, int i1, int i2, int i3) {
        return this.c(world, i0, i1, i2);
    }

    public boolean c(World world, int i0, int i1, int i2) {
        int i3 = world.a(i0, i1, i2);

        return i3 == 0 || r[i3].cO.j();
    }

    public boolean a(World world, int i0, int i1, int i2, EntityPlayer entityplayer, int i3, float f0, float f1, float f2) {
        return false;
    }

    public void b(World world, int i0, int i1, int i2, Entity entity) {
    }

    public int a(World world, int i0, int i1, int i2, int i3, float f0, float f1, float f2, int i4) {
        return i4;
    }

    public void a(World world, int i0, int i1, int i2, EntityPlayer entityplayer) {
    }

    public void a(World world, int i0, int i1, int i2, Entity entity, Vec3 vec3) {
    }

    public void a(IBlockAccess iblockaccess, int i0, int i1, int i2) {
    }

    public final double u() {
        return this.cG;
    }

    public final double v() {
        return this.cJ;
    }

    public final double w() {
        return this.cH;
    }

    public final double x() {
        return this.cK;
    }

    public final double y() {
        return this.cI;
    }

    public final double z() {
        return this.cL;
    }

    public int b(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        return 0;
    }

    public boolean f() {
        return false;
    }

    public void a(World world, int i0, int i1, int i2, Entity entity) {
    }

    public int c(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        return 0;
    }

    public void g() {
    }

    public void a(World world, EntityPlayer entityplayer, int i0, int i1, int i2, int i3) {
        entityplayer.a(StatList.C[this.cz], 1);
        entityplayer.j(0.025F);
        if (this.r_() && EnchantmentHelper.e(entityplayer)) {
            ItemStack itemstack = this.c_(i3);

            if (itemstack != null) {
                this.b(world, i0, i1, i2, itemstack);
            }
        } else {
            int i4 = EnchantmentHelper.f(entityplayer);

            this.c(world, i0, i1, i2, i3, i4);
        }
    }

    protected boolean r_() {
        return this.b() && !this.cF;
    }

    protected ItemStack c_(int i0) {
        int i1 = 0;

        if (this.cz >= 0 && this.cz < Item.f.length && Item.f[this.cz].m()) {
            i1 = i0;
        }

        return new ItemStack(this.cz, 1, i1);
    }

    public int a(int i0, Random random) {
        return this.a(random);
    }

    public boolean f(World world, int i0, int i1, int i2) {
        return true;
    }

    public void a(World world, int i0, int i1, int i2, EntityLiving entityliving, ItemStack itemstack) {
    }

    public void k(World world, int i0, int i1, int i2, int i3) {
    }

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

    public boolean C() {
        return this.cD;
    }

    protected Block D() {
        this.cD = false;
        return this;
    }

    public int h() {
        return this.cO.m();
    }

    public void a(World world, int i0, int i1, int i2, Entity entity, float f0) {
    }

    public int h(World world, int i0, int i1, int i2) {
        return this.a(world.h(i0, i1, i2));
    }

    public Block a(CreativeTabs creativetabs) {
        this.a = creativetabs;
        return this;
    }

    public void a(World world, int i0, int i1, int i2, int i3, EntityPlayer entityplayer) {
    }

    public void l(World world, int i0, int i1, int i2, int i3) {
    }

    public void g(World world, int i0, int i1, int i2) {
    }

    public boolean l() {
        return true;
    }

    public boolean a(Explosion explosion) {
        return true;
    }

    public boolean i(int i0) {
        return this.cz == i0;
    }

    public static boolean b(int i0, int i1) {
        return i0 == i1 ? true : (i0 != 0 && i1 != 0 && r[i0] != null && r[i1] != null ? r[i0].i(i1) : false);
    }

    public boolean q_() {
        return false;
    }

    public int b_(World world, int i0, int i1, int i2, int i3) {
        return 0;
    }

    static {
        Item.f[af.cz] = (new ItemCloth(af.cz - 256)).b("cloth");
        Item.f[N.cz] = (new ItemMultiTextureTile(N.cz - 256, N, BlockLog.a)).b("log");
        Item.f[B.cz] = (new ItemMultiTextureTile(B.cz - 256, B, BlockWood.a)).b("wood");
        Item.f[bp.cz] = (new ItemMultiTextureTile(bp.cz - 256, bp, BlockSilverfish.a)).b("monsterStoneEgg");
        Item.f[bq.cz] = (new ItemMultiTextureTile(bq.cz - 256, bq, BlockStoneBrick.a)).b("stonebricksmooth");
        Item.f[U.cz] = (new ItemMultiTextureTile(U.cz - 256, U, BlockSandStone.a)).b("sandStone");
        Item.f[cv.cz] = (new ItemMultiTextureTile(cv.cz - 256, cv, BlockQuartz.a)).b("quartzBlock");
        Item.f[ao.cz] = (new ItemSlab(ao.cz - 256, ao, an, false)).b("stoneSlab");
        Item.f[an.cz] = (new ItemSlab(an.cz - 256, ao, an, true)).b("stoneSlab");
        Item.f[bS.cz] = (new ItemSlab(bS.cz - 256, bS, bR, false)).b("woodSlab");
        Item.f[bR.cz] = (new ItemSlab(bR.cz - 256, bS, bR, true)).b("woodSlab");
        Item.f[C.cz] = (new ItemMultiTextureTile(C.cz - 256, C, BlockSapling.a)).b("sapling");
        Item.f[O.cz] = (new ItemLeaves(O.cz - 256)).b("leaves");
        Item.f[by.cz] = new ItemColored(by.cz - 256, false);
        Item.f[ab.cz] = (new ItemColored(ab.cz - 256, true)).a(new String[]{"shrub", "grass", "fern"});
        Item.f[aW.cz] = new ItemSnow(aW.cz - 256, aW);
        Item.f[bD.cz] = new ItemLilyPad(bD.cz - 256);
        Item.f[ad.cz] = new ItemPiston(ad.cz - 256);
        Item.f[Z.cz] = new ItemPiston(Z.cz - 256);
        Item.f[cf.cz] = (new ItemMultiTextureTile(cf.cz - 256, cf, BlockWall.a)).b("cobbleWall");
        Item.f[cl.cz] = (new ItemAnvilBlock(cl)).b("anvil");

        for (int i0 = 0; i0 < 256; ++i0) {
            if (r[i0] != null) {
                if (Item.f[i0] == null) {
                    Item.f[i0] = new ItemBlock(i0 - 256);
                    r[i0].s_();
                }

                boolean flag0 = false;

                if (i0 > 0 && r[i0].d() == 10) {
                    flag0 = true;
                }

                if (i0 > 0 && r[i0] instanceof BlockHalfSlab) {
                    flag0 = true;
                }

                if (i0 == aE.cz) {
                    flag0 = true;
                }

                if (u[i0]) {
                    flag0 = true;
                }

                if (t[i0] == 0) {
                    flag0 = true;
                }

                w[i0] = flag0;
            }
        }

        u[0] = true;
        StatList.b();
    }
}
