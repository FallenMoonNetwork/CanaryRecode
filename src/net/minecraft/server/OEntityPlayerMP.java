package net.minecraft.server;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import net.minecraft.server.OAchievementList;
import net.minecraft.server.OChunk;
import net.minecraft.server.OChunkCoordIntPair;
import net.minecraft.server.OChunkCoordinates;
import net.minecraft.server.OContainer;
import net.minecraft.server.OContainerBrewingStand;
import net.minecraft.server.OContainerChest;
import net.minecraft.server.OContainerDispenser;
import net.minecraft.server.OContainerEnchantment;
import net.minecraft.server.OContainerFurnace;
import net.minecraft.server.OContainerWorkbench;
import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityArrow;
import net.minecraft.server.OEntityDamageSource;
import net.minecraft.server.OEntityItem;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntityTracker;
import net.minecraft.server.OEntityXPOrb;
import net.minecraft.server.OEnumAction;
import net.minecraft.server.OEnumStatus;
import net.minecraft.server.OICrafting;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemInWorldManager;
import net.minecraft.server.OItemMapBase;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMinecraftServer;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.ONetServerHandler;
import net.minecraft.server.OPacket;
import net.minecraft.server.OPacket100OpenWindow;
import net.minecraft.server.OPacket101CloseWindow;
import net.minecraft.server.OPacket103SetSlot;
import net.minecraft.server.OPacket104WindowItems;
import net.minecraft.server.OPacket105UpdateProgressbar;
import net.minecraft.server.OPacket17Sleep;
import net.minecraft.server.OPacket18Animation;
import net.minecraft.server.OPacket200Statistic;
import net.minecraft.server.OPacket202PlayerAbilities;
import net.minecraft.server.OPacket22Collect;
import net.minecraft.server.OPacket38EntityStatus;
import net.minecraft.server.OPacket39AttachEntity;
import net.minecraft.server.OPacket3Chat;
import net.minecraft.server.OPacket41EntityEffect;
import net.minecraft.server.OPacket42RemoveEntityEffect;
import net.minecraft.server.OPacket43Experience;
import net.minecraft.server.OPacket51MapChunk;
import net.minecraft.server.OPacket5PlayerInventory;
import net.minecraft.server.OPacket70Bed;
import net.minecraft.server.OPacket8UpdateHealth;
import net.minecraft.server.OPotionEffect;
import net.minecraft.server.OSlotCrafting;
import net.minecraft.server.OStatBase;
import net.minecraft.server.OStringTranslate;
import net.minecraft.server.OTileEntity;
import net.minecraft.server.OTileEntityBrewingStand;
import net.minecraft.server.OTileEntityDispenser;
import net.minecraft.server.OTileEntityFurnace;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldServer;

public class OEntityPlayerMP extends OEntityPlayer implements OICrafting {

   public ONetServerHandler a;
   public OMinecraftServer b;
   public OItemInWorldManager c;
   public double d;
   public double e;
   public List f = new LinkedList();
   public Set g = new HashSet();
   private int cf = -99999999;
   private int cg = -99999999;
   private boolean ch = true;
   private int ci = -99999999;
   private int cj = 60;
   private OItemStack[] ck = new OItemStack[]{null, null, null, null, null};
   private int cl = 0;
   public boolean h;
   public int i;
   public boolean j = false;


   public OEntityPlayerMP(OMinecraftServer var1, OWorld var2, String var3, OItemInWorldManager var4) {
      super(var2);
      var4.b = this;
      this.c = var4;
      OChunkCoordinates var5 = var2.p();
      int var6 = var5.a;
      int var7 = var5.c;
      int var8 = var5.b;
      if(!var2.t.e) {
         var6 += this.bS.nextInt(20) - 10;
         var8 = var2.g(var6, var7);
         var7 += this.bS.nextInt(20) - 10;
      }

      this.c((double)var6 + 0.5D, (double)var8, (double)var7 + 0.5D, 0.0F, 0.0F);
      this.b = var1;
      this.bP = 0.0F;
      this.v = var3;
      this.bF = 0.0F;
   }

   public void a(ONBTTagCompound var1) {
      super.a(var1);
      if(var1.c("playerGameType")) {
         this.c.a(var1.f("playerGameType"));
      }

   }

   public void b(ONBTTagCompound var1) {
      super.b(var1);
      var1.a("playerGameType", this.c.a());
   }

   public void a(OWorld var1) {
      super.a(var1);
   }

   public void e_(int var1) {
      super.e_(var1);
      this.ci = -1;
   }

