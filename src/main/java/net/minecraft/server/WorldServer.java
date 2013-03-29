package net.minecraft.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import net.canarymod.api.CanaryEntityTracker;
import net.canarymod.api.CanaryPlayerManager;
import net.canarymod.api.world.CanaryWorld;

public class WorldServer extends World {

    private final MinecraftServer a;
    private final EntityTracker J;
    private final PlayerManager K;
    private Set L;
    private TreeSet M;
    public ChunkProviderServer b;
    public boolean c;
    private boolean N;
    private int O = 0;
    private final Teleporter P;
    private ServerBlockEventList[] Q = new ServerBlockEventList[] { new ServerBlockEventList((ServerBlockEvent) null), new ServerBlockEventList((ServerBlockEvent) null)};
    private int R = 0;
    private static final WeightedRandomChestContent[] S = new WeightedRandomChestContent[] { new WeightedRandomChestContent(Item.E.cp, 0, 1, 3, 10), new WeightedRandomChestContent(Block.B.cz, 0, 1, 3, 10), new WeightedRandomChestContent(Block.N.cz, 0, 1, 3, 10), new WeightedRandomChestContent(Item.z.cp, 0, 1, 1, 3), new WeightedRandomChestContent(Item.v.cp, 0, 1, 1, 5), new WeightedRandomChestContent(Item.y.cp, 0, 1, 1, 3), new WeightedRandomChestContent(Item.u.cp, 0, 1, 1, 5), new WeightedRandomChestContent(Item.k.cp, 0, 2, 3, 5), new WeightedRandomChestContent(Item.V.cp, 0, 2, 3, 3)};
    private ArrayList T = new ArrayList();
    private IntHashMap U;

    public WorldServer(MinecraftServer minecraftserver, ISaveHandler isavehandler, String s0, int i0, WorldSettings worldsettings, Profiler profiler, ILogAgent ilogagent) {
        //TODO: WorldProvider: Needs changing so it would get any WorldProvider. Might need to make a mapping/register
        super(isavehandler, s0, worldsettings, WorldProvider.a(i0), profiler, ilogagent, net.canarymod.api.world.WorldType.fromId(i0));
        this.a = minecraftserver;
        this.J = new EntityTracker(this);
        this.K = new PlayerManager(this, minecraftserver.ad().o());
        if (this.U == null) {
            this.U = new IntHashMap();
        }

        if (this.L == null) {
            this.L = new HashSet();
        }

        if (this.M == null) {
            this.M = new TreeSet();
        }

        this.P = new Teleporter(this);
        this.D = new ServerScoreboard(minecraftserver);
        ScoreboardSaveData scoreboardsavedata = (ScoreboardSaveData) this.z.a(ScoreboardSaveData.class, "scoreboard");

        if (scoreboardsavedata == null) {
            scoreboardsavedata = new ScoreboardSaveData();
            this.z.a("scoreboard", (WorldSavedData) scoreboardsavedata);
        }

        scoreboardsavedata.a(this.D);
        ((ServerScoreboard) this.D).a(scoreboardsavedata);
        canaryDimension = new CanaryWorld(s0, (WorldServer)this, net.canarymod.api.world.WorldType.fromId(i0));
    }

    @Override
    public void b() {
        super.b();
        if (this.L().t() && this.r < 3) {
            this.r = 3;
        }

        this.t.d.b();
        if (this.e()) {
            boolean flag0 = false;

            if (this.E && this.r >= 1) {
                ;
            }

            if (!flag0) {
                long i0 = this.x.g() + 24000L;

                this.x.c(i0 - i0 % 24000L);
                this.d();
            }
        }

        this.C.a("mobSpawner");
        if (this.M().b("doMobSpawning")) {
            SpawnerAnimals.a(this, this.E, this.F, this.x.f() % 400L == 0L);
        }

        this.C.c("chunkSource");
        this.v.b();
        int i1 = this.a(1.0F);

        if (i1 != this.j) {
            this.j = i1;
        }

        this.x.b(this.x.f() + 1L);
        this.x.c(this.x.g() + 1L);
        this.C.c("tickPending");
        this.a(false);
        this.C.c("tickTiles");
        this.g();
        this.C.c("chunkMap");
        this.K.b();
        this.C.c("village");
        this.A.a();
        this.B.a();
        this.C.c("portalForcer");
        this.P.a(this.G());
        this.C.b();
        this.Y();
    }

