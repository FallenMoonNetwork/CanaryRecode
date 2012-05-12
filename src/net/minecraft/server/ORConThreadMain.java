package net.minecraft.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import net.minecraft.server.OIServer;
import net.minecraft.server.ORConThreadBase;
import net.minecraft.server.ORConThreadClient;

public class ORConThreadMain extends ORConThreadBase {

   private int g;
   private int h;
   private String i;
   private ServerSocket j = null;
   private String k;
   private HashMap l;


   public ORConThreadMain(OIServer var1) {
      super(var1);
      this.g = var1.a("rcon.port", 0);
      this.k = var1.a("rcon.password", "");
      this.i = var1.f();
      this.h = var1.g();
      if(0 == this.g) {
         this.g = this.h + 10;
         this.b("Setting default rcon port to " + this.g);
         var1.a("rcon.port", (Object)Integer.valueOf(this.g));
         if(0 == this.k.length()) {
            var1.a("rcon.password", (Object)"");
         }

         var1.c();
      }

      if(0 == this.i.length()) {
         this.i = "0.0.0.0";
      }

      this.e();
      this.j = null;
   }

   private void e() {
      this.l = new HashMap();
   }

   private void f() {
      Iterator var1 = this.l.entrySet().iterator();

      while(var1.hasNext()) {
         Entry var2 = (Entry)var1.next();
         if(!((ORConThreadClient)var2.getValue()).b()) {
            var1.remove();
         }
      }

   }

   public void run() {
      this.b("RCON running on " + this.i + ":" + this.g);

      while(true) {
         boolean var7 = false;

         try {
            var7 = true;
            if(!this.a) {
               var7 = false;
               break;
            }

            try {
               Socket var1 = this.j.accept();
               var1.setSoTimeout(500);
               ORConThreadClient var2 = new ORConThreadClient(this.b, var1);
               var2.a();
               this.l.put(var1.getRemoteSocketAddress(), var2);
               this.f();
            } catch (SocketTimeoutException var8) {
               this.f();
            } catch (IOException var9) {
               if(this.a) {
                  this.b("IO: " + var9.getMessage());
               }
            }
         } finally {
            if(var7) {
               this.a(this.j);
            }
         }
      }

      this.a(this.j);
   }

   public void a() {
      if(0 == this.k.length()) {
         this.c("No rcon password set in \'" + this.b.e() + "\', rcon disabled!");
      } else if(0 < this.g && '\uffff' >= this.g) {
         if(!this.a) {
            try {
               this.j = new ServerSocket(this.g, 0, InetAddress.getByName(this.i));
               this.j.setSoTimeout(500);
               super.a();
            } catch (IOException var2) {
               this.c("Unable to initialise rcon on " + this.i + ":" + this.g + " : " + var2.getMessage());
            }

         }
      } else {
         this.c("Invalid rcon port " + this.g + " found in \'" + this.b.e() + "\', rcon disabled!");
      }
   }
}
