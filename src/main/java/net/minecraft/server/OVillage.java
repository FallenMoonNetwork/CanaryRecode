package net.minecraft.server;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OChunkCoordinates;
import net.minecraft.server.OEntityIronGolem;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityVillager;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OVec3D;
import net.minecraft.server.OVillageAgressor;
import net.minecraft.server.OVillageDoorInfo;
import net.minecraft.server.OWorld;


public class OVillage {

    private final OWorld a;
    private final List b = new ArrayList();
    private final OChunkCoordinates c = new OChunkCoordinates(0, 0, 0);
    private final OChunkCoordinates d = new OChunkCoordinates(0, 0, 0);
    private int e = 0;
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private List i = new ArrayList();
    private int j = 0;

    public OVillage(OWorld var1) {
        super();
        this.a = var1;
    }

    public void a(int var1) {
        this.g = var1;
        this.k();
        this.j();
        if (var1 % 20 == 0) {
            this.i();
        }

        if (var1 % 30 == 0) {
            this.h();
        }

        int var2 = this.h / 16;

        if (this.j < var2 && this.b.size() > 20 && this.a.r.nextInt(7000) == 0) {
            OVec3D var3 = this.a(OMathHelper.d(this.d.a), OMathHelper.d(this.d.b), OMathHelper.d(this.d.c), 2, 4, 2);

            if (var3 != null) {
                OEntityIronGolem var4 = new OEntityIronGolem(this.a);

                var4.c(var3.a, var3.b, var3.c);
                this.a.b(var4);
                ++this.j;
            }
        }

    }

    private OVec3D a(int var1, int var2, int var3, int var4, int var5, int var6) {
        for (int var7 = 0; var7 < 10; ++var7) {
            int var8 = var1 + this.a.r.nextInt(16) - 8;
            int var9 = var2 + this.a.r.nextInt(6) - 3;
            int var10 = var3 + this.a.r.nextInt(16) - 8;

            if (this.a(var8, var9, var10) && this.b(var8, var9, var10, var4, var5, var6)) {
                return OVec3D.b(var8, var9, var10);
            }
        }

        return null;
    }

    private boolean b(int var1, int var2, int var3, int var4, int var5, int var6) {
        if (!this.a.e(var1, var2 - 1, var3)) {
            return false;
        } else {
            int var7 = var1 - var4 / 2;
            int var8 = var3 - var6 / 2;

            for (int var9 = var7; var9 < var7 + var4; ++var9) {
                for (int var10 = var2; var10 < var2 + var5; ++var10) {
                    for (int var11 = var8; var11 < var8 + var6; ++var11) {
                        if (this.a.e(var9, var10, var11)) {
                            return false;
                        }
                    }
                }
            }

            return true;
        }
    }

    private void h() {
        List var1 = this.a.a(OEntityIronGolem.class, OAxisAlignedBB.b((this.d.a - this.e), (this.d.b - 4), (this.d.c - this.e), (this.d.a + this.e), (this.d.b + 4), (this.d.c + this.e)));

        this.j = var1.size();
    }

    private void i() {
        List var1 = this.a.a(OEntityVillager.class, OAxisAlignedBB.b((this.d.a - this.e), (this.d.b - 4), (this.d.c - this.e), (this.d.a + this.e), (this.d.b + 4), (this.d.c + this.e)));

        this.h = var1.size();
    }

    public OChunkCoordinates a() {
        return this.d;
    }

    public int b() {
        return this.e;
    }

    public int c() {
        return this.b.size();
    }

    public int d() {
        return this.g - this.f;
    }

    public int e() {
        return this.h;
    }

    public boolean a(int var1, int var2, int var3) {
        return this.d.c(var1, var2, var3) < (this.e * this.e);
    }

    public List f() {
        return this.b;
    }

    public OVillageDoorInfo b(int var1, int var2, int var3) {
        OVillageDoorInfo var4 = null;
        int var5 = Integer.MAX_VALUE;
        Iterator var6 = this.b.iterator();

        while (var6.hasNext()) {
            OVillageDoorInfo var7 = (OVillageDoorInfo) var6.next();
            int var8 = var7.a(var1, var2, var3);

            if (var8 < var5) {
                var4 = var7;
                var5 = var8;
            }
        }

        return var4;
    }

