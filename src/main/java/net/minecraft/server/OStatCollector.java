package net.minecraft.server;


import net.minecraft.server.OStringTranslate;


public class OStatCollector {

    private static OStringTranslate a = OStringTranslate.a();

    public OStatCollector() {
        super();
    }

    public static String a(String var0) {
        return a.b(var0);
    }

    public static String a(String var0, Object... var1) {
        return a.a(var0, var1);
    }

}
