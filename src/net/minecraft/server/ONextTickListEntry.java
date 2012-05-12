package net.minecraft.server;


public class ONextTickListEntry implements Comparable {

   private static long f = 0L;
   public int a;
   public int b;
   public int c;
   public int d;
   public long e;
   private long g;


   public ONextTickListEntry(int var1, int var2, int var3, int var4) {
      super();
      this.g = (long)(f++);
      this.a = var1;
      this.b = var2;
      this.c = var3;
      this.d = var4;
   }

   public boolean equals(Object var1) {
      if(!(var1 instanceof ONextTickListEntry)) {
         return false;
      } else {
         ONextTickListEntry var2 = (ONextTickListEntry)var1;
         return this.a == var2.a && this.b == var2.b && this.c == var2.c && this.d == var2.d;
      }
   }

   public int hashCode() {
      return (this.a * 1024 * 1024 + this.c * 1024 + this.b) * 256 + this.d;
   }

   public ONextTickListEntry a(long var1) {
      this.e = var1;
      return this;
   }

   public int a(ONextTickListEntry var1) {
      return this.e < var1.e?-1:(this.e > var1.e?1:(this.g < var1.g?-1:(this.g > var1.g?1:0)));
   }

   // $FF: synthetic method
   // $FF: bridge method
   public int compareTo(Object var1) {
      return this.a((ONextTickListEntry)var1);
   }

}
