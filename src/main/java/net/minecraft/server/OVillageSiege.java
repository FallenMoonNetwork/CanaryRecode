package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import net.minecraft.server.OChunkCoordinates;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntityZombie;
import net.minecraft.server.OEnumCreatureType;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OSpawnerAnimals;
import net.minecraft.server.OVec3D;
import net.minecraft.server.OVillage;
import net.minecraft.server.OWorld;

public class OVillageSiege {

    private OWorld a;
    private boolean b = false;
    private int c = -1;
    private int d;
    private int e;
    private OVillage f;
    private int g;
    private int h;
    private int i;

    public OVillageSiege(OWorld var1) {
        super();
        this.a = var1;
    }

    public void a() {
        boolean var1 = false;
        if (var1) {
            if (this.c == 2) {
                this.d = 100;
                return;
            }
        } else {
            if (this.a.e()) {
                this.c = 0;
                return;
            }

            if (this.c == 2) {
                return;
            }

            if (this.c == 0) {
                float var2 = this.a.b(0.0F);
                if (var2 < 0.5D || var2 > 0.501D) {
                    return;
                }

                this.c = this.a.r.nextInt(10) == 0 ? 1 : 2;
                this.b = false;
                if (this.c == 2) {
                    return;
                }
            }
        }

        if (!this.b) {
            if (!this.b()) {
                return;
            }

            this.b = true;
        }

        if (this.e > 0) {
            --this.e;
        } else {
            this.e = 2;
            if (this.d > 0) {
                this.c();
                --this.d;
            } else {
                this.c = 2;
            }

        }
    }

    private boolean b() {
        List var1 = this.a.d;
        Iterator var2 = var1.iterator();

        while (var2.hasNext()) {
            OEntityPlayer var3 = (OEntityPlayer) var2.next();
            this.f = this.a.A.a((int) var3.bm, (int) var3.bn, (int) var3.bo, 1);
            if (this.f != null && this.f.c() >= 10 && this.f.d() >= 20 && this.f.e() >= 20) {
                OChunkCoordinates var4 = this.f.a();
                float var5 = this.f.b();
                boolean var6 = false;
                int var7 = 0;

                while (true) {
                    if (var7 < 10) {
                        this.g = var4.a + (int) ((OMathHelper.b(this.a.r.nextFloat() * 3.1415927F * 2.0F) * var5) * 0.9D);
                        this.h = var4.b;
                        this.i = var4.c + (int) ((OMathHelper.a(this.a.r.nextFloat() * 3.1415927F * 2.0F) * var5) * 0.9D);
                        var6 = false;
                        Iterator var8 = this.a.A.b().iterator();

                        while (var8.hasNext()) {
                            OVillage var9 = (OVillage) var8.next();
                            if (var9 != this.f && var9.a(this.g, this.h, this.i)) {
                                var6 = true;
                                break;
                            }
                        }

                        if (var6) {
                            ++var7;
                            continue;
                        }
                    }

                    if (var6) {
                        return false;
                    }

                    OVec3D var10 = this.a(this.g, this.h, this.i);
                    if (var10 != null) {
                        this.e = 0;
                        this.d = 20;
                        return true;
                    }
                    break;
                }
            }
        }

        return false;
    }

    private boolean c() {
        OVec3D var1 = this.a(this.g, this.h, this.i);
        if (var1 == null) {
            return false;
        } else {
            OEntityZombie var2;
            try {
                var2 = new OEntityZombie(this.a);
            } catch (Exception var4) {
                var4.printStackTrace();
                return false;
            }

            var2.c(var1.a, var1.b, var1.c, this.a.r.nextFloat() * 360.0F, 0.0F);
            this.a.b(var2);
            OChunkCoordinates var3 = this.f.a();
            var2.b(var3.a, var3.b, var3.c, this.f.b());
            return true;
        }
    }

    private OVec3D a(int var1, int var2, int var3) {
        for (int var4 = 0; var4 < 10; ++var4) {
            int var5 = var1 + this.a.r.nextInt(16) - 8;
            int var6 = var2 + this.a.r.nextInt(6) - 3;
            int var7 = var3 + this.a.r.nextInt(16) - 8;
            if (this.f.a(var5, var6, var7) && OSpawnerAnimals.a(OEnumCreatureType.a, this.a, var5, var6, var7)) {
                return OVec3D.b(var5, var6, var7);
            }
        }

        return null;
    }
}
