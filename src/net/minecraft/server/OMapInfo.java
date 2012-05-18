package net.minecraft.server;

import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMapCoord;
import net.minecraft.server.OMapData;

public class OMapInfo {

    public final OEntityPlayer a;
    public int[] b;
    public int[] c;
    private int e;
    private int f;
    private byte[] g;
    // $FF: synthetic field
    final OMapData d;

    public OMapInfo(OMapData var1, OEntityPlayer var2) {
        super();
        this.d = var1;
        this.b = new int[128];
        this.c = new int[128];
        this.e = 0;
        this.f = 0;
        this.a = var2;

        for (int var3 = 0; var3 < this.b.length; ++var3) {
            this.b[var3] = 0;
            this.c[var3] = 127;
        }

    }

    public byte[] a(OItemStack var1) {
        int var3;
        int var10;
        if (--this.f < 0) {
            this.f = 4;
            byte[] var2 = new byte[this.d.i.size() * 3 + 1];
            var2[0] = 1;

            for (var3 = 0; var3 < this.d.i.size(); ++var3) {
                OMapCoord var4 = (OMapCoord) this.d.i.get(var3);
                var2[var3 * 3 + 1] = (byte) (var4.a + (var4.d & 15) * 16);
                var2[var3 * 3 + 2] = var4.b;
                var2[var3 * 3 + 3] = var4.c;
            }

            boolean var9 = true;
            if (this.g != null && this.g.length == var2.length) {
                for (var10 = 0; var10 < var2.length; ++var10) {
                    if (var2[var10] != this.g[var10]) {
                        var9 = false;
                        break;
                    }
                }
            } else {
                var9 = false;
            }

            if (!var9) {
                this.g = var2;
                return var2;
            }
        }

        for (int var8 = 0; var8 < 10; ++var8) {
            var3 = this.e * 11 % 128;
            ++this.e;
            if (this.b[var3] >= 0) {
                var10 = this.c[var3] - this.b[var3] + 1;
                int var5 = this.b[var3];
                byte[] var6 = new byte[var10 + 3];
                var6[0] = 0;
                var6[1] = (byte) var3;
                var6[2] = (byte) var5;

                for (int var7 = 0; var7 < var6.length - 3; ++var7) {
                    var6[var7 + 3] = this.d.f[(var7 + var5) * 128 + var3];
                }

                this.c[var3] = -1;
                this.b[var3] = -1;
                return var6;
            }
        }

        return null;
    }
}
