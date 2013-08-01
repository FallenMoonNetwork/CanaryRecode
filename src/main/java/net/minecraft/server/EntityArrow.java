package net.minecraft.server;

import java.util.List;
import net.canarymod.api.entity.CanaryArrow;
import net.canarymod.hook.entity.ProjectileHitHook;

public class EntityArrow extends Entity implements IProjectile {

    private int d = -1;
    private int e = -1;
    private int f = -1;
    private int g;
    private int h;
    public boolean i; // CanaryMod: private => public; inGround
    public int a;
    public int b;
    public Entity c;
    public int j; // CanaryMod: private => public; ticksInGround
    public int au; // CanaryMod: private => public; ticksInAir
    private double av = 2.0D;
    private int aw;

    public EntityArrow(World world) {
        super(world);
        this.l = 10.0D;
        this.a(0.5F, 0.5F);
        this.entity = new CanaryArrow(this); // CanaryMod: Wrap Entity
    }

    public EntityArrow(World world, double d0, double d1, double d2) {
        super(world);
        this.l = 10.0D;
        this.a(0.5F, 0.5F);
        this.b(d0, d1, d2);
        this.N = 0.0F;
        this.entity = new CanaryArrow(this); // CanaryMod: Wrap Entity
    }

    public EntityArrow(World world, EntityLivingBase entitylivingbase, EntityLivingBase entitylivingbase1, float f0, float f1) {
        super(world);
        this.l = 10.0D;
        this.c = entitylivingbase;
        if (entitylivingbase instanceof EntityPlayer) {
            this.a = 1;
        }
        this.entity = new CanaryArrow(this); // CanaryMod: Wrap Entity

        this.v = entitylivingbase.v + (double) entitylivingbase.f() - 0.10000000149011612D;
        double d0 = entitylivingbase1.u - entitylivingbase.u;
        double d1 = entitylivingbase1.E.b + (double) (entitylivingbase1.P / 3.0F) - this.v;
        double d2 = entitylivingbase1.w - entitylivingbase.w;
        double d3 = (double) MathHelper.a(d0 * d0 + d2 * d2);

        if (d3 >= 1.0E-7D) {
            float f2 = (float) (Math.atan2(d2, d0) * 180.0D / 3.1415927410125732D) - 90.0F;
            float f3 = (float) (-(Math.atan2(d1, d3) * 180.0D / 3.1415927410125732D));
            double d4 = d0 / d3;
            double d5 = d2 / d3;

            this.b(entitylivingbase.u + d4, this.v, entitylivingbase.w + d5, f2, f3);
            this.N = 0.0F;
            float f4 = (float) d3 * 0.2F;

            this.c(d0, d1 + (double) f4, d2, f0, f1);
        }
    }

    public EntityArrow(World world, EntityLivingBase entitylivingbase, float f0) {
        super(world);
        this.l = 10.0D;
        this.c = entitylivingbase;
        if (entitylivingbase instanceof EntityPlayer) {
            this.a = 1;
        }
        this.entity = new CanaryArrow(this); // CanaryMod: Wrap Entity

        this.a(0.5F, 0.5F);
        this.b(entitylivingbase.u, entitylivingbase.v + (double) entitylivingbase.f(), entitylivingbase.w, entitylivingbase.A, entitylivingbase.B);
        this.u -= (double) (MathHelper.b(this.A / 180.0F * 3.1415927F) * 0.16F);
        this.v -= 0.10000000149011612D;
        this.w -= (double) (MathHelper.a(this.A / 180.0F * 3.1415927F) * 0.16F);
        this.b(this.u, this.v, this.w);
        this.N = 0.0F;
        this.x = (double) (-MathHelper.a(this.A / 180.0F * 3.1415927F) * MathHelper.b(this.B / 180.0F * 3.1415927F));
        this.z = (double) (MathHelper.b(this.A / 180.0F * 3.1415927F) * MathHelper.b(this.B / 180.0F * 3.1415927F));
        this.y = (double) (-MathHelper.a(this.B / 180.0F * 3.1415927F));
        this.c(this.x, this.y, this.z, f0 * 1.5F, 1.0F);
    }

