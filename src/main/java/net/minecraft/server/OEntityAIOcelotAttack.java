package net.minecraft.server;


import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OWorld;


public class OEntityAIOcelotAttack extends OEntityAIBase {

    OWorld a;
    OEntityLiving b;
    OEntityLiving c;
    int d = 0;

    public OEntityAIOcelotAttack(OEntityLiving var1) {
        super();
        this.b = var1;
        this.a = var1.bi;
        this.a(3);
    }

    @Override
    public boolean a() {
        OEntityLiving var1 = this.b.at();

        if (var1 == null) {
            return false;
        } else {
            this.c = var1;
            return true;
        }
    }

    @Override
    public boolean b() {
        return !this.c.aE() ? false : (this.b.j(this.c) > 225.0D ? false : !this.b.al().e() || this.a());
    }

    @Override
    public void d() {
        this.c = null;
        this.b.al().f();
    }

    @Override
    public void e() {
        this.b.ai().a(this.c, 30.0F, 30.0F);
        double var1 = (this.b.bG * 2.0F * this.b.bG * 2.0F);
        double var3 = this.b.e(this.c.bm, this.c.bw.b, this.c.bo);
        float var5 = 0.23F;

        if (var3 > var1 && var3 < 16.0D) {
            var5 = 0.4F;
        } else if (var3 < 225.0D) {
            var5 = 0.18F;
        }

        this.b.al().a(this.c, var5);
        this.d = Math.max(this.d - 1, 0);
        if (var3 <= var1) {
            if (this.d <= 0) {
                this.d = 20;
                this.b.a((OEntity) this.c);
            }
        }
    }
}
