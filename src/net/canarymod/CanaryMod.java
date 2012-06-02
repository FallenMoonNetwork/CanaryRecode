package net.canarymod;

import net.canarymod.backbone.Backbone;
import net.canarymod.config.Configuration;
import net.canarymod.database.Database;
import net.canarymod.database.DatabaseFlatfile;
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
    	this.database = new DatabaseFlatfile();
    	
        // Initialize the loader and scan for plugins
        this.hookExecutor = new HookExecutor();
        this.loader = new PluginLoader();
        this.loader.scanPlugins();
    }

    @Override
    public Backbone getBackbone(Backbone.System arg0) {
        // TODO Auto-generated method stub
        return null;
    }
}
