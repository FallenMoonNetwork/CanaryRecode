package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OEnumToolMaterial;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OItemTool;
import net.minecraft.server.OMaterial;

public class OItemPickaxe extends OItemTool {

   private static OBlock[] bU = new OBlock[]{OBlock.w, OBlock.aj, OBlock.ak, OBlock.t, OBlock.Q, OBlock.ao, OBlock.H, OBlock.ai, OBlock.I, OBlock.ah, OBlock.G, OBlock.aw, OBlock.ax, OBlock.aT, OBlock.bb, OBlock.N, OBlock.O, OBlock.aN, OBlock.aO, OBlock.aG, OBlock.U, OBlock.T};


   protected OItemPickaxe(int var1, OEnumToolMaterial var2) {
      super(var1, 2, var2, bU);
   }

   public boolean a(OBlock var1) {
      return var1 == OBlock.ap?this.b.d() == 3:(var1 != OBlock.ax && var1 != OBlock.aw?(var1 != OBlock.ah && var1 != OBlock.G?(var1 != OBlock.ai && var1 != OBlock.H?(var1 != OBlock.O && var1 != OBlock.N?(var1 != OBlock.aN && var1 != OBlock.aO?(var1.cd == OMaterial.e?true:var1.cd == OMaterial.f):this.b.d() >= 2):this.b.d() >= 1):this.b.d() >= 1):this.b.d() >= 2):this.b.d() >= 2);
   }

   public float a(OItemStack var1, OBlock var2) {
      return var2 != null && (var2.cd == OMaterial.f || var2.cd == OMaterial.e)?this.a:super.a(var1, var2);
   }

}
