package net.minecraft.server;

public class EntityMinecartMobSpawner extends EntityMinecart {

    private final MobSpawnerBaseLogic a = new EntityMinecartMobSpawnerLogic(this);

    public EntityMinecartMobSpawner(World world) {
        super(world);
    }

    public EntityMinecartMobSpawner(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
    }

    public int l() {
        return 4;
    }

    public Block n() {
        return Block.aw;
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.a.a(nbttagcompound);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.a.b(nbttagcompound);
    }

    public void l_() {
        super.l_();
        this.a.g();
    }
}
