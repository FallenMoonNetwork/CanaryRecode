package net.minecraft.server;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.canarymod.api.inventory.CanaryBaseItem;

import java.util.Random;
import java.util.UUID;

public class Item {

    protected static final UUID e = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
    private CreativeTabs a;
    protected static Random f = new Random();
    public static Item[] g = new Item[32000];
    public static Item h = (new ItemSpade(0, EnumToolMaterial.c)).b("shovelIron").d("iron_shovel");
    public static Item i = (new ItemPickaxe(1, EnumToolMaterial.c)).b("pickaxeIron").d("iron_pickaxe");
    public static Item j = (new ItemAxe(2, EnumToolMaterial.c)).b("hatchetIron").d("iron_axe");
    public static Item k = (new ItemFlintAndSteel(3)).b("flintAndSteel").d("flint_and_steel");
    public static Item l = (new ItemFood(4, 4, 0.3F, false)).b("apple").d("apple");
    public static ItemBow m = (ItemBow)(new ItemBow(5)).b("bow").d("bow");
    public static Item n = (new Item(6)).b("arrow").a(CreativeTabs.j).d("arrow");
    public static Item o = (new ItemCoal(7)).b("coal").d("coal");
    public static Item p = (new Item(8)).b("diamond").a(CreativeTabs.l).d("diamond");
    public static Item q = (new Item(9)).b("ingotIron").a(CreativeTabs.l).d("iron_ingot");
    public static Item r = (new Item(10)).b("ingotGold").a(CreativeTabs.l).d("gold_ingot");
    public static Item s = (new ItemSword(11, EnumToolMaterial.c)).b("swordIron").d("iron_sword");
    public static Item t = (new ItemSword(12, EnumToolMaterial.a)).b("swordWood").d("wood_sword");
    public static Item u = (new ItemSpade(13, EnumToolMaterial.a)).b("shovelWood").d("wood_shovel");
    public static Item v = (new ItemPickaxe(14, EnumToolMaterial.a)).b("pickaxeWood").d("wood_pickaxe");
    public static Item w = (new ItemAxe(15, EnumToolMaterial.a)).b("hatchetWood").d("wood_axe");
    public static Item x = (new ItemSword(16, EnumToolMaterial.b)).b("swordStone").d("stone_sword");
    public static Item y = (new ItemSpade(17, EnumToolMaterial.b)).b("shovelStone").d("stone_shovel");
    public static Item z = (new ItemPickaxe(18, EnumToolMaterial.b)).b("pickaxeStone").d("stone_pickaxe");
    public static Item A = (new ItemAxe(19, EnumToolMaterial.b)).b("hatchetStone").d("stone_axe");
    public static Item B = (new ItemSword(20, EnumToolMaterial.d)).b("swordDiamond").d("diamond_sword");
    public static Item C = (new ItemSpade(21, EnumToolMaterial.d)).b("shovelDiamond").d("diamond_shovel");
    public static Item D = (new ItemPickaxe(22, EnumToolMaterial.d)).b("pickaxeDiamond").d("diamond_pickaxe");
    public static Item E = (new ItemAxe(23, EnumToolMaterial.d)).b("hatchetDiamond").d("diamond_axe");
    public static Item F = (new Item(24)).q().b("stick").a(CreativeTabs.l).d("stick");
    public static Item G = (new Item(25)).b("bowl").a(CreativeTabs.l).d("bowl");
    public static Item H = (new ItemSoup(26, 6)).b("mushroomStew").d("mushroom_stew");
    public static Item I = (new ItemSword(27, EnumToolMaterial.e)).b("swordGold").d("gold_sword");
    public static Item J = (new ItemSpade(28, EnumToolMaterial.e)).b("shovelGold").d("gold_shovel");
    public static Item K = (new ItemPickaxe(29, EnumToolMaterial.e)).b("pickaxeGold").d("gold_pickaxe");
    public static Item L = (new ItemAxe(30, EnumToolMaterial.e)).b("hatchetGold").d("gold_axe");
    public static Item M = (new ItemReed(31, Block.bZ)).b("string").a(CreativeTabs.l).d("string");
    public static Item N = (new Item(32)).b("feather").a(CreativeTabs.l).d("feather");
    public static Item O = (new Item(33)).b("sulphur").c(PotionHelper.k).a(CreativeTabs.l).d("gunpowder");
    public static Item P = (new ItemHoe(34, EnumToolMaterial.a)).b("hoeWood").d("wood_hoe");
    public static Item Q = (new ItemHoe(35, EnumToolMaterial.b)).b("hoeStone").d("stone_hoe");
    public static Item R = (new ItemHoe(36, EnumToolMaterial.c)).b("hoeIron").d("iron_hoe");
    public static Item S = (new ItemHoe(37, EnumToolMaterial.d)).b("hoeDiamond").d("diamond_hoe");
    public static Item T = (new ItemHoe(38, EnumToolMaterial.e)).b("hoeGold").d("gold_hoe");
    public static Item U = (new ItemSeeds(39, Block.aE.cF, Block.aF.cF)).b("seeds").d("seeds_wheat");
    public static Item V = (new Item(40)).b("wheat").a(CreativeTabs.l).d("wheat");
    public static Item W = (new ItemFood(41, 5, 0.6F, false)).b("bread").d("bread");
    public static ItemArmor X = (ItemArmor)(new ItemArmor(42, EnumArmorMaterial.a, 0, 0)).b("helmetCloth").d("leather_helmet");
    public static ItemArmor Y = (ItemArmor)(new ItemArmor(43, EnumArmorMaterial.a, 0, 1)).b("chestplateCloth").d("leather_chestplate");
    public static ItemArmor Z = (ItemArmor)(new ItemArmor(44, EnumArmorMaterial.a, 0, 2)).b("leggingsCloth").d("leather_leggings");
    public static ItemArmor aa = (ItemArmor)(new ItemArmor(45, EnumArmorMaterial.a, 0, 3)).b("bootsCloth").d("leather_boots");
    public static ItemArmor ab = (ItemArmor)(new ItemArmor(46, EnumArmorMaterial.b, 1, 0)).b("helmetChain").d("chainmail_helmet");
    public static ItemArmor ac = (ItemArmor)(new ItemArmor(47, EnumArmorMaterial.b, 1, 1)).b("chestplateChain").d("chainmail_chestplate");
    public static ItemArmor ad = (ItemArmor)(new ItemArmor(48, EnumArmorMaterial.b, 1, 2)).b("leggingsChain").d("chainmail_leggings");
    public static ItemArmor ae = (ItemArmor)(new ItemArmor(49, EnumArmorMaterial.b, 1, 3)).b("bootsChain").d("chainmail_boots");
    public static ItemArmor af = (ItemArmor)(new ItemArmor(50, EnumArmorMaterial.c, 2, 0)).b("helmetIron").d("iron_helmet");
    public static ItemArmor ag = (ItemArmor)(new ItemArmor(51, EnumArmorMaterial.c, 2, 1)).b("chestplateIron").d("iron_chestplate");
    public static ItemArmor ah = (ItemArmor)(new ItemArmor(52, EnumArmorMaterial.c, 2, 2)).b("leggingsIron").d("iron_leggings");
    public static ItemArmor ai = (ItemArmor)(new ItemArmor(53, EnumArmorMaterial.c, 2, 3)).b("bootsIron").d("iron_boots");
    public static ItemArmor aj = (ItemArmor)(new ItemArmor(54, EnumArmorMaterial.e, 3, 0)).b("helmetDiamond").d("diamond_helmet");
    public static ItemArmor ak = (ItemArmor)(new ItemArmor(55, EnumArmorMaterial.e, 3, 1)).b("chestplateDiamond").d("diamond_chestplate");
    public static ItemArmor al = (ItemArmor)(new ItemArmor(56, EnumArmorMaterial.e, 3, 2)).b("leggingsDiamond").d("diamond_leggings");
    public static ItemArmor am = (ItemArmor)(new ItemArmor(57, EnumArmorMaterial.e, 3, 3)).b("bootsDiamond").d("diamond_boots");
    public static ItemArmor an = (ItemArmor)(new ItemArmor(58, EnumArmorMaterial.d, 4, 0)).b("helmetGold").d("gold_helmet");
    public static ItemArmor ao = (ItemArmor)(new ItemArmor(59, EnumArmorMaterial.d, 4, 1)).b("chestplateGold").d("gold_chestplate");
    public static ItemArmor ap = (ItemArmor)(new ItemArmor(60, EnumArmorMaterial.d, 4, 2)).b("leggingsGold").d("gold_leggings");
    public static ItemArmor aq = (ItemArmor)(new ItemArmor(61, EnumArmorMaterial.d, 4, 3)).b("bootsGold").d("gold_boots");
    public static Item ar = (new Item(62)).b("flint").a(CreativeTabs.l).d("flint");
    public static Item as = (new ItemFood(63, 3, 0.3F, true)).b("porkchopRaw").d("porkchop_raw");
    public static Item at = (new ItemFood(64, 8, 0.8F, true)).b("porkchopCooked").d("porkchop_cooked");
    public static Item au = (new ItemHangingEntity(65, EntityPainting.class)).b("painting").d("painting");
    public static Item av = (new ItemAppleGold(66, 4, 1.2F, false)).k().a(Potion.l.H, 5, 1, 1.0F).b("appleGold").d("apple_golden");
    public static Item aw = (new ItemSign(67)).b("sign").d("sign");
    public static Item ax = (new ItemDoor(68, Material.d)).b("doorWood").d("door_wood");
    public static Item ay = (new ItemBucket(69, 0)).b("bucket").d(16).d("bucket_empty");
    public static Item az = (new ItemBucket(70, Block.F.cF)).b("bucketWater").a(ay).d("bucket_water");
    public static Item aA = (new ItemBucket(71, Block.H.cF)).b("bucketLava").a(ay).d("bucket_lava");
    public static Item aB = (new ItemMinecart(72, 0)).b("minecart").d("minecart_normal");
    public static Item aC = (new ItemSaddle(73)).b("saddle").d("saddle");
    public static Item aD = (new ItemDoor(74, Material.f)).b("doorIron").d("door_iron");
    public static Item aE = (new ItemRedstone(75)).b("redstone").c(PotionHelper.i).d("redstone_dust");
    public static Item aF = (new ItemSnowball(76)).b("snowball").d("snowball");
    public static Item aG = (new ItemBoat(77)).b("boat").d("boat");
    public static Item aH = (new Item(78)).b("leather").a(CreativeTabs.l).d("leather");
    public static Item aI = (new ItemBucketMilk(79)).b("milk").a(ay).d("bucket_milk");
    public static Item aJ = (new Item(80)).b("brick").a(CreativeTabs.l).d("brick");
    public static Item aK = (new Item(81)).b("clay").a(CreativeTabs.l).d("clay_ball");
    public static Item aL = (new ItemReed(82, Block.bc)).b("reeds").a(CreativeTabs.l).d("reeds");
    public static Item aM = (new Item(83)).b("paper").a(CreativeTabs.f).d("paper");
    public static Item aN = (new ItemBook(84)).b("book").a(CreativeTabs.f).d("book_normal");
    public static Item aO = (new Item(85)).b("slimeball").a(CreativeTabs.f).d("slimeball");
    public static Item aP = (new ItemMinecart(86, 1)).b("minecartChest").d("minecart_chest");
    public static Item aQ = (new ItemMinecart(87, 2)).b("minecartFurnace").d("minecart_furnace");
    public static Item aR = (new ItemEgg(88)).b("egg").d("egg");
    public static Item aS = (new Item(89)).b("compass").a(CreativeTabs.i).d("compass");
    public static ItemFishingRod aT = (ItemFishingRod)(new ItemFishingRod(90)).b("fishingRod").d("fishing_rod");
    public static Item aU = (new Item(91)).b("clock").a(CreativeTabs.i).d("clock");
    public static Item aV = (new Item(92)).b("yellowDust").c(PotionHelper.j).a(CreativeTabs.l).d("glowstone_dust");
    public static Item aW = (new ItemFood(93, 2, 0.3F, false)).b("fishRaw").d("fish_raw");
    public static Item aX = (new ItemFood(94, 5, 0.6F, false)).b("fishCooked").d("fish_cooked");
    public static Item aY = (new ItemDye(95)).b("dyePowder").d("dye_powder");
    public static Item aZ = (new Item(96)).b("bone").q().a(CreativeTabs.f).d("bone");
    public static Item ba = (new Item(97)).b("sugar").c(PotionHelper.b).a(CreativeTabs.l).d("sugar");
    public static Item bb = (new ItemReed(98, Block.bl)).d(1).b("cake").a(CreativeTabs.h).d("cake");
    public static Item bc = (new ItemBed(99)).d(1).b("bed").d("bed");
    public static Item bd = (new ItemReed(100, Block.bm)).b("diode").a(CreativeTabs.d).d("repeater");
    public static Item be = (new ItemFood(101, 2, 0.1F, false)).b("cookie").d("cookie");
    public static ItemMap bf = (ItemMap)(new ItemMap(102)).b("map").d("map_filled");
    public static ItemShears bg = (ItemShears)(new ItemShears(103)).b("shears").d("shears");
    public static Item bh = (new ItemFood(104, 2, 0.3F, false)).b("melon").d("melon");
    public static Item bi = (new ItemSeeds(105, Block.bx.cF, Block.aF.cF)).b("seeds_pumpkin").d("seeds_pumpkin");
    public static Item bj = (new ItemSeeds(106, Block.by.cF, Block.aF.cF)).b("seeds_melon").d("seeds_melon");
    public static Item bk = (new ItemFood(107, 3, 0.3F, true)).b("beefRaw").d("beef_raw");
    public static Item bl = (new ItemFood(108, 8, 0.8F, true)).b("beefCooked").d("beef_cooked");
    public static Item bm = (new ItemFood(109, 2, 0.3F, true)).a(Potion.s.H, 30, 0, 0.3F).b("chickenRaw").d("chicken_raw");
    public static Item bn = (new ItemFood(110, 6, 0.6F, true)).b("chickenCooked").d("chicken_cooked");
    public static Item bo = (new ItemFood(111, 4, 0.1F, true)).a(Potion.s.H, 30, 0, 0.8F).b("rottenFlesh").d("rotten_flesh");
    public static Item bp = (new ItemEnderPearl(112)).b("enderPearl").d("ender_pearl");
    public static Item bq = (new Item(113)).b("blazeRod").a(CreativeTabs.l).d("blaze_rod");
    public static Item br = (new Item(114)).b("ghastTear").c(PotionHelper.c).a(CreativeTabs.k).d("ghast_tear");
    public static Item bs = (new Item(115)).b("goldNugget").a(CreativeTabs.l).d("gold_nugget");
    public static Item bt = (new ItemSeeds(116, Block.bI.cF, Block.bh.cF)).b("netherStalkSeeds").c("+4").d("nether_wart");
    public static ItemPotion bu = (ItemPotion)(new ItemPotion(117)).b("potion").d("potion");
    public static Item bv = (new ItemGlassBottle(118)).b("glassBottle").d("potion_bottle_empty");
    public static Item bw = (new ItemFood(119, 2, 0.8F, false)).a(Potion.u.H, 5, 0, 1.0F).b("spiderEye").c(PotionHelper.d).d("spider_eye");
    public static Item bx = (new Item(120)).b("fermentedSpiderEye").c(PotionHelper.e).a(CreativeTabs.k).d("spider_eye_fermented");
    public static Item by = (new Item(121)).b("blazePowder").c(PotionHelper.g).a(CreativeTabs.k).d("blaze_powder");
    public static Item bz = (new Item(122)).b("magmaCream").c(PotionHelper.h).a(CreativeTabs.k).d("magma_cream");
    public static Item bA = (new ItemReed(123, Block.bK)).b("brewingStand").a(CreativeTabs.k).d("brewing_stand");
    public static Item bB = (new ItemReed(124, Block.bL)).b("cauldron").a(CreativeTabs.k).d("cauldron");
    public static Item bC = (new ItemEnderEye(125)).b("eyeOfEnder").d("ender_eye");
    public static Item bD = (new Item(126)).b("speckledMelon").c(PotionHelper.f).a(CreativeTabs.k).d("melon_speckled");
    public static Item bE = (new ItemMonsterPlacer(127)).b("monsterPlacer").d("spawn_egg");
    public static Item bF = (new ItemExpBottle(128)).b("expBottle").d("experience_bottle");
    public static Item bG = (new ItemFireball(129)).b("fireball").d("fireball");
    public static Item bH = (new ItemWritableBook(130)).b("writingBook").a(CreativeTabs.f).d("book_writable");
    public static Item bI = (new ItemEditableBook(131)).b("writtenBook").d("book_written");
    public static Item bJ = (new Item(132)).b("emerald").a(CreativeTabs.l).d("emerald");
    public static Item bK = (new ItemHangingEntity(133, EntityItemFrame.class)).b("frame").d("item_frame");
    public static Item bL = (new ItemReed(134, Block.ch)).b("flowerPot").a(CreativeTabs.c).d("flower_pot");
    public static Item bM = (new ItemSeedFood(135, 4, 0.6F, Block.ci.cF, Block.aF.cF)).b("carrots").d("carrot");
    public static Item bN = (new ItemSeedFood(136, 1, 0.3F, Block.cj.cF, Block.aF.cF)).b("potato").d("potato");
    public static Item bO = (new ItemFood(137, 6, 0.6F, false)).b("potatoBaked").d("potato_baked");
    public static Item bP = (new ItemFood(138, 2, 0.3F, false)).a(Potion.u.H, 5, 0, 0.6F).b("potatoPoisonous").d("potato_poisonous");
    public static ItemEmptyMap bQ = (ItemEmptyMap)(new ItemEmptyMap(139)).b("emptyMap").d("map_empty");
    public static Item bR = (new ItemFood(140, 6, 1.2F, false)).b("carrotGolden").c(PotionHelper.l).d("carrot_golden");
    public static Item bS = (new ItemSkull(141)).b("skull").d("skull");
    public static Item bT = (new ItemCarrotOnAStick(142)).b("carrotOnAStick").d("carrot_on_a_stick");
    public static Item bU = (new ItemSimpleFoiled(143)).b("netherStar").a(CreativeTabs.l).d("nether_star");
    public static Item bV = (new ItemFood(144, 8, 0.3F, false)).b("pumpkinPie").a(CreativeTabs.h).d("pumpkin_pie");
    public static Item bW = (new ItemFirework(145)).b("fireworks").d("fireworks");
    public static Item bX = (new ItemFireworkCharge(146)).b("fireworksCharge").a(CreativeTabs.f).d("fireworks_charge");
    public static ItemEnchantedBook bY = (ItemEnchantedBook)(new ItemEnchantedBook(147)).d(1).b("enchantedBook").d("book_enchanted");
    public static Item bZ = (new ItemReed(148, Block.cq)).b("comparator").a(CreativeTabs.d).d("comparator");
    public static Item ca = (new Item(149)).b("netherbrick").a(CreativeTabs.l).d("netherbrick");
    public static Item cb = (new Item(150)).b("netherquartz").a(CreativeTabs.l).d("quartz");
    public static Item cc = (new ItemMinecart(151, 3)).b("minecartTnt").d("minecart_tnt");
    public static Item cd = (new ItemMinecart(152, 5)).b("minecartHopper").d("minecart_hopper");
    public static Item ce = (new Item(161)).b("horsearmormetal").d(1).a(CreativeTabs.f).d("iron_horse_armor");
    public static Item cf = (new Item(162)).b("horsearmorgold").d(1).a(CreativeTabs.f).d("gold_horse_armor");
    public static Item cg = (new Item(163)).b("horsearmordiamond").d(1).a(CreativeTabs.f).d("diamond_horse_armor");
    public static Item ch = (new ItemLeash(164)).b("leash").d("lead");
    public static Item ci = (new ItemNameTag(165)).b("nameTag").d("name_tag");
    public static Item cj = (new ItemRecord(2000, "13")).b("record").d("record_13");
    public static Item ck = (new ItemRecord(2001, "cat")).b("record").d("record_cat");
    public static Item cl = (new ItemRecord(2002, "blocks")).b("record").d("record_blocks");
    public static Item cm = (new ItemRecord(2003, "chirp")).b("record").d("record_chirp");
    public static Item cn = (new ItemRecord(2004, "far")).b("record").d("record_far");
    public static Item co = (new ItemRecord(2005, "mall")).b("record").d("record_mall");
    public static Item cp = (new ItemRecord(2006, "mellohi")).b("record").d("record_mellohi");
    public static Item cq = (new ItemRecord(2007, "stal")).b("record").d("record_stal");
    public static Item cr = (new ItemRecord(2008, "strad")).b("record").d("record_strad");
    public static Item cs = (new ItemRecord(2009, "ward")).b("record").d("record_ward");
    public static Item ct = (new ItemRecord(2010, "11")).b("record").d("record_11");
    public static Item cu = (new ItemRecord(2011, "wait")).b("record").d("record_wait");
    public final int cv;
    protected int cw = 64;
    private int b;
    protected boolean cx;
    protected boolean cy;
    private Item c;
    private String d;
    private String cB;
    protected String cA;