   public void x() {
      this.m.a((OICrafting)this);
   }

   public OItemStack[] y() {
      return this.ck;
   }

   protected void A() {
      this.bF = 0.0F;
   }

   public float B() {
      return 1.62F;
   }

   public void F_() {
      this.c.c();
      --this.cj;
      this.m.a();

      for(int var1 = 0; var1 < 5; ++var1) {
         OItemStack var2 = this.c(var1);
         if(var2 != this.ck[var1]) {
            this.b.b(this.w).a(this, new OPacket5PlayerInventory(this.bd, var1, var2));
            this.ck[var1] = var2;
         }
      }

   }

   public OItemStack c(int var1) {
      return var1 == 0?this.k.d():this.k.b[var1 - 1];
   }

   public void a(ODamageSource var1) {
      this.b.h.a((OPacket)(new OPacket3Chat(var1.a((OEntityPlayer)this))));
      this.k.k();
   }

   public boolean a(ODamageSource var1, int var2) {
      if(this.cj > 0) {
         return false;
      } else {
         if(!this.b.q && var1 instanceof OEntityDamageSource) {
            OEntity var3 = var1.a();
            if(var3 instanceof OEntityPlayer) {
               return false;
            }

            if(var3 instanceof OEntityArrow) {
               OEntityArrow var4 = (OEntityArrow)var3;
               if(var4.c instanceof OEntityPlayer) {
                  return false;
               }
            }
         }

         return super.a(var1, var2);
      }
   }

   protected boolean C() {
      return this.b.q;
   }

   public void d(int var1) {
      super.d(var1);
   }

   public void a(boolean var1) {
      super.F_();

      for(int var2 = 0; var2 < this.k.c(); ++var2) {
         OItemStack var3 = this.k.g_(var2);
         if(var3 != null && OItem.d[var3.c].t_() && this.a.b() <= 2) {
            OPacket var4 = ((OItemMapBase)OItem.d[var3.c]).c(var3, this.bi, this);
            if(var4 != null) {
               this.a.b(var4);
            }
         }
      }

      if(var1 && !this.f.isEmpty()) {
         OChunkCoordIntPair var14 = (OChunkCoordIntPair)this.f.get(0);
         double var5 = var14.a(this);

         for(int var7 = 0; var7 < this.f.size(); ++var7) {
            OChunkCoordIntPair var8 = (OChunkCoordIntPair)this.f.get(var7);
            double var9 = var8.a(this);
            if(var9 < var5) {
               var14 = var8;
               var5 = var9;
            }
         }

         if(var14 != null) {
            boolean var17 = false;
            if(this.a.b() < 4) {
               var17 = true;
            }

            if(var17) {
               OWorldServer var18 = this.b.a(this.w);
               if(var18.i(var14.a << 4, 0, var14.b << 4)) {
                  OChunk var11 = var18.d(var14.a, var14.b);
                  if(var11.k) {
                     this.f.remove(var14);
                     this.a.b((OPacket)(new OPacket51MapChunk(var18.d(var14.a, var14.b), true, 0)));
                     List var12 = var18.c(var14.a * 16, 0, var14.b * 16, var14.a * 16 + 16, 256, var14.b * 16 + 16);

                     for(int var13 = 0; var13 < var12.size(); ++var13) {
                        this.a((OTileEntity)var12.get(var13));
                     }
                  }
               }
            }
         }
      }

      if(this.J) {
         if(this.b.d.a("allow-nether", true)) {
            if(this.m != this.l) {
               this.F();
            }

            if(this.bh != null) {
               this.b(this.bh);
            } else {
               this.K += 0.0125F;
               if(this.K >= 1.0F) {
                  this.K = 1.0F;
                  this.I = 10;
                  boolean var15 = false;
                  byte var16;
                  if(this.w == -1) {
                     var16 = 0;
                  } else {
                     var16 = -1;
                  }

                  this.b.h.a(this, var16);
                  this.ci = -1;
                  this.cf = -1;
                  this.cg = -1;
                  this.a((OStatBase)OAchievementList.x);
               }
            }

            this.J = false;
         }
      } else {
         if(this.K > 0.0F) {
            this.K -= 0.05F;
         }

         if(this.K < 0.0F) {
            this.K = 0.0F;
         }
      }

      if(this.I > 0) {
         --this.I;
      }

      if(this.aD() != this.cf || this.cg != this.n.a() || this.n.c() == 0.0F != this.ch) {
         this.a.b((OPacket)(new OPacket8UpdateHealth(this.aD(), this.n.a(), this.n.c())));
         this.cf = this.aD();
         this.cg = this.n.a();
         this.ch = this.n.c() == 0.0F;
      }

      if(this.N != this.ci) {
         this.ci = this.N;
         this.a.b((OPacket)(new OPacket43Experience(this.O, this.N, this.M)));
      }

   }

