package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.OEntity;
import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket17Sleep extends OPacket {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;

    public OPacket17Sleep() {
        super();
    }

    public OPacket17Sleep(OEntity var1, int var2, int var3, int var4, int var5) {
        super();
        this.e = var2;
        this.b = var3;
        this.c = var4;
        this.d = var5;
        this.a = var1.bd;
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readInt();
        this.e = var1.readByte();
        this.b = var1.readInt();
        this.c = var1.readByte();
        this.d = var1.readInt();
    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        var1.writeInt(this.a);
        var1.writeByte(this.e);
        var1.writeInt(this.b);
        var1.writeByte(this.c);
        var1.writeInt(this.d);
    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
    }

    @Override
    public int a() {
        return 14;
    }
}
