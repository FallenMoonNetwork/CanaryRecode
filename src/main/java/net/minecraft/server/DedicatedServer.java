package net.minecraft.server;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import net.canarymod.Canary;
import net.canarymod.api.CanaryServer;
import net.canarymod.config.Configuration;
import net.canarymod.config.ServerConfiguration;
import net.canarymod.config.WorldConfiguration;
import net.canarymod.hook.system.ServerGuiStartHook;

public class DedicatedServer extends MinecraftServer implements IServer {

    private final List l = Collections.synchronizedList(new ArrayList());
    private final ILogAgent m;
    private RConThreadQuery n;
    private RConThreadMain o;
    // CanaryMod - Removed private PropertyManager p;
    // CanaryMod - Removed private boolean q;
    // CanaryMod - Removed private EnumGameType r;
    private NetworkListenThread s;
    private boolean t;

    public DedicatedServer(File file1) {
        super(file1);
        this.m = net.canarymod.Main.getLogAgent(); // new LogAgent("Minecraft-Server", (String) null, (new File(file1, "server.log")).getAbsolutePath());
        new DedicatedServerSleepThread(this);
    }

    protected boolean d() throws IOException {
        DedicatedServerCommandThread dedicatedservercommandthread = new DedicatedServerCommandThread(this);

        dedicatedservercommandthread.setDaemon(true);
        dedicatedservercommandthread.start();
        this.an().a("Starting minecraft server version 1.6.2");
        if (Runtime.getRuntime().maxMemory() / 1024L / 1024L < 512L) {
            this.an().b("To start the server with more ram, launch it as \"java -Xmx1024M -Xms1024M -jar minecraft_server.jar\"");
        }

        this.an().a("Loading properties");
        // this.p = new PropertyManager(new File("server.properties"), this.an()); //CanaryMod - Removed
        // CanaryMod use our config
        ServerConfiguration cfg = Configuration.getServerConfig();
        if (this.K()) {
            this.c("127.0.0.1");
        } else {
            this.d(cfg.isOnlineMode());
            this.c(cfg.getBindIp());
        }
        // CanaryMod: Removed world-dependent settings
        this.n(cfg.getMotd());
        this.m(cfg.getTexturePack());
        InetAddress inetaddress = null;

        if (this.n().length() > 0) {
            inetaddress = InetAddress.getByName(this.n());
        }

        if (this.I() < 0) {
            this.b(cfg.getPort());
        }

        this.an().a("Generating keypair");
        this.a(CryptManager.b());
        this.an().a("Starting Minecraft server on " + (this.n().length() == 0 ? "*" : this.n()) + ":" + this.I());

        try {
            this.s = new DedicatedServerListenThread(this, inetaddress, this.I());
        } catch (IOException ioexception) {
            this.an().b("**** FAILED TO BIND TO PORT!");
            this.an().b("The exception was: {0}", new Object[]{ ioexception.toString() });
            this.an().b("Perhaps a server is already running on that port?");
            return false;
        }

        if (!this.W()) {
            this.an().b("**** SERVER IS RUNNING IN OFFLINE/INSECURE MODE!");
            this.an().b("The server will make no attempt to authenticate usernames. Beware.");
            this.an().b("While this makes the game possible to play without internet access, it also opens up the ability for hackers to connect with any username they choose.");
            this.an().b("To change this, set \"online-mode\" to \"true\" in the server.properties file.");
        }

        this.a((ServerConfigurationManager) (new DedicatedPlayerList(this)));
        long i1 = System.nanoTime();

        if (this.L() == null) {
            this.k(Configuration.getServerConfig().getDefaultWorldName()); // CanaryMod use our world config
        }
        // CanaryMod use or configurations instead of native ones
        WorldConfiguration worldcfg = Configuration.getWorldConfig(this.L() + "_NORMAL");

        String s0 = worldcfg.getWorldSeed(); // this.p.a("level-seed", "");
        String s1 = worldcfg.getWorldType().toString(); // this.p.a("level-type", "DEFAULT");
        String s2 = worldcfg.getGeneratorSettings(); // this.p.a("generator-settings", "");
        long i2 = (new Random()).nextLong();

        if (s0.length() > 0) {
            try {
                long i3 = Long.parseLong(s0);

                if (i3 != 0L) {
                    i2 = i3;
                }
            } catch (NumberFormatException numberformatexception) {
                i2 = (long) s0.hashCode();
            }
        }

        WorldType worldtype = WorldType.a(s1);

        if (worldtype == null) {
            worldtype = WorldType.b;
        }

        this.d(worldcfg.getMaxBuildHeight());
        this.d((this.ad() + 8) / 16 * 16);
        this.d(MathHelper.a(this.ad(), 64, 256));
        worldcfg.getFile().setInt("max-build-height", this.ad());
        // CanaryMod enable plugins here, before the first world is loaded.
        // At this point all bootstrapping should be done and systems should be running
        Canary.enablePlugins();

        if (!MinecraftServer.isHeadless()) {
            // CanaryMod moved GUI start to after plugins enable
            at();
        }

        this.an().a("Preparing level \"" + this.L() + "\"");
        // CanaryMod changed call to initWorld
        net.canarymod.api.world.DimensionType wt = net.canarymod.api.world.DimensionType.fromName("NORMAL");

        this.initWorld(this.L(), i2, worldtype, wt, s2);
        //
        long i4 = System.nanoTime() - i1;
        String s3 = String.format("%.3fs", new Object[]{ Double.valueOf((double) i4 / 1.0E9D) });

        this.an().a("Done (" + s3 + ")! For help, type \"help\" or \"?\"");
        if (cfg.isQueryEnabled()) {
            this.an().a("Starting GS4 status listener");
            this.n = new RConThreadQuery(this);
            this.n.a();
        }
        if (cfg.isRconEnabled()) {
            this.an().a("Starting remote control listener");
            this.o = new RConThreadMain(this);
            this.o.a();
        }

        return true;
    }

