package net.minecraft.server;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.canarymod.Canary;
import net.canarymod.api.world.CanaryChunkProviderServer;
import net.canarymod.hook.world.ChunkCreatedHook;
import net.canarymod.hook.world.ChunkCreationHook;
import net.canarymod.hook.world.ChunkLoadedHook;
import net.canarymod.hook.world.ChunkUnloadHook;


public class ChunkProviderServer implements IChunkProvider {

    public Set b = new HashSet(); // CanaryMod private->public
    private Chunk c;
    public IChunkProvider d; // CanaryMod private->public
    public IChunkLoader e; // //CanaryMod private->public
    public boolean a = true;
    public LongHashMap f = new LongHashMap(); // CanaryMod private->public
    public List g = new ArrayList(); // CanaryMod private->public
    private WorldServer h;

    // CanaryMod start
    private CanaryChunkProviderServer canaryChunkProvider;
    //
    public ChunkProviderServer(WorldServer worldserver, IChunkLoader ichunkloader, IChunkProvider ichunkprovider) {
        this.c = new EmptyChunk(worldserver, 0, 0);
        this.h = worldserver;
        this.e = ichunkloader;
        this.d = ichunkprovider;
        this.canaryChunkProvider = new CanaryChunkProviderServer(this);
    }

    // CanaryMod start
    public CanaryChunkProviderServer getCanaryChunkProvider() {
        return canaryChunkProvider;
    }

    // CanaryMod end

    public boolean a(int i0, int i1) {
        return this.f.b(ChunkCoordIntPair.a(i0, i1));
    }

    public void b(int i0, int i1) {
        if (this.h.t.e()) {
            ChunkCoordinates chunkcoordinates = this.h.K();
            int i2 = i0 * 16 + 8 - chunkcoordinates.a;
            int i3 = i1 * 16 + 8 - chunkcoordinates.c;
            short short1 = 128;

            if (i2 < -short1 || i2 > short1 || i3 < -short1 || i3 > short1) {
                this.b.add(Long.valueOf(ChunkCoordIntPair.a(i0, i1)));
            }
        } else {
            this.b.add(Long.valueOf(ChunkCoordIntPair.a(i0, i1)));
        }
    }

    public void a() {
        Iterator iterator = this.g.iterator();

        while (iterator.hasNext()) {
            Chunk chunk = (Chunk) iterator.next();

            this.b(chunk.g, chunk.h);
        }
    }

    public Chunk c(int i0, int i1) {
        long i2 = ChunkCoordIntPair.a(i0, i1);

        this.b.remove(Long.valueOf(i2));
        Chunk chunk = (Chunk) this.f.a(i2);

        if (chunk == null) {
            chunk = this.f(i0, i1);
            if (chunk == null) {
             // CanaryMod: ChunkCreation
                ChunkCreationHook hook = new ChunkCreationHook(i0, i1, h.getCanaryWorld());
                Canary.hooks().callHook(hook);
                byte[] blocks = hook.getBlockData();
                if (blocks != null) {
                    chunk = new Chunk(h, blocks, i0, i1);
                    chunk.k = true; //is populated
                    chunk.b(); //lighting update
                    if (hook.getBiomeData() != null) {
                        chunk.getCanaryChunk().setBiomeData(hook.getBiomeData());
                    }
                }
                //
                else if (this.d == null) {
                    chunk = this.c;
                } else {
                    try {
                        chunk = this.d.d(i0, i1);
                    } catch (Throwable throwable) {
                        CrashReport crashreport = CrashReport.a(throwable, "Exception generating new chunk");
                        CrashReportCategory crashreportcategory = crashreport.a("Chunk to be generated");

                        crashreportcategory.a("Location", String.format("%d,%d", new Object[] { Integer.valueOf(i0), Integer.valueOf(i1)}));
                        crashreportcategory.a("Position hash", Long.valueOf(i2));
                        crashreportcategory.a("Generator", this.d.e());
                        throw new ReportedException(crashreport);
                    }
                    // CanaryMod: ChunkCreated
                    Canary.hooks().callHook(new ChunkCreatedHook(chunk.getCanaryChunk(), h.getCanaryWorld()));
                    //
                }
            }

            this.f.a(i2, chunk);
            this.g.add(chunk);
            if (chunk != null) {
                chunk.c();
                // CanaryMod: ChunkLoaded
                Canary.hooks().callHook(new ChunkLoadedHook(chunk.getCanaryChunk(), h.getCanaryWorld()));
                //
                if (chunk.k && this.a(i0 + 1, i1 + 1) && this.a(i0, i1 + 1) && this.a(i0 + 1, i1)) {
                    this.a(this, i0, i1);
                }
            }

            chunk.a(this, this, i0, i1);
        }

        return chunk;
    }

