package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlockBed;
import net.minecraft.server.OBlockBookshelf;
import net.minecraft.server.OBlockBrewingStand;
import net.minecraft.server.OBlockButton;
import net.minecraft.server.OBlockCactus;
import net.minecraft.server.OBlockCake;
import net.minecraft.server.OBlockCauldron;
import net.minecraft.server.OBlockChest;
import net.minecraft.server.OBlockClay;
import net.minecraft.server.OBlockCloth;
import net.minecraft.server.OBlockCrops;
import net.minecraft.server.OBlockDeadBush;
import net.minecraft.server.OBlockDetectorRail;
import net.minecraft.server.OBlockDirt;
import net.minecraft.server.OBlockDispenser;
import net.minecraft.server.OBlockDoor;
import net.minecraft.server.OBlockDragonEgg;
import net.minecraft.server.OBlockEnchantmentTable;
import net.minecraft.server.OBlockEndPortal;
import net.minecraft.server.OBlockEndPortalFrame;
import net.minecraft.server.OBlockFarmland;
import net.minecraft.server.OBlockFence;
import net.minecraft.server.OBlockFenceGate;
import net.minecraft.server.OBlockFire;
import net.minecraft.server.OBlockFlower;
import net.minecraft.server.OBlockFlowing;
import net.minecraft.server.OBlockFurnace;
import net.minecraft.server.OBlockGlass;
import net.minecraft.server.OBlockGlowStone;
import net.minecraft.server.OBlockGrass;
import net.minecraft.server.OBlockGravel;
import net.minecraft.server.OBlockIce;
import net.minecraft.server.OBlockJukeBox;
import net.minecraft.server.OBlockLadder;
import net.minecraft.server.OBlockLeaves;
import net.minecraft.server.OBlockLever;
import net.minecraft.server.OBlockLilyPad;
import net.minecraft.server.OBlockLockedChest;
import net.minecraft.server.OBlockLog;
import net.minecraft.server.OBlockMelon;
import net.minecraft.server.OBlockMobSpawner;
import net.minecraft.server.OBlockMushroom;
import net.minecraft.server.OBlockMushroomCap;
import net.minecraft.server.OBlockMycelium;
import net.minecraft.server.OBlockNetherStalk;
import net.minecraft.server.OBlockNetherrack;
import net.minecraft.server.OBlockNote;
import net.minecraft.server.OBlockObsidian;
import net.minecraft.server.OBlockOre;
import net.minecraft.server.OBlockOreStorage;
import net.minecraft.server.OBlockPane;
import net.minecraft.server.OBlockPistonBase;
import net.minecraft.server.OBlockPistonExtension;
import net.minecraft.server.OBlockPistonMoving;
import net.minecraft.server.OBlockPortal;
import net.minecraft.server.OBlockPressurePlate;
import net.minecraft.server.OBlockPumpkin;
import net.minecraft.server.OBlockRail;
import net.minecraft.server.OBlockRedstoneLight;
import net.minecraft.server.OBlockRedstoneOre;
import net.minecraft.server.OBlockRedstoneRepeater;
import net.minecraft.server.OBlockRedstoneTorch;
import net.minecraft.server.OBlockRedstoneWire;
import net.minecraft.server.OBlockReed;
import net.minecraft.server.OBlockSand;
import net.minecraft.server.OBlockSandStone;
import net.minecraft.server.OBlockSapling;
import net.minecraft.server.OBlockSign;
import net.minecraft.server.OBlockSilverfish;
import net.minecraft.server.OBlockSnow;
import net.minecraft.server.OBlockSnowBlock;
import net.minecraft.server.OBlockSoulSand;
import net.minecraft.server.OBlockSponge;
import net.minecraft.server.OBlockStairs;
import net.minecraft.server.OBlockStationary;
import net.minecraft.server.OBlockStem;
import net.minecraft.server.OBlockStep;
import net.minecraft.server.OBlockStone;
import net.minecraft.server.OBlockStoneBrick;
import net.minecraft.server.OBlockTNT;
import net.minecraft.server.OBlockTallGrass;
import net.minecraft.server.OBlockTorch;
import net.minecraft.server.OBlockTrapDoor;
import net.minecraft.server.OBlockVine;
import net.minecraft.server.OBlockWeb;
import net.minecraft.server.OBlockWood;
import net.minecraft.server.OBlockWorkbench;
import net.minecraft.server.OEnchantmentHelper;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityItem;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEnumMobType;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemBlock;
import net.minecraft.server.OItemCloth;
import net.minecraft.server.OItemColored;
import net.minecraft.server.OItemLeaves;
import net.minecraft.server.OItemLilyPad;
import net.minecraft.server.OItemMetadata;
import net.minecraft.server.OItemPiston;
import net.minecraft.server.OItemSapling;
import net.minecraft.server.OItemSlab;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OMovingObjectPosition;
import net.minecraft.server.OStatCollector;
import net.minecraft.server.OStatList;
import net.minecraft.server.OStepSound;
import net.minecraft.server.OStepSoundSand;
import net.minecraft.server.OStepSoundStone;
import net.minecraft.server.OTileEntitySign;
import net.minecraft.server.OVec3D;
import net.minecraft.server.OWorld;

