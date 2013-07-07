package net.canarymod;

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.util.logging.Level;
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
        Canary.addSerializer(new ItemSerializer(), Item.class);
        Canary.addSerializer(new EnchantmentSerializer(), Enchantment.class);
    }

    /**
     * The canary Bootstrap process
     * 
     * @param args
     */
    public static void main(String[] args) {
        try {
            MinecraftServer.setHeadless(GraphicsEnvironment.isHeadless());
            for (int i1 = 0; i1 < args.length; ++i1) {
                String s3 = args[i1];
                if (!s3.equals("nogui") && !s3.equals("--nogui")) {
                    ;
                } else {
                    MinecraftServer.setHeadless(true);
                }
            }

            // Initialize Logging Early, TODO: the new File(".") is a directory setting, the cli arg is --universe
            la = new LogAgent("Minecraft-Server", (String) null, (new File(new File("."), "server.log")).getAbsolutePath());
            la.a().setLevel(Level.ALL);
            if (!MinecraftServer.isHeadless()) {
                TextAreaLogHandler.getLogHandler().poke();
            }
            initBird();

            MinecraftServer.main(args);

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
