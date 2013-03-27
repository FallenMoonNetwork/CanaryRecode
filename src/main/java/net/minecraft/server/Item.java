package net.minecraft.server;


import java.util.Random;
import net.canarymod.api.inventory.CanaryBaseItem;


public class Item {

    private CreativeTabs a = null;
    protected static Random e = new Random();
    public static Item[] f = new Item[32000];
    public static Item g = (new ItemSpade(0, EnumToolMaterial.c)).b("shovelIron");
    public static Item h = (new ItemPickaxe(1, EnumToolMaterial.c)).b("pickaxeIron");
    public static Item i = (new ItemAxe(2, EnumToolMaterial.c)).b("hatchetIron");
    public static Item j = (new ItemFlintAndSteel(3)).b("flintAndSteel");
    public static Item k = (new ItemFood(4, 4, 0.3F, false)).b("apple");
    public static ItemBow l = (ItemBow) (new ItemBow(5)).b("bow");
    public static Item m = (new Item(6)).b("arrow").a(CreativeTabs.j);
    public static Item n = (new ItemCoal(7)).b("coal");
    public static Item o = (new Item(8)).b("diamond").a(CreativeTabs.l);
    public static Item p = (new Item(9)).b("ingotIron").a(CreativeTabs.l);
    public static Item q = (new Item(10)).b("ingotGold").a(CreativeTabs.l);
    public static Item r = (new ItemSword(11, EnumToolMaterial.c)).b("swordIron");
    public static Item s = (new ItemSword(12, EnumToolMaterial.a)).b("swordWood");
    public static Item t = (new ItemSpade(13, EnumToolMaterial.a)).b("shovelWood");
    public static Item u = (new ItemPickaxe(14, EnumToolMaterial.a)).b("pickaxeWood");
    public static Item v = (new ItemAxe(15, EnumToolMaterial.a)).b("hatchetWood");
    public static Item w = (new ItemSword(16, EnumToolMaterial.b)).b("swordStone");
    public static Item x = (new ItemSpade(17, EnumToolMaterial.b)).b("shovelStone");
    public static Item y = (new ItemPickaxe(18, EnumToolMaterial.b)).b("pickaxeStone");
    public static Item z = (new ItemAxe(19, EnumToolMaterial.b)).b("hatchetStone");
    public static Item A = (new ItemSword(20, EnumToolMaterial.d)).b("swordDiamond");
    public static Item B = (new ItemSpade(21, EnumToolMaterial.d)).b("shovelDiamond");
    public static Item C = (new ItemPickaxe(22, EnumToolMaterial.d)).b("pickaxeDiamond");
    public static Item D = (new ItemAxe(23, EnumToolMaterial.d)).b("hatchetDiamond");
    public static Item E = (new Item(24)).p().b("stick").a(CreativeTabs.l);
    public static Item F = (new Item(25)).b("bowl").a(CreativeTabs.l);
    public static Item G = (new ItemSoup(26, 6)).b("mushroomStew");
    public static Item H = (new ItemSword(27, EnumToolMaterial.e)).b("swordGold");
    public static Item I = (new ItemSpade(28, EnumToolMaterial.e)).b("shovelGold");
    public static Item J = (new ItemPickaxe(29, EnumToolMaterial.e)).b("pickaxeGold");
    public static Item K = (new ItemAxe(30, EnumToolMaterial.e)).b("hatchetGold");
    public static Item L = (new ItemReed(31, Block.bY)).b("string").a(CreativeTabs.l);
    public static Item M = (new Item(32)).b("feather").a(CreativeTabs.l);
    public static Item N = (new Item(33)).b("sulphur").c(PotionHelper.k).a(CreativeTabs.l);
    public static Item O = (new ItemHoe(34, EnumToolMaterial.a)).b("hoeWood");
    public static Item P = (new ItemHoe(35, EnumToolMaterial.b)).b("hoeStone");
    public static Item Q = (new ItemHoe(36, EnumToolMaterial.c)).b("hoeIron");
    public static Item R = (new ItemHoe(37, EnumToolMaterial.d)).b("hoeDiamond");
    public static Item S = (new ItemHoe(38, EnumToolMaterial.e)).b("hoeGold");
    public static Item T = (new ItemSeeds(39, Block.aD.cz, Block.aE.cz)).b("seeds");
    public static Item U = (new Item(40)).b("wheat").a(CreativeTabs.l);
    public static Item V = (new ItemFood(41, 5, 0.6F, false)).b("bread");
    public static ItemArmor W = (ItemArmor) (new ItemArmor(42, EnumArmorMaterial.a, 0, 0)).b("helmetCloth");
    public static ItemArmor X = (ItemArmor) (new ItemArmor(43, EnumArmorMaterial.a, 0, 1)).b("chestplateCloth");
    public static ItemArmor Y = (ItemArmor) (new ItemArmor(44, EnumArmorMaterial.a, 0, 2)).b("leggingsCloth");
    public static ItemArmor Z = (ItemArmor) (new ItemArmor(45, EnumArmorMaterial.a, 0, 3)).b("bootsCloth");
    public static ItemArmor aa = (ItemArmor) (new ItemArmor(46, EnumArmorMaterial.b, 1, 0)).b("helmetChain");
    public static ItemArmor ab = (ItemArmor) (new ItemArmor(47, EnumArmorMaterial.b, 1, 1)).b("chestplateChain");
    public static ItemArmor ac = (ItemArmor) (new ItemArmor(48, EnumArmorMaterial.b, 1, 2)).b("leggingsChain");
    public static ItemArmor ad = (ItemArmor) (new ItemArmor(49, EnumArmorMaterial.b, 1, 3)).b("bootsChain");
    public static ItemArmor ae = (ItemArmor) (new ItemArmor(50, EnumArmorMaterial.c, 2, 0)).b("helmetIron");
    public static ItemArmor af = (ItemArmor) (new ItemArmor(51, EnumArmorMaterial.c, 2, 1)).b("chestplateIron");
    public static ItemArmor ag = (ItemArmor) (new ItemArmor(52, EnumArmorMaterial.c, 2, 2)).b("leggingsIron");
    public static ItemArmor ah = (ItemArmor) (new ItemArmor(53, EnumArmorMaterial.c, 2, 3)).b("bootsIron");
    public static ItemArmor ai = (ItemArmor) (new ItemArmor(54, EnumArmorMaterial.e, 3, 0)).b("helmetDiamond");
    public static ItemArmor aj = (ItemArmor) (new ItemArmor(55, EnumArmorMaterial.e, 3, 1)).b("chestplateDiamond");
    public static ItemArmor ak = (ItemArmor) (new ItemArmor(56, EnumArmorMaterial.e, 3, 2)).b("leggingsDiamond");
    public static ItemArmor al = (ItemArmor) (new ItemArmor(57, EnumArmorMaterial.e, 3, 3)).b("bootsDiamond");
    public static ItemArmor am = (ItemArmor) (new ItemArmor(58, EnumArmorMaterial.d, 4, 0)).b("helmetGold");
    public static ItemArmor an = (ItemArmor) (new ItemArmor(59, EnumArmorMaterial.d, 4, 1)).b("chestplateGold");
    public static ItemArmor ao = (ItemArmor) (new ItemArmor(60, EnumArmorMaterial.d, 4, 2)).b("leggingsGold");
    public static ItemArmor ap = (ItemArmor) (new ItemArmor(61, EnumArmorMaterial.d, 4, 3)).b("bootsGold");
    public static Item aq = (new Item(62)).b("flint").a(CreativeTabs.l);
    public static Item ar = (new ItemFood(63, 3, 0.3F, true)).b("porkchopRaw");
    public static Item as = (new ItemFood(64, 8, 0.8F, true)).b("porkchopCooked");
    public static Item at = (new ItemHangingEntity(65, EntityPainting.class)).b("painting");
    public static Item au = (new ItemAppleGold(66, 4, 1.2F, false)).j().a(Potion.l.H, 5, 0, 1.0F).b("appleGold");
    public static Item av = (new ItemSign(67)).b("sign");
    public static Item aw = (new ItemDoor(68, Material.d)).b("doorWood");
    public static Item ax = (new ItemBucket(69, 0)).b("bucket").d(16);
    public static Item ay = (new ItemBucket(70, Block.E.cz)).b("bucketWater").a(ax);
    public static Item az = (new ItemBucket(71, Block.G.cz)).b("bucketLava").a(ax);
    public static Item aA = (new ItemMinecart(72, 0)).b("minecart");
    public static Item aB = (new ItemSaddle(73)).b("saddle");
    public static Item aC = (new ItemDoor(74, Material.f)).b("doorIron");
    public static Item aD = (new ItemRedstone(75)).b("redstone").c(PotionHelper.i);
    public static Item aE = (new ItemSnowball(76)).b("snowball");
    public static Item aF = (new ItemBoat(77)).b("boat");
    public static Item aG = (new Item(78)).b("leather").a(CreativeTabs.l);
    public static Item aH = (new ItemBucketMilk(79)).b("milk").a(ax);
    public static Item aI = (new Item(80)).b("brick").a(CreativeTabs.l);
    public static Item aJ = (new Item(81)).b("clay").a(CreativeTabs.l);
    public static Item aK = (new ItemReed(82, Block.bb)).b("reeds").a(CreativeTabs.l);
    public static Item aL = (new Item(83)).b("paper").a(CreativeTabs.f);
    public static Item aM = (new ItemBook(84)).b("book").a(CreativeTabs.f);
    public static Item aN = (new Item(85)).b("slimeball").a(CreativeTabs.f);
    public static Item aO = (new ItemMinecart(86, 1)).b("minecartChest");
    public static Item aP = (new ItemMinecart(87, 2)).b("minecartFurnace");
    public static Item aQ = (new ItemEgg(88)).b("egg");
    public static Item aR = (new Item(89)).b("compass").a(CreativeTabs.i);
    public static ItemFishingRod aS = (ItemFishingRod) (new ItemFishingRod(90)).b("fishingRod");
    public static Item aT = (new Item(91)).b("clock").a(CreativeTabs.i);
    public static Item aU = (new Item(92)).b("yellowDust").c(PotionHelper.j).a(CreativeTabs.l);
    public static Item aV = (new ItemFood(93, 2, 0.3F, false)).b("fishRaw");
    public static Item aW = (new ItemFood(94, 5, 0.6F, false)).b("fishCooked");
    public static Item aX = (new ItemDye(95)).b("dyePowder");
    public static Item aY = (new Item(96)).b("bone").p().a(CreativeTabs.f);
    public static Item aZ = (new Item(97)).b("sugar").c(PotionHelper.b).a(CreativeTabs.l);
    public static Item ba = (new ItemReed(98, Block.bk)).d(1).b("cake").a(CreativeTabs.h);
    public static Item bb = (new ItemBed(99)).d(1).b("bed");
    public static Item bc = (new ItemReed(100, Block.bl)).b("diode").a(CreativeTabs.d);
    public static Item bd = (new ItemFood(101, 2, 0.1F, false)).b("cookie");
    public static ItemMap be = (ItemMap) (new ItemMap(102)).b("map");
    public static ItemShears bf = (ItemShears) (new ItemShears(103)).b("shears");
    public static Item bg = (new ItemFood(104, 2, 0.3F, false)).b("melon");
    public static Item bh = (new ItemSeeds(105, Block.bw.cz, Block.aE.cz)).b("seeds_pumpkin");
    public static Item bi = (new ItemSeeds(106, Block.bx.cz, Block.aE.cz)).b("seeds_melon");
    public static Item bj = (new ItemFood(107, 3, 0.3F, true)).b("beefRaw");
    public static Item bk = (new ItemFood(108, 8, 0.8F, true)).b("beefCooked");
    public static Item bl = (new ItemFood(109, 2, 0.3F, true)).a(Potion.s.H, 30, 0, 0.3F).b("chickenRaw");
    public static Item bm = (new ItemFood(110, 6, 0.6F, true)).b("chickenCooked");
    public static Item bn = (new ItemFood(111, 4, 0.1F, true)).a(Potion.s.H, 30, 0, 0.8F).b("rottenFlesh");
    public static Item bo = (new ItemEnderPearl(112)).b("enderPearl");
    public static Item bp = (new Item(113)).b("blazeRod").a(CreativeTabs.l);
    public static Item bq = (new Item(114)).b("ghastTear").c(PotionHelper.c).a(CreativeTabs.k);
    public static Item br = (new Item(115)).b("goldNugget").a(CreativeTabs.l);
    public static Item bs = (new ItemSeeds(116, Block.bH.cz, Block.bg.cz)).b("netherStalkSeeds").c("+4");
    public static ItemPotion bt = (ItemPotion) (new ItemPotion(117)).b("potion");
    public static Item bu = (new ItemGlassBottle(118)).b("glassBottle");
    public static Item bv = (new ItemFood(119, 2, 0.8F, false)).a(Potion.u.H, 5, 0, 1.0F).b("spiderEye").c(PotionHelper.d);
    public static Item bw = (new Item(120)).b("fermentedSpiderEye").c(PotionHelper.e).a(CreativeTabs.k);
    public static Item bx = (new Item(121)).b("blazePowder").c(PotionHelper.g).a(CreativeTabs.k);
    public static Item by = (new Item(122)).b("magmaCream").c(PotionHelper.h).a(CreativeTabs.k);
    public static Item bz = (new ItemReed(123, Block.bJ)).b("brewingStand").a(CreativeTabs.k);
    public static Item bA = (new ItemReed(124, Block.bK)).b("cauldron").a(CreativeTabs.k);
    public static Item bB = (new ItemEnderEye(125)).b("eyeOfEnder");
    public static Item bC = (new Item(126)).b("speckledMelon").c(PotionHelper.f).a(CreativeTabs.k);
    public static Item bD = (new ItemMonsterPlacer(127)).b("monsterPlacer");
    public static Item bE = (new ItemExpBottle(128)).b("expBottle");
    public static Item bF = (new ItemFireball(129)).b("fireball");
    public static Item bG = (new ItemWritableBook(130)).b("writingBook").a(CreativeTabs.f);
    public static Item bH = (new ItemEditableBook(131)).b("writtenBook");
    public static Item bI = (new Item(132)).b("emerald").a(CreativeTabs.l);
    public static Item bJ = (new ItemHangingEntity(133, EntityItemFrame.class)).b("frame");
    public static Item bK = (new ItemReed(134, Block.cg)).b("flowerPot").a(CreativeTabs.c);
    public static Item bL = (new ItemSeedFood(135, 4, 0.6F, Block.ch.cz, Block.aE.cz)).b("carrots");
    public static Item bM = (new ItemSeedFood(136, 1, 0.3F, Block.ci.cz, Block.aE.cz)).b("potato");
    public static Item bN = (new ItemFood(137, 6, 0.6F, false)).b("potatoBaked");
    public static Item bO = (new ItemFood(138, 2, 0.3F, false)).a(Potion.u.H, 5, 0, 0.6F).b("potatoPoisonous");
    public static ItemEmptyMap bP = (ItemEmptyMap) (new ItemEmptyMap(139)).b("emptyMap");
    public static Item bQ = (new ItemFood(140, 6, 1.2F, false)).b("carrotGolden").c(PotionHelper.l);
    public static Item bR = (new ItemSkull(141)).b("skull");
    public static Item bS = (new ItemCarrotOnAStick(142)).b("carrotOnAStick");
    public static Item bT = (new ItemSimpleFoiled(143)).b("netherStar").a(CreativeTabs.l);
    public static Item bU = (new ItemFood(144, 8, 0.3F, false)).b("pumpkinPie").a(CreativeTabs.h);
    public static Item bV = (new ItemFirework(145)).b("fireworks");
    public static Item bW = (new ItemFireworkCharge(146)).b("fireworksCharge").a(CreativeTabs.f);
    public static ItemEnchantedBook bX = (ItemEnchantedBook) (new ItemEnchantedBook(147)).d(1).b("enchantedBook");
    public static Item bY = (new ItemReed(148, Block.cp)).b("comparator").a(CreativeTabs.d);
    public static Item bZ = (new Item(149)).b("netherbrick").a(CreativeTabs.l);
    public static Item ca = (new Item(150)).b("netherquartz").a(CreativeTabs.l);
    public static Item cb = (new ItemMinecart(151, 3)).b("minecartTnt");
    public static Item cc = (new ItemMinecart(152, 5)).b("minecartHopper");
    public static Item cd = (new ItemRecord(2000, "13")).b("record");
    public static Item ce = (new ItemRecord(2001, "cat")).b("record");
    public static Item cf = (new ItemRecord(2002, "blocks")).b("record");
    public static Item cg = (new ItemRecord(2003, "chirp")).b("record");
    public static Item ch = (new ItemRecord(2004, "far")).b("record");
    public static Item ci = (new ItemRecord(2005, "mall")).b("record");
    public static Item cj = (new ItemRecord(2006, "mellohi")).b("record");
    public static Item ck = (new ItemRecord(2007, "stal")).b("record");
    public static Item cl = (new ItemRecord(2008, "strad")).b("record");
    public static Item cm = (new ItemRecord(2009, "ward")).b("record");
    public static Item cn = (new ItemRecord(2010, "11")).b("record");
    public static Item co = (new ItemRecord(2011, "wait")).b("record");
    public final int cp;
    protected int cq = 64;
    private int b = 0;
    protected boolean cr = false;
    protected boolean cs = false;
    private Item c = null;
    private String d = null;
    private String cu;

