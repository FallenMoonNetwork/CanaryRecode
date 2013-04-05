package net.minecraft.server;

import net.canarymod.api.world.blocks.CanaryMobSpawner;

public class TileEntityMobSpawner extends TileEntity {

    private final MobSpawnerBaseLogic a = new TileEntityMobSpawnerLogic(this);

    public TileEntityMobSpawner() {
        this.complexBlock = new CanaryMobSpawner(this);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.a.a(nbttagcompound);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.a.b(nbttagcompound);
    }

    public void h() {
        this.a.g();
        super.h();
    }

    public Packet m() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        this.b(nbttagcompound);
        nbttagcompound.o("SpawnPotentials");
        return new Packet132TileEntityData(this.l, this.m, this.n, 1, nbttagcompound);
    }

    public boolean b(int i0, int i1) {
        return this.a.b(i0) ? true : super.b(i0, i1);
    }

    public MobSpawnerBaseLogic a() {
        return this.a;
    }

    // CanaryMod
    public CanaryMobSpawner getCanaryMobSpawner() {
        return (CanaryMobSpawner) complexBlock;
    }
}
