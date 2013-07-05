package net.canarymod.api.entity;

import net.canarymod.api.entity.living.humanoid.EntityNonPlayableCharacter;
import net.canarymod.api.entity.living.humanoid.Player;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.EntityXPOrb;

/**
 * XPOrb wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryXPOrb extends CanaryEntity implements XPOrb {

    /**
     * Constructs a new wrapper for EntityXPOrb
     * 
     * @param entity
     *            the EntityXPOrb to be wrapped
     */
    public CanaryXPOrb(EntityXPOrb entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntityType() {
        return EntityType.XPORB;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public short getOrbAge() {
        return (short) getHandle().b;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOrbAge(short age) {
        getHandle().b = age;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPickUpDelay() {
        return getHandle().c;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPickUpDelay(int delay) {
        getHandle().c = delay;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public short getHealth() {
        return (short) getHandle().d;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHealth(short health) {
        getHandle().d = Math.min(health, 255);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public short getXPValue() {
        return (short) getHandle().c();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setXPValue(short value) {
        getHandle().setXPValue(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getClosestPlayer() {
        EntityPlayer entityplayer = getHandle().getClosestPlayer();
        return (Player) (entityplayer != null && !(entityplayer instanceof EntityNonPlayableCharacter) ? entityplayer.getCanaryEntity() : null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityXPOrb getHandle() {
        return (EntityXPOrb) entity;
    }
}
