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
import net.canarymod.api.world.World;
import net.canarymod.api.world.position.Position;
import net.canarymod.permissionsystem.PermissionProvider;
import net.canarymod.user.Group;
import net.minecraft.server.ISaveHandler;
import net.minecraft.server.SaveHandler;

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
        provider = Canary.permissionManager().getPlayerProvider(name);
        String[] data = Canary.usersAndGroups().getPlayerData(name);
        Group[] subs = Canary.usersAndGroups().getModuleGroupsForPlayer(name);
        groups = new LinkedList<Group>();
        groups.add(Canary.usersAndGroups().getGroup(data[1]));
        for(Group g : subs) {
            if(g != null) {
                groups.add(g);
            }
        }
        prefix = data[0];
        isMuted = Boolean.parseBoolean(data[2]);
    }
    @Override
    public PermissionProvider getPermissionProvider() {
        return provider;
    }

    @Override
    public Group getGroup() {
        return groups.get(0);
    }

    @Override
    public String getPrefix() {
        return prefix;
    }

    @Override
    public boolean hasPermission(String path) {
        return provider.queryPermission(path);
    }

    @Override
    public void setGroup(Group group) {
        this.groups.set(0, group);
        Canary.usersAndGroups().addOrUpdateOfflinePlayer(this);
    }

    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
        Canary.usersAndGroups().addOrUpdateOfflinePlayer(this);
    }

    @Override
    public World getWorld() {
        if(data == null) {
            return Canary.getServer().getDefaultWorld();
        }
        int dim = data.getInt("Dimension");
        String world = data.getString("LevelName");
        return Canary.getServer().getWorldManager().getWorld(world, DimensionType.fromId(dim), false);
    }

    @Override
    public Position getPosition() {
        if(data == null) {
            return new Position();
        }
        ListTag<? extends CanaryBaseTag> poslist = data.getListTag("Pos");
        Position p = new Position();
        p.setX(((CanaryDoubleTag)poslist.get(0)).getValue());
        p.setY(((CanaryDoubleTag)poslist.get(1)).getValue());
        p.setZ(((CanaryDoubleTag)poslist.get(2)).getValue());
        return p;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public boolean isMuted() {
        return isMuted;
    }
    @Override
    public void setMuted(boolean muted) {
        this.isMuted = muted;
        Canary.usersAndGroups().addOrUpdateOfflinePlayer(this);
    }
    @Override
    public void addGroup(Group group) {
        if(!groups.contains(group)) {
            groups.add(group);
            Canary.usersAndGroups().addOrUpdateOfflinePlayer(this);
        }
    }
    @Override
    public Group[] getPlayerGroups() {
        return groups.toArray(new Group[0]);
    }
    @Override
    public boolean removeGroup(Group g) {
        if(groups.get(0).equals(g)) {
            return false;
        }
        boolean success = groups.remove(g);
        if(success) {
            Canary.usersAndGroups().addOrUpdateOfflinePlayer(this);
        }
        return success;
    }
    @Override
    public boolean removeGroup(String g) {
        Group gr = Canary.usersAndGroups().getGroup(g);
        if(gr == null) {
            return false;
        }
        return removeGroup(gr);
    }
    @Override
    public boolean isOnline() {
        return Canary.getServer().getPlayer(name) != null;
    }
    @Override
    public CompoundTag getNBT() {
        return data;
    }
    @Override
    public void save() {
        if(isOnline()) {
            Canary.logDebug("Attempted to save an online player! ("+getName()+")");
            return;
        }
        ISaveHandler handler = ((CanaryWorld)getWorld()).getHandle().L();
        if(handler instanceof SaveHandler) {
            SaveHandler shandler = (SaveHandler)handler;
            shandler.writePlayerNbt(getName(), (CanaryCompoundTag)getNBT());
        }
        else {
            Canary.logServerMessage(getName() + "'s OfflinePlayer could not be saved! Unsupported SaveHandler!");
        }

    }

}
