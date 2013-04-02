package net.minecraft.server;

import net.canarymod.api.entity.living.monster.CanaryGiantZombie;

public class EntityGiantZombie extends EntityMob {

    public EntityGiantZombie(World world) {
        super(world);
        this.aH = "/mob/zombie.png";
        this.bI = 0.5F;
        this.N *= 6.0F;
        this.a(this.O * 6.0F, this.P * 6.0F);
        this.entity = new CanaryGiantZombie(this); // CanaryMod: Wrap Entity
    }

    public int aW() {
        return maxHealth == 0 ? 100 : maxHealth; // CanaryMod: custom Max Health
    }

    public float a(int i0, int i1, int i2) {
        return this.q.q(i0, i1, i2) - 0.5F;
    }

    public int c(Entity entity) {
        return 50;
    }
}
