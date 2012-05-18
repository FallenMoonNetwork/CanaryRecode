package net.canarymod.api.entity;

import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.Dimension;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Vector3D;
import net.canarymod.group.Group;
import net.canarymod.permissionsystem.PermissionProvider;
import net.minecraft.server.OEntityLiving;

/**
 * Canary Player wrapper.
 * @author Chris
 *
 */
public class CanaryPlayer extends CanaryEntityLiving implements Player {

    public CanaryPlayer(OEntityLiving entity) {
        super(entity);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void chat(String message) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void sendMessage(String message) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addExhaustion(float exhaustion) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeExhaustion(float exhaustion) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public float getExhaustionLevel() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setHunger(int hunger) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getHunger() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void addExperience(int experience) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeExperience(int experience) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getExperience() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isSleeping() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setSleeping(boolean sleeping) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void destroyItemHeld() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Item getItemHeld() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void dropItem(Item item) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Vector3D getSpawnPosition() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setSpawnPosition(Vector3D spawn) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getIP() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean executeCommand(String command) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean canFly() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isFlying() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setFlying(boolean flying) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void sendPacket(Object packet) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Group getGroup() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Group[] getPlayerGroups() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addToGroup(String group) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addToGroup(Group group) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeFromGroup(String group) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeFromGroup(Group group) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean hasPermission(String permission) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isAdmin() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean canModifyWorld() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setCanModifyWorld(boolean canModify) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean canIgnoreRestrictions() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setCanIgnoreRestrictions(boolean canIgnore) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isMuted() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setMuted(boolean muted) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public PermissionProvider getPermissionProvider() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Location getLocation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Inventory getInventory() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void giveItem(Item item) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isInGroup(Group group, boolean includeChilds) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void teleportTo(double x, double y, double z) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void teleportTo(double x, double y, double z, Dimension dim) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void teleportTo(double x, double y, double z, float pitch,
            float rotation, Dimension dim) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void teleportTo(double x, double y, double z, float pitch,
            float rotation) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void teleportTo(Location location) {
        // TODO Auto-generated method stub
        
    }

}
