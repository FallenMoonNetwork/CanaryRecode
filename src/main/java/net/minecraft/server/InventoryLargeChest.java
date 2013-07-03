package net.minecraft.server;


public class InventoryLargeChest implements IInventory {

    private String a;
    public IInventory b; // CanaryMod: private -> public
    public IInventory c; // CanaryMod: private -> public

    public InventoryLargeChest(String s0, IInventory iinventory, IInventory iinventory1) {
        this.a = s0;
        if (iinventory == null) {
            iinventory = iinventory1;
        }

        if (iinventory1 == null) {
            iinventory1 = iinventory;
        }

        this.b = iinventory;
        this.c = iinventory1;
    }

    public int j_() {
        return this.b.j_() + this.c.j_();
    }

    public boolean a(IInventory iinventory) {
        return this.b == iinventory || this.c == iinventory;
    }

    public String b() {
        return this.b.c() ? this.b.b() : (this.c.c() ? this.c.b() : this.a);
    }

    public boolean c() {
        return this.b.c() || this.c.c();
    }

    public ItemStack a(int i0) {
        return i0 >= this.b.j_() ? this.c.a(i0 - this.b.j_()) : this.b.a(i0);
    }

    public ItemStack a(int i0, int i1) {
        return i0 >= this.b.j_() ? this.c.a(i0 - this.b.j_(), i1) : this.b.a(i0, i1);
    }

    public ItemStack a_(int i0) {
        return i0 >= this.b.j_() ? this.c.a_(i0 - this.b.j_()) : this.b.a_(i0);
    }

    public void a(int i0, ItemStack itemstack) {
        if (i0 >= this.b.j_()) {
            this.c.a(i0 - this.b.j_(), itemstack);
        } else {
            this.b.a(i0, itemstack);
        }
    }

    public int d() {
        return this.b.d();
    }

    public void e() {
        this.b.e();
        this.c.e();
    }

    public boolean a(EntityPlayer entityplayer) {
        return this.b.a(entityplayer) && this.c.a(entityplayer);
    }

    public void k_() {
        this.b.k_();
        this.c.k_();
    }

    public void g() {
        this.b.g();
        this.c.g();
    }

    public boolean b(int i0, ItemStack itemstack) {
        return true;
    }
}
