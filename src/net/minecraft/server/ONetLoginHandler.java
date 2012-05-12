package net.minecraft.server;

import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Logger;
import net.minecraft.server.OChunkCoordinates;
import net.minecraft.server.OEntityPlayerMP;
import net.minecraft.server.OMinecraftServer;
import net.minecraft.server.ONetHandler;
import net.minecraft.server.ONetServerHandler;
import net.minecraft.server.ONetworkManager;
import net.minecraft.server.OPacket;
import net.minecraft.server.OPacket1Login;
import net.minecraft.server.OPacket202PlayerAbilities;
import net.minecraft.server.OPacket254ServerPing;
import net.minecraft.server.OPacket255KickDisconnect;
import net.minecraft.server.OPacket2Handshake;
import net.minecraft.server.OPacket3Chat;
import net.minecraft.server.OPacket41EntityEffect;
import net.minecraft.server.OPacket4UpdateTime;
import net.minecraft.server.OPacket6SpawnPosition;
import net.minecraft.server.OPotionEffect;
import net.minecraft.server.OThreadLoginVerifier;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldServer;

public class ONetLoginHandler extends ONetHandler {

   public static Logger a = Logger.getLogger("Minecraft");
   private static Random d = new Random();
   public ONetworkManager b;
   public boolean c = false;
   private OMinecraftServer e;
   private int f = 0;
   private String g = null;
   private OPacket1Login h = null;
   private String i = "";


   public ONetLoginHandler(OMinecraftServer var1, Socket var2, String var3) throws IOException {
      super();
      this.e = var1;
      this.b = new ONetworkManager(var2, var3, this);
      this.b.f = 0;
   }

   public void a() {
      if(this.h != null) {
         this.b(this.h);
         this.h = null;
      }

      if(this.f++ == 600) {
         this.a("Took too long to log in");
      } else {
         this.b.b();
      }

   }

   public void a(String var1) {
      try {
         a.info("Disconnecting " + this.b() + ": " + var1);
         this.b.a((OPacket)(new OPacket255KickDisconnect(var1)));
         this.b.d();
         this.c = true;
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   public void a(OPacket2Handshake var1) {
      if(this.e.n) {
         this.i = Long.toString(d.nextLong(), 16);
         this.b.a((OPacket)(new OPacket2Handshake(this.i)));
      } else {
         this.b.a((OPacket)(new OPacket2Handshake("-")));
      }

   }

   public void a(OPacket1Login var1) {
      this.g = var1.b;
      if(var1.a != 29) {
         if(var1.a > 29) {
            this.a("Outdated server!");
         } else {
            this.a("Outdated client!");
         }

      } else {
         if(!this.e.n) {
            this.b(var1);
         } else {
            (new OThreadLoginVerifier(this, var1)).start();
         }

      }
   }

   public void b(OPacket1Login var1) {
      OEntityPlayerMP var2 = this.e.h.a(this, var1.b);
      if(var2 != null) {
         this.e.h.b(var2);
         var2.a((OWorld)this.e.a(var2.w));
         var2.c.a((OWorldServer)var2.bi);
         a.info(this.b() + " logged in with entity id " + var2.bd + " at (" + var2.bm + ", " + var2.bn + ", " + var2.bo + ")");
         OWorldServer var3 = this.e.a(var2.w);
         OChunkCoordinates var4 = var3.p();
         var2.c.b(var3.s().m());
         ONetServerHandler var5 = new ONetServerHandler(this.e, this.b, var2);
         var5.b((OPacket)(new OPacket1Login("", var2.bd, var3.s().p(), var2.c.a(), var3.t.g, (byte)var3.q, (byte)var3.y(), (byte)this.e.h.k())));
         var5.b((OPacket)(new OPacket6SpawnPosition(var4.a, var4.b, var4.c)));
         var5.b((OPacket)(new OPacket202PlayerAbilities(var2.L)));
         this.e.h.a(var2, var3);
         this.e.h.a((OPacket)(new OPacket3Chat("\u00a7e" + var2.v + " joined the game.")));
         this.e.h.c(var2);
         var5.a(var2.bm, var2.bn, var2.bo, var2.bs, var2.bt);
         this.e.c.a(var5);
         var5.b((OPacket)(new OPacket4UpdateTime(var3.o())));
         Iterator var6 = var2.aM().iterator();

         while(var6.hasNext()) {
            OPotionEffect var7 = (OPotionEffect)var6.next();
            var5.b((OPacket)(new OPacket41EntityEffect(var2.bd, var7)));
         }

         var2.x();
      }

      this.c = true;
   }

   public void a(String var1, Object[] var2) {
      a.info(this.b() + " lost connection");
      this.c = true;
   }

   public void a(OPacket254ServerPing var1) {
      try {
         String var2 = this.e.s + "\u00a7" + this.e.h.j() + "\u00a7" + this.e.h.k();
         this.b.a((OPacket)(new OPacket255KickDisconnect(var2)));
         this.b.d();
         this.e.c.a(this.b.f());
         this.c = true;
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   public void a(OPacket var1) {
      this.a("Protocol error");
   }

   public String b() {
      return this.g != null?this.g + " [" + this.b.c().toString() + "]":this.b.c().toString();
   }

   public boolean c() {
      return true;
   }

   // $FF: synthetic method
   static String a(ONetLoginHandler var0) {
      return var0.i;
   }

   // $FF: synthetic method
   static OPacket1Login a(ONetLoginHandler var0, OPacket1Login var1) {
      return var0.h = var1;
   }

}
