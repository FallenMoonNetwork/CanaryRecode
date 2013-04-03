package net.minecraft.server;

import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.WorldType;

public class CommandToggleDownfall extends CommandBase {

    public CommandToggleDownfall() {}

    public String c() {
        return "toggledownfall";
    }

    public int a() {
        return 2;
    }

    public void b(ICommandSender icommandsender, String[] astring) {
        this.d();
        a(icommandsender, "commands.downfall.success", new Object[0]);
    }

    protected void d() {
        for (net.canarymod.api.world.World w : MinecraftServer.D().worldManager.getAllWorlds()) {
            WorldServer worldserver = (WorldServer) ((CanaryWorld) w).getHandle();
            if (worldserver != null && worldserver.getCanaryWorld().getType() == WorldType.fromId(0)) {
                worldserver.z();
                worldserver.L().a(true);
            }
        }
    }
}
