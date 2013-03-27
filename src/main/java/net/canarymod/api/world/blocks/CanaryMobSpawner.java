package net.canarymod.api.world.blocks;


import net.minecraft.server.TileEntityMobSpawner;


public class CanaryMobSpawner extends CanaryComplexBlock implements MobSpawner {

    private MobSpawnerLogic logic = ((TileEntityMobSpawner)this.tileentity).a().logic;

    public CanaryMobSpawner(TileEntityMobSpawner tileentity) {
        super(tileentity);
    }

    @Override
    public MobSpawnerLogic getLogic() {
        return logic;
    }
}
