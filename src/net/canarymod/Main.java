package net.canarymod;

import java.util.logging.Logger;

import net.minecraft.server.OMinecraftServer;

public class Main {

    public static final Logger log = Logger.getLogger("Minecraft");
    /**
     * @param args
     */
    public static void main(String[] args) {
        //Initialize the bird
        CanaryMod mod = new CanaryMod();
        Canary.setCanary(mod);
        mod.initUserAndGroupsManager();
        
        
        try {
            OMinecraftServer.main(args);
        } catch (Throwable t) {
            Logman.logStackTrace("Exception while starting the server: ", t);
        }
        new DeadLockDetector();
    }

}
