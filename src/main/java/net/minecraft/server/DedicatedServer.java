package net.minecraft.server;


import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
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


public class DedicatedServer extends MinecraftServer implements IServer {

    private final List k = Collections.synchronizedList(new ArrayList());
    private final ILogAgent l;
    private RConThreadQuery m;
    private RConThreadMain n;
    //  CanaryMod - Removed private PropertyManager o;
    //  CanaryMod - Removed private boolean p;
    //  CanaryMod - Removed private EnumGameType q;
    private NetworkListenThread r;
    private boolean s = false;

    public DedicatedServer(File file1) {
        super(file1);
        this.l = net.canarymod.Main.getLogAgent(); // new LogAgent("Minecraft-Server", (String) null, (new File(file1, "server.log")).getAbsolutePath());
        new DedicatedServerSleepThread(this);
    }

    protected boolean c() throws IOException {
        DedicatedServerCommandThread dedicatedservercommandthread = new DedicatedServerCommandThread(this);

        dedicatedservercommandthread.setDaemon(true);
        dedicatedservercommandthread.start();
        this.al().a("Starting minecraft server version 1.5.2");
        if (Runtime.getRuntime().maxMemory() / 1024L / 1024L < 512L) {
            this.al().b("To start the server with more ram, launch it as \"java -Xmx1024M -Xms1024M -jar minecraft_server.jar\"");
        }

        this.al().a("Loading properties");
        //        this.o = new PropertyManager(new File("server.properties"), this.al()); //CanaryMod - Removed
        //CanaryMod use our config
        ServerConfiguration cfg = Configuration.getServerConfig();
        if (this.I()) {
            this.d("127.0.0.1");
        } else {
            this.d(cfg.isOnlineMode());
            this.d(cfg.getBindIp());
        }
        //CanaryMod: Removed world-dependent settings
        this.o(cfg.getMotd());
        this.n(cfg.getTexturePack());
        InetAddress inetaddress = null;

        if (this.l().length() > 0) {
            try {
                inetaddress = InetAddress.getByName(this.l());
            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (this.G() < 0) {
            this.b(cfg.getPort());
        }

        this.al().a("Generating keypair");
        this.a(CryptManager.b());
        this.al().a("Starting Minecraft server on " + (this.l().length() == 0 ? "*" : this.l()) + ":" + this.G());

        try {
            this.r = new DedicatedServerListenThread(this, inetaddress, this.G());
        } catch (IOException ioexception) {
            this.al().b("**** FAILED TO BIND TO PORT!");
            this.al().b("The exception was: {0}", new Object[] { ioexception.toString()});
            this.al().b("Perhaps a server is already running on that port?");
            return false;
        }

        if (!this.U()) {
            this.al().b("**** SERVER IS RUNNING IN OFFLINE/INSECURE MODE!");
            this.al().b("The server will make no attempt to authenticate usernames. Beware.");
            this.al().b("While this makes the game possible to play without internet access, it also opens up the ability for hackers to connect with any username they choose.");
            this.al().b("To change this, set \"online-mode\" to \"true\" in the server.properties file.");
        }

        this.a((ServerConfigurationManager) (new DedicatedPlayerList(this)));
        long i1 = System.nanoTime();

        if (this.J() == null) {
            this.l(Configuration.getServerConfig().getDefaultWorldName()); // CanaryMod use our world config
        }
        // CanaryMod use or configurations instead of native ones
        WorldConfiguration worldcfg = Configuration.getWorldConfig(this.J() + "_NORMAL");

        String s0 = worldcfg.getWorldSeed(); // this.o.a("level-seed", "");
        String s1 = worldcfg.getWorldType().toString(); // this.o.a("level-type", "DEFAULT");
        String s2 = worldcfg.getGeneratorSettings(); // this.o.a("generator-settings", "");
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
        this.d((this.ab() + 8) / 16 * 16);
        this.d(MathHelper.a(this.ab(), 64, 256));
        worldcfg.getFile().setInt("max-build-height", this.ab());

        this.al().a("Preparing level \"" + this.J() + "\"");
        // CanaryMod changed call to initWorld
        net.canarymod.api.world.DimensionType wt = net.canarymod.api.world.DimensionType.fromName("NORMAL");

        this.initWorld(this.J(), i2, worldtype, wt, s2);
        //
        long i4 = System.nanoTime() - i1;
        String s3 = String.format("%.3fs", new Object[] { Double.valueOf((double) i4 / 1.0E9D)});

        this.al().a("Done (" + s3 + ")! For help, type \"help\" or \"?\"");
        if (cfg.isQueryEnabled()) {
            this.al().a("Starting GS4 status listener");
            this.m = new RConThreadQuery(this);
            this.m.a();
        }
        if (cfg.isRconEnabled()) {
            this.al().a("Starting remote control listener");
            this.n = new RConThreadMain(this);
            this.n.a();
        }

        return true;
    }

    public boolean f() {
        throw new UnsupportedOperationException("Generate-structures setting has been moved to a per-world configuration!");
    }

    public EnumGameType g() {
        throw new UnsupportedOperationException("GameType setting has been moved to a per-world configuration!");
    }

    public int h() {
        throw new UnsupportedOperationException("Difficulty setting has been moved to a per-world configuration!");
    }

    public boolean i() {
        throw new UnsupportedOperationException("Hardcoremode setting has been moved to a per-world configuration!");
    }

    protected void a(CrashReport crashreport) {
        while (this.m()) {
            this.an();

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

    protected void p() {
        System.exit(0);
    }

    public void r() { // CanaryMod: protected => public
        super.r();
        this.an();
    }

    public boolean s() {
        throw new UnsupportedOperationException("allow-nether has been moved to a per-world config");
    }

    public boolean L() {
        throw new UnsupportedOperationException("spawn-monsters has been moved to a per-world config");
    }

    public void a(PlayerUsageSnooper playerusagesnooper) {
        playerusagesnooper.a("whitelist_enabled", Configuration.getServerConfig().isWhitelistEnabled());
        playerusagesnooper.a("whitelist_count", Canary.whitelist().getSize());
        super.a(playerusagesnooper);
    }

    public boolean R() {
        //CanaryMod moved to config/server.cfg
        return Configuration.getServerConfig().isSnooperEnabled();
    }

    public void a(String s0, ICommandSender icommandsender) {
        this.k.add(new ServerCommand(s0, icommandsender));
    }

    public void an() {
        while (!this.k.isEmpty()) {
            ServerCommand servercommand = (ServerCommand) this.k.remove(0);
            //CanaryMod intercept command queue for our own commands
            if(!Canary.getServer().consoleCommand(servercommand.a)) {
                this.E().a(servercommand.b, servercommand.a);
            }
        }
    }

    public boolean T() {
        return true;
    }

    public DedicatedPlayerList ao() {
        return (DedicatedPlayerList) super.ad();
    }

    public NetworkListenThread ae() {
        return this.r;
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

    @Deprecated
    public void a() {
        throw new UnsupportedOperationException("Cannot finish this request. DdedicatedServer.a() is deprecated");
    }

    @Deprecated
    public String b_() {
        throw new UnsupportedOperationException("Cannot finish this request. DdedicatedServer.b_() is deprecated");
    }

    public void ap() {
        // TODO GUI start Hook
        ((CanaryServer) Canary.getServer()).currentGUI = ServerGUI.a();
        ((CanaryServer) Canary.getServer()).notHeadless = this.s = true;
    }

    public boolean ag() {
        return this.s;
    }

    public String a(EnumGameType enumgametype, boolean flag0) {
        return "";
    }

    public boolean Z() {
        //CanaryMod moved to config/server.cfg
        return Configuration.getServerConfig().isCommandBlockEnabled();
    }

    public int ak() {
        throw new UnsupportedOperationException("spawn-protection has been moved to a per-world config!");
    }

    public boolean a(World world, int i0, int i1, int i2, EntityPlayer entityplayer) {
        WorldConfiguration cfg = Configuration.getWorldConfig(world.getCanaryWorld().getFqName());
        if (world.t.h != 0) {
            return false;
        } else if (this.ao().i().isEmpty()) {
            return false;
        } else if (this.ao().e(entityplayer.bS)) {
            return false;
        } else if (cfg.getSpawnProtectionSize() <= 0) {
            return false;
        } else {
            ChunkCoordinates chunkcoordinates = world.J();
            int i3 = MathHelper.a(i0 - chunkcoordinates.a);
            int i4 = MathHelper.a(i2 - chunkcoordinates.c);
            int i5 = Math.max(i3, i4);

            return i5 <= cfg.getSpawnProtectionSize();
        }
    }

    public ILogAgent al() {
        return this.l;
    }

    public ServerConfigurationManager ad() {
        return this.ao();
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
