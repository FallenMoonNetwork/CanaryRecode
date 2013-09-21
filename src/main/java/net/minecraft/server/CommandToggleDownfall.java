package net.minecraft.server;

import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.DimensionType;

public class CommandToggleDownfall extends CommandBase {

    public CommandToggleDownfall() {
    }

    public String c() {
        return "toggledownfall";
    }

    public int a() {
        return 2;
    }

    public String c(ICommandSender icommandsender) {
        return "commands.downfall.usage";
    }

    public void b(ICommandSender icommandsender, String[] astring) {
        this.d();
        a(icommandsender, "commands.downfall.success", new Object[0]);
    }

    protected void d() {
        for (net.canarymod.api.world.World w : MinecraftServer.F().worldManager.getAllWorlds()) {
            WorldServer worldserver = (WorldServer)((CanaryWorld)w).getHandle();

            if (worldserver != null && worldserver.getCanaryWorld().getType() == DimensionType.fromId(0)) {
                worldserver.B();
                worldserver.N().a(true);
            }
        }
    }
}
