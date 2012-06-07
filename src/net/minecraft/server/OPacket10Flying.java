package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket10Flying extends OPacket {

    public double a;
    public double b;
    public double c;
    public double d;
    public float e;
    public float f;
    public boolean g;
    public boolean h;
    public boolean i;

    public OPacket10Flying() {
        super();
    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.g = var1.read() != 0;
    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        var1.write(this.g ? 1 : 0);
    }

    @Override
    public int a() {
        return 1;
    }
}
