package net.minecraft.server;

import net.canarymod.api.world.CanaryBiome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class BiomeGenBase {

    public static final BiomeGenBase[] a = new BiomeGenBase[256];
    public static final BiomeGenBase b = (new BiomeGenOcean(0)).b(112).a("Ocean").b(-1.0F, 0.4F);
    public static final BiomeGenBase c = (new BiomeGenPlains(1)).b(9286496).a("Plains").a(0.8F, 0.4F);
    public static final BiomeGenBase d = (new BiomeGenDesert(2)).b(16421912).a("Desert").m().a(2.0F, 0.0F).b(0.1F, 0.2F);
    public static final BiomeGenBase e = (new BiomeGenHills(3)).b(6316128).a("Extreme Hills").b(0.3F, 1.5F).a(0.2F, 0.3F);
    public static final BiomeGenBase f = (new BiomeGenForest(4)).b(353825).a("Forest").a(5159473).a(0.7F, 0.8F);
    public static final BiomeGenBase g = (new BiomeGenTaiga(5)).b(747097).a("Taiga").a(5159473).b().a(0.05F, 0.8F).b(0.1F, 0.4F);
    public static final BiomeGenBase h = (new BiomeGenSwamp(6)).b(522674).a("Swampland").a(9154376).b(-0.2F, 0.1F).a(0.8F, 0.9F);
    public static final BiomeGenBase i = (new BiomeGenRiver(7)).b(255).a("River").b(-0.5F, 0.0F);
    public static final BiomeGenBase j = (new BiomeGenHell(8)).b(16711680).a("Hell").m().a(2.0F, 0.0F);
    public static final BiomeGenBase k = (new BiomeGenEnd(9)).b(8421631).a("Sky").m();
    public static final BiomeGenBase l = (new BiomeGenOcean(10)).b(9474208).a("FrozenOcean").b().b(-1.0F, 0.5F).a(0.0F, 0.5F);
    public static final BiomeGenBase m = (new BiomeGenRiver(11)).b(10526975).a("FrozenRiver").b().b(-0.5F, 0.0F).a(0.0F, 0.5F);
    public static final BiomeGenBase n = (new BiomeGenSnow(12)).b(16777215).a("Ice Plains").b().a(0.0F, 0.5F);
    public static final BiomeGenBase o = (new BiomeGenSnow(13)).b(10526880).a("Ice Mountains").b().b(0.3F, 1.3F).a(0.0F, 0.5F);
    public static final BiomeGenBase p = (new BiomeGenMushroomIsland(14)).b(16711935).a("MushroomIsland").a(0.9F, 1.0F).b(0.2F, 1.0F);
    public static final BiomeGenBase q = (new BiomeGenMushroomIsland(15)).b(10486015).a("MushroomIslandShore").a(0.9F, 1.0F).b(-1.0F, 0.1F);
    public static final BiomeGenBase r = (new BiomeGenBeach(16)).b(16440917).a("Beach").a(0.8F, 0.4F).b(0.0F, 0.1F);
    public static final BiomeGenBase s = (new BiomeGenDesert(17)).b(13786898).a("DesertHills").m().a(2.0F, 0.0F).b(0.3F, 0.8F);
    public static final BiomeGenBase t = (new BiomeGenForest(18)).b(2250012).a("ForestHills").a(5159473).a(0.7F, 0.8F).b(0.3F, 0.7F);
    public static final BiomeGenBase u = (new BiomeGenTaiga(19)).b(1456435).a("TaigaHills").b().a(5159473).a(0.05F, 0.8F).b(0.3F, 0.8F);
    public static final BiomeGenBase v = (new BiomeGenHills(20)).b(7501978).a("Extreme Hills Edge").b(0.2F, 0.8F).a(0.2F, 0.3F);
    public static final BiomeGenBase w = (new BiomeGenJungle(21)).b(5470985).a("Jungle").a(5470985).a(1.2F, 0.9F).b(0.2F, 0.4F);
    public static final BiomeGenBase x = (new BiomeGenJungle(22)).b(2900485).a("JungleHills").a(5470985).a(1.2F, 0.9F).b(1.8F, 0.5F);
    public String y;
    public int z;
    public byte A;
    public byte B;
    public int C;
    public float D;
    public float E;
    public float F;
    public float G;
    public int H;
    public BiomeDecorator I;
    protected List J;
    protected List K;
    protected List L;
    protected List M;
    private boolean S;
    private boolean T;
    public final int N;
    protected WorldGenTrees O;
    protected WorldGenBigTree P;
    protected WorldGenForest Q;
    protected WorldGenSwamp R;

    private CanaryBiome biome = new CanaryBiome(this); //

    protected BiomeGenBase(int i0) {
        this.A = (byte)Block.z.cF;
        this.B = (byte)Block.A.cF;
        this.C = 5169201;
        this.D = 0.1F;
        this.E = 0.3F;
        this.F = 0.5F;
        this.G = 0.5F;
        this.H = 16777215;
        this.J = new ArrayList();
        this.K = new ArrayList();
        this.L = new ArrayList();
        this.M = new ArrayList();
        this.T = true;
        this.O = new WorldGenTrees(false);
        this.P = new WorldGenBigTree(false);
        this.Q = new WorldGenForest(false);
        this.R = new WorldGenSwamp();
        this.N = i0;
        a[i0] = this;
        this.I = this.a();
        this.K.add(new SpawnListEntry(EntitySheep.class, 12, 4, 4));
        this.K.add(new SpawnListEntry(EntityPig.class, 10, 4, 4));
        this.K.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
        this.K.add(new SpawnListEntry(EntityCow.class, 8, 4, 4));
        this.J.add(new SpawnListEntry(EntitySpider.class, 10, 4, 4));
        this.J.add(new SpawnListEntry(EntityZombie.class, 10, 4, 4));
        this.J.add(new SpawnListEntry(EntitySkeleton.class, 10, 4, 4));
        this.J.add(new SpawnListEntry(EntityCreeper.class, 10, 4, 4));
        this.J.add(new SpawnListEntry(EntitySlime.class, 10, 4, 4));
        this.J.add(new SpawnListEntry(EntityEnderman.class, 1, 1, 4));
        this.L.add(new SpawnListEntry(EntitySquid.class, 10, 4, 4));
        this.M.add(new SpawnListEntry(EntityBat.class, 10, 8, 8));
    }

    protected BiomeDecorator a() {
        return new BiomeDecorator(this);
    }

    private BiomeGenBase a(float f0, float f1) {
        if (f0 > 0.1F && f0 < 0.2F) {
            throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
        }
        else {
            this.F = f0;
            this.G = f1;
            return this;
        }
    }

    private BiomeGenBase b(float f0, float f1) {
        this.D = f0;
        this.E = f1;
        return this;
    }

    private BiomeGenBase m() {
        this.T = false;
        return this;
    }

    public WorldGenerator a(Random random) {
        return (WorldGenerator)(random.nextInt(10) == 0 ? this.P : this.O);
    }

    public WorldGenerator b(Random random) {
        return new WorldGenTallGrass(Block.ac.cF, 1);
    }

    protected BiomeGenBase b() {
        this.S = true;
        return this;
    }

    protected BiomeGenBase a(String s0) {
        this.y = s0;
        return this;
    }

    public BiomeGenBase a(int i0) { // CanaryMod: protected to public
        this.C = i0;
        return this;
    }

    protected BiomeGenBase b(int i0) {
        this.z = i0;
        return this;
    }

    public List a(EnumCreatureType enumcreaturetype) {
        return enumcreaturetype == EnumCreatureType.a ? this.J : (enumcreaturetype == EnumCreatureType.b ? this.K : (enumcreaturetype == EnumCreatureType.d ? this.L : (enumcreaturetype == EnumCreatureType.c ? this.M : null)));
    }

    public boolean c() {
        return this.S;
    }

    public boolean d() {
        return this.S ? false : this.T;
    }

    public boolean e() {
        return this.G > 0.85F;
    }

    public float f() {
        return 0.1F;
    }

    public final int g() {
        return (int)(this.G * 65536.0F);
    }

    public final int h() {
        return (int)(this.F * 65536.0F);
    }

    public final float j() {
        return this.F;
    }

    public void a(World world, Random random, int i0, int i1) {
        this.I.a(world, random, i0, i1);
    }

    /*
     * Convenience Methods
     */
    public void setCanSnow(boolean bool) {
        this.S = bool;
    }

    public void setCanRain(boolean bool) {
        this.T = bool;
    }

    public boolean canRain() {
        return this.T;
    }

    public void setTemperatureAndPrecipitation(float temp, float precipitation) {
        this.a(temp, precipitation);
    }

    public CanaryBiome getCanaryBiome() {
        return this.biome;
    }
}
