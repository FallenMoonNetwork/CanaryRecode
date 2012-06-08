package net.minecraft.server;

import java.util.List;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemPotion;
import net.minecraft.server.OItemStack;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.ONBTTagList;
import net.minecraft.server.OPotionHelper;
import net.minecraft.server.OTileEntity;

public class OTileEntityBrewingStand extends OTileEntity implements OIInventory {

    private OItemStack[] a = new OItemStack[4];
    private int b;
    private int c;
    private int d;

    public OTileEntityBrewingStand() {
        super();
    }

    @Override
    public String e() {
        return "container.brewing";
    }

    @Override
    public int c() {
        return this.a.length;
    }

    @Override
    public void q_() {
        if (this.b > 0) {
            --this.b;
            if (this.b == 0) {
                this.p();
                this.G_();
            } else if (!this.o()) {
                this.b = 0;
                this.G_();
            } else if (this.d != this.a[3].c) {
                this.b = 0;
                this.G_();
            }
        } else if (this.o()) {
            this.b = 400;
            this.d = this.a[3].c;
        }

        int var1 = this.n();
        if (var1 != this.c) {
            this.c = var1;
            this.k.c(this.l, this.m, this.n, var1);
        }

        super.q_();
    }

    public int i() {
        return this.b;
    }

    private boolean o() {
        if (this.a[3] != null && this.a[3].a > 0) {
            OItemStack var1 = this.a[3];
            if (!OItem.d[var1.c].n()) {
                return false;
            } else {
                boolean var2 = false;

                for (int var3 = 0; var3 < 3; ++var3) {
                    if (this.a[var3] != null && this.a[var3].c == OItem.br.bP) {
                        int var4 = this.a[var3].h();
                        int var5 = this.b(var4, var1);
                        if (!OItemPotion.c(var4) && OItemPotion.c(var5)) {
                            var2 = true;
                            break;
                        }

                        List var6 = OItem.br.b(var4);
                        List var7 = OItem.br.b(var5);
                        if ((var4 <= 0 || var6 != var7) && (var6 == null || !var6.equals(var7) && var7 != null) && var4 != var5) {
                            var2 = true;
                            break;
                        }
                    }
                }

                return var2;
            }
        } else {
            return false;
        }
    }

    private void p() {
        if (this.o()) {
            OItemStack var1 = this.a[3];

            for (int var2 = 0; var2 < 3; ++var2) {
                if (this.a[var2] != null && this.a[var2].c == OItem.br.bP) {
                    int var3 = this.a[var2].h();
                    int var4 = this.b(var3, var1);
                    List var5 = OItem.br.b(var3);
                    List var6 = OItem.br.b(var4);
                    if ((var3 <= 0 || var5 != var6) && (var5 == null || !var5.equals(var6) && var6 != null)) {
                        if (var3 != var4) {
                            this.a[var2].b(var4);
                        }
                    } else if (!OItemPotion.c(var3) && OItemPotion.c(var4)) {
                        this.a[var2].b(var4);
                    }
                }
            }

            if (OItem.d[var1.c].k()) {
                this.a[3] = new OItemStack(OItem.d[var1.c].j());
            } else {
                --this.a[3].a;
                if (this.a[3].a <= 0) {
                    this.a[3] = null;
                }
            }

        }
    }

    private int b(int var1, OItemStack var2) {
        return var2 == null ? var1 : (OItem.d[var2.c].n() ? OPotionHelper.a(var1, OItem.d[var2.c].m()) : var1);
    }

    @Override
    public void a(ONBTTagCompound var1) {
        super.a(var1);
        ONBTTagList var2 = var1.n("Items");
        this.a = new OItemStack[this.c()];

        for (int var3 = 0; var3 < var2.d(); ++var3) {
            ONBTTagCompound var4 = (ONBTTagCompound) var2.a(var3);
            byte var5 = var4.d("Slot");
            if (var5 >= 0 && var5 < this.a.length) {
                this.a[var5] = OItemStack.a(var4);
            }
        }

        this.b = var1.e("BrewTime");
    }

    @Override
    public void b(ONBTTagCompound var1) {
        super.b(var1);
        var1.a("BrewTime", (short) this.b);
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

    @Override
    public OItemStack g_(int var1) {
        return var1 >= 0 && var1 < this.a.length ? this.a[var1] : null;
    }

    @Override
    public OItemStack a(int var1, int var2) {
        if (var1 >= 0 && var1 < this.a.length) {
            OItemStack var3 = this.a[var1];
            this.a[var1] = null;
            return var3;
        } else {
            return null;
        }
    }

    @Override
    public OItemStack b(int var1) {
        if (var1 >= 0 && var1 < this.a.length) {
            OItemStack var2 = this.a[var1];
            this.a[var1] = null;
            return var2;
        } else {
            return null;
        }
    }

    @Override
    public void a(int var1, OItemStack var2) {
        if (var1 >= 0 && var1 < this.a.length) {
            this.a[var1] = var2;
        }

    }

    @Override
    public int a() {
        return 1;
    }

    @Override
    public boolean a(OEntityPlayer var1) {
        return this.k.b(this.l, this.m, this.n) != this ? false : var1.e(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D) <= 64.0D;
    }

    @Override
    public void f() {
    }

    @Override
    public void g() {
    }

    public int n() {
        int var1 = 0;

        for (int var2 = 0; var2 < 3; ++var2) {
            if (this.a[var2] != null) {
                var1 |= 1 << var2;
            }
        }

        return var1;
    }
}
