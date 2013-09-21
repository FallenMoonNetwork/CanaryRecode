package net.minecraft.server;

import net.canarymod.api.world.CanaryWorld;

public class CommandServerSaveAll extends CommandBase {

    public CommandServerSaveAll() {
    }

    public String c() {
        return "save-all";
    }

    public int a() {
        return 4;
    }

    public String c(ICommandSender icommandsender) {
        return "commands.save.usage";
    }

    public void b(ICommandSender icommandsender, String[] astring) {
        MinecraftServer minecraftserver = MinecraftServer.F();

        icommandsender.a(ChatMessageComponent.e("commands.save.start"));
        if (minecraftserver.af() != null) {
            minecraftserver.af().g();
        }

        try {
            int i0;
            // CanaryMod: Fix for MultiWorld
            for (net.canarymod.api.world.World w : minecraftserver.worldManager.getAllWorlds()) {
                WorldServer worldserver = (WorldServer)((CanaryWorld)w).getHandle();

                if (worldserver != null) {
                    boolean flag0 = worldserver.c;

                    worldserver.c = false;
                    worldserver.a(true, (IProgressUpdate)null);
                    worldserver.c = flag0;
                }
            }

            if (astring.length > 0 && "flush".equals(astring[0])) {
                icommandsender.a(ChatMessageComponent.e("commands.save.flushStart"));

                for (net.canarymod.api.world.World w : minecraftserver.worldManager.getAllWorlds()) {
                    WorldServer worldserver = (WorldServer)((CanaryWorld)w).getHandle();

                    if (worldserver != null) {
                        boolean flag0 = worldserver.c;
                        worldserver.c = false;
                        worldserver.m();
                        worldserver.c = flag0;
                    }
                }

                icommandsender.a(ChatMessageComponent.e("commands.save.flushEnd"));
            }
        }
        catch (MinecraftException minecraftexception) {
            a(icommandsender, "commands.save.failed", new Object[]{minecraftexception.getMessage()});
            return;
        }

        a(icommandsender, "commands.save.success", new Object[0]);
    }
}
