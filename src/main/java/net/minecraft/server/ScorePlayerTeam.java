package net.minecraft.server;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import net.canarymod.api.scoreboard.CanaryTeam;

public class ScorePlayerTeam {

    public final Scoreboard a; //CanaryMod: private to public
    private final String b;
    private final Set c = new HashSet();
    private String d;
    private String e = "";
    private String f = "";
    private boolean g = true;
    private boolean h = true;

    private CanaryTeam team = new CanaryTeam(this); // CanaryMod: initialize our variable

    public ScorePlayerTeam(Scoreboard scoreboard, String s0) {
        this.a = scoreboard;
        this.b = s0;
        this.d = s0;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.d;
    }

    public void a(String s0) {
        if (s0 == null) {
            throw new IllegalArgumentException("Name cannot be null");
        } else {
            this.d = s0;
            this.a.b(this);
        }
    }

    public Collection d() {
        return this.c;
    }

    public String e() {
        return this.e;
    }

    public void b(String s0) {
        if (s0 == null) {
            throw new IllegalArgumentException("Prefix cannot be null");
        } else {
            this.e = s0;
            this.a.b(this);
        }
    }

    public String f() {
        return this.f;
    }

    public void c(String s0) {
        if (s0 == null) {
            throw new IllegalArgumentException("Suffix cannot be null");
        } else {
            this.f = s0;
            this.a.b(this);
        }
    }

    public static String a(ScorePlayerTeam scoreplayerteam, String s0) {
        return scoreplayerteam == null ? s0 : scoreplayerteam.e() + s0 + scoreplayerteam.f();
    }

    public boolean g() {
        return this.g;
    }

    public void a(boolean flag0) {
        this.g = flag0;
        this.a.b(this);
    }

    public boolean h() {
        return this.h;
    }

    public void b(boolean flag0) {
        this.h = flag0;
        this.a.b(this);
    }

    public int i() {
        int i0 = 0;

        if (this.g()) {
            i0 |= 1;
        }

        if (this.h()) {
            i0 |= 2;
        }

        return i0;
    }

    // CanaryMod: getter
    public CanaryTeam getCanaryTeam() {
        return this.team;
    }
}
