package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.OEntityBlaze;
import net.minecraft.server.OEntityLavaSlime;
import net.minecraft.server.OEntityPigZombie;
import net.minecraft.server.OMapGenStructure;
import net.minecraft.server.OSpawnListEntry;
import net.minecraft.server.OStructureNetherBridgeStart;
import net.minecraft.server.OStructureStart;

public class OMapGenNetherBridge extends OMapGenStructure {

   private List a = new ArrayList();


   public OMapGenNetherBridge() {
      super();
      this.a.add(new OSpawnListEntry(OEntityBlaze.class, 10, 2, 3));
      this.a.add(new OSpawnListEntry(OEntityPigZombie.class, 10, 4, 4));
      this.a.add(new OSpawnListEntry(OEntityLavaSlime.class, 3, 4, 4));
   }

   public List b() {
      return this.a;
   }

   protected boolean a(int var1, int var2) {
      int var3 = var1 >> 4;
      int var4 = var2 >> 4;
      this.c.setSeed((long)(var3 ^ var4 << 4) ^ this.d.n());
      this.c.nextInt();
      return this.c.nextInt(3) != 0?false:(var1 != (var3 << 4) + 4 + this.c.nextInt(8)?false:var2 == (var4 << 4) + 4 + this.c.nextInt(8));
   }

   protected OStructureStart b(int var1, int var2) {
      return new OStructureNetherBridgeStart(this.d, this.c, var1, var2);
   }
}
