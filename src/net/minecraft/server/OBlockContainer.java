package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OTileEntity;
import net.minecraft.server.OWorld;

public abstract class OBlockContainer extends OBlock {

   protected OBlockContainer(int var1, OMaterial var2) {
      super(var1, var2);
      this.bU = true;
   }

   protected OBlockContainer(int var1, int var2, OMaterial var3) {
      super(var1, var2, var3);
      this.bU = true;
   }

   public void a(OWorld var1, int var2, int var3, int var4) {
      super.a(var1, var2, var3, var4);
      var1.a(var2, var3, var4, this.a_());
   }

   public void d(OWorld var1, int var2, int var3, int var4) {
      super.d(var1, var2, var3, var4);
      var1.q(var2, var3, var4);
   }

   public abstract OTileEntity a_();

   public void a(OWorld var1, int var2, int var3, int var4, int var5, int var6) {
      super.a(var1, var2, var3, var4, var5, var6);
      OTileEntity var7 = var1.b(var2, var3, var4);
      if(var7 != null) {
         var7.b(var5, var6);
      }

   }
}
