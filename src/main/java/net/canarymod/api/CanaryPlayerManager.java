package net.canarymod.api;

import java.util.ArrayList;
import net.canarymod.api.entity.living.humanoid.CanaryPlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.World;

public class CanaryPlayerManager implements PlayerManager {
    private net.minecraft.server.PlayerManager pm;
    private CanaryWorld dim;

    public CanaryPlayerManager(net.minecraft.server.PlayerManager pm, CanaryWorld dimension) {
        this.pm = pm;
        this.dim = dimension;
    }

    @Override
    public void updateMountedMovingPlayer(Player player) {
        pm.c((net.minecraft.server.EntityPlayerMP) ((CanaryPlayer) player).getHandle());

    }

    @Override
    public void addPlayer(Player player) {
        pm.a((net.minecraft.server.EntityPlayerMP) ((CanaryPlayer) player).getHandle());

    }

    @Override
    public void removePlayer(Player player) {
        pm.b((net.minecraft.server.EntityPlayerMP) ((CanaryPlayer) player).getHandle());

    }

    @Override
    public void markBlockNeedsUpdate(int x, int y, int z) {
        pm.a(x, y, z);
    }

    @Override
    public ArrayList<Player> getManagedPlayers() {
        ArrayList<Player> players = new ArrayList<Player>();

        for (net.minecraft.server.EntityPlayerMP player : pm.getManagedPlayers()) {
            players.add(player.getPlayer());
        }
        return players;
    }

    @Override
    public World getAttachedDimension() {
        return dim;
    }

    @Override
    public int getMaxTrackingDistance() {
        return net.minecraft.server.PlayerManager.a(pm.getPlayerViewRadius());
    }

    public net.minecraft.server.PlayerManager getHandle() {
        return pm;
    }

}