public class OBlock {

    public static final OStepSound d = new OStepSound("stone", 1.0F, 1.0F);
    public static final OStepSound e = new OStepSound("wood", 1.0F, 1.0F);
    public static final OStepSound f = new OStepSound("gravel", 1.0F, 1.0F);
    public static final OStepSound g = new OStepSound("grass", 1.0F, 1.0F);
    public static final OStepSound h = new OStepSound("stone", 1.0F, 1.0F);
    public static final OStepSound i = new OStepSound("stone", 1.0F, 1.5F);
    public static final OStepSound j = new OStepSoundStone("stone", 1.0F, 1.0F);
    public static final OStepSound k = new OStepSound("cloth", 1.0F, 1.0F);
    public static final OStepSound l = new OStepSoundSand("sand", 1.0F, 1.0F);
    public static final OBlock[] m = new OBlock[4096];
    public static final boolean[] n = new boolean[4096];
    public static final int[] o = new int[4096];
    public static final boolean[] p = new boolean[4096];
    public static final int[] q = new int[4096];
    public static final boolean[] r = new boolean[4096];
    public static boolean[] s = new boolean[4096];
    public static final OBlock t = (new OBlockStone(1, 1)).c(1.5F).b(10.0F).a(h).a("stone");
    public static final OBlockGrass u = (OBlockGrass) (new OBlockGrass(2)).c(0.6F).a(g).a("grass");
    public static final OBlock v = (new OBlockDirt(3, 2)).c(0.5F).a(f).a("dirt");
    public static final OBlock w = (new OBlock(4, 16, OMaterial.e)).c(2.0F).b(10.0F).a(h).a("stonebrick");
    public static final OBlock x = (new OBlockWood(5)).c(2.0F).b(5.0F).a(e).a("wood").j();
    public static final OBlock y = (new OBlockSapling(6, 15)).c(0.0F).a(g).a("sapling").j();
    public static final OBlock z = (new OBlock(7, 17, OMaterial.e)).l().b(6000000.0F).a(h).a("bedrock").s();
    public static final OBlock A = (new OBlockFlowing(8, OMaterial.g)).c(100.0F).f(3).a("water").s().j();
    public static final OBlock B = (new OBlockStationary(9, OMaterial.g)).c(100.0F).f(3).a("water").s().j();
    public static final OBlock C = (new OBlockFlowing(10, OMaterial.h)).c(0.0F).a(1.0F).f(255).a("lava").s().j();
    public static final OBlock D = (new OBlockStationary(11, OMaterial.h)).c(100.0F).a(1.0F).f(255).a("lava").s().j();
    public static final OBlock E = (new OBlockSand(12, 18)).c(0.5F).a(l).a("sand");
    public static final OBlock F = (new OBlockGravel(13, 19)).c(0.6F).a(f).a("gravel");
    public static final OBlock G = (new OBlockOre(14, 32)).c(3.0F).b(5.0F).a(h).a("oreGold");
    public static final OBlock H = (new OBlockOre(15, 33)).c(3.0F).b(5.0F).a(h).a("oreIron");
    public static final OBlock I = (new OBlockOre(16, 34)).c(3.0F).b(5.0F).a(h).a("oreCoal");
    public static final OBlock J = (new OBlockLog(17)).c(2.0F).a(e).a("log").j();
    public static final OBlockLeaves K = (OBlockLeaves) (new OBlockLeaves(18, 52)).c(0.2F).f(1).a(g).a("leaves").j();
    public static final OBlock L = (new OBlockSponge(19)).c(0.6F).a(g).a("sponge");
    public static final OBlock M = (new OBlockGlass(20, 49, OMaterial.q, false)).c(0.3F).a(j).a("glass");
    public static final OBlock N = (new OBlockOre(21, 160)).c(3.0F).b(5.0F).a(h).a("oreLapis");
    public static final OBlock O = (new OBlock(22, 144, OMaterial.e)).c(3.0F).b(5.0F).a(h).a("blockLapis");
    public static final OBlock P = (new OBlockDispenser(23)).c(3.5F).a(h).a("dispenser").j();
    public static final OBlock Q = (new OBlockSandStone(24)).a(h).c(0.8F).a("sandStone").j();
    public static final OBlock R = (new OBlockNote(25)).c(0.8F).a("musicBlock").j();
    public static final OBlock S = (new OBlockBed(26)).c(0.2F).a("bed").s().j();
    public static final OBlock T = (new OBlockRail(27, 179, true)).c(0.7F).a(i).a("goldenRail").j();
    public static final OBlock U = (new OBlockDetectorRail(28, 195)).c(0.7F).a(i).a("detectorRail").j();
    public static final OBlock V = (new OBlockPistonBase(29, 106, true)).a("pistonStickyBase").j();
    public static final OBlock W = (new OBlockWeb(30, 11)).f(1).c(4.0F).a("web");
    public static final OBlockTallGrass X = (OBlockTallGrass) (new OBlockTallGrass(31, 39)).c(0.0F).a(g).a("tallgrass");
    public static final OBlockDeadBush Y = (OBlockDeadBush) (new OBlockDeadBush(32, 55)).c(0.0F).a(g).a("deadbush");
    public static final OBlock Z = (new OBlockPistonBase(33, 107, false)).a("pistonBase").j();
    public static final OBlockPistonExtension aa = (OBlockPistonExtension) (new OBlockPistonExtension(34, 107)).j();
    public static final OBlock ab = (new OBlockCloth()).c(0.8F).a(k).a("cloth").j();
    public static final OBlockPistonMoving ac = new OBlockPistonMoving(36);
    public static final OBlockFlower ad = (OBlockFlower) (new OBlockFlower(37, 13)).c(0.0F).a(g).a("flower");
    public static final OBlockFlower ae = (OBlockFlower) (new OBlockFlower(38, 12)).c(0.0F).a(g).a("rose");
    public static final OBlockFlower af = (OBlockFlower) (new OBlockMushroom(39, 29)).c(0.0F).a(g).a(0.125F).a("mushroom");
    public static final OBlockFlower ag = (OBlockFlower) (new OBlockMushroom(40, 28)).c(0.0F).a(g).a("mushroom");
    public static final OBlock ah = (new OBlockOreStorage(41, 23)).c(3.0F).b(10.0F).a(i).a("blockGold");
    public static final OBlock ai = (new OBlockOreStorage(42, 22)).c(5.0F).b(10.0F).a(i).a("blockIron");
    public static final OBlock aj = (new OBlockStep(43, true)).c(2.0F).b(10.0F).a(h).a("stoneSlab");
    public static final OBlock ak = (new OBlockStep(44, false)).c(2.0F).b(10.0F).a(h).a("stoneSlab");
    public static final OBlock al = (new OBlock(45, 7, OMaterial.e)).c(2.0F).b(10.0F).a(h).a("brick");
    public static final OBlock am = (new OBlockTNT(46, 8)).c(0.0F).a(g).a("tnt");
    public static final OBlock an = (new OBlockBookshelf(47, 35)).c(1.5F).a(e).a("bookshelf");
    public static final OBlock ao = (new OBlock(48, 36, OMaterial.e)).c(2.0F).b(10.0F).a(h).a("stoneMoss");
    public static final OBlock ap = (new OBlockObsidian(49, 37)).c(50.0F).b(2000.0F).a(h).a("obsidian");
    public static final OBlock aq = (new OBlockTorch(50, 80)).c(0.0F).a(0.9375F).a(e).a("torch").j();
    public static final OBlockFire ar = (OBlockFire) (new OBlockFire(51, 31)).c(0.0F).a(1.0F).a(e).a("fire").s();
    public static final OBlock as = (new OBlockMobSpawner(52, 65)).c(5.0F).a(i).a("mobSpawner").s();
    public static final OBlock at = (new OBlockStairs(53, x)).a("stairsWood").j();
    public static final OBlock au = (new OBlockChest(54)).c(2.5F).a(e).a("chest").j();
    public static final OBlock av = (new OBlockRedstoneWire(55, 164)).c(0.0F).a(d).a("redstoneDust").s().j();
    public static final OBlock aw = (new OBlockOre(56, 50)).c(3.0F).b(5.0F).a(h).a("oreDiamond");
    public static final OBlock ax = (new OBlockOreStorage(57, 24)).c(5.0F).b(10.0F).a(i).a("blockDiamond");
    public static final OBlock ay = (new OBlockWorkbench(58)).c(2.5F).a(e).a("workbench");
    public static final OBlock az = (new OBlockCrops(59, 88)).c(0.0F).a(g).a("crops").s().j();
    public static final OBlock aA = (new OBlockFarmland(60)).c(0.6F).a(f).a("farmland").j();
    public static final OBlock aB = (new OBlockFurnace(61, false)).c(3.5F).a(h).a("furnace").j();
    public static final OBlock aC = (new OBlockFurnace(62, true)).c(3.5F).a(h).a(0.875F).a("furnace").j();
    public static final OBlock aD = (new OBlockSign(63, OTileEntitySign.class, true)).c(1.0F).a(e).a("sign").s().j();
    public static final OBlock aE = (new OBlockDoor(64, OMaterial.d)).c(3.0F).a(e).a("doorWood").s().j();
    public static final OBlock aF = (new OBlockLadder(65, 83)).c(0.4F).a(e).a("ladder").j();
    public static final OBlock aG = (new OBlockRail(66, 128, false)).c(0.7F).a(i).a("rail").j();
    public static final OBlock aH = (new OBlockStairs(67, w)).a("stairsStone").j();
    public static final OBlock aI = (new OBlockSign(68, OTileEntitySign.class, false)).c(1.0F).a(e).a("sign").s().j();
    public static final OBlock aJ = (new OBlockLever(69, 96)).c(0.5F).a(e).a("lever").j();
    public static final OBlock aK = (new OBlockPressurePlate(70, t.bN, OEnumMobType.b, OMaterial.e)).c(0.5F).a(h).a("pressurePlate").j();
    public static final OBlock aL = (new OBlockDoor(71, OMaterial.f)).c(5.0F).a(i).a("doorIron").s().j();
    public static final OBlock aM = (new OBlockPressurePlate(72, x.bN, OEnumMobType.a, OMaterial.d)).c(0.5F).a(e).a("pressurePlate").j();
    public static final OBlock aN = (new OBlockRedstoneOre(73, 51, false)).c(3.0F).b(5.0F).a(h).a("oreRedstone").j();
    public static final OBlock aO = (new OBlockRedstoneOre(74, 51, true)).a(0.625F).c(3.0F).b(5.0F).a(h).a("oreRedstone").j();
    public static final OBlock aP = (new OBlockRedstoneTorch(75, 115, false)).c(0.0F).a(e).a("notGate").j();
    public static final OBlock aQ = (new OBlockRedstoneTorch(76, 99, true)).c(0.0F).a(0.5F).a(e).a("notGate").j();
    public static final OBlock aR = (new OBlockButton(77, t.bN)).c(0.5F).a(h).a("button").j();
    public static final OBlock aS = (new OBlockSnow(78, 66)).c(0.1F).a(k).a("snow").f(0);
    public static final OBlock aT = (new OBlockIce(79, 67)).c(0.5F).f(3).a(j).a("ice");
    public static final OBlock aU = (new OBlockSnowBlock(80, 66)).c(0.2F).a(k).a("snow");
    public static final OBlock aV = (new OBlockCactus(81, 70)).c(0.4F).a(k).a("cactus");
    public static final OBlock aW = (new OBlockClay(82, 72)).c(0.6F).a(f).a("clay");
    public static final OBlock aX = (new OBlockReed(83, 73)).c(0.0F).a(g).a("reeds").s();
    public static final OBlock aY = (new OBlockJukeBox(84, 74)).c(2.0F).b(10.0F).a(h).a("jukebox").j();
    public static final OBlock aZ = (new OBlockFence(85, 4)).c(2.0F).b(5.0F).a(e).a("fence");
    public static final OBlock ba = (new OBlockPumpkin(86, 102, false)).c(1.0F).a(e).a("pumpkin").j();
    public static final OBlock bb = (new OBlockNetherrack(87, 103)).c(0.4F).a(h).a("hellrock");
    public static final OBlock bc = (new OBlockSoulSand(88, 104)).c(0.5F).a(l).a("hellsand");
    public static final OBlock bd = (new OBlockGlowStone(89, 105, OMaterial.q)).c(0.3F).a(j).a(1.0F).a("lightgem");
    public static final OBlockPortal be = (OBlockPortal) (new OBlockPortal(90, 14)).c(-1.0F).a(j).a(0.75F).a("portal");
    public static final OBlock bf = (new OBlockPumpkin(91, 102, true)).c(1.0F).a(e).a(1.0F).a("litpumpkin").j();
    public static final OBlock bg = (new OBlockCake(92, 121)).c(0.5F).a(k).a("cake").s().j();
    public static final OBlock bh = (new OBlockRedstoneRepeater(93, false)).c(0.0F).a(e).a("diode").s().j();
    public static final OBlock bi = (new OBlockRedstoneRepeater(94, true)).c(0.0F).a(0.625F).a(e).a("diode").s().j();
    public static final OBlock bj = (new OBlockLockedChest(95)).c(0.0F).a(1.0F).a(e).a("lockedchest").a(true).j();
    public static final OBlock bk = (new OBlockTrapDoor(96, OMaterial.d)).c(3.0F).a(e).a("trapdoor").s().j();
    public static final OBlock bl = (new OBlockSilverfish(97)).c(0.75F);
    public static final OBlock bm = (new OBlockStoneBrick(98)).c(1.5F).b(10.0F).a(h).a("stonebricksmooth");
    public static final OBlock bn = (new OBlockMushroomCap(99, OMaterial.d, 142, 0)).c(0.2F).a(e).a("mushroom").j();
    public static final OBlock bo = (new OBlockMushroomCap(100, OMaterial.d, 142, 1)).c(0.2F).a(e).a("mushroom").j();
    public static final OBlock bp = (new OBlockPane(101, 85, 85, OMaterial.f, true)).c(5.0F).b(10.0F).a(i).a("fenceIron");
    public static final OBlock bq = (new OBlockPane(102, 49, 148, OMaterial.q, false)).c(0.3F).a(j).a("thinGlass");
    public static final OBlock br = (new OBlockMelon(103)).c(1.0F).a(e).a("melon");
    public static final OBlock bs = (new OBlockStem(104, ba)).c(0.0F).a(e).a("pumpkinStem").j();
    public static final OBlock bt = (new OBlockStem(105, br)).c(0.0F).a(e).a("pumpkinStem").j();
    public static final OBlock bu = (new OBlockVine(106)).c(0.2F).a(g).a("vine").j();
    public static final OBlock bv = (new OBlockFenceGate(107, 4)).c(2.0F).b(5.0F).a(e).a("fenceGate").j();
    public static final OBlock bw = (new OBlockStairs(108, al)).a("stairsBrick").j();
    public static final OBlock bx = (new OBlockStairs(109, bm)).a("stairsStoneBrickSmooth").j();
    public static final OBlockMycelium by = (OBlockMycelium) (new OBlockMycelium(110)).c(0.6F).a(g).a("mycel");
    public static final OBlock bz = (new OBlockLilyPad(111, 76)).c(0.0F).a(g).a("waterlily");
    public static final OBlock bA = (new OBlock(112, 224, OMaterial.e)).c(2.0F).b(10.0F).a(h).a("netherBrick");
    public static final OBlock bB = (new OBlockFence(113, 224, OMaterial.e)).c(2.0F).b(10.0F).a(h).a("netherFence");
    public static final OBlock bC = (new OBlockStairs(114, bA)).a("stairsNetherBrick").j();
    public static final OBlock bD = (new OBlockNetherStalk(115)).a("netherStalk").j();
    public static final OBlock bE = (new OBlockEnchantmentTable(116)).c(5.0F).b(2000.0F).a("enchantmentTable");
    public static final OBlock bF = (new OBlockBrewingStand(117)).c(0.5F).a(0.125F).a("brewingStand").j();
    public static final OBlock bG = (new OBlockCauldron(118)).c(2.0F).a("cauldron").j();
    public static final OBlock bH = (new OBlockEndPortal(119, OMaterial.B)).c(-1.0F).b(6000000.0F);
    public static final OBlock bI = (new OBlockEndPortalFrame(120)).a(j).a(0.125F).c(-1.0F).a("endPortalFrame").j().b(6000000.0F);
    public static final OBlock bJ = (new OBlock(121, 175, OMaterial.e)).c(3.0F).b(15.0F).a(h).a("whiteStone");
    public static final OBlock bK = (new OBlockDragonEgg(122, 167)).c(3.0F).b(15.0F).a(h).a(0.125F).a("dragonEgg");
    public static final OBlock bL = (new OBlockRedstoneLight(123, false)).c(0.3F).a(j).a("redstoneLight");
    public static final OBlock bM = (new OBlockRedstoneLight(124, true)).c(0.3F).a(j).a("redstoneLight");
    public int bN;
    public final int bO;
    protected float bP;
    protected float bQ;
    protected boolean bR;
    protected boolean bS;
    protected boolean bT;
    protected boolean bU;
    public double bV;
    public double bW;
    public double bX;
    public double bY;
    public double bZ;
    public double ca;
    public OStepSound cb;
    public float cc;
    public final OMaterial cd;
    public float ce;
    private String a;