    public OVillageDoorInfo c(int var1, int var2, int var3) {
        OVillageDoorInfo var4 = null;
        int var5 = Integer.MAX_VALUE;
        Iterator var6 = this.b.iterator();

        while (var6.hasNext()) {
            OVillageDoorInfo var7 = (OVillageDoorInfo) var6.next();
            int var8 = var7.a(var1, var2, var3);

            if (var8 > 256) {
                var8 *= 1000;
            } else {
                var8 = var7.f();
            }

            if (var8 < var5) {
                var4 = var7;
                var5 = var8;
            }
        }

        return var4;
    }

    public OVillageDoorInfo d(int var1, int var2, int var3) {
        if (this.d.c(var1, var2, var3) > (this.e * this.e)) {
            return null;
        } else {
            Iterator var4 = this.b.iterator();

            OVillageDoorInfo var5;

            do {
                if (!var4.hasNext()) {
                    return null;
                }

                var5 = (OVillageDoorInfo) var4.next();
            } while (var5.a != var1 || var5.c != var3 || Math.abs(var5.b - var2) > 1);

            return var5;
        }
    }

    public void a(OVillageDoorInfo var1) {
        this.b.add(var1);
        this.c.a += var1.a;
        this.c.b += var1.b;
        this.c.c += var1.c;
        this.l();
        this.f = var1.f;
    }

    public boolean g() {
        return this.b.isEmpty();
    }

    public void a(OEntityLiving var1) {
        Iterator var2 = this.i.iterator();

        OVillageAgressor var3;

        do {
            if (!var2.hasNext()) {
                this.i.add(new OVillageAgressor(this, var1, this.g));
                return;
            }

            var3 = (OVillageAgressor) var2.next();
        } while (var3.a != var1);

        var3.b = this.g;
    }

    public OEntityLiving b(OEntityLiving var1) {
        double var2 = Double.MAX_VALUE;
        OVillageAgressor var4 = null;

        for (int var5 = 0; var5 < this.i.size(); ++var5) {
            OVillageAgressor var6 = (OVillageAgressor) this.i.get(var5);
            double var7 = var6.a.j(var1);

            if (var7 <= var2) {
                var4 = var6;
                var2 = var7;
            }
        }

        return var4 != null ? var4.a : null;
    }

    private void j() {
        Iterator var1 = this.i.iterator();

        while (var1.hasNext()) {
            OVillageAgressor var2 = (OVillageAgressor) var1.next();

            if (!var2.a.aE() || Math.abs(this.g - var2.b) > 300) {
                var1.remove();
            }
        }

    }

    private void k() {
        boolean var1 = false;
        boolean var2 = this.a.r.nextInt(50) == 0;
        Iterator var3 = this.b.iterator();

        while (var3.hasNext()) {
            OVillageDoorInfo var4 = (OVillageDoorInfo) var3.next();

            if (var2) {
                var4.d();
            }

            if (!this.e(var4.a, var4.b, var4.c) || Math.abs(this.g - var4.f) > 1200) {
                this.c.a -= var4.a;
                this.c.b -= var4.b;
                this.c.c -= var4.c;
                var1 = true;
                var4.g = true;
                var3.remove();
            }
        }

        if (var1) {
            this.l();
        }

    }

    private boolean e(int var1, int var2, int var3) {
        int var4 = this.a.a(var1, var2, var3);

        return var4 <= 0 ? false : var4 == OBlock.aE.bO;
    }

    private void l() {
        int var1 = this.b.size();

        if (var1 == 0) {
            this.d.a(0, 0, 0);
            this.e = 0;
        } else {
            this.d.a(this.c.a / var1, this.c.b / var1, this.c.c / var1);
            int var2 = 0;

            OVillageDoorInfo var4;

            for (Iterator var3 = this.b.iterator(); var3.hasNext(); var2 = Math.max(var4.a(this.d.a, this.d.b, this.d.c), var2)) {
                var4 = (OVillageDoorInfo) var3.next();
            }

            this.e = Math.max(32, (int) Math.sqrt(var2) + 1);
        }
    }
}
