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
import net.canarymod.config.Configuration;
import net.canarymod.hook.world.TimeChangeHook;
import net.canarymod.hook.world.WeatherChangeHook;

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
    private final SpawnerAnimals Q = new SpawnerAnimals();
    private ServerBlockEventList[] R = new ServerBlockEventList[]{ new ServerBlockEventList((ServerBlockEvent) null), new ServerBlockEventList((ServerBlockEvent) null) };
    private int S;
    private static final WeightedRandomChestContent[] T = new WeightedRandomChestContent[]{ new WeightedRandomChestContent(Item.F.cv, 0, 1, 3, 10), new WeightedRandomChestContent(Block.C.cF, 0, 1, 3, 10), new WeightedRandomChestContent(Block.O.cF, 0, 1, 3, 10), new WeightedRandomChestContent(Item.A.cv, 0, 1, 1, 3),
            new WeightedRandomChestContent(Item.w.cv, 0, 1, 1, 5), new WeightedRandomChestContent(Item.z.cv, 0, 1, 1, 3), new WeightedRandomChestContent(Item.v.cv, 0, 1, 1, 5), new WeightedRandomChestContent(Item.l.cv, 0, 2, 3, 5), new WeightedRandomChestContent(Item.W.cv, 0, 2, 3, 3) };
    private List U = new ArrayList();
    private IntHashMap V;

    public WorldServer(MinecraftServer minecraftserver, ISaveHandler isavehandler, String s0, int i0, WorldSettings worldsettings, Profiler profiler, ILogAgent ilogagent) {
        // TODO: WorldProvider: Needs changing so it would get any WorldProvider. Might need to make a mapping/register
        super(isavehandler, s0, worldsettings, WorldProvider.a(i0), profiler, ilogagent, net.canarymod.api.world.DimensionType.fromId(i0));
        this.a = minecraftserver;
        this.J = new EntityTracker(this);
        // CanaryMod: Use our view-distance handling
        this.K = new PlayerManager(this, Configuration.getServerConfig().getViewDistance());
        if (this.V == null) {
            this.V = new IntHashMap();
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
        canaryDimension = new CanaryWorld(s0, (WorldServer) this, net.canarymod.api.world.DimensionType.fromId(i0));
    }

    @Override
    public void b() {
        super.b();
        if (this.N().t() && this.r < 3) {
            this.r = 3;
        }

        this.t.e.b();
        if (this.e()) {
            if (this.O().b("doDaylightCycle")) {
                long i0 = this.x.g() + 24000L;

                // CanaryMod: TimeChangeHook
                TimeChangeHook hook = (TimeChangeHook) new TimeChangeHook(getCanaryWorld(), i0 - i0 % 24000L).call();
                if (!hook.isCanceled()) {
                    this.x.c(i0 - i0 % 24000L);
                }
                //
                this.d();
            }
        }

        this.C.a("mobSpawner");
        if (this.O().b("doMobSpawning")) {
            this.Q.a(this, this.E, this.F, this.x.f() % 400L == 0L);
        }

        this.C.c("chunkSource");
        this.v.c();
        int i1 = this.a(1.0F);

        if (i1 != this.j) {
            this.j = i1;
        }

        this.x.b(this.x.f() + 1L);
        if (this.O().b("doDaylightCycle")) {
            // CanaryMod: TimeChangeHook
            TimeChangeHook hook = (TimeChangeHook) new TimeChangeHook(getCanaryWorld(), this.x.g() + 1L).call();
            if (!hook.isCanceled()) {
                this.x.c(this.x.g() + 1L);
            }
            //
        }
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
        this.P.a(this.I());
        this.C.b();
        this.aa();
    }

    public SpawnListEntry a(EnumCreatureType enumcreaturetype, int i0, int i1, int i2) {
        List list = this.L().a(enumcreaturetype, i0, i1, i2);

        return list != null && !list.isEmpty() ? (SpawnListEntry) WeightedRandom.a(this.s, (Collection) list) : null;
    }

    public void c() {
        this.N = !this.h.isEmpty();
        Iterator iterator = this.h.iterator();

        while (iterator.hasNext()) {
            EntityPlayer entityplayer = (EntityPlayer) iterator.next();

            if (!entityplayer.bd()) {
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

            if (entityplayer.bd()) {
                entityplayer.a(false, false, true);
            }
        }

        this.Z();
    }

    private void Z() {
        // CanaryMod: WeatherChange
        WeatherChangeHook hook = (WeatherChangeHook) new WeatherChangeHook(getCanaryWorld(), false, false).call();
        if (!hook.isCanceled()) {
            this.x.g(0);
            this.x.b(false);
        }
        hook = (WeatherChangeHook) new WeatherChangeHook(getCanaryWorld(), false, true).call();
        if (!hook.isCanceled()) {
            this.x.f(0);
            this.x.a(false);
        }
        //
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
            }
            while (entityplayer.by());

            return false;
        } else {
            return false;
        }
    }

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

            if (this.s.nextInt(100000) == 0 && this.Q() && this.P()) {
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
                    this.c(i5 + i2, i7 - 1, i6 + i3, Block.aY.cF);
                }

                if (this.Q() && this.z(i5 + i2, i7, i6 + i3)) {
                    this.c(i5 + i2, i7, i6 + i3, Block.aX.cF);
                }

                if (this.Q()) {
                    BiomeGenBase biomegenbase = this.a(i5 + i2, i6 + i3);

                    if (biomegenbase.d()) {
                        i8 = this.a(i5 + i2, i7 - 1, i6 + i3);
                        if (i8 != 0) {
                            Block.s[i8].g(this, i5 + i2, i7 - 1, i6 + i3);
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
                        Block block = Block.s[i13];

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

    public boolean a(int i0, int i1, int i2, int i3) {
        NextTickListEntry nextticklistentry = new NextTickListEntry(i0, i1, i2, i3);

        return this.U.contains(nextticklistentry);
    }

    public void a(int i0, int i1, int i2, int i3, int i4) {
        this.a(i0, i1, i2, i3, i4, 0);
    }

    public void a(int i0, int i1, int i2, int i3, int i4, int i5) {
        NextTickListEntry nextticklistentry = new NextTickListEntry(i0, i1, i2, i3);
        byte b0 = 0;

        if (this.d && i3 > 0) {
            if (Block.s[i3].l()) {
                b0 = 8;
                if (this.e(nextticklistentry.a - b0, nextticklistentry.b - b0, nextticklistentry.c - b0, nextticklistentry.a + b0, nextticklistentry.b + b0, nextticklistentry.c + b0)) {
                    int i6 = this.a(nextticklistentry.a, nextticklistentry.b, nextticklistentry.c);

                    if (i6 == nextticklistentry.d && i6 > 0) {
                        Block.s[i6].a(this, nextticklistentry.a, nextticklistentry.b, nextticklistentry.c, this.s);
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
                this.U.add(nextticklistentry);
            }

            this.C.b();
            this.C.a("ticking");
            Iterator iterator = this.U.iterator();

            while (iterator.hasNext()) {
                nextticklistentry = (NextTickListEntry) iterator.next();
                iterator.remove();
                byte b0 = 0;

                if (this.e(nextticklistentry.a - b0, nextticklistentry.b - b0, nextticklistentry.c - b0, nextticklistentry.a + b0, nextticklistentry.b + b0, nextticklistentry.c + b0)) {
                    int i2 = this.a(nextticklistentry.a, nextticklistentry.b, nextticklistentry.c);

                    if (i2 > 0 && Block.b(i2, nextticklistentry.d)) {
                        try {
                            Block.s[i2].a(this, nextticklistentry.a, nextticklistentry.b, nextticklistentry.c, this.s);
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
            this.U.clear();
            return !this.M.isEmpty();
        }
    }

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
                iterator = this.U.iterator();
                if (!this.U.isEmpty()) {
                    System.out.println(this.U.size());
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

    public void a(Entity entity, boolean flag0) {
        /* CanaryMod: Spawning checks per world, see World#canSpawn */
        if (!canSpawn(entity)) {
            entity.w();
        }
        if (!(entity.n instanceof EntityPlayer)) {
            super.a(entity, flag0);
        }
    }

    public void b(Entity entity, boolean flag0) {
        try {
            super.a(entity, flag0);
        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.a(throwable, "Forcefully ticking entity");
            CrashReportCategory crashreportcategory = crashreport.a("Entity being force ticked");

            entity.a(crashreportcategory);
            throw new ReportedException(crashreport);
        }
    }

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

    public boolean a(EntityPlayer entityplayer, int i0, int i1, int i2) {
        return !this.a.a(this, i0, i1, i2, entityplayer);
    }

    protected void a(WorldSettings worldsettings) {
        if (this.V == null) {
            this.V = new IntHashMap();
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
            WorldChunkManager worldchunkmanager = this.t.e;
            List list = worldchunkmanager.a();
            Random random = new Random(this.H());
            ChunkPosition chunkposition = worldchunkmanager.a(0, 0, 256, list, random);
            int i0 = 0;
            int i1 = this.t.i();
            int i2 = 0;

            if (chunkposition != null) {
                i0 = chunkposition.a;
                i2 = chunkposition.c;
            } else {
                this.Y().b("Unable to find spawn biome");
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
        WorldGeneratorBonusChest worldgeneratorbonuschest = new WorldGeneratorBonusChest(T, 10);

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
        // CanaryMod assume every world is able to save
        if (this.v.d()) {
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

    public void m() {
        if (this.v.d()) {
            this.v.b();
        }
    }

    protected void a() throws MinecraftException {
        this.G();
        this.w.a(this.x, this.a.af().q());
        this.z.a();
    }

    protected void a(Entity entity) {
        super.a(entity);
        this.V.a(entity.k, entity);
        Entity[] aentity = entity.am();

        if (aentity != null) {
            for (int i0 = 0; i0 < aentity.length; ++i0) {
                this.V.a(aentity[i0].k, aentity[i0]);
            }
        }
    }

    protected void b(Entity entity) {
        super.b(entity);
        this.V.d(entity.k);
        Entity[] aentity = entity.am();

        if (aentity != null) {
            for (int i0 = 0; i0 < aentity.length; ++i0) {
                this.V.d(aentity[i0].k);
            }
        }
    }

    public Entity a(int i0) {
        return (Entity) this.V.a(i0);
    }

    public boolean c(Entity entity) {
        if (super.c(entity)) {
            this.a.af().a(entity.u, entity.v, entity.w, 512.0D, this.t.i, new Packet71Weather(entity));
            return true;
        } else {
            return false;
        }
    }

    public void a(Entity entity, byte b0) {
        Packet38EntityStatus packet38entitystatus = new Packet38EntityStatus(entity.k, b0);

        this.q().b(entity, packet38entitystatus);
    }

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

    public void d(int i0, int i1, int i2, int i3, int i4, int i5) {
        BlockEventData blockeventdata = new BlockEventData(i0, i1, i2, i3, i4, i5);
        Iterator iterator = this.R[this.S].iterator();

        BlockEventData blockeventdata1;

        do {
            if (!iterator.hasNext()) {
                this.R[this.S].add(blockeventdata);
                return;
            }

            blockeventdata1 = (BlockEventData) iterator.next();
        }
        while (!blockeventdata1.equals(blockeventdata));

    }

    private void aa() {
        while (!this.R[this.S].isEmpty()) {
            int i0 = this.S;

            this.S ^= 1;
            Iterator iterator = this.R[i0].iterator();

            while (iterator.hasNext()) {
                BlockEventData blockeventdata = (BlockEventData) iterator.next();

                if (this.a(blockeventdata)) {
                    this.a.af().a((double) blockeventdata.a(), (double) blockeventdata.b(), (double) blockeventdata.c(), 64.0D, this.t.i, new Packet54PlayNoteBlock(blockeventdata.a(), blockeventdata.b(), blockeventdata.c(), blockeventdata.f(), blockeventdata.d(), blockeventdata.e()));
                }
            }

            this.R[i0].clear();
        }
    }

    private boolean a(BlockEventData blockeventdata) {
        int i0 = this.a(blockeventdata.a(), blockeventdata.b(), blockeventdata.c());

        return i0 == blockeventdata.f() ? Block.s[i0].b(this, blockeventdata.a(), blockeventdata.b(), blockeventdata.c(), blockeventdata.d(), blockeventdata.e()) : false;
    }

    public void n() {
        this.w.a();
    }

    protected void o() {
        boolean flag0 = this.Q();

        super.o();
        if (flag0 != this.Q()) {
            if (flag0) {
                this.a.af().a((Packet) (new Packet70GameEvent(2, 0)));
            } else {
                this.a.af().a((Packet) (new Packet70GameEvent(1, 0)));
            }
        }
    }

    public MinecraftServer p() {
        return this.a;
    }

    public EntityTracker q() {
        return this.J;
    }

    public PlayerManager s() {
        return this.K;
    }

    public Teleporter t() {
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
