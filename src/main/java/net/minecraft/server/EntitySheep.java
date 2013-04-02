package net.minecraft.server;

import java.util.Random;
import net.canarymod.api.entity.living.animal.CanarySheep;

public class EntitySheep extends EntityAnimal {

    private final InventoryCrafting e = new InventoryCrafting(new ContainerSheep(this), 2, 1);
    public static final float[][] d = new float[][] { { 1.0F, 1.0F, 1.0F}, { 0.85F, 0.5F, 0.2F}, { 0.7F, 0.3F, 0.85F}, { 0.4F, 0.6F, 0.85F}, { 0.9F, 0.9F, 0.2F}, { 0.5F, 0.8F, 0.1F}, { 0.95F, 0.5F, 0.65F}, { 0.3F, 0.3F, 0.3F}, { 0.6F, 0.6F, 0.6F}, { 0.3F, 0.5F, 0.6F}, { 0.5F, 0.25F, 0.7F}, { 0.2F, 0.3F, 0.7F}, { 0.4F, 0.3F, 0.2F}, { 0.4F, 0.5F, 0.2F}, { 0.6F, 0.2F, 0.2F}, { 0.1F, 0.1F, 0.1F}};
    private int f;
    private EntityAIEatGrass g = new EntityAIEatGrass(this);

    public EntitySheep(World world) {
        super(world);
        this.aH = "/mob/sheep.png";
        this.a(0.9F, 1.3F);
        float f0 = 0.23F;

        this.aC().a(true);
        this.bo.a(0, new EntityAISwimming(this));
        this.bo.a(1, new EntityAIPanic(this, 0.38F));
        this.bo.a(2, new EntityAIMate(this, f0));
        this.bo.a(3, new EntityAITempt(this, 0.25F, Item.U.cp, false));
        this.bo.a(4, new EntityAIFollowParent(this, 0.25F));
        this.bo.a(5, this.g);
        this.bo.a(6, new EntityAIWander(this, f0));
        this.bo.a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.bo.a(8, new EntityAILookIdle(this));
        this.e.a(0, new ItemStack(Item.aX, 1, 0));
        this.e.a(1, new ItemStack(Item.aX, 1, 0));
        this.entity = new CanarySheep(this); // CanaryMod: Wrap Entity
    }

    protected boolean bh() {
        return true;
    }

    protected void bo() {
        this.f = this.g.f();
        super.bo();
    }

    public void c() {
        if (this.q.I) {
            this.f = Math.max(0, this.f - 1);
        }

        super.c();
    }

    public int aW() {
        return maxHealth == 0 ? 8 : maxHealth; // CanaryMod: custom Max Health
    }

    protected void a() {
        super.a();
        this.ah.a(16, new Byte((byte) 0));
    }

    protected void a(boolean flag0, int i0) {
        if (!this.n()) {
            this.a(new ItemStack(Block.af.cz, 1, this.m()), 0.0F);
        }
    }

    protected int be() {
        return Block.af.cz;
    }

    public boolean a_(EntityPlayer entityplayer) {
        ItemStack itemstack = entityplayer.bK.h();

        if (itemstack != null && itemstack.c == Item.bf.cp && !this.n() && !this.h_()) {
            if (!this.q.I) {
                this.i(true);
                int i0 = 1 + this.ab.nextInt(3);

                for (int i1 = 0; i1 < i0; ++i1) {
                    EntityItem entityitem = this.a(new ItemStack(Block.af.cz, 1, this.m()), 1.0F);

                    entityitem.y += (double) (this.ab.nextFloat() * 0.05F);
                    entityitem.x += (double) ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.1F);
                    entityitem.z += (double) ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.1F);
                }
            }

            itemstack.a(1, (EntityLiving) entityplayer);
            this.a("mob.sheep.shear", 1.0F, 1.0F);
        }

        return super.a_(entityplayer);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("Sheared", this.n());
        nbttagcompound.a("Color", (byte) this.m());
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.i(nbttagcompound.n("Sheared"));
        this.s(nbttagcompound.c("Color"));
    }

    protected String bb() {
        return "mob.sheep.say";
    }

    protected String bc() {
        return "mob.sheep.say";
    }

    protected String bd() {
        return "mob.sheep.say";
    }

    protected void a(int i0, int i1, int i2, int i3) {
        this.a("mob.sheep.step", 0.15F, 1.0F);
    }

    public int m() {
        return this.ah.a(16) & 15;
    }

    public void s(int i0) {
        byte b0 = this.ah.a(16);

        this.ah.b(16, Byte.valueOf((byte) (b0 & 240 | i0 & 15)));
    }

    public boolean n() {
        return (this.ah.a(16) & 16) != 0;
    }

    public void i(boolean flag0) {
        byte b0 = this.ah.a(16);

        if (flag0) {
            this.ah.b(16, Byte.valueOf((byte) (b0 | 16)));
        } else {
            this.ah.b(16, Byte.valueOf((byte) (b0 & -17)));
        }
    }

    public static int a(Random random) {
        int i0 = random.nextInt(100);

        return i0 < 5 ? 15 : (i0 < 10 ? 7 : (i0 < 15 ? 8 : (i0 < 18 ? 12 : (random.nextInt(500) == 0 ? 6 : 0))));
    }

    public EntitySheep b(EntityAgeable entityageable) {
        EntitySheep entitysheep = (EntitySheep) entityageable;
        EntitySheep entitysheep1 = new EntitySheep(this.q);
        int i0 = this.a(this, entitysheep);

        entitysheep1.s(15 - i0);
        return entitysheep1;
    }

    public void aK() {
        this.i(false);
        if (this.h_()) {
            int i0 = this.b() + 1200;

            if (i0 > 0) {
                i0 = 0;
            }

            this.a(i0);
        }
    }

    public void bJ() {
        this.s(a(this.q.s));
    }

    private int a(EntityAnimal entityanimal, EntityAnimal entityanimal1) {
        int i0 = this.b(entityanimal);
        int i1 = this.b(entityanimal1);

        this.e.a(0).b(i0);
        this.e.a(1).b(i1);
        ItemStack itemstack = CraftingManager.a().a(this.e, ((EntitySheep) entityanimal).q);
        int i2;

        if (itemstack != null && itemstack.b().cp == Item.aX.cp) {
            i2 = itemstack.k();
        } else {
            i2 = this.q.s.nextBoolean() ? i0 : i1;
        }

        return i2;
    }

    private int b(EntityAnimal entityanimal) {
        return 15 - ((EntitySheep) entityanimal).m();
    }

    public EntityAgeable a(EntityAgeable entityageable) {
        return this.b(entityageable);
    }
}
