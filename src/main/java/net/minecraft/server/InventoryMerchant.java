package net.minecraft.server;

public class InventoryMerchant implements IInventory {

    private final IMerchant a;
    public ItemStack[] b = new ItemStack[3]; // CanaryMod: private -> public
    private final EntityPlayer c;
    private MerchantRecipe d;
    private int e;
    private String name = "mob.villager"; // CanaryMod: custom inventory name

    public InventoryMerchant(EntityPlayer entityplayer, IMerchant imerchant) {
        this.c = entityplayer;
        this.a = imerchant;
    }

    public int j_() {
        return this.b.length;
    }

    public ItemStack a(int i0) {
        return this.b[i0];
    }

    public ItemStack a(int i0, int i1) {
        if (this.b[i0] != null) {
            ItemStack itemstack;

            if (i0 == 2) {
                itemstack = this.b[i0];
                this.b[i0] = null;
                return itemstack;
            }
            else if (this.b[i0].b <= i1) {
                itemstack = this.b[i0];
                this.b[i0] = null;
                if (this.d(i0)) {
                    this.h();
                }

                return itemstack;
            }
            else {
                itemstack = this.b[i0].a(i1);
                if (this.b[i0].b == 0) {
                    this.b[i0] = null;
                }

                if (this.d(i0)) {
                    this.h();
                }

                return itemstack;
            }
        }
        else {
            return null;
        }
    }

    private boolean d(int i0) {
        return i0 == 0 || i0 == 1;
    }

    public ItemStack a_(int i0) {
        if (this.b[i0] != null) {
            ItemStack itemstack = this.b[i0];

            this.b[i0] = null;
            return itemstack;
        }
        else {
            return null;
        }
    }

    public void a(int i0, ItemStack itemstack) {
        this.b[i0] = itemstack;
        if (itemstack != null && itemstack.b > this.d()) {
            itemstack.b = this.d();
        }

        if (this.d(i0)) {
            this.h();
        }
    }

    public String b() {
        return name; // CanaryMod: return name
    }

    public boolean c() {
        return false;
    }

    public int d() {
        return 64;
    }

    public boolean a(EntityPlayer entityplayer) {
        return this.a.m_() == entityplayer;
    }

    public void k_() {
    }

    public void g() {
    }

    public boolean b(int i0, ItemStack itemstack) {
        return true;
    }

    public void e() {
        this.h();
    }

    public void h() {
        this.d = null;
        ItemStack itemstack = this.b[0];
        ItemStack itemstack1 = this.b[1];

        if (itemstack == null) {
            itemstack = itemstack1;
            itemstack1 = null;
        }

        if (itemstack == null) {
            this.a(2, (ItemStack)null);
        }
        else {
            MerchantRecipeList merchantrecipelist = this.a.b(this.c);

            if (merchantrecipelist != null) {
                MerchantRecipe merchantrecipe = merchantrecipelist.a(itemstack, itemstack1, this.e);

                if (merchantrecipe != null && !merchantrecipe.g()) {
                    this.d = merchantrecipe;
                    this.a(2, merchantrecipe.d().m());
                }
                else if (itemstack1 != null) {
                    merchantrecipe = merchantrecipelist.a(itemstack1, itemstack, this.e);
                    if (merchantrecipe != null && !merchantrecipe.g()) {
                        this.d = merchantrecipe;
                        this.a(2, merchantrecipe.d().m());
                    }
                    else {
                        this.a(2, (ItemStack)null);
                    }
                }
                else {
                    this.a(2, (ItemStack)null);
                }
            }
        }

        this.a.a_(this.a(2));
    }

    public MerchantRecipe i() {
        return this.d;
    }

    public void c(int i0) {
        this.e = i0;
        this.h();
    }
}
