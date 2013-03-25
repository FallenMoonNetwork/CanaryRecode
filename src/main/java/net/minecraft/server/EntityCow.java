package net.minecraft.server;


import net.canarymod.api.entity.living.animal.CanaryCow;


public class EntityCow extends EntityAnimal {

    public EntityCow(World world) {
        super(world);
        this.aH = "/mob/cow.png";
        this.a(0.9F, 1.3F);
        this.aC().a(true);
        this.bo.a(0, new EntityAISwimming(this));
        this.bo.a(1, new EntityAIPanic(this, 0.38F));
        this.bo.a(2, new EntityAIMate(this, 0.2F));
        this.bo.a(3, new EntityAITempt(this, 0.25F, Item.U.cp, false));
        this.bo.a(4, new EntityAIFollowParent(this, 0.25F));
        this.bo.a(5, new EntityAIWander(this, 0.2F));
        this.bo.a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.bo.a(7, new EntityAILookIdle(this));
        this.entity = new CanaryCow(this); // CanaryMod: Wrap Entity
    }

    public boolean bh() {
        return true;
    }

    public int aW() {
        return 10;
    }

    protected String bb() {
        return "mob.cow.say";
    }

    protected String bc() {
        return "mob.cow.hurt";
    }

    protected String bd() {
        return "mob.cow.hurt";
    }

    protected void a(int i0, int i1, int i2, int i3) {
        this.a("mob.cow.step", 0.15F, 1.0F);
    }

    protected float ba() {
        return 0.4F;
    }

    protected int be() {
        return Item.aG.cp;
    }

    protected void a(boolean flag0, int i0) {
        int i1 = this.ab.nextInt(3) + this.ab.nextInt(1 + i0);

        int i2;

        for (i2 = 0; i2 < i1; ++i2) {
            this.b(Item.aG.cp, 1);
        }

        i1 = this.ab.nextInt(3) + 1 + this.ab.nextInt(1 + i0);

        for (i2 = 0; i2 < i1; ++i2) {
            if (this.ae()) {
                this.b(Item.bk.cp, 1);
            } else {
                this.b(Item.bj.cp, 1);
            }
        }
    }

    public boolean a_(EntityPlayer entityplayer) {
        ItemStack itemstack = entityplayer.bK.h();

        if (itemstack != null && itemstack.c == Item.ax.cp) {
            if (--itemstack.a <= 0) {
                entityplayer.bK.a(entityplayer.bK.c, new ItemStack(Item.aH));
            } else if (!entityplayer.bK.a(new ItemStack(Item.aH))) {
                entityplayer.c(new ItemStack(Item.aH.cp, 1, 0));
            }

            return true;
        } else {
            return super.a_(entityplayer);
        }
    }

    public EntityCow b(EntityAgeable entityageable) {
        return new EntityCow(this.q);
    }

    public EntityAgeable a(EntityAgeable entityageable) {
        return this.b(entityageable);
    }
}
