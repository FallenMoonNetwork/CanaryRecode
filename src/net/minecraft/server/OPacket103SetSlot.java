package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.OItemStack;
import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket103SetSlot extends OPacket {

   public int a;
   public int b;
   public OItemStack c;


   public OPacket103SetSlot() {
      super();
   }

   public OPacket103SetSlot(int var1, int var2, OItemStack var3) {
      super();
      this.a = var1;
      this.b = var2;
      this.c = var3 == null?var3:var3.j();
   }

   public void a(ONetHandler var1) {
      var1.a(this);
   }

   public void a(DataInputStream var1) throws IOException {
      this.a = var1.readByte();
      this.b = var1.readShort();
      this.c = this.b(var1);
   }

   public void a(DataOutputStream var1) throws IOException {
      var1.writeByte(this.a);
      var1.writeShort(this.b);
      this.a(this.c, var1);
   }

   public int a() {
      return 8;
   }
}
