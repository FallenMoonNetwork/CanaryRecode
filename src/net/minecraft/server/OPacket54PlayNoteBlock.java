package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket54PlayNoteBlock extends OPacket {

    public int a;
    public int b;
    public int c;
    public int d;
    public int e;

    public OPacket54PlayNoteBlock() {
        super();
    }

    public OPacket54PlayNoteBlock(int var1, int var2, int var3, int var4, int var5) {
        super();
        this.a = var1;
        this.b = var2;
        this.c = var3;
        this.d = var4;
        this.e = var5;
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readInt();
        this.b = var1.readShort();
        this.c = var1.readInt();
        this.d = var1.read();
        this.e = var1.read();
    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        var1.writeInt(this.a);
        var1.writeShort(this.b);
        var1.writeInt(this.c);
        var1.write(this.d);
        var1.write(this.e);
    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
    }

    @Override
    public int a() {
        return 12;
    }
}
