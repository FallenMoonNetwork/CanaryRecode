package net.minecraft.server;

import java.io.File;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.CanaryConfigurationManager;
import net.canarymod.api.CanaryPacket;
import net.canarymod.api.entity.living.humanoid.CanaryPlayer;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.position.Location;
import net.canarymod.bansystem.Ban;
import net.canarymod.hook.player.ConnectionHook;
import net.canarymod.hook.player.PlayerRespawnHook;
import net.canarymod.hook.player.PreConnectionHook;
import net.canarymod.hook.system.ServerShutdownHook;

public abstract class ServerConfigurationManager {

    private static final SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd \'at\' HH:mm:ss z");
    private final MinecraftServer e;
    public final List a = new ArrayList();
    private final BanList f = new BanList(new File("banned-players.txt"));
    private final BanList g = new BanList(new File("banned-ips.txt"));
    private Set h = new HashSet();
    private Set i = new HashSet();
    private boolean k;
    protected int b;
    protected int c;
    private EnumGameType l;
    private boolean m;
    private int n = 0;

    // CanaryMod
    protected CanaryConfigurationManager configurationmanager;
    private HashMap<String, IPlayerFileData> playerFileData = new HashMap<String, IPlayerFileData>();
    //
    public ServerConfigurationManager(MinecraftServer minecraftserver) {
        this.e = minecraftserver;
        this.f.a(false);
        this.g.a(false);
        this.b = 8;
        configurationmanager = new CanaryConfigurationManager(this);
    }

    //XXX LOGIN
    public void a(INetworkManager inetworkmanager, EntityPlayerMP entityplayermp) {
        NBTTagCompound nbttagcompound = this.a(entityplayermp);
        CanaryWorld w;
        if(nbttagcompound != null) {
            w = (CanaryWorld) Canary.getServer().getWorldManager().getWorld(nbttagcompound.i("LevelName"), net.canarymod.api.world.WorldType.fromId(nbttagcompound.e("Dimension")), true);
        }
        else {
            w = (CanaryWorld) Canary.getServer().getDefaultWorld();
        }
        entityplayermp.a(w.getHandle());
        entityplayermp.c.a((WorldServer) entityplayermp.q);
        String s0 = "local";

        if (inetworkmanager.c() != null) {
            s0 = inetworkmanager.c().toString();
        }

        this.e.al().a(entityplayermp.bS + "[" + s0 + "] logged in with entity id " + entityplayermp.k + " at (" + entityplayermp.u + ", " + entityplayermp.v + ", " + entityplayermp.w + ")");
        //CanaryMod: Use world we got from players NBT data
        WorldServer worldserver = (WorldServer) w.getHandle();
        ChunkCoordinates chunkcoordinates = worldserver.I();

        this.a(entityplayermp, (EntityPlayerMP) null, worldserver);
        NetServerHandler netserverhandler = new NetServerHandler(this.e, inetworkmanager, entityplayermp);

        netserverhandler.b(new Packet1Login(entityplayermp.k, worldserver.L().u(), entityplayermp.c.b(), worldserver.L().t(), worldserver.t.h, worldserver.r, worldserver.P(), this.l()));
        netserverhandler.b(new Packet6SpawnPosition(chunkcoordinates.a, chunkcoordinates.b, chunkcoordinates.c));
        netserverhandler.b(new Packet202PlayerAbilities(entityplayermp.ce));
        netserverhandler.b(new Packet16BlockItemSwitch(entityplayermp.bK.c));
        this.a((ServerScoreboard) worldserver.V(), entityplayermp);
        this.b(entityplayermp, worldserver);
        //CanaryMod Connection hook
        ConnectionHook hook = new ConnectionHook(entityplayermp.getPlayer(), EnumChatFormatting.o + entityplayermp.ax() + EnumChatFormatting.o + " joined the game.");
        Canary.hooks().callHook(hook);
        if(!hook.isHidden()) {
            this.a((Packet) (new Packet3Chat(hook.getMessage())));
        }
        //CanaryMod end
        this.c(entityplayermp);
        netserverhandler.a(entityplayermp.u, entityplayermp.v, entityplayermp.w, entityplayermp.A, entityplayermp.B, entityplayermp.getCanaryWorld().getType().getId(), entityplayermp.getCanaryWorld().getName());
        this.e.ae().a(netserverhandler);
        netserverhandler.b(new Packet4UpdateTime(worldserver.G(), worldserver.H()));
        if (this.e.Q().length() > 0) {
            entityplayermp.a(this.e.Q(), this.e.S());
        }

        Iterator iterator = entityplayermp.bC().iterator();

        while (iterator.hasNext()) {
            PotionEffect potioneffect = (PotionEffect) iterator.next();

            netserverhandler.b(new Packet41EntityEffect(entityplayermp.k, potioneffect));
        }

        entityplayermp.d_();
        if (nbttagcompound != null && nbttagcompound.b("Riding")) {
            Entity entity = EntityList.a(nbttagcompound.l("Riding"), worldserver);

            if (entity != null) {
                entity.p = true;
                worldserver.d(entity);
                entityplayermp.a(entity);
                entity.p = false;
            }
        }
    }

