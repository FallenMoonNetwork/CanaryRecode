package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OBlock;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldGenBigMushroom;
import net.minecraft.server.OWorldGenCactus;
import net.minecraft.server.OWorldGenClay;
import net.minecraft.server.OWorldGenDeadBush;
import net.minecraft.server.OWorldGenFlowers;
import net.minecraft.server.OWorldGenLiquids;
import net.minecraft.server.OWorldGenMinable;
import net.minecraft.server.OWorldGenPumpkin;
import net.minecraft.server.OWorldGenReed;
import net.minecraft.server.OWorldGenSand;
import net.minecraft.server.OWorldGenWaterlily;
import net.minecraft.server.OWorldGenerator;

public class OBiomeDecorator {

    protected OWorld a;
    protected Random b;
    protected int c;
    protected int d;
    protected OBiomeGenBase e;
    protected OWorldGenerator f = new OWorldGenClay(4);
    protected OWorldGenerator g;
    protected OWorldGenerator h;
    protected OWorldGenerator i;
    protected OWorldGenerator j;
    protected OWorldGenerator k;
    protected OWorldGenerator l;
    protected OWorldGenerator m;
    protected OWorldGenerator n;
    protected OWorldGenerator o;
    protected OWorldGenerator p;
    protected OWorldGenerator q;
    protected OWorldGenerator r;
    protected OWorldGenerator s;
    protected OWorldGenerator t;
    protected OWorldGenerator u;
    protected OWorldGenerator v;
    protected OWorldGenerator w;
    protected OWorldGenerator x;
    protected int y;
    protected int z;
    protected int A;
    protected int B;
    protected int C;
    protected int D;
    protected int E;
    protected int F;
    protected int G;
    protected int H;
    protected int I;
    protected int J;
    public boolean K;

    public OBiomeDecorator(OBiomeGenBase var1) {
        super();
        this.g = new OWorldGenSand(7, OBlock.E.bO);
        this.h = new OWorldGenSand(6, OBlock.F.bO);
        this.i = new OWorldGenMinable(OBlock.v.bO, 32);
        this.j = new OWorldGenMinable(OBlock.F.bO, 32);
        this.k = new OWorldGenMinable(OBlock.I.bO, 16);
        this.l = new OWorldGenMinable(OBlock.H.bO, 8);
        this.m = new OWorldGenMinable(OBlock.G.bO, 8);
        this.n = new OWorldGenMinable(OBlock.aN.bO, 7);
        this.o = new OWorldGenMinable(OBlock.aw.bO, 7);
        this.p = new OWorldGenMinable(OBlock.N.bO, 6);
        this.q = new OWorldGenFlowers(OBlock.ad.bO);
        this.r = new OWorldGenFlowers(OBlock.ae.bO);
        this.s = new OWorldGenFlowers(OBlock.af.bO);
        this.t = new OWorldGenFlowers(OBlock.ag.bO);
        this.u = new OWorldGenBigMushroom();
        this.v = new OWorldGenReed();
        this.w = new OWorldGenCactus();
        this.x = new OWorldGenWaterlily();
        this.y = 0;
        this.z = 0;
        this.A = 2;
        this.B = 1;
        this.C = 0;
        this.D = 0;
        this.E = 0;
        this.F = 0;
        this.G = 1;
        this.H = 3;
        this.I = 1;
        this.J = 0;
        this.K = true;
        this.e = var1;
    }

    public void a(OWorld var1, Random var2, int var3, int var4) {
        if (this.a != null) {
            throw new RuntimeException("Already decorating!!");
        } else {
            this.a = var1;
            this.b = var2;
            this.c = var3;
            this.d = var4;
            this.a();
            this.a = null;
            this.b = null;
        }
    }

