package net.minecraft.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import net.canarymod.Canary;
import net.canarymod.CanaryServer;
import net.canarymod.api.CanaryConfigurationManager;
import net.canarymod.api.entity.CanaryPlayer;
import net.canarymod.api.entity.Player;
import net.canarymod.api.world.CanaryDimension;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.World;
import net.minecraft.server.OChunkCoordinates;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntityPlayerMP;
import net.minecraft.server.OIPlayerFileData;
import net.minecraft.server.OItemInWorldManager;
import net.minecraft.server.OMinecraftServer;
import net.minecraft.server.ONetLoginHandler;
import net.minecraft.server.OPacket;
import net.minecraft.server.OPacket201PlayerInfo;
import net.minecraft.server.OPacket3Chat;
import net.minecraft.server.OPacket4UpdateTime;
import net.minecraft.server.OPacket70Bed;
import net.minecraft.server.OPacket9Respawn;
import net.minecraft.server.OPlayerManager;
import net.minecraft.server.OPlayerUsageSnooper;
import net.minecraft.server.OTeleporter;
import net.minecraft.server.OTileEntity;
import net.minecraft.server.OWorldServer;

public class OServerConfigurationManager {

    public static Logger a = Logger.getLogger("Minecraft");
    public List<OEntityPlayerMP> b = new ArrayList<OEntityPlayerMP>(); //CanaryMod parameterized
    private OMinecraftServer c;
    private OPlayerManager[] d = new OPlayerManager[3];
    private int e;
    private Set f = new HashSet();
    private Set g = new HashSet();
    private Set h = new HashSet();
    private Set i = new HashSet();
    private File j;
    private File k;
    private File l;
    private File m;
//    private OIPlayerFileData n;
    private HashMap<String, OIPlayerFileData> playerFileDataSet;
    private boolean o;
    private int p = 0;
    private CanaryConfigurationManager canaryCfgManager;

    public OServerConfigurationManager(OMinecraftServer var1) {
        //CanaryMod NOTE: Canary.setServer() has been called during construction of OMCS which is being passed along here
        //Just for the records
        super();
        this.c = var1;
        this.j = var1.a("banned-players.txt");
        this.k = var1.a("banned-ips.txt");
        this.l = var1.a("ops.txt");
        this.m = var1.a("white-list.txt");
        int var2 = var1.d.a("view-distance", 10);
        this.d[0] = new OPlayerManager(var1, 0, var2);
        this.d[1] = new OPlayerManager(var1, -1, var2);
        this.d[2] = new OPlayerManager(var1, 1, var2);
        this.e = var1.d.a("max-players", 20);
        this.o = var1.d.a("white-list", false);
        this.l();
        this.n();
        this.p();
        this.r();
        this.m();
        this.o();
        this.q();
        this.s();
        canaryCfgManager = new CanaryConfigurationManager(this);
    }

    /**
     * CanaryMod: Return the cfg manager wrapper
     * @return the canaryCfgManager
     */
    public CanaryConfigurationManager getCanaryConfigurationManager() {
        return canaryCfgManager;
    }

    public void a(OWorldServer[] var1) {
//        this.n = var1[0].r().d();
        //CanaryMod modified section to contain a per-world player info set
        if(this.playerFileDataSet == null) {
            this.playerFileDataSet = new HashMap<String, OIPlayerFileData>();
        }
        this.playerFileDataSet.put(var1[0].getCanaryDimension().getName(), var1[0].r().d());
    }

    public void a(OEntityPlayerMP var1) {
        this.d[0].b(var1);
        this.d[1].b(var1);
        this.d[2].b(var1);
        this.a(var1.w).a(var1);
        OWorldServer var2 = this.c.a(var1.w);
        var2.G.c((int) var1.bm >> 4, (int) var1.bo >> 4);
    }

    public int a() {
        return this.d[0].c();
    }

    private OPlayerManager a(int var1) {
        return var1 == -1 ? this.d[1] : (var1 == 0 ? this.d[0] : (var1 == 1 ? this.d[2] : null));
    }

    public void b(OEntityPlayerMP var1) {
//      this.n.b(var1); //CanaryMod refactored
        this.playerFileDataSet.get(var1.getPlayer().getDimension().getName()).b(var1);
    }

