package net.minecraft.server;

import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OGenLayer;
import net.minecraft.server.OIntCache;

public class OGenLayerShore extends OGenLayer {

   public OGenLayerShore(long var1, OGenLayer var3) {
      super(var1);
      this.a = var3;
   }

   public int[] a(int var1, int var2, int var3, int var4) {
      int[] var5 = this.a.a(var1 - 1, var2 - 1, var3 + 2, var4 + 2);
      int[] var6 = OIntCache.a(var3 * var4);

      for(int var7 = 0; var7 < var4; ++var7) {
         for(int var8 = 0; var8 < var3; ++var8) {
            this.a((long)(var8 + var1), (long)(var7 + var2));
            int var9 = var5[var8 + 1 + (var7 + 1) * (var3 + 2)];
            int var10;
            int var11;
            int var12;
            int var13;
            if(var9 == OBiomeGenBase.p.M) {
               var10 = var5[var8 + 1 + (var7 + 1 - 1) * (var3 + 2)];
               var11 = var5[var8 + 1 + 1 + (var7 + 1) * (var3 + 2)];
               var12 = var5[var8 + 1 - 1 + (var7 + 1) * (var3 + 2)];
               var13 = var5[var8 + 1 + (var7 + 1 + 1) * (var3 + 2)];
               if(var10 != OBiomeGenBase.b.M && var11 != OBiomeGenBase.b.M && var12 != OBiomeGenBase.b.M && var13 != OBiomeGenBase.b.M) {
                  var6[var8 + var7 * var3] = var9;
               } else {
                  var6[var8 + var7 * var3] = OBiomeGenBase.q.M;
               }
            } else if(var9 != OBiomeGenBase.b.M && var9 != OBiomeGenBase.i.M && var9 != OBiomeGenBase.h.M && var9 != OBiomeGenBase.e.M) {
               var10 = var5[var8 + 1 + (var7 + 1 - 1) * (var3 + 2)];
               var11 = var5[var8 + 1 + 1 + (var7 + 1) * (var3 + 2)];
               var12 = var5[var8 + 1 - 1 + (var7 + 1) * (var3 + 2)];
               var13 = var5[var8 + 1 + (var7 + 1 + 1) * (var3 + 2)];
               if(var10 != OBiomeGenBase.b.M && var11 != OBiomeGenBase.b.M && var12 != OBiomeGenBase.b.M && var13 != OBiomeGenBase.b.M) {
                  var6[var8 + var7 * var3] = var9;
               } else {
                  var6[var8 + var7 * var3] = OBiomeGenBase.r.M;
               }
            } else if(var9 == OBiomeGenBase.e.M) {
               var10 = var5[var8 + 1 + (var7 + 1 - 1) * (var3 + 2)];
               var11 = var5[var8 + 1 + 1 + (var7 + 1) * (var3 + 2)];
               var12 = var5[var8 + 1 - 1 + (var7 + 1) * (var3 + 2)];
               var13 = var5[var8 + 1 + (var7 + 1 + 1) * (var3 + 2)];
               if(var10 == OBiomeGenBase.e.M && var11 == OBiomeGenBase.e.M && var12 == OBiomeGenBase.e.M && var13 == OBiomeGenBase.e.M) {
                  var6[var8 + var7 * var3] = var9;
               } else {
                  var6[var8 + var7 * var3] = OBiomeGenBase.v.M;
               }
            } else {
               var6[var8 + var7 * var3] = var9;
            }
         }
      }

      return var6;
   }
}
