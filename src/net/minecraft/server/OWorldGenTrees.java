package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldGenerator;

public class OWorldGenTrees extends OWorldGenerator {

   private final int a;
   private final boolean b;
   private final int c;
   private final int d;


   public OWorldGenTrees(boolean var1) {
      this(var1, 4, 0, 0, false);
   }

   public OWorldGenTrees(boolean var1, int var2, int var3, int var4, boolean var5) {
      super(var1);
      this.a = var2;
      this.c = var3;
      this.d = var4;
      this.b = var5;
   }

   public boolean a(OWorld var1, Random var2, int var3, int var4, int var5) {
      int var6 = var2.nextInt(3) + this.a;
      boolean var7 = true;
      if(var4 >= 1 && var4 + var6 + 1 <= 256) {
         int var8;
         byte var9;
         int var11;
         int var12;
         for(var8 = var4; var8 <= var4 + 1 + var6; ++var8) {
            var9 = 1;
            if(var8 == var4) {
               var9 = 0;
            }

            if(var8 >= var4 + 1 + var6 - 2) {
               var9 = 2;
            }

            for(int var10 = var3 - var9; var10 <= var3 + var9 && var7; ++var10) {
               for(var11 = var5 - var9; var11 <= var5 + var9 && var7; ++var11) {
                  if(var8 >= 0 && var8 < 256) {
                     var12 = var1.a(var10, var8, var11);
                     if(var12 != 0 && var12 != OBlock.K.bO && var12 != OBlock.u.bO && var12 != OBlock.v.bO && var12 != OBlock.J.bO) {
                        var7 = false;
                     }
                  } else {
                     var7 = false;
                  }
               }
            }
         }

         if(!var7) {
            return false;
         } else {
            var8 = var1.a(var3, var4 - 1, var5);
            if((var8 == OBlock.u.bO || var8 == OBlock.v.bO) && var4 < 256 - var6 - 1) {
               this.a(var1, var3, var4 - 1, var5, OBlock.v.bO);
               var9 = 3;
               byte var18 = 0;

               int var13;
               int var14;
               int var15;
               for(var11 = var4 - var9 + var6; var11 <= var4 + var6; ++var11) {
                  var12 = var11 - (var4 + var6);
                  var13 = var18 + 1 - var12 / 2;

                  for(var14 = var3 - var13; var14 <= var3 + var13; ++var14) {
                     var15 = var14 - var3;

                     for(int var16 = var5 - var13; var16 <= var5 + var13; ++var16) {
                        int var17 = var16 - var5;
                        if((Math.abs(var15) != var13 || Math.abs(var17) != var13 || var2.nextInt(2) != 0 && var12 != 0) && !OBlock.n[var1.a(var14, var11, var16)]) {
                           this.a(var1, var14, var11, var16, OBlock.K.bO, this.d);
                        }
                     }
                  }
               }

               for(var11 = 0; var11 < var6; ++var11) {
                  var12 = var1.a(var3, var4 + var11, var5);
                  if(var12 == 0 || var12 == OBlock.K.bO) {
                     this.a(var1, var3, var4 + var11, var5, OBlock.J.bO, this.c);
                     if(this.b && var11 > 0) {
                        if(var2.nextInt(3) > 0 && var1.g(var3 - 1, var4 + var11, var5)) {
                           this.a(var1, var3 - 1, var4 + var11, var5, OBlock.bu.bO, 8);
                        }

                        if(var2.nextInt(3) > 0 && var1.g(var3 + 1, var4 + var11, var5)) {
                           this.a(var1, var3 + 1, var4 + var11, var5, OBlock.bu.bO, 2);
                        }

                        if(var2.nextInt(3) > 0 && var1.g(var3, var4 + var11, var5 - 1)) {
                           this.a(var1, var3, var4 + var11, var5 - 1, OBlock.bu.bO, 1);
                        }

                        if(var2.nextInt(3) > 0 && var1.g(var3, var4 + var11, var5 + 1)) {
                           this.a(var1, var3, var4 + var11, var5 + 1, OBlock.bu.bO, 4);
                        }
                     }
                  }
               }

               if(this.b) {
                  for(var11 = var4 - 3 + var6; var11 <= var4 + var6; ++var11) {
                     var12 = var11 - (var4 + var6);
                     var13 = 2 - var12 / 2;

                     for(var14 = var3 - var13; var14 <= var3 + var13; ++var14) {
                        for(var15 = var5 - var13; var15 <= var5 + var13; ++var15) {
                           if(var1.a(var14, var11, var15) == OBlock.K.bO) {
                              if(var2.nextInt(4) == 0 && var1.a(var14 - 1, var11, var15) == 0) {
                                 this.b(var1, var14 - 1, var11, var15, 8);
                              }

                              if(var2.nextInt(4) == 0 && var1.a(var14 + 1, var11, var15) == 0) {
                                 this.b(var1, var14 + 1, var11, var15, 2);
                              }

                              if(var2.nextInt(4) == 0 && var1.a(var14, var11, var15 - 1) == 0) {
                                 this.b(var1, var14, var11, var15 - 1, 1);
                              }

                              if(var2.nextInt(4) == 0 && var1.a(var14, var11, var15 + 1) == 0) {
                                 this.b(var1, var14, var11, var15 + 1, 4);
                              }
                           }
                        }
                     }
                  }
               }

               return true;
            } else {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   private void b(OWorld var1, int var2, int var3, int var4, int var5) {
      this.a(var1, var2, var3, var4, OBlock.bu.bO, var5);
      int var6 = 4;

      while(true) {
         --var3;
         if(var1.a(var2, var3, var4) != 0 || var6 <= 0) {
            return;
         }

         this.a(var1, var2, var3, var4, OBlock.bu.bO, var5);
         --var6;
      }
   }
}
