package net.minecraft.server;

import net.canarymod.hook.entity.MobTargetHook;

public abstract class EntityMob extends EntityCreature implements IMob {

    public EntityMob(World world) {
        super(world);
        this.b = 5;
    }

    public void c() {
        this.aW();
        float f0 = this.d(1.0F);

        if (f0 > 0.5F) {
            this.aV += 2;
        }

        super.c();
    }

    public void l_() {
        super.l_();
        if (!this.q.I && this.q.r == 0) {
            this.x();
        }
    }

    protected Entity bL() {
        EntityPlayer entityplayer = this.q.b(this, 16.0D);

        return entityplayer != null && this.o(entityplayer) ? entityplayer : null;
    }

    public boolean a(DamageSource damagesource, float f0) {
        if (this.ar()) {
            return false;
        }
        else if (super.a(damagesource, f0)) {
            Entity entity = damagesource.i();

            if (this.n != entity && this.o != entity) {
                if (entity != this) {
                    // CanaryMod: MobTarget
                    if (entity instanceof net.minecraft.server.EntityLiving) {
                        MobTargetHook hook = (MobTargetHook)new MobTargetHook((net.canarymod.api.entity.living.EntityLiving)this.getCanaryEntity(), (net.canarymod.api.entity.living.EntityLiving)entity.getCanaryEntity()).call();
                        if (!hook.isCanceled()) {
                            this.j = entity;
                        }
                    }
                    else {
                        this.j = entity;
                    }

                    //
                }

                return true;
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    public boolean m(Entity entity) {
        float f0 = (float)this.a(SharedMonsterAttributes.e).e();
        int i0 = 0;

        if (entity instanceof EntityLivingBase) {
            f0 += EnchantmentHelper.a((EntityLivingBase)this, (EntityLivingBase)entity);
            i0 += EnchantmentHelper.b(this, (EntityLivingBase)entity);
        }

        boolean flag0 = entity.a(DamageSource.a((EntityLivingBase)this), f0);

        if (flag0) {
            if (i0 > 0) {
                entity.g((double)(-MathHelper.a(this.A * 3.1415927F / 180.0F) * (float)i0 * 0.5F), 0.1D, (double)(MathHelper.b(this.A * 3.1415927F / 180.0F) * (float)i0 * 0.5F));
                this.x *= 0.6D;
                this.z *= 0.6D;
            }

            int i1 = EnchantmentHelper.a((EntityLivingBase)this);

            if (i1 > 0) {
                entity.d(i1 * 4);
            }

            if (entity instanceof EntityLivingBase) {
                EnchantmentThorns.a(this, (EntityLivingBase)entity, this.ab);
            }
        }

        return flag0;
    }

    protected void a(Entity entity, float f0) {
        if (this.aC <= 0 && f0 < 2.0F && entity.E.e > this.E.b && entity.E.b < this.E.e) {
            this.aC = 20;
            this.m(entity);
        }
    }

    public float a(int i0, int i1, int i2) {
        return 0.5F - this.q.q(i0, i1, i2);
    }

    protected boolean i_() {
        int i0 = MathHelper.c(this.u);
        int i1 = MathHelper.c(this.E.b);
        int i2 = MathHelper.c(this.w);

        if (this.q.b(EnumSkyBlock.a, i0, i1, i2) > this.ab.nextInt(32)) {
            return false;
        }
        else {
            int i3 = this.q.n(i0, i1, i2);

            if (this.q.P()) {
                int i4 = this.q.j;

                this.q.j = 10;
                i3 = this.q.n(i0, i1, i2);
                this.q.j = i4;
            }

            return i3 <= this.ab.nextInt(8);
        }
    }

    public boolean bs() {
        return this.q.r > 0 && this.i_() && super.bs();
    }

    protected void az() {
        super.az();
        this.aX().b(SharedMonsterAttributes.e);
    }
}