    public void c(OEntityPlayerMP var1) {
        this.a((new OPacket201PlayerInfo(var1.v, true, 1000)));
        this.b.add(var1);
        OWorldServer var2 = this.c.a(var1.w);
        var2.G.c((int) var1.bm >> 4, (int) var1.bo >> 4);

        while (var2.a(var1, var1.bw).size() != 0) {
            var1.c(var1.bm, var1.bn + 1.0D, var1.bo);
        }

        var2.b(var1);
        this.a(var1.w).a(var1);
        this.u();

        for (int var3 = 0; var3 < this.b.size(); ++var3) {
            OEntityPlayerMP var4 = (OEntityPlayerMP) this.b.get(var3);
            var1.a.b((new OPacket201PlayerInfo(var4.v, true, var4.i)));
        }

    }

    public void d(OEntityPlayerMP var1) {
        this.a(var1.w).c(var1);
    }

    public void e(OEntityPlayerMP var1) {
//        this.n.a(var1); //CanaryMod refactored
        this.playerFileDataSet.get(var1.getPlayer().getDimension().getName()).a(var1);
        this.c.a(var1.w).e(var1);
        this.b.remove(var1);
        this.a(var1.w).b(var1);
        this.a((new OPacket201PlayerInfo(var1.v, false, 9999)));
    }

    public OEntityPlayerMP a(ONetLoginHandler var1, String var2) {
        if (this.f.contains(var2.trim().toLowerCase())) {
            var1.a("You are banned from this server!");
            return null;
        } else if (!this.g(var2)) {
            var1.a("You are not white-listed on this server!");
            return null;
        } else {
            String var3 = var1.b.c().toString();
            var3 = var3.substring(var3.indexOf("/") + 1);
            var3 = var3.substring(0, var3.indexOf(":"));
            if (this.g.contains(var3)) {
                var1.a("Your IP address is banned from this server!");
                return null;
            } else if (this.b.size() >= this.e) {
                var1.a("The server is full!");
                return null;
            } else {
                for (int var4 = 0; var4 < this.b.size(); ++var4) {
                    OEntityPlayerMP var5 = (OEntityPlayerMP) this.b.get(var4);
                    if (var5.v.equalsIgnoreCase(var2)) {
                        var5.a.a("You logged in from another location");
                    }
                }

                return new OEntityPlayerMP(this.c, this.c.a(0), var2, new OItemInWorldManager(this.c.a(0)));
            }
        }
    }

    public OEntityPlayerMP a(OEntityPlayerMP var1, int var2, boolean var3) {
        this.c.b(var1.w).a(var1);
        this.c.b(var1.w).b(var1);
        this.a(var1.w).b(var1);
        this.b.remove(var1);
        this.c.a(var1.w).f(var1);
        OChunkCoordinates var4 = var1.ab();
        var1.w = var2;
        OEntityPlayerMP var5 = new OEntityPlayerMP(this.c, this.c.a(var1.w), var1.v, new OItemInWorldManager(this.c.a(var1.w)));
        if (var3) {
            var5.c(var1);
        }

        var5.bd = var1.bd;
        var5.a = var1.a;
        OWorldServer var6 = this.c.a(var1.w);
        var5.c.a(var1.c.a());
        var5.c.b(var6.s().m());
        if (var4 != null) {
            OChunkCoordinates var7 = OEntityPlayer.a(this.c.a(var1.w), var4);
            if (var7 != null) {
                var5.c((var7.a + 0.5F), (var7.b + 0.1F), (var7.c + 0.5F), 0.0F, 0.0F);
                var5.a(var4);
            } else {
                var5.a.b((new OPacket70Bed(0, 0)));
            }
        }

        var6.G.c((int) var5.bm >> 4, (int) var5.bo >> 4);

        while (var6.a(var5, var5.bw).size() != 0) {
            var5.c(var5.bm, var5.bn + 1.0D, var5.bo);
        }

        var5.a.b((new OPacket9Respawn(var5.w, (byte) var5.bi.q, var5.bi.s().p(), var5.bi.y(), var5.c.a())));
        var5.a.a(var5.bm, var5.bn, var5.bo, var5.bs, var5.bt);
        this.a(var5, var6);
        this.a(var5.w).a(var5);
        var6.b(var5);
        this.b.add(var5);
        var5.x();
        var5.E();
        return var5;
    }

