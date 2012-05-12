package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.server.OBiomeDecorator;
import net.minecraft.server.OBiomeGenBeach;
import net.minecraft.server.OBiomeGenDesert;
import net.minecraft.server.OBiomeGenEnd;
import net.minecraft.server.OBiomeGenForest;
import net.minecraft.server.OBiomeGenHell;
import net.minecraft.server.OBiomeGenHills;
import net.minecraft.server.OBiomeGenJungle;
import net.minecraft.server.OBiomeGenMushroomIsland;
import net.minecraft.server.OBiomeGenOcean;
import net.minecraft.server.OBiomeGenPlains;
import net.minecraft.server.OBiomeGenRiver;
import net.minecraft.server.OBiomeGenSnow;
import net.minecraft.server.OBiomeGenSwamp;
import net.minecraft.server.OBiomeGenTaiga;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityChicken;
import net.minecraft.server.OEntityCow;
import net.minecraft.server.OEntityCreeper;
import net.minecraft.server.OEntityEnderman;
import net.minecraft.server.OEntityPig;
import net.minecraft.server.OEntitySheep;
import net.minecraft.server.OEntitySkeleton;
import net.minecraft.server.OEntitySlime;
import net.minecraft.server.OEntitySpider;
import net.minecraft.server.OEntitySquid;
import net.minecraft.server.OEntityZombie;
import net.minecraft.server.OEnumCreatureType;
import net.minecraft.server.OSpawnListEntry;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldGenBigTree;
import net.minecraft.server.OWorldGenForest;
import net.minecraft.server.OWorldGenSwamp;
import net.minecraft.server.OWorldGenTallGrass;
import net.minecraft.server.OWorldGenTrees;
import net.minecraft.server.OWorldGenerator;

public abstract class OBiomeGenBase {

   public static final OBiomeGenBase[] a = new OBiomeGenBase[256];
   public static final OBiomeGenBase b = (new OBiomeGenOcean(0)).b(112).a("Ocean").b(-1.0F, 0.4F);
   public static final OBiomeGenBase c = (new OBiomeGenPlains(1)).b(9286496).a("Plains").a(0.8F, 0.4F);
   public static final OBiomeGenBase d = (new OBiomeGenDesert(2)).b(16421912).a("Desert").j().a(2.0F, 0.0F).b(0.1F, 0.2F);
   public static final OBiomeGenBase e = (new OBiomeGenHills(3)).b(6316128).a("Extreme Hills").b(0.2F, 1.3F).a(0.2F, 0.3F);
   public static final OBiomeGenBase f = (new OBiomeGenForest(4)).b(353825).a("Forest").a(5159473).a(0.7F, 0.8F);
   public static final OBiomeGenBase g = (new OBiomeGenTaiga(5)).b(747097).a("Taiga").a(5159473).b().a(0.05F, 0.8F).b(0.1F, 0.4F);
   public static final OBiomeGenBase h = (new OBiomeGenSwamp(6)).b(522674).a("Swampland").a(9154376).b(-0.2F, 0.1F).a(0.8F, 0.9F);
   public static final OBiomeGenBase i = (new OBiomeGenRiver(7)).b(255).a("River").b(-0.5F, 0.0F);
   public static final OBiomeGenBase j = (new OBiomeGenHell(8)).b(16711680).a("Hell").j().a(2.0F, 0.0F);
   public static final OBiomeGenBase k = (new OBiomeGenEnd(9)).b(8421631).a("Sky").j();
   public static final OBiomeGenBase l = (new OBiomeGenOcean(10)).b(9474208).a("FrozenOcean").b().b(-1.0F, 0.5F).a(0.0F, 0.5F);
   public static final OBiomeGenBase m = (new OBiomeGenRiver(11)).b(10526975).a("FrozenRiver").b().b(-0.5F, 0.0F).a(0.0F, 0.5F);
   public static final OBiomeGenBase n = (new OBiomeGenSnow(12)).b(16777215).a("Ice Plains").b().a(0.0F, 0.5F);
   public static final OBiomeGenBase o = (new OBiomeGenSnow(13)).b(10526880).a("Ice Mountains").b().b(0.2F, 1.2F).a(0.0F, 0.5F);
   public static final OBiomeGenBase p = (new OBiomeGenMushroomIsland(14)).b(16711935).a("MushroomIsland").a(0.9F, 1.0F).b(0.2F, 1.0F);
   public static final OBiomeGenBase q = (new OBiomeGenMushroomIsland(15)).b(10486015).a("MushroomIslandShore").a(0.9F, 1.0F).b(-1.0F, 0.1F);
   public static final OBiomeGenBase r = (new OBiomeGenBeach(16)).b(16440917).a("Beach").a(0.8F, 0.4F).b(0.0F, 0.1F);
   public static final OBiomeGenBase s = (new OBiomeGenDesert(17)).b(13786898).a("DesertHills").j().a(2.0F, 0.0F).b(0.2F, 0.7F);
   public static final OBiomeGenBase t = (new OBiomeGenForest(18)).b(2250012).a("ForestHills").a(5159473).a(0.7F, 0.8F).b(0.2F, 0.6F);
   public static final OBiomeGenBase u = (new OBiomeGenTaiga(19)).b(1456435).a("TaigaHills").b().a(5159473).a(0.05F, 0.8F).b(0.2F, 0.7F);
   public static final OBiomeGenBase v = (new OBiomeGenHills(20)).b(7501978).a("Extreme Hills Edge").b(0.2F, 0.8F).a(0.2F, 0.3F);
   public static final OBiomeGenBase w = (new OBiomeGenJungle(21)).b(5470985).a("Jungle").a(5470985).a(1.2F, 0.9F).b(0.2F, 0.4F);
   public static final OBiomeGenBase x = (new OBiomeGenJungle(22)).b(2900485).a("JungleHills").a(5470985).a(1.2F, 0.9F).b(1.8F, 0.2F);
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
   public OBiomeDecorator I;
   protected List J;
   protected List K;
   protected List L;
   private boolean R;
   private boolean S;
   public final int M;
   protected OWorldGenTrees N;
   protected OWorldGenBigTree O;
   protected OWorldGenForest P;
   protected OWorldGenSwamp Q;


