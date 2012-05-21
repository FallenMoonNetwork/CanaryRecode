package net.minecraft.server;

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.canarymod.CanaryServer;
import net.minecraft.server.OAnvilSaveConverter;
import net.minecraft.server.OAnvilSaveHandler;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OChunkCoordinates;
import net.minecraft.server.OConsoleCommandHandler;
import net.minecraft.server.OConsoleLogManager;
import net.minecraft.server.OConvertProgressUpdater;
import net.minecraft.server.OEntityTracker;
import net.minecraft.server.OICommandListener;
import net.minecraft.server.OIProgressUpdate;
import net.minecraft.server.OISaveFormat;
import net.minecraft.server.OIServer;
import net.minecraft.server.OIUpdatePlayerListBox;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONetworkListenThread;
import net.minecraft.server.OPacket;
import net.minecraft.server.OPacket4UpdateTime;
import net.minecraft.server.OPropertyManager;
import net.minecraft.server.ORConConsoleSource;
import net.minecraft.server.ORConThreadMain;
import net.minecraft.server.ORConThreadQuery;
import net.minecraft.server.OServerCommand;
import net.minecraft.server.OServerConfigurationManager;
import net.minecraft.server.OServerGUI;
import net.minecraft.server.OStatList;
import net.minecraft.server.OThreadCommandReader;
import net.minecraft.server.OThreadServerApplication;
import net.minecraft.server.OThreadSleepForever;
import net.minecraft.server.OVec3D;
import net.minecraft.server.OWorldManager;
import net.minecraft.server.OWorldServer;
import net.minecraft.server.OWorldServerMulti;
import net.minecraft.server.OWorldSettings;
import net.minecraft.server.OWorldType;

public class OMinecraftServer implements Runnable, OICommandListener, OIServer {

    public static Logger a = Logger.getLogger("Minecraft");
    public static HashMap b = new HashMap();
    private String y;
    private int z;
    public ONetworkListenThread c;
    public OPropertyManager d;
    public OWorldServer[] e;
    public long[] f = new long[100];
    public long[][] g;
    public OServerConfigurationManager h;
    private OConsoleCommandHandler A;
    private boolean B = true;
    public boolean i = false;
    int j = 0;
    public String k;
    public int l;
    private List C = new ArrayList();
    private List D = Collections.synchronizedList(new ArrayList());
    public OEntityTracker[] m = new OEntityTracker[3];
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public String s;
    public int t;
    private long E;
    private long F;
    private long G;
    private long H;
    public long[] u = new long[100];
    public long[] v = new long[100];
    public long[] w = new long[100];
    public long[] x = new long[100];
    private ORConThreadQuery I;
    private ORConThreadMain J;
    //CanaryMod Server reference
    private CanaryServer server;

    public OMinecraftServer() {
        super();
        this.server = new CanaryServer(this);
        new OThreadSleepForever(this);
    }

    /**
     * Get the CanaryMod server handler
     * 
     * @return
     */
    public CanaryServer getServer() {
        return server;
    }

