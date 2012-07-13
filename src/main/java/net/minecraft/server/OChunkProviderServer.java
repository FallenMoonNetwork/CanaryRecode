package net.minecraft.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.canarymod.Canary;
import net.canarymod.CanaryMod;
import net.canarymod.api.world.CanaryChunkProviderServer;
import net.canarymod.hook.Hook;
import net.canarymod.hook.world.ChunkCreationHook;
import net.canarymod.hook.world.ChunkHook;
import net.minecraft.server.OChunk;
import net.minecraft.server.OChunkCoordIntPair;
import net.minecraft.server.OChunkCoordinates;
import net.minecraft.server.OChunkPosition;
import net.minecraft.server.OEmptyChunk;
import net.minecraft.server.OEnumCreatureType;
import net.minecraft.server.OIChunkLoader;
import net.minecraft.server.OIChunkProvider;
import net.minecraft.server.OIProgressUpdate;
import net.minecraft.server.OLongHashMap;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldServer;

public class OChunkProviderServer implements OIChunkProvider {

    public Set<Long> b = new HashSet<Long>(); //CanaryMod private -> public, parameterized
    private OChunk c;
    public OIChunkProvider d; //CanaryMod private -> public
    public OIChunkLoader e; //CanaryMod private -> public
    public boolean a = false;
    public OLongHashMap f = new OLongHashMap(); //CanaryMod private -> public
    public List<OChunk> g = new ArrayList<OChunk>(); //CanaryMod private -> public, parameterized
    private OWorldServer h;
    
    //CanaryMod start
    private CanaryChunkProviderServer canaryChunkProvider;
    private int loadStage = 0;
    //CanaryMod end

    public OChunkProviderServer(OWorldServer var1, OIChunkLoader var2, OIChunkProvider var3) {
        super();
        this.c = new OEmptyChunk(var1, 0, 0);
        this.h = var1;
        this.e = var2;
        this.d = var3;
        this.canaryChunkProvider = new CanaryChunkProviderServer(this);
    }
    
    //CanaryMod start
    public CanaryChunkProviderServer getCanaryChunkProvider() {
        return canaryChunkProvider;
    }
    //CanaryMod end

    @Override
    public boolean a(int var1, int var2) {
        return this.f.b(OChunkCoordIntPair.a(var1, var2));
    }

    public void d(int var1, int var2) {
        if (this.h.t.c()) {
            OChunkCoordinates var3 = this.h.p();
            int var4 = var1 * 16 + 8 - var3.a;
            int var5 = var2 * 16 + 8 - var3.c;
            short var6 = 128;
            if (var4 < -var6 || var4 > var6 || var5 < -var6 || var5 > var6) {
                this.b.add(Long.valueOf(OChunkCoordIntPair.a(var1, var2)));
            }
        } else {
            this.b.add(Long.valueOf(OChunkCoordIntPair.a(var1, var2)));
        }

    }

    public void c() {
        Iterator<OChunk> var1 = this.g.iterator();

        while (var1.hasNext()) {
            OChunk var2 = var1.next();
            this.d(var2.g, var2.h);
        }

    }

    @Override
    public OChunk c(int var1, int var2) {
        long var3 = OChunkCoordIntPair.a(var1, var2);
        this.b.remove(Long.valueOf(var3));
        OChunk var5 = (OChunk) this.f.a(var3);
        if (var5 == null) {
            // CanaryMod start: load preload plugins XXX
            if (loadStage < 1) {
                Canary.loader().loadPlugins(true);
                loadStage = 1;
            }
            // CanaryMod end
            
            
            var5 = this.e(var1, var2);
            if (var5 == null) {
                //CanaryMod start - onChunkCreation
                ChunkCreationHook hook = new ChunkCreationHook(var1, var2, h.getCanaryDimension());
                Canary.hooks().callHook(hook);
                byte[] blocks = hook.getBlockData();
                
                if(blocks != null){
                    var5 = new OChunk(h, blocks, var1, var2); //Make a new chunk with our new block data
                    if(hook.getBiomeData() != null){
                        var5.a(hook.getBiomeData()); //Set the biomedata
                    }
                    var5.k = true; //isPopulated
                    //CanaryMod end
                }
                else if (this.d == null) {
                    var5 = this.c;
                } else {
                    var5 = this.d.b(var1, var2);
                }
                //CanaryMod - onChunkCreated
                Canary.hooks().callHook(new ChunkHook(var5.getCanaryChunk(), Hook.Type.CHUNK_CREATED));
            }

            this.f.a(var3, var5);
            this.g.add(var5);
            if (var5 != null) {
                var5.b();
                var5.c();
                //CanaryMod onChunkLoad
                Canary.hooks().callHook(new ChunkHook(var5.getCanaryChunk(), Hook.Type.CHUNK_LOADED));
            }

            var5.a(this, this, var1, var2);
        }

        return var5;
    }

