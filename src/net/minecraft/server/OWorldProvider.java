package net.minecraft.server;

import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OBlock;
import net.minecraft.server.OChunkCoordinates;
import net.minecraft.server.OChunkProviderFlat;
import net.minecraft.server.OChunkProviderGenerate;
import net.minecraft.server.OEnumWorldType;
import net.minecraft.server.OIChunkProvider;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldChunkManager;
import net.minecraft.server.OWorldChunkManagerHell;
import net.minecraft.server.OWorldProviderEnd;
import net.minecraft.server.OWorldProviderHell;
import net.minecraft.server.OWorldProviderSurface;

public abstract class OWorldProvider {

   public OWorld a;
   public OEnumWorldType b;
   public OWorldChunkManager c;
   public boolean d = false;
   public boolean e = false;
   public float[] f = new float[16];
   public int g = 0;
   private float[] h = new float[4];


   public OWorldProvider() {
      super();
   }

   public final void a(OWorld var1) {
      this.a = var1;
      this.b = var1.s().p();
      this.a();
      this.g();
   }

   protected void g() {
      float var1 = 0.0F;

      for(int var2 = 0; var2 <= 15; ++var2) {
         float var3 = 1.0F - (float)var2 / 15.0F;
         this.f[var2] = (1.0F - var3) / (var3 * 3.0F + 1.0F) * (1.0F - var1) + var1;
      }

   }

   protected void a() {
      if(this.a.s().p() == OEnumWorldType.c) {
         this.c = new OWorldChunkManagerHell(OBiomeGenBase.c, 0.5F, 0.5F);
      } else {
         this.c = new OWorldChunkManager(this.a);
      }

   }

   public OIChunkProvider b() {
      return (OIChunkProvider)(this.b == OEnumWorldType.c?new OChunkProviderFlat(this.a, this.a.n(), this.a.s().n()):new OChunkProviderGenerate(this.a, this.a.n(), this.a.s().n()));
   }

   public boolean a(int var1, int var2) {
      int var3 = this.a.b(var1, var2);
      return var3 == OBlock.u.bO;
   }

   public float a(long var1, float var3) {
      int var4 = (int)(var1 % 24000L);
      float var5 = ((float)var4 + var3) / 24000.0F - 0.25F;
      if(var5 < 0.0F) {
         ++var5;
      }

      if(var5 > 1.0F) {
         --var5;
      }

      float var6 = var5;
      var5 = 1.0F - (float)((Math.cos((double)var5 * 3.141592653589793D) + 1.0D) / 2.0D);
      var5 = var6 + (var5 - var6) / 3.0F;
      return var5;
   }

   public boolean d() {
      return true;
   }

   public boolean c() {
      return true;
   }

   public static OWorldProvider a(int var0) {
      return (OWorldProvider)(var0 == -1?new OWorldProviderHell():(var0 == 0?new OWorldProviderSurface():(var0 == 1?new OWorldProviderEnd():null)));
   }

   public OChunkCoordinates e() {
      return null;
   }

   public int f() {
      return this.b == OEnumWorldType.c?4:64;
   }
}
