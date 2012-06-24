package net.minecraft.server;

import java.util.ArrayList;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OProfiler;

public class OEntitySenses {

    OEntityLiving a;
    ArrayList b = new ArrayList();
    ArrayList c = new ArrayList();

    public OEntitySenses(OEntityLiving var1) {
        super();
        this.a = var1;
    }

    public void a() {
        this.b.clear();
        this.c.clear();
    }

    public boolean a(OEntity var1) {
        if (this.b.contains(var1)) {
            return true;
        } else if (this.c.contains(var1)) {
            return false;
        } else {
            OProfiler.a("canSee");
            boolean var2 = this.a.h(var1);
            OProfiler.a();
            if (var2) {
                this.b.add(var1);
            } else {
                this.c.add(var1);
            }

            return var2;
        }
    }
}
