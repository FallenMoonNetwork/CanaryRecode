package net.minecraft.server;


import java.util.List;
import net.canarymod.api.world.CanaryWorld;


public class CommandTime extends CommandBase {

    public CommandTime() {}

    public String c() {
        return "time";
    }

    public int a() {
        return 2;
    }

    public String a(ICommandSender icommandsender) {
        return icommandsender.a("commands.time.usage", new Object[0]);
    }

    public void b(ICommandSender icommandsender, String[] astring) {
        if (astring.length > 1) {
            int i0;

            if (astring[0].equals("set")) {
                if (astring[1].equals("day")) {
                    i0 = 0;
                } else if (astring[1].equals("night")) {
                    i0 = 12500;
                } else {
                    i0 = a(icommandsender, astring[1], 0);
                }

                this.a(icommandsender, i0);
                a(icommandsender, "commands.time.set", new Object[] { Integer.valueOf(i0)});
                return;
            }

            if (astring[0].equals("add")) {
                i0 = a(icommandsender, astring[1], 0);
                this.b(icommandsender, i0);
                a(icommandsender, "commands.time.added", new Object[] { Integer.valueOf(i0)});
                return;
            }
        }

        throw new WrongUsageException("commands.time.usage", new Object[0]);
    }

    public List a(ICommandSender icommandsender, String[] astring) {
        return astring.length == 1 ? a(astring, new String[] { "set", "add"}) : (astring.length == 2 && astring[0].equals("set") ? a(astring, new String[] { "day", "night"}) : null);
    }

    protected void a(ICommandSender icommandsender, int i0) {
        // CanaryMod: MultiWorld fix
        for (net.canarymod.api.world.World w : MinecraftServer.D().worldManager.getAllWorlds()) {
            WorldServer worldserver = (WorldServer) ((CanaryWorld) w).getHandle();

            if (worldserver != null) {
                worldserver.b((long) i0);
            }
        }
    }

    protected void b(ICommandSender icommandsender, int i0) {
        // CanaryMod: MultiWorld fix
        for (net.canarymod.api.world.World w : MinecraftServer.D().worldManager.getAllWorlds()) {
            WorldServer worldserver = (WorldServer) ((CanaryWorld) w).getHandle();

            if (worldserver != null) {
                worldserver.b(worldserver.H() + (long) i0);
            }
        }
    }
}
