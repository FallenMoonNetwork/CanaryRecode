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
    public static final Material r = (new MaterialLogic(MapColor.e)).g();
    public static final Material s = (new Material(MapColor.b)).r().p();
    public static final Material t = (new Material(MapColor.b)).p();
    public static final Material u = (new Material(MapColor.f)).g().r();
    public static final Material v = (new Material(MapColor.i)).n();
    public static final Material w = (new Material(MapColor.g)).r().p();
    public static final Material x = (new MaterialLogic(MapColor.j)).i().r().f().n();
    public static final Material y = (new Material(MapColor.j)).f();
    public static final Material z = (new Material(MapColor.i)).r().n();
    public static final Material A = new Material(MapColor.k);
    public static final Material B = (new Material(MapColor.i)).n();
    public static final Material C = (new Material(MapColor.i)).n();
    public static final Material D = (new MaterialPortal(MapColor.b)).o();
    public static final Material E = (new Material(MapColor.b)).n();
    public static final Material F = (new MaterialWeb(MapColor.e)).f().n();
    public static final Material G = (new Material(MapColor.m)).o();
    private boolean I;
    public boolean J; // CanaryMod: private => public (translucent)
    private boolean K;
    public final MapColor H;
    private boolean L = true;
    private int M;
    private boolean N;

    private final CanaryBlockMaterial cbm; // CanaryMod

    public Material(MapColor mapcolor) {
        this.H = mapcolor;
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
        this.K = true;
        return this;
    }

    protected Material f() {
        this.L = false;
        return this;
    }

    protected Material g() {
        this.I = true;
        return this;
    }

    public boolean h() {
        return this.I;
    }

    public Material i() {
        this.J = true;
        return this;
    }

    public boolean j() {
        return this.J;
    }

    public boolean k() {
        return this.K ? false : this.c();
    }

    public boolean l() {
        return this.L;
    }

    public int m() {
        return this.M;
    }

    protected Material n() {
        this.M = 1;
        return this;
    }

    protected Material o() {
        this.M = 2;
        return this;
    }

    protected Material p() {
        this.N = true;
        return this;
    }

    public boolean q() {
        return this.N;
    }

    // CanaryMod
    public CanaryBlockMaterial getCanaryBlockMaterial() {
        return this.cbm;
    }
    //
}
