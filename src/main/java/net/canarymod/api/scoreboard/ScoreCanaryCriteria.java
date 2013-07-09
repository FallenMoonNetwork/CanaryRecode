package net.canarymod.api.scoreboard;

import java.util.List;

/**
 * Our implementation of the notchian code for plugin use.
 * 
 * @author Somners
 */
public class ScoreCanaryCriteria extends net.minecraft.server.ScoreDummyCriteria {

    private ScoreObjectiveCriteria criteria;

    public ScoreCanaryCriteria(ScoreObjectiveCriteria criteria) {
        super(criteria.getProtocolName());
        this.criteria = criteria;
    }

    @Override
    public int a(List list) {
        return criteria.getScore(list);
    }

    @Override
    public boolean b() {
        return criteria.isReadOnly();
    }

}
