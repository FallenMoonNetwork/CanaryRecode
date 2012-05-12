package net.minecraft.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import net.minecraft.server.OICommandListener;
import net.minecraft.server.OMinecraftServer;

public class OThreadCommandReader extends Thread {

   // $FF: synthetic field
   final OMinecraftServer a;


   public OThreadCommandReader(OMinecraftServer var1) {
      super();
      this.a = var1;
   }

   public void run() {
      BufferedReader var1 = new BufferedReader(new InputStreamReader(System.in));
      String var2 = null;

      try {
         while(!this.a.i && OMinecraftServer.a(this.a) && (var2 = var1.readLine()) != null) {
            this.a.a(var2, (OICommandListener)this.a);
         }
      } catch (IOException var4) {
         var4.printStackTrace();
      }

   }
}
