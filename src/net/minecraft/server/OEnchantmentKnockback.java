package net.minecraft.server;

import net.minecraft.server.OEnchantment;
import net.minecraft.server.OEnumEnchantmentType;

public class OEnchantmentKnockback extends OEnchantment {

    protected OEnchantmentKnockback(int var1, int var2) {
        super(var1, var2, OEnumEnchantmentType.g);
        this.a("knockback");
    }

    public int a(int var1) {
        return 5 + 20 * (var1 - 1);
    }

    public int b(int var1) {
        return super.a(var1) + 50;
    }

    public int a() {
        return 2;
    }
}
