package net.canarymod;


import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.server.OMinecraftServer;

public class Main {

    public static final Logger log = Logger.getLogger("Minecraft");
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            OMinecraftServer.main(args);
        } catch (Throwable t) {
            log.log(Level.SEVERE, null, t);
        }
        new DeadLockDetector();
    }

}
