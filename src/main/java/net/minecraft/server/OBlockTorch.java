package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockStairs;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OMovingObjectPosition;
import net.minecraft.server.OVec3D;
import net.minecraft.server.OWorld;

public class OBlockTorch extends OBlock {

    protected OBlockTorch(int var1, int var2) {
        super(var1, var2, OMaterial.p);
        this.a(true);
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
        return 2;
    }

    private boolean g(OWorld var1, int var2, int var3, int var4) {
        if (var1.b(var2, var3, var4, true)) {
            return true;
        } else {
            int var5 = var1.a(var2, var3, var4);
            if (var5 != OBlock.aZ.bO && var5 != OBlock.bB.bO && var5 != OBlock.M.bO) {
                if (OBlock.m[var5] != null && OBlock.m[var5] instanceof OBlockStairs) {
                    int var6 = var1.c(var2, var3, var4);
                    if ((4 & var6) != 0) {
                        return true;
                    }
                }

                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    public boolean c(OWorld var1, int var2, int var3, int var4) {
        return var1.b(var2 - 1, var3, var4, true) ? true : (var1.b(var2 + 1, var3, var4, true) ? true : (var1.b(var2, var3, var4 - 1, true) ? true : (var1.b(var2, var3, var4 + 1, true) ? true : this.g(var1, var2, var3 - 1, var4))));
    }

    @Override
    public void e(OWorld var1, int var2, int var3, int var4, int var5) {
        int var6 = var1.c(var2, var3, var4);
        if (var5 == 1 && this.g(var1, var2, var3 - 1, var4)) {
            var6 = 5;
        }

        if (var5 == 2 && var1.b(var2, var3, var4 + 1, true)) {
            var6 = 4;
        }

        if (var5 == 3 && var1.b(var2, var3, var4 - 1, true)) {
            var6 = 3;
        }

        if (var5 == 4 && var1.b(var2 + 1, var3, var4, true)) {
            var6 = 2;
        }

        if (var5 == 5 && var1.b(var2 - 1, var3, var4, true)) {
            var6 = 1;
        }

        var1.c(var2, var3, var4, var6);
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
        super.a(var1, var2, var3, var4, var5);
        if (var1.c(var2, var3, var4) == 0) {
            this.a(var1, var2, var3, var4);
        }

    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4) {
        if (var1.b(var2 - 1, var3, var4, true)) {
            var1.c(var2, var3, var4, 1);
        } else if (var1.b(var2 + 1, var3, var4, true)) {
            var1.c(var2, var3, var4, 2);
        } else if (var1.b(var2, var3, var4 - 1, true)) {
            var1.c(var2, var3, var4, 3);
        } else if (var1.b(var2, var3, var4 + 1, true)) {
            var1.c(var2, var3, var4, 4);
        } else if (this.g(var1, var2, var3 - 1, var4)) {
            var1.c(var2, var3, var4, 5);
        }

        this.h(var1, var2, var3, var4);
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, int var5) {
        if (this.h(var1, var2, var3, var4)) {
            int var6 = var1.c(var2, var3, var4);
            boolean var7 = false;
            if (!var1.b(var2 - 1, var3, var4, true) && var6 == 1) {
                var7 = true;
            }

            if (!var1.b(var2 + 1, var3, var4, true) && var6 == 2) {
                var7 = true;
            }

            if (!var1.b(var2, var3, var4 - 1, true) && var6 == 3) {
                var7 = true;
            }

            if (!var1.b(var2, var3, var4 + 1, true) && var6 == 4) {
                var7 = true;
            }

            if (!this.g(var1, var2, var3 - 1, var4) && var6 == 5) {
                var7 = true;
            }

            if (var7) {
                this.b(var1, var2, var3, var4, var1.c(var2, var3, var4), 0);
                var1.e(var2, var3, var4, 0);
            }
        }

    }

    private boolean h(OWorld var1, int var2, int var3, int var4) {
        if (!this.c(var1, var2, var3, var4)) {
            if (var1.a(var2, var3, var4) == this.bO) {
                this.b(var1, var2, var3, var4, var1.c(var2, var3, var4), 0);
                var1.e(var2, var3, var4, 0);
            }

            return false;
        } else {
            return true;
        }
    }

    @Override
    public OMovingObjectPosition a(OWorld var1, int var2, int var3, int var4, OVec3D var5, OVec3D var6) {
        int var7 = var1.c(var2, var3, var4) & 7;
        float var8 = 0.15F;
        if (var7 == 1) {
            this.a(0.0F, 0.2F, 0.5F - var8, var8 * 2.0F, 0.8F, 0.5F + var8);
        } else if (var7 == 2) {
            this.a(1.0F - var8 * 2.0F, 0.2F, 0.5F - var8, 1.0F, 0.8F, 0.5F + var8);
        } else if (var7 == 3) {
            this.a(0.5F - var8, 0.2F, 0.0F, 0.5F + var8, 0.8F, var8 * 2.0F);
        } else if (var7 == 4) {
            this.a(0.5F - var8, 0.2F, 1.0F - var8 * 2.0F, 0.5F + var8, 0.8F, 1.0F);
        } else {
            var8 = 0.1F;
            this.a(0.5F - var8, 0.0F, 0.5F - var8, 0.5F + var8, 0.6F, 0.5F + var8);
        }

        return super.a(var1, var2, var3, var4, var5, var6);
    }
}
