package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OMaterial;

public class OBlockWood extends OBlock {

    public OBlockWood(int var1) {
        super(var1, 4, OMaterial.d);
    }

    @Override
    public int a(int var1, int var2) {
        switch (var2) {
        case 1:
            return 198;
        case 2:
            return 214;
        case 3:
            return 199;
        default:
            return 4;
        }
    }

    @Override
    protected int c(int var1) {
        return var1;
    }
}
