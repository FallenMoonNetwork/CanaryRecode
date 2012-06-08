package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import net.minecraft.server.OCompressedStreamTools;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIChunkLoader;
import net.minecraft.server.OIPlayerFileData;
import net.minecraft.server.OISaveHandler;
import net.minecraft.server.OMinecraftException;
import net.minecraft.server.ONBTBase;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OWorldInfo;
import net.minecraft.server.OWorldProvider;

public class OSaveHandler implements OIPlayerFileData, OISaveHandler {

    private static final Logger a = Logger.getLogger("Minecraft");
    private final File b;
    private final File c;
    private final File d;
    private final long e = System.currentTimeMillis();
    private final String f;

    public OSaveHandler(File var1, String var2, boolean var3) {
        super();
        this.b = new File(var1, var2);
        this.b.mkdirs();
        this.c = new File(this.b, "players");
        this.d = new File(this.b, "data");
        this.d.mkdirs();
        this.f = var2;
        if (var3) {
            this.c.mkdirs();
        }

        this.f();
    }

    private void f() {
        try {
            File var1 = new File(this.b, "session.lock");
            DataOutputStream var2 = new DataOutputStream(new FileOutputStream(var1));
            boolean var6 = false;

            try {
                var6 = true;
                var2.writeLong(this.e);
                var6 = false;
            } finally {
                if (var6) {
                    var2.close();
                }
            }

            var2.close();
        } catch (IOException var8) {
            var8.printStackTrace();
            throw new RuntimeException("Failed to check session lock, aborting");
        }
    }

    protected File a() {
        return this.b;
    }

    @Override
    public void b() {
        try {
            File var1 = new File(this.b, "session.lock");
            DataInputStream var2 = new DataInputStream(new FileInputStream(var1));
            boolean var6 = false;

            try {
                var6 = true;
                if (var2.readLong() != this.e) {
                    throw new OMinecraftException("The save is being accessed from another location, aborting");
                }

                var6 = false;
            } finally {
                if (var6) {
                    var2.close();
                }
            }

            var2.close();
        } catch (IOException var8) {
            throw new OMinecraftException("Failed to check session lock, aborting");
        }
    }

    @Override
    public OIChunkLoader a(OWorldProvider var1) {
        throw new RuntimeException("Old Chunk Storage is no longer supported.");
    }

    @Override
    public OWorldInfo c() {
        File var1 = new File(this.b, "level.dat");
        ONBTTagCompound var2;
        ONBTTagCompound var3;
        if (var1.exists()) {
            try {
                var2 = OCompressedStreamTools.a((new FileInputStream(var1)));
                var3 = var2.m("Data");
                return new OWorldInfo(var3);
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }

        var1 = new File(this.b, "level.dat_old");
        if (var1.exists()) {
            try {
                var2 = OCompressedStreamTools.a((new FileInputStream(var1)));
                var3 = var2.m("Data");
                return new OWorldInfo(var3);
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public void a(OWorldInfo var1, List var2) {
        ONBTTagCompound var3 = var1.a(var2);
        ONBTTagCompound var4 = new ONBTTagCompound();
        var4.a("Data", (ONBTBase) var3);

        try {
            File var5 = new File(this.b, "level.dat_new");
            File var6 = new File(this.b, "level.dat_old");
            File var7 = new File(this.b, "level.dat");
            OCompressedStreamTools.a(var4, (new FileOutputStream(var5)));
            if (var6.exists()) {
                var6.delete();
            }

            var7.renameTo(var6);
            if (var7.exists()) {
                var7.delete();
            }

            var5.renameTo(var7);
            if (var5.exists()) {
                var5.delete();
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        }

    }

    @Override
    public void a(OWorldInfo var1) {
        ONBTTagCompound var2 = var1.a();
        ONBTTagCompound var3 = new ONBTTagCompound();
        var3.a("Data", (ONBTBase) var2);

        try {
            File var4 = new File(this.b, "level.dat_new");
            File var5 = new File(this.b, "level.dat_old");
            File var6 = new File(this.b, "level.dat");
            OCompressedStreamTools.a(var3, (new FileOutputStream(var4)));
            if (var5.exists()) {
                var5.delete();
            }

            var6.renameTo(var5);
            if (var6.exists()) {
                var6.delete();
            }

            var4.renameTo(var6);
            if (var4.exists()) {
                var4.delete();
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    @Override
    public void a(OEntityPlayer var1) {
        try {
            ONBTTagCompound var2 = new ONBTTagCompound();
            var1.d(var2);
            File var3 = new File(this.c, "_tmp_.dat");
            File var4 = new File(this.c, var1.v + ".dat");
            OCompressedStreamTools.a(var2, (new FileOutputStream(var3)));
            if (var4.exists()) {
                var4.delete();
            }

            var3.renameTo(var4);
        } catch (Exception var5) {
            a.warning("Failed to save player data for " + var1.v);
        }

    }

    @Override
    public void b(OEntityPlayer var1) {
        ONBTTagCompound var2 = this.a(var1.v);
        if (var2 != null) {
            var1.e(var2);
        }

    }

    public ONBTTagCompound a(String var1) {
        try {
            File var2 = new File(this.c, var1 + ".dat");
            if (var2.exists()) {
                return OCompressedStreamTools.a((new FileInputStream(var2)));
            }
        } catch (Exception var3) {
            a.warning("Failed to load player data for " + var1);
        }

        return null;
    }

    @Override
    public OIPlayerFileData d() {
        return this;
    }

    @Override
    public String[] g() {
        return this.c.list();
    }

    @Override
    public void e() {
    }

    @Override
    public File b(String var1) {
        return new File(this.d, var1 + ".dat");
    }

}
