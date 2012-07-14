package net.canarymod;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;

import net.canarymod.api.CanaryServer;
import net.canarymod.serialize.EnchantmentSerializer;
import net.canarymod.serialize.ItemSerializer;
import net.minecraft.server.OMinecraftServer;  
import net.minecraft.server.ONetworkAcceptThread;

public class Main {

    private static String[] args; //Save the arguments for if we want to go and reload
    
    private static void initBird() {
      //Initialize the bird
        CanaryMod mod = new CanaryMod();
        Canary.setCanary(mod);
        //Add system internal serializers
        Canary.addSerializer(new ItemSerializer(), "CanaryItem");
        Canary.addSerializer(new EnchantmentSerializer(), "CanaryEnchantment");
        
        //Initialize providers that require Canary to be set already
        mod.initUserAndGroupsManager();
        mod.initWarps();
        mod.initKits();
        mod.initCommands();
    }
    
    /**
     * The canary Bootstrap process
     * @param args
     */
    public static void main(String[] args) {
        Main.args = args;
        initBird();
        try {
            OMinecraftServer.main(args);
        } catch (Throwable t) {
            Logman.logStackTrace("Exception while starting the server: ", t);
        }
    }
    
    /**
     * Restart the server without killing the JVM
     * @param reloadCanary
     */
    public static void restart(boolean reloadCanary) {
       throw new UnsupportedOperationException("Restart is not implemented yet!");
    }

}
