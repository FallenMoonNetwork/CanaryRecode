package net.minecraft.server;


import net.canarymod.Canary;
import net.canarymod.api.entity.living.monster.CanaryGhast;
import net.canarymod.hook.entity.MobTargetHook;


public class EntityGhast extends EntityFlying implements IMob {

    public int h;
    public double i;
    public double j;
    public double bn;
    private Entity bq;
    private int br;
    public int bo;
    public int bp;
    private int bs = 1;

    public EntityGhast(World world) {
        super(world);
        this.a(4.0F, 4.0F);
        this.ag = true;
        this.b = 5;
        this.entity = new CanaryGhast(this); // CanaryMod: Wrap Entity
    }

    public boolean a(DamageSource damagesource, float f0) {
        if (this.ap()) {
            return false;
        } else if ("fireball".equals(damagesource.n()) && damagesource.i() instanceof EntityPlayer) {
            super.a(damagesource, 1000.0F);
            ((EntityPlayer) damagesource.i()).a((StatBase) AchievementList.y);
            return true;
        } else {
            return super.a(damagesource, f0);
        }
    }

    protected void a() {
        super.a();
        this.ah.a(16, Byte.valueOf((byte) 0));
    }

    protected void ax() {
        super.ax();
        this.a(SharedMonsterAttributes.a).a(10.0D);
    }

    public int aW() {
        return maxHealth == 0 ? 10 : maxHealth; // CanaryMod: custom Max Health
    }

    protected void bh() {
        if (!this.q.I && this.q.r == 0) {
            this.w();
        }

        this.bk();
        this.bo = this.bp;
        double d0 = this.i - this.u;
        double d1 = this.j - this.v;
        double d2 = this.bn - this.w;
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;

        if (d3 < 1.0D || d3 > 3600.0D) {
            this.i = this.u + (double) ((this.ab.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.j = this.v + (double) ((this.ab.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.bn = this.w + (double) ((this.ab.nextFloat() * 2.0F - 1.0F) * 16.0F);
        }

        if (this.h-- <= 0) {
            this.h += this.ab.nextInt(5) + 2;
            d3 = (double) MathHelper.a(d3);
            if (this.a(this.i, this.j, this.bn, d3)) {
                this.x += d0 / d3 * 0.1D;
                this.y += d1 / d3 * 0.1D;
                this.z += d2 / d3 * 0.1D;
            } else {
                this.i = this.u;
                this.j = this.v;
                this.bn = this.w;
            }
        }

        if (this.bq != null && this.bq.M) {
            this.bq = null;
        }

        if (this.bq == null || this.br-- <= 0) {
            // CanaryMod: MobTarget
            EntityPlayer entity = this.q.b(this, 100.0D);

            if (entity != null) {
                MobTargetHook hook = new MobTargetHook((net.canarymod.api.entity.living.EntityLiving) this.getCanaryEntity(), (net.canarymod.api.entity.living.EntityLiving) entity.getCanaryEntity());

                Canary.hooks().callHook(hook);
                if (!hook.isCanceled()) {
                    this.bq = entity;
                }
            }
            //
            if (this.bq != null) {
                this.br = 20;
            }
        }

        double d4 = 64.0D;

        if (this.bq != null && this.bq.e((Entity) this) < d4 * d4) {
            double d5 = this.bq.u - this.u;
            double d6 = this.bq.E.b + (double) (this.bq.P / 2.0F) - (this.v + (double) (this.P / 2.0F));
            double d7 = this.bq.w - this.w;

            this.aN = this.A = -((float) Math.atan2(d5, d7)) * 180.0F / 3.1415927F;
            if (this.o(this.bq)) {
                if (this.bp == 10) {
                    this.q.a((EntityPlayer) null, 1007, (int) this.u, (int) this.v, (int) this.w, 0);
                }

                ++this.bp;
                if (this.bp == 20) {
                    this.q.a((EntityPlayer) null, 1008, (int) this.u, (int) this.v, (int) this.w, 0);
                    EntityLargeFireball entitylargefireball = new EntityLargeFireball(this.q, this, d5, d6, d7);

                    entitylargefireball.e = this.bs;
                    double d8 = 4.0D;
                    Vec3 vec3 = this.j(1.0F);

                    entitylargefireball.u = this.u + vec3.c * d8;
                    entitylargefireball.v = this.v + (double) (this.P / 2.0F) + 0.5D;
                    entitylargefireball.w = this.w + vec3.e * d8;
                    this.q.d((Entity) entitylargefireball);
                    this.bp = -40;
                }
            } else if (this.bp > 0) {
                --this.bp;
            }
        } else {
            this.aN = this.A = -((float) Math.atan2(this.x, this.z)) * 180.0F / 3.1415927F;
            if (this.bp > 0) {
                --this.bp;
            }
        }

        if (!this.q.I) {
            byte b0 = this.ah.a(16);
            byte b1 = (byte) (this.bp > 10 ? 1 : 0);

            if (b0 != b1) {
                this.ah.b(16, Byte.valueOf(b1));
            }
        }
    }

    private boolean a(double d0, double d1, double d2, double d3) {
        double d4 = (this.i - this.u) / d3;
        double d5 = (this.j - this.v) / d3;
        double d6 = (this.bn - this.w) / d3;
        AxisAlignedBB axisalignedbb = this.E.c();

        for (int i0 = 1; (double) i0 < d3; ++i0) {
            axisalignedbb.d(d4, d5, d6);
            if (!this.q.a((Entity) this, axisalignedbb).isEmpty()) {
                return false;
            }
        }

        return true;
    }

    protected String r() {
        return "mob.ghast.moan";
    }

    protected String aK() {
        return "mob.ghast.scream";
    }

    protected String aL() {
        return "mob.ghast.death";
    }

    protected int s() {
        return Item.O.cv;
    }

    protected void b(boolean flag0, int i0) {
        int i1 = this.ab.nextInt(2) + this.ab.nextInt(1 + i0);

        int i2;

        for (i2 = 0; i2 < i1; ++i2) {
            this.b(Item.br.cv, 1);
        }

        i1 = this.ab.nextInt(3) + this.ab.nextInt(1 + i0);

        for (i2 = 0; i2 < i1; ++i2) {
            this.b(Item.O.cv, 1);
        }
    }

    protected float aW() {
        return 10.0F;
    }

    public boolean bo() {
        return this.ab.nextInt(20) == 0 && super.bo() && this.q.r > 0;
    }

    public int br() {
        return 1;
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("ExplosionPower", this.bs);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        if (nbttagcompound.b("ExplosionPower")) {
            this.bs = nbttagcompound.e("ExplosionPower");
        }
    }
}
