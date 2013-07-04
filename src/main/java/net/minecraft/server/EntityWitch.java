package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.canarymod.api.entity.living.monster.CanaryWitch;

public class EntityWitch extends EntityMob implements IRangedAttackMob {

    private static final UUID bp = UUID.fromString("5CD17E52-A79A-43D3-A529-90FDE04B181E");
    private static final AttributeModifier bq = (new AttributeModifier(bp, "Drinking speed penalty", -0.25D, 0)).a(false);
    private static final int[] br = new int[]{ Item.aV.cv, Item.ba.cv, Item.aE.cv, Item.bw.cv, Item.bv.cv, Item.O.cv, Item.F.cv, Item.F.cv };
    private int bs;

    public EntityWitch(World world) {
        super(world);
        this.c.a(1, new EntityAISwimming(this));
        this.c.a(2, new EntityAIArrowAttack(this, 1.0D, 60, 10.0F));
        this.c.a(2, new EntityAIWander(this, 1.0D));
        this.c.a(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.c.a(3, new EntityAILookIdle(this));
        this.d.a(1, new EntityAIHurtByTarget(this, false));
        this.d.a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.entity = new CanaryWitch(this); // CanaryMod: Wrap Entity
    }

    protected void a() {
        super.a();
        this.u().a(21, Byte.valueOf((byte) 0));
    }

    protected String r() {
        return "mob.witch.idle";
    }

    protected String aK() {
        return "mob.witch.hurt";
    }

    protected String aL() {
        return "mob.witch.death";
    }

    public void a(boolean flag0) {
        this.u().b(21, Byte.valueOf((byte) (flag0 ? 1 : 0)));
    }

    public boolean bP() {
        return this.u().a(21) == 1;
    }

    protected void ax() {
        super.ax();
        this.a(SharedMonsterAttributes.a).a(26.0D);
        this.a(SharedMonsterAttributes.d).a(0.25D);
    }

    public boolean bb() {
        return true;
    }

    public void c() {
        if (!this.q.I) {
            if (this.bP()) {
                if (this.bs-- <= 0) {
                    this.a(false);
                    ItemStack itemstack = this.aV();

                    this.c(0, (ItemStack) null);
                    if (itemstack != null && itemstack.d == Item.bu.cv) {
                        List list = Item.bu.g(itemstack);

                        if (list != null) {
                            Iterator iterator = list.iterator();

                            while (iterator.hasNext()) {
                                PotionEffect potioneffect = (PotionEffect) iterator.next();

                                this.d(new PotionEffect(potioneffect));
                            }
                        }
                    }

                    this.a(SharedMonsterAttributes.d).b(bq);
                }
            } else {
                short short1 = -1;

                if (this.ab.nextFloat() < 0.15F && this.ad() && !this.a(Potion.n)) {
                    short1 = 16307;
                } else if (this.ab.nextFloat() < 0.05F && this.aJ() < this.aP()) {
                    short1 = 16341;
                } else if (this.ab.nextFloat() < 0.25F && this.m() != null && !this.a(Potion.c) && this.m().e((Entity) this) > 121.0D) {
                    short1 = 16274;
                } else if (this.ab.nextFloat() < 0.25F && this.m() != null && !this.a(Potion.c) && this.m().e((Entity) this) > 121.0D) {
                    short1 = 16274;
                }

                if (short1 > -1) {
                    this.c(0, new ItemStack(Item.bu, 1, short1));
                    this.bs = this.aV().n();
                    this.a(true);
                    AttributeInstance attributeinstance = this.a(SharedMonsterAttributes.d);

                    attributeinstance.b(bq);
                    attributeinstance.a(bq);
                }
            }

            if (this.ab.nextFloat() < 7.5E-4F) {
                this.q.a((Entity) this, (byte) 15);
            }
        }

        super.c();
    }

    protected float c(DamageSource damagesource, float f0) {
        f0 = super.c(damagesource, f0);
        if (damagesource.i() == this) {
            f0 = 0.0F;
        }

        if (damagesource.q()) {
            f0 = (float) ((double) f0 * 0.15D);
        }

        return f0;
    }

    protected void b(boolean flag0, int i0) {
        int i1 = this.ab.nextInt(3) + 1;

        for (int i2 = 0; i2 < i1; ++i2) {
            int i3 = this.ab.nextInt(3);
            int i4 = br[this.ab.nextInt(br.length)];

            if (i0 > 0) {
                i3 += this.ab.nextInt(i0 + 1);
            }

            for (int i5 = 0; i5 < i3; ++i5) {
                this.b(i4, 1);
            }
        }
    }

    public void a(EntityLivingBase entitylivingbase, float f0) {
        if (!this.bP()) {
            EntityPotion entitypotion = new EntityPotion(this.q, this, 32732);

            entitypotion.B -= -20.0F;
            double d0 = entitylivingbase.u + entitylivingbase.x - this.u;
            double d1 = entitylivingbase.v + (double) entitylivingbase.f() - 1.100000023841858D - this.v;
            double d2 = entitylivingbase.w + entitylivingbase.z - this.w;
            float f1 = MathHelper.a(d0 * d0 + d2 * d2);

            if (f1 >= 8.0F && !entitylivingbase.a(Potion.d)) {
                entitypotion.a(32698);
            } else if (entitylivingbase.aJ() >= 8.0F && !entitylivingbase.a(Potion.u)) {
                entitypotion.a(32660);
            } else if (f1 <= 3.0F && !entitylivingbase.a(Potion.t) && this.ab.nextFloat() < 0.25F) {
                entitypotion.a(32696);
            }

            entitypotion.c(d0, d1 + (double) (f1 * 0.2F), d2, 0.75F, 8.0F);
            this.q.d((Entity) entitypotion);
        }
    }
}
