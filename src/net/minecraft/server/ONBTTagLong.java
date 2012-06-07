package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.server.ONBTBase;

public class ONBTTagLong extends ONBTBase {

    public long a;

    public ONBTTagLong(String var1) {
        super(var1);
    }

    public ONBTTagLong(String var1, long var2) {
        super(var1);
        this.a = var2;
    }

    @Override
    void a(DataOutput var1) throws IOException {
        var1.writeLong(this.a);
    }

    @Override
    void a(DataInput var1) throws IOException {
        this.a = var1.readLong();
    }

    @Override
    public byte a() {
        return (byte) 4;
    }

    @Override
    public String toString() {
        return "" + this.a;
    }

    @Override
    public ONBTBase b() {
        return new ONBTTagLong(this.c(), this.a);
    }

    @Override
    public boolean equals(Object var1) {
        if (super.equals(var1)) {
            ONBTTagLong var2 = (ONBTTagLong) var1;
            return this.a == var2.a;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode() ^ (int) (this.a ^ this.a >>> 32);
    }
}
