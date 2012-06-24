package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OEntity;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OIntHashMap;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OPath;
import net.minecraft.server.OPathEntity;
import net.minecraft.server.OPathPoint;

public class OPathFinder {

    private OIBlockAccess a;
    private OPath b = new OPath();
    private OIntHashMap c = new OIntHashMap();
    private OPathPoint[] d = new OPathPoint[32];
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;

    public OPathFinder(OIBlockAccess var1, boolean var2, boolean var3, boolean var4, boolean var5) {
        super();
        this.a = var1;
        this.e = var2;
        this.f = var3;
        this.g = var4;
        this.h = var5;
    }

    public OPathEntity a(OEntity var1, OEntity var2, float var3) {
        return this.a(var1, var2.bm, var2.bw.b, var2.bo, var3);
    }

    public OPathEntity a(OEntity var1, int var2, int var3, int var4, float var5) {
        return this.a(var1, (var2 + 0.5F), (var3 + 0.5F), (var4 + 0.5F), var5);
    }

    private OPathEntity a(OEntity var1, double var2, double var4, double var6, float var8) {
        this.b.a();
        this.c.a();
        boolean var9 = this.g;
        int var10 = OMathHelper.b(var1.bw.b + 0.5D);
        if (this.h && var1.aU()) {
            var10 = (int) var1.bw.b;

            for (int var11 = this.a.a(OMathHelper.b(var1.bm), var10, OMathHelper.b(var1.bo)); var11 == OBlock.A.bO || var11 == OBlock.B.bO; var11 = this.a.a(OMathHelper.b(var1.bm), var10, OMathHelper.b(var1.bo))) {
                ++var10;
            }

            var9 = this.g;
            this.g = false;
        } else {
            var10 = OMathHelper.b(var1.bw.b + 0.5D);
        }

        OPathPoint var15 = this.a(OMathHelper.b(var1.bw.a), var10, OMathHelper.b(var1.bw.c));
        OPathPoint var12 = this.a(OMathHelper.b(var2 - (var1.bG / 2.0F)), OMathHelper.b(var4), OMathHelper.b(var6 - (var1.bG / 2.0F)));
        OPathPoint var13 = new OPathPoint(OMathHelper.d(var1.bG + 1.0F), OMathHelper.d(var1.bH + 1.0F), OMathHelper.d(var1.bG + 1.0F));
        OPathEntity var14 = this.a(var1, var15, var12, var13, var8);
        this.g = var9;
        return var14;
    }

    private OPathEntity a(OEntity var1, OPathPoint var2, OPathPoint var3, OPathPoint var4, float var5) {
        var2.e = 0.0F;
        var2.f = var2.a(var3);
        var2.g = var2.f;
        this.b.a();
        this.b.a(var2);
        OPathPoint var6 = var2;

        while (!this.b.c()) {
            OPathPoint var7 = this.b.b();
            if (var7.equals(var3)) {
                return this.a(var2, var3);
            }

            if (var7.a(var3) < var6.a(var3)) {
                var6 = var7;
            }

            var7.i = true;
            int var8 = this.b(var1, var7, var4, var3, var5);

            for (int var9 = 0; var9 < var8; ++var9) {
                OPathPoint var10 = this.d[var9];
                float var11 = var7.e + var7.a(var10);
                if (!var10.a() || var11 < var10.e) {
                    var10.h = var7;
                    var10.e = var11;
                    var10.f = var10.a(var3);
                    if (var10.a()) {
                        this.b.a(var10, var10.e + var10.f);
                    } else {
                        var10.g = var10.e + var10.f;
                        this.b.a(var10);
                    }
                }
            }
        }

        if (var6 == var2) {
            return null;
        } else {
            return this.a(var2, var6);
        }
    }

