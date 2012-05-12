package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OMaterial;

public class OBlockStoneBrick extends OBlock {

   public OBlockStoneBrick(int var1) {
      super(var1, 54, OMaterial.e);
   }

   public int a(int var1, int var2) {
      switch(var2) {
      case 1:
         return 100;
      case 2:
         return 101;
      case 3:
         return 213;
      default:
         return 54;
      }
   }

   protected int c(int var1) {
      return var1;
   }
}
