package net.minecraft.server;

import net.minecraft.server.OLongHashMap;

class OLongHashMapEntry {

    final long a;
    Object b;
    OLongHashMapEntry c;
    final int d;

    OLongHashMapEntry(int var1, long var2, Object var4, OLongHashMapEntry var5) {
        super();
        this.b = var4;
        this.c = var5;
        this.a = var2;
        this.d = var1;
    }

    public final long a() {
        return this.a;
    }

    public final Object b() {
        return this.b;
    }

    public final boolean equals(Object var1) {
        if (!(var1 instanceof OLongHashMapEntry)) {
            return false;
        } else {
            OLongHashMapEntry var2 = (OLongHashMapEntry) var1;
            Long var3 = Long.valueOf(this.a());
            Long var4 = Long.valueOf(var2.a());
            if (var3 == var4 || var3 != null && var3.equals(var4)) {
                Object var5 = this.b();
                Object var6 = var2.b();
                if (var5 == var6 || var5 != null && var5.equals(var6)) {
                    return true;
                }
            }

            return false;
        }
    }

    public final int hashCode() {
        return OLongHashMap.f(this.a);
    }

    public final String toString() {
        return this.a() + "=" + this.b();
    }
}
