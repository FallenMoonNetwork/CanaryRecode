package net.minecraft.server;

import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityEgg;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OWorld;

public class OItemEgg extends OItem {

   public OItemEgg(int var1) {
      super(var1);
      this.bQ = 16;
   }

   public OItemStack a(OItemStack var1, OWorld var2, OEntityPlayer var3) {
      if(!var3.L.d) {
         --var1.a;
      }

      var2.a(var3, "random.bow", 0.5F, 0.4F / (c.nextFloat() * 0.4F + 0.8F));
      if(!var2.F) {
         var2.b((OEntity)(new OEntityEgg(var2, var3)));
      }

      return var1;
   }
}
