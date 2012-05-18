package net.minecraft.server;

import net.minecraft.server.OEnchantment;
import net.minecraft.server.OEnumEnchantmentType;

public class OEnchantmentFireAspect extends OEnchantment {

    protected OEnchantmentFireAspect(int var1, int var2) {
        super(var1, var2, OEnumEnchantmentType.g);
        this.a("fire");
    }

    public int a(int var1) {
        return 10 + 20 * (var1 - 1);
    }

    public int b(int var1) {
        return super.a(var1) + 50;
    }

    public int a() {
        return 2;
    }
}