    private boolean s() throws UnknownHostException {
        this.A = new OConsoleCommandHandler(this);
        OThreadCommandReader var1 = new OThreadCommandReader(this);
        var1.setDaemon(true);
        var1.start();
        OConsoleLogManager.a();
        a.info("Starting minecraft server version 1.2.5");
        if (Runtime.getRuntime().maxMemory() / 1024L / 1024L < 512L) {
            a.warning("To start the server with more ram, launch it as \"java -Xmx1024M -Xms1024M -jar minecraft_server.jar\"");
        }

        a.info("Loading properties");
        this.d = new OPropertyManager(new File("server.properties"));
        this.y = this.d.a("server-ip", "");
        this.n = this.d.a("online-mode", true);
        this.o = this.d.a("spawn-animals", true);
        this.p = this.d.a("spawn-npcs", true);
        this.q = this.d.a("pvp", true);
        this.r = this.d.a("allow-flight", false);
        this.s = this.d.a("motd", "A Minecraft Server");
        this.s.replace('\u00a7', '$');
        InetAddress var2 = null;
        if (this.y.length() > 0) {
            var2 = InetAddress.getByName(this.y);
        }

        this.z = this.d.a("server-port", 25565);
        a.info("Starting Minecraft server on " + (this.y.length() == 0 ? "*" : this.y) + ":" + this.z);

        try {
            this.c = new ONetworkListenThread(this, var2, this.z);
        } catch (IOException var18) {
            a.warning("**** FAILED TO BIND TO PORT!");
            a.log(Level.WARNING, "The exception was: " + var18.toString());
            a.warning("Perhaps a server is already running on that port?");
            return false;
        }

        if (!this.n) {
            a.warning("**** SERVER IS RUNNING IN OFFLINE/INSECURE MODE!");
            a.warning("The server will make no attempt to authenticate usernames. Beware.");
            a.warning("While this makes the game possible to play without internet access, it also opens up the ability for hackers to connect with any username they choose.");
            a.warning("To change this, set \"online-mode\" to \"true\" in the server.settings file.");
        }

        this.h = new OServerConfigurationManager(this);
        this.m[0] = new OEntityTracker(this, 0);
        this.m[1] = new OEntityTracker(this, -1);
        this.m[2] = new OEntityTracker(this, 1);
        long var4 = System.nanoTime();
        String var6 = this.d.a("level-name", "world");
        String var7 = this.d.a("level-seed", "");
        String var8 = this.d.a("level-type", "DEFAULT");
        long var9 = (new Random()).nextLong();
        if (var7.length() > 0) {
            try {
                long var11 = Long.parseLong(var7);
                if (var11 != 0L) {
                    var9 = var11;
                }
            } catch (NumberFormatException var17) {
                var9 = var7.hashCode();
            }
        }

        OWorldType var13 = OWorldType.a(var8);
        if (var13 == null) {
            var13 = OWorldType.b;
        }

        this.t = this.d.a("max-build-height", 256);
        this.t = (this.t + 8) / 16 * 16;
        this.t = OMathHelper.a(this.t, 64, 256);
        this.d.a("max-build-height", Integer.valueOf(this.t));
        a.info("Preparing level \"" + var6 + "\"");
        this.a(new OAnvilSaveConverter(new File(".")), var6, var9, var13);
        long var14 = System.nanoTime() - var4;
        String var16 = String.format("%.3fs", new Object[] { Double.valueOf(var14 / 1.0E9D) });
        a.info("Done (" + var16 + ")! For help, type \"help\" or \"?\"");
        if (this.d.a("enable-query", false)) {
            a.info("Starting GS4 status listener");
            this.I = new ORConThreadQuery(this);
            this.I.a();
        }

        if (this.d.a("enable-rcon", false)) {
            a.info("Starting remote control listener");
            this.J = new ORConThreadMain(this);
            this.J.a();
        }

        return true;
    }

    // CanaryMod desc: initWorld
    private void a(OISaveFormat var1, String var2, long var3, OWorldType var5) {
        if (var1.a(var2)) {
            a.info("Converting map!");
            var1.a(var2, new OConvertProgressUpdater(this));
        }

        this.e = new OWorldServer[3];
        this.g = new long[this.e.length][100];
        int var6 = this.d.a("gamemode", 0); // get int property
        var6 = OWorldSettings.a(var6);
        a.info("Default game type: " + var6);
        boolean var7 = this.d.a("generate-structures", true); // get boolean property
        OWorldSettings var8 = new OWorldSettings(var3, var6, var7, false, var5);
        OAnvilSaveHandler var9 = new OAnvilSaveHandler(new File("."), var2, true);

        for (int var10 = 0; var10 < this.e.length; ++var10) {
            byte var11 = 0;
            if (var10 == 1) {
                var11 = -1;
            }

            if (var10 == 2) {
                var11 = 1;
            }

            if (var10 == 0) {
                this.e[var10] = new OWorldServer(this, var9, var2, var11, var8);
            } else {
                this.e[var10] = new OWorldServerMulti(this, var9, var2, var11, var8, this.e[0]);
            }

            this.e[var10].a(new OWorldManager(this, this.e[var10]));
            this.e[var10].q = this.d.a("difficulty", 1); // get int property
            this.e[var10].a(this.d.a("spawn-monsters", true), this.o); // get boolean property
            this.e[var10].s().d(var6);
            this.h.a(this.e);
        }

        short var23 = 196;
        long var12 = System.currentTimeMillis();

        // var14 is level: dimension. 0 = overworld.
        for (int var14 = 0; var14 < 1; ++var14) {
            a.info("Preparing start region for level " + var14);
            OWorldServer var15 = this.e[var14];
            OChunkCoordinates var16 = var15.p();

            for (int var17 = -var23; var17 <= var23 && this.B; var17 += 16) {
                for (int var18 = -var23; var18 <= var23 && this.B; var18 += 16) {
                    long var19 = System.currentTimeMillis();
                    if (var19 < var12) {
                        var12 = var19;
                    }

                    if (var19 > var12 + 1000L) {
                        int var21 = (var23 * 2 + 1) * (var23 * 2 + 1);
                        int var22 = (var17 + var23) * (var23 * 2 + 1) + var18 + 1;
                        this.b("Preparing spawn area", var22 * 100 / var21); // print the percentage
                        var12 = var19;
                    }

                    // loads the spawn chunk
                    var15.G.c(var16.a + var17 >> 4, var16.c + var18 >> 4);

                    // updates all lighting, unless the server stops
                    while (var15.z() && this.B);
                }
            }
        }

        this.t();
    }

