package net.minecraft.server;

import net.minecraft.server.OItemBlock;

public class OItemLeaves extends OItemBlock {

    public OItemLeaves(int var1) {
        super(var1);
        this.f(0);
        this.a(true);
    }

    public int a(int var1) {
        return var1 | 4;
    }
}
