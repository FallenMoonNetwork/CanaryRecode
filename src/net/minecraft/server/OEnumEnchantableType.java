package net.minecraft.server;

import net.minecraft.server.OItem;
import net.minecraft.server.OItemArmor;
import net.minecraft.server.OItemBow;
import net.minecraft.server.OItemSword;
import net.minecraft.server.OItemTool;

public enum OEnumEnchantableType {

   a("all", 0),
   b("armor", 1),
   c("armor_feet", 2),
   d("armor_legs", 3),
   e("armor_torso", 4),
   f("armor_head", 5),
   g("weapon", 6),
   h("digger", 7),
   i("bow", 8);
   // $FF: synthetic field
   private static final OEnumEnchantableType[] j = new OEnumEnchantableType[]{a, b, c, d, e, f, g, h, i};


   private OEnumEnchantableType(String var1, int var2) {}

   public boolean a(OItem var1) {
      if(this == a) {
         return true;
      } else if(var1 instanceof OItemArmor) {
         if(this == b) {
            return true;
         } else {
            OItemArmor var2 = (OItemArmor)var1;
            return var2.a == 0?this == f:(var2.a == 2?this == d:(var2.a == 1?this == e:(var2.a == 3?this == c:false)));
         }
      } else {
         return var1 instanceof OItemSword?this == g:(var1 instanceof OItemTool?this == h:(var1 instanceof OItemBow?this == i:false));
      }
   }

}
