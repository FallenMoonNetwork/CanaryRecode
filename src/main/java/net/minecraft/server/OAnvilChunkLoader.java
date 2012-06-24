package net.minecraft.server;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.server.OAnvilChunkLoaderPending;
import net.minecraft.server.OChunk;
import net.minecraft.server.OChunkCoordIntPair;
import net.minecraft.server.OCompressedStreamTools;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityList;
import net.minecraft.server.OExtendedBlockStorage;
import net.minecraft.server.OIChunkLoader;
import net.minecraft.server.OIThreadedFileIO;
import net.minecraft.server.ONBTBase;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.ONBTTagList;
import net.minecraft.server.ONextTickListEntry;
import net.minecraft.server.ONibbleArray;
import net.minecraft.server.ORegionFileCache;
import net.minecraft.server.OThreadedFileIOBase;
import net.minecraft.server.OTileEntity;
import net.minecraft.server.OWorld;

public class OAnvilChunkLoader implements OIChunkLoader, OIThreadedFileIO {

    private List<OAnvilChunkLoaderPending> a = new ArrayList<OAnvilChunkLoaderPending>();
    private Set<OChunkCoordIntPair> b = new HashSet<OChunkCoordIntPair>();
    private Object c = new Object();
    private final File d;

    public OAnvilChunkLoader(File var1) {
        super();
        this.d = var1;
    }

    @Override
    public OChunk a(OWorld var1, int var2, int var3) throws IOException {
        ONBTTagCompound var4 = null;
        OChunkCoordIntPair var5 = new OChunkCoordIntPair(var2, var3);
        synchronized (this.c) {
            if (this.b.contains(var5)) {
                for (int var7 = 0; var7 < this.a.size(); ++var7) {
                    if (((OAnvilChunkLoaderPending) this.a.get(var7)).a.equals(var5)) {
                        var4 = ((OAnvilChunkLoaderPending) this.a.get(var7)).b;
                        break;
                    }
                }
            }
        }

        if (var4 == null) {
            DataInputStream var10 = ORegionFileCache.b(this.d, var2, var3);
            if (var10 == null) {
                return null;
            }

            var4 = OCompressedStreamTools.a((DataInput) var10);
        }

        return this.a(var1, var2, var3, var4);
    }

    protected OChunk a(OWorld var1, int var2, int var3, ONBTTagCompound var4) {
        if (!var4.c("Level")) {
            System.out.println("Chunk file at " + var2 + "," + var3 + " is missing level data, skipping");
            return null;
        } else if (!var4.m("Level").c("Sections")) {
            System.out.println("Chunk file at " + var2 + "," + var3 + " is missing block data, skipping");
            return null;
        } else {
            OChunk var5 = this.a(var1, var4.m("Level"));
            if (!var5.a(var2, var3)) {
                System.out.println("Chunk file at " + var2 + "," + var3 + " is in the wrong location; relocating. (Expected " + var2 + ", " + var3 + ", got " + var5.g + ", " + var5.h + ")");
                var4.a("xPos", var2);
                var4.a("zPos", var3);
                var5 = this.a(var1, var4.m("Level"));
            }

            var5.i();
            return var5;
        }
    }