    public SpawnListEntry a(EnumCreatureType enumcreaturetype, int i0, int i1, int i2) {
        List list = this.J().a(enumcreaturetype, i0, i1, i2);

        return list != null && !list.isEmpty() ? (SpawnListEntry) WeightedRandom.a(this.s, (Collection) list) : null;
    }

    @Override
    public void c() {
        this.N = !this.h.isEmpty();
        Iterator iterator = this.h.iterator();

        while (iterator.hasNext()) {
            EntityPlayer entityplayer = (EntityPlayer) iterator.next();

            if (!entityplayer.bz()) {
                this.N = false;
                break;
            }
        }
    }

    protected void d() {
        this.N = false;
        Iterator iterator = this.h.iterator();

        while (iterator.hasNext()) {
            EntityPlayer entityplayer = (EntityPlayer) iterator.next();

            if (entityplayer.bz()) {
                entityplayer.a(false, false, true);
            }
        }

        this.X();
    }

    private void X() {
        this.x.g(0);
        this.x.b(false);
        this.x.f(0);
        this.x.a(false);
    }

    public boolean e() {
        if (this.N && !this.I) {
            Iterator iterator = this.h.iterator();

            EntityPlayer entityplayer;

            do {
                if (!iterator.hasNext()) {
                    return true;
                }

                entityplayer = (EntityPlayer) iterator.next();
            } while (entityplayer.cg());

            return false;
        } else {
            return false;
        }
    }

    @Override
    protected void g() {
        super.g();
        int i0 = 0;
        int i1 = 0;
        Iterator iterator = this.G.iterator();

        while (iterator.hasNext()) {
            ChunkCoordIntPair chunkcoordintpair = (ChunkCoordIntPair) iterator.next();
            int i2 = chunkcoordintpair.a * 16;
            int i3 = chunkcoordintpair.b * 16;

            this.C.a("getChunk");
            Chunk chunk = this.e(chunkcoordintpair.a, chunkcoordintpair.b);

            this.a(i2, i3, chunk);
            this.C.c("tickChunk");
            chunk.k();
            this.C.c("thunder");
            int i4;
            int i5;
            int i6;
            int i7;

            if (this.s.nextInt(100000) == 0 && this.O() && this.N()) {
                this.k = this.k * 3 + 1013904223;
                i4 = this.k >> 2;
                i5 = i2 + (i4 & 15);
                i6 = i3 + (i4 >> 8 & 15);
                i7 = this.h(i5, i6);
                if (this.F(i5, i7, i6)) {
                    this.c(new EntityLightningBolt(this, (double) i5, (double) i7, (double) i6));
                }
            }

            this.C.c("iceandsnow");
            int i8;

            if (this.s.nextInt(16) == 0) {
                this.k = this.k * 3 + 1013904223;
                i4 = this.k >> 2;
                i5 = i4 & 15;
                i6 = i4 >> 8 & 15;
                i7 = this.h(i5 + i2, i6 + i3);
                if (this.y(i5 + i2, i7 - 1, i6 + i3)) {
                    this.c(i5 + i2, i7 - 1, i6 + i3, Block.aX.cz);
                }

                if (this.O() && this.z(i5 + i2, i7, i6 + i3)) {
                    this.c(i5 + i2, i7, i6 + i3, Block.aW.cz);
                }

                if (this.O()) {
                    BiomeGenBase biomegenbase = this.a(i5 + i2, i6 + i3);

                    if (biomegenbase.d()) {
                        i8 = this.a(i5 + i2, i7 - 1, i6 + i3);
                        if (i8 != 0) {
                            Block.r[i8].g(this, i5 + i2, i7 - 1, i6 + i3);
                        }
                    }
                }
            }

            this.C.c("tickTiles");
            ExtendedBlockStorage[] aextendedblockstorage = chunk.i();

            i5 = aextendedblockstorage.length;

            for (i6 = 0; i6 < i5; ++i6) {
                ExtendedBlockStorage extendedblockstorage = aextendedblockstorage[i6];

                if (extendedblockstorage != null && extendedblockstorage.b()) {
                    for (int i9 = 0; i9 < 3; ++i9) {
                        this.k = this.k * 3 + 1013904223;
                        i8 = this.k >> 2;
                    int i10 = i8 & 15;
                    int i11 = i8 >> 8 & 15;
                    int i12 = i8 >> 16 & 15;
                    int i13 = extendedblockstorage.a(i10, i12, i11);

                    ++i1;
                    Block block = Block.r[i13];

                    if (block != null && block.s()) {
                        ++i0;
                        block.a(this, i10 + i2, i12 + extendedblockstorage.d(), i11 + i3, this.s);
                    }
                    }
                }
            }

            this.C.b();
        }
    }

