package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.OEntityItem;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket21PickupSpawn extends OPacket {

    public int a;
    public int b;
    public int c;
    public int d;
    public byte e;
    public byte f;
    public byte g;
    public int h;
    public int i;
    public int q;

    public OPacket21PickupSpawn() {
        super();
    }

    public OPacket21PickupSpawn(OEntityItem var1) {
        super();
        this.a = var1.bd;
        this.h = var1.a.c;
        this.i = var1.a.a;
        this.q = var1.a.h();
        this.b = OMathHelper.b(var1.bm * 32.0D);
        this.c = OMathHelper.b(var1.bn * 32.0D);
        this.d = OMathHelper.b(var1.bo * 32.0D);
        this.e = (byte) ((int) (var1.bp * 128.0D));
        this.f = (byte) ((int) (var1.bq * 128.0D));
        this.g = (byte) ((int) (var1.br * 128.0D));
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readInt();
        this.h = var1.readShort();
        this.i = var1.readByte();
        this.q = var1.readShort();
        this.b = var1.readInt();
        this.c = var1.readInt();
        this.d = var1.readInt();
        this.e = var1.readByte();
        this.f = var1.readByte();
        this.g = var1.readByte();
    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        var1.writeInt(this.a);
        var1.writeShort(this.h);
        var1.writeByte(this.i);
        var1.writeShort(this.q);
        var1.writeInt(this.b);
        var1.writeInt(this.c);
        var1.writeInt(this.d);
        var1.writeByte(this.e);
        var1.writeByte(this.f);
        var1.writeByte(this.g);
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
