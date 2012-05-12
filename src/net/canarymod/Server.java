package net.canarymod;

import net.canarymod.api.IServer;
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
    	
    }
    
    public String handleRemoteCommand(String command) { //TODO: Rly?
    	return null;
    }
}