    protected void a(ServerScoreboard serverscoreboard, EntityPlayerMP entityplayermp) {
        HashSet hashset = new HashSet();
        Iterator iterator = serverscoreboard.g().iterator();

        while (iterator.hasNext()) {
            ScorePlayerTeam scoreplayerteam = (ScorePlayerTeam) iterator.next();

            entityplayermp.a.b(new Packet209SetPlayerTeam(scoreplayerteam, 0));
        }

        for (int i0 = 0; i0 < 3; ++i0) {
            ScoreObjective scoreobjective = serverscoreboard.a(i0);

            if (scoreobjective != null && !hashset.contains(scoreobjective)) {
                List list = serverscoreboard.d(scoreobjective);
                Iterator iterator1 = list.iterator();

                while (iterator1.hasNext()) {
                    Packet packet = (Packet) iterator1.next();

                    entityplayermp.a.b(packet);
                }

                hashset.add(scoreobjective);
            }
        }
    }

    public void a(WorldServer server) {
        //CanaryMod Multiworld
        playerFileData.put(server.getCanaryWorld().getName(), server.K().e());
        //
    }

    public void a(EntityPlayerMP entityplayermp, WorldServer worldserver) {
        WorldServer worldserver1 = entityplayermp.o();
        if(worldserver != null) {
            worldserver.r().c(entityplayermp);
        }
        worldserver1.r().a(entityplayermp);
        worldserver1.b.c((int) entityplayermp.u >> 4, (int) entityplayermp.w >> 4);
    }

    public int a() {
        return PlayerManager.a(this.o());
    }

    public NBTTagCompound a(EntityPlayerMP entityplayermp) {
        NBTTagCompound nbttagcompound = entityplayermp.getCanaryWorld().getHandle().L().i();
        NBTTagCompound nbttagcompound1;

        if (entityplayermp.c_().equals(this.e.H()) && nbttagcompound != null) {
            entityplayermp.f(nbttagcompound);
            nbttagcompound1 = nbttagcompound;
            System.out.println("loading single player");
        } else {
            //CanaryMod Multiworld
            nbttagcompound1 = playerFileData.get(entityplayermp.getCanaryWorld().getName()).b(entityplayermp);
            //
        }

        return nbttagcompound1;
    }

    //CanaryMod: get player data for name
    public NBTTagCompound getPlayerDatByName(String name) {
        ISaveHandler handler = ((CanaryWorld) Canary.getServer().getDefaultWorld()).getHandle().K();
        if(handler instanceof SaveHandler) {
            SaveHandler saves = (SaveHandler) handler;
            return saves.a(name);
        }
        else {
            throw new RuntimeException("ISaveHandler is not of type SaveHandler! Failing to laod playerdata");
        }
    }

    protected void b(EntityPlayerMP entityplayermp) {
        //CanaryMod Multiworld
        playerFileData.get(entityplayermp.getCanaryWorld().getName()).a(entityplayermp);
        //
    }

