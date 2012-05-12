package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;

public class OBlockGrass extends OBlock {

   protected OBlockGrass(int var1) {
      super(var1, OMaterial.b);
      this.bN = 3;
      this.a(true);
   }

   public int a(int var1, int var2) {
      return var1 == 1?0:(var1 == 0?2:3);
   }

   public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
      if(!var1.F) {
         if(var1.n(var2, var3 + 1, var4) < 4 && OBlock.o[var1.a(var2, var3 + 1, var4)] > 2) {
            var1.e(var2, var3, var4, OBlock.v.bO);
         } else if(var1.n(var2, var3 + 1, var4) >= 9) {
            for(int var6 = 0; var6 < 4; ++var6) {
               int var7 = var2 + var5.nextInt(3) - 1;
               int var8 = var3 + var5.nextInt(5) - 3;
               int var9 = var4 + var5.nextInt(3) - 1;
               int var10 = var1.a(var7, var8 + 1, var9);
               if(var1.a(var7, var8, var9) == OBlock.v.bO && var1.n(var7, var8 + 1, var9) >= 4 && OBlock.o[var10] <= 2) {
                  var1.e(var7, var8, var9, OBlock.u.bO);
               }
            }
         }

      }
   }

   public int a(int var1, Random var2, int var3) {
      return OBlock.v.a(0, var2, var3);
   }
}
