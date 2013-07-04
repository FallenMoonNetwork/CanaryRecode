package net.minecraft.server;

class ContainerRepairINNER1 extends InventoryBasic {

    final ContainerRepair a;

    ContainerRepairINNER1(ContainerRepair containerrepair, String s0, boolean flag0, int i0) {
        super(s0, flag0, i0);
        this.a = containerrepair;
    }

    public void e() {
        super.e();
        this.a.a((IInventory) this);
    }

    public boolean b(int i0, ItemStack itemstack) {
        return true;
    }
}
