package net.canarymod.api.entity;


import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.canarymod.Canary;
import net.canarymod.Colors;
import net.canarymod.Logman;
import net.canarymod.api.CanaryServer;
import net.canarymod.api.NetServerHandler;
import net.canarymod.api.Packet;
import net.canarymod.api.inventory.CanaryPlayerInventory;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.CanaryDimension;
import net.canarymod.api.world.Dimension;
import net.canarymod.api.world.World;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.position.Direction;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Vector3D;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.config.Configuration;
import net.canarymod.hook.command.PlayerCommandHook;
import net.canarymod.hook.player.ChatHook;
import net.canarymod.permissionsystem.PermissionProvider;
import net.canarymod.user.Group;
import net.canarymod.warp.Warp;
import net.minecraft.server.OAchievementList;
import net.minecraft.server.OBlock;
import net.minecraft.server.OChunkCoordinates;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntityPlayerMP;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMinecraftServer;
import net.minecraft.server.OPacket;
import net.minecraft.server.OPacket70Bed;
import net.minecraft.server.OStatBase;
import net.minecraft.server.OWorldSettings;

/**
 * Canary Player wrapper.
 * @author Chris
 *
 */
public class CanaryPlayer extends CanaryEntityLiving implements Player {
    private Pattern badChatPattern = Pattern.compile("[\u00a7\u2302\u00D7\u00AA\u00BA\u00AE\u00AC\u00BD\u00BC\u00A1\u00AB\u00BB]");
    private Group group; 
    private PermissionProvider permissions;
    private String prefix = null;
    private boolean muted;
    private String[] allowedIPs;
    
    public CanaryPlayer(OEntityPlayerMP entity) {
        super(entity);
        String[] data = Canary.usersAndGroups().getPlayerData(getName());
        group = Canary.usersAndGroups().getGroup(data[1]); 
        permissions = Canary.permissionManager().getPlayerProvider(getName());
        
        if(data[0] != null && (!data[0].isEmpty() && !data[0].equals(" "))) {
            prefix = data[0];
        }
        
        if(data[2] != null && !data[2].isEmpty()) {
            allowedIPs = data[2].split(",");
        }
    }

    /**
     * CanaryMod: Get player handle
     */
    @Override
    public OEntityPlayerMP getHandle() {
        return (OEntityPlayerMP) entity;
    }
    @Override
    public String getName() {
    	return ((OEntityPlayerMP)entity).v;
    }
    
    @Override
    public void chat(String message) {
        if (message.length() > 100) {
            kick("Message too long!");
        } 
        message = message.trim();
        Matcher m = badChatPattern.matcher(message);
        String out = message;
        
        if (m.find() && !this.canIgnoreRestrictions()) {
            out = message.replaceAll(m.group(), "");
        }
        message = out;
        
        //TODO: Add configuration for spam protection?
        
        if(message.startsWith("/")) {
            executeCommand(message.split(" "));
        }
        else {
            if(isMuted()) {
                notify("You are currently muted!");
            }
            else {
                String prefix = "<" + getColor() + getName() + Colors.White + "> ";
                
                //Preventing ConcurrentModification Issues by full making a copy of the list before giving it to plugins
                ArrayList<Player> receivers = new ArrayList<Player>(Canary.getServer().getPlayerList()); // shadow copy the list for size matching
                Collections.copy(receivers, Canary.getServer().getPlayerList()); //Fully copy the list
                
                ChatHook hook = new ChatHook(this, prefix, message, receivers);
                Canary.hooks().callHook(hook);
                if(hook.isCanceled()) {
                    return;
                }
                receivers = hook.getReceiverList();
                for(Player player : receivers) {
                    if (hook.getPrefix().length() + hook.getMessage().length() >= 100) {
                        player.sendMessage(hook.getPrefix());
                        player.sendMessage(hook.getMessage().toString());
                    } else {
                        player.sendMessage(hook.getPrefix()+hook.getMessage().toString());
                    }
                }
            }
        }

    }
    
