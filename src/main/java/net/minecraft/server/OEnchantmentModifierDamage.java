package net.minecraft.server;


import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEmpty3;
import net.minecraft.server.OEnchantment;
import net.minecraft.server.OIEnchantmentModifier;


final class OEnchantmentModifierDamage implements OIEnchantmentModifier {

    public int a;
    public ODamageSource b;

    private OEnchantmentModifierDamage() {
        super();
    }

    @Override
    public void a(OEnchantment var1, int var2) {
        this.a += var1.a(var2, this.b);
    }

    // $FF: synthetic method
    OEnchantmentModifierDamage(OEmpty3 var1) {
        this();
    }
}
