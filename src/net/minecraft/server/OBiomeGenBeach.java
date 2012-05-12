package net.minecraft.server;

import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OBlock;

public class OBiomeGenBeach extends OBiomeGenBase {

   public OBiomeGenBeach(int var1) {
      super(var1);
      this.K.clear();
      this.A = (byte)OBlock.E.bO;
      this.B = (byte)OBlock.E.bO;
      this.I.z = -999;
      this.I.C = 0;
      this.I.E = 0;
      this.I.F = 0;
   }
}
