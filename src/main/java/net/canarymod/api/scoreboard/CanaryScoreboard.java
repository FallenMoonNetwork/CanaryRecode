package net.canarymod.api.scoreboard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.canarymod.api.entity.living.humanoid.CanaryPlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.minecraft.server.Packet;
import net.minecraft.server.Packet208SetDisplayObjective;

/**
 *
 * @author Somners
 */
public class CanaryScoreboard implements Scoreboard{

    private net.minecraft.server.Scoreboard handle;

    public CanaryScoreboard(net.minecraft.server.Scoreboard handle) {
        this.handle = handle;
    }

    @Override
    public List<ScoreObjective> getScoreObjectives() {
        List<ScoreObjective> toRet = new ArrayList<ScoreObjective>();
        for (Object o : handle.getAllScoreObjective()) {
            net.minecraft.server.ScoreObjective objective = (net.minecraft.server.ScoreObjective)o;
            toRet.add(objective.getCanaryScoreObjective());
        }
        return toRet;
    }

    @Override
    public void addScoreObjective(String name) {
        this.handle.a(name, net.minecraft.server.ScoreObjectiveCriteria.b);
    }

    @Override
    public void addScoreObjective(String name, ScoreObjectiveCriteria criteria) {
        this.handle.a(name, ((CanaryScoreDummyCriteria)criteria).getHandle());
    }

    @Override
    public void removeScoreObjective(ScoreObjective objective) {
        this.handle.c(((CanaryScoreObjective)objective).getHandle());
    }

    @Override
    public ScoreObjective getScoreObjective(String name) {
        return handle.getScoreObjective(name).getCanaryScoreObjective();
    }



    @Override
    public void removeScoreObjective(String name) {
        net.minecraft.server.ScoreObjective obj = handle.getScoreObjective(name);
        if (obj != null) {
            handle.k(obj);
        }

    }

    @Override
    public List<Team> getTeams() {
        List<Team> toRet = new ArrayList<Team>();
        for (Object o : handle.g()) {
            net.minecraft.server.ScorePlayerTeam team = (net.minecraft.server.ScorePlayerTeam)o;
            toRet.add(team.getCanaryTeam());
        }
        return toRet;
    }

    @Override
    public void addTeam(Team team) {
        handle.a(((CanaryTeam)team).getHandle());
    }

    @Override
    public void removeTeam(Team team) {
        handle.c(((CanaryTeam)team).getHandle());
    }

    @Override
    public void removeTeam(String name) {
        for (Object o : handle.g()) {
            net.minecraft.server.ScorePlayerTeam team = (net.minecraft.server.ScorePlayerTeam)o;
            if (team.b().equalsIgnoreCase(name)) {
                handle.c(team);
                return;
            }
        }
    }

    @Override
    public Score getScore(Player player, ScoreObjective scoreObjective) {
        return this.getScore(player.getName(), scoreObjective);
    }

    @Override
    public Score getScore(String name, ScoreObjective scoreObjective) {
        return handle.a(name, ((CanaryScoreObjective)scoreObjective).getHandle()).getCanaryScore();
    }

    @Override
    public List<Score> getScores(ScoreObjective scoreObjective) {
        Collection i = handle.i(((CanaryScoreObjective)scoreObjective).getHandle());
        List<Score> scores = new ArrayList<Score>();
        for (Object o : i) {
            scores.add((Score)o);
        }
        return scores;
    }

    @Override
    public List<Score> getAllScores() {
        Collection i = handle.e();
        List<Score> scores = new ArrayList<Score>();
        for (Object o : i) {
            scores.add((Score)o);
        }
        return scores;
    }

    public net.minecraft.server.Scoreboard getHandle() {
        return handle;
    }

    @Override
    public void setScoreObjectivePostion(ScorePosition type, ScoreObjective objective) {
        handle.a(type.getId(), ((CanaryScoreObjective)objective).getHandle());
    }

    @Override
    public void setScoreObjectivePostion(ScorePosition type, ScoreObjective objective, Player player) {
        ((CanaryPlayer)player).getHandle().a.b((Packet) (new Packet208SetDisplayObjective(type.getId(), ((CanaryScoreObjective)objective).getHandle())));
    }

}
