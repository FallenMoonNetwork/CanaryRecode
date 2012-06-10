package net.minecraft.server;

import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEnumAction;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OPotionEffect;
import net.minecraft.server.OWorld;

public class OItemFood extends OItem {

    public final int a;
    private final int b;
    private final float bU;
    private final boolean bV;
    private boolean bW;
    private int bX;
    private int bY;
    private int bZ;
    private float ca;

    public OItemFood(int var1, int var2, float var3, boolean var4) {
        super(var1);
        this.a = 32;
        this.b = var2;
        this.bV = var4;
        this.bU = var3;
    }

    public OItemFood(int var1, int var2, boolean var3) {
        this(var1, var2, 0.6F, var3);
    }

    @Override
    public OItemStack b(OItemStack var1, OWorld var2, OEntityPlayer var3) {
        --var1.a;
        var3.getFoodStats().addStats(this);
        var2.a(var3, "random.burp", 0.5F, var2.r.nextFloat() * 0.1F + 0.9F);
        if (!var2.F && this.bX > 0 && var2.r.nextFloat() < this.ca) {
            var3.e(new OPotionEffect(this.bX, this.bY * 20, this.bZ));
        }

        return var1;
    }

    @Override
    public int c(OItemStack var1) {
        return 32;
    }

    @Override
    public OEnumAction d(OItemStack var1) {
        return OEnumAction.b;
    }

    @Override
    public OItemStack a(OItemStack var1, OWorld var2, OEntityPlayer var3) {
        if (var3.b(this.bW)) {
            var3.a(var1, this.c(var1));
        }

        return var1;
    }

    public int o() {
        return this.b;
    }

    public float p() {
        return this.bU;
    }

    public boolean q() {
        return this.bV;
    }

    public OItemFood a(int var1, int var2, int var3, float var4) {
        this.bX = var1;
        this.bY = var2;
        this.bZ = var3;
        this.ca = var4;
        return this;
    }

    public OItemFood r() {
        this.bW = true;
        return this;
    }

    @Override
    public OItem a(String var1) {
        return super.a(var1);
    }
}
