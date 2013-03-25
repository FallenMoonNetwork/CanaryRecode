package net.canarymod.api.entity.living.animal;


import net.canarymod.api.entity.living.CanaryEntityLiving;
import net.canarymod.api.entity.living.EntityLiving;
import net.minecraft.server.EntityVillager;


public class CanaryVillager extends CanaryEntityAnimal implements Villager {

    public CanaryVillager(EntityVillager entity) {
        super(entity);
    }

    @Override
    public Profession getProfession() {
        return Profession.fromId(((EntityVillager) entity).x());
    }

    @Override
    public void setProfession(Profession profession) {
        ((EntityVillager) entity).f_(profession.getId());
    }

    @Override
    public boolean isMating() {
        return ((EntityVillager) entity).A();
    }

    @Override
    public void setMating(boolean isMating) {
        ((EntityVillager) entity).a(isMating);

    }

    @Override
    public void setRevengeTarget(EntityLiving targetEntity) {
        ((EntityVillager) entity).a(((CanaryEntityLiving) targetEntity).getHandle());
    }

}
