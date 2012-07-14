package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OContainer;
import net.minecraft.server.OCraftingManager;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OInventoryCraftResult;
import net.minecraft.server.OInventoryCrafting;
import net.minecraft.server.OInventoryPlayer;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OSlot;
import net.minecraft.server.OSlotCrafting;
import net.minecraft.server.OWorld;

public class OContainerWorkbench extends OContainer {

    public OInventoryCrafting craftingGrid = new OInventoryCrafting(this, 3, 3); //CanaryMod renamed a -> craftingGrid
    public OIInventory craftResultInventory = new OInventoryCraftResult(); //CanaryMod renamed b -> craftResultInventory
    public OWorld c;
    public int h;
    public int i;
    public int j;
    public OInventoryPlayer playerInventory; // CanaryMod

    public OContainerWorkbench(OInventoryPlayer var1, OWorld var2, int var3, int var4, int var5) {
        super();
        this.playerInventory = var1; // CanaryMod
        this.c = var2;
        this.h = var3;
        this.i = var4;
        this.j = var5;
        this.a((new OSlotCrafting(var1.d, this.craftingGrid, this.craftResultInventory, 0, 124, 35)));

        int var6;
        int var7;
        for (var6 = 0; var6 < 3; ++var6) {
            for (var7 = 0; var7 < 3; ++var7) {
                this.a(new OSlot(this.craftingGrid, var7 + var6 * 3, 30 + var7 * 18, 17 + var6 * 18));
            }
        }

        for (var6 = 0; var6 < 3; ++var6) {
            for (var7 = 0; var7 < 9; ++var7) {
                this.a(new OSlot(var1, var7 + var6 * 9 + 9, 8 + var7 * 18, 84 + var6 * 18));
            }
        }

        for (var6 = 0; var6 < 9; ++var6) {
            this.a(new OSlot(var1, var6, 8 + var6 * 18, 142));
        }

        this.a(this.craftingGrid);
    }

    @Override
    public void a(OIInventory var1) {
        this.craftResultInventory.a(0, OCraftingManager.a().a(this.craftingGrid));
        this.craftingGrid.update(); // CanaryMod
    }

    //CanaryMod renamed a -> onInventoryClose
    @Override
    public void onInventoryClose(OEntityPlayer var1) {
        super.onInventoryClose(var1);
        if (!this.c.F) {
            for (int var2 = 0; var2 < 9; ++var2) {
                OItemStack var3 = this.craftingGrid.b(var2);
                if (var3 != null) {
                    var1.b(var3);
                }
            }

        }
    }

    @Override
    public boolean b(OEntityPlayer var1) {
        return this.c.a(this.h, this.i, this.j) != OBlock.ay.bO ? false : var1.e(this.h + 0.5D, this.i + 0.5D, this.j + 0.5D) <= 64.0D;
    }

    @Override
    public OItemStack a(int var1) {
        OItemStack var2 = null;
        OSlot var3 = (OSlot) this.e.get(var1);
        if (var3 != null && var3.c()) {
            OItemStack var4 = var3.b();
            var2 = var4.j();
            if (var1 == 0) {
                if (!this.a(var4, 10, 46, true)) {
                    return null;
                }

                var3.a(var4, var2);
            } else if (var1 >= 10 && var1 < 37) {
                if (!this.a(var4, 37, 46, false)) {
                    return null;
                }
            } else if (var1 >= 37 && var1 < 46) {
                if (!this.a(var4, 10, 37, false)) {
                    return null;
                }
            } else if (!this.a(var4, 10, 46, false)) {
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
