package net.minecraft.server;

import java.util.Calendar;
import net.canarymod.api.entity.living.monster.CanarySkeleton;

public class EntitySkeleton extends EntityMob implements IRangedAttackMob {

    private EntityAIArrowAttack d = new EntityAIArrowAttack(this, 0.25F, 20, 60, 15.0F);
    private EntityAIAttackOnCollide e = new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.31F, false);

    public EntitySkeleton(World world) {
        super(world);
        this.aH = "/mob/skeleton.png";
        this.bI = 0.25F;
        this.bo.a(1, new EntityAISwimming(this));
        this.bo.a(2, new EntityAIRestrictSun(this));
        this.bo.a(3, new EntityAIFleeSun(this, this.bI));
        this.bo.a(5, new EntityAIWander(this, this.bI));
        this.bo.a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.bo.a(6, new EntityAILookIdle(this));
        this.bp.a(1, new EntityAIHurtByTarget(this, false));
        this.bp.a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));
        if (world != null && !world.I) {
            this.m();
        }
        this.entity = new CanarySkeleton(this); // CanaryMod: Wrap Entity
    }

    protected void a() {
        super.a();
        this.ah.a(13, new Byte((byte) 0));
    }

    public boolean bh() {
        return true;
    }

    public int aW() {
        return maxHealth == 0 ? 20 : maxHealth; // CanaryMod: custom Max Health
    }

    protected String bb() {
        return "mob.skeleton.say";
    }

    protected String bc() {
        return "mob.skeleton.hurt";
    }

    protected String bd() {
        return "mob.skeleton.death";
    }

    protected void a(int i0, int i1, int i2, int i3) {
        this.a("mob.skeleton.step", 0.15F, 1.0F);
    }

    public boolean m(Entity entity) {
        if (super.m(entity)) {
            if (this.o() == 1 && entity instanceof EntityLiving) {
                ((EntityLiving) entity).d(new PotionEffect(Potion.v.H, 200));
            }

            return true;
        } else {
            return false;
        }
    }

    public int c(Entity entity) {
        if (this.o() == 1) {
            ItemStack itemstack = this.bG();
            int i0 = 4;

            if (itemstack != null) {
                i0 += itemstack.a((Entity) this);
            }

            return i0;
        } else {
            return super.c(entity);
        }
    }

    public EnumCreatureAttribute bF() {
        return EnumCreatureAttribute.b;
    }

    public void c() {
        if (this.q.u() && !this.q.I) {
            float f0 = this.c(1.0F);

            if (f0 > 0.5F && this.ab.nextFloat() * 30.0F < (f0 - 0.4F) * 2.0F && this.q.l(MathHelper.c(this.u), MathHelper.c(this.v), MathHelper.c(this.w))) {
                boolean flag0 = true;
                ItemStack itemstack = this.p(4);

                if (itemstack != null) {
                    if (itemstack.g()) {
                        itemstack.b(itemstack.j() + this.ab.nextInt(2));
                        if (itemstack.j() >= itemstack.l()) {
                            this.a(itemstack);
                            this.c(4, (ItemStack) null);
                        }
                    }

                    flag0 = false;
                }

                if (flag0) {
                    this.d(8);
                }
            }
        }

        if (this.q.I && this.o() == 1) {
            this.a(0.72F, 2.34F);
        }

        super.c();
    }

    public void a(DamageSource damagesource) {
        super.a(damagesource);
        if (damagesource.h() instanceof EntityArrow && damagesource.i() instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer) damagesource.i();
            double d0 = entityplayer.u - this.u;
            double d1 = entityplayer.w - this.w;

            if (d0 * d0 + d1 * d1 >= 2500.0D) {
                entityplayer.a((StatBase) AchievementList.v);
            }
        }
    }

    protected int be() {
        return Item.m.cp;
    }

    protected void a(boolean flag0, int i0) {
        int i1;
        int i2;

        if (this.o() == 1) {
            i1 = this.ab.nextInt(3 + i0) - 1;

            for (i2 = 0; i2 < i1; ++i2) {
                this.b(Item.n.cp, 1);
            }
        } else {
            i1 = this.ab.nextInt(3 + i0);

            for (i2 = 0; i2 < i1; ++i2) {
                this.b(Item.m.cp, 1);
            }
        }

        i1 = this.ab.nextInt(3 + i0);

        for (i2 = 0; i2 < i1; ++i2) {
            this.b(Item.aY.cp, 1);
        }
    }

    protected void l(int i0) {
        if (this.o() == 1) {
            this.a(new ItemStack(Item.bR.cp, 1, 1), 0.0F);
        }
    }

    protected void bH() {
        super.bH();
        this.c(0, new ItemStack(Item.l));
    }

    public void bJ() {
        if (this.q.t instanceof WorldProviderHell && this.aE().nextInt(5) > 0) {
            this.bo.a(4, this.e);
            this.a(1);
            this.c(0, new ItemStack(Item.w));
        } else {
            this.bo.a(4, this.d);
            this.bH();
            this.bI();
        }

        this.h(this.ab.nextFloat() < au[this.q.r]);
        if (this.p(4) == null) {
            Calendar calendar = this.q.U();

            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.ab.nextFloat() < 0.25F) {
                this.c(4, new ItemStack(this.ab.nextFloat() < 0.1F ? Block.bj : Block.be));
                this.bq[4] = 0.0F;
            }
        }
    }

    public void m() {
        this.bo.a((EntityAIBase) this.e);
        this.bo.a((EntityAIBase) this.d);
        ItemStack itemstack = this.bG();

        if (itemstack != null && itemstack.c == Item.l.cp) {
            this.bo.a(4, this.d);
        } else {
            this.bo.a(4, this.e);
        }
    }

    public void a(EntityLiving entityliving, float f0) {
        EntityArrow entityarrow = new EntityArrow(this.q, this, entityliving, 1.6F, (float) (14 - this.q.r * 4));
        int i0 = EnchantmentHelper.a(Enchantment.v.z, this.bG());
        int i1 = EnchantmentHelper.a(Enchantment.w.z, this.bG());

        entityarrow.b((double) (f0 * 2.0F) + this.ab.nextGaussian() * 0.25D + (double) ((float) this.q.r * 0.11F));
        if (i0 > 0) {
            entityarrow.b(entityarrow.c() + (double) i0 * 0.5D + 0.5D);
        }

        if (i1 > 0) {
            entityarrow.a(i1);
        }

        if (EnchantmentHelper.a(Enchantment.x.z, this.bG()) > 0 || this.o() == 1) {
            entityarrow.d(100);
        }

        this.a("random.bow", 1.0F, 1.0F / (this.aE().nextFloat() * 0.4F + 0.8F));
        this.q.d((Entity) entityarrow);
    }

    public int o() {
        return this.ah.a(13);
    }

    public void a(int i0) {
        this.ah.b(13, Byte.valueOf((byte) i0));
        this.ag = i0 == 1;
        if (i0 == 1) {
            this.a(0.72F, 2.34F);
        } else {
            this.a(0.6F, 1.8F);
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        if (nbttagcompound.b("SkeletonType")) {
            byte b0 = nbttagcompound.c("SkeletonType");

            this.a(b0);
        }

        this.m();
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("SkeletonType", (byte) this.o());
    }

    public void c(int i0, ItemStack itemstack) {
        super.c(i0, itemstack);
        if (!this.q.I && i0 == 0) {
            this.m();
        }
    }
}
