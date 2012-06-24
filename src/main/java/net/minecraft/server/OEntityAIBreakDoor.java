package net.minecraft.server;

import net.minecraft.server.OEntityAIDoorInteract;
import net.minecraft.server.OEntityLiving;

public class OEntityAIBreakDoor extends OEntityAIDoorInteract {

    private int i;

    public OEntityAIBreakDoor(OEntityLiving var1) {
        super(var1);
    }

    @Override
    public boolean a() {
        return !super.a() ? false : !this.e.d((OIBlockAccess)this.a.bi, this.b, this.c, this.d);
    }

    @Override
    public void c() {
        super.c();
        this.i = 240;
    }

    @Override
    public boolean b() {
        double var1 = this.a.e((double) this.b, (double) this.c, (double) this.d);
        return this.i >= 0 && !this.e.d((OIBlockAccess)this.a.bi, this.b, this.c, this.d) && var1 < 4.0D;
    }

    @Override
    public void e() {
        super.e();
        if (this.a.an().nextInt(20) == 0) {
            this.a.bi.f(1010, this.b, this.c, this.d, 0);
        }

        if (--this.i == 0 && this.a.bi.q == 3) {
            this.a.bi.e(this.b, this.c, this.d, 0);
            this.a.bi.f(1012, this.b, this.c, this.d, 0);
            this.a.bi.f(2001, this.b, this.c, this.d, this.e.bO);
        }

    }
}
