package net.minecraft.server;

import net.minecraft.server.OEntityCreature;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OWorld;

public abstract class OEntityAgeable extends OEntityCreature {

    public OEntityAgeable(OWorld var1) {
        super(var1);
    }

    @Override
    protected void b() {
        super.b();
        this.bY.a(12, new Integer(0));
    }

    public int K() {
        return this.bY.c(12);
    }

    public void c(int var1) {
        this.bY.b(12, Integer.valueOf(var1));
    }

    @Override
    public void b(ONBTTagCompound var1) {
        super.b(var1);
        var1.a("Age", this.K());
    }

    @Override
    public void a(ONBTTagCompound var1) {
        super.a(var1);
        this.c(var1.f("Age"));
    }

    @Override
    public void e() {
        super.e();
        int var1 = this.K();
        if (var1 < 0) {
            ++var1;
            this.c(var1);
        } else if (var1 > 0) {
            --var1;
            this.c(var1);
        }

    }

    @Override
    public boolean aO() {
        return this.K() < 0;
    }
}
