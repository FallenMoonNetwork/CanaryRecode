package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.OEntity;
import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket28EntityVelocity extends OPacket {

    public int a;
    public int b;
    public int c;
    public int d;

    public OPacket28EntityVelocity() {
        super();
    }

    public OPacket28EntityVelocity(OEntity var1) {
        this(var1.bd, var1.bp, var1.bq, var1.br);
    }

    public OPacket28EntityVelocity(int var1, double var2, double var4, double var6) {
        super();
        this.a = var1;
        double var8 = 3.9D;
        if (var2 < -var8) {
            var2 = -var8;
        }

        if (var4 < -var8) {
            var4 = -var8;
        }

        if (var6 < -var8) {
            var6 = -var8;
        }

        if (var2 > var8) {
            var2 = var8;
        }

        if (var4 > var8) {
            var4 = var8;
        }

        if (var6 > var8) {
            var6 = var8;
        }

        this.b = (int) (var2 * 8000.0D);
        this.c = (int) (var4 * 8000.0D);
        this.d = (int) (var6 * 8000.0D);
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readInt();
        this.b = var1.readShort();
        this.c = var1.readShort();
        this.d = var1.readShort();
    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        var1.writeInt(this.a);
        var1.writeShort(this.b);
        var1.writeShort(this.c);
        var1.writeShort(this.d);
    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
    }

    @Override
    public int a() {
        return 10;
    }
}
