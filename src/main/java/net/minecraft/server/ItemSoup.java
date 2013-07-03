package net.minecraft.server;

public class ItemSoup extends ItemFood {

    public ItemSoup(int i0, int i1) {
        super(i0, i1, false);
        this.d(1);
    }

    public ItemStack b(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        int tempAm = itemstack.b;

        super.b(itemstack, world, entityplayer);
        // CanaryMod: check if EatHook got canceled
        if (itemstack.b != tempAm) {
            return new ItemStack(Item.G);
        }
        //
        return itemstack;
    }
}
