package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.OPacket30Entity;

public class OPacket32EntityLook extends OPacket30Entity {

    public OPacket32EntityLook() {
        super();
        this.g = true;
    }

    public OPacket32EntityLook(int var1, byte var2, byte var3) {
        super(var1);
        this.e = var2;
        this.f = var3;
        this.g = true;
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        super.a(var1);
        this.e = var1.readByte();
        this.f = var1.readByte();
    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        super.a(var1);
        var1.writeByte(this.e);
        var1.writeByte(this.f);
    }

    @Override
    public int a() {
        return 6;
    }
}
