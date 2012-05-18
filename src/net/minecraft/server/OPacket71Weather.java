package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityLightningBolt;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket71Weather extends OPacket {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;

    public OPacket71Weather() {
        super();
    }

    public OPacket71Weather(OEntity var1) {
        super();
        this.a = var1.bd;
        this.b = OMathHelper.b(var1.bm * 32.0D);
        this.c = OMathHelper.b(var1.bn * 32.0D);
        this.d = OMathHelper.b(var1.bo * 32.0D);
        if (var1 instanceof OEntityLightningBolt) {
            this.e = 1;
        }

    }

    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readInt();
        this.e = var1.readByte();
        this.b = var1.readInt();
        this.c = var1.readInt();
        this.d = var1.readInt();
    }

    public void a(DataOutputStream var1) throws IOException {
        var1.writeInt(this.a);
        var1.writeByte(this.e);
        var1.writeInt(this.b);
        var1.writeInt(this.c);
        var1.writeInt(this.d);
    }

    public void a(ONetHandler var1) {
        var1.a(this);
    }

    public int a() {
        return 17;
    }
}
