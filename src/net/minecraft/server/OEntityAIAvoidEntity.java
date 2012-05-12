package net.minecraft.server;

import java.util.List;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityCreature;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntityTamable;
import net.minecraft.server.OPathEntity;
import net.minecraft.server.OPathNavigate;
import net.minecraft.server.ORandomPositionGenerator;
import net.minecraft.server.OVec3D;

public class OEntityAIAvoidEntity extends OEntityAIBase {

   private OEntityCreature a;
   private float b;
   private float c;
   private OEntity d;
   private float e;
   private OPathEntity f;
   private OPathNavigate g;
   private Class h;


   public OEntityAIAvoidEntity(OEntityCreature var1, Class var2, float var3, float var4, float var5) {
      super();
      this.a = var1;
      this.h = var2;
      this.e = var3;
      this.b = var4;
      this.c = var5;
      this.g = var1.al();
      this.a(1);
   }

   public boolean a() {
      if(this.h == OEntityPlayer.class) {
         if(this.a instanceof OEntityTamable && ((OEntityTamable)this.a).u_()) {
            return false;
         }

         this.d = this.a.bi.a(this.a, (double)this.e);
         if(this.d == null) {
            return false;
         }
      } else {
         List var1 = this.a.bi.a(this.h, this.a.bw.b((double)this.e, 3.0D, (double)this.e));
         if(var1.size() == 0) {
            return false;
         }

         this.d = (OEntity)var1.get(0);
      }

      if(!this.a.am().a(this.d)) {
         return false;
      } else {
         OVec3D var2 = ORandomPositionGenerator.b(this.a, 16, 7, OVec3D.b(this.d.bm, this.d.bn, this.d.bo));
         if(var2 == null) {
            return false;
         } else if(this.d.e(var2.a, var2.b, var2.c) < this.d.j(this.a)) {
            return false;
         } else {
            this.f = this.g.a(var2.a, var2.b, var2.c);
            return this.f == null?false:this.f.a(var2);
         }
      }
   }

   public boolean b() {
      return !this.g.e();
   }

   public void c() {
      this.g.a(this.f, this.b);
   }

   public void d() {
      this.d = null;
   }

   public void e() {
      if(this.a.j(this.d) < 49.0D) {
         this.a.al().a(this.c);
      } else {
         this.a.al().a(this.b);
      }

   }
}