    private final CanaryBaseItem base; // CanaryMod

    protected Item(int i0) {
        this.cv = 256 + i0;
        if (g[256 + i0] != null) {
            System.out.println("CONFLICT @ " + i0);
        }

        g[256 + i0] = this;
        this.base = new CanaryBaseItem(this); // CanaryMod: wrap Item
    }

    public Item d(int i0) {
        this.cw = i0;
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

    public int m() {
        return this.cw;
    }

    public int a(int i0) {
        return 0;
    }

    public boolean n() {
        return this.cy;
    }

    protected Item a(boolean flag0) {
        this.cy = flag0;
        return this;
    }

    public int o() {
        return this.b;
    }

    public Item e(int i0) { // CanaryMod: protected -> public
        this.b = i0;
        return this;
    }

    public boolean p() {
        return this.b > 0 && !this.cy;
    }

    public boolean a(ItemStack itemstack, EntityLivingBase entitylivingbase, EntityLivingBase entitylivingbase1) {
        return false;
    }

    public boolean a(ItemStack itemstack, World world, int i0, int i1, int i2, int i3, EntityLivingBase entitylivingbase) {
        return false;
    }

    public boolean a(Block block) {
        return false;
    }

    public boolean a(ItemStack itemstack, EntityPlayer entityplayer, EntityLivingBase entitylivingbase) {
        return false;
    }

    public Item q() {
        this.cx = true;
        return this;
    }

    public Item b(String s0) {
        this.cB = s0;
        return this;
    }

    public String i(ItemStack itemstack) {
        String s0 = this.d(itemstack);

        return s0 == null ? "" : StatCollector.a(s0);
    }

    public String a() {
        return "item." + this.cB;
    }

    public String d(ItemStack itemstack) {
        return "item." + this.cB;
    }

    public Item a(Item item) {
        this.c = item;
        return this;
    }

    public boolean j(ItemStack itemstack) {
        return true;
    }

    public boolean s() {
        return true;
    }

    public Item t() {
        return this.c;
    }

    public boolean u() {
        return this.c != null;
    }

    public String v() {
        return StatCollector.a(this.a() + ".name");
    }

    public String k(ItemStack itemstack) {
        return StatCollector.a(this.d(itemstack) + ".name");
    }

    public void a(ItemStack itemstack, World world, Entity entity, int i0, boolean flag0) {
    }

    public void d(ItemStack itemstack, World world, EntityPlayer entityplayer) {
    }

    public boolean f() {
        return false;
    }

    public EnumAction c_(ItemStack itemstack) {
        return EnumAction.a;
    }

    public int d_(ItemStack itemstack) {
        return 0;
    }

    public void a(ItemStack itemstack, World world, EntityPlayer entityplayer, int i0) {
    }

    protected Item c(String s0) {
        this.d = s0;
        return this;
    }

    public String w() {
        return this.d;
    }

    public boolean x() {
        return this.d != null;
    }

    public String l(ItemStack itemstack) {
        return ("" + StatCollector.a(this.i(itemstack) + ".name")).trim();
    }

    public boolean e_(ItemStack itemstack) {
        return this.m() == 1 && this.p();
    }

    protected MovingObjectPosition a(World world, EntityPlayer entityplayer, boolean flag0) {
        float f0 = 1.0F;
        float f1 = entityplayer.D + (entityplayer.B - entityplayer.D) * f0;
        float f2 = entityplayer.C + (entityplayer.A - entityplayer.C) * f0;
        double d0 = entityplayer.r + (entityplayer.u - entityplayer.r) * (double)f0;
        double d1 = entityplayer.s + (entityplayer.v - entityplayer.s) * (double)f0 + 1.62D - (double)entityplayer.N;
        double d2 = entityplayer.t + (entityplayer.w - entityplayer.t) * (double)f0;
        Vec3 vec3 = world.V().a(d0, d1, d2);
        float f3 = MathHelper.b(-f2 * 0.017453292F - 3.1415927F);
        float f4 = MathHelper.a(-f2 * 0.017453292F - 3.1415927F);
        float f5 = -MathHelper.b(-f1 * 0.017453292F);
        float f6 = MathHelper.a(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 5.0D;
        Vec3 vec31 = vec3.c((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);

        return world.a(vec3, vec31, flag0, !flag0);
    }

    public int c() {
        return 0;
    }

    public Item a(CreativeTabs creativetabs) {
        this.a = creativetabs;
        return this;
    }

    public boolean z() {
        return true;
    }

    public boolean a(ItemStack itemstack, ItemStack itemstack1) {
        return false;
    }

    public Multimap h() {
        return HashMultimap.create();
    }

    protected Item d(String s0) {
        this.cA = s0;
        return this;
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
