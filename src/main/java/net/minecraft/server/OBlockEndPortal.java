package net.minecraft.server;


import java.util.ArrayList;
import java.util.Random;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlockContainer;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIBlockAccess;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OTileEntity;
import net.minecraft.server.OTileEntityEndPortal;
import net.minecraft.server.OWorld;


public class OBlockEndPortal extends OBlockContainer {

    public static boolean a = false;

    protected OBlockEndPortal(int var1, OMaterial var2) {
        super(var1, 0, var2);
        this.a(1.0F);
    }

    @Override
    public OTileEntity a_() {
        return new OTileEntityEndPortal();
    }

    @Override
    public void a(OIBlockAccess var1, int var2, int var3, int var4) {
        float var5 = 0.0625F;

        this.a(0.0F, 0.0F, 0.0F, 1.0F, var5, 1.0F);
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, OAxisAlignedBB var5, ArrayList var6) {}

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public boolean b() {
        return false;
    }

    @Override
    public int a(Random var1) {
        return 0;
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4, OEntity var5) {
        if (var5.bh == null && var5.bg == null && var5 instanceof OEntityPlayer && !var1.F) {
            ((OEntityPlayer) var5).e(1);
        }

    }

    @Override
    public int c() {
        return -1;
    }

    @Override
    public void a(OWorld var1, int var2, int var3, int var4) {
        if (!a) {
            if (var1.t.g != 0) {
                var1.e(var2, var3, var4, 0);
            }
        }
    }

}
