package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.OItemStack;
import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket102WindowClick extends OPacket {

    public int a;
    public int b;
    public int c;
    public short d;
    public OItemStack e;
    public boolean f;

    public OPacket102WindowClick() {
        super();
    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readByte();
        this.b = var1.readShort();
        this.c = var1.readByte();
        this.d = var1.readShort();
        this.f = var1.readBoolean();
        this.e = this.b(var1);
    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        var1.writeByte(this.a);
        var1.writeShort(this.b);
        var1.writeByte(this.c);
        var1.writeShort(this.d);
        var1.writeBoolean(this.f);
        this.a(this.e, var1);
    }

    @Override
    public int a() {
        return 11;
    }
}
