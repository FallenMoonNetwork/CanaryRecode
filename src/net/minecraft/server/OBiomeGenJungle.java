package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityChicken;
import net.minecraft.server.OEntityOcelot;
import net.minecraft.server.OSpawnListEntry;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldGenHugeTrees;
import net.minecraft.server.OWorldGenJungleTrees;
import net.minecraft.server.OWorldGenShrub;
import net.minecraft.server.OWorldGenTallGrass;
import net.minecraft.server.OWorldGenTrees;
import net.minecraft.server.OWorldGenerator;

public class OBiomeGenJungle extends OBiomeGenBase {

   public OBiomeGenJungle(int var1) {
      super(var1);
      this.I.z = 50;
      this.I.B = 25;
      this.I.A = 4;
      this.J.add(new OSpawnListEntry(OEntityOcelot.class, 2, 1, 1));
      this.K.add(new OSpawnListEntry(OEntityChicken.class, 10, 4, 4));
   }

   public OWorldGenerator a(Random var1) {
      return (OWorldGenerator)(var1.nextInt(10) == 0?this.O:(var1.nextInt(2) == 0?new OWorldGenShrub(3, 0):(var1.nextInt(3) == 0?new OWorldGenHugeTrees(false, 10 + var1.nextInt(20), 3, 3):new OWorldGenTrees(false, 4 + var1.nextInt(7), 3, 3, true))));
   }

   public OWorldGenerator b(Random var1) {
      return var1.nextInt(4) == 0?new OWorldGenTallGrass(OBlock.X.bO, 2):new OWorldGenTallGrass(OBlock.X.bO, 1);
   }

   public void a(OWorld var1, Random var2, int var3, int var4) {
      super.a(var1, var2, var3, var4);
      OWorldGenJungleTrees var5 = new OWorldGenJungleTrees();

      for(int var6 = 0; var6 < 50; ++var6) {
         int var7 = var3 + var2.nextInt(16) + 8;
         byte var8 = 64;
         int var9 = var4 + var2.nextInt(16) + 8;
         var5.a(var1, var2, var7, var8, var9);
      }

   }
}
