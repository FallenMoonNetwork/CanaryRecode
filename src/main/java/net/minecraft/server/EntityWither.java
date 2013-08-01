package net.minecraft.server;

import java.util.List;
import net.canarymod.api.entity.living.monster.CanaryWither;

public class EntityWither extends EntityMob implements IRangedAttackMob {

    private float[] bp = new float[2];
    private float[] bq = new float[2];
    private float[] br = new float[2];
    private float[] bs = new float[2];
    private int[] bt = new int[2];
    private int[] bu = new int[2];
    private int bv;
    private static final IEntitySelector bw = new EntityWitherAttackFilter();

    public EntityWither(World world) {
        super(world);
        this.g(this.aS());
        this.a(0.9F, 4.0F);
        this.ag = true;
        this.k().e(true);
        this.c.a(0, new EntityAISwimming(this));
        this.c.a(2, new EntityAIArrowAttack(this, 1.0D, 40, 20.0F));
        this.c.a(5, new EntityAIWander(this, 1.0D));
        this.c.a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.c.a(7, new EntityAILookIdle(this));
        this.d.a(1, new EntityAIHurtByTarget(this, false));
        this.d.a(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, false, bw));
        this.b = 50;
        this.entity = new CanaryWither(this); // CanaryMod: Wrap Entity
    }

    protected void a() {
        super.a();
        this.ah.a(17, new Integer(0));
        this.ah.a(18, new Integer(0));
        this.ah.a(19, new Integer(0));
        this.ah.a(20, new Integer(0));
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("Invul", this.bU());
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.p(nbttagcompound.e("Invul"));
    }

    protected String r() {
        return "mob.wither.idle";
    }

    protected String aN() {
        return "mob.wither.hurt";
    }

    protected String aO() {
        return "mob.wither.death";
    }

    public void c() {
        this.y *= 0.6000000238418579D;
        double d0;
        double d1;
        double d2;

        if (!this.q.I && this.q(0) > 0) {
            Entity entity = this.q.a(this.q(0));

            if (entity != null) {
                if (this.v < entity.v || !this.bV() && this.v < entity.v + 5.0D) {
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
            this.bs[i0] = this.bq[i0];
            this.br[i0] = this.bp[i0];
        }

        int i1;

        for (i0 = 0; i0 < 2; ++i0) {
            i1 = this.q(i0 + 1);
            Entity entity1 = null;

            if (i1 > 0) {
                entity1 = this.q.a(i1);
            }

            if (entity1 != null) {
                d0 = this.r(i0 + 1);
                d1 = this.s(i0 + 1);
                d2 = this.t(i0 + 1);
                double d4 = entity1.u - d0;
                double d5 = entity1.v + (double) entity1.f() - d1;
                double d6 = entity1.w - d2;
                double d7 = (double) MathHelper.a(d4 * d4 + d6 * d6);
                float f0 = (float) (Math.atan2(d6, d4) * 180.0D / 3.1415927410125732D) - 90.0F;
                float f1 = (float) (-(Math.atan2(d5, d7) * 180.0D / 3.1415927410125732D));

                this.bp[i0] = this.b(this.bp[i0], f1, 40.0F);
                this.bq[i0] = this.b(this.bq[i0], f0, 10.0F);
            } else {
                this.bq[i0] = this.b(this.bq[i0], this.aN, 10.0F);
            }
        }

        boolean flag0 = this.bV();

        for (i1 = 0; i1 < 3; ++i1) {
            double d8 = this.r(i1);
            double d9 = this.s(i1);
            double d10 = this.t(i1);

            this.q.a("smoke", d8 + this.ab.nextGaussian() * 0.30000001192092896D, d9 + this.ab.nextGaussian() * 0.30000001192092896D, d10 + this.ab.nextGaussian() * 0.30000001192092896D, 0.0D, 0.0D, 0.0D);
            if (flag0 && this.q.s.nextInt(4) == 0) {
                this.q.a("mobSpell", d8 + this.ab.nextGaussian() * 0.30000001192092896D, d9 + this.ab.nextGaussian() * 0.30000001192092896D, d10 + this.ab.nextGaussian() * 0.30000001192092896D, 0.699999988079071D, 0.699999988079071D, 0.5D);
            }
        }

        if (this.bU() > 0) {
            for (i1 = 0; i1 < 3; ++i1) {
                this.q.a("mobSpell", this.u + this.ab.nextGaussian() * 1.0D, this.v + (double) (this.ab.nextFloat() * 3.3F), this.w + this.ab.nextGaussian() * 1.0D, 0.699999988079071D, 0.699999988079071D, 0.8999999761581421D);
            }
        }
    }

    protected void bh() {
        int i0;

        if (this.bU() > 0) {
            i0 = this.bU() - 1;
            if (i0 <= 0) {
                this.q.a(this, this.u, this.v + (double) this.f(), this.w, 7.0F, false, this.q.O().b("mobGriefing"));
                this.q.d(1013, (int) this.u, (int) this.v, (int) this.w, 0);
            }

            this.p(i0);
            if (this.ac % 10 == 0) {
                this.f(10.0F);
            }
        } else {
            super.bh();

            int i1;

            for (i0 = 1; i0 < 3; ++i0) {
                if (this.ac >= this.bt[i0 - 1]) {
                    this.bt[i0 - 1] = this.ac + 10 + this.ab.nextInt(10);
                    if (this.q.r >= 2) {
                        int i4001 = i0 - 1;
                        int i4003 = this.bu[i0 - 1];

                        this.bu[i4001] = this.bu[i0 - 1] + 1;
                        if (i4003 > 15) {
                            float f0 = 10.0F;
                            float f1 = 5.0F;
                            double d0 = MathHelper.a(this.ab, this.u - (double) f0, this.u + (double) f0);
                            double d1 = MathHelper.a(this.ab, this.v - (double) f1, this.v + (double) f1);
                            double d2 = MathHelper.a(this.ab, this.w - (double) f0, this.w + (double) f0);

                            this.a(i0 + 1, d0, d1, d2, true);
                            this.bu[i0 - 1] = 0;
                        }
                    }

                    i1 = this.q(i0);
                    if (i1 > 0) {
                        Entity entity = this.q.a(i1);

                        if (entity != null && entity.S() && this.e(entity) <= 900.0D && this.o(entity)) {
                            this.a(i0 + 1, (EntityLivingBase) entity);
                            this.bt[i0 - 1] = this.ac + 40 + this.ab.nextInt(20);
                            this.bu[i0 - 1] = 0;
                        } else {
                            this.c(i0, 0);
                        }
                    } else {
                        List list = this.q.a(EntityLivingBase.class, this.E.b(20.0D, 8.0D, 20.0D), bw);

                        for (int i4 = 0; i4 < 10 && !list.isEmpty(); ++i4) {
                            EntityLivingBase entitylivingbase = (EntityLivingBase) list.get(this.ab.nextInt(list.size()));

                            if (entitylivingbase != this && entitylivingbase.S() && this.o(entitylivingbase)) {
                                if (entitylivingbase instanceof EntityPlayer) {
                                    if (!((EntityPlayer) entitylivingbase).bG.a) {
                                        this.c(i0, entitylivingbase.k);
                                    }
                                } else {
                                    this.c(i0, entitylivingbase.k);
                                }
                                break;
                            }

                            list.remove(entitylivingbase);
                        }
                    }
                }
            }

            if (this.m() != null) {
                this.c(0, this.m().k);
            } else {
                this.c(0, 0);
            }

            if (this.bv > 0) {
                --this.bv;
                if (this.bv == 0 && this.q.O().b("mobGriefing")) {
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

                                if (i12 > 0 && i12 != Block.E.cF && i12 != Block.bM.cF && i12 != Block.bN.cF) {
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
                this.f(1.0F);
            }
        }
    }

    public void bT() {
        this.p(220);
        this.g(this.aS() / 3.0F);
    }

    public void al() {}

    public int aP() {
        return 4;
    }

    private double r(int i0) {
        if (i0 <= 0) {
            return this.u;
        } else {
            float f0 = (this.aN + (float) (180 * (i0 - 1))) / 180.0F * 3.1415927F;
            float f1 = MathHelper.b(f0);

            return this.u + (double) f1 * 1.3D;
        }
    }

    private double s(int i0) {
        return i0 <= 0 ? this.v + 3.0D : this.v + 2.2D;
    }

    private double t(int i0) {
        if (i0 <= 0) {
            return this.w;
        } else {
            float f0 = (this.aN + (float) (180 * (i0 - 1))) / 180.0F * 3.1415927F;
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

    private void a(int i0, EntityLivingBase entitylivingbase) {
        this.a(i0, entitylivingbase.u, entitylivingbase.v + (double) entitylivingbase.f() * 0.5D, entitylivingbase.w, i0 == 0 && this.ab.nextFloat() < 0.001F);
    }

    private void a(int i0, double d0, double d1, double d2, boolean flag0) {
        this.q.a((EntityPlayer) null, 1014, (int) this.u, (int) this.v, (int) this.w, 0);
        double d3 = this.r(i0);
        double d4 = this.s(i0);
        double d5 = this.t(i0);
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

    public void a(EntityLivingBase entitylivingbase, float f0) {
        this.a(0, entitylivingbase);
    }

    public boolean a(DamageSource damagesource, float f0) {
        if (this.aq()) {
            return false;
        } else if (damagesource == DamageSource.e) {
            return false;
        } else if (this.bU() > 0) {
            return false;
        } else {
            Entity entity;

            if (this.bV()) {
                entity = damagesource.h();
                if (entity instanceof EntityArrow) {
                    return false;
                }
            }

            entity = damagesource.i();
            if (entity != null && !(entity instanceof EntityPlayer) && entity instanceof EntityLivingBase && ((EntityLivingBase) entity).aX() == this.aX()) {
                return false;
            } else {
                if (this.bv <= 0) {
                    this.bv = 20;
                }

                for (int i0 = 0; i0 < this.bu.length; ++i0) {
                    this.bu[i0] += 3;
                }

                return super.a(damagesource, f0);
            }
        }
    }

    protected void b(boolean flag0, int i0) {
        this.b(Item.bU.cv, 1);
    }

    protected void bo() {
        this.aV = 0;
    }

    public boolean K() {
        return !this.M;
    }

    protected void b(float f0) {}

    public void c(PotionEffect potioneffect) {}

    protected boolean be() {
        return true;
    }

    protected void ay() {
        super.ay();
        this.a(SharedMonsterAttributes.a).a(300.0D);
        this.a(SharedMonsterAttributes.d).a(0.6000000238418579D);
        this.a(SharedMonsterAttributes.b).a(40.0D);
    }

    public int bU() {
        return this.ah.c(20);
    }

    public void p(int i0) {
        this.ah.b(20, Integer.valueOf(i0));
    }

    public int q(int i0) {
        return this.ah.c(17 + i0);
    }

    public void c(int i0, int i1) {
        this.ah.b(17 + i0, Integer.valueOf(i1));
    }

    public boolean bV() {
        return this.aM() <= this.aS() / 2.0F;
    }

    public EnumCreatureAttribute aX() {
        return EnumCreatureAttribute.b;
    }

    public void a(Entity entity) {
        this.o = null;
    }
}
