package net.minecraft.server;

import net.minecraft.server.OEntityCreature;
import net.minecraft.server.OIAnimals;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OWorld;

public abstract class OEntityGolem extends OEntityCreature implements OIAnimals {

    public OEntityGolem(OWorld var1) {
        super(var1);
    }

    @Override
    protected void a(float var1) {
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
    protected String i() {
        return "none";
    }

    @Override
    protected String j() {
        return "none";
    }

    @Override
    protected String k() {
        return "none";
    }

    @Override
    public int m() {
        return 120;
    }

    @Override
    protected boolean n() {
        return false;
    }
}
