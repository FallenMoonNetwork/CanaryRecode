package net.minecraft.server;

import net.canarymod.api.CanaryDamageSource;


public class DamageSource {

    public static DamageSource a = (new DamageSource("inFire")).l();
    public static DamageSource b = (new DamageSource("onFire")).j().l();
    public static DamageSource c = (new DamageSource("lava")).l();
    public static DamageSource d = (new DamageSource("inWall")).j();
    public static DamageSource e = (new DamageSource("drown")).j();
    public static DamageSource f = (new DamageSource("starve")).j();
    public static DamageSource g = new DamageSource("cactus");
    public static DamageSource h = (new DamageSource("fall")).j();
    public static DamageSource i = (new DamageSource("outOfWorld")).j().k();
    public static DamageSource j = (new DamageSource("generic")).j();
    public static DamageSource k = (new DamageSource("magic")).j().r();
    public static DamageSource l = (new DamageSource("wither")).j();
    public static DamageSource m = new DamageSource("anvil");
    public static DamageSource n = new DamageSource("fallingBlock");
    private boolean p = false;
    private boolean q = false;
    private float r = 0.3F;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v = false;
    private boolean w = false;
    public String o;

    //CanaryMod
    protected CanaryDamageSource damageSource;

    public static DamageSource a(EntityLiving entityliving) {
        return new EntityDamageSource("mob", entityliving);
    }

    public static DamageSource a(EntityPlayer entityplayer) {
        return new EntityDamageSource("player", entityplayer);
    }

    public static DamageSource a(EntityArrow entityarrow, Entity entity) {
        return (new EntityDamageSourceIndirect("arrow", entityarrow, entity)).b();
    }

    public static DamageSource a(EntityFireball entityfireball, Entity entity) {
        return entity == null ? (new EntityDamageSourceIndirect("onFire", entityfireball, entityfireball)).l().b() : (new EntityDamageSourceIndirect("fireball", entityfireball, entity)).l().b();
    }

    public static DamageSource a(Entity entity, Entity entity1) {
        return (new EntityDamageSourceIndirect("thrown", entity, entity1)).b();
    }

    public static DamageSource b(Entity entity, Entity entity1) {
        return (new EntityDamageSourceIndirect("indirectMagic", entity, entity1)).j().r();
    }

    public static DamageSource a(Entity entity) {
        return (new EntityDamageSource("thorns", entity)).r();
    }

    public static DamageSource a(Explosion explosion) {
        return explosion != null && explosion.c() != null ? (new EntityDamageSource("explosion.player", explosion.c())).o().d() : (new DamageSource("explosion")).o().d();
    }

    public boolean a() {
        return this.t;
    }

    public DamageSource b() {
        this.t = true;
        return this;
    }

    public boolean c() {
        return this.w;
    }

    public DamageSource d() {
        this.w = true;
        return this;
    }

    public boolean e() {
        return this.p;
    }

    public float f() {
        return this.r;
    }

    public boolean g() {
        return this.q;
    }

    protected DamageSource(String s0) {
        this.o = s0;
        damageSource = new CanaryDamageSource(this);
    }

    public Entity h() {
        return this.i();
    }

    public Entity i() {
        return null;
    }

    protected DamageSource j() {
        this.p = true;
        this.r = 0.0F;
        return this;
    }

    protected DamageSource k() {
        this.q = true;
        return this;
    }

    protected DamageSource l() {
        this.s = true;
        return this;
    }

    public String b(EntityLiving entityliving) {
        EntityLiving entityliving1 = entityliving.bN();
        String s0 = "death.attack." + this.o;
        String s1 = s0 + ".player";

        return entityliving1 != null && StatCollector.b(s1) ? StatCollector.a(s1, new Object[] { entityliving.ax(), entityliving1.ax() }) : StatCollector.a(s0, new Object[] { entityliving.ax() });
    }

    public boolean m() {
        return this.s;
    }

    public String n() {
        return this.o;
    }

    public DamageSource o() {
        this.u = true;
        return this;
    }

    public boolean p() {
        return this.u;
    }

    public boolean q() {
        return this.v;
    }

    public DamageSource r() {
        this.v = true;
        return this;
    }

    /**
     * Gets the CanaryMod damagesource wrapper
     * @return
     */
    public CanaryDamageSource getCanaryDamageSource() {
        return damageSource;
    }

    /**
     * Set hunger damage
     * @param f
     */
    public void setHungerDamage(float f) {
        this.r = f;
    }

    /**
     * Set unblockable
     * @param b
     */
    public void setUnblockable(boolean b) {
        this.p = b;
        if(b == true) {
            this.r = 0.0f;
        }
    }
}
