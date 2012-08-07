package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket255KickDisconnect extends OPacket {

    public String a;

    public OPacket255KickDisconnect() {
        super();
    }

    public OPacket255KickDisconnect(String var1) {
        super();
        this.a = var1;
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = a(var1, 256);
    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        a(this.a, var1);
    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
    }

    @Override
    public int a() {
        return this.a.length();
    }
}
