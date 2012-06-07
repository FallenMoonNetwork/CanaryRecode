package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.server.ONBTBase;
import net.minecraft.server.ONBTTagByte;
import net.minecraft.server.ONBTTagByteArray;
import net.minecraft.server.ONBTTagDouble;
import net.minecraft.server.ONBTTagFloat;
import net.minecraft.server.ONBTTagInt;
import net.minecraft.server.ONBTTagIntArray;
import net.minecraft.server.ONBTTagList;
import net.minecraft.server.ONBTTagLong;
import net.minecraft.server.ONBTTagShort;
import net.minecraft.server.ONBTTagString;

public class ONBTTagCompound extends ONBTBase {

    private Map a = new HashMap();

    public ONBTTagCompound() {
        super("");
    }

    public ONBTTagCompound(String var1) {
        super(var1);
    }

    @Override
    void a(DataOutput var1) throws IOException {
        Iterator var2 = this.a.values().iterator();

        while (var2.hasNext()) {
            ONBTBase var3 = (ONBTBase) var2.next();
            ONBTBase.a(var3, var1);
        }

        var1.writeByte(0);
    }

    @Override
    void a(DataInput var1) throws IOException {
        this.a.clear();

        ONBTBase var2;
        while ((var2 = ONBTBase.b(var1)).a() != 0) {
            this.a.put(var2.c(), var2);
        }

    }

    public Collection d() {
        return this.a.values();
    }

    @Override
    public byte a() {
        return (byte) 10;
    }

    public void a(String var1, ONBTBase var2) {
        this.a.put(var1, var2.a(var1));
    }

    public void a(String var1, byte var2) {
        this.a.put(var1, new ONBTTagByte(var1, var2));
    }

    public void a(String var1, short var2) {
        this.a.put(var1, new ONBTTagShort(var1, var2));
    }

    public void a(String var1, int var2) {
        this.a.put(var1, new ONBTTagInt(var1, var2));
    }

    public void a(String var1, long var2) {
        this.a.put(var1, new ONBTTagLong(var1, var2));
    }

    public void a(String var1, float var2) {
        this.a.put(var1, new ONBTTagFloat(var1, var2));
    }

    public void a(String var1, double var2) {
        this.a.put(var1, new ONBTTagDouble(var1, var2));
    }

    public void a(String var1, String var2) {
        this.a.put(var1, new ONBTTagString(var1, var2));
    }

    public void a(String var1, byte[] var2) {
        this.a.put(var1, new ONBTTagByteArray(var1, var2));
    }

    public void a(String var1, int[] var2) {
        this.a.put(var1, new ONBTTagIntArray(var1, var2));
    }

    public void a(String var1, ONBTTagCompound var2) {
        this.a.put(var1, var2.a(var1));
    }

    public void a(String var1, boolean var2) {
        this.a(var1, (byte) (var2 ? 1 : 0));
    }

    public ONBTBase b(String var1) {
        return (ONBTBase) this.a.get(var1);
    }

    public boolean c(String var1) {
        return this.a.containsKey(var1);
    }

    public byte d(String var1) {
        return !this.a.containsKey(var1) ? 0 : ((ONBTTagByte) this.a.get(var1)).a;
    }

    public short e(String var1) {
        return !this.a.containsKey(var1) ? 0 : ((ONBTTagShort) this.a.get(var1)).a;
    }

    public int f(String var1) {
        return !this.a.containsKey(var1) ? 0 : ((ONBTTagInt) this.a.get(var1)).a;
    }

    public long g(String var1) {
        return !this.a.containsKey(var1) ? 0L : ((ONBTTagLong) this.a.get(var1)).a;
    }

    public float h(String var1) {
        return !this.a.containsKey(var1) ? 0.0F : ((ONBTTagFloat) this.a.get(var1)).a;
    }

    public double i(String var1) {
        return !this.a.containsKey(var1) ? 0.0D : ((ONBTTagDouble) this.a.get(var1)).a;
    }

    public String j(String var1) {
        return !this.a.containsKey(var1) ? "" : ((ONBTTagString) this.a.get(var1)).a;
    }

    public byte[] k(String var1) {
        return !this.a.containsKey(var1) ? new byte[0] : ((ONBTTagByteArray) this.a.get(var1)).a;
    }

    public int[] l(String var1) {
        return !this.a.containsKey(var1) ? new int[0] : ((ONBTTagIntArray) this.a.get(var1)).a;
    }

    public ONBTTagCompound m(String var1) {
        return !this.a.containsKey(var1) ? new ONBTTagCompound(var1) : (ONBTTagCompound) this.a.get(var1);
    }

    public ONBTTagList n(String var1) {
        return !this.a.containsKey(var1) ? new ONBTTagList(var1) : (ONBTTagList) this.a.get(var1);
    }

    public boolean o(String var1) {
        return this.d(var1) != 0;
    }

    @Override
    public String toString() {
        return "" + this.a.size() + " entries";
    }

    @Override
    public ONBTBase b() {
        ONBTTagCompound var1 = new ONBTTagCompound(this.c());
        Iterator var2 = this.a.keySet().iterator();

        while (var2.hasNext()) {
            String var3 = (String) var2.next();
            var1.a(var3, ((ONBTBase) this.a.get(var3)).b());
        }

        return var1;
    }

    @Override
    public boolean equals(Object var1) {
        if (super.equals(var1)) {
            ONBTTagCompound var2 = (ONBTTagCompound) var1;
            return this.a.entrySet().equals(var2.a.entrySet());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode() ^ this.a.hashCode();
    }
}
