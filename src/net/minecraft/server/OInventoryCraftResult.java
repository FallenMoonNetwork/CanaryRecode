package net.minecraft.server;

import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OItemStack;

public class OInventoryCraftResult implements OIInventory {

   private OItemStack[] a = new OItemStack[1];


   public OInventoryCraftResult() {
      super();
   }

   public int c() {
      return 1;
   }

   public OItemStack g_(int var1) {
      return this.a[var1];
   }

   public String e() {
      return "Result";
   }

   public OItemStack a(int var1, int var2) {
      if(this.a[var1] != null) {
         OItemStack var3 = this.a[var1];
         this.a[var1] = null;
         return var3;
      } else {
         return null;
      }
   }

   public OItemStack b(int var1) {
      if(this.a[var1] != null) {
         OItemStack var2 = this.a[var1];
         this.a[var1] = null;
         return var2;
      } else {
         return null;
      }
   }

   public void a(int var1, OItemStack var2) {
      this.a[var1] = var2;
   }

   public int a() {
      return 64;
   }

   public void G_() {}

   public boolean a(OEntityPlayer var1) {
      return true;
   }

   public void f() {}

   public void g() {}
}