    public void a(OEntityPlayerMP var1, int var2) {
        int var3 = var1.w;
        OWorldServer var4 = this.c.a(var1.w);
        var1.w = var2;
        OWorldServer var5 = this.c.a(var1.w);
        var1.a.b((new OPacket9Respawn(var1.w, (byte) var1.bi.q, var5.s().p(), var5.y(), var1.c.a())));
        var4.f(var1);
        var1.bE = false;
        double var6 = var1.bm;
        double var8 = var1.bo;
        double var10 = 8.0D;
        if (var1.w == -1) {
            var6 /= var10;
            var8 /= var10;
            var1.c(var6, var1.bn, var8, var1.bs, var1.bt);
            if (var1.aE()) {
                var4.a(var1, false);
            }
        } else if (var1.w == 0) {
            var6 *= var10;
            var8 *= var10;
            var1.c(var6, var1.bn, var8, var1.bs, var1.bt);
            if (var1.aE()) {
                var4.a(var1, false);
            }
        } else {
            OChunkCoordinates var12 = var5.d();
            var6 = var12.a;
            var1.bn = var12.b;
            var8 = var12.c;
            var1.c(var6, var1.bn, var8, 90.0F, 0.0F);
            if (var1.aE()) {
                var4.a(var1, false);
            }
        }

        if (var3 != 1 && var1.aE()) {
            var5.b(var1);
            var1.c(var6, var1.bn, var8, var1.bs, var1.bt);
            var5.a(var1, false);
            var5.G.a = true;
            (new OTeleporter()).a(var5, var1);
            var5.G.a = false;
        }

        this.a(var1);
        var1.a.a(var1.bm, var1.bn, var1.bo, var1.bs, var1.bt);
        var1.a(var5);
        var1.c.a(var5);
        this.a(var1, var5);
        this.f(var1);
    }

    public void b() {
        if (++this.p > 200) {
            this.p = 0;
        }

        if (this.p < this.b.size()) {
            OEntityPlayerMP var1 = (OEntityPlayerMP) this.b.get(this.p);
            this.a((new OPacket201PlayerInfo(var1.v, true, var1.i)));
        }

        for (int var2 = 0; var2 < this.d.length; ++var2) {
            this.d[var2].b();
        }

    }

    public void a(int var1, int var2, int var3, int var4) {
        this.a(var4).a(var1, var2, var3);
    }

    public void a(OPacket var1) {
        for (int var2 = 0; var2 < this.b.size(); ++var2) {
            OEntityPlayerMP var3 = (OEntityPlayerMP) this.b.get(var2);
            var3.a.b(var1);
        }

    }

    public void a(OPacket var1, int var2) {
        for (int var3 = 0; var3 < this.b.size(); ++var3) {
            OEntityPlayerMP var4 = (OEntityPlayerMP) this.b.get(var3);
            if (var4.w == var2) {
                var4.a.b(var1);
            }
        }

    }

    public String c() {
        String var1 = "";

        for (int var2 = 0; var2 < this.b.size(); ++var2) {
            if (var2 > 0) {
                var1 = var1 + ", ";
            }

            var1 = var1 + ((OEntityPlayerMP) this.b.get(var2)).v;
        }

        return var1;
    }

    public String[] d() {
        String[] var1 = new String[this.b.size()];

        for (int var2 = 0; var2 < this.b.size(); ++var2) {
            var1[var2] = ((OEntityPlayerMP) this.b.get(var2)).v;
        }

        return var1;
    }

    public void a(String var1) {
        this.f.add(var1.toLowerCase());
        this.m();
    }

    public void b(String var1) {
        this.f.remove(var1.toLowerCase());
        this.m();
    }

    private void l() {
        try {
            this.f.clear();
            BufferedReader var1 = new BufferedReader(new FileReader(this.j));
            String var2 = "";

            while ((var2 = var1.readLine()) != null) {
                this.f.add(var2.trim().toLowerCase());
            }

            var1.close();
        } catch (Exception var3) {
            a.warning("Failed to load ban list: " + var3);
        }

    }

