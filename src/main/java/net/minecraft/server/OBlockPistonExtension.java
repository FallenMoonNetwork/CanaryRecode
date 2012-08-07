package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockPistonBase;
import net.minecraft.server.OFacing;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;

public class OBlockPistonExtension extends OBlock {

    private int a = -1;

    public OBlockPistonExtension(int var1, int var2) {
        super(var1, var2, OMaterial.E);
        this.a(h);
        this.c(0.5F);
    }

    @Override
    public void d(OWorld var1, int var2, int var3, int var4) {
        super.d(var1, var2, var3, var4);
        int var5 = var1.c(var2, var3, var4);
        int var6 = OFacing.a[b(var5)];
        var2 += OFacing.b[var6];
        var3 += OFacing.c[var6];
        var4 += OFacing.d[var6];
        int var7 = var1.a(var2, var3, var4);
        if (var7 == OBlock.Z.bO || var7 == OBlock.V.bO) {
            var5 = var1.c(var2, var3, var4);
            if (OBlockPistonBase.e(var5)) {
                OBlock.m[var7].b(var1, var2, var3, var4, var5, 0);
                var1.e(var2, var3, var4, 0);
            }
        }

    }

    @Override
    public int a(int var1, int var2) {
        int var3 = b(var2);
        return var1 == var3 ? (this.a >= 0 ? this.a : ((var2 & 8) != 0 ? this.bN - 1 : this.bN)) : (var1 == OFacing.a[var3] ? 107 : 108);
    }

    @Override
    public int c() {
        return 17;
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
    public boolean c(OWorld var1, int var2, int var3, int var4) {
        return false;
    }

    @Override
    public boolean b(OWorld var1, int var2, int var3, int var4, int var5) {
        return false;
    }

    @Override
    public int a(Random var1) {
        return 0;
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, OAxisAlignedBB var5, ArrayList var6) {
        int var7 = var1.c(var2, var3, var4);
        switch (b(var7)) {
        case 0:
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
            super.a(var1, var2, var3, var4, var5, var6);
            this.a(0.375F, 0.25F, 0.375F, 0.625F, 1.0F, 0.625F);
            super.a(var1, var2, var3, var4, var5, var6);
            break;
        case 1:
            this.a(0.0F, 0.75F, 0.0F, 1.0F, 1.0F, 1.0F);
            super.a(var1, var2, var3, var4, var5, var6);
            this.a(0.375F, 0.0F, 0.375F, 0.625F, 0.75F, 0.625F);
            super.a(var1, var2, var3, var4, var5, var6);
            break;
        case 2:
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.25F);
            super.a(var1, var2, var3, var4, var5, var6);
            this.a(0.25F, 0.375F, 0.25F, 0.75F, 0.625F, 1.0F);
            super.a(var1, var2, var3, var4, var5, var6);
            break;
        case 3:
            this.a(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 1.0F);
            super.a(var1, var2, var3, var4, var5, var6);
            this.a(0.25F, 0.375F, 0.0F, 0.75F, 0.625F, 0.75F);
            super.a(var1, var2, var3, var4, var5, var6);
            break;
        case 4:
            this.a(0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
            super.a(var1, var2, var3, var4, var5, var6);
            this.a(0.375F, 0.25F, 0.25F, 0.625F, 0.75F, 1.0F);
            super.a(var1, var2, var3, var4, var5, var6);
            break;
        case 5:
            this.a(0.75F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            super.a(var1, var2, var3, var4, var5, var6);
            this.a(0.0F, 0.375F, 0.25F, 0.75F, 0.625F, 0.75F);
            super.a(var1, var2, var3, var4, var5, var6);
        }

        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void a(OIBlockAccess var1, int var2, int var3, int var4) {
        int var5 = var1.c(var2, var3, var4);
        switch (b(var5)) {
        case 0:
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
            break;
        case 1:
            this.a(0.0F, 0.75F, 0.0F, 1.0F, 1.0F, 1.0F);
            break;
        case 2:
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.25F);
            break;
        case 3:
            this.a(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 1.0F);
            break;
        case 4:
            this.a(0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
            break;
        case 5:
            this.a(0.75F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }

    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, int var5) {
        int var6 = b(var1.c(var2, var3, var4));
        int var7 = var1.a(var2 - OFacing.b[var6], var3 - OFacing.c[var6], var4 - OFacing.d[var6]);
        if (var7 != OBlock.Z.bO && var7 != OBlock.V.bO) {
            var1.e(var2, var3, var4, 0);
        } else {
            OBlock.m[var7].a(var1, var2 - OFacing.b[var6], var3 - OFacing.c[var6], var4 - OFacing.d[var6], var5);
        }

    }

    public static int b(int var0) {
        return var0 & 7;
    }
}
