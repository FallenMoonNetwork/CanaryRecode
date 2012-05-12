package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockBreakable;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEnumSkyBlock;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;

public class OBlockIce extends OBlockBreakable {

   public OBlockIce(int var1, int var2) {
      super(var1, var2, OMaterial.u, false);
      this.ce = 0.98F;
      this.a(true);
   }

   public void a(OWorld var1, OEntityPlayer var2, int var3, int var4, int var5, int var6) {
      super.a(var1, var2, var3, var4, var5, var6);
      OMaterial var7 = var1.d(var3, var4 - 1, var5);
      if(var7.c() || var7.d()) {
         var1.e(var3, var4, var5, OBlock.A.bO);
      }

   }

   public int a(Random var1) {
      return 0;
   }

   public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
      if(var1.a(OEnumSkyBlock.b, var2, var3, var4) > 11 - OBlock.o[this.bO]) {
         this.b(var1, var2, var3, var4, var1.c(var2, var3, var4), 0);
         var1.e(var2, var3, var4, OBlock.B.bO);
      }

   }

   public int g() {
      return 0;
   }

   protected OItemStack a_(int var1) {
      return null;
   }
}