    // CanaryMod
    private CanaryBaseItem base;

    //

    protected Item(int i0) {
        this.cp = 256 + i0;
        if (f[256 + i0] != null) {
            System.out.println("CONFLICT @ " + i0);
        }

        f[256 + i0] = this;
        this.base = new CanaryBaseItem(this); // CanaryMod: wrap Item
    }

    public Item d(int i0) {
        this.cq = i0;
        return this;
    }

    public boolean a(ItemStack itemstack, EntityPlayer entityplayer, World world, int i0, int i1, int i2, int i3, float f0, float f1, float f2) {
        return false;
    }

    public float a(ItemStack itemstack, Block block) {
        return 1.0F;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        return itemstack;
    }

    public ItemStack b(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        return itemstack;
    }

    public int l() {
        return this.cq;
    }

    public int a(int i0) {
        return 0;
    }

    public boolean m() {
        return this.cs;
    }

    protected Item a(boolean flag0) {
        this.cs = flag0;
        return this;
    }

    public int n() {
        return this.b;
    }

    public Item e(int i0) { // CanaryMod: protected => public
        this.b = i0;
        return this;
    }

    public boolean o() {
        return this.b > 0 && !this.cs;
    }

    public boolean a(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1) {
        return false;
    }

    public boolean a(ItemStack itemstack, World world, int i0, int i1, int i2, int i3, EntityLiving entityliving) {
        return false;
    }

