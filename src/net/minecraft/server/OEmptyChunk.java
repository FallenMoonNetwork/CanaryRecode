package net.minecraft.server;

import java.util.List;
import java.util.Random;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OChunk;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEnumSkyBlock;
import net.minecraft.server.OTileEntity;
import net.minecraft.server.OWorld;

public class OEmptyChunk extends OChunk {

   public OEmptyChunk(OWorld var1, int var2, int var3) {
      super(var1, var2, var3);
   }

   public boolean a(int var1, int var2) {
      return var1 == this.g && var2 == this.h;
   }

   public int b(int var1, int var2) {
      return 0;
   }

   public void a() {}

   public void b() {}

   public int a(int var1, int var2, int var3) {
      return 0;
   }

   public int b(int var1, int var2, int var3) {
      return 255;
   }

   public boolean a(int var1, int var2, int var3, int var4, int var5) {
      return true;
   }

   public boolean a(int var1, int var2, int var3, int var4) {
      return true;
   }

   public int c(int var1, int var2, int var3) {
      return 0;
   }

   public boolean b(int var1, int var2, int var3, int var4) {
      return false;
   }

   public int a(OEnumSkyBlock var1, int var2, int var3, int var4) {
      return 0;
   }

   public void a(OEnumSkyBlock var1, int var2, int var3, int var4, int var5) {}

   public int c(int var1, int var2, int var3, int var4) {
      return 0;
   }

   public void a(OEntity var1) {}

   public void b(OEntity var1) {}

   public void a(OEntity var1, int var2) {}

   public boolean d(int var1, int var2, int var3) {
      return false;
   }

   public OTileEntity e(int var1, int var2, int var3) {
      return null;
   }

   public void a(OTileEntity var1) {}

   public void a(int var1, int var2, int var3, OTileEntity var4) {}

   public void f(int var1, int var2, int var3) {}

   public void c() {}

   public void d() {}

   public void e() {}

   public void a(OEntity var1, OAxisAlignedBB var2, List var3) {}

   public void a(Class var1, OAxisAlignedBB var2, List var3) {}

   public boolean a(boolean var1) {
      return false;
   }

   public Random a(long var1) {
      return new Random(this.e.n() + (long)(this.g * this.g * 4987142) + (long)(this.g * 5947611) + (long)(this.h * this.h) * 4392871L + (long)(this.h * 389711) ^ var1);
   }

   public boolean f() {
      return true;
   }

   public boolean c(int var1, int var2) {
      return true;
   }
}
