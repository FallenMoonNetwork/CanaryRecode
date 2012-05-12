package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OTileEntity;

public class OTileEntityEnchantmentTable extends OTileEntity {

   public int a;
   public float b;
   public float c;
   public float d;
   public float e;
   public float f;
   public float g;
   public float h;
   public float i;
   public float j;
   private static Random r = new Random();


   public OTileEntityEnchantmentTable() {
      super();
   }

   public void q_() {
      super.q_();
      this.g = this.f;
      this.i = this.h;
      OEntityPlayer var1 = this.k.a((double)((float)this.l + 0.5F), (double)((float)this.m + 0.5F), (double)((float)this.n + 0.5F), 3.0D);
      if(var1 != null) {
         double var2 = var1.bm - (double)((float)this.l + 0.5F);
         double var4 = var1.bo - (double)((float)this.n + 0.5F);
         this.j = (float)Math.atan2(var4, var2);
         this.f += 0.1F;
         if(this.f < 0.5F || r.nextInt(40) == 0) {
            float var6 = this.d;

            do {
               this.d += (float)(r.nextInt(4) - r.nextInt(4));
            } while(var6 == this.d);
         }
      } else {
         this.j += 0.02F;
         this.f -= 0.1F;
      }

      while(this.h >= 3.1415927F) {
         this.h -= 6.2831855F;
      }

      while(this.h < -3.1415927F) {
         this.h += 6.2831855F;
      }

      while(this.j >= 3.1415927F) {
         this.j -= 6.2831855F;
      }

      while(this.j < -3.1415927F) {
         this.j += 6.2831855F;
      }

      float var7;
      for(var7 = this.j - this.h; var7 >= 3.1415927F; var7 -= 6.2831855F) {
         ;
      }

      while(var7 < -3.1415927F) {
         var7 += 6.2831855F;
      }

      this.h += var7 * 0.4F;
      if(this.f < 0.0F) {
         this.f = 0.0F;
      }

      if(this.f > 1.0F) {
         this.f = 1.0F;
      }

      ++this.a;
      this.c = this.b;
      float var8 = (this.d - this.b) * 0.4F;
      float var9 = 0.2F;
      if(var8 < -var9) {
         var8 = -var9;
      }

      if(var8 > var9) {
         var8 = var9;
      }

      this.e += (var8 - this.e) * 0.9F;
      this.b += this.e;
   }

}
