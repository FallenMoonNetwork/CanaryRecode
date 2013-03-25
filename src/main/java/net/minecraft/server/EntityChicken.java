package net.minecraft.server;


import net.canarymod.api.entity.living.animal.CanaryChicken;


public class EntityChicken extends EntityAnimal {

    public boolean d = false;
    public float e = 0.0F;
    public float f = 0.0F;
    public float g;
    public float h;
    public float i = 1.0F;
    public int j;

    public EntityChicken(World world) {
        super(world);
        this.aH = "/mob/chicken.png";
        this.a(0.3F, 0.7F);
        this.j = this.ab.nextInt(6000) + 6000;
        float f0 = 0.25F;

        this.bo.a(0, new EntityAISwimming(this));
        this.bo.a(1, new EntityAIPanic(this, 0.38F));
        this.bo.a(2, new EntityAIMate(this, f0));
        this.bo.a(3, new EntityAITempt(this, 0.25F, Item.T.cp, false));
        this.bo.a(4, new EntityAIFollowParent(this, 0.28F));
        this.bo.a(5, new EntityAIWander(this, f0));
        this.bo.a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.bo.a(7, new EntityAILookIdle(this));
        this.entity = new CanaryChicken(this); // CanaryMod: Wrap Entity
    }

    public boolean bh() {
        return true;
    }

    public int aW() {
        return 4;
    }

    public void c() {
        super.c();
        this.h = this.e;
        this.g = this.f;
        this.f = (float) ((double) this.f + (double) (this.F ? -1 : 4) * 0.3D);
        if (this.f < 0.0F) {
            this.f = 0.0F;
        }

        if (this.f > 1.0F) {
            this.f = 1.0F;
        }

        if (!this.F && this.i < 1.0F) {
            this.i = 1.0F;
        }

        this.i = (float) ((double) this.i * 0.9D);
        if (!this.F && this.y < 0.0D) {
            this.y *= 0.6D;
        }

        this.e += this.i * 2.0F;
        if (!this.h_() && !this.q.I && --this.j <= 0) {
            this.a("mob.chicken.plop", 1.0F, (this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F);
            this.b(Item.aQ.cp, 1);
            this.j = this.ab.nextInt(6000) + 6000;
        }
    }

    protected void a(float f0) {}

    protected String bb() {
        return "mob.chicken.say";
    }

    protected String bc() {
        return "mob.chicken.hurt";
    }

    protected String bd() {
        return "mob.chicken.hurt";
    }

    protected void a(int i0, int i1, int i2, int i3) {
        this.a("mob.chicken.step", 0.15F, 1.0F);
    }

    protected int be() {
        return Item.M.cp;
    }

    protected void a(boolean flag0, int i0) {
        int i1 = this.ab.nextInt(3) + this.ab.nextInt(1 + i0);

        for (int i2 = 0; i2 < i1; ++i2) {
            this.b(Item.M.cp, 1);
        }

        if (this.ae()) {
            this.b(Item.bm.cp, 1);
        } else {
            this.b(Item.bl.cp, 1);
        }
    }

    public EntityChicken b(EntityAgeable entityageable) {
        return new EntityChicken(this.q);
    }

    public boolean c(ItemStack itemstack) {
        return itemstack != null && itemstack.b() instanceof ItemSeeds;
    }

    public EntityAgeable a(EntityAgeable entityageable) {
        return this.b(entityageable);
    }
}
