package net.minecraft.server;


import net.canarymod.api.entity.living.animal.CanaryChicken;


public class EntityChicken extends EntityAnimal {

    public float bp;
    public float bq;
    public float br;
    public float bs;
    public float bt = 1.0F;
    public int bu;

    public EntityChicken(World world) {
        super(world);
        this.a(0.3F, 0.7F);
        this.bu = this.ab.nextInt(6000) + 6000;
        this.c.a(0, new EntityAISwimming(this));
        this.c.a(1, new EntityAIPanic(this, 1.4D));
        this.c.a(2, new EntityAIMate(this, 1.0D));
        this.c.a(3, new EntityAITempt(this, 1.0D, Item.U.cv, false));
        this.c.a(4, new EntityAIFollowParent(this, 1.1D));
        this.c.a(5, new EntityAIWander(this, 1.0D));
        this.c.a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.c.a(7, new EntityAILookIdle(this));
        this.entity = new CanaryChicken(this); // CanaryMod: Wrap Entity
    }

    public boolean bb() {
        return true;
    }

    protected void ax() {
        super.ax();
        this.a(SharedMonsterAttributes.a).a(4.0D);
        this.a(SharedMonsterAttributes.d).a(0.25D);
    }

    public void c() {
        super.c();
        this.bs = this.bp;
        this.br = this.bq;
        this.bq = (float) ((double) this.bq + (double) (this.F ? -1 : 4) * 0.3D);
        if (this.bq < 0.0F) {
            this.bq = 0.0F;
        }

        if (this.bq > 1.0F) {
            this.bq = 1.0F;
        }

        if (!this.F && this.bt < 1.0F) {
            this.bt = 1.0F;
        }

        this.bt = (float) ((double) this.bt * 0.9D);
        if (!this.F && this.y < 0.0D) {
            this.y *= 0.6D;
        }

        this.bp += this.bt * 2.0F;
        if (!this.g_() && !this.q.I && --this.bu <= 0) {
            this.a("mob.chicken.plop", 1.0F, (this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F);
            this.b(Item.aR.cv, 1);
            this.bu = this.ab.nextInt(6000) + 6000;
        }
    }

    protected void b(float f0) {}

    protected String r() {
        return "mob.chicken.say";
    }

    protected String aK() {
        return "mob.chicken.hurt";
    }

    protected String aL() {
        return "mob.chicken.hurt";
    }

    protected void a(int i0, int i1, int i2, int i3) {
        this.a("mob.chicken.step", 0.15F, 1.0F);
    }

    protected int s() {
        return Item.N.cv;
    }

    protected void b(boolean flag0, int i0) {
        int i1 = this.ab.nextInt(3) + this.ab.nextInt(1 + i0);

        for (int i2 = 0; i2 < i1; ++i2) {
            this.b(Item.N.cv, 1);
        }

        if (this.ad()) {
            this.b(Item.bn.cv, 1);
        } else {
            this.b(Item.bm.cv, 1);
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
