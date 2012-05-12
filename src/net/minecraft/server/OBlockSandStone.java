package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OMaterial;

public class OBlockSandStone extends OBlock {

   public OBlockSandStone(int var1) {
      super(var1, 192, OMaterial.e);
   }

   public int a(int var1, int var2) {
      return var1 != 1 && (var1 != 0 || var2 != 1 && var2 != 2)?(var1 == 0?208:(var2 == 1?229:(var2 == 2?230:192))):176;
   }

   public int a(int var1) {
      return var1 == 1?this.bN - 16:(var1 == 0?this.bN + 16:this.bN);
   }

   protected int c(int var1) {
      return var1;
   }
}
