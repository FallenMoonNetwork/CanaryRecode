package net.canarymod.api.world.blocks;

import net.minecraft.server.OTileEntityRecordPlayer;

public class CanaryJukebox extends CanaryComplexBlock implements Jukebox {

    public CanaryJukebox(OTileEntityRecordPlayer tileentity) {
        super(tileentity);
    }

    @Override
    public int getDiscId() {
        return ((OTileEntityRecordPlayer)tileentity).a;
    }

    @Override
    public void setDiscId(int discid) {
        ((OTileEntityRecordPlayer)tileentity).a = discid;
    }

}