    protected OBlock(int var1, OMaterial var2) {
        super();
        this.bR = true;
        this.bS = true;
        this.cb = d;
        this.cc = 1.0F;
        this.ce = 0.6F;
        if (m[var1] != null) {
            throw new IllegalArgumentException("Slot " + var1 + " is already occupied by " + m[var1] + " when adding " + this);
        } else {
            this.cd = var2;
            m[var1] = this;
            this.bO = var1;
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            n[var1] = this.a();
            o[var1] = this.a() ? 255 : 0;
            p[var1] = !var2.b();
        }
    }

    protected OBlock j() {
        r[this.bO] = true;
        return this;
    }

    protected void k() {
    }

    protected OBlock(int var1, int var2, OMaterial var3) {
        this(var1, var3);
        this.bN = var2;
    }

    protected OBlock a(OStepSound var1) {
        this.cb = var1;
        return this;
    }

    protected OBlock f(int var1) {
        o[this.bO] = var1;
        return this;
    }

    protected OBlock a(float var1) {
        q[this.bO] = (int) (15.0F * var1);
        return this;
    }

    protected OBlock b(float var1) {
        this.bQ = var1 * 3.0F;
        return this;
    }

    public static boolean g(int var0) {
        OBlock var1 = m[var0];
        return var1 == null ? false : var1.cd.j() && var1.b();
    }

