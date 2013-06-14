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

    public String a(ICommandSender icommandsender) {
        return icommandsender.a("commands.tp.usage", new Object[0]);
    }

    public void b(ICommandSender icommandsender, String[] astring) {
        if (astring.length < 1) {
            throw new WrongUsageException("commands.tp.usage", new Object[0]);
        } else {
            EntityPlayerMP entityplayermp;

            if (astring.length != 2 && astring.length != 4) {
                entityplayermp = c(icommandsender);
            } else {
                entityplayermp = c(icommandsender, astring[0]);
                if (entityplayermp == null) {
                    throw new PlayerNotFoundException();
                }
            }

            if (astring.length != 3 && astring.length != 4) {
                if (astring.length == 1 || astring.length == 2) {
                    EntityPlayerMP entityplayermp1 = c(icommandsender, astring[astring.length - 1]);

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
                    a(icommandsender, "commands.tp.success", new Object[] { entityplayermp.am(), entityplayermp1.am()});
                }
            } else if (entityplayermp.q != null) {
                int i0 = astring.length - 3;
                double d0 = this.a(icommandsender, entityplayermp.u, astring[i0++]);
                double d1 = this.a(icommandsender, entityplayermp.v, astring[i0++], 0, 0);
                double d2 = this.a(icommandsender, entityplayermp.w, astring[i0++]);

                entityplayermp.a((Entity) null);
                entityplayermp.a(d0, d1, d2);
                a(icommandsender, "commands.tp.success.coordinates", new Object[] { entityplayermp.am(), Double.valueOf(d0), Double.valueOf(d1), Double.valueOf(d2)});
            }
        }
    }

    private double a(ICommandSender icommandsender, double d0, String s0) {
        return this.a(icommandsender, d0, s0, -30000000, 30000000);
    }

    private double a(ICommandSender icommandsender, double d0, String s0, int i0, int i1) {
        boolean flag0 = s0.startsWith("~");
        double d1 = flag0 ? d0 : 0.0D;

        if (!flag0 || s0.length() > 1) {
            boolean flag1 = s0.contains(".");

            if (flag0) {
                s0 = s0.substring(1);
            }

            d1 += b(icommandsender, s0);
            if (!flag1 && !flag0) {
                d1 += 0.5D;
            }
        }

        if (i0 != 0 || i1 != 0) {
            if (d1 < (double) i0) {
                throw new NumberInvalidException("commands.generic.double.tooSmall", new Object[] { Double.valueOf(d1), Integer.valueOf(i0)});
            }

            if (d1 > (double) i1) {
                throw new NumberInvalidException("commands.generic.double.tooBig", new Object[] { Double.valueOf(d1), Integer.valueOf(i1)});
            }
        }

        return d1;
    }

    public List a(ICommandSender icommandsender, String[] astring) {
        return astring.length != 1 && astring.length != 2 ? null : a(astring, MinecraftServer.D().A());
    }

    public boolean a(String[] astring, int i0) {
        return i0 == 0;
    }
}
