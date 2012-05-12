package net.minecraft.server;

import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OStatCollector;

public class OEntityDamageSource extends ODamageSource {

   protected OEntity a;


   public OEntityDamageSource(String var1, OEntity var2) {
      super(var1);
      this.a = var2;
   }

   public OEntity a() {
      return this.a;
   }

   public String a(OEntityPlayer var1) {
      return OStatCollector.a("death." + this.n, new Object[]{var1.v, this.a.s()});
   }
}
