package net.canarymod;

import net.canarymod.api.Enchantment;
import net.canarymod.api.inventory.Item;
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
        new DeadLockDetector();
        
        //Initialize the bird
        CanaryMod mod = new CanaryMod();
        Canary.setCanary(mod);
        //Add system internal serializers
        Canary.addSerializer(new ItemSerializer(), Item.class);
        Canary.addSerializer(new EnchantmentSerializer(), Enchantment.class);
        
        //Initialize providers that require Canary to be set already
        mod.initUserAndGroupsManager();
        mod.initWarps();
        mod.initKits();
        try {
            OMinecraftServer.main(args);
        } catch (Throwable t) {
            Logman.logStackTrace("Exception while starting the server: ", t);
        }
    }

}
