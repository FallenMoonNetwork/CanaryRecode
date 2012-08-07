package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.canarymod.api.CanaryDataWatcher;
import net.minecraft.server.OChunkCoordinates;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OPacket;
import net.minecraft.server.OWatchableObject;

public class ODataWatcher {

    private static final HashMap a = new HashMap();
    private final Map b = new HashMap();
    private boolean c;
    //CanaryMod datawatcher handler
    private CanaryDataWatcher canaryDataWatcher;

    public ODataWatcher() {
        super();
        canaryDataWatcher = new CanaryDataWatcher(this);
    }

    /**
     * CanaryMod get data watcher handler
     * @return
     */
    public CanaryDataWatcher getCanaryDataWatcher() {
        return canaryDataWatcher;
    }
    public void a(int var1, Object var2) {
        Integer var3 = (Integer) a.get(var2.getClass());
        if (var3 == null) {
            throw new IllegalArgumentException("Unknown data type: " + var2.getClass());
        } else if (var1 > 31) {
            throw new IllegalArgumentException("Data value id is too big with " + var1 + "! (Max is " + 31 + ")");
        } else if (this.b.containsKey(Integer.valueOf(var1))) {
            throw new IllegalArgumentException("Duplicate id value for " + var1 + "!");
        } else {
            OWatchableObject var4 = new OWatchableObject(var3.intValue(), var1, var2);
            this.b.put(Integer.valueOf(var1), var4);
        }
    }

    public byte a(int var1) {
        return ((Byte) ((OWatchableObject) this.b.get(Integer.valueOf(var1))).b()).byteValue();
    }

    public short b(int var1) {
        return ((Short) ((OWatchableObject) this.b.get(Integer.valueOf(var1))).b()).shortValue();
    }

    public int c(int var1) {
        return ((Integer) ((OWatchableObject) this.b.get(Integer.valueOf(var1))).b()).intValue();
    }

    public String d(int var1) {
        return (String) ((OWatchableObject) this.b.get(Integer.valueOf(var1))).b();
    }

    public void b(int var1, Object var2) {
        OWatchableObject var3 = (OWatchableObject) this.b.get(Integer.valueOf(var1));
        if (!var2.equals(var3.b())) {
            var3.a(var2);
            var3.a(true);
            this.c = true;
        }

    }

    public boolean a() {
        return this.c;
    }

    public static void a(List var0, DataOutputStream var1) throws IOException {
        if (var0 != null) {
            Iterator var2 = var0.iterator();

            while (var2.hasNext()) {
                OWatchableObject var3 = (OWatchableObject) var2.next();
                a(var1, var3);
            }
        }

        var1.writeByte(127);
    }

    public ArrayList b() {
        ArrayList var1 = null;
        if (this.c) {
            Iterator var2 = this.b.values().iterator();

            while (var2.hasNext()) {
                OWatchableObject var3 = (OWatchableObject) var2.next();
                if (var3.d()) {
                    var3.a(false);
                    if (var1 == null) {
                        var1 = new ArrayList();
                    }

                    var1.add(var3);
                }
            }
        }

        this.c = false;
        return var1;
    }

    public void a(DataOutputStream var1) throws IOException {
        Iterator var2 = this.b.values().iterator();

        while (var2.hasNext()) {
            OWatchableObject var3 = (OWatchableObject) var2.next();
            a(var1, var3);
        }

        var1.writeByte(127);
    }

    private static void a(DataOutputStream var0, OWatchableObject var1) throws IOException {
        int var2 = (var1.c() << 5 | var1.a() & 31) & 255;
        var0.writeByte(var2);
        switch (var1.c()) {
        case 0:
            var0.writeByte(((Byte) var1.b()).byteValue());
            break;
        case 1:
            var0.writeShort(((Short) var1.b()).shortValue());
            break;
        case 2:
            var0.writeInt(((Integer) var1.b()).intValue());
            break;
        case 3:
            var0.writeFloat(((Float) var1.b()).floatValue());
            break;
        case 4:
            OPacket.a((String) var1.b(), var0);
            break;
        case 5:
            OItemStack var4 = (OItemStack) var1.b();
            var0.writeShort(var4.a().bP);
            var0.writeByte(var4.a);
            var0.writeShort(var4.h());
            break;
        case 6:
            OChunkCoordinates var3 = (OChunkCoordinates) var1.b();
            var0.writeInt(var3.a);
            var0.writeInt(var3.b);
            var0.writeInt(var3.c);
        }

    }

    public static List a(DataInputStream var0) throws IOException {
        ArrayList var1 = null;

        for (byte var2 = var0.readByte(); var2 != 127; var2 = var0.readByte()) {
            if (var1 == null) {
                var1 = new ArrayList();
            }

            int var3 = (var2 & 224) >> 5;
            int var4 = var2 & 31;
            OWatchableObject var5 = null;
            switch (var3) {
            case 0:
                var5 = new OWatchableObject(var3, var4, Byte.valueOf(var0.readByte()));
                break;
            case 1:
                var5 = new OWatchableObject(var3, var4, Short.valueOf(var0.readShort()));
                break;
            case 2:
                var5 = new OWatchableObject(var3, var4, Integer.valueOf(var0.readInt()));
                break;
            case 3:
                var5 = new OWatchableObject(var3, var4, Float.valueOf(var0.readFloat()));
                break;
            case 4:
                var5 = new OWatchableObject(var3, var4, OPacket.a(var0, 64));
                break;
            case 5:
                short var9 = var0.readShort();
                byte var10 = var0.readByte();
                short var11 = var0.readShort();
                var5 = new OWatchableObject(var3, var4, new OItemStack(var9, var10, var11));
                break;
            case 6:
                int var6 = var0.readInt();
                int var7 = var0.readInt();
                int var8 = var0.readInt();
                var5 = new OWatchableObject(var3, var4, new OChunkCoordinates(var6, var7, var8));
            }

            var1.add(var5);
        }

        return var1;
    }

    static {
        a.put(Byte.class, Integer.valueOf(0));
        a.put(Short.class, Integer.valueOf(1));
        a.put(Integer.class, Integer.valueOf(2));
        a.put(Float.class, Integer.valueOf(3));
        a.put(String.class, Integer.valueOf(4));
        a.put(OItemStack.class, Integer.valueOf(5));
        a.put(OChunkCoordinates.class, Integer.valueOf(6));
    }
}
