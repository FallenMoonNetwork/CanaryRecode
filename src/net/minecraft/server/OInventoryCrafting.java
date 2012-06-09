package net.minecraft.server;

import net.minecraft.server.OContainer;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OItemStack;

public class OInventoryCrafting implements OIInventory {

    private OItemStack[] a;
    private int b;
    private OContainer c;
    private String name = "container.crafting"; //CanaryMod

    public OInventoryCrafting(OContainer var1, int var2, int var3) {
        super();
        int var4 = var2 * var3;
        this.a = new OItemStack[var4];
        this.c = var1;
        this.b = var2;
    }

    @Override
    public int c() {
        return this.a.length;
    }

    @Override
    public OItemStack g_(int var1) {
        return var1 >= this.c() ? null : this.a[var1];
    }

    public OItemStack b(int var1, int var2) {
        if (var1 >= 0 && var1 < this.b) {
            int var3 = var1 + var2 * this.b;
            return this.g_(var3);
        } else {
            return null;
        }
    }

    @Override
    public String e() {
        return name;
    }

    @Override
    public OItemStack b(int var1) {
        if (this.a[var1] != null) {
            OItemStack var2 = this.a[var1];
            this.a[var1] = null;
            return var2;
        } else {
            return null;
        }
    }

    @Override
    public OItemStack a(int var1, int var2) {
        if (this.a[var1] != null) {
            OItemStack var3;
            if (this.a[var1].a <= var2) {
                var3 = this.a[var1];
                this.a[var1] = null;
                this.c.a(this);
                return var3;
            } else {
                var3 = this.a[var1].a(var2);
                if (this.a[var1].a == 0) {
                    this.a[var1] = null;
                }

                this.c.a(this);
                return var3;
            }
        } else {
            return null;
        }
    }

    @Override
    public void a(int var1, OItemStack var2) {
        this.a[var1] = var2;
        this.c.a(this);
    }

    @Override
    public int a() {
        return 64;
    }

    @Override
    public void G_() {
    }

    @Override
    public boolean a(OEntityPlayer var1) {
        return true;
    }

    @Override
    public void f() {
    }

    @Override
    public void g() {
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
        return this.g_(index);
    }

    @Override
    public void setSlot(int index, OItemStack value) {
        this.a(index, value);
    }

    @Override
    public int getSize() {
        return this.c();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String value) {
        this.name = value;
    }

    @Override
    public void update() {
        G_();
    }
    //CanaryMod end container
}
