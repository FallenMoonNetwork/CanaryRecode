package net.minecraft.server;


import net.canarymod.api.entity.CanaryXPOrb;


public class EntityXPOrb extends Entity {

    public int a;
    public int b = 0;
    public int c;
    private int d = 5;
    private int e;
    private EntityPlayer f;
    private int g;

    public EntityXPOrb(World world, double d0, double d1, double d2, int i0) {
        super(world);
        this.a(0.5F, 0.5F);
        this.N = this.P / 2.0F;
        this.b(d0, d1, d2);
        this.A = (float) (Math.random() * 360.0D);
        this.x = (double) ((float) (Math.random() * 0.20000000298023224D - 0.10000000149011612D) * 2.0F);
        this.y = (double) ((float) (Math.random() * 0.2D) * 2.0F);
        this.z = (double) ((float) (Math.random() * 0.20000000298023224D - 0.10000000149011612D) * 2.0F);
        this.e = i0;
        this.entity = new CanaryXPOrb(this); // CanaryMod: Wrap Entity
    }

    protected boolean e_() {
        return false;
    }

    public EntityXPOrb(World world) {
        super(world);
        this.a(0.25F, 0.25F);
        this.N = this.P / 2.0F;
        this.entity = new CanaryXPOrb(this); // CanaryMod: Wrap Entity
    }

    protected void a() {}

    public void l_() {
        super.l_();
        if (this.c > 0) {
            --this.c;
        }

        this.r = this.u;
        this.s = this.v;
        this.t = this.w;
        this.y -= 0.029999999329447746D;
        if (this.q.g(MathHelper.c(this.u), MathHelper.c(this.v), MathHelper.c(this.w)) == Material.i) {
            this.y = 0.20000000298023224D;
            this.x = (double) ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F);
            this.z = (double) ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F);
            this.a("random.fizz", 0.4F, 2.0F + this.ab.nextFloat() * 0.4F);
        }

        this.i(this.u, (this.E.b + this.E.e) / 2.0D, this.w);
        double d0 = 8.0D;

        if (this.g < this.a - 20 + this.k % 100) {
            if (this.f == null || this.f.e(this) > d0 * d0) {
                this.f = this.q.a(this, d0);
            }

            this.g = this.a;
        }

        if (this.f != null) {
            double d1 = (this.f.u - this.u) / d0;
            double d2 = (this.f.v + (double) this.f.f() - this.v) / d0;
            double d3 = (this.f.w - this.w) / d0;
            double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
            double d5 = 1.0D - d4;

            if (d5 > 0.0D) {
                d5 *= d5;
                this.x += d1 / d4 * d5 * 0.1D;
                this.y += d2 / d4 * d5 * 0.1D;
                this.z += d3 / d4 * d5 * 0.1D;
            }
        }

        this.d(this.x, this.y, this.z);
        float f0 = 0.98F;

        if (this.F) {
            f0 = 0.58800006F;
            int i0 = this.q.a(MathHelper.c(this.u), MathHelper.c(this.E.b) - 1, MathHelper.c(this.w));

            if (i0 > 0) {
                f0 = Block.s[i0].cV * 0.98F;
            }
        }

        this.x *= (double) f0;
        this.y *= 0.9800000190734863D;
        this.z *= (double) f0;
        if (this.F) {
            this.y *= -0.8999999761581421D;
        }

        ++this.a;
        ++this.b;
        if (this.b >= 6000) {
            this.w();
        }
    }

    public boolean H() {
        return this.q.a(this.E, Material.h, (Entity) this);
    }

    protected void e(int i0) {
        this.a(DamageSource.a, (float) i0);
    }

    public boolean a(DamageSource damagesource, float f0) {
        if (this.ap()) {
            return false;
        } else {
            this.J();
            this.d = (int) ((float) this.d - f0);
            if (this.d <= 0) {
                this.w();
            }

            return false;
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("Health", (short) ((byte) this.d));
        nbttagcompound.a("Age", (short) this.b);
        nbttagcompound.a("Value", (short) this.e);
    }

    public void a(NBTTagCompound nbttagcompound) {
        this.d = nbttagcompound.d("Health") & 255;
        this.b = nbttagcompound.d("Age");
        this.e = nbttagcompound.d("Value");
    }

    public void b_(EntityPlayer entityplayer) {
        if (!this.q.I) {
            if (this.c == 0 && entityplayer.bv == 0) {
                entityplayer.bv = 2;
                this.a("random.orb", 0.1F, 0.5F * ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.7F + 1.8F));
                entityplayer.a((Entity) this, 1);
                entityplayer.s(this.e);
                this.w();
            }
        }
    }

    public int c() {
        return this.e;
    }

    public static int a(int i0) {
        return i0 >= 2477 ? 2477 : (i0 >= 1237 ? 1237 : (i0 >= 617 ? 617 : (i0 >= 307 ? 307 : (i0 >= 149 ? 149 : (i0 >= 73 ? 73 : (i0 >= 37 ? 37 : (i0 >= 17 ? 17 : (i0 >= 7 ? 7 : (i0 >= 3 ? 3 : 1)))))))));
    }

    public boolean ao() {
        return false;
    }
}
