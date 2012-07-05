package net.minecraft.server;

import java.util.List;
import java.util.Random;

import net.canarymod.Canary;
import net.canarymod.api.CanaryDamageSource;
import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.entity.CanaryEntityLiving;
import net.canarymod.api.entity.EntityLiving;
import net.canarymod.api.world.CanaryDimension;
import net.canarymod.hook.CancelableHook;
import net.canarymod.hook.entity.DamageHook;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockFluid;
import net.minecraft.server.ODamageSource;
import net.minecraft.server.ODataWatcher;
import net.minecraft.server.OEntityItem;
import net.minecraft.server.OEntityLightningBolt;
import net.minecraft.server.OEntityList;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.ONBTTagDouble;
import net.minecraft.server.ONBTTagFloat;
import net.minecraft.server.ONBTTagList;
import net.minecraft.server.OProfiler;
import net.minecraft.server.OStatCollector;
import net.minecraft.server.OStepSound;
import net.minecraft.server.OVec3D;
import net.minecraft.server.OWorld;

public abstract class OEntity {

    private static int a = 0;
    public int bd;
    public double be;
    public boolean bf;
    public OEntity bg;
    public OEntity bh;
    public OWorld bi;
    public double bj;
    public double bk;
    public double bl;
    public double bm; // x pos
    public double bn; // y pos
    public double bo; // z pos
    public double bp;
    public double bq;
    public double br;
    public float bs; // pitch
    public float bt; // rotation
    public float bu;
    public float bv;
    public final OAxisAlignedBB bw;
    public boolean bx;
    public boolean by;
    public boolean bz;
    public boolean bA;
    public boolean bB;
    protected boolean bC;
    public boolean bD;
    public boolean bE;
    public float bF;
    public float bG;
    public float bH;
    public float bI;
    public float bJ;
    public float bK;
    private int b;
    public double bL;
    public double bM;
    public double bN;
    public float bO;
    public float bP;
    public boolean bQ;
    public float bR;
    protected Random bS;
    public int bT;
    public int bU;
    public int c; //CanaryMod private -> public
    protected boolean bV;
    public int bW;
    private boolean d;
    protected boolean bX;
    protected ODataWatcher bY;
    private double e;
    private double f;
    public boolean bZ;
    public int ca;
    public int cb;
    public int cc;
    public boolean cd;
    public boolean ce;
    //CanaryMod entity
    CanaryEntity entity;
    public OEntity(OWorld var1) {
        super();
        this.bd = a++;
        this.be = 1.0D;
        this.bf = false;
        this.bw = OAxisAlignedBB.a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        this.bx = false;
        this.bA = false;
        this.bB = false;
        this.bD = true;
        this.bE = false;
        this.bF = 0.0F;
        this.bG = 0.6F;
        this.bH = 1.8F;
        this.bI = 0.0F;
        this.bJ = 0.0F;
        this.bK = 0.0F;
        this.b = 1;
        this.bO = 0.0F;
        this.bP = 0.0F;
        this.bQ = false;
        this.bR = 0.0F;
        this.bS = new Random();
        this.bT = 0;
        this.bU = 1;
        this.c = 0;
        this.bV = false;
        this.bW = 0;
        this.d = true;
        this.bX = false;
        this.bY = new ODataWatcher();
        this.bZ = false;
        this.bi = var1;
        this.c(0.0D, 0.0D, 0.0D);
        this.bY.a(0, Byte.valueOf((byte) 0));
        this.bY.a(1, Short.valueOf((short) 300));
        this.b();
        this.entity = new CanaryEntity(this);
    }
    /**
     * CanaryMod Get this entities current World (dimension)
     * @return
     */
    public CanaryDimension getDimension() {
        return bi.getCanaryDimension();
    }

    public void setDimension(CanaryDimension dim) {
        this.bi = dim.getHandle();
    }

    public CanaryEntity getCanaryEntity() {
        return entity;
    }
    protected abstract void b();

    public ODataWatcher aP() {
        return this.bY;
    }

    @Override
    public boolean equals(Object var1) {
        return var1 instanceof OEntity ? ((OEntity) var1).bd == this.bd : false;
    }

    @Override
    public int hashCode() {
        return this.bd;
    }

    public void X() {
        this.bE = true;
    }

    protected void b(float var1, float var2) {
        this.bG = var1;
        this.bH = var2;
    }

    protected void c(float var1, float var2) {
        this.bs = var1 % 360.0F;
        this.bt = var2 % 360.0F;
    }

    public void c(double var1, double var3, double var5) {
        this.bm = var1;
        this.bn = var3;
        this.bo = var5;
        float var7 = this.bG / 2.0F;
        float var8 = this.bH;
        this.bw.c(var1 - var7, var3 - this.bF + this.bO, var5 - var7, var1 + var7, var3 - this.bF + this.bO + var8, var5 + var7);
    }

