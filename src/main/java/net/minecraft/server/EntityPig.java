package net.minecraft.server;

import net.canarymod.api.entity.living.animal.CanaryPig;

public class EntityPig extends EntityAnimal {

    private final EntityAIControlledByPlayer d;

    public EntityPig(World world) {
        super(world);
        this.aH = "/mob/pig.png";
        this.a(0.9F, 0.9F);
        this.aC().a(true);
        float f0 = 0.25F;

        this.bo.a(0, new EntityAISwimming(this));
        this.bo.a(1, new EntityAIPanic(this, 0.38F));
        this.bo.a(2, this.d = new EntityAIControlledByPlayer(this, 0.34F));
        this.bo.a(3, new EntityAIMate(this, f0));
        this.bo.a(4, new EntityAITempt(this, 0.3F, Item.bS.cp, false));
        this.bo.a(4, new EntityAITempt(this, 0.3F, Item.bL.cp, false));
        this.bo.a(5, new EntityAIFollowParent(this, 0.28F));
        this.bo.a(6, new EntityAIWander(this, f0));
        this.bo.a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.bo.a(8, new EntityAILookIdle(this));
        this.entity = new CanaryPig(this); // CanaryMod: Wrap Entity
    }

    public boolean bh() {
        return true;
    }

    public int aW() {
        return 10;
    }

    protected void bo() {
        super.bo();
    }

    public boolean bL() {
        ItemStack itemstack = ((EntityPlayer) this.n).bG();

        return itemstack != null && itemstack.c == Item.bS.cp;
    }

    protected void a() {
        super.a();
        this.ah.a(16, Byte.valueOf((byte) 0));
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("Saddle", this.m());
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.i(nbttagcompound.n("Saddle"));
    }

    protected String bb() {
        return "mob.pig.say";
    }

    protected String bc() {
        return "mob.pig.say";
    }

    protected String bd() {
        return "mob.pig.death";
    }

    protected void a(int i0, int i1, int i2, int i3) {
        this.a("mob.pig.step", 0.15F, 1.0F);
    }

    public boolean a_(EntityPlayer entityplayer) {
        if (super.a_(entityplayer)) {
            return true;
        } else if (this.m() && !this.q.I && (this.n == null || this.n == entityplayer)) {
            entityplayer.a((Entity) this);
            return true;
        } else {
            return false;
        }
    }

    protected int be() {
        return this.ae() ? Item.as.cp : Item.ar.cp;
    }

    protected void a(boolean flag0, int i0) {
        int i1 = this.ab.nextInt(3) + 1 + this.ab.nextInt(1 + i0);

        for (int i2 = 0; i2 < i1; ++i2) {
            if (this.ae()) {
                this.b(Item.as.cp, 1);
            } else {
                this.b(Item.ar.cp, 1);
            }
        }

        if (this.m()) {
            this.b(Item.aB.cp, 1);
        }
    }

    public boolean m() {
        return (this.ah.a(16) & 1) != 0;
    }

    public void i(boolean flag0) {
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

    protected void a(float f0) {
        super.a(f0);
        if (f0 > 5.0F && this.n instanceof EntityPlayer) {
            ((EntityPlayer) this.n).a((StatBase) AchievementList.u);
        }
    }

    public EntityPig b(EntityAgeable entityageable) {
        return new EntityPig(this.q);
    }

    public boolean c(ItemStack itemstack) {
        return itemstack != null && itemstack.c == Item.bL.cp;
    }

    public EntityAIControlledByPlayer n() {
        return this.d;
    }

    public EntityAgeable a(EntityAgeable entityageable) {
        return this.b(entityageable);
    }
}
