package net.minecraft.server;


import net.canarymod.api.entity.living.monster.CanarySlime;


public class EntitySlime extends EntityLiving implements IMob {

    private static final float[] e = new float[] { 1.0F, 0.75F, 0.5F, 0.25F, 0.0F, 0.25F, 0.5F, 0.75F};
    public float b;
    public float c;
    public float d;
    private int f = 0;

    public EntitySlime(World world) {
        super(world);
        this.aH = "/mob/slime.png";
        int i0 = 1 << this.ab.nextInt(3);

        this.N = 0.0F;
        this.f = this.ab.nextInt(20) + 10;
        this.a(i0);
        this.entity = new CanarySlime(this); // CanaryMod: Wrap Entity
    }

    protected void a() {
        super.a();
        this.ah.a(16, new Byte((byte) 1));
    }

    protected void a(int i0) {
        this.ah.b(16, new Byte((byte) i0));
        this.a(0.6F * (float) i0, 0.6F * (float) i0);
        this.b(this.u, this.v, this.w);
        this.b(this.aW());
        this.be = i0;
    }

    public int aW() {
        int i0 = this.p();

        return i0 * i0;
    }

    public int p() {
        return this.ah.a(16);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("Size", this.p() - 1);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.a(nbttagcompound.e("Size") + 1);
    }

    protected String h() {
        return "slime";
    }

    protected String n() {
        return "mob.slime." + (this.p() > 1 ? "big" : "small");
    }

    public void l_() {
        if (!this.q.I && this.q.r == 0 && this.p() > 0) {
            this.M = true;
        }

        this.c += (this.b - this.c) * 0.5F;
        this.d = this.c;
        boolean flag0 = this.F;

        super.l_();
        int i0;

        if (this.F && !flag0) {
            i0 = this.p();

            for (int i1 = 0; i1 < i0 * 8; ++i1) {
                float f0 = this.ab.nextFloat() * 3.1415927F * 2.0F;
                float f1 = this.ab.nextFloat() * 0.5F + 0.5F;
                float f2 = MathHelper.a(f0) * (float) i0 * 0.5F * f1;
                float f3 = MathHelper.b(f0) * (float) i0 * 0.5F * f1;

                this.q.a(this.h(), this.u + (double) f2, this.E.b, this.w + (double) f3, 0.0D, 0.0D, 0.0D);
            }

            if (this.o()) {
                this.a(this.n(), this.ba(), ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            }

            this.b = -0.5F;
        } else if (!this.F && flag0) {
            this.b = 1.0F;
        }

        this.k();
        if (this.q.I) {
            i0 = this.p();
            this.a(0.6F * (float) i0, 0.6F * (float) i0);
        }
    }

    protected void bq() {
        this.bn();
        EntityPlayer entityplayer = this.q.b(this, 16.0D);

        if (entityplayer != null) {
            this.a(entityplayer, 10.0F, 20.0F);
        }

        if (this.F && this.f-- <= 0) {
            this.f = this.j();
            if (entityplayer != null) {
                this.f /= 3;
            }

            this.bG = true;
            if (this.q()) {
                this.a(this.n(), this.ba(), ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F) * 0.8F);
            }

            this.bD = 1.0F - this.ab.nextFloat() * 2.0F;
            this.bE = (float) (1 * this.p());
        } else {
            this.bG = false;
            if (this.F) {
                this.bD = this.bE = 0.0F;
            }
        }
    }

    protected void k() {
        this.b *= 0.6F;
    }

    protected int j() {
        return this.ab.nextInt(20) + 10;
    }

    protected EntitySlime i() {
        return new EntitySlime(this.q);
    }

    public void w() {
        int i0 = this.p();

        if (!this.q.I && i0 > 1 && this.aX() <= 0) {
            int i1 = 2 + this.ab.nextInt(3);

            for (int i2 = 0; i2 < i1; ++i2) {
                float f0 = ((float) (i2 % 2) - 0.5F) * (float) i0 / 4.0F;
                float f1 = ((float) (i2 / 2) - 0.5F) * (float) i0 / 4.0F;
                EntitySlime entityslime = this.i();

                entityslime.a(i0 / 2);
                entityslime.b(this.u + (double) f0, this.v + 0.5D, this.w + (double) f1, this.ab.nextFloat() * 360.0F, 0.0F);
                this.q.d((Entity) entityslime);
            }
        }

        super.w();
    }

    public void b_(EntityPlayer entityplayer) {
        if (this.l()) {
            int i0 = this.p();

            if (this.n(entityplayer) && this.e(entityplayer) < 0.6D * (double) i0 * 0.6D * (double) i0 && entityplayer.a(DamageSource.a((EntityLiving) this), this.m())) {
                this.a("mob.attack", 1.0F, (this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F);
            }
        }
    }

    protected boolean l() {
        return this.p() > 1;
    }

    protected int m() {
        return this.p();
    }

    protected String bc() {
        return "mob.slime." + (this.p() > 1 ? "big" : "small");
    }

    protected String bd() {
        return "mob.slime." + (this.p() > 1 ? "big" : "small");
    }

    protected int be() {
        return this.p() == 1 ? Item.aN.cp : 0;
    }

    public boolean bv() {
        Chunk chunk = this.q.d(MathHelper.c(this.u), MathHelper.c(this.w));

        if (this.q.L().u() == WorldType.c && this.ab.nextInt(4) != 1) {
            return false;
        } else {
            if (this.p() == 1 || this.q.r > 0) {
                BiomeGenBase biomegenbase = this.q.a(MathHelper.c(this.u), MathHelper.c(this.w));

                if (biomegenbase == BiomeGenBase.h && this.v > 50.0D && this.v < 70.0D && this.ab.nextFloat() < 0.5F && this.ab.nextFloat() < e[this.q.v()] && this.q.n(MathHelper.c(this.u), MathHelper.c(this.v), MathHelper.c(this.w)) <= this.ab.nextInt(8)) {
                    return super.bv();
                }

                if (this.ab.nextInt(10) == 0 && chunk.a(987234911L).nextInt(10) == 0 && this.v < 40.0D) {
                    return super.bv();
                }
            }

            return false;
        }
    }

    protected float ba() {
        return 0.4F * (float) this.p();
    }

    public int bs() {
        return 0;
    }

    protected boolean q() {
        return this.p() > 0;
    }

    protected boolean o() {
        return this.p() > 2;
    }
}
