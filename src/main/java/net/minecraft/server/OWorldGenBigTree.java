package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldGenerator;

public class OWorldGenBigTree extends OWorldGenerator {

    static final byte[] a = new byte[] { (byte) 2, (byte) 0, (byte) 0, (byte) 1, (byte) 2, (byte) 1 };
    Random b = new Random();
    OWorld c;
    int[] d = new int[] { 0, 0, 0 };
    int e = 0;
    int f;
    double g = 0.618D;
    double h = 1.0D;
    double i = 0.381D;
    double j = 1.0D;
    double k = 1.0D;
    int l = 1;
    int m = 12;
    int n = 4;
    int[][] o;

    public OWorldGenBigTree(boolean var1) {
        super(var1);
    }

    void a() {
        this.f = (int) (this.e * this.g);
        if (this.f >= this.e) {
            this.f = this.e - 1;
        }

        int var1 = (int) (1.382D + Math.pow(this.k * this.e / 13.0D, 2.0D));
        if (var1 < 1) {
            var1 = 1;
        }

        int[][] var2 = new int[var1 * this.e][4];
        int var3 = this.d[1] + this.e - this.n;
        int var4 = 1;
        int var5 = this.d[1] + this.f;
        int var6 = var3 - this.d[1];
        var2[0][0] = this.d[0];
        var2[0][1] = var3;
        var2[0][2] = this.d[2];
        var2[0][3] = var5;
        --var3;

        while (var6 >= 0) {
            int var7 = 0;
            float var8 = this.a(var6);
            if (var8 < 0.0F) {
                --var3;
                --var6;
            } else {
                for (double var9 = 0.5D; var7 < var1; ++var7) {
                    double var11 = this.j * var8 * (this.b.nextFloat() + 0.328D);
                    double var13 = this.b.nextFloat() * 2.0D * 3.14159D;
                    int var15 = OMathHelper.b(var11 * Math.sin(var13) + this.d[0] + var9);
                    int var16 = OMathHelper.b(var11 * Math.cos(var13) + this.d[2] + var9);
                    int[] var17 = new int[] { var15, var3, var16 };
                    int[] var18 = new int[] { var15, var3 + this.n, var16 };
                    if (this.a(var17, var18) == -1) {
                        int[] var19 = new int[] { this.d[0], this.d[1], this.d[2] };
                        double var20 = Math.sqrt(Math.pow(Math.abs(this.d[0] - var17[0]), 2.0D) + Math.pow(Math.abs(this.d[2] - var17[2]), 2.0D));
                        double var22 = var20 * this.i;
                        if (var17[1] - var22 > var5) {
                            var19[1] = var5;
                        } else {
                            var19[1] = (int) (var17[1] - var22);
                        }

                        if (this.a(var19, var17) == -1) {
                            var2[var4][0] = var15;
                            var2[var4][1] = var3;
                            var2[var4][2] = var16;
                            var2[var4][3] = var19[1];
                            ++var4;
                        }
                    }
                }

                --var3;
                --var6;
            }
        }

        this.o = new int[var4][4];
        System.arraycopy(var2, 0, this.o, 0, var4);
    }

    void a(int var1, int var2, int var3, float var4, byte var5, int var6) {
        int var7 = (int) (var4 + 0.618D);
        byte var8 = a[var5];
        byte var9 = a[var5 + 3];
        int[] var10 = new int[] { var1, var2, var3 };
        int[] var11 = new int[] { 0, 0, 0 };
        int var12 = -var7;
        int var13 = -var7;

        for (var11[var5] = var10[var5]; var12 <= var7; ++var12) {
            var11[var8] = var10[var8] + var12;
            var13 = -var7;

            while (var13 <= var7) {
                double var14 = Math.sqrt(Math.pow(Math.abs(var12) + 0.5D, 2.0D) + Math.pow(Math.abs(var13) + 0.5D, 2.0D));
                if (var14 > var4) {
                    ++var13;
                } else {
                    var11[var9] = var10[var9] + var13;
                    int var16 = this.c.a(var11[0], var11[1], var11[2]);
                    if (var16 != 0 && var16 != 18) {
                        ++var13;
                    } else {
                        this.a(this.c, var11[0], var11[1], var11[2], var6, 0);
                        ++var13;
                    }
                }
            }
        }

    }

    float a(int var1) {
        if((double)var1 < (double)((float)this.e) * 0.3D) {
         return -1.618F;
      } else {
         float var2 = this.e / 2.0F;
         float var3 = this.e / 2.0F - var1;
         float var4;
         if(var3 == 0.0F) {
            var4 = var2;
         } else if(Math.abs(var3) >= var2) {
            var4 = 0.0F;
         } else {
            var4 = (float)Math.sqrt(Math.pow(Math.abs(var2), 2.0D) - Math.pow(Math.abs(var3), 2.0D));
         }

         var4 *= 0.5F;
         return var4;
      }
   }

    float b(int var1) {
        return var1 >= 0 && var1 < this.n ? (var1 != 0 && var1 != this.n - 1 ? 3.0F : 2.0F) : -1.0F;
    }

    void a(int var1, int var2, int var3) {
        int var4 = var2;

        for (int var5 = var2 + this.n; var4 < var5; ++var4) {
            float var6 = this.b(var4 - var2);
            this.a(var1, var4, var3, var6, (byte) 1, 18);
        }

    }

