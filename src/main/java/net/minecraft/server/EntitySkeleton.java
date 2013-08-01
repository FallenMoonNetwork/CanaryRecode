package net.minecraft.server;

import java.util.Calendar;
import net.canarymod.api.entity.living.monster.CanarySkeleton;

public class EntitySkeleton extends EntityMob implements IRangedAttackMob {

    private EntityAIArrowAttack bp = new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F);
    private EntityAIAttackOnCollide bq = new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.2D, false);

    public EntitySkeleton(World world) {
        super(world);
        this.c.a(1, new EntityAISwimming(this));
        this.c.a(2, new EntityAIRestrictSun(this));
        this.c.a(3, new EntityAIFleeSun(this, 1.0D));
        this.c.a(5, new EntityAIWander(this, 1.0D));
        this.c.a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.c.a(6, new EntityAILookIdle(this));
        this.d.a(1, new EntityAIHurtByTarget(this, false));
        this.d.a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        if (world != null && !world.I) {
            this.bT();
        }
        this.entity = new CanarySkeleton(this); // CanaryMod: Wrap Entity
    }

    protected void ay() {
        super.ay();
        this.a(SharedMonsterAttributes.d).a(0.25D);
    }

    protected void a() {
        super.a();
        this.ah.a(13, new Byte((byte) 0));
    }

    public boolean be() {
        return true;
    }

    protected String r() {
        return "mob.skeleton.say";
    }

    protected String aN() {
        return "mob.skeleton.hurt";
    }

    protected String aO() {
        return "mob.skeleton.death";
    }

    protected void a(int i0, int i1, int i2, int i3) {
        this.a("mob.skeleton.step", 0.15F, 1.0F);
    }

    public boolean m(Entity entity) {
        if (super.m(entity)) {
            if (this.bV() == 1 && entity instanceof EntityLivingBase) {
                ((EntityLivingBase) entity).c(new PotionEffect(Potion.v.H, 200));
            }

            return true;
        } else {
            return false;
        }
    }

    public EnumCreatureAttribute aX() {
        return EnumCreatureAttribute.b;
    }

    public void c() {
        if (this.q.v() && !this.q.I) {
            float f0 = this.d(1.0F);

            if (f0 > 0.5F && this.ab.nextFloat() * 30.0F < (f0 - 0.4F) * 2.0F && this.q.l(MathHelper.c(this.u), MathHelper.c(this.v), MathHelper.c(this.w))) {
                boolean flag0 = true;
                ItemStack itemstack = this.n(4);

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

        if (this.q.I && this.bV() == 1) {
            this.a(0.72F, 2.34F);
        }

        super.c();
    }

    public void U() {
        super.U();
        if (this.o instanceof EntityCreature) {
            EntityCreature entitycreature = (EntityCreature) this.o;

            this.aN = entitycreature.aN;
        }
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

    protected int s() {
        return Item.n.cv;
    }

    protected void b(boolean flag0, int i0) {
        int i1;
        int i2;

        if (this.bV() == 1) {
            i1 = this.ab.nextInt(3 + i0) - 1;

            for (i2 = 0; i2 < i1; ++i2) {
                this.b(Item.o.cv, 1);
            }
        } else {
            i1 = this.ab.nextInt(3 + i0);

            for (i2 = 0; i2 < i1; ++i2) {
                this.b(Item.n.cv, 1);
            }
        }

        i1 = this.ab.nextInt(3 + i0);

        for (i2 = 0; i2 < i1; ++i2) {
            this.b(Item.aZ.cv, 1);
        }
    }

    protected void l(int i0) {
        if (this.bV() == 1) {
            this.a(new ItemStack(Item.bS.cv, 1, 1), 0.0F);
        }
    }

    protected void bw() {
        super.bw();
        this.c(0, new ItemStack(Item.m));
    }

    public EntityLivingData a(EntityLivingData entitylivingdata) {
        entitylivingdata = super.a(entitylivingdata);
        if (this.q.t instanceof WorldProviderHell && this.aC().nextInt(5) > 0) {
            this.c.a(4, this.bq);
            this.a(1);
            this.c(0, new ItemStack(Item.x));
            this.a(SharedMonsterAttributes.e).a(4.0D);
        } else {
            this.c.a(4, this.bp);
            this.bw();
            this.bx();
        }

        this.h(this.ab.nextFloat() < 0.55F * this.q.b(this.u, this.v, this.w));
        if (this.n(4) == null) {
            Calendar calendar = this.q.W();

            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.ab.nextFloat() < 0.25F) {
                this.c(4, new ItemStack(this.ab.nextFloat() < 0.1F ? Block.bk : Block.bf));
                this.e[4] = 0.0F;
            }
        }

        return entitylivingdata;
    }

    public void bT() {
        this.c.a((EntityAIBase) this.bq);
        this.c.a((EntityAIBase) this.bp);
        ItemStack itemstack = this.aY();

        if (itemstack != null && itemstack.d == Item.m.cv) {
            this.c.a(4, this.bp);
        } else {
            this.c.a(4, this.bq);
        }
    }

    public void a(EntityLivingBase entitylivingbase, float f0) {
        EntityArrow entityarrow = new EntityArrow(this.q, this, entitylivingbase, 1.6F, (float) (14 - this.q.r * 4));
        int i0 = EnchantmentHelper.a(Enchantment.v.z, this.aY());
        int i1 = EnchantmentHelper.a(Enchantment.w.z, this.aY());

        entityarrow.b((double) (f0 * 2.0F) + this.ab.nextGaussian() * 0.25D + (double) ((float) this.q.r * 0.11F));
        if (i0 > 0) {
            entityarrow.b(entityarrow.c() + (double) i0 * 0.5D + 0.5D);
        }

        if (i1 > 0) {
            entityarrow.a(i1);
        }

        if (EnchantmentHelper.a(Enchantment.x.z, this.aY()) > 0 || this.bV() == 1) {
            entityarrow.d(100);
        }

        this.a("random.bow", 1.0F, 1.0F / (this.aC().nextFloat() * 0.4F + 0.8F));
        this.q.d((Entity) entityarrow);
    }

    public int bV() {
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

        this.bT();
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("SkeletonType", (byte) this.bV());
    }

    public void c(int i0, ItemStack itemstack) {
        super.c(i0, itemstack);
        if (!this.q.I && i0 == 0) {
            this.bT();
        }
    }

    public double W() {
        return super.W() - 0.5D;
    }
}
