package net.minecraft.server;


import java.util.Random;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;


public class OBlockCake extends OBlock {

    protected OBlockCake(int var1, int var2) {
        super(var1, var2, OMaterial.C);
        this.a(true);
    }

    @Override
    public void a(OIBlockAccess var1, int var2, int var3, int var4) {
        int var5 = var1.c(var2, var3, var4);
        float var6 = 0.0625F;
        float var7 = (1 + var5 * 2) / 16.0F;
        float var8 = 0.5F;

        this.a(var7, 0.0F, var6, 1.0F - var6, var8, 1.0F - var6);
    }

    @Override
    public void f() {
        float var1 = 0.0625F;
        float var2 = 0.5F;

        this.a(var1, 0.0F, var1, 1.0F - var1, var2, 1.0F - var1);
    }

    @Override
    public OAxisAlignedBB e(OWorld var1, int var2, int var3, int var4) {
        int var5 = var1.c(var2, var3, var4);
        float var6 = 0.0625F;
        float var7 = (1 + var5 * 2) / 16.0F;
        float var8 = 0.5F;

        return OAxisAlignedBB.b((var2 + var7), var3, (var4 + var6), ((var2 + 1) - var6), (var3 + var8 - var6), ((var4 + 1) - var6));
    }

    @Override
    public int a(int var1, int var2) {
        return var1 == 1 ? this.bN : (var1 == 0 ? this.bN + 3 : (var2 > 0 && var1 == 4 ? this.bN + 2 : this.bN + 1));
    }

    @Override
    public int a(int var1) {
        return var1 == 1 ? this.bN : (var1 == 0 ? this.bN + 3 : this.bN + 1);
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
    public boolean a(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
        this.c(var1, var2, var3, var4, var5);
        return true;
    }

    @Override
    public void b(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
        this.c(var1, var2, var3, var4, var5);
    }

    private void c(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
        if (var5.b(false)) {
            var5.getFoodStats().addStats(2, 0.1F);
            int var6 = var1.c(var2, var3, var4) + 1;

            if (var6 >= 6) {
                var1.e(var2, var3, var4, 0);
            } else {
                var1.c(var2, var3, var4, var6);
                var1.k(var2, var3, var4);
            }
        }

    }

    @Override
    public boolean c(OWorld var1, int var2, int var3, int var4) {
        return !super.c(var1, var2, var3, var4) ? false : this.f(var1, var2, var3, var4);
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, int var5) {
        if (!this.f(var1, var2, var3, var4)) {
            this.b(var1, var2, var3, var4, var1.c(var2, var3, var4), 0);
            var1.e(var2, var3, var4, 0);
        }

    }

    @Override
    public boolean f(OWorld var1, int var2, int var3, int var4) {
        return var1.d(var2, var3 - 1, var4).a();
    }

    @Override
    public int a(Random var1) {
        return 0;
    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return 0;
    }
}
