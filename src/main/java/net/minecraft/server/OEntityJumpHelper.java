package net.minecraft.server;

import net.minecraft.server.OEntityLiving;

public class OEntityJumpHelper {

    private OEntityLiving a;
    private boolean b = false;

    public OEntityJumpHelper(OEntityLiving var1) {
        super();
        this.a = var1;
    }

    public void a() {
        this.b = true;
    }

    public void b() {
        this.a.f(this.b);
        this.b = false;
    }
}
