package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.server.ONBTBase;

public class ONBTTagShort extends ONBTBase {

    public short a;

    public ONBTTagShort(String var1) {
        super(var1);
    }

    public ONBTTagShort(String var1, short var2) {
        super(var1);
        this.a = var2;
    }

    void a(DataOutput var1) throws IOException {
        var1.writeShort(this.a);
    }

    void a(DataInput var1) throws IOException {
        this.a = var1.readShort();
    }

    public byte a() {
        return (byte) 2;
    }

    public String toString() {
        return "" + this.a;
    }

    public ONBTBase b() {
        return new ONBTTagShort(this.c(), this.a);
    }

    public boolean equals(Object var1) {
        if (super.equals(var1)) {
            ONBTTagShort var2 = (ONBTTagShort) var1;
            return this.a == var2.a;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return super.hashCode() ^ this.a;
    }
}
