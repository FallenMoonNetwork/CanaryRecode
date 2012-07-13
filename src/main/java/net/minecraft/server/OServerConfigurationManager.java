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
import net.canarymod.api.CanaryConfigurationManager;
import net.canarymod.api.CanaryNetServerHandler;
import net.canarymod.api.CanaryPlayerManager;
import net.canarymod.api.entity.CanaryPlayer;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.Player;
import net.canarymod.api.world.CanaryDimension;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.Dimension;
import net.canarymod.api.world.Dimension.Type;
import net.canarymod.api.world.World;
import net.canarymod.api.world.position.Location;
import net.canarymod.config.Configuration;
import net.canarymod.hook.player.LoginChecksHook;
import net.canarymod.hook.player.LoginHook;
import net.canarymod.permissionsystem.PermissionNode;
import net.canarymod.permissionsystem.PermissionProvider;
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
import net.minecraft.server.OPlayerUsageSnooper;
import net.minecraft.server.OTeleporter;
import net.minecraft.server.OTileEntity;
import net.minecraft.server.OWorldServer;

public class OServerConfigurationManager {

    public static Logger a = Logger.getLogger("Minecraft");
    public List<OEntityPlayerMP> b = new ArrayList<OEntityPlayerMP>(); //CanaryMod parameterized
    private OMinecraftServer c;
//    private OPlayerManager[] d = new OPlayerManager[3];
    private int e;
    private Set f = new HashSet();
    private Set g = new HashSet();
    //private Set<String> operators = new HashSet<String>();
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
        // CanaryMod start: changing configurations
        //this.j = var1.a("banned-players.txt");
        //this.k = var1.a("banned-ips.txt");
        //this.l = var1.a("ops.txt");
        //this.m = var1.a("white-list.txt");
        //int var2 = Configuration.getNetConfig().getViewDistance();
        // Creates player managers per dimension (MW: create one per dimension per world)
        // this.d[0] = new OPlayerManager(var1, 0, var2);
        // this.d[1] = new OPlayerManager(var1, -1, var2);
        // this.d[2] = new OPlayerManager(var1, 1, var2);
        this.e = Configuration.getNetConfig().getMaxPlayers();
        // CanaryMod: disable vanilla whitelisting
        this.o = false;
        // CanaryMod: don't load the default whitelists and bans
        //this.l();
        //this.n();
        //this.loadOperatorList();
        //this.r();
        //this.m();
        //this.o();
        //this.q();
        //this.s();
        canaryCfgManager = new CanaryConfigurationManager(this);
        var1.setCanaryConfigurationmanager(canaryCfgManager);
    }
    
    public void reload() {
        this.e = Configuration.getNetConfig().getMaxPlayers();
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
//        this.d[0].b(var1);
//        this.d[1].b(var1);
//        this.d[2].b(var1);
        for(World w : Canary.getServer().getWorldManager().getAllWorlds()) {
            for(Dimension dim : w.getDimensions()) {
                dim.getPlayerManager().removePlayer(var1.getPlayer());
            }
        }
//        this.a(var1.w).a(var1); //remove again? rly?
        
//        OWorldServer var2 = this.c.a(var1.w);
        var1.getDimension().getPlayerManager().addPlayer(var1.getPlayer());
        OWorldServer var2 = (OWorldServer) ((CanaryDimension)var1.getDimension()).getHandle();
        var2.G.c((int) var1.bm >> 4, (int) var1.bo >> 4);
    }

    @Deprecated
    public int a() {
        throw new UnsupportedOperationException("OServerConfigurationManager"
                + ".a() has been replaced by OServerConfigurationManager"
                + ".getMaxTrackingDistance(String).");
    }
    
    /**
     * Do not call this before the world isn't saved in the world manager!
     * @param world
     * @return
     */
    public int getMaxTrackingDistance(String world) {
       return Canary.getServer().getWorld(world).getNormal().getPlayerManager().getMaxTrackingDistance();
    }
