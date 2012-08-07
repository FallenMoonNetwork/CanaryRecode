package net.minecraft.server;

import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityTameable;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OPathNavigate;
import net.minecraft.server.OWorld;

public class OEntityAIFollowOwner extends OEntityAIBase {

    private OEntityTameable d;
    private OEntityLiving e;
    OWorld a;
    private float f;
    private OPathNavigate g;
    private int h;
    float b;
    float c;
    private boolean i;

    public OEntityAIFollowOwner(OEntityTameable var1, float var2, float var3, float var4) {
        super();
        this.d = var1;
        this.a = var1.bi;
        this.f = var2;
        this.g = var1.al();
        this.c = var3;
        this.b = var4;
        this.a(3);
    }

    @Override
    public boolean a() {
        OEntityLiving var1 = this.d.w_();
        if (var1 == null) {
            return false;
        } else if (this.d.v_()) {
            return false;
        } else if (this.d.j(var1) < (this.c * this.c)) {
            return false;
        } else {
            this.e = var1;
            return true;
        }
    }

    @Override
    public boolean b() {
        return !this.g.e() && this.d.j(this.e) > (this.b * this.b) && !this.d.v_();
    }

    @Override
    public void c() {
        this.h = 0;
        this.i = this.d.al().a();
        this.d.al().a(false);
    }

    @Override
    public void d() {
        this.e = null;
        this.g.f();
        this.d.al().a(this.i);
    }

    @Override
    public void e() {
        this.d.ai().a(this.e, 10.0F, this.d.D());
        if (!this.d.v_()) {
            if (--this.h <= 0) {
                this.h = 10;
                if (!this.g.a(this.e, this.f)) {
                    if (this.d.j(this.e) >= 144.0D) {
                        int var1 = OMathHelper.b(this.e.bm) - 2;
                        int var2 = OMathHelper.b(this.e.bo) - 2;
                        int var3 = OMathHelper.b(this.e.bw.b);

                        for (int var4 = 0; var4 <= 4; ++var4) {
                            for (int var5 = 0; var5 <= 4; ++var5) {
                                if ((var4 < 1 || var5 < 1 || var4 > 3 || var5 > 3) && this.a.e(var1 + var4, var3 - 1, var2 + var5) && !this.a.e(var1 + var4, var3, var2 + var5) && !this.a.e(var1 + var4, var3 + 1, var2 + var5)) {
                                    this.d.c(((var1 + var4) + 0.5F), var3, ((var2 + var5) + 0.5F), this.d.bs, this.d.bt);
                                    this.g.f();
                                    return;
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}
