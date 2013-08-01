package net.minecraft.server;

import java.util.List;
import java.util.Random;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.DimensionType;

public class CommandWeather extends CommandBase {

    public CommandWeather() {}

    public String c() {
        return "weather";
    }

    public int a() {
        return 2;
    }

    public String c(ICommandSender icommandsender) {
        return "commands.weather.usage";
    }

    public void b(ICommandSender icommandsender, String[] astring) {
        if (astring.length >= 1 && astring.length <= 2) {
            int i0 = (300 + (new Random()).nextInt(600)) * 20;

            if (astring.length >= 2) {
                i0 = a(icommandsender, astring[1], 1, 1000000) * 20;
            }

            // CanaryMod: MutliWorld fix
            for (net.canarymod.api.world.World w : MinecraftServer.F().worldManager.getAllWorlds()) {
                WorldServer worldserver = (WorldServer) ((CanaryWorld) w).getHandle();
                if (worldserver != null && worldserver.getCanaryWorld().getType() == DimensionType.fromId(0)) {
                    WorldInfo worldinfo = worldserver.N();

                    worldinfo.g(i0);
                    worldinfo.f(i0);
                    if ("clear".equalsIgnoreCase(astring[0])) {
                        worldinfo.b(false);
                        worldinfo.a(false);
                        a(icommandsender, "commands.weather.clear", new Object[0]);
                    } else if ("rain".equalsIgnoreCase(astring[0])) {
                        worldinfo.b(true);
                        worldinfo.a(false);
                        a(icommandsender, "commands.weather.rain", new Object[0]);
                    } else {
                        if (!"thunder".equalsIgnoreCase(astring[0])) {
                            throw new WrongUsageException("commands.weather.usage", new Object[0]);
                        }

                        worldinfo.b(true);
                        worldinfo.a(true);
                        a(icommandsender, "commands.weather.thunder", new Object[0]);
                    }
                }
            }
        } else {
            throw new WrongUsageException("commands.weather.usage",
                    new Object[0]);
        }
    }

    public List a(ICommandSender icommandsender, String[] astring) {
        return astring.length == 1 ? a(astring, new String[]{ "clear", "rain", "thunder" }) : null;
    }
}
