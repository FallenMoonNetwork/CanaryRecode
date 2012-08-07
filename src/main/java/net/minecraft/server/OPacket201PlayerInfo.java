package net.minecraft.server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;


public class OPacket201PlayerInfo extends OPacket {

    public String a;
    public boolean b;
    public int c;

    public OPacket201PlayerInfo() {
        super();
    }

    public OPacket201PlayerInfo(String var1, boolean var2, int var3) {
        super();
        this.a = var1;
        this.b = var2;
        this.c = var3;
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = a(var1, 16);
        this.b = var1.readByte() != 0;
        this.c = var1.readShort();
    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        a(this.a, var1);
        var1.writeByte(this.b ? 1 : 0);
        var1.writeShort(this.c);
    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
    }

    @Override
    public int a() {
        return this.a.length() + 2 + 1 + 2;
    }
}