    private void b(String var1, int var2) {
        this.k = var1;
        this.l = var2;
        a.info(var1 + ": " + var2 + "%");
    }

    private void t() {
        this.k = null;
        this.l = 0;
    }

    private void u() {
        a.info("Saving chunks");

        for (int var1 = 0; var1 < this.e.length; ++var1) {
            OWorldServer var2 = this.e[var1];

            // saves the world
            try {
                var2.a(true, (OIProgressUpdate) null);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            var2.A();
        }
    }

    private void v() {
        a.info("Stopping server");
        if (this.h != null) {
            // save the player states
            this.h.g();
        }

        // for each world
        for (int var1 = 0; var1 < this.e.length; ++var1) {
            OWorldServer var2 = this.e[var1];
            if (var2 != null) {
                this.u(); // save server world
            }
        }
    }

    public void a() {
        this.B = false;
    }

    public void run() {
        boolean var59 = false;

        label595: {
            try {
                var59 = true;
                if (this.s()) {
                    long var1 = System.currentTimeMillis();

                    for (long var3 = 0L; this.B; Thread.sleep(1L)) {
                        long var5 = System.currentTimeMillis();
                        long var7 = var5 - var1;
                        if (var7 > 2000L) {
                            a.warning("Can\'t keep up! Did the system time change, or is the server overloaded?");
                            var7 = 2000L;
                        }

                        if (var7 < 0L) {
                            a.warning("Time ran backwards! Did the system time change?");
                            var7 = 0L;
                        }

                        var3 += var7;
                        var1 = var5;
                        if (this.e[0].v()) {
                            this.w();
                            var3 = 0L;
                        } else {
                            while (var3 > 50L) {
                                var3 -= 50L;
                                this.w();
                            }
                        }
                    }

                    var59 = false;
                } else {
                    while (this.B) {
                        this.b();

                        try {
                            Thread.sleep(10L);
                        } catch (InterruptedException var61) {
                            var61.printStackTrace();
                        }
                    }

                    var59 = false;
                }
                break label595;
            } catch (Throwable var68) {
                var68.printStackTrace();
                a.log(Level.SEVERE, "Unexpected exception", var68);

                while (true) {
                    if (!this.B) {
                        var59 = false;
                        break;
                    }

                    this.b();

                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException var60) {
                        var60.printStackTrace();
                    }
                }
            } finally {
                if (var59) {
                    boolean var48 = false;

                    label523: {
                        label522: {
                            try {
                                var48 = true;
                                this.v();
                                this.i = true;
                                var48 = false;
                                break label522;
                            } catch (Throwable var62) {
                                var62.printStackTrace();
                                var48 = false;
                            } finally {
                                if (var48) {
                                    System.exit(0);
                                }
                            }

                            System.exit(0);
                            break label523;
                        }

                        System.exit(0);
                    }

                }
            }

            boolean var37 = false;

            label596: {
                try {
                    var37 = true;
                    this.v();
                    this.i = true;
                    var37 = false;
                    break label596;
                } catch (Throwable var64) {
                    var64.printStackTrace();
                    var37 = false;
                } finally {
                    if (var37) {
                        System.exit(0);
                    }
                }

                System.exit(0);
                return;
            }

            System.exit(0);
            return;
        }

        boolean var26 = false;

        label597: {
            try {
                var26 = true;
                this.v();
                this.i = true;
                var26 = false;
                break label597;
            } catch (Throwable var66) {
                var66.printStackTrace();
                var26 = false;
            } finally {
                if (var26) {
                    System.exit(0);
                }
            }

            System.exit(0);
            return;
        }

        System.exit(0);
    }

