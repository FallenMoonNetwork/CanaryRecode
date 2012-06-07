package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEnumSkyBlock;
import net.minecraft.server.OItem;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;

public class OBlockSnowBlock extends OBlock {

    protected OBlockSnowBlock(int var1, int var2) {
        super(var1, var2, OMaterial.w);
        this.a(true);
    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return OItem.aC.bP;
    }

    @Override
    public int a(Random var1) {
        return 4;
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
        if (var1.a(OEnumSkyBlock.b, var2, var3, var4) > 11) {
            this.b(var1, var2, var3, var4, var1.c(var2, var3, var4), 0);
            var1.e(var2, var3, var4, 0);
        }

    }
}