    @Override
    public boolean a(int i0, int i1, int i2, int i3) {
        NextTickListEntry nextticklistentry = new NextTickListEntry(i0, i1, i2, i3);

        return this.T.contains(nextticklistentry);
    }

    @Override
    public void a(int i0, int i1, int i2, int i3, int i4) {
        this.a(i0, i1, i2, i3, i4, 0);
    }

    @Override
    public void a(int i0, int i1, int i2, int i3, int i4, int i5) {
        NextTickListEntry nextticklistentry = new NextTickListEntry(i0, i1, i2, i3);
        byte b0 = 0;

        if (this.d && i3 > 0) {
            if (Block.r[i3].l()) {
                if (this.e(nextticklistentry.a - b0, nextticklistentry.b - b0, nextticklistentry.c - b0, nextticklistentry.a + b0, nextticklistentry.b + b0, nextticklistentry.c + b0)) {
                    int i6 = this.a(nextticklistentry.a, nextticklistentry.b, nextticklistentry.c);

                    if (i6 == nextticklistentry.d && i6 > 0) {
                        Block.r[i6].a(this, nextticklistentry.a, nextticklistentry.b, nextticklistentry.c, this.s);
                    }
                }

                return;
            }

            i4 = 1;
        }

        if (this.e(i0 - b0, i1 - b0, i2 - b0, i0 + b0, i1 + b0, i2 + b0)) {
            if (i3 > 0) {
                nextticklistentry.a((long) i4 + this.x.f());
                nextticklistentry.a(i5);
            }

            if (!this.L.contains(nextticklistentry)) {
                this.L.add(nextticklistentry);
                this.M.add(nextticklistentry);
            }
        }
    }

    @Override
    public void b(int i0, int i1, int i2, int i3, int i4, int i5) {
        NextTickListEntry nextticklistentry = new NextTickListEntry(i0, i1, i2, i3);

        nextticklistentry.a(i5);
        if (i3 > 0) {
            nextticklistentry.a((long) i4 + this.x.f());
        }

        if (!this.L.contains(nextticklistentry)) {
            this.L.add(nextticklistentry);
            this.M.add(nextticklistentry);
        }
    }

    @Override
    public void h() {
        if (this.h.isEmpty()) {
            if (this.O++ >= 1200) {
                return;
            }
        } else {
            this.i();
        }

        super.h();
    }

    public void i() {
        this.O = 0;
    }

