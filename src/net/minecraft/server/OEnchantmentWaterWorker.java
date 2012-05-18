package net.minecraft.server;

import net.minecraft.server.OEnchantment;
import net.minecraft.server.OEnumEnchantmentType;

public class OEnchantmentWaterWorker extends OEnchantment {

    public OEnchantmentWaterWorker(int var1, int var2) {
        super(var1, var2, OEnumEnchantmentType.f);
        this.a("waterWorker");
    }

    public int a(int var1) {
        return 1;
    }

    public int b(int var1) {
        return this.a(var1) + 40;
    }

    public int a() {
        return 1;
    }
}
