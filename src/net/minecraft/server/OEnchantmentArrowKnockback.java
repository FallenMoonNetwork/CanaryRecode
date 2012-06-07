package net.minecraft.server;

import net.minecraft.server.OEnchantment;
import net.minecraft.server.OEnumEnchantmentType;

public class OEnchantmentArrowKnockback extends OEnchantment {

    public OEnchantmentArrowKnockback(int var1, int var2) {
        super(var1, var2, OEnumEnchantmentType.i);
        this.a("arrowKnockback");
    }

    @Override
    public int a(int var1) {
        return 12 + (var1 - 1) * 20;
    }

    @Override
    public int b(int var1) {
        return this.a(var1) + 25;
    }

    @Override
    public int a() {
        return 2;
    }
}
