package net.minecraft.server;

import net.minecraft.server.OWorldType;

public final class OWorldSettings {

    private final long a;
    private final int b;
    private final boolean c;
    private final boolean d;
    private final OWorldType e;

    public OWorldSettings(long var1, int var3, boolean var4, boolean var5, OWorldType var6) {
        super();
        this.a = var1;
        this.b = var3;
        this.c = var4;
        this.d = var5;
        this.e = var6;
    }

    public long a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public boolean c() {
        return this.d;
    }

    public boolean d() {
        return this.c;
    }

    public OWorldType e() {
        return this.e;
    }

    public static int a(int var0) {
        switch (var0) {
        case 0:
        case 1:
            return var0;
        default:
            return 0;
        }
    }
}
