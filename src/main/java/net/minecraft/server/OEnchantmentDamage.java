package net.minecraft.server;

import net.minecraft.server.OEnchantment;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEnumCreatureAttribute;
import net.minecraft.server.OEnumEnchantmentType;

public class OEnchantmentDamage extends OEnchantment {

    private static final String[] A = new String[] { "all", "undead", "arthropods" };
    private static final int[] B = new int[] { 1, 5, 5 };
    private static final int[] C = new int[] { 16, 8, 8 };
    private static final int[] D = new int[] { 20, 20, 20 };
    public final int a;

    public OEnchantmentDamage(int var1, int var2, int var3) {
        super(var1, var2, OEnumEnchantmentType.g);
        this.a = var3;
    }

    @Override
    public int a(int var1) {
        return B[this.a] + (var1 - 1) * C[this.a];
    }

    @Override
    public int b(int var1) {
        return this.a(var1) + D[this.a];
    }

    @Override
    public int a() {
        return 5;
    }

    @Override
    public int a(int var1, OEntityLiving var2) {
        return this.a == 0 ? var1 * 3 : (this.a == 1 && var2.v() == OEnumCreatureAttribute.b ? var1 * 4 : (this.a == 2 && var2.v() == OEnumCreatureAttribute.c ? var1 * 4 : 0));
    }

    @Override
    public boolean a(OEnchantment var1) {
        return !(var1 instanceof OEnchantmentDamage);
    }

}
