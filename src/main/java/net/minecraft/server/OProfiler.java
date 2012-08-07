package net.minecraft.server;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OProfiler {

    public static boolean a = false;
    private static List b = new ArrayList();
    private static List c = new ArrayList();
    private static String d = "";
    private static Map e = new HashMap();

    public OProfiler() {
        super();
    }

    public static void a(String var0) {
        if (a) {
            if (d.length() > 0) {
                d = d + ".";
            }

            d = d + var0;
            b.add(d);
            c.add(Long.valueOf(System.nanoTime()));
        }
    }

    public static void a() {
        if (a) {
            long var0 = System.nanoTime();
            long var2 = ((Long) c.remove(c.size() - 1)).longValue();

            b.remove(b.size() - 1);
            long var4 = var0 - var2;

            if (e.containsKey(d)) {
                e.put(d, Long.valueOf(((Long) e.get(d)).longValue() + var4));
            } else {
                e.put(d, Long.valueOf(var4));
            }

            d = b.size() > 0 ? (String) b.get(b.size() - 1) : "";
            if (var4 > 100000000L) {
                System.out.println(d + " " + var4);
            }

        }
    }

    public static void b(String var0) {
        a();
        a(var0);
    }

}
