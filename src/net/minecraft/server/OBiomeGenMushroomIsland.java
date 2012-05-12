package net.minecraft.server;

import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityMushroomCow;
import net.minecraft.server.OSpawnListEntry;

public class OBiomeGenMushroomIsland extends OBiomeGenBase {

   public OBiomeGenMushroomIsland(int var1) {
      super(var1);
      this.I.z = -100;
      this.I.A = -100;
      this.I.B = -100;
      this.I.D = 1;
      this.I.J = 1;
      this.A = (byte)OBlock.by.bO;
      this.J.clear();
      this.K.clear();
      this.L.clear();
      this.K.add(new OSpawnListEntry(OEntityMushroomCow.class, 8, 4, 8));
   }
}
