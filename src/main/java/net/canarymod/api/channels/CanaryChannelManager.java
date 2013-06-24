package net.canarymod.api.channels;

import net.canarymod.Canary;
import net.canarymod.api.CanaryNetServerHandler;
import net.canarymod.api.NetServerHandler;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.channels.ChannelManager;
import net.canarymod.channels.CustomPayloadChannelException;
import net.minecraft.server.Packet250CustomPayload;

/**
 *
 * @author Somners
 */
public class CanaryChannelManager extends ChannelManager {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendCustomPayloadToPlayer(String channel, byte[] bytestream, Player player) {
        try {
            if (bytestream == null) {
                throw new CustomPayloadChannelException("Invalid Custom Payload: Byte Array is null.");
            }
            if (channel == null || channel.trim().equals("") || channel.equalsIgnoreCase("REGISTER") || channel.equalsIgnoreCase("UNREGISTER")) {
                throw new CustomPayloadChannelException(String.format("Invalid Custom Payload: Invalid channel name of '%s'", channel));
            }
            if (channel.length() > 20) {
                throw new CustomPayloadChannelException(String.format("Invalid Custom Payload: Channel Name too long '%s'", channel));
            }
            if (player == null) {
                throw new CustomPayloadChannelException("Invalid Custom Payload: Player is null.");
            }
            if (clients.containsKey(channel)) {
                for (NetServerHandler handler : clients.get(channel)) {
                    if (handler.getUser().equals(player)) {
                        ((CanaryNetServerHandler) handler).sendPacket(new Packet250CustomPayload(channel, bytestream));
                        return true;
                    }
                }
            }
        } catch (CustomPayloadChannelException ex) {
            Canary.logStacktrace(ex.getMessage(), ex);
        }
        return false;
    }

    @Override
    public boolean sendCustomPayloadToAllPlayers(String channel, byte[] bytestream) {
        boolean toRet = false;
        try {
            if (bytestream == null) {
                throw new CustomPayloadChannelException("Invalid Custom Payload: Byte Array is null.");
            }
            if (channel == null || channel.trim().equals("") || channel.equalsIgnoreCase("REGISTER") || channel.equalsIgnoreCase("UNREGISTER")) {
                throw new CustomPayloadChannelException(String.format("Invalid Custom Payload: Invalid channel name of '%s'", channel));
            }
            if (channel.length() > 20) {
                throw new CustomPayloadChannelException(String.format("Invalid Custom Payload: Channel Name too long '%s'", channel));
            }
            if (clients.containsKey(channel)) {
                for (NetServerHandler handler : clients.get(channel)) {
                    ((CanaryNetServerHandler) handler).sendPacket(new Packet250CustomPayload(channel, bytestream));
                    toRet = true;
                }
            }
        } catch (CustomPayloadChannelException ex) {
            Canary.logStacktrace(ex.getMessage(), ex);
        }
        return toRet;
    }
}
