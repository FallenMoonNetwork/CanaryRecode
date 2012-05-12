package net.minecraft.server;

import java.util.List;
import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEnumDoor;
import net.minecraft.server.OItem;
import net.minecraft.server.OMapGenStrongholdPiece;
import net.minecraft.server.OMapGenStrongholdPieces;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructurePiece;
import net.minecraft.server.OStructurePieceTreasure;
import net.minecraft.server.OWorld;

public class OMapGenStrongholdLibrary extends OMapGenStrongholdPiece {

   private static final OStructurePieceTreasure[] b = new OStructurePieceTreasure[]{new OStructurePieceTreasure(OItem.aK.bP, 0, 1, 3, 20), new OStructurePieceTreasure(OItem.aJ.bP, 0, 2, 7, 20), new OStructurePieceTreasure(OItem.bc.bP, 0, 1, 1, 1), new OStructurePieceTreasure(OItem.aP.bP, 0, 1, 1, 1)};
   protected final OEnumDoor a;
   private final boolean c;


   public OMapGenStrongholdLibrary(int var1, Random var2, OStructureBoundingBox var3, int var4) {
      super(var1);
      this.h = var4;
      this.a = this.a(var2);
      this.g = var3;
      this.c = var3.c() > 6;
   }

   public void a(OStructurePiece var1, List var2, Random var3) {}

   public static OMapGenStrongholdLibrary a(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
      OStructureBoundingBox var7 = OStructureBoundingBox.a(var2, var3, var4, -4, -1, 0, 14, 11, 15, var5);
      if(!a(var7) || OStructurePiece.a(var0, var7) != null) {
         var7 = OStructureBoundingBox.a(var2, var3, var4, -4, -1, 0, 14, 6, 15, var5);
         if(!a(var7) || OStructurePiece.a(var0, var7) != null) {
            return null;
         }
      }

      return new OMapGenStrongholdLibrary(var6, var1, var7, var5);
   }

