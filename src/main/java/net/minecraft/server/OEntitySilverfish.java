package net.minecraft.server;

import net.canarymod.api.entity.CanarySilverfish;
import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockSilverfish;
import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityDamageSource;
import net.minecraft.server.OEntityMob;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEnumCreatureAttribute;
import net.minecraft.server.OFacing;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OWorld;

public class OEntitySilverfish extends OEntityMob {

    private int a;
    private CanarySilverfish canarySilverfish;

    public OEntitySilverfish(OWorld var1) {
        super(var1);
        this.ae = "/mob/silverfish.png";
        this.b(0.3F, 0.7F);
        this.bb = 0.6F;
        this.c = 1;
        canarySilverfish = new CanarySilverfish(this);
    }

    /**
     * CanaryMod Get silverfish handler
     * @return the canarySilverfish
     */
    public CanarySilverfish getCanarySilverfish() {
        return canarySilverfish;
    }


    @Override
    public int d() {
        return 8;
    }

    @Override
    protected boolean g_() {
        return false;
    }

    @Override
    protected OEntity o() {
        double var1 = 8.0D;
        return this.bi.b(this, var1);
    }

    @Override
    protected String i() {
        return "mob.silverfish.say";
    }

    @Override
    protected String j() {
        return "mob.silverfish.hit";
    }

    @Override
    protected String k() {
        return "mob.silverfish.kill";
    }

    @Override
    public boolean a(ODamageSource var1, int var2) {
        if (this.a <= 0 && var1 instanceof OEntityDamageSource) {
            this.a = 20;
        }

        return super.a(var1, var2);
    }

    @Override
    protected void a(OEntity var1, float var2) {
        if (this.aw <= 0 && var2 < 1.2F && var1.bw.e > this.bw.b && var1.bw.b < this.bw.e) {
            this.aw = 20;
            var1.a(ODamageSource.a(this), this.c);
        }

    }

    @Override
    protected void a(int var1, int var2, int var3, int var4) {
        this.bi.a(this, "mob.silverfish.step", 1.0F, 1.0F);
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
        return 0;
    }

    @Override
    public void F_() {
        this.V = this.bs;
        super.F_();
    }

    @Override
    protected void d_() {
        super.d_();
        if (!this.bi.F) {
            int var1;
            int var2;
            int var3;
            int var5;
            if (this.a > 0) {
                --this.a;
                if (this.a == 0) {
                    var1 = OMathHelper.b(this.bm);
                    var2 = OMathHelper.b(this.bn);
                    var3 = OMathHelper.b(this.bo);
                    boolean var4 = false;

                    for (var5 = 0; !var4 && var5 <= 5 && var5 >= -5; var5 = var5 <= 0 ? 1 - var5 : 0 - var5) {
                        for (int var6 = 0; !var4 && var6 <= 10 && var6 >= -10; var6 = var6 <= 0 ? 1 - var6 : 0 - var6) {
                            for (int var7 = 0; !var4 && var7 <= 10 && var7 >= -10; var7 = var7 <= 0 ? 1 - var7 : 0 - var7) {
                                int var8 = this.bi.a(var1 + var6, var2 + var5, var3 + var7);
                                if (var8 == OBlock.bl.bO) {
                                    this.bi.f(2001, var1 + var6, var2 + var5, var3 + var7, OBlock.bl.bO + (this.bi.c(var1 + var6, var2 + var5, var3 + var7) << 12));
                                    this.bi.e(var1 + var6, var2 + var5, var3 + var7, 0);
                                    OBlock.bl.c(this.bi, var1 + var6, var2 + var5, var3 + var7, 0);
                                    if (this.bS.nextBoolean()) {
                                        var4 = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (this.d == null && !this.H()) {
                var1 = OMathHelper.b(this.bm);
                var2 = OMathHelper.b(this.bn + 0.5D);
                var3 = OMathHelper.b(this.bo);
                int var9 = this.bS.nextInt(6);
                var5 = this.bi.a(var1 + OFacing.b[var9], var2 + OFacing.c[var9], var3 + OFacing.d[var9]);
                if (OBlockSilverfish.d(var5)) {
                    this.bi.b(var1 + OFacing.b[var9], var2 + OFacing.c[var9], var3 + OFacing.d[var9], OBlock.bl.bO, OBlockSilverfish.e(var5));
                    this.aC();
                    this.X();
                } else {
                    this.G();
                }
            } else if (this.d != null && !this.H()) {
                this.d = null;
            }

        }
    }

    @Override
    public float a(int var1, int var2, int var3) {
        return this.bi.a(var1, var2 - 1, var3) == OBlock.t.bO ? 10.0F : super.a(var1, var2, var3);
    }

    @Override
    protected boolean C() {
        return true;
    }

    @Override
    public boolean l() {
        if (super.l()) {
            OEntityPlayer var1 = this.bi.a(this, 5.0D);
            return var1 == null;
        } else {
            return false;
        }
    }

    @Override
    public OEnumCreatureAttribute v() {
        return OEnumCreatureAttribute.c;
    }
}
