package net.minecraft.server;


import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityPlayer;


public class OEntityAIWatchClosest extends OEntityAIBase {

    private OEntityLiving a;
    private OEntity b;
    private float c;
    private int d;
    private float e;
    private Class f;

    public OEntityAIWatchClosest(OEntityLiving var1, Class var2, float var3) {
        super();
        this.a = var1;
        this.f = var2;
        this.c = var3;
        this.e = 0.02F;
        this.a(2);
    }

    public OEntityAIWatchClosest(OEntityLiving var1, Class var2, float var3, float var4) {
        super();
        this.a = var1;
        this.f = var2;
        this.c = var3;
        this.e = var4;
        this.a(2);
    }

    @Override
    public boolean a() {
        if (this.a.an().nextFloat() >= this.e) {
            return false;
        } else {
            if (this.f == OEntityPlayer.class) {
                this.b = this.a.bi.a(this.a, this.c);
            } else {
                this.b = this.a.bi.a(this.f, this.a.bw.b(this.c, 3.0D, this.c), this.a);
            }

            return this.b != null;
        }
    }

    @Override
    public boolean b() {
        return !this.b.aE() ? false : (this.a.j(this.b) > (this.c * this.c) ? false : this.d > 0);
    }

    @Override
    public void c() {
        this.d = 40 + this.a.an().nextInt(40);
    }

    @Override
    public void d() {
        this.b = null;
    }

    @Override
    public void e() {
        this.a.ai().a(this.b.bm, this.b.bn + this.b.B(), this.b.bo, 10.0F, this.a.D());
        --this.d;
    }
}
