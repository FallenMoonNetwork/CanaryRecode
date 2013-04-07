package net.minecraft.server;


import net.canarymod.api.world.CanaryWorld;


public class CommandServerSaveOff extends CommandBase {

    public CommandServerSaveOff() {}

    public String c() {
        return "save-off";
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
                worldserver.c = true;
            }
        }

        a(icommandsender, "commands.save.disabled", new Object[0]);
    }
}
