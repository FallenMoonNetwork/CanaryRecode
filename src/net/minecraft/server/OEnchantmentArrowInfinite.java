package net.minecraft.server;

import net.minecraft.server.OEnchantment;
import net.minecraft.server.OEnumEnchantableType;

public class OEnchantmentArrowInfinite extends OEnchantment {

   public OEnchantmentArrowInfinite(int var1, int var2) {
      super(var1, var2, OEnumEnchantableType.i);
      this.a("arrowInfinite");
   }

   public int a(int var1) {
      return 20;
   }

   public int b(int var1) {
      return 50;
   }

   public int a() {
      return 1;
   }
}
