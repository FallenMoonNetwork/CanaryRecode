package net.minecraft.server;


import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OChunkProviderHell;
import net.minecraft.server.OIChunkProvider;
import net.minecraft.server.OWorldChunkManagerHell;
import net.minecraft.server.OWorldProvider;


public class OWorldProviderHell extends OWorldProvider {

    public OWorldProviderHell() {
        super();
    }

    @Override
    public void a() {
        this.c = new OWorldChunkManagerHell(OBiomeGenBase.j, 1.0F, 0.0F);
        this.d = true;
        this.e = true;
        this.g = -1;
    }

    @Override
    protected void g() {
        float var1 = 0.1F;

        for (int var2 = 0; var2 <= 15; ++var2) {
            float var3 = 1.0F - var2 / 15.0F;

            this.f[var2] = (1.0F - var3) / (var3 * 3.0F + 1.0F) * (1.0F - var1) + var1;
        }

    }

    @Override
    public OIChunkProvider b() {
        return new OChunkProviderHell(this.a, this.a.n());
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public boolean a(int var1, int var2) {
        return false;
    }

    @Override
    public float a(long var1, float var3) {
        return 0.5F;
    }

    @Override
    public boolean c() {
        return false;
    }
}
