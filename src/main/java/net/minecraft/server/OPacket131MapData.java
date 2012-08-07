package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket131MapData extends OPacket {

    public short a;
    public short b;
    public byte[] c;

    public OPacket131MapData() {
        super();
        this.p = true;
    }

    public OPacket131MapData(short var1, short var2, byte[] var3) {
        super();
        this.p = true;
        this.a = var1;
        this.b = var2;
        this.c = var3;
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readShort();
        this.b = var1.readShort();
        this.c = new byte[var1.readByte() & 255];
        var1.readFully(this.c);
    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        var1.writeShort(this.a);
        var1.writeShort(this.b);
        var1.writeByte(this.c.length);
        var1.write(this.c);
    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
    }

    @Override
    public int a() {
        return 4 + this.c.length;
    }
}
