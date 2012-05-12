package net.minecraft.server;

import net.minecraft.server.OChunkCoordinates;
import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityCreature;
import net.minecraft.server.ORandomPositionGenerator;
import net.minecraft.server.OVec3D;

public class OEntityAIMoveTwardsRestriction extends OEntityAIBase {

   private OEntityCreature a;
   private double b;
   private double c;
   private double d;
   private float e;


   public OEntityAIMoveTwardsRestriction(OEntityCreature var1, float var2) {
      super();
      this.a = var1;
      this.e = var2;
      this.a(1);
   }

   public boolean a() {
      if(this.a.au()) {
         return false;
      } else {
         OChunkCoordinates var1 = this.a.av();
         OVec3D var2 = ORandomPositionGenerator.a(this.a, 16, 7, OVec3D.b((double)var1.a, (double)var1.b, (double)var1.c));
         if(var2 == null) {
            return false;
         } else {
            this.b = var2.a;
            this.c = var2.b;
            this.d = var2.c;
            return true;
         }
      }
   }

   public boolean b() {
      return !this.a.al().e();
   }

   public void c() {
      this.a.al().a(this.b, this.c, this.d, this.e);
   }
}
