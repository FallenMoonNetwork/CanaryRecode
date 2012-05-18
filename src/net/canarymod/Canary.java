package net.canarymod;

//import java.util.logging.Logger;

import net.canarymod.backbone.IBackbone;
import net.canarymod.backbone.IBackbone.System;
import net.canarymod.config.Configuration;
import net.canarymod.plugin.PluginLoader;

/**
 * The implementation of ICanary, a singleton used for everything.
 * 
 * @author Chris Ksoll
 * @author Jos Kuijpers
 */
public class Canary extends ICanary {

//    private static final Logger log = Logger.getLogger("Minecraft");
    
    /**
     * Private Canary Constructor to prevent more than once instance.<br>
     * <b>NOTE: </b>This can only be instanciated ONCE. It is a form of singleton.
     * In-Code other than where it is originally initialized, use ICanary.get()
     * to get your Canary instance!
     * TODO: add subsystem inits
     * and the respective backbones
     */
    public Canary() {
        //We already have an instance if this is not null
        if(instance != null) {
            return;
        }
        this.configuration = new Configuration();
        
        // Initialize the loader and scan for plugins
        this.loader = new PluginLoader();
        this.loader.scanPlugins();
        
        // Determine the back-end and create a database instance for it.
    }
    
    public void setServer(Server server) {
        this.server = server;
    }

    @Override
    public IBackbone getBackbone(System system) {
        // TODO Auto-generated method stub
        return null;
    }
}
