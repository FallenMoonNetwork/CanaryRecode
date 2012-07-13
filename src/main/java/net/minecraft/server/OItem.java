package net.minecraft.server;

import java.util.Random;

import net.canarymod.Canary;
import net.canarymod.api.entity.CanaryBaseItem;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.hook.CancelableHook;
import net.canarymod.hook.Hook;
import net.canarymod.hook.player.RightClickHook;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEnumAction;
import net.minecraft.server.OEnumArmorMaterial;
import net.minecraft.server.OEnumToolMaterial;
import net.minecraft.server.OItemAppleGold;
import net.minecraft.server.OItemArmor;
import net.minecraft.server.OItemAxe;
import net.minecraft.server.OItemBed;
import net.minecraft.server.OItemBoat;
import net.minecraft.server.OItemBow;
import net.minecraft.server.OItemBucket;
import net.minecraft.server.OItemBucketMilk;
import net.minecraft.server.OItemCoal;
import net.minecraft.server.OItemDoor;
import net.minecraft.server.OItemDye;
import net.minecraft.server.OItemEgg;
import net.minecraft.server.OItemEnderEye;
import net.minecraft.server.OItemEnderPearl;
import net.minecraft.server.OItemExpBottle;
import net.minecraft.server.OItemFireball;
import net.minecraft.server.OItemFishingRod;
import net.minecraft.server.OItemFlintAndSteel;
import net.minecraft.server.OItemFood;
import net.minecraft.server.OItemGlassBottle;
import net.minecraft.server.OItemHoe;
import net.minecraft.server.OItemMap;
import net.minecraft.server.OItemMinecart;
import net.minecraft.server.OItemMonsterPlacer;
import net.minecraft.server.OItemPainting;
import net.minecraft.server.OItemPickaxe;
import net.minecraft.server.OItemPotion;
import net.minecraft.server.OItemRecord;
import net.minecraft.server.OItemRedstone;
import net.minecraft.server.OItemReed;
import net.minecraft.server.OItemSaddle;
import net.minecraft.server.OItemSeeds;
import net.minecraft.server.OItemShears;
import net.minecraft.server.OItemSign;
import net.minecraft.server.OItemSnowball;
import net.minecraft.server.OItemSoup;
import net.minecraft.server.OItemSpade;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OItemSword;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OMovingObjectPosition;
import net.minecraft.server.OPotion;
import net.minecraft.server.OPotionHelper;
import net.minecraft.server.OStatCollector;
import net.minecraft.server.OStatList;
import net.minecraft.server.OVec3D;
import net.minecraft.server.OWorld;

public class OItem {

