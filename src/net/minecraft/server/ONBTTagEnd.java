package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import net.minecraft.server.ONBTBase;

public class ONBTTagEnd extends ONBTBase {

    public ONBTTagEnd() {
        super((String) null);
    }

    void a(DataInput var1) {
    }

    void a(DataOutput var1) {
    }

    public byte a() {
        return (byte) 0;
    }

    public String toString() {
        return "END";
    }

    public ONBTBase b() {
        return new ONBTTagEnd();
    }

    public boolean equals(Object var1) {
        return super.equals(var1);
    }
}
