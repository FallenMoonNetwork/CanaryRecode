package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket43Experience extends OPacket {

   public float a;
   public int b;
   public int c;


   public OPacket43Experience() {
      super();
   }

   public OPacket43Experience(float var1, int var2, int var3) {
      super();
      this.a = var1;
      this.b = var2;
      this.c = var3;
   }

   public void a(DataInputStream var1) throws IOException {
      this.a = var1.readFloat();
      this.c = var1.readShort();
      this.b = var1.readShort();
   }

   public void a(DataOutputStream var1) throws IOException {
      var1.writeFloat(this.a);
      var1.writeShort(this.c);
      var1.writeShort(this.b);
   }

   public void a(ONetHandler var1) {
      var1.a(this);
   }

   public int a() {
      return 4;
   }
}
