package net.minecraft.server;


public class OEnumWorldType {

   public static final OEnumWorldType[] a = new OEnumWorldType[16];
   public static final OEnumWorldType b = (new OEnumWorldType(0, "default", 1)).d();
   public static final OEnumWorldType c = new OEnumWorldType(1, "flat");
   public static final OEnumWorldType d = (new OEnumWorldType(8, "default_1_1", 0)).a(false);
   private final String e;
   private final int f;
   private boolean g;
   private boolean h;


   private OEnumWorldType(int var1, String var2) {
      this(var1, var2, 0);
   }

   private OEnumWorldType(int var1, String var2, int var3) {
      super();
      this.e = var2;
      this.f = var3;
      this.g = true;
      a[var1] = this;
   }

   public String a() {
      return this.e;
   }

   public int b() {
      return this.f;
   }

   public OEnumWorldType a(int var1) {
      return this == b && var1 == 0?d:this;
   }

   private OEnumWorldType a(boolean var1) {
      this.g = var1;
      return this;
   }

   private OEnumWorldType d() {
      this.h = true;
      return this;
   }

   public boolean c() {
      return this.h;
   }

   public static OEnumWorldType a(String var0) {
      for(int var1 = 0; var1 < a.length; ++var1) {
         if(a[var1] != null && a[var1].e.equalsIgnoreCase(var0)) {
            return a[var1];
         }
      }

      return null;
   }

}
