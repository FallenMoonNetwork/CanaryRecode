package net.minecraft.server;


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.server.ONBTBase;


public class ONBTTagInt extends ONBTBase {

    public int a;

    public ONBTTagInt(String var1) {
        super(var1);
    }

    public ONBTTagInt(String var1, int var2) {
        super(var1);
        this.a = var2;
    }

    @Override
    void a(DataOutput var1) throws IOException {
        var1.writeInt(this.a);
    }

    @Override
    void a(DataInput var1) throws IOException {
        this.a = var1.readInt();
    }

    @Override
    public byte a() {
        return (byte) 3;
    }

    @Override
    public String toString() {
        return "" + this.a;
    }

    @Override
    public ONBTBase b() {
        return new ONBTTagInt(this.c(), this.a);
    }

    @Override
    public boolean equals(Object var1) {
        if (super.equals(var1)) {
            ONBTTagInt var2 = (ONBTTagInt) var1;

            return this.a == var2.a;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode() ^ this.a;
    }
}
