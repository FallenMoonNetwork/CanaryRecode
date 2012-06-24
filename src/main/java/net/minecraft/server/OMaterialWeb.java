package net.minecraft.server;

import net.minecraft.server.OMapColor;
import net.minecraft.server.OMaterial;

final class OMaterialWeb extends OMaterial {

    OMaterialWeb(OMapColor var1) {
        super(var1);
    }

    @Override
    public boolean c() {
        return false;
    }
}