    protected static Random c = new Random();
    public static OItem[] d = new OItem[32000];
    public static OItem e = (new OItemSpade(0, OEnumToolMaterial.c)).a(2, 5).a("shovelIron");
    public static OItem f = (new OItemPickaxe(1, OEnumToolMaterial.c)).a(2, 6).a("pickaxeIron");
    public static OItem g = (new OItemAxe(2, OEnumToolMaterial.c)).a(2, 7).a("hatchetIron");
    public static OItem h = (new OItemFlintAndSteel(3)).a(5, 0).a("flintAndSteel");
    public static OItem i = (new OItemFood(4, 4, 0.3F, false)).a(10, 0).a("apple");
    public static OItem j = (new OItemBow(5)).a(5, 1).a("bow");
    public static OItem k = (new OItem(6)).a(5, 2).a("arrow");
    public static OItem l = (new OItemCoal(7)).a(7, 0).a("coal");
    public static OItem m = (new OItem(8)).a(7, 3).a("emerald");
    public static OItem n = (new OItem(9)).a(7, 1).a("ingotIron");
    public static OItem o = (new OItem(10)).a(7, 2).a("ingotGold");
    public static OItem p = (new OItemSword(11, OEnumToolMaterial.c)).a(2, 4).a("swordIron");
    public static OItem q = (new OItemSword(12, OEnumToolMaterial.a)).a(0, 4).a("swordWood");
    public static OItem r = (new OItemSpade(13, OEnumToolMaterial.a)).a(0, 5).a("shovelWood");
    public static OItem s = (new OItemPickaxe(14, OEnumToolMaterial.a)).a(0, 6).a("pickaxeWood");
    public static OItem t = (new OItemAxe(15, OEnumToolMaterial.a)).a(0, 7).a("hatchetWood");
    public static OItem u = (new OItemSword(16, OEnumToolMaterial.b)).a(1, 4).a("swordStone");
    public static OItem v = (new OItemSpade(17, OEnumToolMaterial.b)).a(1, 5).a("shovelStone");
    public static OItem w = (new OItemPickaxe(18, OEnumToolMaterial.b)).a(1, 6).a("pickaxeStone");
    public static OItem x = (new OItemAxe(19, OEnumToolMaterial.b)).a(1, 7).a("hatchetStone");
    public static OItem y = (new OItemSword(20, OEnumToolMaterial.d)).a(3, 4).a("swordDiamond");
    public static OItem z = (new OItemSpade(21, OEnumToolMaterial.d)).a(3, 5).a("shovelDiamond");
    public static OItem A = (new OItemPickaxe(22, OEnumToolMaterial.d)).a(3, 6).a("pickaxeDiamond");
    public static OItem B = (new OItemAxe(23, OEnumToolMaterial.d)).a(3, 7).a("hatchetDiamond");
    public static OItem C = (new OItem(24)).a(5, 3).h().a("stick");
    public static OItem D = (new OItem(25)).a(7, 4).a("bowl");
    public static OItem E = (new OItemSoup(26, 8)).a(8, 4).a("mushroomStew");
    public static OItem F = (new OItemSword(27, OEnumToolMaterial.e)).a(4, 4).a("swordGold");
    public static OItem G = (new OItemSpade(28, OEnumToolMaterial.e)).a(4, 5).a("shovelGold");
    public static OItem H = (new OItemPickaxe(29, OEnumToolMaterial.e)).a(4, 6).a("pickaxeGold");
    public static OItem I = (new OItemAxe(30, OEnumToolMaterial.e)).a(4, 7).a("hatchetGold");
    public static OItem J = (new OItem(31)).a(8, 0).a("string");
    public static OItem K = (new OItem(32)).a(8, 1).a("feather");
    public static OItem L = (new OItem(33)).a(8, 2).a("sulphur").b(OPotionHelper.k);
    public static OItem M = (new OItemHoe(34, OEnumToolMaterial.a)).a(0, 8).a("hoeWood");
    public static OItem N = (new OItemHoe(35, OEnumToolMaterial.b)).a(1, 8).a("hoeStone");
    public static OItem O = (new OItemHoe(36, OEnumToolMaterial.c)).a(2, 8).a("hoeIron");
    public static OItem P = (new OItemHoe(37, OEnumToolMaterial.d)).a(3, 8).a("hoeDiamond");
    public static OItem Q = (new OItemHoe(38, OEnumToolMaterial.e)).a(4, 8).a("hoeGold");
    public static OItem R = (new OItemSeeds(39, OBlock.az.bO, OBlock.aA.bO)).a(9, 0).a("seeds");
    public static OItem S = (new OItem(40)).a(9, 1).a("wheat");
    public static OItem T = (new OItemFood(41, 5, 0.6F, false)).a(9, 2).a("bread");
    public static OItem U = (new OItemArmor(42, OEnumArmorMaterial.a, 0, 0)).a(0, 0).a("helmetCloth");
    public static OItem V = (new OItemArmor(43, OEnumArmorMaterial.a, 0, 1)).a(0, 1).a("chestplateCloth");
    public static OItem W = (new OItemArmor(44, OEnumArmorMaterial.a, 0, 2)).a(0, 2).a("leggingsCloth");
    public static OItem X = (new OItemArmor(45, OEnumArmorMaterial.a, 0, 3)).a(0, 3).a("bootsCloth");
    public static OItem Y = (new OItemArmor(46, OEnumArmorMaterial.b, 1, 0)).a(1, 0).a("helmetChain");
    public static OItem Z = (new OItemArmor(47, OEnumArmorMaterial.b, 1, 1)).a(1, 1).a("chestplateChain");
    public static OItem aa = (new OItemArmor(48, OEnumArmorMaterial.b, 1, 2)).a(1, 2).a("leggingsChain");
    public static OItem ab = (new OItemArmor(49, OEnumArmorMaterial.b, 1, 3)).a(1, 3).a("bootsChain");
    public static OItem ac = (new OItemArmor(50, OEnumArmorMaterial.c, 2, 0)).a(2, 0).a("helmetIron");
    public static OItem ad = (new OItemArmor(51, OEnumArmorMaterial.c, 2, 1)).a(2, 1).a("chestplateIron");
    public static OItem ae = (new OItemArmor(52, OEnumArmorMaterial.c, 2, 2)).a(2, 2).a("leggingsIron");
    public static OItem af = (new OItemArmor(53, OEnumArmorMaterial.c, 2, 3)).a(2, 3).a("bootsIron");
    public static OItem ag = (new OItemArmor(54, OEnumArmorMaterial.e, 3, 0)).a(3, 0).a("helmetDiamond");
    public static OItem ah = (new OItemArmor(55, OEnumArmorMaterial.e, 3, 1)).a(3, 1).a("chestplateDiamond");
    public static OItem ai = (new OItemArmor(56, OEnumArmorMaterial.e, 3, 2)).a(3, 2).a("leggingsDiamond");
    public static OItem aj = (new OItemArmor(57, OEnumArmorMaterial.e, 3, 3)).a(3, 3).a("bootsDiamond");
    public static OItem ak = (new OItemArmor(58, OEnumArmorMaterial.d, 4, 0)).a(4, 0).a("helmetGold");
    public static OItem al = (new OItemArmor(59, OEnumArmorMaterial.d, 4, 1)).a(4, 1).a("chestplateGold");
    public static OItem am = (new OItemArmor(60, OEnumArmorMaterial.d, 4, 2)).a(4, 2).a("leggingsGold");
    public static OItem an = (new OItemArmor(61, OEnumArmorMaterial.d, 4, 3)).a(4, 3).a("bootsGold");
    public static OItem ao = (new OItem(62)).a(6, 0).a("flint");
    public static OItem ap = (new OItemFood(63, 3, 0.3F, true)).a(7, 5).a("porkchopRaw");
    public static OItem aq = (new OItemFood(64, 8, 0.8F, true)).a(8, 5).a("porkchopCooked");
    public static OItem ar = (new OItemPainting(65)).a(10, 1).a("painting");
    public static OItem as = (new OItemAppleGold(66, 4, 1.2F, false)).r().a(OPotion.l.H, 5, 0, 1.0F).a(11, 0).a("appleGold");
    public static OItem at = (new OItemSign(67)).a(10, 2).a("sign");
    public static OItem au = (new OItemDoor(68, OMaterial.d)).a(11, 2).a("doorWood");
    public static OItem av = (new OItemBucket(69, 0)).a(10, 4).a("bucket");
    public static OItem aw = (new OItemBucket(70, OBlock.A.bO)).a(11, 4).a("bucketWater").a(av);
    public static OItem ax = (new OItemBucket(71, OBlock.C.bO)).a(12, 4).a("bucketLava").a(av);
    public static OItem ay = (new OItemMinecart(72, 0)).a(7, 8).a("minecart");
    public static OItem az = (new OItemSaddle(73)).a(8, 6).a("saddle");
    public static OItem aA = (new OItemDoor(74, OMaterial.f)).a(12, 2).a("doorIron");
    public static OItem aB = (new OItemRedstone(75)).a(8, 3).a("redstone").b(OPotionHelper.i);
    public static OItem aC = (new OItemSnowball(76)).a(14, 0).a("snowball");
    public static OItem aD = (new OItemBoat(77)).a(8, 8).a("boat");
    public static OItem aE = (new OItem(78)).a(7, 6).a("leather");
    public static OItem aF = (new OItemBucketMilk(79)).a(13, 4).a("milk").a(av);
    public static OItem aG = (new OItem(80)).a(6, 1).a("brick");
    public static OItem aH = (new OItem(81)).a(9, 3).a("clay");
    public static OItem aI = (new OItemReed(82, OBlock.aX)).a(11, 1).a("reeds");
    public static OItem aJ = (new OItem(83)).a(10, 3).a("paper");
    public static OItem aK = (new OItem(84)).a(11, 3).a("book");
    public static OItem aL = (new OItem(85)).a(14, 1).a("slimeball");
    public static OItem aM = (new OItemMinecart(86, 1)).a(7, 9).a("minecartChest");
    public static OItem aN = (new OItemMinecart(87, 2)).a(7, 10).a("minecartFurnace");
    public static OItem aO = (new OItemEgg(88)).a(12, 0).a("egg");
    public static OItem aP = (new OItem(89)).a(6, 3).a("compass");
    public static OItem aQ = (new OItemFishingRod(90)).a(5, 4).a("fishingRod");
    public static OItem aR = (new OItem(91)).a(6, 4).a("clock");
    public static OItem aS = (new OItem(92)).a(9, 4).a("yellowDust").b(OPotionHelper.j);
    public static OItem aT = (new OItemFood(93, 2, 0.3F, false)).a(9, 5).a("fishRaw");
    public static OItem aU = (new OItemFood(94, 5, 0.6F, false)).a(10, 5).a("fishCooked");
    public static OItem aV = (new OItemDye(95)).a(14, 4).a("dyePowder");
    public static OItem aW = (new OItem(96)).a(12, 1).a("bone").h();
    public static OItem aX = (new OItem(97)).a(13, 0).a("sugar").b(OPotionHelper.b);
    public static OItem aY = (new OItemReed(98, OBlock.bg)).e(1).a(13, 1).a("cake");
    public static OItem aZ = (new OItemBed(99)).e(1).a(13, 2).a("bed");
    public static OItem ba = (new OItemReed(100, OBlock.bh)).a(6, 5).a("diode");
    public static OItem bb = (new OItemFood(101, 1, 0.1F, false)).a(12, 5).a("cookie");
    public static OItemMap bc = (OItemMap) (new OItemMap(102)).a(12, 3).a("map");
    public static OItemShears bd = (OItemShears) (new OItemShears(103)).a(13, 5).a("shears");
    public static OItem be = (new OItemFood(104, 2, 0.3F, false)).a(13, 6).a("melon");
    public static OItem bf = (new OItemSeeds(105, OBlock.bs.bO, OBlock.aA.bO)).a(13, 3).a("seeds_pumpkin");
    public static OItem bg = (new OItemSeeds(106, OBlock.bt.bO, OBlock.aA.bO)).a(14, 3).a("seeds_melon");
    public static OItem bh = (new OItemFood(107, 3, 0.3F, true)).a(9, 6).a("beefRaw");
    public static OItem bi = (new OItemFood(108, 8, 0.8F, true)).a(10, 6).a("beefCooked");
    public static OItem bj = (new OItemFood(109, 2, 0.3F, true)).a(OPotion.s.H, 30, 0, 0.3F).a(9, 7).a("chickenRaw");
    public static OItem bk = (new OItemFood(110, 6, 0.6F, true)).a(10, 7).a("chickenCooked");
    public static OItem bl = (new OItemFood(111, 4, 0.1F, true)).a(OPotion.s.H, 30, 0, 0.8F).a(11, 5).a("rottenFlesh");
    public static OItem bm = (new OItemEnderPearl(112)).a(11, 6).a("enderPearl");
    public static OItem bn = (new OItem(113)).a(12, 6).a("blazeRod");
    public static OItem bo = (new OItem(114)).a(11, 7).a("ghastTear").b(OPotionHelper.c);
    public static OItem bp = (new OItem(115)).a(12, 7).a("goldNugget");
    public static OItem bq = (new OItemSeeds(116, OBlock.bD.bO, OBlock.bc.bO)).a(13, 7).a("netherStalkSeeds").b("+4");
    public static OItemPotion br = (OItemPotion) (new OItemPotion(117)).a(13, 8).a("potion");
    public static OItem bs = (new OItemGlassBottle(118)).a(12, 8).a("glassBottle");
    public static OItem bt = (new OItemFood(119, 2, 0.8F, false)).a(OPotion.u.H, 5, 0, 1.0F).a(11, 8).a("spiderEye").b(OPotionHelper.d);
    public static OItem bu = (new OItem(120)).a(10, 8).a("fermentedSpiderEye").b(OPotionHelper.e);
    public static OItem bv = (new OItem(121)).a(13, 9).a("blazePowder").b(OPotionHelper.g);
    public static OItem bw = (new OItem(122)).a(13, 10).a("magmaCream").b(OPotionHelper.h);
    public static OItem bx = (new OItemReed(123, OBlock.bF)).a(12, 10).a("brewingStand");
    public static OItem by = (new OItemReed(124, OBlock.bG)).a(12, 9).a("cauldron");
    public static OItem bz = (new OItemEnderEye(125)).a(11, 9).a("eyeOfEnder");
    public static OItem bA = (new OItem(126)).a(9, 8).a("speckledMelon").b(OPotionHelper.f);
    public static OItem bB = (new OItemMonsterPlacer(127)).a(9, 9).a("monsterPlacer");
    public static OItem bC = (new OItemExpBottle(128)).a(11, 10).a("expBottle");
    public static OItem bD = (new OItemFireball(129)).a(14, 2).a("fireball");
    public static OItem bE = (new OItemRecord(2000, "13")).a(0, 15).a("record");
    public static OItem bF = (new OItemRecord(2001, "cat")).a(1, 15).a("record");
    public static OItem bG = (new OItemRecord(2002, "blocks")).a(2, 15).a("record");
    public static OItem bH = (new OItemRecord(2003, "chirp")).a(3, 15).a("record");
    public static OItem bI = (new OItemRecord(2004, "far")).a(4, 15).a("record");
    public static OItem bJ = (new OItemRecord(2005, "mall")).a(5, 15).a("record");
    public static OItem bK = (new OItemRecord(2006, "mellohi")).a(6, 15).a("record");
    public static OItem bL = (new OItemRecord(2007, "stal")).a(7, 15).a("record");
    public static OItem bM = (new OItemRecord(2008, "strad")).a(8, 15).a("record");
    public static OItem bN = (new OItemRecord(2009, "ward")).a(9, 15).a("record");
    public static OItem bO = (new OItemRecord(2010, "11")).a(10, 15).a("record");
    public final int bP;
    protected int bQ = 64;
    private int a = 0;
    protected int bR;
    protected boolean bS = false;
    protected boolean bT = false;
    private OItem b = null;
    private String bU = null;
    private String bV;
    
