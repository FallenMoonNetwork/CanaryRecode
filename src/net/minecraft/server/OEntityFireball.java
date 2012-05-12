package net.minecraft.server;

import java.util.List;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OMovingObjectPosition;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OVec3D;
import net.minecraft.server.OWorld;

public class OEntityFireball extends OEntity {

   private int e = -1;
   private int f = -1;
   private int g = -1;
   private int h = 0;
   private boolean i = false;
   public OEntityLiving a;
   private int j;
   private int k = 0;
   public double b;
   public double c;
   public double d;


   public OEntityFireball(OWorld var1) {
      super(var1);
      this.b(1.0F, 1.0F);
   }

   protected void b() {}

   public OEntityFireball(OWorld var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1);
      this.b(1.0F, 1.0F);
      this.c(var2, var4, var6, this.bs, this.bt);
      this.c(var2, var4, var6);
      double var14 = (double)OMathHelper.a(var8 * var8 + var10 * var10 + var12 * var12);
      this.b = var8 / var14 * 0.1D;
      this.c = var10 / var14 * 0.1D;
      this.d = var12 / var14 * 0.1D;
   }

   public OEntityFireball(OWorld var1, OEntityLiving var2, double var3, double var5, double var7) {
      super(var1);
      this.a = var2;
      this.b(1.0F, 1.0F);
      this.c(var2.bm, var2.bn, var2.bo, var2.bs, var2.bt);
      this.c(this.bm, this.bn, this.bo);
      this.bF = 0.0F;
      this.bp = this.bq = this.br = 0.0D;
      var3 += this.bS.nextGaussian() * 0.4D;
      var5 += this.bS.nextGaussian() * 0.4D;
      var7 += this.bS.nextGaussian() * 0.4D;
      double var9 = (double)OMathHelper.a(var3 * var3 + var5 * var5 + var7 * var7);
      this.b = var3 / var9 * 0.1D;
      this.c = var5 / var9 * 0.1D;
      this.d = var7 / var9 * 0.1D;
   }

   public void F_() {
      if(!this.bi.F && (this.a != null && this.a.bE || !this.bi.i((int)this.bm, (int)this.bn, (int)this.bo))) {
         this.X();
      } else {
         super.F_();
         this.i(1);
         if(this.i) {
            int var1 = this.bi.a(this.e, this.f, this.g);
            if(var1 == this.h) {
               ++this.j;
               if(this.j == 600) {
                  this.X();
               }

               return;
            }

            this.i = false;
            this.bp *= (double)(this.bS.nextFloat() * 0.2F);
            this.bq *= (double)(this.bS.nextFloat() * 0.2F);
            this.br *= (double)(this.bS.nextFloat() * 0.2F);
            this.j = 0;
            this.k = 0;
         } else {
            ++this.k;
         }

         OVec3D var15 = OVec3D.b(this.bm, this.bn, this.bo);
         OVec3D var2 = OVec3D.b(this.bm + this.bp, this.bn + this.bq, this.bo + this.br);
         OMovingObjectPosition var3 = this.bi.a(var15, var2);
         var15 = OVec3D.b(this.bm, this.bn, this.bo);
         var2 = OVec3D.b(this.bm + this.bp, this.bn + this.bq, this.bo + this.br);
         if(var3 != null) {
            var2 = OVec3D.b(var3.f.a, var3.f.b, var3.f.c);
         }

         OEntity var4 = null;
         List var5 = this.bi.b((OEntity)this, this.bw.a(this.bp, this.bq, this.br).b(1.0D, 1.0D, 1.0D));
         double var6 = 0.0D;

         for(int var8 = 0; var8 < var5.size(); ++var8) {
            OEntity var9 = (OEntity)var5.get(var8);
            if(var9.o_() && (!var9.a_((OEntity)this.a) || this.k >= 25)) {
               float var10 = 0.3F;
               OAxisAlignedBB var11 = var9.bw.b((double)var10, (double)var10, (double)var10);
               OMovingObjectPosition var12 = var11.a(var15, var2);
               if(var12 != null) {
                  double var13 = var15.b(var12.f);
                  if(var13 < var6 || var6 == 0.0D) {
                     var4 = var9;
                     var6 = var13;
                  }
               }
            }
         }

         if(var4 != null) {
            var3 = new OMovingObjectPosition(var4);
         }

         if(var3 != null) {
            this.a(var3);
         }

         this.bm += this.bp;
         this.bn += this.bq;
         this.bo += this.br;
         float var16 = OMathHelper.a(this.bp * this.bp + this.br * this.br);
         this.bs = (float)(Math.atan2(this.bp, this.br) * 180.0D / 3.1415927410125732D);

         for(this.bt = (float)(Math.atan2(this.bq, (double)var16) * 180.0D / 3.1415927410125732D); this.bt - this.bv < -180.0F; this.bv -= 360.0F) {
            ;
         }

         while(this.bt - this.bv >= 180.0F) {
            this.bv += 360.0F;
         }

         while(this.bs - this.bu < -180.0F) {
            this.bu -= 360.0F;
         }

         while(this.bs - this.bu >= 180.0F) {
            this.bu += 360.0F;
         }

         this.bt = this.bv + (this.bt - this.bv) * 0.2F;
         this.bs = this.bu + (this.bs - this.bu) * 0.2F;
         float var17 = 0.95F;
         if(this.aU()) {
            for(int var19 = 0; var19 < 4; ++var19) {
               float var18 = 0.25F;
               this.bi.a("bubble", this.bm - this.bp * (double)var18, this.bn - this.bq * (double)var18, this.bo - this.br * (double)var18, this.bp, this.bq, this.br);
            }

            var17 = 0.8F;
         }

         this.bp += this.b;
         this.bq += this.c;
         this.br += this.d;
         this.bp *= (double)var17;
         this.bq *= (double)var17;
         this.br *= (double)var17;
         this.bi.a("smoke", this.bm, this.bn + 0.5D, this.bo, 0.0D, 0.0D, 0.0D);
         this.c(this.bm, this.bn, this.bo);
      }
   }

   protected void a(OMovingObjectPosition var1) {
      if(!this.bi.F) {
         if(var1.g != null && var1.g.a(ODamageSource.a(this, this.a), 4)) {
            ;
         }

         this.bi.a((OEntity)null, this.bm, this.bn, this.bo, 1.0F, true);
         this.X();
      }

   }

   public void b(ONBTTagCompound var1) {
      var1.a("xTile", (short)this.e);
      var1.a("yTile", (short)this.f);
      var1.a("zTile", (short)this.g);
      var1.a("inTile", (byte)this.h);
      var1.a("inGround", (byte)(this.i?1:0));
   }

   public void a(ONBTTagCompound var1) {
      this.e = var1.e("xTile");
      this.f = var1.e("yTile");
      this.g = var1.e("zTile");
      this.h = var1.d("inTile") & 255;
      this.i = var1.d("inGround") == 1;
   }

   public boolean o_() {
      return true;
   }

   public float j_() {
      return 1.0F;
   }

   public boolean a(ODamageSource var1, int var2) {
      this.aW();
      if(var1.a() != null) {
         OVec3D var3 = var1.a().aJ();
         if(var3 != null) {
            this.bp = var3.a;
            this.bq = var3.b;
            this.br = var3.c;
            this.b = this.bp * 0.1D;
            this.c = this.bq * 0.1D;
            this.d = this.br * 0.1D;
         }

         if(var1.a() instanceof OEntityLiving) {
            this.a = (OEntityLiving)var1.a();
         }

         return true;
      } else {
         return false;
      }
   }

   public float b(float var1) {
      return 1.0F;
   }
}