    public void c(EntityPlayerMP entityplayermp) {
        this.a((Packet) (new Packet201PlayerInfo(entityplayermp.bS, true, 1000)));
        this.a.add(entityplayermp);

        //CanaryMod: Directly use playerworld instead
        WorldServer worldserver = (WorldServer) entityplayermp.getCanaryWorld().getHandle();//this.e.a(entityplayermp.ar);

        worldserver.d(entityplayermp);
        this.a(entityplayermp, (WorldServer) null);

        for (int i0 = 0; i0 < this.a.size(); ++i0) {
            EntityPlayerMP entityplayermp1 = (EntityPlayerMP) this.a.get(i0);

            entityplayermp.a.b(new Packet201PlayerInfo(entityplayermp1.bS, true, entityplayermp1.i));
        }
    }

    public void d(EntityPlayerMP entityplayermp) {
        entityplayermp.o().r().d(entityplayermp);
    }

    public void e(EntityPlayerMP entityplayermp) {
        this.b(entityplayermp);
        WorldServer worldserver = entityplayermp.o();

        if (entityplayermp.o != null) {
            worldserver.e(entityplayermp.o);
            System.out.println("removing player mount");
        }

        worldserver.e(entityplayermp);
        worldserver.r().c(entityplayermp);
        this.a.remove(entityplayermp);
        this.a((Packet) (new Packet201PlayerInfo(entityplayermp.bS, false, 9999)));
    }

    public String a(SocketAddress socketaddress, String s0) {

        // CanaryMod, redo the whole thing
        String s2 = socketaddress.toString();

        s2 = s2.substring(s2.indexOf("/") + 1);
        s2 = s2.substring(0, s2.indexOf(":"));

        PreConnectionHook hook = new PreConnectionHook(s2, s0, net.canarymod.api.world.WorldType.fromId(0), Canary.getServer().getDefaultWorldName());
        Canary.hooks().callHook(hook);

        if (hook.getKickReason() != null) {
            return hook.getKickReason();
        }
        if(Canary.bans().isBanned(s0)) {
            Ban ban = Canary.bans().getBan(s0);
            if(ban.getTimestamp() != -1) {
                return ban.getReason() + ", " + Translator.translateAndFormat("ban expires", Canary.formatTimestamp(ban.getTimestamp()));
            }
            return ban.getReason();
        }
        if(Canary.bans().isIpBanned(s2)) {
            return Translator.translate("you are banned");
        }

        if(!Canary.whitelist().isWhitelisted(s0)) {
            return Translator.translate("not on whitelist");
        }

        return this.a.size() >= this.b ? Translator.translate("server full") : null;

//        if (this.f.a(s0)) {
//            BanEntry banentry = (BanEntry) this.f.c().get(s0);
//            String s1 = "You are banned from this server!\nReason: " + banentry.f();
//
//            if (banentry.d() != null) {
//                s1 = s1 + "\nYour ban will be removed on " + d.format(banentry.d());
//            }
//
//            return s1;
//        } else if (!this.d(s0)) {
//            return "You are not white-listed on this server!";
//        } else {
//            if (this.g.a(s2)) {
//                BanEntry banentry1 = (BanEntry) this.g.c().get(s2);
//                String s3 = "Your IP address is banned from this server!\nReason: " + banentry1.f();
//
//                if (banentry1.d() != null) {
//                    s3 = s3 + "\nYour ban will be removed on " + d.format(banentry1.d());
//                }
//
//                return s3;
//            } else {
//                return this.a.size() >= this.b ? "The server is full!" : null;
//            }
//        }
    }

