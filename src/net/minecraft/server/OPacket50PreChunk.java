package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket50PreChunk extends OPacket {

   public int a;
   public int b;
   public boolean c;


   public OPacket50PreChunk() {
      super();
      this.p = false;
   }

   public OPacket50PreChunk(int var1, int var2, boolean var3) {
      super();
      this.p = false;
      this.a = var1;
      this.b = var2;
      this.c = var3;
   }

   public void a(DataInputStream var1) throws IOException {
      this.a = var1.readInt();
      this.b = var1.readInt();
      this.c = var1.read() != 0;
   }

   public void a(DataOutputStream var1) throws IOException {
      var1.writeInt(this.a);
      var1.writeInt(this.b);
      var1.write(this.c?1:0);
   }

   public void a(ONetHandler var1) {
      var1.a(this);
   }

   public int a() {
      return 9;
   }
}
