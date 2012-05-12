package net.minecraft.server;

import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OPathEntity;
import net.minecraft.server.OProfiler;
import net.minecraft.server.OVec3D;
import net.minecraft.server.OWorld;

public abstract class OEntityCreature extends OEntityLiving {

   private OPathEntity a;
   protected OEntity d;
   protected boolean e = false;
   protected int f = 0;


   public OEntityCreature(OWorld var1) {
      super(var1);
   }

   protected boolean F() {
      return false;
   }

   protected void d_() {
      OProfiler.a("net/minecraft/server/OServerGuiCommandListener");
      if(this.f > 0) {
         --this.f;
      }

      this.e = this.F();
      float var1 = 16.0F;
      if(this.d == null) {
         this.d = this.o();
         if(this.d != null) {
            this.a = this.bi.a(this, this.d, var1, true, false, false, true);
         }
      } else if(!this.d.aE()) {
         this.d = null;
      } else {
         float var2 = this.d.i(this);
         if(this.h(this.d)) {
            this.a(this.d, var2);
         } else {
            this.b(this.d, var2);
         }
      }

      OProfiler.a();
      if(!this.e && this.d != null && (this.a == null || this.bS.nextInt(20) == 0)) {
         this.a = this.bi.a(this, this.d, var1, true, false, false, true);
      } else if(!this.e && (this.a == null && this.bS.nextInt(180) == 0 || this.bS.nextInt(120) == 0 || this.f > 0) && this.aV < 100) {
         this.G();
      }

      int var21 = OMathHelper.b(this.bw.b + 0.5D);
      boolean var3 = this.aU();
      boolean var4 = this.aV();
      this.bt = 0.0F;
      if(this.a != null && this.bS.nextInt(100) != 0) {
         OProfiler.a("followpath");
         OVec3D var5 = this.a.a((OEntity)this);
         double var6 = (double)(this.bG * 2.0F);

         while(var5 != null && var5.d(this.bm, var5.b, this.bo) < var6 * var6) {
            this.a.a();
            if(this.a.b()) {
               var5 = null;
               this.a = null;
            } else {
               var5 = this.a.a((OEntity)this);
            }
         }

         this.aZ = false;
         if(var5 != null) {
            double var8 = var5.a - this.bm;
            double var10 = var5.c - this.bo;
            double var12 = var5.b - (double)var21;
            float var14 = (float)(Math.atan2(var10, var8) * 180.0D / 3.1415927410125732D) - 90.0F;
            float var15 = var14 - this.bs;

            for(this.aX = this.bb; var15 < -180.0F; var15 += 360.0F) {
               ;
            }

            while(var15 >= 180.0F) {
               var15 -= 360.0F;
            }

            if(var15 > 30.0F) {
               var15 = 30.0F;
            }

            if(var15 < -30.0F) {
               var15 = -30.0F;
            }

            this.bs += var15;
            if(this.e && this.d != null) {
               double var16 = this.d.bm - this.bm;
               double var18 = this.d.bo - this.bo;
               float var20 = this.bs;
               this.bs = (float)(Math.atan2(var18, var16) * 180.0D / 3.1415927410125732D) - 90.0F;
               var15 = (var20 - this.bs + 90.0F) * 3.1415927F / 180.0F;
               this.aW = -OMathHelper.a(var15) * this.aX * 1.0F;
               this.aX = OMathHelper.b(var15) * this.aX * 1.0F;
            }

            if(var12 > 0.0D) {
               this.aZ = true;
            }
         }

         if(this.d != null) {
            this.a(this.d, 30.0F, 30.0F);
         }

         if(this.by && !this.H()) {
            this.aZ = true;
         }

         if(this.bS.nextFloat() < 0.8F && (var3 || var4)) {
            this.aZ = true;
         }

         OProfiler.a();
      } else {
         super.d_();
         this.a = null;
      }
   }

   protected void G() {
      OProfiler.a("stroll");
      boolean var1 = false;
      int var2 = -1;
      int var3 = -1;
      int var4 = -1;
      float var5 = -99999.0F;

      for(int var6 = 0; var6 < 10; ++var6) {
         int var7 = OMathHelper.b(this.bm + (double)this.bS.nextInt(13) - 6.0D);
         int var8 = OMathHelper.b(this.bn + (double)this.bS.nextInt(7) - 3.0D);
         int var9 = OMathHelper.b(this.bo + (double)this.bS.nextInt(13) - 6.0D);
         float var10 = this.a(var7, var8, var9);
         if(var10 > var5) {
            var5 = var10;
            var2 = var7;
            var3 = var8;
            var4 = var9;
            var1 = true;
         }
      }

      if(var1) {
         this.a = this.bi.a(this, var2, var3, var4, 10.0F, true, false, false, true);
      }

      OProfiler.a();
   }

   protected void a(OEntity var1, float var2) {}

   protected void b(OEntity var1, float var2) {}

   public float a(int var1, int var2, int var3) {
      return 0.0F;
   }

   protected OEntity o() {
      return null;
   }

   public boolean l() {
      int var1 = OMathHelper.b(this.bm);
      int var2 = OMathHelper.b(this.bw.b);
      int var3 = OMathHelper.b(this.bo);
      return super.l() && this.a(var1, var2, var3) >= 0.0F;
   }

   public boolean H() {
      return this.a != null;
   }

   public void a(OPathEntity var1) {
      this.a = var1;
   }

   public OEntity I() {
      return this.d;
   }

   public void d(OEntity var1) {
      this.d = var1;
   }

   protected float J() {
      if(this.c_()) {
         return 1.0F;
      } else {
         float var1 = super.J();
         if(this.f > 0) {
            var1 *= 2.0F;
         }

         return var1;
      }
   }
}
