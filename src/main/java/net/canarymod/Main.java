package net.canarymod;

import net.minecraft.server.OMinecraftServer;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //Initialize the bird
        CanaryMod mod = new CanaryMod();
        Canary.setCanary(mod);
        
        //Initialize providers that require Canary to be set already
        mod.initUserAndGroupsManager();
        mod.initWarps();
        
        
        try {
            OMinecraftServer.main(args);
        } catch (Throwable t) {
            Logman.logStackTrace("Exception while starting the server: ", t);
        }
        new DeadLockDetector();
    }

}
