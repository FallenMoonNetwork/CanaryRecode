package net.minecraft.server;

import net.minecraft.server.OContainer;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OFurnaceRecipes;
import net.minecraft.server.OICrafting;
import net.minecraft.server.OInventoryPlayer;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OSlot;
import net.minecraft.server.OSlotFurnace;
import net.minecraft.server.OTileEntityFurnace;

public class OContainerFurnace extends OContainer {

   private OTileEntityFurnace a;
   private int b = 0;
   private int c = 0;
   private int h = 0;


   public OContainerFurnace(OInventoryPlayer var1, OTileEntityFurnace var2) {
      super();
      this.a = var2;
      this.a(new OSlot(var2, 0, 56, 17));
      this.a(new OSlot(var2, 1, 56, 53));
      this.a(new OSlotFurnace(var1.d, var2, 2, 116, 35));

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
      var1.a(this, 0, this.a.c);
      var1.a(this, 1, this.a.a);
      var1.a(this, 2, this.a.b);
   }

   public void a() {
      super.a();

      for(int var1 = 0; var1 < this.g.size(); ++var1) {
         OICrafting var2 = (OICrafting)this.g.get(var1);
         if(this.b != this.a.c) {
            var2.a(this, 0, this.a.c);
         }

         if(this.c != this.a.a) {
            var2.a(this, 1, this.a.a);
         }

         if(this.h != this.a.b) {
            var2.a(this, 2, this.a.b);
         }
      }

      this.b = this.a.c;
      this.c = this.a.a;
      this.h = this.a.b;
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
         if(var1 == 2) {
            if(!this.a(var4, 3, 39, true)) {
               return null;
            }

            var3.a(var4, var2);
         } else if(var1 != 1 && var1 != 0) {
            if(OFurnaceRecipes.a().a(var4.a().bP) != null) {
               if(!this.a(var4, 0, 1, false)) {
                  return null;
               }
            } else if(OTileEntityFurnace.b(var4)) {
               if(!this.a(var4, 1, 2, false)) {
                  return null;
               }
            } else if(var1 >= 3 && var1 < 30) {
               if(!this.a(var4, 30, 39, false)) {
                  return null;
               }
            } else if(var1 >= 30 && var1 < 39 && !this.a(var4, 3, 30, false)) {
               return null;
            }
         } else if(!this.a(var4, 3, 39, false)) {
            return null;
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
