package net.minecraft.server;

import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityCreature;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.ORandomPositionGenerator;
import net.minecraft.server.OVec3D;

public class OEntityAIMoveTowardsTarget extends OEntityAIBase {

   private OEntityCreature a;
   private OEntityLiving b;
   private double c;
   private double d;
   private double e;
   private float f;
   private float g;


   public OEntityAIMoveTowardsTarget(OEntityCreature var1, float var2, float var3) {
      super();
      this.a = var1;
      this.f = var2;
      this.g = var3;
      this.a(1);
   }

   public boolean a() {
      this.b = this.a.at();
      if(this.b == null) {
         return false;
      } else if(this.b.j(this.a) > (double)(this.g * this.g)) {
         return false;
      } else {
         OVec3D var1 = ORandomPositionGenerator.a(this.a, 16, 7, OVec3D.b(this.b.bm, this.b.bn, this.b.bo));
         if(var1 == null) {
            return false;
         } else {
            this.c = var1.a;
            this.d = var1.b;
            this.e = var1.c;
            return true;
         }
      }
   }

   public boolean b() {
      return !this.a.al().e() && this.b.aE() && this.b.j(this.a) < (double)(this.g * this.g);
   }

   public void d() {
      this.b = null;
   }

   public void c() {
      this.a.al().a(this.c, this.d, this.e, this.f);
   }
}
