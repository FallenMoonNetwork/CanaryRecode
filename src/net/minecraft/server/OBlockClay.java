package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OItem;
import net.minecraft.server.OMaterial;

public class OBlockClay extends OBlock {

   public OBlockClay(int var1, int var2) {
      super(var1, var2, OMaterial.y);
   }

   public int a(int var1, Random var2, int var3) {
      return OItem.aH.bP;
   }

   public int a(Random var1) {
      return 4;
   }
}
