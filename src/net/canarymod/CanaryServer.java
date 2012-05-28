package net.canarymod;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.canarymod.api.Server;
import net.canarymod.api.entity.CanaryPlayer;
import net.canarymod.api.entity.Player;
import net.canarymod.api.world.WorldManager;
import net.canarymod.hook.command.ConsoleCommandHook;
import net.minecraft.server.OEntityPlayerMP;
import net.minecraft.server.OMinecraftServer;

/**
 * Main entry point of the software
 * 
 * @author Jos Kuijpers
 * @author Chris
 *
 */
public class CanaryServer implements Server {

	public static final Logger log = Logger.getLogger("Minecraft");
	private OMinecraftServer server;
	
	/**
	 * Create a new Server Wrapper
	 * @param server
	 */
	public CanaryServer(OMinecraftServer server) {
        this.server = server;
    }
	
    public String getHostname() {
    	return "localhost"; //Is that really localhost?
    }
    
    public int getNumPlayersOnline() {
    	return 0; //TODO Get from server
    }
    
    public int getMaxPlayers() {
    	return 20; //TODO retrieve from configuration!
    }
    
    public String[] getPlayerNameList() {
    	ArrayList<Player> players = getPlayerList();
    	String[] names = new String[players.size()];
    	for(int i = 0; i < players.size(); i++) {
    	    names[i] = players.get(i).getName();
    	}
    	return names;
    }
    
    public String getDefaultWorldName() {
    	return "default"; //TODO: Retrieve default world name from config!
    }
    
    public WorldManager getWorldManager() {
        return null;
    	
    }

    @Override
    public void consoleCommand(String command) {
        ConsoleCommandHook hook = (ConsoleCommandHook) Canary.hooks().callCancelableHook(new ConsoleCommandHook(null, command));
        if(hook.isCancelled()) {
            return; 
        }
        server.a(command, server);
    }

    @Override
    public void consoleCommand(String command, Player player) {
        ConsoleCommandHook hook = (ConsoleCommandHook) Canary.hooks().callCancelableHook(new ConsoleCommandHook(player, command));
        if(hook.isCancelled()) {
            return; 
        }
        server.a(command, player);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setTimer(String uniqueName, int time) {
        OMinecraftServer.b.put(uniqueName, time);
    }

    @Override
    public boolean isTimerExpired(String uniqueName) {
        return OMinecraftServer.b.containsKey(uniqueName);
    }

    @Override
    public Player matchPlayer(String name) {
        Player lastPlayer = null;

        name = name.toLowerCase();

        for (OEntityPlayerMP player : (List<OEntityPlayerMP>) server.h.b) {
            CanaryPlayer cPlayer = player.getPlayer();
            if (cPlayer.getName().toLowerCase().equals(name)) {
                // Perfect match found
                lastPlayer = cPlayer;
                break;
            }
            if (cPlayer.getName().toLowerCase().indexOf(name) != -1) {
                // Partial match
                if (lastPlayer != null) {
                    // Found multiple
                    return null;
                }
                lastPlayer = cPlayer;
            }
        }

        return lastPlayer;
    }

    @Override
    public Player getPlayer(String name) {
        OEntityPlayerMP user = server.h.i(name); //So ugly :S

        return user == null ? null : user.getPlayer();
    }

    @Override
    public ArrayList<Player> getPlayerList() {
        ArrayList<Player> toRet = new ArrayList<Player>();

        for (OEntityPlayerMP oepmp : (List<OEntityPlayerMP>) server.h.b) {
            toRet.add(oepmp.getPlayer());
        }
        return toRet;
    }
    
    public OMinecraftServer getHandle(){
        return server;
    }
}
