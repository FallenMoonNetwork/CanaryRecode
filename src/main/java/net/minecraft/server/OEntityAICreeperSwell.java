package net.minecraft.server;

import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityCreeper;
import net.minecraft.server.OEntityLiving;

public class OEntityAICreeperSwell extends OEntityAIBase {

    OEntityCreeper a;
    OEntityLiving b;

    public OEntityAICreeperSwell(OEntityCreeper var1) {
        super();
        this.a = var1;
        this.a(1);
    }

    @Override
    public boolean a() {
        OEntityLiving var1 = this.a.at();
        return this.a.A() > 0 || var1 != null && this.a.j(var1) < 9.0D;
    }

    @Override
    public void c() {
        this.a.al().f();
        this.b = this.a.at();
    }

    @Override
    public void d() {
        this.b = null;
    }

    @Override
    public void e() {
        if (this.b == null) {
            this.a.c(-1);
        } else if (this.a.j(this.b) > 49.0D) {
            this.a.c(-1);
        } else if (!this.a.am().a(this.b)) {
            this.a.c(-1);
        } else {
            this.a.c(1);
        }
    }
}
