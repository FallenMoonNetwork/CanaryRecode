package net.minecraft.server;

import java.util.List;
import net.canarymod.hook.player.TeleportHook;

public class CommandServerTp extends CommandBase {

    public CommandServerTp() {}

    public String c() {
        return "tp";
    }

    public int a() {
        return 2;
    }

    public String c(ICommandSender icommandsender) {
        return "commands.tp.usage";
    }

    public void b(ICommandSender icommandsender, String[] astring) {
        if (astring.length < 1) {
            throw new WrongUsageException("commands.tp.usage", new Object[0]);
        } else {
            EntityPlayerMP entityplayermp;

            if (astring.length != 2 && astring.length != 4) {
                entityplayermp = b(icommandsender);
            } else {
                entityplayermp = d(icommandsender, astring[0]);
                if (entityplayermp == null) {
                    throw new PlayerNotFoundException();
                }
            }

            if (astring.length != 3 && astring.length != 4) {
                if (astring.length == 1 || astring.length == 2) {
                    EntityPlayerMP entityplayermp1 = d(icommandsender, astring[astring.length - 1]);

                    if (entityplayermp1 == null) {
                        throw new PlayerNotFoundException();
                    }

                    if (entityplayermp1.q != entityplayermp.q) {
                        a(icommandsender, "commands.tp.notSameDimension", new Object[0]);
                        return;
                    }

                    entityplayermp.a((Entity) null);
                    // CanaryMod: DERP (MultiWorld fix)
                    entityplayermp.a.a(entityplayermp1.u, entityplayermp1.v, entityplayermp1.w, entityplayermp1.A, entityplayermp1.B, entityplayermp1.getCanaryWorld().getType().getId(), entityplayermp1.getCanaryWorld().getName(), TeleportHook.TeleportCause.COMMAND);
                    a(icommandsender, "commands.tp.success", new Object[]{ entityplayermp.al(), entityplayermp1.al() });
                }
            } else if (entityplayermp.q != null) {
                int i0 = astring.length - 3;
                double d0 = a(icommandsender, entityplayermp.u, astring[i0++]);
                double d1 = a(icommandsender, entityplayermp.v, astring[i0++], 0, 0);
                double d2 = a(icommandsender, entityplayermp.w, astring[i0++]);

                entityplayermp.a((Entity) null);
                entityplayermp.a(d0, d1, d2);
                a(icommandsender, "commands.tp.success.coordinates", new Object[]{ entityplayermp.al(), Double.valueOf(d0), Double.valueOf(d1), Double.valueOf(d2) });
            }
        }
    }

    public List a(ICommandSender icommandsender, String[] astring) {
        return astring.length != 1 && astring.length != 2 ? null : a(astring, MinecraftServer.F().C());
    }

    public boolean a(String[] astring, int i0) {
        return i0 == 0;
    }
}
