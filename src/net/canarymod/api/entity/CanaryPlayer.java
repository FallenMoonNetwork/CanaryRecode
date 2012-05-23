package net.canarymod.api.entity;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.canarymod.Canary;
import net.canarymod.Logman;
import net.canarymod.TextFormat;
import net.canarymod.api.NetServerHandler;
import net.canarymod.api.Packet;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.Dimension;
import net.canarymod.api.world.position.Location;
import net.canarymod.group.Group;
import net.canarymod.hook.command.PlayerCommandHook;
import net.canarymod.hook.player.ChatHook;
import net.canarymod.permissionsystem.PermissionProvider;
import net.minecraft.server.OEntityPlayerMP;

/**
 * Canary Player wrapper.
 * @author Chris
 *
 */
public class CanaryPlayer extends CanaryEntityLiving implements Player {
    private Pattern badChatPattern = Pattern.compile("[\u00a7\u2302\u00D7\u00AA\u00BA\u00AE\u00AC\u00BD\u00BC\u00A1\u00AB\u00BB]");
    public CanaryPlayer(OEntityPlayerMP entity) {
        super(entity);
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
                String prefix = "<" + getColor() + getName() + TextFormat.White + "> ";
                StringBuilder msg = new StringBuilder(message);
                ArrayList<Player> receivers = (ArrayList<Player>) Canary.get().getServer().getPlayerList();
                ChatHook hook = (ChatHook) Canary.get().getHookExecutor().callCancelableHook(new ChatHook(this, prefix, msg, receivers));
                if(hook.isCancelled()) {
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
    public void sendMessage(String message) {
        getNetServerHandler().sendMessage(message);
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
    public Location getSpawnPosition() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setSpawnPosition(Location spawn) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getIP() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean executeCommand(String[] command) {
        try {
            String[] split = command;
            String cmd = split[0];
            PlayerCommandHook hook = (PlayerCommandHook) Canary.get().getHookExecutor().callCancelableHook(new PlayerCommandHook(this, split));
            if (hook.isCancelled()) {
                return true;
            } // No need to go on, commands were parsed.
            
            if (!hasPermission("canary.commands."+cmd.replace("/", "")) && !cmd.startsWith("/#")) {
                sendMessage(TextFormat.Rose + "Unknown command.");
                return false;
            }
            if (cmd.startsWith("/#") && hasPermission("canary.commands.vanilla."+cmd.replace("/#", ""))) {

                Logman.logInfo(getName() + " issued server command: " + cmd);
                Canary.get().getServer().handleRemoteCommand(Canary.glueString(command, 1, " "));
                return true;
            }

            //TODO: Add native Canary Commands here!
            return true;
            
        } catch (Throwable ex) {
            Logman.logStackTrace("Exception in command handler:", ex);
            if (isAdmin()) {
                sendMessage(TextFormat.Rose + "Exception occured. Check the server for more info.");
            }
            return false;
        }
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
    public void sendPacket(Packet packet) {
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

    @Override
    public void kick(String reason) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void notify(String message) {
        sendMessage(TextFormat.Rose+message);
        
    }

    @Override
    public String getColor() {
        return TextFormat.LightGreen;
    }

    @Override
    public NetServerHandler getNetServerHandler() {
        return ((OEntityPlayerMP)entity).getServerHandler();
    }

}
