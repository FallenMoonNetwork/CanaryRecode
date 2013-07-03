package net.canarymod.api.scoreboard;

/**
 *
 * @author Somners
 */
public class CanaryScoreObjective implements ScoreObjective {

    private final net.minecraft.server.ScoreObjective handle;

    public CanaryScoreObjective(net.minecraft.server.ScoreObjective handle) {
        this.handle = handle;
    }

    @Override
    public String getProtocolName() {
        return handle.b();
    }

    @Override
    public ScoreObjectiveCriteria getScoreObjectiveCriteria() {
        return ((net.minecraft.server.ScoreDummyCriteria)handle.c()).getCanaryScoreObjectiveCriteria();
    }

    @Override
    public String getDisplayName() {
        return handle.d();
    }

    @Override
    public void setDisplayName(String name) {
        handle.a(name);
    }

    public net.minecraft.server.ScoreObjective getHandle() {
        return handle;
    }
}
