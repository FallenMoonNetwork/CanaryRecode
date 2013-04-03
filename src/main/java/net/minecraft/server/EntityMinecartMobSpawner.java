package net.minecraft.server;

import net.canarymod.api.entity.vehicle.CanaryMobSpawnerMinecart;

public class EntityMinecartMobSpawner extends EntityMinecart {

    public final MobSpawnerBaseLogic a = new EntityMinecartMobSpawnerLogic(this); // CanaryMod: private => public

    public EntityMinecartMobSpawner(World world) {
        super(world);
        this.entity = new CanaryMobSpawnerMinecart(this); // Wrap entity
    }

    public EntityMinecartMobSpawner(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
        this.entity = new CanaryMobSpawnerMinecart(this); // Wrap entity
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
