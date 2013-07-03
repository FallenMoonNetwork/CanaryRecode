package net.minecraft.server;


import java.util.List;


public class InventoryBasic implements IInventory {

    private String a;
    private int b;
    public ItemStack[] c; // CanaryMod: private -> public
    private List d;
    private boolean e;

    public InventoryBasic(String s0, boolean flag0, int i0) {
        this.a = s0;
        this.e = flag0;
        this.b = i0;
        this.c = new ItemStack[i0];
    }

    public void a(IInvBasic iinvbasic) {
        if (this.d == null) {
            this.d = new ArrayList();
        }

        this.d.add(iinvbasic);
    }

    public void b(IInvBasic iinvbasic) {
        this.d.remove(iinvbasic);
    }

    public ItemStack a(int i0) {
        return this.c[i0];
    }

    public ItemStack a(int i0, int i1) {
        if (this.c[i0] != null) {
            ItemStack itemstack;

            if (this.c[i0].b <= i1) {
                itemstack = this.c[i0];
                this.c[i0] = null;
                this.e();
                return itemstack;
            } else {
                itemstack = this.c[i0].a(i1);
                if (this.c[i0].b == 0) {
                    this.c[i0] = null;
                }

                this.e();
                return itemstack;
            }
        } else {
            return null;
        }
    }

    public ItemStack a_(int i0) {
        if (this.c[i0] != null) {
            ItemStack itemstack = this.c[i0];

            this.c[i0] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    public void a(int i0, ItemStack itemstack) {
        this.c[i0] = itemstack;
        if (itemstack != null && itemstack.b > this.d()) {
            itemstack.b = this.d();
        }

        this.e();
    }

    public int j_() {
        return this.b;
    }

    public String b() {
        return this.a;
    }

    public boolean c() {
        return this.e;
    }

    public void a(String s0) {
        this.e = true;
        this.a = s0;
    }

    public int d() {
        return 64;
    }

    public void e() {
        if (this.d != null) {
            for (int i0 = 0; i0 < this.d.size(); ++i0) {
                ((IInvBasic) this.d.get(i0)).a(this);
            }
        }
    }

    public boolean a(EntityPlayer entityplayer) {
        return true;
    }

    public void k_() {}

    public void g() {}

    public boolean b(int i0, ItemStack itemstack) {
        return true;
    }

    public void setName(String value) {
        this.a = value;
        this.e = false; // Crap, i forgot
    }
}
