package net.minecraft.server;

import java.util.List;
import java.util.Random;
import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockSand;
import net.minecraft.server.OChunk;
import net.minecraft.server.OChunkPosition;
import net.minecraft.server.OEnumCreatureType;
import net.minecraft.server.OIChunkProvider;
import net.minecraft.server.OIProgressUpdate;
import net.minecraft.server.OMapGenBase;
import net.minecraft.server.OMapGenCavesHell;
import net.minecraft.server.OMapGenNetherBridge;
import net.minecraft.server.ONoiseGeneratorOctaves;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldGenFire;
import net.minecraft.server.OWorldGenFlowers;
import net.minecraft.server.OWorldGenGlowStone1;
import net.minecraft.server.OWorldGenGlowStone2;
import net.minecraft.server.OWorldGenHellLava;

public class OChunkProviderHell implements OIChunkProvider {

   private Random i;
   private ONoiseGeneratorOctaves j;
   private ONoiseGeneratorOctaves k;
   private ONoiseGeneratorOctaves l;
   private ONoiseGeneratorOctaves m;
   private ONoiseGeneratorOctaves n;
   public ONoiseGeneratorOctaves a;
   public ONoiseGeneratorOctaves b;
   private OWorld o;
   private double[] p;
   public OMapGenNetherBridge c = new OMapGenNetherBridge();
   private double[] q = new double[256];
   private double[] r = new double[256];
   private double[] s = new double[256];
   private OMapGenBase t = new OMapGenCavesHell();
   double[] d;
   double[] e;
   double[] f;
   double[] g;
   double[] h;


   public OChunkProviderHell(OWorld var1, long var2) {
      super();
      this.o = var1;
      this.i = new Random(var2);
      this.j = new ONoiseGeneratorOctaves(this.i, 16);
      this.k = new ONoiseGeneratorOctaves(this.i, 16);
      this.l = new ONoiseGeneratorOctaves(this.i, 8);
      this.m = new ONoiseGeneratorOctaves(this.i, 4);
      this.n = new ONoiseGeneratorOctaves(this.i, 4);
      this.a = new ONoiseGeneratorOctaves(this.i, 10);
      this.b = new ONoiseGeneratorOctaves(this.i, 16);
   }

   public void a(int var1, int var2, byte[] var3) {
      byte var4 = 4;
      byte var5 = 32;
      int var6 = var4 + 1;
      byte var7 = 17;
      int var8 = var4 + 1;
      this.p = this.a(this.p, var1 * var4, 0, var2 * var4, var6, var7, var8);

      for(int var9 = 0; var9 < var4; ++var9) {
         for(int var10 = 0; var10 < var4; ++var10) {
            for(int var11 = 0; var11 < 16; ++var11) {
               double var12 = 0.125D;
               double var14 = this.p[((var9 + 0) * var8 + var10 + 0) * var7 + var11 + 0];
               double var16 = this.p[((var9 + 0) * var8 + var10 + 1) * var7 + var11 + 0];
               double var18 = this.p[((var9 + 1) * var8 + var10 + 0) * var7 + var11 + 0];
               double var20 = this.p[((var9 + 1) * var8 + var10 + 1) * var7 + var11 + 0];
               double var22 = (this.p[((var9 + 0) * var8 + var10 + 0) * var7 + var11 + 1] - var14) * var12;
               double var24 = (this.p[((var9 + 0) * var8 + var10 + 1) * var7 + var11 + 1] - var16) * var12;
               double var26 = (this.p[((var9 + 1) * var8 + var10 + 0) * var7 + var11 + 1] - var18) * var12;
               double var28 = (this.p[((var9 + 1) * var8 + var10 + 1) * var7 + var11 + 1] - var20) * var12;

               for(int var30 = 0; var30 < 8; ++var30) {
                  double var31 = 0.25D;
                  double var33 = var14;
                  double var35 = var16;
                  double var37 = (var18 - var14) * var31;
                  double var39 = (var20 - var16) * var31;

                  for(int var41 = 0; var41 < 4; ++var41) {
                     int var42 = var41 + var9 * 4 << 11 | 0 + var10 * 4 << 7 | var11 * 8 + var30;
                     short var43 = 128;
                     double var44 = 0.25D;
                     double var46 = var33;
                     double var48 = (var35 - var33) * var44;

                     for(int var50 = 0; var50 < 4; ++var50) {
                        int var51 = 0;
                        if(var11 * 8 + var30 < var5) {
                           var51 = OBlock.D.bO;
                        }

                        if(var46 > 0.0D) {
                           var51 = OBlock.bb.bO;
                        }

                        var3[var42] = (byte)var51;
                        var42 += var43;
                        var46 += var48;
                     }

                     var33 += var37;
                     var35 += var39;
                  }

                  var14 += var22;
                  var16 += var24;
                  var18 += var26;
                  var20 += var28;
               }
            }
         }
      }

   }

