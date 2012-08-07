package net.minecraft.server;

public class OWorldType {

    public static final OWorldType[] a = new OWorldType[16];
    public static final OWorldType b = (new OWorldType(0, "default", 1)).d();
    public static final OWorldType c = new OWorldType(1, "flat");
    public static final OWorldType d = (new OWorldType(8, "default_1_1", 0)).a(false);
    private final String e;
    private final int f;
    private boolean g;
    private boolean h;

    private OWorldType(int var1, String var2) {
        this(var1, var2, 0);
    }

    private OWorldType(int var1, String var2, int var3) {
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

    public OWorldType a(int var1) {
        return this == b && var1 == 0 ? d : this;
    }

    private OWorldType a(boolean var1) {
        this.g = var1;
        return this;
    }

    private OWorldType d() {
        this.h = true;
        return this;
    }

    public boolean c() {
        return this.h;
    }

    public static OWorldType a(String var0) {
        for (int var1 = 0; var1 < a.length; ++var1) {
            if (a[var1] != null && a[var1].e.equalsIgnoreCase(var0)) {
                return a[var1];
            }
        }

        return null;
    }

}
