package net.minecraft.server;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import net.canarymod.Canary;
import net.canarymod.api.CanaryDamageSource;
import net.canarymod.api.entity.living.CanaryEntityLiving;
import net.canarymod.api.potion.CanaryPotionEffect;
import net.canarymod.hook.entity.DamageHook;
import net.canarymod.hook.entity.EntityDespawnHook;
import net.canarymod.hook.entity.MobTargetHook;
import net.canarymod.hook.entity.PotionEffectAppliedHook;
import net.canarymod.hook.entity.PotionEffectFinishHook;


public abstract class EntityLiving extends Entity {

    private static final float[] b = new float[] { 0.0F, 0.0F, 0.1F, 0.2F };
    private static final float[] c = new float[] { 0.0F, 0.0F, 0.25F, 0.5F };
    private static final float[] d = new float[] { 0.0F, 0.0F, 0.05F, 0.02F };
    public static final float[] au = new float[] { 0.0F, 0.1F, 0.15F, 0.45F };
    public int av = 20;
    public float aw;
    public float ax;
    public float ay = 0.0F;
    public float az = 0.0F;
    public float aA = 0.0F;
    public float aB = 0.0F;
    protected float aC;
    protected float aD;
    protected float aE;
    protected float aF;
    protected boolean aG = true;
    protected String aH = "/mob/char.png";
    protected boolean aI = true;
    protected float aJ = 0.0F;
    protected String aK = null;
    protected float aL = 1.0F;
    protected int aM = 0;
    protected float aN = 0.0F;
    public float aO = 0.1F;
    public float aP = 0.02F;
    public float aQ;
    public float aR;
    protected int aS = this.aW();
    public int aT;
    protected int aU;
    public int aV;
    public int aW;
    public int aX;
    public float aY = 0.0F;
    public int aZ = 0;
    public int ba = 0;
    public float bb;
    public float bc;
    protected boolean bd = false;
    protected int be;
    public int bf = -1;
    public float bg = (float) (Math.random() * 0.8999999761581421D + 0.10000000149011612D);
    public float bh;
    public float bi;
    public float bj;
    protected EntityPlayer bk = null;
    protected int bl = 0;
    private EntityLiving e = null;
    private int f = 0;
    private EntityLiving g = null;
    public int bm = 0;
    protected HashMap bn = new HashMap();
    private boolean h = true;
    private int i;
    private EntityLookHelper j;
    private EntityMoveHelper bK;
    private EntityJumpHelper bL;
    private EntityBodyHelper bM;
    private PathNavigate bN;
    protected final EntityAITasks bo;
    protected final EntityAITasks bp;
    private EntityLiving bO;
    private EntitySenses bP;
    private float bQ;
    private ChunkCoordinates bR = new ChunkCoordinates(0, 0, 0);
    private float bS = -1.0F;
    private ItemStack[] bT = new ItemStack[5];
    protected float[] bq = new float[5];
    private ItemStack[] bU = new ItemStack[5];
    public boolean br = false;
    public int bs = 0;
    private boolean bV = false;
    private boolean bW = false;
    protected final CombatTracker bt = new CombatTracker(this);
    protected int bu;
    protected double bv;
    protected double bw;
    protected double bx;
    protected double by;
    protected double bz;
    float bA = 0.0F;
    protected int bB = 0;
    public int bC = 0; // CanaryMod: protected => public
    protected float bD;
    protected float bE;
    protected float bF;
    protected boolean bG = false;
    protected float bH = 0.0F;
    protected float bI = 0.7F;
    private int bX = 0;
    private Entity bY;
    protected int bJ = 0;

    // CanaryMod: Custom MaxHealth
    protected int maxHealth;

    public EntityLiving(World world) {
        super(world);
        this.m = true;
        this.bo = new EntityAITasks(world != null && world.C != null ? world.C : null);
        this.bp = new EntityAITasks(world != null && world.C != null ? world.C : null);
        this.j = new EntityLookHelper(this);
        this.bK = new EntityMoveHelper(this);
        this.bL = new EntityJumpHelper(this);
        this.bM = new EntityBodyHelper(this);
        this.bN = new PathNavigate(this, world, (float) this.ay());
        this.bP = new EntitySenses(this);
        this.ax = (float) (Math.random() + 1.0D) * 0.01F;
        this.b(this.u, this.v, this.w);
        this.aw = (float) Math.random() * 12398.0F;
        this.A = (float) (Math.random() * 3.1415927410125732D * 2.0D);
        this.aA = this.A;

        for (int i0 = 0; i0 < this.bq.length; ++i0) {
            this.bq[i0] = 0.085F;
        }

        this.Y = 0.5F;
    }

    protected int ay() {
        return 16;
    }

    public EntityLookHelper az() {
        return this.j;
    }

    public EntityMoveHelper aA() {
        return this.bK;
    }

    public EntityJumpHelper aB() {
        return this.bL;
    }

    public PathNavigate aC() {
        return this.bN;
    }

    public EntitySenses aD() {
        return this.bP;
    }

    public Random aE() {
        return this.ab;
    }

    public EntityLiving aF() {
        return this.e;
    }

    public EntityLiving aG() {
        return this.g;
    }

    public void l(Entity entity) {
        if (entity instanceof EntityLiving) {
            this.g = (EntityLiving) entity;
        }
    }

    public int aH() {
        return this.bC;
    }

    @Override
    public float ao() {
        return this.aA;
    }

    public float aI() {
        return this.bQ;
    }

    public void e(float f0) {
        this.bQ = f0;
        this.f(f0);
    }

    public boolean m(Entity entity) {
        this.l(entity);
        return false;
    }

    public EntityLiving aJ() {
        return this.bO;
    }

    public void b(EntityLiving entityliving) {
        // CanaryMod: MobTarget
        if (entityliving != null) {
            MobTargetHook hook = new MobTargetHook((net.canarymod.api.entity.living.EntityLiving) this.getCanaryEntity(), (net.canarymod.api.entity.living.EntityLiving) entityliving.getCanaryEntity());
            Canary.hooks().callHook(hook);
            if (hook.isCanceled()) {
                return;
            }
        }
        //
        this.bO = entityliving;
    }

    public boolean a(Class oclass0) {
        return EntityCreeper.class != oclass0 && EntityGhast.class != oclass0;
    }

    public void aK() {}

    @Override
    protected void a(double d0, boolean flag0) {
        if (!this.G()) {
            this.H();
        }

        if (flag0 && this.T > 0.0F) {
            int i0 = MathHelper.c(this.u);
            int i1 = MathHelper.c(this.v - 0.20000000298023224D - (double) this.N);
            int i2 = MathHelper.c(this.w);
            int i3 = this.q.a(i0, i1, i2);

            if (i3 == 0) {
                int i4 = this.q.e(i0, i1 - 1, i2);

                if (i4 == 11 || i4 == 32 || i4 == 21) {
                    i3 = this.q.a(i0, i1 - 1, i2);
                }
            }

            if (i3 > 0) {
                Block.r[i3].a(this.q, i0, i1, i2, this, this.T);
            }
        }

        super.a(d0, flag0);
    }

    public boolean aL() {
        return this.d(MathHelper.c(this.u), MathHelper.c(this.v), MathHelper.c(this.w));
    }

    public boolean d(int i0, int i1, int i2) {
        return this.bS == -1.0F ? true : this.bR.e(i0, i1, i2) < this.bS * this.bS;
    }

    public void b(int i0, int i1, int i2, int i3) {
        this.bR.b(i0, i1, i2);
        this.bS = (float) i3;
    }

    public ChunkCoordinates aM() {
        return this.bR;
    }

