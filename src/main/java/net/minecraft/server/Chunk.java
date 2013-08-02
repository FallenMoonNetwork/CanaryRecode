package net.minecraft.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.canarymod.Canary;
import net.canarymod.PortalReconstructJob;
import net.canarymod.api.world.CanaryChunk;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.hook.world.PortalDestroyHook;
import net.canarymod.tasks.ServerTaskManager;

public class Chunk {

    public static boolean a;
    private ExtendedBlockStorage[] r;
    private byte[] s;
    public int[] b;
    public boolean[] c;
    public boolean d;
    public World e;
    public int[] f;
    public final int g;
    public final int h;
    private boolean t;
    public Map i;
    public List[] j;
    public boolean k;
    public boolean l;
    public boolean m;
    public long n;
    public boolean o;
    public int p;
    public long q;
    private int u;
    // CanaryMod Chunk handler
    private CanaryChunk canaryChunk;

    public Chunk(World world, int i0, int i1) {
        canaryChunk = new CanaryChunk(this); // CanaryMod: wrap chunk
        this.r = new ExtendedBlockStorage[16];
        this.s = new byte[256];
        this.b = new int[256];
        this.c = new boolean[256];
        this.i = new HashMap();
        this.u = 4096;
        this.j = new List[16];
        this.e = world;
        this.g = i0;
        this.h = i1;
        this.f = new int[256];

        for (int i2 = 0; i2 < this.j.length; ++i2) {
            this.j[i2] = new ArrayList();
        }

        Arrays.fill(this.b, -999);
        Arrays.fill(this.s, (byte) -1);
    }

    public Chunk(World world, byte[] abyte, int i0, int i1) {
        this(world, i0, i1);
        int i2 = abyte.length / 256;

        for (int i3 = 0; i3 < 16; ++i3) {
            for (int i4 = 0; i4 < 16; ++i4) {
                for (int i5 = 0; i5 < i2; ++i5) {
                    byte b0 = abyte[i3 << 11 | i4 << 7 | i5];

                    if (b0 != 0) {
                        int i6 = i5 >> 4;

                        if (this.r[i6] == null) {
                            this.r[i6] = new ExtendedBlockStorage(i6 << 4, !world.t.g);
                        }

                        this.r[i6].a(i3, i5 & 15, i4, b0);
                    }
                }
            }
        }
    }

    public boolean a(int i0, int i1) {
        return i0 == this.g && i1 == this.h;
    }

    public int b(int i0, int i1) {
        return this.f[i1 << 4 | i0];
    }

    public int h() {
        for (int i0 = this.r.length - 1; i0 >= 0; --i0) {
            if (this.r[i0] != null) {
                return this.r[i0].d();
            }
        }

        return 0;
    }

    public ExtendedBlockStorage[] i() {
        return this.r;
    }

    public void b() {
        int i0 = this.h();

        this.p = Integer.MAX_VALUE;

        int i1;
        int i2;

        for (i1 = 0; i1 < 16; ++i1) {
            i2 = 0;

            while (i2 < 16) {
                this.b[i1 + (i2 << 4)] = -999;
                int i3 = i0 + 16 - 1;

                while (true) {
                    if (i3 > 0) {
                        if (this.b(i1, i3 - 1, i2) == 0) {
                            --i3;
                            continue;
                        }

                        this.f[i2 << 4 | i1] = i3;
                        if (i3 < this.p) {
                            this.p = i3;
                        }
                    }

                    if (!this.e.t.g) {
                        i3 = 15;
                        int i4 = i0 + 16 - 1;

                        do {
                            i3 -= this.b(i1, i4, i2);
                            if (i3 > 0) {
                                ExtendedBlockStorage extendedblockstorage = this.r[i4 >> 4];

                                if (extendedblockstorage != null) {
                                    extendedblockstorage.c(i1, i4 & 15, i2, i3);
                                    this.e.p((this.g << 4) + i1, i4, (this.h << 4) + i2);
                                }
                            }

                            --i4;
                        }
                        while (i4 > 0 && i3 > 0);
                    }

                    ++i2;
                    break;
                }
            }
        }

        this.l = true;

        for (i1 = 0; i1 < 16; ++i1) {
            for (i2 = 0; i2 < 16; ++i2) {
                this.e(i1, i2);
            }
        }
    }

