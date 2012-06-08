package net.minecraft.server;

import net.minecraft.server.OEnchantment;
import net.minecraft.server.OEnumEnchantmentType;

public class OEnchantmentArrowFire extends OEnchantment {

    public OEnchantmentArrowFire(int var1, int var2) {
        super(var1, var2, OEnumEnchantmentType.i);
        this.a("arrowFire");
    }

    @Override
    public int a(int var1) {
        return 20;
    }

    @Override
    public int b(int var1) {
        return 50;
    }

    @Override
    public int a() {
        return 1;
    }
}
