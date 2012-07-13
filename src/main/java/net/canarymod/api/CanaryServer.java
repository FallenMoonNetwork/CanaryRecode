package net.canarymod.api;

import java.util.ArrayList;

import net.canarymod.Canary;
import net.canarymod.Logman;
import net.canarymod.api.ConfigurationManager;
import net.canarymod.api.Server;
import net.canarymod.api.entity.CanaryPlayer;
import net.canarymod.api.entity.Player;
import net.canarymod.api.world.World;
import net.canarymod.api.world.WorldManager;
import net.canarymod.config.Configuration;
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

    private OMinecraftServer server;

    /**
     * Create a new Server Wrapper
     * 
     * @param server
     */
    public CanaryServer(OMinecraftServer server) {
        this.server = server;
    }

    @Override
    public String getHostname() {
        return "localhost"; // TODO put real hostname here
    }

    @Override
    public int getNumPlayersOnline() {
        return server.getCanaryConfigurationManager().getNumPlayersOnline();
    }

    @Override
    public int getMaxPlayers() {
        return Configuration.getNetConfig().getMaxPlayers();
    }

    @Override
    public String[] getPlayerNameList() {
        ArrayList<Player> players = getPlayerList();
        String[] names = new String[players.size()];
        for (int i = 0; i < players.size(); i++) {
            names[i] = players.get(i).getName();
        }
        return names;
    }

    @Override
    public String getDefaultWorldName() {
        return Configuration.getServerConfig().getDefaultWorldName();
    }

    @Override
    public WorldManager getWorldManager() {
        return server.getWorldManager();
    }

    @Override
    public boolean consoleCommand(String command) {
        ConsoleCommandHook hook = new ConsoleCommandHook(this, command);
        Canary.hooks().callHook(hook);
        if (hook.isCanceled()) {
            return true;
        }
        return Canary.commands().parseCommand(this, command.split(" ")[0], command.split(" "));
    }

    @Override
    public boolean consoleCommand(String command, Player player) {
        ConsoleCommandHook hook = new ConsoleCommandHook(player, command);
        Canary.hooks().callHook(hook);
        if (hook.isCanceled()) {
            return true;
        }
        return Canary.commands().parseCommand(player, command.split(" ")[0], command.split(" "));
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

    @Override
    public ConfigurationManager getConfigurationManager() {
        return server.h.getCanaryConfigurationManager();
    }

    @Override
    public String getName() {
        return "Console";
    }

    @Override
    public void notify(String message) {
        Logman.logInfo(message);
    }

    @Override
    public boolean hasPermission(String node) {
        return true;
    }
}
