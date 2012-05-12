package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.OChunkCoordIntPair;
import net.minecraft.server.OEntityPlayerMP;
import net.minecraft.server.OPacket;
import net.minecraft.server.OPacket50PreChunk;
import net.minecraft.server.OPacket51MapChunk;
import net.minecraft.server.OPacket52MultiBlockChange;
import net.minecraft.server.OPacket53BlockChange;
import net.minecraft.server.OPlayerManager;
import net.minecraft.server.OTileEntity;
import net.minecraft.server.OWorldServer;

class OPlayerInstance {

   private List b;
   private int c;
   private int d;
   private OChunkCoordIntPair e;
   private short[] f;
   private int g;
   private int h;
   // $FF: synthetic field
   final OPlayerManager a;


   public OPlayerInstance(OPlayerManager var1, int var2, int var3) {
      super();
      this.a = var1;
      this.b = new ArrayList();
      this.f = new short[64];
      this.g = 0;
      this.c = var2;
      this.d = var3;
      this.e = new OChunkCoordIntPair(var2, var3);
      var1.a().G.c(var2, var3);
   }

   public void a(OEntityPlayerMP var1) {
      if(this.b.contains(var1)) {
         throw new IllegalStateException("Failed to add player. " + var1 + " already is in chunk " + this.c + ", " + this.d);
      } else {
         var1.g.add(this.e);
         var1.a.b((OPacket)(new OPacket50PreChunk(this.e.a, this.e.b, true)));
         this.b.add(var1);
         var1.f.add(this.e);
      }
   }

   public void b(OEntityPlayerMP var1) {
      if(this.b.contains(var1)) {
         this.b.remove(var1);
         if(this.b.size() == 0) {
            long var2 = (long)this.c + 2147483647L | (long)this.d + 2147483647L << 32;
            OPlayerManager.a(this.a).d(var2);
            if(this.g > 0) {
               OPlayerManager.b(this.a).remove(this);
            }

            this.a.a().G.d(this.c, this.d);
         }

         var1.f.remove(this.e);
         if(var1.g.contains(this.e)) {
            var1.a.b((OPacket)(new OPacket50PreChunk(this.c, this.d, false)));
         }

      }
   }

   public void a(int var1, int var2, int var3) {
      if(this.g == 0) {
         OPlayerManager.b(this.a).add(this);
      }

      this.h |= 1 << (var2 >> 4);
      if(this.g < 64) {
         short var4 = (short)(var1 << 12 | var3 << 8 | var2);

         for(int var5 = 0; var5 < this.g; ++var5) {
            if(this.f[var5] == var4) {
               return;
            }
         }

         this.f[this.g++] = var4;
      }

   }

   public void a(OPacket var1) {
      for(int var2 = 0; var2 < this.b.size(); ++var2) {
         OEntityPlayerMP var3 = (OEntityPlayerMP)this.b.get(var2);
         if(var3.g.contains(this.e) && !var3.f.contains(this.e)) {
            var3.a.b(var1);
         }
      }

   }

   public void a() {
      OWorldServer var1 = this.a.a();
      if(this.g != 0) {
         int var2;
         int var3;
         int var4;
         if(this.g == 1) {
            var2 = this.c * 16 + (this.f[0] >> 12 & 15);
            var3 = this.f[0] & 255;
            var4 = this.d * 16 + (this.f[0] >> 8 & 15);
            this.a((OPacket)(new OPacket53BlockChange(var2, var3, var4, var1)));
            if(var1.h(var2, var3, var4)) {
               this.a(var1.b(var2, var3, var4));
            }
         } else {
            int var5;
            if(this.g == 64) {
               var2 = this.c * 16;
               var3 = this.d * 16;
               this.a((OPacket)(new OPacket51MapChunk(var1.d(this.c, this.d), false, this.h)));

               for(var4 = 0; var4 < 16; ++var4) {
                  if((this.h & 1 << var4) != 0) {
                     var5 = var4 << 4;
                     List var6 = var1.c(var2, var5, var3, var2 + 16, var5 + 16, var3 + 16);

                     for(int var7 = 0; var7 < var6.size(); ++var7) {
                        this.a((OTileEntity)var6.get(var7));
                     }
                  }
               }
            } else {
               this.a((OPacket)(new OPacket52MultiBlockChange(this.c, this.d, this.f, this.g, var1)));

               for(var2 = 0; var2 < this.g; ++var2) {
                  var3 = this.c * 16 + (this.f[var2] >> 12 & 15);
                  var4 = this.f[var2] & 255;
                  var5 = this.d * 16 + (this.f[var2] >> 8 & 15);
                  if(var1.h(var3, var4, var5)) {
                     this.a(var1.b(var3, var4, var5));
                  }
               }
            }
         }

         this.g = 0;
         this.h = 0;
      }
   }

   private void a(OTileEntity var1) {
      if(var1 != null) {
         OPacket var2 = var1.d();
         if(var2 != null) {
            this.a(var2);
         }
      }

   }
}
