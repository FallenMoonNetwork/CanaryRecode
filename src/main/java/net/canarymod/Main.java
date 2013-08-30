package net.canarymod;

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.util.logging.Level;
import net.canarymod.api.inventory.CanaryEnchantment;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Enchantment;
import net.canarymod.api.inventory.Item;
import net.canarymod.serialize.EnchantmentSerializer;
import net.canarymod.serialize.ItemSerializer;
import net.minecraft.server.LogAgent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.TextAreaLogHandler;

public class Main {
    private static LogAgent la;
    private static CanaryMod mod;

    private static void initBird() {
        // Initialize the bird
        mod = new CanaryMod();
        Canary.setCanary(mod);
        // Add system internal serializers
        Canary.addSerializer(new ItemSerializer(), CanaryItem.class);
        Canary.addSerializer(new ItemSerializer(), Item.class);
        Canary.addSerializer(new EnchantmentSerializer(), CanaryEnchantment.class);
        Canary.addSerializer(new EnchantmentSerializer(), Enchantment.class);
    }

    /**
     * The canary Bootstrap process
     * 
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Starting: " + Canary.getImplementationTitle() + " " + Canary.getImplementationVersion() + " Specified By: " + Canary.getSpecificationTitle() + " " + Canary.getSpecificationVersion());
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {} // Need to initialize the SQLite driver for some reason, initialize here for plugin use as well
        try {
            MinecraftServer.setHeadless(GraphicsEnvironment.isHeadless());
            for (int index = 0; index < args.length; ++index) {
                String key = args[index];
                String value = index == args.length - 1 ? null : args[index + 1];
                if (key.equals("nogui") || key.equals("--nogui")) {
                    MinecraftServer.setHeadless(true);
                } else if (key.equals("--universe") && value != null) {
                    // Initialize Logging to universe argument
                    la = new LogAgent("Minecraft-Server", (String) null, (new File(new File(value), "server.log")).getAbsolutePath());
                }
            }

            if (la == null) { // If universe wasn't set we need to initialize to the working directory
                la = new LogAgent("Minecraft-Server", (String) null, (new File(new File("."), "server.log")).getAbsolutePath());
            }
            la.a().setLevel(Level.ALL);

            if (!MinecraftServer.isHeadless()) {
                TextAreaLogHandler.getLogHandler().poke();
            }

            initBird(); // Initialize the Bird
            MinecraftServer.main(args); // Boot up the native server

            // They need the server to be set
            mod.initPermissions();
            // Initialize providers that require Canary to be set already
            mod.initUserAndGroupsManager();
            mod.initKits();
            // Warps need the DimensionType data which is created upon servre start
            mod.initWarps();
            // commands require a valid commandOwner which is the server.
            // That means for commands to work, we gotta load Minecraft first
            mod.initCommands();
        } catch (Throwable t) {
            Canary.logStacktrace("Exception while starting the server: ", t);
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

    /**
     * INTERNAL USE ONLY
     */
    public static LogAgent getLogAgent() {
        return la;
    }
}
