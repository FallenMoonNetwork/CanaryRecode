package net.canarymod.api.entity.living.humanoid;


import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.entity.living.CanaryEntityLiving;
import net.canarymod.api.entity.living.EntityLiving;
import net.canarymod.api.world.CanaryVillage;
import net.canarymod.api.world.Village;
import net.minecraft.server.EntityVillager;


/**
 * Villager wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryVillager extends CanaryEntityLiving implements Villager {

    /**
     * Constructs a new wrapper for EntityVillager
     * 
     * @param entity
     *            the EntityVillager to wrap
     */
    public CanaryVillager(EntityVillager entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Profession getProfession() {
        return Profession.fromId(getHandle().m());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setProfession(Profession profession) {
        getHandle().s(profession.ordinal());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMating() {
        return getHandle().n();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMating(boolean isMating) {
        getHandle().i(isMating);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPlaying() {
        return getHandle().o();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlaying(boolean playing) {
        getHandle().j(playing);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRevengeTarget(EntityLiving targetEntity) {
        getHandle().c((net.minecraft.server.EntityLiving) ((CanaryEntity) targetEntity).getHandle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getCustomer() {
        return (Player) getHandle().m_().getCanaryEntity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasCustomer() {
        return getHandle().m_() != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCustomer(Player player) {
        getHandle().a(((CanaryPlayer) player).getHandle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Village getVillage() {
        return getHandle().d.getCanaryVillage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVillage(Village village) {
        getHandle().d = ((CanaryVillage) village).getHandle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getGrowingAge() {
        return getHandle().b();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGrowingAge(int age) {
        getHandle().a(age);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityVillager getHandle() {
        return (EntityVillager) entity;
    }

}
