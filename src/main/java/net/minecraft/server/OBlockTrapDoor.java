package net.minecraft.server;

import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OMovingObjectPosition;
import net.minecraft.server.OVec3D;
import net.minecraft.server.OWorld;

public class OBlockTrapDoor extends OBlock {

    protected OBlockTrapDoor(int var1, OMaterial var2) {
        super(var1, var2);
        this.bN = 84;
        if (var2 == OMaterial.f) {
            ++this.bN;
        }

        float var3 = 0.5F;
        float var4 = 1.0F;
        this.a(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var4, 0.5F + var3);
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public boolean b() {
        return false;
    }

    @Override
    public boolean b(OIBlockAccess var1, int var2, int var3, int var4) {
        return !e(var1.c(var2, var3, var4));
    }

    @Override
    public int c() {
        return 0;
    }

    @Override
    public OAxisAlignedBB e(OWorld var1, int var2, int var3, int var4) {
        this.a(var1, var2, var3, var4);
        return super.e(var1, var2, var3, var4);
    }

    @Override
    public void a(OIBlockAccess var1, int var2, int var3, int var4) {
        this.d(var1.c(var2, var3, var4));
    }

    @Override
    public void f() {
        float var1 = 0.1875F;
        this.a(0.0F, 0.5F - var1 / 2.0F, 0.0F, 1.0F, 0.5F + var1 / 2.0F, 1.0F);
    }

    public void d(int var1) {
        float var2 = 0.1875F;
        this.a(0.0F, 0.0F, 0.0F, 1.0F, var2, 1.0F);
        if (e(var1)) {
            if ((var1 & 3) == 0) {
                this.a(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
            }

            if ((var1 & 3) == 1) {
                this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
            }

            if ((var1 & 3) == 2) {
                this.a(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            }

            if ((var1 & 3) == 3) {
                this.a(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
            }
        }

    }

    @Override
    public void b(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
        this.a(var1, var2, var3, var4, var5);
    }

    @Override
    public boolean a(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
        if (this.cd == OMaterial.f) {
            return true;
        } else {
            int var6 = var1.c(var2, var3, var4);
            var1.c(var2, var3, var4, var6 ^ 4);
            var1.a(var5, 1003, var2, var3, var4, 0);
            return true;
        }
    }

    public void a(OWorld var1, int var2, int var3, int var4, boolean var5) {
        int var6 = var1.c(var2, var3, var4);
        boolean var7 = (var6 & 4) > 0;
        if (var7 != var5) {
            var1.c(var2, var3, var4, var6 ^ 4);
            var1.a((OEntityPlayer) null, 1003, var2, var3, var4, 0);
        }
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, int var5) {
        if (!var1.F) {
            int var6 = var1.c(var2, var3, var4);
            int var7 = var2;
            int var8 = var4;
            if ((var6 & 3) == 0) {
                var8 = var4 + 1;
            }

            if ((var6 & 3) == 1) {
                --var8;
            }

            if ((var6 & 3) == 2) {
                var7 = var2 + 1;
            }

            if ((var6 & 3) == 3) {
                --var7;
            }

            if (!h(var1.a(var7, var3, var8))) {
                var1.e(var2, var3, var4, 0);
                this.b(var1, var2, var3, var4, var6, 0);
            }

            boolean var9 = var1.x(var2, var3, var4);
            if (var9 || var5 > 0 && OBlock.m[var5].e() || var5 == 0) {
                this.a(var1, var2, var3, var4, var9);
            }

        }
    }

    @Override
    public OMovingObjectPosition a(OWorld var1, int var2, int var3, int var4, OVec3D var5, OVec3D var6) {
        this.a(var1, var2, var3, var4);
        return super.a(var1, var2, var3, var4, var5, var6);
    }

    @Override
    public void e(OWorld var1, int var2, int var3, int var4, int var5) {
        byte var6 = 0;
        if (var5 == 2) {
            var6 = 0;
        }

        if (var5 == 3) {
            var6 = 1;
        }

        if (var5 == 4) {
            var6 = 2;
        }

        if (var5 == 5) {
            var6 = 3;
        }

        var1.c(var2, var3, var4, var6);
    }

    @Override
    public boolean b(OWorld var1, int var2, int var3, int var4, int var5) {
        if (var5 == 0) {
            return false;
        } else if (var5 == 1) {
            return false;
        } else {
            if (var5 == 2) {
                ++var4;
            }

            if (var5 == 3) {
                --var4;
            }

            if (var5 == 4) {
                ++var2;
            }

            if (var5 == 5) {
                --var2;
            }

            return h(var1.a(var2, var3, var4));
        }
    }

    public static boolean e(int var0) {
        return (var0 & 4) != 0;
    }

    private static boolean h(int var0) {
        if (var0 <= 0) {
            return false;
        } else {
            OBlock var1 = OBlock.m[var0];
            return var1 != null && var1.cd.j() && var1.b() || var1 == OBlock.bd;
        }
    }
}
