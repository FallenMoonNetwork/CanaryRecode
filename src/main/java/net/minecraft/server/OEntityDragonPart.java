package net.minecraft.server;


import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityDragonBase;
import net.minecraft.server.ONBTTagCompound;


public class OEntityDragonPart extends OEntity {

    public final OEntityDragonBase a;
    public final String b;

    public OEntityDragonPart(OEntityDragonBase var1, String var2, float var3, float var4) {
        super(var1.bi);
        this.b(var3, var4);
        this.a = var1;
        this.b = var2;
    }

    @Override
    protected void b() {}

    @Override
    protected void a(ONBTTagCompound var1) {}

    @Override
    protected void b(ONBTTagCompound var1) {}

    @Override
    public boolean o_() {
        return true;
    }

    @Override
    public boolean a(ODamageSource var1, int var2) {
        return this.a.a(this, var1, var2);
    }

    @Override
    public boolean a_(OEntity var1) {
        return this == var1 || this.a == var1;
    }
}
