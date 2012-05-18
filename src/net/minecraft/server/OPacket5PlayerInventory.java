package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.OItemStack;
import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket5PlayerInventory extends OPacket {

    public int a;
    public int b;
    public int c;
    public int d;

    public OPacket5PlayerInventory() {
        super();
    }

    public OPacket5PlayerInventory(int var1, int var2, OItemStack var3) {
        super();
        this.a = var1;
        this.b = var2;
        if (var3 == null) {
            this.c = -1;
            this.d = 0;
        } else {
            this.c = var3.c;
            this.d = var3.h();
        }

    }

    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readInt();
        this.b = var1.readShort();
        this.c = var1.readShort();
        this.d = var1.readShort();
    }

    public void a(DataOutputStream var1) throws IOException {
        var1.writeInt(this.a);
        var1.writeShort(this.b);
        var1.writeShort(this.c);
        var1.writeShort(this.d);
    }

    public void a(ONetHandler var1) {
        var1.a(this);
    }

    public int a() {
        return 8;
    }
}
