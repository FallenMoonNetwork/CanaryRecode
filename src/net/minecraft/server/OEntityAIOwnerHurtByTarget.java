package net.minecraft.server;

import net.minecraft.server.OEntityAITarget;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityTameable;

public class OEntityAIOwnerHurtByTarget extends OEntityAITarget {

    OEntityTameable a;
    OEntityLiving b;

    public OEntityAIOwnerHurtByTarget(OEntityTameable var1) {
        super(var1, 32.0F, false);
        this.a = var1;
        this.a(1);
    }

    public boolean a() {
        if (!this.a.u_()) {
            return false;
        } else {
            OEntityLiving var1 = this.a.w_();
            if (var1 == null) {
                return false;
            } else {
                this.b = var1.ao();
                return this.a(this.b, false);
            }
        }
    }

    public void c() {
        this.c.b(this.b);
        super.c();
    }
}