    private void e(int i0, int i1) {
        this.c[i0 + i1 * 16] = true;
        this.t = true;
    }

    //CanaryMod private->public
    public void q() {
        this.e.C.a("recheckGaps");
        if (this.e.b(this.g * 16 + 8, 0, this.h * 16 + 8, 16)) {
            for (int i0 = 0; i0 < 16; ++i0) {
                for (int i1 = 0; i1 < 16; ++i1) {
                    if (this.c[i0 + i1 * 16]) {
                        this.c[i0 + i1 * 16] = false;
                        int i2 = this.b(i0, i1);
                        int i3 = this.g * 16 + i0;
                        int i4 = this.h * 16 + i1;
                        int i5 = this.e.g(i3 - 1, i4);
                        int i6 = this.e.g(i3 + 1, i4);
                        int i7 = this.e.g(i3, i4 - 1);
                        int i8 = this.e.g(i3, i4 + 1);

                        if (i6 < i5) {
                            i5 = i6;
                        }

                        if (i7 < i5) {
                            i5 = i7;
                        }

                        if (i8 < i5) {
                            i5 = i8;
                        }

                        this.g(i3, i4, i5);
                        this.g(i3 - 1, i4, i2);
                        this.g(i3 + 1, i4, i2);
                        this.g(i3, i4 - 1, i2);
                        this.g(i3, i4 + 1, i2);
                    }
                }
            }

            this.t = false;
        }

        this.e.C.b();
    }

    private void g(int i0, int i1, int i2) {
        int i3 = this.e.f(i0, i1);

        if (i3 > i2) {
            this.d(i0, i1, i2, i3 + 1);
        } else if (i3 < i2) {
            this.d(i0, i1, i3, i2 + 1);
        }
    }

    private void d(int i0, int i1, int i2, int i3) {
        if (i3 > i2 && this.e.b(i0, 0, i1, 16)) {
            for (int i4 = i2; i4 < i3; ++i4) {
                this.e.c(EnumSkyBlock.a, i0, i4, i1);
            }

            this.l = true;
        }
    }

    //CanaryMod private->public
    public void h(int i0, int i1, int i2) {
        int i3 = this.f[i2 << 4 | i0] & 255;
        int i4 = i3;

        if (i1 > i3) {
            i4 = i1;
        }

        while (i4 > 0 && this.b(i0, i4 - 1, i2) == 0) {
            --i4;
        }

        if (i4 != i3) {
            this.e.e(i0 + this.g * 16, i2 + this.h * 16, i4, i3);
            this.f[i2 << 4 | i0] = i4;
            int i5 = this.g * 16 + i0;
            int i6 = this.h * 16 + i2;
            int i7;
            int i8;

            if (!this.e.t.g) {
                ExtendedBlockStorage extendedblockstorage;

                if (i4 < i3) {
                    for (i7 = i4; i7 < i3; ++i7) {
                        extendedblockstorage = this.r[i7 >> 4];
                        if (extendedblockstorage != null) {
                            extendedblockstorage.c(i0, i7 & 15, i2, 15);
                            this.e.p((this.g << 4) + i0, i7, (this.h << 4) + i2);
                        }
                    }
                } else {
                    for (i7 = i3; i7 < i4; ++i7) {
                        // CanaryMod start: Catch corrupt index info
                        if (i7 >> 4 < 0 || i7 >> 4 >= 16) {
                            Canary.logWarning("Invalid chunk info array index: " + (i7 >> 4));
                            Canary.logWarning("x: " + i3 + ", z: " + i4);
                            Canary.logWarning("Chunk location: " + i5 + ", " + i6);
                            i7 = 0;
                        }
                        // CanaryMod end
                        extendedblockstorage = this.r[i7 >> 4];
                        if (extendedblockstorage != null) {
                            extendedblockstorage.c(i0, i7 & 15, i2, 0);
                            this.e.p((this.g << 4) + i0, i7, (this.h << 4) + i2);
                        }
                    }
                }

                i7 = 15;

                while (i4 > 0 && i7 > 0) {
                    --i4;
                    i8 = this.b(i0, i4, i2);
                    if (i8 == 0) {
                        i8 = 1;
                    }

                    i7 -= i8;
                    if (i7 < 0) {
                        i7 = 0;
                    }

                    ExtendedBlockStorage extendedblockstorage1 = this.r[i4 >> 4];

                    if (extendedblockstorage1 != null) {
                        extendedblockstorage1.c(i0, i4 & 15, i2, i7);
                    }
                }
            }

            i7 = this.f[i2 << 4 | i0];
            i8 = i3;
            int i9 = i7;

            if (i7 < i3) {
                i8 = i7;
                i9 = i3;
            }

            if (i7 < this.p) {
                this.p = i7;
            }

            if (!this.e.t.g) {
                this.d(i5 - 1, i6, i8, i9);
                this.d(i5 + 1, i6, i8, i9);
                this.d(i5, i6 - 1, i8, i9);
                this.d(i5, i6 + 1, i8, i9);
                this.d(i5, i6, i8, i9);
            }

            this.l = true;
        }
    }

