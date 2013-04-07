package net.minecraft.server;


import net.canarymod.Canary;
import net.canarymod.api.entity.living.monster.CanaryGhast;
import net.canarymod.hook.entity.MobTargetHook;


public class EntityGhast extends EntityFlying implements IMob {

    public int b = 0;
    public double c;
    public double d;
    public double e;
    private Entity h = null;
    private int i = 0;
    public int f = 0;
    public int g = 0;
    private int j = 1;

    public EntityGhast(World world) {
        super(world);
        this.aH = "/mob/ghast.png";
        this.a(4.0F, 4.0F);
        this.ag = true;
        this.be = 5;
        this.entity = new CanaryGhast(this); // CanaryMod: Wrap Entity
    }

    public boolean a(DamageSource damagesource, int i0) {
        if (this.aq()) {
            return false;
        } else if ("fireball".equals(damagesource.n()) && damagesource.i() instanceof EntityPlayer) {
            super.a(damagesource, 1000);
            ((EntityPlayer) damagesource.i()).a((StatBase) AchievementList.y);
            return true;
        } else {
            return super.a(damagesource, i0);
        }
    }

    protected void a() {
        super.a();
        this.ah.a(16, Byte.valueOf((byte) 0));
    }

    public int aW() {
        return maxHealth == 0 ? 10 : maxHealth; // CanaryMod: custom Max Health
    }

    public void l_() {
        super.l_();
        byte b0 = this.ah.a(16);

        this.aH = b0 == 1 ? "/mob/ghast_fire.png" : "/mob/ghast.png";
    }

    protected void bq() {
        if (!this.q.I && this.q.r == 0) {
            this.w();
        }

        this.bn();
        this.f = this.g;
        double d0 = this.c - this.u;
        double d1 = this.d - this.v;
        double d2 = this.e - this.w;
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;

        if (d3 < 1.0D || d3 > 3600.0D) {
            this.c = this.u + (double) ((this.ab.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.d = this.v + (double) ((this.ab.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.e = this.w + (double) ((this.ab.nextFloat() * 2.0F - 1.0F) * 16.0F);
        }

        if (this.b-- <= 0) {
            this.b += this.ab.nextInt(5) + 2;
            d3 = (double) MathHelper.a(d3);
            if (this.a(this.c, this.d, this.e, d3)) {
                this.x += d0 / d3 * 0.1D;
                this.y += d1 / d3 * 0.1D;
                this.z += d2 / d3 * 0.1D;
            } else {
                this.c = this.u;
                this.d = this.v;
                this.e = this.w;
            }
        }

        if (this.h != null && this.h.M) {
            this.h = null;
        }

        if (this.h == null || this.i-- <= 0) {
            // CanaryMod: MobTarget
            EntityPlayer entity = this.q.b(this, 100.0D);

            if (entity != null) {
                MobTargetHook hook = new MobTargetHook((net.canarymod.api.entity.living.EntityLiving) this.getCanaryEntity(), (net.canarymod.api.entity.living.EntityLiving) entity.getCanaryEntity());

                Canary.hooks().callHook(hook);
                if (!hook.isCanceled()) {
                    this.h = entity;
                }
            }
            //
            if (this.h != null) {
                this.i = 20;
            }
        }

        double d4 = 64.0D;

        if (this.h != null && this.h.e((Entity) this) < d4 * d4) {
            double d5 = this.h.u - this.u;
            double d6 = this.h.E.b + (double) (this.h.P / 2.0F) - (this.v + (double) (this.P / 2.0F));
            double d7 = this.h.w - this.w;

            this.ay = this.A = -((float) Math.atan2(d5, d7)) * 180.0F / 3.1415927F;
            if (this.n(this.h)) {
                if (this.g == 10) {
                    this.q.a((EntityPlayer) null, 1007, (int) this.u, (int) this.v, (int) this.w, 0);
                }

                ++this.g;
                if (this.g == 20) {
                    this.q.a((EntityPlayer) null, 1008, (int) this.u, (int) this.v, (int) this.w, 0);
                    EntityLargeFireball entitylargefireball = new EntityLargeFireball(this.q, this, d5, d6, d7);

                    entitylargefireball.e = this.j;
                    double d8 = 4.0D;
                    Vec3 vec3 = this.i(1.0F);

                    entitylargefireball.u = this.u + vec3.c * d8;
                    entitylargefireball.v = this.v + (double) (this.P / 2.0F) + 0.5D;
                    entitylargefireball.w = this.w + vec3.e * d8;
                    this.q.d((Entity) entitylargefireball);
                    this.g = -40;
                }
            } else if (this.g > 0) {
                --this.g;
            }
        } else {
            this.ay = this.A = -((float) Math.atan2(this.x, this.z)) * 180.0F / 3.1415927F;
            if (this.g > 0) {
                --this.g;
            }
        }

        if (!this.q.I) {
            byte b0 = this.ah.a(16);
            byte b1 = (byte) (this.g > 10 ? 1 : 0);

            if (b0 != b1) {
                this.ah.b(16, Byte.valueOf(b1));
            }
        }
    }

    private boolean a(double d0, double d1, double d2, double d3) {
        double d4 = (this.c - this.u) / d3;
        double d5 = (this.d - this.v) / d3;
        double d6 = (this.e - this.w) / d3;
        AxisAlignedBB axisalignedbb = this.E.c();

        for (int i0 = 1; (double) i0 < d3; ++i0) {
            axisalignedbb.d(d4, d5, d6);
            if (!this.q.a((Entity) this, axisalignedbb).isEmpty()) {
                return false;
            }
        }

        return true;
    }

    protected String bb() {
        return "mob.ghast.moan";
    }

    protected String bc() {
        return "mob.ghast.scream";
    }

    protected String bd() {
        return "mob.ghast.death";
    }

    protected int be() {
        return Item.N.cp;
    }

    protected void a(boolean flag0, int i0) {
        int i1 = this.ab.nextInt(2) + this.ab.nextInt(1 + i0);

        int i2;

        for (i2 = 0; i2 < i1; ++i2) {
            this.b(Item.bq.cp, 1);
        }

        i1 = this.ab.nextInt(3) + this.ab.nextInt(1 + i0);

        for (i2 = 0; i2 < i1; ++i2) {
            this.b(Item.N.cp, 1);
        }
    }

    protected float ba() {
        return 10.0F;
    }

    public boolean bv() {
        return this.ab.nextInt(20) == 0 && super.bv() && this.q.r > 0;
    }

    public int by() {
        return 1;
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("ExplosionPower", this.j);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        if (nbttagcompound.b("ExplosionPower")) {
            this.j = nbttagcompound.e("ExplosionPower");
        }
    }
}