    protected void a() {
        this.ah.a(16, Byte.valueOf((byte) 0));
    }

    public void c(double d0, double d1, double d2, float f0, float f1) {
        float f2 = MathHelper.a(d0 * d0 + d1 * d1 + d2 * d2);

        d0 /= (double) f2;
        d1 /= (double) f2;
        d2 /= (double) f2;
        d0 += this.ab.nextGaussian() * (double) (this.ab.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double) f1;
        d1 += this.ab.nextGaussian() * (double) (this.ab.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double) f1;
        d2 += this.ab.nextGaussian() * (double) (this.ab.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double) f1;
        d0 *= (double) f0;
        d1 *= (double) f0;
        d2 *= (double) f0;
        this.x = d0;
        this.y = d1;
        this.z = d2;
        float f3 = MathHelper.a(d0 * d0 + d2 * d2);

        this.C = this.A = (float) (Math.atan2(d0, d2) * 180.0D / 3.1415927410125732D);
        this.D = this.B = (float) (Math.atan2(d1, (double) f3) * 180.0D / 3.1415927410125732D);
        this.j = 0;
    }

    public void l_() {
        super.l_();
        if (this.D == 0.0F && this.C == 0.0F) {
            float f0 = MathHelper.a(this.x * this.x + this.z * this.z);

            this.C = this.A = (float) (Math.atan2(this.x, this.z) * 180.0D / 3.1415927410125732D);
            this.D = this.B = (float) (Math.atan2(this.y, (double) f0) * 180.0D / 3.1415927410125732D);
        }

        int i0 = this.q.a(this.d, this.e, this.f);

        if (i0 > 0) {
            Block.s[i0].a((IBlockAccess) this.q, this.d, this.e, this.f);
            AxisAlignedBB axisalignedbb = Block.s[i0].b(this.q, this.d, this.e, this.f);

            if (axisalignedbb != null && axisalignedbb.a(this.q.V().a(this.u, this.v, this.w))) {
                this.i = true;
            }
        }

        if (this.b > 0) {
            --this.b;
        }

        if (this.i) {
            int i1 = this.q.a(this.d, this.e, this.f);
            int i2 = this.q.h(this.d, this.e, this.f);

            if (i1 == this.g && i2 == this.h) {
                ++this.j;
                if (this.j == 1200) {
                    this.w();
                }
            } else {
                this.i = false;
                this.x *= (double) (this.ab.nextFloat() * 0.2F);
                this.y *= (double) (this.ab.nextFloat() * 0.2F);
                this.z *= (double) (this.ab.nextFloat() * 0.2F);
                this.j = 0;
                this.au = 0;
            }
        } else {
            ++this.au;
            Vec3 vec3 = this.q.V().a(this.u, this.v, this.w);
            Vec3 vec31 = this.q.V().a(this.u + this.x, this.v + this.y, this.w + this.z);
            MovingObjectPosition movingobjectposition = this.q.a(vec3, vec31, false, true);

            vec3 = this.q.V().a(this.u, this.v, this.w);
            vec31 = this.q.V().a(this.u + this.x, this.v + this.y, this.w + this.z);
            if (movingobjectposition != null) {
                vec31 = this.q.V().a(movingobjectposition.f.c, movingobjectposition.f.d, movingobjectposition.f.e);
            }

            Entity entity = null;
            List list = this.q.b((Entity) this, this.E.a(this.x, this.y, this.z).b(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;

            int i3;
            float f1;

            for (i3 = 0; i3 < list.size(); ++i3) {
                Entity entity1 = (Entity) list.get(i3);

                if (entity1.K() && (entity1 != this.c || this.au >= 5)) {
                    f1 = 0.3F;
                    AxisAlignedBB axisalignedbb1 = entity1.E.b((double) f1, (double) f1, (double) f1);
                    MovingObjectPosition movingobjectposition1 = axisalignedbb1.a(vec3, vec31);

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

            if (movingobjectposition != null && movingobjectposition.g != null && movingobjectposition.g instanceof EntityPlayer) {
                EntityPlayer entityplayer = (EntityPlayer) movingobjectposition.g;

                if (entityplayer.bG.a || this.c instanceof EntityPlayer && !((EntityPlayer) this.c).a(entityplayer)) {
                    movingobjectposition = null;
                }
            }

            float f2;
            float f3;

            if (movingobjectposition != null) {
                // CanaryMod: ProjectileHit
                ProjectileHitHook hook = (ProjectileHitHook) new ProjectileHitHook(this.getCanaryEntity(), movingobjectposition.g == null ? null : movingobjectposition.g.getCanaryEntity()).call();
                if (!hook.isCanceled()) { //
                    if (movingobjectposition.g != null) {
                        f2 = MathHelper.a(this.x * this.x + this.y * this.y + this.z * this.z);
                        int i4 = MathHelper.f((double) f2 * this.av);

                        if (this.d()) {
                            i4 += this.ab.nextInt(i4 / 2 + 2);
                        }

                        DamageSource damagesource = null;
                        if (this.c == null) {
                            damagesource = DamageSource.a(this, this);
                        } else {
                            damagesource = DamageSource.a(this, this.c);
                        }

                        if (this.ae() && !(movingobjectposition.g instanceof EntityEnderman)) {
                            movingobjectposition.g.d(5);
                        }

                        if (movingobjectposition.g.a(damagesource, (float) i4)) {
                            if (movingobjectposition.g instanceof EntityLivingBase) {
                                EntityLivingBase entitylivingbase = (EntityLivingBase) movingobjectposition.g;

                                if (!this.q.I) {
                                    entitylivingbase.m(entitylivingbase.aT() + 1);
                                }

                                if (this.aw > 0) {
                                    f3 = MathHelper.a(this.x * this.x + this.z * this.z);
                                    if (f3 > 0.0F) {
                                        movingobjectposition.g.g(this.x * (double) this.aw * 0.6000000238418579D / (double) f3, 0.1D, this.z * (double) this.aw * 0.6000000238418579D / (double) f3);
                                    }
                                }

                                if (this.c != null) {
                                    EnchantmentThorns.a(this.c, entitylivingbase, this.ab);
                                }

                                if (this.c != null && movingobjectposition.g != this.c && movingobjectposition.g instanceof EntityPlayer && this.c instanceof EntityPlayerMP) {
                                    ((EntityPlayerMP) this.c).a.b(new Packet70GameEvent(6, 0));
                                }
                            }

                            this.a("random.bowhit", 1.0F, 1.2F / (this.ab.nextFloat() * 0.2F + 0.9F));
                            if (!(movingobjectposition.g instanceof EntityEnderman)) {
                                this.w();
                            }
                        } else {
                            this.x *= -0.10000000149011612D;
                            this.y *= -0.10000000149011612D;
                            this.z *= -0.10000000149011612D;
                            this.A += 180.0F;
                            this.C += 180.0F;
                            this.au = 0;
                        }
                    } else {
                        this.d = movingobjectposition.b;
                        this.e = movingobjectposition.c;
                        this.f = movingobjectposition.d;
                        this.g = this.q.a(this.d, this.e, this.f);
                        this.h = this.q.h(this.d, this.e, this.f);
                        this.x = (double) ((float) (movingobjectposition.f.c - this.u));
                        this.y = (double) ((float) (movingobjectposition.f.d - this.v));
                        this.z = (double) ((float) (movingobjectposition.f.e - this.w));
                        f2 = MathHelper.a(this.x * this.x + this.y * this.y + this.z * this.z);
                        this.u -= this.x / (double) f2 * 0.05000000074505806D;
                        this.v -= this.y / (double) f2 * 0.05000000074505806D;
                        this.w -= this.z / (double) f2 * 0.05000000074505806D;
                        this.a("random.bowhit", 1.0F, 1.2F / (this.ab.nextFloat() * 0.2F + 0.9F));
                        this.i = true;
                        this.b = 7;
                        this.a(false);
                        if (this.g != 0) {
                            Block.s[this.g].a(this.q, this.d, this.e, this.f, (Entity) this);
                        }
                    }
                }
            }

            if (this.d()) {
                for (i3 = 0; i3 < 4; ++i3) {
                    this.q.a("crit", this.u + this.x * (double) i3 / 4.0D, this.v + this.y * (double) i3 / 4.0D, this.w + this.z * (double) i3 / 4.0D, -this.x, -this.y + 0.2D, -this.z);
                }
            }

            this.u += this.x;
            this.v += this.y;
            this.w += this.z;
            f2 = MathHelper.a(this.x * this.x + this.z * this.z);
            this.A = (float) (Math.atan2(this.x, this.z) * 180.0D / 3.1415927410125732D);

            for (this.B = (float) (Math.atan2(this.y, (double) f2) * 180.0D / 3.1415927410125732D); this.B - this.D < -180.0F; this.D -= 360.0F) {
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
            float f4 = 0.99F;

            f1 = 0.05F;
            if (this.G()) {
                for (int i5 = 0; i5 < 4; ++i5) {
                    f3 = 0.25F;
                    this.q.a("bubble", this.u - this.x * (double) f3, this.v - this.y * (double) f3, this.w - this.z * (double) f3, this.x, this.y, this.z);
                }

                f4 = 0.8F;
            }

            this.x *= (double) f4;
            this.y *= (double) f4;
            this.z *= (double) f4;
            this.y -= (double) f1;
            this.b(this.u, this.v, this.w);
            this.C();
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("xTile", (short) this.d);
        nbttagcompound.a("yTile", (short) this.e);
        nbttagcompound.a("zTile", (short) this.f);
        nbttagcompound.a("inTile", (byte) this.g);
        nbttagcompound.a("inData", (byte) this.h);
        nbttagcompound.a("shake", (byte) this.b);
        nbttagcompound.a("inGround", (byte) (this.i ? 1 : 0));
        nbttagcompound.a("pickup", (byte) this.a);
        nbttagcompound.a("damage", this.av);
    }

    public void a(NBTTagCompound nbttagcompound) {
        this.d = nbttagcompound.d("xTile");
        this.e = nbttagcompound.d("yTile");
        this.f = nbttagcompound.d("zTile");
        this.g = nbttagcompound.c("inTile") & 255;
        this.h = nbttagcompound.c("inData") & 255;
        this.b = nbttagcompound.c("shake") & 255;
        this.i = nbttagcompound.c("inGround") == 1;
        if (nbttagcompound.b("damage")) {
            this.av = nbttagcompound.h("damage");
        }

        if (nbttagcompound.b("pickup")) {
            this.a = nbttagcompound.c("pickup");
        } else if (nbttagcompound.b("player")) {
            this.a = nbttagcompound.n("player") ? 1 : 0;
        }
    }

    public void b_(EntityPlayer entityplayer) {
        if (!this.q.I && this.i && this.b <= 0) {
            boolean flag0 = this.a == 1 || this.a == 2 && entityplayer.bG.d;

            if (this.a == 1 && !entityplayer.bn.a(new ItemStack(Item.n, 1))) {
                flag0 = false;
            }

            if (flag0) {
                this.a("random.pop", 0.2F, ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                entityplayer.a((Entity) this, 1);
                this.w();
            }
        }
    }

    protected boolean e_() {
        return false;
    }

    public void b(double d0) {
        this.av = d0;
    }

    public double c() {
        return this.av;
    }

    public void a(int i0) {
        this.aw = i0;
    }

    public boolean ap() {
        return false;
    }

    public void a(boolean flag0) {
        byte b0 = this.ah.a(16);

        if (flag0) {
            this.ah.b(16, Byte.valueOf((byte) (b0 | 1)));
        } else {
            this.ah.b(16, Byte.valueOf((byte) (b0 & -2)));
        }
    }

    public boolean d() {
        byte b0 = this.ah.a(16);

        return (b0 & 1) != 0;
    }

    public int cmeakb() {
        return this.aw;
    }
}
