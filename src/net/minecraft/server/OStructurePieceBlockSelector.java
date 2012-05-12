package net.minecraft.server;

import java.util.Random;

public abstract class OStructurePieceBlockSelector {

   protected int a;
   protected int b;


   protected OStructurePieceBlockSelector() {
      super();
   }

   public abstract void a(Random var1, int var2, int var3, int var4, boolean var5);

   public int a() {
      return this.a;
   }

   public int b() {
      return this.b;
   }
}
