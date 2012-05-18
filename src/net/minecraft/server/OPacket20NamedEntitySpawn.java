package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket20NamedEntitySpawn extends OPacket {

    public int a;
    public String b;
    public int c;
    public int d;
    public int e;
    public byte f;
    public byte g;
    public int h;

    public OPacket20NamedEntitySpawn() {
        super();
    }

    public OPacket20NamedEntitySpawn(OEntityPlayer var1) {
        super();
        this.a = var1.bd;
        this.b = var1.v;
        this.c = OMathHelper.b(var1.bm * 32.0D);
        this.d = OMathHelper.b(var1.bn * 32.0D);
        this.e = OMathHelper.b(var1.bo * 32.0D);
        this.f = (byte) ((int) (var1.bs * 256.0F / 360.0F));
        this.g = (byte) ((int) (var1.bt * 256.0F / 360.0F));
        OItemStack var2 = var1.k.d();
        this.h = var2 == null ? 0 : var2.c;
    }

    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readInt();
        this.b = a(var1, 16);
        this.c = var1.readInt();
        this.d = var1.readInt();
        this.e = var1.readInt();
        this.f = var1.readByte();
        this.g = var1.readByte();
        this.h = var1.readShort();
    }

    public void a(DataOutputStream var1) throws IOException {
        var1.writeInt(this.a);
        a(this.b, var1);
        var1.writeInt(this.c);
        var1.writeInt(this.d);
        var1.writeInt(this.e);
        var1.writeByte(this.f);
        var1.writeByte(this.g);
        var1.writeShort(this.h);
    }

    public void a(ONetHandler var1) {
        var1.a(this);
    }

    public int a() {
        return 28;
    }
}
