package net.minecraft.server;


import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.security.KeyPair;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.canarymod.Canary;
import net.canarymod.api.CanaryConfigurationManager;
import net.canarymod.api.CanaryServer;
import net.canarymod.api.world.CanarySaveConverter;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.CanaryWorldManager;
import net.canarymod.config.Configuration;
import net.canarymod.config.WorldConfiguration;
import net.canarymod.tasks.ServerTaskManager;
import net.visualillusionsent.utils.PropertiesFile;


public abstract class MinecraftServer implements ICommandSender, Runnable, IPlayerUsage {

    private static MinecraftServer k = null;
    private final ISaveFormat l;
    private final PlayerUsageSnooper m = new PlayerUsageSnooper("server", this);
    private final File n;
    private final List o = new ArrayList();
    private final ICommandManager p;
    public final Profiler a = new Profiler();
    private String q;
    private int r = -1;
    // public WorldServer[] b; // XXX
    public ServerConfigurationManager s; // CanaryMod private -> public
    private boolean t = true;
    private boolean u = false;
    private int v = 0;
    public String c;
    public int d;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;
    private boolean A;
    private String B;
    private int C;
    private long D;
    private long E;
    private long F;
    private long G;
    public final long[] e = new long[100];
    public final long[] f = new long[100];
    public final long[] g = new long[100];
    public final long[] h = new long[100];
    public final long[] i = new long[100];
    // public long[][] j; //XXX
    private KeyPair H;
    private String I;
    private String J;
    private boolean L;
    private boolean M;
    private boolean N; // isRunning
    private String O = "";
    private boolean P = false;
    private long Q;
    private String R;
    private boolean S;
    private boolean T = false;

    // CanaryMod start: Multiworld \o/
    public CanaryWorldManager worldManager = new CanaryWorldManager();
    private CanaryServer server;

    // CanaryMod start: Stop Message
    private String stopMsg;
    //

    public MinecraftServer(File file1) {
        k = this;
        this.n = file1;
        this.p = new ServerCommandManager();
        this.l = new AnvilSaveConverter(file1, net.canarymod.api.world.DimensionType.fromName("NORMAL"));
        this.an();
        // CanaryMod
        this.server = new CanaryServer(this);
        Canary.setServer(server);
        //
    }

    private void an() {
        DispenserBehaviors.a();
    }

    protected abstract boolean c() throws IOException;

    protected void b(String s0) {
        if (this.N().b(s0)) {
            this.al().a("Converting map!");
            this.c("menu.convertingLevel");
            this.N().a(s0, new ConvertingProgressUpdate(this));
        }
    }

    protected synchronized void c(String s0) {
        this.R = s0;
    }

    // Used to initialize the "master" worlds
    protected void initWorld(String name, long seed, WorldType nmsWt, net.canarymod.api.world.DimensionType worldtype, String generatorSettings) {
        this.b(name); // Anvil Converter
        this.c("menu.loadingLevel");
        File worldFolder = new File("worlds/" + name);
        CanarySaveConverter converter = new CanarySaveConverter(worldFolder);

        if (converter.isVanillaFormat()) {
            Canary.logInfo("World " + name + " is Vanilla. Will now attempt to convert.");
            converter.convert();
        }
        AnvilSaveHandler isavehandler = new AnvilSaveHandler(new File("worlds/"), name, true, worldtype);
        WorldInfo worldinfo = isavehandler.d();
        WorldConfiguration config = Configuration.getWorldConfig(name + "_" + worldtype.getName());

        WorldSettings worldsettings;
        WorldServer world;

        if (worldinfo == null) {
            worldsettings = new WorldSettings(seed, EnumGameType.a(config.getGameMode().getId()), config.generatesStructures(), false, nmsWt);
            PropertiesFile worldRaw = config.getFile();
            worldRaw.setString("world-seed", String.valueOf(seed));
            worldRaw.setInt("gamemode", 0);
            worldRaw.save();
            // CanaryMod those are flatworld settings, and they are likely unset
            if (generatorSettings != null) {
                worldsettings.a(generatorSettings);
            } else {
                worldsettings.a("");
            }
            //
        } else {
            worldsettings = new WorldSettings(worldinfo);
        }

        if (this.M) {
            worldsettings.a();
        }

        if (worldtype.getId() == 0) {
            if (this.M()) {
                world = new DemoWorldServer(this, isavehandler, name, worldtype.getId(), this.a, this.al());
            } else {
                world = new WorldServer(this, isavehandler, name, worldtype.getId(), worldsettings, this.a, this.al());
            }
        } else {
            world = new WorldServerMulti(this, isavehandler, name, worldtype.getId(), worldsettings, (WorldServer) ((CanaryWorld) worldManager.getWorld(name, net.canarymod.api.world.DimensionType.fromName("NORMAL"), true)).getHandle(), this.a, this.al());
        }

        world.a((IWorldAccess) (new WorldManager(this, world)));
        if (!this.I()) {
            world.M().a(EnumGameType.a(config.getGameMode().getId())); //Use per-world config for game mode
        }

        this.s.a(world); // Init player data files

        // this.c(this.h()); // If we call this then worlds can't do custom difficulty, plus it doesn't work
        world.r = config.getDifficulty().getId(); // Set difficulty directly based on WorldConfiguration setting
        this.e(world); // Generate terrain
        worldManager.addWorld(world.getCanaryWorld());
    }

