package net.minecraft.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class SaveFormatOld implements ISaveFormat {

    protected final File a;
    protected net.canarymod.api.world.DimensionType type;

    // CanaryMod changed constructor

    public SaveFormatOld(File file1, net.canarymod.api.world.DimensionType type) {
        if (!file1.exists()) {
            file1.mkdirs();
        }
        this.type = type;
        this.a = file1;
    }

    @Override
    public void d() {
    }

    public WorldInfo c(String s0) {
        File file1 = new File(this.a, s0);

        if (!file1.exists()) {
            return null;
        }
        else {
            File file2 = new File(file1, "level.dat");
            NBTTagCompound nbttagcompound;
            NBTTagCompound nbttagcompound1;

            if (file2.exists()) {
                try {
                    nbttagcompound = CompressedStreamTools.a((InputStream)(new FileInputStream(file2)));
                    nbttagcompound1 = nbttagcompound.l("Data");
                    return new WorldInfo(nbttagcompound1);
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

            file2 = new File(file1, "level.dat_old");
            if (file2.exists()) {
                try {
                    nbttagcompound = CompressedStreamTools.a((InputStream)(new FileInputStream(file2)));
                    nbttagcompound1 = nbttagcompound.l("Data");
                    return new WorldInfo(nbttagcompound1);
                }
                catch (Exception exception1) {
                    exception1.printStackTrace();
                }
            }

            return null;
        }
    }

    @Override
    public boolean e(String s0) {
        File file1 = new File(this.a, s0);

        if (!file1.exists()) {
            return true;
        }
        else {
            System.out.println("Deleting level " + s0);

            for (int i0 = 1; i0 <= 5; ++i0) {
                System.out.println("Attempt " + i0 + "...");
                if (a(file1.listFiles())) {
                    break;
                }

                System.out.println("Unsuccessful in deleting contents.");
                if (i0 < 5) {
                    try {
                        Thread.sleep(500L);
                    }
                    catch (InterruptedException interruptedexception) {
                        ;
                    }
                }
            }

            return file1.delete();
        }
    }

    protected static boolean a(File[] afile) {
        for (int i0 = 0; i0 < afile.length; ++i0) {
            File file1 = afile[i0];

            System.out.println("Deleting " + file1);
            if (file1.isDirectory() && !a(file1.listFiles())) {
                System.out.println("Couldn\'t delete directory " + file1);
                return false;
            }

            if (!file1.delete()) {
                System.out.println("Couldn\'t delete file " + file1);
                return false;
            }
        }

        return true;
    }

    @Override
    public ISaveHandler a(String s0, boolean flag0) {
        return new SaveHandler(this.a, s0, flag0, type);
    }

    @Override
    public boolean b(String s0) {
        return false;
    }

    @Override
    public boolean a(String s0, IProgressUpdate iprogressupdate) {
        return false;
    }
}
