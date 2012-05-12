package net.minecraft.server;

import net.minecraft.server.OIRecipe;
import net.minecraft.server.OInventoryCrafting;
import net.minecraft.server.OItemStack;

public class OShapedRecipes implements OIRecipe {

   private int b;
   private int c;
   private OItemStack[] d;
   private OItemStack e;
   public final int a;


   public OShapedRecipes(int var1, int var2, OItemStack[] var3, OItemStack var4) {
      super();
      this.a = var4.c;
      this.b = var1;
      this.c = var2;
      this.d = var3;
      this.e = var4;
   }

   public OItemStack b() {
      return this.e;
   }

   public boolean a(OInventoryCrafting var1) {
      for(int var2 = 0; var2 <= 3 - this.b; ++var2) {
         for(int var3 = 0; var3 <= 3 - this.c; ++var3) {
            if(this.a(var1, var2, var3, true)) {
               return true;
            }

            if(this.a(var1, var2, var3, false)) {
               return true;
            }
         }
      }

      return false;
   }

   private boolean a(OInventoryCrafting var1, int var2, int var3, boolean var4) {
      for(int var5 = 0; var5 < 3; ++var5) {
         for(int var6 = 0; var6 < 3; ++var6) {
            int var7 = var5 - var2;
            int var8 = var6 - var3;
            OItemStack var9 = null;
            if(var7 >= 0 && var8 >= 0 && var7 < this.b && var8 < this.c) {
               if(var4) {
                  var9 = this.d[this.b - var7 - 1 + var8 * this.b];
               } else {
                  var9 = this.d[var7 + var8 * this.b];
               }
            }

            OItemStack var10 = var1.b(var5, var6);
            if(var10 != null || var9 != null) {
               if(var10 == null && var9 != null || var10 != null && var9 == null) {
                  return false;
               }

               if(var9.c != var10.c) {
                  return false;
               }

               if(var9.h() != -1 && var9.h() != var10.h()) {
                  return false;
               }
            }
         }
      }

      return true;
   }

   public OItemStack b(OInventoryCrafting var1) {
      return new OItemStack(this.e.c, this.e.a, this.e.h());
   }

   public int a() {
      return this.b * this.c;
   }
}
