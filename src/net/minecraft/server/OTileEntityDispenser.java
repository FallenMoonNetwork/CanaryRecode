package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OItemStack;
import net.minecraft.server.ONBTBase;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.ONBTTagList;
import net.minecraft.server.OTileEntity;

public class OTileEntityDispenser extends OTileEntity implements OIInventory {

    private OItemStack[] a = new OItemStack[9];
    private Random b = new Random();

    public OTileEntityDispenser() {
        super();
    }

    public int c() {
        return 9;
    }

    public OItemStack g_(int var1) {
        return this.a[var1];
    }

    public OItemStack a(int var1, int var2) {
        if (this.a[var1] != null) {
            OItemStack var3;
            if (this.a[var1].a <= var2) {
                var3 = this.a[var1];
                this.a[var1] = null;
                this.G_();
                return var3;
            } else {
                var3 = this.a[var1].a(var2);
                if (this.a[var1].a == 0) {
                    this.a[var1] = null;
                }

                this.G_();
                return var3;
            }
        } else {
            return null;
        }
    }

    public OItemStack b(int var1) {
        if (this.a[var1] != null) {
            OItemStack var2 = this.a[var1];
            this.a[var1] = null;
            return var2;
        } else {
            return null;
        }
    }

    public OItemStack p_() {
        int var1 = -1;
        int var2 = 1;

        for (int var3 = 0; var3 < this.a.length; ++var3) {
            if (this.a[var3] != null && this.b.nextInt(var2++) == 0) {
                var1 = var3;
            }
        }

        if (var1 >= 0) {
            return this.a(var1, 1);
        } else {
            return null;
        }
    }

    public void a(int var1, OItemStack var2) {
        this.a[var1] = var2;
        if (var2 != null && var2.a > this.a()) {
            var2.a = this.a();
        }

        this.G_();
    }

    public String e() {
        return "container.dispenser";
    }

    public void a(ONBTTagCompound var1) {
        super.a(var1);
        ONBTTagList var2 = var1.n("Items");
        this.a = new OItemStack[this.c()];

        for (int var3 = 0; var3 < var2.d(); ++var3) {
            ONBTTagCompound var4 = (ONBTTagCompound) var2.a(var3);
            int var5 = var4.d("Slot") & 255;
            if (var5 >= 0 && var5 < this.a.length) {
                this.a[var5] = OItemStack.a(var4);
            }
        }

    }

    public void b(ONBTTagCompound var1) {
        super.b(var1);
        ONBTTagList var2 = new ONBTTagList();

        for (int var3 = 0; var3 < this.a.length; ++var3) {
            if (this.a[var3] != null) {
                ONBTTagCompound var4 = new ONBTTagCompound();
                var4.a("Slot", (byte) var3);
                this.a[var3].b(var4);
                var2.a(var4);
            }
        }

        var1.a("Items", var2);
    }

    public int a() {
        return 64;
    }

    public boolean a(OEntityPlayer var1) {
        return this.k.b(this.l, this.m, this.n) != this ? false : var1.e(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D) <= 64.0D;
    }

    public void f() {
    }

    public void g() {
    }
}
