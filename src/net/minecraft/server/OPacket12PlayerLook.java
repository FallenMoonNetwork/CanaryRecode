package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.OPacket10Flying;

public class OPacket12PlayerLook extends OPacket10Flying {

    public OPacket12PlayerLook() {
        super();
        this.i = true;
    }

    public void a(DataInputStream var1) throws IOException {
        this.e = var1.readFloat();
        this.f = var1.readFloat();
        super.a(var1);
    }

    public void a(DataOutputStream var1) throws IOException {
        var1.writeFloat(this.e);
        var1.writeFloat(this.f);
        super.a(var1);
    }

    public int a() {
        return 9;
    }
}
