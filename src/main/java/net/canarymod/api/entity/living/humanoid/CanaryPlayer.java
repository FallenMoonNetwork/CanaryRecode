package net.canarymod.api.entity.living.humanoid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.canarymod.Canary;
import net.canarymod.ToolBox;
import net.canarymod.api.CanaryPacket;
import net.canarymod.api.GameMode;
import net.canarymod.api.NetServerHandler;
import net.canarymod.api.Packet;
import net.canarymod.api.PlayerListEntry;
import net.canarymod.api.entity.EntityType;
import net.canarymod.api.inventory.EnderChestInventory;
import net.canarymod.api.world.World;
import net.canarymod.api.world.position.Direction;
import net.canarymod.api.world.position.Location;
import net.canarymod.chat.Colors;
import net.canarymod.chat.TextFormat;
import net.canarymod.config.Configuration;
import net.canarymod.hook.command.PlayerCommandHook;
import net.canarymod.hook.player.ChatHook;
import net.canarymod.hook.player.TeleportHook;
import net.canarymod.hook.player.TeleportHook.TeleportCause;
import net.canarymod.hook.system.PermissionCheckHook;
import net.canarymod.permissionsystem.PermissionProvider;
import net.canarymod.user.Group;
import net.canarymod.warp.Warp;
import net.minecraft.server.ChunkCoordinates;
import net.minecraft.server.EntityPlayerMP;
import net.minecraft.server.EnumGameType;
import net.minecraft.server.Packet201PlayerInfo;
import net.minecraft.server.WorldSettings;
import net.visualillusionsent.utils.StringUtils;

