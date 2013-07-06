package net.minecraft.server;

public class ContainerHorseInventorySlotSaddle extends Slot { // CanaryMod: package => public

    final ContainerHorseInventory a;

    ContainerHorseInventorySlotSaddle(ContainerHorseInventory containerhorseinventory, IInventory iinventory, int i0, int i1, int i2) {
        super(iinventory, i0, i1, i2);
        this.a = containerhorseinventory;
    }

    public boolean a(ItemStack itemstack) {
        return super.a(itemstack) && itemstack.d == Item.aC.cv && !this.e();
    }
}
