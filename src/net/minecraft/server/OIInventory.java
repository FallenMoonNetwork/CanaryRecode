package net.minecraft.server;

import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItemStack;

public interface OIInventory {

    int c();

    OItemStack g_(int var1);

    OItemStack a(int var1, int var2);

    OItemStack b(int var1);

    void a(int var1, OItemStack var2);

    String e();

    int a();

    void G_();

    boolean a(OEntityPlayer var1);

    void f();

    void g();
}
