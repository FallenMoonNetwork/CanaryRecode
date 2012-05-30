package net.minecraft.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.canarymod.CanaryMod;
import net.canarymod.api.world.CanaryChunkProviderServer;
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

    private Set b = new HashSet();
    private OChunk c;
    private OIChunkProvider d;
    private OIChunkLoader e;
    public boolean a = false;
    private OLongHashMap f = new OLongHashMap();
    private List g = new ArrayList();
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
    }
    
    //CanaryMod start
    public CanaryChunkProviderServer getCanaryChunkProvider() {
        return canaryChunkProvider;
    }
    //CanaryMod end

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
        Iterator var1 = this.g.iterator();

        while (var1.hasNext()) {
            OChunk var2 = (OChunk) var1.next();
            this.d(var2.g, var2.h);
        }

    }

    public OChunk c(int var1, int var2) {
        long var3 = OChunkCoordIntPair.a(var1, var2);
        this.b.remove(Long.valueOf(var3));
        OChunk var5 = (OChunk) this.f.a(var3);
        if (var5 == null) {
            // CanaryMod start: load preload plugins XXX
            if (loadStage < 1) {
                CanaryMod.loader().loadPlugins(true);
                loadStage = 1;
            }
            // CanaryMod end
            var5 = this.e(var1, var2);
            if (var5 == null) {
                if (this.d == null) {
                    var5 = this.c;
                } else {
                    var5 = this.d.b(var1, var2);
                }
            }

            this.f.a(var3, var5);
            this.g.add(var5);
            if (var5 != null) {
                var5.b();
                var5.c();
            }

            var5.a(this, this, var1, var2);
        }

        return var5;
    }

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

    private void a(OChunk var1) {
        if (this.e != null) {
            try {
                this.e.b(this.h, var1);
            } catch (Exception var3) {
                var3.printStackTrace();
            }

        }
    }

    private void b(OChunk var1) throws IOException {
        if (this.e != null) {
            var1.n = this.h.o();
            this.e.a(this.h, var1);

        }
    }

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

    public boolean a(boolean var1, OIProgressUpdate var2) throws IOException {
        int var3 = 0;

        // CanaryMod start: load plugins XXX
        if (loadStage < 2) {
            CanaryMod.loader().loadPlugins(false);
            loadStage = 2;
        }
        // CanaryMod end
        
        for (int var4 = 0; var4 < this.g.size(); ++var4) {
            OChunk var5 = (OChunk) this.g.get(var4);
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

    public boolean a() throws IOException {
        if (!this.h.I) {
            for (int var1 = 0; var1 < 100; ++var1) {
                if (!this.b.isEmpty()) {
                    Long var2 = (Long) this.b.iterator().next();
                    OChunk var3 = (OChunk) this.f.a(var2.longValue());
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

    public boolean b() {
        return !this.h.I;
    }

    public String d() {
        return "ServerChunkCache: " + this.f.a() + " Drop: " + this.b.size();
    }

    public List a(OEnumCreatureType var1, int var2, int var3, int var4) {
        return this.d.a(var1, var2, var3, var4);
    }

    public OChunkPosition a(OWorld var1, String var2, int var3, int var4, int var5) {
        return this.d.a(var1, var2, var3, var4, var5);
    }
}
