package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.ODirection;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OStatList;
import net.minecraft.server.OWorld;

public class OBlockVine extends OBlock {

   public OBlockVine(int var1) {
      super(var1, 143, OMaterial.k);
      this.a(true);
   }

   public void f() {
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   public int c() {
      return 20;
   }

   public boolean a() {
      return false;
   }

   public boolean b() {
      return false;
   }

   public void a(OIBlockAccess var1, int var2, int var3, int var4) {
      int var5 = var1.c(var2, var3, var4);
      float var6 = 1.0F;
      float var7 = 1.0F;
      float var8 = 1.0F;
      float var9 = 0.0F;
      float var10 = 0.0F;
      float var11 = 0.0F;
      boolean var12 = var5 > 0;
      if((var5 & 2) != 0) {
         var9 = Math.max(var9, 0.0625F);
         var6 = 0.0F;
         var7 = 0.0F;
         var10 = 1.0F;
         var8 = 0.0F;
         var11 = 1.0F;
         var12 = true;
      }

      if((var5 & 8) != 0) {
         var6 = Math.min(var6, 0.9375F);
         var9 = 1.0F;
         var7 = 0.0F;
         var10 = 1.0F;
         var8 = 0.0F;
         var11 = 1.0F;
         var12 = true;
      }

      if((var5 & 4) != 0) {
         var11 = Math.max(var11, 0.0625F);
         var8 = 0.0F;
         var6 = 0.0F;
         var9 = 1.0F;
         var7 = 0.0F;
         var10 = 1.0F;
         var12 = true;
      }

      if((var5 & 1) != 0) {
         var8 = Math.min(var8, 0.9375F);
         var11 = 1.0F;
         var6 = 0.0F;
         var9 = 1.0F;
         var7 = 0.0F;
         var10 = 1.0F;
         var12 = true;
      }

      if(!var12 && this.d(var1.a(var2, var3 + 1, var4))) {
         var7 = Math.min(var7, 0.9375F);
         var10 = 1.0F;
         var6 = 0.0F;
         var9 = 1.0F;
         var8 = 0.0F;
         var11 = 1.0F;
      }

      this.a(var6, var7, var8, var9, var10, var11);
   }

   public OAxisAlignedBB e(OWorld var1, int var2, int var3, int var4) {
      return null;
   }

   public boolean b(OWorld var1, int var2, int var3, int var4, int var5) {
      switch(var5) {
      case 1:
         return this.d(var1.a(var2, var3 + 1, var4));
      case 2:
         return this.d(var1.a(var2, var3, var4 + 1));
      case 3:
         return this.d(var1.a(var2, var3, var4 - 1));
      case 4:
         return this.d(var1.a(var2 + 1, var3, var4));
      case 5:
         return this.d(var1.a(var2 - 1, var3, var4));
      default:
         return false;
      }
   }

   private boolean d(int var1) {
      if(var1 == 0) {
         return false;
      } else {
         OBlock var2 = OBlock.m[var1];
         return var2.b() && var2.cd.c();
      }
   }

   private boolean g(OWorld var1, int var2, int var3, int var4) {
      int var5 = var1.c(var2, var3, var4);
      int var6 = var5;
      if(var5 > 0) {
         for(int var7 = 0; var7 <= 3; ++var7) {
            int var8 = 1 << var7;
            if((var5 & var8) != 0 && !this.d(var1.a(var2 + ODirection.a[var7], var3, var4 + ODirection.b[var7])) && (var1.a(var2, var3 + 1, var4) != this.bO || (var1.c(var2, var3 + 1, var4) & var8) == 0)) {
               var6 &= ~var8;
            }
         }
      }

      if(var6 == 0 && !this.d(var1.a(var2, var3 + 1, var4))) {
         return false;
      } else {
         if(var6 != var5) {
            var1.c(var2, var3, var4, var6);
         }

         return true;
      }
   }

   public void a(OWorld var1, int var2, int var3, int var4, int var5) {
      if(!var1.F && !this.g(var1, var2, var3, var4)) {
         this.b(var1, var2, var3, var4, var1.c(var2, var3, var4), 0);
         var1.e(var2, var3, var4, 0);
      }

   }

   public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
      if(!var1.F && var1.r.nextInt(4) == 0) {
         byte var6 = 4;
         int var7 = 5;
         boolean var8 = false;

         int var9;
         int var10;
         int var11;
         label138:
         for(var9 = var2 - var6; var9 <= var2 + var6; ++var9) {
            for(var10 = var4 - var6; var10 <= var4 + var6; ++var10) {
               for(var11 = var3 - 1; var11 <= var3 + 1; ++var11) {
                  if(var1.a(var9, var11, var10) == this.bO) {
                     --var7;
                     if(var7 <= 0) {
                        var8 = true;
                        break label138;
                     }
                  }
               }
            }
         }

         var9 = var1.c(var2, var3, var4);
         var10 = var1.r.nextInt(6);
         var11 = ODirection.d[var10];
         int var12;
         int var13;
         if(var10 == 1 && var3 < 255 && var1.g(var2, var3 + 1, var4)) {
            if(var8) {
               return;
            }

            var12 = var1.r.nextInt(16) & var9;
            if(var12 > 0) {
               for(var13 = 0; var13 <= 3; ++var13) {
                  if(!this.d(var1.a(var2 + ODirection.a[var13], var3 + 1, var4 + ODirection.b[var13]))) {
                     var12 &= ~(1 << var13);
                  }
               }

               if(var12 > 0) {
                  var1.b(var2, var3 + 1, var4, this.bO, var12);
               }
            }
         } else {
            int var14;
            if(var10 >= 2 && var10 <= 5 && (var9 & 1 << var11) == 0) {
               if(var8) {
                  return;
               }

               var12 = var1.a(var2 + ODirection.a[var11], var3, var4 + ODirection.b[var11]);
               if(var12 != 0 && OBlock.m[var12] != null) {
                  if(OBlock.m[var12].cd.j() && OBlock.m[var12].b()) {
                     var1.c(var2, var3, var4, var9 | 1 << var11);
                  }
               } else {
                  var13 = var11 + 1 & 3;
                  var14 = var11 + 3 & 3;
                  if((var9 & 1 << var13) != 0 && this.d(var1.a(var2 + ODirection.a[var11] + ODirection.a[var13], var3, var4 + ODirection.b[var11] + ODirection.b[var13]))) {
                     var1.b(var2 + ODirection.a[var11], var3, var4 + ODirection.b[var11], this.bO, 1 << var13);
                  } else if((var9 & 1 << var14) != 0 && this.d(var1.a(var2 + ODirection.a[var11] + ODirection.a[var14], var3, var4 + ODirection.b[var11] + ODirection.b[var14]))) {
                     var1.b(var2 + ODirection.a[var11], var3, var4 + ODirection.b[var11], this.bO, 1 << var14);
                  } else if((var9 & 1 << var13) != 0 && var1.g(var2 + ODirection.a[var11] + ODirection.a[var13], var3, var4 + ODirection.b[var11] + ODirection.b[var13]) && this.d(var1.a(var2 + ODirection.a[var13], var3, var4 + ODirection.b[var13]))) {
                     var1.b(var2 + ODirection.a[var11] + ODirection.a[var13], var3, var4 + ODirection.b[var11] + ODirection.b[var13], this.bO, 1 << (var11 + 2 & 3));
                  } else if((var9 & 1 << var14) != 0 && var1.g(var2 + ODirection.a[var11] + ODirection.a[var14], var3, var4 + ODirection.b[var11] + ODirection.b[var14]) && this.d(var1.a(var2 + ODirection.a[var14], var3, var4 + ODirection.b[var14]))) {
                     var1.b(var2 + ODirection.a[var11] + ODirection.a[var14], var3, var4 + ODirection.b[var11] + ODirection.b[var14], this.bO, 1 << (var11 + 2 & 3));
                  } else if(this.d(var1.a(var2 + ODirection.a[var11], var3 + 1, var4 + ODirection.b[var11]))) {
                     var1.b(var2 + ODirection.a[var11], var3, var4 + ODirection.b[var11], this.bO, 0);
                  }
               }
            } else if(var3 > 1) {
               var12 = var1.a(var2, var3 - 1, var4);
               if(var12 == 0) {
                  var13 = var1.r.nextInt(16) & var9;
                  if(var13 > 0) {
                     var1.b(var2, var3 - 1, var4, this.bO, var13);
                  }
               } else if(var12 == this.bO) {
                  var13 = var1.r.nextInt(16) & var9;
                  var14 = var1.c(var2, var3 - 1, var4);
                  if(var14 != (var14 | var13)) {
                     var1.c(var2, var3 - 1, var4, var14 | var13);
                  }
               }
            }
         }
      }

   }

   public void e(OWorld var1, int var2, int var3, int var4, int var5) {
      byte var6 = 0;
      switch(var5) {
      case 2:
         var6 = 1;
         break;
      case 3:
         var6 = 4;
         break;
      case 4:
         var6 = 8;
         break;
      case 5:
         var6 = 2;
      }

      if(var6 != 0) {
         var1.c(var2, var3, var4, var6);
      }

   }

   public int a(int var1, Random var2, int var3) {
      return 0;
   }

   public int a(Random var1) {
      return 0;
   }

   public void a(OWorld var1, OEntityPlayer var2, int var3, int var4, int var5, int var6) {
      if(!var1.F && var2.U() != null && var2.U().c == OItem.bd.bP) {
         var2.a(OStatList.C[this.bO], 1);
         this.a(var1, var3, var4, var5, new OItemStack(OBlock.bu, 1, 0));
      } else {
         super.a(var1, var2, var3, var4, var5, var6);
      }

   }
}
