package net.minecraft.server;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class OPropertyManager {

    public static Logger a = Logger.getLogger("Minecraft");
    private Properties b = new Properties();
    private File c;

    public OPropertyManager(File var1) {
        super();
        this.c = var1;
        if (var1.exists()) {
            try {
                this.b.load(new FileInputStream(var1));
            } catch (Exception var3) {
                a.log(Level.WARNING, "Failed to load " + var1, var3);
                this.a();
            }
        } else {
            a.log(Level.WARNING, var1 + " does not exist");
            this.a();
        }

    }

    public void a() {
        a.log(Level.INFO, "Generating new properties file");
        this.b();
    }

    public void b() {
        try {
            this.b.store(new FileOutputStream(this.c), "Minecraft server properties");
        } catch (Exception var2) {
            a.log(Level.WARNING, "Failed to save " + this.c, var2);
            this.a();
        }

    }

    public File c() {
        return this.c;
    }

    public String a(String var1, String var2) {
        if (!this.b.containsKey(var1)) {
            this.b.setProperty(var1, var2);
            this.b();
        }

        return this.b.getProperty(var1, var2);
    }

    public int a(String var1, int var2) {
        try {
            return Integer.parseInt(this.a(var1, "" + var2));
        } catch (Exception var4) {
            this.b.setProperty(var1, "" + var2);
            return var2;
        }
    }

    public boolean a(String var1, boolean var2) {
        try {
            return Boolean.parseBoolean(this.a(var1, "" + var2));
        } catch (Exception var4) {
            this.b.setProperty(var1, "" + var2);
            return var2;
        }
    }

    public void a(String var1, Object var2) {
        this.b.setProperty(var1, "" + var2);
    }

    public void b(String var1, boolean var2) {
        this.b.setProperty(var1, "" + var2);
        this.b();
    }

}