    public int b(int i0, int i1, int i2) {
        return Block.u[this.a(i0, i1, i2)];
    }

    public int a(int i0, int i1, int i2) {
        if (i1 >> 4 >= this.r.length) {
            return 0;
        } else {
            ExtendedBlockStorage extendedblockstorage = this.r[i1 >> 4];

            return extendedblockstorage != null ? extendedblockstorage.a(i0, i1 & 15, i2) : 0;
        }
    }

    public int c(int i0, int i1, int i2) {
        if (i1 >> 4 >= this.r.length) {
            return 0;
        } else {
            ExtendedBlockStorage extendedblockstorage = this.r[i1 >> 4];

            return extendedblockstorage != null ? extendedblockstorage.b(i0, i1 & 15, i2) : 0;
        }
    }

    public boolean a(int i0, int i1, int i2, int i3, int i4) {
        return this.a(i0, i1, i2, i3, i4, true); // CanaryMod: Redirect
    }

    public boolean a(int i0, int i1, int i2, int i3, int i4, boolean checkPortal) { // CanaryMod: add Portal Check
        int i5 = i2 << 4 | i0;

        if (i1 >= this.b[i5] - 1) {
            this.b[i5] = -999;
        }

        int i6 = this.f[i5];
        int i7 = this.a(i0, i1, i2);
        int i8 = this.c(i0, i1, i2);

        if (i7 == i3 && i8 == i4) {
            return false;
        } else {
            // CanaryMod: Start - check if removed block is portal block
            int portalPointX = this.g * 16 + i0;
            int portalPointZ = this.h * 16 + i2;

            if (checkPortal == true) {
                int portalPointY = i1;

                int portalId = BlockType.Portal.getId();
                net.canarymod.api.world.World world = canaryChunk.getDimension();
                if (world != null && world.getBlockAt(portalPointX, portalPointY, portalPointZ).getTypeId() == portalId) {
                    // These will be equal 1 if the portal is defined on their axis and 0 if not.
                    int portalXOffset = (canaryChunk.getDimension().getBlockAt(portalPointX - 1, portalPointY, portalPointZ).getTypeId() == portalId || canaryChunk.getDimension().getBlockAt(portalPointX + 1, portalPointY, portalPointZ).getTypeId() == portalId) ? 1 : 0;
                    int portalZOffset = (canaryChunk.getDimension().getBlockAt(portalPointX, portalPointY, portalPointZ - 1).getTypeId() == portalId || canaryChunk.getDimension().getBlockAt(portalPointX, portalPointY, portalPointZ + 1).getTypeId() == portalId) ? 1 : 0;

                    // If the portal is either x aligned or z aligned but not both (has neighbor portal in x or z plane but not both)
                    if (portalXOffset != portalZOffset) {
                        // Get the edge of the portal.
                        int portalX = portalPointX - ((canaryChunk.getDimension().getBlockAt(portalPointX - 1, portalPointY, portalPointZ).getTypeId() == portalId) ? 1 : 0);
                        int portalZ = portalPointZ - ((canaryChunk.getDimension().getBlockAt(portalPointX, portalPointY, portalPointZ - 1).getTypeId() == portalId) ? 1 : 0);
                        int portalY = i1;

                        while (canaryChunk.getDimension().getBlockAt(portalX, ++portalY, portalZ).getTypeId() == portalId) {
                            ;
                        }
                        portalY -= 1;
                        // Scan the portal and see if its still all there (2x3 formation)
                        boolean completePortal = true;
                        CanaryBlock[][] portalBlocks = new CanaryBlock[3][2];

                        for (int i9001 = 0; i9001 < 3 && completePortal; i9001 += 1) {
                            for (int i9002 = 0; i9002 < 2 && completePortal; i9002 += 1) {
                                portalBlocks[i9001][i9002] = (CanaryBlock) canaryChunk.getDimension().getBlockAt(portalX + i9002 * portalXOffset, portalY - i9001, portalZ + i9002 * portalZOffset);
                                if (portalBlocks[i9001][i9002].getTypeId() != portalId) {
                                    completePortal = false;
                                }
                            }
                        }
                        if (completePortal == true) {
                            // CanaryMod: PortalDestroy
                            PortalDestroyHook hook = (PortalDestroyHook) new PortalDestroyHook(portalBlocks).call();
                            if (hook.isCanceled()) {// Hook canceled = don't destroy the portal.
                                // in that case we need to reconstruct the portal's frame to make the portal valid.
                                // Problem is we don't want to reconstruct it right away because more blocks might be deleted (for example on explosion).
                                // In order to avoid spamming the hook for each destroyed block, I'm queuing the reconstruction of the portal instead.
                                ServerTaskManager.addTask(new PortalReconstructJob(this.e.getCanaryWorld(), portalX, portalY, portalZ, (portalXOffset == 1)));
                            }
                        }
                    }
                }
            }
            // CanaryMod: End - check if removed block is portal block0.

            ExtendedBlockStorage extendedblockstorage = this.r[i1 >> 4];
            boolean flag0 = false;

            if (extendedblockstorage == null) {
                if (i3 == 0) {
                    return false;
                }

                extendedblockstorage = this.r[i1 >> 4] = new ExtendedBlockStorage(i1 >> 4 << 4, !this.e.t.g);
                flag0 = i1 >= i6;
            }

            int i9 = this.g * 16 + i0;
            int i10 = this.h * 16 + i2;

            if (i7 != 0 && !this.e.I) {
                Block.s[i7].l(this.e, i9, i1, i10, i8);
            }

            extendedblockstorage.a(i0, i1 & 15, i2, i3);
            if (i7 != 0) {
                if (!this.e.I) {
                    Block.s[i7].a(this.e, i9, i1, i10, i7, i8);
                } else if (Block.s[i7] instanceof ITileEntityProvider && i7 != i3) {
                    this.e.s(i9, i1, i10);
                }
            }

            if (extendedblockstorage.a(i0, i1 & 15, i2) != i3) {
                return false;
            } else {
                extendedblockstorage.b(i0, i1 & 15, i2, i4);
                if (flag0) {
                    this.b();
                } else {
                    if (Block.u[i3 & 4095] > 0) {
                        if (i1 >= i6) {
                            this.h(i0, i1 + 1, i2);
                        }
                    } else if (i1 == i6 - 1) {
                        this.h(i0, i1, i2);
                    }

                    this.e(i0, i2);
                }

                TileEntity tileentity;

                if (i3 != 0) {
                    if (!this.e.I) {
                        Block.s[i3].a(this.e, i9, i1, i10);
                    }

                    if (Block.s[i3] instanceof ITileEntityProvider) {
                        tileentity = this.e(i0, i1, i2);
                        if (tileentity == null) {
                            tileentity = ((ITileEntityProvider) Block.s[i3])
                                    .b(this.e);
                            this.e.a(i9, i1, i10, tileentity);
                        }

                        if (tileentity != null) {
                            tileentity.i();
                        }
                    }
                } else if (i7 > 0 && Block.s[i7] instanceof ITileEntityProvider) {
                    tileentity = this.e(i0, i1, i2);
                    if (tileentity != null) {
                        tileentity.i();
                    }
                }

                this.l = true;
                return true;
            }
        }
    }

