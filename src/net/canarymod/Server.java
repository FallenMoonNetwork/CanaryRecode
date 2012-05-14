package net.canarymod;

import java.util.logging.Logger;

import net.canarymod.api.IServer;
import net.canarymod.api.entity.IPlayer;
import net.canarymod.api.world.IWorldManager;
import net.minecraft.server.OMinecraftServer;

/**
 * Main entry point of the software
 * 
 * @author Jos Kuijpers
 *
 */
public class Server implements IServer {

	public static final Logger log = Logger.getLogger("Minecraft");
	private OMinecraftServer server;
	
	/**
	 * Create the server
	 * 
	 * @param server
	 */
	public Server(OMinecraftServer server) {
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
    
    public IWorldManager getWorldManager() {
        return null;
    	
    }
    
    public String handleRemoteCommand(String command) { //TODO: Rly?
    	return null;
    }

    public void consoleCommand(String command) {
        // TODO Auto-generated method stub
        
    }

    public void consoleCommand(String command, IPlayer player) {
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
