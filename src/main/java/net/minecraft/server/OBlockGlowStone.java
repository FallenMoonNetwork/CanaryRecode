package net.minecraft.server;


import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OItem;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OMathHelper;


public class OBlockGlowStone extends OBlock {

    public OBlockGlowStone(int var1, int var2, OMaterial var3) {
        super(var1, var2, var3);
    }

    @Override
    public int a(int var1, Random var2) {
        return OMathHelper.a(this.a(var2) + var2.nextInt(var1 + 1), 1, 4);
    }

    @Override
    public int a(Random var1) {
        return 2 + var1.nextInt(3);
    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return OItem.aS.bP;
    }
}
