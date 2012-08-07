package net.minecraft.server;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMapCoord;
import net.minecraft.server.OMapInfo;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldSavedData;


public class OMapData extends OWorldSavedData {

    public int b;
    public int c;
    public byte d;
    public byte e;
    public byte[] f = new byte[16384];
    public int g;
    public List h = new ArrayList();
    private Map j = new HashMap();
    public List i = new ArrayList();

    public OMapData(String var1) {
        super(var1);
    }

    @Override
    public void a(ONBTTagCompound var1) {
        this.d = var1.d("dimension");
        this.b = var1.f("xCenter");
        this.c = var1.f("zCenter");
        this.e = var1.d("scale");
        if (this.e < 0) {
            this.e = 0;
        }

        if (this.e > 4) {
            this.e = 4;
        }

        short var2 = var1.e("width");
        short var3 = var1.e("height");

        if (var2 == 128 && var3 == 128) {
            this.f = var1.k("colors");
        } else {
            byte[] var4 = var1.k("colors");

            this.f = new byte[16384];
            int var5 = (128 - var2) / 2;
            int var6 = (128 - var3) / 2;

            for (int var7 = 0; var7 < var3; ++var7) {
                int var8 = var7 + var6;

                if (var8 >= 0 || var8 < 128) {
                    for (int var9 = 0; var9 < var2; ++var9) {
                        int var10 = var9 + var5;

                        if (var10 >= 0 || var10 < 128) {
                            this.f[var10 + var8 * 128] = var4[var9 + var7 * var2];
                        }
                    }
                }
            }
        }

    }

    @Override
    public void b(ONBTTagCompound var1) {
        var1.a("dimension", this.d);
        var1.a("xCenter", this.b);
        var1.a("zCenter", this.c);
        var1.a("scale", this.e);
        var1.a("width", (short) 128);
        var1.a("height", (short) 128);
        var1.a("colors", this.f);
    }

    public void a(OEntityPlayer var1, OItemStack var2) {
        if (!this.j.containsKey(var1)) {
            OMapInfo var3 = new OMapInfo(this, var1);

            this.j.put(var1, var3);
            this.h.add(var3);
        }

        this.i.clear();

        for (int var14 = 0; var14 < this.h.size(); ++var14) {
            OMapInfo var4 = (OMapInfo) this.h.get(var14);

            if (!var4.a.bE && var4.a.k.hasItemStack(var2)) {
                float var5 = (float) (var4.a.bm - this.b) / (1 << this.e);
                float var6 = (float) (var4.a.bo - this.c) / (1 << this.e);
                byte var7 = 64;
                byte var8 = 64;

                if (var5 >= (-var7) && var6 >= (-var8) && var5 <= var7 && var6 <= var8) {
                    byte var9 = 0;
                    byte var10 = (byte) ((int) ((var5 * 2.0F) + 0.5D));
                    byte var11 = (byte) ((int) ((var6 * 2.0F) + 0.5D));
                    byte var12 = (byte) ((int) ((var1.bs * 16.0F / 360.0F) + 0.5D));

                    if (this.d < 0) {
                        int var13 = this.g / 10;

                        var12 = (byte) (var13 * var13 * 34187121 + var13 * 121 >> 15 & 15);
                    }

                    if (var4.a.w == this.d) {
                        this.i.add(new OMapCoord(this, var9, var10, var11, var12));
                    }
                }
            } else {
                this.j.remove(var4.a);
                this.h.remove(var4);
            }
        }

    }

    public byte[] a(OItemStack var1, OWorld var2, OEntityPlayer var3) {
        OMapInfo var4 = (OMapInfo) this.j.get(var3);

        if (var4 == null) {
            return null;
        } else {
            byte[] var5 = var4.a(var1);

            return var5;
        }
    }

    public void a(int var1, int var2, int var3) {
        super.a();

        for (int var4 = 0; var4 < this.h.size(); ++var4) {
            OMapInfo var5 = (OMapInfo) this.h.get(var4);

            if (var5.b[var1] < 0 || var5.b[var1] > var2) {
                var5.b[var1] = var2;
            }

            if (var5.c[var1] < 0 || var5.c[var1] < var3) {
                var5.c[var1] = var3;
            }
        }

    }
}