    //CanaryMod Start
    private CanaryBaseItem baseItem;
    //CanaryMod End

    protected OItem(int var1) {
        super();
        this.bP = 256 + var1;
        if (d[256 + var1] != null) {
            System.out.println("CONFLICT @ " + var1);
        }

        d[256 + var1] = this;
        baseItem = new CanaryBaseItem(this);
    }

    public OItem d(int var1) {
        this.bR = var1;
        return this;
    }

    public OItem e(int var1) {
        this.bQ = var1;
        return this;
    }

    public OItem a(int var1, int var2) {
        this.bR = var1 + var2 * 16;
        return this;
    }

    public boolean a(OItemStack var1, OEntityPlayer var2, OWorld var3, int var4, int var5, int var6, int var7) {
        //CanaryMod call item use hook
        CanaryBlock block = (CanaryBlock) var2.bi.getCanaryDimension().getBlockAt(var4, var5, var6);
        block.setFaceClicked(BlockFace.fromByte((byte)var7));
        RightClickHook hook = new RightClickHook(((OEntityPlayerMP)var2).getPlayer(), null, block, new CanaryItem(var1), null, Hook.Type.ITEM_USE);
        Canary.hooks().callHook(hook);
        if(hook.isCanceled()) {
            return true;
        }
        return false;
    }

