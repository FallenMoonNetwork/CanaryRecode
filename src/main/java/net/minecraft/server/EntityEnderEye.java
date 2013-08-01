package net.minecraft.server;

import net.canarymod.api.entity.CanaryEnderEye;

public class EntityEnderEye extends Entity {

    public double a; // CanaryMod: private => public; targetX
    public double b; // CanaryMod: private => public; targetY
    public double c; // CanaryMod: private => public; targetZ
    public int d; // CanaryMod: private => public; despawnTimer
    public boolean e; // CanaryMod: private => public; dropOrShatter

    public EntityEnderEye(World world) {
        super(world);
        this.a(0.25F, 0.25F);
        this.entity = new CanaryEnderEye(this); // CanaryMod: Wrap Entity
    }

    protected void a() {}

    public EntityEnderEye(World world, double d0, double d1, double d2) {
        super(world);
        this.d = 0;
        this.a(0.25F, 0.25F);
        this.b(d0, d1, d2);
        this.N = 0.0F;
        this.entity = new CanaryEnderEye(this); // CanaryMod: Wrap Entity
    }

    public void a(double d0, int i0, double d1) {
        double d2 = d0 - this.u;
        double d3 = d1 - this.w;
        float f0 = MathHelper.a(d2 * d2 + d3 * d3);

        if (f0 > 12.0F) {
            this.a = this.u + d2 / (double) f0 * 12.0D;
            this.c = this.w + d3 / (double) f0 * 12.0D;
            this.b = this.v + 8.0D;
        } else {
            this.a = d0;
            this.b = (double) i0;
            this.c = d1;
        }

        this.d = 0;
        this.e = this.ab.nextInt(5) > 0;
    }

    public void l_() {
        this.U = this.u;
        this.V = this.v;
        this.W = this.w;
        super.l_();
        this.u += this.x;
        this.v += this.y;
        this.w += this.z;
        float f0 = MathHelper.a(this.x * this.x + this.z * this.z);

        this.A = (float) (Math.atan2(this.x, this.z) * 180.0D / 3.1415927410125732D);

        for (this.B = (float) (Math.atan2(this.y, (double) f0) * 180.0D / 3.1415927410125732D); this.B - this.D < -180.0F; this.D -= 360.0F) {
            ;
        }

        while (this.B - this.D >= 180.0F) {
            this.D += 360.0F;
        }

        while (this.A - this.C < -180.0F) {
            this.C -= 360.0F;
        }

        while (this.A - this.C >= 180.0F) {
            this.C += 360.0F;
        }

        this.B = this.D + (this.B - this.D) * 0.2F;
        this.A = this.C + (this.A - this.C) * 0.2F;
        if (!this.q.I) {
            double d0 = this.a - this.u;
            double d1 = this.c - this.w;
            float f1 = (float) Math.sqrt(d0 * d0 + d1 * d1);
            float f2 = (float) Math.atan2(d1, d0);
            double d2 = (double) f0 + (double) (f1 - f0) * 0.0025D;

            if (f1 < 1.0F) {
                d2 *= 0.8D;
                this.y *= 0.8D;
            }

            this.x = Math.cos((double) f2) * d2;
            this.z = Math.sin((double) f2) * d2;
            if (this.v < this.b) {
                this.y += (1.0D - this.y) * 0.014999999664723873D;
            } else {
                this.y += (-1.0D - this.y) * 0.014999999664723873D;
            }
        }

        float f3 = 0.25F;

        if (this.G()) {
            for (int i0 = 0; i0 < 4; ++i0) {
                this.q.a("bubble", this.u - this.x * (double) f3, this.v - this.y * (double) f3, this.w - this.z * (double) f3, this.x, this.y, this.z);
            }
        } else {
            this.q.a("portal", this.u - this.x * (double) f3 + this.ab.nextDouble() * 0.6D - 0.3D, this.v - this.y * (double) f3 - 0.5D, this.w - this.z * (double) f3 + this.ab.nextDouble() * 0.6D - 0.3D, this.x, this.y, this.z);
        }

        if (!this.q.I) {
            this.b(this.u, this.v, this.w);
            ++this.d;
            if (this.d > 80 && !this.q.I) {
                this.w();
                if (this.e) {
                    this.q.d((Entity) (new EntityItem(this.q, this.u, this.v, this.w, new ItemStack(Item.bC))));
                } else {
                    this.q.e(2003, (int) Math.round(this.u), (int) Math.round(this.v), (int) Math.round(this.w), 0);
                }
            }
        }
    }

    public void b(NBTTagCompound nbttagcompound) {}

    public void a(NBTTagCompound nbttagcompound) {}

    public float d(float f0) {
        return 1.0F;
    }

    public boolean ap() {
        return false;
    }
}
