package net.minecraft.server;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.server.OChunk;
import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;
import net.minecraft.server.OWorld;

public class OPacket52MultiBlockChange extends OPacket {

    public int a;
    public int b;
    public byte[] c;
    public int d;
    private static byte[] e = new byte[0];

    public OPacket52MultiBlockChange() {
        super();
        this.p = true;
    }

    public OPacket52MultiBlockChange(int var1, int var2, short[] var3, int var4, OWorld var5) {
        super();
        this.p = true;
        this.a = var1;
        this.b = var2;
        this.d = var4;
        int var6 = 4 * var4;
        OChunk var7 = var5.d(var1, var2);

        try {
            if (var4 >= 64) {
                System.out.println("ChunkTilesUpdatePacket compress " + var4);
                if (e.length < var6) {
                    e = new byte[var6];
                }
            } else {
                ByteArrayOutputStream var8 = new ByteArrayOutputStream(var6);
                DataOutputStream var9 = new DataOutputStream(var8);

                for (int var10 = 0; var10 < var4; ++var10) {
                    int var11 = var3[var10] >> 12 & 15;
                    int var12 = var3[var10] >> 8 & 15;
                    int var13 = var3[var10] & 255;
                    var9.writeShort(var3[var10]);
                    var9.writeShort((short) ((var7.a(var11, var13, var12) & 4095) << 4 | var7.c(var11, var13, var12) & 15));
                }

                this.c = var8.toByteArray();
                if (this.c.length != var6) {
                    throw new RuntimeException("Expected length " + var6 + " doesn\'t match received length " + this.c.length);
                }
            }
        } catch (IOException var14) {
            System.err.println(var14.getMessage());
            this.c = null;
        }

    }

    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readInt();
        this.b = var1.readInt();
        this.d = var1.readShort() & '\uffff';
        int var2 = var1.readInt();
        if (var2 > 0) {
            this.c = new byte[var2];
            var1.readFully(this.c);
        }

    }

    public void a(DataOutputStream var1) throws IOException {
        var1.writeInt(this.a);
        var1.writeInt(this.b);
        var1.writeShort((short) this.d);
        if (this.c != null) {
            var1.writeInt(this.c.length);
            var1.write(this.c);
        } else {
            var1.writeInt(0);
        }

    }

    public void a(ONetHandler var1) {
        var1.a(this);
    }

    public int a() {
        return 10 + this.d * 4;
    }

}
