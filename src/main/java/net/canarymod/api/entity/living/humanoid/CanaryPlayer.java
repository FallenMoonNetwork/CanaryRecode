package net.canarymod.api.entity.living.humanoid;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.canarymod.Canary;
import net.canarymod.ToolBox;
import net.canarymod.api.CanaryPacket;
import net.canarymod.api.NetServerHandler;
import net.canarymod.api.Packet;
import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.EntityItem;
import net.canarymod.api.entity.living.CanaryEntityLiving;
import net.canarymod.api.inventory.CanaryPlayerInventory;
import net.canarymod.api.inventory.EnderChestInventory;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.World;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.position.Direction;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Position;
import net.canarymod.chat.Colors;
import net.canarymod.chat.TextFormat;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.config.Configuration;
import net.canarymod.hook.command.PlayerCommandHook;
import net.canarymod.hook.player.ChatHook;
import net.canarymod.permissionsystem.PermissionProvider;
import net.canarymod.user.Group;
import net.canarymod.warp.Warp;
import net.minecraft.server.ChunkCoordinates;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.EntityPlayerMP;
import net.minecraft.server.EnumGameType;
import net.minecraft.server.ItemStack;
import net.minecraft.server.WorldSettings;


/**
 * Canary Player wrapper.
 * 
 * @author Chris
 */
public class CanaryPlayer extends CanaryEntityLiving implements Player {
    private Pattern badChatPattern = Pattern.compile("[\u00a7\u2302\u00D7\u00AA\u00BA\u00AE\u00AC\u00BD\u00BC\u00A1\u00AB\u00BB]");
    private Group group;
    private PermissionProvider permissions;
    private String prefix = null;
    private boolean muted;
    private String[] allowedIPs;

    public CanaryPlayer(EntityPlayerMP entity) {
        super(entity);
        Canary.println("Creating CanaryPlayer");
        String[] data = Canary.usersAndGroups().getPlayerData(getName());

        group = Canary.usersAndGroups().getGroup(data[1]);
        permissions = Canary.permissionManager().getPlayerProvider(getName());
        if (data[0] != null && (!data[0].isEmpty() && !data[0].equals(" "))) {
            prefix = ToolBox.stringToNull(data[0]);
        }

        //        if (data[2] != null && !data[2].isEmpty()) {
        //            allowedIPs = data[2].split(",");
        //        }
    }

    /**
     * CanaryMod: Get player handle
     */
    @Override
    public EntityPlayerMP getHandle() {
        return (EntityPlayerMP) entity;
    }

    @Override
    public String getName() {
        return ((EntityPlayerMP) entity).bS;
    }

    @Override
    public void chat(String message) {
        Canary.println(getName() + " is chatting");
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

        // TODO: Add configuration for spam protection?

        if (message.startsWith("/")) {
            executeCommand(message.split(" "));
        } else {
            if (isMuted()) {
                notice("You are currently muted!");
            } else {
                String prefix = "<" + getColor() + getName() + Colors.WHITE + "> ";

                // This is a copy of the real player list already, no need to copy again (re: Collections.copy())
                ArrayList<Player> receivers = Canary.getServer().getPlayerList();

                ChatHook hook = new ChatHook(this, prefix, message, receivers);

                Canary.hooks().callHook(hook);
                if (hook.isCanceled()) {
                    return;
                }
                receivers = hook.getReceiverList();
                String toSend = hook.getPrefix() + hook.getMessage();

                for (Player player : receivers) {
                    if (hook.getPrefix().length() + hook.getMessage().length() >= 100) {
                        player.sendMessage(hook.getPrefix());
                        player.sendMessage(hook.getMessage());
                    } else {
                        player.sendMessage(toSend);
                    }
                }
            }
        }

    }

    @Override
    public void kill() {
        super.kill();
        dropInventory();
    }

    @Override
    public void sendMessage(String message) {
        getNetServerHandler().sendMessage(message);
        // Should cover all chat logging
        Canary.logInfo(TextFormat.removeFormatting(message));
    }

    @Override
    public void addExhaustion(float exhaustion) {
        ((EntityPlayerMP) entity).cl().a(exhaustion);
    }

    @Override
    public void setExhaustion(float exhaustion) {
        ((EntityPlayerMP) entity).cl().setExhaustionLevel(exhaustion);
    }

    @Override
    public float getExhaustionLevel() {
        return ((EntityPlayerMP) entity).cl().getExhaustionLevel();
    }

