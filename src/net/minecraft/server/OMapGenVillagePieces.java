package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.server.OMapGenVillage;
import net.minecraft.server.OMapGenVillageChurch;
import net.minecraft.server.OMapGenVillageField;
import net.minecraft.server.OMapGenVillageField2;
import net.minecraft.server.OMapGenVillageHall;
import net.minecraft.server.OMapGenVillageHouse1;
import net.minecraft.server.OMapGenVillageHouse2;
import net.minecraft.server.OMapGenVillageHouse3;
import net.minecraft.server.OMapGenVillageHouse4;
import net.minecraft.server.OMapGenVillagePathGen;
import net.minecraft.server.OMapGenVillagePieceWeight;
import net.minecraft.server.OMapGenVillageStartPiece;
import net.minecraft.server.OMapGenVillageStructureGen;
import net.minecraft.server.OMapGenVillageTorch;
import net.minecraft.server.OMapGenVillageWoodHut;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructurePiece;

public class OMapGenVillagePieces {

   public OMapGenVillagePieces() {
      super();
   }

   public static ArrayList a(Random var0, int var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new OMapGenVillagePieceWeight(OMapGenVillageHouse4.class, 4, OMathHelper.a(var0, 2 + var1, 4 + var1 * 2)));
      var2.add(new OMapGenVillagePieceWeight(OMapGenVillageChurch.class, 20, OMathHelper.a(var0, 0 + var1, 1 + var1)));
      var2.add(new OMapGenVillagePieceWeight(OMapGenVillageHouse1.class, 20, OMathHelper.a(var0, 0 + var1, 2 + var1)));
      var2.add(new OMapGenVillagePieceWeight(OMapGenVillageWoodHut.class, 3, OMathHelper.a(var0, 2 + var1, 5 + var1 * 3)));
      var2.add(new OMapGenVillagePieceWeight(OMapGenVillageHall.class, 15, OMathHelper.a(var0, 0 + var1, 2 + var1)));
      var2.add(new OMapGenVillagePieceWeight(OMapGenVillageField.class, 3, OMathHelper.a(var0, 1 + var1, 4 + var1)));
      var2.add(new OMapGenVillagePieceWeight(OMapGenVillageField2.class, 3, OMathHelper.a(var0, 2 + var1, 4 + var1 * 2)));
      var2.add(new OMapGenVillagePieceWeight(OMapGenVillageHouse2.class, 15, OMathHelper.a(var0, 0, 1 + var1)));
      var2.add(new OMapGenVillagePieceWeight(OMapGenVillageHouse3.class, 8, OMathHelper.a(var0, 0 + var1, 3 + var1 * 2)));
      Iterator var3 = var2.iterator();

      while(var3.hasNext()) {
         if(((OMapGenVillagePieceWeight)var3.next()).d == 0) {
            var3.remove();
         }
      }

