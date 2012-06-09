package net.minecraft.server;

import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OItemStack;

public class OInventoryLargeChest implements OIInventory {

    private String a;
    private OIInventory b;
    private OIInventory c;

    public OInventoryLargeChest(String var1, OIInventory var2, OIInventory var3) {
        super();
        this.a = var1;
        if (var2 == null) {
            var2 = var3;
        }

        if (var3 == null) {
            var3 = var2;
        }

        this.b = var2;
        this.c = var3;
    }

    @Override
    public int c() {
        return this.b.c() + this.c.c();
    }

    @Override
    public String e() {
        return this.a;
    }

    @Override
    public OItemStack g_(int var1) {
        return var1 >= this.b.c() ? this.c.g_(var1 - this.b.c()) : this.b.g_(var1);
    }

    @Override
    public OItemStack a(int var1, int var2) {
        return var1 >= this.b.c() ? this.c.a(var1 - this.b.c(), var2) : this.b.a(var1, var2);
    }

    @Override
    public OItemStack b(int var1) {
        return var1 >= this.b.c() ? this.c.b(var1 - this.b.c()) : this.b.b(var1);
    }

    @Override
    public void a(int var1, OItemStack var2) {
        if (var1 >= this.b.c()) {
            this.c.a(var1 - this.b.c(), var2);
        } else {
            this.b.a(var1, var2);
        }

    }

    @Override
    public int a() {
        return this.b.a();
    }

    @Override
    public void G_() {
        this.b.G_();
        this.c.G_();
    }

    @Override
    public boolean a(OEntityPlayer var1) {
        return this.b.a(var1) && this.c.a(var1);
    }

    @Override
    public void f() {
        this.b.f();
        this.c.f();
    }

    @Override
    public void g() {
        this.b.g();
        this.c.g();
    }

    //CanaryMod start container
    @Override
    public OItemStack[] getContents() {
        int size = getSize();
        OItemStack[] result = new OItemStack[size];

        for (int i = 0; i < size; i++) {
            result[i] = getSlot(i);
        }
        return result;
    }

    @Override
    public void setContents(OItemStack[] values) {
        int size = getSize();

        for (int i = 0; i < size; i++) {
            setSlot(i, values[i]);
        }
    }

    @Override
    public OItemStack getSlot(int index) {
        return g_(index);
    }

    @Override
    public void setSlot(int index, OItemStack value) {
        a(index, value);
    }

    @Override
    public int getSize() {
        return c();
    }

    @Override
    public String getName() {
        return a;
    }

    @Override
    public void setName(String value) {
        a = value;
    }

    @Override
    public void update() {
        G_();
    }
    //CanaryMod end container
}
