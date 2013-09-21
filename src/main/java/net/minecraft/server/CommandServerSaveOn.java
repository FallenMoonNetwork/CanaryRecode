package net.minecraft.server;

import net.canarymod.api.world.CanaryWorld;

public class CommandServerSaveOn extends CommandBase {

    public CommandServerSaveOn() {
    }

    public String c() {
        return "save-on";
    }

    public int a() {
        return 4;
    }

    public String c(ICommandSender icommandsender) {
        return "commands.save-on.usage";
    }

    public void b(ICommandSender icommandsender, String[] astring) {
        MinecraftServer minecraftserver = MinecraftServer.F();
        boolean flag0 = false;

        // CanaryMod: Fix for MultiWorld
        for (net.canarymod.api.world.World w : minecraftserver.worldManager.getAllWorlds()) {
            WorldServer worldserver = (WorldServer)((CanaryWorld)w).getHandle();

            if (worldserver != null) {
                worldserver.c = false;
                flag0 = true;
            }
        }

        if (flag0) {
            a(icommandsender, "commands.save.enabled", new Object[0]);
        }
        else {
            throw new CommandException("commands.save-on.alreadyOn",
                    new Object[0]);
        }
    }
}
