package net.minecraft.server;

import java.util.List;

public abstract class EntityFireball extends Entity {

    private int e = -1;
    private int f = -1;
    private int g = -1;
    private int h;
    public boolean i; // CanaryMod: private => public; inGround
    public EntityLivingBase a;
    public int j; // CanaryMod: private => public; ticksAlive
    public int au; // CanaryMod: private => public; ticksInAir
    public double b;
    public double c;
    public double d;
    private float motionFactor = 0.95F; // CanaryMod: changeable motion factor

    public EntityFireball(World world) {
        super(world);
        this.a(1.0F, 1.0F);
    }

    protected void a() {
    }

    public EntityFireball(World world, double d0, double d1, double d2, double d3, double d4, double d5) {
        super(world);
        this.a(1.0F, 1.0F);
        this.b(d0, d1, d2, this.A, this.B);
        this.b(d0, d1, d2);
        double d6 = (double)MathHelper.a(d3 * d3 + d4 * d4 + d5 * d5);

        this.b = d3 / d6 * 0.1D;
        this.c = d4 / d6 * 0.1D;
        this.d = d5 / d6 * 0.1D;
    }

    public EntityFireball(World world, EntityLivingBase entitylivingbase, double d0, double d1, double d2) {
        super(world);
        this.a = entitylivingbase;
        this.a(1.0F, 1.0F);
        this.b(entitylivingbase.u, entitylivingbase.v, entitylivingbase.w, entitylivingbase.A, entitylivingbase.B);
        this.b(this.u, this.v, this.w);
        this.N = 0.0F;
        this.x = this.y = this.z = 0.0D;
        d0 += this.ab.nextGaussian() * 0.4D;
        d1 += this.ab.nextGaussian() * 0.4D;
        d2 += this.ab.nextGaussian() * 0.4D;
        double d3 = (double)MathHelper.a(d0 * d0 + d1 * d1 + d2 * d2);

        this.b = d0 / d3 * 0.1D;
        this.c = d1 / d3 * 0.1D;
        this.d = d2 / d3 * 0.1D;
    }

