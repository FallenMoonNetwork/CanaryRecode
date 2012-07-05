package net.minecraft.server;

import java.util.Random;

import net.canarymod.Canary;
import net.canarymod.api.world.blocks.Dispenser;
import net.canarymod.hook.world.DispenseHook;
import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockContainer;
import net.minecraft.server.OEntityArrow;
import net.minecraft.server.OEntityEgg;
import net.minecraft.server.OEntityExpBottle;
import net.minecraft.server.OEntityItem;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntityPotion;
import net.minecraft.server.OEntitySmallFireball;
import net.minecraft.server.OEntitySnowball;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemMonsterPlacer;
import net.minecraft.server.OItemPotion;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OTileEntity;
import net.minecraft.server.OTileEntityDispenser;
import net.minecraft.server.OWorld;

public class OBlockDispenser extends OBlockContainer {

    private Random a = new Random();

    protected OBlockDispenser(int var1) {
        super(var1, OMaterial.e);
        this.bN = 45;
    }

    @Override
    public int d() {
        return 4;
    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return OBlock.P.bO;
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4) {
        super.a(var1, var2, var3, var4);
        this.g(var1, var2, var3, var4);
    }

    private void g(OWorld var1, int var2, int var3, int var4) {
        if (!var1.F) {
            int var5 = var1.a(var2, var3, var4 - 1);
            int var6 = var1.a(var2, var3, var4 + 1);
            int var7 = var1.a(var2 - 1, var3, var4);
            int var8 = var1.a(var2 + 1, var3, var4);
            byte var9 = 3;
            if (OBlock.n[var5] && !OBlock.n[var6]) {
                var9 = 3;
            }

            if (OBlock.n[var6] && !OBlock.n[var5]) {
                var9 = 2;
            }

            if (OBlock.n[var7] && !OBlock.n[var8]) {
                var9 = 5;
            }

            if (OBlock.n[var8] && !OBlock.n[var7]) {
                var9 = 4;
            }

            var1.c(var2, var3, var4, var9);
        }
    }

    @Override
    public int a(int var1) {
        return var1 == 1 ? this.bN + 17 : (var1 == 0 ? this.bN + 17 : (var1 == 3 ? this.bN + 1 : this.bN));
    }

    @Override
    public boolean a(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
        if (var1.F) {
            return true;
        } else {
            OTileEntityDispenser var6 = (OTileEntityDispenser) var1.b(var2, var3, var4);
            if (var6 != null) {
                var5.a(var6);
            }

            return true;
        }
    }

