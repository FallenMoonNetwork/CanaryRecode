package net.minecraft.server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;


public class OPacket0KeepAlive extends OPacket {

    public int a;

    public OPacket0KeepAlive() {
        super();
    }

    public OPacket0KeepAlive(int var1) {
        super();
        this.a = var1;
    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readInt();
    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        var1.writeInt(this.a);
    }

    @Override
    public int a() {
        return 4;
    }
}
