package net.minecraft.server;

import net.canarymod.api.entity.vehicle.CanaryFurnaceMinecart;

public class EntityMinecartFurnace extends EntityMinecart {

    public int c; // CanaryMod: private -> public
    public double a;
    public double b;

    public EntityMinecartFurnace(World world) {
        super(world);
        this.entity = new CanaryFurnaceMinecart(this); // CanaryMod: Wrap Entity
    }

    public EntityMinecartFurnace(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
        this.entity = new CanaryFurnaceMinecart(this); // CanaryMod: Wrap Entity
    }

    public int l() {
        return 2;
    }

    protected void a() {
        super.a();
        this.ah.a(16, new Byte((byte) 0));
    }

    public void l_() {
        super.l_();
        if (this.c > 0) {
            --this.c;
        }

        if (this.c <= 0) {
            this.a = this.b = 0.0D;
        }

        this.f(this.c > 0);
        if (this.d() && this.ab.nextInt(4) == 0) {
            this.q.a("largesmoke", this.u, this.v + 0.8D, this.w, 0.0D, 0.0D, 0.0D);
        }
    }

    public void a(DamageSource damagesource) {
        super.a(damagesource);
        if (!damagesource.c()) {
            this.a(new ItemStack(Block.aG, 1), 0.0F);
        }
    }

    protected void a(int i0, int i1, int i2, double d0, double d1, int i3, int i4) {
        super.a(i0, i1, i2, d0, d1, i3, i4);
        double d2 = this.a * this.a + this.b * this.b;

        if (d2 > 1.0E-4D && this.x * this.x + this.z * this.z > 0.001D) {
            d2 = (double) MathHelper.a(d2);
            this.a /= d2;
            this.b /= d2;
            if (this.a * this.x + this.b * this.z < 0.0D) {
                this.a = 0.0D;
                this.b = 0.0D;
            } else {
                this.a = this.x;
                this.b = this.z;
            }
        }
    }

    protected void h() {
        double d0 = this.a * this.a + this.b * this.b;

        if (d0 > 1.0E-4D) {
            d0 = (double) MathHelper.a(d0);
            this.a /= d0;
            this.b /= d0;
            double d1 = 0.05D;

            this.x *= 0.800000011920929D;
            this.y *= 0.0D;
            this.z *= 0.800000011920929D;
            this.x += this.a * d1;
            this.z += this.b * d1;
        } else {
            this.x *= 0.9800000190734863D;
            this.y *= 0.0D;
            this.z *= 0.9800000190734863D;
        }

        super.h();
    }

    public boolean c(EntityPlayer entityplayer) {
        ItemStack itemstack = entityplayer.bn.h();

        if (itemstack != null && itemstack.d == Item.o.cv) {
            if (!entityplayer.bG.d && --itemstack.b == 0) {
                entityplayer.bn.a(entityplayer.bn.c, (ItemStack) null);
            }

            this.c += 3600;
        }

        this.a = this.u - entityplayer.u;
        this.b = this.w - entityplayer.w;
        return true;
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("PushX", this.a);
        nbttagcompound.a("PushZ", this.b);
        nbttagcompound.a("Fuel", (short) this.c);
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.a = nbttagcompound.h("PushX");
        this.b = nbttagcompound.h("PushZ");
        this.c = nbttagcompound.d("Fuel");
    }

    protected boolean d() {
        return (this.ah.a(16) & 1) != 0;
    }

    protected void f(boolean flag0) {
        if (flag0) {
            this.ah.b(16, Byte.valueOf((byte) (this.ah.a(16) | 1)));
        } else {
            this.ah.b(16, Byte.valueOf((byte) (this.ah.a(16) & -2)));
        }
    }

    public Block n() {
        return Block.aH;
    }

    public int p() {
        return 2;
    }
}
