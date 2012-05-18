package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlockContainer;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityItem;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OTileEntity;
import net.minecraft.server.OTileEntityBrewingStand;
import net.minecraft.server.OWorld;

public class OBlockBrewingStand extends OBlockContainer {

    private Random a = new Random();

    public OBlockBrewingStand(int var1) {
        super(var1, OMaterial.f);
        this.bN = 157;
    }

    public boolean a() {
        return false;
    }

    public int c() {
        return 25;
    }

    public OTileEntity a_() {
        return new OTileEntityBrewingStand();
    }

    public boolean b() {
        return false;
    }

    public void a(OWorld var1, int var2, int var3, int var4, OAxisAlignedBB var5, ArrayList var6) {
        this.a(0.4375F, 0.0F, 0.4375F, 0.5625F, 0.875F, 0.5625F);
        super.a(var1, var2, var3, var4, var5, var6);
        this.f();
        super.a(var1, var2, var3, var4, var5, var6);
    }

    public void f() {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
    }

    public boolean a(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
        if (var1.F) {
            return true;
        } else {
            OTileEntityBrewingStand var6 = (OTileEntityBrewingStand) var1.b(var2, var3, var4);
            if (var6 != null) {
                var5.a(var6);
            }

            return true;
        }
    }

    public void d(OWorld var1, int var2, int var3, int var4) {
        OTileEntity var5 = var1.b(var2, var3, var4);
        if (var5 != null && var5 instanceof OTileEntityBrewingStand) {
            OTileEntityBrewingStand var6 = (OTileEntityBrewingStand) var5;

            for (int var7 = 0; var7 < var6.c(); ++var7) {
                OItemStack var8 = var6.g_(var7);
                if (var8 != null) {
                    float var9 = this.a.nextFloat() * 0.8F + 0.1F;
                    float var10 = this.a.nextFloat() * 0.8F + 0.1F;
                    float var11 = this.a.nextFloat() * 0.8F + 0.1F;

                    while (var8.a > 0) {
                        int var12 = this.a.nextInt(21) + 10;
                        if (var12 > var8.a) {
                            var12 = var8.a;
                        }

                        var8.a -= var12;
                        OEntityItem var13 = new OEntityItem(var1, (var2 + var9), (var3 + var10), (var4 + var11), new OItemStack(var8.c, var12, var8.h()));
                        float var14 = 0.05F;
                        var13.bp = ((float) this.a.nextGaussian() * var14);
                        var13.bq = ((float) this.a.nextGaussian() * var14 + 0.2F);
                        var13.br = ((float) this.a.nextGaussian() * var14);
                        var1.b(var13);
                    }
                }
            }
        }

        super.d(var1, var2, var3, var4);
    }

    public int a(int var1, Random var2, int var3) {
        return OItem.bx.bP;
    }
}
