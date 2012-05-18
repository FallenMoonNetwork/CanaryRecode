package net.minecraft.server;

import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;

public class OBlockFence extends OBlock {

    public OBlockFence(int var1, int var2) {
        super(var1, var2, OMaterial.d);
    }

    public OBlockFence(int var1, int var2, OMaterial var3) {
        super(var1, var2, var3);
    }

    public boolean c(OWorld var1, int var2, int var3, int var4) {
        return super.c(var1, var2, var3, var4);
    }

    public OAxisAlignedBB e(OWorld var1, int var2, int var3, int var4) {
        boolean var5 = this.c((OIBlockAccess) var1, var2, var3, var4 - 1);
        boolean var6 = this.c((OIBlockAccess) var1, var2, var3, var4 + 1);
        boolean var7 = this.c((OIBlockAccess) var1, var2 - 1, var3, var4);
        boolean var8 = this.c((OIBlockAccess) var1, var2 + 1, var3, var4);
        float var9 = 0.375F;
        float var10 = 0.625F;
        float var11 = 0.375F;
        float var12 = 0.625F;
        if (var5) {
            var11 = 0.0F;
        }

        if (var6) {
            var12 = 1.0F;
        }

        if (var7) {
            var9 = 0.0F;
        }

        if (var8) {
            var10 = 1.0F;
        }

        return OAxisAlignedBB.b((var2 + var9), var3, (var4 + var11), (var2 + var10), (var3 + 1.5F), (var4 + var12));
    }

    public void a(OIBlockAccess var1, int var2, int var3, int var4) {
        boolean var5 = this.c(var1, var2, var3, var4 - 1);
        boolean var6 = this.c(var1, var2, var3, var4 + 1);
        boolean var7 = this.c(var1, var2 - 1, var3, var4);
        boolean var8 = this.c(var1, var2 + 1, var3, var4);
        float var9 = 0.375F;
        float var10 = 0.625F;
        float var11 = 0.375F;
        float var12 = 0.625F;
        if (var5) {
            var11 = 0.0F;
        }

        if (var6) {
            var12 = 1.0F;
        }

        if (var7) {
            var9 = 0.0F;
        }

        if (var8) {
            var10 = 1.0F;
        }

        this.a(var9, 0.0F, var11, var10, 1.0F, var12);
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public boolean b(OIBlockAccess var1, int var2, int var3, int var4) {
        return false;
    }

    public int c() {
        return 11;
    }

    public boolean c(OIBlockAccess var1, int var2, int var3, int var4) {
        int var5 = var1.a(var2, var3, var4);
        if (var5 != this.bO && var5 != OBlock.bv.bO) {
            OBlock var6 = OBlock.m[var5];
            return var6 != null && var6.cd.j() && var6.b() ? var6.cd != OMaterial.z : false;
        } else {
            return true;
        }
    }
}