      return var2;
   }

   private static int a(ArrayList var0) {
      boolean var1 = false;
      int var2 = 0;

      OMapGenVillagePieceWeight var4;
      for(Iterator var3 = var0.iterator(); var3.hasNext(); var2 += var4.b) {
         var4 = (OMapGenVillagePieceWeight)var3.next();
         if(var4.d > 0 && var4.c < var4.d) {
            var1 = true;
         }
      }

      return var1?var2:-1;
   }

   private static OMapGenVillageStructureGen a(OMapGenVillagePieceWeight var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      Class var8 = var0.a;
      Object var9 = null;
      if(var8 == OMapGenVillageHouse4.class) {
         var9 = OMapGenVillageHouse4.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var8 == OMapGenVillageChurch.class) {
         var9 = OMapGenVillageChurch.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var8 == OMapGenVillageHouse1.class) {
         var9 = OMapGenVillageHouse1.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var8 == OMapGenVillageWoodHut.class) {
         var9 = OMapGenVillageWoodHut.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var8 == OMapGenVillageHall.class) {
         var9 = OMapGenVillageHall.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var8 == OMapGenVillageField.class) {
         var9 = OMapGenVillageField.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var8 == OMapGenVillageField2.class) {
         var9 = OMapGenVillageField2.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var8 == OMapGenVillageHouse2.class) {
         var9 = OMapGenVillageHouse2.a(var1, var2, var3, var4, var5, var6, var7);
      } else if(var8 == OMapGenVillageHouse3.class) {
         var9 = OMapGenVillageHouse3.a(var1, var2, var3, var4, var5, var6, var7);
      }

      return (OMapGenVillageStructureGen)var9;
   }

   private static OMapGenVillageStructureGen c(OMapGenVillageStartPiece var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      int var8 = a(var0.d);
      if(var8 <= 0) {
         return null;
      } else {
         int var9 = 0;

         while(var9 < 5) {
            ++var9;
            int var10 = var2.nextInt(var8);
            Iterator var11 = var0.d.iterator();

            while(var11.hasNext()) {
               OMapGenVillagePieceWeight var12 = (OMapGenVillagePieceWeight)var11.next();
               var10 -= var12.b;
               if(var10 < 0) {
                  if(!var12.a(var7) || var12 == var0.c && var0.d.size() > 1) {
                     break;
                  }

                  OMapGenVillageStructureGen var13 = a(var12, var1, var2, var3, var4, var5, var6, var7);
                  if(var13 != null) {
                     ++var12.c;
                     var0.c = var12;
                     if(!var12.a()) {
                        var0.d.remove(var12);
                     }

                     return var13;
                  }
               }
            }
         }

         OStructureBoundingBox var14 = OMapGenVillageTorch.a(var1, var2, var3, var4, var5, var6);
         if(var14 != null) {
            return new OMapGenVillageTorch(var7, var2, var14, var6);
         } else {
            return null;
         }
      }
   }

   private static OStructurePiece d(OMapGenVillageStartPiece var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      if(var7 > 50) {
         return null;
      } else if(Math.abs(var3 - var0.b().a) <= 112 && Math.abs(var5 - var0.b().c) <= 112) {
         OMapGenVillageStructureGen var8 = c(var0, var1, var2, var3, var4, var5, var6, var7 + 1);
         if(var8 != null) {
            int var9 = (var8.g.a + var8.g.d) / 2;
            int var10 = (var8.g.c + var8.g.f) / 2;
            int var11 = var8.g.d - var8.g.a;
            int var12 = var8.g.f - var8.g.c;
            int var13 = var11 > var12?var11:var12;
            if(var0.a().a(var9, var10, var13 / 2 + 4, OMapGenVillage.a)) {
               var1.add(var8);
               var0.e.add(var8);
               return var8;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   private static OStructurePiece e(OMapGenVillageStartPiece var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      if(var7 > 3 + var0.b) {
         return null;
      } else if(Math.abs(var3 - var0.b().a) <= 112 && Math.abs(var5 - var0.b().c) <= 112) {
         OStructureBoundingBox var8 = OMapGenVillagePathGen.a(var0, var1, var2, var3, var4, var5, var6);
         if(var8 != null && var8.b > 10) {
            OMapGenVillagePathGen var9 = new OMapGenVillagePathGen(var7, var2, var8, var6);
            int var10 = (var9.g.a + var9.g.d) / 2;
            int var11 = (var9.g.c + var9.g.f) / 2;
            int var12 = var9.g.d - var9.g.a;
            int var13 = var9.g.f - var9.g.c;
            int var14 = var12 > var13?var12:var13;
            if(var0.a().a(var10, var11, var14 / 2 + 4, OMapGenVillage.a)) {
               var1.add(var9);
               var0.f.add(var9);
               return var9;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   // $FF: synthetic method
   static OStructurePiece a(OMapGenVillageStartPiece var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      return d(var0, var1, var2, var3, var4, var5, var6, var7);
   }

   // $FF: synthetic method
   static OStructurePiece b(OMapGenVillageStartPiece var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
      return e(var0, var1, var2, var3, var4, var5, var6, var7);
   }
}
