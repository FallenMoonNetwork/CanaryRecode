package net.minecraft.server;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import net.minecraft.server.ONBTBase;
import net.minecraft.server.ONBTTagCompound;

public class OCompressedStreamTools {

    public OCompressedStreamTools() {
        super();
    }

    public static ONBTTagCompound a(InputStream var0) throws IOException {
        DataInputStream var1 = new DataInputStream(new BufferedInputStream(new GZIPInputStream(var0)));
        boolean var5 = false;

        ONBTTagCompound var2;
        try {
            var5 = true;
            var2 = a((DataInput) var1);
            var5 = false;
        } finally {
            if (var5) {
                var1.close();
            }
        }

        var1.close();
        return var2;
    }

    public static void a(ONBTTagCompound var0, OutputStream var1) throws IOException {
        DataOutputStream var2 = new DataOutputStream(new GZIPOutputStream(var1));
        boolean var5 = false;

        try {
            var5 = true;
            a(var0, (DataOutput) var2);
            var5 = false;
        } finally {
            if (var5) {
                var2.close();
            }
        }

        var2.close();
    }

    public static ONBTTagCompound a(byte[] var0) throws IOException {
        DataInputStream var1 = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(var0))));
        boolean var5 = false;

        ONBTTagCompound var2;
        try {
            var5 = true;
            var2 = a((DataInput) var1);
            var5 = false;
        } finally {
            if (var5) {
                var1.close();
            }
        }

        var1.close();
        return var2;
    }

    public static byte[] a(ONBTTagCompound var0) throws IOException {
        ByteArrayOutputStream var1 = new ByteArrayOutputStream();
        DataOutputStream var2 = new DataOutputStream(new GZIPOutputStream(var1));
        boolean var5 = false;

        try {
            var5 = true;
            a(var0, (DataOutput) var2);
            var5 = false;
        } finally {
            if (var5) {
                var2.close();
            }
        }

        var2.close();
        return var1.toByteArray();
    }

    public static ONBTTagCompound a(DataInput var0) throws IOException {
        ONBTBase var1 = ONBTBase.b(var0);
        if (var1 instanceof ONBTTagCompound) {
            return (ONBTTagCompound) var1;
        } else {
            throw new IOException("Root tag must be a named compound tag");
        }
    }

    public static void a(ONBTTagCompound var0, DataOutput var1) throws IOException {
        ONBTBase.a(var0, var1);
    }
}
