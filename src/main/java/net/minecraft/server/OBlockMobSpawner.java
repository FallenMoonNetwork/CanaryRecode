package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlockContainer;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OTileEntity;
import net.minecraft.server.OTileEntityMobSpawner;

public class OBlockMobSpawner extends OBlockContainer {

    protected OBlockMobSpawner(int var1, int var2) {
        super(var1, var2, OMaterial.e);
    }

    @Override
    public OTileEntity a_() {
        return new OTileEntityMobSpawner();
    }

    @Override
    public int a(int var1, Random var2, int var3) {
        return 0;
    }

    @Override
    public int a(Random var1) {
        return 0;
    }

    @Override
    public boolean a() {
        return false;
    }
}
