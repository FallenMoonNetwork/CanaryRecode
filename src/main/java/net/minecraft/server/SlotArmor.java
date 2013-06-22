package net.minecraft.server;

public class SlotArmor extends Slot { // CanaryMod: package => public

    final int a;

    final ContainerPlayer b;

    SlotArmor(ContainerPlayer containerplayer, IInventory iinventory, int i0, int i1, int i2, int i3) {
        super(iinventory, i0, i1, i2);
        this.b = containerplayer;
        this.a = i3;
    }

    public int a() {
        return 1;
    }

    public boolean a(ItemStack itemstack) {
        return itemstack == null ? false : (itemstack.b() instanceof ItemArmor ? ((ItemArmor) itemstack.b()).b == this.a : (itemstack.b().cp != Block.be.cz && itemstack.b().cp != Item.bR.cp ? false : this.a == 0));
    }
}
