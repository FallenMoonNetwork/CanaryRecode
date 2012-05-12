package net.minecraft.server;

import java.util.List;
import java.util.Random;
import net.minecraft.server.OMapGenMineshaftRoom;
import net.minecraft.server.OStructurePiece;
import net.minecraft.server.OStructureStart;
import net.minecraft.server.OWorld;

public class OMapGenMineshaftStart extends OStructureStart {

   public OMapGenMineshaftStart(OWorld var1, Random var2, int var3, int var4) {
      super();
      OMapGenMineshaftRoom var5 = new OMapGenMineshaftRoom(0, var2, (var3 << 4) + 2, (var4 << 4) + 2);
      this.a.add(var5);
      var5.a((OStructurePiece)var5, (List)this.a, var2);
      this.d();
      this.a(var1, var2, 10);
   }
}
