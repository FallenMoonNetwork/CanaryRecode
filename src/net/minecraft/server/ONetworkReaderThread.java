package net.minecraft.server;

import net.minecraft.server.ONetworkManager;

class ONetworkReaderThread extends Thread {

   // $FF: synthetic field
   final ONetworkManager a;


   ONetworkReaderThread(ONetworkManager var1, String var2) {
      super(var2);
      this.a = var1;
   }

   public void run() {
      Object var1 = ONetworkManager.a;
      synchronized(ONetworkManager.a) {
         ++ONetworkManager.b;
      }

      while(true) {
         boolean var12 = false;

         try {
            var12 = true;
            if(!ONetworkManager.a(this.a)) {
               var12 = false;
               break;
            }

            if(ONetworkManager.b(this.a)) {
               var12 = false;
               break;
            }

            while(ONetworkManager.c(this.a)) {
               ;
            }

            try {
               sleep(2L);
            } catch (InterruptedException var15) {
               ;
            }
         } finally {
            if(var12) {
               Object var5 = ONetworkManager.a;
               synchronized(ONetworkManager.a) {
                  --ONetworkManager.b;
               }
            }
         }
      }

      var1 = ONetworkManager.a;
      synchronized(ONetworkManager.a) {
         --ONetworkManager.b;
      }
   }
}
