package net.minecraft.server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;


public class OPacket8UpdateHealth extends OPacket {

    public int a;
    public int b;
    public float c;

    public OPacket8UpdateHealth() {
        super();
    }

    public OPacket8UpdateHealth(int var1, int var2, float var3) {
        super();
        this.a = var1;
        this.b = var2;
        this.c = var3;
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readShort();
        this.b = var1.readShort();
        this.c = var1.readFloat();
    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        var1.writeShort(this.a);
        var1.writeShort(this.b);
        var1.writeFloat(this.c);
    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
    }

    @Override
    public int a() {
        return 8;
    }
}
