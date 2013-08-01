package net.canarymod.api.entity;

import net.canarymod.api.world.blocks.BlockType;
import net.minecraft.server.EntityFallingSand;

/**
 * FallingBlock wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryFallingBlock extends CanaryEntity implements FallingBlock {

    /**
     * Constructs a new wrapper for EntityFallingSand
     * 
     * @param entity
     *            the EntityFallingSand to be wrapped
     */
    public CanaryFallingBlock(EntityFallingSand entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.FALLINGBLOCK;
    }

    @Override
    public String getFqName() {
        return "FallingBlock";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public short getBlockID() {
        return (short) getHandle().a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBlockID(short id) {
        if (BlockType.fromId(id) != null) { // Safety
            getHandle().a = id;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public short getBlockMetaData() {
        return (short) getHandle().b;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBlockMetaData(short data) {
        getHandle().b = data;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFallTime() {
        return getHandle().c;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFallTime(int fallTime) {
        getHandle().c = fallTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean dropsItem() {
        return getHandle().d;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDropsItem(boolean drops) {
        getHandle().d = drops;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBreakingAnvil() {
        return getHandle().f;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIsBreakingAnvil(boolean breaking) {
        if (getBlockID() == BlockType.Anvil.getId()) {
            getHandle().f = breaking;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hurtEntities() {
        return getHandle().g;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHurtEntities(boolean hurt) {
        getHandle().g = hurt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxDamage() {
        return getHandle().h;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMaxDamage(int max) {
        getHandle().h = max;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getDamageAmount() {
        return getHandle().i;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDamageAmount(float damage) {
        getHandle().i = damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityFallingSand getHandle() {
        return (EntityFallingSand) entity;
    }
}
