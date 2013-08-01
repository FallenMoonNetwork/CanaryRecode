package net.minecraft.server;

public interface IInventory {

    int j_();

    ItemStack a(int i0);

    ItemStack a(int i0, int i1);

    ItemStack a_(int i0);

    void a(int i0, ItemStack itemstack);

    String b();

    boolean c();

    int d();

    void e();

    boolean a(EntityPlayer entityplayer);

    void k_();

    void g();

    boolean b(int i0, ItemStack itemstack);
}
