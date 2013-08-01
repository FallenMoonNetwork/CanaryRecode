package net.minecraft.server;

public class SlotEnchantmentTable extends InventoryBasic { // CanaryMod: package => public

    final ContainerEnchantment a;

    SlotEnchantmentTable(ContainerEnchantment containerenchantment, String s0, boolean flag0, int i0) {
        super(s0, flag0, i0);
        this.a = containerenchantment;
    }

    public int d() {
        return 1;
    }

    public void e() {
        super.e();
        this.a.a((IInventory) this);
    }

    public boolean b(int i0, ItemStack itemstack) {
        return true;
    }
}