    public int a(Entity entity) {
        return 1;
    }

    public boolean a(Block block) {
        return false;
    }

    public boolean a(ItemStack itemstack, EntityLiving entityliving) {
        return false;
    }

    public Item p() {
        this.cr = true;
        return this;
    }

    public Item b(String s0) {
        this.cu = s0;
        return this;
    }

    public String i(ItemStack itemstack) {
        String s0 = this.d(itemstack);

        return s0 == null ? "" : StatCollector.a(s0);
    }

    public String a() {
        return "item." + this.cu;
    }

    public String d(ItemStack itemstack) {
        return "item." + this.cu;
    }

    public Item a(Item item) {
        this.c = item;
        return this;
    }

    public boolean j(ItemStack itemstack) {
        return true;
    }

    public boolean r() {
        return true;
    }

    public Item s() {
        return this.c;
    }

    public boolean t() {
        return this.c != null;
    }

    public String u() {
        return StatCollector.a(this.a() + ".name");
    }

    public String k(ItemStack itemstack) {
        return StatCollector.a(this.d(itemstack) + ".name");
    }

    public void a(ItemStack itemstack, World world, Entity entity, int i0, boolean flag0) {}

    public void d(ItemStack itemstack, World world, EntityPlayer entityplayer) {}

    public boolean f() {
        return false;
    }