//CanaryMod playermanagers are now stored per world, this isn't needed
//    private OPlayerManager a(int var1) {
//        throw new UnsupportedOperationException("OServerConfigurationManager.a"
//                + "(int) has been replaced by OServerConfigurationManager.get"
//                + "Manager(String, int).");
//    }

    public void b(OEntityPlayerMP var1) {
//      this.n.b(var1); //CanaryMod refactored
        this.playerFileDataSet.get(var1.getPlayer().getDimension().getName()).b(var1);
    }

    public void c(OEntityPlayerMP var1) {
        this.sendPacketToAll((new OPacket201PlayerInfo(var1.v, true, 1000)));
        this.b.add(var1);
        OWorldServer var2 = (OWorldServer) ((CanaryDimension)var1.getDimension().getWorld().getDimension(Type.fromId(var1.w))).getHandle();
        var1.bi = var2; //re-set world
        var2.G.c((int) var1.bm >> 4, (int) var1.bo >> 4);

        while (var2.a(var1, var1.bw).size() != 0) {
            var1.c(var1.bm, var1.bn + 1.0D, var1.bo);
        }

        var2.b(var1);
        //CanaryMod
        var1.getDimension().getPlayerManager().addPlayer(var1.getPlayer());
        this.u();

        for (int var3 = 0; var3 < this.b.size(); ++var3) {
            OEntityPlayerMP var4 = (OEntityPlayerMP) this.b.get(var3);
            var1.a.b((new OPacket201PlayerInfo(var4.v, true, var4.i)));
        }
        Canary.hooks().callHook(new LoginHook(var1.getPlayer()));
        var1.getPlayer().refreshCreativeMode();

    }

    public void d(OEntityPlayerMP var1) {
        //Canary
        var1.getDimension().getPlayerManager().updateMountedMovingPlayer(var1.getPlayer());
    }

    public void e(OEntityPlayerMP var1) {
//        this.n.a(var1); //CanaryMod refactored
        this.playerFileDataSet.get(var1.getPlayer().getDimension().getName()).a(var1);
        //CanaryMod refactored
        ((CanaryDimension)var1.getDimension()).getHandle().e(var1);
        this.b.remove(var1);
        var1.getDimension().getPlayerManager().removePlayer(var1.getPlayer());
//        this.a(var1.w).b(var1);
        this.sendPacketToAll((new OPacket201PlayerInfo(var1.v, false, 9999)));
    }

    public OEntityPlayerMP a(ONetLoginHandler var1, String var2) {
        String var3 = var1.b.c().toString();
        //IP
        var3 = var3.substring(var3.indexOf("/") + 1);
        var3 = var3.substring(0, var3.indexOf(":"));
        Dimension dim = Canary.getServer().getDefaultWorld().getNormal();
        LoginChecksHook hook = new LoginChecksHook(var3, var2, dim.getType(), dim.getName());
        Canary.hooks().callHook(hook);
        if (hook.getKickReason() != null && !hook.getKickReason().trim().equals("")) {
            var1.a(hook.getKickReason());
        }
        // CanaryMod - end
        //TODO: Refactor to use canary white listing, banning etc etc
        
        if (this.f.contains(var2.trim().toLowerCase())) {
            var1.a("You are banned from this server!");
            return null;
        } else if (!this.isAllowedToLogin(var2)) {
            var1.a("You are not white-listed on this server!");
            return null;
        } else if (this.g.contains(var3)) {
            var1.a("Your IP address is banned from this server!");
            return null;
        } else {
            if (this.b.size() >= this.e) {
                var1.a("The server is full!");
                return null;
            } else {
                for (int var4 = 0; var4 < this.b.size(); ++var4) {
                    OEntityPlayerMP var5 = (OEntityPlayerMP) this.b.get(var4);
                    if (var5.v.equalsIgnoreCase(var2)) {
                        var5.a.a("You logged in from another location");
                    }
                }
                OWorldServer dimension = (OWorldServer) ((CanaryDimension) Canary.getServer().getWorld(hook.getWorld()).getDimension(hook.getDimensionType())).getHandle();
                return new OEntityPlayerMP(this.c, dimension, var2, new OItemInWorldManager(dimension));
            }
        }
    }

    // CanaryMod alias to set location when respawning.
    public OEntityPlayerMP a(OEntityPlayerMP var1, int var2, boolean var3) {
        return a(var1, var2, var3, null);
    }
    
    //Respawn player
    public OEntityPlayerMP a(OEntityPlayerMP var1, int var2, boolean var3, Location spawnLocation) {
        var1.getDimension().getEntityTracker().untrackPlayerSymmetrics(var1.getPlayer());
        var1.getDimension().getEntityTracker().untrackEntity(var1.getCanaryEntity());
        var1.getDimension().getPlayerManager().removePlayer(var1.getPlayer());
        this.b.remove(var1);
        var1.getDimension().removePlayerFromWorld(var1.getPlayer()); //This calls the despawn method in world!
        
        OChunkCoordinates var4 = var1.ab();
        var1.w = var2; //Set new dimension
        CanaryWorld cworld = (CanaryWorld) var1.getDimension().getWorld();
        CanaryDimension dim = (CanaryDimension) cworld.getDimension(Type.fromId(var2));
        OEntityPlayerMP var5 = new OEntityPlayerMP(this.c, dim.getHandle(), var1.v, new OItemInWorldManager(dim.getHandle()));
        var5.w = var2;
        if (var3) {
            //copy player
            var5.c((OEntityPlayer)var1);
        }

        var5.bd = var1.bd;
        //Set NetServerHandler
        var5.a = var1.a;
        var5.setServerHandler(var5.a.getCanaryNetServerHandler());
        var5.a.setUser(var5.getPlayer());
        
        OWorldServer var6 = (OWorldServer) dim.getHandle();
        var5.c.a(var1.c.a());
        var5.c.b(var6.s().getGameMode());
        if (var4 != null) {
            OChunkCoordinates var7 = OEntityPlayer.a(((CanaryDimension)var1.getDimension()).getHandle(), var4);
            if (var7 != null) {
                var5.c((var7.a + 0.5F), (var7.b + 0.1F), (var7.c + 0.5F), 0.0F, 0.0F);
                var5.a(var4);
            } else {
                var5.a.b((new OPacket70Bed(0, 0)));
            }
        }

        // CanaryMod set player location and angle if a spawn location is defined
        if (spawnLocation != null){
            var5.c(spawnLocation.getX(), spawnLocation.getY(), spawnLocation.getZ(), spawnLocation.getRotation(), spawnLocation.getPitch());
        }
        
        var6.G.c((int) var5.bm >> 4, (int) var5.bo >> 4);

        while (var6.a(var5, var5.bw).size() != 0) {  //This is the checks for canSpawnHere  should we do something with this so players don't end up on roofs?
            var5.c(var5.bm, var5.bn + 1.0D, var5.bo);
        }

        //respawn packet
        var5.a.b((new OPacket9Respawn(var5.w, (byte) var5.bi.q, var5.bi.s().getWorldType(), var5.bi.y(), var5.c.a())));
        //teleport to spawn location
        var5.a.a(var5.bm, var5.bn, var5.bo, var5.bs, var5.bt, var5.w, var5.bi.getCanaryDimension().getName());
        //something
        this.a(var5, var6);
        var5.getDimension().getPlayerManager().addPlayer((Player)var5.getPlayer());
        var5.getDimension().addPlayerToWorld(var5.getPlayer()); //This also calls the spawn method in world!
        this.b.add(var5);
        var5.x();
        var5.E();
        return var5;
    }

    /**
     * Send player to another dimension. Was a(OEntityPlayerMP var1, int var2) before
     * @param var1 Player
     * @param var2 Dimension ID
     * @param createPortal true if a portal needs to be created
     */
    public void switchDimension(OEntityPlayerMP var1, int var2, boolean createPortal) {
        int var3 = var1.w; //current dimension
        OWorldServer var4 = (OWorldServer) ((CanaryDimension)var1.getDimension()).getHandle();
        
        var1.w = var2; //set new dimension
        OWorldServer var5 = (OWorldServer) ((CanaryDimension) this.c.getWorldManager().getDimension(var1.bi.getCanaryDimension().getName(), var1.w)).getHandle();
        var1.a.b((new OPacket9Respawn(var1.w, (byte) var1.bi.q, var5.s().getWorldType(), var5.y(), var1.c.a())));
//        var4.f(var1);
        
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
         // CanaryMod - don't create portal if we are not using a portal to teleport.
            if(createPortal) {
                var5.G.a = true;
                (new OTeleporter()).a(var5, var1);
                var5.G.a = false;
            }
        }

        this.a(var1);
        var1.a.a(var1.bm, var1.bn, var1.bo, var1.bs, var1.bt, var1.w, var1.bi.getCanaryDimension().getName());
        var1.a(var5);
        var1.c.a(var5);
        this.a(var1, var5);
        this.f(var1);
    }

    public void sendPacketToAll(OPacket var1) {
        for (int var2 = 0; var2 < this.b.size(); ++var2) {
            OEntityPlayerMP var3 = (OEntityPlayerMP) this.b.get(var2);

            var3.a.b(var1);
        }

    }
    
    public void b() {
        // TODO: Something with Configuration.getServerConfig().getPlayerlistTicks() and Configuration.getServerConfig().getPlayerlistAutoUpdate() here
        if (++this.p > 200) {
            this.p = 0;
        }

        if (this.p < this.b.size()) {
            OEntityPlayerMP var1 = (OEntityPlayerMP) this.b.get(this.p);
            this.sendPacketToAll((new OPacket201PlayerInfo(var1.v, true, var1.i)));
        }

        for(World w : Canary.getServer().getWorldManager().getAllWorlds()) {
            for(Dimension dim : w.getDimensions()) {
                ((CanaryPlayerManager)dim.getPlayerManager()).getHandle().b();
            }
        }
    }

    @Deprecated
    public void a(int var1, int var2, int var3, int var4) {
        throw new UnsupportedOperationException("OServerConfigurationManager"
                + ".a(int, int, int, int) has been replaced by OServer"
                + "ConfigurationManager.markBlockNeedsUpdate(int, int, int, int,"
                + " String).");
    }
    
    public void markBlockNeedsUpdate(int var1, int var2, int var3, int var4, String var5) {
        Canary.getServer().getWorld(var5).getDimension(Dimension.Type.fromId(var4)).getPlayerManager().markBlockNeedsUpdate(var1, var2, var3);
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

    public void op(String var1) {
        //this.operators.add(var1.toLowerCase());
        // CanaryMod start
        //this.saveOperatorList();
        //Player p = Canary.getServer().getPlayer(var1);
        //p.getPermissionProvider().addPermission("canary.vanilla.op", true, id)
        // TODO: add canary.vanilla.op to the user
        // CanaryMod end
    }

    public void deop(String var1) {
        //this.operators.remove(var1.toLowerCase());
        // CanaryMod start
        //this.saveOperatorList();
        // TODO: remove canary.vanilla.op from the user
        // CanaryMod end
    }

    private void loadOperatorList() {
        /*
        try {
            this.operators.clear();
            BufferedReader var1 = new BufferedReader(new FileReader(this.l));
            String var2 = "";

            while ((var2 = var1.readLine()) != null) {
                this.operators.add(var2.trim().toLowerCase());
            }

            var1.close();
        } catch (Exception var3) {
            a.warning("Failed to load operators list: " + var3);
        }
        */
    }

    private void saveOperatorList() {
        /*try {
            PrintWriter var1 = new PrintWriter(new FileWriter(this.l, false));
            Iterator var2 = this.operators.iterator();

            while (var2.hasNext()) {
                String var3 = (String) var2.next();
                var1.println(var3);
            }

            var1.close();
        } catch (Exception var4) {
            a.warning("Failed to save operators list: " + var4);
        }*/

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

    private void saveWhiteList() {
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

    public boolean isAllowedToLogin(String var1) {
        var1 = var1.trim().toLowerCase();
        return !this.o || isOperator(var1) || this.i.contains(var1);
    }

    public boolean isOperator(String var1) {
        //return this.operators.contains(var1.trim().toLowerCase());
        // CanaryMod: use permission system instead
        return Canary.getServer().getPlayer(var1).hasPermission("canary.vanilla.op");
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
        this.a((OEntityPlayer) null, var1, var3, var5, var7, var9, var10, null);
    }

    //CanaryMod changed signature, added String
    public void a(OEntityPlayer var1, double var2, double var4, double var6, double var8, int var10, OPacket var11, String worldName) {
        //CanaryMod start - Refactored
        if(worldName == null) {
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
        else {
            for (int var12 = 0; var12 < this.b.size(); ++var12) {
                OEntityPlayerMP var13 = (OEntityPlayerMP) this.b.get(var12);
                if (var13 != var1 && var13.w == var10 && worldName.equals(var13.bi.getCanaryDimension().getName())) {
                    double var14 = var2 - var13.bm;
                    double var16 = var4 - var13.bn;
                    double var18 = var6 - var13.bo;
                    if (var14 * var14 + var16 * var16 + var18 * var18 < var8 * var8) {
                        var13.a.b(var11);
                    }
                }
            }
        }
        

    }

    public void j(String var1) {
        OPacket3Chat var2 = new OPacket3Chat(var1);

        for (int var3 = 0; var3 < this.b.size(); ++var3) {
            OEntityPlayerMP var4 = (OEntityPlayerMP) this.b.get(var3);
            if (this.isOperator(var4.v)) {
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
        this.saveWhiteList();
    }

    public void l(String var1) {
        this.i.remove(var1);
        this.saveWhiteList();
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
