package net.canarymod.api.world.blocks;

import net.canarymod.api.MobSpawnerLogic;
import net.minecraft.server.Container;
import net.minecraft.server.TileEntity;
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

    @Override
    public TileEntity getTileEntity() {
        return this.tileentity;
    }

    /**
     * @throws UnsupportedOperationException
     *             this isn't a Minecraft Container instance
     */
    @Override
    public Container getContainer() {
        throw new UnsupportedOperationException("Not a Container");
    }
}