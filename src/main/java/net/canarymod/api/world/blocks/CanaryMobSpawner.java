package net.canarymod.api.world.blocks;


import net.minecraft.server.TileEntityMobSpawner;


public class CanaryMobSpawner extends CanaryComplexBlock implements MobSpawner {

    public CanaryMobSpawner(TileEntityMobSpawner tileentity) {
        super(tileentity);
    }

    @Override
    public String getSpawnType() {
        return ((TileEntityMobSpawner) tileentity).d;
    }

    @Override
    public void setSpawnType(String spawn) {
        ((TileEntityMobSpawner) tileentity).d = spawn;
    }

    @Override
    public int getDelay() {
        return ((TileEntityMobSpawner) tileentity).reset;
    }

    @Override
    public void setDelay(int delay) {
        ((TileEntityMobSpawner) tileentity).reset = delay;
    }

}
