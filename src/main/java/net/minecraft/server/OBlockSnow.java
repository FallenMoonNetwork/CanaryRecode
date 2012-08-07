package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityItem;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEnumSkyBlock;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OStatList;
import net.minecraft.server.OWorld;

public class OBlockSnow extends OBlock {

    protected OBlockSnow(int var1, int var2) {
        super(var1, var2, OMaterial.v);
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        this.a(true);
    }

    @Override
    public OAxisAlignedBB e(OWorld var1, int var2, int var3, int var4) {
        int var5 = var1.c(var2, var3, var4) & 7;
        return var5 >= 3 ? OAxisAlignedBB.b(var2 + this.bV, var3 + this.bW, var4 + this.bX, var2 + this.bY, (var3 + 0.5F), var4 + this.ca) : null;
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
    public void a(OIBlockAccess var1, int var2, int var3, int var4) {
        int var5 = var1.c(var2, var3, var4) & 7;
        float var6 = (2 * (1 + var5)) / 16.0F;
        this.a(0.0F, 0.0F, 0.0F, 1.0F, var6, 1.0F);
    }

    @Override
    public boolean c(OWorld var1, int var2, int var3, int var4) {
        int var5 = var1.a(var2, var3 - 1, var4);
        return var5 != 0 && (var5 == OBlock.K.bO || OBlock.m[var5].a()) ? var1.d(var2, var3 - 1, var4).c() : false;
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, int var5) {
        this.g(var1, var2, var3, var4);
    }

    private boolean g(OWorld var1, int var2, int var3, int var4) {
        if (!this.c(var1, var2, var3, var4)) {
            this.b(var1, var2, var3, var4, var1.c(var2, var3, var4), 0);
            var1.e(var2, var3, var4, 0);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void a(OWorld var1, OEntityPlayer var2, int var3, int var4, int var5, int var6) {
        int var7 = OItem.aC.bP;
        float var8 = 0.7F;
        double var9 = (var1.r.nextFloat() * var8) + (1.0F - var8) * 0.5D;
        double var11 = (var1.r.nextFloat() * var8) + (1.0F - var8) * 0.5D;
        double var13 = (var1.r.nextFloat() * var8) + (1.0F - var8) * 0.5D;
        OEntityItem var15 = new OEntityItem(var1, var3 + var9, var4 + var11, var5 + var13, new OItemStack(var7, 1, 0));
        var15.c = 10;
        var1.b(var15);
        var1.e(var3, var4, var5, 0);
        var2.a(OStatList.C[this.bO], 1);
    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return OItem.aC.bP;
    }

    @Override
    public int a(Random var1) {
        return 0;
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
        if (var1.a(OEnumSkyBlock.b, var2, var3, var4) > 11) {
            this.b(var1, var2, var3, var4, var1.c(var2, var3, var4), 0);
            var1.e(var2, var3, var4, 0);
        }

    }
}