    @Override
    public void kill(){
        super.kill();
        dropInventory();
    }

    @Override
    public void sendMessage(String message) {
        getNetServerHandler().sendMessage(message);
    }

    @Override
    public void addExhaustion(float exhaustion) {
        ((OEntityPlayerMP)entity).getFoodStats().addExhaustion(exhaustion);
    }

    @Override
    public void setExhaustion(float exhaustion) {
        ((OEntityPlayerMP)entity).getFoodStats().setFoodExhaustionLevel(exhaustion);
    }

    @Override
    public float getExhaustionLevel() {
        return ((OEntityPlayerMP)entity).getFoodStats().getFoodSaturationLevel();
    }

    @Override
    public void setHunger(int hunger) {
        ((OEntityPlayerMP)entity).getFoodStats().setFoodLevel(hunger);
    }

    @Override
    public int getHunger() {
        return ((OEntityPlayerMP)entity).getFoodStats().getFoodLevel();
    }

    @Override
    public void addExperience(int experience) {
        ((OEntityPlayerMP)entity).addXP(experience);
    }

    @Override
    public void removeExperience(int experience) {
        ((OEntityPlayerMP)entity).removeXP(experience);
    }

    @Override
    public int getExperience() {
        return ((OEntityPlayerMP)entity).N;
    }

    @Override
    public void setExperience(int xp) {
        if(xp < 0) return;
        ((OEntityPlayerMP)entity).setXP(xp);
    }

    @Override
    public int getLevel() {
        return ((OEntityPlayerMP)entity).M;
    }

    @Override
    public boolean isSleeping() {
        return ((OEntityPlayerMP)entity).Z();
    }
    
    @Override
    public boolean isDeeplySleeping() {
        return ((OEntityPlayerMP)entity).aa();
    }

    @Override
    public void destroyItemHeld() {
        OEntityPlayerMP player = (OEntityPlayerMP) entity;
        if(player.k.d() != null) {
            player.k.d().getCanaryItem().setId(0);
        }
    }

    @Override
    public Item getItemHeld() {
        OItemStack item = ((CanaryPlayerInventory) getInventory()).getItemInHand();
        if(item != null) {
            return item.getCanaryItem();
        }
        return null;
    }

    @Override
    public void dropItem(Item item) {
        getDimension().dropItem((int)getX(), (int)getY(), (int)getZ(), item);
    }

    @Override
    public Location getSpawnPosition() {
        Location spawn = Canary.getServer().getDefaultWorld().getNormal().getSpawnLocation();
        OChunkCoordinates loc = ((OEntityPlayerMP)entity).ab();
        if (loc != null) {
            spawn = new Location(Canary.getServer().getDefaultWorld().getNormal(), loc.a, loc.b, loc.c, 0.0F, 0.0F);
        }
        return spawn;
    }
    
    @Override
    public Location getHome() {
        Warp home = Canary.warps().getHome(this);
        if(home != null) {
            return home.getLocation();
        }
        return getSpawnPosition();
    }
    
    @Override
    public void setHome(Location home) {
        Canary.warps().setHome(this, home);
    }
    
    @Override
    public boolean hasHome() {
        Warp home = Canary.warps().getHome(this);
        if(home != null) {
            return true;
        }
        return false;
    }

    @Override
    public void setSpawnPosition(Location spawn) {
        OChunkCoordinates loc = new OChunkCoordinates((int)spawn.getX(), (int)spawn.getY(), (int)spawn.getZ());
        ((OEntityPlayerMP)entity).a(loc);
    }

    @Override
    public String getIP() {
        String ip = ((OEntityPlayerMP)entity).a.b.c().toString();
        return ip.substring(1,ip.lastIndexOf(":"));
    }

