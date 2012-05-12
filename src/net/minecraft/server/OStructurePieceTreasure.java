package net.minecraft.server;

import net.minecraft.server.OWeightedRandomChoice;

public class OStructurePieceTreasure extends OWeightedRandomChoice {

   public int a;
   public int b;
   public int c;
   public int e;


   public OStructurePieceTreasure(int var1, int var2, int var3, int var4, int var5) {
      super(var5);
      this.a = var1;
      this.b = var2;
      this.c = var3;
      this.e = var4;
   }
}
