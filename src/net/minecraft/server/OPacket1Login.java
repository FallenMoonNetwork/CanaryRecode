package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;
import net.minecraft.server.OWorldType;

public class OPacket1Login extends OPacket {

    public int a;
    public String b;
    public OWorldType c;
    public int d;
    public int e;
    public byte f;
    public byte g;
    public byte h;

    public OPacket1Login() {
        super();
    }

    public OPacket1Login(String var1, int var2, OWorldType var3, int var4, int var5, byte var6, byte var7, byte var8) {
        super();
        this.b = var1;
        this.a = var2;
        this.c = var3;
        this.e = var5;
        this.f = var6;
        this.d = var4;
        this.g = var7;
        this.h = var8;
    }

    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readInt();
        this.b = a(var1, 16);
        String var2 = a(var1, 16);
        this.c = OWorldType.a(var2);
        if (this.c == null) {
            this.c = OWorldType.b;
        }

        this.d = var1.readInt();
        this.e = var1.readInt();
        this.f = var1.readByte();
        this.g = var1.readByte();
        this.h = var1.readByte();
    }

    public void a(DataOutputStream var1) throws IOException {
        var1.writeInt(this.a);
        a(this.b, var1);
        if (this.c == null) {
            a("", var1);
        } else {
            a(this.c.a(), var1);
        }

        var1.writeInt(this.d);
        var1.writeInt(this.e);
        var1.writeByte(this.f);
        var1.writeByte(this.g);
        var1.writeByte(this.h);
    }

    public void a(ONetHandler var1) {
        var1.a(this);
    }

    public int a() {
        int var1 = 0;
        if (this.c != null) {
            var1 = this.c.a().length();
        }

        return 4 + this.b.length() + 4 + 7 + 7 + var1;
    }
}
