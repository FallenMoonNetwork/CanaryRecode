package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityThrowable;
import net.minecraft.server.OItem;
import net.minecraft.server.OMovingObjectPosition;
import net.minecraft.server.OPotion;
import net.minecraft.server.OPotionEffect;
import net.minecraft.server.OWorld;

public class OEntityPotion extends OEntityThrowable {

    private int d;

    public OEntityPotion(OWorld var1) {
        super(var1);
    }

    public OEntityPotion(OWorld var1, OEntityLiving var2, int var3) {
        super(var1, var2);
        this.d = var3;
    }

    public OEntityPotion(OWorld var1, double var2, double var4, double var6, int var8) {
        super(var1, var2, var4, var6);
        this.d = var8;
    }

    @Override
    protected float e() {
        return 0.05F;
    }

    @Override
    protected float c() {
        return 0.5F;
    }

    @Override
    protected float d() {
        return -20.0F;
    }

    public int f() {
        return this.d;
    }

    @Override
    protected void a(OMovingObjectPosition var1) {
        if (!this.bi.F) {
            List var2 = OItem.br.b(this.d);
            if (var2 != null && !var2.isEmpty()) {
                OAxisAlignedBB var3 = this.bw.b(4.0D, 2.0D, 4.0D);
                List var4 = this.bi.a(OEntityLiving.class, var3);
                if (var4 != null && !var4.isEmpty()) {
                    Iterator var5 = var4.iterator();

                    while (var5.hasNext()) {
                        OEntity var6 = (OEntity) var5.next();
                        double var7 = this.j(var6);
                        if (var7 < 16.0D) {
                            double var9 = 1.0D - Math.sqrt(var7) / 4.0D;
                            if (var6 == var1.g) {
                                var9 = 1.0D;
                            }

                            Iterator var11 = var2.iterator();

                            while (var11.hasNext()) {
                                OPotionEffect var12 = (OPotionEffect) var11.next();
                                int var13 = var12.a();
                                if (OPotion.a[var13].b()) {
                                    OPotion.a[var13].a(this.c, (OEntityLiving) var6, var12.c(), var9);
                                } else {
                                    int var14 = (int) (var9 * var12.b() + 0.5D);
                                    if (var14 > 20) {
                                        ((OEntityLiving) var6).e(new OPotionEffect(var13, var14, var12.c()));
                                    }
                                }
                            }
                        }
                    }
                }
            }

            this.bi.f(2002, (int) Math.round(this.bm), (int) Math.round(this.bn), (int) Math.round(this.bo), this.d);
            this.X();
        }

    }
}
