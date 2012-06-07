package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket105UpdateProgressbar extends OPacket {

    public int a;
    public int b;
    public int c;

    public OPacket105UpdateProgressbar() {
        super();
    }

    public OPacket105UpdateProgressbar(int var1, int var2, int var3) {
        super();
        this.a = var1;
        this.b = var2;
        this.c = var3;
    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readByte();
        this.b = var1.readShort();
        this.c = var1.readShort();
    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        var1.writeByte(this.a);
        var1.writeShort(this.b);
        var1.writeShort(this.c);
    }

    @Override
    public int a() {
        return 5;
    }
}
