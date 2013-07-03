package net.minecraft.server;

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.Proxy;
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
import net.canarymod.hook.system.LoadWorldHook;
import net.canarymod.hook.system.ServerTickHook;
import net.canarymod.logger.Logman;
import net.canarymod.tasks.ServerTaskManager;
import net.visualillusionsent.utils.PropertiesFile;

public abstract class MinecraftServer implements ICommandSender, Runnable, IPlayerUsage {

    private static boolean notHeadless;

    private static MinecraftServer l = null;
    private final ISaveFormat m;
    private final PlayerUsageSnooper n = new PlayerUsageSnooper("server", this, aq());
    private final File o;
    private final List p = new ArrayList();
    private final ICommandManager q;
    public final Profiler a = new Profiler();
    private String r;
    private int s = -1;
    // public WorldServer[] b; // XXX
    public ServerConfigurationManager t; // CanaryMod private -> public
    private boolean u = true;
    private boolean v;
    private int w;
    protected Proxy c;
    public String d;
    public int e;
    private boolean x;
    private boolean y;
    private boolean z;
    private boolean A;
    private boolean B;
    private String C;
    private int D;
    private long E;
    private long F;
    private long G;
    private long H;
    public final long[] f;
    public final long[] g;
    public final long[] h;
    public final long[] i;
    public final long[] j;
    // public long[][] k;
    private KeyPair I;
    private String J;
    private String K;
    private boolean M;
    private boolean N;
    private boolean O;
    private String P;
    private boolean Q;
    private long R;
    private String S;
    private boolean T;
    private boolean U;

    // CanaryMod start: Multiworld \o/
    public CanaryWorldManager worldManager = new CanaryWorldManager();
    private CanaryServer server;

    // CanaryMod start: Stop Message
    private String stopMsg;

    //

    public MinecraftServer(File file1) {
        this.c = Proxy.NO_PROXY;
        this.f = new long[100];
        this.g = new long[100];
        this.h = new long[100];
        this.i = new long[100];
        this.j = new long[100];
        this.P = "";
        l = this;
        this.o = file1;
        this.q = new ServerCommandManager();
        this.m = new AnvilSaveConverter(file1, net.canarymod.api.world.DimensionType.fromName("NORMAL"));
        this.ar();
        // CanaryMod
        this.server = new CanaryServer(this);
        Canary.setServer(server);
        //
    }

    private void ar() {
        DispenserBehaviors.a();
    }

    protected abstract boolean d() throws IOException;

    protected void a(String s0) {
        if (this.P().b(s0)) {
            this.an().a("Converting map!");
            this.b("menu.convertingLevel");
            this.P().a(s0, new ConvertingProgressUpdate(this));
        }
    }

    protected synchronized void b(String s0) {
        this.S = s0;
    }

    // Used to initialize the "master" worlds
    protected void initWorld(String name, long seed, WorldType nmsWt, net.canarymod.api.world.DimensionType worldtype, String generatorSettings) {
        this.a(name); // Anvil Converter
        this.b("menu.loadingLevel");
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
            // CanaryMod: Force game type from config
            worldinfo.a(EnumGameType.a(config.getGameMode().getId()));
            worldsettings = new WorldSettings(worldinfo);
        }

        if (this.N) {
            worldsettings.a();
        }

        if (worldtype.getId() == 0) {
            if (this.O()) {
                world = new DemoWorldServer(this, isavehandler, name, worldtype.getId(), this.a, this.an());
            } else {
                world = new WorldServer(this, isavehandler, name, worldtype.getId(), worldsettings, this.a, this.an());
            }
        } else {
            world = new WorldServerMulti(this, isavehandler, name, worldtype.getId(), worldsettings, (WorldServer) ((CanaryWorld) worldManager.getWorld(name, net.canarymod.api.world.DimensionType.fromName("NORMAL"), true)).getHandle(), this.a, this.an());
        }

        world.a((IWorldAccess) (new WorldManager(this, world)));
        if (!this.K()) {
            world.N().a(EnumGameType.a(config.getGameMode().getId()));
        }

        this.t.a(new WorldServer[]{ world }); // Init player data files

