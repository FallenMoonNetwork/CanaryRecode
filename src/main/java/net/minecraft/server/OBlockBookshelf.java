package net.minecraft.server;


import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OItem;
import net.minecraft.server.OMaterial;


public class OBlockBookshelf extends OBlock {

    public OBlockBookshelf(int var1, int var2) {
        super(var1, var2, OMaterial.d);
    }

    @Override
    public int a(int var1) {
        return var1 <= 1 ? 4 : this.bN;
    }

    @Override
    public int a(Random var1) {
        return 3;
    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return OItem.aK.bP;
    }
}
