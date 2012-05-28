package net.canarymod.api;

import net.canarymod.api.entity.CanaryPlayer;
import net.canarymod.api.entity.Player;
import net.minecraft.server.OEntityPlayerMP;
import net.minecraft.server.OPlayerManager;

public class CanaryPlayerManager implements PlayerManager {
    private OPlayerManager pm;
    
    public CanaryPlayerManager(OPlayerManager pm) {
        this.pm = pm;
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

}
