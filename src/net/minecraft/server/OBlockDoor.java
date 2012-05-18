package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OItem;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OMovingObjectPosition;
import net.minecraft.server.OVec3D;
import net.minecraft.server.OWorld;

public class OBlockDoor extends OBlock {

    protected OBlockDoor(int var1, OMaterial var2) {
        super(var1, var2);
        this.bN = 97;
        if (var2 == OMaterial.f) {
            ++this.bN;
        }

        float var3 = 0.5F;
        float var4 = 1.0F;
        this.a(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var4, 0.5F + var3);
    }

    public boolean a() {
        return false;
    }

    public boolean b(OIBlockAccess var1, int var2, int var3, int var4) {
        int var5 = this.e(var1, var2, var3, var4);
        return (var5 & 4) != 0;
    }

    public boolean b() {
        return false;
    }

    public int c() {
        return 7;
    }

    public OAxisAlignedBB e(OWorld var1, int var2, int var3, int var4) {
        this.a(var1, var2, var3, var4);
        return super.e(var1, var2, var3, var4);
    }

    public void a(OIBlockAccess var1, int var2, int var3, int var4) {
        this.d(this.e(var1, var2, var3, var4));
    }

    public int c(OIBlockAccess var1, int var2, int var3, int var4) {
        return this.e(var1, var2, var3, var4) & 3;
    }

    public boolean d(OIBlockAccess var1, int var2, int var3, int var4) {
        return (this.e(var1, var2, var3, var4) & 4) != 0;
    }

    private void d(int var1) {
        float var2 = 0.1875F;
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
        int var3 = var1 & 3;
        boolean var4 = (var1 & 4) != 0;
        boolean var5 = (var1 & 16) != 0;
        if (var3 == 0) {
            if (!var4) {
                this.a(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
            } else if (!var5) {
                this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
            } else {
                this.a(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
            }
        } else if (var3 == 1) {
            if (!var4) {
                this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
            } else if (!var5) {
                this.a(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            } else {
                this.a(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
            }
        } else if (var3 == 2) {
            if (!var4) {
                this.a(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            } else if (!var5) {
                this.a(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
            } else {
                this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
            }
        } else if (var3 == 3) {
            if (!var4) {
                this.a(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
            } else if (!var5) {
                this.a(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
            } else {
                this.a(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            }
        }

    }

    public void b(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
        this.a(var1, var2, var3, var4, var5);
    }

    public boolean a(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
        if (this.cd == OMaterial.f) {
            return true;
        } else {
            int var6 = this.e((OIBlockAccess) var1, var2, var3, var4);
            int var7 = var6 & 7;
            var7 ^= 4;
            if ((var6 & 8) != 0) {
                var1.c(var2, var3 - 1, var4, var7);
                var1.b(var2, var3 - 1, var4, var2, var3, var4);
            } else {
                var1.c(var2, var3, var4, var7);
                var1.b(var2, var3, var4, var2, var3, var4);
            }

            var1.a(var5, 1003, var2, var3, var4, 0);
            return true;
        }
    }

    public void a(OWorld var1, int var2, int var3, int var4, boolean var5) {
        int var6 = this.e((OIBlockAccess) var1, var2, var3, var4);
        boolean var7 = (var6 & 4) != 0;
        if (var7 != var5) {
            int var8 = var6 & 7;
            var8 ^= 4;
            if ((var6 & 8) != 0) {
                var1.c(var2, var3 - 1, var4, var8);
                var1.b(var2, var3 - 1, var4, var2, var3, var4);
            } else {
                var1.c(var2, var3, var4, var8);
                var1.b(var2, var3, var4, var2, var3, var4);
            }

            var1.a((OEntityPlayer) null, 1003, var2, var3, var4, 0);
        }
    }

    public void a(OWorld var1, int var2, int var3, int var4, int var5) {
        int var6 = var1.c(var2, var3, var4);
        if ((var6 & 8) != 0) {
            if (var1.a(var2, var3 - 1, var4) != this.bO) {
                var1.e(var2, var3, var4, 0);
            }

            if (var5 > 0 && var5 != this.bO) {
                this.a(var1, var2, var3 - 1, var4, var5);
            }
        } else {
            boolean var7 = false;
            if (var1.a(var2, var3 + 1, var4) != this.bO) {
                var1.e(var2, var3, var4, 0);
                var7 = true;
            }

            if (!var1.e(var2, var3 - 1, var4)) {
                var1.e(var2, var3, var4, 0);
                var7 = true;
                if (var1.a(var2, var3 + 1, var4) == this.bO) {
                    var1.e(var2, var3 + 1, var4, 0);
                }
            }

            if (var7) {
                if (!var1.F) {
                    this.b(var1, var2, var3, var4, var6, 0);
                }
            } else {
                boolean var8 = var1.x(var2, var3, var4) || var1.x(var2, var3 + 1, var4);
                if ((var8 || var5 > 0 && OBlock.m[var5].e() || var5 == 0) && var5 != this.bO) {
                    this.a(var1, var2, var3, var4, var8);
                }
            }
        }

    }

    public int a(int var1, Random var2, int var3) {
        return (var1 & 8) != 0 ? 0 : (this.cd == OMaterial.f ? OItem.aA.bP : OItem.au.bP);
    }

    public OMovingObjectPosition a(OWorld var1, int var2, int var3, int var4, OVec3D var5, OVec3D var6) {
        this.a(var1, var2, var3, var4);
        return super.a(var1, var2, var3, var4, var5, var6);
    }

    public boolean c(OWorld var1, int var2, int var3, int var4) {
        return var3 >= 255 ? false : var1.e(var2, var3 - 1, var4) && super.c(var1, var2, var3, var4) && super.c(var1, var2, var3 + 1, var4);
    }

    public int g() {
        return 1;
    }

    public int e(OIBlockAccess var1, int var2, int var3, int var4) {
        int var5 = var1.c(var2, var3, var4);
        boolean var6 = (var5 & 8) != 0;
        int var7;
        int var8;
        if (var6) {
            var7 = var1.c(var2, var3 - 1, var4);
            var8 = var5;
        } else {
            var7 = var5;
            var8 = var1.c(var2, var3 + 1, var4);
        }

        boolean var9 = (var8 & 1) != 0;
        int var10 = var7 & 7 | (var6 ? 8 : 0) | (var9 ? 16 : 0);
        return var10;
    }
}
