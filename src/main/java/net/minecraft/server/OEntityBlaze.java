package net.minecraft.server;

import net.canarymod.api.entity.CanaryBlaze;
import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityMob;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntitySmallFireball;
import net.minecraft.server.OItem;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OWorld;

public class OEntityBlaze extends OEntityMob {

    private float a = 0.5F;
    private int b;
    private int g;
    //CanaMod blaze handler
    private CanaryBlaze canaryBlaze;

    public OEntityBlaze(OWorld var1) {
        super(var1);
        this.ae = "/mob/fire.png";
        this.bX = true;
        this.c = 6;
        this.aA = 10;
        canaryBlaze = new CanaryBlaze(this);
    }

    /**
     * CanaryMod Get Blaze handleer
     * @return
     */
    public CanaryBlaze getCanaryBlaze() {
        return canaryBlaze;
    }
    @Override
    public int d() {
        return 20;
    }

    @Override
    protected void b() {
        super.b();
        this.bY.a(16, new Byte((byte) 0));
    }

    @Override
    protected String i() {
        return "mob.blaze.breathe";
    }

    @Override
    protected String j() {
        return "mob.blaze.hit";
    }

    @Override
    protected String k() {
        return "mob.blaze.death";
    }

    @Override
    public boolean a(ODamageSource var1, int var2) {
        return super.a(var1, var2);
    }

    @Override
    public void a(ODamageSource var1) {
        super.a(var1);
    }

    @Override
    public float b(float var1) {
        return 1.0F;
    }

    @Override
    public void e() {
        if (!this.bi.F) {
            if (this.aT()) {
                this.a(ODamageSource.f, 1);
            }

            --this.b;
            if (this.b <= 0) {
                this.b = 100;
                this.a = 0.5F + (float) this.bS.nextGaussian() * 3.0F;
            }

            if (this.I() != null && this.I().bn + this.I().B() > this.bn + this.B() + this.a) {
                this.bq += (0.30000001192092896D - this.bq) * 0.30000001192092896D;
            }
        }

        if (this.bS.nextInt(24) == 0) {
            this.bi.a(this.bm + 0.5D, this.bn + 0.5D, this.bo + 0.5D, "fire.fire", 1.0F + this.bS.nextFloat(), this.bS.nextFloat() * 0.7F + 0.3F);
        }

        if (!this.bx && this.bq < 0.0D) {
            this.bq *= 0.6D;
        }

        for (int var1 = 0; var1 < 2; ++var1) {
            this.bi.a("largesmoke", this.bm + (this.bS.nextDouble() - 0.5D) * this.bG, this.bn + this.bS.nextDouble() * this.bH, this.bo + (this.bS.nextDouble() - 0.5D) * this.bG, 0.0D, 0.0D, 0.0D);
        }

        super.e();
    }

    @Override
    protected void a(OEntity var1, float var2) {
        if (this.aw <= 0 && var2 < 2.0F && var1.bw.e > this.bw.b && var1.bw.b < this.bw.e) {
            this.aw = 20;
            this.a(var1);
        } else if (var2 < 30.0F) {
            double var3 = var1.bm - this.bm;
            double var5 = var1.bw.b + (var1.bH / 2.0F) - (this.bn + (this.bH / 2.0F));
            double var7 = var1.bo - this.bo;
            if (this.aw == 0) {
                ++this.g;
                if (this.g == 1) {
                    this.aw = 60;
                    this.a(true);
                } else if (this.g <= 4) {
                    this.aw = 6;
                } else {
                    this.aw = 100;
                    this.g = 0;
                    this.a(false);
                }

                if (this.g > 1) {
                    float var9 = OMathHelper.c(var2) * 0.5F;
                    this.bi.a((OEntityPlayer) null, 1009, (int) this.bm, (int) this.bn, (int) this.bo, 0);

                    for (int var10 = 0; var10 < 1; ++var10) {
                        OEntitySmallFireball var11 = new OEntitySmallFireball(this.bi, this, var3 + this.bS.nextGaussian() * var9, var5, var7 + this.bS.nextGaussian() * var9);
                        var11.bn = this.bn + (this.bH / 2.0F) + 0.5D;
                        this.bi.b(var11);
                    }
                }
            }

            this.bs = (float) (Math.atan2(var7, var3) * 180.0D / 3.1415927410125732D) - 90.0F;
            this.e = true;
        }

    }

    @Override
    protected void a(float var1) {
    }

    @Override
    public void b(ONBTTagCompound var1) {
        super.b(var1);
    }

    @Override
    public void a(ONBTTagCompound var1) {
        super.a(var1);
    }

    @Override
    protected int f() {
        return OItem.bn.bP;
    }

    @Override
    public boolean B_() {
        return this.A();
    }

    @Override
    protected void a(boolean var1, int var2) {
        if (var1) {
            int var3 = this.bS.nextInt(2 + var2);

            for (int var4 = 0; var4 < var3; ++var4) {
                this.b(OItem.bn.bP, 1);
            }
        }

    }

    public boolean A() {
        return (this.bY.a(16) & 1) != 0;
    }

    public void a(boolean var1) {
        byte var2 = this.bY.a(16);
        if (var1) {
            var2 = (byte) (var2 | 1);
        } else {
            var2 &= -2;
        }

        this.bY.b(16, Byte.valueOf(var2));
    }

    @Override
    protected boolean C() {
        return true;
    }
}
