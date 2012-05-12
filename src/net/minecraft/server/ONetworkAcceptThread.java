package net.minecraft.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import net.minecraft.server.OMinecraftServer;
import net.minecraft.server.ONetLoginHandler;
import net.minecraft.server.ONetworkListenThread;

class ONetworkAcceptThread extends Thread {

   // $FF: synthetic field
   final OMinecraftServer a;
   // $FF: synthetic field
   final ONetworkListenThread b;


   ONetworkAcceptThread(ONetworkListenThread var1, String var2, OMinecraftServer var3) {
      super(var2);
      this.b = var1;
      this.a = var3;
   }

   public void run() {
      while(this.b.b) {
         try {
            Socket var1 = ONetworkListenThread.a(this.b).accept();
            if(var1 != null) {
               synchronized(ONetworkListenThread.b(this.b)) {
                  InetAddress var3 = var1.getInetAddress();
                  if(ONetworkListenThread.b(this.b).containsKey(var3) && !"127.0.0.1".equals(var3.getHostAddress()) && System.currentTimeMillis() - ((Long)ONetworkListenThread.b(this.b).get(var3)).longValue() < 4000L) {
                     ONetworkListenThread.b(this.b).put(var3, Long.valueOf(System.currentTimeMillis()));
                     var1.close();
                     continue;
                  }

                  ONetworkListenThread.b(this.b).put(var3, Long.valueOf(System.currentTimeMillis()));
               }

               ONetLoginHandler var2 = new ONetLoginHandler(this.a, var1, "Connection #" + ONetworkListenThread.c(this.b));
               ONetworkListenThread.a(this.b, var2);
            }
         } catch (IOException var6) {
            var6.printStackTrace();
         }
      }

   }
}