    public boolean g() {
        throw new UnsupportedOperationException("Generate-structures setting has been moved to a per-world configuration!");
    }

    public EnumGameType h() {
        throw new UnsupportedOperationException("GameType setting has been moved to a per-world configuration!");
    }

    public int i() {
        throw new UnsupportedOperationException("Difficulty setting has been moved to a per-world configuration!");
    }

    public boolean j() {
        throw new UnsupportedOperationException("Hardcoremode setting has been moved to a per-world configuration!");
    }

    protected void a(CrashReport crashreport) {
        while (this.o()) {
            this.ar();

            try {
                Thread.sleep(10L);
            } catch (InterruptedException interruptedexception) {
                interruptedexception.printStackTrace();
            }
        }
    }

    public CrashReport b(CrashReport crashreport) {
        crashreport = super.b(crashreport);
        crashreport.g().a("Is Modded", (Callable) (new CallableType(this)));
        crashreport.g().a("Type", (Callable) (new CallableServerType(this)));
        return crashreport;
    }

    protected void r() {
        System.exit(0);
    }

    public void t() { // CanaryMod: protected => public
        super.t();
        this.ar();
    }

    public boolean u() {
        throw new UnsupportedOperationException("allow-nether has been moved to a per-world config");
    }

    public boolean N() {
        throw new UnsupportedOperationException("spawn-monsters has been moved to a per-world config");
    }

    public void a(PlayerUsageSnooper playerusagesnooper) {
        playerusagesnooper.a("whitelist_enabled", Configuration.getServerConfig().isWhitelistEnabled());
        playerusagesnooper.a("whitelist_count", Canary.whitelist().getSize());
        super.a(playerusagesnooper);
    }

    public boolean T() {
        // CanaryMod moved to config/server.cfg
        return Configuration.getServerConfig().isSnooperEnabled();
    }

    public void a(String s0, ICommandSender icommandsender) {
        this.l.add(new ServerCommand(s0, icommandsender));
    }

    public void ar() {
        while (!this.l.isEmpty()) {
            ServerCommand servercommand = (ServerCommand) this.l.remove(0);
            // CanaryMod intercept command queue for our own commands
            if (!Canary.getServer().consoleCommand(servercommand.a)) {
                this.G().a(servercommand.b, servercommand.a);
            }
        }
    }