   public void e(int var1) {
      if(this.w == 1 && var1 == 1) {
         this.a((OStatBase)OAchievementList.C);
         this.bi.e(this);
         this.j = true;
         this.a.b((OPacket)(new OPacket70Bed(4, 0)));
      } else {
         this.a((OStatBase)OAchievementList.B);
         OChunkCoordinates var2 = this.b.a(var1).d();
         if(var2 != null) {
            this.a.a((double)var2.a, (double)var2.b, (double)var2.c, 0.0F, 0.0F);
         }

         this.b.h.a(this, 1);
         this.ci = -1;
         this.cf = -1;
         this.cg = -1;
      }

   }

   private void a(OTileEntity var1) {
      if(var1 != null) {
         OPacket var2 = var1.d();
         if(var2 != null) {
            this.a.b(var2);
         }
      }

   }

   public void a(OEntity var1, int var2) {
      if(!var1.bE) {
         OEntityTracker var3 = this.b.b(this.w);
         if(var1 instanceof OEntityItem) {
            var3.a(var1, new OPacket22Collect(var1.bd, this.bd));
         }

         if(var1 instanceof OEntityArrow) {
            var3.a(var1, new OPacket22Collect(var1.bd, this.bd));
         }

         if(var1 instanceof OEntityXPOrb) {
            var3.a(var1, new OPacket22Collect(var1.bd, this.bd));
         }
      }

      super.a(var1, var2);
      this.m.a();
   }

   public void C_() {
      if(!this.t) {
         this.u = -1;
         this.t = true;
         OEntityTracker var1 = this.b.b(this.w);
         var1.a(this, new OPacket18Animation(this, 1));
      }

   }

   public void E() {}

   public OEnumStatus a(int var1, int var2, int var3) {
      OEnumStatus var4 = super.a(var1, var2, var3);
      if(var4 == OEnumStatus.a) {
         OEntityTracker var5 = this.b.b(this.w);
         OPacket17Sleep var6 = new OPacket17Sleep(this, 0, var1, var2, var3);
         var5.a(this, var6);
         this.a.a(this.bm, this.bn, this.bo, this.bs, this.bt);
         this.a.b((OPacket)var6);
      }

      return var4;
   }

   public void a(boolean var1, boolean var2, boolean var3) {
      if(this.Z()) {
         OEntityTracker var4 = this.b.b(this.w);
         var4.b(this, new OPacket18Animation(this, 3));
      }

      super.a(var1, var2, var3);
      if(this.a != null) {
         this.a.a(this.bm, this.bn, this.bo, this.bs, this.bt);
      }

   }

   public void b(OEntity var1) {
      super.b(var1);
      this.a.b((OPacket)(new OPacket39AttachEntity(this, this.bh)));
      this.a.a(this.bm, this.bn, this.bo, this.bs, this.bt);
   }

   protected void a(double var1, boolean var3) {}

   public void b(double var1, boolean var3) {
      super.a(var1, var3);
   }

   private void bc() {
      this.cl = this.cl % 100 + 1;
   }

   public void b(int var1, int var2, int var3) {
      this.bc();
      this.a.b((OPacket)(new OPacket100OpenWindow(this.cl, 1, "Crafting", 9)));
      this.m = new OContainerWorkbench(this.k, this.bi, var1, var2, var3);
      this.m.f = this.cl;
      this.m.a((OICrafting)this);
   }

   public void c(int var1, int var2, int var3) {
      this.bc();
      this.a.b((OPacket)(new OPacket100OpenWindow(this.cl, 4, "Enchanting", 9)));
      this.m = new OContainerEnchantment(this.k, this.bi, var1, var2, var3);
      this.m.f = this.cl;
      this.m.a((OICrafting)this);
   }

   public void a(OIInventory var1) {
      this.bc();
      this.a.b((OPacket)(new OPacket100OpenWindow(this.cl, 0, var1.e(), var1.c())));
      this.m = new OContainerChest(this.k, var1);
      this.m.f = this.cl;
      this.m.a((OICrafting)this);
   }

