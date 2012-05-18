package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket61DoorChange extends OPacket {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;

    public OPacket61DoorChange() {
        super();
    }

    public OPacket61DoorChange(int var1, int var2, int var3, int var4, int var5) {
        super();
        this.a = var1;
        this.c = var2;
        this.d = var3;
        this.e = var4;
        this.b = var5;
    }

    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readInt();
        this.c = var1.readInt();
        this.d = var1.readByte() & 255;
        this.e = var1.readInt();
        this.b = var1.readInt();
    }

    public void a(DataOutputStream var1) throws IOException {
        var1.writeInt(this.a);
        var1.writeInt(this.c);
        var1.writeByte(this.d & 255);
        var1.writeInt(this.e);
        var1.writeInt(this.b);
    }

    public void a(ONetHandler var1) {
        var1.a(this);
    }

    public int a() {
        return 20;
    }
}
