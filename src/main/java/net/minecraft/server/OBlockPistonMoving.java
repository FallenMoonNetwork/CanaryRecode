package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockContainer;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OFacing;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OTileEntity;
import net.minecraft.server.OTileEntityPiston;
import net.minecraft.server.OWorld;

public class OBlockPistonMoving extends OBlockContainer {

    public OBlockPistonMoving(int var1) {
        super(var1, OMaterial.E);
        this.c(-1.0F);
    }

    @Override
    public OTileEntity a_() {
        return null;
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4) {
    }

    @Override
    public void d(OWorld var1, int var2, int var3, int var4) {
        OTileEntity var5 = var1.b(var2, var3, var4);
        if (var5 != null && var5 instanceof OTileEntityPiston) {
            ((OTileEntityPiston) var5).g();
        } else {
            super.d(var1, var2, var3, var4);
        }

    }

    @Override
    public boolean c(OWorld var1, int var2, int var3, int var4) {
        return false;
    }

    @Override
    public boolean b(OWorld var1, int var2, int var3, int var4, int var5) {
        return false;
    }

    @Override
    public int c() {
        return -1;
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
    public boolean a(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
        if (!var1.F && var1.b(var2, var3, var4) == null) {
            var1.e(var2, var3, var4, 0);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return 0;
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, int var5, float var6, int var7) {
        if (!var1.F) {
            OTileEntityPiston var8 = this.c((OIBlockAccess) var1, var2, var3, var4);
            if (var8 != null) {
                OBlock.m[var8.c()].b(var1, var2, var3, var4, var8.k(), 0);
            }
        }
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, int var5) {
        if (!var1.F && var1.b(var2, var3, var4) == null) {
            ;
        }

    }

    public static OTileEntity a(int var0, int var1, int var2, boolean var3, boolean var4) {
        return new OTileEntityPiston(var0, var1, var2, var3, var4);
    }

    @Override
    public OAxisAlignedBB e(OWorld var1, int var2, int var3, int var4) {
        OTileEntityPiston var5 = this.c((OIBlockAccess) var1, var2, var3, var4);
        if (var5 == null) {
            return null;
        } else {
            float var6 = var5.a(0.0F);
            if (var5.e()) {
                var6 = 1.0F - var6;
            }

            return this.b(var1, var2, var3, var4, var5.c(), var6, var5.f());
        }
    }

    @Override
    public void a(OIBlockAccess var1, int var2, int var3, int var4) {
        OTileEntityPiston var5 = this.c(var1, var2, var3, var4);
        if (var5 != null) {
            OBlock var6 = OBlock.m[var5.c()];
            if (var6 == null || var6 == this) {
                return;
            }

            var6.a(var1, var2, var3, var4);
            float var7 = var5.a(0.0F);
            if (var5.e()) {
                var7 = 1.0F - var7;
            }

            int var8 = var5.f();
            this.bV = var6.bV - (OFacing.b[var8] * var7);
            this.bW = var6.bW - (OFacing.c[var8] * var7);
            this.bX = var6.bX - (OFacing.d[var8] * var7);
            this.bY = var6.bY - (OFacing.b[var8] * var7);
            this.bZ = var6.bZ - (OFacing.c[var8] * var7);
            this.ca = var6.ca - (OFacing.d[var8] * var7);
        }

    }

    public OAxisAlignedBB b(OWorld var1, int var2, int var3, int var4, int var5, float var6, int var7) {
        if (var5 != 0 && var5 != this.bO) {
            OAxisAlignedBB var8 = OBlock.m[var5].e(var1, var2, var3, var4);
            if (var8 == null) {
                return null;
            } else {
                if (OFacing.b[var7] < 0) {
                    var8.a -= (OFacing.b[var7] * var6);
                } else {
                    var8.d -= (OFacing.b[var7] * var6);
                }

                if (OFacing.c[var7] < 0) {
                    var8.b -= (OFacing.c[var7] * var6);
                } else {
                    var8.e -= (OFacing.c[var7] * var6);
                }

                if (OFacing.d[var7] < 0) {
                    var8.c -= (OFacing.d[var7] * var6);
                } else {
                    var8.f -= (OFacing.d[var7] * var6);
                }

                return var8;
            }
        } else {
            return null;
        }
    }

    private OTileEntityPiston c(OIBlockAccess var1, int var2, int var3, int var4) {
        OTileEntity var5 = var1.b(var2, var3, var4);
        return var5 != null && var5 instanceof OTileEntityPiston ? (OTileEntityPiston) var5 : null;
    }
}
