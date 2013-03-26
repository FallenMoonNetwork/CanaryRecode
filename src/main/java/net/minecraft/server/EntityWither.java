package net.minecraft.server;

import java.util.List;
import net.canarymod.api.entity.living.monster.CanaryWither;

public class EntityWither extends EntityMob implements IRangedAttackMob {

    private float[] d = new float[2];
    private float[] e = new float[2];
    private float[] f = new float[2];
    private float[] g = new float[2];
    private int[] h = new int[2];
    private int[] i = new int[2];
    private int j;
    private static final IEntitySelector bK = new EntityWitherAttackFilter();

    public EntityWither(World world) {
        super(world);
        this.b(this.aW());
        this.aH = "/mob/wither.png";
        this.a(0.9F, 4.0F);
        this.ag = true;
        this.bI = 0.6F;
        this.aC().e(true);
        this.bo.a(0, new EntityAISwimming(this));
        this.bo.a(2, new EntityAIArrowAttack(this, this.bI, 40, 20.0F));
        this.bo.a(5, new EntityAIWander(this, this.bI));
        this.bo.a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.bo.a(7, new EntityAILookIdle(this));
        this.bp.a(1, new EntityAIHurtByTarget(this, false));
        this.bp.a(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 30.0F, 0, false, false, bK));
        this.be = 50;
        this.entity = new CanaryWither(this); // CanaryMod: Wrap Entity
    }

    protected void a() {
        super.a();
        this.ah.a(16, new Integer(100));
        this.ah.a(17, new Integer(0));
        this.ah.a(18, new Integer(0));
        this.ah.a(19, new Integer(0));
        this.ah.a(20, new Integer(0));
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("Invul", this.n());
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.t(nbttagcompound.e("Invul"));
        this.ah.b(16, Integer.valueOf(this.aS));
    }

    protected String bb() {
        return "mob.wither.idle";
    }

    protected String bc() {
        return "mob.wither.hurt";
    }

    protected String bd() {
        return "mob.wither.death";
    }

    public void c() {
        if (!this.q.I) {
            this.ah.b(16, Integer.valueOf(this.aS));
        }

        this.y *= 0.6000000238418579D;
        double d0;
        double d1;
        double d2;

        if (!this.q.I && this.u(0) > 0) {
            Entity entity = this.q.a(this.u(0));

            if (entity != null) {
                if (this.v < entity.v || !this.o() && this.v < entity.v + 5.0D) {
                    if (this.y < 0.0D) {
                        this.y = 0.0D;
                    }

                    this.y += (0.5D - this.y) * 0.6000000238418579D;
                }

                double d3 = entity.u - this.u;

                d0 = entity.w - this.w;
                d1 = d3 * d3 + d0 * d0;
                if (d1 > 9.0D) {
                    d2 = (double) MathHelper.a(d1);
                    this.x += (d3 / d2 * 0.5D - this.x) * 0.6000000238418579D;
                    this.z += (d0 / d2 * 0.5D - this.z) * 0.6000000238418579D;
                }
            }
        }

        if (this.x * this.x + this.z * this.z > 0.05000000074505806D) {
            this.A = (float) Math.atan2(this.z, this.x) * 57.295776F - 90.0F;
        }

        super.c();

        int i0;

        for (i0 = 0; i0 < 2; ++i0) {
            this.g[i0] = this.e[i0];
            this.f[i0] = this.d[i0];
        }

        int i1;

        for (i0 = 0; i0 < 2; ++i0) {
            i1 = this.u(i0 + 1);
            Entity entity1 = null;

            if (i1 > 0) {
                entity1 = this.q.a(i1);
            }

            if (entity1 != null) {
                d0 = this.v(i0 + 1);
                d1 = this.w(i0 + 1);
                d2 = this.x(i0 + 1);
                double d4 = entity1.u - d0;
                double d5 = entity1.v + (double) entity1.e() - d1;
                double d6 = entity1.w - d2;
                double d7 = (double) MathHelper.a(d4 * d4 + d6 * d6);
                float f0 = (float) (Math.atan2(d6, d4) * 180.0D / 3.1415927410125732D) - 90.0F;
                float f1 = (float) (-(Math.atan2(d5, d7) * 180.0D / 3.1415927410125732D));

                this.d[i0] = this.b(this.d[i0], f1, 40.0F);
                this.e[i0] = this.b(this.e[i0], f0, 10.0F);
            } else {
                this.e[i0] = this.b(this.e[i0], this.ay, 10.0F);
            }
        }

        boolean flag0 = this.o();

        for (i1 = 0; i1 < 3; ++i1) {
            double d8 = this.v(i1);
            double d9 = this.w(i1);
            double d10 = this.x(i1);

            this.q.a("smoke", d8 + this.ab.nextGaussian() * 0.30000001192092896D, d9 + this.ab.nextGaussian() * 0.30000001192092896D, d10 + this.ab.nextGaussian() * 0.30000001192092896D, 0.0D, 0.0D, 0.0D);
            if (flag0 && this.q.s.nextInt(4) == 0) {
                this.q.a("mobSpell", d8 + this.ab.nextGaussian() * 0.30000001192092896D, d9 + this.ab.nextGaussian() * 0.30000001192092896D, d10 + this.ab.nextGaussian() * 0.30000001192092896D, 0.699999988079071D, 0.699999988079071D, 0.5D);
            }
        }

        if (this.n() > 0) {
            for (i1 = 0; i1 < 3; ++i1) {
                this.q.a("mobSpell", this.u + this.ab.nextGaussian() * 1.0D, this.v + (double) (this.ab.nextFloat() * 3.3F), this.w + this.ab.nextGaussian() * 1.0D, 0.699999988079071D, 0.699999988079071D, 0.8999999761581421D);
            }
        }
    }

    protected void bo() {
        int i0;

        if (this.n() > 0) {
            i0 = this.n() - 1;
            if (i0 <= 0) {
                this.q.a(this, this.u, this.v + (double) this.e(), this.w, 7.0F, false, this.q.M().b("mobGriefing"));
                this.q.d(1013, (int) this.u, (int) this.v, (int) this.w, 0);
            }

            this.t(i0);
            if (this.ac % 10 == 0) {
                this.j(10);
            }
        } else {
            super.bo();

            int i1;

            for (i0 = 1; i0 < 3; ++i0) {
                if (this.ac >= this.h[i0 - 1]) {
                    this.h[i0 - 1] = this.ac + 10 + this.ab.nextInt(10);
                    if (this.q.r >= 2) {
                        int i4001 = i0 - 1;
                        int i4003 = this.i[i0 - 1];

                        this.i[i4001] = this.i[i0 - 1] + 1;
                        if (i4003 > 15) {
                            float f0 = 10.0F;
                            float f1 = 5.0F;
                            double d0 = MathHelper.a(this.ab, this.u - (double) f0, this.u + (double) f0);
                            double d1 = MathHelper.a(this.ab, this.v - (double) f1, this.v + (double) f1);
                            double d2 = MathHelper.a(this.ab, this.w - (double) f0, this.w + (double) f0);

                            this.a(i0 + 1, d0, d1, d2, true);
                            this.i[i0 - 1] = 0;
                        }
                    }

                    i1 = this.u(i0);
                    if (i1 > 0) {
                        Entity entity = this.q.a(i1);

                        if (entity != null && entity.R() && this.e(entity) <= 900.0D && this.n(entity)) {
                            this.a(i0 + 1, (EntityLiving) entity);
                            this.h[i0 - 1] = this.ac + 40 + this.ab.nextInt(20);
                            this.i[i0 - 1] = 0;
                        } else {
                            this.c(i0, 0);
                        }
                    } else {
                        List list = this.q.a(EntityLiving.class, this.E.b(20.0D, 8.0D, 20.0D), bK);

                        for (int i4 = 0; i4 < 10 && !list.isEmpty(); ++i4) {
                            EntityLiving entityliving = (EntityLiving) list.get(this.ab.nextInt(list.size()));

                            if (entityliving != this && entityliving.R() && this.n(entityliving)) {
                                if (entityliving instanceof EntityPlayer) {
                                    if (!((EntityPlayer) entityliving).ce.a) {
                                        this.c(i0, entityliving.k);
                                    }
                                } else {
                                    this.c(i0, entityliving.k);
                                }
                                break;
                            }

                            list.remove(entityliving);
                        }
                    }
                }
            }

            if (this.aJ() != null) {
                this.c(0, this.aJ().k);
            } else {
                this.c(0, 0);
            }

            if (this.j > 0) {
                --this.j;
                if (this.j == 0 && this.q.M().b("mobGriefing")) {
                    i0 = MathHelper.c(this.v);
                    i1 = MathHelper.c(this.u);
                    int i5 = MathHelper.c(this.w);
                    boolean flag0 = false;

                    for (int i6 = -1; i6 <= 1; ++i6) {
                        for (int i7 = -1; i7 <= 1; ++i7) {
                            for (int i8 = 0; i8 <= 3; ++i8) {
                                int i9 = i1 + i6;
                                int i10 = i0 + i8;
                                int i11 = i5 + i7;
                                int i12 = this.q.a(i9, i10, i11);

                                if (i12 > 0 && i12 != Block.D.cz && i12 != Block.bL.cz && i12 != Block.bM.cz) {
                                    flag0 = this.q.a(i9, i10, i11, true) || flag0;
                                }
                            }
                        }
                    }

                    if (flag0) {
                        this.q.a((EntityPlayer) null, 1012, (int) this.u, (int) this.v, (int) this.w, 0);
                    }
                }
            }

            if (this.ac % 20 == 0) {
                this.j(1);
            }
        }
    }

    public void m() {
        this.t(220);
        this.b(this.aW() / 3);
    }

    public void al() {}

    public int aZ() {
        return 4;
    }

    private double v(int i0) {
        if (i0 <= 0) {
            return this.u;
        } else {
            float f0 = (this.ay + (float) (180 * (i0 - 1))) / 180.0F * 3.1415927F;
            float f1 = MathHelper.b(f0);

            return this.u + (double) f1 * 1.3D;
        }
    }

    private double w(int i0) {
        return i0 <= 0 ? this.v + 3.0D : this.v + 2.2D;
    }

    private double x(int i0) {
        if (i0 <= 0) {
            return this.w;
        } else {
            float f0 = (this.ay + (float) (180 * (i0 - 1))) / 180.0F * 3.1415927F;
            float f1 = MathHelper.a(f0);

            return this.w + (double) f1 * 1.3D;
        }
    }

    private float b(float f0, float f1, float f2) {
        float f3 = MathHelper.g(f1 - f0);

        if (f3 > f2) {
            f3 = f2;
        }

        if (f3 < -f2) {
            f3 = -f2;
        }

        return f0 + f3;
    }

    private void a(int i0, EntityLiving entityliving) {
        this.a(i0, entityliving.u, entityliving.v + (double) entityliving.e() * 0.5D, entityliving.w, i0 == 0 && this.ab.nextFloat() < 0.001F);
    }

    private void a(int i0, double d0, double d1, double d2, boolean flag0) {
        this.q.a((EntityPlayer) null, 1014, (int) this.u, (int) this.v, (int) this.w, 0);
        double d3 = this.v(i0);
        double d4 = this.w(i0);
        double d5 = this.x(i0);
        double d6 = d0 - d3;
        double d7 = d1 - d4;
        double d8 = d2 - d5;
        EntityWitherSkull entitywitherskull = new EntityWitherSkull(this.q, this, d6, d7, d8);

        if (flag0) {
            entitywitherskull.a(true);
        }

        entitywitherskull.v = d4;
        entitywitherskull.u = d3;
        entitywitherskull.w = d5;
        this.q.d((Entity) entitywitherskull);
    }

    public void a(EntityLiving entityliving, float f0) {
        this.a(0, entityliving);
    }

    public boolean a(DamageSource damagesource, int i0) {
        if (this.aq()) {
            return false;
        } else if (damagesource == DamageSource.e) {
            return false;
        } else if (this.n() > 0) {
            return false;
        } else {
            Entity entity;

            if (this.o()) {
                entity = damagesource.h();
                if (entity instanceof EntityArrow) {
                    return false;
                }
            }

            entity = damagesource.i();
            if (entity != null && !(entity instanceof EntityPlayer) && entity instanceof EntityLiving && ((EntityLiving) entity).bF() == this.bF()) {
                return false;
            } else {
                if (this.j <= 0) {
                    this.j = 20;
                }

                for (int i1 = 0; i1 < this.i.length; ++i1) {
                    this.i[i1] += 3;
                }

                return super.a(damagesource, i0);
            }
        }
    }

    protected void a(boolean flag0, int i0) {
        this.b(Item.bT.cp, 1);
    }

    protected void bn() {
        this.bC = 0;
    }

    public boolean K() {
        return !this.M;
    }

    public int b() {
        return this.ah.c(16);
    }

    protected void a(float f0) {}

    public void d(PotionEffect potioneffect) {}

    protected boolean bh() {
        return true;
    }

    public int aW() {
        return 300;
    }

    public int n() {
        return this.ah.c(20);
    }

    public void t(int i0) {
        this.ah.b(20, Integer.valueOf(i0));
    }

    public int u(int i0) {
        return this.ah.c(17 + i0);
    }

    public void c(int i0, int i1) {
        this.ah.b(17 + i0, Integer.valueOf(i1));
    }

    public boolean o() {
        return this.b() <= this.aW() / 2;
    }

    public EnumCreatureAttribute bF() {
        return EnumCreatureAttribute.b;
    }

    public void a(Entity entity) {
        this.o = null;
    }
}