   public void b(int var1, int var2, byte[] var3) {
      byte var4 = 64;
      double var5 = 0.03125D;
      this.q = this.m.a(this.q, var1 * 16, var2 * 16, 0, 16, 16, 1, var5, var5, 1.0D);
      this.r = this.m.a(this.r, var1 * 16, 109, var2 * 16, 16, 1, 16, var5, 1.0D, var5);
      this.s = this.n.a(this.s, var1 * 16, var2 * 16, 0, 16, 16, 1, var5 * 2.0D, var5 * 2.0D, var5 * 2.0D);

      for(int var7 = 0; var7 < 16; ++var7) {
         for(int var8 = 0; var8 < 16; ++var8) {
            boolean var9 = this.q[var7 + var8 * 16] + this.i.nextDouble() * 0.2D > 0.0D;
            boolean var10 = this.r[var7 + var8 * 16] + this.i.nextDouble() * 0.2D > 0.0D;
            int var11 = (int)(this.s[var7 + var8 * 16] / 3.0D + 3.0D + this.i.nextDouble() * 0.25D);
            int var12 = -1;
            byte var13 = (byte)OBlock.bb.bO;
            byte var14 = (byte)OBlock.bb.bO;

            for(int var15 = 127; var15 >= 0; --var15) {
               int var16 = (var8 * 16 + var7) * 128 + var15;
               if(var15 >= 127 - this.i.nextInt(5)) {
                  var3[var16] = (byte)OBlock.z.bO;
               } else if(var15 <= 0 + this.i.nextInt(5)) {
                  var3[var16] = (byte)OBlock.z.bO;
               } else {
                  byte var17 = var3[var16];
                  if(var17 == 0) {
                     var12 = -1;
                  } else if(var17 == OBlock.bb.bO) {
                     if(var12 == -1) {
                        if(var11 <= 0) {
                           var13 = 0;
                           var14 = (byte)OBlock.bb.bO;
                        } else if(var15 >= var4 - 4 && var15 <= var4 + 1) {
                           var13 = (byte)OBlock.bb.bO;
                           var14 = (byte)OBlock.bb.bO;
                           if(var10) {
                              var13 = (byte)OBlock.F.bO;
                           }

                           if(var10) {
                              var14 = (byte)OBlock.bb.bO;
                           }

                           if(var9) {
                              var13 = (byte)OBlock.bc.bO;
                           }

                           if(var9) {
                              var14 = (byte)OBlock.bc.bO;
                           }
                        }

                        if(var15 < var4 && var13 == 0) {
                           var13 = (byte)OBlock.D.bO;
                        }

                        var12 = var11;
                        if(var15 >= var4 - 1) {
                           var3[var16] = var13;
                        } else {
                           var3[var16] = var14;
                        }
                     } else if(var12 > 0) {
                        --var12;
                        var3[var16] = var14;
                     }
                  }
               }
            }
         }
      }

   }

