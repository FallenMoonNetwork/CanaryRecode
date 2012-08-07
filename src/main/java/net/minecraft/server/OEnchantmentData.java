package net.minecraft.server;


import net.minecraft.server.OEnchantment;
import net.minecraft.server.OWeightedRandomChoice;


public class OEnchantmentData extends OWeightedRandomChoice {

    public final OEnchantment a;
    public final int b;

    public OEnchantmentData(OEnchantment var1, int var2) {
        super(var1.b());
        this.a = var1;
        this.b = var2;
    }
}
