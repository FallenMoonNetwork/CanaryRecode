package net.minecraft.server;


import net.canarymod.api.entity.vehicle.CanaryTNTMinecart;


public class EntityMinecartTNT extends EntityMinecart {

    public int a = -1; // CanaryMod: private -> public

    public EntityMinecartTNT(World world) {
        super(world);
        this.entity = new CanaryTNTMinecart(this); // CanaryMod: Wrap Entity
    }

    public EntityMinecartTNT(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
        this.entity = new CanaryTNTMinecart(this); // CanaryMod: Wrap Entity
    }

    public int l() {
        return 3;
    }

    public Block n() {
        return Block.ar;
    }

    public void l_() {
        super.l_();
        if (this.a > 0) {
            --this.a;
            this.q.a("smoke", this.u, this.v + 0.5D, this.w, 0.0D, 0.0D, 0.0D);
        } else if (this.a == 0) {
            this.c(this.x * this.x + this.z * this.z);
        }

        if (this.G) {
            double d0 = this.x * this.x + this.z * this.z;

            if (d0 >= 0.009999999776482582D) {
                this.c(d0);
            }
        }
    }

    public void a(DamageSource damagesource) {
        super.a(damagesource);
        double d0 = this.x * this.x + this.z * this.z;

        if (!damagesource.c()) {
            this.a(new ItemStack(Block.ar, 1), 0.0F);
        }

        if (damagesource.m() || damagesource.c() || d0 >= 0.009999999776482582D) {
            this.c(d0);
        }
    }

    protected void c(double d0) {
        if (!this.q.I) {
            double d1 = Math.sqrt(d0);

            if (d1 > 5.0D) {
                d1 = 5.0D;
            }

            this.q.a(this, this.u, this.v, this.w, (float) (4.0D + this.ab.nextDouble() * 1.5D * d1), true);
            this.w();
        }
    }

    protected void b(float f0) {
        if (f0 >= 3.0F) {
            float f1 = f0 / 10.0F;

            this.c((double) (f1 * f1));
        }

        super.b(f0);
    }

    public void a(int i0, int i1, int i2, boolean flag0) {
        if (flag0 && this.a < 0) {
            this.d();
        }
    }

    public void d() {
        this.a = 80;
        if (!this.q.I) {
            this.q.a((Entity) this, (byte) 10);
            this.q.a((Entity) this, "random.fuse", 1.0F, 1.0F);
        }
    }

    public boolean ax() {
        return this.a > -1;
    }

    public float a(Explosion explosion, World world, int i0, int i1, int i2, Block block) {
        return this.ax() && (BlockRailBase.e_(block.cF) || BlockRailBase.d_(world, i0, i1 + 1, i2)) ? 0.0F : super.a(explosion, world, i0, i1, i2, block);
    }

    public boolean a(Explosion explosion, World world, int i0, int i1, int i2, int i3, float f0) {
        return this.ax() && (BlockRailBase.e_(i3) || BlockRailBase.d_(world, i0, i1 + 1, i2)) ? false : super.a(explosion, world, i0, i1, i2, i3, f0);
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        if (nbttagcompound.b("TNTFuse")) {
            this.a = nbttagcompound.e("TNTFuse");
        }
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("TNTFuse", this.a);
    }
}
