package net.minecraft.server;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.canarymod.Canary;
import net.canarymod.api.CanaryDamageSource;
import net.canarymod.api.entity.CanaryEntityLiving;
import net.canarymod.api.entity.EntityLiving;
import net.canarymod.hook.CancelableHook;
import net.canarymod.hook.entity.DamageHook;
import net.canarymod.hook.entity.EntitySpawnHook;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OChunkCoordinates;
import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEnchantmentHelper;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityAITasks;
import net.minecraft.server.OEntityBodyHelper;
import net.minecraft.server.OEntityCreeper;
import net.minecraft.server.OEntityGhast;
import net.minecraft.server.OEntityJumpHelper;
import net.minecraft.server.OEntityLookHelper;
import net.minecraft.server.OEntityMoveHelper;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntitySenses;
import net.minecraft.server.OEntityWolf;
import net.minecraft.server.OEntityXPOrb;
import net.minecraft.server.OEnumCreatureAttribute;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.ONBTTagList;
import net.minecraft.server.OPathNavigate;
import net.minecraft.server.OPotion;
import net.minecraft.server.OPotionEffect;
import net.minecraft.server.OPotionHelper;
import net.minecraft.server.OProfiler;
import net.minecraft.server.OStepSound;
import net.minecraft.server.OVec3D;
import net.minecraft.server.OWorld;

public abstract class OEntityLiving extends OEntity {

    public int S = 20;
    public float T;
    public float U;
    public float V = 0.0F;
    public float W = 0.0F;
    public float X = 0.0F;
    public float Y = 0.0F;
    protected float Z;
    protected float aa;
    protected float ab;
    protected float ac;
    protected boolean ad = true;
    protected String ae = "/mob/char.png";
    protected boolean af = true;
    protected float ag = 0.0F;
    protected String ah = null;
    protected float ai = 1.0F;
    protected int aj = 0;
    protected float ak = 0.0F;
    public float al = 0.1F;
    public float am = 0.02F;
    public float an;
    public float ao;
    protected int ap = this.d();
    public int aq;
    protected int ar;
    private int a;
    public int as;
    public int at;
    public float au = 0.0F;
    public int av = 0;
    public int aw = 0;
    public float ax;
    public float ay;
    protected boolean az = false;
    protected int aA;
    public int aB = -1;
    public float aC = (float) (Math.random() * 0.8999999761581421D + 0.10000000149011612D);
    public float aD;
    public float aE;
    public float aF;
    protected OEntityPlayer aG = null;
    protected int aH = 0;
    private OEntityLiving b = null;
    private int c = 0;
    private OEntityLiving d = null;
    public int aI = 0;
    public int aJ = 0;
    protected HashMap aK = new HashMap();
    private boolean e = true;
    private int f;
    private OEntityLookHelper g;
    private OEntityMoveHelper h;
    private OEntityJumpHelper i;
    private OEntityBodyHelper j;
    private OPathNavigate k;
    protected OEntityAITasks aL = new OEntityAITasks();
    protected OEntityAITasks aM = new OEntityAITasks();
    private OEntityLiving l;
    private OEntitySenses m;
    private float n;
    private OChunkCoordinates o = new OChunkCoordinates(0, 0, 0);
    private float p = -1.0F;
    protected int aN;
    protected double aO;
    protected double aP;
    protected double aQ;
    protected double aR;
    protected double aS;
    float aT = 0.0F;
    protected int aU = 0;
    public int aV = 0; //CanaryMod protected -> public  age
    protected float aW;
    protected float aX;
    protected float aY;
    protected boolean aZ = false;
    protected float ba = 0.0F;
    protected float bb = 0.7F;
    private int q = 0;
    private OEntity r;
    protected int bc = 0;
    private CanaryEntityLiving canaryEntityLiving;
    public OEntityLiving(OWorld var1) {
        super(var1);
        this.bf = true;
        this.g = new OEntityLookHelper(this);
        this.h = new OEntityMoveHelper(this);
        this.i = new OEntityJumpHelper(this);
        this.j = new OEntityBodyHelper(this);
        this.k = new OPathNavigate(this, var1, 16.0F);
        this.m = new OEntitySenses(this);
        this.U = (float) (Math.random() + 1.0D) * 0.01F;
        this.c(this.bm, this.bn, this.bo);
        this.T = (float) Math.random() * 12398.0F;
        this.bs = (float) (Math.random() * 3.1415927410125732D * 2.0D);
        this.X = this.bs;
        this.bP = 0.5F;
        canaryEntityLiving = new CanaryEntityLiving(this);
    }

    /**
     * CanaryMod Get entity living wrapper
     * @return the canaryEntityLiving
     */
    public CanaryEntityLiving getCanaryEntityLiving() {
        return canaryEntityLiving;
    }

    public OEntityLookHelper ai() {
        return this.g;
    }

    public OEntityMoveHelper aj() {
        return this.h;
    }

    public OEntityJumpHelper ak() {
        return this.i;
    }

    public OPathNavigate al() {
        return this.k;
    }

    public OEntitySenses am() {
        return this.m;
    }

    public Random an() {
        return this.bS;
    }

    public OEntityLiving ao() {
        return this.b;
    }

    public OEntityLiving ap() {
        return this.d;
    }

    public void g(OEntity var1) {
        if (var1 instanceof OEntityLiving) {
            this.d = (OEntityLiving) var1;
        }

    }

