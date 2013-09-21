package net.minecraft.server;

import net.canarymod.api.world.CanaryWorld;

public class CommandServerSaveOff extends CommandBase {

    public CommandServerSaveOff() {
    }

    public String c() {
        return "save-off";
    }

    public int a() {
        return 4;
    }

    public String c(ICommandSender icommandsender) {
        return "commands.save-off.usage";
    }

    public void b(ICommandSender icommandsender, String[] astring) {
        MinecraftServer minecraftserver = MinecraftServer.F();
        boolean flag0 = false;

        // CanaryMod: Fix for MultiWorld
        for (net.canarymod.api.world.World w : minecraftserver.worldManager.getAllWorlds()) {
            WorldServer worldserver = (WorldServer)((CanaryWorld)w).getHandle();

            if (worldserver != null) {
                worldserver.c = true;
                flag0 = true;
            }
        }

        if (flag0) {
            a(icommandsender, "commands.save.disabled", new Object[0]);
        }
        else {
            throw new CommandException("commands.save-off.alreadyOff",
                    new Object[0]);
        }
    }
}
