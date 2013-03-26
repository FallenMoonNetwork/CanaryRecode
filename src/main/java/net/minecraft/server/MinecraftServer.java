package net.minecraft.server;


import java.awt.GraphicsEnvironment;
import java.io.File;
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
import net.canarymod.api.world.CanaryWorldManager;


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
    public WorldServer[] b; // XXX
    private ServerConfigurationManager s;
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
    public long[][] j;
    private KeyPair H;
    private String I;
    private String J;
    private boolean L;
    private boolean M;
    private boolean N;
    private String O = "";
    private boolean P = false;
    private long Q;
    private String R;
    private boolean S;

    // CanaryMod
    private CanaryServer server;
    private CanaryConfigurationManager cfgManager;
    CanaryWorldManager worldManager;

    //

    public MinecraftServer(File file1) {
        k = this;
        this.n = file1;
        this.p = new ServerCommandManager();
        this.l = new AnvilSaveConverter(file1);
        this.am();
        // CanaryMod
        this.server = new CanaryServer(this);
        Canary.setServer(server);
        worldManager = new CanaryWorldManager();
        //
    }

    private void am() {
        DispenserBehaviors.a();
    }

    protected abstract boolean c();

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

    protected void a(String s0, String s1, long i0, WorldType worldtype, String s2) {
        this.b(s0);
        this.c("menu.loadingLevel");
        this.b = new WorldServer[3];
        this.j = new long[this.b.length][100];
        ISaveHandler isavehandler = this.l.a(s0, true);
        WorldInfo worldinfo = isavehandler.d();
        WorldSettings worldsettings;

        if (worldinfo == null) {
            worldsettings = new WorldSettings(i0, this.g(), this.f(), this.i(), worldtype);
            worldsettings.a(s2);
        } else {
            worldsettings = new WorldSettings(worldinfo);
        }

        if (this.M) {
            worldsettings.a();
        }

        for (int i1 = 0; i1 < this.b.length; ++i1) {
            byte b0 = 0;

            if (i1 == 1) {
                b0 = -1;
            }

            if (i1 == 2) {
                b0 = 1;
            }

            if (i1 == 0) {
                if (this.M()) {
                    this.b[i1] = new DemoWorldServer(this, isavehandler, s1, b0, this.a, this.al());
                } else {
                    this.b[i1] = new WorldServer(this, isavehandler, s1, b0, worldsettings, this.a, this.al());
                }
            } else {
                this.b[i1] = new WorldServerMulti(this, isavehandler, s1, b0, worldsettings, this.b[0], this.a, this.al());
            }

            this.b[i1].a((IWorldAccess) (new WorldManager(this, this.b[i1])));
            if (!this.I()) {
                this.b[i1].L().a(this.g());
            }

            this.s.a(this.b);
        }

        this.c(this.h());
        this.e();
    }

    protected void e() {
        int i0 = 0;

        this.c("menu.generatingTerrain");
        byte b0 = 0;

        this.al().a("Preparing start region for level " + b0);
        WorldServer worldserver = this.b[b0];
        ChunkCoordinates chunkcoordinates = worldserver.I();
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
            WorldServer[] aworldserver = this.b;
            int i0 = aworldserver.length;

            for (int i1 = 0; i1 < i0; ++i1) {
                WorldServer worldserver = aworldserver[i1];

                if (worldserver != null) {
                    if (!flag0) {
                        this.al().a("Saving chunks for level \'" + worldserver.L().k() + "\'/" + worldserver.t.l());
                    }

                    try {
                        worldserver.a(true, (IProgressUpdate) null);
                    } catch (MinecraftException minecraftexception) {
                        this.al().b(minecraftexception.getMessage());
                    }
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

            for (int i0 = 0; i0 < this.b.length; ++i0) {
                WorldServer worldserver = this.b[i0];

                worldserver.m();
            }

            if (this.m != null && this.m.d()) {
                this.m.e();
            }
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
                    if (this.b[0].e()) {
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

    public void r() {
        this.a.a("levels");

        int i0;

        for (i0 = 0; i0 < this.b.length; ++i0) {
            long i1 = System.nanoTime();

            if (i0 == 0 || this.s()) {
                WorldServer worldserver = this.b[i0];

                this.a.a(worldserver.L().k());
                this.a.a("pools");
                worldserver.T().a();
                this.a.b();
                if (this.v % 20 == 0) {
                    this.a.a("timeSync");
                    this.s.a((Packet) (new Packet4UpdateTime(worldserver.G(), worldserver.H())), worldserver.t.h);
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
                worldserver.p().a();
                this.a.b();
                this.a.b();
            }

            this.j[i0][this.v % 100] = System.nanoTime() - i1;
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
        return true;
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
                dedicatedserver.ao();
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

    public WorldServer a(int i0) {
        return i0 == -1 ? this.b[1] : (i0 == 1 ? this.b[2] : this.b[0]);
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
        return "1.5.1";
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
        return "vanilla";
    }

    public CrashReport b(CrashReport crashreport) {
        crashreport.g().a("Profiler Position", (Callable) (new CallableIsServerModded(this)));
        if (this.b != null && this.b.length > 0 && this.b[0] != null) {
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
        for (int i1 = 0; i1 < this.b.length; ++i1) {
            WorldServer worldserver = this.b[i1];

            if (worldserver != null) {
                if (worldserver.L().t()) {
                    worldserver.r = 3;
                    worldserver.a(true, true);
                } else if (this.I()) {
                    worldserver.r = i0;
                    worldserver.a(worldserver.r > 0, true);
                } else {
                    worldserver.r = i0;
                    worldserver.a(this.L(), this.x);
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
        this.N = true;
        this.N().d();

        for (int i0 = 0; i0 < this.b.length; ++i0) {
            WorldServer worldserver = this.b[i0];

            if (worldserver != null) {
                worldserver.m();
            }
        }

        this.N().e(this.b[0].K().g());
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

        for (int i1 = 0; i1 < this.b.length; ++i1) {
            if (this.b[i1] != null) {
                WorldServer worldserver = this.b[i1];
                WorldInfo worldinfo = worldserver.L();

                playerusagesnooper.a("world[" + i0 + "][dimension]", Integer.valueOf(worldserver.t.h));
                playerusagesnooper.a("world[" + i0 + "][mode]", worldinfo.r());
                playerusagesnooper.a("world[" + i0 + "][difficulty]", Integer.valueOf(worldserver.r));
                playerusagesnooper.a("world[" + i0 + "][hardcore]", Boolean.valueOf(worldinfo.t()));
                playerusagesnooper.a("world[" + i0 + "][generator_name]", worldinfo.u().a());
                playerusagesnooper.a("world[" + i0 + "][generator_version]", Integer.valueOf(worldinfo.u().c()));
                playerusagesnooper.a("world[" + i0 + "][height]", Integer.valueOf(this.C));
                playerusagesnooper.a("world[" + i0 + "][chunks_loaded]", Integer.valueOf(worldserver.J().e()));
                ++i0;
            }
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
        for (int i0 = 0; i0 < this.b.length; ++i0) {
            D().b[i0].L().a(enumgametype);
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
     * CanaryMod get configuration manager
     * 
     * @return the cfgManager
     */
    public CanaryConfigurationManager getCanaryConfigurationManager() {
        return cfgManager;
    }

    public void setCanaryConfigurationmanager(CanaryConfigurationManager man) {
        cfgManager = man;
    }

    /**
     * Reload configurations
     */
    public abstract void reload(); // look in DedicatedServer class

    /**
     * Get the CanaryMod server handler
     * 
     * @return
     */
    public CanaryServer getServer() {
        return server;
    }

    public CanaryConfigurationManager getConfigurationManager() {
        return cfgManager;
    }
}
