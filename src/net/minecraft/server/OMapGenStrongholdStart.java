package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.server.OMapGenStrongholdPieces;
import net.minecraft.server.OMapGenStrongholdStairs2;
import net.minecraft.server.OStructurePiece;
import net.minecraft.server.OStructureStart;
import net.minecraft.server.OWorld;

class OMapGenStrongholdStart extends OStructureStart {

   public OMapGenStrongholdStart(OWorld var1, Random var2, int var3, int var4) {
      super();
      OMapGenStrongholdPieces.a();
      OMapGenStrongholdStairs2 var5 = new OMapGenStrongholdStairs2(0, var2, (var3 << 4) + 2, (var4 << 4) + 2);
      this.a.add(var5);
      var5.a(var5, this.a, var2);
      ArrayList var6 = var5.c;

      while(!var6.isEmpty()) {
         int var7 = var2.nextInt(var6.size());
         OStructurePiece var8 = (OStructurePiece)var6.remove(var7);
         var8.a((OStructurePiece)var5, (List)this.a, var2);
      }

      this.d();
      this.a(var1, var2, 10);
   }
}