    public boolean b() {
        return true;
    }

    public boolean b(OIBlockAccess var1, int var2, int var3, int var4) {
        return !this.cd.c();
    }

    public int c() {
        return 0;
    }

    protected OBlock c(float var1) {
        this.bP = var1;
        if (this.bQ < var1 * 5.0F) {
            this.bQ = var1 * 5.0F;
        }

        return this;
    }

    protected OBlock l() {
        this.c(-1.0F);
        return this;
    }

    public float m() {
        return this.bP;
    }

    protected OBlock a(boolean var1) {
        this.bT = var1;
        return this;
    }

    public boolean n() {
        return this.bT;
    }

    public boolean o() {
        return this.bU;
    }

    public void a(float var1, float var2, float var3, float var4, float var5, float var6) {
        this.bV = var1;
        this.bW = var2;
        this.bX = var3;
        this.bY = var4;
        this.bZ = var5;
        this.ca = var6;
    }

    public boolean b(OIBlockAccess var1, int var2, int var3, int var4, int var5) {
        return var1.d(var2, var3, var4).a();
    }

    public int a(int var1, int var2) {
        return this.a(var1);
    }

    public int a(int var1) {
        return this.bN;
    }

    public void a(OWorld var1, int var2, int var3, int var4, OAxisAlignedBB var5, ArrayList<OAxisAlignedBB> var6) {
        OAxisAlignedBB var7 = this.e(var1, var2, var3, var4);
        if (var7 != null && var5.a(var7)) {
            var6.add(var7);
        }

    }

