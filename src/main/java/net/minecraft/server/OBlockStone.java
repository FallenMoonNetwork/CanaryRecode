package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OMaterial;

public class OBlockStone extends OBlock {

    public OBlockStone(int var1, int var2) {
        super(var1, var2, OMaterial.e);
    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return OBlock.w.bO;
    }
}
