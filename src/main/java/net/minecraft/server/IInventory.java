package net.minecraft.server;

public interface IInventory extends net.canarymod.api.inventory.Container<ItemStack> { // CanaryMod: adds extends

    int j_();

    ItemStack a(int i0);

    ItemStack a(int i0, int i1);

    ItemStack b(int i0);

    void a(int i0, ItemStack itemstack);

    String b();

    boolean c();

    int d();

    void k_();

    boolean a(EntityPlayer entityplayer);

    void f();

    void g();

    boolean b(int i0, ItemStack itemstack);
}
