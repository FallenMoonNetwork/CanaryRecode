package net.minecraft.server;

import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEnchantment;
import net.minecraft.server.OEnumEnchantmentType;

public class OEnchantmentProtection extends OEnchantment {

    private static final String[] A = new String[] { "all", "fire", "fall", "explosion", "projectile" };
    private static final int[] B = new int[] { 1, 10, 5, 5, 3 };
    private static final int[] C = new int[] { 16, 8, 6, 8, 6 };
    private static final int[] D = new int[] { 20, 12, 10, 12, 15 };
    public final int a;

    public OEnchantmentProtection(int var1, int var2, int var3) {
        super(var1, var2, OEnumEnchantmentType.b);
        this.a = var3;
        if (var3 == 2) {
            this.y = OEnumEnchantmentType.c;
        }

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
        return 4;
    }

    @Override
    public int a(int var1, ODamageSource var2) {
        if (var2.g()) {
            return 0;
        } else {
            int var3 = (6 + var1 * var1) / 2;
            return this.a == 0 ? var3 : (this.a == 1 && var2.k() ? var3 : (this.a == 2 && var2 == ODamageSource.i ? var3 * 2 : (this.a == 3 && var2 == ODamageSource.l ? var3 : (this.a == 4 && var2.c() ? var3 : 0))));
        }
    }

    @Override
    public boolean a(OEnchantment var1) {
        if (var1 instanceof OEnchantmentProtection) {
            OEnchantmentProtection var2 = (OEnchantmentProtection) var1;
            return var2.a == this.a ? false : this.a == 2 || var2.a == 2;
        } else {
            return super.a(var1);
        }
    }

}