    public EntityPlayerMP a(String playername) {
        ArrayList arraylist = new ArrayList();

        EntityPlayerMP entityplayermp;
        for (int i0 = 0; i0 < this.a.size(); ++i0) {
            entityplayermp = (EntityPlayerMP) this.a.get(i0);
            if (entityplayermp.bS.equalsIgnoreCase(playername)) {
                arraylist.add(entityplayermp);
            }
        }

        Iterator iterator = arraylist.iterator();

        while (iterator.hasNext()) {
            entityplayermp = (EntityPlayerMP) iterator.next();
            entityplayermp.a.c("You logged in from another location");
        }

        //CanaryMod read the players dat file to find out the world it was last in
        String worldName = Canary.getServer().getDefaultWorldName();
        net.canarymod.api.world.WorldType worldtype = net.canarymod.api.world.WorldType.fromId(0);
        NBTTagCompound playertag = getPlayerDatByName(playername);

        if(playertag != null) {
            Canary.println("item manager from NBT data");
            net.canarymod.api.nbt.CanaryCompoundTag canarycompound = new net.canarymod.api.nbt.CanaryCompoundTag(playertag);
            worldName = canarycompound.getString("LevelName");
            if(worldName == null || worldName.isEmpty()) {
                worldName = Canary.getServer().getDefaultWorldName();
            }
            worldtype = net.canarymod.api.world.WorldType.fromId(canarycompound.getInt("Dimension"));
        }

        WorldServer world = (WorldServer) ((CanaryWorld) Canary.getServer().getWorldManager().getWorld(worldName, worldtype, true)).getHandle();
        Object object;

        if (this.e.M()) {
            object = new DemoWorldManager(world);
        } else {
            object = new ItemInWorldManager(world);
        }

        return new EntityPlayerMP(this.e, world, playername, (ItemInWorldManager) object);
    }

    public EntityPlayerMP a(EntityPlayerMP entityplayermp, int i, boolean flag) {
        return this.a(entityplayermp, i, flag, null);
    }

    //XXX
    //IMPORTANT, HERE IS WORLD SWITCHING GOING ON!
    public EntityPlayerMP a(EntityPlayerMP entityplayermp, int i0, boolean flag0, Location loc) {
        entityplayermp.o().p().a(entityplayermp);
        entityplayermp.o().p().b(entityplayermp);
        entityplayermp.o().r().c(entityplayermp);
        this.a.remove(entityplayermp);
        entityplayermp.getCanaryWorld().getHandle().f(entityplayermp);
        ChunkCoordinates chunkcoordinates = entityplayermp.ci();
        boolean flag1 = entityplayermp.cj();

        entityplayermp.ar = i0;
        Object object;
        String name = entityplayermp.getCanaryWorld().getName();
        net.canarymod.api.world.WorldType type = net.canarymod.api.world.WorldType.fromId(i0);

        WorldServer worldserver = (WorldServer) (loc == null ? (WorldServer) ((CanaryWorld)Canary.getServer().getWorldManager().getWorld(name, type, true)).getHandle(): ((CanaryWorld) loc.getWorld()).getHandle());
        if (this.e.M()) {
            object = new DemoWorldManager(worldserver);
        } else {
            object = new ItemInWorldManager(worldserver);
        }

        EntityPlayerMP entityplayermp1 = new EntityPlayerMP(this.e, worldserver, entityplayermp.bS, (ItemInWorldManager) object);

        entityplayermp1.a = entityplayermp.a;
        entityplayermp1.a(entityplayermp, flag0);
        entityplayermp1.k = entityplayermp.k;

        this.a(entityplayermp1, entityplayermp, worldserver);
        ChunkCoordinates chunkcoordinates1;

        if (chunkcoordinates != null) {
            //CanaryMod get world from player
            //chunkcoordinates1 = EntityPlayer.a(this.e.a(entityplayermp.ar), chunkcoordinates, flag1);
            chunkcoordinates1 = EntityPlayer.a(worldserver, chunkcoordinates, flag1);
            if (chunkcoordinates1 != null) {
                entityplayermp1.b((double) ((float) chunkcoordinates1.a + 0.5F), (double) ((float) chunkcoordinates1.b + 0.1F), (double) ((float) chunkcoordinates1.c + 0.5F), 0.0F, 0.0F);
                entityplayermp1.a(chunkcoordinates, flag1);
            } else {
                entityplayermp1.a.b(new Packet70GameEvent(0, 0));
            }
        }

     // CanaryMod set player location and angle if a spawn location is defined
        if (loc != null) {
            entityplayermp1.a.c = entityplayermp1; // Set ONetServerHandler.user
            entityplayermp1.b(loc.getX(), loc.getY(), loc.getZ(), loc.getPitch(), loc.getRotation());
        }
        worldserver.b.c((int) entityplayermp1.u >> 4, (int) entityplayermp1.w >> 4);

        while (!worldserver.a(entityplayermp1, entityplayermp1.E).isEmpty()) {
            entityplayermp1.b(entityplayermp1.u, entityplayermp1.v + 1.0D, entityplayermp1.w);
        }

        entityplayermp1.a.b(new Packet9Respawn(entityplayermp1.ar >= 0? -1 : 0, (byte) entityplayermp1.q.r, entityplayermp1.q.L().u(), entityplayermp1.q.P(), entityplayermp1.c.b()));
        entityplayermp1.a.b(new Packet9Respawn(entityplayermp1.ar, (byte) entityplayermp1.q.r, entityplayermp1.q.L().u(), entityplayermp1.q.P(), entityplayermp1.c.b()));

        chunkcoordinates1 = worldserver.I();

        entityplayermp1.a.a(entityplayermp1.u, entityplayermp1.v, entityplayermp1.w, entityplayermp1.A, entityplayermp1.B, entityplayermp1.getCanaryWorld().getType().getId(), entityplayermp1.getCanaryWorld().getName());
        entityplayermp1.a.b(new Packet6SpawnPosition(chunkcoordinates1.a, chunkcoordinates1.b, chunkcoordinates1.c));
        entityplayermp1.a.b(new Packet43Experience(entityplayermp1.ch, entityplayermp1.cg, entityplayermp1.cf));
        this.b(entityplayermp1, worldserver);

        worldserver.r().a(entityplayermp1);
        worldserver.d(entityplayermp1);
        this.a.add(entityplayermp1);
        entityplayermp1.d_();
        entityplayermp1.b(entityplayermp1.aX());
        // CanaryMod: PlayerRespawn
        PlayerRespawnHook hook = new PlayerRespawnHook(entityplayermp1.getPlayer(), loc);
        Canary.hooks().callHook(hook);
        //
        return entityplayermp1;
    }