   public boolean a(OWorld var1, Random var2, OStructureBoundingBox var3) {
      if(this.a(var1, var3)) {
         return false;
      } else {
         byte var4 = 11;
         if(!this.c) {
            var4 = 6;
         }

         this.a(var1, var3, 0, 0, 0, 13, var4 - 1, 14, true, var2, OMapGenStrongholdPieces.b());
         this.a(var1, var2, var3, this.a, 4, 1, 0);
         this.a(var1, var3, var2, 0.07F, 2, 1, 1, 11, 4, 13, OBlock.W.bO, OBlock.W.bO, false);

         int var5;
         for(var5 = 1; var5 <= 13; ++var5) {
            if((var5 - 1) % 4 == 0) {
               this.a(var1, var3, 1, 1, var5, 1, 4, var5, OBlock.x.bO, OBlock.x.bO, false);
               this.a(var1, var3, 12, 1, var5, 12, 4, var5, OBlock.x.bO, OBlock.x.bO, false);
               this.a(var1, OBlock.aq.bO, 0, 2, 3, var5, var3);
               this.a(var1, OBlock.aq.bO, 0, 11, 3, var5, var3);
               if(this.c) {
                  this.a(var1, var3, 1, 6, var5, 1, 9, var5, OBlock.x.bO, OBlock.x.bO, false);
                  this.a(var1, var3, 12, 6, var5, 12, 9, var5, OBlock.x.bO, OBlock.x.bO, false);
               }
            } else {
               this.a(var1, var3, 1, 1, var5, 1, 4, var5, OBlock.an.bO, OBlock.an.bO, false);
               this.a(var1, var3, 12, 1, var5, 12, 4, var5, OBlock.an.bO, OBlock.an.bO, false);
               if(this.c) {
                  this.a(var1, var3, 1, 6, var5, 1, 9, var5, OBlock.an.bO, OBlock.an.bO, false);
                  this.a(var1, var3, 12, 6, var5, 12, 9, var5, OBlock.an.bO, OBlock.an.bO, false);
               }
            }
         }

         for(var5 = 3; var5 < 12; var5 += 2) {
            this.a(var1, var3, 3, 1, var5, 4, 3, var5, OBlock.an.bO, OBlock.an.bO, false);
            this.a(var1, var3, 6, 1, var5, 7, 3, var5, OBlock.an.bO, OBlock.an.bO, false);
            this.a(var1, var3, 9, 1, var5, 10, 3, var5, OBlock.an.bO, OBlock.an.bO, false);
         }

         if(this.c) {
            this.a(var1, var3, 1, 5, 1, 3, 5, 13, OBlock.x.bO, OBlock.x.bO, false);
            this.a(var1, var3, 10, 5, 1, 12, 5, 13, OBlock.x.bO, OBlock.x.bO, false);
            this.a(var1, var3, 4, 5, 1, 9, 5, 2, OBlock.x.bO, OBlock.x.bO, false);
            this.a(var1, var3, 4, 5, 12, 9, 5, 13, OBlock.x.bO, OBlock.x.bO, false);
            this.a(var1, OBlock.x.bO, 0, 9, 5, 11, var3);
            this.a(var1, OBlock.x.bO, 0, 8, 5, 11, var3);
            this.a(var1, OBlock.x.bO, 0, 9, 5, 10, var3);
            this.a(var1, var3, 3, 6, 2, 3, 6, 12, OBlock.aZ.bO, OBlock.aZ.bO, false);
            this.a(var1, var3, 10, 6, 2, 10, 6, 10, OBlock.aZ.bO, OBlock.aZ.bO, false);
            this.a(var1, var3, 4, 6, 2, 9, 6, 2, OBlock.aZ.bO, OBlock.aZ.bO, false);
            this.a(var1, var3, 4, 6, 12, 8, 6, 12, OBlock.aZ.bO, OBlock.aZ.bO, false);
            this.a(var1, OBlock.aZ.bO, 0, 9, 6, 11, var3);
            this.a(var1, OBlock.aZ.bO, 0, 8, 6, 11, var3);
            this.a(var1, OBlock.aZ.bO, 0, 9, 6, 10, var3);
            var5 = this.c(OBlock.aF.bO, 3);
            this.a(var1, OBlock.aF.bO, var5, 10, 1, 13, var3);
            this.a(var1, OBlock.aF.bO, var5, 10, 2, 13, var3);
            this.a(var1, OBlock.aF.bO, var5, 10, 3, 13, var3);
            this.a(var1, OBlock.aF.bO, var5, 10, 4, 13, var3);
            this.a(var1, OBlock.aF.bO, var5, 10, 5, 13, var3);
            this.a(var1, OBlock.aF.bO, var5, 10, 6, 13, var3);
            this.a(var1, OBlock.aF.bO, var5, 10, 7, 13, var3);
            byte var6 = 7;
            byte var7 = 7;
            this.a(var1, OBlock.aZ.bO, 0, var6 - 1, 9, var7, var3);
            this.a(var1, OBlock.aZ.bO, 0, var6, 9, var7, var3);
            this.a(var1, OBlock.aZ.bO, 0, var6 - 1, 8, var7, var3);
            this.a(var1, OBlock.aZ.bO, 0, var6, 8, var7, var3);
            this.a(var1, OBlock.aZ.bO, 0, var6 - 1, 7, var7, var3);
            this.a(var1, OBlock.aZ.bO, 0, var6, 7, var7, var3);
            this.a(var1, OBlock.aZ.bO, 0, var6 - 2, 7, var7, var3);
            this.a(var1, OBlock.aZ.bO, 0, var6 + 1, 7, var7, var3);
            this.a(var1, OBlock.aZ.bO, 0, var6 - 1, 7, var7 - 1, var3);
            this.a(var1, OBlock.aZ.bO, 0, var6 - 1, 7, var7 + 1, var3);
            this.a(var1, OBlock.aZ.bO, 0, var6, 7, var7 - 1, var3);
            this.a(var1, OBlock.aZ.bO, 0, var6, 7, var7 + 1, var3);
            this.a(var1, OBlock.aq.bO, 0, var6 - 2, 8, var7, var3);
            this.a(var1, OBlock.aq.bO, 0, var6 + 1, 8, var7, var3);
            this.a(var1, OBlock.aq.bO, 0, var6 - 1, 8, var7 - 1, var3);
            this.a(var1, OBlock.aq.bO, 0, var6 - 1, 8, var7 + 1, var3);
            this.a(var1, OBlock.aq.bO, 0, var6, 8, var7 - 1, var3);
            this.a(var1, OBlock.aq.bO, 0, var6, 8, var7 + 1, var3);
         }

         this.a(var1, var3, var2, 3, 3, 5, b, 1 + var2.nextInt(4));
         if(this.c) {
            this.a(var1, 0, 0, 12, 9, 1, var3);
            this.a(var1, var3, var2, 12, 8, 1, b, 1 + var2.nextInt(4));
         }

         return true;
      }
   }

}
