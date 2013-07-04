package net.minecraft.server;

import net.canarymod.api.entity.living.animal.CanaryCow;

public class EntityCow extends EntityAnimal {

    public EntityCow(World world) {
        super(world);
        this.a(0.9F, 1.3F);
        this.k().a(true);
        this.c.a(0, new EntityAISwimming(this));
        this.c.a(1, new EntityAIPanic(this, 2.0D));
        this.c.a(2, new EntityAIMate(this, 1.0D));
        this.c.a(3, new EntityAITempt(this, 1.25D, Item.V.cv, false));
        this.c.a(4, new EntityAIFollowParent(this, 1.25D));
        this.c.a(5, new EntityAIWander(this, 1.0D));
        this.c.a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.c.a(7, new EntityAILookIdle(this));
        this.entity = new CanaryCow(this); // CanaryMod: Wrap Entity
    }

    public boolean bb() {
        return true;
    }

    protected void ax() {
        super.ax();
        this.a(SharedMonsterAttributes.a).a(10.0D);
        this.a(SharedMonsterAttributes.d).a(0.20000000298023224D);
    }

    protected String r() {
        return "mob.cow.say";
    }

    protected String aK() {
        return "mob.cow.hurt";
    }

    protected String aL() {
        return "mob.cow.hurt";
    }

    protected void a(int i0, int i1, int i2, int i3) {
        this.a("mob.cow.step", 0.15F, 1.0F);
    }

    protected float aW() {
        return 0.4F;
    }

    protected int s() {
        return Item.aH.cv;
    }

    protected void b(boolean flag0, int i0) {
        int i1 = this.ab.nextInt(3) + this.ab.nextInt(1 + i0);

        int i2;

        for (i2 = 0; i2 < i1; ++i2) {
            this.b(Item.aH.cv, 1);
        }

        i1 = this.ab.nextInt(3) + 1 + this.ab.nextInt(1 + i0);

        for (i2 = 0; i2 < i1; ++i2) {
            if (this.ad()) {
                this.b(Item.bl.cv, 1);
            } else {
                this.b(Item.bk.cv, 1);
            }
        }
    }

    public boolean a(EntityPlayer entityplayer) {
        ItemStack itemstack = entityplayer.bn.h();

        if (itemstack != null && itemstack.d == Item.ay.cv && !entityplayer.bG.d) {
            if (itemstack.b-- == 1) {
                entityplayer.bn.a(entityplayer.bn.c, new ItemStack(Item.aI));
            } else if (!entityplayer.bn.a(new ItemStack(Item.aI))) {
                entityplayer.b(new ItemStack(Item.aI.cv, 1, 0));
            }

            return true;
        } else {
            return super.a(entityplayer);
        }
    }

    public EntityCow b(EntityAgeable entityageable) {
        return new EntityCow(this.q);
    }

    public EntityAgeable a(EntityAgeable entityageable) {
        return this.b(entityageable);
    }
}
