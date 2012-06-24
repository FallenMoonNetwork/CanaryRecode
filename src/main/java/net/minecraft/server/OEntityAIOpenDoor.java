package net.minecraft.server;

import net.minecraft.server.OEntityAIDoorInteract;
import net.minecraft.server.OEntityLiving;

public class OEntityAIOpenDoor extends OEntityAIDoorInteract {

    boolean i;
    int j;

    public OEntityAIOpenDoor(OEntityLiving var1, boolean var2) {
        super(var1);
        this.a = var1;
        this.i = var2;
    }

    @Override
    public boolean b() {
        return this.i && this.j > 0 && super.b();
    }

    @Override
    public void c() {
        this.j = 20;
        this.e.a(this.a.bi, this.b, this.c, this.d, true);
    }

    @Override
    public void d() {
        if (this.i) {
            this.e.a(this.a.bi, this.b, this.c, this.d, false);
        }

    }

    @Override
    public void e() {
        --this.j;
        super.e();
    }
}
