package net.canarymod.api;

import net.canarymod.api.entity.Entity;
import net.canarymod.api.world.World;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.position.Location;
import net.minecraft.server.PathNavigate;

/**
 * @author Somners
 */
public class CanaryPathFinder implements PathFinder {

    private PathNavigate nav = null;

    public CanaryPathFinder(PathNavigate nav) {
        this.nav = nav;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean setPathToXYZ(double x, double y, double z, World world) {
        return nav.a(x, y, z, nav.d);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean setPathToLocation(Location location) {
        return nav.a(location.getX(), location.getY(), location.getZ(), nav.d);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean setPathToEntity(Entity entity) {
        return nav.a(entity.getX(), entity.getY(), entity.getZ(), nav.d);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean setPathToBlock(Block block) {
        return nav.a(block.getX(), block.getY(), block.getZ(), nav.d);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWaterPathing(boolean bool) {
        nav.a(bool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCanSwim(boolean bool) {
        nav.e(bool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCanPassOpenDoors(boolean bool) {
        nav.c(bool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCanPassClosedDoors(boolean bool) {
        nav.b(bool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAvoidSun(boolean bool) {
        nav.d(bool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpeed(float speed) {
        nav.a(speed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPathSearchRange(float range) {
        // nav.e = range; // Unable to set Range at this time...
    }

}
