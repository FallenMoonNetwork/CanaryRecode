package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import net.minecraft.server.ODataWatcher;
import net.minecraft.server.OEntityList;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket24MobSpawn extends OPacket {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public byte f;
    public byte g;
    public byte h;
    private ODataWatcher i;
    private List q;

    public OPacket24MobSpawn() {
        super();
    }

    public OPacket24MobSpawn(OEntityLiving var1) {
        super();
        this.a = var1.bd;
        this.b = (byte) OEntityList.a(var1);
        this.c = OMathHelper.b(var1.bm * 32.0D);
        this.d = OMathHelper.b(var1.bn * 32.0D);
        this.e = OMathHelper.b(var1.bo * 32.0D);
        this.f = (byte) ((int) (var1.bs * 256.0F / 360.0F));
        this.g = (byte) ((int) (var1.bt * 256.0F / 360.0F));
        this.h = (byte) ((int) (var1.X * 256.0F / 360.0F));
        this.i = var1.aP();
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readInt();
        this.b = var1.readByte() & 255;
        this.c = var1.readInt();
        this.d = var1.readInt();
        this.e = var1.readInt();
        this.f = var1.readByte();
        this.g = var1.readByte();
        this.h = var1.readByte();
        this.q = ODataWatcher.a(var1);
    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        var1.writeInt(this.a);
        var1.writeByte(this.b & 255);
        var1.writeInt(this.c);
        var1.writeInt(this.d);
        var1.writeInt(this.e);
        var1.writeByte(this.f);
        var1.writeByte(this.g);
        var1.writeByte(this.h);
        this.i.a(var1);
    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
    }

    @Override
    public int a() {
        return 20;
    }
}