    public boolean b(int i0, int i1, int i2, int i3) {
        ExtendedBlockStorage extendedblockstorage = this.r[i1 >> 4];

        if (extendedblockstorage == null) {
            return false;
        } else {
            int i4 = extendedblockstorage.b(i0, i1 & 15, i2);

            if (i4 == i3) {
                return false;
            } else {
                this.l = true;
                extendedblockstorage.b(i0, i1 & 15, i2, i3);
                int i5 = extendedblockstorage.a(i0, i1 & 15, i2);

                if (i5 > 0 && Block.s[i5] instanceof ITileEntityProvider) {
                    TileEntity tileentity = this.e(i0, i1, i2);

                    if (tileentity != null) {
                        tileentity.i();
                        tileentity.p = i3;
                    }
                }

                return true;
            }
        }
    }

    public int a(EnumSkyBlock enumskyblock, int i0, int i1, int i2) {
        ExtendedBlockStorage extendedblockstorage = this.r[i1 >> 4];

        return extendedblockstorage == null ? (this.d(i0, i1, i2) ? enumskyblock.c
                : 0)
                : (enumskyblock == EnumSkyBlock.a ? (this.e.t.g ? 0
                        : extendedblockstorage.c(i0, i1 & 15, i2))
                        : (enumskyblock == EnumSkyBlock.b ? extendedblockstorage
                                .d(i0, i1 & 15, i2) : enumskyblock.c));
    }

