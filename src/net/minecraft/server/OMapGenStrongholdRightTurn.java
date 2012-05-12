package net.minecraft.server;

import java.util.List;
import java.util.Random;
import net.minecraft.server.OMapGenStrongholdLeftTurn;
import net.minecraft.server.OMapGenStrongholdPieces;
import net.minecraft.server.OMapGenStrongholdStairs2;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructurePiece;
import net.minecraft.server.OWorld;

public class OMapGenStrongholdRightTurn extends OMapGenStrongholdLeftTurn {

   public OMapGenStrongholdRightTurn(int var1, Random var2, OStructureBoundingBox var3, int var4) {
      super(var1, var2, var3, var4);
   }

   public void a(OStructurePiece var1, List var2, Random var3) {
      if(this.h != 2 && this.h != 3) {
         this.b((OMapGenStrongholdStairs2)var1, var2, var3, 1, 1);
      } else {
         this.c((OMapGenStrongholdStairs2)var1, var2, var3, 1, 1);
      }

   }

   public boolean a(OWorld var1, Random var2, OStructureBoundingBox var3) {
      if(this.a(var1, var3)) {
         return false;
      } else {
         this.a(var1, var3, 0, 0, 0, 4, 4, 4, true, var2, OMapGenStrongholdPieces.b());
         this.a(var1, var2, var3, this.a, 1, 1, 0);
         if(this.h != 2 && this.h != 3) {
            this.a(var1, var3, 0, 1, 1, 0, 3, 3, 0, 0, false);
         } else {
            this.a(var1, var3, 4, 1, 1, 4, 3, 3, 0, 0, false);
         }

         return true;
      }
   }
}
