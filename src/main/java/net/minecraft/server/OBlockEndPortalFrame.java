package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OWorld;

public class OBlockEndPortalFrame extends OBlock {

    public OBlockEndPortalFrame(int var1) {
        super(var1, 159, OMaterial.q);
    }

    @Override
    public int a(int var1, int var2) {
        return var1 == 1 ? this.bN - 1 : (var1 == 0 ? this.bN + 16 : this.bN);
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public int c() {
        return 26;
    }

    @Override
    public void f() {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.8125F, 1.0F);
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, OAxisAlignedBB var5, ArrayList var6) {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.8125F, 1.0F);
        super.a(var1, var2, var3, var4, var5, var6);
        int var7 = var1.c(var2, var3, var4);
        if (d(var7)) {
            this.a(0.3125F, 0.8125F, 0.3125F, 0.6875F, 1.0F, 0.6875F);
            super.a(var1, var2, var3, var4, var5, var6);
        }

        this.f();
    }

    public static boolean d(int var0) {
        return (var0 & 4) != 0;
    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return 0;
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, OEntityLiving var5) {
        int var6 = ((OMathHelper.b((var5.bs * 4.0F / 360.0F) + 0.5D) & 3) + 2) % 4;
        var1.c(var2, var3, var4, var6);
    }
}
