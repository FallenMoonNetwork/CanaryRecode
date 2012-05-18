package net.minecraft.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import net.minecraft.server.OCompressedStreamTools;
import net.minecraft.server.OIProgressUpdate;
import net.minecraft.server.OISaveFormat;
import net.minecraft.server.OISaveHandler;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OSaveHandler;
import net.minecraft.server.OWorldInfo;

public class OSaveFormatOld implements OISaveFormat {

    protected final File a;

    public OSaveFormatOld(File var1) {
        super();
        if (!var1.exists()) {
            var1.mkdirs();
        }

        this.a = var1;
    }

    public OWorldInfo b(String var1) {
        File var2 = new File(this.a, var1);
        if (!var2.exists()) {
            return null;
        } else {
            File var3 = new File(var2, "level.dat");
            ONBTTagCompound var4;
            ONBTTagCompound var5;
            if (var3.exists()) {
                try {
                    var4 = OCompressedStreamTools.a((new FileInputStream(var3)));
                    var5 = var4.m("Data");
                    return new OWorldInfo(var5);
                } catch (Exception var7) {
                    var7.printStackTrace();
                }
            }

            var3 = new File(var2, "level.dat_old");
            if (var3.exists()) {
                try {
                    var4 = OCompressedStreamTools.a((new FileInputStream(var3)));
                    var5 = var4.m("Data");
                    return new OWorldInfo(var5);
                } catch (Exception var6) {
                    var6.printStackTrace();
                }
            }

            return null;
        }
    }

    public OISaveHandler a(String var1, boolean var2) {
        return new OSaveHandler(this.a, var1, var2);
    }

    public boolean a(String var1) {
        return false;
    }

    public boolean a(String var1, OIProgressUpdate var2) {
        return false;
    }
}
