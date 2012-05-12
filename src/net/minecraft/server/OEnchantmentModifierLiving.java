package net.minecraft.server;

import net.minecraft.server.OEmpty1;
import net.minecraft.server.OEnchantment;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OIEnchantmentModifier;

final class OEnchantmentModifierLiving implements OIEnchantmentModifier {

   public int a;
   public OEntityLiving b;


   private OEnchantmentModifierLiving() {
      super();
   }

   public void a(OEnchantment var1, int var2) {
      this.a += var1.a(var2, this.b);
   }

   // $FF: synthetic method
   OEnchantmentModifierLiving(OEmpty1 var1) {
      this();
   }
}
