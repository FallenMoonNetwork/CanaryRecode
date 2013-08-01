package net.minecraft.server;

public class SlotEnchantment extends Slot { // CanaryMod: package => public

    final ContainerEnchantment a;

    SlotEnchantment(ContainerEnchantment containerenchantment, IInventory iinventory, int i0, int i1, int i2) {
        super(iinventory, i0, i1, i2);
        this.a = containerenchantment;
    }

    public boolean a(ItemStack itemstack) {
        return true;
    }
}
