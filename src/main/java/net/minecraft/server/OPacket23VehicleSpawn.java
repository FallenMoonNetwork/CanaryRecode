package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.OEntity;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket23VehicleSpawn extends OPacket {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;

    public OPacket23VehicleSpawn() {
        super();
    }

    public OPacket23VehicleSpawn(OEntity var1, int var2) {
        this(var1, var2, 0);
    }

    public OPacket23VehicleSpawn(OEntity var1, int var2, int var3) {
        super();
        this.a = var1.bd;
        this.b = OMathHelper.b(var1.bm * 32.0D);
        this.c = OMathHelper.b(var1.bn * 32.0D);
        this.d = OMathHelper.b(var1.bo * 32.0D);
        this.h = var2;
        this.i = var3;
        if (var3 > 0) {
            double var4 = var1.bp;
            double var6 = var1.bq;
            double var8 = var1.br;
            double var10 = 3.9D;
            if (var4 < -var10) {
                var4 = -var10;
            }

            if (var6 < -var10) {
                var6 = -var10;
            }

            if (var8 < -var10) {
                var8 = -var10;
            }

            if (var4 > var10) {
                var4 = var10;
            }

            if (var6 > var10) {
                var6 = var10;
            }

            if (var8 > var10) {
                var8 = var10;
            }

            this.e = (int) (var4 * 8000.0D);
            this.f = (int) (var6 * 8000.0D);
            this.g = (int) (var8 * 8000.0D);
        }

    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readInt();
        this.h = var1.readByte();
        this.b = var1.readInt();
        this.c = var1.readInt();
        this.d = var1.readInt();
        this.i = var1.readInt();
        if (this.i > 0) {
            this.e = var1.readShort();
            this.f = var1.readShort();
            this.g = var1.readShort();
        }

    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        var1.writeInt(this.a);
        var1.writeByte(this.h);
        var1.writeInt(this.b);
        var1.writeInt(this.c);
        var1.writeInt(this.d);
        var1.writeInt(this.i);
        if (this.i > 0) {
            var1.writeShort(this.e);
            var1.writeShort(this.f);
            var1.writeShort(this.g);
        }

    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
    }

    @Override
    public int a() {
        return 21 + this.i > 0 ? 6 : 0;
    }
}
