package net.minecraft.server;


import java.util.List;


public abstract class EntityThrowable extends Entity implements IProjectile {

    private int c = -1;
    private int d = -1;
    private int e = -1;
    private int f;
    protected boolean a;
    public int b;
    private EntityLivingBase g;
    private String h;
    private int i;
    private int j = 0;

    public float gravity = 0.03F; // CanaryMod

    public EntityThrowable(World world) {
        super(world);
        this.a(0.25F, 0.25F);
    }

    protected void a() {}

    public EntityThrowable(World world, EntityLivingBase entitylivingbase) {
        super(world);
        this.g = entitylivingbase;
        this.a(0.25F, 0.25F);
        this.b(entitylivingbase.u, entitylivingbase.v + (double) entitylivingbase.f(), entitylivingbase.w, entitylivingbase.A, entitylivingbase.B);
        this.u -= (double) (MathHelper.b(this.A / 180.0F * 3.1415927F) * 0.16F);
        this.v -= 0.10000000149011612D;
        this.w -= (double) (MathHelper.a(this.A / 180.0F * 3.1415927F) * 0.16F);
        this.b(this.u, this.v, this.w);
        this.N = 0.0F;
        float f0 = 0.4F;

        this.x = (double) (-MathHelper.a(this.A / 180.0F * 3.1415927F) * MathHelper.b(this.B / 180.0F * 3.1415927F) * f0);
        this.z = (double) (MathHelper.b(this.A / 180.0F * 3.1415927F) * MathHelper.b(this.B / 180.0F * 3.1415927F) * f0);
        this.y = (double) (-MathHelper.a((this.B + this.d()) / 180.0F * 3.1415927F) * f0);
        this.c(this.x, this.y, this.z, this.c(), 1.0F);
    }

    public EntityThrowable(World world, double d0, double d1, double d2) {
        super(world);
        this.i = 0;
        this.a(0.25F, 0.25F);
        this.b(d0, d1, d2);
        this.N = 0.0F;
    }

    protected float c() {
        return 1.5F;
    }

    protected float d() {
        return 0.0F;
    }

    public void c(double d0, double d1, double d2, float f0, float f1) {
        float f2 = MathHelper.a(d0 * d0 + d1 * d1 + d2 * d2);

        d0 /= (double) f2;
        d1 /= (double) f2;
        d2 /= (double) f2;
        d0 += this.ab.nextGaussian() * 0.007499999832361937D * (double) f1;
        d1 += this.ab.nextGaussian() * 0.007499999832361937D * (double) f1;
        d2 += this.ab.nextGaussian() * 0.007499999832361937D * (double) f1;
        d0 *= (double) f0;
        d1 *= (double) f0;
        d2 *= (double) f0;
        this.x = d0;
        this.y = d1;
        this.z = d2;
        float f3 = MathHelper.a(d0 * d0 + d2 * d2);

        this.C = this.A = (float) (Math.atan2(d0, d2) * 180.0D / 3.1415927410125732D);
        this.D = this.B = (float) (Math.atan2(d1, (double) f3) * 180.0D / 3.1415927410125732D);
        this.i = 0;
    }

