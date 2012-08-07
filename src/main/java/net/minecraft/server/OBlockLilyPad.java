package net.minecraft.server;


import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockFlower;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;


public class OBlockLilyPad extends OBlockFlower {

    protected OBlockLilyPad(int var1, int var2) {
        super(var1, var2);
        float var3 = 0.5F;
        float var4 = 0.015625F;

        this.a(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var4, 0.5F + var3);
    }

    @Override
    public int c() {
        return 23;
    }

    @Override
    public OAxisAlignedBB e(OWorld var1, int var2, int var3, int var4) {
        return OAxisAlignedBB.b(var2 + this.bV, var3 + this.bW, var4 + this.bX, var2 + this.bY, var3 + this.bZ, var4 + this.ca);
    }

    @Override
    public boolean c(OWorld var1, int var2, int var3, int var4) {
        return super.c(var1, var2, var3, var4);
    }

    @Override
    protected boolean d(int var1) {
        return var1 == OBlock.B.bO;
    }

    @Override
    public boolean f(OWorld var1, int var2, int var3, int var4) {
        return var3 >= 0 && var3 < 256 ? var1.d(var2, var3 - 1, var4) == OMaterial.g && var1.c(var2, var3 - 1, var4) == 0 : false;
    }
}