    @Override
    public boolean executeCommand(String[] command) {
        try {
            if(Configuration.getServerConfig().isLogging()) {
                Logman.logInfo("Command used by " + getName() + ": " + Canary.glueString(command, 0, " "));
            }
            
            String commandName = command[0];
            //It's a vanilla command, forward it to the server
            if (commandName.startsWith("/#") && (hasPermission("canary.commands.vanilla."+commandName.replace("/#", "")) || hasPermission("canary.vanilla.op"))) {
                return Canary.getServer().consoleCommand(Canary.glueString(command, 0, " ").replace("/#", ""), this);
            }
            commandName = commandName.replace("/", "");
            PlayerCommandHook hook = new PlayerCommandHook(this, command);
            Canary.hooks().callHook(hook);
            if (hook.isCanceled()) {
                return true;
            } // someone wants us not to execute the command. So lets do them the favor
            
            CanaryCommand com = Canary.commands().getCommand(commandName);
            if(com != null) {
                return com.parseCommand(this, command);
            }
            else {
                if (Configuration.getServerConfig().getShowUnkownCommand()) {
                    notify("Unknown command");
                }
                return false;
            }
        } catch (Throwable ex) {
            Logman.logStackTrace("Exception in command handler: ", ex);
            if (isAdmin()) {
                sendMessage(Colors.Rose + "Exception occured. "+ex.getMessage());
            }
            return false;
        }
    }

    @Override
    public boolean canFly() {
        return hasPermission("canary.player.canFly");
    }

    @Override
    public boolean isFlying() {
        return ((OEntityPlayerMP)entity).L.b;
    }

    @Override
    public void setFlying(boolean flying) {
        ((OEntityPlayerMP)entity).L.b = flying;
    }

    @Override
    public void sendPacket(Packet packet) {
        getDimension().getEntityTracker().sendPacketToTrackedPlayer(this, packet);
    }

    @Override
    public Group getGroup() {
        return group;
    }

    @Override
    public Group[] getPlayerGroups() {
        return group.parentsToList().toArray(new Group[]{});
    }

    @Override
    public void setGroup(Group group) {
         this.group = group;
         Canary.usersAndGroups().addOrUpdatePlayerData(this);
    }

    @Override
    public boolean hasPermission(String permission) {
        if(!group.hasPermission(permission)) {
            return permissions.queryPermission(permission);
        }
        return true;
    }

    @Override
    public boolean isAdmin() {
        return hasPermission("canary.super.administrator");
    }

    @Override
    public boolean canBuild() {
        return hasPermission("canary.world.build") || hasPermission("canary.canilla.op") || isAdmin();
    }

    @Override
    public void setCanBuild(boolean canModify) {
        permissions.addPermission("canary.world.build", canModify);
    }

    @Override
    public boolean canIgnoreRestrictions() {
        return hasPermission("canary.super.ignoreRestrictions");
    }

    @Override
    public void setCanIgnoreRestrictions(boolean canIgnore) {
        permissions.addPermission("canary.player.ignoreRestrictions", canIgnore, -1);
    }

    @Override
    public boolean isMuted() {
        return muted;
    }

    @Override
    public void setMuted(boolean muted) {
         this.muted = muted;
    }

    @Override
    public PermissionProvider getPermissionProvider() {
        return permissions;
    }

    @Override
    public Location getLocation() {
        return new Location(entity.bi.getCanaryDimension(), getX(), getY(), getZ(), getPitch(), getRotation());
    }
    
    @Override
    public Vector3D getPosition() {
        return new Vector3D(getX(), getY(), getZ());
    }

    @Override
    public Inventory getInventory() {
        return ((OEntityPlayer)entity).getInventory();
    }

    @Override
    public void giveItem(Item item) {
        ((OEntityPlayer)entity).getInventory().addItem(item.getId(), item.getAmount());
        ((OEntityPlayer)entity).getInventory().update();
    }

