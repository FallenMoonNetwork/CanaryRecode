package net.minecraft.server;

import net.canarymod.api.entity.throwable.CanaryXPBottle;

public class EntityExpBottle extends EntityThrowable {

    public EntityExpBottle(World world) {
        super(world);
        // CanaryMod
        this.gravity = 0.07F;
        this.entity = new CanaryXPBottle(this); // Wrap Entity
        //
    }

    public EntityExpBottle(World world, EntityLivingBase entitylivingbase) {
        super(world, entitylivingbase);
        this.gravity = 0.07F;
        this.entity = new CanaryXPBottle(this); // Wrap Entity
    }

    public EntityExpBottle(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
        // CanaryMod
        this.gravity = 0.07F;
        this.entity = new CanaryXPBottle(this); // Wrap Entity
        //
    }

    // CanaryMod: remove unneeded method override
    // protected float e() {
    // return 0.07F;
    // }
    //

    protected float c() {
        return 0.7F;
    }

    protected float d() {
        return -20.0F;
    }

    protected void a(MovingObjectPosition movingobjectposition) {
        if (!this.q.I) {
            this.q.e(2002, (int)Math.round(this.u), (int)Math.round(this.v), (int)Math.round(this.w), 0);
            int i0 = 3 + this.q.s.nextInt(5) + this.q.s.nextInt(5);

            while (i0 > 0) {
                int i1 = EntityXPOrb.a(i0);

                i0 -= i1;
                this.q.d((Entity)(new EntityXPOrb(this.q, this.u, this.v, this.w, i1)));
            }

            this.x();
        }
    }
}
