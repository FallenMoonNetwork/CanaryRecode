package net.minecraft.server;

import net.canarymod.api.entity.living.monster.CanaryZombie;

import java.util.Calendar;
import java.util.UUID;

public class EntityZombie extends EntityMob {

    protected static final Attribute bp = (new RangedAttribute("zombie.spawnReinforcements", 0.0D, 0.0D, 1.0D)).a("Spawn Reinforcements Chance");
    private static final UUID bq = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
    private static final AttributeModifier br = new AttributeModifier(bq, "Baby speed boost", 0.5D, 1);
    private int bs;

    public EntityZombie(World world) {
        super(world);
        this.k().b(true);
        this.c.a(0, new EntityAISwimming(this));
        this.c.a(1, new EntityAIBreakDoor(this));
        this.c.a(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.c.a(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
        this.c.a(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.c.a(5, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.c.a(6, new EntityAIWander(this, 1.0D));
        this.c.a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.c.a(7, new EntityAILookIdle(this));
        this.d.a(1, new EntityAIHurtByTarget(this, true));
        this.d.a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.d.a(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
        this.entity = new CanaryZombie(this); // CanaryMod: Wrap Entity
    }

    protected void az() {
        super.az();
        this.a(SharedMonsterAttributes.b).a(40.0D);
        this.a(SharedMonsterAttributes.d).a(0.23000000417232513D);
        this.a(SharedMonsterAttributes.e).a(3.0D);
        this.aX().b(bp).a(this.ab.nextDouble() * 0.10000000149011612D);
    }

    protected void a() {
        super.a();
        this.v().a(12, Byte.valueOf((byte)0));
        this.v().a(13, Byte.valueOf((byte)0));
        this.v().a(14, Byte.valueOf((byte)0));
    }

    public int aQ() {
        int i0 = super.aQ() + 2;

        if (i0 > 20) {
            i0 = 20;
        }

        return i0;
    }

    protected boolean bf() {
        return true;
    }

    public boolean g_() {
        return this.v().a(12) == 1;
    }

    public void a(boolean flag0) {
        this.v().b(12, Byte.valueOf((byte)(flag0 ? 1 : 0)));
        if (this.q != null && !this.q.I) {
            AttributeInstance attributeinstance = this.a(SharedMonsterAttributes.d);

            attributeinstance.b(br);
            if (flag0) {
                attributeinstance.a(br);
            }
        }
    }

    public boolean bT() {
        return this.v().a(13) == 1;
    }

    public void i(boolean flag0) {
        this.v().b(13, Byte.valueOf((byte)(flag0 ? 1 : 0)));
    }

    public void c() {
        if (this.q.v() && !this.q.I && !this.g_()) {
            float f0 = this.d(1.0F);

            if (f0 > 0.5F && this.ab.nextFloat() * 30.0F < (f0 - 0.4F) * 2.0F && this.q.l(MathHelper.c(this.u), MathHelper.c(this.v), MathHelper.c(this.w))) {
                boolean flag0 = true;
                ItemStack itemstack = this.n(4);

                if (itemstack != null) {
                    if (itemstack.g()) {
                        itemstack.b(itemstack.j() + this.ab.nextInt(2));
                        if (itemstack.j() >= itemstack.l()) {
                            this.a(itemstack);
                            this.c(4, (ItemStack)null);
                        }
                    }

                    flag0 = false;
                }

                if (flag0) {
                    this.d(8);
                }
            }
        }

        super.c();
    }

    public boolean a(DamageSource damagesource, float f0) {
        if (!super.a(damagesource, f0)) {
            return false;
        }
        else {
            EntityLivingBase entitylivingbase = this.m();

            if (entitylivingbase == null && this.bN() instanceof EntityLivingBase) {
                entitylivingbase = (EntityLivingBase)this.bN();
            }

            if (entitylivingbase == null && damagesource.i() instanceof EntityLivingBase) {
                entitylivingbase = (EntityLivingBase)damagesource.i();
            }

            if (entitylivingbase != null && this.q.r >= 3 && (double)this.ab.nextFloat() < this.a(bp).e()) {
                int i0 = MathHelper.c(this.u);
                int i1 = MathHelper.c(this.v);
                int i2 = MathHelper.c(this.w);
                EntityZombie entityzombie = new EntityZombie(this.q);

                for (int i3 = 0; i3 < 50; ++i3) {
                    int i4 = i0 + MathHelper.a(this.ab, 7, 40) * MathHelper.a(this.ab, -1, 1);
                    int i5 = i1 + MathHelper.a(this.ab, 7, 40) * MathHelper.a(this.ab, -1, 1);
                    int i6 = i2 + MathHelper.a(this.ab, 7, 40) * MathHelper.a(this.ab, -1, 1);

                    if (this.q.w(i4, i5 - 1, i6) && this.q.n(i4, i5, i6) < 10) {
                        entityzombie.b((double)i4, (double)i5, (double)i6);
                        if (this.q.b(entityzombie.E) && this.q.a((Entity)entityzombie, entityzombie.E).isEmpty() && !this.q.d(entityzombie.E)) {
                            this.q.d((Entity)entityzombie);
                            entityzombie.d(entitylivingbase);
                            entityzombie.a((EntityLivingData)null);
                            this.a(bp).a(new AttributeModifier("Zombie reinforcement caller charge", -0.05000000074505806D, 0));
                            entityzombie.a(bp).a(new AttributeModifier("Zombie reinforcement callee charge", -0.05000000074505806D, 0));
                            break;
                        }
                    }
                }
            }

            return true;
        }
    }

    public void l_() {
        if (!this.q.I && this.bV()) {
            int i0 = this.bX();

            this.bs -= i0;
            if (this.bs <= 0) {
                this.bW();
            }
        }

        super.l_();
    }

    public boolean m(Entity entity) {
        boolean flag0 = super.m(entity);

        if (flag0 && this.aZ() == null && this.af() && this.ab.nextFloat() < (float)this.q.r * 0.3F) {
            entity.d(2 * this.q.r);
        }

        return flag0;
    }

    protected String r() {
        return "mob.zombie.say";
    }

    protected String aO() {
        return "mob.zombie.hurt";
    }

    protected String aP() {
        return "mob.zombie.death";
    }

    protected void a(int i0, int i1, int i2, int i3) {
        this.a("mob.zombie.step", 0.15F, 1.0F);
    }

    protected int s() {
        return Item.bo.cv;
    }

    public EnumCreatureAttribute aY() {
        return EnumCreatureAttribute.b;
    }

    protected void l(int i0) {
        switch (this.ab.nextInt(3)) {
            case 0:
                this.b(Item.q.cv, 1);
                break;

            case 1:
                this.b(Item.bM.cv, 1);
                break;

            case 2:
                this.b(Item.bN.cv, 1);
        }
    }

    protected void bw() {
        super.bw();
        if (this.ab.nextFloat() < (this.q.r == 3 ? 0.05F : 0.01F)) {
            int i0 = this.ab.nextInt(3);

            if (i0 == 0) {
                this.c(0, new ItemStack(Item.s));
            }
            else {
                this.c(0, new ItemStack(Item.h));
            }
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        if (this.g_()) {
            nbttagcompound.a("IsBaby", true);
        }

        if (this.bT()) {
            nbttagcompound.a("IsVillager", true);
        }

        nbttagcompound.a("ConversionTime", this.bV() ? this.bs : -1);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        if (nbttagcompound.n("IsBaby")) {
            this.a(true);
        }

        if (nbttagcompound.n("IsVillager")) {
            this.i(true);
        }

        if (nbttagcompound.b("ConversionTime") && nbttagcompound.e("ConversionTime") > -1) {
            this.a(nbttagcompound.e("ConversionTime"));
        }
    }

    public void a(EntityLivingBase entitylivingbase) {
        super.a(entitylivingbase);
        if (this.q.r >= 2 && entitylivingbase instanceof EntityVillager) {
            if (this.q.r == 2 && this.ab.nextBoolean()) {
                return;
            }

            EntityZombie entityzombie = new EntityZombie(this.q);

            entityzombie.j(entitylivingbase);
            this.q.e((Entity)entitylivingbase);
            entityzombie.a((EntityLivingData)null);
            entityzombie.i(true);
            if (entitylivingbase.g_()) {
                entityzombie.a(true);
            }

            this.q.d((Entity)entityzombie);
            this.q.a((EntityPlayer)null, 1016, (int)this.u, (int)this.v, (int)this.w, 0);
        }
    }

    public EntityLivingData a(EntityLivingData entitylivingdata) {
        Object object = super.a(entitylivingdata);
        float f0 = this.q.b(this.u, this.v, this.w);

        this.h(this.ab.nextFloat() < 0.55F * f0);
        if (object == null) {
            object = new EntityZombieGroupData(this, this.q.s.nextFloat() < 0.05F, this.q.s.nextFloat() < 0.05F, (EntityZombieINNER1)null);
        }

        if (object instanceof EntityZombieGroupData) {
            EntityZombieGroupData entityzombiegroupdata = (EntityZombieGroupData)object;

            if (entityzombiegroupdata.b) {
                this.i(true);
            }

            if (entityzombiegroupdata.a) {
                this.a(true);
            }
        }

        this.bw();
        this.bx();
        if (this.n(4) == null) {
            Calendar calendar = this.q.W();

            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.ab.nextFloat() < 0.25F) {
                this.c(4, new ItemStack(this.ab.nextFloat() < 0.1F ? Block.bk : Block.bf));
                this.e[4] = 0.0F;
            }
        }

        this.a(SharedMonsterAttributes.c).a(new AttributeModifier("Random spawn bonus", this.ab.nextDouble() * 0.05000000074505806D, 0));
        this.a(SharedMonsterAttributes.b).a(new AttributeModifier("Random zombie-spawn bonus", this.ab.nextDouble() * 1.5D, 2));
        if (this.ab.nextFloat() < f0 * 0.05F) {
            this.a(bp).a(new AttributeModifier("Leader zombie bonus", this.ab.nextDouble() * 0.25D + 0.5D, 0));
            this.a(SharedMonsterAttributes.a).a(new AttributeModifier("Leader zombie bonus", this.ab.nextDouble() * 3.0D + 1.0D, 2));
        }

        return (EntityLivingData)object;
    }

    public boolean a(EntityPlayer entityplayer) {
        ItemStack itemstack = entityplayer.by();

        if (itemstack != null && itemstack.b() == Item.av && itemstack.k() == 0 && this.bT() && this.a(Potion.t)) {
            if (!entityplayer.bG.d) {
                --itemstack.b;
            }

            if (itemstack.b <= 0) {
                entityplayer.bn.a(entityplayer.bn.c, (ItemStack)null);
            }

            if (!this.q.I) {
                this.a(this.ab.nextInt(2401) + 3600);
            }

            return true;
        }
        else {
            return false;
        }
    }

    protected void a(int i0) {
        this.bs = i0;
        this.v().b(14, Byte.valueOf((byte)1));
        this.k(Potion.t.H);
        this.c(new PotionEffect(Potion.g.H, i0, Math.min(this.q.r - 1, 0)));
        this.q.a((Entity)this, (byte)16);
    }

    protected boolean t() {
        return !this.bV();
    }

    public boolean bV() {
        return this.v().a(14) == 1;
    }

    public void bW() { // CanaryMod: protected => public
        EntityVillager entityvillager = new EntityVillager(this.q);

        entityvillager.j(this);
        entityvillager.a((EntityLivingData)null);
        entityvillager.bX();
        if (this.g_()) {
            entityvillager.c(-24000);
        }

        this.q.e((Entity)this);
        this.q.d((Entity)entityvillager);
        entityvillager.c(new PotionEffect(Potion.k.H, 200, 0));
        this.q.a((EntityPlayer)null, 1017, (int)this.u, (int)this.v, (int)this.w, 0);
    }

    protected int bX() {
        int i0 = 1;

        if (this.ab.nextFloat() < 0.01F) {
            int i1 = 0;

            for (int i2 = (int)this.u - 4; i2 < (int)this.u + 4 && i1 < 14; ++i2) {
                for (int i3 = (int)this.v - 4; i3 < (int)this.v + 4 && i1 < 14; ++i3) {
                    for (int i4 = (int)this.w - 4; i4 < (int)this.w + 4 && i1 < 14; ++i4) {
                        int i5 = this.q.a(i2, i3, i4);

                        if (i5 == Block.bu.cF || i5 == Block.X.cF) {
                            if (this.ab.nextFloat() < 0.3F) {
                                ++i0;
                            }

                            ++i1;
                        }
                    }
                }
            }
        }

        return i0;
    }

    // CanaryMod
    public int getConvertTicks() {
        return this.bs;
    }

    public void stopConversion() {
        this.v().b(12, Byte.valueOf((byte)0));
        this.bs = -1;
    }
    //
}
