package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import net.canarymod.Canary;
import net.canarymod.hook.world.RedstoneChangeHook;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OChunkPosition;
import net.minecraft.server.ODirection;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OItem;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;

public class OBlockRedstoneWire extends OBlock {

    private boolean a = true;
    private Set b = new HashSet();

    public OBlockRedstoneWire(int var1, int var2) {
        super(var1, var2, OMaterial.p);
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
    }

    @Override
    public int a(int var1, int var2) {
        return this.bN;
    }

    @Override
    public OAxisAlignedBB e(OWorld var1, int var2, int var3, int var4) {
        return null;
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public boolean b() {
        return false;
    }

    @Override
    public int c() {
        return 5;
    }

    @Override
    public boolean c(OWorld var1, int var2, int var3, int var4) {
        return var1.e(var2, var3 - 1, var4) || var1.a(var2, var3 - 1, var4) == OBlock.bd.bO;
    }

    private void g(OWorld var1, int var2, int var3, int var4) {
        this.a(var1, var2, var3, var4, var2, var3, var4);
        ArrayList var5 = new ArrayList(this.b);
        this.b.clear();

        for (int var6 = 0; var6 < var5.size(); ++var6) {
            OChunkPosition var7 = (OChunkPosition) var5.get(var6);
            var1.h(var7.a, var7.b, var7.c, this.bO);
        }

    }

    private void a(OWorld var1, int var2, int var3, int var4, int var5, int var6, int var7) {
        int var8 = var1.c(var2, var3, var4);
        int var9 = 0;
        this.a = false;
        boolean var10 = var1.x(var2, var3, var4);
        this.a = true;
        int var11;
        int var12;
        int var13;
        if (var10) {
            var9 = 15;
        } else {
            for (var11 = 0; var11 < 4; ++var11) {
                var12 = var2;
                var13 = var4;
                if (var11 == 0) {
                    var12 = var2 - 1;
                }

                if (var11 == 1) {
                    ++var12;
                }

                if (var11 == 2) {
                    var13 = var4 - 1;
                }

                if (var11 == 3) {
                    ++var13;
                }

                if (var12 != var5 || var3 != var6 || var13 != var7) {
                    var9 = this.f(var1, var12, var3, var13, var9);
                }

                if (var1.e(var12, var3, var13) && !var1.e(var2, var3 + 1, var4)) {
                    if (var12 != var5 || var3 + 1 != var6 || var13 != var7) {
                        var9 = this.f(var1, var12, var3 + 1, var13, var9);
                    }
                } else if (!var1.e(var12, var3, var13) && (var12 != var5 || var3 - 1 != var6 || var13 != var7)) {
                    var9 = this.f(var1, var12, var3 - 1, var13, var9);
                }
            }

            if (var9 > 0) {
                --var9;
            } else {
                var9 = 0;
            }
        }

        if (var8 != var9) {
            RedstoneChangeHook hook = new RedstoneChangeHook(var1.getCanaryDimension().getBlockAt(var2, var3, var4), var8, var9);
            Canary.hooks().callHook(hook);
            if(hook.isCanceled()) {
                return;
            }
            var8 = hook.getNewLevel();
            
            if(var8 == var9) {
                return;
            }
            
            var1.o = true;
            var1.c(var2, var3, var4, var9);
            var1.b(var2, var3, var4, var2, var3, var4);
            var1.o = false;

            for (var11 = 0; var11 < 4; ++var11) {
                var12 = var2;
                var13 = var4;
                int var14 = var3 - 1;
                if (var11 == 0) {
                    var12 = var2 - 1;
                }

                if (var11 == 1) {
                    ++var12;
                }

                if (var11 == 2) {
                    var13 = var4 - 1;
                }

                if (var11 == 3) {
                    ++var13;
                }

                if (var1.e(var12, var3, var13)) {
                    var14 += 2;
                }

                boolean var15 = false;
                int var16 = this.f(var1, var12, var3, var13, -1);
                var9 = var1.c(var2, var3, var4);
                if (var9 > 0) {
                    --var9;
                }

                if (var16 >= 0 && var16 != var9) {
                    this.a(var1, var12, var3, var13, var2, var3, var4);
                }

                var16 = this.f(var1, var12, var14, var13, -1);
                var9 = var1.c(var2, var3, var4);
                if (var9 > 0) {
                    --var9;
                }

                if (var16 >= 0 && var16 != var9) {
                    this.a(var1, var12, var14, var13, var2, var3, var4);
                }
            }

            if (var8 < var9 || var9 == 0) {
                this.b.add(new OChunkPosition(var2, var3, var4));
                this.b.add(new OChunkPosition(var2 - 1, var3, var4));
                this.b.add(new OChunkPosition(var2 + 1, var3, var4));
                this.b.add(new OChunkPosition(var2, var3 - 1, var4));
                this.b.add(new OChunkPosition(var2, var3 + 1, var4));
                this.b.add(new OChunkPosition(var2, var3, var4 - 1));
                this.b.add(new OChunkPosition(var2, var3, var4 + 1));
            }
        }

    }

    private void h(OWorld var1, int var2, int var3, int var4) {
        if (var1.a(var2, var3, var4) == this.bO) {
            var1.h(var2, var3, var4, this.bO);
            var1.h(var2 - 1, var3, var4, this.bO);
            var1.h(var2 + 1, var3, var4, this.bO);
            var1.h(var2, var3, var4 - 1, this.bO);
            var1.h(var2, var3, var4 + 1, this.bO);
            var1.h(var2, var3 - 1, var4, this.bO);
            var1.h(var2, var3 + 1, var4, this.bO);
        }
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4) {
        super.a(var1, var2, var3, var4);
        if (!var1.F) {
            this.g(var1, var2, var3, var4);
            var1.h(var2, var3 + 1, var4, this.bO);
            var1.h(var2, var3 - 1, var4, this.bO);
            this.h(var1, var2 - 1, var3, var4);
            this.h(var1, var2 + 1, var3, var4);
            this.h(var1, var2, var3, var4 - 1);
            this.h(var1, var2, var3, var4 + 1);
            if (var1.e(var2 - 1, var3, var4)) {
                this.h(var1, var2 - 1, var3 + 1, var4);
            } else {
                this.h(var1, var2 - 1, var3 - 1, var4);
            }

            if (var1.e(var2 + 1, var3, var4)) {
                this.h(var1, var2 + 1, var3 + 1, var4);
            } else {
                this.h(var1, var2 + 1, var3 - 1, var4);
            }

            if (var1.e(var2, var3, var4 - 1)) {
                this.h(var1, var2, var3 + 1, var4 - 1);
            } else {
                this.h(var1, var2, var3 - 1, var4 - 1);
            }

            if (var1.e(var2, var3, var4 + 1)) {
                this.h(var1, var2, var3 + 1, var4 + 1);
            } else {
                this.h(var1, var2, var3 - 1, var4 + 1);
            }

        }
    }

    @Override
    public void d(OWorld var1, int var2, int var3, int var4) {
        super.d(var1, var2, var3, var4);
        if (!var1.F) {
            var1.h(var2, var3 + 1, var4, this.bO);
            var1.h(var2, var3 - 1, var4, this.bO);
            var1.h(var2 + 1, var3, var4, this.bO);
            var1.h(var2 - 1, var3, var4, this.bO);
            var1.h(var2, var3, var4 + 1, this.bO);
            var1.h(var2, var3, var4 - 1, this.bO);
            this.g(var1, var2, var3, var4);
            this.h(var1, var2 - 1, var3, var4);
            this.h(var1, var2 + 1, var3, var4);
            this.h(var1, var2, var3, var4 - 1);
            this.h(var1, var2, var3, var4 + 1);
            if (var1.e(var2 - 1, var3, var4)) {
                this.h(var1, var2 - 1, var3 + 1, var4);
            } else {
                this.h(var1, var2 - 1, var3 - 1, var4);
            }

            if (var1.e(var2 + 1, var3, var4)) {
                this.h(var1, var2 + 1, var3 + 1, var4);
            } else {
                this.h(var1, var2 + 1, var3 - 1, var4);
            }

            if (var1.e(var2, var3, var4 - 1)) {
                this.h(var1, var2, var3 + 1, var4 - 1);
            } else {
                this.h(var1, var2, var3 - 1, var4 - 1);
            }

            if (var1.e(var2, var3, var4 + 1)) {
                this.h(var1, var2, var3 + 1, var4 + 1);
            } else {
                this.h(var1, var2, var3 - 1, var4 + 1);
            }

        }
    }

    private int f(OWorld var1, int var2, int var3, int var4, int var5) {
        if (var1.a(var2, var3, var4) != this.bO) {
            return var5;
        } else {
            int var6 = var1.c(var2, var3, var4);
            return var6 > var5 ? var6 : var5;
        }
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, int var5) {
        if (!var1.F) {
            int var6 = var1.c(var2, var3, var4);
            boolean var7 = this.c(var1, var2, var3, var4);
            if (!var7) {
                this.b(var1, var2, var3, var4, var6, 0);
                var1.e(var2, var3, var4, 0);
            } else {
                this.g(var1, var2, var3, var4);
            }

            super.a(var1, var2, var3, var4, var5);
        }
    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return OItem.aB.bP;
    }

    @Override
    public boolean d(OWorld var1, int var2, int var3, int var4, int var5) {
        return !this.a ? false : this.a((OIBlockAccess) var1, var2, var3, var4, var5);
    }

    @Override
    public boolean a(OIBlockAccess var1, int var2, int var3, int var4, int var5) {
        if (!this.a) {
            return false;
        } else if (var1.c(var2, var3, var4) == 0) {
            return false;
        } else if (var5 == 1) {
            return true;
        } else {
            boolean var6 = d(var1, var2 - 1, var3, var4, 1) || !var1.e(var2 - 1, var3, var4) && d(var1, var2 - 1, var3 - 1, var4, -1);
            boolean var7 = d(var1, var2 + 1, var3, var4, 3) || !var1.e(var2 + 1, var3, var4) && d(var1, var2 + 1, var3 - 1, var4, -1);
            boolean var8 = d(var1, var2, var3, var4 - 1, 2) || !var1.e(var2, var3, var4 - 1) && d(var1, var2, var3 - 1, var4 - 1, -1);
            boolean var9 = d(var1, var2, var3, var4 + 1, 0) || !var1.e(var2, var3, var4 + 1) && d(var1, var2, var3 - 1, var4 + 1, -1);
            if (!var1.e(var2, var3 + 1, var4)) {
                if (var1.e(var2 - 1, var3, var4) && d(var1, var2 - 1, var3 + 1, var4, -1)) {
                    var6 = true;
                }

                if (var1.e(var2 + 1, var3, var4) && d(var1, var2 + 1, var3 + 1, var4, -1)) {
                    var7 = true;
                }

                if (var1.e(var2, var3, var4 - 1) && d(var1, var2, var3 + 1, var4 - 1, -1)) {
                    var8 = true;
                }

                if (var1.e(var2, var3, var4 + 1) && d(var1, var2, var3 + 1, var4 + 1, -1)) {
                    var9 = true;
                }
            }

            return !var8 && !var7 && !var6 && !var9 && var5 >= 2 && var5 <= 5 ? true : (var5 == 2 && var8 && !var6 && !var7 ? true : (var5 == 3 && var9 && !var6 && !var7 ? true : (var5 == 4 && var6 && !var8 && !var9 ? true : var5 == 5 && var7 && !var8 && !var9)));
        }
    }

    @Override
    public boolean e() {
        return this.a;
    }

    public static boolean c(OIBlockAccess var0, int var1, int var2, int var3, int var4) {
        int var5 = var0.a(var1, var2, var3);
        if (var5 == OBlock.av.bO) {
            return true;
        } else if (var5 == 0) {
            return false;
        } else if (var5 != OBlock.bh.bO && var5 != OBlock.bi.bO) {
            return OBlock.m[var5].e() && var4 != -1;
        } else {
            int var6 = var0.c(var1, var2, var3);
            return var4 == (var6 & 3) || var4 == ODirection.e[var6 & 3];
        }
    }

    public static boolean d(OIBlockAccess var0, int var1, int var2, int var3, int var4) {
        if (c(var0, var1, var2, var3, var4)) {
            return true;
        } else {
            int var5 = var0.a(var1, var2, var3);
            if (var5 == OBlock.bi.bO) {
                int var6 = var0.c(var1, var2, var3);
                return var4 == (var6 & 3);
            } else {
                return false;
            }
        }
    }
}