    private void b(OWorld var1, int var2, int var3, int var4, Random var5) {
        int var6 = var1.c(var2, var3, var4);
        byte var7 = 0;
        byte var8 = 0;
        if (var6 == 3) {
            var8 = 1;
        } else if (var6 == 2) {
            var8 = -1;
        } else if (var6 == 5) {
            var7 = 1;
        } else {
            var7 = -1;
        }

        OTileEntityDispenser var9 = (OTileEntityDispenser) var1.b(var2, var3, var4);
        if (var9 != null) {
            OItemStack var10 = var9.p_();
            double var11 = var2 + var7 * 0.6D + 0.5D;
            double var13 = var3 + 0.5D;
            double var15 = var4 + var8 * 0.6D + 0.5D;
            
            DispenseHook hook;
            Dispenser dispenser = var9.getDispenser();
            if (var10 == null) {
                //CanaryMod - onDispense
                hook = new DispenseHook(dispenser, null);
                Canary.hooks().callHook(hook);
                if(!hook.isCanceled()){
                    var1.f(1001, var2, var3, var4, 0);
                }
            } else {
                if (var10.c == OItem.k.bP) {
                    OEntityArrow var17 = new OEntityArrow(var1, var11, var13, var15);
                    var17.a(var7, 0.10000000149011612D, var8, 1.1F, 6.0F);
                    var17.a = true;
                    //CanaryMod - onDispense
                    hook = new DispenseHook(dispenser, var17.getCanaryEntity());
                    Canary.hooks().callHook(hook);
                    if(!hook.isCanceled()){
                        var1.b(var17);
                        var1.f(1002, var2, var3, var4, 0);
                    }
                } else if (var10.c == OItem.aO.bP) {
                    OEntityEgg var22 = new OEntityEgg(var1, var11, var13, var15);
                    var22.a(var7, 0.10000000149011612D, var8, 1.1F, 6.0F);
                    //CanaryMod start - onDispense
                    hook = new DispenseHook(dispenser, var22.getCanaryEntity());
                    Canary.hooks().callHook(hook);
                    if(!hook.isCanceled()){
                        var1.b(var22);
                        var1.f(1002, var2, var3, var4, 0);
                    }
                } else if (var10.c == OItem.aC.bP) {
                    OEntitySnowball var21 = new OEntitySnowball(var1, var11, var13, var15);
                    var21.a(var7, 0.10000000149011612D, var8, 1.1F, 6.0F);
                    //CanaryMod start - onDispense
                    hook = new DispenseHook(dispenser, var21.getCanaryEntity());
                    Canary.hooks().callHook(hook);
                    if(!hook.isCanceled()){
                        var1.b(var21);
                        var1.f(1002, var2, var3, var4, 0);
                    }
                } else if (var10.c == OItem.br.bP && OItemPotion.c(var10.h())) {
                    OEntityPotion var23 = new OEntityPotion(var1, var11, var13, var15, var10.h());
                    var23.a(var7, 0.10000000149011612D, var8, 1.375F, 3.0F);
                    //CanaryMod start - onDispense
                    hook = new DispenseHook(dispenser, var23.getCanaryEntity());
                    Canary.hooks().callHook(hook);
                    if(!hook.isCanceled()){
                        var1.b(var23);
                        var1.f(1002, var2, var3, var4, 0);
                    }
                } else if (var10.c == OItem.bC.bP) {
                    OEntityExpBottle var20 = new OEntityExpBottle(var1, var11, var13, var15);
                    var20.a(var7, 0.10000000149011612D, var8, 1.375F, 3.0F);
                    //CanaryMod start - onDispense
                    hook = new DispenseHook(dispenser, var20.getCanaryEntity());
                    Canary.hooks().callHook(hook);
                    if(!hook.isCanceled()){
                        var1.b(var20);
                        var1.f(1002, var2, var3, var4, 0);
                    }
                } else if (var10.c == OItem.bB.bP) {
                    //CanaryMod start - onDispense
                    //CanaryMod - get the entity being created
                    OEntity mob = null;
                    if(OEntityList.a.containsKey(Integer.valueOf(var10.h()))){
                        mob = OEntityList.a(var10.h(), var1);
                    }
                    hook = new DispenseHook(dispenser, mob.getCanaryEntity());
                    Canary.hooks().callHook(hook);
                    if(!hook.isCanceled()){
                        OItemMonsterPlacer.a(var1, var10.h(), var11 + var7 * 0.3D, var13 - 0.3D, var15 + var8 * 0.3D);
                        var1.f(1002, var2, var3, var4, 0);
                    }
                } else if (var10.c == OItem.bD.bP) {
                    OEntitySmallFireball var25 = new OEntitySmallFireball(var1, var11 + var7 * 0.3D, var13, var15 + var8 * 0.3D, var7 + var5.nextGaussian() * 0.05D, var5.nextGaussian() * 0.05D, var8 + var5.nextGaussian() * 0.05D);
                  //CanaryMod start - onDispense
                    hook = new DispenseHook(dispenser, var25.getCanaryEntity());
                    Canary.hooks().callHook(hook);
                    if(!hook.isCanceled()){
                        var1.b(var25);
                        var1.f(1009, var2, var3, var4, 0);
                    }
                } else {
                    OEntityItem var24 = new OEntityItem(var1, var11, var13 - 0.3D, var15, var10);
                    double var18 = var5.nextDouble() * 0.1D + 0.2D;
                    var24.bp = var7 * var18;
                    var24.bq = 0.20000000298023224D;
                    var24.br = var8 * var18;
                    var24.bp += var5.nextGaussian() * 0.007499999832361937D * 6.0D;
                    var24.bq += var5.nextGaussian() * 0.007499999832361937D * 6.0D;
                    var24.br += var5.nextGaussian() * 0.007499999832361937D * 6.0D;
                    //CanaryMod start - onDispense
                    hook = new DispenseHook(dispenser, var24.getCanaryEntity());
                    Canary.hooks().callHook(hook);
                    if(!hook.isCanceled()){
                        var1.b(var24);
                        var1.f(1000, var2, var3, var4, 0);
                    }
                }

                var1.f(2000, var2, var3, var4, var7 + 1 + (var8 + 1) * 3);
            }
        }

    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, int var5) {
        if (var5 > 0 && OBlock.m[var5].e()) {
            boolean var6 = var1.x(var2, var3, var4) || var1.x(var2, var3 + 1, var4);
            if (var6) {
                var1.c(var2, var3, var4, this.bO, this.d());
            }
        }

    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
        if (!var1.F && (var1.x(var2, var3, var4) || var1.x(var2, var3 + 1, var4))) {
            this.b(var1, var2, var3, var4, var5);
        }

    }

    @Override
    public OTileEntity a_() {
        return new OTileEntityDispenser();
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, OEntityLiving var5) {
        int var6 = OMathHelper.b((var5.bs * 4.0F / 360.0F) + 0.5D) & 3;
        if (var6 == 0) {
            var1.c(var2, var3, var4, 2);
        }

        if (var6 == 1) {
            var1.c(var2, var3, var4, 5);
        }

        if (var6 == 2) {
            var1.c(var2, var3, var4, 3);
        }

        if (var6 == 3) {
            var1.c(var2, var3, var4, 4);
        }

    }

    @Override
    public void d(OWorld var1, int var2, int var3, int var4) {
        OTileEntityDispenser var5 = (OTileEntityDispenser) var1.b(var2, var3, var4);
        if (var5 != null) {
            for (int var6 = 0; var6 < var5.getInventorySize(); ++var6) {
                OItemStack var7 = var5.g_(var6);
                if (var7 != null) {
                    float var8 = this.a.nextFloat() * 0.8F + 0.1F;
                    float var9 = this.a.nextFloat() * 0.8F + 0.1F;
                    float var10 = this.a.nextFloat() * 0.8F + 0.1F;

                    while (var7.a > 0) {
                        int var11 = this.a.nextInt(21) + 10;
                        if (var11 > var7.a) {
                            var11 = var7.a;
                        }

                        var7.a -= var11;
                        OEntityItem var12 = new OEntityItem(var1, (var2 + var8), (var3 + var9), (var4 + var10), new OItemStack(var7.c, var11, var7.h()));
                        if (var7.n()) {
                            var12.a.d((ONBTTagCompound) var7.o().b());
                        }

                        float var13 = 0.05F;
                        var12.bp = ((float) this.a.nextGaussian() * var13);
                        var12.bq = ((float) this.a.nextGaussian() * var13 + 0.2F);
                        var12.br = ((float) this.a.nextGaussian() * var13);
                        var1.b(var12);
                    }
                }
            }
        }

        super.d(var1, var2, var3, var4);
    }
}