    private void m() {
        try {
            PrintWriter var1 = new PrintWriter(new FileWriter(this.j, false));
            Iterator var2 = this.f.iterator();

            while (var2.hasNext()) {
                String var3 = (String) var2.next();
                var1.println(var3);
            }

            var1.close();
        } catch (Exception var4) {
            a.warning("Failed to save ban list: " + var4);
        }

    }

    public Set e() {
        return this.f;
    }

    public Set f() {
        return this.g;
    }

    public void c(String var1) {
        this.g.add(var1.toLowerCase());
        this.o();
    }

    public void d(String var1) {
        this.g.remove(var1.toLowerCase());
        this.o();
    }

    private void n() {
        try {
            this.g.clear();
            BufferedReader var1 = new BufferedReader(new FileReader(this.k));
            String var2 = "";

            while ((var2 = var1.readLine()) != null) {
                this.g.add(var2.trim().toLowerCase());
            }

            var1.close();
        } catch (Exception var3) {
            a.warning("Failed to load ip ban list: " + var3);
        }

    }

    private void o() {
        try {
            PrintWriter var1 = new PrintWriter(new FileWriter(this.k, false));
            Iterator var2 = this.g.iterator();

            while (var2.hasNext()) {
                String var3 = (String) var2.next();
                var1.println(var3);
            }

            var1.close();
        } catch (Exception var4) {
            a.warning("Failed to save ip ban list: " + var4);
        }

    }

    public void e(String var1) {
        this.h.add(var1.toLowerCase());
        this.q();
    }

    public void f(String var1) {
        this.h.remove(var1.toLowerCase());
        this.q();
    }

    private void p() {
        try {
            this.h.clear();
            BufferedReader var1 = new BufferedReader(new FileReader(this.l));
            String var2 = "";

            while ((var2 = var1.readLine()) != null) {
                this.h.add(var2.trim().toLowerCase());
            }

            var1.close();
        } catch (Exception var3) {
            a.warning("Failed to load operators list: " + var3);
        }

    }

    private void q() {
        try {
            PrintWriter var1 = new PrintWriter(new FileWriter(this.l, false));
            Iterator var2 = this.h.iterator();

            while (var2.hasNext()) {
                String var3 = (String) var2.next();
                var1.println(var3);
            }

            var1.close();
        } catch (Exception var4) {
            a.warning("Failed to save operators list: " + var4);
        }

    }

    private void r() {
        try {
            this.i.clear();
            BufferedReader var1 = new BufferedReader(new FileReader(this.m));
            String var2 = "";

            while ((var2 = var1.readLine()) != null) {
                this.i.add(var2.trim().toLowerCase());
            }

            var1.close();
        } catch (Exception var3) {
            a.warning("Failed to load white-list: " + var3);
        }

    }

    private void s() {
        try {
            PrintWriter var1 = new PrintWriter(new FileWriter(this.m, false));
            Iterator var2 = this.i.iterator();

            while (var2.hasNext()) {
                String var3 = (String) var2.next();
                var1.println(var3);
            }

            var1.close();
        } catch (Exception var4) {
            a.warning("Failed to save white-list: " + var4);
        }

    }

    public boolean g(String var1) {
        var1 = var1.trim().toLowerCase();
        return !this.o || this.h.contains(var1) || this.i.contains(var1);
    }

    public boolean h(String var1) {
        return this.h.contains(var1.trim().toLowerCase());
    }

    public OEntityPlayerMP i(String var1) {
        for (int var2 = 0; var2 < this.b.size(); ++var2) {
            OEntityPlayerMP var3 = (OEntityPlayerMP) this.b.get(var2);
            if (var3.v.equalsIgnoreCase(var1)) {
                return var3;
            }
        }

        return null;
    }

    public void a(String var1, String var2) {
        OEntityPlayerMP var3 = this.i(var1);
        if (var3 != null) {
            var3.a.b((new OPacket3Chat(var2)));
        }

    }

