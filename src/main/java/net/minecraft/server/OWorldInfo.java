package net.minecraft.server;

import java.util.List;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OWorldSettings;
import net.minecraft.server.OWorldType;

public class OWorldInfo {

    private long randomSeed;
    private OWorldType worldType;
    private int spawnX;
    private int spawnY;
    private int spawnZ;
    private long time;
    private long g;
    private long sizeOnDisk;
    private ONBTTagCompound i;
    private int dimension;
    private String worldName;
    private int version;
    private boolean isRaining;
    private int rainTimeTicks;
    private boolean isThundering;
    private int thunderTimeTicks;
    private int gameMode;
    private boolean structures;
    private boolean hardcore;

    public OWorldInfo(ONBTTagCompound var1) {
        super();
        this.worldType = OWorldType.b;
        this.hardcore = false;
        this.randomSeed = var1.g("RandomSeed");
        if (var1.c("generatorName")) {
            String var2 = var1.j("generatorName");
            this.worldType = OWorldType.a(var2);
            if (this.worldType == null) {
                this.worldType = OWorldType.b;
            } else if (this.worldType.c()) {
                int var3 = 0;
                if (var1.c("generatorVersion")) {
                    var3 = var1.f("generatorVersion");
                }

                this.worldType = this.worldType.a(var3);
            }
        }

        this.gameMode = var1.f("GameType");
        if (var1.c("MapFeatures")) {
            this.structures = var1.o("MapFeatures");
        } else {
            this.structures = true;
        }

        this.spawnX = var1.f("SpawnX");
        this.spawnY = var1.f("SpawnY");
        this.spawnZ = var1.f("SpawnZ");
        this.time = var1.g("Time");
        this.g = var1.g("LastPlayed");
        this.sizeOnDisk = var1.g("SizeOnDisk");
        this.worldName = var1.j("LevelName");
        this.version = var1.f("version");
        this.rainTimeTicks = var1.f("rainTime");
        this.isRaining = var1.o("raining");
        this.thunderTimeTicks = var1.f("thunderTime");
        this.isThundering = var1.o("thundering");
        this.hardcore = var1.o("hardcore");
        if (var1.c("Player")) {
            this.i = var1.m("Player");
            this.dimension = this.i.f("Dimension");
        }

    }

    public OWorldInfo(OWorldSettings var1, String var2) {
        super();
        this.worldType = OWorldType.b;
        this.hardcore = false;
        this.randomSeed = var1.getSeed();
        this.gameMode = var1.getGameMode();
        this.structures = var1.areStructuresEnabled();
        this.worldName = var2;
        this.hardcore = var1.isHardcoreEnabled();
        this.worldType = var1.getWorldType();
    }

    public OWorldInfo(OWorldInfo var1) {
        super();
        this.worldType = OWorldType.b;
        this.hardcore = false;
        this.randomSeed = var1.randomSeed;
        this.worldType = var1.worldType;
        this.gameMode = var1.gameMode;
        this.structures = var1.structures;
        this.spawnX = var1.spawnX;
        this.spawnY = var1.spawnY;
        this.spawnZ = var1.spawnZ;
        this.time = var1.time;
        this.g = var1.g;
        this.sizeOnDisk = var1.sizeOnDisk;
        this.i = var1.i;
        this.dimension = var1.dimension;
        this.worldName = var1.worldName;
        this.version = var1.version;
        this.rainTimeTicks = var1.rainTimeTicks;
        this.isRaining = var1.isRaining;
        this.thunderTimeTicks = var1.thunderTimeTicks;
        this.isThundering = var1.isThundering;
        this.hardcore = var1.hardcore;
    }

    public ONBTTagCompound a() {
        ONBTTagCompound var1 = new ONBTTagCompound();
        this.a(var1, this.i);
        return var1;
    }

    public ONBTTagCompound a(List var1) {
        ONBTTagCompound var2 = new ONBTTagCompound();
        OEntityPlayer var3 = null;
        ONBTTagCompound var4 = null;
        if (var1.size() > 0) {
            var3 = (OEntityPlayer) var1.get(0);
        }

        if (var3 != null) {
            var4 = new ONBTTagCompound();
            var3.d(var4);
        }

        this.a(var2, var4);
        return var2;
    }

    private void a(ONBTTagCompound var1, ONBTTagCompound var2) {
        var1.a("RandomSeed", this.randomSeed);
        var1.a("generatorName", this.worldType.a());
        var1.a("generatorVersion", this.worldType.b());
        var1.a("GameType", this.gameMode);
        var1.a("MapFeatures", this.structures);
        var1.a("SpawnX", this.spawnX);
        var1.a("SpawnY", this.spawnY);
        var1.a("SpawnZ", this.spawnZ);
        var1.a("Time", this.time);
        var1.a("SizeOnDisk", this.sizeOnDisk);
        var1.a("LastPlayed", System.currentTimeMillis());
        var1.a("LevelName", this.worldName);
        var1.a("version", this.version);
        var1.a("rainTime", this.rainTimeTicks);
        var1.a("raining", this.isRaining);
        var1.a("thunderTime", this.thunderTimeTicks);
        var1.a("thundering", this.isThundering);
        var1.a("hardcore", this.hardcore);
        if (var2 != null) {
            var1.a("Player", var2);
        }

    }

    public long getRandomSeed() {
        return this.randomSeed;
    }

    public int getSpawnX() {
        return this.spawnX;
    }

    public int getSpawnY() {
        return this.spawnY;
    }

    public int getSpawnZ() {
        return this.spawnZ;
    }

    public long getTime() {
        return this.time;
    }

    public int getDimension() {
        return this.dimension;
    }

    public void setTime(long var1) {
        this.time = var1;
    }

    public void setSpawn(int var1, int var2, int var3) {
        this.spawnX = var1;
        this.spawnY = var2;
        this.spawnZ = var3;
    }

    public void a(String var1) {
        this.worldName = var1;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int var1) {
        this.version = var1;
    }

    public boolean isThundering() {
        return this.isThundering;
    }

    public void setThundering(boolean var1) {
        this.isThundering = var1;
    }

    public int getThunderTimeTicks() {
        return this.thunderTimeTicks;
    }

    public void setThunderTimeTicks(int var1) {
        this.thunderTimeTicks = var1;
    }

    public boolean isRaining() {
        return this.isRaining;
    }

    public void setRaining(boolean var1) {
        this.isRaining = var1;
    }

    public int getRainTimeTicks() {
        return this.rainTimeTicks;
    }

    public void setRainTimeTicks(int var1) {
        this.rainTimeTicks = var1;
    }

    public int getGameMode() {
        return this.gameMode;
    }

    public boolean getStructuresEnabled() {
        return this.structures;
    }

    public void setGameMode(int var1) {
        this.gameMode = var1;
    }

    public boolean getHardcodeEnabled() {
        return this.hardcore;
    }

    public OWorldType getWorldType() {
        return this.worldType;
    }

    public void setWorldType(OWorldType var1) {
        this.worldType = var1;
    }
}
