package net.minecraft.server;

import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OPathEntity;
import net.minecraft.server.OWorld;

public class OEntityAIAttackOnCollide extends OEntityAIBase {

   OWorld a;
   OEntityLiving b;
   OEntityLiving c;
   int d;
   float e;
   boolean f;
   OPathEntity g;
   Class h;
   private int i;


   public OEntityAIAttackOnCollide(OEntityLiving var1, Class var2, float var3, boolean var4) {
      this(var1, var3, var4);
      this.h = var2;
   }

   public OEntityAIAttackOnCollide(OEntityLiving var1, float var2, boolean var3) {
      super();
      this.d = 0;
      this.b = var1;
      this.a = var1.bi;
      this.e = var2;
      this.f = var3;
      this.a(3);
   }

   public boolean a() {
      OEntityLiving var1 = this.b.at();
      if(var1 == null) {
         return false;
      } else if(this.h != null && !this.h.isAssignableFrom(var1.getClass())) {
         return false;
      } else {
         this.c = var1;
         this.g = this.b.al().a(this.c);
         return this.g != null;
      }
   }

   public boolean b() {
      OEntityLiving var1 = this.b.at();
      return var1 == null?false:(!this.c.aE()?false:(!this.f?!this.b.al().e():this.b.e(OMathHelper.b(this.c.bm), OMathHelper.b(this.c.bn), OMathHelper.b(this.c.bo))));
   }

   public void c() {
      this.b.al().a(this.g, this.e);
      this.i = 0;
   }

   public void d() {
      this.c = null;
      this.b.al().f();
   }

   public void e() {
      this.b.ai().a(this.c, 30.0F, 30.0F);
      if((this.f || this.b.am().a(this.c)) && --this.i <= 0) {
         this.i = 4 + this.b.an().nextInt(7);
         this.b.al().a(this.c, this.e);
      }

      this.d = Math.max(this.d - 1, 0);
      double var1 = (double)(this.b.bG * 2.0F * this.b.bG * 2.0F);
      if(this.b.e(this.c.bm, this.c.bw.b, this.c.bo) <= var1) {
         if(this.d <= 0) {
            this.d = 20;
            this.b.a((OEntity)this.c);
         }
      }
   }
}
