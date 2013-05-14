package net.canarymod.api;


import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.TextFormat;
import net.minecraft.server.Packet3Chat;


/**
 * Wrap up NetServerHandler to minimize entry point to notch code
 *
 * @author Chris Ksoll
 */
public class CanaryNetServerHandler implements NetServerHandler {

    private net.minecraft.server.NetServerHandler handler;

    public CanaryNetServerHandler(net.minecraft.server.NetServerHandler handle) {
        handler = handle;
    }

    @Override
    public void sendPacket(Packet packet) {
        handler.b(((CanaryPacket) packet).getPacket());

    }

    public void sendPacket(net.minecraft.server.Packet packet) {
        handler.b(packet);
    }

    @Override
    public void handleChat(Packet chatPacket) {
        if (chatPacket.getPacketId() != 3) {
            return; // Not a chat packet :O
        }
        handler.a((net.minecraft.server.Packet3Chat) ((CanaryPacket) chatPacket).getPacket());

    }

    @Override
    public void sendMessage(String msg) {
        if (msg.length() >= 119) {
            String cutMsg = msg.substring(0, 118);
            int finalCut = cutMsg.lastIndexOf(" ");
            if (finalCut == -1) {
                finalCut = 118;
            }
            String subCut = cutMsg.substring(0, finalCut);
            String newMsg = msg.substring(finalCut);

            handler.b(new Packet3Chat(subCut));
            String lastColor = TextFormat.getLastColor(subCut);
            sendMessage((lastColor == null ? "" : lastColor) + newMsg);
        } else {
            handler.b(new Packet3Chat(msg));
        }
    }

    @Override
    public void handleCommand(String[] command) {
        getUser().executeCommand(command);

    }

    @Override
    public void handleRespawn(Packet respawnPacket) {
        if (respawnPacket.getPacketId() != 9) {
            return; // Not a respawning packet :O
        }
        handler.a((net.minecraft.server.Packet9Respawn) ((CanaryPacket) respawnPacket).getPacket());
    }

    @Override
    public Player getUser() {
        return handler.c.getPlayer();
    }

}
