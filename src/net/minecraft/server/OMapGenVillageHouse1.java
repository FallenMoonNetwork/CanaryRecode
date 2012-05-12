package net.minecraft.server;

import java.util.List;
import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OMapGenVillageStructureGen;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructurePiece;
import net.minecraft.server.OWorld;

public class OMapGenVillageHouse1 extends OMapGenVillageStructureGen {

   private int a = -1;


   public OMapGenVillageHouse1(int var1, Random var2, OStructureBoundingBox var3, int var4) {
      super(var1);
      this.h = var4;
      this.g = var3;
   }

   public void a(OStructurePiece var1, List var2, Random var3) {}

   public static OMapGenVillageHouse1 a(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
      OStructureBoundingBox var7 = OStructureBoundingBox.a(var2, var3, var4, 0, 0, 0, 9, 9, 6, var5);
      return a(var7) && OStructurePiece.a(var0, var7) == null?new OMapGenVillageHouse1(var6, var1, var7, var5):null;
   }

   public boolean a(OWorld var1, Random var2, OStructureBoundingBox var3) {
      if(this.a < 0) {
         this.a = this.b(var1, var3);
         if(this.a < 0) {
            return true;
         }

         this.g.a(0, this.a - this.g.e + 9 - 1, 0);
      }

      this.a(var1, var3, 1, 1, 1, 7, 5, 4, 0, 0, false);
      this.a(var1, var3, 0, 0, 0, 8, 0, 5, OBlock.w.bO, OBlock.w.bO, false);
      this.a(var1, var3, 0, 5, 0, 8, 5, 5, OBlock.w.bO, OBlock.w.bO, false);
      this.a(var1, var3, 0, 6, 1, 8, 6, 4, OBlock.w.bO, OBlock.w.bO, false);
      this.a(var1, var3, 0, 7, 2, 8, 7, 3, OBlock.w.bO, OBlock.w.bO, false);
      int var4 = this.c(OBlock.at.bO, 3);
      int var5 = this.c(OBlock.at.bO, 2);

      int var6;
      int var7;
      for(var6 = -1; var6 <= 2; ++var6) {
         for(var7 = 0; var7 <= 8; ++var7) {
            this.a(var1, OBlock.at.bO, var4, var7, 6 + var6, var6, var3);
            this.a(var1, OBlock.at.bO, var5, var7, 6 + var6, 5 - var6, var3);
         }
      }

      this.a(var1, var3, 0, 1, 0, 0, 1, 5, OBlock.w.bO, OBlock.w.bO, false);
      this.a(var1, var3, 1, 1, 5, 8, 1, 5, OBlock.w.bO, OBlock.w.bO, false);
      this.a(var1, var3, 8, 1, 0, 8, 1, 4, OBlock.w.bO, OBlock.w.bO, false);
      this.a(var1, var3, 2, 1, 0, 7, 1, 0, OBlock.w.bO, OBlock.w.bO, false);
      this.a(var1, var3, 0, 2, 0, 0, 4, 0, OBlock.w.bO, OBlock.w.bO, false);
      this.a(var1, var3, 0, 2, 5, 0, 4, 5, OBlock.w.bO, OBlock.w.bO, false);
      this.a(var1, var3, 8, 2, 5, 8, 4, 5, OBlock.w.bO, OBlock.w.bO, false);
      this.a(var1, var3, 8, 2, 0, 8, 4, 0, OBlock.w.bO, OBlock.w.bO, false);
      this.a(var1, var3, 0, 2, 1, 0, 4, 4, OBlock.x.bO, OBlock.x.bO, false);
      this.a(var1, var3, 1, 2, 5, 7, 4, 5, OBlock.x.bO, OBlock.x.bO, false);
      this.a(var1, var3, 8, 2, 1, 8, 4, 4, OBlock.x.bO, OBlock.x.bO, false);
      this.a(var1, var3, 1, 2, 0, 7, 4, 0, OBlock.x.bO, OBlock.x.bO, false);
      this.a(var1, OBlock.bq.bO, 0, 4, 2, 0, var3);
      this.a(var1, OBlock.bq.bO, 0, 5, 2, 0, var3);
      this.a(var1, OBlock.bq.bO, 0, 6, 2, 0, var3);
      this.a(var1, OBlock.bq.bO, 0, 4, 3, 0, var3);
      this.a(var1, OBlock.bq.bO, 0, 5, 3, 0, var3);
      this.a(var1, OBlock.bq.bO, 0, 6, 3, 0, var3);
      this.a(var1, OBlock.bq.bO, 0, 0, 2, 2, var3);
      this.a(var1, OBlock.bq.bO, 0, 0, 2, 3, var3);
      this.a(var1, OBlock.bq.bO, 0, 0, 3, 2, var3);
      this.a(var1, OBlock.bq.bO, 0, 0, 3, 3, var3);
      this.a(var1, OBlock.bq.bO, 0, 8, 2, 2, var3);
      this.a(var1, OBlock.bq.bO, 0, 8, 2, 3, var3);
      this.a(var1, OBlock.bq.bO, 0, 8, 3, 2, var3);
      this.a(var1, OBlock.bq.bO, 0, 8, 3, 3, var3);
      this.a(var1, OBlock.bq.bO, 0, 2, 2, 5, var3);
      this.a(var1, OBlock.bq.bO, 0, 3, 2, 5, var3);
      this.a(var1, OBlock.bq.bO, 0, 5, 2, 5, var3);
      this.a(var1, OBlock.bq.bO, 0, 6, 2, 5, var3);
      this.a(var1, var3, 1, 4, 1, 7, 4, 1, OBlock.x.bO, OBlock.x.bO, false);
      this.a(var1, var3, 1, 4, 4, 7, 4, 4, OBlock.x.bO, OBlock.x.bO, false);
      this.a(var1, var3, 1, 3, 4, 7, 3, 4, OBlock.an.bO, OBlock.an.bO, false);
      this.a(var1, OBlock.x.bO, 0, 7, 1, 4, var3);
      this.a(var1, OBlock.at.bO, this.c(OBlock.at.bO, 0), 7, 1, 3, var3);
      var6 = this.c(OBlock.at.bO, 3);
      this.a(var1, OBlock.at.bO, var6, 6, 1, 4, var3);
      this.a(var1, OBlock.at.bO, var6, 5, 1, 4, var3);
      this.a(var1, OBlock.at.bO, var6, 4, 1, 4, var3);
      this.a(var1, OBlock.at.bO, var6, 3, 1, 4, var3);
      this.a(var1, OBlock.aZ.bO, 0, 6, 1, 3, var3);
      this.a(var1, OBlock.aM.bO, 0, 6, 2, 3, var3);
      this.a(var1, OBlock.aZ.bO, 0, 4, 1, 3, var3);
      this.a(var1, OBlock.aM.bO, 0, 4, 2, 3, var3);
      this.a(var1, OBlock.ay.bO, 0, 7, 1, 1, var3);
      this.a(var1, 0, 0, 1, 1, 0, var3);
      this.a(var1, 0, 0, 1, 2, 0, var3);
      this.a(var1, var3, var2, 1, 1, 0, this.c(OBlock.aE.bO, 1));
      if(this.a(var1, 1, 0, -1, var3) == 0 && this.a(var1, 1, -1, -1, var3) != 0) {
         this.a(var1, OBlock.aH.bO, this.c(OBlock.aH.bO, 3), 1, 0, -1, var3);
      }

      for(var7 = 0; var7 < 6; ++var7) {
         for(int var8 = 0; var8 < 9; ++var8) {
            this.b(var1, var8, 9, var7, var3);
            this.b(var1, OBlock.w.bO, 0, var8, -1, var7, var3);
         }
      }

      this.a(var1, var3, 2, 1, 2, 1);
      return true;
   }

   protected int a(int var1) {
      return 1;
   }
}
