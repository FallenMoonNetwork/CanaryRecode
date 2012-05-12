package net.minecraft.server;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OMapGenStructure;
import net.minecraft.server.OMapGenVillageStart;
import net.minecraft.server.OStructureStart;

public class OMapGenVillage extends OMapGenStructure {

   public static List a = Arrays.asList(new OBiomeGenBase[]{OBiomeGenBase.c, OBiomeGenBase.d});
   private final int f;


   public OMapGenVillage(int var1) {
      super();
      this.f = var1;
   }

   protected boolean a(int var1, int var2) {
      byte var3 = 32;
      byte var4 = 8;
      int var5 = var1;
      int var6 = var2;
      if(var1 < 0) {
         var1 -= var3 - 1;
      }

      if(var2 < 0) {
         var2 -= var3 - 1;
      }

      int var7 = var1 / var3;
      int var8 = var2 / var3;
      Random var9 = this.d.A(var7, var8, 10387312);
      var7 *= var3;
      var8 *= var3;
      var7 += var9.nextInt(var3 - var4);
      var8 += var9.nextInt(var3 - var4);
      if(var5 == var7 && var6 == var8) {
         boolean var10 = this.d.a().a(var5 * 16 + 8, var6 * 16 + 8, 0, a);
         if(var10) {
            return true;
         }
      }

      return false;
   }

   protected OStructureStart b(int var1, int var2) {
      return new OMapGenVillageStart(this.d, this.c, var1, var2, this.f);
   }

}