    public void F_() {
        this.aA();
    }

    public void aA() {
        OProfiler.a("entityBaseTick");
        if (this.bh != null && this.bh.bE) {
            this.bh = null;
        }

        ++this.bT;
        this.bI = this.bJ;
        this.bj = this.bm;
        this.bk = this.bn;
        this.bl = this.bo;
        this.bv = this.bt;
        this.bu = this.bs;
        int var3;
        if (this.aZ() && !this.aU()) {
            int var1 = OMathHelper.b(this.bm);
            int var2 = OMathHelper.b(this.bn - 0.20000000298023224D - this.bF);
            var3 = OMathHelper.b(this.bo);
            int var4 = this.bi.a(var1, var2, var3);
            if (var4 > 0) {
                this.bi.a("tilecrack_" + var4, this.bm + (this.bS.nextFloat() - 0.5D) * this.bG, this.bw.b + 0.1D, this.bo + (this.bS.nextFloat() - 0.5D) * this.bG, -this.bp * 4.0D, 1.5D, -this.br * 4.0D);
            }
        }

        if (this.h_()) {
            if (!this.bV && !this.d) {
                float var6 = OMathHelper.a(this.bp * this.bp * 0.20000000298023224D + this.bq * this.bq + this.br * this.br * 0.20000000298023224D) * 0.2F;
                if (var6 > 1.0F) {
                    var6 = 1.0F;
                }

                this.bi.a(this, "random.splash", var6, 1.0F + (this.bS.nextFloat() - this.bS.nextFloat()) * 0.4F);
                float var7 = OMathHelper.b(this.bw.b);

                float var5;
                float var8;
                for (var3 = 0; var3 < 1.0F + this.bG * 20.0F; ++var3) {
                    var8 = (this.bS.nextFloat() * 2.0F - 1.0F) * this.bG;
                    var5 = (this.bS.nextFloat() * 2.0F - 1.0F) * this.bG;
                    this.bi.a("bubble", this.bm + var8, (var7 + 1.0F), this.bo + var5, this.bp, this.bq - (this.bS.nextFloat() * 0.2F), this.br);
                }

                for (var3 = 0; var3 < 1.0F + this.bG * 20.0F; ++var3) {
                    var8 = (this.bS.nextFloat() * 2.0F - 1.0F) * this.bG;
                    var5 = (this.bS.nextFloat() * 2.0F - 1.0F) * this.bG;
                    this.bi.a("splash", this.bm + var8, (var7 + 1.0F), this.bo + var5, this.bp, this.bq, this.br);
                }
            }

            this.bK = 0.0F;
            this.bV = true;
            this.c = 0;
        } else {
            this.bV = false;
        }

        if (this.bi.F) {
            this.c = 0;
        } else if (this.c > 0) {
            if (this.bX) {
                this.c -= 4;
                if (this.c < 0) {
                    this.c = 0;
                }
            } else {
                if (this.c % 20 == 0) {
                    // CanaryMod - fire tick damage. 
                    if (this instanceof OEntityLiving) {
                        DamageHook hook = new DamageHook(null, ((OEntityLiving) this).getCanaryEntityLiving(), new CanaryDamageSource(ODamageSource.c), 1);
                        Canary.hooks().callHook(hook);
                        if (!hook.isCanceled()) {
                            this.a(ODamageSource.c, 1);
                        }
                    }
                    // CanaryMod - end.
                }

                --this.c;
            }
        }

        if (this.aV()) {
            this.aQ();
            this.bK *= 0.5F;
        }

        if (this.bn < -64.0D) {
            this.aI();
        }

        if (!this.bi.F) {
            this.a(0, this.c > 0);
            this.a(2, this.bh != null);
        }

        this.d = false;
        OProfiler.a();
    }

    protected void aQ() {
        if (!this.bX) {
            // CanaryMod - lava damage.
            if (this instanceof OEntityLiving) { 
                DamageHook hook = new DamageHook(null, ((OEntityLiving) this).getCanaryEntityLiving(), new CanaryDamageSource(ODamageSource.d), 4);
                Canary.hooks().callHook(hook);
                if (hook.isCanceled()) {
                    return;
                }
            }
            // CanaryMod - end.
            this.a(ODamageSource.d, 4);
            this.i(15);
        }

    }

    public void i(int var1) {
        int var2 = var1 * 20;
        if (this.c < var2) {
            this.c = var2;
        }

    }

    public void aR() {
        this.c = 0;
    }

    protected void aI() {
        this.X();
    }

