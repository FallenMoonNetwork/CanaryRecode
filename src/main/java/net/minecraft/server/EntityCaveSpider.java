package net.minecraft.server;


import net.canarymod.api.entity.living.monster.CanaryCaveSpider;


public class EntityCaveSpider extends EntitySpider {

    public EntityCaveSpider(World world) {
        super(world);
        this.a(0.7F, 0.5F);
        this.entity = new CanaryCaveSpider(this); // CanaryMod: Wrap Entity
    }

    protected void ax() {
        super.ax();
        this.a(SharedMonsterAttributes.a).a(12.0D);
    }

    public boolean m(Entity entity) {
        if (super.m(entity)) {
            if (entity instanceof EntityLivingBase) {
                byte b0 = 0;

                if (this.q.r > 1) {
                    if (this.q.r == 2) {
                        b0 = 7;
                    } else if (this.q.r == 3) {
                        b0 = 15;
                    }
                }

                if (b0 > 0) {
                    ((EntityLivingBase) entity).d(new PotionEffect(Potion.u.H, b0 * 20, 0));
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public EntityLivingData a(EntityLivingData entitylivingdata) {
        return entitylivingdata;
    }
}
