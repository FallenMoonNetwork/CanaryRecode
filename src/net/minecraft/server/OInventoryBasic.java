package net.minecraft.server;

import java.util.Arrays;
import java.util.List;

import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIInvBasic;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OItemStack;

public class OInventoryBasic implements OIInventory {

    private String a;
    private int b;
    private OItemStack[] c;
    private List d;

    public OInventoryBasic(String var1, int var2) {
        super();
        this.a = var1;
        this.b = var2;
        this.c = new OItemStack[var2];
    }

    @Override
    public OItemStack g_(int var1) {
        return this.c[var1];
    }

    @Override
    public OItemStack a(int var1, int var2) {
        if (this.c[var1] != null) {
            OItemStack var3;
            if (this.c[var1].a <= var2) {
                var3 = this.c[var1];
                this.c[var1] = null;
                this.G_();
                return var3;
            } else {
                var3 = this.c[var1].a(var2);
                if (this.c[var1].a == 0) {
                    this.c[var1] = null;
                }

                this.G_();
                return var3;
            }
        } else {
            return null;
        }
    }

    @Override
    public OItemStack b(int var1) {
        if (this.c[var1] != null) {
            OItemStack var2 = this.c[var1];
            this.c[var1] = null;
            return var2;
        } else {
            return null;
        }
    }

    @Override
    public void a(int var1, OItemStack var2) {
        this.c[var1] = var2;
        if (var2 != null && var2.a > this.a()) {
            var2.a = this.a();
        }

        this.G_();
    }

    @Override
    public int c() {
        return this.b;
    }

    @Override
    public String e() {
        return this.a;
    }

    @Override
    public int a() {
        return 64;
    }

    @Override
    public void G_() {
        if (this.d != null) {
            for (int var1 = 0; var1 < this.d.size(); ++var1) {
                ((OIInvBasic) this.d.get(var1)).a(this);
            }
        }

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
        return Arrays.copyOf(this.c, getSize());
    }

    @Override
    public void setContents(OItemStack[] values) {
        this.c = Arrays.copyOf(values, getSize());
    }

    @Override
    public OItemStack getSlot(int index) {
        return this.g_(index);
    }

    @Override
    public void setSlot(int index, OItemStack value) {
        this.a(b, value);
    }

    @Override
    public int getSize() {
        return this.c();
    }

    @Override
    public String getName() {
        return this.a;
    }

    @Override
    public void setName(String value) {
        this.a = value;
    }

    @Override
    public void update() {
        G_();
    }
    //CanaryMod end container
}
