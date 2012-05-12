package net.minecraft.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.server.OChunk;
import net.minecraft.server.OChunkCoordIntPair;
import net.minecraft.server.OChunkCoordinates;
import net.minecraft.server.OChunkPosition;
import net.minecraft.server.OEmptyChunk;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEnumCreatureType;
import net.minecraft.server.OIChunkLoader;
import net.minecraft.server.OIChunkProvider;
import net.minecraft.server.OIProgressUpdate;
import net.minecraft.server.OLongHashMap;
import net.minecraft.server.OWorld;

public class OChunkProvider implements OIChunkProvider {

   private Set a = new HashSet();
   private OChunk b;
   private OIChunkProvider c;
   private OIChunkLoader d;
   private OLongHashMap e = new OLongHashMap();
   private List f = new ArrayList();
   private OWorld g;
   private int h;


   public OChunkProvider(OWorld var1, OIChunkLoader var2, OIChunkProvider var3) {
      super();
      this.b = new OEmptyChunk(var1, 0, 0);
      this.g = var1;
      this.d = var2;
      this.c = var3;
   }

   public boolean a(int var1, int var2) {
      return this.e.b(OChunkCoordIntPair.a(var1, var2));
   }

   public void d(int var1, int var2) {
      OChunkCoordinates var3 = this.g.p();
      int var4 = var1 * 16 + 8 - var3.a;
      int var5 = var2 * 16 + 8 - var3.c;
      short var6 = 128;
      if(var4 < -var6 || var4 > var6 || var5 < -var6 || var5 > var6) {
         this.a.add(Long.valueOf(OChunkCoordIntPair.a(var1, var2)));
      }

   }

   public OChunk c(int var1, int var2) {
      long var3 = OChunkCoordIntPair.a(var1, var2);
      this.a.remove(Long.valueOf(var3));
      OChunk var5 = (OChunk)this.e.a(var3);
      if(var5 == null) {
         int var6 = 1875004;
         if(var1 < -var6 || var2 < -var6 || var1 >= var6 || var2 >= var6) {
            return this.b;
         }

         var5 = this.e(var1, var2);
         if(var5 == null) {
            if(this.c == null) {
               var5 = this.b;
            } else {
               var5 = this.c.b(var1, var2);
            }
         }

         this.e.a(var3, var5);
         this.f.add(var5);
         if(var5 != null) {
            var5.b();
            var5.c();
         }

         var5.a(this, this, var1, var2);
      }

      return var5;
   }

   public OChunk b(int var1, int var2) {
      OChunk var3 = (OChunk)this.e.a(OChunkCoordIntPair.a(var1, var2));
      return var3 == null?this.c(var1, var2):var3;
   }

   private OChunk e(int var1, int var2) {
      if(this.d == null) {
         return null;
      } else {
         try {
            OChunk var3 = this.d.a(this.g, var1, var2);
            if(var3 != null) {
               var3.n = this.g.o();
            }

            return var3;
         } catch (Exception var4) {
            var4.printStackTrace();
            return null;
         }
      }
   }

   private void a(OChunk var1) {
      if(this.d != null) {
         try {
            this.d.b(this.g, var1);
         } catch (Exception var3) {
            var3.printStackTrace();
         }

      }
   }

   private void b(OChunk var1) {
      if(this.d != null) {
         var1.n = this.g.o();
        this.d.a(this.g, var1);

      }
   }

   public void a(OIChunkProvider var1, int var2, int var3) {
      OChunk var4 = this.b(var2, var3);
      if(!var4.k) {
         var4.k = true;
         if(this.c != null) {
            this.c.a(var1, var2, var3);
            var4.e();
         }
      }

   }

   public boolean a(boolean var1, OIProgressUpdate var2) {
      int var3 = 0;

      for(int var4 = 0; var4 < this.f.size(); ++var4) {
         OChunk var5 = (OChunk)this.f.get(var4);
         if(var1) {
            this.a(var5);
         }

         if(var5.a(var1)) {
            this.b(var5);
            var5.l = false;
            ++var3;
            if(var3 == 24 && !var1) {
               return false;
            }
         }
      }

      if(var1) {
         if(this.d == null) {
            return true;
         }

         this.d.b();
      }

      return true;
   }

   public boolean a() {
      int var1;
      for(var1 = 0; var1 < 100; ++var1) {
         if(!this.a.isEmpty()) {
            Long var2 = (Long)this.a.iterator().next();
            OChunk var3 = (OChunk)this.e.a(var2.longValue());
            var3.d();
            this.b(var3);
            this.a(var3);
            this.a.remove(var2);
            this.e.d(var2.longValue());
            this.f.remove(var3);
         }
      }

      for(var1 = 0; var1 < 10; ++var1) {
         if(this.h >= this.f.size()) {
            this.h = 0;
            break;
         }

         OChunk var4 = (OChunk)this.f.get(this.h++);
         OEntityPlayer var5 = this.g.a((double)(var4.g << 4) + 8.0D, (double)(var4.h << 4) + 8.0D, 288.0D);
         if(var5 == null) {
            this.d(var4.g, var4.h);
         }
      }

      if(this.d != null) {
         this.d.a();
      }

      return this.c.a();
   }

   public boolean b() {
      return true;
   }

   public List a(OEnumCreatureType var1, int var2, int var3, int var4) {
      return this.c.a(var1, var2, var3, var4);
   }

   public OChunkPosition a(OWorld var1, String var2, int var3, int var4, int var5) {
      return this.c.a(var1, var2, var3, var4, var5);
   }
}
