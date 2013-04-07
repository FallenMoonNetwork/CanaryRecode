package net.minecraft.server;


import net.canarymod.Canary;
import net.canarymod.hook.entity.MobTargetHook;


public abstract class EntityCreature extends EntityLiving {

    private PathEntity d;
    protected Entity a_;
    protected boolean b = false;
    protected int c = 0;

    public EntityCreature(World world) {
        super(world);
    }

    protected boolean h() {
        return false;
    }

    protected void bq() {
        this.q.C.a("ai");
        if (this.c > 0) {
            --this.c;
        }

        this.b = this.h();
        float f0 = 16.0F;

        if (this.a_ == null) {
            // CanaryMod: MobTarget
            Entity entity = this.j();

            if (entity != null && entity instanceof EntityLiving) {
                MobTargetHook hook = new MobTargetHook((net.canarymod.api.entity.living.EntityLiving) this.getCanaryEntity(), (net.canarymod.api.entity.living.EntityLiving) entity.getCanaryEntity());

                Canary.hooks().callHook(hook);
                if (!hook.isCanceled()) {
                    this.a_ = entity;
                }
            }
            //

            if (this.a_ != null) {
                this.d = this.q.a(this, this.a_, f0, true, false, false, true);
            }
        } else if (this.a_.R()) {
            float f1 = this.a_.d((Entity) this);

            if (this.n(this.a_)) {
                this.a(this.a_, f1);
            }
        } else {
            this.a_ = null;
        }

        this.q.C.b();
        if (!this.b && this.a_ != null && (this.d == null || this.ab.nextInt(20) == 0)) {
            this.d = this.q.a(this, this.a_, f0, true, false, false, true);
        } else if (!this.b && (this.d == null && this.ab.nextInt(180) == 0 || this.ab.nextInt(120) == 0 || this.c > 0) && this.bC < 100) {
            this.i();
        }

        int i0 = MathHelper.c(this.E.b + 0.5D);
        boolean flag0 = this.G();
        boolean flag1 = this.I();

        this.B = 0.0F;
        if (this.d != null && this.ab.nextInt(100) != 0) {
            this.q.C.a("followpath");
            Vec3 vec3 = this.d.a((Entity) this);
            double d0 = (double) (this.O * 2.0F);

            while (vec3 != null && vec3.d(this.u, vec3.d, this.w) < d0 * d0) {
                this.d.a();
                if (this.d.b()) {
                    vec3 = null;
                    this.d = null;
                } else {
                    vec3 = this.d.a((Entity) this);
                }
            }

            this.bG = false;
            if (vec3 != null) {
                double d1 = vec3.c - this.u;
                double d2 = vec3.e - this.w;
                double d3 = vec3.d - (double) i0;
                float f2 = (float) (Math.atan2(d2, d1) * 180.0D / 3.1415927410125732D) - 90.0F;
                float f3 = MathHelper.g(f2 - this.A);

                this.bE = this.bI;
                if (f3 > 30.0F) {
                    f3 = 30.0F;
                }

                if (f3 < -30.0F) {
                    f3 = -30.0F;
                }

                this.A += f3;
                if (this.b && this.a_ != null) {
                    double d4 = this.a_.u - this.u;
                    double d5 = this.a_.w - this.w;
                    float f4 = this.A;

                    this.A = (float) (Math.atan2(d5, d4) * 180.0D / 3.1415927410125732D) - 90.0F;
                    f3 = (f4 - this.A + 90.0F) * 3.1415927F / 180.0F;
                    this.bD = -MathHelper.a(f3) * this.bE * 1.0F;
                    this.bE = MathHelper.b(f3) * this.bE * 1.0F;
                }

                if (d3 > 0.0D) {
                    this.bG = true;
                }
            }

            if (this.a_ != null) {
                this.a(this.a_, 30.0F, 30.0F);
            }

            if (this.G && !this.k()) {
                this.bG = true;
            }

            if (this.ab.nextFloat() < 0.8F && (flag0 || flag1)) {
                this.bG = true;
            }

            this.q.C.b();
        } else {
            super.bq();
            this.d = null;
        }
    }

    protected void i() {
        this.q.C.a("stroll");
        boolean flag0 = false;
        int i0 = -1;
        int i1 = -1;
        int i2 = -1;
        float f0 = -99999.0F;

        for (int i3 = 0; i3 < 10; ++i3) {
            int i4 = MathHelper.c(this.u + (double) this.ab.nextInt(13) - 6.0D);
            int i5 = MathHelper.c(this.v + (double) this.ab.nextInt(7) - 3.0D);
            int i6 = MathHelper.c(this.w + (double) this.ab.nextInt(13) - 6.0D);
            float f1 = this.a(i4, i5, i6);

            if (f1 > f0) {
                f0 = f1;
                i0 = i4;
                i1 = i5;
                i2 = i6;
                flag0 = true;
            }
        }

        if (flag0) {
            this.d = this.q.a(this, i0, i1, i2, 10.0F, true, false, false, true);
        }

        this.q.C.b();
    }

    protected void a(Entity entity, float f0) {}

    public float a(int i0, int i1, int i2) {
        return 0.0F;
    }

    protected Entity j() {
        return null;
    }

    public boolean bv() {
        int i0 = MathHelper.c(this.u);
        int i1 = MathHelper.c(this.E.b);
        int i2 = MathHelper.c(this.w);

        return super.bv() && this.a(i0, i1, i2) >= 0.0F;
    }

    public boolean k() {
        return this.d != null;
    }

    public void a(PathEntity pathentity) {
        this.d = pathentity;
    }

    public Entity l() {
        return this.a_;
    }

    public void b(Entity entity) {
        this.a_ = entity;
    }

    public float bE() {
        float f0 = super.bE();

        if (this.c > 0 && !this.bh()) {
            f0 *= 2.0F;
        }

        return f0;
    }
}
