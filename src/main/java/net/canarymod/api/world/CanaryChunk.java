package net.canarymod.api.world;



public class CanaryChunk implements Chunk {
    private net.minecraft.server.Chunk handle;

    public CanaryChunk(net.minecraft.server.Chunk chunk) {
        this.handle = chunk;
    }

    public net.minecraft.server.Chunk getHandle() {
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
        return handle.a(x, y, z);
    }

    @Override
    public void setBlockTypeAt(int x, int y, int z, int type) {
        handle.b(x, y, z, type);
    }

    @Override
    public int getBlockDataAt(int x, int y, int z) {
        return handle.c(x, y, z);
    }

    @Override
    public void setBlockDataAt(int x, int y, int z, int data) {
        handle.b(x, y, z, data);
    }

    @Override
    public int getMaxHeigth() {
        return 256;
    }

    @Override
    public boolean isLoaded() {
        return handle.d;
    }

    @Override
    public World getDimension() {
        return handle.e.getCanaryWorld();
    }

    @Override
    public byte[] getBiomeByteData() {
        return handle.m();
    }

    @Override
    public BiomeType[] getBiomeData() {
        byte[] data = handle.m();
        BiomeType[] typedata = new BiomeType[256];

        for (int index = 0; index < 256; index++) {
            if (data[index] < 0 || data[index] > 22) {
                typedata[index] = BiomeType.OCEAN;
                continue;
            }
            typedata[index] = BiomeType.fromId(data[index]);
        }
        return typedata;
    }

    @Override
    public void setBiomeData(BiomeType[] type) {
        if (type.length != 256) {
            return;
        }
        byte[] data = new byte[256];

        for (int index = 0; index < 256; index++) {
            if (type[index] == null) {
                data[index] = (byte) 0;
                continue;
            }
            data[index] = type[index].getId();
        }
        handle.a(data);
    }

    @Override
    public void setBiomeData(byte[] data) {
        if (data.length != 256) {
            return;
        }
        for (byte check : data) {
            if (check < 0 || check > 22) {
                check = (byte) 0;
            }
        }
        handle.a(data);
    }
}
