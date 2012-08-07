package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlockSand;
import net.minecraft.server.OItem;

public class OBlockGravel extends OBlockSand {

    public OBlockGravel(int var1, int var2) {
        super(var1, var2);
    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return var2.nextInt(10 - var3 * 3) == 0 ? OItem.ao.bP : this.bO;
    }
}