    public void a(EnumSkyBlock enumskyblock, int i0, int i1, int i2, int i3) {
        ExtendedBlockStorage extendedblockstorage = this.r[i1 >> 4];

        if (extendedblockstorage == null) {
            extendedblockstorage = this.r[i1 >> 4] = new ExtendedBlockStorage(i1 >> 4 << 4, !this.e.t.g);
            this.b();
        }

        this.l = true;
        if (enumskyblock == EnumSkyBlock.a) {
            if (!this.e.t.g) {
                extendedblockstorage.c(i0, i1 & 15, i2, i3);
            }
        } else if (enumskyblock == EnumSkyBlock.b) {
            extendedblockstorage.d(i0, i1 & 15, i2, i3);
        }
    }

    public int c(int i0, int i1, int i2, int i3) {
        ExtendedBlockStorage extendedblockstorage = this.r[i1 >> 4];

        if (extendedblockstorage == null) {
            return !this.e.t.g && i3 < EnumSkyBlock.a.c ? EnumSkyBlock.a.c - i3 : 0;
        } else {
            int i4 = this.e.t.g ? 0 : extendedblockstorage.c(i0, i1 & 15, i2);

            if (i4 > 0) {
                a = true;
            }

            i4 -= i3;
            int i5 = extendedblockstorage.d(i0, i1 & 15, i2);

            if (i5 > i4) {
                i4 = i5;
            }

            return i4;
        }
    }

    public void a(Entity entity) {
        this.m = true;
        int i0 = MathHelper.c(entity.u / 16.0D);
        int i1 = MathHelper.c(entity.w / 16.0D);

        if (i0 != this.g || i1 != this.h) {
            this.e.Y().c("Wrong location! " + entity);
            // Thread.dumpStack();
            entity.w();
            return;
        }

        int i2 = MathHelper.c(entity.v / 16.0D);

        if (i2 < 0) {
            i2 = 0;
        }

        if (i2 >= this.j.length) {
            i2 = this.j.length - 1;
        }

        entity.ai = true;
        entity.aj = this.g;
        entity.ak = i2;
        entity.al = this.h;
        this.j[i2].add(entity);
    }

