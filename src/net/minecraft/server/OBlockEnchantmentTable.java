package net.minecraft.server;

import net.minecraft.server.OBlockContainer;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OTileEntity;
import net.minecraft.server.OTileEntityEnchantmentTable;
import net.minecraft.server.OWorld;

public class OBlockEnchantmentTable extends OBlockContainer {

    protected OBlockEnchantmentTable(int var1) {
        super(var1, 166, OMaterial.e);
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
        this.f(0);
    }

    @Override
    public boolean b() {
        return false;
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public int a(int var1, int var2) {
        return this.a(var1);
    }

    @Override
    public int a(int var1) {
        return var1 == 0 ? this.bN + 17 : (var1 == 1 ? this.bN : this.bN + 16);
    }

    @Override
    public OTileEntity a_() {
        return new OTileEntityEnchantmentTable();
    }

    @Override
    public boolean a(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
        if (var1.F) {
            return true;
        } else {
            var5.c(var2, var3, var4);
            return true;
        }
    }
}
