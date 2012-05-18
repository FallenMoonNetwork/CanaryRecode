package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.OEntity;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket34EntityTeleport extends OPacket {

    public int a;
    public int b;
    public int c;
    public int d;
    public byte e;
    public byte f;

    public OPacket34EntityTeleport() {
        super();
    }

    public OPacket34EntityTeleport(OEntity var1) {
        super();
        this.a = var1.bd;
        this.b = OMathHelper.b(var1.bm * 32.0D);
        this.c = OMathHelper.b(var1.bn * 32.0D);
        this.d = OMathHelper.b(var1.bo * 32.0D);
        this.e = (byte) ((int) (var1.bs * 256.0F / 360.0F));
        this.f = (byte) ((int) (var1.bt * 256.0F / 360.0F));
    }

    public OPacket34EntityTeleport(int var1, int var2, int var3, int var4, byte var5, byte var6) {
        super();
        this.a = var1;
        this.b = var2;
        this.c = var3;
        this.d = var4;
        this.e = var5;
        this.f = var6;
    }

    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readInt();
        this.b = var1.readInt();
        this.c = var1.readInt();
        this.d = var1.readInt();
        this.e = (byte) var1.read();
        this.f = (byte) var1.read();
    }

    public void a(DataOutputStream var1) throws IOException {
        var1.writeInt(this.a);
        var1.writeInt(this.b);
        var1.writeInt(this.c);
        var1.writeInt(this.d);
        var1.write(this.e);
        var1.write(this.f);
    }

    public void a(ONetHandler var1) {
        var1.a(this);
    }

    public int a() {
        return 34;
    }
}