    public boolean d(double var1, double var3, double var5) {
        OAxisAlignedBB var7 = this.bw.c(var1, var3, var5);
        List var8 = this.bi.a(this, var7);
        return var8.size() > 0 ? false : !this.bi.c(var7);
    }

    public void a(double var1, double var3, double var5) {
        if (this.bQ) {
            this.bw.d(var1, var3, var5);
            this.bm = (this.bw.a + this.bw.d) / 2.0D;
            this.bn = this.bw.b + this.bF - this.bO;
            this.bo = (this.bw.c + this.bw.f) / 2.0D;
        } else {
            OProfiler.a("move");
            this.bO *= 0.4F;
            double var7 = this.bm;
            double var9 = this.bo;
            if (this.bC) {
                this.bC = false;
                var1 *= 0.25D;
                var3 *= 0.05000000074505806D;
                var5 *= 0.25D;
                this.bp = 0.0D;
                this.bq = 0.0D;
                this.br = 0.0D;
            }

            double var11 = var1;
            double var13 = var3;
            double var15 = var5;
            OAxisAlignedBB var17 = this.bw.b();
            boolean var18 = this.bx && this.aY() && this instanceof OEntityPlayer;
            if (var18) {
                double var19;
                for (var19 = 0.05D; var1 != 0.0D && this.bi.a(this, this.bw.c(var1, -1.0D, 0.0D)).size() == 0; var11 = var1) {
                    if (var1 < var19 && var1 >= -var19) {
                        var1 = 0.0D;
                    } else if (var1 > 0.0D) {
                        var1 -= var19;
                    } else {
                        var1 += var19;
                    }
                }

                for (; var5 != 0.0D && this.bi.a(this, this.bw.c(0.0D, -1.0D, var5)).size() == 0; var15 = var5) {
                    if (var5 < var19 && var5 >= -var19) {
                        var5 = 0.0D;
                    } else if (var5 > 0.0D) {
                        var5 -= var19;
                    } else {
                        var5 += var19;
                    }
                }

                while (var1 != 0.0D && var5 != 0.0D && this.bi.a(this, this.bw.c(var1, -1.0D, var5)).size() == 0) {
                    if (var1 < var19 && var1 >= -var19) {
                        var1 = 0.0D;
                    } else if (var1 > 0.0D) {
                        var1 -= var19;
                    } else {
                        var1 += var19;
                    }

                    if (var5 < var19 && var5 >= -var19) {
                        var5 = 0.0D;
                    } else if (var5 > 0.0D) {
                        var5 -= var19;
                    } else {
                        var5 += var19;
                    }

                    var11 = var1;
                    var15 = var5;
                }
            }

            List var21 = this.bi.a(this, this.bw.a(var1, var3, var5));

            for (int var22 = 0; var22 < var21.size(); ++var22) {
                var3 = ((OAxisAlignedBB) var21.get(var22)).b(this.bw, var3);
            }

            this.bw.d(0.0D, var3, 0.0D);
            if (!this.bD && var13 != var3) {
                var5 = 0.0D;
                var3 = 0.0D;
                var1 = 0.0D;
            }

            boolean var42 = this.bx || var13 != var3 && var13 < 0.0D;

            int var23;
            for (var23 = 0; var23 < var21.size(); ++var23) {
                var1 = ((OAxisAlignedBB) var21.get(var23)).a(this.bw, var1);
            }

            this.bw.d(var1, 0.0D, 0.0D);
            if (!this.bD && var11 != var1) {
                var5 = 0.0D;
                var3 = 0.0D;
                var1 = 0.0D;
            }

            for (var23 = 0; var23 < var21.size(); ++var23) {
                var5 = ((OAxisAlignedBB) var21.get(var23)).c(this.bw, var5);
            }

            this.bw.d(0.0D, 0.0D, var5);
            if (!this.bD && var15 != var5) {
                var5 = 0.0D;
                var3 = 0.0D;
                var1 = 0.0D;
            }

            double var24;
            double var26;
            int var31;
            if (this.bP > 0.0F && var42 && (var18 || this.bO < 0.05F) && (var11 != var1 || var15 != var5)) {
                var24 = var1;
                var26 = var3;
                double var28 = var5;
                var1 = var11;
                var3 = this.bP;
                var5 = var15;
                OAxisAlignedBB var30 = this.bw.b();
                this.bw.b(var17);
                var21 = this.bi.a(this, this.bw.a(var11, var3, var15));

                for (var31 = 0; var31 < var21.size(); ++var31) {
                    var3 = ((OAxisAlignedBB) var21.get(var31)).b(this.bw, var3);
                }

                this.bw.d(0.0D, var3, 0.0D);
                if (!this.bD && var13 != var3) {
                    var5 = 0.0D;
                    var3 = 0.0D;
                    var1 = 0.0D;
                }

                for (var31 = 0; var31 < var21.size(); ++var31) {
                    var1 = ((OAxisAlignedBB) var21.get(var31)).a(this.bw, var1);
                }

                this.bw.d(var1, 0.0D, 0.0D);
                if (!this.bD && var11 != var1) {
                    var5 = 0.0D;
                    var3 = 0.0D;
                    var1 = 0.0D;
                }

                for (var31 = 0; var31 < var21.size(); ++var31) {
                    var5 = ((OAxisAlignedBB) var21.get(var31)).c(this.bw, var5);
                }

                this.bw.d(0.0D, 0.0D, var5);
                if (!this.bD && var15 != var5) {
                    var5 = 0.0D;
                    var3 = 0.0D;
                    var1 = 0.0D;
                }

                if (!this.bD && var13 != var3) {
                    var5 = 0.0D;
                    var3 = 0.0D;
                    var1 = 0.0D;
                } else {
                    var3 = (-this.bP);

                    for (var31 = 0; var31 < var21.size(); ++var31) {
                        var3 = ((OAxisAlignedBB) var21.get(var31)).b(this.bw, var3);
                    }

                    this.bw.d(0.0D, var3, 0.0D);
                }

                if (var24 * var24 + var28 * var28 >= var1 * var1 + var5 * var5) {
                    var1 = var24;
                    var3 = var26;
                    var5 = var28;
                    this.bw.b(var30);
                } else {
                    double var32 = this.bw.b - ((int) this.bw.b);
                    if (var32 > 0.0D) {
                        this.bO = (float) (this.bO + var32 + 0.01D);
                    }
                }
            }

            OProfiler.a();
            OProfiler.a("rest");
            this.bm = (this.bw.a + this.bw.d) / 2.0D;
            this.bn = this.bw.b + this.bF - this.bO;
            this.bo = (this.bw.c + this.bw.f) / 2.0D;
            this.by = var11 != var1 || var15 != var5;
            this.bz = var13 != var3;
            this.bx = var13 != var3 && var13 < 0.0D;
            this.bA = this.by || this.bz;
            this.a(var3, this.bx);
            if (var11 != var1) {
                this.bp = 0.0D;
            }

            if (var13 != var3) {
                this.bq = 0.0D;
            }

            if (var15 != var5) {
                this.br = 0.0D;
            }

            var24 = this.bm - var7;
            var26 = this.bo - var9;
            int var34;
            int var35;
            int var43;
            if (this.g_() && !var18 && this.bh == null) {
                this.bJ = (float) (this.bJ + OMathHelper.a(var24 * var24 + var26 * var26) * 0.6D);
                var34 = OMathHelper.b(this.bm);
                var35 = OMathHelper.b(this.bn - 0.20000000298023224D - this.bF);
                var43 = OMathHelper.b(this.bo);
                var31 = this.bi.a(var34, var35, var43);
                if (var31 == 0 && this.bi.a(var34, var35 - 1, var43) == OBlock.aZ.bO) {
                    var31 = this.bi.a(var34, var35 - 1, var43);
                }

                if (this.bJ > this.b && var31 > 0) {
                    this.b = (int) this.bJ + 1;
                    this.a(var34, var35, var43, var31);
                    OBlock.m[var31].b(this.bi, var34, var35, var43, this);
                }
            }

            var34 = OMathHelper.b(this.bw.a + 0.0010D);
            var35 = OMathHelper.b(this.bw.b + 0.0010D);
            var43 = OMathHelper.b(this.bw.c + 0.0010D);
            var31 = OMathHelper.b(this.bw.d - 0.0010D);
            int var36 = OMathHelper.b(this.bw.e - 0.0010D);
            int var37 = OMathHelper.b(this.bw.f - 0.0010D);
            if (this.bi.a(var34, var35, var43, var31, var36, var37)) {
                for (int var38 = var34; var38 <= var31; ++var38) {
                    for (int var39 = var35; var39 <= var36; ++var39) {
                        for (int var40 = var43; var40 <= var37; ++var40) {
                            int var41 = this.bi.a(var38, var39, var40);
                            if (var41 > 0) {
                                OBlock.m[var41].a(this.bi, var38, var39, var40, this);
                            }
                        }
                    }
                }
            }

            boolean var44 = this.aT();
            if (this.bi.d(this.bw.e(0.0010D, 0.0010D, 0.0010D))) {
                this.a(1);
                if (!var44) {
                    ++this.c;
                    if (this.c == 0) {
                        this.i(8);
                    }
                }
            } else if (this.c <= 0) {
                this.c = -this.bU;
            }

            if (var44 && this.c > 0) {
                this.bi.a(this, "random.fizz", 0.7F, 1.6F + (this.bS.nextFloat() - this.bS.nextFloat()) * 0.4F);
                this.c = -this.bU;
            }

            OProfiler.a();
        }
    }

