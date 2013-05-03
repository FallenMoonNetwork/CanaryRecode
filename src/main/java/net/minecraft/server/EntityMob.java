package net.minecraft.server;


import net.canarymod.Canary;
import net.canarymod.hook.entity.MobTargetHook;


public abstract class EntityMob extends EntityCreature implements IMob {

    public EntityMob(World world) {
        super(world);
        this.be = 5;
    }

    public void c() {
        this.br();
        float f0 = this.c(1.0F);

        if (f0 > 0.5F) {
            this.bC += 2;
        }

        super.c();
    }

    public void l_() {
        super.l_();
        if (!this.q.I && this.q.r == 0) {
            this.w();
        }
    }

    protected Entity j() {
        EntityPlayer entityplayer = this.q.b(this, 16.0D);

        return entityplayer != null && this.n(entityplayer) ? entityplayer : null;
    }

    public boolean a(DamageSource damagesource, int i0) {
        if (this.aq()) {
            return false;
        } else if (super.a(damagesource, i0)) {
            Entity entity = damagesource.i();

            if (this.n != entity && this.o != entity) {
                if (entity != this) {
                    // CanaryMod: MobTarget
                    if (entity instanceof net.minecraft.server.EntityLiving) {
                        MobTargetHook hook = new MobTargetHook((net.canarymod.api.entity.living.EntityLiving) this.getCanaryEntity(), (net.canarymod.api.entity.living.EntityLiving) entity.getCanaryEntity());

                        Canary.hooks().callHook(hook);
                        if (!hook.isCanceled()) {
                            this.a_ = entity;
                        }
                    } else {
                        this.a_ = entity;
                    }

                    //
                }

                return true;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean m(Entity entity) {
        int i0 = this.c(entity);

        if (this.a(Potion.g)) {
            i0 += 3 << this.b(Potion.g).c();
        }

        if (this.a(Potion.t)) {
            i0 -= 2 << this.b(Potion.t).c();
        }

        int i1 = 0;

        if (entity instanceof EntityLiving) {
            i0 += EnchantmentHelper.a((EntityLiving) this, (EntityLiving) entity);
            i1 += EnchantmentHelper.b(this, (EntityLiving) entity);
        }

        boolean flag0 = entity.a(DamageSource.a((EntityLiving) this), i0);

        if (flag0) {
            if (i1 > 0) {
                entity.g((double) (-MathHelper.a(this.A * 3.1415927F / 180.0F) * (float) i1 * 0.5F), 0.1D, (double) (MathHelper.b(this.A * 3.1415927F / 180.0F) * (float) i1 * 0.5F));
                this.x *= 0.6D;
                this.z *= 0.6D;
            }

            int i2 = EnchantmentHelper.a((EntityLiving) this);

            if (i2 > 0) {
                entity.d(i2 * 4);
            }

            if (entity instanceof EntityLiving) {
                EnchantmentThorns.a(this, (EntityLiving) entity, this.ab);
            }
        }

        return flag0;
    }

    protected void a(Entity entity, float f0) {
        if (this.ba <= 0 && f0 < 2.0F && entity.E.e > this.E.b && entity.E.b < this.E.e) {
            this.ba = 20;
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
        } else {
            int i3 = this.q.n(i0, i1, i2);

            if (this.q.O()) {
                int i4 = this.q.j;

                this.q.j = 10;
                i3 = this.q.n(i0, i1, i2);
                this.q.j = i4;
            }

            return i3 <= this.ab.nextInt(8);
        }
    }

    public boolean bv() {
        return this.i_() && super.bv();
    }

    public int c(Entity entity) {
        return 2;
    }
}
