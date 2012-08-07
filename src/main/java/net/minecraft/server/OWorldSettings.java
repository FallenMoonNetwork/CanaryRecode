package net.minecraft.server;

import net.minecraft.server.OWorldType;

public final class OWorldSettings {

    private final long seed;
    private final int gameMode;
    private final boolean structuresEnabled;
    private final boolean hardcoreEnabled;
    private final OWorldType worldType;

    public OWorldSettings(long var1, int var3, boolean var4, boolean var5, OWorldType var6) {
        super();
        this.seed = var1;
        this.gameMode = var3;
        this.structuresEnabled = var4;
        this.hardcoreEnabled = var5;
        this.worldType = var6;
    }

    public long getSeed() {
        return this.seed;
    }

    public int getGameMode() {
        return this.gameMode;
    }

    public boolean isHardcoreEnabled() {
        return this.hardcoreEnabled;
    }

    public boolean areStructuresEnabled() {
        return this.structuresEnabled;
    }

    public OWorldType getWorldType() {
        return this.worldType;
    }

    public static int a(int var0) {
        switch (var0) {
        case 0:
        case 1:
            return var0;
        default:
            return 0;
        }
    }
}
