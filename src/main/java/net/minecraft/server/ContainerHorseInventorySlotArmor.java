package net.minecraft.server;

public class ContainerHorseInventorySlotArmor extends Slot { // CanaryMod: package => public

    final EntityHorse a;

    final ContainerHorseInventory b;

    ContainerHorseInventorySlotArmor(ContainerHorseInventory containerhorseinventory, IInventory iinventory, int i0, int i1, int i2, EntityHorse entityhorse) {
        super(iinventory, i0, i1, i2);
        this.b = containerhorseinventory;
        this.a = entityhorse;
    }

    public boolean a(ItemStack itemstack) {
        return super.a(itemstack) && this.a.cv() && EntityHorse.v(itemstack.d);
    }
}
