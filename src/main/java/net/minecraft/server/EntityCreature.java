package net.minecraft.server;

import net.canarymod.hook.entity.MobTargetHook;

import java.util.UUID;

public abstract class EntityCreature extends EntityLiving {

    public static final UUID h = UUID.fromString("E199AD21-BA8A-4C53-8D13-6182D5C69D3A");
    public static final AttributeModifier i = (new AttributeModifier(h, "Fleeing speed bonus", 2.0D, 2)).a(false);
    private PathEntity bp;
    protected Entity j;
    protected boolean bn;
    protected int bo;
    private ChunkCoordinates bq = new ChunkCoordinates(0, 0, 0);
    private float br = -1.0F;
    private EntityAIBase bs = new EntityAIMoveTowardsRestriction(this, 1.0D);
    private boolean bt;

    public EntityCreature(World world) {
        super(world);
    }

    protected boolean bJ() {
        return false;
    }

    protected void bl() {
        this.q.C.a("ai");
        if (this.bo > 0 && --this.bo == 0) {
            AttributeInstance attributeinstance = this.a(SharedMonsterAttributes.d);

            attributeinstance.b(i);
        }

        this.bn = this.bJ();
        float f11 = 16.0F;

        if (this.j == null) {
            // CanaryMod: MobTarget
            Entity entity = this.bL();
            if (entity != null && entity instanceof EntityLivingBase) {
                MobTargetHook hook = (MobTargetHook)new MobTargetHook((net.canarymod.api.entity.living.LivingBase)this.getCanaryEntity(), (net.canarymod.api.entity.living.LivingBase)entity.getCanaryEntity()).call();
                if (!hook.isCanceled()) {
                    this.j = entity;
                }
            }
            //

            if (this.j != null) {
                this.bp = this.q.a(this, this.j, f11, true, false, false, true);
            }
        }
        else if (this.j.T()) {
            float f1 = this.j.d((Entity)this);

            if (this.o(this.j)) {
                this.a(this.j, f1);
            }
        }
        else {
            this.j = null;
        }

        this.q.C.b();
        if (!this.bn && this.j != null && (this.bp == null || this.ab.nextInt(20) == 0)) {
            this.bp = this.q.a(this, this.j, f11, true, false, false, true);
        }
        else if (!this.bn && (this.bp == null && this.ab.nextInt(180) == 0 || this.ab.nextInt(120) == 0 || this.bo > 0) && this.aV < 100) {
            this.bK();
        }

        int i0 = MathHelper.c(this.E.b + 0.5D);
        boolean flag0 = this.H();
        boolean flag1 = this.J();

        this.B = 0.0F;
        if (this.bp != null && this.ab.nextInt(100) != 0) {
            this.q.C.a("followpath");
            Vec3 vec3 = this.bp.a((Entity)this);
            double d0 = (double)(this.O * 2.0F);

            while (vec3 != null && vec3.d(this.u, vec3.d, this.w) < d0 * d0) {
                this.bp.a();
                if (this.bp.b()) {
                    vec3 = null;
                    this.bp = null;
                }
                else {
                    vec3 = this.bp.a((Entity)this);
                }
            }

            this.bd = false;
            if (vec3 != null) {
                double d1 = vec3.c - this.u;
                double d2 = vec3.e - this.w;
                double d3 = vec3.d - (double)i0;
                float f2 = (float)(Math.atan2(d2, d1) * 180.0D / 3.1415927410125732D) - 90.0F;
                float f3 = MathHelper.g(f2 - this.A);

                this.bf = (float)this.a(SharedMonsterAttributes.d).e();
                if (f3 > 30.0F) {
                    f3 = 30.0F;
                }

                if (f3 < -30.0F) {
                    f3 = -30.0F;
                }

                this.A += f3;
                if (this.bn && this.j != null) {
                    double d4 = this.j.u - this.u;
                    double d5 = this.j.w - this.w;
                    float f4 = this.A;

                    this.A = (float)(Math.atan2(d5, d4) * 180.0D / 3.1415927410125732D) - 90.0F;
                    f3 = (f4 - this.A + 90.0F) * 3.1415927F / 180.0F;
                    this.be = -MathHelper.a(f3) * this.bf * 1.0F;
                    this.bf = MathHelper.b(f3) * this.bf * 1.0F;
                }

                if (d3 > 0.0D) {
                    this.bd = true;
                }
            }

            if (this.j != null) {
                this.a(this.j, 30.0F, 30.0F);
            }

            if (this.G && !this.bM()) {
                this.bd = true;
            }

            if (this.ab.nextFloat() < 0.8F && (flag0 || flag1)) {
                this.bd = true;
            }

            this.q.C.b();
        }
        else {
            super.bk();
            this.bp = null;
        }
    }

