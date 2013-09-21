package net.minecraft.server;

public class MerchantRecipe {

    // CanaryMod: private => public
    public ItemStack a;
    public ItemStack b;
    public ItemStack c;
    //
    private int d;
    private int e;

    public MerchantRecipe(NBTTagCompound nbttagcompound) {
        this.a(nbttagcompound);
    }

    public MerchantRecipe(ItemStack itemstack, ItemStack itemstack1, ItemStack itemstack2) {
        this.a = itemstack;
        this.b = itemstack1;
        this.c = itemstack2;
        this.e = 7;
    }

    public MerchantRecipe(ItemStack itemstack, ItemStack itemstack1) {
        this(itemstack, (ItemStack)null, itemstack1);
    }

    public MerchantRecipe(ItemStack itemstack, Item item) {
        this(itemstack, new ItemStack(item));
    }

    public ItemStack a() {
        return this.a;
    }

    public ItemStack b() {
        return this.b;
    }

    public boolean c() {
        return this.b != null;
    }

    public ItemStack d() {
        return this.c;
    }

    public boolean a(MerchantRecipe merchantrecipe) {
        return this.a.d == merchantrecipe.a.d && this.c.d == merchantrecipe.c.d ? this.b == null && merchantrecipe.b == null || this.b != null && merchantrecipe.b != null && this.b.d == merchantrecipe.b.d : false;
    }

    public boolean b(MerchantRecipe merchantrecipe) {
        return this.a(merchantrecipe) && (this.a.b < merchantrecipe.a.b || this.b != null && this.b.b < merchantrecipe.b.b);
    }

    public void f() {
        ++this.d;
    }

    public void a(int i0) {
        this.e += i0;
    }

    public boolean g() {
        return this.d >= this.e;
    }

    public void a(NBTTagCompound nbttagcompound) {
        NBTTagCompound nbttagcompound1 = nbttagcompound.l("buy");

        this.a = ItemStack.a(nbttagcompound1);
        NBTTagCompound nbttagcompound2 = nbttagcompound.l("sell");

        this.c = ItemStack.a(nbttagcompound2);
        if (nbttagcompound.b("buyB")) {
            this.b = ItemStack.a(nbttagcompound.l("buyB"));
        }

        if (nbttagcompound.b("uses")) {
            this.d = nbttagcompound.e("uses");
        }

        if (nbttagcompound.b("maxUses")) {
            this.e = nbttagcompound.e("maxUses");
        }
        else {
            this.e = 7;
        }
    }

    public NBTTagCompound i() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        nbttagcompound.a("buy", this.a.b(new NBTTagCompound("buy")));
        nbttagcompound.a("sell", this.c.b(new NBTTagCompound("sell")));
        if (this.b != null) {
            nbttagcompound.a("buyB", this.b.b(new NBTTagCompound("buyB")));
        }

        nbttagcompound.a("uses", this.d);
        nbttagcompound.a("maxUses", this.e);
        return nbttagcompound;
    }
}
