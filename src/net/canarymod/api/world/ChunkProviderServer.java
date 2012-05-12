package net.canarymod.api.world;

import net.minecraft.server.OChunkProviderServer;

public class ChunkProviderServer implements IChunkProviderServer {

    private OChunkProviderServer _handle;
    
    public ChunkProviderServer(OChunkProviderServer handle) {
        _handle = handle;
    }

    @Override
    public boolean canSave() {
        return _handle.b();
    }

    @Override
    public boolean chunkExists(int x, int z) {
        return _handle.a(x, z);
    }

    @Override
    public IChunk loadChunk(int x, int z) {
        return _handle.c(x >> 4, z >> 4).getHandler();
    }

    @Override
    public IChunk provideChunk(int x, int z) {
        return _handle.b(x, z).getHandler();
    }

    @Override
    public boolean saveChunk(boolean saveAll) {
        //The chunk saver doesn't touch the progress object thingy
        return _handle.a(saveAll, null);
    }

    /**
     * Unload 100 oldest chunks
     */
    @Override
    public void unloadAllChunks() {
        _handle.a();
    }

}
