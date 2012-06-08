package net.minecraft.server;

import net.minecraft.server.OEnchantment;
import net.minecraft.server.OEnumEnchantmentType;

public class OEnchantmentLootBonus extends OEnchantment {

    protected OEnchantmentLootBonus(int var1, int var2, OEnumEnchantmentType var3) {
        super(var1, var2, var3);
        this.a("lootBonus");
        if (var3 == OEnumEnchantmentType.h) {
            this.a("lootBonusDigger");
        }

    }

    @Override
    public int a(int var1) {
        return 20 + (var1 - 1) * 12;
    }

    @Override
    public int b(int var1) {
        return super.a(var1) + 50;
    }

    @Override
    public int a() {
        return 3;
    }

    @Override
    public boolean a(OEnchantment var1) {
        return super.a(var1) && var1.x != q.x;
    }
}