    private void w() {
        long var1 = System.nanoTime();
        ArrayList var3 = new ArrayList();
        Iterator var4 = b.keySet().iterator();

        while (var4.hasNext()) {
            String var5 = (String) var4.next();
            int var6 = ((Integer) b.get(var5)).intValue();
            if (var6 > 0) {
                b.put(var5, Integer.valueOf(var6 - 1));
            } else {
                var3.add(var5);
            }
        }

        int var11;
        for (var11 = 0; var11 < var3.size(); ++var11) {
            b.remove(var3.get(var11));
        }

        OAxisAlignedBB.a();
        OVec3D.a();
        ++this.j;

        for (var11 = 0; var11 < this.e.length; ++var11) {
            long var7 = System.nanoTime();
            if (var11 == 0 || this.d.a("allow-nether", true)) {
                OWorldServer var9 = this.e[var11];
                if (this.j % 20 == 0) {
                    this.h.a((new OPacket4UpdateTime(var9.o())), var9.t.g);
                }

                try {
                    var9.h();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                while (true) {
                    if (!var9.z()) {
                        var9.f();
                        break;
                    }
                }
            }

            this.g[var11][this.j % 100] = System.nanoTime() - var7;
        }

        this.c.a();
        this.h.b();

        for (var11 = 0; var11 < this.m.length; ++var11) {
            this.m[var11].a();
        }

        for (var11 = 0; var11 < this.C.size(); ++var11) {
            ((OIUpdatePlayerListBox) this.C.get(var11)).a();
        }

        try {
            this.b();
        } catch (Exception var10) {
            a.log(Level.WARNING, "Unexpected exception while parsing console command", var10);
        }

        this.f[this.j % 100] = System.nanoTime() - var1;
        this.u[this.j % 100] = OPacket.n - this.E;
        this.E = OPacket.n;
        this.v[this.j % 100] = OPacket.o - this.F;
        this.F = OPacket.o;
        this.w[this.j % 100] = OPacket.l - this.G;
        this.G = OPacket.l;
        this.x[this.j % 100] = OPacket.m - this.H;
        this.H = OPacket.m;
    }

    public void a(String var1, OICommandListener var2) {
        this.D.add(new OServerCommand(var1, var2));
    }

    public void b() {
        while (this.D.size() > 0) {
            OServerCommand var1 = (OServerCommand) this.D.remove(0);
            this.A.a(var1);
        }

    }

    public void a(OIUpdatePlayerListBox var1) {
        this.C.add(var1);
    }

    public static void main(String[] var0) {
        OStatList.a();

        try {
            OMinecraftServer var1 = new OMinecraftServer();
            if (!GraphicsEnvironment.isHeadless() && (var0.length <= 0 || !var0[0].equals("nogui"))) {
                OServerGUI.a(var1);
            }

            (new OThreadServerApplication("Server thread", var1)).start();
        } catch (Exception var2) {
            a.log(Level.SEVERE, "Failed to start the minecraft server", var2);
        }

    }

    public File a(String var1) {
        return new File(var1);
    }

    public void b(String var1) {
        a.info(var1);
    }

    public void c(String var1) {
        a.warning(var1);
    }

    public String d() {
        return "CONSOLE";
    }

    public OWorldServer a(int var1) {
        return var1 == -1 ? this.e[1] : (var1 == 1 ? this.e[2] : this.e[0]);
    }

    public OEntityTracker b(int var1) {
        return var1 == -1 ? this.m[1] : (var1 == 1 ? this.m[2] : this.m[0]);
    }

    public int a(String var1, int var2) {
        return this.d.a(var1, var2);
    }

    public String a(String var1, String var2) {
        return this.d.a(var1, var2);
    }

    public void a(String var1, Object var2) {
        this.d.a(var1, var2);
    }

    public void c() {
        this.d.b();
    }

    public String e() {
        File var1 = this.d.c();
        return var1 != null ? var1.getAbsolutePath() : "No settings file";
    }

    public String f() {
        return this.y;
    }

    public int g() {
        return this.z;
    }

    public String h() {
        return this.s;
    }

    public String i() {
        return "1.2.5";
    }

    public int j() {
        return this.h.j();
    }

    public int k() {
        return this.h.k();
    }

    public String[] l() {
        return this.h.d();
    }

    public String m() {
        return this.d.a("level-name", "world");
    }

    public String n() {
        return "";
    }

    public void o() {
    }

    public String d(String var1) {
        ORConConsoleSource.a.a();
        this.A.a(new OServerCommand(var1, ORConConsoleSource.a));
        return ORConConsoleSource.a.b();
    }

    public boolean p() {
        return false;
    }

    public void e(String var1) {
        a.log(Level.SEVERE, var1);
    }

    public void f(String var1) {
        if (this.p()) {
            a.log(Level.INFO, var1);
        }

    }

    public String[] q() {
        return (String[]) this.h.f().toArray(new String[0]);
    }

    public String[] r() {
        return (String[]) this.h.e().toArray(new String[0]);
    }

    public String getServerModName() {
        return "vanilla";
    }

    // $FF: synthetic method
    public static boolean a(OMinecraftServer var0) {
        return var0.B;
    }

}
