package net.minecraft.server;

import net.minecraft.server.OICommandListener;

public class ORConConsoleSource implements OICommandListener {

    public static final ORConConsoleSource a = new ORConConsoleSource();
    private StringBuffer b = new StringBuffer();

    public ORConConsoleSource() {
        super();
    }

    public void a() {
        this.b.setLength(0);
    }

    public String b() {
        return this.b.toString();
    }

    public void b(String var1) {
        this.b.append(var1);
    }

    public String d() {
        return "Rcon";
    }

}
