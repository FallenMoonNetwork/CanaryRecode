package net.minecraft.server;


import java.util.List;
import java.util.Random;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OChunk;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEnumSkyBlock;
import net.minecraft.server.OTileEntity;
import net.minecraft.server.OWorld;


public class OEmptyChunk extends OChunk {

    public OEmptyChunk(OWorld var1, int var2, int var3) {
        super(var1, var2, var3);
    }

    @Override
    public boolean a(int var1, int var2) {
        return var1 == this.g && var2 == this.h;
    }

    @Override
    public int b(int var1, int var2) {
        return 0;
    }

    @Override
    public void a() {}

    @Override
    public void b() {}

    @Override
    public int a(int var1, int var2, int var3) {
        return 0;
    }

    @Override
    public int b(int var1, int var2, int var3) {
        return 255;
    }

    @Override
    public boolean a(int var1, int var2, int var3, int var4, int var5) {
        return true;
    }

    @Override
    public boolean a(int var1, int var2, int var3, int var4) {
        return true;
    }

    @Override
    public int c(int var1, int var2, int var3) {
        return 0;
    }

    @Override
    public boolean b(int var1, int var2, int var3, int var4) {
        return false;
    }

    @Override
    public int a(OEnumSkyBlock var1, int var2, int var3, int var4) {
        return 0;
    }

    @Override
    public void a(OEnumSkyBlock var1, int var2, int var3, int var4, int var5) {}

    @Override
    public int c(int var1, int var2, int var3, int var4) {
        return 0;
    }

    @Override
    public void a(OEntity var1) {}

    @Override
    public void b(OEntity var1) {}

    @Override
    public void a(OEntity var1, int var2) {}

    @Override
    public boolean d(int var1, int var2, int var3) {
        return false;
    }

    @Override
    public OTileEntity e(int var1, int var2, int var3) {
        return null;
    }

    @Override
    public void a(OTileEntity var1) {}

    @Override
    public void a(int var1, int var2, int var3, OTileEntity var4) {}

    @Override
    public void f(int var1, int var2, int var3) {}

    @Override
    public void c() {}

    @Override
    public void d() {}

    @Override
    public void e() {}

    @Override
    public void a(OEntity var1, OAxisAlignedBB var2, List var3) {}

    @Override
    public void a(Class var1, OAxisAlignedBB var2, List var3) {}

    @Override
    public boolean a(boolean var1) {
        return false;
    }

    @Override
    public Random a(long var1) {
        return new Random(this.e.n() + (this.g * this.g * 4987142) + (this.g * 5947611) + (this.h * this.h) * 4392871L + (this.h * 389711) ^ var1);
    }

    @Override
    public boolean f() {
        return true;
    }

    @Override
    public boolean c(int var1, int var2) {
        return true;
    }
}