    @Override
    public void setHunger(int hunger) {
        ((EntityPlayerMP) entity).cl().setFoodLevel(hunger);
    }

    @Override
    public int getHunger() {
        return ((EntityPlayerMP) entity).cl().a();
    }

    @Override
    public void addExperience(int experience) {
        ((EntityPlayerMP) entity).addXP(experience);
    }

    @Override
    public void removeExperience(int experience) {
        ((EntityPlayerMP) entity).removeXP(experience);
    }

    @Override
    public int getExperience() {
        return ((EntityPlayerMP) entity).cg;
    }

    @Override
    public void setExperience(int xp) {
        if (xp < 0) {
            return;
        }
        ((EntityPlayerMP) entity).setXP(xp);
    }

    @Override
    public int getLevel() {
        return ((EntityPlayerMP) entity).cf;
    }

    @Override
    public boolean isSleeping() {
        return ((EntityPlayerMP) entity).bz();
    }

    @Override
    public boolean isDeeplySleeping() {
        return ((EntityPlayerMP) entity).cg();
    }

    @Override
    public void destroyItemHeld() {
        ((EntityPlayerMP) entity).bY();
    }

    @Override
    public Item getItemHeld() {
        ItemStack item = ((CanaryPlayerInventory) getInventory()).getItemInHand();

        if (item != null) {
            return item.getCanaryItem();
        }
        return null;
    }

    @Override
    public void dropItem(Item item) {
        getWorld().dropItem((int) getX(), (int) getY(), (int) getZ(), item);
    }

    @Override
    public Location getSpawnPosition() {
        Location spawn = Canary.getServer().getDefaultWorld().getSpawnLocation();
        ChunkCoordinates loc = ((EntityPlayerMP) entity).ci();

        if (loc != null) {
            spawn = new Location(Canary.getServer().getDefaultWorld(), loc.a, loc.b, loc.c, 0.0F, 0.0F);
        }
        return spawn;
    }

