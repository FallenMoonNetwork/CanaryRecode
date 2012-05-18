package net.minecraft.server;

import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityLiving;

public class OEntityAILookIdle extends OEntityAIBase {

    private OEntityLiving a;
    private double b;
    private double c;
    private int d = 0;

    public OEntityAILookIdle(OEntityLiving var1) {
        super();
        this.a = var1;
        this.a(3);
    }

    public boolean a() {
        return this.a.an().nextFloat() < 0.02F;
    }

    public boolean b() {
        return this.d >= 0;
    }

    public void c() {
        double var1 = 6.283185307179586D * this.a.an().nextDouble();
        this.b = Math.cos(var1);
        this.c = Math.sin(var1);
        this.d = 20 + this.a.an().nextInt(20);
    }

    public void e() {
        --this.d;
        this.a.ai().a(this.a.bm + this.b, this.a.bn + this.a.B(), this.a.bo + this.c, 10.0F, this.a.D());
    }
}
