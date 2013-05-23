package net.canarymod.api.world;


import net.minecraft.server.ChunkCoordIntPair;


public class CanaryChunkProviderServer implements ChunkProvider {

    private net.minecraft.server.ChunkProviderServer handle;

    public CanaryChunkProviderServer(net.minecraft.server.ChunkProviderServer handle) {
        this.handle = handle;
    }

    @Override
    public boolean canSave() {
        return this.handle.c();
    }

    @Override
    public boolean chunkExists(int x, int z) {
        return this.handle.a(x, z);
    }

    @Override
    public Chunk loadChunk(int x, int z) {
        return this.handle.c(x, z).getCanaryChunk();
    }

    @Override
    public Chunk provideChunk(int x, int z) {
        return this.handle.d(x, z).getCanaryChunk();
    }

    @Override
    public boolean saveChunk(boolean saveAll) {
        // The chunk saver doesn't touch the progress object thingy
        return this.handle.a(saveAll, null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Chunk regenerateChunk(int x, int z) {
        Long chunkCoordIntPair = ChunkCoordIntPair.a(x, z);
        // Unloading the chunk
        net.minecraft.server.Chunk unloadedChunk = (net.minecraft.server.Chunk) handle.f.a(chunkCoordIntPair.longValue());

        if (unloadedChunk != null) {
            unloadedChunk.e();
            handle.b(unloadedChunk); // save chunk data
            handle.a(unloadedChunk); // save extra chunk data

            handle.b.remove(chunkCoordIntPair);
            handle.f.d(chunkCoordIntPair.longValue());
            handle.g.remove(unloadedChunk);
        }

        // Generating the new chunk
        net.minecraft.server.Chunk newChunk = handle.d.d(x, z);

        handle.f.a(chunkCoordIntPair, newChunk);
        handle.g.add(newChunk);
        if (newChunk != null) {
            newChunk.c();
            newChunk.d();
        }
        newChunk.a(handle, handle, x, z);

        // Save the new chunk, overriding the old one
        handle.a(newChunk);
        handle.b(newChunk);
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
        handle.d(x, z);
    }

    @Override
    public boolean isChunkLoaded(int x, int z) {
        return handle.a(x, z);
    }

    @Override
    public void populate(ChunkProvider provider, int x, int z) {
        handle.a(null, x, z);
    }

    @Override
    public String getStatistics() {
        return handle.e();
    }

}
