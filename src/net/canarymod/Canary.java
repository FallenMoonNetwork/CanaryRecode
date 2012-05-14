package net.canarymod;

import net.canarymod.backbone.IBackbone;
import net.canarymod.backbone.IBackbone.System;
public class Canary extends ICanary {

    private static Canary instance;
    
    /**
     * Private Canary Constructor to prevent more than once instance
     * TODO: This needs Configuration information in order to load 
     * the respective backbones
     */
    private Canary() {
        //this.banManager = new BanManager(bone, type) <- see here. Needs the backbone
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
