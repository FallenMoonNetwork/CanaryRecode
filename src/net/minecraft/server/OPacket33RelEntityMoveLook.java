package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.OPacket30Entity;

public class OPacket33RelEntityMoveLook extends OPacket30Entity {

   public OPacket33RelEntityMoveLook() {
      super();
      this.g = true;
   }

   public OPacket33RelEntityMoveLook(int var1, byte var2, byte var3, byte var4, byte var5, byte var6) {
      super(var1);
      this.b = var2;
      this.c = var3;
      this.d = var4;
      this.e = var5;
      this.f = var6;
      this.g = true;
   }

   public void a(DataInputStream var1) throws IOException {
      super.a(var1);
      this.b = var1.readByte();
      this.c = var1.readByte();
      this.d = var1.readByte();
      this.e = var1.readByte();
      this.f = var1.readByte();
   }

   public void a(DataOutputStream var1) throws IOException {
      super.a(var1);
      var1.writeByte(this.b);
      var1.writeByte(this.c);
      var1.writeByte(this.d);
      var1.writeByte(this.e);
      var1.writeByte(this.f);
   }

   public int a() {
      return 9;
   }
}