    public void a(double var1, double var3, double var5, double var7, int var9, OPacket var10) {
        this.a((OEntityPlayer) null, var1, var3, var5, var7, var9, var10);
    }

    public void a(OEntityPlayer var1, double var2, double var4, double var6, double var8, int var10, OPacket var11) {
        for (int var12 = 0; var12 < this.b.size(); ++var12) {
            OEntityPlayerMP var13 = (OEntityPlayerMP) this.b.get(var12);
            if (var13 != var1 && var13.w == var10) {
                double var14 = var2 - var13.bm;
                double var16 = var4 - var13.bn;
                double var18 = var6 - var13.bo;
                if (var14 * var14 + var16 * var16 + var18 * var18 < var8 * var8) {
                    var13.a.b(var11);
                }
            }
        }

    }

    public void j(String var1) {
        OPacket3Chat var2 = new OPacket3Chat(var1);

        for (int var3 = 0; var3 < this.b.size(); ++var3) {
            OEntityPlayerMP var4 = (OEntityPlayerMP) this.b.get(var3);
            if (this.h(var4.v)) {
                var4.a.b(var2);
            }
        }

    }

    public boolean a(String var1, OPacket var2) {
        OEntityPlayerMP var3 = this.i(var1);
        if (var3 != null) {
            var3.a.b(var2);
            return true;
        } else {
            return false;
        }
    }

    public void g() {
        //CanaryMod refactored for multiworld purposes
//        for (int var1 = 0; var1 < this.b.size(); ++var1) {
//            this.n.a((OEntityPlayer) this.b.get(var1));
//        }
        for(Player player : getCanaryConfigurationManager().getAllPlayers()) {
            this.playerFileDataSet.get(player.getDimension().getName()).a((OEntityPlayer) ((CanaryPlayer)player).getHandle());
        }
        //CanaryMod end

    }

    public void a(int var1, int var2, int var3, OTileEntity var4) {
    }

    public void k(String var1) {
        this.i.add(var1);
        this.s();
    }

    public void l(String var1) {
        this.i.remove(var1);
        this.s();
    }

    public Set h() {
        return this.i;
    }

    public void i() {
        this.r();
    }

    public void a(OEntityPlayerMP var1, OWorldServer var2) {
        var1.a.b((new OPacket4UpdateTime(var2.o())));
        if (var2.x()) {
            var1.a.b((new OPacket70Bed(1, 0)));
        }

    }

    public void f(OEntityPlayerMP var1) {
        var1.a(var1.l);
        var1.D_();
    }

    public int j() {
        return this.b.size();
    }

    public int k() {
        return this.e;
    }

    public String[] t() {
        //CanaryMod refactored to incorporate everythign from anywhere
        ArrayList<String> playersSeen = new ArrayList<String>();
        for(World w : this.c.getWorldManager().getAllWorlds()) {
            CanaryDimension dim = (CanaryDimension) ((CanaryWorld)w).getDimensions()[0];
            for(String s : dim.getHandle().r().d().g()) {
                playersSeen.add(s);
            }
        }
        return playersSeen.toArray(new String[playersSeen.size()]);
    }

    private void u() {
        OPlayerUsageSnooper var1 = new OPlayerUsageSnooper("server");
        var1.a("version", this.c.i());
        var1.a("os_name", System.getProperty("os.name"));
        var1.a("os_version", System.getProperty("os.version"));
        var1.a("os_architecture", System.getProperty("os.arch"));
        var1.a("memory_total", Long.valueOf(Runtime.getRuntime().totalMemory()));
        var1.a("memory_max", Long.valueOf(Runtime.getRuntime().maxMemory()));
        var1.a("memory_free", Long.valueOf(Runtime.getRuntime().freeMemory()));
        var1.a("java_version", System.getProperty("java.version"));
        var1.a("cpu_cores", Integer.valueOf(Runtime.getRuntime().availableProcessors()));
        var1.a("players_current", Integer.valueOf(this.j()));
        var1.a("players_max", Integer.valueOf(this.k()));
        var1.a("players_seen", Integer.valueOf(this.t().length));
        var1.a("uses_auth", Boolean.valueOf(this.c.n));
        var1.a("server_brand", this.c.getServerModName());
        var1.a();
    }

}
