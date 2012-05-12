package net.minecraft.server;

import net.minecraft.server.OGenLayer;
import net.minecraft.server.OIntCache;

public class OGenLayerIsland extends OGenLayer {

   public OGenLayerIsland(long var1) {
      super(var1);
   }

   public int[] a(int var1, int var2, int var3, int var4) {
      int[] var5 = OIntCache.a(var3 * var4);

      for(int var6 = 0; var6 < var4; ++var6) {
         for(int var7 = 0; var7 < var3; ++var7) {
            this.a((long)(var1 + var7), (long)(var2 + var6));
            var5[var7 + var6 * var3] = this.a(10) == 0?1:0;
         }
      }

      if(var1 > -var3 && var1 <= 0 && var2 > -var4 && var2 <= 0) {
         var5[-var1 + -var2 * var3] = 1;
      }

      return var5;
   }
}
