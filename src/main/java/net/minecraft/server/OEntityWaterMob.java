package net.minecraft.server;

import net.minecraft.server.OEntityCreature;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIAnimals;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OWorld;

public abstract class OEntityWaterMob extends OEntityCreature implements OIAnimals {

    public OEntityWaterMob(OWorld var1) {
        super(var1);
    }

    @Override
    public boolean f_() {
        return true;
    }

    @Override
    public void b(ONBTTagCompound var1) {
        super.b(var1);
    }

    @Override
    public void a(ONBTTagCompound var1) {
        super.a(var1);
    }

    @Override
    public boolean l() {
        return this.bi.a(this.bw);
    }

    @Override
    public int m() {
        return 120;
    }

    @Override
    protected boolean n() {
        return true;
    }

    @Override
    protected int a(OEntityPlayer var1) {
        return 1 + this.bi.r.nextInt(3);
    }
}
