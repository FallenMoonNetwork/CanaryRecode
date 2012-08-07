package net.minecraft.server;

import net.minecraft.server.OEnumArmorMaterial;
import net.minecraft.server.OItem;

public class OItemArmor extends OItem {

    private static final int[] bV = new int[] { 11, 16, 15, 13 };
    public final int a;
    public final int b;
    public final int bU;
    private final OEnumArmorMaterial bW;

    public OItemArmor(int var1, OEnumArmorMaterial var2, int var3, int var4) {
        super(var1);
        this.bW = var2;
        this.a = var4;
        this.bU = var3;
        this.b = var2.b(var4);
        this.f(var2.a(var4));
        this.bQ = 1;
    }

    @Override
    public int c() {
        return this.bW.a();
    }

    // $FF: synthetic method
    static int[] o() {
        return bV;
    }

}
