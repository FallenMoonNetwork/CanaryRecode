package net.minecraft.server;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.server.OBlock;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OPacket;
import net.minecraft.server.OTileEntityBrewingStand;
import net.minecraft.server.OTileEntityChest;
import net.minecraft.server.OTileEntityDispenser;
import net.minecraft.server.OTileEntityEnchantmentTable;
import net.minecraft.server.OTileEntityEndPortal;
import net.minecraft.server.OTileEntityFurnace;
import net.minecraft.server.OTileEntityMobSpawner;
import net.minecraft.server.OTileEntityNote;
import net.minecraft.server.OTileEntityPiston;
import net.minecraft.server.OTileEntityRecordPlayer;
import net.minecraft.server.OTileEntitySign;
import net.minecraft.server.OWorld;

public class OTileEntity {

   private static Map a = new HashMap();
   private static Map b = new HashMap();
   public OWorld k;
   public int l;
   public int m;
   public int n;
   protected boolean o;
   public int p = -1;
   public OBlock q;


   public OTileEntity() {
      super();
   }

   private static void a(Class var0, String var1) {
      if(b.containsKey(var1)) {
         throw new IllegalArgumentException("Duplicate id: " + var1);
      } else {
         a.put(var1, var0);
         b.put(var0, var1);
      }
   }

   public void a(ONBTTagCompound var1) {
      this.l = var1.f("x");
      this.m = var1.f("y");
      this.n = var1.f("z");
   }

   public void b(ONBTTagCompound var1) {
      String var2 = (String)b.get(this.getClass());
      if(var2 == null) {
         throw new RuntimeException(this.getClass() + " is missing a mapping! This is a bug!");
      } else {
         var1.a("id", var2);
         var1.a("x", this.l);
         var1.a("y", this.m);
         var1.a("z", this.n);
      }
   }

   public void q_() {}

   public static OTileEntity c(ONBTTagCompound var0) {
      OTileEntity var1 = null;

      try {
         Class var2 = (Class)a.get(var0.j("id"));
         if(var2 != null) {
            var1 = (OTileEntity)var2.newInstance();
         }
      } catch (Exception var3) {
         var3.printStackTrace();
      }

      if(var1 != null) {
         var1.a(var0);
      } else {
         System.out.println("Skipping TileEntity with id " + var0.j("id"));
      }

      return var1;
   }

   public int k() {
      if(this.p == -1) {
         this.p = this.k.c(this.l, this.m, this.n);
      }

      return this.p;
   }

   public void G_() {
      if(this.k != null) {
         this.p = this.k.c(this.l, this.m, this.n);
         this.k.b(this.l, this.m, this.n, this);
      }

   }

   public OPacket d() {
      return null;
   }

   public boolean l() {
      return this.o;
   }

   public void j() {
      this.o = true;
   }

   public void m() {
      this.o = false;
   }

   public void b(int var1, int var2) {}

   public void h() {
      this.q = null;
      this.p = -1;
   }

   static {
      a(OTileEntityFurnace.class, "Furnace");
      a(OTileEntityChest.class, "Chest");
      a(OTileEntityRecordPlayer.class, "RecordPlayer");
      a(OTileEntityDispenser.class, "Trap");
      a(OTileEntitySign.class, "Sign");
      a(OTileEntityMobSpawner.class, "MobSpawner");
      a(OTileEntityNote.class, "Music");
      a(OTileEntityPiston.class, "Piston");
      a(OTileEntityBrewingStand.class, "Cauldron");
      a(OTileEntityEnchantmentTable.class, "EnchantTable");
      a(OTileEntityEndPortal.class, "Airportal");
   }
}
