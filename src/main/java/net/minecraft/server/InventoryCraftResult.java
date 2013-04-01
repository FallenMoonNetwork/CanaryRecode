package net.minecraft.server;


public class InventoryCraftResult implements IInventory {

    public ItemStack[] a = new ItemStack[1]; // CanaryMod: private => public
    private String name = "Result"; // CanaryMod: changeable name

    public InventoryCraftResult() {}

    public int j_() {
        return 1;
    }

    public ItemStack a(int i0) {
        return this.a[0];
    }

    public String b() {
        return name; // CanaryMod: return name
    }

    public boolean c() {
        return false;
    }

    public ItemStack a(int i0, int i1) {
        if (this.a[0] != null) {
            ItemStack itemstack = this.a[0];

            this.a[0] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    public ItemStack b(int i0) {
        if (this.a[0] != null) {
            ItemStack itemstack = this.a[0];

            this.a[0] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    public void a(int i0, ItemStack itemstack) {
        this.a[0] = itemstack;
    }

    public int d() {
        return 64;
    }

    public void k_() {}

    public boolean a(EntityPlayer entityplayer) {
        return true;
    }

    public void f() {}

    public void g() {}

    public boolean b(int i0, ItemStack itemstack) {
        return true;
    }
}
