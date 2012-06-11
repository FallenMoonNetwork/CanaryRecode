package net.minecraft.server;

import net.canarymod.api.entity.CanaryWolf;
import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityAIAttackOnCollide;
import net.minecraft.server.OEntityAIBeg;
import net.minecraft.server.OEntityAIFollowOwner;
import net.minecraft.server.OEntityAIHurtByTarget;
import net.minecraft.server.OEntityAILeapAtTarget;
import net.minecraft.server.OEntityAILookIdle;
import net.minecraft.server.OEntityAIMate;
import net.minecraft.server.OEntityAIOwnerHurtByTarget;
import net.minecraft.server.OEntityAIOwnerHurtTarget;
import net.minecraft.server.OEntityAISwimming;
import net.minecraft.server.OEntityAITargetNonTamed;
import net.minecraft.server.OEntityAIWander;
import net.minecraft.server.OEntityAIWatchClosest;
import net.minecraft.server.OEntityAnimal;
import net.minecraft.server.OEntityArrow;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntitySheep;
import net.minecraft.server.OEntityTameable;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemFood;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OPathEntity;
import net.minecraft.server.OWorld;

public class OEntityWolf extends OEntityTameable {

    private boolean b = false;
    private float c;
    private float g;
    private boolean h;
    private boolean i;
    private float j;
    private float k;

    //CanaryMod wolf handler
    private CanaryWolf canaryWolf;
    
    public OEntityWolf(OWorld var1) {
        super(var1);
        this.ae = "/mob/wolf.png";
        this.b(0.6F, 0.8F);
        this.bb = 0.3F;
        this.al().a(true);
        this.aL.a(1, new OEntityAISwimming(this));
        this.aL.a(2, this.a);
        this.aL.a(3, new OEntityAILeapAtTarget(this, 0.4F));
        this.aL.a(4, new OEntityAIAttackOnCollide(this, this.bb, true));
        this.aL.a(5, new OEntityAIFollowOwner(this, this.bb, 10.0F, 2.0F));
        this.aL.a(6, new OEntityAIMate(this, this.bb));
        this.aL.a(7, new OEntityAIWander(this, this.bb));
        this.aL.a(8, new OEntityAIBeg(this, 8.0F));
        this.aL.a(9, new OEntityAIWatchClosest(this, OEntityPlayer.class, 8.0F));
        this.aL.a(9, new OEntityAILookIdle(this));
        this.aM.a(1, new OEntityAIOwnerHurtByTarget(this));
        this.aM.a(2, new OEntityAIOwnerHurtTarget(this));
        this.aM.a(3, new OEntityAIHurtByTarget(this, true));
        this.aM.a(4, new OEntityAITargetNonTamed(this, OEntitySheep.class, 16.0F, 200, false));
        canaryWolf = new CanaryWolf(this);
    }

    /**
     * CanaryMod Get Wolf handler
     * @return the canaryWolf
     */
    public CanaryWolf getCanaryWolf() {
        return canaryWolf;
    }

    @Override
    public boolean c_() {
        return true;
    }

    @Override
    public void b(OEntityLiving var1) {
        super.b(var1);
        if (var1 instanceof OEntityPlayer) {
            this.d(true);
        }

    }

    @Override
    protected void g() {
        this.bY.b(18, Integer.valueOf(this.aD()));
    }

    @Override
    public int d() {
        return this.u_() ? 20 : 8;
    }

    @Override
    protected void b() {
        super.b();
        this.bY.a(18, new Integer(this.aD()));
    }

    @Override
    protected boolean g_() {
        return false;
    }

    @Override
    public void b(ONBTTagCompound var1) {
        super.b(var1);
        var1.a("Angry", this.E());
    }

    @Override
    public void a(ONBTTagCompound var1) {
        super.a(var1);
        this.d(var1.o("Angry"));
    }

    @Override
    protected boolean n() {
        return this.E();
    }

    @Override
    protected String i() {
        return this.E() ? "mob.wolf.growl" : (this.bS.nextInt(3) == 0 ? (this.u_() && this.bY.c(18) < 10 ? "mob.wolf.whine" : "mob.wolf.panting") : "mob.wolf.bark");
    }

    @Override
    protected String j() {
        return "mob.wolf.hurt";
    }

    @Override
    protected String k() {
        return "mob.wolf.death";
    }

    @Override
    protected float p() {
        return 0.4F;
    }

    @Override
    protected int f() {
        return -1;
    }

    @Override
    public void e() {
        super.e();
        if (!this.bi.F && this.h && !this.i && !this.H() && this.bx) {
            this.i = true;
            this.j = 0.0F;
            this.k = 0.0F;
            this.bi.a(this, (byte) 8);
        }

    }

