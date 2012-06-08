package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket200Statistic extends OPacket {

    public int a;
    public int b;

    public OPacket200Statistic() {
        super();
    }

    public OPacket200Statistic(int var1, int var2) {
        super();
        this.a = var1;
        this.b = var2;
    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
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
    public int a() {
        return 6;
    }
}