   public void a(OTileEntityFurnace var1) {
      this.bc();
      this.a.b((OPacket)(new OPacket100OpenWindow(this.cl, 2, var1.e(), var1.c())));
      this.m = new OContainerFurnace(this.k, var1);
      this.m.f = this.cl;
      this.m.a((OICrafting)this);
   }

   public void a(OTileEntityDispenser var1) {
      this.bc();
      this.a.b((OPacket)(new OPacket100OpenWindow(this.cl, 3, var1.e(), var1.c())));
      this.m = new OContainerDispenser(this.k, var1);
      this.m.f = this.cl;
      this.m.a((OICrafting)this);
   }

   public void a(OTileEntityBrewingStand var1) {
      this.bc();
      this.a.b((OPacket)(new OPacket100OpenWindow(this.cl, 5, var1.e(), var1.c())));
      this.m = new OContainerBrewingStand(this.k, var1);
      this.m.f = this.cl;
      this.m.a((OICrafting)this);
   }

   public void a(OContainer var1, int var2, OItemStack var3) {
      if(!(var1.b(var2) instanceof OSlotCrafting)) {
         if(!this.h) {
            this.a.b((OPacket)(new OPacket103SetSlot(var1.f, var2, var3)));
         }
      }
   }

   public void a(OContainer var1) {
      this.a(var1, var1.b());
   }

   public void a(OContainer var1, List var2) {
      this.a.b((OPacket)(new OPacket104WindowItems(var1.f, var2)));
      this.a.b((OPacket)(new OPacket103SetSlot(-1, -1, this.k.l())));
   }

   public void a(OContainer var1, int var2, int var3) {
      this.a.b((OPacket)(new OPacket105UpdateProgressbar(var1.f, var2, var3)));
   }

   public void a(OItemStack var1) {}

   public void F() {
      this.a.b((OPacket)(new OPacket101CloseWindow(this.m.f)));
      this.H();
   }

   public void G() {
      if(!this.h) {
         this.a.b((OPacket)(new OPacket103SetSlot(-1, -1, this.k.l())));
      }
   }

   public void H() {
      this.m.a((OEntityPlayer)this);
      this.m = this.l;
   }

   public void a(OStatBase var1, int var2) {
      if(var1 != null) {
         if(!var1.f) {
            while(var2 > 100) {
               this.a.b((OPacket)(new OPacket200Statistic(var1.e, 100)));
               var2 -= 100;
            }

            this.a.b((OPacket)(new OPacket200Statistic(var1.e, var2)));
         }

      }
   }

   public void I() {
      if(this.bh != null) {
         this.b(this.bh);
      }

      if(this.bg != null) {
         this.bg.b((OEntity)this);
      }

      if(this.E) {
         this.a(true, false, false);
      }

   }

   public void D_() {
      this.cf = -99999999;
   }

   public void a(String var1) {
      OStringTranslate var2 = OStringTranslate.a();
      String var3 = var2.b(var1);
      this.a.b((OPacket)(new OPacket3Chat(var3)));
   }

   protected void K() {
      this.a.b((OPacket)(new OPacket38EntityStatus(this.bd, (byte)9)));
      super.K();
   }

   public void a(OItemStack var1, int var2) {
      super.a(var1, var2);
      if(var1 != null && var1.a() != null && var1.a().d(var1) == OEnumAction.b) {
         OEntityTracker var3 = this.b.b(this.w);
         var3.b(this, new OPacket18Animation(this, 5));
      }

   }

   protected void b(OPotionEffect var1) {
      super.b(var1);
      this.a.b((OPacket)(new OPacket41EntityEffect(this.bd, var1)));
   }

   protected void c(OPotionEffect var1) {
      super.c(var1);
      this.a.b((OPacket)(new OPacket41EntityEffect(this.bd, var1)));
   }

   protected void d(OPotionEffect var1) {
      super.d(var1);
      this.a.b((OPacket)(new OPacket42RemoveEntityEffect(this.bd, var1)));
   }

   public void a_(double var1, double var3, double var5) {
      this.a.a(var1, var3, var5, this.bs, this.bt);
   }

   public void c(OEntity var1) {
      OEntityTracker var2 = this.b.b(this.w);
      var2.b(this, new OPacket18Animation(var1, 6));
   }

   public void d(OEntity var1) {
      OEntityTracker var2 = this.b.b(this.w);
      var2.b(this, new OPacket18Animation(var1, 7));
   }

   public void L() {
      if(this.a != null) {
         this.a.b((OPacket)(new OPacket202PlayerAbilities(this.L)));
      }
   }
}
