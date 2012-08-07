package net.minecraft.server;

public class OMapColor {

    public static final OMapColor[] a = new OMapColor[16];
    public static final OMapColor b = new OMapColor(0, 0);
    public static final OMapColor c = new OMapColor(1, 8368696);
    public static final OMapColor d = new OMapColor(2, 16247203);
    public static final OMapColor e = new OMapColor(3, 10987431);
    public static final OMapColor f = new OMapColor(4, 16711680);
    public static final OMapColor g = new OMapColor(5, 10526975);
    public static final OMapColor h = new OMapColor(6, 10987431);
    public static final OMapColor i = new OMapColor(7, 31744);
    public static final OMapColor j = new OMapColor(8, 16777215);
    public static final OMapColor k = new OMapColor(9, 10791096);
    public static final OMapColor l = new OMapColor(10, 12020271);
    public static final OMapColor m = new OMapColor(11, 7368816);
    public static final OMapColor n = new OMapColor(12, 4210943);
    public static final OMapColor o = new OMapColor(13, 6837042);
    public final int p;
    public final int q;

    private OMapColor(int var1, int var2) {
        super();
        this.q = var1;
        this.p = var2;
        a[var1] = this;
    }

}
