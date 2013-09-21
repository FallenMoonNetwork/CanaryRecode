package net.minecraft.server;

public class SlotBrewingStandPotion extends Slot { // CanaryMod: package => public

    private EntityPlayer a;

    public SlotBrewingStandPotion(EntityPlayer entityplayer, IInventory iinventory, int i0, int i1, int i2) {
        super(iinventory, i0, i1, i2);
        this.a = entityplayer;
    }

    public boolean a(ItemStack itemstack) {
        return b_(itemstack);
    }

    public int a() {
        return 1;
    }

    public void a(EntityPlayer entityplayer, ItemStack itemstack) {
        if (itemstack.d == Item.bu.cv && itemstack.k() > 0) {
            this.a.a((StatBase)AchievementList.A, 1);
        }

        super.a(entityplayer, itemstack);
    }

    public static boolean b_(ItemStack itemstack) {
        return itemstack != null && (itemstack.d == Item.bu.cv || itemstack.d == Item.bv.cv);
    }
}