    public void b(Entity entity) {
        this.a(entity, entity.ak);
    }

    public void a(Entity entity, int i0) {
        if (i0 < 0) {
            i0 = 0;
        }

        if (i0 >= this.j.length) {
            i0 = this.j.length - 1;
        }

        this.j[i0].remove(entity);
    }

    public boolean d(int i0, int i1, int i2) {
        return i1 >= this.f[i2 << 4 | i0];
    }

    public TileEntity e(int i0, int i1, int i2) {
        ChunkPosition chunkposition = new ChunkPosition(i0, i1, i2);
        TileEntity tileentity = (TileEntity) this.i.get(chunkposition);

        if (tileentity == null) {
            int i3 = this.a(i0, i1, i2);

            if (i3 <= 0 || !Block.s[i3].t()) {
                return null;
            }

            if (tileentity == null) {
                tileentity = ((ITileEntityProvider) Block.s[i3]).b(this.e);
                this.e.a(this.g * 16 + i0, i1, this.h * 16 + i2, tileentity);
            }

            tileentity = (TileEntity) this.i.get(chunkposition);
        }

        if (tileentity != null && tileentity.r()) {
            this.i.remove(chunkposition);
            return null;
        } else {
            return tileentity;
        }
    }

    public void a(TileEntity tileentity) {
        int i0 = tileentity.l - this.g * 16;
        int i1 = tileentity.m;
        int i2 = tileentity.n - this.h * 16;

        this.a(i0, i1, i2, tileentity);
        if (this.d) {
            this.e.g.add(tileentity);
        }
    }

    public void a(int i0, int i1, int i2, TileEntity tileentity) {
        ChunkPosition chunkposition = new ChunkPosition(i0, i1, i2);

        tileentity.b(this.e);
        tileentity.l = this.g * 16 + i0;
        tileentity.m = i1;
        tileentity.n = this.h * 16 + i2;
        if (this.a(i0, i1, i2) != 0 && Block.s[this.a(i0, i1, i2)] instanceof ITileEntityProvider) {
            if (this.i.containsKey(chunkposition)) {
                ((TileEntity) this.i.get(chunkposition)).w_();
            }

            tileentity.s();
            this.i.put(chunkposition, tileentity);
        }
    }

    public void f(int i0, int i1, int i2) {
        ChunkPosition chunkposition = new ChunkPosition(i0, i1, i2);

        if (this.d) {
            TileEntity tileentity = (TileEntity) this.i.remove(chunkposition);

            if (tileentity != null) {
                tileentity.w_();
            }
        }
    }

    public void c() {
        this.d = true;
        this.e.a(this.i.values());

        for (int i0 = 0; i0 < this.j.length; ++i0) {
            Iterator iterator = this.j[i0].iterator();

            while (iterator.hasNext()) {
                Entity entity = (Entity) iterator.next();

                entity.Q();
            }

            this.e.a(this.j[i0]);
        }
    }

    public void d() {
        this.d = false;
        Iterator iterator = this.i.values().iterator();

        while (iterator.hasNext()) {
            TileEntity tileentity = (TileEntity) iterator.next();

            this.e.a(tileentity);
        }

        for (int i0 = 0; i0 < this.j.length; ++i0) {
            this.e.b(this.j[i0]);
        }
    }

    public void e() {
        this.l = true;
    }

