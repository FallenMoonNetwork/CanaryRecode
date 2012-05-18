package net.minecraft.server;

import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityCreature;

public class OEntityAIRestrictSun extends OEntityAIBase {

    private OEntityCreature a;

    public OEntityAIRestrictSun(OEntityCreature var1) {
        super();
        this.a = var1;
    }

    public boolean a() {
        return this.a.bi.e();
    }

    public void c() {
        this.a.al().d(true);
    }

    public void d() {
        this.a.al().d(false);
    }
}