    public float aN() {
        return this.bS;
    }

    public void aO() {
        this.bS = -1.0F;
    }

    public boolean aP() {
        return this.bS != -1.0F;
    }

    public void c(EntityLiving entityliving) {
        this.e = entityliving;
        this.f = this.e != null ? 100 : 0;
    }

    @Override
    protected void a() {
        this.ah.a(8, Integer.valueOf(this.i));
        this.ah.a(9, Byte.valueOf((byte) 0));
        this.ah.a(10, Byte.valueOf((byte) 0));
        this.ah.a(6, Byte.valueOf((byte) 0));
        this.ah.a(5, "");
    }

    public boolean n(Entity entity) {
        return this.q.a(this.q.T().a(this.u, this.v + (double) this.e(), this.w), this.q.T().a(entity.u, entity.v + (double) entity.e(), entity.w)) == null;
    }

    @Override
    public boolean K() {
        return !this.M;
    }

    @Override
    public boolean L() {
        return !this.M;
    }

    @Override
    public float e() {
        return this.P * 0.85F;
    }

    public int aQ() {
        return 80;
    }

    public void aR() {
        String s0 = this.bb();

        if (s0 != null) {
            this.a(s0, this.ba(), this.aY());
        }
    }

    @Override
    public void x() {
        this.aQ = this.aR;
        super.x();
        this.q.C.a("mobBaseTick");
        if (this.R() && this.ab.nextInt(1000) < this.aV++) {
            this.aV = -this.aQ();
            this.aR();
        }

        if (this.R() && this.S()) {
            // CanaryMod: call DamageHook (Suffocation)
            DamageHook hook = new DamageHook(null, entity, new CanaryDamageSource(DamageSource.d), 1);

            Canary.hooks().callHook(hook);
            if (!hook.isCanceled()) {
                this.a((((CanaryDamageSource) hook.getDamageSource()).getHandle()), hook.getDamageDealt());
            }
            //
        }

        if (this.E() || this.q.I) {
            this.A();
        }

        boolean flag0 = this instanceof EntityPlayer && ((EntityPlayer) this).ce.a;

        if (this.R() && this.a(Material.h) && !this.bf() && !this.bn.containsKey(Integer.valueOf(Potion.o.H)) && !flag0) {
            this.g(this.h(this.ak()));
            if (this.ak() == -20) {
                this.g(0);

                // CanaryMod - drowning damage.
                DamageHook hook = new DamageHook(null, entity, new CanaryDamageSource(DamageSource.e), 2);

                Canary.hooks().callHook(hook);
                if (!hook.isCanceled()) {
                    for (int i0 = 0; i0 < 8; ++i0) {
                        float f0 = this.ab.nextFloat() - this.ab.nextFloat();
                        float f1 = this.ab.nextFloat() - this.ab.nextFloat();
                        float f2 = this.ab.nextFloat() - this.ab.nextFloat();

                        this.q.a("bubble", this.u + (double) f0, this.v + (double) f1, this.w + (double) f2, this.x, this.y, this.z);
                    }

                    this.a((((CanaryDamageSource) hook.getDamageSource()).getHandle()), hook.getDamageDealt());
                }
                //
            }

            this.A();
        } else {
            this.g(300);
        }

        this.bb = this.bc;
        if (this.ba > 0) {
            --this.ba;
        }

        if (this.aW > 0) {
            --this.aW;
        }

        if (this.af > 0) {
            --this.af;
        }

        if (this.aS <= 0) {
            this.aS();
        }

        if (this.bl > 0) {
            --this.bl;
        } else {
            this.bk = null;
        }

        if (this.g != null && !this.g.R()) {
            this.g = null;
        }

        if (this.e != null) {
            if (!this.e.R()) {
                this.c((EntityLiving) null);
            } else if (this.f > 0) {
                --this.f;
            } else {
                this.c((EntityLiving) null);
            }
        }

        this.bA();
        this.aF = this.aE;
        this.az = this.ay;
        this.aB = this.aA;
        this.C = this.A;
        this.D = this.B;
        this.q.C.b();
    }

    protected void aS() {
        ++this.aZ;
        if (this.aZ == 20) {
            int i0;

            if (!this.q.I && (this.bl > 0 || this.aT()) && !this.h_() && this.q.M().b("doMobLoot")) {
                i0 = this.d(this.bk);

                while (i0 > 0) {
                    int i1 = EntityXPOrb.a(i0);

                    i0 -= i1;
                    this.q.d((Entity) (new EntityXPOrb(this.q, this.u, this.v, this.w, i1)));
                }
            }

            this.w();

            for (i0 = 0; i0 < 20; ++i0) {
                double d0 = this.ab.nextGaussian() * 0.02D;
                double d1 = this.ab.nextGaussian() * 0.02D;
                double d2 = this.ab.nextGaussian() * 0.02D;

                this.q.a("explode", this.u + (double) (this.ab.nextFloat() * this.O * 2.0F) - (double) this.O, this.v + (double) (this.ab.nextFloat() * this.P), this.w + (double) (this.ab.nextFloat() * this.O * 2.0F) - (double) this.O, d0, d1, d2);
            }
        }
    }

    protected int h(int i0) {
        int i1 = EnchantmentHelper.b(this);

        return i1 > 0 && this.ab.nextInt(i1 + 1) > 0 ? i0 : i0 - 1;
    }

    protected int d(EntityPlayer entityplayer) {
        if (this.be > 0) {
            int i0 = this.be;
            ItemStack[] aitemstack = this.ad();

            for (int i1 = 0; i1 < aitemstack.length; ++i1) {
                if (aitemstack[i1] != null && this.bq[i1] <= 1.0F) {
                    i0 += 1 + this.ab.nextInt(3);
                }
            }

            return i0;
        } else {
            return this.be;
        }
    }

    protected boolean aT() {
        return false;
    }

    public void aU() {
        for (int i0 = 0; i0 < 20; ++i0) {
            double d0 = this.ab.nextGaussian() * 0.02D;
            double d1 = this.ab.nextGaussian() * 0.02D;
            double d2 = this.ab.nextGaussian() * 0.02D;
            double d3 = 10.0D;

            this.q.a("explode", this.u + (double) (this.ab.nextFloat() * this.O * 2.0F) - (double) this.O - d0 * d3, this.v + (double) (this.ab.nextFloat() * this.P) - d1 * d3, this.w + (double) (this.ab.nextFloat() * this.O * 2.0F) - (double) this.O - d2 * d3, d0, d1, d2);
        }
    }

    @Override
    public void T() {
        super.T();
        this.aC = this.aD;
        this.aD = 0.0F;
        this.T = 0.0F;
    }

