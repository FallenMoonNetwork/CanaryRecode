package net.canarymod.api;

import net.canarymod.api.entity.living.humanoid.Player;

/**
 * Wrap up NetServerHandler to minimize entry point to notch code
 * 
 * @author Chris Ksoll
 */
public class CanaryNetServerHandler implements NetServerHandler{

    private net.minecraft.server.NetServerHandler handler;

    public CanaryNetServerHandler(net.minecraft.server.NetServerHandler handle){
        handler = handle;
    }

    @Override
    public void sendPacket(Packet packet){
        handler.b(((CanaryPacket) packet).getPacket());

    }

    public void sendPacket(net.minecraft.server.Packet packet){
        handler.b(packet);
    }

    @Override
    public void handleChat(Packet chatPacket){
        if (chatPacket.getPacketId() != 3) {
            return; // Not a chat packet :O
        }
        handler.playerChat((net.minecraft.server.Packet3Chat) ((CanaryPacket) chatPacket).getPacket());

    }

    @Override
    public void sendMessage(String messgage){
        handler.sendMessage(messgage);
    }

    @Override
    public void handleCommand(String[] command){
        handler.getUser().executeCommand(command);

    }

    @Override
    public void handleRespawn(Packet respawnPacket){
        if (respawnPacket.getPacketId() != 9) {
            return; // Not a respawning packet :O
        }
        handler.a((net.minecraft.server.Packet9Respawn) ((CanaryPacket) respawnPacket).getPacket());
    }

    @Override
    public Player getUser(){
        return handler.getUser();
    }

}