    @Deprecated
    public void a(EntityPlayerMP entityplayermp, int i0) {
        throw new UnsupportedOperationException("a(EntityPlayerMP, int) is deprecated. please use a(EntityPlayerMP, String, int))");
    }

    //XXX
    //XXX
    //IMPORTANT, HERE IS DIMENSION SWITCHING GOING ON!
    public void a(EntityPlayerMP entityplayermp, String worldName, int i0) {
        int i1 = entityplayermp.ar;
        WorldServer worldserver = (WorldServer) entityplayermp.getCanaryWorld().getHandle();

        entityplayermp.ar = i0;
        net.canarymod.api.world.WorldType type = net.canarymod.api.world.WorldType.fromId(i0);
        WorldServer worldserver1 = (WorldServer) ((CanaryWorld) Canary.getServer().getWorldManager().getWorld(worldName, type, true)).getHandle();

        //Pre-load a chunk in the new world, makes spawning there a little faster
        worldserver1.b.c((int) entityplayermp.u >> 4, (int) entityplayermp.w >> 4);

        entityplayermp.a.b(new Packet9Respawn(i0, (byte) entityplayermp.q.r, worldserver1.L().u(), worldserver1.P(), entityplayermp.c.b()));
        worldserver.f(entityplayermp);
        entityplayermp.M = false;
        this.a(entityplayermp, i1, worldserver, worldserver1); //i1
        this.a(entityplayermp, worldserver);
        //TP player to position
        entityplayermp.a.a(entityplayermp.u, entityplayermp.v, entityplayermp.w, entityplayermp.A, entityplayermp.B, entityplayermp.getCanaryWorld().getType().getId(), entityplayermp.getCanaryWorld().getName());
        entityplayermp.c.a(worldserver1);
        this.b(entityplayermp, worldserver1);
        this.f(entityplayermp);
        Iterator iterator = entityplayermp.bC().iterator();

        while (iterator.hasNext()) {
            PotionEffect potioneffect = (PotionEffect) iterator.next();

            entityplayermp.a.b(new Packet41EntityEffect(entityplayermp.k, potioneffect));
        }
    }

