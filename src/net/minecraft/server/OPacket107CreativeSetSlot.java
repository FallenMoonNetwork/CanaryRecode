package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.OItemStack;
import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket107CreativeSetSlot extends OPacket {

   public int a;
   public OItemStack b;


   public OPacket107CreativeSetSlot() {
      super();
   }

   public void a(ONetHandler var1) {
      var1.a(this);
   }

   public void a(DataInputStream var1) throws IOException {
      this.a = var1.readShort();
      this.b = this.b(var1);
   }

   public void a(DataOutputStream var1) throws IOException {
      var1.writeShort(this.a);
      this.a(this.b, var1);
   }

   public int a() {
      return 8;
   }
}
