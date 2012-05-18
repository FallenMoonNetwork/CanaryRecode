package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.OPacket30Entity;

public class OPacket31RelEntityMove extends OPacket30Entity {

    public OPacket31RelEntityMove() {
        super();
    }

    public OPacket31RelEntityMove(int var1, byte var2, byte var3, byte var4) {
        super(var1);
        this.b = var2;
        this.c = var3;
        this.d = var4;
    }

    public void a(DataInputStream var1) throws IOException {
        super.a(var1);
        this.b = var1.readByte();
        this.c = var1.readByte();
        this.d = var1.readByte();
    }

    public void a(DataOutputStream var1) throws IOException {
        super.a(var1);
        var1.writeByte(this.b);
        var1.writeByte(this.c);
        var1.writeByte(this.d);
    }

    public int a() {
        return 7;
    }
}
