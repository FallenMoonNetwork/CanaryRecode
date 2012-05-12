package net.minecraft.server;

import net.minecraft.server.OMapGenStrongholdPieceWeight;

final class OStructureStrongholdPieceWeight3 extends OMapGenStrongholdPieceWeight {

   OStructureStrongholdPieceWeight3(Class var1, int var2, int var3) {
      super(var1, var2, var3);
   }

   public boolean a(int var1) {
      return super.a(var1) && var1 > 5;
   }
}
