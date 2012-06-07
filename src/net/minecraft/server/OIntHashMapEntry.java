package net.minecraft.server;

import net.minecraft.server.OIntHashMap;

class OIntHashMapEntry {

    final int a;
    Object b;
    OIntHashMapEntry c;
    final int d;

    OIntHashMapEntry(int var1, int var2, Object var3, OIntHashMapEntry var4) {
        super();
        this.b = var3;
        this.c = var4;
        this.a = var2;
        this.d = var1;
    }

    public final int a() {
        return this.a;
    }

    public final Object b() {
        return this.b;
    }

    @Override
    public final boolean equals(Object var1) {
        if (!(var1 instanceof OIntHashMapEntry)) {
            return false;
        } else {
            OIntHashMapEntry var2 = (OIntHashMapEntry) var1;
            Integer var3 = Integer.valueOf(this.a());
            Integer var4 = Integer.valueOf(var2.a());
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

    @Override
    public final int hashCode() {
        return OIntHashMap.f(this.a);
    }

    @Override
    public final String toString() {
        return this.a() + "=" + this.b();
    }
}
