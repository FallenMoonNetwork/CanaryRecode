package net.minecraft.server;

import net.minecraft.server.OContainerBrewingStand;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OSlot;

class OSlotBrewingStandIngredient extends OSlot {

    // $FF: synthetic field
    final OContainerBrewingStand a;

    public OSlotBrewingStandIngredient(OContainerBrewingStand var1, OIInventory var2, int var3, int var4, int var5) {
        super(var2, var3, var4, var5);
        this.a = var1;
    }

    public boolean a(OItemStack var1) {
        return var1 != null ? OItem.d[var1.c].n() : false;
    }

    public int a() {
        return 64;
    }
}
