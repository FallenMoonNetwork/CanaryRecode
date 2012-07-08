package net.canarymod;

import net.canarymod.serialize.EnchantmentSerializer;
import net.canarymod.serialize.ItemSerializer;
import net.minecraft.server.OMinecraftServer;

public class Main {

    /**
     * The canary Bootstrap process
     * @param args
     */
    public static void main(String[] args) {
        //Initialize the deadlock detector

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
        try {
            OMinecraftServer.main(args);
        } catch (Throwable t) {
            Logman.logStackTrace("Exception while starting the server: ", t);
        }
    }

}
