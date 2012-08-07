package net.minecraft.server;

import net.minecraft.server.OMapColor;
import net.minecraft.server.OMaterial;

public class OMaterialTransparent extends OMaterial {

    public OMaterialTransparent(OMapColor var1) {
        super(var1);
        this.h();
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public boolean b() {
        return false;
    }

    @Override
    public boolean c() {
        return false;
    }
}