   public OChunk c(int var1, int var2) {
      return this.b(var1, var2);
   }

   public OChunk b(int var1, int var2) {
      this.i.setSeed((long)var1 * 341873128712L + (long)var2 * 132897987541L);
      byte[] var3 = new byte['\u8000'];
      this.a(var1, var2, var3);
      this.b(var1, var2, var3);
      this.t.a(this, this.o, var1, var2, var3);
      this.c.a(this, this.o, var1, var2, var3);
      OChunk var4 = new OChunk(this.o, var3, var1, var2);
      OBiomeGenBase[] var5 = this.o.a().b((OBiomeGenBase[])null, var1 * 16, var2 * 16, 16, 16);
      byte[] var6 = var4.l();

      for(int var7 = 0; var7 < var6.length; ++var7) {
         var6[var7] = (byte)var5[var7].M;
      }

      var4.m();
      return var4;
   }

   private double[] a(double[] var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      if(var1 == null) {
         var1 = new double[var5 * var6 * var7];
      }

      double var8 = 684.412D;
      double var10 = 2053.236D;
      this.g = this.a.a(this.g, var2, var3, var4, var5, 1, var7, 1.0D, 0.0D, 1.0D);
      this.h = this.b.a(this.h, var2, var3, var4, var5, 1, var7, 100.0D, 0.0D, 100.0D);
      this.d = this.l.a(this.d, var2, var3, var4, var5, var6, var7, var8 / 80.0D, var10 / 60.0D, var8 / 80.0D);
      this.e = this.j.a(this.e, var2, var3, var4, var5, var6, var7, var8, var10, var8);
      this.f = this.k.a(this.f, var2, var3, var4, var5, var6, var7, var8, var10, var8);
      int var12 = 0;
      int var13 = 0;
      double[] var14 = new double[var6];

      int var15;
      for(var15 = 0; var15 < var6; ++var15) {
         var14[var15] = Math.cos((double)var15 * 3.141592653589793D * 6.0D / (double)var6) * 2.0D;
         double var16 = (double)var15;
         if(var15 > var6 / 2) {
            var16 = (double)(var6 - 1 - var15);
         }

         if(var16 < 4.0D) {
            var16 = 4.0D - var16;
            var14[var15] -= var16 * var16 * var16 * 10.0D;
         }
      }

      for(var15 = 0; var15 < var5; ++var15) {
         for(int var18 = 0; var18 < var7; ++var18) {
            double var19 = (this.g[var13] + 256.0D) / 512.0D;
            if(var19 > 1.0D) {
               var19 = 1.0D;
            }

            double var21 = 0.0D;
            double var23 = this.h[var13] / 8000.0D;
            if(var23 < 0.0D) {
               var23 = -var23;
            }

            var23 = var23 * 3.0D - 3.0D;
            if(var23 < 0.0D) {
               var23 /= 2.0D;
               if(var23 < -1.0D) {
                  var23 = -1.0D;
               }

               var23 /= 1.4D;
               var23 /= 2.0D;
               var19 = 0.0D;
            } else {
               if(var23 > 1.0D) {
                  var23 = 1.0D;
               }

               var23 /= 6.0D;
            }

            var19 += 0.5D;
            var23 = var23 * (double)var6 / 16.0D;
            ++var13;

            for(int var25 = 0; var25 < var6; ++var25) {
               double var26 = 0.0D;
               double var28 = var14[var25];
               double var30 = this.e[var12] / 512.0D;
               double var32 = this.f[var12] / 512.0D;
               double var34 = (this.d[var12] / 10.0D + 1.0D) / 2.0D;
               if(var34 < 0.0D) {
                  var26 = var30;
               } else if(var34 > 1.0D) {
                  var26 = var32;
               } else {
                  var26 = var30 + (var32 - var30) * var34;
               }

               var26 -= var28;
               double var36;
               if(var25 > var6 - 4) {
                  var36 = (double)((float)(var25 - (var6 - 4)) / 3.0F);
                  var26 = var26 * (1.0D - var36) + -10.0D * var36;
               }

               if((double)var25 < var21) {
                  var36 = (var21 - (double)var25) / 4.0D;
                  if(var36 < 0.0D) {
                     var36 = 0.0D;
                  }

                  if(var36 > 1.0D) {
                     var36 = 1.0D;
                  }

                  var26 = var26 * (1.0D - var36) + -10.0D * var36;
               }

               var1[var12] = var26;
               ++var12;
            }
         }
      }

      return var1;
   }

