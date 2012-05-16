package net.canarymod;

import java.util.logging.Logger;

import net.canarymod.backbone.IBackbone;
import net.canarymod.backbone.IBackbone.System;
import net.canarymod.config.Configuration;
import net.canarymod.plugin.PluginLoader;
public class Canary extends ICanary {

    private static Canary instance;
    private PluginLoader loader;
    private static final Logger log = Logger.getLogger("Minecraft");
    
    /**
     * Private Canary Constructor to prevent more than once instance
     * TODO: add subsystem inits
     * and the respective backbones
     */
    private Canary() {
        this.configuration = new Configuration();
        this.loader = new PluginLoader();
        this.loader.loadPlugins();
    }
    /**
     * Get a Canary which contains access to all relevant subsystems
     * @return
     */
    public static Canary get() {
        if(instance == null) {
            instance = new Canary();
        }
        return instance;
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
