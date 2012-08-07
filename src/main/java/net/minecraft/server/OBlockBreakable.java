package net.minecraft.server;


import net.minecraft.server.OBlock;
import net.minecraft.server.OMaterial;


public class OBlockBreakable extends OBlock {

    private boolean a;

    protected OBlockBreakable(int var1, int var2, OMaterial var3, boolean var4) {
        super(var1, var2, var3);
        this.a = var4;
    }

    @Override
    public boolean a() {
        return false;
    }
}