    @Override
    public boolean a(boolean flag0) {
        int i0 = this.M.size();

        if (i0 != this.L.size()) {
            throw new IllegalStateException("TickNextTick list out of synch");
        } else {
            if (i0 > 1000) {
                i0 = 1000;
            }

            this.C.a("cleaning");

            NextTickListEntry nextticklistentry;

            for (int i1 = 0; i1 < i0; ++i1) {
                nextticklistentry = (NextTickListEntry) this.M.first();
                if (!flag0 && nextticklistentry.e > this.x.f()) {
                    break;
                }

                this.M.remove(nextticklistentry);
                this.L.remove(nextticklistentry);
                this.T.add(nextticklistentry);
            }

            this.C.b();
            this.C.a("ticking");
            Iterator iterator = this.T.iterator();

            while (iterator.hasNext()) {
                nextticklistentry = (NextTickListEntry) iterator.next();
                iterator.remove();
                byte b0 = 0;

                if (this.e(nextticklistentry.a - b0, nextticklistentry.b - b0, nextticklistentry.c - b0, nextticklistentry.a + b0, nextticklistentry.b + b0, nextticklistentry.c + b0)) {
                    int i2 = this.a(nextticklistentry.a, nextticklistentry.b, nextticklistentry.c);

                    if (i2 > 0 && Block.b(i2, nextticklistentry.d)) {
                        try {
                            Block.r[i2].a(this, nextticklistentry.a, nextticklistentry.b, nextticklistentry.c, this.s);
                        } catch (Throwable throwable) {
                            CrashReport crashreport = CrashReport.a(throwable, "Exception while ticking a block");
                            CrashReportCategory crashreportcategory = crashreport.a("Block being ticked");

                            int i3;

                            try {
                                i3 = this.h(nextticklistentry.a, nextticklistentry.b, nextticklistentry.c);
                            } catch (Throwable throwable1) {
                                i3 = -1;
                            }

                            CrashReportCategory.a(crashreportcategory, nextticklistentry.a, nextticklistentry.b, nextticklistentry.c, i2, i3);
                            throw new ReportedException(crashreport);
                        }
                    }
                } else {
                    this.a(nextticklistentry.a, nextticklistentry.b, nextticklistentry.c, nextticklistentry.d, 0);
                }
            }

            this.C.b();
            this.T.clear();
            return !this.M.isEmpty();
        }
    }

    @Override
    public List a(Chunk chunk, boolean flag0) {
        ArrayList arraylist = null;
        ChunkCoordIntPair chunkcoordintpair = chunk.l();
        int i0 = (chunkcoordintpair.a << 4) - 2;
        int i1 = i0 + 16 + 2;
        int i2 = (chunkcoordintpair.b << 4) - 2;
        int i3 = i2 + 16 + 2;

        for (int i4 = 0; i4 < 2; ++i4) {
            Iterator iterator;

            if (i4 == 0) {
                iterator = this.M.iterator();
            } else {
                iterator = this.T.iterator();
                if (!this.T.isEmpty()) {
                    System.out.println(this.T.size());
                }
            }

            while (iterator.hasNext()) {
                NextTickListEntry nextticklistentry = (NextTickListEntry) iterator.next();

                if (nextticklistentry.a >= i0 && nextticklistentry.a < i1 && nextticklistentry.c >= i2 && nextticklistentry.c < i3) {
                    if (flag0) {
                        this.L.remove(nextticklistentry);
                        iterator.remove();
                    }

                    if (arraylist == null) {
                        arraylist = new ArrayList();
                    }

                    arraylist.add(nextticklistentry);
                }
            }
        }

        return arraylist;
    }

    @Override
    public void a(Entity entity, boolean flag0) {
        if (!this.a.V() && (entity instanceof EntityAnimal || entity instanceof EntityWaterMob)) {
            entity.w();
        }

        if (!this.a.W() && entity instanceof INpc) {
            entity.w();
        }

        if (!(entity.n instanceof EntityPlayer)) {
            super.a(entity, flag0);
        }
    }

    public void b(Entity entity, boolean flag0) {
        super.a(entity, flag0);
    }

    @Override
    protected IChunkProvider j() {
        IChunkLoader ichunkloader = this.w.a(this.t);

        this.b = new ChunkProviderServer(this, ichunkloader, this.t.c());
        return this.b;
    }

