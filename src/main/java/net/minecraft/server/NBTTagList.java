package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NBTTagList extends NBTBase {

    public List a = new ArrayList(); // CanaryMod: private -> public
    private byte c;

    public NBTTagList() {
        super("");
    }

    public NBTTagList(String s0) {
        super(s0);
    }

    void a(DataOutput dataoutput) throws IOException {
        if (!this.a.isEmpty()) {
            this.c = ((NBTBase) this.a.get(0)).a();
        } else {
            this.c = 1;
        }

        dataoutput.writeByte(this.c);
        dataoutput.writeInt(this.a.size());

        for (int i0 = 0; i0 < this.a.size(); ++i0) {
            ((NBTBase) this.a.get(i0)).a(dataoutput);
        }
    }

    void a(DataInput datainput, int i0) throws IOException {
        if (i0 > 512) {
            throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
        } else {
            this.c = datainput.readByte();
            int i1 = datainput.readInt();

            this.a = new ArrayList();

            for (int i2 = 0; i2 < i1; ++i2) {
                NBTBase nbtbase = NBTBase.a(this.c, (String) null);

                nbtbase.a(datainput, i0 + 1);
                this.a.add(nbtbase);
            }
        }
    }

    public byte a() {
        return (byte) 9;
    }

    public String toString() {
        return "" + this.a.size() + " entries of type " + NBTBase.a(this.c);
    }

    public void a(NBTBase nbtbase) {
        this.c = nbtbase.a();
        this.a.add(nbtbase);
    }

    public NBTBase b(int i0) {
        return (NBTBase) this.a.get(i0);
    }

    public int c() {
        return this.a.size();
    }

    public NBTBase b() {
        NBTTagList nbttaglist = new NBTTagList(this.e());

        nbttaglist.c = this.c;
        Iterator iterator = this.a.iterator();

        while (iterator.hasNext()) {
            NBTBase nbtbase = (NBTBase) iterator.next();
            NBTBase nbtbase1 = nbtbase.b();

            nbttaglist.a.add(nbtbase1);
        }

        return nbttaglist;
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagList nbttaglist = (NBTTagList) object;

            if (this.c == nbttaglist.c) {
                return this.a.equals(nbttaglist.a);
            }
        }

        return false;
    }

    public int hashCode() {
        return super.hashCode() ^ this.a.hashCode();
    }
}
