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
import net.minecraft.server.OMapGenCaves;
import net.minecraft.server.OMapGenMineshaft;
import net.minecraft.server.OMapGenRavine;
import net.minecraft.server.OMapGenStronghold;
import net.minecraft.server.OMapGenVillage;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONoiseGeneratorOctaves;
import net.minecraft.server.OSpawnerAnimals;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldGenDungeons;
import net.minecraft.server.OWorldGenLakes;


public class OChunkProviderGenerate implements OIChunkProvider {

    private Random k;
    private ONoiseGeneratorOctaves l;
    private ONoiseGeneratorOctaves m;
    private ONoiseGeneratorOctaves n;
    private ONoiseGeneratorOctaves o;
    public ONoiseGeneratorOctaves a;
    public ONoiseGeneratorOctaves b;
    public ONoiseGeneratorOctaves c;
    private OWorld p;
    private final boolean q;
    private double[] r;
    private double[] s = new double[256];
    private OMapGenBase t = new OMapGenCaves();
    private OMapGenStronghold u = new OMapGenStronghold();
    private OMapGenVillage v = new OMapGenVillage(0);
    private OMapGenMineshaft w = new OMapGenMineshaft();
    private OMapGenBase x = new OMapGenRavine();
    private OBiomeGenBase[] y;
    double[] d;
    double[] e;
    double[] f;
    double[] g;
    double[] h;
    float[] i;
    int[][] j = new int[32][32];

    public OChunkProviderGenerate(OWorld var1, long var2, boolean var4) {
        super();
        this.p = var1;
        this.q = var4;
        this.k = new Random(var2);
        this.l = new ONoiseGeneratorOctaves(this.k, 16);
        this.m = new ONoiseGeneratorOctaves(this.k, 16);
        this.n = new ONoiseGeneratorOctaves(this.k, 8);
        this.o = new ONoiseGeneratorOctaves(this.k, 4);
        this.a = new ONoiseGeneratorOctaves(this.k, 10);
        this.b = new ONoiseGeneratorOctaves(this.k, 16);
        this.c = new ONoiseGeneratorOctaves(this.k, 8);
    }

    public void a(int var1, int var2, byte[] var3) {
        byte var4 = 4;
        byte var5 = 16;
        byte var6 = 63;
        int var7 = var4 + 1;
        byte var8 = 17;
        int var9 = var4 + 1;

        this.y = this.p.a().a(this.y, var1 * 4 - 2, var2 * 4 - 2, var7 + 5, var9 + 5);
        this.r = this.a(this.r, var1 * var4, 0, var2 * var4, var7, var8, var9);

        for (int var10 = 0; var10 < var4; ++var10) {
            for (int var11 = 0; var11 < var4; ++var11) {
                for (int var12 = 0; var12 < var5; ++var12) {
                    double var13 = 0.125D;
                    double var15 = this.r[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 0];
                    double var17 = this.r[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 0];
                    double var19 = this.r[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 0];
                    double var21 = this.r[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 0];
                    double var23 = (this.r[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 1] - var15) * var13;
                    double var25 = (this.r[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 1] - var17) * var13;
                    double var27 = (this.r[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 1] - var19) * var13;
                    double var29 = (this.r[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 1] - var21) * var13;

                    for (int var31 = 0; var31 < 8; ++var31) {
                        double var32 = 0.25D;
                        double var34 = var15;
                        double var36 = var17;
                        double var38 = (var19 - var15) * var32;
                        double var40 = (var21 - var17) * var32;

                        for (int var42 = 0; var42 < 4; ++var42) {
                            int var43 = var42 + var10 * 4 << 11 | 0 + var11 * 4 << 7 | var12 * 8 + var31;
                            short var44 = 128;

                            var43 -= var44;
                            double var45 = 0.25D;
                            double var49 = (var36 - var34) * var45;
                            double var47 = var34 - var49;

                            for (int var51 = 0; var51 < 4; ++var51) {
                                if ((var47 += var49) > 0.0D) {
                                    var3[var43 += var44] = (byte) OBlock.t.bO;
                                } else if (var12 * 8 + var31 < var6) {
                                    var3[var43 += var44] = (byte) OBlock.B.bO;
                                } else {
                                    var3[var43 += var44] = 0;
                                }
                            }

                            var34 += var38;
                            var36 += var40;
                        }

                        var15 += var23;
                        var17 += var25;
                        var19 += var27;
                        var21 += var29;
                    }
                }
            }
        }

    }

