package net.canarymod.api.world;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.position.Location;

/**
 * Village wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryVillage implements Village {
    private final net.minecraft.server.Village village;

    public CanaryVillage(net.minecraft.server.Village village) {
        this.village = village;
    }

    @Override
    public void setReputationForPlayer(Player player, int rep) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getReputationForPlayer(Player player) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isPlayerReputationTooLow(Player player) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isMatingSeason() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void startMatingSeason() {
        // TODO Auto-generated method stub

    }

    @Override
    public void endMatingSeason() {
        // TODO Auto-generated method stub

    }

    @Override
    public Location getCenter() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getRadius() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getVillagerCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getIronGolemCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isAnnihilated() {
        // TODO Auto-generated method stub
        return false;
    }

    public net.minecraft.server.Village getHandle() {
        return village;
    }

}