    @Override
    public void l_() {
        super.l_();
        if (!this.q.I) {
            int i0;

            for (i0 = 0; i0 < 5; ++i0) {
                ItemStack itemstack = this.p(i0);

                if (!ItemStack.b(itemstack, this.bU[i0])) {
                    ((WorldServer) this.q).p().a((Entity) this, (Packet) (new Packet5PlayerInventory(this.k, i0, itemstack)));
                    this.bU[i0] = itemstack == null ? null : itemstack.m();
                }
            }

            i0 = this.bM();
            if (i0 > 0) {
                if (this.bm <= 0) {
                    this.bm = 20 * (30 - i0);
                }

                --this.bm;
                if (this.bm <= 0) {
                    this.r(i0 - 1);
                }
            }
        }

        this.c();
        double d0 = this.u - this.r;
        double d1 = this.w - this.t;
        float f0 = (float) (d0 * d0 + d1 * d1);
        float f1 = this.ay;
        float f2 = 0.0F;

        this.aC = this.aD;
        float f3 = 0.0F;

        if (f0 > 0.0025000002F) {
            f3 = 1.0F;
            f2 = (float) Math.sqrt((double) f0) * 3.0F;
            f1 = (float) Math.atan2(d1, d0) * 180.0F / 3.1415927F - 90.0F;
        }

        if (this.aR > 0.0F) {
            f1 = this.A;
        }

        if (!this.F) {
            f3 = 0.0F;
        }

        this.aD += (f3 - this.aD) * 0.3F;
        this.q.C.a("headTurn");
        if (this.bh()) {
            this.bM.a();
        } else {
            float f4 = MathHelper.g(f1 - this.ay);

            this.ay += f4 * 0.3F;
            float f5 = MathHelper.g(this.A - this.ay);
            boolean flag0 = f5 < -90.0F || f5 >= 90.0F;

            if (f5 < -75.0F) {
                f5 = -75.0F;
            }

            if (f5 >= 75.0F) {
                f5 = 75.0F;
            }

            this.ay = this.A - f5;
            if (f5 * f5 > 2500.0F) {
                this.ay += f5 * 0.2F;
            }

            if (flag0) {
                f2 *= -1.0F;
            }
        }

        this.q.C.b();
        this.q.C.a("rangeChecks");

        while (this.A - this.C < -180.0F) {
            this.C -= 360.0F;
        }

        while (this.A - this.C >= 180.0F) {
            this.C += 360.0F;
        }

        while (this.ay - this.az < -180.0F) {
            this.az -= 360.0F;
        }

        while (this.ay - this.az >= 180.0F) {
            this.az += 360.0F;
        }

        while (this.B - this.D < -180.0F) {
            this.D -= 360.0F;
        }

        while (this.B - this.D >= 180.0F) {
            this.D += 360.0F;
        }

        while (this.aA - this.aB < -180.0F) {
            this.aB -= 360.0F;
        }

        while (this.aA - this.aB >= 180.0F) {
            this.aB += 360.0F;
        }

        this.q.C.b();
        this.aE += f2;
    }

    public void j(int i0) {
        if (this.aS > 0) {
            this.b(this.aX() + i0);
            if (this.aS > this.aW()) {
                this.b(this.aW());
            }

            this.af = this.av / 2;
        }
    }

    public abstract int aW();

    public int aX() {
        return this.aS;
    }

    public void b(int i0) {
        this.aS = i0;
        if (i0 > this.aW()) {
            i0 = this.aW();
        }
    }

    @Override
    public boolean a(DamageSource damagesource, int i0) {
        if (this.aq()) {
            return false;
        } else if (this.q.I) {
            return false;
        } else {
            this.bC = 0;
            if (this.aS <= 0) {
                return false;
            } else if (damagesource.m() && this.a(Potion.n)) {
                return false;
            } else {
                if ((damagesource == DamageSource.m || damagesource == DamageSource.n) && this.p(4) != null) {
                    this.p(4).a(i0 * 4 + this.ab.nextInt(i0 * 2), this);
                    i0 = (int) ((float) i0 * 0.75F);
                }

                this.bi = 1.5F;
                boolean flag0 = true;

                // CanaryMod: call DamageHook (Entity)
                CanaryEntityLiving attacker = null;

                if (damagesource instanceof EntityDamageSource && ((EntityDamageSource) damagesource).h() instanceof EntityLiving) {
                    attacker = (CanaryEntityLiving) ((EntityDamageSource) damagesource).h().getCanaryEntity();
                }
                DamageHook hook = new DamageHook(attacker, entity, new CanaryDamageSource(damagesource), i0);

                if ((float) this.af > (float) this.av / 2.0F) {
                    if (i0 <= this.bB) {
                        return false;
                    }

                    hook.setDamageDealt(i0 - this.bB);
                    if (attacker != null) {
                        Canary.hooks().callHook(hook);
                    }
                    if (hook.isCanceled()) {
                        if (this instanceof EntityCreature) {
                            ((EntityCreature) this).c = 0;
                        }
                        return false;
                    }

                    this.d((((CanaryDamageSource) hook.getDamageSource()).getHandle()), hook.getDamageDealt());
                    this.bB = i0;
                    flag0 = false;
                } else {
                    if (attacker != null) {
                        Canary.hooks().callHook(hook);
                    }
                    if (hook.isCanceled()) {
                        if (this instanceof EntityCreature) {
                            ((EntityCreature) this).c = 0;
                        }
                        return false;
                    }
                    this.bB = i0;
                    this.aT = this.aS;
                    this.af = this.av;
                    this.d((((CanaryDamageSource) hook.getDamageSource()).getHandle()), hook.getDamageDealt());
                    this.aW = this.aX = 10;
                }
                //

                this.aY = 0.0F;
                Entity entity = damagesource.i();

                if (entity != null) {
                    if (entity instanceof EntityLiving) {
                        this.c((EntityLiving) entity);
                    }

                    if (entity instanceof EntityPlayer) {
                        this.bl = 100;
                        this.bk = (EntityPlayer) entity;
                    } else if (entity instanceof EntityWolf) {
                        EntityWolf entitywolf = (EntityWolf) entity;

                        if (entitywolf.m()) {
                            this.bl = 100;
                            this.bk = null;
                        }
                    }
                }

                if (flag0) {
                    this.q.a((Entity) this, (byte) 2);
                    if (damagesource != DamageSource.e) {
                        this.J();
                    }

                    if (entity != null) {
                        double d0 = entity.u - this.u;

                        double d1;

                        for (d1 = entity.w - this.w; d0 * d0 + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D) {
                            d0 = (Math.random() - Math.random()) * 0.01D;
                        }

                        this.aY = (float) (Math.atan2(d1, d0) * 180.0D / 3.1415927410125732D) - this.A;
                        this.a(entity, i0, d0, d1);
                    } else {
                        this.aY = (float) ((int) (Math.random() * 2.0D) * 180);
                    }
                }

                if (this.aS <= 0) {
                    if (flag0) {
                        this.a(this.bd(), this.ba(), this.aY());
                    }

                    this.a(damagesource);
                } else if (flag0) {
                    this.a(this.bc(), this.ba(), this.aY());
                }

                return true;
            }
        }
    }

