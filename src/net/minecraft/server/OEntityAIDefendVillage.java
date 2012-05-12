package net.minecraft.server;

import net.minecraft.server.OEntityAITarget;
import net.minecraft.server.OEntityIronGolem;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OVillage;

public class OEntityAIDefendVillage extends OEntityAITarget {

   OEntityIronGolem a;
   OEntityLiving b;


   public OEntityAIDefendVillage(OEntityIronGolem var1) {
      super(var1, 16.0F, false, true);
      this.a = var1;
      this.a(1);
   }

   public boolean a() {
      OVillage var1 = this.a.l_();
      if(var1 == null) {
         return false;
      } else {
         this.b = var1.b(this.a);
         return this.a(this.b, false);
      }
   }

   public void c() {
      this.a.b(this.b);
      super.c();
   }
}
