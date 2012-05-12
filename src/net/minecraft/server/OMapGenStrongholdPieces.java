package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.server.OComponentStrongholdChestCorridor;
import net.minecraft.server.OComponentStrongholdPortalRoom;
import net.minecraft.server.OMapGenStrongholdCorridor;
import net.minecraft.server.OMapGenStrongholdCrossing;
import net.minecraft.server.OMapGenStrongholdLeftTurn;
import net.minecraft.server.OMapGenStrongholdLibrary;
import net.minecraft.server.OMapGenStrongholdPiece;
import net.minecraft.server.OMapGenStrongholdPieceWeight;
import net.minecraft.server.OMapGenStrongholdPieceWeight2;
import net.minecraft.server.OMapGenStrongholdPrison;
import net.minecraft.server.OMapGenStrongholdRightTurn;
import net.minecraft.server.OMapGenStrongholdRoomCrossing;
import net.minecraft.server.OMapGenStrongholdStairs;
import net.minecraft.server.OMapGenStrongholdStairs2;
import net.minecraft.server.OMapGenStrongholdStairsStraight;
import net.minecraft.server.OMapGenStrongholdStones;
import net.minecraft.server.OMapGenStrongholdStraight;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructurePiece;
import net.minecraft.server.OStructureStrongholdPieceWeight3;

public class OMapGenStrongholdPieces {

   private static final OMapGenStrongholdPieceWeight[] b = new OMapGenStrongholdPieceWeight[]{new OMapGenStrongholdPieceWeight(OMapGenStrongholdStraight.class, 40, 0), new OMapGenStrongholdPieceWeight(OMapGenStrongholdPrison.class, 5, 5), new OMapGenStrongholdPieceWeight(OMapGenStrongholdLeftTurn.class, 20, 0), new OMapGenStrongholdPieceWeight(OMapGenStrongholdRightTurn.class, 20, 0), new OMapGenStrongholdPieceWeight(OMapGenStrongholdRoomCrossing.class, 10, 6), new OMapGenStrongholdPieceWeight(OMapGenStrongholdStairsStraight.class, 5, 5), new OMapGenStrongholdPieceWeight(OMapGenStrongholdStairs.class, 5, 5), new OMapGenStrongholdPieceWeight(OMapGenStrongholdCrossing.class, 5, 4), new OMapGenStrongholdPieceWeight(OComponentStrongholdChestCorridor.class, 5, 4), new OMapGenStrongholdPieceWeight2(OMapGenStrongholdLibrary.class, 10, 2), new OStructureStrongholdPieceWeight3(OComponentStrongholdPortalRoom.class, 20, 1)};
   private static List c;
   private static Class d;
   static int a = 0;
   private static final OMapGenStrongholdStones e = new OMapGenStrongholdStones((OMapGenStrongholdPieceWeight2)null);


   public OMapGenStrongholdPieces() {
      super();
   }

   public static void a() {
      c = new ArrayList();
      OMapGenStrongholdPieceWeight[] var0 = b;
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         OMapGenStrongholdPieceWeight var3 = var0[var2];
         var3.c = 0;
         c.add(var3);
      }

      d = null;
   }

   private static boolean c() {
      boolean var0 = false;
      a = 0;

      OMapGenStrongholdPieceWeight var2;
      for(Iterator var1 = c.iterator(); var1.hasNext(); a += var2.b) {
         var2 = (OMapGenStrongholdPieceWeight)var1.next();
         if(var2.d > 0 && var2.c < var2.d) {
            var0 = true;
         }
      }

      return var0;
   }

   private static OMapGenStrongholdPiece a(Class var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      Object var8 = null;
      if(var0 == OMapGenStrongholdStraight.class) {
         var8 = OMapGenStrongholdStraight.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var0 == OMapGenStrongholdPrison.class) {
         var8 = OMapGenStrongholdPrison.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var0 == OMapGenStrongholdLeftTurn.class) {
         var8 = OMapGenStrongholdLeftTurn.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var0 == OMapGenStrongholdRightTurn.class) {
         var8 = OMapGenStrongholdRightTurn.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var0 == OMapGenStrongholdRoomCrossing.class) {
         var8 = OMapGenStrongholdRoomCrossing.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var0 == OMapGenStrongholdStairsStraight.class) {
         var8 = OMapGenStrongholdStairsStraight.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var0 == OMapGenStrongholdStairs.class) {
         var8 = OMapGenStrongholdStairs.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var0 == OMapGenStrongholdCrossing.class) {
         var8 = OMapGenStrongholdCrossing.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var0 == OComponentStrongholdChestCorridor.class) {
         var8 = OComponentStrongholdChestCorridor.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var0 == OMapGenStrongholdLibrary.class) {
         var8 = OMapGenStrongholdLibrary.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var0 == OComponentStrongholdPortalRoom.class) {
         var8 = OComponentStrongholdPortalRoom.a(var1, var2, var3, var4, var5, var6, var7);
      }

      return (OMapGenStrongholdPiece)var8;
   }

   private static OMapGenStrongholdPiece b(OMapGenStrongholdStairs2 var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      if(!c()) {
         return null;
      } else {
         if(d != null) {
            OMapGenStrongholdPiece var8 = a(d, var1, var2, var3, var4, var5, var6, var7);
            d = null;
            if(var8 != null) {
               return var8;
            }
         }

         int var13 = 0;

         while(var13 < 5) {
            ++var13;
            int var9 = var2.nextInt(a);
            Iterator var10 = c.iterator();

            while(var10.hasNext()) {
               OMapGenStrongholdPieceWeight var11 = (OMapGenStrongholdPieceWeight)var10.next();
               var9 -= var11.b;
               if(var9 < 0) {
                  if(!var11.a(var7) || var11 == var0.a) {
                     break;
                  }

                  OMapGenStrongholdPiece var12 = a(var11.a, var1, var2, var3, var4, var5, var6, var7);
                  if(var12 != null) {
                     ++var11.c;
                     var0.a = var11;
                     if(!var11.a()) {
                        c.remove(var11);
                     }

                     return var12;
                  }
               }
            }
         }

         OStructureBoundingBox var14 = OMapGenStrongholdCorridor.a(var1, var2, var3, var4, var5, var6);
         if(var14 != null && var14.b > 1) {
            return new OMapGenStrongholdCorridor(var7, var2, var14, var6);
         } else {
            return null;
         }
      }
   }

   private static OStructurePiece c(OMapGenStrongholdStairs2 var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      if(var7 > 50) {
         return null;
      } else if(Math.abs(var3 - var0.b().a) <= 112 && Math.abs(var5 - var0.b().c) <= 112) {
         OMapGenStrongholdPiece var8 = b(var0, var1, var2, var3, var4, var5, var6, var7 + 1);
         if(var8 != null) {
            var1.add(var8);
            var0.c.add(var8);
         }

         return var8;
      } else {
         return null;
      }
   }

   // $FF: synthetic method
   static OStructurePiece a(OMapGenStrongholdStairs2 var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      return c(var0, var1, var2, var3, var4, var5, var6, var7);
   }

   // $FF: synthetic method
   static Class a(Class var0) {
      d = var0;
      return var0;
   }

   // $FF: synthetic method
   static OMapGenStrongholdStones b() {
      return e;
   }

}
