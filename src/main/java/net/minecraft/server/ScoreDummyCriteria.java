package net.minecraft.server;

import net.canarymod.api.scoreboard.CanaryScoreDummyCriteria;

import java.util.List;

public class ScoreDummyCriteria implements ScoreObjectiveCriteria {

    private final String g;

    protected net.canarymod.api.scoreboard.ScoreObjectiveCriteria criteria;

    public ScoreDummyCriteria(String s0) {
        this.g = s0;
        // CanaryMod: lets not add doubles, otay?
        if (!ScoreObjectiveCriteria.a.containsKey(s0)) {
            ScoreObjectiveCriteria.a.put(s0, this);
        }//
        // CanaryMod: Set Variable
        if (!(this instanceof ScoreHealthCriteria)) {
            criteria = new CanaryScoreDummyCriteria(this);
        }//
    }

    public String a() {
        return this.g;
    }

    public int a(List list) {
        return 0;
    }

    public boolean b() {
        return false;
    }

    public net.canarymod.api.scoreboard.ScoreObjectiveCriteria getCanaryScoreObjectiveCriteria() {
        return this.criteria;
    }
}
