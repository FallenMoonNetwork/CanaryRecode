package net.minecraft.server;


import java.util.Random;
import net.minecraft.server.OIChunkProvider;
import net.minecraft.server.OWorld;


public class OMapGenBase {

    protected int b = 8;
    protected Random c = new Random();
    protected OWorld d;

    public OMapGenBase() {
        super();
    }

    public void a(OIChunkProvider var1, OWorld var2, int var3, int var4, byte[] var5) {
        int var6 = this.b;

        this.d = var2;
        this.c.setSeed(var2.n());
        long var7 = this.c.nextLong();
        long var9 = this.c.nextLong();

        for (int var11 = var3 - var6; var11 <= var3 + var6; ++var11) {
            for (int var12 = var4 - var6; var12 <= var4 + var6; ++var12) {
                long var13 = var11 * var7;
                long var15 = var12 * var9;

                this.c.setSeed(var13 ^ var15 ^ var2.n());
                this.a(var2, var11, var12, var3, var4, var5);
            }
        }

    }

    protected void a(OWorld var1, int var2, int var3, int var4, int var5, byte[] var6) {}
}
