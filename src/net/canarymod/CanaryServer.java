package net.canarymod;

import java.util.logging.Logger;

import net.canarymod.api.Server;
import net.canarymod.api.entity.Player;
import net.canarymod.api.world.WorldManager;
import net.minecraft.server.OMinecraftServer;

/**
 * Main entry point of the software
 * 
 * @author Jos Kuijpers
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
    	return "localhost";
    }
    
    public int getNumPlayersOnline() {
    	return 0;
    }
    
    public int getMaxPlayers() {
    	return 20;
    }
    
    public String[] getPlayerNameList() {
    	return null;
    }
    
    public String getDefaultWorldName() {
    	return "default";
    }
    
    public WorldManager getWorldManager() {
        return null;
    	
    }
    
    public String handleRemoteCommand(String command) { //TODO: Rly?
    	return null;
    }

    public void consoleCommand(String command) {
        // TODO Auto-generated method stub
        
    }

    public void consoleCommand(String command, Player player) {
        // TODO Auto-generated method stub
        
    }

    public void setTimer(String uniqueName, int time) {
        // TODO Auto-generated method stub
        
    }

    public boolean isTimerExpired(String uniqueName) {
        // TODO Auto-generated method stub
        return false;
    }
}
