package net.minecraft.server;

import net.minecraft.server.OEnchantment;
import net.minecraft.server.OEnumEnchantableType;

public class OEnchantmentArrowKnockback extends OEnchantment {

   public OEnchantmentArrowKnockback(int var1, int var2) {
      super(var1, var2, OEnumEnchantableType.i);
      this.a("arrowKnockback");
   }

   public int a(int var1) {
      return 12 + (var1 - 1) * 20;
   }

   public int b(int var1) {
      return this.a(var1) + 25;
   }

   public int a() {
      return 2;
   }
}
