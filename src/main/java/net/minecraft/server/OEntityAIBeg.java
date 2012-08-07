package net.minecraft.server;

import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntityWolf;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OWorld;

public class OEntityAIBeg extends OEntityAIBase {

    private OEntityWolf a;
    private OEntityPlayer b;
    private OWorld c;
    private float d;
    private int e;

    public OEntityAIBeg(OEntityWolf var1, float var2) {
        super();
        this.a = var1;
        this.c = var1.bi;
        this.d = var2;
        this.a(2);
    }

    @Override
    public boolean a() {
        this.b = this.c.a(this.a, this.d);
        return this.b == null ? false : this.a(this.b);
    }

    @Override
    public boolean b() {
        return !this.b.aE() ? false : (this.a.j(this.b) > (this.d * this.d) ? false : this.e > 0 && this.a(this.b));
    }

    @Override
    public void c() {
        this.a.e(true);
        this.e = 40 + this.a.an().nextInt(40);
    }

    @Override
    public void d() {
        this.a.e(false);
        this.b = null;
    }

    @Override
    public void e() {
        this.a.ai().a(this.b.bm, this.b.bn + this.b.B(), this.b.bo, 10.0F, this.a.D());
        --this.e;
    }

    private boolean a(OEntityPlayer var1) {
        OItemStack var2 = var1.k.d();
        return var2 == null ? false : (!this.a.u_() && var2.c == OItem.aW.bP ? true : this.a.a(var2));
    }
}