    protected void a(int var1, int var2, int var3, int var4) {
        OStepSound var5 = OBlock.m[var4].cb;
        if (this.bi.a(var1, var2 + 1, var3) == OBlock.aS.bO) {
            var5 = OBlock.aS.cb;
            this.bi.a(this, var5.c(), var5.a() * 0.15F, var5.b());
        } else if (!OBlock.m[var4].cd.d()) {
            this.bi.a(this, var5.c(), var5.a() * 0.15F, var5.b());
        }

    }

    protected boolean g_() {
        return true;
    }

    protected void a(double var1, boolean var3) {
        if (var3) {
            if (this.bK > 0.0F) {
                if (this instanceof OEntityLiving) {
                    int var4 = OMathHelper.b(this.bm);
                    int var5 = OMathHelper.b(this.bn - 0.20000000298023224D - this.bF);
                    int var6 = OMathHelper.b(this.bo);
                    int var7 = this.bi.a(var4, var5, var6);
                    if (var7 == 0 && this.bi.a(var4, var5 - 1, var6) == OBlock.aZ.bO) {
                        var7 = this.bi.a(var4, var5 - 1, var6);
                    }

                    if (var7 > 0) {
                        OBlock.m[var7].a(this.bi, var4, var5, var6, this, this.bK);
                    }
                }

                this.a(this.bK);
                this.bK = 0.0F;
            }
        } else if (var1 < 0.0D) {
            this.bK = (float) (this.bK - var1);
        }

    }

