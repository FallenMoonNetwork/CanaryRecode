package net.minecraft.server;

import net.minecraft.server.OEntityCreature;
import net.minecraft.server.OIAnimals;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OWorld;

public abstract class OEntityGolem extends OEntityCreature implements OIAnimals {

   public OEntityGolem(OWorld var1) {
      super(var1);
   }

   protected void a(float var1) {}

   public void b(ONBTTagCompound var1) {
      super.b(var1);
   }

   public void a(ONBTTagCompound var1) {
      super.a(var1);
   }

   protected String i() {
      return "none";
   }

   protected String j() {
      return "none";
   }

   protected String k() {
      return "none";
   }

   public int m() {
      return 120;
   }

   protected boolean n() {
      return false;
   }
}
