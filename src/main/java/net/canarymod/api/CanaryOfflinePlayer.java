package net.canarymod.api;

import java.util.LinkedList;
import java.util.List;

import net.canarymod.Canary;
import net.canarymod.api.nbt.CanaryBaseTag;
import net.canarymod.api.nbt.CanaryCompoundTag;
import net.canarymod.api.nbt.CanaryDoubleTag;
import net.canarymod.api.nbt.CompoundTag;
import net.canarymod.api.nbt.ListTag;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.DimensionType;
import net.canarymod.api.world.UnknownWorldException;
import net.canarymod.api.world.World;
import net.canarymod.api.world.position.Position;
import net.canarymod.permissionsystem.PermissionProvider;
import net.canarymod.user.Group;
import net.minecraft.server.ISaveHandler;
import net.minecraft.server.SaveHandler;

/**
 * Offline Player implementation
 *
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public class CanaryOfflinePlayer implements OfflinePlayer {

    private CanaryCompoundTag data;
    private PermissionProvider provider;
    private List<Group> groups = null;
    private String prefix = null;
    private String name;
    private boolean isMuted;

    public CanaryOfflinePlayer(String name, CanaryCompoundTag tag) {
        this.data = tag;
        this.name = name;
        provider = Canary.permissionManager().getPlayerProvider(name, getWorld().getFqName());
        String[] data = Canary.usersAndGroups().getPlayerData(name);
        Group[] subs = Canary.usersAndGroups().getModuleGroupsForPlayer(name);
        groups = new LinkedList<Group>();
        groups.add(Canary.usersAndGroups().getGroup(data[1]));
        for (Group g : subs) {
            if (g != null) {
                groups.add(g);
            }
        }
        prefix = data[0];
        isMuted = Boolean.parseBoolean(data[2]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PermissionProvider getPermissionProvider() {
        return provider;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Group getGroup() {
        return groups.get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPrefix() {
        return prefix;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasPermission(String path) {
        return provider.queryPermission(path);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGroup(Group group) {
        this.groups.set(0, group);
        Canary.usersAndGroups().addOrUpdateOfflinePlayer(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
        Canary.usersAndGroups().addOrUpdateOfflinePlayer(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public World getWorld() {
        if (data == null) {
            return Canary.getServer().getDefaultWorld();
        }
        int dim = data.getInt("Dimension");
        String world = data.getString("LevelName");
        try {
            return Canary.getServer().getWorldManager().getWorld(world, DimensionType.fromId(dim), false);
        } catch (UnknownWorldException e) {
            return Canary.getServer().getDefaultWorld();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getPosition() {
        if (data == null) {
            return new Position();
        }
        ListTag<? extends CanaryBaseTag> poslist = data.getListTag("Pos");
        Position p = new Position();
        p.setX(((CanaryDoubleTag) poslist.get(0)).getValue());
        p.setY(((CanaryDoubleTag) poslist.get(1)).getValue());
        p.setZ(((CanaryDoubleTag) poslist.get(2)).getValue());
        return p;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMuted() {
        return isMuted;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMuted(boolean muted) {
        this.isMuted = muted;
        Canary.usersAndGroups().addOrUpdateOfflinePlayer(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addGroup(Group group) {
        if (!groups.contains(group)) {
            groups.add(group);
            Canary.usersAndGroups().addOrUpdateOfflinePlayer(this);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Group[] getPlayerGroups() {
        return groups.toArray(new Group[groups.size()]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeGroup(Group g) {
        if (groups.get(0).equals(g)) {
            return false;
        }
        boolean success = groups.remove(g);
        if (success) {
            Canary.usersAndGroups().addOrUpdateOfflinePlayer(this);
        }
        return success;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeGroup(String g) {
        Group gr = Canary.usersAndGroups().getGroup(g);
        if (gr == null) {
            return false;
        }
        return removeGroup(gr);
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOnline() {
        return Canary.getServer().getPlayer(name) != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompoundTag getNBT() {
        return data;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save() {
        if (isOnline()) {
            Canary.logDebug("Attempted to save an online player! (" + getName() + ")");
            return;
        }
        if (getNBT() != null) {
            ISaveHandler handler = ((CanaryWorld) getWorld()).getHandle().M();
            if (handler instanceof SaveHandler) {
                SaveHandler shandler = (SaveHandler) handler;
                shandler.writePlayerNbt(getName(), (CanaryCompoundTag) getNBT());
            } else {
                Canary.logServerMessage(getName() + "'s OfflinePlayer could not be saved! Unsupported SaveHandler!");
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFirstJoined() {
        if (getNBT() != null && getNBT().containsKey("FirstJoin")) {
            return getNBT().getString("FirstJoin");
        }
        return "NEVER";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getTimePlayed() {
        if (getNBT() != null && getNBT().containsKey("TimePlayed")) {
            return getNBT().getLong("TimePlayed");
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getModeId() {
        if (getNBT() != null && getNBT().containsKey("playerGameType")) {
            return getNBT().getInt("playerGameType");
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameMode getMode() {
        return GameMode.fromId(getModeId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMode(GameMode mode) {
        if (getNBT() != null) {
            getNBT().put("playerGameType", mode.getId());
            save();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setModeId(int mode) {
        if (getNBT() != null) {
            getNBT().put("playerGameType", mode);
            save();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAdmin() {
        return hasPermission("canary.super.administrator") || Canary.ops().isOpped(getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canBuild() {
        return hasPermission("canary.world.build") || isAdmin();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCanBuild(boolean canModify) {
        provider.addPermission("canary.world.build", canModify);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canIgnoreRestrictions() {
        return hasPermission("canary.super.ignoreRestrictions");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCanIgnoreRestrictions(boolean canIgnore) {
        provider.addPermission("canary.super.ignoreRestrictions", canIgnore, -1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addExhaustion(float exhaustion) {
        if (getNBT() != null) {
            float oldEx = getExhaustionLevel();
            getNBT().put("foodExhaustionLevel", oldEx + exhaustion);
            save();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setExhaustion(float exhaustion) {
        if (getNBT() != null) {
            getNBT().put("foodExhaustionLevel", exhaustion);
            save();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getExhaustionLevel() {
        if (getNBT() != null && getNBT().containsKey("foodExhaustionLevel")) {
            return getNBT().getFloat("foodExhaustionLevel");
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHunger(int hunger) {
        if (getNBT() != null) {
            getNBT().put("foodLevel", hunger);
            save();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHunger() {
        if (getNBT() != null && getNBT().containsKey("foodLevel")) {
            return getNBT().getInt("foodLevel");
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addExperience(int experience) {
        if (getNBT() != null) {
            int oldXp = getExperience();
            getNBT().put("XpTotal", oldXp + experience);
            save();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeExperience(int experience) {
        if (getNBT() != null) {
            int oldXp = getExperience();
            getNBT().put("XpTotal", oldXp - experience);
            save();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getExperience() {
        if (getNBT() != null && getNBT().containsKey("XpTotal")) {
            return getNBT().getInt("XpTotal");
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setExperience(int xp) {
        if (getNBT() != null) {
            getNBT().put("XpTotal", xp);
            save();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLevel() {
        if (getNBT() != null && getNBT().containsKey("XpLevel")) {
            return getNBT().getInt("XpLevel");
        }
        return 0;
    }
}
