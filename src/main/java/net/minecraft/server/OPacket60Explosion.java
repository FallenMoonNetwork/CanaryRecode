package net.minecraft.server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import net.minecraft.server.OChunkPosition;
import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;


public class OPacket60Explosion extends OPacket {

    public double a;
    public double b;
    public double c;
    public float d;
    public Set e;

    public OPacket60Explosion() {
        super();
    }

    public OPacket60Explosion(double var1, double var3, double var5, float var7, Set var8) {
        super();
        this.a = var1;
        this.b = var3;
        this.c = var5;
        this.d = var7;
        this.e = new HashSet(var8);
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readDouble();
        this.b = var1.readDouble();
        this.c = var1.readDouble();
        this.d = var1.readFloat();
        int var2 = var1.readInt();

        this.e = new HashSet();
        int var3 = (int) this.a;
        int var4 = (int) this.b;
        int var5 = (int) this.c;

        for (int var6 = 0; var6 < var2; ++var6) {
            int var7 = var1.readByte() + var3;
            int var8 = var1.readByte() + var4;
            int var9 = var1.readByte() + var5;

            this.e.add(new OChunkPosition(var7, var8, var9));
        }

    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        var1.writeDouble(this.a);
        var1.writeDouble(this.b);
        var1.writeDouble(this.c);
        var1.writeFloat(this.d);
        var1.writeInt(this.e.size());
        int var2 = (int) this.a;
        int var3 = (int) this.b;
        int var4 = (int) this.c;
        Iterator var5 = this.e.iterator();

        while (var5.hasNext()) {
            OChunkPosition var6 = (OChunkPosition) var5.next();
            int var7 = var6.a - var2;
            int var8 = var6.b - var3;
            int var9 = var6.c - var4;

            var1.writeByte(var7);
            var1.writeByte(var8);
            var1.writeByte(var9);
        }

    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
    }

    @Override
    public int a() {
        return 32 + this.e.size() * 3;
    }
}