    public Chunk d(int i0, int i1) {
        Chunk chunk = (Chunk) this.f.a(ChunkCoordIntPair.a(i0, i1));

        return chunk == null ? (!this.h.y && !this.a ? this.c : this.c(i0, i1)) : chunk;
    }

    private Chunk f(int i0, int i1) {
        if (this.e == null) {
            return null;
        } else {
            try {
                Chunk chunk = this.e.a(this.h, i0, i1);

                if (chunk != null) {
                    chunk.n = this.h.I();
                    if (this.d != null) {
                        this.d.e(i0, i1);
                    }
                }

                return chunk;
            } catch (Exception exception) {
                exception.printStackTrace();
                return null;
            }
        }
    }

    // CanaryMod private -> public
    public void a(Chunk chunk) {
        if (this.e != null) {
            try {
                this.e.b(this.h, chunk);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    // CanaryMod private -> public
    public void b(Chunk chunk) {
        if (this.e != null) {
            try {
                chunk.n = this.h.I();
                this.e.a(this.h, chunk);
            } catch (IOException ioexception) {
                ioexception.printStackTrace();
            } catch (MinecraftException minecraftexception) {
                minecraftexception.printStackTrace();
            }
        }
    }

    public void a(IChunkProvider ichunkprovider, int i0, int i1) {
        Chunk chunk = this.d(i0, i1);

        if (!chunk.k) {
            chunk.k = true;
            if (this.d != null) {
                this.d.a(ichunkprovider, i0, i1);
                chunk.e();
            }
        }
    }

    public boolean a(boolean flag0, IProgressUpdate iprogressupdate) {
        int i0 = 0;

        for (int i1 = 0; i1 < this.g.size(); ++i1) {
            Chunk chunk = (Chunk) this.g.get(i1);

            if (flag0) {
                this.a(chunk);
            }

            if (chunk.a(flag0)) {
                this.b(chunk);
                chunk.l = false;
                ++i0;
                if (i0 == 24 && !flag0) {
                    return false;
                }
            }
        }

        return true;
    }

    public void b() {
        if (this.e != null) {
            this.e.b();
        }
    }

    public boolean c() {
        if (!this.h.c) {
            for (int i0 = 0; i0 < 100; ++i0) {
                if (!this.b.isEmpty()) {
                    Long olong = (Long) this.b.iterator().next();
                    Chunk chunk = (Chunk) this.f.a(olong.longValue());

                    if (chunk != null) {
                        // CanaryMod: ChunkUnload
                        ChunkUnloadHook hook = new ChunkUnloadHook(chunk.getCanaryChunk(), h.getCanaryWorld());

                        Canary.hooks().callHook(hook);
                        if (hook.isCanceled()) {
                            // TODO: Might need to return false instead ... unsure
                            return true;
                        }
                        //
                        chunk.d();
                        this.b(chunk);
                        this.a(chunk);
                        this.b.remove(olong);
                        this.f.d(olong.longValue());
                        this.g.remove(chunk);
                    }
                }
            }

            if (this.e != null) {
                this.e.a();
            }
        }

        return this.d.c();
    }

    public boolean d() {
        return !this.h.c;
    }

    public String e() {
        return "ServerChunkCache: " + this.f.a() + " Drop: " + this.b.size();
    }

    public List a(EnumCreatureType enumcreaturetype, int i0, int i1, int i2) {
        return this.d.a(enumcreaturetype, i0, i1, i2);
    }

    public ChunkPosition a(World world, String s0, int i0, int i1, int i2) {
        return this.d.a(world, s0, i0, i1, i2);
    }

    public int f() {
        return this.f.a();
    }

    public void e(int i0, int i1) {}
}
