package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.OPacket10Flying;

public class OPacket11PlayerPosition extends OPacket10Flying {

   public OPacket11PlayerPosition() {
      super();
      this.h = true;
   }

   public void a(DataInputStream var1) throws IOException {
      this.a = var1.readDouble();
      this.b = var1.readDouble();
      this.d = var1.readDouble();
      this.c = var1.readDouble();
      super.a(var1);
   }

   public void a(DataOutputStream var1) throws IOException {
      var1.writeDouble(this.a);
      var1.writeDouble(this.b);
      var1.writeDouble(this.d);
      var1.writeDouble(this.c);
      super.a(var1);
   }

   public int a() {
      return 33;
   }
}
