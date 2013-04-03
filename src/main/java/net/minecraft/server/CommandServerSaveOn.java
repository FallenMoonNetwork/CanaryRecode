package net.minecraft.server;

import net.canarymod.api.world.CanaryWorld;

public class CommandServerSaveOn extends CommandBase {

    public CommandServerSaveOn() {}

    public String c() {
        return "save-on";
    }

    public int a() {
        return 4;
    }

    public void b(ICommandSender icommandsender, String[] astring) {
        MinecraftServer minecraftserver = MinecraftServer.D();

        // CanaryMod: Fix for MultiWorld
        for (net.canarymod.api.world.World w : minecraftserver.worldManager.getAllWorlds()) {
            WorldServer worldserver = (WorldServer) ((CanaryWorld) w).getHandle();
            if (worldserver != null) {
                worldserver.c = false;
            }
        }

        a(icommandsender, "commands.save.enabled", new Object[0]);
    }
}
