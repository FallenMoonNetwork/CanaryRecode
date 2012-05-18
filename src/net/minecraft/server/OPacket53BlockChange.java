package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;
import net.minecraft.server.OWorld;

public class OPacket53BlockChange extends OPacket {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;

    public OPacket53BlockChange() {
        super();
        this.p = true;
    }

    public OPacket53BlockChange(int var1, int var2, int var3, OWorld var4) {
        super();
        this.p = true;
        this.a = var1;
        this.b = var2;
        this.c = var3;
        this.d = var4.a(var1, var2, var3);
        this.e = var4.c(var1, var2, var3);
    }

    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readInt();
        this.b = var1.read();
        this.c = var1.readInt();
        this.d = var1.read();
        this.e = var1.read();
    }

    public void a(DataOutputStream var1) throws IOException {
        var1.writeInt(this.a);
        var1.write(this.b);
        var1.writeInt(this.c);
        var1.write(this.d);
        var1.write(this.e);
    }

    public void a(ONetHandler var1) {
        var1.a(this);
    }

    public int a() {
        return 11;
    }
}
