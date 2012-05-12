package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityIronGolem;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityVillager;

public class OEntityAIFollowGolem extends OEntityAIBase {

   private OEntityVillager a;
   private OEntityIronGolem b;
   private int c;
   private boolean d = false;


   public OEntityAIFollowGolem(OEntityVillager var1) {
      super();
      this.a = var1;
      this.a(3);
   }

   public boolean a() {
      if(this.a.K() >= 0) {
         return false;
      } else if(!this.a.bi.e()) {
         return false;
      } else {
         List var1 = this.a.bi.a(OEntityIronGolem.class, this.a.bw.b(6.0D, 2.0D, 6.0D));
         if(var1.size() == 0) {
            return false;
         } else {
            Iterator var2 = var1.iterator();

            while(var2.hasNext()) {
               OEntity var3 = (OEntity)var2.next();
               OEntityIronGolem var4 = (OEntityIronGolem)var3;
               if(var4.m_() > 0) {
                  this.b = var4;
                  break;
               }
            }

            return this.b != null;
         }
      }
   }

   public boolean b() {
      return this.b.m_() > 0;
   }

   public void c() {
      this.c = this.a.an().nextInt(320);
      this.d = false;
      this.b.al().f();
   }

   public void d() {
      this.b = null;
      this.a.al().f();
   }

   public void e() {
      this.a.ai().a(this.b, 30.0F, 30.0F);
      if(this.b.m_() == this.c) {
         this.a.al().a((OEntityLiving)this.b, 0.15F);
         this.d = true;
      }

      if(this.d && this.a.j(this.b) < 4.0D) {
         this.b.a(false);
         this.a.al().f();
      }

   }
}