    public List c(int i0, int i1, int i2, int i3, int i4, int i5) {
        ArrayList arraylist = new ArrayList();

        for (int i6 = 0; i6 < this.g.size(); ++i6) {
            TileEntity tileentity = (TileEntity) this.g.get(i6);

            if (tileentity.l >= i0 && tileentity.m >= i1 && tileentity.n >= i2 && tileentity.l < i3 && tileentity.m < i4 && tileentity.n < i5) {
                arraylist.add(tileentity);
            }
        }

        return arraylist;
    }

    @Override
    public boolean a(EntityPlayer entityplayer, int i0, int i1, int i2) {
        return !this.a.a(this, i0, i1, i2, entityplayer);
    }

    @Override
    protected void a(WorldSettings worldsettings) {
        if (this.U == null) {
            this.U = new IntHashMap();
        }

        if (this.L == null) {
            this.L = new HashSet();
        }

        if (this.M == null) {
            this.M = new TreeSet();
        }

        this.b(worldsettings);
        super.a(worldsettings);
    }

    protected void b(WorldSettings worldsettings) {
        if (!this.t.e()) {
            this.x.a(0, this.t.i(), 0);
        } else {
            this.y = true;
            WorldChunkManager worldchunkmanager = this.t.d;
            List list = worldchunkmanager.a();
            Random random = new Random(this.F());
            ChunkPosition chunkposition = worldchunkmanager.a(0, 0, 256, list, random);
            int i0 = 0;
            int i1 = this.t.i();
            int i2 = 0;

            if (chunkposition != null) {
                i0 = chunkposition.a;
                i2 = chunkposition.c;
            } else {
                this.W().b("Unable to find spawn biome");
            }

            int i3 = 0;

            while (!this.t.a(i0, i2)) {
                i0 += random.nextInt(64) - random.nextInt(64);
                i2 += random.nextInt(64) - random.nextInt(64);
                ++i3;
                if (i3 == 1000) {
                    break;
                }
            }

            this.x.a(i0, i1, i2);
            this.y = false;
            if (worldsettings.c()) {
                this.k();
            }
        }
    }

    protected void k() {
        WorldGeneratorBonusChest worldgeneratorbonuschest = new WorldGeneratorBonusChest(S, 10);

        for (int i0 = 0; i0 < 10; ++i0) {
            int i1 = this.x.c() + this.s.nextInt(6) - this.s.nextInt(6);
            int i2 = this.x.e() + this.s.nextInt(6) - this.s.nextInt(6);
            int i3 = this.i(i1, i2) + 1;

            if (worldgeneratorbonuschest.a(this, this.s, i1, i3, i2)) {
                break;
            }
        }
    }

    public ChunkCoordinates l() {
        return this.t.h();
    }

    public void a(boolean flag0, IProgressUpdate iprogressupdate) throws MinecraftException {
        if (this.v.c()) {
            if (iprogressupdate != null) {
                iprogressupdate.a("Saving level");
            }

            this.a();
            if (iprogressupdate != null) {
                iprogressupdate.c("Saving chunks");
            }

            this.v.a(flag0, iprogressupdate);
        }
    }

    protected void a() throws MinecraftException {
        this.E();
        this.w.a(this.x, this.a.ad().q());
        this.z.a();
    }

    @Override
    protected void a(Entity entity) {
        super.a(entity);
        this.U.a(entity.k, entity);
        Entity[] aentity = entity.an();

        if (aentity != null) {
            for (int i0 = 0; i0 < aentity.length; ++i0) {
                this.U.a(aentity[i0].k, aentity[i0]);
            }
        }
    }

    @Override
    protected void b(Entity entity) {
        super.b(entity);
        this.U.d(entity.k);
        Entity[] aentity = entity.an();

        if (aentity != null) {
            for (int i0 = 0; i0 < aentity.length; ++i0) {
                this.U.d(aentity[i0].k);
            }
        }
    }

