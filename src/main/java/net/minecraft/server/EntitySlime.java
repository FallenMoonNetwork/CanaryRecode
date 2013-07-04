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
        this.ah.a(16, new Byte((byte) 1));
    }

    protected void a(int i0) {
        this.ah.b(16, new Byte((byte) i0));
        this.a(0.6F * (float) i0, 0.6F * (float) i0);
        this.b(this.u, this.v, this.w);
        this.a(SharedMonsterAttributes.a).a((double) (i0 * i0));
        this.g(this.aP());
        this.b = i0;
    }

    public int bN() {
        return this.ah.a(16);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("Size", this.bN() - 1);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.a(nbttagcompound.e("Size") + 1);
    }

    protected String bF() {
        return "slime";
    }

    protected String bL() {
        return "mob.slime." + (this.bN() > 1 ? "big" : "small");
    }

    public void l_() {
        if (!this.q.I && this.q.r == 0 && this.bN() > 0) {
            this.M = true;
        }

        this.i += (this.h - this.i) * 0.5F;
        this.j = this.i;
        boolean flag0 = this.F;

        super.l_();
        int i0;

        if (this.F && !flag0) {
            i0 = this.bN();

            for (int i1 = 0; i1 < i0 * 8; ++i1) {
                float f0 = this.ab.nextFloat() * 3.1415927F * 2.0F;
                float f1 = this.ab.nextFloat() * 0.5F + 0.5F;
                float f2 = MathHelper.a(f0) * (float) i0 * 0.5F * f1;
                float f3 = MathHelper.b(f0) * (float) i0 * 0.5F * f1;

                this.q.a(this.bF(), this.u + (double) f2, this.E.b, this.w + (double) f3, 0.0D, 0.0D, 0.0D);
            }

            if (this.bM()) {
                this.a(this.bL(), this.aW(), ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            }

            this.h = -0.5F;
        } else if (!this.F && flag0) {
            this.h = 1.0F;
        }

        this.bI();
        if (this.q.I) {
            i0 = this.bN();
            this.a(0.6F * (float) i0, 0.6F * (float) i0);
        }
    }

    protected void bh() {
        this.bk();
        EntityPlayer entityplayer = this.q.b(this, 16.0D);

        if (entityplayer != null) {
            this.a(entityplayer, 10.0F, 20.0F);
        }

        if (this.F && this.bn-- <= 0) {
            this.bn = this.bH();
            if (entityplayer != null) {
                this.bn /= 3;
            }

            this.bd = true;
            if (this.bO()) {
                this.a(this.bL(), this.aW(), ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F) * 0.8F);
            }

            this.be = 1.0F - this.ab.nextFloat() * 2.0F;
            this.bf = (float) (1 * this.bN());
        } else {
            this.bd = false;
            if (this.F) {
                this.be = this.bf = 0.0F;
            }
        }
    }

    protected void bI() {
        this.h *= 0.6F;
    }

    protected int bH() {
        return this.ab.nextInt(20) + 10;
    }

    protected EntitySlime bG() {
        return new EntitySlime(this.q);
    }

    public void w() {
        int i0 = this.bN();

        if (!this.q.I && i0 > 1 && this.aJ() <= 0.0F) {
            int i1 = 2 + this.ab.nextInt(3);

            for (int i2 = 0; i2 < i1; ++i2) {
                float f0 = ((float) (i2 % 2) - 0.5F) * (float) i0 / 4.0F;
                float f1 = ((float) (i2 / 2) - 0.5F) * (float) i0 / 4.0F;
                EntitySlime entityslime = this.bG();

                entityslime.a(i0 / 2);
                entityslime.b(this.u + (double) f0, this.v + 0.5D, this.w + (double) f1, this.ab.nextFloat() * 360.0F, 0.0F);
                this.q.d((Entity) entityslime);
            }
        }

        super.w();
    }

    public void b_(EntityPlayer entityplayer) {
        if (this.bJ()) {
            int i0 = this.bN();

            if (this.o(entityplayer) && this.e(entityplayer) < 0.6D * (double) i0 * 0.6D * (double) i0 && entityplayer.a(DamageSource.a((EntityLivingBase) this), (float) this.bK())) {
                this.a("mob.attack", 1.0F, (this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F);
            }
        }
    }

    protected boolean bJ() {
        return this.bN() > 1;
    }

    protected int bK() {
        return this.bN();
    }

    protected String aK() {
        return "mob.slime." + (this.bN() > 1 ? "big" : "small");
    }

    protected String aL() {
        return "mob.slime." + (this.bN() > 1 ? "big" : "small");
    }

    protected int s() {
        return this.bN() == 1 ? Item.aO.cv : 0;
    }

    public boolean bo() {
        Chunk chunk = this.q.d(MathHelper.c(this.u), MathHelper.c(this.w));

        if (this.q.N().u() == WorldType.c && this.ab.nextInt(4) != 1) {
            return false;
        } else {
            if (this.bN() == 1 || this.q.r > 0) {
                BiomeGenBase biomegenbase = this.q.a(MathHelper.c(this.u), MathHelper.c(this.w));

                if (biomegenbase == BiomeGenBase.h && this.v > 50.0D && this.v < 70.0D && this.ab.nextFloat() < 0.5F && this.ab.nextFloat() < this.q.x() && this.q.n(MathHelper.c(this.u), MathHelper.c(this.v), MathHelper.c(this.w)) <= this.ab.nextInt(8)) {
                    return super.bo();
                }

                if (this.ab.nextInt(10) == 0 && chunk.a(987234911L).nextInt(10) == 0 && this.v < 40.0D) {
                    return super.bo();
                }
            }

            return false;
        }
    }

    protected float aW() {
        return 0.4F * (float) this.bN();
    }

    public int bl() {
        return 0;
    }

    protected boolean bO() {
        return this.bN() > 0;
    }

    protected boolean bM() {
        return this.bN() > 2;
    }
}