    public int aq() {
        return this.aV;
    }

    @Override
    public float ar() {
        return this.X;
    }

    public float as() {
        return this.n;
    }

    public void d(float var1) {
        this.n = var1;
        this.e(var1);
    }

    public boolean a(OEntity var1) {
        this.g(var1);
        return false;
    }

    public OEntityLiving at() {
        return this.l;
    }

    public void b(OEntityLiving var1) {
        this.l = var1;
    }

    public boolean a(Class var1) {
        return OEntityCreeper.class != var1 && OEntityGhast.class != var1;
    }

    public void z() {
    }

    public boolean au() {
        return this.e(OMathHelper.b(this.bm), OMathHelper.b(this.bn), OMathHelper.b(this.bo));
    }

    public boolean e(int var1, int var2, int var3) {
        return this.p == -1.0F ? true : this.o.c(var1, var2, var3) < this.p * this.p;
    }

    public void b(int var1, int var2, int var3, int var4) {
        this.o.a(var1, var2, var3);
        this.p = var4;
    }

    public OChunkCoordinates av() {
        return this.o;
    }

    public float aw() {
        return this.p;
    }

    public void ax() {
        this.p = -1.0F;
    }

    public boolean ay() {
        return this.p != -1.0F;
    }

    public void a(OEntityLiving var1) {
        this.b = var1;
        this.c = this.b != null ? 60 : 0;
    }

    @Override
    protected void b() {
        this.bY.a(8, Integer.valueOf(this.f));
    }

    public boolean h(OEntity var1) {
        return this.bi.a(OVec3D.b(this.bm, this.bn + this.B(), this.bo), OVec3D.b(var1.bm, var1.bn + var1.B(), var1.bo)) == null;
    }

    @Override
    public boolean o_() {
        return !this.bE;
    }

    @Override
    public boolean e_() {
        return !this.bE;
    }

    @Override
    public float B() {
        return this.bH * 0.85F;
    }

    public int m() {
        return 80;
    }

    public void az() {
        String var1 = this.i();
        if (var1 != null) {
            this.bi.a(this, var1, this.p(), this.A());
        }

    }

    @Override
    public void aA() {
        this.an = this.ao;
        super.aA();
        OProfiler.a("mobBaseTick");
        if (this.aE() && this.bS.nextInt(1000) < this.a++) {
            this.a = -this.m();
            this.az();
        }

        if (this.aE() && this.Y()) {
            // CanaryMod - suffocation damage.  
            DamageHook hook = new DamageHook(null, this.canaryEntityLiving, new CanaryDamageSource(ODamageSource.e), 1);
            Canary.hooks().callHook(hook);
            if (!hook.isCanceled()) {
                this.a(ODamageSource.e, 1);
            }
            // CanaryMod - end.
            
        }

        if (this.aS() || this.bi.F) {
            this.aR();
        }

        if (this.aE() && this.a(OMaterial.g) && !this.f_() && !this.aK.containsKey(Integer.valueOf(OPotion.o.H))) {
            this.k(this.b_(this.ba()));
            if (this.ba() == -20) {
                this.k(0);
                // CanaryMod - drowning damage.  
                DamageHook hook = new DamageHook(null, this.canaryEntityLiving, new CanaryDamageSource(ODamageSource.f), 2);
                Canary.hooks().callHook(hook);
                if (!hook.isCanceled()) {
                    for (int var1 = 0; var1 < 8; ++var1) {
                        float var2 = this.bS.nextFloat() - this.bS.nextFloat();
                        float var3 = this.bS.nextFloat() - this.bS.nextFloat();
                        float var4 = this.bS.nextFloat() - this.bS.nextFloat();
                        this.bi.a("bubble", this.bm + var2, this.bn + var3, this.bo + var4, this.bp, this.bq, this.br);
                    }
                    this.a(ODamageSource.f, 2);
                }
                // CanaryMod - end.
            }

            this.aR();
        } else {
            this.k(300);
        }

        this.ax = this.ay;
        if (this.aw > 0) {
            --this.aw;
        }

        if (this.as > 0) {
            --this.as;
        }

        if (this.bW > 0) {
            --this.bW;
        }

        if (this.ap <= 0) {
            this.aB();
        }

        if (this.aH > 0) {
            --this.aH;
        } else {
            this.aG = null;
        }

        if (this.d != null && !this.d.aE()) {
            this.d = null;
        }

        if (this.b != null) {
            if (!this.b.aE()) {
                this.a((OEntityLiving) null);
            } else if (this.c > 0) {
                --this.c;
            } else {
                this.a((OEntityLiving) null);
            }
        }

        this.aK();
        this.ac = this.ab;
        this.W = this.V;
        this.Y = this.X;
        this.bu = this.bs;
        this.bv = this.bt;
        OProfiler.a();
    }

    protected void aB() {
        ++this.av;
        if (this.av == 20) {
            int var1;
            if (!this.bi.F && (this.aH > 0 || this.ah()) && !this.aO()) {
                var1 = this.a(this.aG);

                while (var1 > 0) {
                    int var2 = OEntityXPOrb.b(var1);
                    var1 -= var2;
                    this.bi.b((new OEntityXPOrb(this.bi, this.bm, this.bn, this.bo, var2)));
                }
            }

            this.aH();
            this.X();

            for (var1 = 0; var1 < 20; ++var1) {
                double var3 = this.bS.nextGaussian() * 0.02D;
                double var5 = this.bS.nextGaussian() * 0.02D;
                double var7 = this.bS.nextGaussian() * 0.02D;
                this.bi.a("explode", this.bm + (this.bS.nextFloat() * this.bG * 2.0F) - this.bG, this.bn + (this.bS.nextFloat() * this.bH), this.bo + (this.bS.nextFloat() * this.bG * 2.0F) - this.bG, var3, var5, var7);
            }
        }

    }