    @Override
    public boolean isInGroup(Group group, boolean parents) {
        if(this.group.name.equals(group.name)) {
            return true;
        }
        else {
            if(parents) {
                return this.group.parentsToList().contains(group);
            }
            return false;
        }
    }

    @Override
    public void teleportTo(double x, double y, double z) {
        teleportTo(x, y, z, 0.0F, 0.0F);
    }

    public void teleportTo(Vector3D position) {
        teleportTo(position.getX(), position.getY(), position.getZ(), 0.0f, 0.0f);
    }
    @Override
    public void teleportTo(double x, double y, double z, Dimension dim) {
        if (!(getDimension().hashCode() == dim.hashCode())) {
            switchWorlds(dim);
        }
        teleportTo(x, y, z, 0.0F, 0.0F);
        
    }

    @Override
    public void teleportTo(double x, double y, double z, float pitch, float rotation, Dimension dim) {
        if (!(getDimension().hashCode() == dim.hashCode())) {
            switchWorlds(dim);
        }
        teleportTo(x, y, z, pitch, rotation);
    }

    @Override
    public void teleportTo(double x, double y, double z, float pitch, float rotation) {
        OEntityPlayerMP player = (OEntityPlayerMP) entity;
        // If player is in vehicle - eject them before they are teleported.
        if (player.bh != null) {
            player.b(player.bh);
        }
        player.a.a(x, y, z, rotation, pitch, getDimension().getType().getId(), getDimension().getName());
        
    }

    @Override
    public void teleportTo(Location location) {
        if (!(getDimension().hashCode() == location.getDimension().hashCode())) {
            switchWorlds(location.getDimension());
        }
        teleportTo(location.getX(),location.getY(), location.getZ(),location.getPitch(), location.getRotation());
    }

    @Override
    public void kick(String reason) {
        ((OEntityPlayerMP)entity).a.a(reason);
    }

    @Override
    public void notify(String message) {
        sendMessage(Colors.Rose+message);
        
    }

    @Override
    public String getColor() {
        if(prefix != null) {
            return Colors.Marker+prefix;
        }
        else if(group.prefix != null) {
            return Colors.Marker+group.prefix;
        }
        else {
            return Colors.Marker+Colors.White;
        }
    }

    @Override
    public NetServerHandler getNetServerHandler() {
        return ((OEntityPlayerMP)entity).getServerHandler();
    }
    
    @Override
    public int damageVsEntity(Entity entity) {
        return ((OEntityPlayerMP)entity).k.a(((CanaryEntity) entity).getHandle());
    }
    
    @Override
    public float damageVsBlock(Block block) {
        return ((OEntityPlayerMP)entity).k.a(OBlock.m[block.getType()]);
    }

