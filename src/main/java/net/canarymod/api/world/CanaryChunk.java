package net.canarymod.api.world;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.world.blocks.TileEntity;
import net.canarymod.api.world.position.Position;
import net.minecraft.server.ChunkPosition;

/**
 * Chunk implementation
 * 
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 * @author Jos Kuijpers
 */
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
        BiomeType[] data = BiomeType.fromIdArray(handle.m());

        return data;
    }

    @Override
    public void setBiomeData(BiomeType[] type) {
        if (type.length != 256) {
            return;
        }
        handle.a(BiomeType.fromTypeArray(type));
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

    @Override
    @SuppressWarnings("unchecked")
    public Map<Position, TileEntity> getTileEntityMap() {
        HashMap<Position, TileEntity> toRet = new HashMap<Position, TileEntity>();
        synchronized (handle.i) {
            for (ChunkPosition pos : (Set<ChunkPosition>) handle.i.keySet()) {
                Position cPos = new Position(pos.a, pos.b, pos.c);
                net.minecraft.server.TileEntity te = (net.minecraft.server.TileEntity) handle.i.get(pos);
                if (te.complexBlock != null) {
                    toRet.put(cPos, te.complexBlock);
                }
            }
        }
        return toRet;
    }

    @Override
    public boolean hasEntities() {
        return handle.m;
    }

    @SuppressWarnings("unchecked")
    public List<Entity>[] getEntityLists() {
        List<Entity>[] toRet = new List[handle.j.length];
        for (int index = 0; index < handle.j.length; index++) {
            for (Object e : handle.j[index]) {
                toRet[index].add(((net.minecraft.server.Entity) e).getCanaryEntity());
            }
        }
        return toRet;
    }

    @Override
    public int[] getHeightMap() {
        return handle.f;
    }

    @Override
    public int[] getPrecipitationHeightMap() {
        return handle.b;
    }

    @Override
    public long getLastSaveTime() {
        return handle.n;
    }

    @Override
    public boolean isTerrainPopulated() {
        return handle.k;
    }

    @Override
    public boolean isModified() {
        return handle.l;
    }

    @Override
    public void generateSkyLightMap() {
        handle.b();
    }

    @Override
    public void updateSkyLightMap(boolean force) {
        if(force) {
            handle.q();
        }
        else {
            handle.k();
        }
    }

    @Override
    public void relightBlock(int x, int y, int z) {
        handle.h(x, y, z);
        
    }
}