    public boolean V() {
        return true;
    }

    public DedicatedPlayerList as() {
        return (DedicatedPlayerList) super.af();
    }

    public NetworkListenThread ag() {
        return this.s;
    }

    public int a(String s0, int i0) {
        throw new UnsupportedOperationException("Setting int values to server.properties is disabled!");
    }

    public String a(String s0, String s1) {
        throw new UnsupportedOperationException("Setting String values to server.properties is disabled!");
    }

    public boolean a(String s0, boolean flag0) {
        throw new UnsupportedOperationException("Setting boolean values to server.properties is disabled!");
    }

    public void a(String s0, Object object) {
        throw new UnsupportedOperationException("Setting Object values to server.properties is disabled!");
    }

    public void a() {
        throw new UnsupportedOperationException("Cannot finish this request. DdedicatedServer.a() is deprecated");
    }

    public String b_() {
        throw new UnsupportedOperationException("Cannot finish this request. DdedicatedServer.b_() is deprecated");
    }

    public void at() {
        ServerGuiStartHook guiHook = (ServerGuiStartHook) new ServerGuiStartHook(MinecraftServerGui.preInit(this)).call(); // CanaryMod: PreInitialize the GUI without starting it
        if (guiHook.getGui() != null) {
            ((CanaryServer) Canary.getServer()).setCurrentGUI(guiHook.getGui());
        } else {
            ((CanaryServer) Canary.getServer()).setCurrentGUI(MinecraftServerGui.a(this));
        }
        ((CanaryServer) Canary.getServer()).getCurrentGUI().start();
        this.t = true;
        MinecraftServer.setHeadless(false);
    }

    public boolean ai() {
        return this.t;
    }

    public String a(EnumGameType enumgametype, boolean flag0) {
        return "";
    }

    public boolean ab() {
        // CanaryMod moved to config/server.cfg
        return Configuration.getServerConfig().isCommandBlockEnabled();
    }

    public int am() {
        throw new UnsupportedOperationException("spawn-protection has been moved to a per-world config!");
    }

    public boolean a(World world, int i0, int i1, int i2, EntityPlayer entityplayer) {
        WorldConfiguration cfg = Configuration.getWorldConfig(world.getCanaryWorld().getFqName());
        if (world.t.i != 0) {
            return false;
            // } else if (this.as().i().isEmpty()) { // CanaryMod: Empty Ops list shouldn't break spawn protections...
            // return false;
        } else if (this.as().e(entityplayer.c_())) {
            return false;
        } else if (cfg.getSpawnProtectionSize() <= 0) {
            return false;
        } else {
            ChunkCoordinates chunkcoordinates = world.K();
            int i3 = MathHelper.a(i0 - chunkcoordinates.a);
            int i4 = MathHelper.a(i2 - chunkcoordinates.c);
            int i5 = Math.max(i3, i4);

            return i5 <= cfg.getSpawnProtectionSize();
        }
    }

    public ILogAgent an() {
        return this.m;
    }

    public int k() {
        // MERGE: This must be in server.cfg instead! XXX
        // return this.p.a("op-permission-level", 4);
        return 4;
    }

    public ServerConfigurationManager af() {
        return this.as();
    }

    @Override
    public void reload() {/* WorldConfiguration defWorld = Configuration.getWorldConfig(Configuration.getServerConfig().getDefaultWorldName());
                          * // this.d = new OPropertyManager(new File("server.properties"));
                          * this.y = Configuration.getNetConfig().getBindIp();
                          * this.n = Configuration.getNetConfig().isOnlineMode();
                          * this.o = defWorld.canSpawnAnimals();
                          * this.p = defWorld.canSpawnNpcs();
                          * this.q = defWorld.isPvpEnabled();
                          * this.r = defWorld.isFlightAllowed();
                          * this.s = Configuration.getServerConfig().getMotd();
                          * this.z = Configuration.getNetConfig().getPort();
                          * this.t = defWorld.getMaxBuildHeight();
                          * this.t = (this.t + 8) / 16 * 16;
                          * this.t = OMathHelper.a(this.t, 64, 256);
                          * // TODO Update worlds (??) */}
}
