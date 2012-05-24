package net.canarymod.api.world;

import net.minecraft.server.OChunk;

public class CanaryChunk implements Chunk {
    OChunk handle;
    
    public CanaryChunk(OChunk chunk) {
        this.handle = chunk;
    }
    
    public OChunk getHandle() {
        return handle;
    }
    @Override
    public int getX() {
        return handle.g;
    }

    @Override
    public int getZ() {
        return handle.h;
    }

    @Override
    public int getBlockTypeAt(int x, int y, int z) {
        return handle.b(x, y, z);
    }

    @Override
    public void setBlockTypeAt(int x, int y, int z, int type) {
    	// TODO Auto-generated method stub
    }
    
    @Override
    public int getBlockDataAt(int x, int y, int z) {
        return handle.c(x, y, z);
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
    public Dimension getDimension() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public byte[] getBiomeByteData() {
        return handle.l();
    }

    @Override
    public BiomeType[] getBiomeData() {
        byte[] data = handle.l();
        BiomeType[] typedata = new BiomeType[256];
        for(int index = 0; index < 256; index++){
            if(data[index] < 0 || data[index] > 22){
                typedata[index] = BiomeType.OCEAN;
                continue;
            }
            typedata[index] = BiomeType.fromId(data[index]);
        }
        return typedata;
    }

    @Override
    public void setBiomeData(BiomeType[] type) {
        if(type.length != 256){
            return;
        }
        byte[] data = new byte[256];
        for(int index = 0; index < 256; index++){
            if(type[index] == null){
                data[index] = (byte)0;
                continue;
            }
            data[index] = type[index].getId();
        }
        handle.a(data);
    }

    @Override
    public void setBiomeData(byte[] data) {
        if(data.length != 256){
            return;
        }
        for(byte check : data){
            if(check < 0 || check > 22){
                check = (byte)0;
            }
        }
        handle.a(data);
    }
}
