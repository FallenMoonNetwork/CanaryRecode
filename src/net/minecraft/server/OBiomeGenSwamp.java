package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OWorldGenerator;

public class OBiomeGenSwamp extends OBiomeGenBase {

   protected OBiomeGenSwamp(int var1) {
      super(var1);
      this.I.z = 2;
      this.I.A = -999;
      this.I.C = 1;
      this.I.D = 8;
      this.I.E = 10;
      this.I.I = 1;
      this.I.y = 4;
      this.H = 14745518;
   }

   public OWorldGenerator a(Random var1) {
      return this.Q;
   }
}
