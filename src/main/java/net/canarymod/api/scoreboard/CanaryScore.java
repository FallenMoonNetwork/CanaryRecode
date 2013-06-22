package net.canarymod.api.scoreboard;

import java.util.List;
import net.minecraft.server.ServerScoreboard;

/**
 *
 * @author Somners
 */
public class CanaryScore implements Score {

    private net.minecraft.server.Score handle;

    public CanaryScore(net.minecraft.server.Score handle) {
        this.handle = handle;
    }

    @Override
    public void addToScore(int toAdd) {
        handle.a(toAdd);
    }

    @Override
    public void removeFromScore(int toRemove) {
        handle.b(toRemove);
    }

    @Override
    public void setScore(int toSet) {
        handle.c(toSet);
    }

    @Override
    public int getScore() {
        return handle.c();
    }

    @Override
    public ScoreObjective getScoreObjective() {
        return handle.d().getCanaryScoreObjective();
    }

    @Override
    public Scoreboard getScoreboard() {
        return ((ServerScoreboard)handle.f()).getCanaryScoreboard();
    }

    @Override
    public void setReadOnlyScore(List list) {
        this.setScore(this.getScoreObjective().getScoreObjectiveCriteria().getScore(list));
    }

    @Override
    public String getName() {
        return handle.e();
    }

    @Override
    public void update() {
        handle.f().a(handle);
    }

}
