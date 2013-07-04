package net.canarymod.api;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.canarymod.Canary;
import net.canarymod.Main;
import net.canarymod.api.entity.living.humanoid.CanaryPlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.gui.GUIControl;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.recipes.CanaryRecipe;
import net.canarymod.api.inventory.recipes.CraftingRecipe;
import net.canarymod.api.inventory.recipes.Recipe;
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
import net.canarymod.tasks.ServerTask;
import net.canarymod.tasks.ServerTaskManager;
import net.minecraft.server.CraftingManager;
import net.minecraft.server.FurnaceRecipes;
import net.minecraft.server.IRecipe;
import net.minecraft.server.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerConfigurationManager;
import net.minecraft.server.ShapedRecipes;
import net.minecraft.server.ShapelessRecipes;
import net.minecraft.server.TcpConnection;
import net.visualillusionsent.utils.TaskManager;

/**
 * Main entry point of the software
 * 
 * @author Jos Kuijpers
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public class CanaryServer implements Server {

    protected HashMap<String, ServerTimer> timers = new HashMap<String, ServerTimer>();
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
            return server.G().a(getHandle(), command) > 0; // Vanilla Commands passed
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
        if (!Canary.commands().parseCommand(player, split[0], split)) {
            return server.G().a(((CanaryPlayer) player).getHandle(), command) > 0; // Vanilla Commands passed
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
            return server.G().a(((CanaryCommandBlock) cmdBlock).getTileEntity(), command) > 0; // Vanilla Commands passed
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
        ServerTimer newTimer = new ServerTimer(uniqueName);
        TaskManager.scheduleDelayedTaskInSeconds(newTimer, time);
        timers.put(uniqueName, newTimer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTimerExpired(String uniqueName) {
        return !timers.containsKey(uniqueName);
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
            player.message(message);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Recipe addRecipe(CraftingRecipe recipe) {
        if (recipe.hasShape()) {
            return CraftingManager.a().a(((CanaryItem) recipe.getResult()).getHandle(), ShapedRecipeHelper.createRecipeShape(recipe)).getCanaryRecipe();
        } else {
            ItemStack result = ((CanaryItem) recipe.getResult()).getHandle();
            Object[] rec = new Object[recipe.getItems().length];

            for (int index = 0; index < recipe.getItems().length; index++) {
                rec[index] = ((CanaryItem) recipe.getItems()[index]).getHandle();
            }
            return CraftingManager.a().addShapeless(result, rec).getCanaryRecipe();
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Recipe> getServerRecipes() {
        List<IRecipe> server_recipes = CraftingManager.a().b();
        List<Recipe> rtn_recipes = new ArrayList<Recipe>();
        for (IRecipe recipe : server_recipes) {
            if (recipe instanceof ShapedRecipes) {
                rtn_recipes.add(((ShapedRecipes) recipe).getCanaryRecipe());
            } else if (recipe instanceof ShapelessRecipes) {
                rtn_recipes.add(((ShapelessRecipes) recipe).getCanaryRecipe());
            }
            // if it's neither, something went wrong or its something I haven't included yet
        }
        return rtn_recipes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeRecipe(Recipe recipe) {
        return CraftingManager.a().b().remove(((CanaryRecipe) recipe).getHandle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSmeltingRecipe(SmeltRecipe recipe) {
        FurnaceRecipes.a().a(recipe.getItemIDFrom(), ((CanaryItem) recipe.getResult()).getHandle(), recipe.getXP());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SmeltRecipe> getServerSmeltRecipes() {
        return FurnaceRecipes.a().getSmeltingRecipes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeSmeltRecipe(SmeltRecipe recipe) {
        return FurnaceRecipes.a().removeSmeltingRecipe(recipe);
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
        return server.f;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long[] getSentPacketSizeArray() {
        return server.g;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long[] getReceivedPacketCountArray() {
        return server.h;
    }

    @Override
    public long[] getReceivedPacketSizeArray() {
        return server.i;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long[] getTickTimeArray() {
        return server.j;
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
        if (canaryVersion == null) {
            Package p = getClass().getPackage();
            if (p == null) {
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
        return server.z();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getServerGUILog() {
        if (!isHeadless()) {
            // return TextAreaLogHandler.getLog(); ///XXX
            return null;
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

    @Override
    public boolean addSynchronousTask(ServerTask task) {
        return ServerTaskManager.addTask(task);
    }

    @Override
    public boolean removeSynchronousTask(ServerTask task) {
        return ServerTaskManager.removeTask(task);
    }

    @Override
    public void sendPlayerListEntry(PlayerListEntry entry) {
        if (Configuration.getServerConfig().isPlayerListEnabled()) {
            server.af().a(new net.minecraft.server.Packet201PlayerInfo(entry.getName(), entry.isShown(), entry.getPing()));
        }
    }

    public class ServerTimer implements Runnable {
        private String name;

        public ServerTimer(String name) {
            this.name = name;
        }

        @Override
        public synchronized void run() {
            timers.remove(name);
        }
    }
}
