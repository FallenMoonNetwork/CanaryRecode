package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;

public class OBlockPane extends OBlock {

    private int a;
    private final boolean b;

    protected OBlockPane(int var1, int var2, int var3, OMaterial var4, boolean var5) {
        super(var1, var2, var4);
        this.a = var3;
        this.b = var5;
    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return !this.b ? 0 : super.a(var1, var2, var3);
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
        return 18;
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, OAxisAlignedBB var5, ArrayList var6) {
        boolean var7 = this.d(var1.a(var2, var3, var4 - 1));
        boolean var8 = this.d(var1.a(var2, var3, var4 + 1));
        boolean var9 = this.d(var1.a(var2 - 1, var3, var4));
        boolean var10 = this.d(var1.a(var2 + 1, var3, var4));
        if ((!var9 || !var10) && (var9 || var10 || var7 || var8)) {
            if (var9 && !var10) {
                this.a(0.0F, 0.0F, 0.4375F, 0.5F, 1.0F, 0.5625F);
                super.a(var1, var2, var3, var4, var5, var6);
            } else if (!var9 && var10) {
                this.a(0.5F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
                super.a(var1, var2, var3, var4, var5, var6);
            }
        } else {
            this.a(0.0F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
            super.a(var1, var2, var3, var4, var5, var6);
        }

        if ((!var7 || !var8) && (var9 || var10 || var7 || var8)) {
            if (var7 && !var8) {
                this.a(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 0.5F);
                super.a(var1, var2, var3, var4, var5, var6);
            } else if (!var7 && var8) {
                this.a(0.4375F, 0.0F, 0.5F, 0.5625F, 1.0F, 1.0F);
                super.a(var1, var2, var3, var4, var5, var6);
            }
        } else {
            this.a(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 1.0F);
            super.a(var1, var2, var3, var4, var5, var6);
        }

    }

    @Override
    public void f() {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void a(OIBlockAccess var1, int var2, int var3, int var4) {
        float var5 = 0.4375F;
        float var6 = 0.5625F;
        float var7 = 0.4375F;
        float var8 = 0.5625F;
        boolean var9 = this.d(var1.a(var2, var3, var4 - 1));
        boolean var10 = this.d(var1.a(var2, var3, var4 + 1));
        boolean var11 = this.d(var1.a(var2 - 1, var3, var4));
        boolean var12 = this.d(var1.a(var2 + 1, var3, var4));
        if ((!var11 || !var12) && (var11 || var12 || var9 || var10)) {
            if (var11 && !var12) {
                var5 = 0.0F;
            } else if (!var11 && var12) {
                var6 = 1.0F;
            }
        } else {
            var5 = 0.0F;
            var6 = 1.0F;
        }

        if ((!var9 || !var10) && (var11 || var12 || var9 || var10)) {
            if (var9 && !var10) {
                var7 = 0.0F;
            } else if (!var9 && var10) {
                var8 = 1.0F;
            }
        } else {
            var7 = 0.0F;
            var8 = 1.0F;
        }

        this.a(var5, 0.0F, var7, var6, 1.0F, var8);
    }

    public final boolean d(int var1) {
        return OBlock.n[var1] || var1 == this.bO || var1 == OBlock.M.bO;
    }
}
