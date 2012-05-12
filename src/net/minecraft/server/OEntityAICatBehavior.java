package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockBed;
import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityOcelot;
import net.minecraft.server.OTileEntityChest;
import net.minecraft.server.OWorld;

public class OEntityAICatBehavior extends OEntityAIBase {

   private final OEntityOcelot a;
   private final float b;
   private int c = 0;
   private int h = 0;
   private int d = 0;
   private int e = 0;
   private int f = 0;
   private int g = 0;


   public OEntityAICatBehavior(OEntityOcelot var1, float var2) {
      super();
      this.a = var1;
      this.b = var2;
      this.a(5);
   }

   public boolean a() {
      return this.a.u_() && !this.a.v_() && this.a.an().nextDouble() <= 0.006500000134110451D && this.f();
   }

   public boolean b() {
      return this.c <= this.d && this.h <= 60 && this.a(this.a.bi, this.e, this.f, this.g);
   }

   public void c() {
      this.a.al().a((double)((float)this.e) + 0.5D, (double)(this.f + 1), (double)((float)this.g) + 0.5D, this.b);
      this.c = 0;
      this.h = 0;
      this.d = this.a.an().nextInt(this.a.an().nextInt(1200) + 1200) + 1200;
      this.a.C().a(false);
   }

   public void d() {
      this.a.c(false);
   }

   public void e() {
      ++this.c;
      this.a.C().a(false);
      if(this.a.e((double)this.e, (double)(this.f + 1), (double)this.g) > 1.0D) {
         this.a.c(false);
         this.a.al().a((double)((float)this.e) + 0.5D, (double)(this.f + 1), (double)((float)this.g) + 0.5D, this.b);
         ++this.h;
      } else if(!this.a.v_()) {
         this.a.c(true);
      } else {
         --this.h;
      }

   }

   private boolean f() {
      int var1 = (int)this.a.bn;
      double var2 = 2.147483647E9D;

      for(int var4 = (int)this.a.bm - 8; (double)var4 < this.a.bm + 8.0D; ++var4) {
         for(int var5 = (int)this.a.bo - 8; (double)var5 < this.a.bo + 8.0D; ++var5) {
            if(this.a(this.a.bi, var4, var1, var5) && this.a.bi.g(var4, var1 + 1, var5)) {
               double var6 = this.a.e((double)var4, (double)var1, (double)var5);
               if(var6 < var2) {
                  this.e = var4;
                  this.f = var1;
                  this.g = var5;
                  var2 = var6;
               }
            }
         }
      }

      return var2 < 2.147483647E9D;
   }

   private boolean a(OWorld var1, int var2, int var3, int var4) {
      int var5 = var1.a(var2, var3, var4);
      int var6 = var1.c(var2, var3, var4);
      if(var5 == OBlock.au.bO) {
         OTileEntityChest var7 = (OTileEntityChest)var1.b(var2, var3, var4);
         if(var7.h < 1) {
            return true;
         }
      } else {
         if(var5 == OBlock.aC.bO) {
            return true;
         }

         if(var5 == OBlock.S.bO && !OBlockBed.d(var6)) {
            return true;
         }
      }

      return false;
   }
}
