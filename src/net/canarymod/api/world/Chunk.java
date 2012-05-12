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
    public void setBlockTypeAt(int x, int y, int z, int type) {
    	// TODO Auto-generated method stub
    }
    
    @Override
    public int getBlockDataAt(int x, int y, int z) {
        return _handle.c(x, y, z);
    }

    @Override
    public void setBlockDataAt(int x, int y, int z, int data) {
    	// TODO Auto-generated method stub
    }
    
    @Override
    public int getMaxHeigth() {
    	return 256; // TODO: configurable
    }
    
    @Override
    public boolean isLoaded() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean load() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean reload() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean unload() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean regenerateChunk() {
        // TODO Auto-generated method stub
    	return false;
    }

    @Override
    public IDimension getDimension() {
        // TODO Auto-generated method stub
        return null;
    }

}
