package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;
import net.minecraft.server.ONBTBase;

public class ONBTTagIntArray extends ONBTBase {

   public int[] a;


   public ONBTTagIntArray(String var1) {
      super(var1);
   }

   public ONBTTagIntArray(String var1, int[] var2) {
      super(var1);
      this.a = var2;
   }

   void a(DataOutput var1) throws IOException {
      var1.writeInt(this.a.length);

      for(int var2 = 0; var2 < this.a.length; ++var2) {
         var1.writeInt(this.a[var2]);
      }

   }

   void a(DataInput var1) throws IOException {
      int var2 = var1.readInt();
      this.a = new int[var2];

      for(int var3 = 0; var3 < var2; ++var3) {
         this.a[var3] = var1.readInt();
      }

   }

   public byte a() {
      return (byte)11;
   }

   public String toString() {
      return "[" + this.a.length + " bytes]";
   }

   public ONBTBase b() {
      int[] var1 = new int[this.a.length];
      System.arraycopy(this.a, 0, var1, 0, this.a.length);
      return new ONBTTagIntArray(this.c(), var1);
   }

   public boolean equals(Object var1) {
      if(!super.equals(var1)) {
         return false;
      } else {
         ONBTTagIntArray var2 = (ONBTTagIntArray)var1;
         return this.a == null && var2.a == null || this.a != null && this.a.equals(var2.a);
      }
   }

   public int hashCode() {
      return super.hashCode() ^ Arrays.hashCode(this.a);
   }
}