    public OAxisAlignedBB h() {
        return null;
    }

    protected void a(int var1) {
        if (!this.bX) {
            // CanaryMod - fire damage. 
            if (this instanceof OEntityLiving) {
                DamageHook hook = new DamageHook(null, ((OEntityLiving) this).getCanaryEntityLiving(), new CanaryDamageSource(ODamageSource.b), var1);
                Canary.hooks().callHook(hook);
                if (!hook.isCanceled()) {
                    this.a(ODamageSource.b, var1);
                }
            }
            // CanaryMod - end.

        }

    }

    public final boolean aS() {
        return this.bX;
    }

    protected void a(float var1) {
        if (this.bg != null) {
            this.bg.a(var1);
        }

    }

    public boolean aT() {
        return this.bV || this.bi.y(OMathHelper.b(this.bm), OMathHelper.b(this.bn), OMathHelper.b(this.bo));
    }

    public boolean aU() {
        return this.bV;
    }

    public boolean h_() {
        return this.bi.a(this.bw.b(0.0D, -0.4000000059604645D, 0.0D).e(0.0010D, 0.0010D, 0.0010D), OMaterial.g, this);
    }

    public boolean a(OMaterial var1) {
        double var2 = this.bn + this.B();
        int var4 = OMathHelper.b(this.bm);
        int var5 = OMathHelper.d(OMathHelper.b(var2));
        int var6 = OMathHelper.b(this.bo);
        int var7 = this.bi.a(var4, var5, var6);
        if (var7 != 0 && OBlock.m[var7].cd == var1) {
            float var8 = OBlockFluid.d(this.bi.c(var4, var5, var6)) - 0.11111111F;
            float var9 = (var5 + 1) - var8;
            return var2 < var9;
        } else {
            return false;
        }
    }

    public float B() {
        return 0.0F;
    }

    public boolean aV() {
        return this.bi.a(this.bw.b(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), OMaterial.h);
    }

    public void a(float var1, float var2, float var3) {
        float var4 = OMathHelper.c(var1 * var1 + var2 * var2);
        if (var4 >= 0.01F) {
            if (var4 < 1.0F) {
                var4 = 1.0F;
            }

            var4 = var3 / var4;
            var1 *= var4;
            var2 *= var4;
            float var5 = OMathHelper.a(this.bs * 3.1415927F / 180.0F);
            float var6 = OMathHelper.b(this.bs * 3.1415927F / 180.0F);
            this.bp += (var1 * var6 - var2 * var5);
            this.br += (var2 * var6 + var1 * var5);
        }
    }

    public float b(float var1) {
        int var2 = OMathHelper.b(this.bm);
        int var3 = OMathHelper.b(this.bo);
        if (this.bi.i(var2, 0, var3)) {
            double var4 = (this.bw.e - this.bw.b) * 0.66D;
            int var6 = OMathHelper.b(this.bn - this.bF + var4);
            return this.bi.p(var2, var6, var3);
        } else {
            return 0.0F;
        }
    }

    public void a(OWorld var1) {
        this.bi = var1;
    }

