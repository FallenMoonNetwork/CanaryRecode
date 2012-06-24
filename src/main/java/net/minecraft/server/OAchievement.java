package net.minecraft.server;

import net.minecraft.server.OAchievementList;
import net.minecraft.server.OBlock;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OStatBase;

public class OAchievement extends OStatBase {

   public final int a;
   public final int b;
   public final OAchievement c;
   @SuppressWarnings("unused")
private final String k;
   public final OItemStack d;
   private boolean l;


   public OAchievement(int var1, String var2, int var3, int var4, OItem var5, OAchievement var6) {
      this(var1, var2, var3, var4, new OItemStack(var5), var6);
   }

   public OAchievement(int var1, String var2, int var3, int var4, OBlock var5, OAchievement var6) {
      this(var1, var2, var3, var4, new OItemStack(var5), var6);
   }

   public OAchievement(int var1, String var2, int var3, int var4, OItemStack var5, OAchievement var6) {
      super(5242880 + var1, "achievement." + var2);
      this.d = var5;
      this.k = "achievement." + var2 + ".desc";
      this.a = var3;
      this.b = var4;
      if(var3 < OAchievementList.a) {
          OAchievementList.a = var3;
      }

      if(var4 < OAchievementList.b) {
          OAchievementList.b = var4;
      }

      if(var3 > OAchievementList.c) {
         OAchievementList.c = var3;
      }

      if(var4 > OAchievementList.d) {
         OAchievementList.d = var4;
      }

      this.c = var6;
   }

   public OAchievement a() {
      this.f = true;
      return this;
   }

   public OAchievement b() {
      this.l = true;
      return this;
   }

   public OAchievement c() {
      super.d();
      OAchievementList.e.add(this);
      return this;
   }

   // $FF: synthetic method
   // $FF: bridge method
   @Override
public OStatBase d() {
      return this.c();
   }

   // $FF: synthetic method
   // $FF: bridge method
   @Override
public OStatBase e() {
      return this.a();
   }
}