    public float a(OItemStack var1, OBlock var2) {
        return 1.0F;
    }

    public OItemStack a(OItemStack var1, OWorld var2, OEntityPlayer var3) {
        return var1;
    }

    public OItemStack b(OItemStack var1, OWorld var2, OEntityPlayer var3) {
        return var1;
    }
    
    /**
     * CanaryMod: Get BaseItem handler
     * @return
     */
    public CanaryBaseItem getBaseItem() {
        return this.baseItem;
    }

    public int d() {
        return this.bQ;
    }

    public int a(int var1) {
        return 0;
    }

    public boolean e() {
        return this.bT;
    }

    protected OItem a(boolean var1) {
        this.bT = var1;
        return this;
    }

    public int f() {
        return this.a;
    }

    //CanaryMod protected -> public
    public OItem f(int var1) {
        this.a = var1;
        return this;
    }

    public boolean g() {
        return this.a > 0 && !this.bT;
    }

    public boolean a(OItemStack var1, OEntityLiving var2, OEntityLiving var3) {
        return false;
    }

    public boolean a(OItemStack var1, int var2, int var3, int var4, int var5, OEntityLiving var6) {
        return false;
    }

    public int a(OEntity var1) {
        return 1;
    }

    public boolean a(OBlock var1) {
        return false;
    }