    @Override
    public void F_() {
        super.F_();
        this.g = this.c;
        if (this.b) {
            this.c += (1.0F - this.c) * 0.4F;
        } else {
            this.c += (0.0F - this.c) * 0.4F;
        }

        if (this.b) {
            this.bc = 10;
        }

        if (this.aT()) {
            this.h = true;
            this.i = false;
            this.j = 0.0F;
            this.k = 0.0F;
        } else if ((this.h || this.i) && this.i) {
            if (this.j == 0.0F) {
                this.bi.a(this, "mob.wolf.shake", this.p(), (this.bS.nextFloat() - this.bS.nextFloat()) * 0.2F + 1.0F);
            }

            this.k = this.j;
            this.j += 0.05F;
            if (this.k >= 2.0F) {
                this.h = false;
                this.i = false;
                this.k = 0.0F;
                this.j = 0.0F;
            }

            if (this.j > 0.4F) {
                float var1 = (float) this.bw.b;
                int var2 = (int) (OMathHelper.a((this.j - 0.4F) * 3.1415927F) * 7.0F);

                for (int var3 = 0; var3 < var2; ++var3) {
                    float var4 = (this.bS.nextFloat() * 2.0F - 1.0F) * this.bG * 0.5F;
                    float var5 = (this.bS.nextFloat() * 2.0F - 1.0F) * this.bG * 0.5F;
                    this.bi.a("splash", this.bm + var4, (var1 + 0.8F), this.bo + var5, this.bp, this.bq, this.br);
                }
            }
        }

    }

    @Override
    public float B() {
        return this.bH * 0.8F;
    }

    @Override
    public int D() {
        return this.v_() ? 20 : super.D();
    }

    @Override
    public boolean a(ODamageSource var1, int var2) {
        OEntity var3 = var1.a();
        this.a.a(false);
        if (var3 != null && !(var3 instanceof OEntityPlayer) && !(var3 instanceof OEntityArrow)) {
            var2 = (var2 + 1) / 2;
        }

        return super.a(var1, var2);
    }

    @Override
    public boolean a(OEntity var1) {
        int var2 = this.u_() ? 4 : 2;
        return var1.a(ODamageSource.a(this), var2);
    }

    @Override
    public boolean b(OEntityPlayer var1) {
        OItemStack var2 = var1.k.d();
        if (!this.u_()) {
            if (var2 != null && var2.c == OItem.aW.bP && !this.E()) {
                if (!var1.L.d) {
                    --var2.a;
                }

                if (var2.a <= 0) {
                    var1.k.a(var1.k.c, (OItemStack) null);
                }

                if (!this.bi.F) {
                    if (this.bS.nextInt(3) == 0) {
                        this.b(true);
                        this.a((OPathEntity) null);
                        this.b((OEntityLiving) null);
                        this.a.a(true);
                        this.h(20);
                        this.a(var1.v);
                        this.a(true);
                        this.bi.a(this, (byte) 7);
                    } else {
                        this.a(false);
                        this.bi.a(this, (byte) 6);
                    }
                }

                return true;
            }
        } else {
            if (var2 != null && OItem.d[var2.c] instanceof OItemFood) {
                OItemFood var3 = (OItemFood) OItem.d[var2.c];
                if (var3.q() && this.bY.c(18) < 20) {
                    if (!var1.L.d) {
                        --var2.a;
                    }

                    this.d(var3.o());
                    if (var2.a <= 0) {
                        var1.k.a(var1.k.c, (OItemStack) null);
                    }

                    return true;
                }
            }

            if (var1.v.equalsIgnoreCase(this.A()) && !this.bi.F && !this.a(var2)) {
                this.a.a(!this.v_());
                this.aZ = false;
                this.a((OPathEntity) null);
            }
        }

        return super.b(var1);
    }

    @Override
    public boolean a(OItemStack var1) {
        return var1 == null ? false : (!(OItem.d[var1.c] instanceof OItemFood) ? false : ((OItemFood) OItem.d[var1.c]).q());
    }

    @Override
    public int q() {
        return 8;
    }

    public boolean E() {
        return (this.bY.a(16) & 2) != 0;
    }

    public void d(boolean var1) {
        byte var2 = this.bY.a(16);
        if (var1) {
            this.bY.b(16, Byte.valueOf((byte) (var2 | 2)));
        } else {
            this.bY.b(16, Byte.valueOf((byte) (var2 & -3)));
        }

    }

    @Override
    public OEntityAnimal a(OEntityAnimal var1) {
        OEntityWolf var2 = new OEntityWolf(this.bi);
        var2.a(this.A());
        var2.b(true);
        return var2;
    }

    public void e(boolean var1) {
        this.b = var1;
    }

    @Override
    public boolean b(OEntityAnimal var1) {
        if (var1 == this) {
            return false;
        } else if (!this.u_()) {
            return false;
        } else if (!(var1 instanceof OEntityWolf)) {
            return false;
        } else {
            OEntityWolf var2 = (OEntityWolf) var1;
            return !var2.u_() ? false : (var2.v_() ? false : this.r_() && var2.r_());
        }
    }
}
