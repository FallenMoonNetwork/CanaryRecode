package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.server.OMapGenVillagePieces;
import net.minecraft.server.OMapGenVillageRoadPiece;
import net.minecraft.server.OMapGenVillageStartPiece;
import net.minecraft.server.OStructurePiece;
import net.minecraft.server.OStructureStart;
import net.minecraft.server.OWorld;

class OMapGenVillageStart extends OStructureStart {

   private boolean c = false;


   public OMapGenVillageStart(OWorld var1, Random var2, int var3, int var4, int var5) {
      super();
      ArrayList var7 = OMapGenVillagePieces.a(var2, var5);
      OMapGenVillageStartPiece var8 = new OMapGenVillageStartPiece(var1.a(), 0, var2, (var3 << 4) + 2, (var4 << 4) + 2, var7, var5);
      this.a.add(var8);
      var8.a(var8, this.a, var2);
      ArrayList var9 = var8.f;
      ArrayList var10 = var8.e;

      int var11;
      while(!var9.isEmpty() || !var10.isEmpty()) {
         OStructurePiece var12;
         if(!var9.isEmpty()) {
            var11 = var2.nextInt(var9.size());
            var12 = (OStructurePiece)var9.remove(var11);
            var12.a((OStructurePiece)var8, (List)this.a, var2);
         } else {
            var11 = var2.nextInt(var10.size());
            var12 = (OStructurePiece)var10.remove(var11);
            var12.a((OStructurePiece)var8, (List)this.a, var2);
         }
      }

      this.d();
      var11 = 0;
      Iterator var14 = this.a.iterator();

      while(var14.hasNext()) {
         OStructurePiece var13 = (OStructurePiece)var14.next();
         if(!(var13 instanceof OMapGenVillageRoadPiece)) {
            ++var11;
         }
      }

      this.c = var11 > 2;
   }

   public boolean a() {
      return this.c;
   }
}
