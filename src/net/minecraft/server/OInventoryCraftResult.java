package net.minecraft.server;

import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OItemStack;

public class OInventoryCraftResult implements OIInventory {

    private OItemStack[] a = new OItemStack[1];

    public OInventoryCraftResult() {
        super();
    }

    @Override
    public int c() {
        return 1;
    }

    @Override
    public OItemStack g_(int var1) {
        return this.a[var1];
    }

    @Override
    public String e() {
        return "Result";
    }

    @Override
    public OItemStack a(int var1, int var2) {
        if (this.a[var1] != null) {
            OItemStack var3 = this.a[var1];
            this.a[var1] = null;
            return var3;
        } else {
            return null;
        }
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
    public void a(int var1, OItemStack var2) {
        this.a[var1] = var2;
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
}
