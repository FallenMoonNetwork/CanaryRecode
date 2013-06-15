package net.minecraft.server;

import net.canarymod.api.world.blocks.CanaryBlockMaterial;

public class Material {

    public static final Material a = new MaterialTransparent(MapColor.b);
    public static final Material b = new Material(MapColor.c);
    public static final Material c = new Material(MapColor.l);
    public static final Material d = (new Material(MapColor.o)).g();
    public static final Material e = (new Material(MapColor.m)).f();
    public static final Material f = (new Material(MapColor.h)).f();
    public static final Material g = (new Material(MapColor.h)).f().o();
    public static final Material h = (new MaterialLiquid(MapColor.n)).n();
    public static final Material i = (new MaterialLiquid(MapColor.f)).n();
    public static final Material j = (new Material(MapColor.i)).g().r().n();
    public static final Material k = (new MaterialLogic(MapColor.i)).n();
    public static final Material l = (new MaterialLogic(MapColor.i)).g().n().i();
    public static final Material m = new Material(MapColor.e);
    public static final Material n = (new Material(MapColor.e)).g();
    public static final Material o = (new MaterialTransparent(MapColor.b)).n();
    public static final Material p = new Material(MapColor.d);
    public static final Material q = (new MaterialLogic(MapColor.b)).n();
    public static final Material r = (new Material(MapColor.b)).r().p();
    public static final Material s = (new Material(MapColor.b)).p();
    public static final Material t = (new Material(MapColor.f)).g().r();
    public static final Material u = (new Material(MapColor.i)).n();
    public static final Material v = (new Material(MapColor.g)).r().p();
    public static final Material w = (new MaterialLogic(MapColor.j)).i().r().f().n();
    public static final Material x = (new Material(MapColor.j)).f();
    public static final Material y = (new Material(MapColor.i)).r().n();
    public static final Material z = new Material(MapColor.k);
    public static final Material A = (new Material(MapColor.i)).n();
    public static final Material B = (new Material(MapColor.i)).n();
    public static final Material C = (new MaterialPortal(MapColor.b)).o();
    public static final Material D = (new Material(MapColor.b)).n();
    public static final Material E = (new MaterialWeb(MapColor.e)).f().n();
    public static final Material F = (new Material(MapColor.m)).o();
    private boolean H;
    private boolean I;
    public boolean J; // CanaryMod: private => public (translucent)
    public final MapColor G;
    private boolean K = true;
    private int L;
    private boolean M;
    private final CanaryBlockMaterial cbm;

    public Material(MapColor mapcolor) {
        this.G = mapcolor;
        this.cbm = new CanaryBlockMaterial(this);
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

    private Material r() {
        this.J = true;
        return this;
    }

    protected Material f() {
        this.K = false;
        return this;
    }

    protected Material g() {
        this.H = true;
        return this;
    }

    public boolean h() {
        return this.H;
    }

    public Material i() {
        this.I = true;
        return this;
    }

    public boolean j() {
        return this.I;
    }

    public boolean k() {
        return this.J ? false : this.c();
    }

    public boolean l() {
        return this.K;
    }

    public int m() {
        return this.L;
    }

    protected Material n() {
        this.L = 1;
        return this;
    }

    protected Material o() {
        this.L = 2;
        return this;
    }

    protected Material p() {
        this.M = true;
        return this;
    }

    public boolean q() {
        return this.M;
    }

    // CanaryMod
    public CanaryBlockMaterial getCanaryBlockMaterial() {
        return this.cbm;
    }
    //
}
