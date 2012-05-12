package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.server.OComponentNetherBridgeStartPiece;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructureNetherBridgeEnd;
import net.minecraft.server.OStructureNetherBridgePieceWeight;
import net.minecraft.server.OStructureNetherBridgePieces;
import net.minecraft.server.OStructurePiece;

abstract class OComponentNetherBridgePiece extends OStructurePiece {

   protected OComponentNetherBridgePiece(int var1) {
      super(var1);
   }

   private int a(List var1) {
      boolean var2 = false;
      int var3 = 0;

      OStructureNetherBridgePieceWeight var5;
      for(Iterator var4 = var1.iterator(); var4.hasNext(); var3 += var5.b) {
         var5 = (OStructureNetherBridgePieceWeight)var4.next();
         if(var5.d > 0 && var5.c < var5.d) {
            var2 = true;
         }
      }

      return var2?var3:-1;
   }

   private OComponentNetherBridgePiece a(OComponentNetherBridgeStartPiece var1, List var2, List var3, Random var4, int var5, int var6, int var7, int var8, int var9) {
      int var10 = this.a(var2);
      boolean var11 = var10 > 0 && var9 <= 30;
      int var12 = 0;

      while(var12 < 5 && var11) {
         ++var12;
         int var13 = var4.nextInt(var10);
         Iterator var14 = var2.iterator();

         while(var14.hasNext()) {
            OStructureNetherBridgePieceWeight var15 = (OStructureNetherBridgePieceWeight)var14.next();
            var13 -= var15.b;
            if(var13 < 0) {
               if(!var15.a(var9) || var15 == var1.a && !var15.e) {
                  break;
               }

               OComponentNetherBridgePiece var16 = OStructureNetherBridgePieces.a(var15, var3, var4, var5, var6, var7, var8, var9);
               if(var16 != null) {
                  ++var15.c;
                  var1.a = var15;
                  if(!var15.a()) {
                     var2.remove(var15);
                  }

                  return var16;
               }
            }
         }
      }

      OStructureNetherBridgeEnd var17 = OStructureNetherBridgeEnd.a(var3, var4, var5, var6, var7, var8, var9);
      return var17;
   }

   private OStructurePiece a(OComponentNetherBridgeStartPiece var1, List var2, Random var3, int var4, int var5, int var6, int var7, int var8, boolean var9) {
      if(Math.abs(var4 - var1.b().a) <= 112 && Math.abs(var6 - var1.b().c) <= 112) {
         List var12 = var1.b;
         if(var9) {
            var12 = var1.c;
         }

         OComponentNetherBridgePiece var11 = this.a(var1, var12, var2, var3, var4, var5, var6, var7, var8 + 1);
         if(var11 != null) {
            var2.add(var11);
            var1.d.add(var11);
         }

         return var11;
      } else {
         OStructureNetherBridgeEnd var10 = OStructureNetherBridgeEnd.a(var2, var3, var4, var5, var6, var7, var8);
         return var10;
      }
   }

   protected OStructurePiece a(OComponentNetherBridgeStartPiece var1, List var2, Random var3, int var4, int var5, boolean var6) {
      switch(this.h) {
      case 0:
         return this.a(var1, var2, var3, this.g.a + var4, this.g.b + var5, this.g.f + 1, this.h, this.c(), var6);
      case 1:
         return this.a(var1, var2, var3, this.g.a - 1, this.g.b + var5, this.g.c + var4, this.h, this.c(), var6);
      case 2:
         return this.a(var1, var2, var3, this.g.a + var4, this.g.b + var5, this.g.c - 1, this.h, this.c(), var6);
      case 3:
         return this.a(var1, var2, var3, this.g.d + 1, this.g.b + var5, this.g.c + var4, this.h, this.c(), var6);
      default:
         return null;
      }
   }

   protected OStructurePiece b(OComponentNetherBridgeStartPiece var1, List var2, Random var3, int var4, int var5, boolean var6) {
      switch(this.h) {
      case 0:
         return this.a(var1, var2, var3, this.g.a - 1, this.g.b + var4, this.g.c + var5, 1, this.c(), var6);
      case 1:
         return this.a(var1, var2, var3, this.g.a + var5, this.g.b + var4, this.g.c - 1, 2, this.c(), var6);
      case 2:
         return this.a(var1, var2, var3, this.g.a - 1, this.g.b + var4, this.g.c + var5, 1, this.c(), var6);
      case 3:
         return this.a(var1, var2, var3, this.g.a + var5, this.g.b + var4, this.g.c - 1, 2, this.c(), var6);
      default:
         return null;
      }
   }

   protected OStructurePiece c(OComponentNetherBridgeStartPiece var1, List var2, Random var3, int var4, int var5, boolean var6) {
      switch(this.h) {
      case 0:
         return this.a(var1, var2, var3, this.g.d + 1, this.g.b + var4, this.g.c + var5, 3, this.c(), var6);
      case 1:
         return this.a(var1, var2, var3, this.g.a + var5, this.g.b + var4, this.g.f + 1, 0, this.c(), var6);
      case 2:
         return this.a(var1, var2, var3, this.g.d + 1, this.g.b + var4, this.g.c + var5, 3, this.c(), var6);
      case 3:
         return this.a(var1, var2, var3, this.g.a + var5, this.g.b + var4, this.g.f + 1, 0, this.c(), var6);
      default:
         return null;
      }
   }

   protected static boolean a(OStructureBoundingBox var0) {
      return var0 != null && var0.b > 10;
   }
}