    public void l_() {
        if (!this.q.I && (this.a != null && this.a.M || !this.q.f((int)this.u, (int)this.v, (int)this.w))) {
            this.x();
        }
        else {
            super.l_();
            this.d(1);
            if (this.i) {
                int i0 = this.q.a(this.e, this.f, this.g);

                if (i0 == this.h) {
                    ++this.j;
                    if (this.j == 600) {
                        this.x();
                    }

                    return;
                }

                this.i = false;
                this.x *= (double)(this.ab.nextFloat() * 0.2F);
                this.y *= (double)(this.ab.nextFloat() * 0.2F);
                this.z *= (double)(this.ab.nextFloat() * 0.2F);
                this.j = 0;
                this.au = 0;
            }
            else {
                ++this.au;
            }

            Vec3 vec3 = this.q.V().a(this.u, this.v, this.w);
            Vec3 vec31 = this.q.V().a(this.u + this.x, this.v + this.y, this.w + this.z);
            MovingObjectPosition movingobjectposition = this.q.a(vec3, vec31);

            vec3 = this.q.V().a(this.u, this.v, this.w);
            vec31 = this.q.V().a(this.u + this.x, this.v + this.y, this.w + this.z);
            if (movingobjectposition != null) {
                vec31 = this.q.V().a(movingobjectposition.f.c, movingobjectposition.f.d, movingobjectposition.f.e);
            }

            Entity entity = null;
            List list = this.q.b((Entity)this, this.E.a(this.x, this.y, this.z).b(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;

            for (int i1 = 0; i1 < list.size(); ++i1) {
                Entity entity1 = (Entity)list.get(i1);

                if (entity1.L() && (!entity1.h(this.a) || this.au >= 25)) {
                    float f0 = 0.3F;
                    AxisAlignedBB axisalignedbb = entity1.E.b((double)f0, (double)f0, (double)f0);
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

            if (movingobjectposition != null) {
                this.a(movingobjectposition);
            }

            this.u += this.x;
            this.v += this.y;
            this.w += this.z;
            float f1 = MathHelper.a(this.x * this.x + this.z * this.z);

            this.A = (float)(Math.atan2(this.z, this.x) * 180.0D / 3.1415927410125732D) + 90.0F;

            for (this.B = (float)(Math.atan2((double)f1, this.y) * 180.0D / 3.1415927410125732D) - 90.0F; this.B - this.D < -180.0F; this.D -= 360.0F) {
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
            float f2 = this.c();

            if (this.G()) {
                for (int i2 = 0; i2 < 4; ++i2) {
                    float f3 = 0.25F;

                    this.q.a("bubble", this.u - this.x * (double)f3, this.v - this.y * (double)f3, this.w - this.z * (double)f3, this.x, this.y, this.z);
                }

                // f2 = 0.8F;
                f2 -= 0.15F; // CanaryMod: Change to reduce water speed rather than set it
            }

            this.x += this.b;
            this.y += this.c;
            this.z += this.d;
            this.x *= (double)f2;
            this.y *= (double)f2;
            this.z *= (double)f2;
            this.q.a("smoke", this.u, this.v + 0.5D, this.w, 0.0D, 0.0D, 0.0D);
            this.b(this.u, this.v, this.w);
        }
    }

    public float c() { // CanaryMod: protected => public
        return motionFactor; // CanaryMod: return custom factor
    }

    protected abstract void a(MovingObjectPosition movingobjectposition);

    public void b(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("xTile", (short)this.e);
        nbttagcompound.a("yTile", (short)this.f);
        nbttagcompound.a("zTile", (short)this.g);
        nbttagcompound.a("inTile", (byte)this.h);
        nbttagcompound.a("inGround", (byte)(this.i ? 1 : 0));
        nbttagcompound.a("direction", (NBTBase)this.a(new double[]{this.x, this.y, this.z}));
        nbttagcompound.a("motionFactor", this.motionFactor); // CanaryMod: store motionFactor
    }

    public void a(NBTTagCompound nbttagcompound) {
        this.e = nbttagcompound.d("xTile");
        this.f = nbttagcompound.d("yTile");
        this.g = nbttagcompound.d("zTile");
        this.h = nbttagcompound.c("inTile") & 255;
        this.i = nbttagcompound.c("inGround") == 1;
        if (nbttagcompound.b("motionFactor")) { // CanaryMod: If motionFactor is stored, retrive it
            this.motionFactor = nbttagcompound.g("motionFactor");
        }
        if (nbttagcompound.b("direction")) {
            NBTTagList nbttaglist = nbttagcompound.m("direction");

            this.x = ((NBTTagDouble)nbttaglist.b(0)).a;
            this.y = ((NBTTagDouble)nbttaglist.b(1)).a;
            this.z = ((NBTTagDouble)nbttaglist.b(2)).a;
        }
        else {
            this.x();
        }
    }

    public boolean L() {
        return true;
    }

    public float Z() {
        return 1.0F;
    }

    public boolean a(DamageSource damagesource, float f0) {
        if (this.ar()) {
            return false;
        }
        else {
            this.K();
            if (damagesource.i() != null) {
                Vec3 vec3 = damagesource.i().aa();

                if (vec3 != null) {
                    this.x = vec3.c;
                    this.y = vec3.d;
                    this.z = vec3.e;
                    this.b = this.x * 0.1D;
                    this.c = this.y * 0.1D;
                    this.d = this.z * 0.1D;
                }

                if (damagesource.i() instanceof EntityLivingBase) {
                    this.a = (EntityLivingBase)damagesource.i();
                }

                return true;
            }
            else {
                return false;
            }
        }
    }

    public float d(float f0) {
        return 1.0F;
    }

    // CanaryMod
    public void setMotionFactor(float factor) {
        this.motionFactor = factor;
    }
}
