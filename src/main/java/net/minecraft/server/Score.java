package net.minecraft.server;

import java.util.Comparator;
import java.util.List;
import net.canarymod.api.scoreboard.CanaryScore;

public class Score {

    public static final Comparator a = new ScoreComparator();
    private final Scoreboard b;
    private final ScoreObjective c;
    private final String d;
    private int e;

    private CanaryScore score = new CanaryScore(this); // CanaryMod: set our variable

    public Score(Scoreboard scoreboard, ScoreObjective scoreobjective, String s0) {
        this.b = scoreboard;
        this.c = scoreobjective;
        this.d = s0;
    }

    public void a(int i0) {
        if (this.c.c().b()) {
            throw new IllegalStateException("Cannot modify read-only score");
        } else {
            this.c(this.c() + i0);
        }
    }

    public void b(int i0) {
        if (this.c.c().b()) {
            throw new IllegalStateException("Cannot modify read-only score");
        } else {
            this.c(this.c() - i0);
        }
    }

    public void a() {
        if (this.c.c().b()) {
            throw new IllegalStateException("Cannot modify read-only score");
        } else {
            this.a(1);
        }
    }

    public int c() {
        return this.e;
    }

    public void c(int i0) {
        int i1 = this.e;

        this.e = i0;
        if (i1 != i0) {
            this.f().a(this);
        }
    }

    public ScoreObjective d() {
        return this.c;
    }

    public String e() {
        return this.d;
    }

    public Scoreboard f() {
        return this.b;
    }

    public void a(List list) {
        this.c(this.c.c().a(list));
    }

    // CanaryMod: getters
    public CanaryScore getCanaryScore() {
        return this.score;
    }
    
}
