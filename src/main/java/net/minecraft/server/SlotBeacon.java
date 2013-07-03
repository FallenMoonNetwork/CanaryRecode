package net.minecraft.server;

public class SlotBeacon extends Slot { // CanaryMod: package => public

    final ContainerBeacon a;

    public SlotBeacon(ContainerBeacon containerbeacon, IInventory iinventory, int i0, int i1, int i2) {
        super(iinventory, i0, i1, i2);
        this.a = containerbeacon;
    }

    public boolean a(ItemStack itemstack) {
        return itemstack == null ? false : itemstack.c == Item.bI.cp || itemstack.c == Item.o.cp || itemstack.c == Item.q.cp || itemstack.c == Item.p.cp;
    }

    public int a() {
        return 1;
    }
}
