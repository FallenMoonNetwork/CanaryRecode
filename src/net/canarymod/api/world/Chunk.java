package net.canarymod.api.world;

import net.minecraft.server.OChunk;

public class Chunk implements IChunk {
    OChunk _handle;
    
    public Chunk(OChunk chunk) {
        _handle = chunk;
    }
    
    public OChunk getHandle() {
        return _handle;
    }
    @Override
    public int getX() {
        return _handle.g;
    }

    @Override
    public int getZ() {
        return _handle.h;
    }

    @Override
    public int getBlockTypeAt(int x, int y, int z) {
        return _handle.b(x, y, z);
    }

    @Override
    public int getBlockDataAt(int x, int y, int z) {
        return _handle.c(x, y, z);
    }

    @Override
    public boolean isLoaded() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void regenerateChunk() {
        // TODO Auto-generated method stub

    }

    @Override
    public IDimension getWorld() {
        // TODO Auto-generated method stub
        return null;
    }

}
