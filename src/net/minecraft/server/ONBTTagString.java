package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.server.ONBTBase;

public class ONBTTagString extends ONBTBase {

    public String a;

    public ONBTTagString(String var1) {
        super(var1);
    }

    public ONBTTagString(String var1, String var2) {
        super(var1);
        this.a = var2;
        if (var2 == null) {
            throw new IllegalArgumentException("Empty string not allowed");
        }
    }

    void a(DataOutput var1) throws IOException {
        var1.writeUTF(this.a);
    }

    void a(DataInput var1) throws IOException {
        this.a = var1.readUTF();
    }

    public byte a() {
        return (byte) 8;
    }

    public String toString() {
        return "" + this.a;
    }

    public ONBTBase b() {
        return new ONBTTagString(this.c(), this.a);
    }

    public boolean equals(Object var1) {
        if (!super.equals(var1)) {
            return false;
        } else {
            ONBTTagString var2 = (ONBTTagString) var1;
            return this.a == null && var2.a == null || this.a != null && this.a.equals(var2.a);
        }
    }

    public int hashCode() {
        return super.hashCode() ^ this.a.hashCode();
    }
}
