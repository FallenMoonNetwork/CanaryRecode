package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import net.minecraft.server.OChunk;
import net.minecraft.server.OExtendedBlockStorage;
import net.minecraft.server.ONetHandler;
import net.minecraft.server.ONibbleArray;
import net.minecraft.server.OPacket;

public class OPacket51MapChunk extends OPacket {

    public int a;
    public int b;
    public int c;
    public int d;
    public byte[] e;
    public boolean f;
    private int g;
    private int h;
    private static byte[] i = new byte[0];

    public OPacket51MapChunk() {
        super();
        this.p = true;
    }

    public OPacket51MapChunk(OChunk var1, boolean var2, int var3) {
        super();
        this.p = true;
        this.a = var1.g;
        this.b = var1.h;
        this.f = var2;
        if (var2) {
            var3 = '\uffff';
            var1.o = true;
        }

        OExtendedBlockStorage[] var4 = var1.h();
        int var5 = 0;
        int var6 = 0;

        int var7;
        for (var7 = 0; var7 < var4.length; ++var7) {
            if (var4[var7] != null && (!var2 || !var4[var7].a()) && (var3 & 1 << var7) != 0) {
                this.c |= 1 << var7;
                ++var5;
                if (var4[var7].h() != null) {
                    this.d |= 1 << var7;
                    ++var6;
                }
            }
        }

        var7 = 2048 * (5 * var5 + var6);
        if (var2) {
            var7 += 256;
        }

        if (i.length < var7) {
            i = new byte[var7];
        }

        byte[] var8 = i;
        int var9 = 0;

        int var10;
        for (var10 = 0; var10 < var4.length; ++var10) {
            if (var4[var10] != null && (!var2 || !var4[var10].a()) && (var3 & 1 << var10) != 0) {
                byte[] var11 = var4[var10].g();
                System.arraycopy(var11, 0, var8, var9, var11.length);
                var9 += var11.length;
            }
        }

        ONibbleArray var16;
        for (var10 = 0; var10 < var4.length; ++var10) {
            if (var4[var10] != null && (!var2 || !var4[var10].a()) && (var3 & 1 << var10) != 0) {
                var16 = var4[var10].i();
                System.arraycopy(var16.a, 0, var8, var9, var16.a.length);
                var9 += var16.a.length;
            }
        }

        for (var10 = 0; var10 < var4.length; ++var10) {
            if (var4[var10] != null && (!var2 || !var4[var10].a()) && (var3 & 1 << var10) != 0) {
                var16 = var4[var10].j();
                System.arraycopy(var16.a, 0, var8, var9, var16.a.length);
                var9 += var16.a.length;
            }
        }

        for (var10 = 0; var10 < var4.length; ++var10) {
            if (var4[var10] != null && (!var2 || !var4[var10].a()) && (var3 & 1 << var10) != 0) {
                var16 = var4[var10].k();
                System.arraycopy(var16.a, 0, var8, var9, var16.a.length);
                var9 += var16.a.length;
            }
        }

        if (var6 > 0) {
            for (var10 = 0; var10 < var4.length; ++var10) {
                if (var4[var10] != null && (!var2 || !var4[var10].a()) && var4[var10].h() != null && (var3 & 1 << var10) != 0) {
                    var16 = var4[var10].h();
                    System.arraycopy(var16.a, 0, var8, var9, var16.a.length);
                    var9 += var16.a.length;
                }
            }
        }

        if (var2) {
            byte[] var18 = var1.l();
            System.arraycopy(var18, 0, var8, var9, var18.length);
            var9 += var18.length;
        }

        Deflater var17 = new Deflater(-1);
        boolean var14 = false;

        try {
            var14 = true;
            var17.setInput(var8, 0, var9);
            var17.finish();
            this.e = new byte[var9];
            this.g = var17.deflate(this.e);
            var14 = false;
        } finally {
            if (var14) {
                var17.end();
            }
        }

        var17.end();
    }

    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readInt();
        this.b = var1.readInt();
        this.f = var1.readBoolean();
        this.c = var1.readShort();
        this.d = var1.readShort();
        this.g = var1.readInt();
        this.h = var1.readInt();
        if (i.length < this.g) {
            i = new byte[this.g];
        }

        var1.readFully(i, 0, this.g);
        int var2 = 0;

        int var3;
        for (var3 = 0; var3 < 16; ++var3) {
            var2 += this.c >> var3 & 1;
        }

        var3 = 12288 * var2;
        if (this.f) {
            var3 += 256;
        }

        this.e = new byte[var3];
        Inflater var4 = new Inflater();
        var4.setInput(i, 0, this.g);
        boolean var9 = false;

        try {
            var9 = true;
            var4.inflate(this.e);
            var9 = false;
        } catch (DataFormatException var10) {
            throw new IOException("Bad compressed data format");
        } finally {
            if (var9) {
                var4.end();
            }
        }

        var4.end();
    }

    public void a(DataOutputStream var1) throws IOException {
        var1.writeInt(this.a);
        var1.writeInt(this.b);
        var1.writeBoolean(this.f);
        var1.writeShort((short) (this.c & '\uffff'));
        var1.writeShort((short) (this.d & '\uffff'));
        var1.writeInt(this.g);
        var1.writeInt(this.h);
        var1.write(this.e, 0, this.g);
    }

    public void a(ONetHandler var1) {
        var1.a(this);
    }

    public int a() {
        return 17 + this.g;
    }

}
