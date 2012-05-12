package net.minecraft.server;

import java.util.List;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityWeatherEffect;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OWorld;

public class OEntityLightningBolt extends OEntityWeatherEffect {

   private int b;
   public long a = 0L;
   private int c;


   public OEntityLightningBolt(OWorld var1, double var2, double var4, double var6) {
      super(var1);
      this.c(var2, var4, var6, 0.0F, 0.0F);
      this.b = 2;
      this.a = this.bS.nextLong();
      this.c = this.bS.nextInt(3) + 1;
      if(var1.q >= 2 && var1.a(OMathHelper.b(var2), OMathHelper.b(var4), OMathHelper.b(var6), 10)) {
         int var8 = OMathHelper.b(var2);
         int var9 = OMathHelper.b(var4);
         int var10 = OMathHelper.b(var6);
         if(var1.a(var8, var9, var10) == 0 && OBlock.ar.c(var1, var8, var9, var10)) {
            var1.e(var8, var9, var10, OBlock.ar.bO);
         }

         for(var8 = 0; var8 < 4; ++var8) {
            var9 = OMathHelper.b(var2) + this.bS.nextInt(3) - 1;
            var10 = OMathHelper.b(var4) + this.bS.nextInt(3) - 1;
            int var11 = OMathHelper.b(var6) + this.bS.nextInt(3) - 1;
            if(var1.a(var9, var10, var11) == 0 && OBlock.ar.c(var1, var9, var10, var11)) {
               var1.e(var9, var10, var11, OBlock.ar.bO);
            }
         }
      }

   }

   public void F_() {
      super.F_();
      if(this.b == 2) {
         this.bi.a(this.bm, this.bn, this.bo, "ambient.weather.thunder", 10000.0F, 0.8F + this.bS.nextFloat() * 0.2F);
         this.bi.a(this.bm, this.bn, this.bo, "random.explode", 2.0F, 0.5F + this.bS.nextFloat() * 0.2F);
      }

      --this.b;
      if(this.b < 0) {
         if(this.c == 0) {
            this.X();
         } else if(this.b < -this.bS.nextInt(10)) {
            --this.c;
            this.b = 1;
            this.a = this.bS.nextLong();
            if(this.bi.a(OMathHelper.b(this.bm), OMathHelper.b(this.bn), OMathHelper.b(this.bo), 10)) {
               int var1 = OMathHelper.b(this.bm);
               int var2 = OMathHelper.b(this.bn);
               int var3 = OMathHelper.b(this.bo);
               if(this.bi.a(var1, var2, var3) == 0 && OBlock.ar.c(this.bi, var1, var2, var3)) {
                  this.bi.e(var1, var2, var3, OBlock.ar.bO);
               }
            }
         }
      }

      if(this.b >= 0) {
         double var4 = 3.0D;
         List var8 = this.bi.b((OEntity)this, OAxisAlignedBB.b(this.bm - var4, this.bn - var4, this.bo - var4, this.bm + var4, this.bn + 6.0D + var4, this.bo + var4));

         for(int var6 = 0; var6 < var8.size(); ++var6) {
            OEntity var7 = (OEntity)var8.get(var6);
            var7.a(this);
         }

         this.bi.n = 2;
      }

   }

   protected void b() {}

   protected void a(ONBTTagCompound var1) {}

   protected void b(ONBTTagCompound var1) {}
}
