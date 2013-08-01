package net.minecraft.server;

public class SlotFurnace extends Slot {

    private EntityPlayer a;
    private int b;

    public SlotFurnace(EntityPlayer entityplayer, IInventory iinventory, int i0, int i1, int i2) {
        super(iinventory, i0, i1, i2);
        this.a = entityplayer;
    }

    public boolean a(ItemStack itemstack) {
        return false;
    }

    public ItemStack a(int i0) {
        if (this.e()) {
            this.b += Math.min(i0, this.d().b);
        }

        return super.a(i0);
    }

    public void a(EntityPlayer entityplayer, ItemStack itemstack) {
        this.b(itemstack);
        super.a(entityplayer, itemstack);
    }

    protected void a(ItemStack itemstack, int i0) {
        this.b += i0;
        this.b(itemstack);
    }

    protected void b(ItemStack itemstack) {
        itemstack.a(this.a.q, this.a, this.b);
        if (!this.a.q.I) {
            int i0 = this.b;
            float f0 = FurnaceRecipes.a().c(itemstack.d);
            int i1;

            if (f0 == 0.0F) {
                i0 = 0;
            } else if (f0 < 1.0F) {
                i1 = MathHelper.d((float) i0 * f0);
                if (i1 < MathHelper.f((float) i0 * f0) && (float) Math.random() < (float) i0 * f0 - (float) i1) {
                    ++i1;
                }

                i0 = i1;
            }

            while (i0 > 0) {
                i1 = EntityXPOrb.a(i0);
                i0 -= i1;
                this.a.q.d((Entity) (new EntityXPOrb(this.a.q, this.a.u, this.a.v + 0.5D, this.a.w + 0.5D, i1)));
            }
        }

        this.b = 0;
        if (itemstack.d == Item.q.cv) {
            this.a.a((StatBase) AchievementList.k, 1);
        }

        if (itemstack.d == Item.aX.cv) {
            this.a.a((StatBase) AchievementList.p, 1);
        }
    }
}
