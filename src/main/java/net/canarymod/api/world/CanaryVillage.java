package net.canarymod.api.world;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.position.Location;
import net.minecraft.server.ChunkCoordinates;

/**
 * Village wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryVillage implements Village {
    private final net.minecraft.server.Village village;

    /**
     * Constructs a new wrapper for Village
     * 
     * @param village
     *            the Village to wrap
     */
    public CanaryVillage(net.minecraft.server.Village village) {
        this.village = village;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setReputationForPlayer(Player player, int rep) {
        getHandle().a(player.getName(), rep);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getReputationForPlayer(Player player) {
        return getHandle().a(player.getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPlayerReputationTooLow(Player player) {
        return getHandle().d(player.getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMatingSeason() {
        return getHandle().i();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startMatingSeason() {
        getHandle().i -= 3600;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endMatingSeason() {
        getHandle().h();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Location getCenter() {
        ChunkCoordinates cc = getHandle().a();

        return new Location(cc.a, cc.b, cc.c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRadius() {
        return getHandle().b();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getVillagerCount() {
        return getHandle().e();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getIronGolemCount() {
        return getHandle().l;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAnnihilated() {
        return getHandle().g();
    }

    /**
     * Gets the Village being wrapped
     * 
     * @return the Village
     */
    public net.minecraft.server.Village getHandle() {
        return village;
    }

}
