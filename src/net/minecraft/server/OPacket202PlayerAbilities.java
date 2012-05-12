package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;
import net.minecraft.server.OPlayerCapabilities;

public class OPacket202PlayerAbilities extends OPacket {

   public boolean a = false;
   public boolean b = false;
   public boolean c = false;
   public boolean d = false;


   public OPacket202PlayerAbilities() {
      super();
   }

   public OPacket202PlayerAbilities(OPlayerCapabilities var1) {
      super();
      this.a = var1.a;
      this.b = var1.b;
      this.c = var1.c;
      this.d = var1.d;
   }

   public void a(DataInputStream var1) throws IOException {
      this.a = var1.readBoolean();
      this.b = var1.readBoolean();
      this.c = var1.readBoolean();
      this.d = var1.readBoolean();
   }

   public void a(DataOutputStream var1) throws IOException {
      var1.writeBoolean(this.a);
      var1.writeBoolean(this.b);
      var1.writeBoolean(this.c);
      var1.writeBoolean(this.d);
   }

   public void a(ONetHandler var1) {
      var1.a(this);
   }

   public int a() {
      return 1;
   }
}