    public void b(double var1, double var3, double var5, float var7, float var8) {
        this.bj = this.bm = var1;
        this.bk = this.bn = var3;
        this.bl = this.bo = var5;
        this.bu = this.bs = var7;
        this.bv = this.bt = var8;
        this.bO = 0.0F;
        double var9 = (this.bu - var7);
        if (var9 < -180.0D) {
            this.bu += 360.0F;
        }

        if (var9 >= 180.0D) {
            this.bu -= 360.0F;
        }

        this.c(this.bm, this.bn, this.bo);
        this.c(var7, var8);
    }

    public void c(double var1, double var3, double var5, float var7, float var8) {
        this.bL = this.bj = this.bm = var1;
        this.bM = this.bk = this.bn = var3 + this.bF;
        this.bN = this.bl = this.bo = var5;
        this.bs = var7;
        this.bt = var8;
        this.c(this.bm, this.bn, this.bo);
    }

    public float i(OEntity var1) {
        float var2 = (float) (this.bm - var1.bm);
        float var3 = (float) (this.bn - var1.bn);
        float var4 = (float) (this.bo - var1.bo);
        return OMathHelper.c(var2 * var2 + var3 * var3 + var4 * var4);
    }

    public double e(double var1, double var3, double var5) {
        double var7 = this.bm - var1;
        double var9 = this.bn - var3;
        double var11 = this.bo - var5;
        return var7 * var7 + var9 * var9 + var11 * var11;
    }

    public double f(double var1, double var3, double var5) {
        double var7 = this.bm - var1;
        double var9 = this.bn - var3;
        double var11 = this.bo - var5;
        return OMathHelper.a(var7 * var7 + var9 * var9 + var11 * var11);
    }

    public double j(OEntity var1) {
        double var2 = this.bm - var1.bm;
        double var4 = this.bn - var1.bn;
        double var6 = this.bo - var1.bo;
        return var2 * var2 + var4 * var4 + var6 * var6;
    }

    public void a_(OEntityPlayer var1) {
    }

    public void k(OEntity var1) {
        if (var1.bg != this && var1.bh != this) {
            double var2 = var1.bm - this.bm;
            double var4 = var1.bo - this.bo;
            double var6 = OMathHelper.a(var2, var4);
            if (var6 >= 0.009999999776482582D) {
                var6 = OMathHelper.a(var6);
                var2 /= var6;
                var4 /= var6;
                double var8 = 1.0D / var6;
                if (var8 > 1.0D) {
                    var8 = 1.0D;
                }

                var2 *= var8;
                var4 *= var8;
                var2 *= 0.05000000074505806D;
                var4 *= 0.05000000074505806D;
                var2 *= (1.0F - this.bR);
                var4 *= (1.0F - this.bR);
                this.b_(-var2, 0.0D, -var4);
                var1.b_(var2, 0.0D, var4);
            }

        }
    }

    public void b_(double var1, double var3, double var5) {
        this.bp += var1;
        this.bq += var3;
        this.br += var5;
        this.ce = true;
    }

    protected void aW() {
        this.bB = true;
    }

    public boolean a(ODamageSource var1, int var2) {
        this.aW();
        return false;
    }

    public boolean o_() {
        return false;
    }

    public boolean e_() {
        return false;
    }

    public void b(OEntity var1, int var2) {
    }

    public boolean c(ONBTTagCompound var1) {
        String var2 = this.aX();
        if (!this.bE && var2 != null) {
            var1.a("id", var2);
            this.d(var1);
            return true;
        } else {
            return false;
        }
    }

    public void d(ONBTTagCompound var1) {
        var1.a("Pos", this.a(new double[] { this.bm, this.bn + this.bO, this.bo }));
        var1.a("Motion", this.a(new double[] { this.bp, this.bq, this.br }));
        var1.a("Rotation", this.a(new float[] { this.bs, this.bt }));
        var1.a("FallDistance", this.bK);
        var1.a("Fire", (short) this.c);
        var1.a("Air", (short) this.ba());
        var1.a("OnGround", this.bx);
        this.b(var1);
    }

