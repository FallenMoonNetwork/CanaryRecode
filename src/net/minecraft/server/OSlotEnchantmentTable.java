package net.minecraft.server;

import net.minecraft.server.OContainerEnchantment;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OInventoryBasic;

class OSlotEnchantmentTable extends OInventoryBasic {

    // $FF: synthetic field
    final OContainerEnchantment a;

    OSlotEnchantmentTable(OContainerEnchantment var1, String var2, int var3) {
        super(var2, var3);
        this.a = var1;
    }

    public int a() {
        return 1;
    }

    public void G_() {
        super.G_();
        this.a.a(this);
    }
}
