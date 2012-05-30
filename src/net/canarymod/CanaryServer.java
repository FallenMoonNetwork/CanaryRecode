package net.canarymod;

import java.util.ArrayList;
import java.util.logging.Logger;

import net.canarymod.api.Server;
import net.canarymod.api.entity.CanaryPlayer;
import net.canarymod.api.entity.Player;
import net.canarymod.api.world.World;
import net.canarymod.api.world.WorldManager;
import net.canarymod.hook.command.ConsoleCommandHook;
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
     * 
     * @param server
     */
    public CanaryServer(OMinecraftServer server) {
        this.server = server;
    }

    public String getHostname() {
        return "localhost"; // Is that really localhost?
    }

    public int getNumPlayersOnline() {
        return server.getCanaryConfigurationManager().getNumPlayersOnline();
    }

    public int getMaxPlayers() {
        return server.getCanaryConfigurationManager().getMaxPlayers();
    }

    public String[] getPlayerNameList() {
        ArrayList<Player> players = getPlayerList();
        String[] names = new String[players.size()];
        for (int i = 0; i < players.size(); i++) {
            names[i] = players.get(i).getName();
        }
        return names;
    }

    public String getDefaultWorldName() {
        return "world"; // TODO: Retrieve default world name from config! - it's world by default in mc cfg
    }

    public WorldManager getWorldManager() {
        return server.getWorldManager();
    }

    @Override
    public void consoleCommand(String command) {
        ConsoleCommandHook hook = (ConsoleCommandHook) Canary.hooks()
                .callCancelableHook(new ConsoleCommandHook(null, command));
        if (hook.isCancelled()) {
            return;
        }
        server.a(command, server);
    }

    @Override
    public void consoleCommand(String command, Player player) {
        ConsoleCommandHook hook = (ConsoleCommandHook) Canary.hooks()
                .callCancelableHook(new ConsoleCommandHook(player, command));
        if (hook.isCancelled()) {
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

        for (Player player : server.getCanaryConfigurationManager()
                .getAllPlayers()) {
            CanaryPlayer cPlayer = (CanaryPlayer) player;
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
        return server.getCanaryConfigurationManager().getPlayerByName(name);
    }

    @Override
    public ArrayList<Player> getPlayerList() {
        return server.getCanaryConfigurationManager().getAllPlayers();
    }

    public OMinecraftServer getHandle() {
        return server;
    }

    @Override
    public void broadcastMessage(String message) {
        for (Player player : getPlayerList()) {
            player.sendMessage(message);
        }

    }

    @Override
    public boolean loadWorld(String name, long seed) {
        server.loadWorld(name, seed);
        if(server.getWorldManager().worldIsLoaded(name)) {
            return true;
        }
        return false;
    }

    @Override
    public World getWorld(String name) {
        return server.getWorldManager().getWorld(name);
    }

    @Override
    public World getDefaultWorld() {
        return getWorldManager().getWorld(getDefaultWorldName());
    }
}
