package net.canarymod.api.scoreboard;

import java.util.List;

/**
 * @author Somners
 */
public class CanaryScoreHealthCriteria implements ScoreHealthCriteria {

    private net.minecraft.server.ScoreHealthCriteria handle;

    public CanaryScoreHealthCriteria(net.minecraft.server.ScoreHealthCriteria handle) {
        this.handle = handle;
    }

    @Override
    public String getProtocolName() {
        return handle.a();
    }

    @Override
    public int getScore(List list) {
        return handle.a(list);
    }

    @Override
    public boolean isReadOnly() {
        return handle.b();
    }

    public net.minecraft.server.ScoreHealthCriteria getHandle() {
        return handle;
    }
}
