package net.minecraft.server;

import net.minecraft.server.ONBTBase;
import net.minecraft.server.ONBTTagCompound;

public class OPlayerCapabilities {

    public boolean a = false;
    public boolean b = false;
    public boolean c = false;
    public boolean d = false;

    public OPlayerCapabilities() {
        super();
    }

    public void a(ONBTTagCompound var1) {
        ONBTTagCompound var2 = new ONBTTagCompound();
        var2.a("invulnerable", this.a);
        var2.a("flying", this.b);
        var2.a("mayfly", this.c);
        var2.a("instabuild", this.d);
        var1.a("abilities", (ONBTBase) var2);
    }

    public void b(ONBTTagCompound var1) {
        if (var1.c("abilities")) {
            ONBTTagCompound var2 = var1.m("abilities");
            this.a = var2.o("invulnerable");
            this.b = var2.o("flying");
            this.c = var2.o("mayfly");
            this.d = var2.o("instabuild");
        }

    }
}
