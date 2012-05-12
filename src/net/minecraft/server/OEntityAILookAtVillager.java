package net.minecraft.server;

import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityIronGolem;
import net.minecraft.server.OEntityVillager;

public class OEntityAILookAtVillager extends OEntityAIBase {

   private OEntityIronGolem a;
   private OEntityVillager b;
   private int c;


   public OEntityAILookAtVillager(OEntityIronGolem var1) {
      super();
      this.a = var1;
      this.a(3);
   }

   public boolean a() {
      if(!this.a.bi.e()) {
         return false;
      } else if(this.a.an().nextInt(8000) != 0) {
         return false;
      } else {
         this.b = (OEntityVillager)this.a.bi.a(OEntityVillager.class, this.a.bw.b(6.0D, 2.0D, 6.0D), this.a);
         return this.b != null;
      }
   }

   public boolean b() {
      return this.c > 0;
   }

   public void c() {
      this.c = 400;
      this.a.a(true);
   }

   public void d() {
      this.a.a(false);
      this.b = null;
   }

   public void e() {
      this.a.ai().a(this.b, 30.0F, 30.0F);
      --this.c;
   }
}