    public void a(Entity entity, int i0, WorldServer worldserver, WorldServer worldserver1) {
        double d0 = entity.u;
        double d1 = entity.w;
        double d2 = 8.0D;
        double d3 = entity.u;
        double d4 = entity.v;
        double d5 = entity.w;
        float f0 = entity.A;

        worldserver.C.a("moving");
        if (entity.ar == -1) {
            d0 /= d2;
            d1 /= d2;
            entity.b(d0, entity.v, d1, entity.A, entity.B);
            if (entity.R()) {
                worldserver.a(entity, false);
            }
        } else if (entity.ar == 0) {
            d0 *= d2;
            d1 *= d2;
            entity.b(d0, entity.v, d1, entity.A, entity.B);
            if (entity.R()) {
                worldserver.a(entity, false);
            }
        } else {
            ChunkCoordinates chunkcoordinates;

            if (i0 == 1) {
                chunkcoordinates = worldserver1.I();
            } else {
                chunkcoordinates = worldserver1.l();
            }

            d0 = (double) chunkcoordinates.a;
            entity.v = (double) chunkcoordinates.b;
            d1 = (double) chunkcoordinates.c;
            entity.b(d0, entity.v, d1, 90.0F, 0.0F);
            if (entity.R()) {
                worldserver.a(entity, false);
            }
        }

        worldserver.C.b();
        if (i0 != 1) {
            worldserver.C.a("placing");
            d0 = (double) MathHelper.a((int) d0, -29999872, 29999872);
            d1 = (double) MathHelper.a((int) d1, -29999872, 29999872);
            if (entity.R()) {
                worldserver1.d(entity);
                entity.b(d0, entity.v, d1, entity.A, entity.B);
                worldserver1.a(entity, false);
                worldserver1.s().a(entity, d3, d4, d5, f0);
            }

            worldserver.C.b();
        }

        entity.a((World) worldserver1);
    }

    public void b() {
        if (++this.n > 600) {
            this.n = 0;
        }

        if (this.n < this.a.size()) {
            EntityPlayerMP entityplayermp = (EntityPlayerMP) this.a.get(this.n);

            this.a((Packet) (new Packet201PlayerInfo(entityplayermp.bS, true, entityplayermp.i)));
        }
    }

    public void a(Packet packet) {
        for (int i0 = 0; i0 < this.a.size(); ++i0) {
            ((EntityPlayerMP) this.a.get(i0)).a.b(packet);
        }
    }

    //CanaryMod re-route packets properly
    public void sendPacketToDimension(Packet opacket, String world, int i) {
        for (int j = 0; j < this.a.size(); ++j) {
            EntityPlayerMP oentityplayermp = (EntityPlayerMP) this.a.get(j);

            if (world.equals(oentityplayermp.getCanaryWorld().getName()) && oentityplayermp.ar == i) {
                // TODO check: CanaryMod re-route time updates to world-specific entity trackers
                oentityplayermp.a.b(opacket);
            }
        }
    }
    //CanaryMod end

    @Deprecated
    public void a(Packet packet, int i0) {
        throw new UnsupportedOperationException("a(packet, int) has been deprecated. use sendPacketToDimension instead!");
    }

    public String c() {
        String s0 = "";

        for (int i0 = 0; i0 < this.a.size(); ++i0) {
            if (i0 > 0) {
                s0 = s0 + ", ";
            }

            s0 = s0 + ((EntityPlayerMP) this.a.get(i0)).bS;
        }

        return s0;
    }

    public String[] d() {
        String[] astring = new String[this.a.size()];

        for (int i0 = 0; i0 < this.a.size(); ++i0) {
            astring[i0] = ((EntityPlayerMP) this.a.get(i0)).bS;
        }

        return astring;
    }

    public BanList e() {
        return this.f;
    }

    public BanList f() {
        return this.g;
    }

    public void b(String s0) {
        this.h.add(s0.toLowerCase());
    }