    protected void e(WorldServer worldserver) {
        int i0 = 0;

        this.c("menu.generatingTerrain");

        this.al().a("Preparing start region for level " + worldserver.getCanaryWorld().getName());
        ChunkCoordinates chunkcoordinates = worldserver.J();
        long i1 = System.currentTimeMillis();

        for (int i2 = -192; i2 <= 192 && this.m(); i2 += 16) {
            for (int i3 = -192; i3 <= 192 && this.m(); i3 += 16) {
                long i4 = System.currentTimeMillis();

                if (i4 - i1 > 1000L) {
                    this.a_("Preparing spawn area", i0 * 100 / 625);
                    i1 = i4;
                }

                ++i0;
                worldserver.b.c(chunkcoordinates.a + i2 >> 4, chunkcoordinates.c + i3 >> 4);
            }
        }

        this.j();
    }

    public abstract boolean f();

    public abstract EnumGameType g();

    public abstract int h();

    public abstract boolean i();

    protected void a_(String s0, int i0) {
        this.c = s0;
        this.d = i0;
        this.al().a(s0 + ": " + i0 + "%");
    }

    protected void j() {
        this.c = null;
        this.d = 0;
    }

    protected void a(boolean flag0) {
        if (!this.N) {
            // CanaryMod changed to use worldManager
            for (net.canarymod.api.world.World w : worldManager.getAllWorlds()) {
                WorldServer worldserver = (WorldServer) ((CanaryWorld) w).getHandle();

                if (worldserver != null) {
                    if (!flag0) {
                        this.al().a("Saving chunks for level \'" + worldserver.M().k() + "\'/" + worldserver.t.l());
                    }

                    try {
                        worldserver.a(true, (IProgressUpdate) null);
                    } catch (MinecraftException minecraftexception) {
                        Canary.println(minecraftexception.getMessage());
                        this.al().b(minecraftexception.getMessage());
                    }
                } else {
                    Canary.println("World is null");
                }
            }
        }
    }

    public void k() {
        if (!this.N) {
            this.al().a("Stopping server");
            if (this.ae() != null) {
                this.ae().a();
            }

            if (this.s != null) {
                this.al().a("Saving players");
                this.s.g();
                this.s.r();
            }

            this.al().a("Saving worlds");
            this.a(false);

            // CanaryMod Multiworld
            for (net.canarymod.api.world.World w : worldManager.getAllWorlds()) {
                WorldServer worldserver = (WorldServer) ((CanaryWorld) w).getHandle();

                worldserver.n();
            }

            if (this.m != null && this.m.d()) {
                this.m.e();
            }
            // CanaryMod disable plugins:
            Canary.logInfo("Disabling Plugins ...");
            Canary.loader().disableAllPlugins();
        }
    }

    public String l() {
        return this.q;
    }

