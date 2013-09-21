package net.minecraft.server;

import net.canarymod.Canary;
import net.canarymod.api.world.CanaryWorld;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ServerCommandScoreboard extends CommandBase {

    public ServerCommandScoreboard() {
    }

    public String c() {
        return "scoreboard";
    }

    public int a() {
        return 2;
    }

    public String c(ICommandSender icommandsender) {
        return "commands.scoreboard.usage";
    }

    public void b(ICommandSender icommandsender, String[] astring) {
        if (astring.length >= 1) {
            if (astring[0].equalsIgnoreCase("objectives")) {
                if (astring.length == 1) {
                    throw new WrongUsageException("commands.scoreboard.objectives.usage", new Object[0]);
                }

                if (astring[1].equalsIgnoreCase("list")) {
                    this.d(icommandsender);
                }
                else if (astring[1].equalsIgnoreCase("add")) {
                    if (astring.length < 4) {
                        throw new WrongUsageException("commands.scoreboard.objectives.add.usage", new Object[0]);
                    }

                    this.b(icommandsender, astring, 2);
                }
                else if (astring[1].equalsIgnoreCase("remove")) {
                    if (astring.length != 3) {
                        throw new WrongUsageException("commands.scoreboard.objectives.remove.usage", new Object[0]);
                    }

                    this.f(icommandsender, astring[2]);
                }
                else {
                    if (!astring[1].equalsIgnoreCase("setdisplay")) {
                        throw new WrongUsageException("commands.scoreboard.objectives.usage", new Object[0]);
                    }

                    if (astring.length != 3 && astring.length != 4) {
                        throw new WrongUsageException("commands.scoreboard.objectives.setdisplay.usage", new Object[0]);
                    }

                    this.j(icommandsender, astring, 2);
                }

                return;
            }

            if (astring[0].equalsIgnoreCase("players")) {
                if (astring.length == 1) {
                    throw new WrongUsageException("commands.scoreboard.players.usage", new Object[0]);
                }

                if (astring[1].equalsIgnoreCase("list")) {
                    if (astring.length > 3) {
                        throw new WrongUsageException("commands.scoreboard.players.list.usage", new Object[0]);
                    }

                    this.k(icommandsender, astring, 2);
                }
                else if (astring[1].equalsIgnoreCase("add")) {
                    if (astring.length != 5) {
                        throw new WrongUsageException("commands.scoreboard.players.add.usage", new Object[0]);
                    }

                    this.l(icommandsender, astring, 2);
                }
                else if (astring[1].equalsIgnoreCase("remove")) {
                    if (astring.length != 5) {
                        throw new WrongUsageException("commands.scoreboard.players.remove.usage", new Object[0]);
                    }

                    this.l(icommandsender, astring, 2);
                }
                else if (astring[1].equalsIgnoreCase("set")) {
                    if (astring.length != 5) {
                        throw new WrongUsageException("commands.scoreboard.players.set.usage", new Object[0]);
                    }

                    this.l(icommandsender, astring, 2);
                }
                else {
                    if (!astring[1].equalsIgnoreCase("reset")) {
                        throw new WrongUsageException("commands.scoreboard.players.usage", new Object[0]);
                    }

                    if (astring.length != 3) {
                        throw new WrongUsageException("commands.scoreboard.players.reset.usage", new Object[0]);
                    }

                    this.m(icommandsender, astring, 2);
                }

                return;
            }

            if (astring[0].equalsIgnoreCase("teams")) {
                if (astring.length == 1) {
                    throw new WrongUsageException("commands.scoreboard.teams.usage", new Object[0]);
                }

                if (astring[1].equalsIgnoreCase("list")) {
                    if (astring.length > 3) {
                        throw new WrongUsageException("commands.scoreboard.teams.list.usage", new Object[0]);
                    }

                    this.f(icommandsender, astring, 2);
                }
                else if (astring[1].equalsIgnoreCase("add")) {
                    if (astring.length < 3) {
                        throw new WrongUsageException("commands.scoreboard.teams.add.usage", new Object[0]);
                    }

                    this.c(icommandsender, astring, 2);
                }
                else if (astring[1].equalsIgnoreCase("remove")) {
                    if (astring.length != 3) {
                        throw new WrongUsageException("commands.scoreboard.teams.remove.usage", new Object[0]);
                    }

                    this.e(icommandsender, astring, 2);
                }
                else if (astring[1].equalsIgnoreCase("empty")) {
                    if (astring.length != 3) {
                        throw new WrongUsageException("commands.scoreboard.teams.empty.usage", new Object[0]);
                    }

                    this.i(icommandsender, astring, 2);
                }
                else if (astring[1].equalsIgnoreCase("join")) {
                    if (astring.length < 4 && (astring.length != 3 || !(icommandsender instanceof EntityPlayer))) {
                        throw new WrongUsageException("commands.scoreboard.teams.join.usage", new Object[0]);
                    }

                    this.g(icommandsender, astring, 2);
                }
                else if (astring[1].equalsIgnoreCase("leave")) {
                    if (astring.length < 3 && !(icommandsender instanceof EntityPlayer)) {
                        throw new WrongUsageException("commands.scoreboard.teams.leave.usage", new Object[0]);
                    }

                    this.h(icommandsender, astring, 2);
                }
                else {
                    if (!astring[1].equalsIgnoreCase("option")) {
                        throw new WrongUsageException("commands.scoreboard.teams.usage", new Object[0]);
                    }

                    if (astring.length != 4 && astring.length != 5) {
                        throw new WrongUsageException("commands.scoreboard.teams.option.usage", new Object[0]);
                    }

                    this.d(icommandsender, astring, 2);
                }

                return;
            }
        }

        throw new WrongUsageException("commands.scoreboard.usage", new Object[0]);
    }

    protected Scoreboard d() {
        // CanaryMod: This here returns the default world to get the default scoreboard.
        return ((CanaryWorld)Canary.getServer().getDefaultWorld()).getHandle().X();
    }

    protected Scoreboard getScoreboard(World world) {
        // CanaryMod: This here returns the default world to get the default scoreboard.
        return ((CanaryWorld)Canary.getServer().getDefaultWorld()).getHandle().X();
    }

    protected ScoreObjective a(String s0, boolean flag0) {
        Scoreboard scoreboard = this.d();
        ScoreObjective scoreobjective = scoreboard.b(s0);

        if (scoreobjective == null) {
            throw new CommandException("commands.scoreboard.objectiveNotFound", new Object[]{s0});
        }
        else if (flag0 && scoreobjective.c().b()) {
            throw new CommandException("commands.scoreboard.objectiveReadOnly", new Object[]{s0});
        }
        else {
            return scoreobjective;
        }
    }

    protected ScorePlayerTeam a(String s0) {
        Scoreboard scoreboard = this.d();
        ScorePlayerTeam scoreplayerteam = scoreboard.e(s0);

        if (scoreplayerteam == null) {
            throw new CommandException("commands.scoreboard.teamNotFound", new Object[]{s0});
        }
        else {
            return scoreplayerteam;
        }
    }

    protected void b(ICommandSender icommandsender, String[] astring, int i0) {
        String s0 = astring[i0++];
        String s1 = astring[i0++];
        Scoreboard scoreboard = this.d();
        ScoreObjectiveCriteria scoreobjectivecriteria = (ScoreObjectiveCriteria)ScoreObjectiveCriteria.a.get(s1);

        if (scoreobjectivecriteria == null) {
            String[] astring1 = (String[])ScoreObjectiveCriteria.a.keySet().toArray(new String[0]);

            throw new WrongUsageException("commands.scoreboard.objectives.add.wrongType", new Object[]{a(astring1)});
        }
        else if (scoreboard.b(s0) != null) {
            throw new CommandException("commands.scoreboard.objectives.add.alreadyExists", new Object[]{s0});
        }
        else if (s0.length() > 16) {
            throw new SyntaxErrorException("commands.scoreboard.objectives.add.tooLong", new Object[]{s0, Integer.valueOf(16)});
        }
        else if (s0.length() == 0) {
            throw new WrongUsageException("commands.scoreboard.objectives.add.usage", new Object[0]);
        }
        else {
            if (astring.length > i0) {
                String s2 = a(icommandsender, astring, i0);

                if (s2.length() > 32) {
                    throw new SyntaxErrorException("commands.scoreboard.objectives.add.displayTooLong", new Object[]{s2, Integer.valueOf(32)});
                }

                if (s2.length() > 0) {
                    scoreboard.a(s0, scoreobjectivecriteria).a(s2);
                }
                else {
                    scoreboard.a(s0, scoreobjectivecriteria);
                }
            }
            else {
                scoreboard.a(s0, scoreobjectivecriteria);
            }

            a(icommandsender, "commands.scoreboard.objectives.add.success", new Object[]{s0});
        }
    }

    protected void c(ICommandSender icommandsender, String[] astring, int i0) {
        String s0 = astring[i0++];
        Scoreboard scoreboard = this.d();

        if (scoreboard.e(s0) != null) {
            throw new CommandException("commands.scoreboard.teams.add.alreadyExists", new Object[]{s0});
        }
        else if (s0.length() > 16) {
            throw new SyntaxErrorException("commands.scoreboard.teams.add.tooLong", new Object[]{s0, Integer.valueOf(16)});
        }
        else if (s0.length() == 0) {
            throw new WrongUsageException("commands.scoreboard.teams.add.usage", new Object[0]);
        }
        else {
            if (astring.length > i0) {
                String s1 = a(icommandsender, astring, i0);

                if (s1.length() > 32) {
                    throw new SyntaxErrorException("commands.scoreboard.teams.add.displayTooLong", new Object[]{s1, Integer.valueOf(32)});
                }

                if (s1.length() > 0) {
                    scoreboard.f(s0).a(s1);
                }
                else {
                    scoreboard.f(s0);
                }
            }
            else {
                scoreboard.f(s0);
            }

            a(icommandsender, "commands.scoreboard.teams.add.success", new Object[]{s0});
        }
    }

    protected void d(ICommandSender icommandsender, String[] astring, int i0) {
        ScorePlayerTeam scoreplayerteam = this.a(astring[i0++]);
        String s0 = astring[i0++].toLowerCase();

        if (!s0.equalsIgnoreCase("color") && !s0.equalsIgnoreCase("friendlyfire") && !s0.equalsIgnoreCase("seeFriendlyInvisibles")) {
            throw new WrongUsageException("commands.scoreboard.teams.option.usage", new Object[0]);
        }
        else if (astring.length == 4) {
            if (s0.equalsIgnoreCase("color")) {
                throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[]{s0, a(EnumChatFormatting.a(true, false))});
            }
            else if (!s0.equalsIgnoreCase("friendlyfire") && !s0.equalsIgnoreCase("seeFriendlyInvisibles")) {
                throw new WrongUsageException("commands.scoreboard.teams.option.usage", new Object[0]);
            }
            else {
                throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[]{s0, a(Arrays.asList(new String[]{"true", "false"}))});
            }
        }
        else {
            String s1 = astring[i0++];

            if (s0.equalsIgnoreCase("color")) {
                EnumChatFormatting enumchatformatting = EnumChatFormatting.b(s1);

                if (s1 == null) {
                    throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[]{s0, a(EnumChatFormatting.a(true, false))});
                }

                scoreplayerteam.b(enumchatformatting.toString());
                scoreplayerteam.c(EnumChatFormatting.v.toString());
            }
            else if (s0.equalsIgnoreCase("friendlyfire")) {
                if (!s1.equalsIgnoreCase("true") && !s1.equalsIgnoreCase("false")) {
                    throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[]{s0, a(Arrays.asList(new String[]{"true", "false"}))});
                }

                scoreplayerteam.a(s1.equalsIgnoreCase("true"));
            }
            else if (s0.equalsIgnoreCase("seeFriendlyInvisibles")) {
                if (!s1.equalsIgnoreCase("true") && !s1.equalsIgnoreCase("false")) {
                    throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[]{s0, a(Arrays.asList(new String[]{"true", "false"}))});
                }

                scoreplayerteam.b(s1.equalsIgnoreCase("true"));
            }

            a(icommandsender, "commands.scoreboard.teams.option.success", new Object[]{s0, scoreplayerteam.b(), s1});
        }
    }

    protected void e(ICommandSender icommandsender, String[] astring, int i0) {
        Scoreboard scoreboard = this.d();
        ScorePlayerTeam scoreplayerteam = this.a(astring[i0++]);

        scoreboard.d(scoreplayerteam);
        a(icommandsender, "commands.scoreboard.teams.remove.success", new Object[]{scoreplayerteam.b()});
    }

    protected void f(ICommandSender icommandsender, String[] astring, int i0) {
        Scoreboard scoreboard = this.d();

        if (astring.length > i0) {
            ScorePlayerTeam scoreplayerteam = this.a(astring[i0++]);
            Collection collection = scoreplayerteam.d();

            if (collection.size() <= 0) {
                throw new CommandException("commands.scoreboard.teams.list.player.empty", new Object[]{scoreplayerteam.b()});
            }

            icommandsender.a(ChatMessageComponent.b("commands.scoreboard.teams.list.player.count", new Object[]{Integer.valueOf(collection.size()), scoreplayerteam.b()}).a(EnumChatFormatting.c));
            icommandsender.a(ChatMessageComponent.d(a(collection.toArray())));
        }
        else {
            Collection collection1 = scoreboard.g();

            if (collection1.size() <= 0) {
                throw new CommandException("commands.scoreboard.teams.list.empty", new Object[0]);
            }

            icommandsender.a(ChatMessageComponent.b("commands.scoreboard.teams.list.count", new Object[]{Integer.valueOf(collection1.size())}).a(EnumChatFormatting.c));
            Iterator iterator = collection1.iterator();

            while (iterator.hasNext()) {
                ScorePlayerTeam scoreplayerteam1 = (ScorePlayerTeam)iterator.next();

                icommandsender.a(ChatMessageComponent.b("commands.scoreboard.teams.list.entry", new Object[]{scoreplayerteam1.b(), scoreplayerteam1.c(), Integer.valueOf(scoreplayerteam1.d().size())}));
            }
        }
    }

    protected void g(ICommandSender icommandsender, String[] astring, int i0) {
        Scoreboard scoreboard = this.d();
        ScorePlayerTeam scoreplayerteam = scoreboard.e(astring[i0++]);
        HashSet hashset = new HashSet();
        String s0;

        if (icommandsender instanceof EntityPlayer && i0 == astring.length) {
            s0 = b(icommandsender).an();
            scoreboard.a(s0, scoreplayerteam);
            hashset.add(s0);
        }
        else {
            while (i0 < astring.length) {
                s0 = e(icommandsender, astring[i0++]);
                scoreboard.a(s0, scoreplayerteam);
                hashset.add(s0);
            }
        }

        if (!hashset.isEmpty()) {
            a(icommandsender, "commands.scoreboard.teams.join.success", new Object[]{Integer.valueOf(hashset.size()), scoreplayerteam.b(), a(hashset.toArray(new String[0]))});
        }
    }

    protected void h(ICommandSender icommandsender, String[] astring, int i0) {
        Scoreboard scoreboard = this.d();
        HashSet hashset = new HashSet();
        HashSet hashset1 = new HashSet();
        String s0;

        if (icommandsender instanceof EntityPlayer && i0 == astring.length) {
            s0 = b(icommandsender).an();
            if (scoreboard.g(s0)) {
                hashset.add(s0);
            }
            else {
                hashset1.add(s0);
            }
        }
        else {
            while (i0 < astring.length) {
                s0 = e(icommandsender, astring[i0++]);
                if (scoreboard.g(s0)) {
                    hashset.add(s0);
                }
                else {
                    hashset1.add(s0);
                }
            }
        }

        if (!hashset.isEmpty()) {
            a(icommandsender, "commands.scoreboard.teams.leave.success", new Object[]{Integer.valueOf(hashset.size()), a(hashset.toArray(new String[0]))});
        }

        if (!hashset1.isEmpty()) {
            throw new CommandException("commands.scoreboard.teams.leave.failure", new Object[]{Integer.valueOf(hashset1.size()), a(hashset1.toArray(new String[0]))});
        }
    }

    protected void i(ICommandSender icommandsender, String[] astring, int i0) {
        Scoreboard scoreboard = this.d();
        ScorePlayerTeam scoreplayerteam = this.a(astring[i0++]);
        ArrayList arraylist = new ArrayList(scoreplayerteam.d());

        if (arraylist.isEmpty()) {
            throw new CommandException("commands.scoreboard.teams.empty.alreadyEmpty", new Object[]{scoreplayerteam.b()});
        }
        else {
            Iterator iterator = arraylist.iterator();

            while (iterator.hasNext()) {
                String s0 = (String)iterator.next();

                scoreboard.b(s0, scoreplayerteam);
            }

            a(icommandsender, "commands.scoreboard.teams.empty.success", new Object[]{Integer.valueOf(arraylist.size()), scoreplayerteam.b()});
        }
    }

    protected void f(ICommandSender icommandsender, String s0) {
        Scoreboard scoreboard = this.d();
        ScoreObjective scoreobjective = this.a(s0, false);

        scoreboard.k(scoreobjective);
        a(icommandsender, "commands.scoreboard.objectives.remove.success", new Object[]{s0});
    }

    protected void d(ICommandSender icommandsender) {
        Scoreboard scoreboard = this.d();
        Collection collection = scoreboard.c();

        if (collection.size() <= 0) {
            throw new CommandException("commands.scoreboard.objectives.list.empty", new Object[0]);
        }
        else {
            icommandsender.a(ChatMessageComponent.b("commands.scoreboard.objectives.list.count", new Object[]{Integer.valueOf(collection.size())}).a(EnumChatFormatting.c));
            Iterator iterator = collection.iterator();

            while (iterator.hasNext()) {
                ScoreObjective scoreobjective = (ScoreObjective)iterator.next();

                icommandsender.a(ChatMessageComponent.b("commands.scoreboard.objectives.list.entry", new Object[]{scoreobjective.b(), scoreobjective.d(), scoreobjective.c().a()}));
            }
        }
    }

    protected void j(ICommandSender icommandsender, String[] astring, int i0) {
        Scoreboard scoreboard = this.d();
        String s0 = astring[i0++];
        int i1 = Scoreboard.j(s0);
        ScoreObjective scoreobjective = null;

        if (astring.length == 4) {
            scoreobjective = this.a(astring[i0++], false);
        }

        if (i1 < 0) {
            throw new CommandException("commands.scoreboard.objectives.setdisplay.invalidSlot", new Object[]{s0});
        }
        else {
            scoreboard.a(i1, scoreobjective);
            if (scoreobjective != null) {
                a(icommandsender, "commands.scoreboard.objectives.setdisplay.successSet", new Object[]{Scoreboard.b(i1), scoreobjective.b()});
            }
            else {
                a(icommandsender, "commands.scoreboard.objectives.setdisplay.successCleared", new Object[]{Scoreboard.b(i1)});
            }
        }
    }

    protected void k(ICommandSender icommandsender, String[] astring, int i0) {
        Scoreboard scoreboard = this.d();

        if (astring.length > i0) {
            String s0 = e(icommandsender, astring[i0++]);
            Map map = scoreboard.d(s0);

            if (map.size() <= 0) {
                throw new CommandException("commands.scoreboard.players.list.player.empty", new Object[]{s0});
            }

            icommandsender.a(ChatMessageComponent.b("commands.scoreboard.players.list.player.count", new Object[]{Integer.valueOf(map.size()), s0}).a(EnumChatFormatting.c));
            Iterator iterator = map.values().iterator();

            while (iterator.hasNext()) {
                Score score = (Score)iterator.next();

                icommandsender.a(ChatMessageComponent.b("commands.scoreboard.players.list.player.entry", new Object[]{Integer.valueOf(score.c()), score.d().d(), score.d().b()}));
            }
        }
        else {
            Collection collection = scoreboard.d();

            if (collection.size() <= 0) {
                throw new CommandException("commands.scoreboard.players.list.empty", new Object[0]);
            }

            icommandsender.a(ChatMessageComponent.b("commands.scoreboard.players.list.count", new Object[]{Integer.valueOf(collection.size())}).a(EnumChatFormatting.c));
            icommandsender.a(ChatMessageComponent.d(a(collection.toArray())));
        }
    }

    protected void l(ICommandSender icommandsender, String[] astring, int i0) {
        String s0 = astring[i0 - 1];
        String s1 = e(icommandsender, astring[i0++]);
        ScoreObjective scoreobjective = this.a(astring[i0++], true);
        int i1 = s0.equalsIgnoreCase("set") ? a(icommandsender, astring[i0++]) : a(icommandsender, astring[i0++], 1);
        Scoreboard scoreboard = this.d();
        Score score = scoreboard.a(s1, scoreobjective);

        if (s0.equalsIgnoreCase("set")) {
            score.c(i1);
        }
        else if (s0.equalsIgnoreCase("add")) {
            score.a(i1);
        }
        else {
            score.b(i1);
        }

        a(icommandsender, "commands.scoreboard.players.set.success", new Object[]{scoreobjective.b(), s1, Integer.valueOf(score.c())});
    }

    protected void m(ICommandSender icommandsender, String[] astring, int i0) {
        Scoreboard scoreboard = this.d();
        String s0 = e(icommandsender, astring[i0++]);

        scoreboard.c(s0);
        a(icommandsender, "commands.scoreboard.players.reset.success", new Object[]{s0});
    }

    public List a(ICommandSender icommandsender, String[] astring) {
        if (astring.length == 1) {
            return a(astring, new String[]{"objectives", "players", "teams"});
        }
        else {
            if (astring[0].equalsIgnoreCase("objectives")) {
                if (astring.length == 2) {
                    return a(astring, new String[]{"list", "add", "remove", "setdisplay"});
                }

                if (astring[1].equalsIgnoreCase("add")) {
                    if (astring.length == 4) {
                        return a(astring, ScoreObjectiveCriteria.a.keySet());
                    }
                }
                else if (astring[1].equalsIgnoreCase("remove")) {
                    if (astring.length == 3) {
                        return a(astring, this.a(false));
                    }
                }
                else if (astring[1].equalsIgnoreCase("setdisplay")) {
                    if (astring.length == 3) {
                        return a(astring, new String[]{"list", "sidebar", "belowName"});
                    }

                    if (astring.length == 4) {
                        return a(astring, this.a(false));
                    }
                }
            }
            else if (astring[0].equalsIgnoreCase("players")) {
                if (astring.length == 2) {
                    return a(astring, new String[]{"set", "add", "remove", "reset", "list"});
                }

                if (!astring[1].equalsIgnoreCase("set") && !astring[1].equalsIgnoreCase("add") && !astring[1].equalsIgnoreCase("remove")) {
                    if ((astring[1].equalsIgnoreCase("reset") || astring[1].equalsIgnoreCase("list")) && astring.length == 3) {
                        return a(astring, this.d().d());
                    }
                }
                else {
                    if (astring.length == 3) {
                        return a(astring, MinecraftServer.F().C());
                    }

                    if (astring.length == 4) {
                        return a(astring, this.a(true));
                    }
                }
            }
            else if (astring[0].equalsIgnoreCase("teams")) {
                if (astring.length == 2) {
                    return a(astring, new String[]{"add", "remove", "join", "leave", "empty", "list", "option"});
                }

                if (astring[1].equalsIgnoreCase("join")) {
                    if (astring.length == 3) {
                        return a(astring, this.d().f());
                    }

                    if (astring.length >= 4) {
                        return a(astring, MinecraftServer.F().C());
                    }
                }
                else {
                    if (astring[1].equalsIgnoreCase("leave")) {
                        return a(astring, MinecraftServer.F().C());
                    }

                    if (!astring[1].equalsIgnoreCase("empty") && !astring[1].equalsIgnoreCase("list") && !astring[1].equalsIgnoreCase("remove")) {
                        if (astring[1].equalsIgnoreCase("option")) {
                            if (astring.length == 3) {
                                return a(astring, this.d().f());
                            }

                            if (astring.length == 4) {
                                return a(astring, new String[]{"color", "friendlyfire", "seeFriendlyInvisibles"});
                            }

                            if (astring.length == 5) {
                                if (astring[3].equalsIgnoreCase("color")) {
                                    return a(astring, EnumChatFormatting.a(true, false));
                                }

                                if (astring[3].equalsIgnoreCase("friendlyfire") || astring[3].equalsIgnoreCase("seeFriendlyInvisibles")) {
                                    return a(astring, new String[]{"true", "false"});
                                }
                            }
                        }
                    }
                    else if (astring.length == 3) {
                        return a(astring, this.d().f());
                    }
                }
            }

            return null;
        }
    }

    protected List a(boolean flag0) {
        Collection collection = this.d().c();
        ArrayList arraylist = new ArrayList();
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            ScoreObjective scoreobjective = (ScoreObjective)iterator.next();

            if (!flag0 || !scoreobjective.c().b()) {
                arraylist.add(scoreobjective.b());
            }
        }

        return arraylist;
    }

    public boolean a(String[] astring, int i0) {
        return astring[0].equalsIgnoreCase("players") ? i0 == 2 : (!astring[0].equalsIgnoreCase("teams") ? false : i0 == 2 || i0 == 3);
    }
}