    @Override
    public boolean isInGroup(String group, boolean parents) {
        if(parents) {
            ArrayList<Group> groups = this.group.parentsToList();
            for(Group g : groups) {
                if(g.name.equals(group)) {
                    return true;
                }
            }
        }
        else {
            if(this.group.name.equals(group)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String[] getAllowedIPs() {
        return allowedIPs;
    }

    @Override
    public Direction getCardinalDirection() {
        double degrees = (getRotation() - 180) % 360;

        if (degrees < 0) {
            degrees += 360.0;
        }
        
        if (0 <= degrees && degrees < 22.5) {
            return Direction.NORTH;
        } else if (22.5 <= degrees && degrees < 67.5) {
            return Direction.NORTHEAST;
        } else if (67.5 <= degrees && degrees < 112.5) {
            return Direction.EAST;
        } else if (112.5 <= degrees && degrees < 157.5) {
            return Direction.SOUTHEAST;
        } else if (157.5 <= degrees && degrees < 202.5) {
            return Direction.SOUTH;
        } else if (202.5 <= degrees && degrees < 247.5) {
            return Direction.SOUTHWEST;
        } else if (247.5 <= degrees && degrees < 292.5) {
            return Direction.WEST;
        } else if (292.5 <= degrees && degrees < 337.5) {
            return Direction.NORTHWEST;
        } else if (337.5 <= degrees && degrees < 360.0) {
            return Direction.NORTH;
        } else {
            return Direction.ERROR;
        }
    }
    
    public void switchWorlds(Dimension dim) {
        OMinecraftServer mcServer = ((CanaryServer) Canary.getServer()).getHandle();
        OEntityPlayerMP ent = (OEntityPlayerMP) entity;
        
        // Nether is not allowed, so shush
        if (dim.getType() == Dimension.Type.NETHER && !mcServer.d.a("allow-nether", true)) {
            return;
        }
        // The End is not allowed, so shush
        if (dim.getType() == Dimension.Type.END && !mcServer.d.a("allow-end", true)) {
            return;
        }
        // Dismount first or get buggy
        if (ent.bh != null) {
            ent.b(ent.bh);
        }

        //Collect world switch achievement ?
        ent.a((OStatBase) OAchievementList.B);
        
        //switch world if needed
        if(!(dim.getName().equals(ent.bi.getCanaryDimension().getName()))) {
            Dimension oldWorld = ent.bi.getCanaryDimension();
            //remove player from entity tracker
            oldWorld.getEntityTracker().untrackPlayerSymmetrics(ent.getPlayer());
            oldWorld.getEntityTracker().untrackEntity(ent.getPlayer());
            //remove player from old worlds entity list
            oldWorld.removePlayerFromWorld(ent.getPlayer());
            //Remove player from player manager for the old world
            oldWorld.getPlayerManager().removePlayer(ent.getPlayer());
            
            //Change players world reference
            ent.bi = ((CanaryDimension) dim).getHandle();
            //Add player back to the new world
//            dim.addPlayerToWorld(this);
//            dim.getPlayerManager().addPlayer(this);
        }
        //Get chunk coordinates...
//        OChunkCoordinates var2 = mcServer.getWorld(ent.bi.getCanaryDimension().getName(), dim.getType().getId()).d();
        OChunkCoordinates var2 = ent.bi.d();

        if (var2 != null) {
            ent.a.a((double) var2.a, (double) var2.b, (double) var2.c, 0.0F, 0.0F, dim.getType().getId(), ent.bi.getCanaryDimension().getName());
        }

        mcServer.h.switchDimension(ent, dim.getType().getId(), false);
        
        refreshCreativeMode();
    }

    @Override
    public EntityItem[] dropInventory() {
        Item[] items = getInventory().getContents();
        EntityItem[] drops = new EntityItem[items.length];
        for(int i = 0; i < items.length; i++) {
            if(items[i] == null) {
                continue;
            }
            drops[i] = getDimension().dropItem(getPosition(), items[i]);
            
        }
        getInventory().clearContents();
        return drops;
    }

    @Override
    public boolean isDamageDisabled() {
        return ((OEntityPlayerMP)entity).L.a;
    }
    
    @Override
    public void setDamageDisabled(boolean disabled) {
        ((OEntityPlayerMP)entity).L.a = disabled;
    }

    @Override
    public int getMode() {
        return ((OEntityPlayerMP)entity).c.a();
    }

    @Override
    public void setMode(int mode) {
        //Adjust mode, make it null if number is invalid
        mode = OWorldSettings.a(mode);
        OEntityPlayerMP ent = ((OEntityPlayerMP)entity);
        if (ent.c.a() != mode) {
            ent.c.a(mode);
            ent.a.b((OPacket) (new OPacket70Bed(3, mode)));
        }
    }
    
    public void refreshCreativeMode() {
        if (getMode() == 1 || Configuration.getWorldConfig(getWorld().getName()).getGameMode() == World.GameMode.CREATIVE) {
            ((OEntityPlayerMP)entity).c.a(1);
        } else {
            ((OEntityPlayerMP)entity).c.a(0);
        }
    }

}
