package net.minecraft.server;

import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntitySpider;
import net.minecraft.server.OPotion;
import net.minecraft.server.OPotionEffect;
import net.minecraft.server.OWorld;

public class OEntityCaveSpider extends OEntitySpider {

   public OEntityCaveSpider(OWorld var1) {
      super(var1);
      this.ae = "/mob/cavespider.png";
      this.b(0.7F, 0.5F);
   }

   public int d() {
      return 12;
   }

   public boolean a(OEntity var1) {
      if(super.a(var1)) {
         if(var1 instanceof OEntityLiving) {
            byte var2 = 0;
            if(this.bi.q > 1) {
               if(this.bi.q == 2) {
                  var2 = 7;
               } else if(this.bi.q == 3) {
                  var2 = 15;
               }
            }

            if(var2 > 0) {
               ((OEntityLiving)var1).e(new OPotionEffect(OPotion.u.H, var2 * 20, 0));
            }
         }

         return true;
      } else {
         return false;
      }
   }
}
