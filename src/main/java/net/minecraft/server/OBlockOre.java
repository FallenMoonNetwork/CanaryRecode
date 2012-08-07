package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OItem;
import net.minecraft.server.OMaterial;

public class OBlockOre extends OBlock {

    public OBlockOre(int var1, int var2) {
        super(var1, var2, OMaterial.e);
    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return this.bO == OBlock.I.bO ? OItem.l.bP : (this.bO == OBlock.aw.bO ? OItem.m.bP : (this.bO == OBlock.N.bO ? OItem.aV.bP : this.bO));
    }

    @Override
    public int a(Random var1) {
        return this.bO == OBlock.N.bO ? 4 + var1.nextInt(5) : 1;
    }

    @Override
    public int a(int var1, Random var2) {
        if (var1 > 0 && this.bO != this.a(0, var2, var1)) {
            int var3 = var2.nextInt(var1 + 2) - 1;
            if (var3 < 0) {
                var3 = 0;
            }

            return this.a(var2) * (var3 + 1);
        } else {
            return this.a(var2);
        }
    }

    @Override
    protected int c(int var1) {
        return this.bO == OBlock.N.bO ? 4 : 0;
    }
}
