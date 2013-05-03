package net.minecraft.server;


import java.util.Iterator;
import net.canarymod.Canary;
import net.canarymod.api.world.CanaryWorld;


public class ServerCommandManager extends CommandHandler implements IAdminCommand {

    public ServerCommandManager() {
        // TODO: Should we disable commands we implemented differently?
        this.a(new CommandTime());
        this.a(new CommandGameMode());
        this.a(new CommandDifficulty());
        this.a(new CommandDefaultGameMode());
        this.a(new CommandKill());
        this.a(new CommandToggleDownfall());
        this.a(new CommandWeather());
        this.a(new CommandXP());
        this.a(new CommandServerTp());
        this.a(new CommandGive());
        this.a(new CommandEffect());
        this.a(new CommandEnchant());
        this.a(new CommandServerEmote());
        this.a(new CommandShowSeed());
        this.a(new CommandHelp());
        this.a(new CommandDebug());
        this.a(new CommandServerMessage());
        this.a(new CommandServerSay());
        this.a(new CommandSetSpawnpoint());
        this.a(new CommandGameRule());
        this.a(new CommandClearInventory());
        this.a(new ServerCommandTestFor());
        this.a(new ServerCommandScoreboard());
        if (MinecraftServer.D().T()) {
            this.a(new CommandServerOp());
            this.a(new CommandServerDeop());
            this.a(new CommandServerStop());
            this.a(new CommandServerSaveAll());
            this.a(new CommandServerSaveOff());
            this.a(new CommandServerSaveOn());
            this.a(new CommandServerBanIp());
            this.a(new CommandServerPardonIp());
            this.a(new CommandServerBan());
            this.a(new CommandServerBanlist());
            this.a(new CommandServerPardon());
            this.a(new CommandServerKick());
            this.a(new CommandServerList());
            this.a(new CommandServerWhitelist());
        } else {
            this.a(new CommandServerPublishLocal());
        }

        CommandBase.a((IAdminCommand) this);
    }

    public void a(ICommandSender icommandsender, int i0, String s0, Object... aobject) {
        boolean flag0 = true;

        // CanaryMod: Fix for MultiWorld
        if (icommandsender instanceof TileEntityCommandBlock && !((CanaryWorld) Canary.getServer().getDefaultWorld()).getHandle().N().b("commandBlockOutput")) {
            flag0 = false;
        }

        if (flag0) {
            Iterator iterator = MinecraftServer.D().ad().a.iterator();

            while (iterator.hasNext()) {
                EntityPlayerMP entityplayermp = (EntityPlayerMP) iterator.next();

                if (entityplayermp != icommandsender && MinecraftServer.D().ad().e(entityplayermp.bS)) {
                    entityplayermp.a("" + EnumChatFormatting.h + "" + EnumChatFormatting.u + "[" + icommandsender.c_() + ": " + entityplayermp.a(s0, aobject) + "]");
                }
            }
        }

        if (icommandsender != MinecraftServer.D()) {
            MinecraftServer.D().al().a("[" + icommandsender.c_() + ": " + MinecraftServer.D().a(s0, aobject) + "]");
        }

        if ((i0 & 1) != 1) {
            icommandsender.a(icommandsender.a(s0, aobject));
        }
    }
}