    public void c(String s0) {
        this.h.remove(s0.toLowerCase());
    }

    public boolean d(String s0) {
        s0 = s0.trim().toLowerCase();
        return !this.k || this.h.contains(s0) || this.i.contains(s0);
    }

    public boolean e(String s0) {
        WorldServer srv = (WorldServer)((CanaryWorld) Canary.getServer().getDefaultWorld()).getHandle();
        return this.h.contains(s0.trim().toLowerCase()) || this.e.I() && srv.L().v() && this.e.H().equalsIgnoreCase(s0) || this.m;
    }

    public EntityPlayerMP f(String s0) {
        Iterator iterator = this.a.iterator();

        EntityPlayerMP entityplayermp;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            entityplayermp = (EntityPlayerMP) iterator.next();
        } while (!entityplayermp.bS.equalsIgnoreCase(s0));

        return entityplayermp;
    }

    public List a(ChunkCoordinates chunkcoordinates, int i0, int i1, int i2, int i3, int i4, int i5, Map map, String s0, String s1) {
        if (this.a.isEmpty()) {
            return null;
        } else {
            Object object = new ArrayList();
            boolean flag0 = i2 < 0;
            int i6 = i0 * i0;
            int i7 = i1 * i1;

            i2 = MathHelper.a(i2);

            for (int i8 = 0; i8 < this.a.size(); ++i8) {
                EntityPlayerMP entityplayermp = (EntityPlayerMP) this.a.get(i8);
                boolean flag1;

                if (s0 != null) {
                    flag1 = s0.startsWith("!");
                    if (flag1) {
                        s0 = s0.substring(1);
                    }

                    if (flag1 == s0.equalsIgnoreCase(entityplayermp.am())) {
                        continue;
                    }
                }

                if (s1 != null) {
                    flag1 = s1.startsWith("!");
                    if (flag1) {
                        s1 = s1.substring(1);
                    }

                    ScorePlayerTeam scoreplayerteam = entityplayermp.cq();
                    String s2 = scoreplayerteam == null ? "" : scoreplayerteam.b();

                    if (flag1 == s1.equalsIgnoreCase(s2)) {
                        continue;
                    }
                }

                if (chunkcoordinates != null && (i0 > 0 || i1 > 0)) {
                    float f0 = chunkcoordinates.e(entityplayermp.b());

                    if (i0 > 0 && f0 < (float) i6 || i1 > 0 && f0 > (float) i7) {
                        continue;
                    }
                }

                if (this.a((EntityPlayer) entityplayermp, map) && (i3 == EnumGameType.a.a() || i3 == entityplayermp.c.b().a()) && (i4 <= 0 || entityplayermp.cf >= i4) && entityplayermp.cf <= i5) {
                    ((List) object).add(entityplayermp);
                }
            }

            if (chunkcoordinates != null) {
                Collections.sort((List) object, new PlayerPositionComparator(chunkcoordinates));
            }

            if (flag0) {
                Collections.reverse((List) object);
            }

            if (i2 > 0) {
                object = ((List) object).subList(0, Math.min(i2, ((List) object).size()));
            }

            return (List) object;
        }
    }

    private boolean a(EntityPlayer entityplayer, Map map) {
        if (map != null && map.size() != 0) {
            Iterator iterator = map.entrySet().iterator();

            Entry entry;
            boolean flag0;
            int i0;

            do {
                if (!iterator.hasNext()) {
                    return true;
                }

                entry = (Entry) iterator.next();
                String s0 = (String) entry.getKey();

                flag0 = false;
                if (s0.endsWith("_min") && s0.length() > 4) {
                    flag0 = true;
                    s0 = s0.substring(0, s0.length() - 4);
                }

                Scoreboard scoreboard = entityplayer.cp();
                ScoreObjective scoreobjective = scoreboard.b(s0);

                if (scoreobjective == null) {
                    return false;
                }

                Score score = entityplayer.cp().a(entityplayer.am(), scoreobjective);

                i0 = score.c();
                if (i0 < ((Integer) entry.getValue()).intValue() && flag0) {
                    return false;
                }
            } while (i0 <= ((Integer) entry.getValue()).intValue() || flag0);

            return false;
        } else {
            return true;
        }
    }

    public void a(double d0, double d1, double d2, double d3, int i0, Packet packet) {
        this.a((EntityPlayer) null, d0, d1, d2, d3, i0, packet);
    }

    public void a(EntityPlayer entityplayer, double d0, double d1, double d2, double d3, int i0, Packet packet) {
        for (int i1 = 0; i1 < this.a.size(); ++i1) {
            EntityPlayerMP entityplayermp = (EntityPlayerMP) this.a.get(i1);

            if (entityplayermp != entityplayer && entityplayermp.ar == i0) {
                double d4 = d0 - entityplayermp.u;
                double d5 = d1 - entityplayermp.v;
                double d6 = d2 - entityplayermp.w;

                if (d4 * d4 + d5 * d5 + d6 * d6 < d3 * d3) {
                    entityplayermp.a.b(packet);
                }
            }
        }
    }

    public void g() {
        for (int i0 = 0; i0 < this.a.size(); ++i0) {
            this.b((EntityPlayerMP) this.a.get(i0));
        }
    }

    public void h(String s0) {
        this.i.add(s0);
    }

    public void i(String s0) {
        this.i.remove(s0);
    }

    public Set h() {
        return this.i;
    }

    public Set i() {
        return this.h;
    }

    public void j() {}

    public void b(EntityPlayerMP entityplayermp, WorldServer worldserver) {
        entityplayermp.a.b(new Packet4UpdateTime(worldserver.G(), worldserver.H()));
        if (worldserver.O()) {
            entityplayermp.a.b(new Packet70GameEvent(1, 0));
        }
    }

    public void f(EntityPlayerMP entityplayermp) {
        entityplayermp.a(entityplayermp.bL);
        entityplayermp.l();
        entityplayermp.a.b(new Packet16BlockItemSwitch(entityplayermp.bK.c));
    }

    public int k() {
        return this.a.size();
    }

    public int l() {
        return this.b;
    }

    public String[] m() {
        WorldServer srv = (WorldServer)((CanaryWorld) Canary.getServer().getDefaultWorld()).getHandle();
        return srv.K().e().f();
    }

    public boolean n() {
        return this.k;
    }

    public void a(boolean flag0) {
        this.k = flag0;
    }

    public List j(String s0) {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = this.a.iterator();

        while (iterator.hasNext()) {
            EntityPlayerMP entityplayermp = (EntityPlayerMP) iterator.next();

            if (entityplayermp.p().equals(s0)) {
                arraylist.add(entityplayermp);
            }
        }

        return arraylist;
    }

    public int o() {
        return this.c;
    }

    public MinecraftServer p() {
        return this.e;
    }

    public NBTTagCompound q() {
        return null;
    }

    private void a(EntityPlayerMP entityplayermp, EntityPlayerMP entityplayermp1, World world) {
        if (entityplayermp1 != null) {
            entityplayermp.c.a(entityplayermp1.c.b());
        } else if (this.l != null) {
            entityplayermp.c.a(this.l);
        }

        entityplayermp.c.b(world.L().r());
    }

    public void r() {
        //CanaryMod shutdown hook
        ServerShutdownHook hook = new ServerShutdownHook("Server closed");
        Canary.hooks().callHook(hook);
        //
        while (!this.a.isEmpty()) {
            ((EntityPlayerMP) this.a.get(0)).a.c(hook.getReason());
        }
    }

    public void k(String s0) {
        this.e.f(s0);
        this.a((Packet) (new Packet3Chat(s0)));
    }

    /**
     * Get the configuration management wrapper
     * @return the canary configuration manager
     */
    public CanaryConfigurationManager getConfigurationManager() {
        return configurationmanager;
    }

    //This is a CanaryMod method
    /**
     * Send a packet to a specified player.
     * @param player
     * @param packet
     */
    public void sendPacketToPlayer(CanaryPlayer player, CanaryPacket packet) {
        if(a.contains(player.getHandle())) {
            player.getHandle().a.b(packet.getPacket());
        }
    }
}
