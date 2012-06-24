package net.canarymod.api;

import java.util.ArrayList;

import net.canarymod.api.entity.CanaryPlayer;
import net.canarymod.api.entity.Player;
import net.canarymod.api.world.CanaryDimension;
import net.canarymod.api.world.Dimension;
import net.minecraft.server.OEntityPlayerMP;
import net.minecraft.server.OPlayerManager;

public class CanaryPlayerManager implements PlayerManager {
    private OPlayerManager pm;
    private CanaryDimension dim;
    public CanaryPlayerManager(OPlayerManager pm, CanaryDimension dimension) {
        this.pm = pm;
        this.dim = dimension;
    }

    @Override
    public void updateMountedMovingPlayer(Player player) {
        pm.c((OEntityPlayerMP) ((CanaryPlayer)player).getHandle());

    }

    @Override
    public void addPlayer(Player player) {
        pm.a((OEntityPlayerMP) ((CanaryPlayer)player).getHandle());

    }

    @Override
    public void removePlayer(Player player) {
        pm.b((OEntityPlayerMP) ((CanaryPlayer)player).getHandle());

    }

    @Override
    public void markBlockNeedsUpdate(int x, int y, int z) {
        pm.a(x, y, z);
    }

    @Override
    public ArrayList<Player> getManagedPlayers() {
        ArrayList<Player> players = new ArrayList<Player>();
        for(OEntityPlayerMP player : pm.managedPlayers) {
            players.add(player.getPlayer());
        }
        return players;
    }

    @Override
    public Dimension getAttachedDimension() {
        return dim;
    }

    @Override
    public int getMaxTrackingDistance() {
        return pm.c();
    }

    public OPlayerManager getHandle() {
        return pm;
    }

}
