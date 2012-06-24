package net.minecraft.server;

import net.minecraft.server.OEnchantment;
import net.minecraft.server.OEnumEnchantmentType;

public class OEnchantmentDigging extends OEnchantment {

    protected OEnchantmentDigging(int var1, int var2) {
        super(var1, var2, OEnumEnchantmentType.h);
        this.a("digging");
    }

    @Override
    public int a(int var1) {
        return 1 + 15 * (var1 - 1);
    }

    @Override
    public int b(int var1) {
        return super.a(var1) + 50;
    }

    @Override
    public int a() {
        return 5;
    }
}
