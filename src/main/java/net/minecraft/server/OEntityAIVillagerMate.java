package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityVillager;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OVillage;
import net.minecraft.server.OWorld;

public class OEntityAIVillagerMate extends OEntityAIBase {

    private OEntityVillager b;
    private OEntityVillager c;
    private OWorld d;
    private int e = 0;
    OVillage a;

    public OEntityAIVillagerMate(OEntityVillager var1) {
        super();
        this.b = var1;
        this.d = var1.bi;
        this.a(3);
    }

    @Override
    public boolean a() {
        if (this.b.K() != 0) {
            return false;
        } else if (this.b.an().nextInt(500) != 0) {
            return false;
        } else {
            this.a = this.d.A.a(OMathHelper.b(this.b.bm), OMathHelper.b(this.b.bn), OMathHelper.b(this.b.bo), 0);
            if (this.a == null) {
                return false;
            } else if (!this.f()) {
                return false;
            } else {
                OEntity var1 = this.d.a(OEntityVillager.class, this.b.bw.b(8.0D, 3.0D, 8.0D), this.b);
                if (var1 == null) {
                    return false;
                } else {
                    this.c = (OEntityVillager) var1;
                    return this.c.K() == 0;
                }
            }
        }
    }

    @Override
    public void c() {
        this.e = 300;
        this.b.a(true);
    }

    @Override
    public void d() {
        this.a = null;
        this.c = null;
        this.b.a(false);
    }

    @Override
    public boolean b() {
        return this.e >= 0 && this.f() && this.b.K() == 0;
    }

    @Override
    public void e() {
        --this.e;
        this.b.ai().a(this.c, 10.0F, 30.0F);
        if (this.b.j(this.c) > 2.25D) {
            this.b.al().a(this.c, 0.25F);
        } else if (this.e == 0 && this.c.A()) {
            this.i();
        }

        if (this.b.an().nextInt(35) == 0) {
            this.a(this.b);
        }

    }

    private boolean f() {
      int var1 = (int)((double)a.c() * 0.35D);
      return this.a.e() < var1;
   }

    private void i() {
        OEntityVillager var1 = new OEntityVillager(this.d);
        this.c.c(6000);
        this.b.c(6000);
        var1.c(-24000);
        var1.f_(this.b.an().nextInt(5));
        var1.c(this.b.bm, this.b.bn, this.b.bo, 0.0F, 0.0F);
        this.d.b(var1);
        this.a(var1);
    }

    private void a(OEntityLiving var1) {
        Random var2 = var1.an();

        for (int var3 = 0; var3 < 5; ++var3) {
            double var4 = var2.nextGaussian() * 0.02D;
            double var6 = var2.nextGaussian() * 0.02D;
            double var8 = var2.nextGaussian() * 0.02D;
            this.d.a("heart", var1.bm + (var2.nextFloat() * var1.bG * 2.0F) - var1.bG, var1.bn + 1.0D + (var2.nextFloat() * var1.bH), var1.bo + (var2.nextFloat() * var1.bG * 2.0F) - var1.bG, var4, var6, var8);
        }

    }
}
