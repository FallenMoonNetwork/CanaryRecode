package net.minecraft.server;

import net.minecraft.server.OIInventory;
import net.minecraft.server.OItemStack;

public class OSlot {

    private final int a;
    public final OIInventory b;
    public int c;
    public int d;
    public int e;

    public OSlot(OIInventory var1, int var2, int var3, int var4) {
        super();
        this.b = var1;
        this.a = var2;
        this.d = var3;
        this.e = var4;
    }

    public void a(OItemStack var1, OItemStack var2) {
        if (var1 != null && var2 != null) {
            if (var1.c == var2.c) {
                int var3 = var2.a - var1.a;
                if (var3 > 0) {
                    this.a(var1, var3);
                }

            }
        }
    }

    protected void a(OItemStack var1, int var2) {
    }

    protected void b(OItemStack var1) {
    }

    public void c(OItemStack var1) {
        this.d();
    }

    public boolean a(OItemStack var1) {
        return true;
    }

    /**
     * Get the itemstack of this slot
     * @return
     */
    public OItemStack b() {
        return this.b.g_(this.a);
    }

    public boolean c() {
        return this.b() != null;
    }
    
    public void d(OItemStack var1) {
        this.b.a(this.a, var1);
        this.d();
    }

    public void d() {
        this.b.G_();
    }

    public int a() {
        return this.b.a();
    }

    /**
     * Decrease Stack size in this slot by the given var1
     * @param var1
     * @return
     */
    public OItemStack a(int var1) {
        return this.b.a(this.a, var1);
    }

    public boolean a(OIInventory var1, int var2) {
        return var1 == this.b && var2 == this.a;
    }
}
