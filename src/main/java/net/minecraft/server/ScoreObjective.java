package net.minecraft.server;

import net.canarymod.api.scoreboard.CanaryScoreObjective;

public class ScoreObjective {

    private final Scoreboard a;
    private final String b;
    private final ScoreObjectiveCriteria c;
    private String d;

    // CanaryMod: our variables
    private final CanaryScoreObjective scoreObjective = new CanaryScoreObjective(this);

    public ScoreObjective(Scoreboard scoreboard, String s0, ScoreObjectiveCriteria scoreobjectivecriteria) {
        this.a = scoreboard;
        this.b = s0;
        this.c = scoreobjectivecriteria;
        this.d = s0;
    }

    public String b() {
        return this.b;
    }

    public ScoreObjectiveCriteria c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public void a(String s0) {
        this.d = s0;
        this.a.b(this);
    }

    // CanaryMod: getter
    public CanaryScoreObjective getCanaryScoreObjective() {
        return this.scoreObjective;
    }
}
