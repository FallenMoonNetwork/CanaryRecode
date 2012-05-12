package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket254ServerPing extends OPacket {

   public OPacket254ServerPing() {
      super();
   }

   public void a(DataInputStream var1) {}

   public void a(DataOutputStream var1) {}

   public void a(ONetHandler var1) {
      var1.a(this);
   }

   public int a() {
      return 0;
   }
}
