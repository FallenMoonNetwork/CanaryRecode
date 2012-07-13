package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

import net.canarymod.Canary;
import net.canarymod.api.entity.CanaryPainting;
import net.canarymod.api.entity.Player;
import net.canarymod.hook.CancelableHook;
import net.canarymod.hook.entity.PaintingHook;
import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityItem;
import net.minecraft.server.OEnumArt;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OWorld;

public class OEntityPainting extends OEntity {

    private int f;
    public int a;
    public int b;
    public int c;
    public int d;
    public OEnumArt e;
    private CanaryPainting painting; //CanaryMod

    public OEntityPainting(OWorld var1) {
        super(var1);
        this.f = 0;
        this.a = 0;
        this.bF = 0.0F;
        this.b(0.5F, 0.5F);
        painting = new CanaryPainting(this);
    }

    public OEntityPainting(OWorld var1, int var2, int var3, int var4, int var5) {
        this(var1);
        this.b = var2;
        this.c = var3;
        this.d = var4;
        ArrayList var6 = new ArrayList();
        OEnumArt[] var7 = OEnumArt.values();
        int var8 = var7.length;

        for (int var9 = 0; var9 < var8; ++var9) {
            OEnumArt var10 = var7[var9];
            this.e = var10;
            this.b(var5);
            if (this.k()) {
                var6.add(var10);
            }
        }

        if (var6.size() > 0) {
            this.e = (OEnumArt) var6.get(this.bS.nextInt(var6.size()));
        }

        this.b(var5);
    }

    @Override
    protected void b() {
    }

    public void b(int var1) {
        this.a = var1;
        this.bu = this.bs = (var1 * 90);
        float var2 = this.e.B;
        float var3 = this.e.C;
        float var4 = this.e.B;
        if (var1 != 0 && var1 != 2) {
            var2 = 0.5F;
        } else {
            var4 = 0.5F;
        }

        var2 /= 32.0F;
        var3 /= 32.0F;
        var4 /= 32.0F;
        float var5 = this.b + 0.5F;
        float var6 = this.c + 0.5F;
        float var7 = this.d + 0.5F;
        float var8 = 0.5625F;
        if (var1 == 0) {
            var7 -= var8;
        }

        if (var1 == 1) {
            var5 -= var8;
        }

        if (var1 == 2) {
            var7 += var8;
        }

        if (var1 == 3) {
            var5 += var8;
        }

        if (var1 == 0) {
            var5 -= this.c(this.e.B);
        }

        if (var1 == 1) {
            var7 += this.c(this.e.B);
        }

        if (var1 == 2) {
            var5 += this.c(this.e.B);
        }

        if (var1 == 3) {
            var7 -= this.c(this.e.B);
        }

        var6 += this.c(this.e.C);
        this.c(var5, var6, var7);
        float var9 = -0.00625F;
        this.bw.c((var5 - var2 - var9), (var6 - var3 - var9), (var7 - var4 - var9), (var5 + var2 + var9), (var6 + var3 + var9), (var7 + var4 + var9));
    }

    private float c(int var1) {
        return var1 == 32 ? 0.5F : (var1 == 64 ? 0.5F : 0.0F);
    }

    @Override
    public void F_() {
        if (this.f++ == 100 && !this.bi.F) {
            this.f = 0;
            if (!this.bE && !this.k()) {
                this.X();
                this.bi.b((new OEntityItem(this.bi, this.bm, this.bn, this.bo, new OItemStack(OItem.ar))));
            }
        }

    }

    public boolean k() {
        if (this.bi.a(this, this.bw).size() > 0) {
            return false;
        } else {
            int var1 = this.e.B / 16;
            int var2 = this.e.C / 16;
            int var3 = this.b;
            int var4 = this.c;
            int var5 = this.d;
            if (this.a == 0) {
                var3 = OMathHelper.b(this.bm - (this.e.B / 32.0F));
            }

            if (this.a == 1) {
                var5 = OMathHelper.b(this.bo - (this.e.B / 32.0F));
            }

            if (this.a == 2) {
                var3 = OMathHelper.b(this.bm - (this.e.B / 32.0F));
            }

            if (this.a == 3) {
                var5 = OMathHelper.b(this.bo - (this.e.B / 32.0F));
            }

            var4 = OMathHelper.b(this.bn - (this.e.C / 32.0F));

            int var7;
            for (int var6 = 0; var6 < var1; ++var6) {
                for (var7 = 0; var7 < var2; ++var7) {
                    OMaterial var8;
                    if (this.a != 0 && this.a != 2) {
                        var8 = this.bi.d(this.b, var4 + var7, var5 + var6);
                    } else {
                        var8 = this.bi.d(var3 + var6, var4 + var7, this.d);
                    }

                    if (!var8.a()) {
                        return false;
                    }
                }
            }

            List var9 = this.bi.b(this, this.bw);

            for (var7 = 0; var7 < var9.size(); ++var7) {
                if (var9.get(var7) instanceof OEntityPainting) {
                    return false;
                }
            }

            return true;
        }
    }

    @Override
    public boolean o_() {
        return true;
    }

    @Override
    public boolean a(ODamageSource var1, int var2) {
        if (!this.bE && !this.bi.F) {
            //CanaryMod onPaintingDestory
            Player player = null;
            if (var1 != null && var1 instanceof OEntityDamageSource && ((OEntityDamageSource) var1).a() instanceof OEntityPlayerMP) {
                player = ((OEntityPlayerMP) ((OEntityDamageSource) var1).a()).getPlayer();
            }
            if(player != null){
                PaintingHook hook = new PaintingHook(painting, player);
                Canary.hooks().callHook(hook);
                if(hook.isCanceled()){
                    return false;
                }
            }
            //CanaryMod end
            this.X();
            this.aW();
            this.bi.b(new OEntityItem(this.bi, this.bm, this.bn, this.bo, new OItemStack(OItem.ar)));
        }

        return true;
    }

    @Override
    public void b(ONBTTagCompound var1) {
        var1.a("Dir", (byte) this.a);
        var1.a("Motive", this.e.A);
        var1.a("TileX", this.b);
        var1.a("TileY", this.c);
        var1.a("TileZ", this.d);
    }

    @Override
    public void a(ONBTTagCompound var1) {
        this.a = var1.d("Dir");
        this.b = var1.f("TileX");
        this.c = var1.f("TileY");
        this.d = var1.f("TileZ");
        String var2 = var1.j("Motive");
        OEnumArt[] var3 = OEnumArt.values();
        int var4 = var3.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            OEnumArt var6 = var3[var5];
            if (var6.A.equals(var2)) {
                this.e = var6;
            }
        }

        if (this.e == null) {
            this.e = OEnumArt.a;
        }

        this.b(this.a);
    }

    @Override
    public void a(double var1, double var3, double var5) {
        if (!this.bi.F && !this.bE && var1 * var1 + var3 * var3 + var5 * var5 > 0.0D) {
            this.X();
            this.bi.b((new OEntityItem(this.bi, this.bm, this.bn, this.bo, new OItemStack(OItem.ar))));
        }

    }

    @Override
    public void b_(double var1, double var3, double var5) {
        if (!this.bi.F && !this.bE && var1 * var1 + var3 * var3 + var5 * var5 > 0.0D) {
            this.X();
            this.bi.b((new OEntityItem(this.bi, this.bm, this.bn, this.bo, new OItemStack(OItem.ar))));
        }

    }
}
