package net.canarymod.api.entity;

import net.minecraft.server.OEntityVillager;

public class CanaryVillager extends CanaryEntityAnimal implements Villager {

    public CanaryVillager(OEntityVillager entity) {
        super(entity);
    }

    @Override
    public Profession getProfession() {
        return Profession.fromId(((OEntityVillager)entity).x());
    }

    @Override
    public void setProfession(Profession profession) {
        ((OEntityVillager)entity).f_(profession.getId());
    }

    @Override
    public boolean isMating() {
        return ((OEntityVillager)entity).A();
    }

    @Override
    public void setMating(boolean isMating) {
        ((OEntityVillager)entity).a(isMating);

    }

    @Override
    public void setRevengeTarget(EntityLiving targetEntity) {
        ((OEntityVillager)entity).a( ((CanaryEntityLiving)targetEntity).getHandle() );
    }

}
