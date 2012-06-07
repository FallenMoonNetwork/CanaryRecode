package net.minecraft.server;

import net.minecraft.server.OEntityAISit;
import net.minecraft.server.OEntityAnimal;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OWorld;

public abstract class OEntityTameable extends OEntityAnimal {

    protected OEntityAISit a = new OEntityAISit(this);

    public OEntityTameable(OWorld var1) {
        super(var1);
    }

    @Override
    protected void b() {
        super.b();
        this.bY.a(16, Byte.valueOf((byte) 0));
        this.bY.a(17, "");
    }

    @Override
    public void b(ONBTTagCompound var1) {
        super.b(var1);
        if (this.A() == null) {
            var1.a("Owner", "");
        } else {
            var1.a("Owner", this.A());
        }

        var1.a("Sitting", this.v_());
    }

    @Override
    public void a(ONBTTagCompound var1) {
        super.a(var1);
        String var2 = var1.j("Owner");
        if (var2.length() > 0) {
            this.a(var2);
            this.b(true);
        }

        this.a.a(var1.o("Sitting"));
    }

    protected void a(boolean var1) {
        String var2 = "heart";
        if (!var1) {
            var2 = "smoke";
        }

        for (int var3 = 0; var3 < 7; ++var3) {
            double var4 = this.bS.nextGaussian() * 0.02D;
            double var6 = this.bS.nextGaussian() * 0.02D;
            double var8 = this.bS.nextGaussian() * 0.02D;
            this.bi.a(var2, this.bm + (this.bS.nextFloat() * this.bG * 2.0F) - this.bG, this.bn + 0.5D + (this.bS.nextFloat() * this.bH), this.bo + (this.bS.nextFloat() * this.bG * 2.0F) - this.bG, var4, var6, var8);
        }

    }

    public boolean u_() {
        return (this.bY.a(16) & 4) != 0;
    }

    public void b(boolean var1) {
        byte var2 = this.bY.a(16);
        if (var1) {
            this.bY.b(16, Byte.valueOf((byte) (var2 | 4)));
        } else {
            this.bY.b(16, Byte.valueOf((byte) (var2 & -5)));
        }

    }

    public boolean v_() {
        return (this.bY.a(16) & 1) != 0;
    }

    public void c(boolean var1) {
        byte var2 = this.bY.a(16);
        if (var1) {
            this.bY.b(16, Byte.valueOf((byte) (var2 | 1)));
        } else {
            this.bY.b(16, Byte.valueOf((byte) (var2 & -2)));
        }

    }

    public String A() {
        return this.bY.d(17);
    }

    public void a(String var1) {
        this.bY.b(17, var1);
    }

    public OEntityLiving w_() {
        return this.bi.a(this.A());
    }

    public OEntityAISit C() {
        return this.a;
    }
}
