package net.minecraft.server;


import net.canarymod.api.entity.throwable.CanaryEnderPerl;


public class EntityEnderPearl extends EntityThrowable {

    public EntityEnderPearl(World world) {
        super(world);
        this.entity = new CanaryEnderPerl(this);
    }

    public EntityEnderPearl(World world, EntityLiving entityliving) {
        super(world, entityliving);
        this.entity = new CanaryEnderPerl(this); // CanaryMod: Wrap Entity
    }

    protected void a(MovingObjectPosition movingobjectposition) {
        if (movingobjectposition.g != null) {
            movingobjectposition.g.a(DamageSource.a((Entity) this, this.h()), 0);
        }

        for (int i0 = 0; i0 < 32; ++i0) {
            this.q.a("portal", this.u, this.v + this.ab.nextDouble() * 2.0D, this.w, this.ab.nextGaussian(), 0.0D, this.ab.nextGaussian());
        }

        if (!this.q.I) {
            if (this.h() != null && this.h() instanceof EntityPlayerMP) {
                EntityPlayerMP entityplayermp = (EntityPlayerMP) this.h();

                if (!entityplayermp.a.b && entityplayermp.q == this.q) {
                    this.h().a(this.u, this.v, this.w);
                    this.h().T = 0.0F;
                    this.h().a(DamageSource.h, 5);
                }
            }

            this.w();
        }
    }
}
