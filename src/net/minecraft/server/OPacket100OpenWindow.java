package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket100OpenWindow extends OPacket {

    public int a;
    public int b;
    public String c;
    public int d;

    public OPacket100OpenWindow() {
        super();
    }

    public OPacket100OpenWindow(int var1, int var2, String var3, int var4) {
        super();
        this.a = var1;
        this.b = var2;
        this.c = var3;
        this.d = var4;
    }

    public void a(ONetHandler var1) {
        var1.a(this);
    }

    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readByte() & 255;
        this.b = var1.readByte() & 255;
        this.c = a(var1, 32);
        this.d = var1.readByte() & 255;
    }

    public void a(DataOutputStream var1) throws IOException {
        var1.writeByte(this.a & 255);
        var1.writeByte(this.b & 255);
        a(this.c, var1);
        var1.writeByte(this.d & 255);
    }

    public int a() {
        return 3 + this.c.length();
    }
}