    public void a(OItemStack var1, OEntityLiving var2) {
    }

    public OItem h() {
        this.bS = true;
        return this;
    }

    public OItem a(String var1) {
        this.bV = "item." + var1;
        return this;
    }

    public String b() {
        return this.bV;
    }

    public String a(OItemStack var1) {
        return this.bV;
    }

    public OItem a(OItem var1) {
        this.b = var1;
        return this;
    }

    public boolean e(OItemStack var1) {
        return true;
    }

    public boolean i() {
        return false;
    }

    public OItem j() {
        return this.b;
    }

    public boolean k() {
        return this.b != null;
    }

    public String l() {
        return OStatCollector.a(this.b() + ".name");
    }

    public void a(OItemStack var1, OWorld var2, OEntity var3, int var4, boolean var5) {
    }

    public void d(OItemStack var1, OWorld var2, OEntityPlayer var3) {
    }

    public boolean t_() {
        return false;
    }

    public OEnumAction d(OItemStack var1) {
        return OEnumAction.a;
    }

    public int c(OItemStack var1) {
        return 0;
    }

    public void a(OItemStack var1, OWorld var2, OEntityPlayer var3, int var4) {
    }

    protected OItem b(String var1) {
        this.bU = var1;
        return this;
    }

    public String m() {
        return this.bU;
    }

