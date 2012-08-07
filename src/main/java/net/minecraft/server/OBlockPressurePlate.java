package net.minecraft.server;


import java.util.List;
import java.util.Random;

import net.canarymod.hook.world.RedstoneChangeHook;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEnumMobType;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;


public class OBlockPressurePlate extends OBlock {

    private OEnumMobType a;

    protected OBlockPressurePlate(int var1, int var2, OEnumMobType var3, OMaterial var4) {
        super(var1, var2, var4);
        this.a = var3;
        this.a(true);
        float var5 = 0.0625F;

        this.a(var5, 0.0F, var5, 1.0F - var5, 0.03125F, 1.0F - var5);
    }

    @Override
    public int d() {
        return 20;
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
    public boolean b(OIBlockAccess var1, int var2, int var3, int var4) {
        return true;
    }

    @Override
    public boolean c(OWorld var1, int var2, int var3, int var4) {
        return var1.e(var2, var3 - 1, var4) || var1.a(var2, var3 - 1, var4) == OBlock.aZ.bO;
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4) {}

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, int var5) {
        boolean var6 = false;

        if (!var1.e(var2, var3 - 1, var4) && var1.a(var2, var3 - 1, var4) != OBlock.aZ.bO) {
            var6 = true;
        }

        if (var6) {
            this.b(var1, var2, var3, var4, var1.c(var2, var3, var4), 0);
            var1.e(var2, var3, var4, 0);
        }

    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
        if (!var1.F) {
            if (var1.c(var2, var3, var4) != 0) {
                this.g(var1, var2, var3, var4);
            }
        }
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, OEntity var5) {
        if (!var1.F) {
            if (var1.c(var2, var3, var4) != 1) {
                this.g(var1, var2, var3, var4);
            }
        }
    }

    private void g(OWorld var1, int var2, int var3, int var4) {
        boolean var5 = var1.c(var2, var3, var4) == 1;
        boolean var6 = false;
        float var7 = 0.125F;
        List var8 = null;

        if (this.a == OEnumMobType.a) {
            var8 = var1.b((OEntity) null, OAxisAlignedBB.b((var2 + var7), var3, (var4 + var7), ((var2 + 1) - var7), var3 + 0.25D, ((var4 + 1) - var7)));
        }

        if (this.a == OEnumMobType.b) {
            var8 = var1.a(OEntityLiving.class, OAxisAlignedBB.b((var2 + var7), var3, (var4 + var7), ((var2 + 1) - var7), var3 + 0.25D, ((var4 + 1) - var7)));
        }

        if (this.a == OEnumMobType.c) {
            var8 = var1.a(OEntityPlayer.class, OAxisAlignedBB.b((var2 + var7), var3, (var4 + var7), ((var2 + 1) - var7), var3 + 0.25D, ((var4 + 1) - var7)));
        }

        if (var8.size() > 0) {
            var6 = true;
        }
        
        // CanaryMod controll pressure plate redstone change
        if (var6 != var5) {
            RedstoneChangeHook hook = new RedstoneChangeHook(var1.getCanaryWorld().getBlockAt(var2, var3, var4), var5 ? 1 : 0, var6 ? 1 : 0);

            if (hook.isCanceled()) {
                return; // do nothing
            }
            var6 = hook.getNewLevel() > 0;
        }

        if (var6 && !var5) {
            var1.c(var2, var3, var4, 1);
            var1.h(var2, var3, var4, this.bO);
            var1.h(var2, var3 - 1, var4, this.bO);
            var1.b(var2, var3, var4, var2, var3, var4);
            var1.a(var2 + 0.5D, var3 + 0.1D, var4 + 0.5D, "random.click", 0.3F, 0.6F);
        }

        if (!var6 && var5) {
            var1.c(var2, var3, var4, 0);
            var1.h(var2, var3, var4, this.bO);
            var1.h(var2, var3 - 1, var4, this.bO);
            var1.b(var2, var3, var4, var2, var3, var4);
            var1.a(var2 + 0.5D, var3 + 0.1D, var4 + 0.5D, "random.click", 0.3F, 0.5F);
        }

        if (var6) {
            var1.c(var2, var3, var4, this.bO, this.d());
        }

    }

    @Override
    public void d(OWorld var1, int var2, int var3, int var4) {
        int var5 = var1.c(var2, var3, var4);

        if (var5 > 0) {
            var1.h(var2, var3, var4, this.bO);
            var1.h(var2, var3 - 1, var4, this.bO);
        }

        super.d(var1, var2, var3, var4);
    }

    @Override
    public void a(OIBlockAccess var1, int var2, int var3, int var4) {
        boolean var5 = var1.c(var2, var3, var4) == 1;
        float var6 = 0.0625F;

        if (var5) {
            this.a(var6, 0.0F, var6, 1.0F - var6, 0.03125F, 1.0F - var6);
        } else {
            this.a(var6, 0.0F, var6, 1.0F - var6, 0.0625F, 1.0F - var6);
        }

    }

    @Override
    public boolean a(OIBlockAccess var1, int var2, int var3, int var4, int var5) {
        return var1.c(var2, var3, var4) > 0;
    }

    @Override
    public boolean d(OWorld var1, int var2, int var3, int var4, int var5) {
        return var1.c(var2, var3, var4) == 0 ? false : var5 == 1;
    }

    @Override
    public boolean e() {
        return true;
    }

    @Override
    public void f() {
        float var1 = 0.5F;
        float var2 = 0.125F;
        float var3 = 0.5F;

        this.a(0.5F - var1, 0.5F - var2, 0.5F - var3, 0.5F + var1, 0.5F + var2, 0.5F + var3);
    }

    @Override
    public int g() {
        return 1;
    }
}
