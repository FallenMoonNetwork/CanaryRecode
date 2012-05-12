package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntity;
import net.minecraft.server.OItem;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;

public class OBlockWeb extends OBlock {

   public OBlockWeb(int var1, int var2) {
      super(var1, var2, OMaterial.D);
   }

   public void a(OWorld var1, int var2, int var3, int var4, OEntity var5) {
      var5.u();
   }

   public boolean a() {
      return false;
   }

   public OAxisAlignedBB e(OWorld var1, int var2, int var3, int var4) {
      return null;
   }

   public int c() {
      return 1;
   }

   public boolean b() {
      return false;
   }

   public int a(int var1, Random var2, int var3) {
      return OItem.J.bP;
   }
}
