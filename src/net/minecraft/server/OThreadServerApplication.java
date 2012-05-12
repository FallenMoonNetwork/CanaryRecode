package net.minecraft.server;

import net.minecraft.server.OMinecraftServer;

public final class OThreadServerApplication extends Thread {

   // $FF: synthetic field
   final OMinecraftServer a;


   public OThreadServerApplication(String var1, OMinecraftServer var2) {
      super(var1);
      this.a = var2;
   }

   public void run() {
      this.a.run();
   }
}
