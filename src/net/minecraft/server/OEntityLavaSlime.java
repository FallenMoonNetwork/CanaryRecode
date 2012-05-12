package net.minecraft.server;

import net.minecraft.server.OEntity;
import net.minecraft.server.OEntitySlime;
import net.minecraft.server.OItem;
import net.minecraft.server.OWorld;

public class OEntityLavaSlime extends OEntitySlime {

   public OEntityLavaSlime(OWorld var1) {
      super(var1);
      this.ae = "/mob/lava.png";
      this.bX = true;
      this.al = 0.2F;
   }

   public boolean l() {
      return this.bi.q > 0 && this.bi.a(this.bw) && this.bi.a((OEntity)this, this.bw).size() == 0 && !this.bi.c(this.bw);
   }

   public int T() {
      return this.L() * 3;
   }

   public float b(float var1) {
      return 1.0F;
   }

   protected String A() {
      return "flame";
   }

   protected OEntitySlime C() {
      return new OEntityLavaSlime(this.bi);
   }

   protected int f() {
      return OItem.bw.bP;
   }

   protected void a(boolean var1, int var2) {
      int var3 = this.f();
      if(var3 > 0 && this.L() > 1) {
         int var4 = this.bS.nextInt(4) - 2;
         if(var2 > 0) {
            var4 += this.bS.nextInt(var2 + 1);
         }

         for(int var5 = 0; var5 < var4; ++var5) {
            this.b(var3, 1);
         }
      }

   }

   public boolean B_() {
      return false;
   }

   protected int E() {
      return super.E() * 4;
   }

   protected void F() {
      this.a *= 0.9F;
   }

   protected void ac() {
      this.bq = (double)(0.42F + (float)this.L() * 0.1F);
      this.ce = true;
   }

   protected void a(float var1) {}

   protected boolean G() {
      return true;
   }

   protected int H() {
      return super.H() + 2;
   }

   protected String j() {
      return "mob.slime";
   }

   protected String k() {
      return "mob.slime";
   }

   protected String I() {
      return this.L() > 1?"mob.magmacube.big":"mob.magmacube.small";
   }

   public boolean aV() {
      return false;
   }

   protected boolean K() {
      return true;
   }
}
