package net.minecraft.server;

import net.minecraft.server.OEnchantment;
import net.minecraft.server.OEnumEnchantmentType;

public class OEnchantmentUntouching extends OEnchantment {

    protected OEnchantmentUntouching(int var1, int var2) {
        super(var1, var2, OEnumEnchantmentType.h);
        this.a("untouching");
    }

    public int a(int var1) {
        return 25;
    }

    public int b(int var1) {
        return super.a(var1) + 50;
    }

    public int a() {
        return 1;
    }

    public boolean a(OEnchantment var1) {
        return super.a(var1) && var1.x != s.x;
    }
}
