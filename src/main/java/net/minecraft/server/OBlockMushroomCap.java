package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OMaterial;

public class OBlockMushroomCap extends OBlock {

    private int a;

    public OBlockMushroomCap(int var1, OMaterial var2, int var3, int var4) {
        super(var1, var3, var2);
        this.a = var4;
    }

    @Override
    public int a(int var1, int var2) {
        return var2 == 10 && var1 > 1 ? this.bN - 1 : (var2 >= 1 && var2 <= 9 && var1 == 1 ? this.bN - 16 - this.a : (var2 >= 1 && var2 <= 3 && var1 == 2 ? this.bN - 16 - this.a : (var2 >= 7 && var2 <= 9 && var1 == 3 ? this.bN - 16 - this.a : ((var2 == 1 || var2 == 4 || var2 == 7) && var1 == 4 ? this.bN - 16 - this.a : ((var2 == 3 || var2 == 6 || var2 == 9) && var1 == 5 ? this.bN - 16 - this.a : (var2 == 14 ? this.bN - 16 - this.a : (var2 == 15 ? this.bN - 1 : this.bN)))))));
    }

    @Override
    public int a(Random var1) {
        int var2 = var1.nextInt(10) - 7;
        if (var2 < 0) {
            var2 = 0;
        }

        return var2;
    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return OBlock.af.bO + this.a;
    }
}
