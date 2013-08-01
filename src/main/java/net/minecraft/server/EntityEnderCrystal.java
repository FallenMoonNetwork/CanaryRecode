package net.minecraft.server;

import net.canarymod.api.entity.CanaryEnderCrystal;

public class EntityEnderCrystal extends Entity {

    public int a;
    public int b;

    public EntityEnderCrystal(World world) {
        super(world);
        this.m = true;
        this.a(2.0F, 2.0F);
        this.N = this.P / 2.0F;
        this.b = 5;
        this.a = this.ab.nextInt(100000);
        this.entity = new CanaryEnderCrystal(this); // CanaryMod: Wrap Entity
    }

    protected boolean e_() {
        return false;
    }

    protected void a() {
        this.ah.a(8, Integer.valueOf(this.b));
    }

    public void l_() {
        this.r = this.u;
        this.s = this.v;
        this.t = this.w;
        ++this.a;
        this.ah.b(8, Integer.valueOf(this.b));
        int i0 = MathHelper.c(this.u);
        int i1 = MathHelper.c(this.v);
        int i2 = MathHelper.c(this.w);

        if (this.q.a(i0, i1, i2) != Block.aw.cF) {
            this.q.c(i0, i1, i2, Block.aw.cF);
        }
    }

    protected void b(NBTTagCompound nbttagcompound) {}

    protected void a(NBTTagCompound nbttagcompound) {}

    public boolean K() {
        return true;
    }

    public boolean a(DamageSource damagesource, float f0) {
        if (this.aq()) {
            return false;
        } else {
            if (!this.M && !this.q.I) {
                // CanaryMod: Check if one hit can kill this entity
                if (((CanaryEnderCrystal) entity).isOneHitDetonate()) {
                    this.b = 0;
                } else {
                    this.b -= f0;
                }
                //
                if (this.b <= 0) {
                    this.w();
                    if (!this.q.I) {
                        this.q.a(this, this.u, this.v, this.w, ((CanaryEnderCrystal) entity).getPower(), true); // CanaryMod: configure Explosion power and set the entity properly
                    }
                }
            }

            return true;
        }
    }
}
