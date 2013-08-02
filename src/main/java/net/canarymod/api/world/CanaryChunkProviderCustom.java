package net.canarymod.api.world;

import java.util.List;
import net.minecraft.server.Chunk;
import net.minecraft.server.ChunkPosition;
import net.minecraft.server.EnumCreatureType;
import net.minecraft.server.IChunkProvider;
import net.minecraft.server.IProgressUpdate;
import net.minecraft.server.World;

/**
 * Implements an IChunkProvider and maps to a CanaryMod ChunkProvider implementation.
 *
 * @author Chris (damagefilter)
 */
public class CanaryChunkProviderCustom implements IChunkProvider {
    private ChunkProviderCustom provider;
    private IChunkProvider handle;

    //NOTE: to get the ichunkprovider see where ChunkProviderServre is instantiated!
    //Then pass it a copy of this custom provider, with the handle that would usually go into it instead.
    //the ichunkprovider that would go into the server provider needs to be in here instead!
    public CanaryChunkProviderCustom(ChunkProviderCustom provider, IChunkProvider handle) {
        this.provider = provider;
        this.handle = handle;
    }

    //chunkExists
    @Override
    public boolean a(int i0, int i1) {
        return handle.a(i0, i1);
    }

    //provideChunk
    @Override
    public Chunk d(int i0, int i1) {
        CanaryChunk c = (CanaryChunk)provider.provideChunk(i0, i1);
        return c != null ? c.getHandle() : null;
    }

    //loadChunk
    @Override
    public Chunk c(int i0, int i1) {
        return handle.c(i0, i1);
    }

    //populate (distribute ores, flowers and detail structures etc)
    @Override
    public void a(IChunkProvider ichunkprovider, int i0, int i1) {
        provider.populate(i0, i1);
    }

    //saveChunks
    @Override
    public boolean a(boolean flag0, IProgressUpdate iprogressupdate) {
        return handle.a(flag0, iprogressupdate);
    }

    //unloadQueuedChunks
    @Override
    public boolean c() {
        throw new UnsupportedOperationException("Custom Chunk Provider cannot unload chunks! This must be called from ChunkProviderServer instead!");
    }

    //canSave
    @Override
    public boolean d() {
        return true; //ofc we can save!
    }

    //getStatisticsAsString
    @Override
    public String e() {
        return handle.e();
    }

    //getPossibleCreaturs (list of mobs that can spawn here) TODO: Should be useful to have access to!
    @Override
    public List a(EnumCreatureType enumcreaturetype, int i0, int i1, int i2) {
        return handle.a(enumcreaturetype, i0, i1, i2);
    }

    //Find closes structure
    @Override
    public ChunkPosition a(World world, String s0, int i0, int i1, int i2) {
        return handle.a(world, s0, i0, i1, i2);
    }

    //getLoadedChunkCount
    @Override
    public int f() {
       return handle.f();
    }

    //recreateStructures
    @Override
    public void e(int i0, int i1) {
        provider.createStructures(i0, i1);
    }

    @Override
    public void b() {
        handle.b();
    }

}