    @Override
    public Entity a(int i0) {
        return (Entity) this.U.a(i0);
    }

    @Override
    public boolean c(Entity entity) {
        if (super.c(entity)) {
            this.a.ad().a(entity.u, entity.v, entity.w, 512.0D, this.t.h, new Packet71Weather(entity));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void a(Entity entity, byte b0) {
        Packet38EntityStatus packet38entitystatus = new Packet38EntityStatus(entity.k, b0);

        this.p().b(entity, packet38entitystatus);
    }

    @Override
    public Explosion a(Entity entity, double d0, double d1, double d2, float f0, boolean flag0, boolean flag1) {
        Explosion explosion = new Explosion(this, entity, d0, d1, d2, f0);

        explosion.a = flag0;
        explosion.b = flag1;
        explosion.a();
        explosion.a(false);
        if (!flag1) {
            explosion.h.clear();
        }

        Iterator iterator = this.h.iterator();

        while (iterator.hasNext()) {
            EntityPlayer entityplayer = (EntityPlayer) iterator.next();

            if (entityplayer.e(d0, d1, d2) < 4096.0D) {
                ((EntityPlayerMP) entityplayer).a.b(new Packet60Explosion(d0, d1, d2, f0, explosion.h, (Vec3) explosion.b().get(entityplayer)));
            }
        }

        return explosion;
    }

    @Override
    public void d(int i0, int i1, int i2, int i3, int i4, int i5) {
        BlockEventData blockeventdata = new BlockEventData(i0, i1, i2, i3, i4, i5);
        Iterator iterator = this.Q[this.R].iterator();

        BlockEventData blockeventdata1;

        do {
            if (!iterator.hasNext()) {
                this.Q[this.R].add(blockeventdata);
                return;
            }

            blockeventdata1 = (BlockEventData) iterator.next();
        } while (!blockeventdata1.equals(blockeventdata));

    }

    private void Y() {
        while (!this.Q[this.R].isEmpty()) {
            int i0 = this.R;

            this.R ^= 1;
            Iterator iterator = this.Q[i0].iterator();

            while (iterator.hasNext()) {
                BlockEventData blockeventdata = (BlockEventData) iterator.next();

                if (this.a(blockeventdata)) {
                    this.a.ad().a((double) blockeventdata.a(), (double) blockeventdata.b(), (double) blockeventdata.c(), 64.0D, this.t.h, new Packet54PlayNoteBlock(blockeventdata.a(), blockeventdata.b(), blockeventdata.c(), blockeventdata.f(), blockeventdata.d(), blockeventdata.e()));
                }
            }

            this.Q[i0].clear();
        }
    }

    private boolean a(BlockEventData blockeventdata) {
        int i0 = this.a(blockeventdata.a(), blockeventdata.b(), blockeventdata.c());

        return i0 == blockeventdata.f() ? Block.r[i0].b(this, blockeventdata.a(), blockeventdata.b(), blockeventdata.c(), blockeventdata.d(), blockeventdata.e()) : false;
    }

    public void m() {
        this.w.a();
    }

    @Override
    protected void n() {
        boolean flag0 = this.O();

        super.n();
        if (flag0 != this.O()) {
            if (flag0) {
                this.a.ad().a((Packet) (new Packet70GameEvent(2, 0)));
            } else {
                this.a.ad().a((Packet) (new Packet70GameEvent(1, 0)));
            }
        }
    }

    public MinecraftServer o() {
        return this.a;
    }

    public EntityTracker p() {
        return this.J;
    }

    public PlayerManager r() {
        return this.K;
    }

    public Teleporter s() {
        return this.P;
    }

    public CanaryEntityTracker getEntityTracker() {
        return J.getCanaryEntityTracker();
    }

    /**
     * Get the canary player manager wrapper for this dimension
     * 
     * @return
     */
    public CanaryPlayerManager getPlayerManager() {
        return K.getPlayerManager();
    }
}
