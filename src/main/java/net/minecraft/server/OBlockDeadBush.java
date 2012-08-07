package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockFlower;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OStatList;
import net.minecraft.server.OWorld;

public class OBlockDeadBush extends OBlockFlower {

    protected OBlockDeadBush(int var1, int var2) {
        super(var1, var2, OMaterial.k);
        float var3 = 0.4F;
        this.a(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.8F, 0.5F + var3);
    }

    @Override
    protected boolean d(int var1) {
        return var1 == OBlock.E.bO;
    }

    @Override
    public int a(int var1, int var2) {
        return this.bN;
    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return -1;
    }

    @Override
    public void a(OWorld var1, OEntityPlayer var2, int var3, int var4, int var5, int var6) {
        if (!var1.F && var2.U() != null && var2.U().c == OItem.bd.bP) {
            var2.a(OStatList.C[this.bO], 1);
            this.a(var1, var3, var4, var5, new OItemStack(OBlock.Y, 1, var6));
        } else {
            super.a(var1, var2, var3, var4, var5, var6);
        }

    }
}
