package net.canarymod.api.world.blocks;


import net.minecraft.server.TileEntityRecordPlayer;


public class CanaryJukebox extends CanaryComplexBlock implements Jukebox {

    public CanaryJukebox(TileEntityRecordPlayer tileentity) {
        super(tileentity);
    }

    @Override
    public int getDiscId() {
        return ((TileEntityRecordPlayer) tileentity).a;
    }

    @Override
    public void setDiscId(int discid) {
        ((TileEntityRecordPlayer) tileentity).a = discid;
    }

}
