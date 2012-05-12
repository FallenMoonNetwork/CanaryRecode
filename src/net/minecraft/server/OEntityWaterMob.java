package net.minecraft.server;

import net.minecraft.server.OEntityCreature;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIAnimals;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OWorld;

public abstract class OEntityWaterMob extends OEntityCreature implements OIAnimals {

   public OEntityWaterMob(OWorld var1) {
      super(var1);
   }

   public boolean f_() {
      return true;
   }

   public void b(ONBTTagCompound var1) {
      super.b(var1);
   }

   public void a(ONBTTagCompound var1) {
      super.a(var1);
   }

   public boolean l() {
      return this.bi.a(this.bw);
   }

   public int m() {
      return 120;
   }

   protected boolean n() {
      return true;
   }

   protected int a(OEntityPlayer var1) {
      return 1 + this.bi.r.nextInt(3);
   }
}