   public boolean a(int var1, int var2) {
      return true;
   }

   public void a(OIChunkProvider var1, int var2, int var3) {
      OBlockSand.a = true;
      int var4 = var2 * 16;
      int var5 = var3 * 16;
      this.c.a(this.o, this.i, var2, var3);

      int var6;
      int var7;
      int var8;
      int var9;
      for(var6 = 0; var6 < 8; ++var6) {
         var7 = var4 + this.i.nextInt(16) + 8;
         var8 = this.i.nextInt(120) + 4;
         var9 = var5 + this.i.nextInt(16) + 8;
         (new OWorldGenHellLava(OBlock.C.bO)).a(this.o, this.i, var7, var8, var9);
      }

      var6 = this.i.nextInt(this.i.nextInt(10) + 1) + 1;

      int var10;
      for(var7 = 0; var7 < var6; ++var7) {
         var8 = var4 + this.i.nextInt(16) + 8;
         var9 = this.i.nextInt(120) + 4;
         var10 = var5 + this.i.nextInt(16) + 8;
         (new OWorldGenFire()).a(this.o, this.i, var8, var9, var10);
      }

      var6 = this.i.nextInt(this.i.nextInt(10) + 1);

      for(var7 = 0; var7 < var6; ++var7) {
         var8 = var4 + this.i.nextInt(16) + 8;
         var9 = this.i.nextInt(120) + 4;
         var10 = var5 + this.i.nextInt(16) + 8;
         (new OWorldGenGlowStone1()).a(this.o, this.i, var8, var9, var10);
      }

      for(var7 = 0; var7 < 10; ++var7) {
         var8 = var4 + this.i.nextInt(16) + 8;
         var9 = this.i.nextInt(128);
         var10 = var5 + this.i.nextInt(16) + 8;
         (new OWorldGenGlowStone2()).a(this.o, this.i, var8, var9, var10);
      }

      if(this.i.nextInt(1) == 0) {
         var7 = var4 + this.i.nextInt(16) + 8;
         var8 = this.i.nextInt(128);
         var9 = var5 + this.i.nextInt(16) + 8;
         (new OWorldGenFlowers(OBlock.af.bO)).a(this.o, this.i, var7, var8, var9);
      }

      if(this.i.nextInt(1) == 0) {
         var7 = var4 + this.i.nextInt(16) + 8;
         var8 = this.i.nextInt(128);
         var9 = var5 + this.i.nextInt(16) + 8;
         (new OWorldGenFlowers(OBlock.ag.bO)).a(this.o, this.i, var7, var8, var9);
      }

      OBlockSand.a = false;
   }

   public boolean a(boolean var1, OIProgressUpdate var2) {
      return true;
   }

   public boolean a() {
      return false;
   }

   public boolean b() {
      return true;
   }

   public List a(OEnumCreatureType var1, int var2, int var3, int var4) {
      if(var1 == OEnumCreatureType.a && this.c.a(var2, var3, var4)) {
         return this.c.b();
      } else {
         OBiomeGenBase var5 = this.o.a(var2, var4);
         return var5 == null?null:var5.a(var1);
      }
   }

   public OChunkPosition a(OWorld var1, String var2, int var3, int var4, int var5) {
      return null;
   }
}
