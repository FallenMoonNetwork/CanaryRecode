package net.minecraft.server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;


public class OPacket14BlockDig extends OPacket {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;

    public OPacket14BlockDig() {
        super();
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.e = var1.read();
        this.a = var1.readInt();
        this.b = var1.read();
        this.c = var1.readInt();
        this.d = var1.read();
    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        var1.write(this.e);
        var1.writeInt(this.a);
        var1.write(this.b);
        var1.writeInt(this.c);
        var1.write(this.d);
    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
    }

    @Override
    public int a() {
        return 11;
    }
}
