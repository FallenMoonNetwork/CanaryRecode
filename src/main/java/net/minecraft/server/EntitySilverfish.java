package net.minecraft.server;


import net.canarymod.api.entity.living.monster.CanarySilverfish;


public class EntitySilverfish extends EntityMob {

    private int bp;

    public EntitySilverfish(World world) {
        super(world);
        this.a(0.3F, 0.7F);
        this.entity = new CanarySilverfish(this); // CanaryMod: Wrap Entity
    }

    protected void ax() {
        super.ax();
        this.a(SharedMonsterAttributes.a).a(8.0D);
        this.a(SharedMonsterAttributes.d).a(0.6000000238418579D);
        this.a(SharedMonsterAttributes.e).a(1.0D);
    }

    protected boolean e_() {
        return false;
    }

    protected Entity bH() {
        double d0 = 8.0D;

        return this.q.b(this, d0);
    }

    protected String r() {
        return "mob.silverfish.say";
    }

    protected String aK() {
        return "mob.silverfish.hit";
    }

    protected String aL() {
        return "mob.silverfish.kill";
    }

    public boolean a(DamageSource damagesource, float f0) {
        if (this.ap()) {
            return false;
        } else {
            if (this.bp <= 0 && (damagesource instanceof EntityDamageSource || damagesource == DamageSource.k)) {
                this.bp = 20;
            }

            return super.a(damagesource, f0);
        }
    }

    protected void a(Entity entity, float f0) {
        if (this.aC <= 0 && f0 < 1.2F && entity.E.e > this.E.b && entity.E.b < this.E.e) {
            this.aC = 20;
            this.m(entity);
        }
    }

    protected void a(int i0, int i1, int i2, int i3) {
        this.a("mob.silverfish.step", 0.15F, 1.0F);
    }

    protected int s() {
        return 0;
    }

    public void l_() {
        this.aN = this.A;
        super.l_();
    }

    protected void bh() {
        super.bh();
        if (!this.q.I) {
            int i0;
            int i1;
            int i2;
            int i3;

            if (this.bp > 0) {
                --this.bp;
                if (this.bp == 0) {
                    i0 = MathHelper.c(this.u);
                    i1 = MathHelper.c(this.v);
                    i2 = MathHelper.c(this.w);
                    boolean flag0 = false;

                    for (i3 = 0; !flag0 && i3 <= 5 && i3 >= -5; i3 = i3 <= 0 ? 1 - i3 : 0 - i3) {
                        for (int i4 = 0; !flag0 && i4 <= 10 && i4 >= -10; i4 = i4 <= 0 ? 1 - i4 : 0 - i4) {
                            for (int i5 = 0; !flag0 && i5 <= 10 && i5 >= -10; i5 = i5 <= 0 ? 1 - i5 : 0 - i5) {
                                int i6 = this.q.a(i0 + i4, i1 + i3, i2 + i5);

                                if (i6 == Block.bq.cF) {
                                    if (!this.q.O().b("mobGriefing")) {
                                        int i7 = this.q.h(i0 + i4, i1 + i3, i2 + i5);
                                        Block block = Block.y;

                                        if (i7 == 1) {
                                            block = Block.B;
                                        }

                                        if (i7 == 2) {
                                            block = Block.br;
                                        }

                                        this.q.f(i0 + i4, i1 + i3, i2 + i5, block.cF, 0, 3);
                                    } else {
                                        this.q.a(i0 + i4, i1 + i3, i2 + i5, false);
                                    }

                                    Block.bq.g(this.q, i0 + i4, i1 + i3, i2 + i5, 0);
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

            if (this.j == null && !this.bI()) {
                i0 = MathHelper.c(this.u);
                i1 = MathHelper.c(this.v + 0.5D);
                i2 = MathHelper.c(this.w);
                int i8 = this.ab.nextInt(6);

                i3 = this.q.a(i0 + Facing.b[i8], i1 + Facing.c[i8], i2 + Facing.d[i8]);
                if (BlockSilverfish.d(i3)) {
                    this.q.f(i0 + Facing.b[i8], i1 + Facing.c[i8], i2 + Facing.d[i8], Block.bq.cF, BlockSilverfish.e(i3), 3);
                    this.q();
                    this.w();
                } else {
                    this.bG();
                }
            } else if (this.j != null && !this.bI()) {
                this.j = null;
            }
        }
    }

    public float a(int i0, int i1, int i2) {
        return this.q.a(i0, i1 - 1, i2) == Block.y.cF ? 10.0F : super.a(i0, i1, i2);
    }

    protected boolean i_() {
        return true;
    }

    public boolean bo() {
        if (super.bo()) {
            EntityPlayer entityplayer = this.q.a(this, 5.0D);

            return entityplayer == null;
        } else {
            return false;
        }
    }

    public EnumCreatureAttribute aU() {
        return EnumCreatureAttribute.c;
    }
}
