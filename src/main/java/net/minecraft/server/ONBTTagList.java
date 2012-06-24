package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.server.ONBTBase;

public class ONBTTagList extends ONBTBase {

    private List a = new ArrayList();
    private byte b;

    public ONBTTagList() {
        super("");
    }

    public ONBTTagList(String var1) {
        super(var1);
    }

    @Override
    void a(DataOutput var1) throws IOException {
        if (this.a.size() > 0) {
            this.b = ((ONBTBase) this.a.get(0)).a();
        } else {
            this.b = 1;
        }

        var1.writeByte(this.b);
        var1.writeInt(this.a.size());

        for (int var2 = 0; var2 < this.a.size(); ++var2) {
            ((ONBTBase) this.a.get(var2)).a(var1);
        }

    }

    @Override
    void a(DataInput var1) throws IOException {
        this.b = var1.readByte();
        int var2 = var1.readInt();
        this.a = new ArrayList();

        for (int var3 = 0; var3 < var2; ++var3) {
            ONBTBase var4 = ONBTBase.a(this.b, (String) null);
            var4.a(var1);
            this.a.add(var4);
        }

    }

    @Override
    public byte a() {
        return (byte) 9;
    }

    @Override
    public String toString() {
        return "" + this.a.size() + " entries of type " + ONBTBase.a(this.b);
    }

    public void a(ONBTBase var1) {
        this.b = var1.a();
        this.a.add(var1);
    }

    public ONBTBase a(int var1) {
        return (ONBTBase) this.a.get(var1);
    }

    public int d() {
        return this.a.size();
    }

    @Override
    public ONBTBase b() {
        ONBTTagList var1 = new ONBTTagList(this.c());
        var1.b = this.b;
        Iterator var2 = this.a.iterator();

        while (var2.hasNext()) {
            ONBTBase var3 = (ONBTBase) var2.next();
            ONBTBase var4 = var3.b();
            var1.a.add(var4);
        }

        return var1;
    }

    @Override
    public boolean equals(Object var1) {
        if (super.equals(var1)) {
            ONBTTagList var2 = (ONBTTagList) var1;
            if (this.b == var2.b) {
                return this.a.equals(var2.a);
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode() ^ this.a.hashCode();
    }
}
