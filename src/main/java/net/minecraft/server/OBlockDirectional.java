package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OMaterial;

public abstract class OBlockDirectional extends OBlock {

    protected OBlockDirectional(int var1, int var2, OMaterial var3) {
        super(var1, var2, var3);
    }

    protected OBlockDirectional(int var1, OMaterial var2) {
        super(var1, var2);
    }

    public static int b(int var0) {
        return var0 & 3;
    }
}