    protected float aY() {
        return this.h_() ? (this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.5F : (this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F;
    }

    public int aZ() {
        int i0 = 0;
        ItemStack[] aitemstack = this.ad();
        int i1 = aitemstack.length;

        for (int i2 = 0; i2 < i1; ++i2) {
            ItemStack itemstack = aitemstack[i2];

            if (itemstack != null && itemstack.b() instanceof ItemArmor) {
                int i3 = ((ItemArmor) itemstack.b()).c;

                i0 += i3;
            }
        }

        return i0;
    }

    protected void k(int i0) {}

    protected int b(DamageSource damagesource, int i0) {
        if (!damagesource.e()) {
            int i1 = 25 - this.aZ();
            int i2 = i0 * i1 + this.aU;

            this.k(i0);
            i0 = i2 / 25;
            this.aU = i2 % 25;
        }

        return i0;
    }

    protected int c(DamageSource damagesource, int i0) {
        int i1;
        int i2;
        int i3;

        if (this.a(Potion.m)) {
            i1 = (this.b(Potion.m).c() + 1) * 5;
            i2 = 25 - i1;
            i3 = i0 * i2 + this.aU;
            i0 = i3 / 25;
            this.aU = i3 % 25;
        }

        if (i0 <= 0) {
            return 0;
        } else {
            i1 = EnchantmentHelper.a(this.ad(), damagesource);
            if (i1 > 20) {
                i1 = 20;
            }

            if (i1 > 0 && i1 <= 20) {
                i2 = 25 - i1;
                i3 = i0 * i2 + this.aU;
                i0 = i3 / 25;
                this.aU = i3 % 25;
            }

            return i0;
        }
    }

    protected void d(DamageSource damagesource, int i0) {
        if (!this.aq()) {
            i0 = this.b(damagesource, i0);
            i0 = this.c(damagesource, i0);
            int i1 = this.aX();

            this.aS -= i0;
            this.bt.a(damagesource, i1, i0);
        }
    }

    protected float ba() {
        return 1.0F;
    }

    protected String bb() {
        return null;
    }

    protected String bc() {
        return "damage.hit";
    }

    protected String bd() {
        return "damage.hit";
    }

    public void a(Entity entity, int i0, double d0, double d1) {
        this.an = true;
        float f0 = MathHelper.a(d0 * d0 + d1 * d1);
        float f1 = 0.4F;

        this.x /= 2.0D;
        this.y /= 2.0D;
        this.z /= 2.0D;
        this.x -= d0 / (double) f0 * (double) f1;
        this.y += (double) f1;
        this.z -= d1 / (double) f0 * (double) f1;
        if (this.y > 0.4000000059604645D) {
            this.y = 0.4000000059604645D;
        }
    }

    public void a(DamageSource damagesource) {
        Entity entity = damagesource.i();
        EntityLiving entityliving = this.bN();

        if (this.aM >= 0 && entityliving != null) {
            entityliving.c(this, this.aM);
        }

        if (entity != null) {
            entity.a(this);
        }

        this.bd = true;
        if (!this.q.I) {
            int i0 = 0;

            if (entity instanceof EntityPlayer) {
                i0 = EnchantmentHelper.g((EntityLiving) entity);
            }

            if (!this.h_() && this.q.M().b("doMobLoot")) {
                this.a(this.bl > 0, i0);
                this.b(this.bl > 0, i0);
                if (this.bl > 0) {
                    int i1 = this.ab.nextInt(200) - i0;

                    if (i1 < 5) {
                        this.l(i1 <= 0 ? 1 : 0);
                    }
                }
            }
        }

        this.q.a((Entity) this, (byte) 3);
    }

    protected void l(int i0) {}

    protected void a(boolean flag0, int i0) {
        int i1 = this.be();

        if (i1 > 0) {
            int i2 = this.ab.nextInt(3);

            if (i0 > 0) {
                i2 += this.ab.nextInt(i0 + 1);
            }

            for (int i3 = 0; i3 < i2; ++i3) {
                this.b(i1, 1);
            }
        }
    }

    protected int be() {
        return 0;
    }

    @Override
    protected void a(float f0) {
        super.a(f0);
        int i0 = MathHelper.f(f0 - 3.0F);

        if (i0 > 0) {
            // CanaryMod: call DamageHook (Fall)
            DamageHook hook = new DamageHook(null, entity, new CanaryDamageSource(DamageSource.h), i0);

            Canary.hooks().callHook(hook);
            if (!hook.isCanceled()) {
                if (hook.getDamageDealt() > 4) {
                    this.a("damage.fallbig", 1.0F, 1.0F);
                } else {
                    this.a("damage.fallsmall", 1.0F, 1.0F);
                }

                this.a((((CanaryDamageSource) hook.getDamageSource()).getHandle()), hook.getDamageDealt());
            }
            //

            int i1 = this.q.a(MathHelper.c(this.u), MathHelper.c(this.v - 0.20000000298023224D - (double) this.N), MathHelper.c(this.w));

            if (i1 > 0) {
                StepSound stepsound = Block.r[i1].cM;

                this.a(stepsound.e(), stepsound.c() * 0.5F, stepsound.d() * 0.75F);
            }
        }
    }

    public void e(float f0, float f1) {
        double d0;

        if (this.G() && (!(this instanceof EntityPlayer) || !((EntityPlayer) this).ce.b)) {
            d0 = this.v;
            this.a(f0, f1, this.bh() ? 0.04F : 0.02F);
            this.d(this.x, this.y, this.z);
            this.x *= 0.800000011920929D;
            this.y *= 0.800000011920929D;
            this.z *= 0.800000011920929D;
            this.y -= 0.02D;
            if (this.G && this.c(this.x, this.y + 0.6000000238418579D - this.v + d0, this.z)) {
                this.y = 0.30000001192092896D;
            }
        } else if (this.I() && (!(this instanceof EntityPlayer) || !((EntityPlayer) this).ce.b)) {
            d0 = this.v;
            this.a(f0, f1, 0.02F);
            this.d(this.x, this.y, this.z);
            this.x *= 0.5D;
            this.y *= 0.5D;
            this.z *= 0.5D;
            this.y -= 0.02D;
            if (this.G && this.c(this.x, this.y + 0.6000000238418579D - this.v + d0, this.z)) {
                this.y = 0.30000001192092896D;
            }
        } else {
            float f2 = 0.91F;

            if (this.F) {
                f2 = 0.54600006F;
                int i0 = this.q.a(MathHelper.c(this.u), MathHelper.c(this.E.b) - 1, MathHelper.c(this.w));

                if (i0 > 0) {
                    f2 = Block.r[i0].cP * 0.91F;
                }
            }

            float f3 = 0.16277136F / (f2 * f2 * f2);
            float f4;

            if (this.F) {
                if (this.bh()) {
                    f4 = this.aI();
                } else {
                    f4 = this.aO;
                }

                f4 *= f3;
            } else {
                f4 = this.aP;
            }

            this.a(f0, f1, f4);
            f2 = 0.91F;
            if (this.F) {
                f2 = 0.54600006F;
                int i1 = this.q.a(MathHelper.c(this.u), MathHelper.c(this.E.b) - 1, MathHelper.c(this.w));

                if (i1 > 0) {
                    f2 = Block.r[i1].cP * 0.91F;
                }
            }

            if (this.g_()) {
                float f5 = 0.15F;

                if (this.x < (double) (-f5)) {
                    this.x = (double) (-f5);
                }

                if (this.x > (double) f5) {
                    this.x = (double) f5;
                }

                if (this.z < (double) (-f5)) {
                    this.z = (double) (-f5);
                }

                if (this.z > (double) f5) {
                    this.z = (double) f5;
                }

                this.T = 0.0F;
                if (this.y < -0.15D) {
                    this.y = -0.15D;
                }

                boolean flag0 = this.ag() && this instanceof EntityPlayer;

                if (flag0 && this.y < 0.0D) {
                    this.y = 0.0D;
                }
            }

            this.d(this.x, this.y, this.z);
            if (this.G && this.g_()) {
                this.y = 0.2D;
            }

            if (this.q.I && (!this.q.f((int) this.u, 0, (int) this.w) || !this.q.d((int) this.u, (int) this.w).d)) {
                if (this.v > 0.0D) {
                    this.y = -0.1D;
                } else {
                    this.y = 0.0D;
                }
            } else {
                this.y -= 0.08D;
            }

            this.y *= 0.9800000190734863D;
            this.x *= (double) f2;
            this.z *= (double) f2;
        }

        this.bh = this.bi;
        d0 = this.u - this.r;
        double d1 = this.w - this.t;
        float f6 = MathHelper.a(d0 * d0 + d1 * d1) * 4.0F;

        if (f6 > 1.0F) {
            f6 = 1.0F;
        }

        this.bi += (f6 - this.bi) * 0.4F;
        this.bj += this.bi;
    }

    public boolean g_() {
        int i0 = MathHelper.c(this.u);
        int i1 = MathHelper.c(this.E.b);
        int i2 = MathHelper.c(this.w);
        int i3 = this.q.a(i0, i1, i2);

        return i3 == Block.aJ.cz || i3 == Block.by.cz;
    }

    @Override
    public void b(NBTTagCompound nbttagcompound) {
        if (this.aS < -32768) {
            this.aS = -32768;
        }

        nbttagcompound.a("Health", (short) this.aS);
        nbttagcompound.a("HurtTime", (short) this.aW);
        nbttagcompound.a("DeathTime", (short) this.aZ);
        nbttagcompound.a("AttackTime", (short) this.ba);
        nbttagcompound.a("CanPickUpLoot", this.bS());
        nbttagcompound.a("PersistenceRequired", this.bW);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i0 = 0; i0 < this.bT.length; ++i0) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();

            if (this.bT[i0] != null) {
                this.bT[i0].b(nbttagcompound1);
            }

            nbttaglist.a((NBTBase) nbttagcompound1);
        }

        nbttagcompound.a("Equipment", (NBTBase) nbttaglist);
        NBTTagList nbttaglist1;

        if (!this.bn.isEmpty()) {
            nbttaglist1 = new NBTTagList();
            Iterator iterator = this.bn.values().iterator();

            while (iterator.hasNext()) {
                PotionEffect potioneffect = (PotionEffect) iterator.next();

                nbttaglist1.a((NBTBase) potioneffect.a(new NBTTagCompound()));
            }

            nbttagcompound.a("ActiveEffects", (NBTBase) nbttaglist1);
        }

        nbttaglist1 = new NBTTagList();

        for (int i1 = 0; i1 < this.bq.length; ++i1) {
            nbttaglist1.a((NBTBase) (new NBTTagFloat(i1 + "", this.bq[i1])));
        }

        nbttagcompound.a("DropChances", (NBTBase) nbttaglist1);
        nbttagcompound.a("CustomName", this.bO());
        nbttagcompound.a("CustomNameVisible", this.bQ());
    }

