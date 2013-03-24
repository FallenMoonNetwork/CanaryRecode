package net.canarymod.api;

import java.util.ArrayList;
import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.WorldType;
import net.minecraft.server.EntityPlayerMP;
import net.minecraft.server.ServerConfigurationManager;

public class CanaryConfigurationManager implements ConfigurationManager{

    ServerConfigurationManager manager;

    public CanaryConfigurationManager(ServerConfigurationManager man){
        manager = man;
    }

    public ServerConfigurationManager getHandle(){
        return manager;
    }

    @Override
    public void sendPacketToAllInWorld(String world, Packet packet){
        for (EntityPlayerMP p : manager.b) {
            if (p.getCanaryWorld().getName().equals(world)) {
                manager.a(p.getPlayer().getName(), ((CanaryPacket) packet).getPacket());
            }
        }

    }

    @Override
    public int getNumPlayersOnline(){
        return manager.b.size();
    }

    @Override
    public Player getPlayerByName(String name){
        return manager.i(name).getPlayer();
    }

    @Override
    public ArrayList<Player> getAllPlayers(){
        ArrayList<Player> players = new ArrayList<Player>(manager.b.size());
        for (EntityPlayerMP omp : manager.b) {
            players.add(omp.getPlayer());
        }
        return players;
    }

    @Override
    public int getMaxPlayers(){
        return manager.k();
    }

    @Override
    public void markBlockNeedsUpdate(int x, int y, int z, WorldType dimension, String world){
        Canary.getServer().getWorldManager().getWorld(world, dimension, true).getPlayerManager().markBlockNeedsUpdate(x, y, z);

    }

}
