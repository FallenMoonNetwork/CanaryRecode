package net.canarymod;

import net.canarymod.backbone.Backbone;
import net.canarymod.bansystem.BanManager;
import net.canarymod.config.Configuration;
import net.canarymod.database.Database.Type;
import net.canarymod.database.DatabaseFlatfile;
import net.canarymod.database.DatabaseMySql;
import net.canarymod.group.GroupsProvider;
import net.canarymod.help.HelpManager;
import net.canarymod.hook.HookExecutor;
import net.canarymod.plugin.PluginLoader;

/**
 * The implementation of ICanary, a singleton used for everything.
 * 
 * @author Chris Ksoll
 * @author Jos Kuijpers
 */
public class CanaryMod extends Canary {

    /**
     * Creates a new CanaryMod
     */
    public CanaryMod() {
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

        // Initialize the loader and scan for plugins
        this.hookExecutor = new HookExecutor();
        this.helpManager = new HelpManager();
        this.banManager = new BanManager(database);
        this.groupsProvider = new GroupsProvider(database);
        
        this.loader = new PluginLoader();
        this.loader.scanPlugins();
    }

    @Override
    public Backbone getBackbone(Backbone.System system) {
        // TODO Auto-generated method stub
        return null;
    }
}
