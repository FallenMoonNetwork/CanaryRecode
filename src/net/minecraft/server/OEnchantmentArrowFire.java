package net.minecraft.server;

import net.minecraft.server.OEnchantment;
import net.minecraft.server.OEnumEnchantableType;

public class OEnchantmentArrowFire extends OEnchantment {

   public OEnchantmentArrowFire(int var1, int var2) {
      super(var1, var2, OEnumEnchantableType.i);
      this.a("arrowFire");
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