    public boolean n() {
        return this.bU != null;
    }

    public boolean f(OItemStack var1) {
        return this.d() == 1 && this.g();
    }

    protected OMovingObjectPosition a(OWorld var1, OEntityPlayer var2, boolean var3) {
        float var4 = 1.0F;
        float var5 = var2.bv + (var2.bt - var2.bv) * var4;
        float var6 = var2.bu + (var2.bs - var2.bu) * var4;
        double var7 = var2.bj + (var2.bm - var2.bj) * var4;
        double var9 = var2.bk + (var2.bn - var2.bk) * var4 + 1.62D - var2.bF;
        double var11 = var2.bl + (var2.bo - var2.bl) * var4;
        OVec3D var13 = OVec3D.b(var7, var9, var11);
        float var14 = OMathHelper.b(-var6 * 0.017453292F - 3.1415927F);
        float var15 = OMathHelper.a(-var6 * 0.017453292F - 3.1415927F);
        float var16 = -OMathHelper.b(-var5 * 0.017453292F);
        float var17 = OMathHelper.a(-var5 * 0.017453292F);
        float var18 = var15 * var16;
        float var20 = var14 * var16;
        double var21 = 5.0D;
        OVec3D var23 = var13.c(var18 * var21, var17 * var21, var20 * var21);
        OMovingObjectPosition var24 = var1.a(var13, var23, var3, !var3);
        return var24;
    }

    public int c() {
        return 0;
    }

    static {
        OStatList.c();
    }
}
