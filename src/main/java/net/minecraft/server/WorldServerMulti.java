package net.minecraft.server;

public class WorldServerMulti extends WorldServer {

    public WorldServerMulti(MinecraftServer minecraftserver, ISaveHandler isavehandler, String s0, int i0, WorldSettings worldsettings, WorldServer worldserver, Profiler profiler, ILogAgent ilogagent) {
        super(minecraftserver, isavehandler, s0, i0, worldsettings, profiler, ilogagent);
        this.z = worldserver.z;
        this.D = worldserver.V();
        this.x = new DerivedWorldInfo(worldserver.L());
    }

    protected void a() throws MinecraftException {}
}
