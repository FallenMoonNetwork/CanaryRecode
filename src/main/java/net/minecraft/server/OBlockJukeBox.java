package net.minecraft.server;

import net.minecraft.server.OBlockContainer;
import net.minecraft.server.OEntityItem;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OTileEntity;
import net.minecraft.server.OTileEntityRecordPlayer;
import net.minecraft.server.OWorld;

public class OBlockJukeBox extends OBlockContainer {

    protected OBlockJukeBox(int var1, int var2) {
        super(var1, var2, OMaterial.d);
    }

    @Override
    public int a(int var1) {
        return this.bN + (var1 == 1 ? 1 : 0);
    }

    @Override
    public boolean a(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
        if (var1.c(var2, var3, var4) == 0) {
            return false;
        } else {
            this.c_(var1, var2, var3, var4);
            return true;
        }
    }

    public void f(OWorld var1, int var2, int var3, int var4, int var5) {
        if (!var1.F) {
            OTileEntityRecordPlayer var6 = (OTileEntityRecordPlayer) var1.b(var2, var3, var4);
            if (var6 != null) {
                var6.a = var5;
                var6.G_();
                var1.c(var2, var3, var4, 1);
            }
        }
    }

    public void c_(OWorld var1, int var2, int var3, int var4) {
        if (!var1.F) {
            OTileEntityRecordPlayer var5 = (OTileEntityRecordPlayer) var1.b(var2, var3, var4);
            if (var5 != null) {
                int var6 = var5.a;
                if (var6 != 0) {
                    var1.f(1005, var2, var3, var4, 0);
                    var1.a((String) null, var2, var3, var4);
                    var5.a = 0;
                    var5.G_();
                    var1.c(var2, var3, var4, 0);
                    float var8 = 0.7F;
                    double var9 = (var1.r.nextFloat() * var8) + (1.0F - var8) * 0.5D;
                    double var11 = (var1.r.nextFloat() * var8) + (1.0F - var8) * 0.2D + 0.6D;
                    double var13 = (var1.r.nextFloat() * var8) + (1.0F - var8) * 0.5D;
                    OEntityItem var15 = new OEntityItem(var1, var2 + var9, var3 + var11, var4 + var13, new OItemStack(var6, 1, 0));
                    var15.c = 10;
                    var1.b(var15);
                }
            }
        }
    }

    @Override
    public void d(OWorld var1, int var2, int var3, int var4) {
        this.c_(var1, var2, var3, var4);
        super.d(var1, var2, var3, var4);
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, int var5, float var6, int var7) {
        if (!var1.F) {
            super.a(var1, var2, var3, var4, var5, var6, 0);
        }
    }

    @Override
    public OTileEntity a_() {
        return new OTileEntityRecordPlayer();
    }
}