    protected int b_(int var1) {
        return var1 - 1;
    }

    protected int a(OEntityPlayer var1) {
        return this.aA;
    }

    //CanaryMob protected -> public
    public boolean ah() {
        return false;
    }

    public void aC() {
        for (int var1 = 0; var1 < 20; ++var1) {
            double var2 = this.bS.nextGaussian() * 0.02D;
            double var4 = this.bS.nextGaussian() * 0.02D;
            double var6 = this.bS.nextGaussian() * 0.02D;
            double var8 = 10.0D;
            this.bi.a("explode", this.bm + (this.bS.nextFloat() * this.bG * 2.0F) - this.bG - var2 * var8, this.bn + (this.bS.nextFloat() * this.bH) - var4 * var8, this.bo + (this.bS.nextFloat() * this.bG * 2.0F) - this.bG - var6 * var8, var2, var4, var6);
        }

    }

    @Override
    public void R() {
        super.R();
        this.Z = this.aa;
        this.aa = 0.0F;
        this.bK = 0.0F;
    }

    @Override
    public void F_() {
        super.F_();
        if (this.aI > 0) {
            if (this.aJ <= 0) {
                this.aJ = 60;
            }

            --this.aJ;
            if (this.aJ <= 0) {
                --this.aI;
            }
        }

        this.e();
        double var1 = this.bm - this.bj;
        double var3 = this.bo - this.bl;
        float var5 = OMathHelper.a(var1 * var1 + var3 * var3);
        float var6 = this.V;
        float var7 = 0.0F;
        this.Z = this.aa;
        float var8 = 0.0F;
        if (var5 > 0.05F) {
            var8 = 1.0F;
            var7 = var5 * 3.0F;
            var6 = (float) Math.atan2(var3, var1) * 180.0F / 3.1415927F - 90.0F;
        }

        if (this.ao > 0.0F) {
            var6 = this.bs;
        }

        if (!this.bx) {
            var8 = 0.0F;
        }

        this.aa += (var8 - this.aa) * 0.3F;
        if (this.c_()) {
            this.j.a();
        } else {
            float var9;
            for (var9 = var6 - this.V; var9 < -180.0F; var9 += 360.0F) {
                ;
            }

            while (var9 >= 180.0F) {
                var9 -= 360.0F;
            }

            this.V += var9 * 0.3F;

            float var10;
            for (var10 = this.bs - this.V; var10 < -180.0F; var10 += 360.0F) {
                ;
            }

            while (var10 >= 180.0F) {
                var10 -= 360.0F;
            }

            boolean var11 = var10 < -90.0F || var10 >= 90.0F;
            if (var10 < -75.0F) {
                var10 = -75.0F;
            }

            if (var10 >= 75.0F) {
                var10 = 75.0F;
            }

            this.V = this.bs - var10;
            if (var10 * var10 > 2500.0F) {
                this.V += var10 * 0.2F;
            }

            if (var11) {
                var7 *= -1.0F;
            }
        }

        while (this.bs - this.bu < -180.0F) {
            this.bu -= 360.0F;
        }

        while (this.bs - this.bu >= 180.0F) {
            this.bu += 360.0F;
        }

        while (this.V - this.W < -180.0F) {
            this.W -= 360.0F;
        }

        while (this.V - this.W >= 180.0F) {
            this.W += 360.0F;
        }

        while (this.bt - this.bv < -180.0F) {
            this.bv -= 360.0F;
        }

        while (this.bt - this.bv >= 180.0F) {
            this.bv += 360.0F;
        }

        while (this.X - this.Y < -180.0F) {
            this.Y -= 360.0F;
        }

        while (this.X - this.Y >= 180.0F) {
            this.Y += 360.0F;
        }

        this.ab += var7;
    }

    @Override
    protected void b(float var1, float var2) {
        super.b(var1, var2);
    }

    public void d(int var1) {
        if (this.ap > 0) {
            this.ap += var1;
            if (this.ap > this.d()) {
                this.ap = this.d();
            }

            this.bW = this.S / 2;
        }
    }

    public abstract int d();

    public int aD() {
        return this.ap;
    }

    public void h(int var1) {
        this.ap = var1;
        if (var1 > this.d()) {
            var1 = this.d();
        }

    }

