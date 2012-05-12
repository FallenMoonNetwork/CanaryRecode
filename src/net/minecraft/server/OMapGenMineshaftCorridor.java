package net.minecraft.server;

import java.util.List;
import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OMapGenMineshaftPieces;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructurePiece;
import net.minecraft.server.OTileEntityMobSpawner;
import net.minecraft.server.OWorld;

public class OMapGenMineshaftCorridor extends OStructurePiece {

   private final boolean a;
   private final boolean b;
   private boolean c;
   private int d;


   public OMapGenMineshaftCorridor(int var1, Random var2, OStructureBoundingBox var3, int var4) {
      super(var1);
      this.h = var4;
      this.g = var3;
      this.a = var2.nextInt(3) == 0;
      this.b = !this.a && var2.nextInt(23) == 0;
      if(this.h != 2 && this.h != 0) {
         this.d = var3.b() / 5;
      } else {
         this.d = var3.d() / 5;
      }

   }

   public static OStructureBoundingBox a(List var0, Random var1, int var2, int var3, int var4, int var5) {
      OStructureBoundingBox var6 = new OStructureBoundingBox(var2, var3, var4, var2, var3 + 2, var4);

      int var7;
      for(var7 = var1.nextInt(3) + 2; var7 > 0; --var7) {
         int var8 = var7 * 5;
         switch(var5) {
         case 0:
            var6.d = var2 + 2;
            var6.f = var4 + (var8 - 1);
            break;
         case 1:
            var6.a = var2 - (var8 - 1);
            var6.f = var4 + 2;
            break;
         case 2:
            var6.d = var2 + 2;
            var6.c = var4 - (var8 - 1);
            break;
         case 3:
            var6.d = var2 + (var8 - 1);
            var6.f = var4 + 2;
         }

         if(OStructurePiece.a(var0, var6) == null) {
            break;
         }
      }

      return var7 > 0?var6:null;
   }

   public void a(OStructurePiece var1, List var2, Random var3) {
      int var4 = this.c();
      int var5 = var3.nextInt(4);
      switch(this.h) {
      case 0:
         if(var5 <= 1) {
            OMapGenMineshaftPieces.a(var1, var2, var3, this.g.a, this.g.b - 1 + var3.nextInt(3), this.g.f + 1, this.h, var4);
         } else if(var5 == 2) {
            OMapGenMineshaftPieces.a(var1, var2, var3, this.g.a - 1, this.g.b - 1 + var3.nextInt(3), this.g.f - 3, 1, var4);
         } else {
            OMapGenMineshaftPieces.a(var1, var2, var3, this.g.d + 1, this.g.b - 1 + var3.nextInt(3), this.g.f - 3, 3, var4);
         }
         break;
      case 1:
         if(var5 <= 1) {
            OMapGenMineshaftPieces.a(var1, var2, var3, this.g.a - 1, this.g.b - 1 + var3.nextInt(3), this.g.c, this.h, var4);
         } else if(var5 == 2) {
            OMapGenMineshaftPieces.a(var1, var2, var3, this.g.a, this.g.b - 1 + var3.nextInt(3), this.g.c - 1, 2, var4);
         } else {
            OMapGenMineshaftPieces.a(var1, var2, var3, this.g.a, this.g.b - 1 + var3.nextInt(3), this.g.f + 1, 0, var4);
         }
         break;
      case 2:
         if(var5 <= 1) {
            OMapGenMineshaftPieces.a(var1, var2, var3, this.g.a, this.g.b - 1 + var3.nextInt(3), this.g.c - 1, this.h, var4);
         } else if(var5 == 2) {
            OMapGenMineshaftPieces.a(var1, var2, var3, this.g.a - 1, this.g.b - 1 + var3.nextInt(3), this.g.c, 1, var4);
         } else {
            OMapGenMineshaftPieces.a(var1, var2, var3, this.g.d + 1, this.g.b - 1 + var3.nextInt(3), this.g.c, 3, var4);
         }
         break;
      case 3:
         if(var5 <= 1) {
            OMapGenMineshaftPieces.a(var1, var2, var3, this.g.d + 1, this.g.b - 1 + var3.nextInt(3), this.g.c, this.h, var4);
         } else if(var5 == 2) {
            OMapGenMineshaftPieces.a(var1, var2, var3, this.g.d - 3, this.g.b - 1 + var3.nextInt(3), this.g.c - 1, 2, var4);
         } else {
            OMapGenMineshaftPieces.a(var1, var2, var3, this.g.d - 3, this.g.b - 1 + var3.nextInt(3), this.g.f + 1, 0, var4);
         }
      }

      if(var4 < 8) {
         int var6;
         int var7;
         if(this.h != 2 && this.h != 0) {
            for(var6 = this.g.a + 3; var6 + 3 <= this.g.d; var6 += 5) {
               var7 = var3.nextInt(5);
               if(var7 == 0) {
                  OMapGenMineshaftPieces.a(var1, var2, var3, var6, this.g.b, this.g.c - 1, 2, var4 + 1);
               } else if(var7 == 1) {
                  OMapGenMineshaftPieces.a(var1, var2, var3, var6, this.g.b, this.g.f + 1, 0, var4 + 1);
               }
            }
         } else {
            for(var6 = this.g.c + 3; var6 + 3 <= this.g.f; var6 += 5) {
               var7 = var3.nextInt(5);
               if(var7 == 0) {
                  OMapGenMineshaftPieces.a(var1, var2, var3, this.g.a - 1, this.g.b, var6, 1, var4 + 1);
               } else if(var7 == 1) {
                  OMapGenMineshaftPieces.a(var1, var2, var3, this.g.d + 1, this.g.b, var6, 3, var4 + 1);
               }
            }
         }
      }

   }