    public void a(int var1, int var2, byte[] var3, OBiomeGenBase[] var4) {
        byte var5 = 63;
        double var6 = 0.03125D;

        this.s = this.o.a(this.s, var1 * 16, var2 * 16, 0, 16, 16, 1, var6 * 2.0D, var6 * 2.0D, var6 * 2.0D);

        for (int var8 = 0; var8 < 16; ++var8) {
            for (int var9 = 0; var9 < 16; ++var9) {
                OBiomeGenBase var10 = var4[var9 + var8 * 16];
                float var11 = var10.i();
                int var12 = (int) (this.s[var8 + var9 * 16] / 3.0D + 3.0D + this.k.nextDouble() * 0.25D);
                int var13 = -1;
                byte var14 = var10.A;
                byte var15 = var10.B;

                for (int var16 = 127; var16 >= 0; --var16) {
                    int var17 = (var9 * 16 + var8) * 128 + var16;

                    if (var16 <= 0 + this.k.nextInt(5)) {
                        var3[var17] = (byte) OBlock.z.bO;
                    } else {
                        byte var18 = var3[var17];

                        if (var18 == 0) {
                            var13 = -1;
                        } else if (var18 == OBlock.t.bO) {
                            if (var13 == -1) {
                                if (var12 <= 0) {
                                    var14 = 0;
                                    var15 = (byte) OBlock.t.bO;
                                } else if (var16 >= var5 - 4 && var16 <= var5 + 1) {
                                    var14 = var10.A;
                                    var15 = var10.B;
                                }

                                if (var16 < var5 && var14 == 0) {
                                    if (var11 < 0.15F) {
                                        var14 = (byte) OBlock.aT.bO;
                                    } else {
                                        var14 = (byte) OBlock.B.bO;
                                    }
                                }

                                var13 = var12;
                                if (var16 >= var5 - 1) {
                                    var3[var17] = var14;
                                } else {
                                    var3[var17] = var15;
                                }
                            } else if (var13 > 0) {
                                --var13;
                                var3[var17] = var15;
                                if (var13 == 0 && var15 == OBlock.E.bO) {
                                    var13 = this.k.nextInt(4);
                                    var15 = (byte) OBlock.Q.bO;
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    @Override
    public OChunk c(int var1, int var2) {
        return this.b(var1, var2);
    }

    @Override
    public OChunk b(int var1, int var2) {
        this.k.setSeed(var1 * 341873128712L + var2 * 132897987541L);
        byte[] var3 = new byte['\u8000'];

        this.a(var1, var2, var3);
        this.y = this.p.a().b(this.y, var1 * 16, var2 * 16, 16, 16);
        this.a(var1, var2, var3, this.y);
        this.t.a(this, this.p, var1, var2, var3);
        this.x.a(this, this.p, var1, var2, var3);
        if (this.q) {
            this.w.a(this, this.p, var1, var2, var3);
            this.v.a(this, this.p, var1, var2, var3);
            this.u.a(this, this.p, var1, var2, var3);
        }

        OChunk var4 = new OChunk(this.p, var3, var1, var2);
        byte[] var5 = var4.l();

        for (int var6 = 0; var6 < var5.length; ++var6) {
            var5[var6] = (byte) this.y[var6].M;
        }

        var4.a();
        return var4;
    }

    private double[] a(double[] var1, int var2, int var3, int var4, int var5, int var6, int var7) {
        if (var1 == null) {
            var1 = new double[var5 * var6 * var7];
        }

        if (this.i == null) {
            this.i = new float[25];

            for (int var8 = -2; var8 <= 2; ++var8) {
                for (int var9 = -2; var9 <= 2; ++var9) {
                    float var10 = 10.0F / OMathHelper.c((var8 * var8 + var9 * var9) + 0.2F);

                    this.i[var8 + 2 + (var9 + 2) * 5] = var10;
                }
            }
        }

        double var11 = 684.412D;
        double var13 = 684.412D;

        this.g = this.a.a(this.g, var2, var4, var5, var7, 1.121D, 1.121D, 0.5D);
        this.h = this.b.a(this.h, var2, var4, var5, var7, 200.0D, 200.0D, 0.5D);
        this.d = this.n.a(this.d, var2, var3, var4, var5, var6, var7, var11 / 80.0D, var13 / 160.0D, var11 / 80.0D);
        this.e = this.l.a(this.e, var2, var3, var4, var5, var6, var7, var11, var13, var11);
        this.f = this.m.a(this.f, var2, var3, var4, var5, var6, var7, var11, var13, var11);
        boolean var49 = false;
        boolean var48 = false;
        int var15 = 0;
        int var16 = 0;

        for (int var17 = 0; var17 < var5; ++var17) {
            for (int var18 = 0; var18 < var7; ++var18) {
                float var19 = 0.0F;
                float var20 = 0.0F;
                float var21 = 0.0F;
                byte var22 = 2;
                OBiomeGenBase var23 = this.y[var17 + 2 + (var18 + 2) * (var5 + 5)];

                for (int var24 = -var22; var24 <= var22; ++var24) {
                    for (int var25 = -var22; var25 <= var22; ++var25) {
                        OBiomeGenBase var26 = this.y[var17 + var24 + 2 + (var18 + var25 + 2) * (var5 + 5)];
                        float var27 = this.i[var24 + 2 + (var25 + 2) * 5] / (var26.D + 2.0F);

                        if (var26.D > var23.D) {
                            var27 /= 2.0F;
                        }

                        var19 += var26.E * var27;
                        var20 += var26.D * var27;
                        var21 += var27;
                    }
                }

                var19 /= var21;
                var20 /= var21;
                var19 = var19 * 0.9F + 0.1F;
                var20 = (var20 * 4.0F - 1.0F) / 8.0F;
                double var28 = this.h[var16] / 8000.0D;

                if (var28 < 0.0D) {
                    var28 = -var28 * 0.3D;
                }

                var28 = var28 * 3.0D - 2.0D;
                if (var28 < 0.0D) {
                    var28 /= 2.0D;
                    if (var28 < -1.0D) {
                        var28 = -1.0D;
                    }

                    var28 /= 1.4D;
                    var28 /= 2.0D;
                } else {
                    if (var28 > 1.0D) {
                        var28 = 1.0D;
                    }

                    var28 /= 8.0D;
                }

                ++var16;

                for (int var50 = 0; var50 < var6; ++var50) {
                    double var30 = var20;
                    double var32 = var19;

                    var30 += var28 * 0.2D;
                    var30 = var30 * var6 / 16.0D;
                    double var34 = var6 / 2.0D + var30 * 4.0D;
                    double var36 = 0.0D;
                    double var38 = (var50 - var34) * 12.0D * 128.0D / 128.0D / var32;

                    if (var38 < 0.0D) {
                        var38 *= 4.0D;
                    }

                    double var40 = this.e[var15] / 512.0D;
                    double var42 = this.f[var15] / 512.0D;
                    double var44 = (this.d[var15] / 10.0D + 1.0D) / 2.0D;

                    if (var44 < 0.0D) {
                        var36 = var40;
                    } else if (var44 > 1.0D) {
                        var36 = var42;
                    } else {
                        var36 = var40 + (var42 - var40) * var44;
                    }

                    var36 -= var38;
                    if (var50 > var6 - 4) {
                        double var46 = ((var50 - (var6 - 4)) / 3.0F);

                        var36 = var36 * (1.0D - var46) + -10.0D * var46;
                    }

                    var1[var15] = var36;
                    ++var15;
                }
            }
        }

        return var1;
    }

    @Override
    public boolean a(int var1, int var2) {
        return true;
    }

    @Override
    public void a(OIChunkProvider var1, int var2, int var3) {
        OBlockSand.a = true;
        int var4 = var2 * 16;
        int var5 = var3 * 16;
        OBiomeGenBase var6 = this.p.a(var4 + 16, var5 + 16);

        this.k.setSeed(this.p.n());
        long var7 = this.k.nextLong() / 2L * 2L + 1L;
        long var9 = this.k.nextLong() / 2L * 2L + 1L;

        this.k.setSeed(var2 * var7 + var3 * var9 ^ this.p.n());
        boolean var11 = false;

        if (this.q) {
            this.w.a(this.p, this.k, var2, var3);
            var11 = this.v.a(this.p, this.k, var2, var3);
            this.u.a(this.p, this.k, var2, var3);
        }

        int var12;
        int var13;
        int var14;

        if (!var11 && this.k.nextInt(4) == 0) {
            var12 = var4 + this.k.nextInt(16) + 8;
            var13 = this.k.nextInt(128);
            var14 = var5 + this.k.nextInt(16) + 8;
            (new OWorldGenLakes(OBlock.B.bO)).a(this.p, this.k, var12, var13, var14);
        }

        if (!var11 && this.k.nextInt(8) == 0) {
            var12 = var4 + this.k.nextInt(16) + 8;
            var13 = this.k.nextInt(this.k.nextInt(120) + 8);
            var14 = var5 + this.k.nextInt(16) + 8;
            if (var13 < 63 || this.k.nextInt(10) == 0) {
                (new OWorldGenLakes(OBlock.D.bO)).a(this.p, this.k, var12, var13, var14);
            }
        }

        for (var12 = 0; var12 < 8; ++var12) {
            var13 = var4 + this.k.nextInt(16) + 8;
            var14 = this.k.nextInt(128);
            int var15 = var5 + this.k.nextInt(16) + 8;

            if ((new OWorldGenDungeons()).a(this.p, this.k, var13, var14, var15)) {
                ;
            }
        }

        var6.a(this.p, this.k, var4, var5);
        OSpawnerAnimals.a(this.p, var6, var4 + 8, var5 + 8, 16, 16, this.k);
        var4 += 8;
        var5 += 8;

        for (var12 = 0; var12 < 16; ++var12) {
            for (var13 = 0; var13 < 16; ++var13) {
                var14 = this.p.f(var4 + var12, var5 + var13);
                if (this.p.s(var12 + var4, var14 - 1, var13 + var5)) {
                    this.p.e(var12 + var4, var14 - 1, var13 + var5, OBlock.aT.bO);
                }

                if (this.p.u(var12 + var4, var14, var13 + var5)) {
                    this.p.e(var12 + var4, var14, var13 + var5, OBlock.aS.bO);
                }
            }
        }

        OBlockSand.a = false;
    }

    @Override
    public boolean a(boolean var1, OIProgressUpdate var2) {
        return true;
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public boolean b() {
        return true;
    }

    @Override
    public List a(OEnumCreatureType var1, int var2, int var3, int var4) {
        OBiomeGenBase var5 = this.p.a(var2, var4);

        return var5 == null ? null : var5.a(var1);
    }

    @Override
    public OChunkPosition a(OWorld var1, String var2, int var3, int var4, int var5) {
        return "Stronghold".equals(var2) && this.u != null ? this.u.a(var1, var3, var4, var5) : null;
    }
}
