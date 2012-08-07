package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OWorld;

public class OEntityAIEatGrass extends OEntityAIBase {

    private OEntityLiving b;
    private OWorld c;
    int a = 0;

    public OEntityAIEatGrass(OEntityLiving var1) {
        super();
        this.b = var1;
        this.c = var1.bi;
        this.a(7);
    }

    @Override
    public boolean a() {
        if (this.b.an().nextInt(this.b.aO() ? 50 : 1000) != 0) {
            return false;
        } else {
            int var1 = OMathHelper.b(this.b.bm);
            int var2 = OMathHelper.b(this.b.bn);
            int var3 = OMathHelper.b(this.b.bo);
            return this.c.a(var1, var2, var3) == OBlock.X.bO && this.c.c(var1, var2, var3) == 1 ? true : this.c.a(var1, var2 - 1, var3) == OBlock.u.bO;
        }
    }

    @Override
    public void c() {
        this.a = 40;
        this.c.a(this.b, (byte) 10);
        this.b.al().f();
    }

    @Override
    public void d() {
        this.a = 0;
    }

    @Override
    public boolean b() {
        return this.a > 0;
    }

    public int f() {
        return this.a;
    }

    @Override
    public void e() {
        this.a = Math.max(0, this.a - 1);
        if (this.a == 4) {
            int var1 = OMathHelper.b(this.b.bm);
            int var2 = OMathHelper.b(this.b.bn);
            int var3 = OMathHelper.b(this.b.bo);
            if (this.c.a(var1, var2, var3) == OBlock.X.bO) {
                this.c.f(2001, var1, var2, var3, OBlock.X.bO + 4096);
                this.c.e(var1, var2, var3, 0);
                this.b.z();
            } else if (this.c.a(var1, var2 - 1, var3) == OBlock.u.bO) {
                this.c.f(2001, var1, var2 - 1, var3, OBlock.u.bO);
                this.c.e(var1, var2 - 1, var3, OBlock.v.bO);
                this.b.z();
            }

        }
    }
}
