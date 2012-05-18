package net.minecraft.server;

import net.minecraft.server.OEnchantment;
import net.minecraft.server.OEnumEnchantmentType;

public class OEnchantmentOxygen extends OEnchantment {

    public OEnchantmentOxygen(int var1, int var2) {
        super(var1, var2, OEnumEnchantmentType.f);
        this.a("oxygen");
    }

    public int a(int var1) {
        return 10 * var1;
    }

    public int b(int var1) {
        return this.a(var1) + 30;
    }

    public int a() {
        return 3;
    }
}
