package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;
import net.minecraft.server.OPotionEffect;

public class OPacket41EntityEffect extends OPacket {

   public int a;
   public byte b;
   public byte c;
   public short d;


   public OPacket41EntityEffect() {
      super();
   }

   public OPacket41EntityEffect(int var1, OPotionEffect var2) {
      super();
      this.a = var1;
      this.b = (byte)(var2.a() & 255);
      this.c = (byte)(var2.c() & 255);
      this.d = (short)var2.b();
   }

   public void a(DataInputStream var1) throws IOException {
      this.a = var1.readInt();
      this.b = var1.readByte();
      this.c = var1.readByte();
      this.d = var1.readShort();
   }

   public void a(DataOutputStream var1) throws IOException {
      var1.writeInt(this.a);
      var1.writeByte(this.b);
      var1.writeByte(this.c);
      var1.writeShort(this.d);
   }

   public void a(ONetHandler var1) {
      var1.a(this);
   }

   public int a() {
      return 8;
   }
}
