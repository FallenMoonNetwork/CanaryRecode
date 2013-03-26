package net.canarymod.api;


import java.util.ArrayList;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.World;
import net.canarymod.api.world.WorldType;
import net.minecraft.server.EntityPlayerMP;
import net.minecraft.server.ServerConfigurationManager;


public class CanaryConfigurationManager implements ConfigurationManager {

    ServerConfigurationManager manager;

    public CanaryConfigurationManager(ServerConfigurationManager man) {
        manager = man;
    }

    public ServerConfigurationManager getHandle() {
        return manager;
    }

    @Override
    public void sendPacketToAllInWorld(String world, Packet packet) {
        for (Object p : manager.a) {
            if(!(p instanceof EntityPlayerMP)) {
                continue;
            }
            if (((EntityPlayerMP)p).getCanaryWorld().getName().equals(world)) {
                manager.sendPacketToPlayer(((EntityPlayerMP)p).getPlayer(), (CanaryPacket)packet);
            }
        }

    }

    @Override
    public int getNumPlayersOnline() {
        return manager.a.size();
    }

    @Override
    public Player getPlayerByName(String name) {
        return manager.f(name).getPlayer();
    }

    @Override
    public ArrayList<Player> getAllPlayers() {
        ArrayList<Player> players = new ArrayList<Player>(manager.a.size());

        for (Object p : manager.a) {
            if(!(p instanceof EntityPlayerMP)) {
                continue;
            }
            players.add(((EntityPlayerMP)p).getPlayer());
        }
        return players;
    }

    @Override
    public int getMaxPlayers() {
        return manager.k();
    }

    @Override
    public void markBlockNeedsUpdate(int x, int y, int z, WorldType dimension, String world) {
        Canary.getServer().getWorldManager().getWorld(world, dimension, true).getPlayerManager().markBlockNeedsUpdate(x, y, z);

    }

    @Override
    public void switchDimension(Player player, World world, boolean createPortal) {
        // TODO Auto-generated method stub

    }
}
