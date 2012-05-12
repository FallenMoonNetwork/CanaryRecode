package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntity;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;

public class OBlockFarmland extends OBlock {

   protected OBlockFarmland(int var1) {
      super(var1, OMaterial.c);
      this.bN = 87;
      this.a(true);
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
      this.f(255);
   }

   public OAxisAlignedBB e(OWorld var1, int var2, int var3, int var4) {
      return OAxisAlignedBB.b((double)(var2 + 0), (double)(var3 + 0), (double)(var4 + 0), (double)(var2 + 1), (double)(var3 + 1), (double)(var4 + 1));
   }

   public boolean a() {
      return false;
   }

   public boolean b() {
      return false;
   }

   public int a(int var1, int var2) {
      return var1 == 1 && var2 > 0?this.bN - 1:(var1 == 1?this.bN:2);
   }

   public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
      if(!this.h(var1, var2, var3, var4) && !var1.y(var2, var3 + 1, var4)) {
         int var6 = var1.c(var2, var3, var4);
         if(var6 > 0) {
            var1.c(var2, var3, var4, var6 - 1);
         } else if(!this.g(var1, var2, var3, var4)) {
            var1.e(var2, var3, var4, OBlock.v.bO);
         }
      } else {
         var1.c(var2, var3, var4, 7);
      }

   }

   public void a(OWorld var1, int var2, int var3, int var4, OEntity var5, float var6) {
      if(var1.r.nextFloat() < var6 - 0.5F) {
         var1.e(var2, var3, var4, OBlock.v.bO);
      }

   }

   private boolean g(OWorld var1, int var2, int var3, int var4) {
      byte var5 = 0;

      for(int var6 = var2 - var5; var6 <= var2 + var5; ++var6) {
         for(int var7 = var4 - var5; var7 <= var4 + var5; ++var7) {
            int var8 = var1.a(var6, var3 + 1, var7);
            if(var8 == OBlock.az.bO || var8 == OBlock.bt.bO || var8 == OBlock.bs.bO) {
               return true;
            }
         }
      }

      return false;
   }

   private boolean h(OWorld var1, int var2, int var3, int var4) {
      for(int var5 = var2 - 4; var5 <= var2 + 4; ++var5) {
         for(int var6 = var3; var6 <= var3 + 1; ++var6) {
            for(int var7 = var4 - 4; var7 <= var4 + 4; ++var7) {
               if(var1.d(var5, var6, var7) == OMaterial.g) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public void a(OWorld var1, int var2, int var3, int var4, int var5) {
      super.a(var1, var2, var3, var4, var5);
      OMaterial var6 = var1.d(var2, var3 + 1, var4);
      if(var6.a()) {
         var1.e(var2, var3, var4, OBlock.v.bO);
      }

   }

   public int a(int var1, Random var2, int var3) {
      return OBlock.v.a(0, var2, var3);
   }
}
