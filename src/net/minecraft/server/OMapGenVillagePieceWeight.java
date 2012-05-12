package net.minecraft.server;


public class OMapGenVillagePieceWeight {

   public Class a;
   public final int b;
   public int c;
   public int d;


   public OMapGenVillagePieceWeight(Class var1, int var2, int var3) {
      super();
      this.a = var1;
      this.b = var2;
      this.d = var3;
   }

   public boolean a(int var1) {
      return this.d == 0 || this.c < this.d;
   }

   public boolean a() {
      return this.d == 0 || this.c < this.d;
   }
}
