package net.minecraft.server;

import net.minecraft.server.ONBTTagCompound;

public abstract class OWorldSavedData {

   public final String a;
   private boolean b;


   public OWorldSavedData(String var1) {
      super();
      this.a = var1;
   }

   public abstract void a(ONBTTagCompound var1);

   public abstract void b(ONBTTagCompound var1);

   public void a() {
      this.a(true);
   }

   public void a(boolean var1) {
      this.b = var1;
   }

   public boolean b() {
      return this.b;
   }
}
