package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.OEntityPainting;
import net.minecraft.server.OEnumArt;
import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket25EntityPainting extends OPacket {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public String f;

    public OPacket25EntityPainting() {
        super();
    }

    public OPacket25EntityPainting(OEntityPainting var1) {
        super();
        this.a = var1.bd;
        this.b = var1.b;
        this.c = var1.c;
        this.d = var1.d;
        this.e = var1.a;
        this.f = var1.e.A;
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readInt();
        this.f = a(var1, OEnumArt.z);
        this.b = var1.readInt();
        this.c = var1.readInt();
        this.d = var1.readInt();
        this.e = var1.readInt();
    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        var1.writeInt(this.a);
        a(this.f, var1);
        var1.writeInt(this.b);
        var1.writeInt(this.c);
        var1.writeInt(this.d);
        var1.writeInt(this.e);
    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
    }

    @Override
    public int a() {
        return 24;
    }
}
