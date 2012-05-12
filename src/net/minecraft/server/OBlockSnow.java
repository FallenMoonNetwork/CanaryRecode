package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityItem;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEnumSkyBlock;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OStatList;
import net.minecraft.server.OWorld;

public class OBlockSnow extends OBlock {

   protected OBlockSnow(int var1, int var2) {
      super(var1, var2, OMaterial.v);
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
      this.a(true);
   }

   public OAxisAlignedBB e(OWorld var1, int var2, int var3, int var4) {
      int var5 = var1.c(var2, var3, var4) & 7;
      return var5 >= 3?OAxisAlignedBB.b((double)var2 + this.bV, (double)var3 + this.bW, (double)var4 + this.bX, (double)var2 + this.bY, (double)((float)var3 + 0.5F), (double)var4 + this.ca):null;
   }

   public boolean a() {
      return false;
   }

   public boolean b() {
      return false;
   }

   public void a(OIBlockAccess var1, int var2, int var3, int var4) {
      int var5 = var1.c(var2, var3, var4) & 7;
      float var6 = (float)(2 * (1 + var5)) / 16.0F;
      this.a(0.0F, 0.0F, 0.0F, 1.0F, var6, 1.0F);
   }

   public boolean c(OWorld var1, int var2, int var3, int var4) {
      int var5 = var1.a(var2, var3 - 1, var4);
      return var5 != 0 && (var5 == OBlock.K.bO || OBlock.m[var5].a())?var1.d(var2, var3 - 1, var4).c():false;
   }

   public void a(OWorld var1, int var2, int var3, int var4, int var5) {
      this.g(var1, var2, var3, var4);
   }

   private boolean g(OWorld var1, int var2, int var3, int var4) {
      if(!this.c(var1, var2, var3, var4)) {
         this.b(var1, var2, var3, var4, var1.c(var2, var3, var4), 0);
         var1.e(var2, var3, var4, 0);
         return false;
      } else {
         return true;
      }
   }

   public void a(OWorld var1, OEntityPlayer var2, int var3, int var4, int var5, int var6) {
      int var7 = OItem.aC.bP;
      float var8 = 0.7F;
      double var9 = (double)(var1.r.nextFloat() * var8) + (double)(1.0F - var8) * 0.5D;
      double var11 = (double)(var1.r.nextFloat() * var8) + (double)(1.0F - var8) * 0.5D;
      double var13 = (double)(var1.r.nextFloat() * var8) + (double)(1.0F - var8) * 0.5D;
      OEntityItem var15 = new OEntityItem(var1, (double)var3 + var9, (double)var4 + var11, (double)var5 + var13, new OItemStack(var7, 1, 0));
      var15.c = 10;
      var1.b((OEntity)var15);
      var1.e(var3, var4, var5, 0);
      var2.a(OStatList.C[this.bO], 1);
   }

   public int a(int var1, Random var2, int var3) {
      return OItem.aC.bP;
   }

   public int a(Random var1) {
      return 0;
   }

   public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
      if(var1.a(OEnumSkyBlock.b, var2, var3, var4) > 11) {
         this.b(var1, var2, var3, var4, var1.c(var2, var3, var4), 0);
         var1.e(var2, var3, var4, 0);
      }

   }
}
