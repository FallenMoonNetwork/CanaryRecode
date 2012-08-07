package net.minecraft.server;

import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEntityDragonPart;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OWorld;

public class OEntityDragonBase extends OEntityLiving {

    protected int t = 100;

    public OEntityDragonBase(OWorld var1) {
        super(var1);
    }

    @Override
    public int d() {
        return this.t;
    }

    public boolean a(OEntityDragonPart var1, ODamageSource var2, int var3) {
        return this.a(var2, var3);
    }

    @Override
    public boolean a(ODamageSource var1, int var2) {
        return false;
    }

    protected boolean e(ODamageSource var1, int var2) {
        return super.a(var1, var2);
    }
}
