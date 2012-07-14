package net.minecraft.server;

import net.minecraft.server.OContainer;
import net.minecraft.server.OCraftingManager;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OInventoryCraftResult;
import net.minecraft.server.OInventoryCrafting;
import net.minecraft.server.OInventoryPlayer;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OSlot;
import net.minecraft.server.OSlotArmor;
import net.minecraft.server.OSlotCrafting;

public class OContainerPlayer extends OContainer {

    public OInventoryCrafting a;
    public OIInventory b;
    public boolean c;
    public OInventoryPlayer playerInventory; // CanaryMod

    public OContainerPlayer(OInventoryPlayer var1) {
        this(var1, true);
    }

    public OContainerPlayer(OInventoryPlayer var1, boolean var2) {
        super();
        this.playerInventory = var1; // CanaryMod
        this.a = new OInventoryCrafting(this, 2, 2);
        this.b = new OInventoryCraftResult();
        this.c = false;
        this.c = var2;
        this.a((new OSlotCrafting(var1.d, this.a, this.b, 0, 144, 36)));

        int var3;
        int var4;
        for (var3 = 0; var3 < 2; ++var3) {
            for (var4 = 0; var4 < 2; ++var4) {
                this.a(new OSlot(this.a, var4 + var3 * 2, 88 + var4 * 18, 26 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 4; ++var3) {
            this.a((new OSlotArmor(this, var1, var1.getInventorySize() - 1 - var3, 8, 8 + var3 * 18, var3)));
        }

        for (var3 = 0; var3 < 3; ++var3) {
            for (var4 = 0; var4 < 9; ++var4) {
                this.a(new OSlot(var1, var4 + (var3 + 1) * 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3) {
            this.a(new OSlot(var1, var3, 8 + var3 * 18, 142));
        }

        this.a(this.a);
    }

    @Override
    public void a(OIInventory var1) {
        this.b.a(0, OCraftingManager.a().a(this.a));
        this.a.update(); // CanaryMod
    }
    @Override
    public void onInventoryClose(OEntityPlayer var1) {
        super.onInventoryClose(var1);

        for (int var2 = 0; var2 < 4; ++var2) {
            OItemStack var3 = this.a.b(var2);
            if (var3 != null) {
                var1.b(var3);
            }
        }

        this.b.a(0, (OItemStack) null);
    }

    @Override
    public boolean b(OEntityPlayer var1) {
        return true;
    }

    @Override
    public OItemStack a(int var1) {
        OItemStack var2 = null;
        OSlot var3 = (OSlot) this.e.get(var1);
        if (var3 != null && var3.c()) {
            OItemStack var4 = var3.b();
            var2 = var4.j();
            if (var1 == 0) {
                if (!this.a(var4, 9, 45, true)) {
                    return null;
                }

                var3.a(var4, var2);
            } else if (var1 >= 9 && var1 < 36) {
                if (!this.a(var4, 36, 45, false)) {
                    return null;
                }
            } else if (var1 >= 36 && var1 < 45) {
                if (!this.a(var4, 9, 36, false)) {
                    return null;
                }
            } else if (!this.a(var4, 9, 45, false)) {
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
