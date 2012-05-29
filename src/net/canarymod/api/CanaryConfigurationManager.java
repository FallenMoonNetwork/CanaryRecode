package net.canarymod.api;


import java.util.ArrayList;

import net.canarymod.api.entity.Player;
import net.minecraft.server.OEntityPlayerMP;
import net.minecraft.server.OServerConfigurationManager;

public class CanaryConfigurationManager implements ConfigurationManager{

    OServerConfigurationManager manager;
    public CanaryConfigurationManager(OServerConfigurationManager man) {
        manager =  man;
    }
    @Override
    public void sendPacketToAllInWorld(String world, Packet packet) {
        for(OEntityPlayerMP p : manager.b) {
            if(p.getDimension().getWorld().getName().equals(world)) {
                manager.a(p.getPlayer().getName(), ((CanaryPacket)packet).getPacket());
            }
        }
        
    }

    @Override
    public int getNumPlayersOnline() {
        return manager.b.size();
    }

    @Override
    public Player getPlayerByName(String name) {
        return manager.i(name).getPlayer();
    }
    
    @Override
    public ArrayList<Player> getAllPlayers() {
        ArrayList<Player> players = new ArrayList<Player>(manager.b.size());
        for(OEntityPlayerMP omp : manager.b) {
            players.add(omp.getPlayer());
        }
        return players;
    }
    @Override
    public int getMaxPlayers() {
        return manager.k();
    }

}