    protected void bK() {
        this.q.C.a("stroll");
        boolean flag0 = false;
        int i0 = -1;
        int i1 = -1;
        int i2 = -1;
        float f0 = -99999.0F;

        for (int i3 = 0; i3 < 10; ++i3) {
            int i4 = MathHelper.c(this.u + (double)this.ab.nextInt(13) - 6.0D);
            int i5 = MathHelper.c(this.v + (double)this.ab.nextInt(7) - 3.0D);
            int i6 = MathHelper.c(this.w + (double)this.ab.nextInt(13) - 6.0D);
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
            this.bp = this.q.a(this, i0, i1, i2, 10.0F, true, false, false, true);
        }

        this.q.C.b();
    }

    protected void a(Entity entity, float f0) {
    }

    public float a(int i0, int i1, int i2) {
        return 0.0F;
    }

    protected Entity bL() {
        return null;
    }

    public boolean bs() {
        int i0 = MathHelper.c(this.u);
        int i1 = MathHelper.c(this.E.b);
        int i2 = MathHelper.c(this.w);

        return super.bs() && this.a(i0, i1, i2) >= 0.0F;
    }

    public boolean bM() {
        return this.bp != null;
    }

    public void a(PathEntity pathentity) {
        this.bp = pathentity;
    }

    public Entity bN() {
        return this.j;
    }

    public void b(Entity entity) {
        this.j = entity;
    }

    public boolean bO() {
        return this.b(MathHelper.c(this.u), MathHelper.c(this.v), MathHelper.c(this.w));
    }

    public boolean b(int i0, int i1, int i2) {
        return this.br == -1.0F ? true : this.bq.e(i0, i1, i2) < this.br * this.br;
    }

    public void b(int i0, int i1, int i2, int i3) {
        this.bq.b(i0, i1, i2);
        this.br = (float)i3;
    }

    public ChunkCoordinates bP() {
        return this.bq;
    }

    public float bQ() {
        return this.br;
    }

    public void bR() {
        this.br = -1.0F;
    }

    public boolean bS() {
        return this.br != -1.0F;
    }

    protected void bF() {
        super.bF();
        if (this.bH() && this.bI() != null && this.bI().q == this.q) {
            Entity entity = this.bI();

            this.b((int)entity.u, (int)entity.v, (int)entity.w, 5);
            float f0 = this.d(entity);

            if (this instanceof EntityTameable && ((EntityTameable)this).bU()) {
                if (f0 > 10.0F) {
                    this.a(true, true);
                }

                return;
            }

            if (!this.bt) {
                this.c.a(2, this.bs);
                this.k().a(false);
                this.bt = true;
            }

            this.o(f0);
            if (f0 > 4.0F) {
                this.k().a(entity, 1.0D);
            }

            if (f0 > 6.0F) {
                double d0 = (entity.u - this.u) / (double)f0;
                double d1 = (entity.v - this.v) / (double)f0;
                double d2 = (entity.w - this.w) / (double)f0;

                this.x += d0 * Math.abs(d0) * 0.4D;
                this.y += d1 * Math.abs(d1) * 0.4D;
                this.z += d2 * Math.abs(d2) * 0.4D;
            }

            if (f0 > 10.0F) {
                this.a(true, true);
            }
        }
        else if (!this.bH() && this.bt) {
            this.bt = false;
            this.c.a(this.bs);
            this.k().a(true);
            this.bR();
        }
    }

    protected void o(float f0) {
    }
}
