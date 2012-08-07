package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;

public class OBlockLockedChest extends OBlock {

    protected OBlockLockedChest(int var1) {
        super(var1, OMaterial.d);
        this.bN = 26;
    }

    @Override
    public int a(int var1) {
        return var1 == 1 ? this.bN - 1 : (var1 == 0 ? this.bN - 1 : (var1 == 3 ? this.bN + 1 : this.bN));
    }

    @Override
    public boolean c(OWorld var1, int var2, int var3, int var4) {
        return true;
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
        var1.e(var2, var3, var4, 0);
    }
}
