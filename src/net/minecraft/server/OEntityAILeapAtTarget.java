package net.minecraft.server;

import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OMathHelper;

public class OEntityAILeapAtTarget extends OEntityAIBase {

   OEntityLiving a;
   OEntityLiving b;
   float c;


   public OEntityAILeapAtTarget(OEntityLiving var1, float var2) {
      super();
      this.a = var1;
      this.c = var2;
      this.a(5);
   }

   public boolean a() {
      this.b = this.a.at();
      if(this.b == null) {
         return false;
      } else {
         double var1 = this.a.j(this.b);
         return var1 >= 4.0D && var1 <= 16.0D?(!this.a.bx?false:this.a.an().nextInt(5) == 0):false;
      }
   }

   public boolean b() {
      return !this.a.bx;
   }

   public void c() {
      double var1 = this.b.bm - this.a.bm;
      double var3 = this.b.bo - this.a.bo;
      float var5 = OMathHelper.a(var1 * var1 + var3 * var3);
      this.a.bp += var1 / (double)var5 * 0.5D * 0.800000011920929D + this.a.bp * 0.20000000298023224D;
      this.a.br += var3 / (double)var5 * 0.5D * 0.800000011920929D + this.a.br * 0.20000000298023224D;
      this.a.bq = (double)this.c;
   }
}
