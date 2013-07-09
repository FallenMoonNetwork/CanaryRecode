package net.canarymod.api.scoreboard;

import java.util.List;

/**
 * @author Somners
 */
public class CanaryScoreDummyCriteria implements ScoreDummyCriteria {

    private net.minecraft.server.ScoreDummyCriteria handle;

    public CanaryScoreDummyCriteria(net.minecraft.server.ScoreDummyCriteria handle) {
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

    public net.minecraft.server.ScoreDummyCriteria getHandle() {
        return handle;
    }
}
