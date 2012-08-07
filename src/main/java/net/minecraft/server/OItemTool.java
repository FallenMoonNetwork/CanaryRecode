package net.minecraft.server;


import net.minecraft.server.OBlock;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEnumToolMaterial;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;


public class OItemTool extends OItem {

    private OBlock[] bU;
    protected float a = 4.0F;
    private int bV;
    protected OEnumToolMaterial b;

    protected OItemTool(int var1, int var2, OEnumToolMaterial var3, OBlock[] var4) {
        super(var1);
        this.b = var3;
        this.bU = var4;
        this.bQ = 1;
        this.f(var3.a());
        this.a = var3.b();
        this.bV = var2 + var3.c();
    }

    @Override
    public float a(OItemStack var1, OBlock var2) {
        for (int var3 = 0; var3 < this.bU.length; ++var3) {
            if (this.bU[var3] == var2) {
                return this.a;
            }
        }

        return 1.0F;
    }

    @Override
    public boolean a(OItemStack var1, OEntityLiving var2, OEntityLiving var3) {
        var1.a(2, var3);
        return true;
    }

    @Override
    public boolean a(OItemStack var1, int var2, int var3, int var4, int var5, OEntityLiving var6) {
        var1.a(1, var6);
        return true;
    }

    @Override
    public int a(OEntity var1) {
        return this.bV;
    }

    @Override
    public int c() {
        return this.b.e();
    }
}
