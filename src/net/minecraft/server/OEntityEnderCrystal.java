package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEntity;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OWorld;

public class OEntityEnderCrystal extends OEntity {

   public int a = 0;
   public int b;


   public OEntityEnderCrystal(OWorld var1) {
      super(var1);
      this.bf = true;
      this.b(2.0F, 2.0F);
      this.bF = this.bH / 2.0F;
      this.b = 5;
      this.a = this.bS.nextInt(100000);
   }

   protected boolean g_() {
      return false;
   }

   protected void b() {
      this.bY.a(8, Integer.valueOf(this.b));
   }

   public void F_() {
      this.bj = this.bm;
      this.bk = this.bn;
      this.bl = this.bo;
      ++this.a;
      this.bY.b(8, Integer.valueOf(this.b));
      int var1 = OMathHelper.b(this.bm);
      int var2 = OMathHelper.b(this.bn);
      int var3 = OMathHelper.b(this.bo);
      if(this.bi.a(var1, var2, var3) != OBlock.ar.bO) {
         this.bi.e(var1, var2, var3, OBlock.ar.bO);
      }

   }

   protected void b(ONBTTagCompound var1) {}

   protected void a(ONBTTagCompound var1) {}

   public boolean o_() {
      return true;
   }

   public boolean a(ODamageSource var1, int var2) {
      if(!this.bE && !this.bi.F) {
         this.b = 0;
         if(this.b <= 0) {
            if(!this.bi.F) {
               this.X();
               this.bi.a((OEntity)null, this.bm, this.bn, this.bo, 6.0F);
            } else {
               this.X();
            }
         }
      }

      return true;
   }
}
