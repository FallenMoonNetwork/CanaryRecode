package net.minecraft.server;


import net.canarymod.api.entity.living.monster.CanaryGiantZombie;


public class EntityGiantZombie extends EntityMob {

    public EntityGiantZombie(World world) {
        super(world);
        this.N *= 6.0F;
        this.a(this.O * 6.0F, this.P * 6.0F);
        this.entity = new CanaryGiantZombie(this); // CanaryMod: Wrap Entity
    }

    protected void ax() {
        super.ax();
        this.a(SharedMonsterAttributes.a).a(100.0D);
        this.a(SharedMonsterAttributes.d).a(0.5D);
        this.a(SharedMonsterAttributes.e).a(50.0D);
    }

    public int aW() {
        return maxHealth == 0 ? 100 : maxHealth; // CanaryMod: custom Max Health
    }

    public float a(int i0, int i1, int i2) {
        return this.q.q(i0, i1, i2) - 0.5F;
    }
}
