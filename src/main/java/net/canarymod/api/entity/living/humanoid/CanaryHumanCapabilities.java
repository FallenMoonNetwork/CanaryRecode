package net.canarymod.api.entity.living.humanoid;

import net.minecraft.server.PlayerCapabilities;

/**
 * HumanCapabilities wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryHumanCapabilities implements HumanCapabilities {
    private final PlayerCapabilities capabilities;

    public CanaryHumanCapabilities(PlayerCapabilities capabilities) {
        this.capabilities = capabilities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInvulnerable() {
        return capabilities.a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInvulnerable(boolean invulnerable) {
        capabilities.a = invulnerable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFlying() {
        return capabilities.b;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFlying(boolean flying) {
        capabilities.b = flying;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean mayFly() {
        return capabilities.c;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMayFly(boolean mayfly) {
        capabilities.c = mayfly;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean instantBuild() {
        return capabilities.d;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInstantBuild(boolean instant) {
        capabilities.d = instant;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getFlySpeed() {
        return capabilities.f;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFlySpeed(float speed) {
        capabilities.f = speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getWalkSpeed() {
        return capabilities.g;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWalkSpeed(float speed) {
        capabilities.g = speed;
    }
}
