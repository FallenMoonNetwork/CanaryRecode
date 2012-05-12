package net.minecraft.server;

import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityDamageSource;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OStatCollector;

public class OEntityDamageSourceIndirect extends OEntityDamageSource {

   private OEntity o;


   public OEntityDamageSourceIndirect(String var1, OEntity var2, OEntity var3) {
      super(var1, var2);
      this.o = var3;
   }

   public OEntity b() {
      return this.a;
   }

   public OEntity a() {
      return this.o;
   }

   public String a(OEntityPlayer var1) {
      return OStatCollector.a("death." + this.n, new Object[]{var1.v, this.o.s()});
   }
}
