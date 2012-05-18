package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.OEntity;
import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket39AttachEntity extends OPacket {

    public int a;
    public int b;

    public OPacket39AttachEntity() {
        super();
    }

    public OPacket39AttachEntity(OEntity var1, OEntity var2) {
        super();
        this.a = var1.bd;
        this.b = var2 != null ? var2.bd : -1;
    }

    public int a() {
        return 8;
    }

    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readInt();
        this.b = var1.readInt();
    }

    public void a(DataOutputStream var1) throws IOException {
        var1.writeInt(this.a);
        var1.writeInt(this.b);
    }

    public void a(ONetHandler var1) {
        var1.a(this);
    }
}
