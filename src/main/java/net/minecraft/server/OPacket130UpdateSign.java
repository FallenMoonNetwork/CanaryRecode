package net.minecraft.server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;


public class OPacket130UpdateSign extends OPacket {

    public int a;
    public int b;
    public int c;
    public String[] d;

    public OPacket130UpdateSign() {
        super();
        this.p = true;
    }

    public OPacket130UpdateSign(int var1, int var2, int var3, String[] var4) {
        super();
        this.p = true;
        this.a = var1;
        this.b = var2;
        this.c = var3;
        this.d = var4;
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readInt();
        this.b = var1.readShort();
        this.c = var1.readInt();
        this.d = new String[4];

        for (int var2 = 0; var2 < 4; ++var2) {
            this.d[var2] = a(var1, 15);
        }

    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        var1.writeInt(this.a);
        var1.writeShort(this.b);
        var1.writeInt(this.c);

        for (int var2 = 0; var2 < 4; ++var2) {
            a(this.d[var2], var1);
        }

    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
    }

    @Override
    public int a() {
        int var1 = 0;

        for (int var2 = 0; var2 < 4; ++var2) {
            var1 += this.d[var2].length();
        }

        return var1;
    }
}
