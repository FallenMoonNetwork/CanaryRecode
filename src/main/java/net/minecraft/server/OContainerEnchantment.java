package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OContainer;
import net.minecraft.server.OEnchantmentData;
import net.minecraft.server.OEnchantmentHelper;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OICrafting;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OInventoryPlayer;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OSlot;
import net.minecraft.server.OSlotEnchantment;
import net.minecraft.server.OSlotEnchantmentTable;
import net.minecraft.server.OWorld;

public class OContainerEnchantment extends OContainer {

    public OIInventory a = new OSlotEnchantmentTable(this, "Enchant", 1);
    private OWorld h;
    private int i;
    private int j;
    private int k;
    private Random l = new Random();
    public long b;
    public int[] c = new int[3];

    public OContainerEnchantment(OInventoryPlayer var1, OWorld var2, int var3, int var4, int var5) {
        super();
        this.h = var2;
        this.i = var3;
        this.j = var4;
        this.k = var5;
        this.a((new OSlotEnchantment(this, this.a, 0, 25, 47)));

        int var6;
        for (var6 = 0; var6 < 3; ++var6) {
            for (int var7 = 0; var7 < 9; ++var7) {
                this.a(new OSlot(var1, var7 + var6 * 9 + 9, 8 + var7 * 18, 84 + var6 * 18));
            }
        }

        for (var6 = 0; var6 < 9; ++var6) {
            this.a(new OSlot(var1, var6, 8 + var6 * 18, 142));
        }

    }

    @Override
    public void a(OICrafting var1) {
        super.a(var1);
        var1.a(this, 0, this.c[0]);
        var1.a(this, 1, this.c[1]);
        var1.a(this, 2, this.c[2]);
    }

    @Override
    public void a() {
        super.a();

        for (int var1 = 0; var1 < this.g.size(); ++var1) {
            OICrafting var2 = (OICrafting) this.g.get(var1);
            var2.a(this, 0, this.c[0]);
            var2.a(this, 1, this.c[1]);
            var2.a(this, 2, this.c[2]);
        }

    }

    @Override
    public void a(OIInventory var1) {
        if (var1 == this.a) {
            OItemStack var2 = var1.b(0);
            int var3;
            if (var2 != null && var2.q()) {
                this.b = this.l.nextLong();
                if (!this.h.F) {
                    var3 = 0;

                    int var4;
                    for (var4 = -1; var4 <= 1; ++var4) {
                        for (int var5 = -1; var5 <= 1; ++var5) {
                            if ((var4 != 0 || var5 != 0) && this.h.g(this.i + var5, this.j, this.k + var4) && this.h.g(this.i + var5, this.j + 1, this.k + var4)) {
                                if (this.h.a(this.i + var5 * 2, this.j, this.k + var4 * 2) == OBlock.an.bO) {
                                    ++var3;
                                }

                                if (this.h.a(this.i + var5 * 2, this.j + 1, this.k + var4 * 2) == OBlock.an.bO) {
                                    ++var3;
                                }

                                if (var5 != 0 && var4 != 0) {
                                    if (this.h.a(this.i + var5 * 2, this.j, this.k + var4) == OBlock.an.bO) {
                                        ++var3;
                                    }

                                    if (this.h.a(this.i + var5 * 2, this.j + 1, this.k + var4) == OBlock.an.bO) {
                                        ++var3;
                                    }

                                    if (this.h.a(this.i + var5, this.j, this.k + var4 * 2) == OBlock.an.bO) {
                                        ++var3;
                                    }

                                    if (this.h.a(this.i + var5, this.j + 1, this.k + var4 * 2) == OBlock.an.bO) {
                                        ++var3;
                                    }
                                }
                            }
                        }
                    }

                    for (var4 = 0; var4 < 3; ++var4) {
                        this.c[var4] = OEnchantmentHelper.a(this.l, var4, var3, var2);
                    }

                    this.a();
                }
            } else {
                for (var3 = 0; var3 < 3; ++var3) {
                    this.c[var3] = 0;
                }
            }
        }

    }

    @Override
    public boolean a(OEntityPlayer var1, int var2) {
        OItemStack var3 = this.a.b(0);
        if (this.c[var2] > 0 && var3 != null && (var1.M >= this.c[var2] || var1.L.d)) {
            if (!this.h.F) {
                List var4 = OEnchantmentHelper.b(this.l, var3, this.c[var2]);
                if (var4 != null) {
                    var1.e_(this.c[var2]);
                    Iterator var5 = var4.iterator();

                    while (var5.hasNext()) {
                        OEnchantmentData var6 = (OEnchantmentData) var5.next();
                        var3.a(var6.a, var6.b);
                    }

                    this.a(this.a);
                }
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onInventoryClose(OEntityPlayer var1) {
        super.onInventoryClose(var1);
        if (!this.h.F) {
            OItemStack var2 = this.a.b(0);
            if (var2 != null) {
                var1.b(var2);
            }

        }
    }

    @Override
    public boolean b(OEntityPlayer var1) {
        return this.h.a(this.i, this.j, this.k) != OBlock.bE.bO ? false : var1.e(this.i + 0.5D, this.j + 0.5D, this.k + 0.5D) <= 64.0D;
    }

    @Override
    public OItemStack a(int var1) {
        OItemStack var2 = null;
        OSlot var3 = (OSlot) this.e.get(var1);
        if (var3 != null && var3.c()) {
            OItemStack var4 = var3.b();
            var2 = var4.j();
            if (var1 != 0) {
                return null;
            }

            if (!this.a(var4, 1, 37, true)) {
                return null;
            }

            if (var4.a == 0) {
                var3.d((OItemStack) null);
            } else {
                var3.d();
            }

            if (var4.a == var2.a) {
                return null;
            }

            var3.c(var4);
        }

        return var2;
    }
}