    void a(int[] var1, int[] var2, int var3) {
        int[] var4 = new int[] { 0, 0, 0 };
        byte var5 = 0;

        byte var6;
        for (var6 = 0; var5 < 3; ++var5) {
            var4[var5] = var2[var5] - var1[var5];
            if (Math.abs(var4[var5]) > Math.abs(var4[var6])) {
                var6 = var5;
            }
        }

        if (var4[var6] != 0) {
            byte var7 = a[var6];
            byte var8 = a[var6 + 3];
            byte var9;
            if (var4[var6] > 0) {
                var9 = 1;
            } else {
                var9 = -1;
            }

            double var10 = (double) var4[var7] / (double) var4[var6];
            double var12 = (double) var4[var8] / (double) var4[var6];
            int[] var14 = new int[] { 0, 0, 0 };
            int var15 = 0;

            for (int var16 = var4[var6] + var9; var15 != var16; var15 += var9) {
                var14[var6] = OMathHelper.b((var1[var6] + var15) + 0.5D);
                var14[var7] = OMathHelper.b(var1[var7] + var15 * var10 + 0.5D);
                var14[var8] = OMathHelper.b(var1[var8] + var15 * var12 + 0.5D);
                this.a(this.c, var14[0], var14[1], var14[2], var3, 0);
            }

        }
    }

    void b() {
        int var1 = 0;

        for (int var2 = this.o.length; var1 < var2; ++var1) {
            int var3 = this.o[var1][0];
            int var4 = this.o[var1][1];
            int var5 = this.o[var1][2];
            this.a(var3, var4, var5);
        }

    }

    boolean c(int var1) {
        return var1 >= this.e * 0.2D;
    }

    void c() {
        int var1 = this.d[0];
        int var2 = this.d[1];
        int var3 = this.d[1] + this.f;
        int var4 = this.d[2];
        int[] var5 = new int[] { var1, var2, var4 };
        int[] var6 = new int[] { var1, var3, var4 };
        this.a(var5, var6, 17);
        if (this.l == 2) {
            ++var5[0];
            ++var6[0];
            this.a(var5, var6, 17);
            ++var5[2];
            ++var6[2];
            this.a(var5, var6, 17);
            var5[0] += -1;
            var6[0] += -1;
            this.a(var5, var6, 17);
        }

    }

    void d() {
        int var1 = 0;
        int var2 = this.o.length;

        for (int[] var3 = new int[] { this.d[0], this.d[1], this.d[2] }; var1 < var2; ++var1) {
            int[] var4 = this.o[var1];
            int[] var5 = new int[] { var4[0], var4[1], var4[2] };
            var3[1] = var4[3];
            int var6 = var3[1] - this.d[1];
            if (this.c(var6)) {
                this.a(var3, var5, 17);
            }
        }

    }

    int a(int[] var1, int[] var2) {
        int[] var3 = new int[] { 0, 0, 0 };
        byte var4 = 0;

        byte var5;
        for (var5 = 0; var4 < 3; ++var4) {
            var3[var4] = var2[var4] - var1[var4];
            if (Math.abs(var3[var4]) > Math.abs(var3[var5])) {
                var5 = var4;
            }
        }

        if (var3[var5] == 0) {
            return -1;
        } else {
            byte var6 = a[var5];
            byte var7 = a[var5 + 3];
            byte var8;
            if (var3[var5] > 0) {
                var8 = 1;
            } else {
                var8 = -1;
            }

            double var9 = (double) var3[var6] / (double) var3[var5];
            double var11 = (double) var3[var7] / (double) var3[var5];
            int[] var13 = new int[] { 0, 0, 0 };
            int var14 = 0;

            int var15;
            for (var15 = var3[var5] + var8; var14 != var15; var14 += var8) {
                var13[var5] = var1[var5] + var14;
                var13[var6] = OMathHelper.b(var1[var6] + var14 * var9);
                var13[var7] = OMathHelper.b(var1[var7] + var14 * var11);
                int var16 = this.c.a(var13[0], var13[1], var13[2]);
                if (var16 != 0 && var16 != 18) {
                    break;
                }
            }

            return var14 == var15 ? -1 : Math.abs(var14);
        }
    }

    boolean e() {
        int[] var1 = new int[] { this.d[0], this.d[1], this.d[2] };
        int[] var2 = new int[] { this.d[0], this.d[1] + this.e - 1, this.d[2] };
        int var3 = this.c.a(this.d[0], this.d[1] - 1, this.d[2]);
        if (var3 != 2 && var3 != 3) {
            return false;
        } else {
            int var4 = this.a(var1, var2);
            if (var4 == -1) {
                return true;
            } else if (var4 < 6) {
                return false;
            } else {
                this.e = var4;
                return true;
            }
        }
    }

    @Override
    public void a(double var1, double var3, double var5) {
        this.m = (int) (var1 * 12.0D);
        if (var1 > 0.5D) {
            this.n = 5;
        }

        this.j = var3;
        this.k = var5;
    }

    @Override
    public boolean a(OWorld var1, Random var2, int var3, int var4, int var5) {
        this.c = var1;
        long var6 = var2.nextLong();
        this.b.setSeed(var6);
        this.d[0] = var3;
        this.d[1] = var4;
        this.d[2] = var5;
        if (this.e == 0) {
            this.e = 5 + this.b.nextInt(this.m);
        }

        if (!this.e()) {
            return false;
        } else {
            this.a();
            this.b();
            this.c();
            this.d();
            return true;
        }
    }

}
