package net.canarymod.api.entity.hanging;

import net.canarymod.api.entity.hanging.Painting;
import net.minecraft.server.EntityPainting;

/**
 * Painting wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryPainting extends CanaryHangingEntity implements Painting {

    /**
     * Constructs a new wrapper for EntityPaintings
     * 
     * @param entity
     *            the EntityItemFrame to be wrapped
     */
    public CanaryPainting(EntityPainting entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArtType getArtType() {
        return ArtType.values()[((EntityPainting) entity).e.ordinal()];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setArtType(ArtType type) {
        ((EntityPainting) entity).e = net.minecraft.server.EnumArt.values()[type.ordinal()];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSizeX() {
        return ((EntityPainting) entity).d();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSizeY() {
        return ((EntityPainting) entity).e();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getOffsetX() {
        return ((EntityPainting) entity).e.E;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getOffsetY() {
        return ((EntityPainting) entity).e.F;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityPainting getHandle() {
        return (EntityPainting) entity;
    }

}
