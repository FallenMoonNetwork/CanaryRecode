package net.minecraft.server;

import net.minecraft.server.OItemArmor;

public enum OEnumArmorMaterial {

    a("CLOTH", 0, 5, new int[] { 1, 3, 2, 1 }, 15), b("CHAIN", 1, 15, new int[] { 2, 5, 4, 1 }, 12), c("IRON", 2, 15, new int[] { 2, 6, 5, 2 }, 9), d(
            "GOLD",
            3,
            7,
            new int[] { 2, 5, 3, 1 },
            25), e("DIAMOND", 4, 33, new int[] { 3, 8, 6, 3 }, 10);
    private int f;
    private int[] g;
    private int h;
    // $FF: synthetic field
    private static final OEnumArmorMaterial[] i = new OEnumArmorMaterial[] { a, b, c, d, e };

    private OEnumArmorMaterial(String var1, int var2, int var3, int[] var4, int var5) {
        this.f = var3;
        this.g = var4;
        this.h = var5;
    }

    public int a(int var1) {
        return OItemArmor.o()[var1] * this.f;
    }

    public int b(int var1) {
        return this.g[var1];
    }

    public int a() {
        return this.h;
    }

}
