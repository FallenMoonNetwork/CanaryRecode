package net.canarymod;

import net.canarymod.api.Enchantment;
import net.canarymod.api.inventory.Item;
import net.canarymod.serialize.EnchantmentSerializer;
import net.canarymod.serialize.ItemSerializer;
import net.minecraft.server.MinecraftServer;

public class Main {

    private static void initBird() {
        // Initialize the bird
        CanaryMod mod = new CanaryMod();
        Canary.setCanary(mod);
        // Add system internal serializers
        Canary.addSerializer(new ItemSerializer(), Item.class);
        Canary.addSerializer(new EnchantmentSerializer(), Enchantment.class);

        // Initialize providers that require Canary to be set already
        mod.initUserAndGroupsManager();
        mod.initWarps();
        mod.initKits();
        mod.initCommands();
    }

    /**
     * The canary Bootstrap process
     * 
     * @param args
     */
    public static void main(String[] args) {
        initBird();
        try {
            MinecraftServer.main(args);
        } catch (Throwable t) {
            Canary.logStackTrace("Exception while starting the server: ", t);
        }
    }

    /**
     * Restart the server without killing the JVM
     * 
     * @param reloadCanary
     */
    public static void restart(boolean reloadCanary) {
        throw new UnsupportedOperationException("Restart is not implemented yet!");
    }

}
