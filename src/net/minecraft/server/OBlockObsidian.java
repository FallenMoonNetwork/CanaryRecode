package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockStone;

public class OBlockObsidian extends OBlockStone {

   public OBlockObsidian(int var1, int var2) {
      super(var1, var2);
   }

   public int a(Random var1) {
      return 1;
   }

   public int a(int var1, Random var2, int var3) {
      return OBlock.ap.bO;
   }
}
