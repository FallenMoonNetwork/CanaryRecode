package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityAnimal;

public class OEntityAIFollowParent extends OEntityAIBase {

    OEntityAnimal a;
    OEntityAnimal b;
    float c;
    private int d;

    public OEntityAIFollowParent(OEntityAnimal var1, float var2) {
        super();
        this.a = var1;
        this.c = var2;
    }

    @Override
    public boolean a() {
        if (this.a.K() >= 0) {
            return false;
        } else {
            List var1 = this.a.bi.a(this.a.getClass(), this.a.bw.b(8.0D, 4.0D, 8.0D));
            OEntityAnimal var2 = null;
            double var3 = Double.MAX_VALUE;
            Iterator var5 = var1.iterator();

            while (var5.hasNext()) {
                OEntity var6 = (OEntity) var5.next();
                OEntityAnimal var7 = (OEntityAnimal) var6;
                if (var7.K() >= 0) {
                    double var8 = this.a.j(var7);
                    if (var8 <= var3) {
                        var3 = var8;
                        var2 = var7;
                    }
                }
            }

            if (var2 == null) {
                return false;
            } else if (var3 < 9.0D) {
                return false;
            } else {
                this.b = var2;
                return true;
            }
        }
    }

    @Override
    public boolean b() {
        if (!this.b.aE()) {
            return false;
        } else {
            double var1 = this.a.j(this.b);
            return var1 >= 9.0D && var1 <= 256.0D;
        }
    }

    @Override
    public void c() {
        this.d = 0;
    }

    @Override
    public void d() {
        this.b = null;
    }

    @Override
    public void e() {
        if (--this.d <= 0) {
            this.d = 10;
            this.a.al().a(this.b, this.c);
        }
    }
}
