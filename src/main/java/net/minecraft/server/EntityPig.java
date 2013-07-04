package net.minecraft.server;

import net.canarymod.api.entity.living.animal.CanaryPig;

public class EntityPig extends EntityAnimal {

    private final EntityAIControlledByPlayer bp;

    public EntityPig(World world) {
        super(world);
        this.a(0.9F, 0.9F);
        this.k().a(true);
        this.c.a(0, new EntityAISwimming(this));
        this.c.a(1, new EntityAIPanic(this, 1.25D));
        this.c.a(2, this.bp = new EntityAIControlledByPlayer(this, 0.3F));
        this.c.a(3, new EntityAIMate(this, 1.0D));
        this.c.a(4, new EntityAITempt(this, 1.2D, Item.bT.cv, false));
        this.c.a(4, new EntityAITempt(this, 1.2D, Item.bM.cv, false));
        this.c.a(5, new EntityAIFollowParent(this, 1.1D));
        this.c.a(6, new EntityAIWander(this, 1.0D));
        this.c.a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.c.a(8, new EntityAILookIdle(this));
        this.entity = new CanaryPig(this); // CanaryMod: Wrap Entity
    }

    public boolean bb() {
        return true;
    }

    protected void ax() {
        super.ax();
        this.a(SharedMonsterAttributes.a).a(10.0D);
        this.a(SharedMonsterAttributes.d).a(0.25D);
    }

    protected void be() {
        super.be();
    }

    public boolean bu() {
        ItemStack itemstack = ((EntityPlayer) this.n).aV();

        return itemstack != null && itemstack.d == Item.bT.cv;
    }

    protected void a() {
        super.a();
        this.ah.a(16, Byte.valueOf((byte) 0));
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("Saddle", this.bP());
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.j(nbttagcompound.n("Saddle"));
    }

    protected String r() {
        return "mob.pig.say";
    }

    protected String aK() {
        return "mob.pig.say";
    }

    protected String aL() {
        return "mob.pig.death";
    }

    protected void a(int i0, int i1, int i2, int i3) {
        this.a("mob.pig.step", 0.15F, 1.0F);
    }

    public boolean a(EntityPlayer entityplayer) {
        if (super.a(entityplayer)) {
            return true;
        } else if (this.bP() && !this.q.I && (this.n == null || this.n == entityplayer)) {
            entityplayer.a((Entity) this);
            return true;
        } else {
            return false;
        }
    }

    protected int s() {
        return this.ad() ? Item.at.cv : Item.as.cv;
    }

    protected void b(boolean flag0, int i0) {
        int i1 = this.ab.nextInt(3) + 1 + this.ab.nextInt(1 + i0);

        for (int i2 = 0; i2 < i1; ++i2) {
            if (this.ad()) {
                this.b(Item.at.cv, 1);
            } else {
                this.b(Item.as.cv, 1);
            }
        }

        if (this.bP()) {
            this.b(Item.aC.cv, 1);
        }
    }

    public boolean bP() {
        return (this.ah.a(16) & 1) != 0;
    }

    public void j(boolean flag0) {
        if (flag0) {
            this.ah.b(16, Byte.valueOf((byte) 1));
        } else {
            this.ah.b(16, Byte.valueOf((byte) 0));
        }
    }

    public void a(EntityLightningBolt entitylightningbolt) {
        if (!this.q.I) {
            EntityPigZombie entitypigzombie = new EntityPigZombie(this.q);

            entitypigzombie.b(this.u, this.v, this.w, this.A, this.B);
            this.q.d((Entity) entitypigzombie);
            this.w();
        }
    }

    protected void b(float f0) {
        super.b(f0);
        if (f0 > 5.0F && this.n instanceof EntityPlayer) {
            ((EntityPlayer) this.n).a((StatBase) AchievementList.u);
        }
    }

    public EntityPig b(EntityAgeable entityageable) {
        return new EntityPig(this.q);
    }

    public boolean c(ItemStack itemstack) {
        return itemstack != null && itemstack.d == Item.bM.cv;
    }

    public EntityAIControlledByPlayer bQ() {
        return this.bp;
    }

    public EntityAgeable a(EntityAgeable entityageable) {
        return this.b(entityageable);
    }
}
