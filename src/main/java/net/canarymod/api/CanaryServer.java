package net.canarymod.api;


import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarFile;

import net.canarymod.Canary;
import net.canarymod.Main;
import net.canarymod.api.entity.living.humanoid.CanaryPlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.gui.GUIControl;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.recipes.CraftingRecipe;
import net.canarymod.api.inventory.recipes.ShapedRecipeHelper;
import net.canarymod.api.inventory.recipes.SmeltRecipe;
import net.canarymod.api.nbt.CanaryCompoundTag;
import net.canarymod.api.world.World;
import net.canarymod.api.world.WorldManager;
import net.canarymod.api.world.blocks.CanaryCommandBlock;
import net.canarymod.api.world.blocks.CommandBlock;
import net.canarymod.chat.TextFormat;
import net.canarymod.config.Configuration;
import net.canarymod.hook.command.ConsoleCommandHook;
import net.canarymod.hook.system.PermissionCheckHook;
import net.minecraft.server.CraftingManager;
import net.minecraft.server.FurnaceRecipes;
import net.minecraft.server.GuiLogOutputHandler;
import net.minecraft.server.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerConfigurationManager;
import net.minecraft.server.TcpConnection;


/**
 * Main entry point of the software
 *
 * @author Jos Kuijpers
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public class CanaryServer implements Server {

    protected HashMap<String, ServerTimer> timers = new HashMap<String, ServerTimer>();
    protected ScheduledExecutorService taskExecutor = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
    private MinecraftServer server;
    private GUIControl currentGUI = null;
    String canaryVersion = null;
    String mcVersion = null;

    /**
     * Create a new Server Wrapper
     *
     * @param server
     */
    public CanaryServer(MinecraftServer server) {
        this.server = server;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {}
        return "local.host";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumPlayersOnline() {
        return server.getConfigurationManager().getNumPlayersOnline();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxPlayers() {
        return Configuration.getServerConfig().getMaxPlayers();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getPlayerNameList() {
        ArrayList<Player> players = getPlayerList();
        String[] names = new String[players.size()];

        for (int i = 0; i < players.size(); i++) {
            names[i] = players.get(i).getName();
        }
        return names;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDefaultWorldName() {
        return Configuration.getServerConfig().getDefaultWorldName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WorldManager getWorldManager() {
        return server.getWorldManager();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean consoleCommand(String command) {
        ConsoleCommandHook hook = new ConsoleCommandHook(this, command);

        Canary.hooks().callHook(hook);
        if (hook.isCanceled()) {
            return true;
        }
        String[] split = command.split(" ");
        if (!Canary.commands().parseCommand(this, split[0], split)) {
            return server.E().a(getHandle(), command) > 0; // Vanilla Commands passed
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean consoleCommand(String command, Player player) {
        ConsoleCommandHook hook = new ConsoleCommandHook(player, command);

        Canary.hooks().callHook(hook);
        if (hook.isCanceled()) {
            return true;
        }
        String[] split = command.split(" ");
        if(!Canary.commands().parseCommand(player, split[0], split)) {
            return server.E().a(((CanaryPlayer) player).getHandle(), command) > 0; // Vanilla Commands passed
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean consoleCommand(String command, CommandBlock cmdBlock) {
        ConsoleCommandHook hook = new ConsoleCommandHook(cmdBlock, command);

        Canary.hooks().callHook(hook);
        if (hook.isCanceled()) {
            return true;
        }
        if (!Canary.commands().parseCommand(cmdBlock, command.split(" ")[0], command.split(" "))) {
            return server.E().a(((CanaryCommandBlock) cmdBlock).getTileEntity(), command) > 0; // Vanilla Commands passed
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTimer(String uniqueName, int time) {
        if (timers.containsKey(uniqueName)) {
            Canary.logWarning("Unique key timer " + uniqueName + " is already running, skipping.");
            return;
        }
        ServerTimer newTimer = new ServerTimer(time, uniqueName);

        synchronized (taskExecutor) {
            taskExecutor.schedule(newTimer, 1, TimeUnit.SECONDS);
            timers.put(uniqueName, newTimer);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTimerExpired(String uniqueName) {
        return timers.containsKey(uniqueName);
    }

    @Override
    public Player matchPlayer(String name) {
        Player lastPlayer = null;

        name = name.toLowerCase();

        for (Player cPlayer : server.getConfigurationManager().getAllPlayers()) {
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
    public OfflinePlayer getOfflinePlayer(String player) {
        CanaryCompoundTag comp = new CanaryCompoundTag(ServerConfigurationManager.getPlayerDatByName(player));
        return new CanaryOfflinePlayer(player, comp);
    }

    @Override
    public Player getPlayer(String name) {
        return server.getConfigurationManager().getPlayerByName(name);
    }

    @Override
    public ArrayList<Player> getPlayerList() {
        return server.getConfigurationManager().getAllPlayers();
    }

    public MinecraftServer getHandle() {
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
        if (server.getWorldManager().worldIsLoaded(name)) {
            return true;
        }
        return false;
    }

    @Override
    public World getWorld(String name) {
        return server.getWorldManager().getWorld(name, false);
    }

    @Override
    public World getDefaultWorld() {
        return getWorldManager().getWorld(getDefaultWorldName(), false);
    }

    @Override
    public ConfigurationManager getConfigurationManager() {
        return server.getConfigurationManager();
    }

    @Override
    public String getName() {
        return "Console";
    }

    @Override
    public boolean hasPermission(String node) {
        PermissionCheckHook hook = new PermissionCheckHook(node, this, true);
        Canary.hooks().callHook(hook);
        return hook.getResult();
    }

    @Override
    public boolean safeHasPermission(String node) {
        return true;
    }

    @Override
    public void initiateShutdown() {
        server.initShutdown();
    }

    @Override
    public void restart(boolean reloadCanary) {
        Main.restart(reloadCanary);
    }

    @Override
    public boolean isRunning() {
        return server.isRunning();
    }

    /**
     * Null the server reference
     */
    public void nullServer() {
        server = null;
    }

    @Override
    public void message(String message) {
        Canary.logServerMessage(TextFormat.removeFormatting(message));
    }

    @Override
    public void notice(String message) {
        Canary.logNotice(message);
    }

    public class ServerTimer implements Runnable {
        private int time;
        private String name;
        public ServerTimer(int time, String name) {
            this.time = time;
            this.name = name;
        }

        @Override
        public synchronized void run() {
            time--;
            if (time > 0) {
                taskExecutor.schedule(this, 1, TimeUnit.SECONDS);
            } else {
                timers.remove(name);
            }
        }
    }

    @Override
    public void addRecipe(CraftingRecipe recipe) {
        if (recipe.hasShape()) {
            CraftingManager.a().a(((CanaryItem) recipe.getResult()).getHandle(), ShapedRecipeHelper.createRecipeShape(recipe));
        } else {
            ItemStack result = ((CanaryItem) recipe.getResult()).getHandle();
            Object[] rec = new Object[recipe.getItems().length];

            for (int index = 0; index < recipe.getItems().length; index++) {
                rec[index] = ((CanaryItem) recipe.getItems()[index]).getHandle();
            }
            CraftingManager.a().b(result, rec);
        }
    }

    @Override
    public void addSmeltingRecipe(SmeltRecipe recipe) {
        FurnaceRecipes.a().a(recipe.getItemIDFrom(), ((CanaryItem) recipe.getResult()).getHandle(), recipe.getXP());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addGUI(GUIControl gui) {
        if (currentGUI != null) {
            currentGUI.closeWindow();
        }
        if (!isHeadless()) {
            currentGUI = gui;
            currentGUI.start();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long[] getSentPacketCountArray() {
        return server.e;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long[] getSentPacketSizeArray() {
        return server.f;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long[] getReceivedPacketCountArray() {
        return server.g;
    }

    @Override
    public long[] getReceivedPacketSizeArray() {
        return server.h;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long[] getTickTimeArray() {
        return server.i;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTcpReaderThreadCount() {
        return TcpConnection.a.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTcpWriterThreadCount() {
        return TcpConnection.b.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCanaryModVersion() {
        if(canaryVersion == null) {
            Package p = getClass().getPackage();
            if(p == null) {
                return "info missing!";
            }
            canaryVersion = p.getImplementationVersion();
        }
        return canaryVersion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getServerVersion() {
        if(mcVersion == null) {
            try {
                CodeSource codeSource = this.getClass().getProtectionDomain().getCodeSource();
                codeSource.getLocation().toURI().getPath();
                JarFile jar = new JarFile(codeSource.getLocation().toURI().getPath());
                mcVersion = jar.getManifest().getMainAttributes().getValue("Server-Version");
            } catch (IOException e) {
                Canary.logStackTrace(e.getMessage(), e);
            } catch (URISyntaxException e) {
                Canary.logStackTrace(e.getMessage(), e);
            } catch(NullPointerException e) {
                Canary.logStackTrace(e.getMessage(), e);
            }
            finally {
                if(mcVersion == null) {
                    mcVersion = "info missing!";
                }
            }
        }

        return mcVersion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getServerGUILog() {
        if (!isHeadless()) {
            return GuiLogOutputHandler.getLog();
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GUIControl getCurrentGUI() {
        return this.currentGUI;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isHeadless() {
        return MinecraftServer.isHeadless();
    }

    public void setCurrentGUI(GUIControl guicontrol) {
        this.currentGUI = guicontrol;
    }

}
