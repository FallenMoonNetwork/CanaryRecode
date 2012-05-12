package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.OItemStack;
import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket15Place extends OPacket {

   public int a;
   public int b;
   public int c;
   public int d;
   public OItemStack e;


   public OPacket15Place() {
      super();
   }

   public void a(DataInputStream var1) throws IOException {
      this.a = var1.readInt();
      this.b = var1.read();
      this.c = var1.readInt();
      this.d = var1.read();
      this.e = this.b(var1);
   }

   public void a(DataOutputStream var1) throws IOException {
      var1.writeInt(this.a);
      var1.write(this.b);
      var1.writeInt(this.c);
      var1.write(this.d);
      this.a(this.e, var1);
   }

   public void a(ONetHandler var1) {
      var1.a(this);
   }

   public int a() {
      return 15;
   }
}
