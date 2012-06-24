package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;

public class OBlockStep extends OBlock {

    public static final String[] a = new String[] { "stone", "sand", "wood", "cobble", "brick", "smoothStoneBrick" };
    private boolean b;

    public OBlockStep(int var1, boolean var2) {
        super(var1, 6, OMaterial.e);
        this.b = var2;
        if (!var2) {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        } else {
            n[var1] = true;
        }

        this.f(255);
    }

    @Override
    public void a(OIBlockAccess var1, int var2, int var3, int var4) {
        if (this.b) {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        } else {
            boolean var5 = (var1.c(var2, var3, var4) & 8) != 0;
            if (var5) {
                this.a(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
            } else {
                this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
            }
        }

    }

    @Override
    public void f() {
        if (this.b) {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        } else {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        }

    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, OAxisAlignedBB var5, ArrayList var6) {
        this.a(var1, var2, var3, var4);
        super.a(var1, var2, var3, var4, var5, var6);
    }

    @Override
    public int a(int var1, int var2) {
        int var3 = var2 & 7;
        return var3 == 0 ? (var1 <= 1 ? 6 : 5) : (var3 == 1 ? (var1 == 0 ? 208 : (var1 == 1 ? 176 : 192)) : (var3 == 2 ? 4 : (var3 == 3 ? 16 : (var3 == 4 ? OBlock.al.bN : (var3 == 5 ? OBlock.bm.bN : 6)))));
    }

    @Override
    public int a(int var1) {
        return this.a(var1, 0);
    }

    @Override
    public boolean a() {
        return this.b;
    }

    @Override
    public void e(OWorld var1, int var2, int var3, int var4, int var5) {
        if (var5 == 0 && !this.b) {
            int var6 = var1.c(var2, var3, var4) & 7;
            var1.c(var2, var3, var4, var6 | 8);
        }

    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return OBlock.ak.bO;
    }

    @Override
    public int a(Random var1) {
        return this.b ? 2 : 1;
    }

    @Override
    protected int c(int var1) {
        return var1 & 7;
    }

    @Override
    public boolean b() {
        return this.b;
    }

    @Override
    protected OItemStack a_(int var1) {
        return new OItemStack(OBlock.ak.bO, 1, var1 & 7);
    }

}
