package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OMaterial;

public class OBlockLeavesBase extends OBlock {

    protected boolean b;

    protected OBlockLeavesBase(int var1, int var2, OMaterial var3, boolean var4) {
        super(var1, var2, var3);
        this.b = var4;
    }

    public boolean a() {
        return false;
    }
}
