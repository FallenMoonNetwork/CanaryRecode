package net.minecraft.server;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockDoor;
import net.minecraft.server.OChunkCoordinates;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OVillage;
import net.minecraft.server.OVillageDoorInfo;
import net.minecraft.server.OWorld;


public class OVillageCollection {

    private OWorld a;
    private final List b = new ArrayList();
    private final List c = new ArrayList();
    private final List d = new ArrayList();
    private int e = 0;

    public OVillageCollection(OWorld var1) {
        super();
        this.a = var1;
    }

    public void a(int var1, int var2, int var3) {
        if (this.b.size() <= 64) {
            if (!this.d(var1, var2, var3)) {
                this.b.add(new OChunkCoordinates(var1, var2, var3));
            }

        }
    }

    public void a() {
        ++this.e;
        Iterator var1 = this.d.iterator();

        while (var1.hasNext()) {
            OVillage var2 = (OVillage) var1.next();

            var2.a(this.e);
        }

        this.c();
        this.d();
        this.e();
    }

    private void c() {
        Iterator var1 = this.d.iterator();

        while (var1.hasNext()) {
            OVillage var2 = (OVillage) var1.next();

            if (var2.g()) {
                var1.remove();
            }
        }

    }

    public List b() {
        return this.d;
    }

    public OVillage a(int var1, int var2, int var3, int var4) {
        OVillage var5 = null;
        float var6 = Float.MAX_VALUE;
        Iterator var7 = this.d.iterator();

        while (var7.hasNext()) {
            OVillage var8 = (OVillage) var7.next();
            float var9 = var8.a().c(var1, var2, var3);

            if (var9 < var6) {
                int var10 = var4 + var8.b();

                if (var9 <= (var10 * var10)) {
                    var5 = var8;
                    var6 = var9;
                }
            }
        }

        return var5;
    }

    private void d() {
        if (!this.b.isEmpty()) {
            this.a((OChunkCoordinates) this.b.remove(0));
        }
    }

    private void e() {
        int var1 = 0;

        while (var1 < this.c.size()) {
            OVillageDoorInfo var2 = (OVillageDoorInfo) this.c.get(var1);
            boolean var3 = false;
            Iterator var4 = this.d.iterator();

            while (true) {
                if (var4.hasNext()) {
                    OVillage var5 = (OVillage) var4.next();
                    int var6 = (int) var5.a().b(var2.a, var2.b, var2.c);

                    if (var6 > 32 + var5.b()) {
                        continue;
                    }

                    var5.a(var2);
                    var3 = true;
                }

                if (!var3) {
                    OVillage var7 = new OVillage(this.a);

                    var7.a(var2);
                    this.d.add(var7);
                }

                ++var1;
                break;
            }
        }

        this.c.clear();
    }

    private void a(OChunkCoordinates var1) {
        byte var2 = 16;
        byte var3 = 4;
        byte var4 = 16;

        for (int var5 = var1.a - var2; var5 < var1.a + var2; ++var5) {
            for (int var6 = var1.b - var3; var6 < var1.b + var3; ++var6) {
                for (int var7 = var1.c - var4; var7 < var1.c + var4; ++var7) {
                    if (this.e(var5, var6, var7)) {
                        OVillageDoorInfo var8 = this.b(var5, var6, var7);

                        if (var8 == null) {
                            this.c(var5, var6, var7);
                        } else {
                            var8.f = this.e;
                        }
                    }
                }
            }
        }

    }

    private OVillageDoorInfo b(int var1, int var2, int var3) {
        Iterator var4 = this.c.iterator();

        OVillageDoorInfo var5;

        do {
            if (!var4.hasNext()) {
                var4 = this.d.iterator();

                OVillageDoorInfo var6;

                do {
                    if (!var4.hasNext()) {
                        return null;
                    }

                    OVillage var7 = (OVillage) var4.next();

                    var6 = var7.d(var1, var2, var3);
                } while (var6 == null);

                return var6;
            }

            var5 = (OVillageDoorInfo) var4.next();
        } while (var5.a != var1 || var5.c != var3 || Math.abs(var5.b - var2) > 1);

        return var5;
    }

    private void c(int var1, int var2, int var3) {
        int var4 = ((OBlockDoor) OBlock.aE).c((OIBlockAccess) this.a, var1, var2, var3);
        int var5;
        int var6;

        if (var4 != 0 && var4 != 2) {
            var5 = 0;

            for (var6 = -5; var6 < 0; ++var6) {
                if (this.a.l(var1, var2, var3 + var6)) {
                    --var5;
                }
            }

            for (var6 = 1; var6 <= 5; ++var6) {
                if (this.a.l(var1, var2, var3 + var6)) {
                    ++var5;
                }
            }

            if (var5 != 0) {
                this.c.add(new OVillageDoorInfo(var1, var2, var3, 0, var5 > 0 ? -2 : 2, this.e));
            }
        } else {
            var5 = 0;

            for (var6 = -5; var6 < 0; ++var6) {
                if (this.a.l(var1 + var6, var2, var3)) {
                    --var5;
                }
            }

            for (var6 = 1; var6 <= 5; ++var6) {
                if (this.a.l(var1 + var6, var2, var3)) {
                    ++var5;
                }
            }

            if (var5 != 0) {
                this.c.add(new OVillageDoorInfo(var1, var2, var3, var5 > 0 ? -2 : 2, 0, this.e));
            }
        }

    }

    private boolean d(int var1, int var2, int var3) {
        Iterator var4 = this.b.iterator();

        OChunkCoordinates var5;

        do {
            if (!var4.hasNext()) {
                return false;
            }

            var5 = (OChunkCoordinates) var4.next();
        } while (var5.a != var1 || var5.b != var2 || var5.c != var3);

        return true;
    }

    private boolean e(int var1, int var2, int var3) {
        int var4 = this.a.a(var1, var2, var3);

        return var4 == OBlock.aE.bO;
    }
}
