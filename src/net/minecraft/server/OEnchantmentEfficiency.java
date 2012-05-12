package net.minecraft.server;

import net.minecraft.server.OEnchantment;
import net.minecraft.server.OEnumEnchantableType;

public class OEnchantmentEfficiency extends OEnchantment {

   protected OEnchantmentEfficiency(int var1, int var2) {
      super(var1, var2, OEnumEnchantableType.h);
      this.a("digging");
   }

   public int a(int var1) {
      return 1 + 15 * (var1 - 1);
   }

   public int b(int var1) {
      return super.a(var1) + 50;
   }

   public int a() {
      return 5;
   }
}
