package net.canarymod;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.canarymod.api.Server;
import net.canarymod.api.entity.CanaryPlayer;
import net.canarymod.api.entity.Player;
import net.canarymod.api.world.WorldManager;
import net.canarymod.hook.command.ConsoleCommandHook;
import net.canarymod.hook.command.PlayerCommandHook;
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
    @Override
    public void handleRemoteCommand(String command) {
        ConsoleCommandHook hook = (ConsoleCommandHook) Canary.get().getHookExecutor().callCancelableHook(new ConsoleCommandHook(command));
        if(hook.isCancelled()) {
            return; //Command has been parsed, no need to go on
        }
        server.d(command);
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
}
