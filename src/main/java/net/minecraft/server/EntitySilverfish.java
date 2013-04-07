package net.minecraft.server;


import net.canarymod.api.entity.living.monster.CanarySilverfish;


public class EntitySilverfish extends EntityMob {

    private int d;

    public EntitySilverfish(World world) {
        super(world);
        this.aH = "/mob/silverfish.png";
        this.a(0.3F, 0.7F);
        this.bI = 0.6F;
        this.entity = new CanarySilverfish(this); // CanaryMod: Wrap Entity
    }

    public int aW() {
        return maxHealth == 0 ? 8 : maxHealth; // CanaryMod: custom Max Health
    }

    protected boolean f_() {
        return false;
    }

    protected Entity j() {
        double d0 = 8.0D;

        return this.q.b(this, d0);
    }

    protected String bb() {
        return "mob.silverfish.say";
    }

    protected String bc() {
        return "mob.silverfish.hit";
    }

    protected String bd() {
        return "mob.silverfish.kill";
    }

    public boolean a(DamageSource damagesource, int i0) {
        if (this.aq()) {
            return false;
        } else {
            if (this.d <= 0 && (damagesource instanceof EntityDamageSource || damagesource == DamageSource.k)) {
                this.d = 20;
            }

            return super.a(damagesource, i0);
        }
    }

    protected void a(Entity entity, float f0) {
        if (this.ba <= 0 && f0 < 1.2F && entity.E.e > this.E.b && entity.E.b < this.E.e) {
            this.ba = 20;
            this.m(entity);
        }
    }

    protected void a(int i0, int i1, int i2, int i3) {
        this.a("mob.silverfish.step", 0.15F, 1.0F);
    }

    protected int be() {
        return 0;
    }

    public void l_() {
        this.ay = this.A;
        super.l_();
    }

    protected void bq() {
        super.bq();
        if (!this.q.I) {
            int i0;
            int i1;
            int i2;
            int i3;

            if (this.d > 0) {
                --this.d;
                if (this.d == 0) {
                    i0 = MathHelper.c(this.u);
                    i1 = MathHelper.c(this.v);
                    i2 = MathHelper.c(this.w);
                    boolean flag0 = false;

                    for (i3 = 0; !flag0 && i3 <= 5 && i3 >= -5; i3 = i3 <= 0 ? 1 - i3 : 0 - i3) {
                        for (int i4 = 0; !flag0 && i4 <= 10 && i4 >= -10; i4 = i4 <= 0 ? 1 - i4 : 0 - i4) {
                            for (int i5 = 0; !flag0 && i5 <= 10 && i5 >= -10; i5 = i5 <= 0 ? 1 - i5 : 0 - i5) {
                                int i6 = this.q.a(i0 + i4, i1 + i3, i2 + i5);

                                if (i6 == Block.bp.cz) {
                                    this.q.a(i0 + i4, i1 + i3, i2 + i5, false);
                                    Block.bp.g(this.q, i0 + i4, i1 + i3, i2 + i5, 0);
                                    if (this.ab.nextBoolean()) {
                                        flag0 = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (this.a_ == null && !this.k()) {
                i0 = MathHelper.c(this.u);
                i1 = MathHelper.c(this.v + 0.5D);
                i2 = MathHelper.c(this.w);
                int i7 = this.ab.nextInt(6);

                i3 = this.q.a(i0 + Facing.b[i7], i1 + Facing.c[i7], i2 + Facing.d[i7]);
                if (BlockSilverfish.d(i3)) {
                    this.q.f(i0 + Facing.b[i7], i1 + Facing.c[i7], i2 + Facing.d[i7], Block.bp.cz, BlockSilverfish.e(i3), 3);
                    this.aU();
                    this.w();
                } else {
                    this.i();
                }
            } else if (this.a_ != null && !this.k()) {
                this.a_ = null;
            }
        }
    }

    public float a(int i0, int i1, int i2) {
        return this.q.a(i0, i1 - 1, i2) == Block.x.cz ? 10.0F : super.a(i0, i1, i2);
    }

    protected boolean i_() {
        return true;
    }

    public boolean bv() {
        if (super.bv()) {
            EntityPlayer entityplayer = this.q.a(this, 5.0D);

            return entityplayer == null;
        } else {
            return false;
        }
    }

    public int c(Entity entity) {
        return 1;
    }

    public EnumCreatureAttribute bF() {
        return EnumCreatureAttribute.c;
    }
}