/**
 * Canary Player wrapper.
 * 
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public class CanaryPlayer extends CanaryHuman implements Player {
    private Pattern badChatPattern = Pattern.compile("[\u00a7\u2302\u00D7\u00AA\u00BA\u00AE\u00AC\u00BD\u00BC\u00A1\u00AB\u00BB]");
    private List<Group> groups;
    private PermissionProvider permissions;
    private boolean muted;
    private String[] allowedIPs;
    private HashMap<String, String> defaultChatpattern = new HashMap<String, String>();

    public CanaryPlayer(EntityPlayerMP entity) {
        super(entity);
        initPlayerData();
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.PLAYER;
    }

    @Override
    public String getFqName() {
        return "Player";
    }

    @Override
    public void chat(final String message) {
        if (message.length() > 100) {
            kick("Message too long!");
        }
        String out = message.trim();
        Matcher m = badChatPattern.matcher(out);

        if (m.find() && !this.canIgnoreRestrictions()) {
            out = out.replaceAll(m.group(), "");
        }

        // TODO: Add configuration for spam protection
        if (out.startsWith("/")) {
            executeCommand(out.split(" "));
        } else {
            if (isMuted()) {
                notice("You are currently muted!");
            } else {
                // This is a copy of the real player list already, no need to copy again (re: Collections.copy())
                ArrayList<Player> receivers = Canary.getServer().getPlayerList();
                defaultChatpattern.put("%message", out);
                ChatHook hook = (ChatHook) new ChatHook(this, "<%prefix%name" + Colors.WHITE + "> %message", receivers, defaultChatpattern).call();
                if (hook.isCanceled()) {
                    return;
                }
                receivers = hook.getReceiverList();
                String formattedMessage = hook.buildSendMessage();
                for (Player player : receivers) {
                    player.message(formattedMessage);
                }
                Canary.logInfo("[" + getName() + "] " + TextFormat.removeFormatting(formattedMessage));
            }
        }

    }

    @Override
    public void message(String message) {
        getNetServerHandler().sendMessage(message);
        // Should cover all chat logging
    }

    @Override
    public void notice(String message) {
        message(Colors.LIGHT_RED + message);
    }

    @Override
    public Location getSpawnPosition() {
        Location spawn = Canary.getServer().getDefaultWorld().getSpawnLocation();
        ChunkCoordinates loc = ((EntityPlayerMP) entity).bE();

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
        return Canary.warps().getHome(this) != null;
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
                Canary.logInfo("Command used by " + getName() + ": " + StringUtils.joinString(command, " ", 0));
            }

            String commandName = command[0];

            // It's a vanilla command, forward it to the server
            if (commandName.startsWith("/#") && (hasPermission("canary.commands.vanilla." + commandName.replace("/#", "")) || hasPermission("canary.vanilla.op"))) {
                return Canary.getServer().consoleCommand(StringUtils.joinString(command, " ", 0).replace("/#", ""), this);
            }
            commandName = commandName.replace("/", "");
            PlayerCommandHook hook = new PlayerCommandHook(this, command);

            Canary.hooks().callHook(hook);
            if (hook.isCanceled()) {
                return true;
            } // someone wants us not to execute the command. So lets do them the favor

            if (!Canary.commands().parseCommand(this, commandName, command)) {
                if (Configuration.getServerConfig().getShowUnkownCommand()) {
                    notice("Unknown command");
                }
                return false;
            }
            return true;

        } catch (Throwable ex) {
            Canary.logStacktrace("Exception in command handler: ", ex);
            if (isAdmin()) {
                message(Colors.LIGHT_RED + "Exception occured. " + ex.getMessage());
            }
            return false;
        }
    }

    @Override
    public void sendPacket(Packet packet) {
        getHandle().a.b(((CanaryPacket) packet).getPacket());
    }

    @Override
    public Group getGroup() {
        return groups.get(0);
    }

    @Override
    public Group[] getPlayerGroups() {
        return groups.toArray(new Group[0]);
    }

    @Override
    public void setGroup(Group group) {
        groups.set(0, group);
        Canary.usersAndGroups().addOrUpdatePlayerData(this);
    }

    @Override
    public void addGroup(Group group) {
        if (!groups.contains(group)) {
            groups.add(group);
            Canary.usersAndGroups().addOrUpdatePlayerData(this);
        }
    }

    @Override
    public boolean hasPermission(String permission) {
        PermissionCheckHook hook = new PermissionCheckHook(permission, this, false);
        // If player has the permission set, use its personal permissions
        if (permissions.pathExists(permission)) {
            hook.setResult(permissions.queryPermission(permission));
            Canary.hooks().callHook(hook);
            return hook.getResult();
        }
        // Only main group is set
        else if (groups.size() == 1) {
            hook.setResult(groups.get(0).hasPermission(permission));
            Canary.hooks().callHook(hook);
            return hook.getResult();
        }

        // Check sub groups
        for (int i = 1; i < groups.size(); i++) {
            // First group that
            if (groups.get(i).getPermissionProvider().pathExists(permission)) {
                hook.setResult(groups.get(i).hasPermission(permission));
                Canary.hooks().callHook(hook);
                return hook.getResult();
            }
        }

        // No subgroup has permission defined, use what base group has to say
        hook.setResult(groups.get(0).hasPermission(permission));
        Canary.hooks().callHook(hook);
        return hook.getResult();
    }

    @Override
    public boolean safeHasPermission(String permission) {
        // If player has the permission set, use its personal permissions
        if (permissions.pathExists(permission)) {
            return permissions.queryPermission(permission);
        }
        // Only main group is set
        else if (groups.size() == 1) {
            return groups.get(0).hasPermission(permission);
        }

        // Check sub groups
        for (int i = 1; i < groups.size(); i++) {
            // First group that
            if (groups.get(i).getPermissionProvider().pathExists(permission)) {
                return groups.get(i).hasPermission(permission);
            }
        }

        // No subgroup has permission defined, use what base group has to say
        return groups.get(0).hasPermission(permission);
    }

    @Override
    public boolean isAdmin() {
        return hasPermission("canary.super.administrator") || Canary.ops().isOpped(getName());
    }

    @Override
    public boolean canBuild() {
        return hasPermission("canary.world.build") || hasPermission("canary.vanilla.op") || isAdmin();
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
        permissions.addPermission("canary.super.ignoreRestrictions", canIgnore, -1);
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
    public EnderChestInventory getEnderChestInventory() {
        return (EnderChestInventory) ((EntityPlayerMP) entity).getEnderChestInventory();
    }

    @Override
    public boolean isInGroup(Group group, boolean parents) {
        for (Group g : groups) {
            if (g.getName().equals(group.getName())) {
                return true;
            }
            if (parents) {
                for (Group parent : g.parentsToList()) {
                    if (parent.getName().equals(group.getName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void kick(String reason) {
        ((EntityPlayerMP) entity).a.c(reason);
    }

    @Override
    public NetServerHandler getNetServerHandler() {
        return ((EntityPlayerMP) entity).getServerHandler();
    }

    @Override
    public boolean isInGroup(String group, boolean parents) {
        for (Group g : groups) {
            if (g.getName().equals(group)) {
                return true;
            }
            if (parents) {
                for (Group parent : g.parentsToList()) {
                    if (parent.getName().equals(group)) {
                        return true;
                    }
                }
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

    @Override
    public void initPlayerData() {
        String[] data = Canary.usersAndGroups().getPlayerData(getName());
        Group[] subs = Canary.usersAndGroups().getModuleGroupsForPlayer(getName());
        groups = new LinkedList<Group>();
        groups.add(Canary.usersAndGroups().getGroup(data[1]));
        for (Group g : subs) {
            if (g != null) {
                groups.add(g);
            }
        }

        permissions = Canary.permissionManager().getPlayerProvider(getName(), getWorld().getFqName());
        if (data[0] != null && (!data[0].isEmpty() && !data[0].equals(" "))) {
            prefix = ToolBox.stringToNull(data[0]);
        }

        if (data[2] != null && !data[2].isEmpty()) {
            muted = Boolean.valueOf(data[2]);
        }
        defaultChatpattern.put("%name", getDisplayName());
        defaultChatpattern.put("%prefix", getPrefix());
    }

    @Override
    public boolean removeGroup(Group group) {
        if (groups.get(0).equals(group)) {
            return false;
        }
        boolean success = groups.remove(group);
        if (success) {
            Canary.usersAndGroups().addOrUpdatePlayerData(this);
        }
        return success;
    }

    @Override
    public boolean removeGroup(String group) {
        Group g = Canary.usersAndGroups().getGroup(group);
        if (g == null) {
            return false;
        }
        return removeGroup(g);
    }

    @Override
    public int getPing() {
        return ((EntityPlayerMP) entity).i;
    }

    @Override
    public PlayerListEntry getPlayerListEntry(boolean shown) {
        return new PlayerListEntry(this, shown);
    }

    @Override
    public void sendPlayerListEntry(PlayerListEntry plentry) {
        if (Configuration.getServerConfig().isPlayerListEnabled()) {
            ((EntityPlayerMP) entity).a.b(new Packet201PlayerInfo(plentry.getName(), plentry.isShown(), plentry.getPing()));
        }
    }

    @Override
    public void addExhaustion(float exhaustion) {
        getHandle().bH().a(exhaustion);
    }

    @Override
    public void setExhaustion(float exhaustion) {
        getHandle().bH().setExhaustionLevel(exhaustion);
    }

    @Override
    public float getExhaustionLevel() {
        return getHandle().bH().getExhaustionLevel();
    }

    @Override
    public void setHunger(int hunger) {
        getHandle().bH().setFoodLevel(hunger);
    }

    @Override
    public int getHunger() {
        return getHandle().bH().a();
    }

    @Override
    public void addExperience(int experience) {
        getHandle().addXP(experience);
    }

    @Override
    public void removeExperience(int experience) {
        getHandle().removeXP(experience);
    }

    @Override
    public int getExperience() {
        return getHandle().bI;
    }

    @Override
    public void setExperience(int xp) {
        if (xp < 0) {
            return;
        }
        getHandle().setXP(xp);
    }

    @Override
    public int getLevel() {
        return getHandle().bH;
    }

    @Override
    public boolean isSleeping() {
        return getHandle().bg();
    }

    @Override
    public boolean isDeeplySleeping() {
        return getHandle().bC();
    }

    @Override
    public int getModeId() {
        return getHandle().c.b().a();
    }

    public GameMode getMode() {
        return GameMode.fromId(getHandle().c.b().a());
    }

    @Override
    public void setModeId(int mode) {
        // Adjust mode, make it null if number is invalid
        EnumGameType gt = WorldSettings.a(mode);
        EntityPlayerMP ent = getHandle();

        if (ent.c.b() != gt) {
            ent.c.a(gt);
            ent.getServerHandler().sendPacket(new CanaryPacket(new net.minecraft.server.Packet70GameEvent(3, mode)));
        }
    }

    @Override
    public void setMode(GameMode mode) {
        this.setModeId(mode.getId());
    }

    @Override
    public void refreshCreativeMode() {
        if (getModeId() == 1 || Configuration.getWorldConfig(getWorld().getFqName()).getGameMode() == GameMode.CREATIVE) {
            getHandle().c.a(WorldSettings.a(1));
        } else {
            getHandle().c.a(WorldSettings.a(0));
        }
    }

    @Override
    public void setDimension(World world) {
        Canary.getServer().getConfigurationManager().switchDimension(this, world, false);
    }

    @Override
    public void teleportTo(double x, double y, double z, float pitch, float rotation) {
        this.teleportTo(x, y, z, pitch, rotation, getWorld());
    }

    @Override
    public void teleportTo(double x, double y, double z, float pitch, float rotation, World dim) {
        this.teleportTo(x, y, z, pitch, rotation, dim, TeleportHook.TeleportCause.PLUGIN);
    }

    @Override
    public void teleportTo(Location location, TeleportCause cause) {
        this.teleportTo(location.getX(), location.getY(), location.getZ(), location.getPitch(), location.getRotation(), location.getWorld(), cause);
    }

    protected void teleportTo(double x, double y, double z, float pitch, float rotation, World world, TeleportHook.TeleportCause cause) {
        // If in a vehicle - eject before teleporting.
        if (isRiding()) {
            getHandle().h(getHandle().o);
        }
        if (world != this.getWorld()) {
            this.setDimension(world);
        }

        getHandle().a.a(x, y, z, rotation, pitch, getWorld().getType().getId(), getWorld().getName(), cause);
    }

    @Override
    public String getPrefix() {
        if (prefix != null) {
            return prefix;
        } else if (groups.get(0).getPrefix() != null) {
            return groups.get(0).getPrefix();
        } else {
            return Colors.WHITE;
        }
    }

    @Override
    public void setPrefix(String prefix) {
        super.setPrefix(prefix);
        Canary.usersAndGroups().addOrUpdatePlayerData(this);
        this.defaultChatpattern.put("%prefix", getPrefix());
    }

    @Override
    public String toString() {
        return String.format("Player[name=%s]", getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        final Player other = (Player) obj;

        return getName().equals(other.getName());
    }

    @Override
    public int hashCode() {
        int hash = 7;

        hash = 89 * hash + (this.getName() != null ? this.getName().hashCode() : 0);
        return hash;
    }

    public EntityPlayerMP getHandle() {
        return (EntityPlayerMP) entity;
    }
}
