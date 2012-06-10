package net.minecraft.server;

import net.canarymod.api.inventory.Container;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItemStack;

public interface OIInventory extends Container<OItemStack> { //CanaryMod extension added

    int getInventorySize();

    OItemStack getStackFromSlot(int var1);

    OItemStack decreaseItemStackSize(int var1, int var2);

    OItemStack b(int var1);

    void setItemStackToSlot(int var1, OItemStack var2);

    String getInventoryName();

    int getInventoryStackLimit();

    void G_();

    boolean a(OEntityPlayer var1);

    void f();

    void g();
}
