package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OWorld;

public class OItemReed extends OItem {

   private int a;


   public OItemReed(int var1, OBlock var2) {
      super(var1);
      this.a = var2.bO;
   }

   public boolean a(OItemStack var1, OEntityPlayer var2, OWorld var3, int var4, int var5, int var6, int var7) {
      int var8 = var3.a(var4, var5, var6);
      if(var8 == OBlock.aS.bO) {
         var7 = 1;
      } else if(var8 != OBlock.bu.bO && var8 != OBlock.X.bO && var8 != OBlock.Y.bO) {
         if(var7 == 0) {
            --var5;
         }

         if(var7 == 1) {
            ++var5;
         }

         if(var7 == 2) {
            --var6;
         }

         if(var7 == 3) {
            ++var6;
         }

         if(var7 == 4) {
            --var4;
         }

         if(var7 == 5) {
            ++var4;
         }
      }

      if(!var2.d(var4, var5, var6)) {
         return false;
      } else if(var1.a == 0) {
         return false;
      } else {
         if(var3.a(this.a, var4, var5, var6, false, var7)) {
            OBlock var9 = OBlock.m[this.a];
            if(var3.e(var4, var5, var6, this.a)) {
               if(var3.a(var4, var5, var6) == this.a) {
                  OBlock.m[this.a].e(var3, var4, var5, var6, var7);
                  OBlock.m[this.a].a(var3, var4, var5, var6, (OEntityLiving)var2);
               }

               var3.a((double)((float)var4 + 0.5F), (double)((float)var5 + 0.5F), (double)((float)var6 + 0.5F), var9.cb.c(), (var9.cb.a() + 1.0F) / 2.0F, var9.cb.b() * 0.8F);
               --var1.a;
            }
         }

         return true;
      }
   }
}
