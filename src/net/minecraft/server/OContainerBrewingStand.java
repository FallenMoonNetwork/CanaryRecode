package net.minecraft.server;

import net.minecraft.server.OContainer;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OICrafting;
import net.minecraft.server.OInventoryPlayer;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OSlot;
import net.minecraft.server.OSlotBrewingStandIngredient;
import net.minecraft.server.OSlotBrewingStandPotion;
import net.minecraft.server.OTileEntityBrewingStand;

public class OContainerBrewingStand extends OContainer {

   private OTileEntityBrewingStand a;
   private int b = 0;


   public OContainerBrewingStand(OInventoryPlayer var1, OTileEntityBrewingStand var2) {
      super();
      this.a = var2;
      this.a(new OSlotBrewingStandPotion(this, var1.d, var2, 0, 56, 46));
      this.a(new OSlotBrewingStandPotion(this, var1.d, var2, 1, 79, 53));
      this.a(new OSlotBrewingStandPotion(this, var1.d, var2, 2, 102, 46));
      this.a(new OSlotBrewingStandIngredient(this, var2, 3, 79, 17));

      int var3;
      for(var3 = 0; var3 < 3; ++var3) {
         for(int var4 = 0; var4 < 9; ++var4) {
            this.a(new OSlot(var1, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
         }
      }

      for(var3 = 0; var3 < 9; ++var3) {
         this.a(new OSlot(var1, var3, 8 + var3 * 18, 142));
      }

   }

   public void a(OICrafting var1) {
      super.a(var1);
      var1.a(this, 0, this.a.i());
   }

   public void a() {
      super.a();

      for(int var1 = 0; var1 < this.g.size(); ++var1) {
         OICrafting var2 = (OICrafting)this.g.get(var1);
         if(this.b != this.a.i()) {
            var2.a(this, 0, this.a.i());
         }
      }

      this.b = this.a.i();
   }

   public boolean b(OEntityPlayer var1) {
      return this.a.a(var1);
   }

   public OItemStack a(int var1) {
      OItemStack var2 = null;
      OSlot var3 = (OSlot)this.e.get(var1);
      if(var3 != null && var3.c()) {
         OItemStack var4 = var3.b();
         var2 = var4.j();
         if((var1 < 0 || var1 > 2) && var1 != 3) {
            if(var1 >= 4 && var1 < 31) {
               if(!this.a(var4, 31, 40, false)) {
                  return null;
               }
            } else if(var1 >= 31 && var1 < 40) {
               if(!this.a(var4, 4, 31, false)) {
                  return null;
               }
            } else if(!this.a(var4, 4, 40, false)) {
               return null;
            }
         } else {
            if(!this.a(var4, 4, 40, true)) {
               return null;
            }

            var3.a(var4, var2);
         }

         if(var4.a == 0) {
            var3.d((OItemStack)null);
         } else {
            var3.d();
         }

         if(var4.a == var2.a) {
            return null;
         }

         var3.c(var4);
      }

      return var2;
   }
}
