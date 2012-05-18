package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;

public class OBlockFlower extends OBlock {

    protected OBlockFlower(int var1, int var2, OMaterial var3) {
        super(var1, var3);
        this.bN = var2;
        this.a(true);
        float var4 = 0.2F;
        this.a(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var4 * 3.0F, 0.5F + var4);
    }

    protected OBlockFlower(int var1, int var2) {
        this(var1, var2, OMaterial.j);
    }

    public boolean c(OWorld var1, int var2, int var3, int var4) {
        return super.c(var1, var2, var3, var4) && this.d(var1.a(var2, var3 - 1, var4));
    }

    protected boolean d(int var1) {
        return var1 == OBlock.u.bO || var1 == OBlock.v.bO || var1 == OBlock.aA.bO;
    }

    public void a(OWorld var1, int var2, int var3, int var4, int var5) {
        super.a(var1, var2, var3, var4, var5);
        this.h(var1, var2, var3, var4);
    }

    public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
        this.h(var1, var2, var3, var4);
    }

    protected final void h(OWorld var1, int var2, int var3, int var4) {
        if (!this.f(var1, var2, var3, var4)) {
            this.b(var1, var2, var3, var4, var1.c(var2, var3, var4), 0);
            var1.e(var2, var3, var4, 0);
        }

    }

    public boolean f(OWorld var1, int var2, int var3, int var4) {
        return (var1.m(var2, var3, var4) >= 8 || var1.l(var2, var3, var4)) && this.d(var1.a(var2, var3 - 1, var4));
    }

    public OAxisAlignedBB e(OWorld var1, int var2, int var3, int var4) {
        return null;
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public int c() {
        return 1;
    }
}