    @Override
    public OChunk b(int var1, int var2) {
        OChunk var3 = (OChunk) this.f.a(OChunkCoordIntPair.a(var1, var2));
        return var3 == null ? (!this.h.y && !this.a ? this.c : this.c(var1, var2)) : var3;
    }

    private OChunk e(int var1, int var2) {
        if (this.e == null) {
            return null;
        } else {
            try {
                OChunk var3 = this.e.a(this.h, var1, var2);
                if (var3 != null) {
                    var3.n = this.h.o();
                }

                return var3;
            } catch (Exception var4) {
                var4.printStackTrace();
                return null;
            }
        }
    }

    //CanaryMod private -> public
    public void a(OChunk var1) {
        if (this.e != null) {
            try {
                this.e.b(this.h, var1);
            } catch (Exception var3) {
                var3.printStackTrace();
            }

        }
    }

    //CanaryMod private -> public
    public void b(OChunk var1) throws IOException {
        if (this.e != null) {
            var1.n = this.h.o();
            this.e.a(this.h, var1);

        }
    }

    @Override
    public void a(OIChunkProvider var1, int var2, int var3) {
        OChunk var4 = this.b(var2, var3);
        if (!var4.k) {
            var4.k = true;
            if (this.d != null) {
                this.d.a(var1, var2, var3);
                var4.e();
            }
        }

    }

    @Override
    public boolean a(boolean var1, OIProgressUpdate var2) throws IOException {
        int var3 = 0;

        // CanaryMod start: load plugins XXX
        if (loadStage < 2) {
            Canary.loader().loadPlugins(false);
            loadStage = 2;
        }
        // CanaryMod end
        
        for (int var4 = 0; var4 < this.g.size(); ++var4) {
            OChunk var5 = this.g.get(var4);
            if (var1) {
                this.a(var5);
            }

            if (var5.a(var1)) {
                this.b(var5);
                var5.l = false;
                ++var3;
                if (var3 == 24 && !var1) {
                    return false;
                }
            }
        }

        if (var1) {
            if (this.e == null) {
                return true;
            }

            this.e.b();
        }

        return true;
    }

    @Override
    public boolean a() throws IOException {
        if (!this.h.I) {
            for (int var1 = 0; var1 < 100; ++var1) {
                if (!this.b.isEmpty()) {
                    Long var2 = this.b.iterator().next();
                    OChunk var3 = (OChunk) this.f.a(var2.longValue());
                    
                    //CanaryMod onChunkUnloaded
                    Canary.hooks().callHook(new ChunkHook(var3.getCanaryChunk(), Hook.Type.CHUNK_UNLOADED));
                    var3.d();
                    this.b(var3);
                    this.a(var3);
                    this.b.remove(var2);
                    this.f.d(var2.longValue());
                    this.g.remove(var3);
                }
            }

            if (this.e != null) {
                this.e.a();
            }
        }

        return this.d.a();
    }

    @Override
    public boolean b() {
        return !this.h.I;
    }

    public String d() {
        return "ServerChunkCache: " + this.f.a() + " Drop: " + this.b.size();
    }

    @Override
    public List a(OEnumCreatureType var1, int var2, int var3, int var4) {
        return this.d.a(var1, var2, var3, var4);
    }

    @Override
    public OChunkPosition a(OWorld var1, String var2, int var3, int var4, int var5) {
        return this.d.a(var1, var2, var3, var4, var5);
    }
}
