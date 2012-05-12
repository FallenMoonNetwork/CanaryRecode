package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.OMathHelper;

public class OVec3D {

   private static List d = new ArrayList();
   private static int e = 0;
   public double a;
   public double b;
   public double c;


   public static OVec3D a(double var0, double var2, double var4) {
      return new OVec3D(var0, var2, var4);
   }

   public static void a() {
      e = 0;
   }

   public static OVec3D b(double var0, double var2, double var4) {
      if(e >= d.size()) {
         d.add(a(0.0D, 0.0D, 0.0D));
      }

      return ((OVec3D)d.get(e++)).e(var0, var2, var4);
   }

   private OVec3D(double var1, double var3, double var5) {
      super();
      if(var1 == -0.0D) {
         var1 = 0.0D;
      }

      if(var3 == -0.0D) {
         var3 = 0.0D;
      }

      if(var5 == -0.0D) {
         var5 = 0.0D;
      }

      this.a = var1;
      this.b = var3;
      this.c = var5;
   }

   private OVec3D e(double var1, double var3, double var5) {
      this.a = var1;
      this.b = var3;
      this.c = var5;
      return this;
   }

   public OVec3D b() {
      double var1 = (double)OMathHelper.a(this.a * this.a + this.b * this.b + this.c * this.c);
      return var1 < 1.0E-4D?b(0.0D, 0.0D, 0.0D):b(this.a / var1, this.b / var1, this.c / var1);
   }

   public double a(OVec3D var1) {
      return this.a * var1.a + this.b * var1.b + this.c * var1.c;
   }

   public OVec3D c(double var1, double var3, double var5) {
      return b(this.a + var1, this.b + var3, this.c + var5);
   }

   public double b(OVec3D var1) {
      double var2 = var1.a - this.a;
      double var4 = var1.b - this.b;
      double var6 = var1.c - this.c;
      return (double)OMathHelper.a(var2 * var2 + var4 * var4 + var6 * var6);
   }

   public double c(OVec3D var1) {
      double var2 = var1.a - this.a;
      double var4 = var1.b - this.b;
      double var6 = var1.c - this.c;
      return var2 * var2 + var4 * var4 + var6 * var6;
   }

   public double d(double var1, double var3, double var5) {
      double var7 = var1 - this.a;
      double var9 = var3 - this.b;
      double var11 = var5 - this.c;
      return var7 * var7 + var9 * var9 + var11 * var11;
   }

   public double c() {
      return (double)OMathHelper.a(this.a * this.a + this.b * this.b + this.c * this.c);
   }

   public OVec3D a(OVec3D var1, double var2) {
      double var4 = var1.a - this.a;
      double var6 = var1.b - this.b;
      double var8 = var1.c - this.c;
      if(var4 * var4 < 1.0000000116860974E-7D) {
         return null;
      } else {
         double var10 = (var2 - this.a) / var4;
         return var10 >= 0.0D && var10 <= 1.0D?b(this.a + var4 * var10, this.b + var6 * var10, this.c + var8 * var10):null;
      }
   }

   public OVec3D b(OVec3D var1, double var2) {
      double var4 = var1.a - this.a;
      double var6 = var1.b - this.b;
      double var8 = var1.c - this.c;
      if(var6 * var6 < 1.0000000116860974E-7D) {
         return null;
      } else {
         double var10 = (var2 - this.b) / var6;
         return var10 >= 0.0D && var10 <= 1.0D?b(this.a + var4 * var10, this.b + var6 * var10, this.c + var8 * var10):null;
      }
   }

   public OVec3D c(OVec3D var1, double var2) {
      double var4 = var1.a - this.a;
      double var6 = var1.b - this.b;
      double var8 = var1.c - this.c;
      if(var8 * var8 < 1.0000000116860974E-7D) {
         return null;
      } else {
         double var10 = (var2 - this.c) / var8;
         return var10 >= 0.0D && var10 <= 1.0D?b(this.a + var4 * var10, this.b + var6 * var10, this.c + var8 * var10):null;
      }
   }

   public String toString() {
      return "(" + this.a + ", " + this.b + ", " + this.c + ")";
   }

   public void a(float var1) {
      float var2 = OMathHelper.b(var1);
      float var3 = OMathHelper.a(var1);
      double var4 = this.a;
      double var6 = this.b * (double)var2 + this.c * (double)var3;
      double var8 = this.c * (double)var2 - this.b * (double)var3;
      this.a = var4;
      this.b = var6;
      this.c = var8;
   }

   public void b(float var1) {
      float var2 = OMathHelper.b(var1);
      float var3 = OMathHelper.a(var1);
      double var4 = this.a * (double)var2 + this.c * (double)var3;
      double var6 = this.b;
      double var8 = this.c * (double)var2 - this.a * (double)var3;
      this.a = var4;
      this.b = var6;
      this.c = var8;
   }

}
