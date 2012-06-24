package net.canarymod.api;

import net.minecraft.server.OPacket;

public class CanaryPacket implements Packet {

    private OPacket packet;
    
    public CanaryPacket(OPacket p) {
        packet = p;
    }
    
    @Override
    public int getPacketSize() {
        return packet.a();
    }

    @Override
    public int getPacketId() {
        return packet.b();
    }
    
    public OPacket getPacket() {
        return packet;
    }

}
