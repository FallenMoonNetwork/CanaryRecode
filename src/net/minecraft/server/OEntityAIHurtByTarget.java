package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityAITarget;
import net.minecraft.server.OEntityLiving;

public class OEntityAIHurtByTarget extends OEntityAITarget {

    boolean a;

    public OEntityAIHurtByTarget(OEntityLiving var1, boolean var2) {
        super(var1, 16.0F, false);
        this.a = var2;
        this.a(1);
    }

    public boolean a() {
        return this.a(this.c.ao(), true);
    }

    public void c() {
        this.c.b(this.c.ao());
        if (this.a) {
            List var1 = this.c.bi.a(this.c.getClass(), OAxisAlignedBB.b(this.c.bm, this.c.bn, this.c.bo, this.c.bm + 1.0D, this.c.bn + 1.0D, this.c.bo + 1.0D).b(this.d, 4.0D, this.d));
            Iterator var2 = var1.iterator();

            while (var2.hasNext()) {
                OEntity var3 = (OEntity) var2.next();
                OEntityLiving var4 = (OEntityLiving) var3;
                if (this.c != var4 && var4.at() == null) {
                    var4.b(this.c.ao());
                }
            }
        }

        super.c();
    }
}
