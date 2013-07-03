package net.minecraft.server;


import net.canarymod.api.entity.throwable.CanaryEnderPearl;


public class EntityEnderPearl extends EntityThrowable {

    public EntityEnderPearl(World world) {
        super(world);
        this.entity = new CanaryEnderPearl(this); // CanaryMod: Wrap Entity
    }

    public EntityEnderPearl(World world, EntityLivingBase entitylivingbase) {
        super(world, entitylivingbase);
        this.entity = new CanaryEnderPearl(this); // CanaryMod: Wrap Entity
    }

    protected void a(MovingObjectPosition movingobjectposition) {
        if (movingobjectposition.g != null) {
            movingobjectposition.g.a(DamageSource.a((Entity) this, this.h()), 0.0F);
        }

        for (int i0 = 0; i0 < 32; ++i0) {
            this.q.a("portal", this.u, this.v + this.ab.nextDouble() * 2.0D, this.w, this.ab.nextGaussian(), 0.0D, this.ab.nextGaussian());
        }

        if (!this.q.I) {
            if (this.h() != null && this.h() instanceof EntityPlayerMP) {
                EntityPlayerMP entityplayermp = (EntityPlayerMP) this.h();

                if (!entityplayermp.a.b && entityplayermp.q == this.q) {
                    if (this.h().ae()) {
                        this.h().a((Entity) null);
                    }

                    this.h().a(this.u, this.v, this.w);
                    this.h().T = 0.0F;
                    this.h().a(DamageSource.h, 5.0F);
                }
            }

            this.w();
        }
    }
}
