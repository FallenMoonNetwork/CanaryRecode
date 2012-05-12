package net.minecraft.server;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import net.minecraft.server.OMinecraftServer;

final class OServerWindowAdapter extends WindowAdapter {

   // $FF: synthetic field
   final OMinecraftServer a;


   OServerWindowAdapter(OMinecraftServer var1) {
      super();
      this.a = var1;
   }

   public void windowClosing(WindowEvent var1) {
      this.a.a();

      while(!this.a.i) {
         try {
            Thread.sleep(100L);
         } catch (InterruptedException var3) {
            var3.printStackTrace();
         }
      }

      System.exit(0);
   }
}
