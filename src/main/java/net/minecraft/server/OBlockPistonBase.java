package net.minecraft.server;

import java.util.ArrayList;

import net.canarymod.Canary;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.hook.CancelableHook;
import net.canarymod.hook.player.TeleportHook;
import net.canarymod.hook.world.PistonHook;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockContainer;
import net.minecraft.server.OBlockPistonMoving;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OFacing;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OTileEntity;
import net.minecraft.server.OTileEntityPiston;
import net.minecraft.server.OWorld;

public class OBlockPistonBase extends OBlock {

    private boolean a;
    private static boolean b;
    private boolean attemptRetractBlock; //CanaryMod

    public OBlockPistonBase(int var1, int var2, boolean var3) {
        super(var1, var2, OMaterial.E);
        a = var3;
        this.a(h);
        this.c(0.5F);
    }

    @Override
    public int a(int var1, int var2) {
        int var3 = d(var2);
        return var3 > 5 ? this.bN : (var1 == var3 ? (!e(var2) && this.bV <= 0.0D && this.bW <= 0.0D && this.bX <= 0.0D && this.bY >= 1.0D && this.bZ >= 1.0D && this.ca >= 1.0D ? this.bN : 110) : (var1 == OFacing.a[var3] ? 109 : 108));
    }

    @Override
    public int c() {
        return 16;
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public boolean a(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
        return false;
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, OEntityLiving var5) {
        int var6 = c(var1, var2, var3, var4, (OEntityPlayer) var5);
        var1.c(var2, var3, var4, var6);
        if (!var1.F && !b) {
            this.g(var1, var2, var3, var4);
        }

    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, int var5) {
        if (!var1.F && !b) {
            this.g(var1, var2, var3, var4);
        }

    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4) {
        if (!var1.F && var1.b(var2, var3, var4) == null && !b) {
            this.g(var1, var2, var3, var4);
        }

    }

    private void g(OWorld var1, int var2, int var3, int var4) {
        int var5 = var1.c(var2, var3, var4);
        int var6 = d(var5);
        boolean var7 = this.f(var1, var2, var3, var4, var6);
        if (var5 != 7) {
            if (var7 && !e(var5)) {
                if (g(var1, var2, var3, var4, var6)) {
                    
                    //CanaryMod onPistonExtend start
                    Block piston = new CanaryBlock((this.a ? (short)29 : (short)33), (byte)0, var2, var3, var4, var1.getCanaryDimension());
                    Block moving = new CanaryBlock((short)var1.a(var2 + OFacing.b[var6], var3 + OFacing.c[var6], var4 + OFacing.d[var6]), (byte)0, (var2 + OFacing.b[var6]), (var3 + OFacing.c[var6]), (var4 + OFacing.d[var6]), var1.getCanaryDimension());
                    PistonHook hook = new PistonHook(piston, moving, true);
                    Canary.hooks().callHook(hook);
                    if (hook.isCanceled()) {
                        return;
                    }
                    //CanaryMod onPistonExtend end
                    
                    var1.d(var2, var3, var4, var6 | 8);
                    var1.e(var2, var3, var4, 0, var6);
                }
            } else if (!var7 && e(var5)) {
                
                //CanaryMod onPistonRetract start
                Block piston = new CanaryBlock((this.a ? (short)29 : (short)33), (byte)0, var2, var3, var4, var1.getCanaryDimension());
                Block moving = new CanaryBlock((short)var1.a(var2 + OFacing.b[var6] * 2, var3 + OFacing.c[var6] * 2, var4 + OFacing.d[var6] * 2), (byte)0, (var2 + OFacing.b[var6]), (var3 + OFacing.c[var6]), (var4 + OFacing.d[var6]), var1.getCanaryDimension());
                PistonHook hook = new PistonHook(piston, moving, false);
                Canary.hooks().callHook(hook);
                attemptRetractBlock = !hook.isCanceled();
                //CanaryMod onPistonRetract end
                
                var1.d(var2, var3, var4, var6);
                var1.e(var2, var3, var4, 1, var6);
            }

        }
    }

    private boolean f(OWorld var1, int var2, int var3, int var4, int var5) {
        return var5 != 0 && var1.j(var2, var3 - 1, var4, 0) ? true : (var5 != 1 && var1.j(var2, var3 + 1, var4, 1) ? true : (var5 != 2 && var1.j(var2, var3, var4 - 1, 2) ? true : (var5 != 3 && var1.j(var2, var3, var4 + 1, 3) ? true : (var5 != 5 && var1.j(var2 + 1, var3, var4, 5) ? true : (var5 != 4 && var1.j(var2 - 1, var3, var4, 4) ? true : (var1.j(var2, var3, var4, 0) ? true : (var1.j(var2, var3 + 2, var4, 1) ? true : (var1.j(var2, var3 + 1, var4 - 1, 2) ? true : (var1.j(var2, var3 + 1, var4 + 1, 3) ? true : (var1.j(var2 - 1, var3 + 1, var4, 4) ? true : var1.j(var2 + 1, var3 + 1, var4, 5)))))))))));
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, int var5, int var6) {
        b = true;
        if (var5 == 0) {
            if (this.h(var1, var2, var3, var4, var6)) {
                var1.c(var2, var3, var4, var6 | 8);
                var1.a(var2 + 0.5D, var3 + 0.5D, var4 + 0.5D, "tile.piston.out", 0.5F, var1.r.nextFloat() * 0.25F + 0.6F);
            } else {
                var1.d(var2, var3, var4, var6);
            }
        } else if (var5 == 1) {
            OTileEntity var8 = var1.b(var2 + OFacing.b[var6], var3 + OFacing.c[var6], var4 + OFacing.d[var6]);
            if (var8 != null && var8 instanceof OTileEntityPiston) {
                ((OTileEntityPiston) var8).g();
            }

            var1.a(var2, var3, var4, OBlock.ac.bO, var6);
            var1.a(var2, var3, var4, OBlockPistonMoving.a(this.bO, var6, var6, false, true));
            if (this.a) {
                int var9 = var2 + OFacing.b[var6] * 2;
                int var10 = var3 + OFacing.c[var6] * 2;
                int var11 = var4 + OFacing.d[var6] * 2;
                int var12 = var1.a(var9, var10, var11);
                int var13 = var1.c(var9, var10, var11);
                boolean var14 = false;
                if (var12 == OBlock.ac.bO) {
                    OTileEntity var15 = var1.b(var9, var10, var11);
                    if (var15 != null && var15 instanceof OTileEntityPiston) {
                        OTileEntityPiston var16 = (OTileEntityPiston) var15;
                        if (var16.f() == var6 && var16.e()) {
                            var16.g();
                            var12 = var16.c();
                            var13 = var16.k();
                            var14 = true;
                        }
                    }
                }

                if (this.attemptRetractBlock && !var14 && var12 > 0 && a(var12, var1, var9, var10, var11, false) && (OBlock.m[var12].g() == 0 || var12 == OBlock.Z.bO || var12 == OBlock.V.bO)) {
                    var2 += OFacing.b[var6];
                    var3 += OFacing.c[var6];
                    var4 += OFacing.d[var6];
                    var1.a(var2, var3, var4, OBlock.ac.bO, var13);
                    var1.a(var2, var3, var4, OBlockPistonMoving.a(var12, var13, var6, false, false));
                    b = false;
                    var1.e(var9, var10, var11, 0);
                    b = true;
                } else if (!var14 || !this.attemptRetractBlock) {
                    b = false;
                    var1.e(var2 + OFacing.b[var6], var3 + OFacing.c[var6], var4 + OFacing.d[var6], 0);
                    b = true;
                }
            } else {
                b = false;
                var1.e(var2 + OFacing.b[var6], var3 + OFacing.c[var6], var4 + OFacing.d[var6], 0);
                b = true;
            }

            var1.a(var2 + 0.5D, var3 + 0.5D, var4 + 0.5D, "tile.piston.in", 0.5F, var1.r.nextFloat() * 0.15F + 0.6F);
        }

        b = false;
    }

    @Override
    public void a(OIBlockAccess var1, int var2, int var3, int var4) {
        int var5 = var1.c(var2, var3, var4);
        if (e(var5)) {
            switch (d(var5)) {
            case 0:
                this.a(0.0F, 0.25F, 0.0F, 1.0F, 1.0F, 1.0F);
                break;
            case 1:
                this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
                break;
            case 2:
                this.a(0.0F, 0.0F, 0.25F, 1.0F, 1.0F, 1.0F);
                break;
            case 3:
                this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.75F);
                break;
            case 4:
                this.a(0.25F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                break;
            case 5:
                this.a(0.0F, 0.0F, 0.0F, 0.75F, 1.0F, 1.0F);
            }
        } else {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }

    }

    @Override
    public void f() {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, OAxisAlignedBB var5, ArrayList var6) {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.a(var1, var2, var3, var4, var5, var6);
    }

    @Override
    public OAxisAlignedBB e(OWorld var1, int var2, int var3, int var4) {
        this.a((OIBlockAccess) var1, var2, var3, var4);
        return super.e(var1, var2, var3, var4);
    }

    @Override
    public boolean b() {
        return false;
    }

    public static int d(int var0) {
        return var0 & 7;
    }

    public static boolean e(int var0) {
        return (var0 & 8) != 0;
    }

    private static int c(OWorld var0, int var1, int var2, int var3, OEntityPlayer var4) {
        if (OMathHelper.e((float) var4.bm - var1) < 2.0F && OMathHelper.e((float) var4.bo - var3) < 2.0F) {
            double var5 = var4.bn + 1.82D - var4.bF;
            if (var5 - var2 > 2.0D) {
                return 1;
            }

            if (var2 - var5 > 0.0D) {
                return 0;
            }
        }

        int var7 = OMathHelper.b((var4.bs * 4.0F / 360.0F) + 0.5D) & 3;
        return var7 == 0 ? 2 : (var7 == 1 ? 5 : (var7 == 2 ? 3 : (var7 == 3 ? 4 : 0)));
    }

    private static boolean a(int var0, OWorld var1, int var2, int var3, int var4, boolean var5) {
        if (var0 == OBlock.ap.bO) {
            return false;
        } else {
            if (var0 != OBlock.Z.bO && var0 != OBlock.V.bO) {
                if (OBlock.m[var0].m() == -1.0F) {
                    return false;
                }

                if (OBlock.m[var0].g() == 2) {
                    return false;
                }

                if (!var5 && OBlock.m[var0].g() == 1) {
                    return false;
                }
            } else if (e(var1.c(var2, var3, var4))) {
                return false;
            }
            
            return !(OBlock.m[var0] instanceof OBlockContainer);
        }
    }

    private static boolean g(OWorld var0, int var1, int var2, int var3, int var4) {
        int var5 = var1 + OFacing.b[var4];
        int var6 = var2 + OFacing.c[var4];
        int var7 = var3 + OFacing.d[var4];
        int var8 = 0;

        while (true) {
            if (var8 < 13) {
                if (var6 <= 0 || var6 >= 255) {
                    return false;
                }

                int var9 = var0.a(var5, var6, var7);
                
                if (var9 != 0) {
                    if (!a(var9, var0, var5, var6, var7, true)) {
                        return false;
                    }
                    
                    if (OBlock.m[var9].g() != 1) {
                        if (var8 == 12) {
                            return false;
                        }

                        var5 += OFacing.b[var4];
                        var6 += OFacing.c[var4];
                        var7 += OFacing.d[var4];
                        ++var8;
                        continue;
                    }
                }
            }

            return true;
        }
    }

    private boolean h(OWorld var1, int var2, int var3, int var4, int var5) {
        int var6 = var2 + OFacing.b[var5];
        int var7 = var3 + OFacing.c[var5];
        int var8 = var4 + OFacing.d[var5];
        int var9 = 0;

        while (true) {
            int var10;
            if (var9 < 13) {
                if (var7 <= 0 || var7 >= 255) {
                    return false;
                }

                var10 = var1.a(var6, var7, var8);
                
                if (var10 != 0) {
                    if (!a(var10, var1, var6, var7, var8, true)) {
                        return false;
                    }

                    if (OBlock.m[var10].g() != 1) {
                        if (var9 == 12) {
                            return false;
                        }

                        var6 += OFacing.b[var5];
                        var7 += OFacing.c[var5];
                        var8 += OFacing.d[var5];
                        ++var9;
                        continue;
                    }

                    OBlock.m[var10].b(var1, var6, var7, var8, var1.c(var6, var7, var8), 0);
                    var1.e(var6, var7, var8, 0);
                }
            }

            while (var6 != var2 || var7 != var3 || var8 != var4) {
                var9 = var6 - OFacing.b[var5];
                var10 = var7 - OFacing.c[var5];
                int var11 = var8 - OFacing.d[var5];
                int var12 = var1.a(var9, var10, var11);
                int var13 = var1.c(var9, var10, var11);
                if (var12 == this.bO && var9 == var2 && var10 == var3 && var11 == var4) {
                    var1.a(var6, var7, var8, OBlock.ac.bO, var5 | (this.a ? 8 : 0));
                    var1.a(var6, var7, var8, OBlockPistonMoving.a(OBlock.aa.bO, var5 | (this.a ? 8 : 0), var5, true, false));
                } else {
                    var1.a(var6, var7, var8, OBlock.ac.bO, var13);
                    var1.a(var6, var7, var8, OBlockPistonMoving.a(var12, var13, var5, true, false));
                }

                var6 = var9;
                var7 = var10;
                var8 = var11;
            }

            return true;
        }
    }
}