    public void e(ONBTTagCompound var1) {
        ONBTTagList var2 = var1.n("Pos");
        ONBTTagList var3 = var1.n("Motion");
        ONBTTagList var4 = var1.n("Rotation");
        this.bp = ((ONBTTagDouble) var3.a(0)).a;
        this.bq = ((ONBTTagDouble) var3.a(1)).a;
        this.br = ((ONBTTagDouble) var3.a(2)).a;
        if (Math.abs(this.bp) > 10.0D) {
            this.bp = 0.0D;
        }

        if (Math.abs(this.bq) > 10.0D) {
            this.bq = 0.0D;
        }

        if (Math.abs(this.br) > 10.0D) {
            this.br = 0.0D;
        }

        this.bj = this.bL = this.bm = ((ONBTTagDouble) var2.a(0)).a;
        this.bk = this.bM = this.bn = ((ONBTTagDouble) var2.a(1)).a;
        this.bl = this.bN = this.bo = ((ONBTTagDouble) var2.a(2)).a;
        this.bu = this.bs = ((ONBTTagFloat) var4.a(0)).a;
        this.bv = this.bt = ((ONBTTagFloat) var4.a(1)).a;
        this.bK = var1.h("FallDistance");
        this.c = var1.e("Fire");
        this.k(var1.e("Air"));
        this.bx = var1.o("OnGround");
        this.c(this.bm, this.bn, this.bo);
        this.c(this.bs, this.bt);
        this.a(var1);
    }

    protected final String aX() {
        return OEntityList.b(this);
    }

    protected abstract void a(ONBTTagCompound var1);

    protected abstract void b(ONBTTagCompound var1);

    protected ONBTTagList a(double... var1) {
        ONBTTagList var2 = new ONBTTagList();
        double[] var3 = var1;
        int var4 = var1.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            double var6 = var3[var5];
            var2.a((new ONBTTagDouble((String) null, var6)));
        }