    public void a(Entity entity, AxisAlignedBB axisalignedbb, List list, IEntitySelector ientityselector) {
        int i0 = MathHelper.c((axisalignedbb.b - 2.0D) / 16.0D);
        int i1 = MathHelper.c((axisalignedbb.e + 2.0D) / 16.0D);

        if (i0 < 0) {
            i0 = 0;
            i1 = Math.max(i0, i1);
        }

        if (i1 >= this.j.length) {
            i1 = this.j.length - 1;
            i0 = Math.min(i0, i1);
        }

        for (int i2 = i0; i2 <= i1; ++i2) {
            List list1 = this.j[i2];

            for (int i3 = 0; i3 < list1.size(); ++i3) {
                Entity entity1 = (Entity) list1.get(i3);

                if (entity1 != entity && entity1.E.b(axisalignedbb) && (ientityselector == null || ientityselector.a(entity1))) {
                    list.add(entity1);
                    Entity[] aentity = entity1.an();

                    if (aentity != null) {
                        for (int i4 = 0; i4 < aentity.length; ++i4) {
                            entity1 = aentity[i4];
                            if (entity1 != entity && entity1.E.b(axisalignedbb) && (ientityselector == null || ientityselector.a(entity1))) {
                                list.add(entity1);
                            }
                        }
                    }
                }
            }
        }
    }

    public void a(Class oclass0, AxisAlignedBB axisalignedbb, List list, IEntitySelector ientityselector) {
        int i0 = MathHelper.c((axisalignedbb.b - 2.0D) / 16.0D);
        int i1 = MathHelper.c((axisalignedbb.e + 2.0D) / 16.0D);

        if (i0 < 0) {
            i0 = 0;
        } else if (i0 >= this.j.length) {
            i0 = this.j.length - 1;
        }

        if (i1 >= this.j.length) {
            i1 = this.j.length - 1;
        } else if (i1 < 0) {
            i1 = 0;
        }

        for (int i2 = i0; i2 <= i1; ++i2) {
            List list1 = this.j[i2];

            for (int i3 = 0; i3 < list1.size(); ++i3) {
                Entity entity = (Entity) list1.get(i3);

                if (oclass0.isAssignableFrom(entity.getClass()) && entity.E.b(axisalignedbb) && (ientityselector == null || ientityselector.a(entity))) {
                    list.add(entity);
                }
            }
        }
    }

    public boolean a(boolean flag0) {
        if (flag0) {
            if (this.m && this.e.I() != this.n || this.l) {
                return true;
            }
        } else if (this.m && this.e.I() >= this.n + 600L) {
            return true;
        }

        return this.l;
    }

    public Random a(long i0) {
        return new Random(this.e.H() + (long) (this.g * this.g * 4987142) + (long) (this.g * 5947611) + (long) (this.h * this.h) * 4392871L + (long) (this.h * 389711) ^ i0);
    }

    public boolean g() {
        return false;
    }

    public void a(IChunkProvider ichunkprovider, IChunkProvider ichunkprovider1, int i0, int i1) {
        if (!this.k && ichunkprovider.a(i0 + 1, i1 + 1) && ichunkprovider.a(i0, i1 + 1) && ichunkprovider.a(i0 + 1, i1)) {
            ichunkprovider.a(ichunkprovider1, i0, i1);
        }

        if (ichunkprovider.a(i0 - 1, i1) && !ichunkprovider.d(i0 - 1, i1).k && ichunkprovider.a(i0 - 1, i1 + 1) && ichunkprovider.a(i0, i1 + 1) && ichunkprovider.a(i0 - 1, i1 + 1)) {
            ichunkprovider.a(ichunkprovider1, i0 - 1, i1);
        }

        if (ichunkprovider.a(i0, i1 - 1) && !ichunkprovider.d(i0, i1 - 1).k && ichunkprovider.a(i0 + 1, i1 - 1) && ichunkprovider.a(i0 + 1, i1 - 1) && ichunkprovider.a(i0 + 1, i1)) {
            ichunkprovider.a(ichunkprovider1, i0, i1 - 1);
        }

        if (ichunkprovider.a(i0 - 1, i1 - 1) && !ichunkprovider.d(i0 - 1, i1 - 1).k && ichunkprovider.a(i0, i1 - 1) && ichunkprovider.a(i0 - 1, i1)) {
            ichunkprovider.a(ichunkprovider1, i0 - 1, i1 - 1);
        }
    }

