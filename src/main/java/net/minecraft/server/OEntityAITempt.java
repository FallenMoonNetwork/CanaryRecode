package net.minecraft.server;


import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityCreature;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItemStack;


public class OEntityAITempt extends OEntityAIBase {

    private OEntityCreature a;
    private float b;
    private double c;
    private double d;
    private double e;
    private double f;
    private double g;
    private OEntityPlayer h;
    private int i = 0;
    private boolean j;
    private int k;
    private boolean l;
    private boolean m;

    public OEntityAITempt(OEntityCreature var1, float var2, int var3, boolean var4) {
        super();
        this.a = var1;
        this.b = var2;
        this.k = var3;
        this.l = var4;
        this.a(3);
    }

    @Override
    public boolean a() {
        if (this.i > 0) {
            --this.i;
            return false;
        } else {
            this.h = this.a.bi.a(this.a, 10.0D);
            if (this.h == null) {
                return false;
            } else {
                OItemStack var1 = this.h.U();

                return var1 == null ? false : var1.c == this.k;
            }
        }
    }

    @Override
    public boolean b() {
        if (this.l) {
            if (this.a.j(this.h) < 36.0D) {
                if (this.h.e(this.c, this.d, this.e) > 0.010000000000000002D) {
                    return false;
                }

                if (Math.abs(this.h.bt - this.f) > 5.0D || Math.abs(this.h.bs - this.g) > 5.0D) {
                    return false;
                }
            } else {
                this.c = this.h.bm;
                this.d = this.h.bn;
                this.e = this.h.bo;
            }

            this.f = this.h.bt;
            this.g = this.h.bs;
        }

        return this.a();
    }

    @Override
    public void c() {
        this.c = this.h.bm;
        this.d = this.h.bn;
        this.e = this.h.bo;
        this.j = true;
        this.m = this.a.al().a();
        this.a.al().a(false);
    }

    @Override
    public void d() {
        this.h = null;
        this.a.al().f();
        this.i = 100;
        this.j = false;
        this.a.al().a(this.m);
    }

    @Override
    public void e() {
        this.a.ai().a(this.h, 30.0F, this.a.D());
        if (this.a.j(this.h) < 6.25D) {
            this.a.al().f();
        } else {
            this.a.al().a(this.h, this.b);
        }

    }

    public boolean f() {
        return this.j;
    }
}
