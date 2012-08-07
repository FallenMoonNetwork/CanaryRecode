package net.minecraft.server;

import java.util.HashMap;
import java.util.Map;

public class OPacketCount {

    public static boolean a = true;
    private static final Map b = new HashMap();
    private static final Map c = new HashMap();
    private static final Object d = new Object();

    public OPacketCount() {
        super();
    }

    public static void a(int var0, long var1) {
        if (a) {
            Object var3 = d;
            synchronized (d) {
                if (b.containsKey(Integer.valueOf(var0))) {
                    b.put(Integer.valueOf(var0), Long.valueOf(((Long) b.get(Integer.valueOf(var0))).longValue() + 1L));
                    c.put(Integer.valueOf(var0), Long.valueOf(((Long) c.get(Integer.valueOf(var0))).longValue() + var1));
                } else {
                    b.put(Integer.valueOf(var0), Long.valueOf(1L));
                    c.put(Integer.valueOf(var0), Long.valueOf(var1));
                }

            }
        }
    }

}
