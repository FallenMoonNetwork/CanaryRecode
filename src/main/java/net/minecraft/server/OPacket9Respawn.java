package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;
import net.minecraft.server.OWorldType;

public class OPacket9Respawn extends OPacket {

    public int a;
    public int b;
    public int c;
    public int d;
    public OWorldType e;

    public OPacket9Respawn() {
        super();
    }

    public OPacket9Respawn(int var1, byte var2, OWorldType var3, int var4, int var5) {
        super();
        this.a = var1;
        this.b = var2;
        this.c = var4;
        this.d = var5;
        this.e = var3;
    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readInt();
        this.b = var1.readByte();
        this.d = var1.readByte();
        this.c = var1.readShort();
        String var2 = a(var1, 16);
        this.e = OWorldType.a(var2);
        if (this.e == null) {
            this.e = OWorldType.b;
        }

    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        var1.writeInt(this.a);
        var1.writeByte(this.b);
        var1.writeByte(this.d);
        var1.writeShort(this.c);
        a(this.e.a(), var1);
    }

    @Override
    public int a() {
        return 8 + this.e.a().length();
    }
}
