package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket70Bed extends OPacket {

   public static final String[] a = new String[]{"tile.bed.notValid", null, null, "gameMode.changed"};
   public int b;
   public int c;


   public OPacket70Bed() {
      super();
   }

   public OPacket70Bed(int var1, int var2) {
      super();
      this.b = var1;
      this.c = var2;
   }

   public void a(DataInputStream var1) throws IOException {
      this.b = var1.readByte();
      this.c = var1.readByte();
   }

   public void a(DataOutputStream var1) throws IOException {
      var1.writeByte(this.b);
      var1.writeByte(this.c);
   }

   public void a(ONetHandler var1) {
      var1.a(this);
   }

   public int a() {
      return 2;
   }

}
