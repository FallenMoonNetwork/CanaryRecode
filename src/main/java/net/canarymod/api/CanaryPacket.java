package net.canarymod.api;

public class CanaryPacket implements Packet {

    private net.minecraft.server.Packet packet;

    public CanaryPacket(net.minecraft.server.Packet p) {
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

    public net.minecraft.server.Packet getPacket() {
        return packet;
    }

}
