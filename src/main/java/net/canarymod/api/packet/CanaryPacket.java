package net.canarymod.api.packet;

public class CanaryPacket implements net.canarymod.api.packet.Packet {

    protected final net.minecraft.server.Packet packet;

    public CanaryPacket(net.minecraft.server.Packet packet) {
        this.packet = packet;
    }

    @Override
    public int getPacketSize() {
        return this.packet.a();
    }

    @Override
    public int getPacketId() {
        return this.packet.n();
    }

    public net.minecraft.server.Packet getPacket() {
        return this.packet;
    }

}
