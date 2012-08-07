package net.minecraft.server;


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.server.ONBTTagByte;
import net.minecraft.server.ONBTTagByteArray;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.ONBTTagDouble;
import net.minecraft.server.ONBTTagEnd;
import net.minecraft.server.ONBTTagFloat;
import net.minecraft.server.ONBTTagInt;
import net.minecraft.server.ONBTTagIntArray;
import net.minecraft.server.ONBTTagList;
import net.minecraft.server.ONBTTagLong;
import net.minecraft.server.ONBTTagShort;
import net.minecraft.server.ONBTTagString;


public abstract class ONBTBase {

    private String a;

    abstract void a(DataOutput var1) throws IOException;

    abstract void a(DataInput var1) throws IOException;

    public abstract byte a();

    protected ONBTBase(String var1) {
        super();
        if (var1 == null) {
            this.a = "";
        } else {
            this.a = var1;
        }

    }

    public ONBTBase a(String var1) {
        if (var1 == null) {
            this.a = "";
        } else {
            this.a = var1;
        }

        return this;
    }

    public String c() {
        return this.a == null ? "" : this.a;
    }

    public static ONBTBase b(DataInput var0) throws IOException {
        byte var1 = var0.readByte();

        if (var1 == 0) {
            return new ONBTTagEnd();
        } else {
            String var2 = var0.readUTF();
            ONBTBase var3 = a(var1, var2);

            var3.a(var0);
            return var3;
        }
    }

    public static void a(ONBTBase var0, DataOutput var1) throws IOException {
        var1.writeByte(var0.a());
        if (var0.a() != 0) {
            var1.writeUTF(var0.c());
            var0.a(var1);
        }
    }

    public static ONBTBase a(byte var0, String var1) {
        switch (var0) {
        case 0:
            return new ONBTTagEnd();

        case 1:
            return new ONBTTagByte(var1);

        case 2:
            return new ONBTTagShort(var1);

        case 3:
            return new ONBTTagInt(var1);

        case 4:
            return new ONBTTagLong(var1);

        case 5:
            return new ONBTTagFloat(var1);

        case 6:
            return new ONBTTagDouble(var1);

        case 7:
            return new ONBTTagByteArray(var1);

        case 8:
            return new ONBTTagString(var1);

        case 9:
            return new ONBTTagList(var1);

        case 10:
            return new ONBTTagCompound(var1);

        case 11:
            return new ONBTTagIntArray(var1);

        default:
            return null;
        }
    }

    public static String a(byte var0) {
        switch (var0) {
        case 0:
            return "TAG_End";

        case 1:
            return "TAG_Byte";

        case 2:
            return "TAG_Short";

        case 3:
            return "TAG_Int";

        case 4:
            return "TAG_Long";

        case 5:
            return "TAG_Float";

        case 6:
            return "TAG_Double";

        case 7:
            return "TAG_Byte_Array";

        case 8:
            return "TAG_String";

        case 9:
            return "TAG_List";

        case 10:
            return "TAG_Compound";

        case 11:
            return "TAG_Int_Array";

        default:
            return "UNKNOWN";
        }
    }

    public abstract ONBTBase b();

    @Override
    public boolean equals(Object var1) {
        if (var1 != null && var1 instanceof ONBTBase) {
            ONBTBase var2 = (ONBTBase) var1;

            return this.a() != var2.a() ? false : ((this.a != null || var2.a == null) && (this.a == null || var2.a != null) ? this.a == null || this.a.equals(var2.a) : false);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.a.hashCode() ^ this.a();
    }
}
