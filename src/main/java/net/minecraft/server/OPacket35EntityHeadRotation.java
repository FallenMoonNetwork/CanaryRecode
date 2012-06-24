package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket35EntityHeadRotation extends OPacket {

    public int a;
    public byte b;

    public OPacket35EntityHeadRotation() {
        super();
    }

    public OPacket35EntityHeadRotation(int var1, byte var2) {
        super();
        this.a = var1;
        this.b = var2;
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readInt();
        this.b = var1.readByte();
    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        var1.writeInt(this.a);
        var1.writeByte(this.b);
    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
    }

    @Override
    public int a() {
        return 5;
    }
}
