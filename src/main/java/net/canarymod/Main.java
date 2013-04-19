package net.canarymod;


import java.io.File;

import net.canarymod.api.inventory.Enchantment;
import net.canarymod.api.inventory.Item;
import net.canarymod.serialize.EnchantmentSerializer;
import net.canarymod.serialize.ItemSerializer;
import net.minecraft.server.LogAgent;
import net.minecraft.server.MinecraftServer;


public class Main {
    private static LogAgent la;
    private static CanaryMod mod;

    private static void initBird() {
        // Initialize the bird
        mod = new CanaryMod();

        Canary.setCanary(mod);
        // Add system internal serializers
        Canary.addSerializer(new ItemSerializer(), Item.class);
        Canary.addSerializer(new EnchantmentSerializer(), Enchantment.class);

        // Initialize providers that require Canary to be set already
        mod.initUserAndGroupsManager();
        mod.initKits();
    }

    /**
     * The canary Bootstrap process
     *
     * @param args
     */
    public static void main(String[] args) {
        // Initialize Logging Early, TODO: the new File(".") is a directory setting, the cli arg is --universe
        la = new LogAgent("Minecraft-Server", (String) null, (new File(new File("."), "server.log")).getAbsolutePath());
        initBird();
        try {
            MinecraftServer.main(args);
        } catch (Throwable t) {
            Canary.logStackTrace("Exception while starting the server: ", t);
        }
        // commands require a valid commandOwner which is the server.
        // That means for commands to work, we gotta load Minecraft first
        mod.initCommands();
        //Warps need the DimensionType data which is created upon servre start
        mod.initWarps();
    }

    /**
     * Restart the server without killing the JVM
     *
     * @param reloadCanary
     */
    public static void restart(boolean reloadCanary) {
        throw new UnsupportedOperationException("Restart is not implemented yet!");
    }

    /**
     * INTERNAL USE ONLY
     */
    public static LogAgent getLogAgent() {
        return la;
    }
}