   protected OBiomeGenBase(int var1) {
      super();
      this.A = (byte)OBlock.u.bO;
      this.B = (byte)OBlock.v.bO;
      this.C = 5169201;
      this.D = 0.1F;
      this.E = 0.3F;
      this.F = 0.5F;
      this.G = 0.5F;
      this.H = 16777215;
      this.J = new ArrayList();
      this.K = new ArrayList();
      this.L = new ArrayList();
      this.S = true;
      this.N = new OWorldGenTrees(false);
      this.O = new OWorldGenBigTree(false);
      this.P = new OWorldGenForest(false);
      this.Q = new OWorldGenSwamp();
      this.M = var1;
      a[var1] = this;
      this.I = this.a();
      this.K.add(new OSpawnListEntry(OEntitySheep.class, 12, 4, 4));
      this.K.add(new OSpawnListEntry(OEntityPig.class, 10, 4, 4));
      this.K.add(new OSpawnListEntry(OEntityChicken.class, 10, 4, 4));
      this.K.add(new OSpawnListEntry(OEntityCow.class, 8, 4, 4));
      this.J.add(new OSpawnListEntry(OEntitySpider.class, 10, 4, 4));
      this.J.add(new OSpawnListEntry(OEntityZombie.class, 10, 4, 4));
      this.J.add(new OSpawnListEntry(OEntitySkeleton.class, 10, 4, 4));
      this.J.add(new OSpawnListEntry(OEntityCreeper.class, 10, 4, 4));
      this.J.add(new OSpawnListEntry(OEntitySlime.class, 10, 4, 4));
      this.J.add(new OSpawnListEntry(OEntityEnderman.class, 1, 1, 4));
      this.L.add(new OSpawnListEntry(OEntitySquid.class, 10, 4, 4));
   }

   protected OBiomeDecorator a() {
      return new OBiomeDecorator(this);
   }

   private OBiomeGenBase a(float var1, float var2) {
      if(var1 > 0.1F && var1 < 0.2F) {
         throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
      } else {
         this.F = var1;
         this.G = var2;
         return this;
      }
   }

   private OBiomeGenBase b(float var1, float var2) {
      this.D = var1;
      this.E = var2;
      return this;
   }

   private OBiomeGenBase j() {
      this.S = false;
      return this;
   }

   public OWorldGenerator a(Random var1) {
      return (OWorldGenerator)(var1.nextInt(10) == 0?this.O:this.N);
   }

   public OWorldGenerator b(Random var1) {
      return new OWorldGenTallGrass(OBlock.X.bO, 1);
   }

   protected OBiomeGenBase b() {
      this.R = true;
      return this;
   }

   protected OBiomeGenBase a(String var1) {
      this.y = var1;
      return this;
   }

   protected OBiomeGenBase a(int var1) {
      this.C = var1;
      return this;
   }

   protected OBiomeGenBase b(int var1) {
      this.z = var1;
      return this;
   }

   public List a(OEnumCreatureType var1) {
      return var1 == OEnumCreatureType.a?this.J:(var1 == OEnumCreatureType.b?this.K:(var1 == OEnumCreatureType.c?this.L:null));
   }

   public boolean c() {
      return this.R;
   }

   public boolean d() {
      return this.R?false:this.S;
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

   public final float i() {
      return this.F;
   }

   public void a(OWorld var1, Random var2, int var3, int var4) {
      this.I.a(var1, var2, var3, var4);
   }

}