    public EnumAction b_(ItemStack itemstack) {
        return EnumAction.a;
    }

    public int c_(ItemStack itemstack) {
        return 0;
    }

    public void a(ItemStack itemstack, World world, EntityPlayer entityplayer, int i0) {}

    protected Item c(String s0) {
        this.d = s0;
        return this;
    }

    public String v() {
        return this.d;
    }

    public boolean w() {
        return this.d != null;
    }

    public String l(ItemStack itemstack) {
        return ("" + StringTranslate.a().c(this.i(itemstack))).trim();
    }

    public boolean d_(ItemStack itemstack) {
        return this.l() == 1 && this.o();
    }

    protected MovingObjectPosition a(World world, EntityPlayer entityplayer, boolean flag0) {
        float f0 = 1.0F;
        float f1 = entityplayer.D + (entityplayer.B - entityplayer.D) * f0;
        float f2 = entityplayer.C + (entityplayer.A - entityplayer.C) * f0;
        double d0 = entityplayer.r + (entityplayer.u - entityplayer.r) * (double) f0;
        double d1 = entityplayer.s + (entityplayer.v - entityplayer.s) * (double) f0 + 1.62D - (double) entityplayer.N;
        double d2 = entityplayer.t + (entityplayer.w - entityplayer.t) * (double) f0;
        Vec3 vec3 = world.T().a(d0, d1, d2);
        float f3 = MathHelper.b(-f2 * 0.017453292F - 3.1415927F);
        float f4 = MathHelper.a(-f2 * 0.017453292F - 3.1415927F);
        float f5 = -MathHelper.b(-f1 * 0.017453292F);
        float f6 = MathHelper.a(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 5.0D;
        Vec3 vec31 = vec3.c((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);

        return world.a(vec3, vec31, flag0, !flag0);
    }

    public int c() {
        return 0;
    }

    public Item a(CreativeTabs creativetabs) {
        this.a = creativetabs;
        return this;
    }

    public boolean y() {
        return true;
    }

    public boolean a(ItemStack itemstack, ItemStack itemstack1) {
        return false;
    }

    // CanaryMod
    public CanaryBaseItem getBaseItem() {
        return this.base;
    }

    //

    static {
        StatList.c();
    }
}
