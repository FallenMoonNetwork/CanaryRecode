package net.minecraft.server;


import java.util.Iterator;
import java.util.List;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityVillager;
import net.minecraft.server.ORandomPositionGenerator;
import net.minecraft.server.OVec3D;


public class OEntityAIPlay extends OEntityAIBase {

    private OEntityVillager a;
    private OEntityLiving b;
    private float c;
    private int d;

    public OEntityAIPlay(OEntityVillager var1, float var2) {
        super();
        this.a = var1;
        this.c = var2;
        this.a(1);
    }

    @Override
    public boolean a() {
        if (this.a.K() >= 0) {
            return false;
        } else if (this.a.an().nextInt(400) != 0) {
            return false;
        } else {
            List var1 = this.a.bi.a(OEntityVillager.class, this.a.bw.b(6.0D, 3.0D, 6.0D));
            double var2 = Double.MAX_VALUE;
            Iterator var4 = var1.iterator();

            while (var4.hasNext()) {
                OEntity var5 = (OEntity) var4.next();

                if (var5 != this.a) {
                    OEntityVillager var6 = (OEntityVillager) var5;

                    if (!var6.C() && var6.K() < 0) {
                        double var7 = var6.j(this.a);

                        if (var7 <= var2) {
                            var2 = var7;
                            this.b = var6;
                        }
                    }
                }
            }

            if (this.b == null) {
                OVec3D var9 = ORandomPositionGenerator.a(this.a, 16, 3);

                if (var9 == null) {
                    return false;
                }
            }

            return true;
        }
    }

    @Override
    public boolean b() {
        return this.d > 0;
    }

    @Override
    public void c() {
        if (this.b != null) {
            this.a.b(true);
        }

        this.d = 1000;
    }

    @Override
    public void d() {
        this.a.b(false);
        this.b = null;
    }

    @Override
    public void e() {
        --this.d;
        if (this.b != null) {
            if (this.a.j(this.b) > 4.0D) {
                this.a.al().a(this.b, this.c);
            }
        } else if (this.a.al().e()) {
            OVec3D var1 = ORandomPositionGenerator.a(this.a, 16, 3);

            if (var1 == null) {
                return;
            }

            this.a.al().a(var1.a, var1.b, var1.c, this.c);
        }

    }
}