    public void l_() {
        this.U = this.u;
        this.V = this.v;
        this.W = this.w;
        super.l_();
        if (this.b > 0) {
            --this.b;
        }

        if (this.a) {
            int i0 = this.q.a(this.c, this.d, this.e);

            if (i0 == this.f) {
                ++this.i;
                if (this.i == 1200) {
                    this.w();
                }

                return;
            }

            this.a = false;
            this.x *= (double) (this.ab.nextFloat() * 0.2F);
            this.y *= (double) (this.ab.nextFloat() * 0.2F);
            this.z *= (double) (this.ab.nextFloat() * 0.2F);
            this.i = 0;
            this.j = 0;
        } else {
            ++this.j;
        }

        Vec3 vec3 = this.q.V().a(this.u, this.v, this.w);
        Vec3 vec31 = this.q.V().a(this.u + this.x, this.v + this.y, this.w + this.z);
        MovingObjectPosition movingobjectposition = this.q.a(vec3, vec31);

        vec3 = this.q.V().a(this.u, this.v, this.w);
        vec31 = this.q.V().a(this.u + this.x, this.v + this.y, this.w + this.z);
        if (movingobjectposition != null) {
            vec31 = this.q.V().a(movingobjectposition.f.c, movingobjectposition.f.d, movingobjectposition.f.e);
        }

        if (!this.q.I) {
            Entity entity = null;
            List list = this.q.b((Entity) this, this.E.a(this.x, this.y, this.z).b(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;
            EntityLivingBase entitylivingbase = this.h();

            for (int i1 = 0; i1 < list.size(); ++i1) {
                Entity entity1 = (Entity) list.get(i1);

                if (entity1.K() && (entity1 != entitylivingbase || this.j >= 5)) {
                    float f0 = 0.3F;
                    AxisAlignedBB axisalignedbb = entity1.E.b((double) f0, (double) f0, (double) f0);
                    MovingObjectPosition movingobjectposition1 = axisalignedbb.a(vec3, vec31);

                    if (movingobjectposition1 != null) {
                        double d1 = vec3.d(movingobjectposition1.f);

                        if (d1 < d0 || d0 == 0.0D) {
                            entity = entity1;
                            d0 = d1;
                        }
                    }
                }
            }

            if (entity != null) {
                movingobjectposition = new MovingObjectPosition(entity);
            }
        }

        if (movingobjectposition != null) {
            if (movingobjectposition.a == EnumMovingObjectType.a && this.q.a(movingobjectposition.b, movingobjectposition.c, movingobjectposition.d) == Block.bj.cF) {
                this.Z();
            } else {
                this.a(movingobjectposition);
            }
        }

        this.u += this.x;
        this.v += this.y;
        this.w += this.z;
        float f1 = MathHelper.a(this.x * this.x + this.z * this.z);

        this.A = (float) (Math.atan2(this.x, this.z) * 180.0D / 3.1415927410125732D);

        for (this.B = (float) (Math.atan2(this.y, (double) f1) * 180.0D / 3.1415927410125732D); this.B - this.D < -180.0F; this.D -= 360.0F) {
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
        float f2 = 0.99F;
        float f3 = this.e();

        if (this.G()) {
            for (int i2 = 0; i2 < 4; ++i2) {
                float f4 = 0.25F;

                this.q.a("bubble", this.u - this.x * (double) f4, this.v - this.y * (double) f4, this.w - this.z * (double) f4, this.x, this.y, this.z);
            }

            f2 = 0.8F;
        }

        this.x *= (double) f2;
        this.y *= (double) f2;
        this.z *= (double) f2;
        this.y -= (double) f3;
        this.b(this.u, this.v, this.w);
    }

    protected float e() {
        return gravity; // CanaryMod: return gravity
    }

    protected abstract void a(MovingObjectPosition movingobjectposition);

    public void b(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("xTile", (short) this.c);
        nbttagcompound.a("yTile", (short) this.d);
        nbttagcompound.a("zTile", (short) this.e);
        nbttagcompound.a("inTile", (byte) this.f);
        nbttagcompound.a("shake", (byte) this.b);
        nbttagcompound.a("inGround", (byte) (this.a ? 1 : 0));
        if ((this.h == null || this.h.length() == 0) && this.g != null && this.g instanceof EntityPlayer) {
            this.h = this.g.al();
        }

        nbttagcompound.a("ownerName", this.h == null ? "" : this.h);
    }

    public void a(NBTTagCompound nbttagcompound) {
        this.c = nbttagcompound.d("xTile");
        this.d = nbttagcompound.d("yTile");
        this.e = nbttagcompound.d("zTile");
        this.f = nbttagcompound.c("inTile") & 255;
        this.b = nbttagcompound.c("shake") & 255;
        this.a = nbttagcompound.c("inGround") == 1;
        this.h = nbttagcompound.i("ownerName");
        if (this.h != null && this.h.length() == 0) {
            this.h = null;
        }
    }

    public EntityLivingBase h() {
        if (this.g == null && this.h != null && this.h.length() > 0) {
            this.g = this.q.a(this.h);
        }

        return this.g;
    }
}
