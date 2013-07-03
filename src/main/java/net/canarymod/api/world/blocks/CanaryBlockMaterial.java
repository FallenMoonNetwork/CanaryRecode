package net.canarymod.api.world.blocks;

import net.minecraft.server.Material;

/**
 * Material wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryBlockMaterial implements BlockMaterial {
    private final Material material;

    public CanaryBlockMaterial(Material material) {
        this.material = material;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLiquid() {
        return material.d();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSolid() {
        return material.a();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canPreventGrassGrowth() {
        return material.b();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canBurn() {
        return material.h();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isReplaceable() {
        return material.j();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOpaque() {
        return material.k();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean noToolRequired() {
        return material.l();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMobility() {
        return material.m();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlwaysHarvested() {
        return material.q();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTranslucent() {
        return material.J;
    }
}
