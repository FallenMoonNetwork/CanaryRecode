package net.minecraft.server;

import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityBlaze;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityThrowable;
import net.minecraft.server.OMovingObjectPosition;
import net.minecraft.server.OWorld;

public class OEntitySnowball extends OEntityThrowable {

   public OEntitySnowball(OWorld var1) {
      super(var1);
   }

   public OEntitySnowball(OWorld var1, OEntityLiving var2) {
      super(var1, var2);
   }

   public OEntitySnowball(OWorld var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6);
   }

   protected void a(OMovingObjectPosition var1) {
      if(var1.g != null) {
         byte var2 = 0;
         if(var1.g instanceof OEntityBlaze) {
            var2 = 3;
         }

         if(var1.g.a(ODamageSource.a((OEntity)this, this.c), var2)) {
            ;
         }
      }

      for(int var3 = 0; var3 < 8; ++var3) {
         this.bi.a("snowballpoof", this.bm, this.bn, this.bo, 0.0D, 0.0D, 0.0D);
      }

      if(!this.bi.F) {
         this.X();
      }

   }
}