    @Override
    public boolean a(ODamageSource var1, int var2) {
        if (this.bi.F) {
            return false;
        } else {
            this.aV = 0;
            if (this.ap <= 0) {
                return false;
            } else if (var1.k() && this.a(OPotion.n)) {
                return false;
            } else {
                this.aE = 1.5F;
                EntityLiving attacker = null;
                if (var1 != null && var1 instanceof OEntityDamageSource && ((OEntityDamageSource) var1).a() instanceof OEntityLiving) {
                    attacker = new CanaryEntityLiving((OEntityLiving) ((OEntityDamageSource) var1).a());
                }
                // CanaryMod - Entity damage. Needed?
                if (attacker != null) {
                    DamageHook hook = new DamageHook(attacker, this.canaryEntityLiving, new CanaryDamageSource(var1), var2);
                    Canary.hooks().callHook(hook);
                    if (this instanceof OEntityCreature) {
                        ((OEntityCreature) this).f = 0; // flee timer.
                    }
                    if (hook.isCanceled()) {
                        return false;
                    }
                }
                // CanaryMod - end.
                boolean var3 = true;
                if (this.bW > this.S / 2.0F) {
                    if (var2 <= this.aU) {
                        return false;
                    }
                    // CanaryMod - Partial damage.
                    if (attacker != null) {
                        DamageHook hook = new DamageHook(attacker, this.canaryEntityLiving, new CanaryDamageSource(var1), var2 - bW);
                        Canary.hooks().callHook(hook);
                        if (hook.isCanceled()) {
                            return false;
                        }
                    }
                    // CanaryMod - end.
                    this.c(var1, var2 - this.aU);
                    this.aU = var2;
                    var3 = false;
                } else {
                    // CanaryMod - full damage.
                    if (attacker != null) {
                        DamageHook hook = new DamageHook(attacker, this.canaryEntityLiving, new CanaryDamageSource(var1), var2);
                        Canary.hooks().callHook(hook);
                        if (hook.isCanceled()) {
                            return false;
                        }
                    }
                    // CanaryMod - end.
                    this.aU = var2;
                    this.aq = this.ap;
                    this.bW = this.S;
                    this.c(var1, var2);
                    this.as = this.at = 10;
                }

                this.au = 0.0F;
                OEntity var4 = var1.a();
                if (var4 != null) {
                    if (var4 instanceof OEntityLiving) {
                        this.a((OEntityLiving) var4);
                    }

                    if (var4 instanceof OEntityPlayer) {
                        this.aH = 60;
                        this.aG = (OEntityPlayer) var4;
                    } else if (var4 instanceof OEntityWolf) {
                        OEntityWolf var5 = (OEntityWolf) var4;
                        if (var5.u_()) {
                            this.aH = 60;
                            this.aG = null;
                        }
                    }
                }

                if (var3) {
                    this.bi.a(this, (byte) 2);
                    this.aW();
                    if (var4 != null) {
                        double var6 = var4.bm - this.bm;

                        double var8;
                        for (var8 = var4.bo - this.bo; var6 * var6 + var8 * var8 < 1.0E-4D; var8 = (Math.random() - Math.random()) * 0.01D) {
                            var6 = (Math.random() - Math.random()) * 0.01D;
                        }

                        this.au = (float) (Math.atan2(var8, var6) * 180.0D / 3.1415927410125732D) - this.bs;
                        this.a(var4, var2, var6, var8);
                    } else {
                        this.au = ((int) (Math.random() * 2.0D) * 180);
                    }
                }

                if (this.ap <= 0) {
                    if (var3) {
                        this.bi.a(this, this.k(), this.p(), this.A());
                    }

                    this.a(var1);
                } else if (var3) {
                    this.bi.a(this, this.j(), this.p(), this.A());
                }

                return true;
            }
        }
    }

    private float A() {
        return this.aO() ? (this.bS.nextFloat() - this.bS.nextFloat()) * 0.2F + 1.5F : (this.bS.nextFloat() - this.bS.nextFloat()) * 0.2F + 1.0F;
    }

    public int T() {
        return 0;
    }

    protected void f(int var1) {
    }

    protected int d(ODamageSource var1, int var2) {
        if (!var1.e()) {
            int var3 = 25 - this.T();
            int var4 = var2 * var3 + this.ar;
            this.f(var2);
            var2 = var4 / 25;
            this.ar = var4 % 25;
        }

        return var2;
    }

    protected int b(ODamageSource var1, int var2) {
        if (this.a(OPotion.m)) {
            int var3 = (this.b(OPotion.m).c() + 1) * 5;
            int var4 = 25 - var3;
            int var5 = var2 * var4 + this.ar;
            var2 = var5 / 25;
            this.ar = var5 % 25;
        }

        return var2;
    }

    protected void c(ODamageSource var1, int var2) {
        var2 = this.d(var1, var2);
        var2 = this.b(var1, var2);
        this.ap -= var2;
    }

    protected float p() {
        return 1.0F;
    }

    protected String i() {
        return null;
    }

    protected String j() {
        return "damage.hurtflesh";
    }

    protected String k() {
        return "damage.hurtflesh";
    }

    public void a(OEntity var1, int var2, double var3, double var5) {
        this.ce = true;
        float var7 = OMathHelper.a(var3 * var3 + var5 * var5);
        float var8 = 0.4F;
        this.bp /= 2.0D;
        this.bq /= 2.0D;
        this.br /= 2.0D;
        this.bp -= var3 / var7 * var8;
        this.bq += var8;
        this.br -= var5 / var7 * var8;
        if (this.bq > 0.4000000059604645D) {
            this.bq = 0.4000000059604645D;
        }

    }

    public void a(ODamageSource var1) {
        OEntity var2 = var1.a();
        if (this.aj >= 0 && var2 != null) {
            var2.b(this, this.aj);
        }

        if (var2 != null) {
            var2.c(this);
        }

        this.az = true;
        if (!this.bi.F) {
            int var3 = 0;
            if (var2 instanceof OEntityPlayer) {
                var3 = OEnchantmentHelper.f(((OEntityPlayer) var2).k);
            }

            if (!this.aO()) {
                this.a(this.aH > 0, var3);
                if (this.aH > 0) {
                    int var4 = this.bS.nextInt(200) - var3;
                    if (var4 < 5) {
                        this.b(var4 <= 0 ? 1 : 0);
                    }
                }
            }
        }

        this.bi.a(this, (byte) 3);
    }

