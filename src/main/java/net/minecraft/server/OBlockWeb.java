package net.minecraft.server;


import java.util.Random;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntity;
import net.minecraft.server.OItem;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;


public class OBlockWeb extends OBlock {

    public OBlockWeb(int var1, int var2) {
        super(var1, var2, OMaterial.D);
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, OEntity var5) {
        var5.u();
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public OAxisAlignedBB e(OWorld var1, int var2, int var3, int var4) {
        return null;
    }

    @Override
    public int c() {
        return 1;
    }

    @Override
    public boolean b() {
        return false;
    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return OItem.J.bP;
    }
}