    @Override
    public void a(NBTTagCompound nbttagcompound) {
        this.aS = nbttagcompound.d("Health");
        if (!nbttagcompound.b("Health")) {
            this.aS = this.aW();
        }

        this.aW = nbttagcompound.d("HurtTime");
        this.aZ = nbttagcompound.d("DeathTime");
        this.ba = nbttagcompound.d("AttackTime");
        this.h(nbttagcompound.n("CanPickUpLoot"));
        this.bW = nbttagcompound.n("PersistenceRequired");
        if (nbttagcompound.b("CustomName") && nbttagcompound.i("CustomName").length() > 0) {
            this.c(nbttagcompound.i("CustomName"));
        }

        this.g(nbttagcompound.n("CustomNameVisible"));
        NBTTagList nbttaglist;
        int i0;

        if (nbttagcompound.b("Equipment")) {
            nbttaglist = nbttagcompound.m("Equipment");

            for (i0 = 0; i0 < this.bT.length; ++i0) {
                this.bT[i0] = ItemStack.a((NBTTagCompound) nbttaglist.b(i0));
            }
        }

        if (nbttagcompound.b("ActiveEffects")) {
            nbttaglist = nbttagcompound.m("ActiveEffects");

            for (i0 = 0; i0 < nbttaglist.c(); ++i0) {
                NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.b(i0);
                PotionEffect potioneffect = PotionEffect.b(nbttagcompound1);

                this.bn.put(Integer.valueOf(potioneffect.a()), potioneffect);
            }
        }

        if (nbttagcompound.b("DropChances")) {
            nbttaglist = nbttagcompound.m("DropChances");

            for (i0 = 0; i0 < nbttaglist.c(); ++i0) {
                this.bq[i0] = ((NBTTagFloat) nbttaglist.b(i0)).a;
            }
        }
    }

    @Override
    public boolean R() {
        return !this.M && this.aS > 0;
    }

    public boolean bf() {
        return false;
    }

    public void f(float f0) {
        this.bE = f0;
    }

    public void f(boolean flag0) {
        this.bG = flag0;
    }

    public void c() {
        if (this.bX > 0) {
            --this.bX;
        }

        if (this.bu > 0) {
            double d0 = this.u + (this.bv - this.u) / (double) this.bu;
            double d1 = this.v + (this.bw - this.v) / (double) this.bu;
            double d2 = this.w + (this.bx - this.w) / (double) this.bu;
            double d3 = MathHelper.g(this.by - (double) this.A);

            this.A = (float) ((double) this.A + d3 / (double) this.bu);
            this.B = (float) ((double) this.B + (this.bz - (double) this.B) / (double) this.bu);
            --this.bu;
            this.b(d0, d1, d2);
            this.b(this.A, this.B);
        } else if (!this.bi()) {
            this.x *= 0.98D;
            this.y *= 0.98D;
            this.z *= 0.98D;
        }

        if (Math.abs(this.x) < 0.005D) {
            this.x = 0.0D;
        }

        if (Math.abs(this.y) < 0.005D) {
            this.y = 0.0D;
        }

        if (Math.abs(this.z) < 0.005D) {
            this.z = 0.0D;
        }

        this.q.C.a("ai");
        if (this.bj()) {
            this.bG = false;
            this.bD = 0.0F;
            this.bE = 0.0F;
            this.bF = 0.0F;
        } else if (this.bi()) {
            if (this.bh()) {
                this.q.C.a("newAi");
                this.bo();
                this.q.C.b();
            } else {
                this.q.C.a("oldAi");
                this.bq();
                this.q.C.b();
                this.aA = this.A;
            }
        }

        this.q.C.b();
        this.q.C.a("jump");
        if (this.bG) {
            if (!this.G() && !this.I()) {
                if (this.F && this.bX == 0) {
                    this.bl();
                    this.bX = 10;
                }
            } else {
                this.y += 0.03999999910593033D;
            }
        } else {
            this.bX = 0;
        }

        this.q.C.b();
        this.q.C.a("travel");
        this.bD *= 0.98F;
        this.bE *= 0.98F;
        this.bF *= 0.9F;
        float f0 = this.aO;

        this.aO *= this.bE();
        this.e(this.bD, this.bE);
        this.aO = f0;
        this.q.C.b();
        this.q.C.a("push");
        if (!this.q.I) {
            this.bg();
        }

        this.q.C.b();
        this.q.C.a("looting");
        if (!this.q.I && this.bS() && !this.bd && this.q.M().b("mobGriefing")) {
            List list = this.q.a(EntityItem.class, this.E.b(1.0D, 0.0D, 1.0D));
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                EntityItem entityitem = (EntityItem) iterator.next();

                if (!entityitem.M && entityitem.d() != null) {
                    ItemStack itemstack = entityitem.d();
                    int i0 = b(itemstack);

                    if (i0 > -1) {
                        boolean flag0 = true;
                        ItemStack itemstack1 = this.p(i0);

                        if (itemstack1 != null) {
                            if (i0 == 0) {
                                if (itemstack.b() instanceof ItemSword && !(itemstack1.b() instanceof ItemSword)) {
                                    flag0 = true;
                                } else if (itemstack.b() instanceof ItemSword && itemstack1.b() instanceof ItemSword) {
                                    ItemSword itemsword = (ItemSword) itemstack.b();
                                    ItemSword itemsword1 = (ItemSword) itemstack1.b();

                                    if (itemsword.g() == itemsword1.g()) {
                                        flag0 = itemstack.k() > itemstack1.k() || itemstack.p() && !itemstack1.p();
                                    } else {
                                        flag0 = itemsword.g() > itemsword1.g();
                                    }
                                } else {
                                    flag0 = false;
                                }
                            } else if (itemstack.b() instanceof ItemArmor && !(itemstack1.b() instanceof ItemArmor)) {
                                flag0 = true;
                            } else if (itemstack.b() instanceof ItemArmor && itemstack1.b() instanceof ItemArmor) {
                                ItemArmor itemarmor = (ItemArmor) itemstack.b();
                                ItemArmor itemarmor1 = (ItemArmor) itemstack1.b();

                                if (itemarmor.c == itemarmor1.c) {
                                    flag0 = itemstack.k() > itemstack1.k() || itemstack.p() && !itemstack1.p();
                                } else {
                                    flag0 = itemarmor.c > itemarmor1.c;
                                }
                            } else {
                                flag0 = false;
                            }
                        }

                        if (flag0) {
                            if (itemstack1 != null && this.ab.nextFloat() - 0.1F < this.bq[i0]) {
                                this.a(itemstack1, 0.0F);
                            }

                            this.c(i0, itemstack);
                            this.bq[i0] = 2.0F;
                            this.bW = true;
                            this.a((Entity) entityitem, 1);
                            entityitem.w();
                        }
                    }
                }
            }
        }

