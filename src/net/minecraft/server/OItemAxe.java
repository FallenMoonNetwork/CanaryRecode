package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OEnumToolMaterial;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OItemTool;
import net.minecraft.server.OMaterial;

public class OItemAxe extends OItemTool {

   private static OBlock[] bU = new OBlock[]{OBlock.x, OBlock.an, OBlock.J, OBlock.au, OBlock.aj, OBlock.ak, OBlock.ba, OBlock.bf};


   protected OItemAxe(int var1, OEnumToolMaterial var2) {
      super(var1, 3, var2, bU);
   }

   public float a(OItemStack var1, OBlock var2) {
      return var2 != null && var2.cd == OMaterial.d?this.a:super.a(var1, var2);
   }

}
