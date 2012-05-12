package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.server.ONBTBase;

public class ONBTTagDouble extends ONBTBase {

   public double a;


   public ONBTTagDouble(String var1) {
      super(var1);
   }

   public ONBTTagDouble(String var1, double var2) {
      super(var1);
      this.a = var2;
   }

   void a(DataOutput var1) {
      try {
        var1.writeDouble(this.a);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
   }

   void a(DataInput var1) {
      try {
        this.a = var1.readDouble();
    } catch (IOException e) {
        // TODO Auto-generated catch block TODO: LOOK IN MCP SOURCE IF THEY THROW OR CATCH!!! 
        e.printStackTrace();
    }
   }

   public byte a() {
      return (byte)6;
   }

   public String toString() {
      return "" + this.a;
   }

   public ONBTBase b() {
      return new ONBTTagDouble(this.c(), this.a);
   }

   public boolean equals(Object var1) {
      if(super.equals(var1)) {
         ONBTTagDouble var2 = (ONBTTagDouble)var1;
         return this.a == var2.a;
      } else {
         return false;
      }
   }

   public int hashCode() {
      long var1 = Double.doubleToLongBits(this.a);
      return super.hashCode() ^ (int)(var1 ^ var1 >>> 32);
   }
}
