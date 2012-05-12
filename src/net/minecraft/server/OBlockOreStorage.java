package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OMaterial;

public class OBlockOreStorage extends OBlock {

   public OBlockOreStorage(int var1, int var2) {
      super(var1, OMaterial.f);
      this.bN = var2;
   }

   public int a(int var1) {
      return this.bN;
   }
}
