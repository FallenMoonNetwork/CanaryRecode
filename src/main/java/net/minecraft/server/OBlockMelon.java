package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OItem;
import net.minecraft.server.OMaterial;

public class OBlockMelon extends OBlock {

    protected OBlockMelon(int var1) {
        super(var1, OMaterial.z);
        this.bN = 136;
    }

    @Override
    public int a(int var1, int var2) {
        return var1 != 1 && var1 != 0 ? 136 : 137;
    }

    @Override
    public int a(int var1) {
        return var1 != 1 && var1 != 0 ? 136 : 137;
    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return OItem.be.bP;
    }

    @Override
    public int a(Random var1) {
        return 3 + var1.nextInt(5);
    }

    @Override
    public int a(int var1, Random var2) {
        int var3 = this.a(var2) + var2.nextInt(1 + var1);
        if (var3 > 9) {
            var3 = 9;
        }

        return var3;
    }
}