    protected void a() {
        this.b();

        int var1;
        int var2;
        int var3;
        for (var1 = 0; var1 < this.H; ++var1) {
            var2 = this.c + this.b.nextInt(16) + 8;
            var3 = this.d + this.b.nextInt(16) + 8;
            this.g.a(this.a, this.b, var2, this.a.g(var2, var3), var3);
        }

        for (var1 = 0; var1 < this.I; ++var1) {
            var2 = this.c + this.b.nextInt(16) + 8;
            var3 = this.d + this.b.nextInt(16) + 8;
            this.f.a(this.a, this.b, var2, this.a.g(var2, var3), var3);
        }

        for (var1 = 0; var1 < this.G; ++var1) {
            var2 = this.c + this.b.nextInt(16) + 8;
            var3 = this.d + this.b.nextInt(16) + 8;
            this.g.a(this.a, this.b, var2, this.a.g(var2, var3), var3);
        }

        var1 = this.z;
        if (this.b.nextInt(10) == 0) {
            ++var1;
        }

        int var4;
        for (var2 = 0; var2 < var1; ++var2) {
            var3 = this.c + this.b.nextInt(16) + 8;
            var4 = this.d + this.b.nextInt(16) + 8;
            OWorldGenerator var5 = this.e.a(this.b);
            var5.a(1.0D, 1.0D, 1.0D);
            var5.a(this.a, this.b, var3, this.a.e(var3, var4), var4);
        }

        for (var2 = 0; var2 < this.J; ++var2) {
            var3 = this.c + this.b.nextInt(16) + 8;
            var4 = this.d + this.b.nextInt(16) + 8;
            this.u.a(this.a, this.b, var3, this.a.e(var3, var4), var4);
        }

        int var7;
        for (var2 = 0; var2 < this.A; ++var2) {
            var3 = this.c + this.b.nextInt(16) + 8;
            var4 = this.b.nextInt(128);
            var7 = this.d + this.b.nextInt(16) + 8;
            this.q.a(this.a, this.b, var3, var4, var7);
            if (this.b.nextInt(4) == 0) {
                var3 = this.c + this.b.nextInt(16) + 8;
                var4 = this.b.nextInt(128);
                var7 = this.d + this.b.nextInt(16) + 8;
                this.r.a(this.a, this.b, var3, var4, var7);
            }
        }

        for (var2 = 0; var2 < this.B; ++var2) {
            var3 = this.c + this.b.nextInt(16) + 8;
            var4 = this.b.nextInt(128);
            var7 = this.d + this.b.nextInt(16) + 8;
            OWorldGenerator var6 = this.e.b(this.b);
            var6.a(this.a, this.b, var3, var4, var7);
        }

        for (var2 = 0; var2 < this.C; ++var2) {
            var3 = this.c + this.b.nextInt(16) + 8;
            var4 = this.b.nextInt(128);
            var7 = this.d + this.b.nextInt(16) + 8;
            (new OWorldGenDeadBush(OBlock.Y.bO)).a(this.a, this.b, var3, var4, var7);
        }

        for (var2 = 0; var2 < this.y; ++var2) {
            var3 = this.c + this.b.nextInt(16) + 8;
            var4 = this.d + this.b.nextInt(16) + 8;

            for (var7 = this.b.nextInt(128); var7 > 0 && this.a.a(var3, var7 - 1, var4) == 0; --var7) {
                ;
            }

            this.x.a(this.a, this.b, var3, var7, var4);
        }

        for (var2 = 0; var2 < this.D; ++var2) {
            if (this.b.nextInt(4) == 0) {
                var3 = this.c + this.b.nextInt(16) + 8;
                var4 = this.d + this.b.nextInt(16) + 8;
                var7 = this.a.e(var3, var4);
                this.s.a(this.a, this.b, var3, var7, var4);
            }

            if (this.b.nextInt(8) == 0) {
                var3 = this.c + this.b.nextInt(16) + 8;
                var4 = this.d + this.b.nextInt(16) + 8;
                var7 = this.b.nextInt(128);
                this.t.a(this.a, this.b, var3, var7, var4);
            }
        }

        if (this.b.nextInt(4) == 0) {
            var2 = this.c + this.b.nextInt(16) + 8;
            var3 = this.b.nextInt(128);
            var4 = this.d + this.b.nextInt(16) + 8;
            this.s.a(this.a, this.b, var2, var3, var4);
        }

        if (this.b.nextInt(8) == 0) {
            var2 = this.c + this.b.nextInt(16) + 8;
            var3 = this.b.nextInt(128);
            var4 = this.d + this.b.nextInt(16) + 8;
            this.t.a(this.a, this.b, var2, var3, var4);
        }

        for (var2 = 0; var2 < this.E; ++var2) {
            var3 = this.c + this.b.nextInt(16) + 8;
            var4 = this.d + this.b.nextInt(16) + 8;
            var7 = this.b.nextInt(128);
            this.v.a(this.a, this.b, var3, var7, var4);
        }

        for (var2 = 0; var2 < 10; ++var2) {
            var3 = this.c + this.b.nextInt(16) + 8;
            var4 = this.b.nextInt(128);
            var7 = this.d + this.b.nextInt(16) + 8;
            this.v.a(this.a, this.b, var3, var4, var7);
        }

        if (this.b.nextInt(32) == 0) {
            var2 = this.c + this.b.nextInt(16) + 8;
            var3 = this.b.nextInt(128);
            var4 = this.d + this.b.nextInt(16) + 8;
            (new OWorldGenPumpkin()).a(this.a, this.b, var2, var3, var4);
        }

        for (var2 = 0; var2 < this.F; ++var2) {
            var3 = this.c + this.b.nextInt(16) + 8;
            var4 = this.b.nextInt(128);
            var7 = this.d + this.b.nextInt(16) + 8;
            this.w.a(this.a, this.b, var3, var4, var7);
        }

        if (this.K) {
            for (var2 = 0; var2 < 50; ++var2) {
                var3 = this.c + this.b.nextInt(16) + 8;
                var4 = this.b.nextInt(this.b.nextInt(120) + 8);
                var7 = this.d + this.b.nextInt(16) + 8;
                (new OWorldGenLiquids(OBlock.A.bO)).a(this.a, this.b, var3, var4, var7);
            }

            for (var2 = 0; var2 < 20; ++var2) {
                var3 = this.c + this.b.nextInt(16) + 8;
                var4 = this.b.nextInt(this.b.nextInt(this.b.nextInt(112) + 8) + 8);
                var7 = this.d + this.b.nextInt(16) + 8;
                (new OWorldGenLiquids(OBlock.C.bO)).a(this.a, this.b, var3, var4, var7);
            }
        }

    }

    protected void a(int var1, OWorldGenerator var2, int var3, int var4) {
        for (int var5 = 0; var5 < var1; ++var5) {
            int var6 = this.c + this.b.nextInt(16);
            int var7 = this.b.nextInt(var4 - var3) + var3;
            int var8 = this.d + this.b.nextInt(16);
            var2.a(this.a, this.b, var6, var7, var8);
        }

    }

    protected void b(int var1, OWorldGenerator var2, int var3, int var4) {
        for (int var5 = 0; var5 < var1; ++var5) {
            int var6 = this.c + this.b.nextInt(16);
            int var7 = this.b.nextInt(var4) + this.b.nextInt(var4) + (var3 - var4);
            int var8 = this.d + this.b.nextInt(16);
            var2.a(this.a, this.b, var6, var7, var8);
        }

    }

    protected void b() {
        this.a(20, this.i, 0, 128);
        this.a(10, this.j, 0, 128);
        this.a(20, this.k, 0, 128);
        this.a(20, this.l, 0, 64);
        this.a(2, this.m, 0, 32);
        this.a(8, this.n, 0, 16);
        this.a(1, this.o, 0, 16);
        this.b(1, this.p, 16, 16);
    }
}