    protected void b(int var1) {
    }

    protected void a(boolean var1, int var2) {
        int var3 = this.f();
        if (var3 > 0) {
            int var4 = this.bS.nextInt(3);
            if (var2 > 0) {
                var4 += this.bS.nextInt(var2 + 1);
            }

            for (int var5 = 0; var5 < var4; ++var5) {
                this.b(var3, 1);
            }
        }

    }

    protected int f() {
        return 0;
    }

    @Override
    protected void a(float var1) {
        super.a(var1);
        int var2 = (int) Math.ceil((var1 - 3.0F));
        if (var2 > 0) {
            // CanaryMod - fall damage.
            DamageHook hook = new DamageHook(null, this.canaryEntityLiving, new CanaryDamageSource(ODamageSource.i), var2);
            Canary.hooks().callHook(hook);
            if (!hook.isCanceled()) {
                if (var2 > 4) {
                    this.bi.a(this, "damage.fallbig", 1.0F, 1.0F);
                } else {
                    this.bi.a(this, "damage.fallsmall", 1.0F, 1.0F);
                }

                this.a(ODamageSource.i, var2);
            }
            // CanaryMod - end.
            int var3 = this.bi.a(OMathHelper.b(this.bm), OMathHelper.b(this.bn - 0.20000000298023224D - this.bF), OMathHelper.b(this.bo));
            if (var3 > 0) {
                OStepSound var4 = OBlock.m[var3].cb;
                this.bi.a(this, var4.c(), var4.a() * 0.5F, var4.b() * 0.75F);
            }
        }

    }

    public void a(float var1, float var2) {
        double var3;
        if (this.aU()) {
            var3 = this.bn;
            this.a(var1, var2, this.c_() ? 0.04F : 0.02F);
            this.a(this.bp, this.bq, this.br);
            this.bp *= 0.800000011920929D;
            this.bq *= 0.800000011920929D;
            this.br *= 0.800000011920929D;
            this.bq -= 0.02D;
            if (this.by && this.d(this.bp, this.bq + 0.6000000238418579D - this.bn + var3, this.br)) {
                this.bq = 0.30000001192092896D;
            }
        } else if (this.aV()) {
            var3 = this.bn;
            this.a(var1, var2, 0.02F);
            this.a(this.bp, this.bq, this.br);
            this.bp *= 0.5D;
            this.bq *= 0.5D;
            this.br *= 0.5D;
            this.bq -= 0.02D;
            if (this.by && this.d(this.bp, this.bq + 0.6000000238418579D - this.bn + var3, this.br)) {
                this.bq = 0.30000001192092896D;
            }
        } else {
            float var5 = 0.91F;
            if (this.bx) {
                var5 = 0.54600006F;
                int var6 = this.bi.a(OMathHelper.b(this.bm), OMathHelper.b(this.bw.b) - 1, OMathHelper.b(this.bo));
                if (var6 > 0) {
                    var5 = OBlock.m[var6].ce * 0.91F;
                }
            }

            float var12 = 0.16277136F / (var5 * var5 * var5);
            float var7;
            if (this.bx) {
                if (this.c_()) {
                    var7 = this.as();
                } else {
                    var7 = this.al;
                }

                var7 *= var12;
            } else {
                var7 = this.am;
            }

            this.a(var1, var2, var7);
            var5 = 0.91F;
            if (this.bx) {
                var5 = 0.54600006F;
                int var8 = this.bi.a(OMathHelper.b(this.bm), OMathHelper.b(this.bw.b) - 1, OMathHelper.b(this.bo));
                if (var8 > 0) {
                    var5 = OBlock.m[var8].ce * 0.91F;
                }
            }

            if (this.t()) {
                float var13 = 0.15F;
                if (this.bp < (-var13)) {
                    this.bp = (-var13);
                }

                if (this.bp > var13) {
                    this.bp = var13;
                }

                if (this.br < (-var13)) {
                    this.br = (-var13);
                }

                if (this.br > var13) {
                    this.br = var13;
                }

                this.bK = 0.0F;
                if (this.bq < -0.15D) {
                    this.bq = -0.15D;
                }

                boolean var9 = this.aY() && this instanceof OEntityPlayer;
                if (var9 && this.bq < 0.0D) {
                    this.bq = 0.0D;
                }
            }

            this.a(this.bp, this.bq, this.br);
            if (this.by && this.t()) {
                this.bq = 0.2D;
            }

            this.bq -= 0.08D;
            this.bq *= 0.9800000190734863D;
            this.bp *= var5;
            this.br *= var5;
        }

        this.aD = this.aE;
        var3 = this.bm - this.bj;
        double var10 = this.bo - this.bl;
        float var14 = OMathHelper.a(var3 * var3 + var10 * var10) * 4.0F;
        if (var14 > 1.0F) {
            var14 = 1.0F;
        }

        this.aE += (var14 - this.aE) * 0.4F;
        this.aF += this.aE;
    }

