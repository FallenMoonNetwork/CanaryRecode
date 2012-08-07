package net.minecraft.server;


import java.io.DataInput;
import java.io.DataOutput;
import net.minecraft.server.ONBTBase;


public class ONBTTagEnd extends ONBTBase {

    public ONBTTagEnd() {
        super((String) null);
    }

    @Override
    void a(DataInput var1) {}

    @Override
    void a(DataOutput var1) {}

    @Override
    public byte a() {
        return (byte) 0;
    }

    @Override
    public String toString() {
        return "END";
    }

    @Override
    public ONBTBase b() {
        return new ONBTTagEnd();
    }

    @Override
    public boolean equals(Object var1) {
        return super.equals(var1);
    }
}