    public int d(int i0, int i1) {
        int i2 = i0 | i1 << 4;
        int i3 = this.b[i2];

        if (i3 == -999) {
            int i4 = this.h() + 15;

            i3 = -1;

            while (i4 > 0 && i3 == -1) {
                int i5 = this.a(i0, i4, i1);
                Material material = i5 == 0 ? Material.a : Block.s[i5].cU;

                if (!material.c() && !material.d()) {
                    --i4;
                } else {
                    i3 = i4 + 1;
                }
            }

            this.b[i2] = i3;
        }

        return i3;
    }

    public void k() {
        if (this.t && !this.e.t.g) {
            this.q();
        }
    }

    public ChunkCoordIntPair l() {
        return new ChunkCoordIntPair(this.g, this.h);
    }

    public boolean c(int i0, int i1) {
        if (i0 < 0) {
            i0 = 0;
        }

        if (i1 >= 256) {
            i1 = 255;
        }

        for (int i2 = i0; i2 <= i1; i2 += 16) {
            ExtendedBlockStorage extendedblockstorage = this.r[i2 >> 4];

            if (extendedblockstorage != null && !extendedblockstorage.a()) {
                return false;
            }
        }

        return true;
    }

    public void a(ExtendedBlockStorage[] aextendedblockstorage) {
        this.r = aextendedblockstorage;
    }

    public BiomeGenBase a(int i0, int i1, WorldChunkManager worldchunkmanager) {
        int i2 = this.s[i1 << 4 | i0] & 255;

        if (i2 == 255) {
            BiomeGenBase biomegenbase = worldchunkmanager.a((this.g << 4) + i0, (this.h << 4) + i1);

            i2 = biomegenbase.N;
            this.s[i1 << 4 | i0] = (byte) (i2 & 255);
        }

        return BiomeGenBase.a[i2] == null ? BiomeGenBase.c : BiomeGenBase.a[i2];
    }

    public byte[] m() {
        return this.s;
    }

    public void a(byte[] abyte) {
        this.s = abyte;
    }

    public void n() {
        this.u = 0;
    }

    public void o() {
        for (int i0 = 0; i0 < 8; ++i0) {
            if (this.u >= 4096) {
                return;
            }

            int i1 = this.u % 16;
            int i2 = this.u / 16 % 16;
            int i3 = this.u / 256;

            ++this.u;
            int i4 = (this.g << 4) + i2;
            int i5 = (this.h << 4) + i3;

            for (int i6 = 0; i6 < 16; ++i6) {
                int i7 = (i1 << 4) + i6;

                if (this.r[i1] == null && (i6 == 0 || i6 == 15 || i2 == 0 || i2 == 15 || i3 == 0 || i3 == 15) || this.r[i1] != null && this.r[i1].a(i2, i6, i3) == 0) {
                    if (Block.w[this.e.a(i4, i7 - 1, i5)] > 0) {
                        this.e.A(i4, i7 - 1, i5);
                    }

                    if (Block.w[this.e.a(i4, i7 + 1, i5)] > 0) {
                        this.e.A(i4, i7 + 1, i5);
                    }

                    if (Block.w[this.e.a(i4 - 1, i7, i5)] > 0) {
                        this.e.A(i4 - 1, i7, i5);
                    }

                    if (Block.w[this.e.a(i4 + 1, i7, i5)] > 0) {
                        this.e.A(i4 + 1, i7, i5);
                    }

                    if (Block.w[this.e.a(i4, i7, i5 - 1)] > 0) {
                        this.e.A(i4, i7, i5 - 1);
                    }

                    if (Block.w[this.e.a(i4, i7, i5 + 1)] > 0) {
                        this.e.A(i4, i7, i5 + 1);
                    }

                    this.e.A(i4, i7, i5);
                }
            }
        }
    }

    // CanaryMod start
    public CanaryChunk getCanaryChunk() {
        return canaryChunk;
    }
    // CanaryMod end
}