    public boolean t() {
        int var1 = OMathHelper.b(this.bm);
        int var2 = OMathHelper.b(this.bw.b);
        int var3 = OMathHelper.b(this.bo);
        int var4 = this.bi.a(var1, var2, var3);
        return var4 == OBlock.aF.bO || var4 == OBlock.bu.bO;
    }

    @Override
    public void b(ONBTTagCompound var1) {
        var1.a("Health", (short) this.ap);
        var1.a("HurtTime", (short) this.as);
        var1.a("DeathTime", (short) this.av);
        var1.a("AttackTime", (short) this.aw);
        if (!this.aK.isEmpty()) {
            ONBTTagList var2 = new ONBTTagList();
            Iterator var3 = this.aK.values().iterator();

            while (var3.hasNext()) {
                OPotionEffect var4 = (OPotionEffect) var3.next();
                ONBTTagCompound var5 = new ONBTTagCompound();
                var5.a("Id", (byte) var4.a());
                var5.a("Amplifier", (byte) var4.c());
                var5.a("Duration", var4.b());
                var2.a(var5);
            }

            var1.a("ActiveEffects", var2);
        }

    }

    @Override
    public void a(ONBTTagCompound var1) {
        if (this.ap < -32768) {
            this.ap = -32768;
        }

        this.ap = var1.e("Health");
        if (!var1.c("Health")) {
            this.ap = this.d();
        }

        this.as = var1.e("HurtTime");
        this.av = var1.e("DeathTime");
        this.aw = var1.e("AttackTime");
        if (var1.c("ActiveEffects")) {
            ONBTTagList var2 = var1.n("ActiveEffects");

            for (int var3 = 0; var3 < var2.d(); ++var3) {
                ONBTTagCompound var4 = (ONBTTagCompound) var2.a(var3);
                byte var5 = var4.d("Id");
                byte var6 = var4.d("Amplifier");
                int var7 = var4.f("Duration");
                this.aK.put(Integer.valueOf(var5), new OPotionEffect(var5, var7, var6));
            }
        }

    }

    @Override
    public boolean aE() {
        return !this.bE && this.ap > 0;
    }

    public boolean f_() {
        return false;
    }

    public void e(float var1) {
        this.aX = var1;
    }

    public void f(boolean var1) {
        this.aZ = var1;
    }

    public void e() {
        if (this.q > 0) {
            --this.q;
        }

        if (this.aN > 0) {
            double var1 = this.bm + (this.aO - this.bm) / this.aN;
            double var3 = this.bn + (this.aP - this.bn) / this.aN;
            double var5 = this.bo + (this.aQ - this.bo) / this.aN;

            double var7;
            for (var7 = this.aR - this.bs; var7 < -180.0D; var7 += 360.0D) {
                ;
            }

            while (var7 >= 180.0D) {
                var7 -= 360.0D;
            }

            this.bs = (float) (this.bs + var7 / this.aN);
            this.bt = (float) (this.bt + (this.aS - this.bt) / this.aN);
            --this.aN;
            this.c(var1, var3, var5);
            this.c(this.bs, this.bt);
            List var9 = this.bi.a(this, this.bw.e(0.03125D, 0.0D, 0.03125D));
            if (var9.size() > 0) {
                double var10 = 0.0D;

                for (int var12 = 0; var12 < var9.size(); ++var12) {
                    OAxisAlignedBB var13 = (OAxisAlignedBB) var9.get(var12);
                    if (var13.e > var10) {
                        var10 = var13.e;
                    }
                }

                var3 += var10 - this.bw.b;
                this.c(var1, var3, var5);
            }
        }

        OProfiler.a("ai");
        if (this.Q()) {
            this.aZ = false;
            this.aW = 0.0F;
            this.aX = 0.0F;
            this.aY = 0.0F;
        } else if (this.aF()) {
            if (this.c_()) {
                OProfiler.a("newAi");
                this.z_();
                OProfiler.a();
            } else {
                OProfiler.a("oldAi");
                this.d_();
                OProfiler.a();
                this.X = this.bs;
            }
        }

        OProfiler.a();
        boolean var14 = this.aU();
        boolean var15 = this.aV();
        if (this.aZ) {
            if (var14) {
                this.bq += 0.03999999910593033D;
            } else if (var15) {
                this.bq += 0.03999999910593033D;
            } else if (this.bx && this.q == 0) {
                this.ac();
                this.q = 10;
            }
        } else {
            this.q = 0;
        }

        this.aW *= 0.98F;
        this.aX *= 0.98F;
        this.aY *= 0.9F;
        float var16 = this.al;
        this.al *= this.J();
        this.a(this.aW, this.aX);
        this.al = var16;
        OProfiler.a("push");
        List var17 = this.bi.b(this, this.bw.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));
        if (var17 != null && var17.size() > 0) {
            for (int var18 = 0; var18 < var17.size(); ++var18) {
                OEntity var19 = (OEntity) var17.get(var18);
                if (var19.e_()) {
                    var19.k(this);
                }
            }
        }

