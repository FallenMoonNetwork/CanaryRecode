package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket4UpdateTime extends OPacket {

    public long a;

    public OPacket4UpdateTime() {
        super();
    }

    public OPacket4UpdateTime(long var1) {
        super();
        this.a = var1;
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readLong();
    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        var1.writeLong(this.a);
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