        // this.c(this.i()); // If we call this then worlds can't do custom difficulty, plus it doesn't work
        world.r = config.getDifficulty().getId(); // Set difficulty directly based on WorldConfiguration setting
        this.f(world); // Generate terrain
        worldManager.addWorld(world.getCanaryWorld());
        new LoadWorldHook(world.getCanaryWorld()).call();
    }

    protected void f(WorldServer worldserver) { // CanaryMod: signature changed
        boolean flag0 = true;
        boolean flag1 = true;
        boolean flag2 = true;
        boolean flag3 = true;
        int i0 = 0;

        this.b("menu.generatingTerrain");
        byte b0 = 0;

        this.an().a("Preparing start region for level " + worldserver.getCanaryWorld().getName());
        ChunkCoordinates chunkcoordinates = worldserver.K();
        long i1 = aq();

        for (int i2 = -192; i2 <= 192 && this.o(); i2 += 16) {
            for (int i3 = -192; i3 <= 192 && this.o(); i3 += 16) {
                long i4 = aq();

                if (i4 - i1 > 1000L) {
                    this.a_("Preparing spawn area", i0 * 100 / 625);
                    i1 = i4;
                }

                ++i0;
                worldserver.b.c(chunkcoordinates.a + i2 >> 4, chunkcoordinates.c + i3 >> 4);
            }
        }

        this.l();
    }

    public abstract boolean g();

    public abstract EnumGameType h();

    public abstract int i();

    public abstract boolean j();

    public abstract int k();

    protected void a_(String s0, int i0) {
        this.d = s0;
        this.e = i0;
        this.an().a(s0 + ": " + i0 + "%");
    }

    protected void l() {
        this.d = null;
        this.e = 0;
    }

    protected void a(boolean flag0) {
        if (!this.O) {
            // CanaryMod changed to use worldManager
            for (net.canarymod.api.world.World w : worldManager.getAllWorlds()) {
                WorldServer worldserver = (WorldServer) ((CanaryWorld) w).getHandle();

                if (worldserver != null) {
                    if (!flag0) {
                        this.an().a("Saving chunks for level \'" + worldserver.N().k() + "\'/" + worldserver.t.l());
                    }

                    try {
                        worldserver.a(true, (IProgressUpdate) null);
                    } catch (MinecraftException minecraftexception) {
                        this.an().b(minecraftexception.getMessage());
                    }
                } else {
                    Logman.println("World is null");
                }
            }
        }
    }

    public void m() {
        if (!this.O) {
            this.an().a("Stopping server");
            if (this.ag() != null) {
                this.ag().a();
            }

            if (this.t != null) {
                this.an().a("Saving players");
                this.t.g();
                this.t.r();
            }

            this.an().a("Saving worlds");
            this.a(false);

            // CanaryMod Multiworld
            for (net.canarymod.api.world.World w : worldManager.getAllWorlds()) {
                WorldServer worldserver = (WorldServer) ((CanaryWorld) w).getHandle();

                worldserver.n();
            }

            if (this.n != null && this.n.d()) {
                this.n.e();
            }
            // CanaryMod disable plugins:
            Canary.logInfo("Disabling Plugins ...");
            Canary.loader().disableAllPlugins();
        }
    }

    public String n() {
        return this.r;
    }

    public void c(String s0) {
        this.r = s0;
    }

    public boolean o() {
        return this.u;
    }

    public void p() {
        this.u = false;
    }

    @Override
    public void run() {
        try {
            if (this.d()) {
                long i0 = aq();

                for (long i1 = 0L; this.u; this.Q = true) {
                    ServerTaskManager.runTasks(); // CanaryMod: Run tasks
                    long i2 = aq();
                    long i3 = i2 - i0;

                    if (i3 > 2000L && i0 - this.R >= 15000L) {
                        this.an().b("Can\'t keep up! Did the system time change, or is the server overloaded?");
                        i3 = 2000L;
                        this.R = i0;
                    }

                    if (i3 < 0L) {
                        this.an().b("Time ran backwards! Did the system time change?");
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
                        this.s();
                        i1 = 0L;
                    } else {
                        while (i1 > 50L) {
                            i1 -= 50L;
                            this.s();
                        }
                    }

                    Thread.sleep(1L);
                }
            } else {
                this.a((CrashReport) null);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            this.an().c("Encountered an unexpected exception " + throwable.getClass().getSimpleName(), throwable);
            CrashReport crashreport = null;

            if (throwable instanceof ReportedException) {
                crashreport = this.b(((ReportedException) throwable).a());
            } else {
                crashreport = this.b(new CrashReport("Exception in server tick loop", throwable));
            }

            File file1 = new File(new File(this.q(), "crash-reports"), "crash-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + "-server.txt");

            if (crashreport.a(file1, this.an())) {
                this.an().c("This crash report has been saved to: " + file1.getAbsolutePath());
            } else {
                this.an().c("We were unable to save this crash report to disk.");
            }

            this.a(crashreport);
        }
        finally {
            try {
                this.m();
                this.v = true;
            } catch (Throwable throwable1) {
                throwable1.printStackTrace();
            }
            finally {
                this.r();
            }
        }
    }

    protected File q() {
        return new File(".");
    }

    protected void a(CrashReport crashreport) {}

    protected void r() {}

    protected void s() {
        long i0 = System.nanoTime();

        AxisAlignedBB.a().a();
        ++this.w;
        if (this.T) {
            this.T = false;
            this.a.a = true;
            this.a.a();
        }

        this.a.a("root");
        this.t();
        if (this.w % 900 == 0) {
            this.a.a("save");
            this.t.g();
            this.a(true);
            this.a.b();
        }

        this.a.a("tallying");
        this.j[this.w % 100] = System.nanoTime() - i0;
        this.f[this.w % 100] = Packet.q - this.E;
        this.E = Packet.q;
        this.g[this.w % 100] = Packet.r - this.F;
        this.F = Packet.r;
        this.h[this.w % 100] = Packet.o - this.G;
        this.G = Packet.o;
        this.i[this.w % 100] = Packet.p - this.H;
        this.H = Packet.p;
        this.a.b();
        this.a.a("snooper");
        if (!this.n.d() && this.w > 100) {
            this.n.a();
        }

        if (this.w % 6000 == 0) {
            this.n.b();
        }

        this.a.b();
        this.a.b();
    }

    // CanaryMod: ticks world
    private long previousTick = -1L; // Tick Time Tracker

    public void t() {
        new ServerTickHook(previousTick).call(); // CanaryMod: ServerTick
        long curTrack = System.nanoTime(); // CanaryMod: Start tick track

        this.a.a("levels");
        int i0;

        // CanaryMod use worldManager instead
        for (net.canarymod.api.world.World w : worldManager.getAllWorlds()) {
            WorldServer worldserver = (WorldServer) ((CanaryWorld) w).getHandle();
            //
            long i1 = System.nanoTime();

            this.a.a(worldserver.N().k());
            this.a.a("pools");
            worldserver.V().a();
            this.a.b();
            if (this.w % 20 == 0) {
                this.a.a("timeSync");
                // this.t.a((Packet) (new Packet4UpdateTime(worldserver.I(), worldserver.J())), worldserver.O().b("doDaylightCycle"));
                this.t.sendPacketToDimension((new Packet4UpdateTime(worldserver.I(), worldserver.J(), worldserver.O().b("doDaylightCycle"))), w.getName(), w.getType().getId());
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
            w.setNanoTick(this.w % 100, System.nanoTime() - i1);
            // this.k[i0][this.w % 100] = System.nanoTime() - i1;
        }

        this.a.c("connection");
        this.ag().b();
        this.a.c("players");
        this.t.b();
        this.a.c("tickables");

        for (i0 = 0; i0 < this.p.size(); ++i0) {
            ((IUpdatePlayerListBox) this.p.get(i0)).a();
        }

        this.a.b();
    }

    public boolean u() {
        throw new UnsupportedOperationException("allow-nether has been moved to a per-world configuration!");
    }

    public void a(IUpdatePlayerListBox iupdateplayerlistbox) {
        this.p.add(iupdateplayerlistbox);
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

            if (notHeadless) {
                MinecraftServerGui.getServerGui(dedicatedserver).validate();
            }

            ilogagent = dedicatedserver.an();
            if (s0 != null) {
                dedicatedserver.j(s0);
            }

            if (s2 != null) {
                dedicatedserver.k(s2);
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
                dedicatedserver.at();
            }

            dedicatedserver.v();
            Runtime.getRuntime().addShutdownHook(new ThreadDedicatedServer(dedicatedserver));
        } catch (Exception exception) {
            if (ilogagent != null) {
                ilogagent.c("Failed to start the minecraft server", exception);
            } else {
                Logger.getAnonymousLogger().log(Level.SEVERE, "Failed to start the minecraft server", exception);
            }
        }
    }

    public void v() {
        (new ThreadMinecraftServer(this, "Server thread")).start();
    }

    public File d(String s0) {
        return new File(this.q(), s0);
    }

    public void e(String s0) {
        this.an().a(s0);
    }

    public void f(String s0) {
        this.an().b(s0);
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

    public String w() {
        return this.r;
    }

    public int x() {
        return this.s;
    }

    public String y() {
        return this.C;
    }

    public String z() {
        return "1.6.1";
    }

    public int A() {
        return this.t.k();
    }

    public int B() {
        return this.t.l();
    }

    public String[] C() {
        return this.t.d();
    }

    public String D() {
        return "";
    }

    public String g(String s0) {
        RConConsoleSource.a.d();
        this.q.a(RConConsoleSource.a, s0);
        return RConConsoleSource.a.e();
    }

    public boolean E() {
        return false;
    }

    public void h(String s0) {
        this.an().c(s0);
    }

    public void i(String s0) {
        if (this.E()) {
            this.an().a(s0);
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

        if (this.t != null) {
            crashreport.g().a("Player Count", (Callable) (new CallableServerMemoryStats(this)));
        }

        return crashreport;
    }

    public List a(ICommandSender icommandsender, String s0) {
        ArrayList arraylist = new ArrayList();

        if (s0.startsWith("/")) {
            s0 = s0.substring(1);
            boolean flag0 = !s0.contains(" ");
            List list = this.q.b(icommandsender, s0);

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
            String[] astring1 = this.t.d();
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

    public static MinecraftServer F() {
        return l;
    }

    @Override
    public String c_() {
        return "Server";
    }

    public void a(ChatMessageComponent chatmessagecomponent) {
        this.an().a(chatmessagecomponent.toString());
    }

    @Override
    public boolean a(int i0, String s0) {
        return true;
    }

    public ICommandManager G() {
        return this.q;
    }

    public KeyPair H() {
        return this.I;
    }

    public int I() {
        return this.s;
    }

    public void b(int i0) {
        this.s = i0;
    }

    public String J() {
        return this.J;
    }

    public void j(String s0) {
        this.J = s0;
    }

    public boolean K() {
        return this.J != null;
    }

    public String L() {
        return this.K;
    }

    public void k(String s0) {
        this.K = s0;
    }

    public void a(KeyPair keypair) {
        this.I = keypair;
    }

    public void c(int i0) {
        // CanaryMod changes for Multiworld
        for (net.canarymod.api.world.World w : worldManager.getAllWorlds()) {
            WorldServer worldserver = (WorldServer) ((CanaryWorld) w).getHandle();

            //
            if (worldserver != null) {
                // System.out.println(worldserver.getCanaryWorld().getName() + " Difficulty " + i0);
                if (worldserver.N().t()) {
                    worldserver.r = 3;
                    worldserver.a(true, true);
                } else if (this.K()) {
                    worldserver.r = i0;
                    worldserver.a(worldserver.r > 0, true);
                } else {
                    worldserver.r = i0;
                    // Canarymod moved spawn-monsters to per-world config
                    worldserver.a(Configuration.getWorldConfig(w.getFqName()).canSpawnMonsters(), this.y);
                }
            }
        }
    }

    protected boolean N() {
        return true;
    }

    public boolean O() {
        return this.M;
    }

    public void b(boolean flag0) {
        this.M = flag0;
    }

    public void c(boolean flag0) {
        this.N = flag0;
    }

    public ISaveFormat P() {
        return this.m;
    }

    public void R() {
        this.O = true;
        this.P().d();

        for (net.canarymod.api.world.World w : worldManager.getAllWorlds()) {
            WorldServer worldserver = (WorldServer) ((CanaryWorld) w).getHandle();

            if (worldserver != null) {
                worldserver.n();
            }

            if (w.getType().getId() == 0) {
                this.P().e(worldserver.M().g());
            }
        }

        this.p();
    }

    public String S() {
        return this.P;
    }

    public void m(String s0) {
        this.P = s0;
    }

    @Override
    public void a(PlayerUsageSnooper playerusagesnooper) {
        playerusagesnooper.a("whitelist_enabled", Boolean.valueOf(false));
        playerusagesnooper.a("whitelist_count", Integer.valueOf(0));
        playerusagesnooper.a("players_current", Integer.valueOf(this.A()));
        playerusagesnooper.a("players_max", Integer.valueOf(this.B()));
        playerusagesnooper.a("players_seen", Integer.valueOf(this.t.m().length));
        playerusagesnooper.a("uses_auth", Boolean.valueOf(this.x));
        playerusagesnooper.a("gui_state", this.ai() ? "enabled" : "disabled");
        playerusagesnooper.a("run_time", Long.valueOf((aq() - playerusagesnooper.g()) / 60L * 1000L));
        playerusagesnooper.a("avg_tick_ms", Integer.valueOf((int) (MathHelper.a(this.j) * 1.0E-6D)));
        playerusagesnooper.a("avg_sent_packet_count", Integer.valueOf((int) MathHelper.a(this.f)));
        playerusagesnooper.a("avg_sent_packet_size", Integer.valueOf((int) MathHelper.a(this.g)));
        playerusagesnooper.a("avg_rec_packet_count", Integer.valueOf((int) MathHelper.a(this.h)));
        playerusagesnooper.a("avg_rec_packet_size", Integer.valueOf((int) MathHelper.a(this.i)));
        int i0 = 0;

        for (net.canarymod.api.world.World w : worldManager.getAllWorlds()) {
            WorldServer worldserver = (WorldServer) ((CanaryWorld) w).getHandle();
            WorldInfo worldinfo = worldserver.N();

            playerusagesnooper.a("world[" + i0 + "][dimension]", Integer.valueOf(worldserver.t.i));
            playerusagesnooper.a("world[" + i0 + "][mode]", worldinfo.r());
            playerusagesnooper.a("world[" + i0 + "][difficulty]", Integer.valueOf(worldserver.r));
            playerusagesnooper.a("world[" + i0 + "][hardcore]", Boolean.valueOf(worldinfo.t()));
            playerusagesnooper.a("world[" + i0 + "][generator_name]", worldinfo.u().a());
            playerusagesnooper.a("world[" + i0 + "][generator_version]", Integer.valueOf(worldinfo.u().c()));
            playerusagesnooper.a("world[" + i0 + "][height]", Integer.valueOf(this.D));
            playerusagesnooper.a("world[" + i0 + "][chunks_loaded]", Integer.valueOf(worldserver.L().f()));
            ++i0;
        }

        playerusagesnooper.a("worlds", Integer.valueOf(i0));
    }

    @Override
    public void b(PlayerUsageSnooper playerusagesnooper) {
        playerusagesnooper.a("singleplayer", Boolean.valueOf(this.K()));
        playerusagesnooper.a("server_brand", this.getServerModName());
        playerusagesnooper.a("gui_supported", GraphicsEnvironment.isHeadless() ? "headless" : "supported");
        playerusagesnooper.a("dedicated", Boolean.valueOf(this.V()));
    }

    public boolean T() {
        return true;
    }

    public int U() {
        return 16;
    }

    public abstract boolean V();

    public boolean W() {
        return this.x;
    }

    public void d(boolean flag0) {
        this.x = flag0;
    }

    public boolean X() {
        return this.y;
    }

    public void e(boolean flag0) {
        this.y = flag0;
    }

    public boolean Y() {
        return this.z;
    }

    public void f(boolean flag0) {
        this.z = flag0;
    }

    public boolean Z() {
        return this.A;
    }

    public void g(boolean flag0) {
        this.A = flag0;
    }

    public boolean aa() {
        return this.B;
    }

    public void h(boolean flag0) {
        this.B = flag0;
    }

    public abstract boolean ab();

    public String ac() {
        return this.C;
    }

    public void n(String s0) {
        this.C = s0;
    }

    public int ad() {
        return this.D;
    }

    public void d(int i0) {
        this.D = i0;
    }

    public boolean ae() {
        return this.v;
    }

    public ServerConfigurationManager af() {
        return this.t;
    }

    public void a(ServerConfigurationManager serverconfigurationmanager) {
        this.t = serverconfigurationmanager;
    }

    public void a(EnumGameType enumgametype) {
        for (net.canarymod.api.world.World w : worldManager.getAllWorlds()) {
            WorldServer worldserver = (WorldServer) ((CanaryWorld) w).getHandle();

            worldserver.N().a(enumgametype);
        }
    }

    public abstract NetworkListenThread ag();

    public boolean ai() {
        return false;
    }

    public abstract String a(EnumGameType enumgametype, boolean flag0);

    public int aj() {
        return this.w;
    }

    public void ak() {
        this.T = true;
    }

    @Override
    public ChunkCoordinates b() {
        return new ChunkCoordinates(0, 0, 0);
    }

    public World f_() {
        return ((CanaryWorld) Canary.getServer().getDefaultWorld()).getHandle(); // CanaryMod
    }

    public int am() {
        return 16;
    }

    public boolean a(World world, int i0, int i1, int i2, EntityPlayer entityplayer) {
        return false;
    }

    public abstract ILogAgent an();

    public void i(boolean flag0) {
        this.U = flag0;
    }

    public boolean ao() {
        return this.U;
    }

    public Proxy ap() {
        return this.c;
    }

    public static long aq() {
        return System.currentTimeMillis();
    }

    public static ServerConfigurationManager a(MinecraftServer minecraftserver) {
        return minecraftserver.t;
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
        return t.getConfigurationManager();
    }

    public void initShutdown() {
        this.n();
    }

    public boolean isRunning() {
        return this.o();
    }

    /**
     * Creates a new world with given name and seed.
     * This will load the default (NORMAL) world
     * 
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

    public static boolean isHeadless() {
        return !MinecraftServer.notHeadless;
    }

    public static void setHeadless(boolean state) {
        MinecraftServer.notHeadless = !state;
    }

}
