package net.minecraft.server;

import java.util.Comparator;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityAINearestAttackableTarget;

public class OEntityAINearestAttackableTargetSorter implements Comparator {

    private OEntity b;
    // $FF: synthetic field
    final OEntityAINearestAttackableTarget a;

    public OEntityAINearestAttackableTargetSorter(OEntityAINearestAttackableTarget var1, OEntity var2) {
        super();
        this.a = var1;
        this.b = var2;
    }

    public int a(OEntity var1, OEntity var2) {
        double var3 = this.b.j(var1);
        double var5 = this.b.j(var2);
        return var3 < var5 ? -1 : (var3 > var5 ? 1 : 0);
    }

    // $FF: synthetic method
    // $FF: bridge method
    @Override
    public int compare(Object var1, Object var2) {
        return this.a((OEntity) var1, (OEntity) var2);
    }
}
