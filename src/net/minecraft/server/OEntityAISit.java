package net.minecraft.server;

import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityTamable;

public class OEntityAISit extends OEntityAIBase {

   private OEntityTamable a;
   private boolean b = false;


   public OEntityAISit(OEntityTamable var1) {
      super();
      this.a = var1;
      this.a(5);
   }

   public boolean a() {
      if(!this.a.u_()) {
         return false;
      } else if(this.a.aU()) {
         return false;
      } else if(!this.a.bx) {
         return false;
      } else {
         OEntityLiving var1 = this.a.w_();
         return var1 == null?true:(this.a.j(var1) < 144.0D && var1.ao() != null?false:this.b);
      }
   }

   public void c() {
      this.a.al().f();
      this.a.c(true);
   }

   public void d() {
      this.a.c(false);
   }

   public void a(boolean var1) {
      this.b = var1;
   }
}
