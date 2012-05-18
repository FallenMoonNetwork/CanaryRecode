package net.canarymod.api.world;

import java.io.IOException;

import net.minecraft.server.OChunkProviderServer;

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
        return this.handle.c(x >> 4, z >> 4).getHandler();
    }

    @Override
    public Chunk provideChunk(int x, int z) {
        return this.handle.b(x, z).getHandler();
    }

    @Override
    public boolean saveChunk(boolean saveAll) {
        //The chunk saver doesn't touch the progress object thingy
        try {
            return this.handle.a(saveAll, null);
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Unload 100 oldest chunks
     */
    @Override
    public void unloadAllChunks() {
        try {
            this.handle.a();
        } catch (IOException e) {
            
        }
    }

}
