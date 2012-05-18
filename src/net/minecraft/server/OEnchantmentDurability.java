package net.minecraft.server;

import net.minecraft.server.OEnchantment;
import net.minecraft.server.OEnumEnchantmentType;

public class OEnchantmentDurability extends OEnchantment {

    protected OEnchantmentDurability(int var1, int var2) {
        super(var1, var2, OEnumEnchantmentType.h);
        this.a("durability");
    }

    public int a(int var1) {
        return 5 + (var1 - 1) * 10;
    }

    public int b(int var1) {
        return super.a(var1) + 50;
    }

    public int a() {
        return 3;
    }
}
