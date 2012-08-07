package net.minecraft.server;


import java.util.Random;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntity;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OVec3D;
import net.minecraft.server.OWorld;


public abstract class OBlockFluid extends OBlock {

    protected OBlockFluid(int var1, OMaterial var2) {
        super(var1, (var2 == OMaterial.h ? 14 : 12) * 16 + 13, var2);
        float var3 = 0.0F;
        float var4 = 0.0F;

        this.a(0.0F + var4, 0.0F + var3, 0.0F + var4, 1.0F + var4, 1.0F + var3, 1.0F + var4);
        this.a(true);
    }

    @Override
    public boolean b(OIBlockAccess var1, int var2, int var3, int var4) {
        return this.cd != OMaterial.h;
    }

    public static float d(int var0) {
        if (var0 >= 8) {
            var0 = 0;
        }

        float var1 = (var0 + 1) / 9.0F;

        return var1;
    }

    @Override
    public int a(int var1) {
        return var1 != 0 && var1 != 1 ? this.bN + 1 : this.bN;
    }

    protected int g(OWorld var1, int var2, int var3, int var4) {
        return var1.d(var2, var3, var4) != this.cd ? -1 : var1.c(var2, var3, var4);
    }

    protected int c(OIBlockAccess var1, int var2, int var3, int var4) {
        if (var1.d(var2, var3, var4) != this.cd) {
            return -1;
        } else {
            int var5 = var1.c(var2, var3, var4);

            if (var5 >= 8) {
                var5 = 0;
            }

            return var5;
        }
    }

    @Override
    public boolean b() {
        return false;
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public boolean a(int var1, boolean var2) {
        return var2 && var1 == 0;
    }

    @Override
    public boolean b(OIBlockAccess var1, int var2, int var3, int var4, int var5) {
        OMaterial var6 = var1.d(var2, var3, var4);

        return var6 == this.cd ? false : (var5 == 1 ? true : (var6 == OMaterial.u ? false : super.b(var1, var2, var3, var4, var5)));
    }

    @Override
    public OAxisAlignedBB e(OWorld var1, int var2, int var3, int var4) {
        return null;
    }

    @Override
    public int c() {
        return 4;
    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return 0;
    }

    @Override
    public int a(Random var1) {
        return 0;
    }

    private OVec3D d(OIBlockAccess var1, int var2, int var3, int var4) {
        OVec3D var5 = OVec3D.b(0.0D, 0.0D, 0.0D);
        int var6 = this.c(var1, var2, var3, var4);

        for (int var7 = 0; var7 < 4; ++var7) {
            int var8 = var2;
            int var10 = var4;

            if (var7 == 0) {
                var8 = var2 - 1;
            }

            if (var7 == 1) {
                var10 = var4 - 1;
            }

            if (var7 == 2) {
                ++var8;
            }

            if (var7 == 3) {
                ++var10;
            }

            int var11 = this.c(var1, var8, var3, var10);
            int var12;

            if (var11 < 0) {
                if (!var1.d(var8, var3, var10).c()) {
                    var11 = this.c(var1, var8, var3 - 1, var10);
                    if (var11 >= 0) {
                        var12 = var11 - (var6 - 8);
                        var5 = var5.c(((var8 - var2) * var12), ((var3 - var3) * var12), ((var10 - var4) * var12));
                    }
                }
            } else if (var11 >= 0) {
                var12 = var11 - var6;
                var5 = var5.c(((var8 - var2) * var12), ((var3 - var3) * var12), ((var10 - var4) * var12));
            }
        }

        if (var1.c(var2, var3, var4) >= 8) {
            boolean var13 = false;

            if (var13 || this.b(var1, var2, var3, var4 - 1, 2)) {
                var13 = true;
            }

            if (var13 || this.b(var1, var2, var3, var4 + 1, 3)) {
                var13 = true;
            }

            if (var13 || this.b(var1, var2 - 1, var3, var4, 4)) {
                var13 = true;
            }

            if (var13 || this.b(var1, var2 + 1, var3, var4, 5)) {
                var13 = true;
            }

            if (var13 || this.b(var1, var2, var3 + 1, var4 - 1, 2)) {
                var13 = true;
            }

            if (var13 || this.b(var1, var2, var3 + 1, var4 + 1, 3)) {
                var13 = true;
            }

            if (var13 || this.b(var1, var2 - 1, var3 + 1, var4, 4)) {
                var13 = true;
            }

            if (var13 || this.b(var1, var2 + 1, var3 + 1, var4, 5)) {
                var13 = true;
            }

            if (var13) {
                var5 = var5.b().c(0.0D, -6.0D, 0.0D);
            }
        }

        var5 = var5.b();
        return var5;
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, OEntity var5, OVec3D var6) {
        OVec3D var7 = this.d((OIBlockAccess) var1, var2, var3, var4);

        var6.a += var7.a;
        var6.b += var7.b;
        var6.c += var7.c;
    }

    @Override
    public int d() {
        return this.cd == OMaterial.g ? 5 : (this.cd == OMaterial.h ? 30 : 0);
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
        super.a(var1, var2, var3, var4, var5);
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4) {
        this.i(var1, var2, var3, var4);
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, int var5) {
        this.i(var1, var2, var3, var4);
    }

    private void i(OWorld var1, int var2, int var3, int var4) {
        if (var1.a(var2, var3, var4) == this.bO) {
            if (this.cd == OMaterial.h) {
                boolean var5 = false;

                if (var5 || var1.d(var2, var3, var4 - 1) == OMaterial.g) {
                    var5 = true;
                }

                if (var5 || var1.d(var2, var3, var4 + 1) == OMaterial.g) {
                    var5 = true;
                }

                if (var5 || var1.d(var2 - 1, var3, var4) == OMaterial.g) {
                    var5 = true;
                }

                if (var5 || var1.d(var2 + 1, var3, var4) == OMaterial.g) {
                    var5 = true;
                }

                if (var5 || var1.d(var2, var3 + 1, var4) == OMaterial.g) {
                    var5 = true;
                }

                if (var5) {
                    int var6 = var1.c(var2, var3, var4);

                    if (var6 == 0) {
                        var1.e(var2, var3, var4, OBlock.ap.bO);
                    } else if (var6 <= 4) {
                        var1.e(var2, var3, var4, OBlock.w.bO);
                    }

                    this.h(var1, var2, var3, var4);
                }
            }

        }
    }

    protected void h(OWorld var1, int var2, int var3, int var4) {
        var1.a((var2 + 0.5F), (var3 + 0.5F), (var4 + 0.5F), "random.fizz", 0.5F, 2.6F + (var1.r.nextFloat() - var1.r.nextFloat()) * 0.8F);

        for (int var5 = 0; var5 < 8; ++var5) {
            var1.a("largesmoke", var2 + Math.random(), var3 + 1.2D, var4 + Math.random(), 0.0D, 0.0D, 0.0D);
        }

    }
}
