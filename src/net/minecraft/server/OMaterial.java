package net.minecraft.server;

import net.minecraft.server.OMapColor;
import net.minecraft.server.OMaterialLiquid;
import net.minecraft.server.OMaterialLogic;
import net.minecraft.server.OMaterialPortal;
import net.minecraft.server.OMaterialTransparent;
import net.minecraft.server.OMaterialWeb;

public class OMaterial {

    public static final OMaterial a = new OMaterialTransparent(OMapColor.b);
    public static final OMaterial b = new OMaterial(OMapColor.c);
    public static final OMaterial c = new OMaterial(OMapColor.l);
    public static final OMaterial d = (new OMaterial(OMapColor.o)).f();
    public static final OMaterial e = (new OMaterial(OMapColor.m)).e();
    public static final OMaterial f = (new OMaterial(OMapColor.h)).e();
    public static final OMaterial g = (new OMaterialLiquid(OMapColor.n)).m();
    public static final OMaterial h = (new OMaterialLiquid(OMapColor.f)).m();
    public static final OMaterial i = (new OMaterial(OMapColor.i)).f().o().m();
    public static final OMaterial j = (new OMaterialLogic(OMapColor.i)).m();
    public static final OMaterial k = (new OMaterialLogic(OMapColor.i)).f().m().h();
    public static final OMaterial l = new OMaterial(OMapColor.e);
    public static final OMaterial m = (new OMaterial(OMapColor.e)).f();
    public static final OMaterial n = (new OMaterialTransparent(OMapColor.b)).m();
    public static final OMaterial o = new OMaterial(OMapColor.d);
    public static final OMaterial p = (new OMaterialLogic(OMapColor.b)).m();
    public static final OMaterial q = (new OMaterial(OMapColor.b)).o();
    public static final OMaterial r = new OMaterial(OMapColor.b);
    public static final OMaterial s = (new OMaterial(OMapColor.f)).f().o();
    public static final OMaterial t = (new OMaterial(OMapColor.i)).m();
    public static final OMaterial u = (new OMaterial(OMapColor.g)).o();
    public static final OMaterial v = (new OMaterialLogic(OMapColor.j)).h().o().e().m();
    public static final OMaterial w = (new OMaterial(OMapColor.j)).e();
    public static final OMaterial x = (new OMaterial(OMapColor.i)).o().m();
    public static final OMaterial y = new OMaterial(OMapColor.k);
    public static final OMaterial z = (new OMaterial(OMapColor.i)).m();
    public static final OMaterial A = (new OMaterial(OMapColor.i)).m();
    public static final OMaterial B = (new OMaterialPortal(OMapColor.b)).n();
    public static final OMaterial C = (new OMaterial(OMapColor.b)).m();
    public static final OMaterial D = (new OMaterialWeb(OMapColor.e)).e().m();
    public static final OMaterial E = (new OMaterial(OMapColor.m)).n();
    private boolean G;
    private boolean H;
    private boolean I;
    public final OMapColor F;
    private boolean J = true;
    private int K;

    public OMaterial(OMapColor var1) {
        super();
        this.F = var1;
    }

    public boolean d() {
        return false;
    }

    public boolean a() {
        return true;
    }

    public boolean b() {
        return true;
    }

    public boolean c() {
        return true;
    }

    private OMaterial o() {
        this.I = true;
        return this;
    }

    protected OMaterial e() {
        this.J = false;
        return this;
    }

    protected OMaterial f() {
        this.G = true;
        return this;
    }

    public boolean g() {
        return this.G;
    }

    public OMaterial h() {
        this.H = true;
        return this;
    }

    public boolean i() {
        return this.H;
    }

    public boolean j() {
        return this.I ? false : this.c();
    }

    public boolean k() {
        return this.J;
    }

    public int l() {
        return this.K;
    }

    protected OMaterial m() {
        this.K = 1;
        return this;
    }

    protected OMaterial n() {
        this.K = 2;
        return this;
    }

}
