package net.minecraft.server;


import net.minecraft.server.OEnchantment;
import net.minecraft.server.OEnumEnchantmentType;


public class OEnchantmentWaterWorker extends OEnchantment {

    public OEnchantmentWaterWorker(int var1, int var2) {
        super(var1, var2, OEnumEnchantmentType.f);
        this.a("waterWorker");
    }

    @Override
    public int a(int var1) {
        return 1;
    }

    @Override
    public int b(int var1) {
        return this.a(var1) + 40;
    }

    @Override
    public int a() {
        return 1;
    }
}
