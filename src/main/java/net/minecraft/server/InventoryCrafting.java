package net.minecraft.server;


public class InventoryCrafting implements IInventory {

    public ItemStack[] a; // CanaryMod: private -> public
    private int b;
    private Container c;
    private String name = "container.crafting"; // CanaryMod: custom name

    public InventoryCrafting(Container container, int i0, int i1) {
        int i2 = i0 * i1;

        this.a = new ItemStack[i2];
        this.c = container;
        this.b = i0;
    }

    public int j_() {
        return this.a.length;
    }

    public ItemStack a(int i0) {
        return i0 >= this.j_() ? null : this.a[i0];
    }

    public ItemStack b(int i0, int i1) {
        if (i0 >= 0 && i0 < this.b) {
            int i2 = i0 + i1 * this.b;

            return this.a(i2);
        } else {
            return null;
        }
    }

    public String b() {
        return name; // CanaryMod: Return custom name
    }

    public boolean c() {
        return !this.name.equals("container.crafting");
    }

    public ItemStack a_(int i0) {
        if (this.a[i0] != null) {
            ItemStack itemstack = this.a[i0];

            this.a[i0] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    public ItemStack a(int i0, int i1) {
        if (this.a[i0] != null) {
            ItemStack itemstack;

            if (this.a[i0].b <= i1) {
                itemstack = this.a[i0];
                this.a[i0] = null;
                this.c.a((IInventory) this);
                return itemstack;
            } else {
                itemstack = this.a[i0].a(i1);
                if (this.a[i0].b == 0) {
                    this.a[i0] = null;
                }

                this.c.a((IInventory) this);
                return itemstack;
            }
        } else {
            return null;
        }
    }

    public void a(int i0, ItemStack itemstack) {
        this.a[i0] = itemstack;
        this.c.a((IInventory) this);
    }

    public int d() {
        return 64;
    }

    public void e() {}

    public boolean a(EntityPlayer entityplayer) {
        return true;
    }

    public void k_() {}

    public void g() {}

    public boolean b(int i0, ItemStack itemstack) {
        return true;
    }

    // CanaryMod
    public void setName(String value) {
        this.name = value;
    }
}
