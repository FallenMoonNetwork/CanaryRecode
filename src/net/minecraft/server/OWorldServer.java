package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

import net.canarymod.api.world.CanaryDimension;
import net.minecraft.server.OChunkProviderServer;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityAnimal;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntityWaterMob;
import net.minecraft.server.OExplosion;
import net.minecraft.server.OIChunkLoader;
import net.minecraft.server.OIChunkProvider;
import net.minecraft.server.OINpc;
import net.minecraft.server.OISaveHandler;
import net.minecraft.server.OIntHashMap;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OMinecraftServer;
import net.minecraft.server.OPacket;
import net.minecraft.server.OPacket38EntityStatus;
import net.minecraft.server.OPacket54PlayNoteBlock;
import net.minecraft.server.OPacket60Explosion;
import net.minecraft.server.OPacket70Bed;
import net.minecraft.server.OPacket71Weather;
import net.minecraft.server.OTileEntity;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldProvider;
import net.minecraft.server.OWorldSettings;

public class OWorldServer extends OWorld {

    public OChunkProviderServer G;
    public boolean H = false;
    public boolean I;
    private OMinecraftServer J;
    private OIntHashMap K;
    
    /**
     * CanaryMod Dimension (OWorldWrapper)
     */
    private CanaryDimension canaryWorld;

    /**
     * Get the CanaryMod Server wrapper
     * @return
     */
    public Object getServer() {
        return J.getServer();
    }
    
    public CanaryDimension getCanaryWorld() {
        return canaryWorld;
    }
    
    public void setCanaryWorld(CanaryDimension dim) {
        canaryWorld = dim;
    }
    public OWorldServer(OMinecraftServer var1, OISaveHandler var2, String var3, int var4, OWorldSettings var5) {
        super(var2, var3, var5, OWorldProvider.a(var4));
        this.J = var1;
        if (this.K == null) {
            this.K = new OIntHashMap();
        }
    }

    public void a(OEntity var1, boolean var2) {
        if (!this.J.o && (var1 instanceof OEntityAnimal || var1 instanceof OEntityWaterMob)) {
            var1.X();
        }

        if (!this.J.p && var1 instanceof OINpc) {
            var1.X();
        }

        if (var1.bg == null || !(var1.bg instanceof OEntityPlayer)) {
            super.a(var1, var2);
        }

    }

    public void b(OEntity var1, boolean var2) {
        super.a(var1, var2);
    }

    protected OIChunkProvider b() {
        OIChunkLoader var1 = this.w.a(this.t);
        this.G = new OChunkProviderServer(this, var1, this.t.b());
        return this.G;
    }

    public List c(int var1, int var2, int var3, int var4, int var5, int var6) {
        ArrayList var7 = new ArrayList();

        for (int var8 = 0; var8 < this.c.size(); ++var8) {
            OTileEntity var9 = (OTileEntity) this.c.get(var8);
            if (var9.l >= var1 && var9.m >= var2 && var9.n >= var3 && var9.l < var4 && var9.m < var5 && var9.n < var6) {
                var7.add(var9);
            }
        }

        return var7;
    }

    public boolean a(OEntityPlayer var1, int var2, int var3, int var4) {
        int var5 = OMathHelper.a(var2 - this.x.c());
        int var6 = OMathHelper.a(var4 - this.x.e());
        if (var5 > var6) {
            var6 = var5;
        }

        return var6 > 16 || this.J.h.h(var1.v);
    }

    protected void c() {
        if (this.K == null) {
            this.K = new OIntHashMap();
        }

        super.c();
    }

    protected void c(OEntity var1) {
        super.c(var1);
        this.K.a(var1.bd, var1);
        OEntity[] var2 = var1.bb();
        if (var2 != null) {
            for (int var3 = 0; var3 < var2.length; ++var3) {
                this.K.a(var2[var3].bd, var2[var3]);
            }
        }

    }

    protected void d(OEntity var1) {
        super.d(var1);
        this.K.d(var1.bd);
        OEntity[] var2 = var1.bb();
        if (var2 != null) {
            for (int var3 = 0; var3 < var2.length; ++var3) {
                this.K.d(var2[var3].bd);
            }
        }

    }

    public OEntity a(int var1) {
        return (OEntity) this.K.a(var1);
    }

    public boolean a(OEntity var1) {
        if (super.a(var1)) {
            this.J.h.a(var1.bm, var1.bn, var1.bo, 512.0D, this.t.g, new OPacket71Weather(var1));
            return true;
        } else {
            return false;
        }
    }

    public void a(OEntity var1, byte var2) {
        OPacket38EntityStatus var3 = new OPacket38EntityStatus(var1.bd, var2);
        this.J.b(this.t.g).b(var1, var3);
    }

    public OExplosion a(OEntity var1, double var2, double var4, double var6, float var8, boolean var9) {
        OExplosion var10 = new OExplosion(this, var1, var2, var4, var6, var8);
        var10.a = var9;
        var10.a();
        var10.a(false);
        this.J.h.a(var2, var4, var6, 64.0D, this.t.g, new OPacket60Explosion(var2, var4, var6, var8, var10.g));
        return var10;
    }

    public void e(int var1, int var2, int var3, int var4, int var5) {
        super.e(var1, var2, var3, var4, var5);
        this.J.h.a(var1, var2, var3, 64.0D, this.t.g, new OPacket54PlayNoteBlock(var1, var2, var3, var4, var5));
    }

    public void A() {
        this.w.e();
    }

    protected void i() {
        boolean var1 = this.x();
        super.i();
        if (var1 != this.x()) {
            if (var1) {
                this.J.h.a((new OPacket70Bed(2, 0)));
            } else {
                this.J.h.a((new OPacket70Bed(1, 0)));
            }
        }

    }
}
