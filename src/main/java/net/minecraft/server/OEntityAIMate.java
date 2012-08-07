package net.minecraft.server;


import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityAnimal;
import net.minecraft.server.OWorld;


public class OEntityAIMate extends OEntityAIBase {

    private OEntityAnimal d;
    OWorld a;
    private OEntityAnimal e;
    int b = 0;
    float c;

    public OEntityAIMate(OEntityAnimal var1, float var2) {
        super();
        this.d = var1;
        this.a = var1.bi;
        this.c = var2;
        this.a(3);
    }

    @Override
    public boolean a() {
        if (!this.d.r_()) {
            return false;
        } else {
            this.e = this.f();
            return this.e != null;
        }
    }

    @Override
    public boolean b() {
        return this.e.aE() && this.e.r_() && this.b < 60;
    }

    @Override
    public void d() {
        this.e = null;
        this.b = 0;
    }

    @Override
    public void e() {
        this.d.ai().a(this.e, 10.0F, this.d.D());
        this.d.al().a(this.e, this.c);
        ++this.b;
        if (this.b == 60) {
            this.i();
        }

    }

    private OEntityAnimal f() {
        float var1 = 8.0F;
        List var2 = this.a.a(this.d.getClass(), this.d.bw.b(var1, var1, var1));
        Iterator var3 = var2.iterator();

        OEntityAnimal var5;

        do {
            if (!var3.hasNext()) {
                return null;
            }

            OEntity var4 = (OEntity) var3.next();

            var5 = (OEntityAnimal) var4;
        } while (!this.d.b(var5));

        return var5;
    }

    private void i() {
        OEntityAnimal var1 = this.d.a(this.e);

        if (var1 != null) {
            this.d.c(6000);
            this.e.c(6000);
            this.d.s_();
            this.e.s_();
            var1.c(-24000);
            var1.c(this.d.bm, this.d.bn, this.d.bo, 0.0F, 0.0F);
            this.a.b(var1);
            Random var2 = this.d.an();

            for (int var3 = 0; var3 < 7; ++var3) {
                double var4 = var2.nextGaussian() * 0.02D;
                double var6 = var2.nextGaussian() * 0.02D;
                double var8 = var2.nextGaussian() * 0.02D;

                this.a.a("heart", this.d.bm + (var2.nextFloat() * this.d.bG * 2.0F) - this.d.bG, this.d.bn + 0.5D + (var2.nextFloat() * this.d.bH), this.d.bo + (var2.nextFloat() * this.d.bG * 2.0F) - this.d.bG, var4, var6, var8);
            }

        }
    }
}
