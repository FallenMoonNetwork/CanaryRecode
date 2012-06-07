package net.canarymod.api;

import net.canarymod.api.entity.Player;
import net.minecraft.server.ONetServerHandler;
import net.minecraft.server.OPacket;
import net.minecraft.server.OPacket3Chat;
import net.minecraft.server.OPacket9Respawn;

/**
 * Wrap up NetServerHandler to minimize entry point to notch code
 * @author Chris Ksoll
 *
 */
public class CanaryNetServerHandler implements NetServerHandler {
    
    private ONetServerHandler handler;
    
    public CanaryNetServerHandler(ONetServerHandler handle) {
        handler = handle;
    }

    @Override
    public void sendPacket(Packet packet) {
        handler.b(((CanaryPacket)packet).getPacket());

    }
    
    public void sendPacket(OPacket packet) {
        handler.b(packet);
    }

    @Override
    public void handleChat(Packet chatPacket) {
        if(chatPacket.getPacketId() != 3) {
            return; //Not a chat packet :O
        }
        handler.playerChat((OPacket3Chat)((CanaryPacket)chatPacket).getPacket());

    }
    
    @Override
    public void sendMessage(String messgage) {
        handler.sendMessage(messgage);
    }

    @Override
    public void handleCommand(String[] command) {
        handler.getUser().executeCommand(command);

    }

    @Override
    public void handleRespawn(Packet respawnPacket) {
        if(respawnPacket.getPacketId() != 9) {
            return; //Not a respawning packet :O
        }
        handler.a((OPacket9Respawn)((CanaryPacket)respawnPacket).getPacket());
    }

    @Override
    public Player getUser() {
        return handler.getUser();
    }

}
