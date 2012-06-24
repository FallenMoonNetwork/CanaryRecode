package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket132TileEntityData extends OPacket {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;

    public OPacket132TileEntityData() {
        super();
        this.p = true;
    }

    public OPacket132TileEntityData(int var1, int var2, int var3, int var4, int var5) {
        super();
        this.p = true;
        this.a = var1;
        this.b = var2;
        this.c = var3;
        this.d = var4;
        this.e = var5;
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readInt();
        this.b = var1.readShort();
        this.c = var1.readInt();
        this.d = var1.readByte();
        this.e = var1.readInt();
        this.f = var1.readInt();
        this.g = var1.readInt();
    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        var1.writeInt(this.a);
        var1.writeShort(this.b);
        var1.writeInt(this.c);
        var1.writeByte((byte) this.d);
        var1.writeInt(this.e);
        var1.writeInt(this.f);
        var1.writeInt(this.g);
    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
    }

    @Override
    public int a() {
        return 25;
    }
}
