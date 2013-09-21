package net.minecraft.server;

import net.canarymod.api.entity.living.animal.CanarySheep;

import java.util.Random;

public class EntitySheep extends EntityAnimal {

    private final InventoryCrafting bq = new InventoryCrafting(new ContainerSheep(this), 2, 1);
    public static final float[][] bp = new float[][]{{1.0F, 1.0F, 1.0F}, {0.85F, 0.5F, 0.2F}, {0.7F, 0.3F, 0.85F}, {0.4F, 0.6F, 0.85F}, {0.9F, 0.9F, 0.2F}, {0.5F, 0.8F, 0.1F}, {0.95F, 0.5F, 0.65F}, {0.3F, 0.3F, 0.3F}, {0.6F, 0.6F, 0.6F}, {0.3F, 0.5F, 0.6F}, {0.5F, 0.25F, 0.7F}, {0.2F, 0.3F, 0.7F}, {0.4F, 0.3F, 0.2F}, {0.4F, 0.5F, 0.2F}, {0.6F, 0.2F, 0.2F}, {0.1F, 0.1F, 0.1F}};
    private int br;
    private EntityAIEatGrass bs = new EntityAIEatGrass(this);

    public EntitySheep(World world) {
        super(world);
        this.a(0.9F, 1.3F);
        this.k().a(true);
        this.c.a(0, new EntityAISwimming(this));
        this.c.a(1, new EntityAIPanic(this, 1.25D));
        this.c.a(2, new EntityAIMate(this, 1.0D));
        this.c.a(3, new EntityAITempt(this, 1.1D, Item.V.cv, false));
        this.c.a(4, new EntityAIFollowParent(this, 1.1D));
        this.c.a(5, this.bs);
        this.c.a(6, new EntityAIWander(this, 1.0D));
        this.c.a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.c.a(8, new EntityAILookIdle(this));
        this.bq.a(0, new ItemStack(Item.aY, 1, 0));
        this.bq.a(1, new ItemStack(Item.aY, 1, 0));
        this.entity = new CanarySheep(this); // CanaryMod: Wrap Entity
    }

    protected boolean bf() {
        return true;
    }

    protected void bi() {
        this.br = this.bs.f();
        super.bi();
    }

    public void c() {
        if (this.q.I) {
            this.br = Math.max(0, this.br - 1);
        }

        super.c();
    }

    protected void az() {
        super.az();
        this.a(SharedMonsterAttributes.a).a(8.0D);
        this.a(SharedMonsterAttributes.d).a(0.23000000417232513D);
    }

    protected void a() {
        super.a();
        this.ah.a(16, new Byte((byte)0));
    }

    protected void b(boolean flag0, int i0) {
        if (!this.bU()) {
            this.a(new ItemStack(Block.ag.cF, 1, this.bT()), 0.0F);
        }
    }

    protected int s() {
        return Block.ag.cF;
    }

    public boolean a(EntityPlayer entityplayer) {
        ItemStack itemstack = entityplayer.bn.h();

        if (itemstack != null && itemstack.d == Item.bg.cv && !this.bU() && !this.g_()) {
            if (!this.q.I) {
                this.i(true);
                int i0 = 1 + this.ab.nextInt(3);

                for (int i1 = 0; i1 < i0; ++i1) {
                    EntityItem entityitem = this.a(new ItemStack(Block.ag.cF, 1, this.bT()), 1.0F);

                    entityitem.y += (double)(this.ab.nextFloat() * 0.05F);
                    entityitem.x += (double)((this.ab.nextFloat() - this.ab.nextFloat()) * 0.1F);
                    entityitem.z += (double)((this.ab.nextFloat() - this.ab.nextFloat()) * 0.1F);
                }
            }

            itemstack.a(1, (EntityLivingBase)entityplayer);
            this.a("mob.sheep.shear", 1.0F, 1.0F);
        }

        return super.a(entityplayer);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("Sheared", this.bU());
        nbttagcompound.a("Color", (byte)this.bT());
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.i(nbttagcompound.n("Sheared"));
        this.p(nbttagcompound.c("Color"));
    }

    protected String r() {
        return "mob.sheep.say";
    }

    protected String aO() {
        return "mob.sheep.say";
    }

    protected String aP() {
        return "mob.sheep.say";
    }

    protected void a(int i0, int i1, int i2, int i3) {
        this.a("mob.sheep.step", 0.15F, 1.0F);
    }

    public int bT() {
        return this.ah.a(16) & 15;
    }

    public void p(int i0) {
        byte b0 = this.ah.a(16);

        this.ah.b(16, Byte.valueOf((byte)(b0 & 240 | i0 & 15)));
    }

    public boolean bU() {
        return (this.ah.a(16) & 16) != 0;
    }

    public void i(boolean flag0) {
        byte b0 = this.ah.a(16);

        if (flag0) {
            this.ah.b(16, Byte.valueOf((byte)(b0 | 16)));
        }
        else {
            this.ah.b(16, Byte.valueOf((byte)(b0 & -17)));
        }
    }

    public static int a(Random random) {
        int i0 = random.nextInt(100);

        return i0 < 5 ? 15 : (i0 < 10 ? 7 : (i0 < 15 ? 8 : (i0 < 18 ? 12 : (random.nextInt(500) == 0 ? 6 : 0))));
    }

    public EntitySheep b(EntityAgeable entityageable) {
        EntitySheep entitysheep = (EntitySheep)entityageable;
        EntitySheep entitysheep1 = new EntitySheep(this.q);
        int i0 = this.a(this, entitysheep);

        entitysheep1.p(15 - i0);
        return entitysheep1;
    }

    public void n() {
        this.i(false);
        if (this.g_()) {
            this.a(60);
        }
    }

    public EntityLivingData a(EntityLivingData entitylivingdata) {
        entitylivingdata = super.a(entitylivingdata);
        this.p(a(this.q.s));
        return entitylivingdata;
    }

    private int a(EntityAnimal entityanimal, EntityAnimal entityanimal1) {
        int i0 = this.b(entityanimal);
        int i1 = this.b(entityanimal1);

        this.bq.a(0).b(i0);
        this.bq.a(1).b(i1);
        ItemStack itemstack = CraftingManager.a().a(this.bq, ((EntitySheep)entityanimal).q);
        int i2;

        if (itemstack != null && itemstack.b().cv == Item.aY.cv) {
            i2 = itemstack.k();
        }
        else {
            i2 = this.q.s.nextBoolean() ? i0 : i1;
        }

        return i2;
    }

    private int b(EntityAnimal entityanimal) {
        return 15 - ((EntitySheep)entityanimal).bT();
    }

    public EntityAgeable a(EntityAgeable entityageable) {
        return this.b(entityageable);
    }
}
