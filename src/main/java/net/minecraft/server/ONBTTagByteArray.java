package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;
import net.minecraft.server.ONBTBase;

public class ONBTTagByteArray extends ONBTBase {

    public byte[] a;

    public ONBTTagByteArray(String var1) {
        super(var1);
    }

    public ONBTTagByteArray(String var1, byte[] var2) {
        super(var1);
        this.a = var2;
    }

    @Override
    void a(DataOutput var1) throws IOException {
        var1.writeInt(this.a.length);
        var1.write(this.a);
    }

    @Override
    void a(DataInput var1) throws IOException {
        int var2 = var1.readInt();
        this.a = new byte[var2];
        var1.readFully(this.a);
    }

    @Override
    public byte a() {
        return (byte) 7;
    }

    @Override
    public String toString() {
        return "[" + this.a.length + " bytes]";
    }

    @Override
    public ONBTBase b() {
        byte[] var1 = new byte[this.a.length];
        System.arraycopy(this.a, 0, var1, 0, this.a.length);
        return new ONBTTagByteArray(this.c(), var1);
    }

    @Override
    public boolean equals(Object var1) {
        return super.equals(var1) ? Arrays.equals(this.a, ((ONBTTagByteArray) var1).a) : false;
    }

    @Override
    public int hashCode() {
        return super.hashCode() ^ Arrays.hashCode(this.a);
    }
}
