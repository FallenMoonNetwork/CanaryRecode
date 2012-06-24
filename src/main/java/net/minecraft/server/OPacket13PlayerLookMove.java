package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.OPacket10Flying;

public class OPacket13PlayerLookMove extends OPacket10Flying {

    public OPacket13PlayerLookMove() {
        super();
        this.i = true;
        this.h = true;
    }

    public OPacket13PlayerLookMove(double var1, double var3, double var5, double var7, float var9, float var10, boolean var11) {
        super();
        this.a = var1;
        this.b = var3;
        this.d = var5;
        this.c = var7;
        this.e = var9;
        this.f = var10;
        this.g = var11;
        this.i = true;
        this.h = true;
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readDouble();
        this.b = var1.readDouble();
        this.d = var1.readDouble();
        this.c = var1.readDouble();
        this.e = var1.readFloat();
        this.f = var1.readFloat();
        super.a(var1);
    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        var1.writeDouble(this.a);
        var1.writeDouble(this.b);
        var1.writeDouble(this.d);
        var1.writeDouble(this.c);
        var1.writeFloat(this.e);
        var1.writeFloat(this.f);
        super.a(var1);
    }

    @Override
    public int a() {
        return 41;
    }
}
