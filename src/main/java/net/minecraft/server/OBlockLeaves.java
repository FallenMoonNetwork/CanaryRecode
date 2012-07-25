package net.minecraft.server;

import java.util.Random;

import net.canarymod.hook.world.LeafDecayHook;
import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockLeavesBase;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OStatList;
import net.minecraft.server.OWorld;

public class OBlockLeaves extends OBlockLeavesBase {

    private int c;
    int[] a;

    protected OBlockLeaves(int var1, int var2) {
        super(var1, var2, OMaterial.i, false);
        this.c = var2;
        this.a(true);
    }

    @Override
    public void d(OWorld var1, int var2, int var3, int var4) {
        byte var5 = 1;
        int var6 = var5 + 1;
        if (var1.a(var2 - var6, var3 - var6, var4 - var6, var2 + var6, var3 + var6, var4 + var6)) {
            for (int var7 = -var5; var7 <= var5; ++var7) {
                for (int var8 = -var5; var8 <= var5; ++var8) {
                    for (int var9 = -var5; var9 <= var5; ++var9) {
                        int var10 = var1.a(var2 + var7, var3 + var8, var4 + var9);
                        if (var10 == OBlock.K.bO) {
                            int var11 = var1.c(var2 + var7, var3 + var8, var4 + var9);
                            var1.d(var2 + var7, var3 + var8, var4 + var9, var11 | 8);
                        }
                    }
                }
            }
        }

    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
        if (!var1.F) {
            int var6 = var1.c(var2, var3, var4);
            if ((var6 & 8) != 0 && (var6 & 4) == 0) {
                byte var7 = 4;
                int var8 = var7 + 1;
                byte var9 = 32;
                int var10 = var9 * var9;
                int var11 = var9 / 2;
                if (this.a == null) {
                    this.a = new int[var9 * var9 * var9];
                }

                int var12;
                if (var1.a(var2 - var8, var3 - var8, var4 - var8, var2 + var8, var3 + var8, var4 + var8)) {
                    int var13;
                    int var14;
                    int var15;
                    for (var12 = -var7; var12 <= var7; ++var12) {
                        for (var13 = -var7; var13 <= var7; ++var13) {
                            for (var14 = -var7; var14 <= var7; ++var14) {
                                var15 = var1.a(var2 + var12, var3 + var13, var4 + var14);
                                if (var15 == OBlock.J.bO) {
                                    this.a[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = 0;
                                } else if (var15 == OBlock.K.bO) {
                                    this.a[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = -2;
                                } else {
                                    this.a[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = -1;
                                }
                            }
                        }
                    }

                    for (var12 = 1; var12 <= 4; ++var12) {
                        for (var13 = -var7; var13 <= var7; ++var13) {
                            for (var14 = -var7; var14 <= var7; ++var14) {
                                for (var15 = -var7; var15 <= var7; ++var15) {
                                    if (this.a[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11] == var12 - 1) {
                                        if (this.a[(var13 + var11 - 1) * var10 + (var14 + var11) * var9 + var15 + var11] == -2) {
                                            this.a[(var13 + var11 - 1) * var10 + (var14 + var11) * var9 + var15 + var11] = var12;
                                        }

                                        if (this.a[(var13 + var11 + 1) * var10 + (var14 + var11) * var9 + var15 + var11] == -2) {
                                            this.a[(var13 + var11 + 1) * var10 + (var14 + var11) * var9 + var15 + var11] = var12;
                                        }

                                        if (this.a[(var13 + var11) * var10 + (var14 + var11 - 1) * var9 + var15 + var11] == -2) {
                                            this.a[(var13 + var11) * var10 + (var14 + var11 - 1) * var9 + var15 + var11] = var12;
                                        }

                                        if (this.a[(var13 + var11) * var10 + (var14 + var11 + 1) * var9 + var15 + var11] == -2) {
                                            this.a[(var13 + var11) * var10 + (var14 + var11 + 1) * var9 + var15 + var11] = var12;
                                        }

                                        if (this.a[(var13 + var11) * var10 + (var14 + var11) * var9 + (var15 + var11 - 1)] == -2) {
                                            this.a[(var13 + var11) * var10 + (var14 + var11) * var9 + (var15 + var11 - 1)] = var12;
                                        }

                                        if (this.a[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11 + 1] == -2) {
                                            this.a[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11 + 1] = var12;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                var12 = this.a[var11 * var10 + var11 * var9 + var11];
                if (var12 >= 0) {
                    var1.d(var2, var3, var4, var6 & -9);
                } else {
                    this.g(var1, var2, var3, var4);
                }
            }

        }
    }

    private void g(OWorld var1, int var2, int var3, int var4) {
        LeafDecayHook hook = new LeafDecayHook(var1.getCanaryWorld().getBlockAt(var2, var3, var4));
        if(!hook.isCanceled()) {
            this.b(var1, var2, var3, var4, var1.c(var2, var3, var4), 0);
            var1.e(var2, var3, var4, 0);
        }
    }

    @Override
    public int a(Random var1) {
        return var1.nextInt(20) == 0 ? 1 : 0;
    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return OBlock.y.bO;
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, int var5, float var6, int var7) {
        if (!var1.F) {
            byte var8 = 20;
            if ((var5 & 3) == 3) {
                var8 = 40;
            }

            if (var1.r.nextInt(var8) == 0) {
                int var9 = this.a(var5, var1.r, var7);
                this.a(var1, var2, var3, var4, new OItemStack(var9, 1, this.c(var5)));
            }

            if ((var5 & 3) == 0 && var1.r.nextInt(200) == 0) {
                this.a(var1, var2, var3, var4, new OItemStack(OItem.i, 1, 0));
            }
        }

    }

    @Override
    public void a(OWorld var1, OEntityPlayer var2, int var3, int var4, int var5, int var6) {
        if (!var1.F && var2.U() != null && var2.U().c == OItem.bd.bP) {
            var2.a(OStatList.C[this.bO], 1);
            this.a(var1, var3, var4, var5, new OItemStack(OBlock.K.bO, 1, var6 & 3));
        } else {
            super.a(var1, var2, var3, var4, var5, var6);
        }

    }

    @Override
    protected int c(int var1) {
        return var1 & 3;
    }

    @Override
    public boolean a() {
        return !this.b;
    }

    @Override
    public int a(int var1, int var2) {
        return (var2 & 3) == 1 ? this.bN + 80 : ((var2 & 3) == 3 ? this.bN + 144 : this.bN);
    }

    @Override
    public void b(OWorld var1, int var2, int var3, int var4, OEntity var5) {
        super.b(var1, var2, var3, var4, var5);
    }
}
