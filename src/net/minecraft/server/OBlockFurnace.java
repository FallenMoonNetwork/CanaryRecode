package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockContainer;
import net.minecraft.server.OEntityItem;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OTileEntity;
import net.minecraft.server.OTileEntityFurnace;
import net.minecraft.server.OWorld;

public class OBlockFurnace extends OBlockContainer {

    private Random a = new Random();
    private final boolean b;
    private static boolean c = false;

    protected OBlockFurnace(int var1, boolean var2) {
        super(var1, OMaterial.e);
        this.b = var2;
        this.bN = 45;
    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return OBlock.aB.bO;
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
        return var1 == 1 ? this.bN + 17 : (var1 == 0 ? this.bN + 17 : (var1 == 3 ? this.bN - 1 : this.bN));
    }

    @Override
    public boolean a(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
        if (var1.F) {
            return true;
        } else {
            OTileEntityFurnace var6 = (OTileEntityFurnace) var1.b(var2, var3, var4);
            if (var6 != null) {
                var5.a(var6);
            }

            return true;
        }
    }

    public static void a(boolean var0, OWorld var1, int var2, int var3, int var4) {
        int var5 = var1.c(var2, var3, var4);
        OTileEntity var6 = var1.b(var2, var3, var4);
        c = true;
        if (var0) {
            var1.e(var2, var3, var4, OBlock.aC.bO);
        } else {
            var1.e(var2, var3, var4, OBlock.aB.bO);
        }

        c = false;
        var1.c(var2, var3, var4, var5);
        if (var6 != null) {
            var6.m();
            var1.a(var2, var3, var4, var6);
        }

    }

    @Override
    public OTileEntity a_() {
        return new OTileEntityFurnace();
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
        if (!c) {
            OTileEntityFurnace var5 = (OTileEntityFurnace) var1.b(var2, var3, var4);
            if (var5 != null) {
                for (int var6 = 0; var6 < var5.getInventorySize(); ++var6) {
                    OItemStack var7 = var5.getStackFromSlot(var6);
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
        }

        super.d(var1, var2, var3, var4);
    }

}
