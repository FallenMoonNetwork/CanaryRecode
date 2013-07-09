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
    private ChunkProvider provider;

    public CanaryChunkProviderCustom(ChunkProvider provider) {
        this.provider = provider;
    }

    @Override
    public boolean a(int i0, int i1) {
        return provider.chunkExists(i0, i1);
    }

    @Override
    public Chunk d(int i0, int i1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Chunk c(int i0, int i1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void a(IChunkProvider ichunkprovider, int i0, int i1) {
        provider.populate(null, i0, i1);
    }

    @Override
    public boolean a(boolean flag0, IProgressUpdate iprogressupdate) {
        // TODO: Saving chunks, Needs to be implemented on our side,
        return false;
    }

    @Override
    public boolean c() {
        // TODO: unloadQueuedChunks, Needs to be implemented on our side,
        return false;
    }

    @Override
    public boolean d() {
        // TODO: canSave, Needs to be implemented on our side,
        return false;
    }

    @Override
    public String e() {
        return provider.getStatistics();
    }

    @Override
    public List a(EnumCreatureType enumcreaturetype, int i0, int i1, int i2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ChunkPosition a(World world, String s0, int i0, int i1, int i2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int f() {
        // TODO: GetNum loaded chunks
        return 0;
    }

    @Override
    public void e(int i0, int i1) {
        // TODO: Re-Create structures (is this maybe a re-populate?)

    }

    @Override
    public void b() {
        // TODO save extra data, needs to be handled canary side
    }

}
