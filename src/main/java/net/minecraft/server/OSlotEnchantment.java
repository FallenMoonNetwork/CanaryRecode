package net.minecraft.server;

import net.minecraft.server.OContainerEnchantment;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OSlot;

class OSlotEnchantment extends OSlot {

    // $FF: synthetic field
    final OContainerEnchantment a;

    OSlotEnchantment(OContainerEnchantment var1, OIInventory var2, int var3, int var4, int var5) {
        super(var2, var3, var4, var5);
        this.a = var1;
    }

    @Override
    public boolean a(OItemStack var1) {
        return true;
    }
}
