package net.minecraft.server;


import net.canarymod.api.world.CanaryWorld;


public class CommandServerSaveAll extends CommandBase {

    public CommandServerSaveAll() {}

    public String c() {
        return "save-all";
    }

    public int a() {
        return 4;
    }

    public void b(ICommandSender icommandsender, String[] astring) {
        MinecraftServer minecraftserver = MinecraftServer.D();

        icommandsender.a(icommandsender.a("commands.save.start", new Object[0]));
        if (minecraftserver.ad() != null) {
            minecraftserver.ad().g();
        }

        try {
            // CanaryMod: Fix for MultiWorld
            for (net.canarymod.api.world.World w : minecraftserver.worldManager.getAllWorlds()) {
                WorldServer worldserver = (WorldServer) ((CanaryWorld) w).getHandle();

                if (worldserver != null) {
                    boolean flag0 = worldserver.c;

                    worldserver.c = false;
                    worldserver.a(true, (IProgressUpdate) null);
                    worldserver.c = flag0;
                }
            }
        } catch (MinecraftException minecraftexception) {
            a(icommandsender, "commands.save.failed", new Object[] { minecraftexception.getMessage()});
            return;
        }

        a(icommandsender, "commands.save.success", new Object[0]);
    }
}