    @Override
    public Location getHome() {
        Warp home = Canary.warps().getHome(this);

        if (home != null) {
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

        if (home != null) {
            return true;
        }
        return false;
    }

    @Override
    public void setSpawnPosition(Location spawn) {
        ChunkCoordinates loc = new ChunkCoordinates((int) spawn.getX(), (int) spawn.getY(), (int) spawn.getZ());
        ((EntityPlayerMP) entity).a(loc, true);
    }

    @Override
    public String getIP() {
        String ip = ((EntityPlayerMP) entity).a.a.c().toString();

        return ip.substring(1, ip.lastIndexOf(":"));
    }

    @Override
    public boolean executeCommand(String[] command) {
        try {
            if (Configuration.getServerConfig().isLogging()) {
                Canary.logInfo("Command used by " + getName() + ": " + Canary.glueString(command, 0, " "));
            }

            String commandName = command[0];

            // It's a vanilla command, forward it to the server
            if (commandName.startsWith("/#") && (hasPermission("canary.commands.vanilla." + commandName.replace("/#", "")) || hasPermission("canary.vanilla.op"))) {
                return Canary.getServer().consoleCommand(Canary.glueString(command, 0, " ").replace("/#", ""), this);
            }
            commandName = commandName.replace("/", "");
            PlayerCommandHook hook = new PlayerCommandHook(this, command);

            Canary.hooks().callHook(hook);
            if (hook.isCanceled()) {
                return true;
            } // someone wants us not to execute the command. So lets do them the favor

            CanaryCommand com = Canary.commands().getCommand(commandName);

            if (com != null) {
                return com.parseCommand(this, command);
            } else {
                if (Configuration.getServerConfig().getShowUnkownCommand()) {
                    notice("Unknown command");
                }
                return false;
            }
        } catch (Throwable ex) {
            Canary.logStackTrace("Exception in command handler: ", ex);
            if (isAdmin()) {
                sendMessage(Colors.LIGHT_RED + "Exception occured. " + ex.getMessage());
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
        return ((EntityPlayerMP) entity).ce.b;
    }

    @Override
    public void setFlying(boolean flying) {
        ((EntityPlayerMP) entity).ce.b = flying;
    }

    @Override
    public void sendPacket(Packet packet) {
        getWorld().getEntityTracker().sendPacketToTrackedPlayer(this, packet);
    }

    @Override
    public Group getGroup() {
        return group;
    }

    @Override
    public Group[] getPlayerGroups() {
        return group.parentsToList().toArray(new Group[] {});
    }

    @Override
    public void setGroup(Group group) {
        this.group = group;
        Canary.usersAndGroups().addOrUpdatePlayerData(this);
    }

    @Override
    public boolean hasPermission(String permission) {
        if (!group.hasPermission(permission)) {
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
        return new Location(entity.getCanaryWorld(), getX(), getY(), getZ(), getPitch(), getRotation());
    }

    @Override
    public Position getPosition() {
        return new Position(getX(), getY(), getZ());
    }

    @Override
    public Inventory getInventory() {
        return ((EntityPlayerMP) entity).getPlayerInventory();
    }

    @Override
    public EnderChestInventory getEnderChestInventory() {
        return (EnderChestInventory) ((EntityPlayerMP) entity).getEnderChestInventory();
    }

    @Override
    public void giveItem(Item item) {
        ((EntityPlayer) entity).getPlayerInventory().addItem(item.getId(), item.getAmount());
        ((EntityPlayer) entity).getPlayerInventory().update();
    }

    @Override
    public boolean isInGroup(Group group, boolean parents) {
        if (this.group.getName().equals(group.getName())) {
            return true;
        } else {
            if (parents) {
                return this.group.parentsToList().contains(group);
            }
            return false;
        }
    }

    @Override
    public void teleportTo(double x, double y, double z) {
        teleportTo(x, y, z, 0.0F, 0.0F);
    }

    @Override
    public void teleportTo(Position position) {
        teleportTo(position.getX(), position.getY(), position.getZ(), 0.0f, 0.0f);
    }

    @Override
    public void teleportTo(double x, double y, double z, World dim) {
        if (!(getWorld().getType().equals(dim.getType()))) {
            Canary.println("Switching world from " + getWorld().getFqName() + " to " + dim.getFqName());
            switchWorlds(dim);
        }
        teleportTo(x, y, z, 0.0F, 0.0F);

    }

    @Override
    public void teleportTo(double x, double y, double z, float pitch, float rotation, World dim) {
        if (!(getWorld().getType().equals(dim.getType()))) {
            Canary.println("Switching world from " + getWorld().getFqName() + " to " + dim.getFqName());
            switchWorlds(dim);
        }
        teleportTo(x, y, z, pitch, rotation);
    }

    @Override
    public void teleportTo(double x, double y, double z, float pitch, float rotation) {
        EntityPlayerMP player = (EntityPlayerMP) entity;

        // If player is in vehicle - eject them before they are teleported.
        if (isRiding()) {
            player.h(player.o);
        }
        player.a.a(x, y, z, rotation, pitch, getWorld().getType().getId(), getWorld().getName());

    }

    @Override
    public void teleportTo(Location location) {
        if (!(getWorld().getType().equals(location.getWorld().getType()))) {
            Canary.println("Switching world from " + getWorld().getFqName() + " to " + location.getWorld().getFqName());
            switchWorlds(location.getWorld());
        }
        teleportTo(location.getX(), location.getY(), location.getZ(), location.getPitch(), location.getRotation());
    }

    @Override
    public void kick(String reason) {
        ((EntityPlayerMP) entity).a.c(reason);
    }

    @Override
    public void notice(String message) {
        sendMessage(Colors.LIGHT_RED + message);

    }

    @Override
    public String getColor() {
        if (prefix != null) {
            return Colors.MARKER + prefix;
        } else if (group.getPrefix() != null) {
            return Colors.MARKER + group.getPrefix();
        } else {
            return Colors.WHITE;
        }
    }

    @Override
    public NetServerHandler getNetServerHandler() {
        return ((EntityPlayerMP) entity).getServerHandler();
    }

    @Override
    public int damageVsEntity(Entity entity) {
        return ((EntityPlayerMP) entity).bK.a(((CanaryEntity) entity).getHandle());
    }

    @Override
    public float damageVsBlock(Block block) {
        return ((EntityPlayerMP) entity).bK.a(net.minecraft.server.Block.r[block.getType()]);
    }

    @Override
    public boolean isInGroup(String group, boolean parents) {
        if (parents) {
            ArrayList<Group> groups = this.group.parentsToList();

            for (Group g : groups) {
                if (g.getName().equals(group)) {
                    return true;
                }
            }
        } else {
            if (this.group.getName().equals(group)) {
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

    public void switchWorlds(World dim) {
        Canary.println(getName() + " is switching dimension to " + dim.getType().getName());
        //        MinecraftServer mcServer = ((CanaryServer) Canary.getServer()).getHandle();
        EntityPlayerMP ent = (EntityPlayerMP) entity;

        // Do not check for worlds. let plugins handle restrictions!
        // // Nether is not allowed, so shush
        // if (dim.getType().equals(WorldType.fromName("NETHER")) && !mcServer.d.a("allow-nether", true)) {
        // return;
        // }
        // // The End is not allowed, so shush
        // if (dim.getType().equals(WorldType.fromName("END")) && !mcServer.d.a("allow-end", true)) {
        // return;
        // }

        // Dismount first or get buggy
        if (ent.o != null) {
            ent.h(ent.o);
        }
        Canary.getServer().getConfigurationManager().switchDimension(this, dim, false);
        // Collect world switch achievement ?
        //        ent.a((StatBase) AchievementList.B);
        //        // switch world if needed
        //        if (!(dim.getName().equals(ent.getCanaryWorld().getName()))) {
        //            World oldWorld = ent.getCanaryWorld();
        //
        //            // remove player from entity tracker
        //            oldWorld.getEntityTracker().untrackPlayerSymmetrics(ent.getPlayer());
        //            oldWorld.getEntityTracker().untrackEntity(ent.getPlayer());
        //            // remove player from old worlds entity list
        //            oldWorld.removePlayerFromWorld(ent.getPlayer());
        //            // Remove player from player manager for the old world
        //            oldWorld.getPlayerManager().removePlayer(ent.getPlayer());
        //
        //            // Change players world reference
        //            ent.q = ((CanaryWorld) dim).getHandle();
        //            // Add player back to the new world
        //            // dim.addPlayerToWorld(this);
        //            // dim.getPlayerManager().addPlayer(this);
        //        }
        //        // Get chunk coordinates...
        //        // OChunkCoordinates var2 = mcServer.getWorld(ent.bi.getCanaryDimension().getName(), dim.getType().getId()).d();
        //        Location l = ent.getCanaryWorld().getSpawnLocation();
        //        ChunkCoordinates var2 = new ChunkCoordinates((int)l.getX(), (int)l.getY(), (int)l.getZ());
        //
        //        if (var2 != null) {
        //            ent.a.a((double) var2.a, (double) var2.b, (double) var2.c, 0.0F, 0.0F, dim.getType().getId(), ent.getCanaryWorld().getName());
        //        }
        //        Canary.logInfo("Prepared to switch worlds.");
        //        mcServer.getConfigurationManager().switchDimension(ent.getPlayer(), dim, false);
        //
        //        refreshCreativeMode();
    }

    @Override
    public EntityItem[] dropInventory() {
        Item[] items = getInventory().getContents();
        EntityItem[] drops = new EntityItem[items.length];

        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                continue;
            }
            drops[i] = getWorld().dropItem(getPosition(), items[i]);

        }
        getInventory().clearContents();
        return drops;
    }

    @Override
    public boolean isDamageDisabled() {
        return ((EntityPlayerMP) entity).ce.a;
    }

    @Override
    public void setDamageDisabled(boolean disabled) {
        ((EntityPlayerMP) entity).ce.a = disabled;
    }

    @Override
    public int getMode() {
        return ((EntityPlayerMP) entity).c.b().a();
    }

    @Override
    public void setMode(int mode) {
        // Adjust mode, make it null if number is invalid
        EnumGameType gt = WorldSettings.a(mode);
        EntityPlayerMP ent = ((EntityPlayerMP) entity);

        if (ent.c.b() != gt) {
            ent.c.a(gt);
            ent.getServerHandler().sendPacket(new CanaryPacket(new net.minecraft.server.Packet70GameEvent(3, mode)));
        }
    }

    public void refreshCreativeMode() {
        if (getMode() == 1 || Configuration.getWorldConfig(getWorld().getName()).getGameMode() == World.GameMode.CREATIVE) {
            ((EntityPlayerMP) entity).c.a(WorldSettings.a(1));
        } else {
            ((EntityPlayerMP) entity).c.a(WorldSettings.a(0));
        }
    }

    @Override
    public boolean isBlocking() {
        return ((EntityPlayerMP) entity).bk();
    }

    @Override
    public boolean isInVehicle() {
        return ((EntityPlayerMP) entity).o != null;
    }

    @Override
    public void message(String message) {
        sendMessage(message);
    }

}
