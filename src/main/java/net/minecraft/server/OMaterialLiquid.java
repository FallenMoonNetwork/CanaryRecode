package net.minecraft.server;


import net.minecraft.server.OMapColor;
import net.minecraft.server.OMaterial;


public class OMaterialLiquid extends OMaterial {

    public OMaterialLiquid(OMapColor var1) {
        super(var1);
        this.h();
        this.m();
    }

    @Override
    public boolean d() {
        return true;
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public boolean a() {
        return false;
    }
}