        OProfiler.a();
    }

    protected boolean c_() {
        return false;
    }

    protected boolean aF() {
        return !this.bi.F;
    }

    protected boolean Q() {
        return this.ap <= 0;
    }

    public boolean P() {
        return false;
    }

    protected void ac() {
        this.bq = 0.41999998688697815D;
        if (this.a(OPotion.j)) {
            this.bq += ((this.b(OPotion.j).c() + 1) * 0.1F);
        }

        if (this.aZ()) {
            float var1 = this.bs * 0.017453292F;
            this.bp -= (OMathHelper.a(var1) * 0.2F);
            this.br += (OMathHelper.b(var1) * 0.2F);
        }

        this.ce = true;
    }

    protected boolean n() {
        return true;
    }

    protected void aG() {
        OEntityPlayer var1 = this.bi.a(this, -1.0D);
        if (var1 != null) {
            double var2 = var1.bm - this.bm;
            double var4 = var1.bn - this.bn;
            double var6 = var1.bo - this.bo;
            double var8 = var2 * var2 + var4 * var4 + var6 * var6;
            if (this.n() && var8 > 16384.0D) {
                EntitySpawnHook hook = new EntitySpawnHook(this.getCanaryEntityLiving(), false);
                Canary.hooks().callHook(hook); //CanaryMod - MobDespawn
                if(!hook.isCanceled()){
                    this.X();
                }
            }

            if (this.aV > 600 && this.bS.nextInt(800) == 0 && var8 > 1024.0D && this.n()) {
                EntitySpawnHook hook = new EntitySpawnHook(this.getCanaryEntityLiving(), false);
                Canary.hooks().callHook(hook); //CanaryMod - MobDespawn
                if(!hook.isCanceled()){
                    this.X();
                }
                else{
                    this.aV = 0;
                }
            } else if (var8 < 1024.0D) {
                this.aV = 0;
            }
        }

    }

    protected void z_() {
        ++this.aV;
        OProfiler.a("checkDespawn");
        this.aG();
        OProfiler.a();
        OProfiler.a("sensing");
        this.m.a();
        OProfiler.a();
        OProfiler.a("targetSelector");
        this.aM.a();
        OProfiler.a();
        OProfiler.a("goalSelector");
        this.aL.a();
        OProfiler.a();
        OProfiler.a("navigation");
        this.k.d();
        OProfiler.a();
        OProfiler.a("mob tick");
        this.g();
        OProfiler.a();
        OProfiler.a("controls");
        this.h.c();
        this.g.a();
        this.i.b();
        OProfiler.a();
    }

    protected void g() {
    }

    protected void d_() {
        ++this.aV;
        this.aG();
        this.aW = 0.0F;
        this.aX = 0.0F;
        float var1 = 8.0F;
        if (this.bS.nextFloat() < 0.02F) {
            OEntityPlayer var2 = this.bi.a(this, var1);
            if (var2 != null) {
                this.r = var2;
                this.bc = 10 + this.bS.nextInt(20);
            } else {
                this.aY = (this.bS.nextFloat() - 0.5F) * 20.0F;
            }
        }

        if (this.r != null) {
            this.a(this.r, 10.0F, this.D());
            if (this.bc-- <= 0 || this.r.bE || this.r.j(this) > (var1 * var1)) {
                this.r = null;
            }
        } else {
            if (this.bS.nextFloat() < 0.05F) {
                this.aY = (this.bS.nextFloat() - 0.5F) * 20.0F;
            }

            this.bs += this.aY;
            this.bt = this.ba;
        }

        boolean var4 = this.aU();
        boolean var3 = this.aV();
        if (var4 || var3) {
            this.aZ = this.bS.nextFloat() < 0.8F;
        }

    }

    public int D() {
        return 40;
    }

    public void a(OEntity var1, float var2, float var3) {
        double var4 = var1.bm - this.bm;
        double var6 = var1.bo - this.bo;
        double var9;
        if (var1 instanceof OEntityLiving) {
            OEntityLiving var8 = (OEntityLiving) var1;
            var9 = this.bn + this.B() - (var8.bn + var8.B());
        } else {
            var9 = (var1.bw.b + var1.bw.e) / 2.0D - (this.bn + this.B());
        }

        double var11 = OMathHelper.a(var4 * var4 + var6 * var6);
        float var13 = (float) (Math.atan2(var6, var4) * 180.0D / 3.1415927410125732D) - 90.0F;
        float var14 = (float) (-(Math.atan2(var9, var11) * 180.0D / 3.1415927410125732D));
        this.bt = -this.b(this.bt, var14, var3);
        this.bs = this.b(this.bs, var13, var2);
    }

    private float b(float var1, float var2, float var3) {
        float var4;
        for (var4 = var2 - var1; var4 < -180.0F; var4 += 360.0F) {
            ;
        }

        while (var4 >= 180.0F) {
            var4 -= 360.0F;
        }

        if (var4 > var3) {
            var4 = var3;
        }

        if (var4 < -var3) {
            var4 = -var3;
        }

        return var1 + var4;
    }

    public void aH() {
    }

    public boolean l() {
        return this.bi.a(this.bw) && this.bi.a(this, this.bw).size() == 0 && !this.bi.c(this.bw);
    }

    @Override
    protected void aI() {
        this.a(ODamageSource.j, 4);
    }

    @Override
    public OVec3D aJ() {
        return this.f(1.0F);
    }

    public OVec3D f(float var1) {
        float var2;
        float var3;
        float var4;
        float var5;
        if (var1 == 1.0F) {
            var2 = OMathHelper.b(-this.bs * 0.017453292F - 3.1415927F);
            var3 = OMathHelper.a(-this.bs * 0.017453292F - 3.1415927F);
            var4 = -OMathHelper.b(-this.bt * 0.017453292F);
            var5 = OMathHelper.a(-this.bt * 0.017453292F);
            return OVec3D.b((var3 * var4), var5, (var2 * var4));
        } else {
            var2 = this.bv + (this.bt - this.bv) * var1;
            var3 = this.bu + (this.bs - this.bu) * var1;
            var4 = OMathHelper.b(-var3 * 0.017453292F - 3.1415927F);
            var5 = OMathHelper.a(-var3 * 0.017453292F - 3.1415927F);
            float var6 = -OMathHelper.b(-var2 * 0.017453292F);
            float var7 = OMathHelper.a(-var2 * 0.017453292F);
            return OVec3D.b((var5 * var6), var7, (var4 * var6));
        }
    }

    public int q() {
        return 4;
    }

    public boolean Z() {
        return false;
    }

    protected void aK() {
        Iterator var1 = this.aK.keySet().iterator();

        while (var1.hasNext()) {
            Integer var2 = (Integer) var1.next();
            OPotionEffect var3 = (OPotionEffect) this.aK.get(var2);
            if (!var3.a(this) && !this.bi.F) {
                var1.remove();
                this.d(var3);
            }
        }

        int var10;
        if (this.e) {
            if (!this.bi.F) {
                if (!this.aK.isEmpty()) {
                    var10 = OPotionHelper.a(this.aK.values());
                    this.bY.b(8, Integer.valueOf(var10));
                } else {
                    this.bY.b(8, Integer.valueOf(0));
                }
            }

            this.e = false;
        }

        if (this.bS.nextBoolean()) {
            var10 = this.bY.c(8);
            if (var10 > 0) {
                double var4 = (var10 >> 16 & 255) / 255.0D;
                double var6 = (var10 >> 8 & 255) / 255.0D;
                double var8 = (var10 >> 0 & 255) / 255.0D;
                this.bi.a("mobSpell", this.bm + (this.bS.nextDouble() - 0.5D) * this.bG, this.bn + this.bS.nextDouble() * this.bH - this.bF, this.bo + (this.bS.nextDouble() - 0.5D) * this.bG, var4, var6, var8);
            }
        }

    }

    public void aL() {
        Iterator var1 = this.aK.keySet().iterator();

        while (var1.hasNext()) {
            Integer var2 = (Integer) var1.next();
            OPotionEffect var3 = (OPotionEffect) this.aK.get(var2);
            if (!this.bi.F) {
                var1.remove();
                this.d(var3);
            }
        }

    }

    public Collection aM() {
        return this.aK.values();
    }

    public boolean a(OPotion var1) {
        return this.aK.containsKey(Integer.valueOf(var1.H));
    }

    public OPotionEffect b(OPotion var1) {
        return (OPotionEffect) this.aK.get(Integer.valueOf(var1.H));
    }

    public void e(OPotionEffect var1) {
        if (this.a(var1)) {
            if (this.aK.containsKey(Integer.valueOf(var1.a()))) {
                ((OPotionEffect) this.aK.get(Integer.valueOf(var1.a()))).a(var1);
                this.c((OPotionEffect) this.aK.get(Integer.valueOf(var1.a())));
            } else {
                this.aK.put(Integer.valueOf(var1.a()), var1);
                this.b(var1);
            }

        }
    }

    public boolean a(OPotionEffect var1) {
        if (this.v() == OEnumCreatureAttribute.b) {
            int var2 = var1.a();
            if (var2 == OPotion.l.H || var2 == OPotion.u.H) {
                return false;
            }
        }

        return true;
    }

    public boolean aN() {
        return this.v() == OEnumCreatureAttribute.b;
    }

    protected void b(OPotionEffect var1) {
        this.e = true;
    }

    protected void c(OPotionEffect var1) {
        this.e = true;
    }

    protected void d(OPotionEffect var1) {
        this.e = true;
    }

    protected float J() {
        float var1 = 1.0F;
        if (this.a(OPotion.c)) {
            var1 *= 1.0F + 0.2F * (this.b(OPotion.c).c() + 1);
        }

        if (this.a(OPotion.d)) {
            var1 *= 1.0F - 0.15F * (this.b(OPotion.d).c() + 1);
        }

        return var1;
    }

    public void a_(double var1, double var3, double var5) {
        this.c(var1, var3, var5, this.bs, this.bt);
    }

    public boolean aO() {
        return false;
    }

    public OEnumCreatureAttribute v() {
        return OEnumCreatureAttribute.a;
    }

    public void c(OItemStack var1) {
        this.bi.a(this, "random.break", 0.8F, 0.8F + this.bi.r.nextFloat() * 0.4F);

        for (int var2 = 0; var2 < 5; ++var2) {
            OVec3D var3 = OVec3D.b((this.bS.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
            var3.a(-this.bt * 3.1415927F / 180.0F);
            var3.b(-this.bs * 3.1415927F / 180.0F);
            OVec3D var4 = OVec3D.b((this.bS.nextFloat() - 0.5D) * 0.3D, (-this.bS.nextFloat()) * 0.6D - 0.3D, 0.6D);
            var4.a(-this.bt * 3.1415927F / 180.0F);
            var4.b(-this.bs * 3.1415927F / 180.0F);
            var4 = var4.c(this.bm, this.bn + this.B(), this.bo);
            this.bi.a("iconcrack_" + var1.a().bP, var4.a, var4.b, var4.c, var3.a, var3.b + 0.05D, var3.c);
        }

    }
}
