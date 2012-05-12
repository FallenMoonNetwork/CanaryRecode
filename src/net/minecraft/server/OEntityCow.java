package net.minecraft.server;

import net.minecraft.server.OEntityAIFollowParent;
import net.minecraft.server.OEntityAILookIdle;
import net.minecraft.server.OEntityAIMate;
import net.minecraft.server.OEntityAIPanic;
import net.minecraft.server.OEntityAISwimming;
import net.minecraft.server.OEntityAITempt;
import net.minecraft.server.OEntityAIWander;
import net.minecraft.server.OEntityAIWatchClosest;
import net.minecraft.server.OEntityAnimal;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OWorld;

public class OEntityCow extends OEntityAnimal {

   public OEntityCow(OWorld var1) {
      super(var1);
      this.ae = "/mob/cow.png";
      this.b(0.9F, 1.3F);
      this.al().a(true);
      this.aL.a(0, new OEntityAISwimming(this));
      this.aL.a(1, new OEntityAIPanic(this, 0.38F));
      this.aL.a(2, new OEntityAIMate(this, 0.2F));
      this.aL.a(3, new OEntityAITempt(this, 0.25F, OItem.S.bP, false));
      this.aL.a(4, new OEntityAIFollowParent(this, 0.25F));
      this.aL.a(5, new OEntityAIWander(this, 0.2F));
      this.aL.a(6, new OEntityAIWatchClosest(this, OEntityPlayer.class, 6.0F));
      this.aL.a(7, new OEntityAILookIdle(this));
   }

   public boolean c_() {
      return true;
   }

   public int d() {
      return 10;
   }

   public void b(ONBTTagCompound var1) {
      super.b(var1);
   }

   public void a(ONBTTagCompound var1) {
      super.a(var1);
   }

   protected String i() {
      return "mob.cow";
   }

   protected String j() {
      return "mob.cowhurt";
   }

   protected String k() {
      return "mob.cowhurt";
   }

   protected float p() {
      return 0.4F;
   }

   protected int f() {
      return OItem.aE.bP;
   }

   protected void a(boolean var1, int var2) {
      int var3 = this.bS.nextInt(3) + this.bS.nextInt(1 + var2);

      int var4;
      for(var4 = 0; var4 < var3; ++var4) {
         this.b(OItem.aE.bP, 1);
      }

      var3 = this.bS.nextInt(3) + 1 + this.bS.nextInt(1 + var2);

      for(var4 = 0; var4 < var3; ++var4) {
         if(this.B_()) {
            this.b(OItem.bi.bP, 1);
         } else {
            this.b(OItem.bh.bP, 1);
         }
      }

   }

   public boolean b(OEntityPlayer var1) {
      OItemStack var2 = var1.k.d();
      if(var2 != null && var2.c == OItem.av.bP) {
         var1.k.a(var1.k.c, new OItemStack(OItem.aF));
         return true;
      } else {
         return super.b(var1);
      }
   }

   public OEntityAnimal a(OEntityAnimal var1) {
      return new OEntityCow(this.bi);
   }
}
