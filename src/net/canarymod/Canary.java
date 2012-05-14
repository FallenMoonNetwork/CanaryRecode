package net.canarymod;

import net.canarymod.backbone.IBackbone;
import net.canarymod.backbone.IBackbone.System;
import net.canarymod.config.Configuration;
public class Canary extends ICanary {

    private static Canary instance;
    
    /**
     * Private Canary Constructor to prevent more than once instance
     * TODO: add subsystem inits
     * and the respective backbones
     */
    private Canary() {
        this.configuration = new Configuration();
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