    public void d(String s0) {
        this.q = s0;
    }

    public boolean m() {
        return this.t;
    }

    public void n() {
        this.t = false;
    }

    @Override
    public void run() {
        try {
            if (this.c()) {
                long i0 = System.currentTimeMillis();

                for (long i1 = 0L; this.t; this.P = true) {
                    ServerTaskManager.runTasks(); // CanaryMod: Run tasks
                    long i2 = System.currentTimeMillis();
                    long i3 = i2 - i0;

                    if (i3 > 2000L && i0 - this.Q >= 15000L) {
                        this.al().b("Can\'t keep up! Did the system time change, or is the server overloaded?");
                        i3 = 2000L;
                        this.Q = i0;
                    }

                    if (i3 < 0L) {
                        this.al().b("Time ran backwards! Did the system time change?");
                        i3 = 0L;
                    }

                    i1 += i3;
                    i0 = i2;
                    // CanaryMod start: multiworld sleeping
                    boolean allSleeping = true;

                    for (net.canarymod.api.world.World w : worldManager.getAllWorlds()) {
                        allSleeping &= ((WorldServer) ((CanaryWorld) w).getHandle()).e();
                    }
                    // CanaryMod end
                    if (allSleeping) {
                        this.q();
                        i1 = 0L;
                    } else {
                        while (i1 > 50L) {
                            i1 -= 50L;
                            this.q();
                        }
                    }

                    Thread.sleep(1L);
                }
            } else {
                this.a((CrashReport) null);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            this.al().c("Encountered an unexpected exception " + throwable.getClass().getSimpleName(), throwable);
            CrashReport crashreport = null;

            if (throwable instanceof ReportedException) {
                crashreport = this.b(((ReportedException) throwable).a());
            } else {
                crashreport = this.b(new CrashReport("Exception in server tick loop", throwable));
            }

            File file1 = new File(new File(this.o(), "crash-reports"), "crash-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + "-server.txt");

            if (crashreport.a(file1, this.al())) {
                this.al().c("This crash report has been saved to: " + file1.getAbsolutePath());
            } else {
                this.al().c("We were unable to save this crash report to disk.");
            }

            this.a(crashreport);
        } finally {
            try {
                this.k();
                this.u = true;
            } catch (Throwable throwable1) {
                throwable1.printStackTrace();
            } finally {
                this.p();
            }
        }
    }

    protected File o() {
        return new File(".");
    }

    protected void a(CrashReport crashreport) {}

    protected void p() {}

    protected void q() {
        long i0 = System.nanoTime();

        AxisAlignedBB.a().a();
        ++this.v;
        if (this.S) {
            this.S = false;
            this.a.a = true;
            this.a.a();
        }

        this.a.a("root");
        this.r();
        if (this.v % 900 == 0) {
            this.a.a("save");
            this.s.g();
            this.a(true);
            this.a.b();
        }

        this.a.a("tallying");
        this.i[this.v % 100] = System.nanoTime() - i0;
        this.e[this.v % 100] = Packet.q - this.D;
        this.D = Packet.q;
        this.f[this.v % 100] = Packet.r - this.E;
        this.E = Packet.r;
        this.g[this.v % 100] = Packet.o - this.F;
        this.F = Packet.o;
        this.h[this.v % 100] = Packet.p - this.G;
        this.G = Packet.p;
        this.a.b();
        this.a.a("snooper");
        if (!this.m.d() && this.v > 100) {
            this.m.a();
        }

        if (this.v % 6000 == 0) {
            this.m.b();
        }

        this.a.b();
        this.a.b();
    }

    //CanaryMod: ticks world
    public void r() {
        this.a.a("levels");

        int i0;

        // CanaryMod use worldManager instead
        for (net.canarymod.api.world.World w : worldManager.getAllWorlds()) {
            WorldServer worldserver = (WorldServer) ((CanaryWorld) w).getHandle();
            //
            long i1 = System.nanoTime();

            this.a.a(worldserver.M().k());
            this.a.a("pools");
            worldserver.U().a();
            this.a.b();
            if (this.v % 20 == 0) {
                this.a.a("timeSync");
                // this.s.a((Packet) (new Packet4UpdateTime(worldserver.G(), worldserver.H())), worldserver.t.h);
                this.s.sendPacketToDimension((new Packet4UpdateTime(worldserver.H(), worldserver.I())), w.getName(), w.getType().getId());
                this.a.b();
            }

            this.a.a("tick");

            CrashReport crashreport;

            try {
                worldserver.b();
            } catch (Throwable throwable) {
                crashreport = CrashReport.a(throwable, "Exception ticking world");
                worldserver.a(crashreport);
                throw new ReportedException(crashreport);
            }

            try {
                worldserver.h();
            } catch (Throwable throwable1) {
                crashreport = CrashReport.a(throwable1, "Exception ticking world entities");
                worldserver.a(crashreport);
                throw new ReportedException(crashreport);
            }

            this.a.b();
            this.a.a("tracker");
            worldserver.q().a();
            this.a.b();
            this.a.b();
            w.setNanoTick(this.v % 100, System.nanoTime() - i1);
            // this.j[i0][this.v % 100] = System.nanoTime() - i1;
        }

        this.a.c("connection");
        this.ae().b();
        this.a.c("players");
        this.s.b();
        this.a.c("tickables");

        for (i0 = 0; i0 < this.o.size(); ++i0) {
            ((IUpdatePlayerListBox) this.o.get(i0)).a();
        }

        this.a.b();
    }

    public boolean s() {
        throw new UnsupportedOperationException("allow-nether has been moved to a per-world configuration!");
    }

    public void a(IUpdatePlayerListBox iupdateplayerlistbox) {
        this.o.add(iupdateplayerlistbox);
    }

    public static void main(String[] astring) {
        StatList.a();
        ILogAgent ilogagent = null;

        try {
            boolean flag0 = !GraphicsEnvironment.isHeadless();
            String s0 = null;
            String s1 = ".";
            String s2 = null;
            boolean flag1 = false;
            boolean flag2 = false;
            int i0 = -1;

            for (int i1 = 0; i1 < astring.length; ++i1) {
                String s3 = astring[i1];
                String s4 = i1 == astring.length - 1 ? null : astring[i1 + 1];
                boolean flag3 = false;

                if (!s3.equals("nogui") && !s3.equals("--nogui")) {
                    if (s3.equals("--port") && s4 != null) {
                        flag3 = true;

                        try {
                            i0 = Integer.parseInt(s4);
                        } catch (NumberFormatException numberformatexception) {
                            ;
                        }
                    } else if (s3.equals("--singleplayer") && s4 != null) {
                        flag3 = true;
                        s0 = s4;
                    } else if (s3.equals("--universe") && s4 != null) {
                        flag3 = true;
                        s1 = s4;
                    } else if (s3.equals("--world") && s4 != null) {
                        flag3 = true;
                        s2 = s4;
                    } else if (s3.equals("--demo")) {
                        flag1 = true;
                    } else if (s3.equals("--bonusChest")) {
                        flag2 = true;
                    }
                } else {
                    flag0 = false;
                }

                if (flag3) {
                    ++i1;
                }
            }

            DedicatedServer dedicatedserver = new DedicatedServer(new File(s1));

            ilogagent = dedicatedserver.al();
            if (s0 != null) {
                dedicatedserver.k(s0);
            }

            if (s2 != null) {
                dedicatedserver.l(s2);
            }

            if (i0 >= 0) {
                dedicatedserver.b(i0);
            }

            if (flag1) {
                dedicatedserver.b(true);
            }

            if (flag2) {
                dedicatedserver.c(true);
            }

            if (flag0) {
                dedicatedserver.ap();
            }

            dedicatedserver.t();
            Runtime.getRuntime().addShutdownHook(new ThreadDedicatedServer(dedicatedserver));
        } catch (Exception exception) {
            if (ilogagent != null) {
                ilogagent.c("Failed to start the minecraft server", exception);
            } else {
                Logger.getAnonymousLogger().log(Level.SEVERE, "Failed to start the minecraft server", exception);
            }
        }
    }

    public void t() {
        (new ThreadMinecraftServer(this, "Server thread")).start();
    }

    public File e(String s0) {
        return new File(this.o(), s0);
    }

    public void f(String s0) {
        this.al().a(s0);
    }

    public void g(String s0) {
        this.al().b(s0);
    }

    @Deprecated
    public WorldServer a(int i) {
        throw new UnsupportedOperationException("MinecraftServer.a(int) has" + " been replaced by MinecraftServer.getWorld(String, int).");
    }

    public WorldServer getWorld(String s, int i) {
        net.canarymod.api.world.World w = worldManager.getWorld(s, net.canarymod.api.world.DimensionType.fromId(i), true);

        if (w != null) {
            return (WorldServer) ((CanaryWorld) w).getHandle();
        }
        return null;
    }

    public String u() {
        return this.q;
    }

    public int v() {
        return this.r;
    }

    public String w() {
        return this.B;
    }

    public String x() {
        return "1.5.2";
    }

    public int y() {
        return this.s.k();
    }

    public int z() {
        return this.s.l();
    }

    public String[] A() {
        return this.s.d();
    }

    public String B() {
        return "";
    }

    public String h(String s0) {
        RConConsoleSource.a.c();
        this.p.a(RConConsoleSource.a, s0);
        return RConConsoleSource.a.d();
    }

    public boolean C() {
        return false;
    }

    public void i(String s0) {
        this.al().c(s0);
    }

    public void j(String s0) {
        if (this.C()) {
            this.al().a(s0);
        }
    }

    public String getServerModName() {
        return "CanaryMod";
    }

    public CrashReport b(CrashReport crashreport) {
        crashreport.g().a("Profiler Position", (Callable) (new CallableIsServerModded(this)));
        // if (this.b != null && this.b.length > 0 && this.b[0] != null) {
        if (worldManager != null && worldManager.getAllWorlds().size() > 0) {
            crashreport.g().a("Vec3 Pool Size", (Callable) (new CallableServerProfiler(this)));
        }

        if (this.s != null) {
            crashreport.g().a("Player Count", (Callable) (new CallableServerMemoryStats(this)));
        }

        return crashreport;
    }

    public List a(ICommandSender icommandsender, String s0) {
        ArrayList arraylist = new ArrayList();

        if (s0.startsWith("/")) {
            s0 = s0.substring(1);
            boolean flag0 = !s0.contains(" ");
            List list = this.p.b(icommandsender, s0);

            if (list != null) {
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    String s1 = (String) iterator.next();

                    if (flag0) {
                        arraylist.add("/" + s1);
                    } else {
                        arraylist.add(s1);
                    }
                }
            }

            return arraylist;
        } else {
            String[] astring = s0.split(" ", -1);
            String s2 = astring[astring.length - 1];
            String[] astring1 = this.s.d();
            int i0 = astring1.length;

            for (int i1 = 0; i1 < i0; ++i1) {
                String s3 = astring1[i1];

                if (CommandBase.a(s2, s3)) {
                    arraylist.add(s3);
                }
            }

            return arraylist;
        }
    }

    public static MinecraftServer D() {
        return k;
    }

    @Override
    public String c_() {
        return "Server";
    }

    @Override
    public void a(String s0) {
        this.al().a(StringUtils.a(s0));
    }

    @Override
    public boolean a(int i0, String s0) {
        return true;
    }

    @Override
    public String a(String s0, Object... aobject) {
        return StringTranslate.a().a(s0, aobject);
    }

    public ICommandManager E() {
        return this.p;
    }

    public KeyPair F() {
        return this.H;
    }

    public int G() {
        return this.r;
    }

    public void b(int i0) {
        this.r = i0;
    }

    public String H() {
        return this.I;
    }

    public void k(String s0) {
        this.I = s0;
    }

    public boolean I() {
        return this.I != null;
    }

    /**
     * Level name
     * @return
     */
    public String J() {
        return this.J;
    }

    public void l(String s0) {
        this.J = s0;
    }

    public void a(KeyPair keypair) {
        this.H = keypair;
    }

    public void c(int i0) {
        // CanaryMod changes for Multiworld
        for (net.canarymod.api.world.World w : worldManager.getAllWorlds()) {
            WorldServer worldserver = (WorldServer) ((CanaryWorld) w).getHandle();

            //
            if (worldserver != null) {
                //System.out.println(worldserver.getCanaryWorld().getName() + " Difficulty " + i0);
                if (worldserver.M().t()) {
                    worldserver.r = 3;
                    worldserver.a(true, true);
                } else if (this.I()) {
                    worldserver.r = i0;
                    worldserver.a(worldserver.r > 0, true);
                } else {
                    worldserver.r = i0;
                    //Canarymod moved spawn-monsters to per-world config
                    worldserver.a(Configuration.getWorldConfig(w.getFqName()).canSpawnMonsters(), this.x);
                }
            }
        }
    }

    protected boolean L() {
        return true;
    }

    public boolean M() {
        return this.L;
    }

    public void b(boolean flag0) {
        this.L = flag0;
    }

    public void c(boolean flag0) {
        this.M = flag0;
    }

    public ISaveFormat N() {
        return this.l;
    }

    public void P() {
        // CanaryMod XXX: Remove this? It'll delete all worlds
        this.N = true;
        this.N().d();

        for (net.canarymod.api.world.World w : worldManager.getAllWorlds()) {
            WorldServer worldserver = (WorldServer) ((CanaryWorld) w).getHandle();

            if (worldserver != null) {
                worldserver.n();
            }
            if (w.getType().getId() == 0) {
                this.N().e(worldserver.L().g());
            }
        }
        this.n();
    }

    public String Q() {
        return this.O;
    }

    public void n(String s0) {
        this.O = s0;
    }

    @Override
    public void a(PlayerUsageSnooper playerusagesnooper) {
        playerusagesnooper.a("whitelist_enabled", Boolean.valueOf(false));
        playerusagesnooper.a("whitelist_count", Integer.valueOf(0));
        playerusagesnooper.a("players_current", Integer.valueOf(this.y()));
        playerusagesnooper.a("players_max", Integer.valueOf(this.z()));
        playerusagesnooper.a("players_seen", Integer.valueOf(this.s.m().length));
        playerusagesnooper.a("uses_auth", Boolean.valueOf(this.w));
        playerusagesnooper.a("gui_state", this.ag() ? "enabled" : "disabled");
        playerusagesnooper.a("avg_tick_ms", Integer.valueOf((int) (MathHelper.a(this.i) * 1.0E-6D)));
        playerusagesnooper.a("avg_sent_packet_count", Integer.valueOf((int) MathHelper.a(this.e)));
        playerusagesnooper.a("avg_sent_packet_size", Integer.valueOf((int) MathHelper.a(this.f)));
        playerusagesnooper.a("avg_rec_packet_count", Integer.valueOf((int) MathHelper.a(this.g)));
        playerusagesnooper.a("avg_rec_packet_size", Integer.valueOf((int) MathHelper.a(this.h)));
        int i0 = 0;

        for (net.canarymod.api.world.World w : worldManager.getAllWorlds()) {
            WorldServer worldserver = (WorldServer) ((CanaryWorld) w).getHandle();
            WorldInfo worldinfo = worldserver.M();

            playerusagesnooper.a("world[" + i0 + "][dimension]", Integer.valueOf(worldserver.t.h));
            playerusagesnooper.a("world[" + i0 + "][mode]", worldinfo.r());
            playerusagesnooper.a("world[" + i0 + "][difficulty]", Integer.valueOf(worldserver.r));
            playerusagesnooper.a("world[" + i0 + "][hardcore]", Boolean.valueOf(worldinfo.t()));
            playerusagesnooper.a("world[" + i0 + "][generator_name]", worldinfo.u().a());
            playerusagesnooper.a("world[" + i0 + "][generator_version]", Integer.valueOf(worldinfo.u().c()));
            playerusagesnooper.a("world[" + i0 + "][height]", Integer.valueOf(this.C));
            playerusagesnooper.a("world[" + i0 + "][chunks_loaded]", Integer.valueOf(worldserver.K().f()));
            ++i0;
        }

        playerusagesnooper.a("worlds", Integer.valueOf(i0));
    }

    @Override
    public void b(PlayerUsageSnooper playerusagesnooper) {
        playerusagesnooper.a("singleplayer", Boolean.valueOf(this.I()));
        playerusagesnooper.a("server_brand", this.getServerModName());
        playerusagesnooper.a("gui_supported", GraphicsEnvironment.isHeadless() ? "headless" : "supported");
        playerusagesnooper.a("dedicated", Boolean.valueOf(this.T()));
    }

    @Override
    public boolean R() {
        return true;
    }

    public int S() {
        return 16;
    }

    public abstract boolean T();

    public boolean U() {
        return this.w;
    }

    public void d(boolean flag0) {
        this.w = flag0;
    }

    public boolean V() {
        return this.x;
    }

    public void e(boolean flag0) {
        this.x = flag0;
    }

    public boolean W() {
        return this.y;
    }

    public void f(boolean flag0) {
        this.y = flag0;
    }

    public boolean X() {
        return this.z;
    }

    public void g(boolean flag0) {
        this.z = flag0;
    }

    public boolean Y() {
        return this.A;
    }

    public void h(boolean flag0) {
        this.A = flag0;
    }

    public abstract boolean Z();

    public String aa() {
        return this.B;
    }

    public void o(String s0) {
        this.B = s0;
    }

    public int ab() {
        return this.C;
    }

    public void d(int i0) {
        this.C = i0;
    }

    public boolean ac() {
        return this.u;
    }

    public ServerConfigurationManager ad() {
        return this.s;
    }

    public void a(ServerConfigurationManager serverconfigurationmanager) {
        this.s = serverconfigurationmanager;
    }

    public void a(EnumGameType enumgametype) {
        for (net.canarymod.api.world.World w : worldManager.getAllWorlds()) {
            WorldServer worldserver = (WorldServer) ((CanaryWorld) w).getHandle();

            worldserver.M().a(enumgametype);
        }
    }

    public abstract NetworkListenThread ae();

    public boolean ag() {
        return false;
    }

    public abstract String a(EnumGameType enumgametype, boolean flag0);

    public int ah() {
        return this.v;
    }

    public void ai() {
        this.S = true;
    }

    @Override
    public ChunkCoordinates b() {
        return new ChunkCoordinates(0, 0, 0);
    }

    public int ak() {
        return 16;
    }

    public boolean a(World world, int i0, int i1, int i2, EntityPlayer entityplayer) {
        return false;
    }

    @Override
    public abstract ILogAgent al();

    public void i(boolean flag0) {
        this.T = flag0;
    }

    public boolean am() {
        return this.T;
    }

    public static ServerConfigurationManager a(MinecraftServer minecraftserver) {
        return minecraftserver.s;
    }

    /**
     * Returns the canary world manager for this server instance
     *
     * @return
     */
    public CanaryWorldManager getWorldManager() {
        return worldManager;
    }

    /**
     * Reload configurations
     */
    public abstract void reload(); // look in DedicatedServer class

    // CanaryMod
    /**
     * Get the CanaryMod server handler
     *
     * @return
     */
    public CanaryServer getServer() {
        return server;
    }

    public CanaryConfigurationManager getConfigurationManager() {
        return s.getConfigurationManager();
    }

    public void initShutdown() {
        this.n();
    }

    public boolean isRunning() {
        return this.m();
    }

    /**
     * Creates a new world with given name and seed.
     * This will load the default (NORMAL) world
     * @param name
     * @param seed
     */
    public void loadWorld(String name, long seed) {
        loadWorld(name, seed, net.canarymod.api.world.DimensionType.fromName("NORMAL"));
    }

    public void loadWorld(String name, long seed, net.canarymod.api.world.DimensionType type) {
        this.loadWorld(name, seed, type, net.canarymod.api.world.WorldType.DEFAULT);
    }

    public void loadWorld(String name, long seed, net.canarymod.api.world.DimensionType type, net.canarymod.api.world.WorldType typeGen) {
        this.initWorld(name, seed, WorldType.a(typeGen.toString()), type, null);
    }

}