    private int b(OEntity var1, OPathPoint var2, OPathPoint var3, OPathPoint var4, float var5) {
        int var6 = 0;
        byte var7 = 0;
        if (this.a(var1, var2.a, var2.b + 1, var2.c, var3) == 1) {
            var7 = 1;
        }

        OPathPoint var8 = this.a(var1, var2.a, var2.b, var2.c + 1, var3, var7);
        OPathPoint var9 = this.a(var1, var2.a - 1, var2.b, var2.c, var3, var7);
        OPathPoint var10 = this.a(var1, var2.a + 1, var2.b, var2.c, var3, var7);
        OPathPoint var11 = this.a(var1, var2.a, var2.b, var2.c - 1, var3, var7);
        if (var8 != null && !var8.i && var8.a(var4) < var5) {
            this.d[var6++] = var8;
        }

        if (var9 != null && !var9.i && var9.a(var4) < var5) {
            this.d[var6++] = var9;
        }

        if (var10 != null && !var10.i && var10.a(var4) < var5) {
            this.d[var6++] = var10;
        }

        if (var11 != null && !var11.i && var11.a(var4) < var5) {
            this.d[var6++] = var11;
        }

        return var6;
    }

    private OPathPoint a(OEntity var1, int var2, int var3, int var4, OPathPoint var5, int var6) {
        OPathPoint var7 = null;
        int var8 = this.a(var1, var2, var3, var4, var5);
        if (var8 == 2) {
            return this.a(var2, var3, var4);
        } else {
            if (var8 == 1) {
                var7 = this.a(var2, var3, var4);
            }

            if (var7 == null && var6 > 0 && var8 != -3 && var8 != -4 && this.a(var1, var2, var3 + var6, var4, var5) == 1) {
                var7 = this.a(var2, var3 + var6, var4);
                var3 += var6;
            }

            if (var7 != null) {
                int var9 = 0;
                int var10 = 0;

                while (var3 > 0) {
                    var10 = this.a(var1, var2, var3 - 1, var4, var5);
                    if (this.g && var10 == -1) {
                        return null;
                    }

                    if (var10 != 1) {
                        break;
                    }

                    ++var9;
                    if (var9 >= 4) {
                        return null;
                    }

                    --var3;
                    if (var3 > 0) {
                        var7 = this.a(var2, var3, var4);
                    }
                }

                if (var10 == -2) {
                    return null;
                }
            }

            return var7;
        }
    }

    private final OPathPoint a(int var1, int var2, int var3) {
        int var4 = OPathPoint.a(var1, var2, var3);
        OPathPoint var5 = (OPathPoint) this.c.a(var4);
        if (var5 == null) {
            var5 = new OPathPoint(var1, var2, var3);
            this.c.a(var4, var5);
        }

        return var5;
    }

    private int a(OEntity var1, int var2, int var3, int var4, OPathPoint var5) {
        boolean var6 = false;

        for (int var7 = var2; var7 < var2 + var5.a; ++var7) {
            for (int var8 = var3; var8 < var3 + var5.b; ++var8) {
                for (int var9 = var4; var9 < var4 + var5.c; ++var9) {
                    int var10 = this.a.a(var7, var8, var9);
                    if (var10 > 0) {
                        if (var10 == OBlock.bk.bO) {
                            var6 = true;
                        } else if (var10 != OBlock.A.bO && var10 != OBlock.B.bO) {
                            if (!this.e && var10 == OBlock.aE.bO) {
                                return 0;
                            }
                        } else {
                            if (this.g) {
                                return -1;
                            }

                            var6 = true;
                        }

                        OBlock var11 = OBlock.m[var10];
                        if (!var11.b(this.a, var7, var8, var9) && (!this.f || var10 != OBlock.aE.bO)) {
                            if (var10 == OBlock.aZ.bO || var10 == OBlock.bv.bO) {
                                return -3;
                            }

                            if (var10 == OBlock.bk.bO) {
                                return -4;
                            }

                            OMaterial var12 = var11.cd;
                            if (var12 != OMaterial.h) {
                                return 0;
                            }

                            if (!var1.aV()) {
                                return -2;
                            }
                        }
                    }
                }
            }
        }

        return var6 ? 2 : 1;
    }

    private OPathEntity a(OPathPoint var1, OPathPoint var2) {
        int var3 = 1;

        OPathPoint var4;
        for (var4 = var2; var4.h != null; var4 = var4.h) {
            ++var3;
        }

        OPathPoint[] var5 = new OPathPoint[var3];
        var4 = var2;
        --var3;

        for (var5[var3] = var2; var4.h != null; var5[var3] = var4) {
            var4 = var4.h;
            --var3;
        }

        return new OPathEntity(var5);
    }
}
