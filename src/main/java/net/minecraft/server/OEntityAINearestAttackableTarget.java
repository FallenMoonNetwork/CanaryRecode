package net.minecraft.server;


import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityAINearestAttackableTargetSorter;
import net.minecraft.server.OEntityAITarget;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityPlayer;


public class OEntityAINearestAttackableTarget extends OEntityAITarget {

    OEntityLiving a;
    Class b;
    int f;
    private OEntityAINearestAttackableTargetSorter g;

    public OEntityAINearestAttackableTarget(OEntityLiving var1, Class var2, float var3, int var4, boolean var5) {
        this(var1, var2, var3, var4, var5, false);
    }

    public OEntityAINearestAttackableTarget(OEntityLiving var1, Class var2, float var3, int var4, boolean var5, boolean var6) {
        super(var1, var3, var5, var6);
        this.b = var2;
        this.d = var3;
        this.f = var4;
        this.g = new OEntityAINearestAttackableTargetSorter(this, var1);
        this.a(1);
    }

    @Override
    public boolean a() {
        if (this.f > 0 && this.c.an().nextInt(this.f) != 0) {
            return false;
        } else {
            if (this.b == OEntityPlayer.class) {
                OEntityPlayer var1 = this.c.bi.b(this.c, this.d);

                if (this.a(var1, false)) {
                    this.a = var1;
                    return true;
                }
            } else {
                List var5 = this.c.bi.a(this.b, this.c.bw.b(this.d, 4.0D, this.d));

                Collections.sort(var5, this.g);
                Iterator var2 = var5.iterator();

                while (var2.hasNext()) {
                    OEntity var3 = (OEntity) var2.next();
                    OEntityLiving var4 = (OEntityLiving) var3;

                    if (this.a(var4, false)) {
                        this.a = var4;
                        return true;
                    }
                }
            }

            return false;
        }
    }

    @Override
    public void c() {
        this.c.b(this.a);
        super.c();
    }
}
