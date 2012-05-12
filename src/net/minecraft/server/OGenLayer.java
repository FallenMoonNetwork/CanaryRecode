package net.minecraft.server;

import net.minecraft.server.OEnumWorldType;
import net.minecraft.server.OGenLayerAddIsland;
import net.minecraft.server.OGenLayerAddMushroomIsland;
import net.minecraft.server.OGenLayerAddSnow;
import net.minecraft.server.OGenLayerHills;
import net.minecraft.server.OGenLayerIsland;
import net.minecraft.server.OGenLayerRiver;
import net.minecraft.server.OGenLayerRiverInit;
import net.minecraft.server.OGenLayerRiverMix;
import net.minecraft.server.OGenLayerShore;
import net.minecraft.server.OGenLayerSmooth;
import net.minecraft.server.OGenLayerSwampRivers;
import net.minecraft.server.OGenLayerZoom;
import net.minecraft.server.OGenLayerZoomFuzzy;
import net.minecraft.server.OGenLayerZoomVoronoi;
import net.minecraft.server.OMapGenVillageLandscapeManager;

public abstract class OGenLayer {

   private long b;
   protected OGenLayer a;
   private long c;
   private long d;


   public static OGenLayer[] a(long var0, OEnumWorldType var2) {
      OGenLayerIsland var3 = new OGenLayerIsland(1L);
      OGenLayerZoomFuzzy var9 = new OGenLayerZoomFuzzy(2000L, var3);
      OGenLayerAddIsland var10 = new OGenLayerAddIsland(1L, var9);
      OGenLayerZoom var11 = new OGenLayerZoom(2001L, var10);
      var10 = new OGenLayerAddIsland(2L, var11);
      OGenLayerAddSnow var12 = new OGenLayerAddSnow(2L, var10);
      var11 = new OGenLayerZoom(2002L, var12);
      var10 = new OGenLayerAddIsland(3L, var11);
      var11 = new OGenLayerZoom(2003L, var10);
      var10 = new OGenLayerAddIsland(4L, var11);
      OGenLayerAddMushroomIsland var17 = new OGenLayerAddMushroomIsland(5L, var10);
      byte var4 = 4;
      OGenLayer var5 = OGenLayerZoom.a(1000L, var17, 0);
      OGenLayerRiverInit var13 = new OGenLayerRiverInit(100L, var5);
      var5 = OGenLayerZoom.a(1000L, var13, var4 + 2);
      OGenLayerRiver var14 = new OGenLayerRiver(1L, var5);
      OGenLayerSmooth var16 = new OGenLayerSmooth(1000L, var14);
      OGenLayer var6 = OGenLayerZoom.a(1000L, var17, 0);
      OMapGenVillageLandscapeManager var15 = new OMapGenVillageLandscapeManager(200L, var6, var2);
      var6 = OGenLayerZoom.a(1000L, var15, 2);
      Object var18 = new OGenLayerHills(1000L, var6);

      for(int var7 = 0; var7 < var4; ++var7) {
         var18 = new OGenLayerZoom((long)(1000 + var7), (OGenLayer)var18);
         if(var7 == 0) {
            var18 = new OGenLayerAddIsland(3L, (OGenLayer)var18);
         }

         if(var7 == 1) {
            var18 = new OGenLayerShore(1000L, (OGenLayer)var18);
         }

         if(var7 == 1) {
            var18 = new OGenLayerSwampRivers(1000L, (OGenLayer)var18);
         }
      }

      OGenLayerSmooth var19 = new OGenLayerSmooth(1000L, (OGenLayer)var18);
      OGenLayerRiverMix var20 = new OGenLayerRiverMix(100L, var19, var16);
      OGenLayerZoomVoronoi var8 = new OGenLayerZoomVoronoi(10L, var20);
      var20.a(var0);
      var8.a(var0);
      return new OGenLayer[]{var20, var8, var20};
   }

   public OGenLayer(long var1) {
      super();
      this.d = var1;
      this.d *= this.d * 6364136223846793005L + 1442695040888963407L;
      this.d += var1;
      this.d *= this.d * 6364136223846793005L + 1442695040888963407L;
      this.d += var1;
      this.d *= this.d * 6364136223846793005L + 1442695040888963407L;
      this.d += var1;
   }

   public void a(long var1) {
      this.b = var1;
      if(this.a != null) {
         this.a.a(var1);
      }

      this.b *= this.b * 6364136223846793005L + 1442695040888963407L;
      this.b += this.d;
      this.b *= this.b * 6364136223846793005L + 1442695040888963407L;
      this.b += this.d;
      this.b *= this.b * 6364136223846793005L + 1442695040888963407L;
      this.b += this.d;
   }

   public void a(long var1, long var3) {
      this.c = this.b;
      this.c *= this.c * 6364136223846793005L + 1442695040888963407L;
      this.c += var1;
      this.c *= this.c * 6364136223846793005L + 1442695040888963407L;
      this.c += var3;
      this.c *= this.c * 6364136223846793005L + 1442695040888963407L;
      this.c += var1;
      this.c *= this.c * 6364136223846793005L + 1442695040888963407L;
      this.c += var3;
   }

   protected int a(int var1) {
      int var2 = (int)((this.c >> 24) % (long)var1);
      if(var2 < 0) {
         var2 += var1;
      }

      this.c *= this.c * 6364136223846793005L + 1442695040888963407L;
      this.c += this.b;
      return var2;
   }

   public abstract int[] a(int var1, int var2, int var3, int var4);
}
