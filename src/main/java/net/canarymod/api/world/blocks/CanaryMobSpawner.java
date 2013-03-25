package net.canarymod.api.world.blocks;


import net.minecraft.server.OTileEntityMobSpawner;


public class CanaryMobSpawner extends CanaryComplexBlock implements MobSpawner {

    public CanaryMobSpawner(OTileEntityMobSpawner tileentity) {
        super(tileentity);
    }

    @Override
    public String getSpawnType() {
        return ((OTileEntityMobSpawner) tileentity).d;
    }

    @Override
    public void setSpawnType(String spawn) {
        ((OTileEntityMobSpawner) tileentity).d = spawn;
    }

    @Override
    public int getDelay() {
        return ((OTileEntityMobSpawner) tileentity).reset;
    }

    @Override
    public void setDelay(int delay) {
        ((OTileEntityMobSpawner) tileentity).reset = delay;
    }

}
