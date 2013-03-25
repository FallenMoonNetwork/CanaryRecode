package net.minecraft.server;


import net.canarymod.api.entity.CanaryEnderCrystal;


public class EntityEnderCrystal extends Entity {

    public int a = 0;
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

    protected boolean f_() {
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

        if (this.q.a(i0, i1, i2) != Block.av.cz) {
            this.q.c(i0, i1, i2, Block.av.cz);
        }
    }

    protected void b(NBTTagCompound nbttagcompound) {}

    protected void a(NBTTagCompound nbttagcompound) {}

    public boolean K() {
        return true;
    }

    public boolean a(DamageSource damagesource, int i0) {
        if (this.aq()) {
            return false;
        } else {
            if (!this.M && !this.q.I) {
                this.b = 0;
                if (this.b <= 0) {
                    this.w();
                    if (!this.q.I) {
                        this.q.a((Entity) null, this.u, this.v, this.w, 6.0F, true);
                    }
                }
            }

            return true;
        }
    }
}
