package net.minecraft.server;


import java.util.Iterator;
import java.util.List;
import net.canarymod.api.entity.living.monster.CanaryWitch;


public class EntityWitch extends EntityMob implements IRangedAttackMob {

    private static final int[] d = new int[] { Item.aU.cp, Item.aZ.cp, Item.aD.cp, Item.bv.cp, Item.bu.cp, Item.N.cp, Item.E.cp, Item.E.cp};
    private int e = 0;

    public EntityWitch(World world) {
        super(world);
        this.aH = "/mob/villager/witch.png";
        this.bI = 0.25F;
        this.bo.a(1, new EntityAISwimming(this));
        this.bo.a(2, new EntityAIArrowAttack(this, this.bI, 60, 10.0F));
        this.bo.a(2, new EntityAIWander(this, this.bI));
        this.bo.a(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.bo.a(3, new EntityAILookIdle(this));
        this.bp.a(1, new EntityAIHurtByTarget(this, false));
        this.bp.a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));
        this.entity = new CanaryWitch(this); // CanaryMod: Wrap Entity
    }

    protected void a() {
        super.a();
        this.u().a(21, Byte.valueOf((byte) 0));
    }

    protected String bb() {
        return "mob.witch.idle";
    }

    protected String bc() {
        return "mob.witch.hurt";
    }

    protected String bd() {
        return "mob.witch.death";
    }

    public void a(boolean flag0) {
        this.u().b(21, Byte.valueOf((byte) (flag0 ? 1 : 0)));
    }

    public boolean m() {
        return this.u().a(21) == 1;
    }

    public int aW() {
        return maxHealth == 0 ? 26 : maxHealth; // CanaryMod: custom Max Health
    }

    public boolean bh() {
        return true;
    }

    public void c() {
        if (!this.q.I) {
            if (this.m()) {
                if (this.e-- <= 0) {
                    this.a(false);
                    ItemStack itemstack = this.bG();

                    this.c(0, (ItemStack) null);
                    if (itemstack != null && itemstack.c == Item.bt.cp) {
                        List list = Item.bt.g(itemstack);

                        if (list != null) {
                            Iterator iterator = list.iterator();

                            while (iterator.hasNext()) {
                                PotionEffect potioneffect = (PotionEffect) iterator.next();

                                this.d(new PotionEffect(potioneffect));
                            }
                        }
                    }
                }
            } else {
                short short1 = -1;

                if (this.ab.nextFloat() < 0.15F && this.ae() && !this.a(Potion.n)) {
                    short1 = 16307;
                } else if (this.ab.nextFloat() < 0.05F && this.aS < this.aW()) {
                    short1 = 16341;
                } else if (this.ab.nextFloat() < 0.25F && this.aJ() != null && !this.a(Potion.c) && this.aJ().e(this) > 121.0D) {
                    short1 = 16274;
                } else if (this.ab.nextFloat() < 0.25F && this.aJ() != null && !this.a(Potion.c) && this.aJ().e(this) > 121.0D) {
                    short1 = 16274;
                }

                if (short1 > -1) {
                    this.c(0, new ItemStack(Item.bt, 1, short1));
                    this.e = this.bG().n();
                    this.a(true);
                }
            }

            if (this.ab.nextFloat() < 7.5E-4F) {
                this.q.a((Entity) this, (byte) 15);
            }
        }

        super.c();
    }

    protected int c(DamageSource damagesource, int i0) {
        i0 = super.c(damagesource, i0);
        if (damagesource.i() == this) {
            i0 = 0;
        }

        if (damagesource.q()) {
            i0 = (int) ((double) i0 * 0.15D);
        }

        return i0;
    }

    public float bE() {
        float f0 = super.bE();

        if (this.m()) {
            f0 *= 0.75F;
        }

        return f0;
    }

    protected void a(boolean flag0, int i0) {
        int i1 = this.ab.nextInt(3) + 1;

        for (int i2 = 0; i2 < i1; ++i2) {
            int i3 = this.ab.nextInt(3);
            int i4 = d[this.ab.nextInt(d.length)];

            if (i0 > 0) {
                i3 += this.ab.nextInt(i0 + 1);
            }

            for (int i5 = 0; i5 < i3; ++i5) {
                this.b(i4, 1);
            }
        }
    }

    public void a(EntityLiving entityliving, float f0) {
        if (!this.m()) {
            EntityPotion entitypotion = new EntityPotion(this.q, this, 32732);

            entitypotion.B -= -20.0F;
            double d0 = entityliving.u + entityliving.x - this.u;
            double d1 = entityliving.v + (double) entityliving.e() - 1.100000023841858D - this.v;
            double d2 = entityliving.w + entityliving.z - this.w;
            float f1 = MathHelper.a(d0 * d0 + d2 * d2);

            if (f1 >= 8.0F && !entityliving.a(Potion.d)) {
                entitypotion.a(32698);
            } else if (entityliving.aX() >= 8 && !entityliving.a(Potion.u)) {
                entitypotion.a(32660);
            } else if (f1 <= 3.0F && !entityliving.a(Potion.t) && this.ab.nextFloat() < 0.25F) {
                entitypotion.a(32696);
            }

            entitypotion.c(d0, d1 + (double) (f1 * 0.2F), d2, 0.75F, 8.0F);
            this.q.d((Entity) entitypotion);
        }
    }
}
