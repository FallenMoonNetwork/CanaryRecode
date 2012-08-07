package net.minecraft.server;


import net.canarymod.api.entity.CanaryCaveSpider;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntitySpider;
import net.minecraft.server.OPotion;
import net.minecraft.server.OPotionEffect;
import net.minecraft.server.OWorld;


public class OEntityCaveSpider extends OEntitySpider {

    // CanaryMod CaveSpider handler
    private CanaryCaveSpider canaryCaveSpider;
    
    public OEntityCaveSpider(OWorld var1) {
        super(var1);
        this.ae = "/mob/cavespider.png";
        this.b(0.7F, 0.5F);
        canaryCaveSpider = new CanaryCaveSpider(this);
    }

    /**
     * CanaryMod get CaveSpider handler
     * @return
     */
    public CanaryCaveSpider getCanaryCaveSpider() {
        return canaryCaveSpider;
    }
    
    @Override
    public int d() {
        return 12;
    }

    @Override
    public boolean a(OEntity var1) {
        if (super.a(var1)) {
            if (var1 instanceof OEntityLiving) {
                byte var2 = 0;

                if (this.bi.q > 1) {
                    if (this.bi.q == 2) {
                        var2 = 7;
                    } else if (this.bi.q == 3) {
                        var2 = 15;
                    }
                }

                if (var2 > 0) {
                    ((OEntityLiving) var1).e(new OPotionEffect(OPotion.u.H, var2 * 20, 0));
                }
            }

            return true;
        } else {
            return false;
        }
    }
}
