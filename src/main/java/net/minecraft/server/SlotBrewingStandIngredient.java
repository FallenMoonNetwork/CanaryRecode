package net.minecraft.server;

public class SlotBrewingStandIngredient extends Slot { // CanaryMod: package => public

    final ContainerBrewingStand a;

    public SlotBrewingStandIngredient(ContainerBrewingStand containerbrewingstand, IInventory iinventory, int i0, int i1, int i2) {
        super(iinventory, i0, i1, i2);
        this.a = containerbrewingstand;
    }

    public boolean a(ItemStack itemstack) {
        return itemstack != null ? Item.f[itemstack.c].w() : false;
    }

    public int a() {
        return 64;
    }
}
