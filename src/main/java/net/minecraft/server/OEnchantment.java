package net.minecraft.server;

import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEnchantmentArrowDamage;
import net.minecraft.server.OEnchantmentArrowFire;
import net.minecraft.server.OEnchantmentArrowInfinite;
import net.minecraft.server.OEnchantmentArrowKnockback;
import net.minecraft.server.OEnchantmentDamage;
import net.minecraft.server.OEnchantmentDigging;
import net.minecraft.server.OEnchantmentDurability;
import net.minecraft.server.OEnchantmentFireAspect;
import net.minecraft.server.OEnchantmentKnockback;
import net.minecraft.server.OEnchantmentLootBonus;
import net.minecraft.server.OEnchantmentOxygen;
import net.minecraft.server.OEnchantmentProtection;
import net.minecraft.server.OEnchantmentUntouching;
import net.minecraft.server.OEnchantmentWaterWorker;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEnumEnchantmentType;

public abstract class OEnchantment {

    public static final OEnchantment[] b = new OEnchantment[256];
    public static final OEnchantment c = new OEnchantmentProtection(0, 10, 0);
    public static final OEnchantment d = new OEnchantmentProtection(1, 5, 1);
    public static final OEnchantment e = new OEnchantmentProtection(2, 5, 2);
    public static final OEnchantment f = new OEnchantmentProtection(3, 2, 3);
    public static final OEnchantment g = new OEnchantmentProtection(4, 5, 4);
    public static final OEnchantment h = new OEnchantmentOxygen(5, 2);
    public static final OEnchantment i = new OEnchantmentWaterWorker(6, 2);
    public static final OEnchantment j = new OEnchantmentDamage(16, 10, 0);
    public static final OEnchantment k = new OEnchantmentDamage(17, 5, 1);
    public static final OEnchantment l = new OEnchantmentDamage(18, 5, 2);
    public static final OEnchantment m = new OEnchantmentKnockback(19, 5);
    public static final OEnchantment n = new OEnchantmentFireAspect(20, 2);
    public static final OEnchantment o = new OEnchantmentLootBonus(21, 2, OEnumEnchantmentType.g);
    public static final OEnchantment p = new OEnchantmentDigging(32, 10);
    public static final OEnchantment q = new OEnchantmentUntouching(33, 1);
    public static final OEnchantment r = new OEnchantmentDurability(34, 5);
    public static final OEnchantment s = new OEnchantmentLootBonus(35, 2, OEnumEnchantmentType.h);
    public static final OEnchantment t = new OEnchantmentArrowDamage(48, 10);
    public static final OEnchantment u = new OEnchantmentArrowKnockback(49, 2);
    public static final OEnchantment v = new OEnchantmentArrowFire(50, 2);
    public static final OEnchantment w = new OEnchantmentArrowInfinite(51, 1);
    public final int x;
    private final int a;
    public OEnumEnchantmentType y;
    protected String z;

    protected OEnchantment(int var1, int var2, OEnumEnchantmentType var3) {
        super();
        this.x = var1;
        this.a = var2;
        this.y = var3;
        if (b[var1] != null) {
            throw new IllegalArgumentException("Duplicate enchantment id!");
        } else {
            b[var1] = this;
        }
    }

    public int b() {
        return this.a;
    }

    public int c() {
        return 1;
    }

    public int a() {
        return 1;
    }

    public int a(int var1) {
        return 1 + var1 * 10;
    }

    public int b(int var1) {
        return this.a(var1) + 5;
    }

    public int a(int var1, ODamageSource var2) {
        return 0;
    }

    public int a(int var1, OEntityLiving var2) {
        return 0;
    }

    public boolean a(OEnchantment var1) {
        return this != var1;
    }

    public OEnchantment a(String var1) {
        this.z = var1;
        return this;
    }

}
