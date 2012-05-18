package net.minecraft.server;

import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityLiving;

public class OEntityAISwimming extends OEntityAIBase {

    private OEntityLiving a;

    public OEntityAISwimming(OEntityLiving var1) {
        super();
        this.a = var1;
        this.a(4);
        var1.al().e(true);
    }

    public boolean a() {
        return this.a.aU() || this.a.aV();
    }

    public void e() {
        if (this.a.an().nextFloat() < 0.8F) {
            this.a.ak().a();
        }

    }
}