        this.q.C.b();
    }

    protected void bg() {
        List list = this.q.b((Entity) this, this.E.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));

        if (list != null && !list.isEmpty()) {
            for (int i0 = 0; i0 < list.size(); ++i0) {
                Entity entity = (Entity) list.get(i0);

                if (entity.L()) {
                    this.o(entity);
                }
            }
        }
    }

    protected void o(Entity entity) {
        entity.f((Entity) this);
    }

    protected boolean bh() {
        return false;
    }

    protected boolean bi() {
        return !this.q.I;
    }

    protected boolean bj() {
        return this.aS <= 0;
    }

    public boolean bk() {
        return false;
    }

    protected void bl() {
        this.y = 0.41999998688697815D;
        if (this.a(Potion.j)) {
            this.y += (double) ((float) (this.b(Potion.j).c() + 1) * 0.1F);
        }

        if (this.ah()) {
            float f0 = this.A * 0.017453292F;

            this.x -= (double) (MathHelper.a(f0) * 0.2F);
            this.z += (double) (MathHelper.b(f0) * 0.2F);
        }

        this.an = true;
    }

    protected boolean bm() {
        // CanaryMod: EntityDespawn
        EntityDespawnHook hook = new EntityDespawnHook(entity);

        Canary.hooks().callHook(hook);
        return !hook.isCanceled();
        //
    }

    protected void bn() {
        if (!this.bW) {
            EntityPlayer entityplayer = this.q.a(this, -1.0D);

            if (entityplayer != null) {
                double d0 = entityplayer.u - this.u;
                double d1 = entityplayer.v - this.v;
                double d2 = entityplayer.w - this.w;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;

                if (this.bm() && d3 > 16384.0D) {
                    this.w();
                }

                if (this.bC > 600 && this.ab.nextInt(800) == 0 && d3 > 1024.0D && this.bm()) {
                    this.w();
                } else if (d3 < 1024.0D) {
                    this.bC = 0;
                }
            }
        }
    }

    protected void bo() {
        ++this.bC;
        this.q.C.a("checkDespawn");
        this.bn();
        this.q.C.b();
        this.q.C.a("sensing");
        this.bP.a();
        this.q.C.b();
        this.q.C.a("targetSelector");
        this.bp.a();
        this.q.C.b();
        this.q.C.a("goalSelector");
        this.bo.a();
        this.q.C.b();
        this.q.C.a("navigation");
        this.bN.e();
        this.q.C.b();
        this.q.C.a("mob tick");
        this.bp();
        this.q.C.b();
        this.q.C.a("controls");
        this.q.C.a("move");
        this.bK.c();
        this.q.C.c("look");
        this.j.a();
        this.q.C.c("jump");
        this.bL.b();
        this.q.C.b();
        this.q.C.b();
    }

    protected void bp() {}

    protected void bq() {
        ++this.bC;
        this.bn();
        this.bD = 0.0F;
        this.bE = 0.0F;
        float f0 = 8.0F;

        if (this.ab.nextFloat() < 0.02F) {
            EntityPlayer entityplayer = this.q.a(this, (double) f0);

            if (entityplayer != null) {
                this.bY = entityplayer;
                this.bJ = 10 + this.ab.nextInt(20);
            } else {
                this.bF = (this.ab.nextFloat() - 0.5F) * 20.0F;
            }
        }

        if (this.bY != null) {
            this.a(this.bY, 10.0F, (float) this.bs());
            if (this.bJ-- <= 0 || this.bY.M || this.bY.e((Entity) this) > (double) (f0 * f0)) {
                this.bY = null;
            }
        } else {
            if (this.ab.nextFloat() < 0.05F) {
                this.bF = (this.ab.nextFloat() - 0.5F) * 20.0F;
            }

            this.A += this.bF;
            this.B = this.bH;
        }

        boolean flag0 = this.G();
        boolean flag1 = this.I();

        if (flag0 || flag1) {
            this.bG = this.ab.nextFloat() < 0.8F;
        }
    }

    protected void br() {
        int i0 = this.h();

        if (this.br) {
            ++this.bs;
            if (this.bs >= i0) {
                this.bs = 0;
                this.br = false;
            }
        } else {
            this.bs = 0;
        }

        this.aR = (float) this.bs / (float) i0;
    }

    public int bs() {
        return 40;
    }

    public void a(Entity entity, float f0, float f1) {
        double d0 = entity.u - this.u;
        double d1 = entity.w - this.w;
        double d2;

        if (entity instanceof EntityLiving) {
            EntityLiving entityliving = (EntityLiving) entity;

            d2 = entityliving.v + (double) entityliving.e() - (this.v + (double) this.e());
        } else {
            d2 = (entity.E.b + entity.E.e) / 2.0D - (this.v + (double) this.e());
        }

        double d3 = (double) MathHelper.a(d0 * d0 + d1 * d1);
        float f2 = (float) (Math.atan2(d1, d0) * 180.0D / 3.1415927410125732D) - 90.0F;
        float f3 = (float) (-(Math.atan2(d2, d3) * 180.0D / 3.1415927410125732D));

        this.B = this.b(this.B, f3, f1);
        this.A = this.b(this.A, f2, f0);
    }

    private float b(float f0, float f1, float f2) {
        float f3 = MathHelper.g(f1 - f0);

        if (f3 > f2) {
            f3 = f2;
        }

        if (f3 < -f2) {
            f3 = -f2;
        }

        return f0 + f3;
    }

    public boolean bv() {
        return this.q.b(this.E) && this.q.a((Entity) this, this.E).isEmpty() && !this.q.d(this.E);
    }

    @Override
    protected void B() {
        this.a(DamageSource.i, 4);
    }

    @Override
    public Vec3 Y() {
        return this.i(1.0F);
    }

    public Vec3 i(float f0) {
        float f1;
        float f2;
        float f3;
        float f4;

        if (f0 == 1.0F) {
            f1 = MathHelper.b(-this.A * 0.017453292F - 3.1415927F);
            f2 = MathHelper.a(-this.A * 0.017453292F - 3.1415927F);
            f3 = -MathHelper.b(-this.B * 0.017453292F);
            f4 = MathHelper.a(-this.B * 0.017453292F);
            return this.q.T().a((double) (f2 * f3), (double) f4, (double) (f1 * f3));
        } else {
            f1 = this.D + (this.B - this.D) * f0;
            f2 = this.C + (this.A - this.C) * f0;
            f3 = MathHelper.b(-f2 * 0.017453292F - 3.1415927F);
            f4 = MathHelper.a(-f2 * 0.017453292F - 3.1415927F);
            float f5 = -MathHelper.b(-f1 * 0.017453292F);
            float f6 = MathHelper.a(-f1 * 0.017453292F);

            return this.q.T().a((double) (f4 * f5), (double) f6, (double) (f3 * f5));
        }
    }

    public int by() {
        return 4;
    }

    public boolean bz() {
        return false;
    }

    protected void bA() {
        Iterator iterator = this.bn.keySet().iterator();

        while (iterator.hasNext()) {
            Integer integer = (Integer) iterator.next();
            PotionEffect potioneffect = (PotionEffect) this.bn.get(integer);

            try {
                if (!potioneffect.a(this)) {
                    if (!this.q.I) {
                        iterator.remove();
                        this.c(potioneffect);
                    }
                } else if (potioneffect.b() % 600 == 0) {
                    this.b(potioneffect);
                }
            } catch (Throwable throwable) {
                CrashReport crashreport = CrashReport.a(throwable, "Ticking mob effect instance");
                CrashReportCategory crashreportcategory = crashreport.a("Mob effect being ticked");

                crashreportcategory.a("Effect Name", (Callable) (new CallableEffectName(this, potioneffect)));
                crashreportcategory.a("Effect ID", (Callable) (new CallableEffectID(this, potioneffect)));
                crashreportcategory.a("Effect Duration", (Callable) (new CallableEffectDuration(this, potioneffect)));
                crashreportcategory.a("Effect Amplifier", (Callable) (new CallableEffectAmplifier(this, potioneffect)));
                crashreportcategory.a("Effect is Splash", (Callable) (new CallableEffectIsSplash(this, potioneffect)));
                crashreportcategory.a("Effect is Ambient", (Callable) (new CallableEffectIsAmbient(this, potioneffect)));
                throw new ReportedException(crashreport);
            }
        }

        int i0;

        if (this.h) {
            if (!this.q.I) {
                if (this.bn.isEmpty()) {
                    this.ah.b(9, Byte.valueOf((byte) 0));
                    this.ah.b(8, Integer.valueOf(0));
                    this.d(false);
                } else {
                    i0 = PotionHelper.a(this.bn.values());
                    this.ah.b(9, Byte.valueOf((byte) (PotionHelper.b(this.bn.values()) ? 1 : 0)));
                    this.ah.b(8, Integer.valueOf(i0));
                    this.d(this.m(Potion.p.H));
                }
            }

            this.h = false;
        }

        i0 = this.ah.c(8);
        boolean flag0 = this.ah.a(9) > 0;

        if (i0 > 0) {
            boolean flag1 = false;

            if (!this.ai()) {
                flag1 = this.ab.nextBoolean();
            } else {
                flag1 = this.ab.nextInt(15) == 0;
            }

            if (flag0) {
                flag1 &= this.ab.nextInt(5) == 0;
            }

            if (flag1 && i0 > 0) {
                double d0 = (double) (i0 >> 16 & 255) / 255.0D;
                double d1 = (double) (i0 >> 8 & 255) / 255.0D;
                double d2 = (double) (i0 >> 0 & 255) / 255.0D;

                this.q.a(flag0 ? "mobSpellAmbient" : "mobSpell", this.u + (this.ab.nextDouble() - 0.5D) * (double) this.O, this.v + this.ab.nextDouble() * (double) this.P - (double) this.N, this.w + (this.ab.nextDouble() - 0.5D) * (double) this.O, d0, d1, d2);
            }
        }
    }

    public void bB() {
        Iterator iterator = this.bn.keySet().iterator();

        while (iterator.hasNext()) {
            Integer integer = (Integer) iterator.next();
            PotionEffect potioneffect = (PotionEffect) this.bn.get(integer);

            if (!this.q.I) {
                iterator.remove();
                this.c(potioneffect);
            }
        }
    }

    public Collection bC() {
        return this.bn.values();
    }

    public boolean m(int i0) {
        return this.bn.containsKey(Integer.valueOf(i0));
    }

    public boolean a(Potion potion) {
        return this.bn.containsKey(Integer.valueOf(potion.H));
    }

    public PotionEffect b(Potion potion) {
        return (PotionEffect) this.bn.get(Integer.valueOf(potion.H));
    }

    public void d(PotionEffect potioneffect) {
        // CanaryMod: PotionEffectApplied
        PotionEffectAppliedHook hook = new PotionEffectAppliedHook((net.canarymod.api.entity.living.EntityLiving) getCanaryEntity(), new CanaryPotionEffect(potioneffect));
        Canary.hooks().callHook(hook);
        if (hook.getPotionEffect() == null) {
            return;
        }
        potioneffect = ((CanaryPotionEffect) hook.getPotionEffect()).getHandle();
        //
        if (this.e(potioneffect)) {
            if (this.bn.containsKey(Integer.valueOf(potioneffect.a()))) {
                ((PotionEffect) this.bn.get(Integer.valueOf(potioneffect.a()))).a(potioneffect);
                this.b((PotionEffect) this.bn.get(Integer.valueOf(potioneffect.a())));
            } else {
                this.bn.put(Integer.valueOf(potioneffect.a()), potioneffect);
                this.a(potioneffect);
            }
        }
    }

    public boolean e(PotionEffect potioneffect) {
        if (this.bF() == EnumCreatureAttribute.b) {
            int i0 = potioneffect.a();

            if (i0 == Potion.l.H || i0 == Potion.u.H) {
                return false;
            }
        }

        return true;
    }

    public boolean bD() {
        return this.bF() == EnumCreatureAttribute.b;
    }

    public void o(int i0) {
        PotionEffect potioneffect = (PotionEffect) this.bn.remove(Integer.valueOf(i0));

        if (potioneffect != null) {
            this.c(potioneffect);
        }
    }

    protected void a(PotionEffect potioneffect) {
        this.h = true;
    }

    protected void b(PotionEffect potioneffect) {
        this.h = true;
    }

    protected void c(PotionEffect potioneffect) {
        // CanaryMod: PotionEffectFinish
        PotionEffectFinishHook hook = new PotionEffectFinishHook((net.canarymod.api.entity.living.EntityLiving) getCanaryEntity(), new CanaryPotionEffect(potioneffect));
        Canary.hooks().callHook(hook);
        //
        this.h = true;
    }

    public float bE() {
        float f0 = 1.0F;

        if (this.a(Potion.c)) {
            f0 *= 1.0F + 0.2F * (float) (this.b(Potion.c).c() + 1);
        }

        if (this.a(Potion.d)) {
            f0 *= 1.0F - 0.15F * (float) (this.b(Potion.d).c() + 1);
        }

        if (f0 < 0.0F) {
            f0 = 0.0F;
        }

        return f0;
    }

    public void a(double d0, double d1, double d2) {
        this.b(d0, d1, d2, this.A, this.B);
    }

    public boolean h_() {
        return false;
    }

    public EnumCreatureAttribute bF() {
        return EnumCreatureAttribute.a;
    }

    public void a(ItemStack itemstack) {
        this.a("random.break", 0.8F, 0.8F + this.q.s.nextFloat() * 0.4F);

        for (int i0 = 0; i0 < 5; ++i0) {
            Vec3 vec3 = this.q.T().a(((double) this.ab.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);

            vec3.a(-this.B * 3.1415927F / 180.0F);
            vec3.b(-this.A * 3.1415927F / 180.0F);
            Vec3 vec31 = this.q.T().a(((double) this.ab.nextFloat() - 0.5D) * 0.3D, (double) (-this.ab.nextFloat()) * 0.6D - 0.3D, 0.6D);

            vec31.a(-this.B * 3.1415927F / 180.0F);
            vec31.b(-this.A * 3.1415927F / 180.0F);
            vec31 = vec31.c(this.u, this.v + (double) this.e(), this.w);
            this.q.a("iconcrack_" + itemstack.b().cp, vec31.c, vec31.d, vec31.e, vec3.c, vec3.d + 0.05D, vec3.e);
        }
    }

    @Override
    public int ar() {
        if (this.aJ() == null) {
            return 3;
        } else {
            int i0 = (int) ((float) this.aS - (float) this.aW() * 0.33F);

            i0 -= (3 - this.q.r) * 4;
            if (i0 < 0) {
                i0 = 0;
            }

            return i0 + 3;
        }
    }

    public ItemStack bG() {
        return this.bT[0];
    }

    public ItemStack p(int i0) {
        return this.bT[i0];
    }

    public ItemStack q(int i0) {
        return this.bT[i0 + 1];
    }

    @Override
    public void c(int i0, ItemStack itemstack) {
        this.bT[i0] = itemstack;
    }

    @Override
    public ItemStack[] ad() {
        return this.bT;
    }

    protected void b(boolean flag0, int i0) {
        for (int i1 = 0; i1 < this.ad().length; ++i1) {
            ItemStack itemstack = this.p(i1);
            boolean flag1 = this.bq[i1] > 1.0F;

            if (itemstack != null && (flag0 || flag1) && this.ab.nextFloat() - (float) i0 * 0.01F < this.bq[i1]) {
                if (!flag1 && itemstack.g()) {
                    int i2 = Math.max(itemstack.l() - 25, 1);
                    int i3 = itemstack.l() - this.ab.nextInt(this.ab.nextInt(i2) + 1);

                    if (i3 > i2) {
                        i3 = i2;
                    }

                    if (i3 < 1) {
                        i3 = 1;
                    }

                    itemstack.b(i3);
                }

                this.a(itemstack, 0.0F);
            }
        }
    }

    protected void bH() {
        if (this.ab.nextFloat() < d[this.q.r]) {
            int i0 = this.ab.nextInt(2);
            float f0 = this.q.r == 3 ? 0.1F : 0.25F;

            if (this.ab.nextFloat() < 0.095F) {
                ++i0;
            }

            if (this.ab.nextFloat() < 0.095F) {
                ++i0;
            }

            if (this.ab.nextFloat() < 0.095F) {
                ++i0;
            }

            for (int i1 = 3; i1 >= 0; --i1) {
                ItemStack itemstack = this.q(i1);

                if (i1 < 3 && this.ab.nextFloat() < f0) {
                    break;
                }

                if (itemstack == null) {
                    Item item = a(i1 + 1, i0);

                    if (item != null) {
                        this.c(i1 + 1, new ItemStack(item));
                    }
                }
            }
        }
    }

    public void a(Entity entity, int i0) {
        if (!entity.M && !this.q.I) {
            EntityTracker entitytracker = ((WorldServer) this.q).p();

            if (entity instanceof EntityItem) {
                entitytracker.a(entity, (Packet) (new Packet22Collect(entity.k, this.k)));
            }

            if (entity instanceof EntityArrow) {
                entitytracker.a(entity, (Packet) (new Packet22Collect(entity.k, this.k)));
            }

            if (entity instanceof EntityXPOrb) {
                entitytracker.a(entity, (Packet) (new Packet22Collect(entity.k, this.k)));
            }
        }
    }

    public static int b(ItemStack itemstack) {
        if (itemstack.c != Block.be.cz && itemstack.c != Item.bR.cp) {
            if (itemstack.b() instanceof ItemArmor) {
                switch (((ItemArmor) itemstack.b()).b) {
                    case 0:
                        return 4;

                    case 1:
                        return 3;

                    case 2:
                        return 2;

                    case 3:
                        return 1;
                }
            }

            return 0;
        } else {
            return 4;
        }
    }

    public static Item a(int i0, int i1) {
        switch (i0) {
            case 4:
                if (i1 == 0) {
                    return Item.W;
                } else if (i1 == 1) {
                    return Item.am;
                } else if (i1 == 2) {
                    return Item.aa;
                } else if (i1 == 3) {
                    return Item.ae;
                } else if (i1 == 4) {
                    return Item.ai;
                }

            case 3:
                if (i1 == 0) {
                    return Item.X;
                } else if (i1 == 1) {
                    return Item.an;
                } else if (i1 == 2) {
                    return Item.ab;
                } else if (i1 == 3) {
                    return Item.af;
                } else if (i1 == 4) {
                    return Item.aj;
                }

            case 2:
                if (i1 == 0) {
                    return Item.Y;
                } else if (i1 == 1) {
                    return Item.ao;
                } else if (i1 == 2) {
                    return Item.ac;
                } else if (i1 == 3) {
                    return Item.ag;
                } else if (i1 == 4) {
                    return Item.ak;
                }

            case 1:
                if (i1 == 0) {
                    return Item.Z;
                } else if (i1 == 1) {
                    return Item.ap;
                } else if (i1 == 2) {
                    return Item.ad;
                } else if (i1 == 3) {
                    return Item.ah;
                } else if (i1 == 4) {
                    return Item.al;
                }

            default:
                return null;
        }
    }

    protected void bI() {
        if (this.bG() != null && this.ab.nextFloat() < b[this.q.r]) {
            EnchantmentHelper.a(this.ab, this.bG(), 5 + this.q.r * this.ab.nextInt(6));
        }

        for (int i0 = 0; i0 < 4; ++i0) {
            ItemStack itemstack = this.q(i0);

            if (itemstack != null && this.ab.nextFloat() < c[this.q.r]) {
                EnchantmentHelper.a(this.ab, itemstack, 5 + this.q.r * this.ab.nextInt(6));
            }
        }
    }

    public void bJ() {}

    private int h() {
        return this.a(Potion.e) ? 6 - (1 + this.b(Potion.e).c()) * 1 : (this.a(Potion.f) ? 6 + (1 + this.b(Potion.f).c()) * 2 : 6);
    }

    public void bK() {
        if (!this.br || this.bs >= this.h() / 2 || this.bs < 0) {
            this.bs = -1;
            this.br = true;
            if (this.q instanceof WorldServer) {
                ((WorldServer) this.q).p().a((Entity) this, (Packet) (new Packet18Animation(this, 1)));
            }
        }
    }

    public boolean bL() {
        return false;
    }

    public final int bM() {
        return this.ah.a(10);
    }

    public final void r(int i0) {
        this.ah.b(10, Byte.valueOf((byte) i0));
    }

    public EntityLiving bN() {
        return (EntityLiving) (this.bt.c() != null ? this.bt.c() : (this.bk != null ? this.bk : (this.e != null ? this.e : null)));
    }

    @Override
    public String am() {
        return this.bP() ? this.bO() : super.am();
    }

    public void c(String s0) {
        this.ah.b(5, s0);
    }

    public String bO() {
        return this.ah.e(5);
    }

    public boolean bP() {
        return this.ah.e(5).length() > 0;
    }

    public void g(boolean flag0) {
        this.ah.b(6, Byte.valueOf((byte) (flag0 ? 1 : 0)));
    }

    public boolean bQ() {
        return this.ah.a(6) == 1;
    }

    public void a(int i0, float f0) {
        this.bq[i0] = f0;
    }

    public boolean bS() {
        return this.bV;
    }

    public void h(boolean flag0) {
        this.bV = flag0;
    }

    // CanaryMod
    public void setMaxHealth(int maxHealth) {
        this.aS = maxHealth;
    }
}