    public OAxisAlignedBB e(OWorld var1, int var2, int var3, int var4) {
        return OAxisAlignedBB.b(var2 + this.bV, var3 + this.bW, var4 + this.bX, var2 + this.bY, var3 + this.bZ, var4 + this.ca);
    }

    public boolean a() {
        return true;
    }

    public boolean a(int var1, boolean var2) {
        return this.E_();
    }

    public boolean E_() {
        return true;
    }

    public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
    }

    public void c(OWorld var1, int var2, int var3, int var4, int var5) {
    }

    public void a(OWorld var1, int var2, int var3, int var4, int var5) {
    }

    public int d() {
        return 10;
    }

    public void a(OWorld var1, int var2, int var3, int var4) {
    }

    public void d(OWorld var1, int var2, int var3, int var4) {
    }

    public int a(Random var1) {
        return 1;
    }

    public int a(int var1, Random var2, int var3) {
        return this.bO;
    }

    public float a(OEntityPlayer var1) {
        return this.bP < 0.0F ? 0.0F : (!var1.b(this) ? 1.0F / this.bP / 100.0F : var1.a(this) / this.bP / 30.0F);
    }

    public final void b(OWorld var1, int var2, int var3, int var4, int var5, int var6) {
        this.a(var1, var2, var3, var4, var5, 1.0F, var6);
    }

    public void a(OWorld var1, int var2, int var3, int var4, int var5, float var6, int var7) {
        if (!var1.F) {
            int var8 = this.a(var7, var1.r);

            for (int var9 = 0; var9 < var8; ++var9) {
                if (var1.r.nextFloat() <= var6) {
                    int var10 = this.a(var5, var1.r, var7);
                    if (var10 > 0) {
                        this.a(var1, var2, var3, var4, new OItemStack(var10, 1, this.c(var5)));
                    }
                }
            }

        }
    }

    protected void a(OWorld var1, int var2, int var3, int var4, OItemStack var5) {
        if (!var1.F) {
            float var6 = 0.7F;
            double var7 = (var1.r.nextFloat() * var6) + (1.0F - var6) * 0.5D;
            double var9 = (var1.r.nextFloat() * var6) + (1.0F - var6) * 0.5D;
            double var11 = (var1.r.nextFloat() * var6) + (1.0F - var6) * 0.5D;
            OEntityItem var13 = new OEntityItem(var1, var2 + var7, var3 + var9, var4 + var11, var5);
            var13.c = 10;
            var1.b(var13);
        }
    }

    protected int c(int var1) {
        return 0;
    }

    public float a(OEntity var1) {
        return this.bQ / 5.0F;
    }

    public OMovingObjectPosition a(OWorld var1, int var2, int var3, int var4, OVec3D var5, OVec3D var6) {
        this.a((OIBlockAccess) var1, var2, var3, var4);
        var5 = var5.c((-var2), (-var3), (-var4));
        var6 = var6.c((-var2), (-var3), (-var4));
        OVec3D var7 = var5.a(var6, this.bV);
        OVec3D var8 = var5.a(var6, this.bY);
        OVec3D var9 = var5.b(var6, this.bW);
        OVec3D var10 = var5.b(var6, this.bZ);
        OVec3D var11 = var5.c(var6, this.bX);
        OVec3D var12 = var5.c(var6, this.ca);
        if (!this.a(var7)) {
            var7 = null;
        }

        if (!this.a(var8)) {
            var8 = null;
        }

        if (!this.b(var9)) {
            var9 = null;
        }

        if (!this.b(var10)) {
            var10 = null;
        }

        if (!this.c(var11)) {
            var11 = null;
        }

        if (!this.c(var12)) {
            var12 = null;
        }

        OVec3D var13 = null;
        if (var7 != null && (var13 == null || var5.b(var7) < var5.b(var13))) {
            var13 = var7;
        }

        if (var8 != null && (var13 == null || var5.b(var8) < var5.b(var13))) {
            var13 = var8;
        }

        if (var9 != null && (var13 == null || var5.b(var9) < var5.b(var13))) {
            var13 = var9;
        }

        if (var10 != null && (var13 == null || var5.b(var10) < var5.b(var13))) {
            var13 = var10;
        }

        if (var11 != null && (var13 == null || var5.b(var11) < var5.b(var13))) {
            var13 = var11;
        }

        if (var12 != null && (var13 == null || var5.b(var12) < var5.b(var13))) {
            var13 = var12;
        }

        if (var13 == null) {
            return null;
        } else {
            byte var14 = -1;
            if (var13 == var7) {
                var14 = 4;
            }

            if (var13 == var8) {
                var14 = 5;
            }

            if (var13 == var9) {
                var14 = 0;
            }

            if (var13 == var10) {
                var14 = 1;
            }

            if (var13 == var11) {
                var14 = 2;
            }

            if (var13 == var12) {
                var14 = 3;
            }

            return new OMovingObjectPosition(var2, var3, var4, var14, var13.c(var2, var3, var4));
        }
    }

    private boolean a(OVec3D var1) {
        return var1 == null ? false : var1.b >= this.bW && var1.b <= this.bZ && var1.c >= this.bX && var1.c <= this.ca;
    }

    private boolean b(OVec3D var1) {
        return var1 == null ? false : var1.a >= this.bV && var1.a <= this.bY && var1.c >= this.bX && var1.c <= this.ca;
    }

    private boolean c(OVec3D var1) {
        return var1 == null ? false : var1.a >= this.bV && var1.a <= this.bY && var1.b >= this.bW && var1.b <= this.bZ;
    }

    public void a_(OWorld var1, int var2, int var3, int var4) {
    }

    public boolean b(OWorld var1, int var2, int var3, int var4, int var5) {
        return this.c(var1, var2, var3, var4);
    }

    public boolean c(OWorld var1, int var2, int var3, int var4) {
        int var5 = var1.a(var2, var3, var4);
        return var5 == 0 || m[var5].cd.i();
    }

    public boolean a(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
        return false;
    }

    public void b(OWorld var1, int var2, int var3, int var4, OEntity var5) {
    }

    public void e(OWorld var1, int var2, int var3, int var4, int var5) {
    }

    public void b(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
    }

    public void a(OWorld var1, int var2, int var3, int var4, OEntity var5, OVec3D var6) {
    }

    public void a(OIBlockAccess var1, int var2, int var3, int var4) {
    }

    public boolean a(OIBlockAccess var1, int var2, int var3, int var4, int var5) {
        return false;
    }

    public boolean e() {
        return false;
    }

    public void a(OWorld var1, int var2, int var3, int var4, OEntity var5) {
    }

    public boolean d(OWorld var1, int var2, int var3, int var4, int var5) {
        return false;
    }

    public void f() {
    }

    public void a(OWorld var1, OEntityPlayer var2, int var3, int var4, int var5, int var6) {
        var2.a(OStatList.C[this.bO], 1);
        var2.c(0.025F);
        if (this.h() && OEnchantmentHelper.d(var2.k)) {
            OItemStack var8 = this.a_(var6);
            if (var8 != null) {
                this.a(var1, var3, var4, var5, var8);
            }
        } else {
            int var7 = OEnchantmentHelper.e(var2.k);
            this.b(var1, var3, var4, var5, var6, var7);
        }

    }

    protected boolean h() {
        return this.b() && !this.bU;
    }

    protected OItemStack a_(int var1) {
        int var2 = 0;
        if (this.bO >= 0 && this.bO < OItem.d.length && OItem.d[this.bO].e()) {
            var2 = var1;
        }

        return new OItemStack(this.bO, 1, var2);
    }

    public int a(int var1, Random var2) {
        return this.a(var2);
    }

    public boolean f(OWorld var1, int var2, int var3, int var4) {
        return true;
    }

    public void a(OWorld var1, int var2, int var3, int var4, OEntityLiving var5) {
    }

    public OBlock a(String var1) {
        this.a = "tile." + var1;
        return this;
    }

    public String p() {
        return OStatCollector.a(this.q() + ".name");
    }

    public String q() {
        return this.a;
    }

    public void a(OWorld var1, int var2, int var3, int var4, int var5, int var6) {
    }

    public boolean r() {
        return this.bS;
    }

    protected OBlock s() {
        this.bS = false;
        return this;
    }

    public int g() {
        return this.cd.l();
    }

    public void a(OWorld var1, int var2, int var3, int var4, OEntity var5, float var6) {
    }

    static {
        OItem.d[ab.bO] = (new OItemCloth(ab.bO - 256)).a("cloth");
        OItem.d[J.bO] = (new OItemMetadata(J.bO - 256, J)).a("log");
        OItem.d[x.bO] = (new OItemMetadata(x.bO - 256, x)).a("wood");
        OItem.d[bm.bO] = (new OItemMetadata(bm.bO - 256, bm)).a("stonebricksmooth");
        OItem.d[Q.bO] = (new OItemMetadata(Q.bO - 256, Q)).a("sandStone");
        OItem.d[ak.bO] = (new OItemSlab(ak.bO - 256)).a("stoneSlab");
        OItem.d[y.bO] = (new OItemSapling(y.bO - 256)).a("sapling");
        OItem.d[K.bO] = (new OItemLeaves(K.bO - 256)).a("leaves");
        OItem.d[bu.bO] = new OItemColored(bu.bO - 256, false);
        OItem.d[X.bO] = (new OItemColored(X.bO - 256, true)).a(new String[] { "shrub", "grass", "fern" });
        OItem.d[bz.bO] = new OItemLilyPad(bz.bO - 256);
        OItem.d[Z.bO] = new OItemPiston(Z.bO - 256);
        OItem.d[V.bO] = new OItemPiston(V.bO - 256);

        for (int var0 = 0; var0 < 256; ++var0) {
            if (m[var0] != null) {
                if (OItem.d[var0] == null) {
                    OItem.d[var0] = new OItemBlock(var0 - 256);
                    m[var0].k();
                }

                boolean var1 = false;
                if (var0 > 0 && m[var0].c() == 10) {
                    var1 = true;
                }

                if (var0 > 0 && m[var0] instanceof OBlockStep) {
                    var1 = true;
                }

                if (var0 == aA.bO) {
                    var1 = true;
                }

                if (p[var0]) {
                    var1 = true;
                }

                s[var0] = var1;
            }
        }

        p[0] = true;
        OStatList.b();
    }
}