    @Override
    public void a(OWorld var1, OChunk var2) {
        var1.m();

        try {
            ONBTTagCompound var3 = new ONBTTagCompound();
            ONBTTagCompound var4 = new ONBTTagCompound();
            var3.a("Level", (ONBTBase) var4);
            this.a(var2, var1, var4);
            this.a(var2.k(), var3);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    protected void a(OChunkCoordIntPair var1, ONBTTagCompound var2) {
        synchronized (this.c) {
            if (this.b.contains(var1)) {
                for (int var4 = 0; var4 < this.a.size(); ++var4) {
                    if (((OAnvilChunkLoaderPending) this.a.get(var4)).a.equals(var1)) {
                        this.a.set(var4, new OAnvilChunkLoaderPending(var1, var2));
                        return;
                    }
                }
            }

            this.a.add(new OAnvilChunkLoaderPending(var1, var2));
            this.b.add(var1);
            OThreadedFileIOBase.a.a(this);
        }
    }

    @Override
    public boolean c() {
        OAnvilChunkLoaderPending var1 = null;
        synchronized (this.c) {
            if (this.a.size() <= 0) {
                return false;
            }

            var1 = (OAnvilChunkLoaderPending) this.a.remove(0);
            this.b.remove(var1.a);
        }

        if (var1 != null) {
            try {
                this.a(var1);
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        }

        return true;
    }

    private void a(OAnvilChunkLoaderPending var1) throws IOException {
        DataOutputStream var2 = ORegionFileCache.c(this.d, var1.a.a, var1.a.b);
        OCompressedStreamTools.a(var1.b, (DataOutput) var2);
        var2.close();
    }

    @Override
    public void b(OWorld var1, OChunk var2) {
    }

    @Override
    public void a() {
    }

    @Override
    public void b() {
    }

    private void a(OChunk var1, OWorld var2, ONBTTagCompound var3) {
        var2.m();
        var3.a("xPos", var1.g);
        var3.a("zPos", var1.h);
        var3.a("LastUpdate", var2.o());
        var3.a("HeightMap", var1.f);
        var3.a("TerrainPopulated", var1.k);
        OExtendedBlockStorage[] var4 = var1.h();
        ONBTTagList var5 = new ONBTTagList("Sections");
        OExtendedBlockStorage[] var6 = var4;
        int var7 = var4.length;

        ONBTTagCompound var10;
        for (int var8 = 0; var8 < var7; ++var8) {
            OExtendedBlockStorage var9 = var6[var8];
            if (var9 != null && var9.f() != 0) {
                var10 = new ONBTTagCompound();
                var10.a("Y", (byte) (var9.c() >> 4 & 255));
                var10.a("Blocks", var9.g());
                if (var9.h() != null) {
                    var10.a("Add", var9.h().a);
                }

                var10.a("Data", var9.i().a);
                var10.a("SkyLight", var9.k().a);
                var10.a("BlockLight", var9.j().a);
                var5.a(var10);
            }
        }

        var3.a("Sections", var5);
        var3.a("Biomes", var1.l());
        var1.m = false;
        ONBTTagList var17 = new ONBTTagList();

        Iterator var19;
        for (var7 = 0; var7 < var1.j.length; ++var7) {
            var19 = var1.j[var7].iterator();

            while (var19.hasNext()) {
                OEntity var21 = (OEntity) var19.next();
                var1.m = true;
                var10 = new ONBTTagCompound();
                if (var21.c(var10)) {
                    var17.a(var10);
                }
            }
        }

        var3.a("Entities", var17);
        ONBTTagList var18 = new ONBTTagList();
        var19 = var1.i.values().iterator();

        while (var19.hasNext()) {
            OTileEntity var22 = (OTileEntity) var19.next();
            var10 = new ONBTTagCompound();
            var22.b(var10);
            var18.a(var10);
        }

        var3.a("TileEntities", var18);
        List var20 = var2.a(var1, false);
        if (var20 != null) {
            long var11 = var2.o();
            ONBTTagList var13 = new ONBTTagList();
            Iterator var14 = var20.iterator();

            while (var14.hasNext()) {
                ONextTickListEntry var15 = (ONextTickListEntry) var14.next();
                ONBTTagCompound var16 = new ONBTTagCompound();
                var16.a("i", var15.d);
                var16.a("x", var15.a);
                var16.a("y", var15.b);
                var16.a("z", var15.c);
                var16.a("t", (int)(var15.e - var11));
                var13.a(var16);
            }

            var3.a("TileTicks", var13);
        }

    }

    private OChunk a(OWorld var1, ONBTTagCompound var2) {
        int var3 = var2.f("xPos");
        int var4 = var2.f("zPos");
        OChunk var5 = new OChunk(var1, var3, var4);
        var5.f = var2.l("HeightMap");
        var5.k = var2.o("TerrainPopulated");
        ONBTTagList var6 = var2.n("Sections");
        byte var7 = 16;
        OExtendedBlockStorage[] var8 = new OExtendedBlockStorage[var7];

        for (int var9 = 0; var9 < var6.d(); ++var9) {
            ONBTTagCompound var10 = (ONBTTagCompound) var6.a(var9);
            byte var11 = var10.d("Y");
            OExtendedBlockStorage var12 = new OExtendedBlockStorage(var11 << 4);
            var12.a(var10.k("Blocks"));
            if (var10.c("Add")) {
                var12.a(new ONibbleArray(var10.k("Add"), 4));
            }

            var12.b(new ONibbleArray(var10.k("Data"), 4));
            var12.d(new ONibbleArray(var10.k("SkyLight"), 4));
            var12.c(new ONibbleArray(var10.k("BlockLight"), 4));
            var12.d();
            var8[var11] = var12;
        }

        var5.a(var8);
        if (var2.c("Biomes")) {
            var5.a(var2.k("Biomes"));
        }

        ONBTTagList var14 = var2.n("Entities");
        if (var14 != null) {
            for (int var17 = 0; var17 < var14.d(); ++var17) {
                ONBTTagCompound var16 = (ONBTTagCompound) var14.a(var17);
                OEntity var18 = OEntityList.a(var16, var1);
                var5.m = true;
                if (var18 != null) {
                    var5.a(var18);
                }
            }
        }

        ONBTTagList var15 = var2.n("TileEntities");
        if (var15 != null) {
            for (int var21 = 0; var21 < var15.d(); ++var21) {
                ONBTTagCompound var20 = (ONBTTagCompound) var15.a(var21);
                OTileEntity var13 = OTileEntity.c(var20);
                if (var13 != null) {
                    var5.a(var13);
                }
            }
        }

        if (var2.c("TileTicks")) {
            ONBTTagList var19 = var2.n("TileTicks");
            if (var19 != null) {
                for (int var22 = 0; var22 < var19.d(); ++var22) {
                    ONBTTagCompound var23 = (ONBTTagCompound) var19.a(var22);
                    var1.d(var23.f("x"), var23.f("y"), var23.f("z"), var23.f("i"), var23.f("t"));
                }
            }
        }

        return var5;
    }
}
