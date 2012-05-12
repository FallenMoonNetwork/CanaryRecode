package net.minecraft.server;

import java.util.List;
import java.util.Random;
import net.minecraft.server.OComponentNetherBridgeCorridor;
import net.minecraft.server.OComponentNetherBridgeCorridor2;
import net.minecraft.server.OComponentNetherBridgeCorridor3;
import net.minecraft.server.OComponentNetherBridgeCorridor4;
import net.minecraft.server.OComponentNetherBridgeCorridor5;
import net.minecraft.server.OComponentNetherBridgeCrossing;
import net.minecraft.server.OComponentNetherBridgeCrossing2;
import net.minecraft.server.OComponentNetherBridgeCrossing3;
import net.minecraft.server.OComponentNetherBridgeEntrance;
import net.minecraft.server.OComponentNetherBridgeNetherStalkRoom;
import net.minecraft.server.OComponentNetherBridgePiece;
import net.minecraft.server.OComponentNetherBridgeStairs;
import net.minecraft.server.OComponentNetherBridgeStraight;
import net.minecraft.server.OComponentNetherBridgeThrone;
import net.minecraft.server.OStructureNetherBridgePieceWeight;

public class OStructureNetherBridgePieces {

   private static final OStructureNetherBridgePieceWeight[] a = new OStructureNetherBridgePieceWeight[]{new OStructureNetherBridgePieceWeight(OComponentNetherBridgeStraight.class, 30, 0, true), new OStructureNetherBridgePieceWeight(OComponentNetherBridgeCrossing3.class, 10, 4), new OStructureNetherBridgePieceWeight(OComponentNetherBridgeCrossing.class, 10, 4), new OStructureNetherBridgePieceWeight(OComponentNetherBridgeStairs.class, 10, 3), new OStructureNetherBridgePieceWeight(OComponentNetherBridgeThrone.class, 5, 2), new OStructureNetherBridgePieceWeight(OComponentNetherBridgeEntrance.class, 5, 1)};
   private static final OStructureNetherBridgePieceWeight[] b = new OStructureNetherBridgePieceWeight[]{new OStructureNetherBridgePieceWeight(OComponentNetherBridgeCorridor5.class, 25, 0, true), new OStructureNetherBridgePieceWeight(OComponentNetherBridgeCrossing2.class, 15, 5), new OStructureNetherBridgePieceWeight(OComponentNetherBridgeCorridor2.class, 5, 10), new OStructureNetherBridgePieceWeight(OComponentNetherBridgeCorridor.class, 5, 10), new OStructureNetherBridgePieceWeight(OComponentNetherBridgeCorridor3.class, 10, 3, true), new OStructureNetherBridgePieceWeight(OComponentNetherBridgeCorridor4.class, 7, 2), new OStructureNetherBridgePieceWeight(OComponentNetherBridgeNetherStalkRoom.class, 5, 2)};


   public OStructureNetherBridgePieces() {
      super();
   }

   private static OComponentNetherBridgePiece b(OStructureNetherBridgePieceWeight var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      Class var8 = var0.a;
      Object var9 = null;
      if(var8 == OComponentNetherBridgeStraight.class) {
         var9 = OComponentNetherBridgeStraight.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var8 == OComponentNetherBridgeCrossing3.class) {
         var9 = OComponentNetherBridgeCrossing3.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var8 == OComponentNetherBridgeCrossing.class) {
         var9 = OComponentNetherBridgeCrossing.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var8 == OComponentNetherBridgeStairs.class) {
         var9 = OComponentNetherBridgeStairs.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var8 == OComponentNetherBridgeThrone.class) {
         var9 = OComponentNetherBridgeThrone.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var8 == OComponentNetherBridgeEntrance.class) {
         var9 = OComponentNetherBridgeEntrance.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var8 == OComponentNetherBridgeCorridor5.class) {
         var9 = OComponentNetherBridgeCorridor5.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var8 == OComponentNetherBridgeCorridor2.class) {
         var9 = OComponentNetherBridgeCorridor2.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var8 == OComponentNetherBridgeCorridor.class) {
         var9 = OComponentNetherBridgeCorridor.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var8 == OComponentNetherBridgeCorridor3.class) {
         var9 = OComponentNetherBridgeCorridor3.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var8 == OComponentNetherBridgeCorridor4.class) {
         var9 = OComponentNetherBridgeCorridor4.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var8 == OComponentNetherBridgeCrossing2.class) {
         var9 = OComponentNetherBridgeCrossing2.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var8 == OComponentNetherBridgeNetherStalkRoom.class) {
         var9 = OComponentNetherBridgeNetherStalkRoom.a(var1, var2, var3, var4, var5, var6, var7);
      }

      return (OComponentNetherBridgePiece)var9;
   }

   // $FF: synthetic method
   static OComponentNetherBridgePiece a(OStructureNetherBridgePieceWeight var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      return b(var0, var1, var2, var3, var4, var5, var6, var7);
   }

   // $FF: synthetic method
   static OStructureNetherBridgePieceWeight[] a() {
      return a;
   }

   // $FF: synthetic method
   static OStructureNetherBridgePieceWeight[] b() {
      return b;
   }

}
