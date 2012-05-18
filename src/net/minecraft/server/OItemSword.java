package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEnumAction;
import net.minecraft.server.OEnumToolMaterial;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OWorld;

public class OItemSword extends OItem {

    private int a;
    private final OEnumToolMaterial b;

    public OItemSword(int var1, OEnumToolMaterial var2) {
        super(var1);
        this.b = var2;
        this.bQ = 1;
        this.f(var2.a());
        this.a = 4 + var2.c();
    }

    public float a(OItemStack var1, OBlock var2) {
        return var2.bO == OBlock.W.bO ? 15.0F : 1.5F;
    }

    public boolean a(OItemStack var1, OEntityLiving var2, OEntityLiving var3) {
        var1.a(1, var3);
        return true;
    }

    public boolean a(OItemStack var1, int var2, int var3, int var4, int var5, OEntityLiving var6) {
        var1.a(2, var6);
        return true;
    }

    public int a(OEntity var1) {
        return this.a;
    }

    public OEnumAction d(OItemStack var1) {
        return OEnumAction.d;
    }

    public int c(OItemStack var1) {
        return 72000;
    }

    public OItemStack a(OItemStack var1, OWorld var2, OEntityPlayer var3) {
        var3.a(var1, this.c(var1));
        return var1;
    }

    public boolean a(OBlock var1) {
        return var1.bO == OBlock.W.bO;
    }

    public int c() {
        return this.b.e();
    }
}
