package net.canarymod;

import net.canarymod.bansystem.BanManager;
import net.canarymod.commandsys.CommandList;
import net.canarymod.commandsys.CommandManager;
import net.canarymod.config.Configuration;
import net.canarymod.database.Database.Type;
import net.canarymod.database.flatfile.DatabaseFlatfile;
import net.canarymod.database.mysql.DatabaseMySql;
import net.canarymod.help.HelpManager;
import net.canarymod.hook.HookExecutor;
import net.canarymod.kit.KitProvider;
import net.canarymod.permissionsystem.PermissionManager;
import net.canarymod.plugin.PluginLoader;
import net.canarymod.user.UserAndGroupsProvider;
import net.canarymod.warp.WarpProvider;
import net.canarymod.api.CanaryConfigurationManager;
import net.canarymod.api.CanaryServer;

/**
 * The implementation of Canary, the new catch-all etc replacement, only much better :P
 * 
 * @author Chris Ksoll
 * @author Jos Kuijpers
 */
public class CanaryMod extends Canary {

    /**
     * Creates a new CanaryMod
     */
    public CanaryMod() {
        Canary.instance = this;
        
        this.config = new Configuration();
        Type backend = Configuration.getServerConfig().getDatasourceType();
        if (backend == Type.FLATFILE) {
            this.database = new DatabaseFlatfile();
        } 
        else if (backend == Type.MYSQL) {
            this.database = new DatabaseMySql();
        } 
        else {
            //Uh oh ...
            Logman.logWarning("The specified datasource is invalid! Using Flatfile as default.");
            this.database = new DatabaseFlatfile();
        }

        // Initialize the subsystems that do not rely on others
        this.commandManager = new CommandManager();
        this.permissionLoader = new PermissionManager();
        this.hookExecutor = new HookExecutor();
        this.helpManager = new HelpManager();
        this.banManager = new BanManager();
        //Initialize the plugin loader and scan for plugins
        this.loader = new PluginLoader();
        this.loader.scanPlugins();
    }
    
    /**
     * Separately set users and groups provider
     */
    public void initUserAndGroupsManager() {
        this.userAndGroupsProvider = new UserAndGroupsProvider();
    }
    
    /**
     * Separately set the warps provider
     */
    public void initWarps() {
        this.warpProvider = new WarpProvider();
    }
    
    public void initKits() {
        this.kitProvider = new KitProvider();
    }
    
    public void initCommands() {
        this.commandManager.registerAll(CommandList.class);
    }
    
    @Override
    public void reload() {
        super.reload();
        
        // Reload minecraft variables
        ((CanaryConfigurationManager)instance.server.getConfigurationManager()).getHandle().reload();
        // TODO RCON + QUERY?
        ((CanaryServer)instance.server).getHandle().reload();
    }
}
