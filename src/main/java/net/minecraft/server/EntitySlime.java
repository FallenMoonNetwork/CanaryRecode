package net.minecraft.server;

import net.canarymod.api.entity.living.monster.CanarySlime;

public class EntitySlime extends EntityLiving implements IMob {

    public float h;
    public float i;
    public float j;
    private int bn;

    public EntitySlime(World world) {
        super(world);
        int i0 = 1 << this.ab.nextInt(3);

        this.N = 0.0F;
        this.bn = this.ab.nextInt(20) + 10;
        this.a(i0);
        this.entity = new CanarySlime(this); // CanaryMod: Wrap Entity
    }

    protected void a() {
        super.a();
        this.ah.a(16, new Byte((byte)1));
    }

    public void a(int i0) { // CanaryMod: protected => public
        this.ah.b(16, new Byte((byte)i0));
        this.a(0.6F * (float)i0, 0.6F * (float)i0);
        this.b(this.u, this.v, this.w);
        this.a(SharedMonsterAttributes.a).a((double)(i0 * i0));
        this.g(this.aT());
        this.b = i0;
    }

    public int bR() {
        return this.ah.a(16);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("Size", this.bR() - 1);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.a(nbttagcompound.e("Size") + 1);
    }

    protected String bJ() {
        return "slime";
    }

    protected String bP() {
        return "mob.slime." + (this.bR() > 1 ? "big" : "small");
    }

    public void l_() {
        if (!this.q.I && this.q.r == 0 && this.bR() > 0) {
            this.M = true;
        }

        this.i += (this.h - this.i) * 0.5F;
        this.j = this.i;
        boolean flag0 = this.F;

        super.l_();
        int i0;

        if (this.F && !flag0) {
            i0 = this.bR();

            for (int i1 = 0; i1 < i0 * 8; ++i1) {
                float f0 = this.ab.nextFloat() * 3.1415927F * 2.0F;
                float f1 = this.ab.nextFloat() * 0.5F + 0.5F;
                float f2 = MathHelper.a(f0) * (float)i0 * 0.5F * f1;
                float f3 = MathHelper.b(f0) * (float)i0 * 0.5F * f1;

                this.q.a(this.bJ(), this.u + (double)f2, this.E.b, this.w + (double)f3, 0.0D, 0.0D, 0.0D);
            }

            if (this.bQ()) {
                this.a(this.bP(), this.ba(), ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            }

            this.h = -0.5F;
        }
        else if (!this.F && flag0) {
            this.h = 1.0F;
        }

        this.bM();
        if (this.q.I) {
            i0 = this.bR();
            this.a(0.6F * (float)i0, 0.6F * (float)i0);
        }
    }

    protected void bl() {
        this.u();
        EntityPlayer entityplayer = this.q.b(this, 16.0D);

        if (entityplayer != null) {
            this.a(entityplayer, 10.0F, 20.0F);
        }

        if (this.F && this.bn-- <= 0) {
            this.bn = this.bL();
            if (entityplayer != null) {
                this.bn /= 3;
            }

            this.bd = true;
            if (this.bS()) {
                this.a(this.bP(), this.ba(), ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F) * 0.8F);
            }

            this.be = 1.0F - this.ab.nextFloat() * 2.0F;
            this.bf = (float)(1 * this.bR());
        }
        else {
            this.bd = false;
            if (this.F) {
                this.be = this.bf = 0.0F;
            }
        }
    }

    protected void bM() {
        this.h *= 0.6F;
    }

    protected int bL() {
        return this.ab.nextInt(20) + 10;
    }

    protected EntitySlime bK() {
        return new EntitySlime(this.q);
    }

    public void x() {
        int i0 = this.bR();

        if (!this.q.I && i0 > 1 && this.aN() <= 0.0F) {
            int i1 = 2 + this.ab.nextInt(3);

            for (int i2 = 0; i2 < i1; ++i2) {
                float f0 = ((float)(i2 % 2) - 0.5F) * (float)i0 / 4.0F;
                float f1 = ((float)(i2 / 2) - 0.5F) * (float)i0 / 4.0F;
                EntitySlime entityslime = this.bK();

                entityslime.a(i0 / 2);
                entityslime.b(this.u + (double)f0, this.v + 0.5D, this.w + (double)f1, this.ab.nextFloat() * 360.0F, 0.0F);
                this.q.d((Entity)entityslime);
            }
        }

        super.x();
    }

    public void b_(EntityPlayer entityplayer) {
        if (this.bN()) {
            int i0 = this.bR();

            if (this.o(entityplayer) && this.e(entityplayer) < 0.6D * (double)i0 * 0.6D * (double)i0 && entityplayer.a(DamageSource.a((EntityLivingBase)this), (float)this.bO())) {
                this.a("mob.attack", 1.0F, (this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F);
            }
        }
    }

    protected boolean bN() {
        return this.bR() > 1;
    }

    protected int bO() {
        return this.bR();
    }

    protected String aO() {
        return "mob.slime." + (this.bR() > 1 ? "big" : "small");
    }

    protected String aP() {
        return "mob.slime." + (this.bR() > 1 ? "big" : "small");
    }

    protected int s() {
        return this.bR() == 1 ? Item.aO.cv : 0;
    }

    public boolean bs() {
        Chunk chunk = this.q.d(MathHelper.c(this.u), MathHelper.c(this.w));

        if (this.q.N().u() == WorldType.c && this.ab.nextInt(4) != 1) {
            return false;
        }
        else {
            if (this.bR() == 1 || this.q.r > 0) {
                BiomeGenBase biomegenbase = this.q.a(MathHelper.c(this.u), MathHelper.c(this.w));

                if (biomegenbase == BiomeGenBase.h && this.v > 50.0D && this.v < 70.0D && this.ab.nextFloat() < 0.5F && this.ab.nextFloat() < this.q.x() && this.q.n(MathHelper.c(this.u), MathHelper.c(this.v), MathHelper.c(this.w)) <= this.ab.nextInt(8)) {
                    return super.bs();
                }

                if (this.ab.nextInt(10) == 0 && chunk.a(987234911L).nextInt(10) == 0 && this.v < 40.0D) {
                    return super.bs();
                }
            }

            return false;
        }
    }

    protected float ba() {
        return 0.4F * (float)this.bR();
    }

    public int bp() {
        return 0;
    }

    protected boolean bS() {
        return this.bR() > 0;
    }

    protected boolean bQ() {
        return this.bR() > 2;
    }
}