   public boolean a(OWorld var1, Random var2, OStructureBoundingBox var3) {
      if(this.a(var1, var3)) {
         return false;
      } else {
         int var4 = this.d * 5 - 1;
         this.a(var1, var3, 0, 0, 0, 2, 1, var4, 0, 0, false);
         this.a(var1, var3, var2, 0.8F, 0, 2, 0, 2, 2, var4, 0, 0, false);
         if(this.b) {
            this.a(var1, var3, var2, 0.6F, 0, 0, 0, 2, 1, var4, OBlock.W.bO, 0, false);
         }

         int var5;
         int var6;
         int var7;
         for(var5 = 0; var5 < this.d; ++var5) {
            var6 = 2 + var5 * 5;
            this.a(var1, var3, 0, 0, var6, 0, 1, var6, OBlock.aZ.bO, 0, false);
            this.a(var1, var3, 2, 0, var6, 2, 1, var6, OBlock.aZ.bO, 0, false);
            if(var2.nextInt(4) != 0) {
               this.a(var1, var3, 0, 2, var6, 2, 2, var6, OBlock.x.bO, 0, false);
            } else {
               this.a(var1, var3, 0, 2, var6, 0, 2, var6, OBlock.x.bO, 0, false);
               this.a(var1, var3, 2, 2, var6, 2, 2, var6, OBlock.x.bO, 0, false);
            }

            this.a(var1, var3, var2, 0.1F, 0, 2, var6 - 1, OBlock.W.bO, 0);
            this.a(var1, var3, var2, 0.1F, 2, 2, var6 - 1, OBlock.W.bO, 0);
            this.a(var1, var3, var2, 0.1F, 0, 2, var6 + 1, OBlock.W.bO, 0);
            this.a(var1, var3, var2, 0.1F, 2, 2, var6 + 1, OBlock.W.bO, 0);
            this.a(var1, var3, var2, 0.05F, 0, 2, var6 - 2, OBlock.W.bO, 0);
            this.a(var1, var3, var2, 0.05F, 2, 2, var6 - 2, OBlock.W.bO, 0);
            this.a(var1, var3, var2, 0.05F, 0, 2, var6 + 2, OBlock.W.bO, 0);
            this.a(var1, var3, var2, 0.05F, 2, 2, var6 + 2, OBlock.W.bO, 0);
            this.a(var1, var3, var2, 0.05F, 1, 2, var6 - 1, OBlock.aq.bO, 0);
            this.a(var1, var3, var2, 0.05F, 1, 2, var6 + 1, OBlock.aq.bO, 0);
            if(var2.nextInt(100) == 0) {
               this.a(var1, var3, var2, 2, 0, var6 - 1, OMapGenMineshaftPieces.a(), 3 + var2.nextInt(4));
            }

            if(var2.nextInt(100) == 0) {
               this.a(var1, var3, var2, 0, 0, var6 + 1, OMapGenMineshaftPieces.a(), 3 + var2.nextInt(4));
            }

            if(this.b && !this.c) {
               var7 = this.b(0);
               int var8 = var6 - 1 + var2.nextInt(3);
               int var9 = this.a(1, var8);
               var8 = this.b(1, var8);
               if(var3.b(var9, var7, var8)) {
                  this.c = true;
                  var1.e(var9, var7, var8, OBlock.as.bO);
                  OTileEntityMobSpawner var10 = (OTileEntityMobSpawner)var1.b(var9, var7, var8);
                  if(var10 != null) {
                     var10.a("CaveSpider");
                  }
               }
            }
         }

         for(var5 = 0; var5 <= 2; ++var5) {
            for(var6 = 0; var6 <= var4; ++var6) {
               var7 = this.a(var1, var5, -1, var6, var3);
               if(var7 == 0) {
                  this.a(var1, OBlock.x.bO, 0, var5, -1, var6, var3);
               }
            }
         }

         if(this.a) {
            for(var5 = 0; var5 <= var4; ++var5) {
               var6 = this.a(var1, 1, -1, var5, var3);
               if(var6 > 0 && OBlock.n[var6]) {
                  this.a(var1, var3, var2, 0.7F, 1, 0, var5, OBlock.aG.bO, this.c(OBlock.aG.bO, 0));
               }
            }
         }

         return true;
      }
   }
}
