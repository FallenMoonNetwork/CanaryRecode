package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OContainerPlayer;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OItemArmor;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OSlot;

class OSlotArmor extends OSlot {

   // $FF: synthetic field
   final int a;
   // $FF: synthetic field
   final OContainerPlayer f;


   OSlotArmor(OContainerPlayer var1, OIInventory var2, int var3, int var4, int var5, int var6) {
      super(var2, var3, var4, var5);
      this.f = var1;
      this.a = var6;
   }

   public int a() {
      return 1;
   }

   public boolean a(OItemStack var1) {
      return var1.a() instanceof OItemArmor?((OItemArmor)var1.a()).a == this.a:(var1.a().bP == OBlock.ba.bO?this.a == 0:false);
   }
}
