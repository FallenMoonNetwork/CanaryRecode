package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;

public class OBlockWorkbench extends OBlock {

    protected OBlockWorkbench(int var1) {
        super(var1, OMaterial.d);
        this.bN = 59;
    }

    @Override
    public int a(int var1) {
        return var1 == 1 ? this.bN - 16 : (var1 == 0 ? OBlock.x.a(0) : (var1 != 2 && var1 != 4 ? this.bN : this.bN + 1));
    }

    @Override
    public boolean a(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
        if (var1.F) {
            return true;
        } else {
            var5.b(var2, var3, var4);
            return true;
        }
    }
}
