package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockDoor;
import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OPathEntity;
import net.minecraft.server.OPathNavigate;
import net.minecraft.server.OPathPoint;

public abstract class OEntityAIDoorInteract extends OEntityAIBase {

   protected OEntityLiving a;
   protected int b;
   protected int c;
   protected int d;
   protected OBlockDoor e;
   boolean f;
   float g;
   float h;


   public OEntityAIDoorInteract(OEntityLiving var1) {
      super();
      this.a = var1;
   }

   public boolean a() {
      if(!this.a.by) {
         return false;
      } else {
         OPathNavigate var1 = this.a.al();
         OPathEntity var2 = var1.c();
         if(var2 != null && !var2.b() && var1.b()) {
            for(int var3 = 0; var3 < Math.min(var2.e() + 2, var2.d()); ++var3) {
               OPathPoint var4 = var2.a(var3);
               this.b = var4.a;
               this.c = var4.b + 1;
               this.d = var4.c;
               if(this.a.e((double)this.b, this.a.bn, (double)this.d) <= 2.25D) {
                  this.e = this.a(this.b, this.c, this.d);
                  if(this.e != null) {
                     return true;
                  }
               }
            }

            this.b = OMathHelper.b(this.a.bm);
            this.c = OMathHelper.b(this.a.bn + 1.0D);
            this.d = OMathHelper.b(this.a.bo);
            this.e = this.a(this.b, this.c, this.d);
            return this.e != null;
         } else {
            return false;
         }
      }
   }

   public boolean b() {
      return !this.f;
   }

   public void c() {
      this.f = false;
      this.g = (float)((double)((float)this.b + 0.5F) - this.a.bm);
      this.h = (float)((double)((float)this.d + 0.5F) - this.a.bo);
   }

   public void e() {
      float var1 = (float)((double)((float)this.b + 0.5F) - this.a.bm);
      float var2 = (float)((double)((float)this.d + 0.5F) - this.a.bo);
      float var3 = this.g * var1 + this.h * var2;
      if(var3 < 0.0F) {
         this.f = true;
      }

   }

   private OBlockDoor a(int var1, int var2, int var3) {
      int var4 = this.a.bi.a(var1, var2, var3);
      if(var4 != OBlock.aE.bO) {
         return null;
      } else {
         OBlockDoor var5 = (OBlockDoor)OBlock.m[var4];
         return var5;
      }
   }
}
