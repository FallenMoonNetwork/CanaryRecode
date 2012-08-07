package net.minecraft.server;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.server.OPlayerUsageSnooperThread;

public class OPlayerUsageSnooper {

    private Map a = new HashMap();
    private final URL b;

    public OPlayerUsageSnooper(String var1) {
        super();

        try {
            this.b = new URL("http://snoop.minecraft.net/" + var1);
        } catch (MalformedURLException var3) {
            throw new IllegalArgumentException();
        }
    }

    public void a(String var1, Object var2) {
        this.a.put(var1, var2);
    }

    public void a() {
        OPlayerUsageSnooperThread var1 = new OPlayerUsageSnooperThread(this, "reporter");
        var1.setDaemon(true);
        var1.start();
    }

    // $FF: synthetic method
    static URL a(OPlayerUsageSnooper var0) {
        return var0.b;
    }

    // $FF: synthetic method
    static Map b(OPlayerUsageSnooper var0) {
        return var0.a;
    }
}
