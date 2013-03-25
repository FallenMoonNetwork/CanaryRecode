package net.canarymod.api.entity;


import net.minecraft.server.EntityPainting;

/**
 * Painting wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryPainting extends CanaryHangingEntity implements Painting {

    public CanaryPainting(EntityPainting entity) {
        super(entity);
    }

    @Override
    public ArtType getArtType() {
        return ArtType.values()[((EntityPainting) entity).e.ordinal()];
    }

    @Override
    public void setArtType(ArtType type) {
        ((EntityPainting) entity).e = net.minecraft.server.EnumArt.values()[type.ordinal()];
    }

    @Override
    public int getSizeX() {
        return ((EntityPainting) entity).d();
    }

    @Override
    public int getSizeY() {
        return ((EntityPainting) entity).g();
    }

    @Override
    public int getOffsetX() {
        return ((EntityPainting) entity).e.E;
    }

    @Override
    public int getOffsetY() {
        return ((EntityPainting) entity).e.F;
    }

    @Override
    public EntityPainting getHandle() {
        return (EntityPainting) entity;
    }

}
