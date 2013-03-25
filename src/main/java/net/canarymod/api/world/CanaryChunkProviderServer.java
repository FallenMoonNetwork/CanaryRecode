package net.canarymod.api.world;


import java.io.IOException;

import net.canarymod.Canary;


public class CanaryChunkProviderServer implements ChunkProviderServer {

    private OChunkProviderServer handle;

    public CanaryChunkProviderServer(OChunkProviderServer handle) {
        this.handle = handle;
    }

    @Override
    public boolean canSave() {
        return this.handle.b();
    }

    @Override
    public boolean chunkExists(int x, int z) {
        return this.handle.a(x, z);
    }

    @Override
    public Chunk loadChunk(int x, int z) {
        return this.handle.c(x >> 4, z >> 4).getCanaryChunk();
    }

    @Override
    public Chunk provideChunk(int x, int z) {
        return this.handle.b(x, z).getCanaryChunk();
    }

    @Override
    public boolean saveChunk(boolean saveAll) {
        // The chunk saver doesn't touch the progress object thingy
        try {
            return this.handle.a(saveAll, null);
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public Chunk regenerateChunk(int x, int z) {
        Long chunkCoordIntPair = OChunkCoordIntPair.a(x, z);
        // Unloading the chunk
        OChunk unloadedChunk = (OChunk) handle.f.a(chunkCoordIntPair.longValue());

        if (unloadedChunk != null) {
            unloadedChunk.e();
            try {
                handle.b(unloadedChunk);
            } catch (IOException e) {
                Canary.logStackTrace("Exception while unloading a chunk!", e);
            }

            handle.a(unloadedChunk);
            handle.b.remove(chunkCoordIntPair);
            handle.f.d(chunkCoordIntPair.longValue());
            handle.g.remove(unloadedChunk);
        }

        // Generating the new chunk
        OChunk newChunk = handle.d.b(x, z);

        handle.f.a(chunkCoordIntPair, newChunk);
        handle.g.add(newChunk);
        if (newChunk != null) {
            newChunk.c();
            newChunk.d();
        }
        newChunk.a(handle, handle, x, z);

        // Save the new chunk, overriding the old one
        handle.a(newChunk);
        try {
            handle.b(newChunk);
        } catch (IOException e) {
            Canary.logStackTrace("Exception while regenerating a chunk!", e);
        }
        newChunk.k = false;
        if (handle.e != null) {
            handle.e.b();
        }

        return newChunk.getCanaryChunk();
    }

    @Override
    public void reloadChunk(int x, int z) {
        dropChunk(x, z);
        loadChunk(x, z);
    }

    @Override
    public void dropChunk(int x, int z) {
        handle.d(x >> 4, z >> 4);
    }

    @Override
    public boolean isChunkLoaded(int x, int z) {
        return handle.a(x, z);
    }

}