        return var2;
    }

    protected ONBTTagList a(float... var1) {
        ONBTTagList var2 = new ONBTTagList();
        float[] var3 = var1;
        int var4 = var1.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            float var6 = var3[var5];
            var2.a((new ONBTTagFloat((String) null, var6)));
        }

        return var2;
    }

    public OEntityItem b(int var1, int var2) {
        return this.a(var1, var2, 0.0F);
    }

    public OEntityItem a(int var1, int var2, float var3) {
        return this.a(new OItemStack(var1, var2, 0), var3);
    }

    public OEntityItem a(OItemStack var1, float var2) {
        OEntityItem var3 = new OEntityItem(this.bi, this.bm, this.bn + var2, this.bo, var1);
        var3.c = 10;
        this.bi.b(var3);
        return var3;
    }

    public boolean aE() {
        return !this.bE;
    }

    public boolean Y() {
        for (int var1 = 0; var1 < 8; ++var1) {
            float var2 = (((var1 >> 0) % 2) - 0.5F) * this.bG * 0.8F;
            float var3 = (((var1 >> 1) % 2) - 0.5F) * 0.1F;
            float var4 = (((var1 >> 2) % 2) - 0.5F) * this.bG * 0.8F;
            int var5 = OMathHelper.b(this.bm + var2);
            int var6 = OMathHelper.b(this.bn + this.B() + var3);
            int var7 = OMathHelper.b(this.bo + var4);
            if (this.bi.e(var5, var6, var7)) {
                return true;
            }
        }

        return false;
    }

    public boolean b(OEntityPlayer var1) {
        return false;
    }

    public OAxisAlignedBB b_(OEntity var1) {
        return null;
    }

    public void R() {
        if (this.bh.bE) {
            this.bh = null;
        } else {
            this.bp = 0.0D;
            this.bq = 0.0D;
            this.br = 0.0D;
            this.F_();
            if (this.bh != null) {
                this.bh.i_();
                this.f += (this.bh.bs - this.bh.bu);

                for (this.e += (this.bh.bt - this.bh.bv); this.f >= 180.0D; this.f -= 360.0D) {
                    ;
                }

                while (this.f < -180.0D) {
                    this.f += 360.0D;
                }

                while (this.e >= 180.0D) {
                    this.e -= 360.0D;
                }

                while (this.e < -180.0D) {
                    this.e += 360.0D;
                }

                double var1 = this.f * 0.5D;
                double var3 = this.e * 0.5D;
                float var5 = 10.0F;
                if (var1 > var5) {
                    var1 = var5;
                }

                if (var1 < (-var5)) {
                    var1 = (-var5);
                }

                if (var3 > var5) {
                    var3 = var5;
                }

                if (var3 < (-var5)) {
                    var3 = (-var5);
                }

                this.f -= var1;
                this.e -= var3;
                this.bs = (float) (this.bs + var1);
                this.bt = (float) (this.bt + var3);
            }
        }
    }

    public void i_() {
        this.bg.c(this.bm, this.bn + this.x_() + this.bg.W(), this.bo);
    }

    public double W() {
        return this.bF;
    }

    public double x_() {
        return this.bH * 0.75D;
    }

    public void b(OEntity var1) {
        this.e = 0.0D;
        this.f = 0.0D;
        if (var1 == null) {
            if (this.bh != null) {
                this.c(this.bh.bm, this.bh.bw.b + this.bh.bH, this.bh.bo, this.bs, this.bt);
                this.bh.bg = null;
            }

            this.bh = null;
        } else if (this.bh == var1) {
            this.bh.bg = null;
            this.bh = null;
            this.c(var1.bm, var1.bw.b + var1.bH, var1.bo, this.bs, this.bt);
        } else {
            if (this.bh != null) {
                this.bh.bg = null;
            }

            if (var1.bg != null) {
                var1.bg.bh = null;
            }

            this.bh = var1;
            var1.bg = this;
        }
    }

    public float j_() {
        return 0.1F;
    }

    public OVec3D aJ() {
        return null;
    }

    public void ad() {
    }

    public OItemStack[] y() {
        return null;
    }

    public boolean B_() {
        return this.c > 0 || this.j(0);
    }

    public boolean aY() {
        return this.j(1);
    }

    public void g(boolean var1) {
        this.a(1, var1);
    }

    public boolean aZ() {
        return this.j(3);
    }

    public void h(boolean var1) {
        this.a(3, var1);
    }

    public void i(boolean var1) {
        this.a(4, var1);
    }

    protected boolean j(int var1) {
        return (this.bY.a(0) & 1 << var1) != 0;
    }

    protected void a(int var1, boolean var2) {
        byte var3 = this.bY.a(0);
        if (var2) {
            this.bY.b(0, Byte.valueOf((byte) (var3 | 1 << var1)));
        } else {
            this.bY.b(0, Byte.valueOf((byte) (var3 & ~(1 << var1))));
        }

    }

    public int ba() {
        return this.bY.b(1);
    }

    public void k(int var1) {
        this.bY.b(1, Short.valueOf((short) var1));
    }

    public void a(OEntityLightningBolt var1) {
        // CanaryMod - lightning damage. 
        // Note: At the moment this damage is counted as fire damage. 
        if (this instanceof OEntityLiving) {
            DamageHook hook = new DamageHook(null, ((OEntityLiving) this).getCanaryEntityLiving(), new CanaryDamageSource(ODamageSource.b), 5);
            Canary.hooks().callHook(hook);
            if (hook.isCanceled()) {
                return;
            }
        }
        // CanaryMod - end.
        this.a(5);
        ++this.c;
        if (this.c == 0) {
            this.i(8);
        }

    }

    public void c(OEntityLiving var1) {
    }

    protected boolean g(double var1, double var3, double var5) {
        int var7 = OMathHelper.b(var1);
        int var8 = OMathHelper.b(var3);
        int var9 = OMathHelper.b(var5);
        double var10 = var1 - var7;
        double var12 = var3 - var8;
        double var14 = var5 - var9;
        if (this.bi.e(var7, var8, var9)) {
            boolean var16 = !this.bi.e(var7 - 1, var8, var9);
            boolean var17 = !this.bi.e(var7 + 1, var8, var9);
            boolean var18 = !this.bi.e(var7, var8 - 1, var9);
            boolean var19 = !this.bi.e(var7, var8 + 1, var9);
            boolean var20 = !this.bi.e(var7, var8, var9 - 1);
            boolean var21 = !this.bi.e(var7, var8, var9 + 1);
            byte var22 = -1;
            double var23 = 9999.0D;
            if (var16 && var10 < var23) {
                var23 = var10;
                var22 = 0;
            }

            if (var17 && 1.0D - var10 < var23) {
                var23 = 1.0D - var10;
                var22 = 1;
            }

            if (var18 && var12 < var23) {
                var23 = var12;
                var22 = 2;
            }

            if (var19 && 1.0D - var12 < var23) {
                var23 = 1.0D - var12;
                var22 = 3;
            }

            if (var20 && var14 < var23) {
                var23 = var14;
                var22 = 4;
            }

            if (var21 && 1.0D - var14 < var23) {
                var23 = 1.0D - var14;
                var22 = 5;
            }

            float var25 = this.bS.nextFloat() * 0.2F + 0.1F;
            if (var22 == 0) {
                this.bp = (-var25);
            }

            if (var22 == 1) {
                this.bp = var25;
            }

            if (var22 == 2) {
                this.bq = (-var25);
            }

            if (var22 == 3) {
                this.bq = var25;
            }

            if (var22 == 4) {
                this.br = (-var25);
            }

            if (var22 == 5) {
                this.br = var25;
            }

            return true;
        } else {
            return false;
        }
    }

    public void u() {
        this.bC = true;
        this.bK = 0.0F;
    }

    public String s() {
        String var1 = OEntityList.b(this);
        if (var1 == null) {
            var1 = "generic";
        }

        return OStatCollector.a("entity." + var1 + ".name");
    }

    public OEntity[] bb() {
        return null;
    }

    public boolean a_(OEntity var1) {
        return this == var1;
    }

    public float ar() {
        return 0.0F;
    }

    public boolean k_() {
        return true;
    }

}
