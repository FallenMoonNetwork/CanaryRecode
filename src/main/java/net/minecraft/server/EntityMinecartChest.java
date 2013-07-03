package net.minecraft.server;


import net.canarymod.api.entity.vehicle.CanaryChestMinecart;


public class EntityMinecartChest extends EntityMinecartContainer {

    public EntityMinecartChest(World world) {
        super(world);
        this.entity = new CanaryChestMinecart(this); // CanaryMod: Wrap Entity
    }

    public EntityMinecartChest(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
        this.entity = new CanaryChestMinecart(this); // CanaryMod: Wrap Entity
    }

    public void a(DamageSource damagesource) {
        super.a(damagesource);
        this.a(Block.az.cF, 1, 0.0F);
    }

    public int j_() {
        return 27;
    }

    public int l() {
        return 1;
    }

    public Block n() {
        return Block.az;
    }

    public int r() {
        return 8;
    }
}
