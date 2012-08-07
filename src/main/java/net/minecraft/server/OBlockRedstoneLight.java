package net.minecraft.server;


import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;


public class OBlockRedstoneLight extends OBlock {

    private final boolean a;

    public OBlockRedstoneLight(int var1, boolean var2) {
        super(var1, 211, OMaterial.r);
        this.a = var2;
        if (var2) {
            this.a(1.0F);
            ++this.bN;
        }

    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4) {
        if (!var1.F) {
            if (this.a && !var1.x(var2, var3, var4)) {
                var1.c(var2, var3, var4, this.bO, 4);
            } else if (!this.a && var1.x(var2, var3, var4)) {
                var1.e(var2, var3, var4, OBlock.bM.bO);
            }
        }

    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, int var5) {
        if (!var1.F) {
            if (this.a && !var1.x(var2, var3, var4)) {
                var1.c(var2, var3, var4, this.bO, 4);
            } else if (!this.a && var1.x(var2, var3, var4)) {
                var1.e(var2, var3, var4, OBlock.bM.bO);
            }
        }

    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
        if (!var1.F && this.a && !var1.x(var2, var3, var4)) {
            var1.e(var2, var3, var4, OBlock.bL.bO);
        }

    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return OBlock.bL.bO;
    }
}
