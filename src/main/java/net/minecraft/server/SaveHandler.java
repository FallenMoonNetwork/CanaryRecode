package net.minecraft.server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SaveHandler implements ISaveHandler, IPlayerFileData {

    private final File a;
    private final File b;
    private final File worldbaseDir; // CanaryMod
    private final File c;
    private final long d = System.currentTimeMillis();
    private final String e;
    protected net.canarymod.api.world.WorldType type;

    public SaveHandler(File file1, String s0, boolean flag0, net.canarymod.api.world.WorldType type) {
        // CanaryMod refactored for more flexible folder structure
        this.a = new File(file1, s0 + "/" + s0 + "_" + type.getName());
        this.a.mkdirs();

        //CanaryMod put the players and data files into a global folder valid for all worlds
        this.b = new File(file1, "players");
        this.c = new File(a, "data");
        this.c.mkdirs();
        this.e = s0;
        this.worldbaseDir = file1;
        if (flag0) {
            this.b.mkdirs();
        }
        this.f();
        this.type = type;
        this.h();
    }

    // CanaryMod added getname
    /**
     * get the base name of this world saver (only world name, without dimension appendix)
     * @return
     */
    public String getBaseName() {
        return this.e;
    }

    // CanaryMod
    /**
     * get the dir folder (worlds/)
     * @return
     */
    public File getWorldBaseDir() {
        return worldbaseDir;
    }

    private void h() {
        try {
            File file1 = new File(this.a, "session.lock");
            DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream(file1));

            try {
                dataoutputstream.writeLong(this.d);
            } finally {
                dataoutputstream.close();
            }
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
            throw new RuntimeException("Failed to check session lock, aborting");
        }
    }

    protected File b() {
        return this.a;
    }

    @Override
    public void c() throws MinecraftException {
        try {
            File file1 = new File(this.a, "session.lock");
            DataInputStream datainputstream = new DataInputStream(new FileInputStream(file1));

            try {
                if (datainputstream.readLong() != this.d) {
                    throw new MinecraftException("The save is being accessed from another location, aborting");
                }
            } finally {
                datainputstream.close();
            }
        } catch (IOException ioexception) {
            throw new MinecraftException("Failed to check session lock, aborting");
        }
    }

    @Override
    public IChunkLoader a(WorldProvider worldprovider) {
        throw new RuntimeException("Old Chunk Storage is no longer supported.");
    }

    @Override
    public WorldInfo d() {
        File file1 = new File(this.a, "level.dat");
        NBTTagCompound nbttagcompound;
        NBTTagCompound nbttagcompound1;

        if (file1.exists()) {
            try {
                nbttagcompound = CompressedStreamTools.a((InputStream) (new FileInputStream(file1)));
                nbttagcompound1 = nbttagcompound.l("Data");
                return new WorldInfo(nbttagcompound1);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        file1 = new File(this.a, "level.dat_old");
        if (file1.exists()) {
            try {
                nbttagcompound = CompressedStreamTools.a((InputStream) (new FileInputStream(file1)));
                nbttagcompound1 = nbttagcompound.l("Data");
                return new WorldInfo(nbttagcompound1);
            } catch (Exception exception1) {
                exception1.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public void a(WorldInfo worldinfo, NBTTagCompound nbttagcompound) {
        NBTTagCompound nbttagcompound1 = worldinfo.a(nbttagcompound);
        NBTTagCompound nbttagcompound2 = new NBTTagCompound();

        nbttagcompound2.a("Data", (NBTBase) nbttagcompound1);

        try {
            File file1 = new File(this.a, "level.dat_new");
            File file2 = new File(this.a, "level.dat_old");
            File file3 = new File(this.a, "level.dat");

            CompressedStreamTools.a(nbttagcompound2, (OutputStream) (new FileOutputStream(file1)));
            if (file2.exists()) {
                file2.delete();
            }

            file3.renameTo(file2);
            if (file3.exists()) {
                file3.delete();
            }

            file1.renameTo(file3);
            if (file1.exists()) {
                file1.delete();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void a(WorldInfo worldinfo) {
        NBTTagCompound nbttagcompound = worldinfo.a();
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();

        nbttagcompound1.a("Data", (NBTBase) nbttagcompound);

        try {
            File file1 = new File(this.a, "level.dat_new");
            File file2 = new File(this.a, "level.dat_old");
            File file3 = new File(this.a, "level.dat");

            CompressedStreamTools.a(nbttagcompound1, (OutputStream) (new FileOutputStream(file1)));
            if (file2.exists()) {
                file2.delete();
            }

            file3.renameTo(file2);
            if (file3.exists()) {
                file3.delete();
            }

            file1.renameTo(file3);
            if (file1.exists()) {
                file1.delete();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void a(EntityPlayer entityplayer) {
        try {
            NBTTagCompound nbttagcompound = new NBTTagCompound();

            entityplayer.e(nbttagcompound);
            File file1 = new File(this.b, entityplayer.bS + ".dat.tmp");
            File file2 = new File(this.b, entityplayer.bS + ".dat");

            CompressedStreamTools.a(nbttagcompound, (OutputStream) (new FileOutputStream(file1)));
            if (file2.exists()) {
                file2.delete();
            }

            file1.renameTo(file2);
        } catch (Exception exception) {
            MinecraftServer.D().al().b("Failed to save player data for " + entityplayer.bS);
        }

    }

    @Override
    public NBTTagCompound b(EntityPlayer entityplayer) {
        NBTTagCompound nbttagcompound = this.a(entityplayer.bS);

        if (nbttagcompound != null) {
            entityplayer.f(nbttagcompound);
        }

        return nbttagcompound;
    }

    public NBTTagCompound a(String s0) {
        try {
            File file1 = new File(this.b, s0 + ".dat");

            if (file1.exists()) {
                return CompressedStreamTools.a((InputStream) (new FileInputStream(file1)));
            }
        } catch (Exception exception) {
            MinecraftServer.D().al().b("Failed to load player data for " + s0);
        }

        return null;
    }

    @Override
    public IPlayerFileData e() {
        return this;
    }

    @Override
    public String[] f() {
        String[] astring = this.b.list();

        for (int i0 = 0; i0 < astring.length; ++i0) {
            if (astring[i0].endsWith(".dat")) {
                astring[i0] = astring[i0].substring(0, astring[i0].length() - 4);
            }
        }

        return astring;
    }

    @Override
    public void a() {}

    @Override
    public File b(String s0) {
        return new File(this.c, s0 + ".dat");
    }

    @Override
    public String g() {
        return this.e;
    }
}