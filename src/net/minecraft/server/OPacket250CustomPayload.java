package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket250CustomPayload extends OPacket {

    public String a;
    public int b;
    public byte[] c;

    public OPacket250CustomPayload() {
        super();
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = a(var1, 16);
        this.b = var1.readShort();
        if (this.b > 0 && this.b < 32767) {
            this.c = new byte[this.b];
            var1.readFully(this.c);
        }

    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        a(this.a, var1);
        var1.writeShort((short) this.b);
        if (this.c != null) {
            var1.write(this.c);
        }

    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
    }

    @Override
    public int a() {
        return 2 + this.a.length() * 2 + 2 + this.b;
    }
}
