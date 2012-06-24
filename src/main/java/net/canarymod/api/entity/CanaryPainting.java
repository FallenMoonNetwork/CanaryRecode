package net.canarymod.api.entity;

import net.minecraft.server.OEntityPainting;
import net.minecraft.server.OEnumArt;

public class CanaryPainting extends CanaryEntity implements Painting{

    public CanaryPainting(OEntityPainting entity) {
        super(entity);
    }

    @Override
    public ArtType getArtType() {
        return ArtType.values()[((OEntityPainting)entity).e.ordinal()];
    }

    @Override
    public void setArtType(ArtType type) {
        ((OEntityPainting)entity).e = OEnumArt.values()[type.ordinal()];
    }

    @Override
    public int getSizeX() {
        return ((OEntityPainting)entity).e.B;
    }

    @Override
    public int getSizeY() {
        return ((OEntityPainting)entity).e.C;
    }

    @Override
    public int getOffsetX() {
        return ((OEntityPainting)entity).e.D;
    }

    @Override
    public int getOffsetY() {
        return ((OEntityPainting)entity).e.E;
    }

}
