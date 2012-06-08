package net.minecraft.server;

import net.canarymod.api.entity.CanarySquid;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntityWaterMob;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OWorld;

public class OEntitySquid extends OEntityWaterMob {

    public float a = 0.0F;
    public float b = 0.0F;
    public float c = 0.0F;
    public float g = 0.0F;
    public float h = 0.0F;
    public float i = 0.0F;
    public float j = 0.0F;
    public float k = 0.0F;
    private float l = 0.0F;
    private float m = 0.0F;
    private float n = 0.0F;
    private float o = 0.0F;
    private float p = 0.0F;
    private float q = 0.0F;
    
    //CanaryMod squid handler
    private CanarySquid canarySquid;

    public OEntitySquid(OWorld var1) {
        super(var1);
        this.ae = "/mob/squid.png";
        this.b(0.95F, 0.95F);
        this.m = 1.0F / (this.bS.nextFloat() + 1.0F) * 0.2F;
        canarySquid = new CanarySquid(this);
    }

    /**
     * CanaryMod Get the squid handler
     * @return the canarySquid
     */
    public CanarySquid getCanarySquid() {
        return canarySquid;
    }
    @Override
    public int d() {
        return 10;
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
    protected String i() {
        return null;
    }

    @Override
    protected String j() {
        return null;
    }

    @Override
    protected String k() {
        return null;
    }

    @Override
    protected float p() {
        return 0.4F;
    }

    @Override
    protected int f() {
        return 0;
    }

    @Override
    protected void a(boolean var1, int var2) {
        int var3 = this.bS.nextInt(3 + var2) + 1;

        for (int var4 = 0; var4 < var3; ++var4) {
            this.a(new OItemStack(OItem.aV, 1, 0), 0.0F);
        }

    }

    @Override
    public boolean b(OEntityPlayer var1) {
        return super.b(var1);
    }

    @Override
    public boolean aU() {
        return this.bi.a(this.bw.b(0.0D, -0.6000000238418579D, 0.0D), OMaterial.g, this);
    }

    @Override
    public void e() {
        super.e();
        this.b = this.a;
        this.g = this.c;
        this.i = this.h;
        this.k = this.j;
        this.h += this.m;
        if (this.h > 6.2831855F) {
            this.h -= 6.2831855F;
            if (this.bS.nextInt(10) == 0) {
                this.m = 1.0F / (this.bS.nextFloat() + 1.0F) * 0.2F;
            }
        }

        if (this.aU()) {
            float var1;
            if (this.h < 3.1415927F) {
                var1 = this.h / 3.1415927F;
                this.j = OMathHelper.a(var1 * var1 * 3.1415927F) * 3.1415927F * 0.25F;
                if (var1 > 0.75D) {
                    this.l = 1.0F;
                    this.n = 1.0F;
                } else {
                    this.n *= 0.8F;
                }
            } else {
                this.j = 0.0F;
                this.l *= 0.9F;
                this.n *= 0.99F;
            }

            if (!this.bi.F) {
                this.bp = (this.o * this.l);
                this.bq = (this.p * this.l);
                this.br = (this.q * this.l);
            }

            var1 = OMathHelper.a(this.bp * this.bp + this.br * this.br);
            this.V += (-((float) Math.atan2(this.bp, this.br)) * 180.0F / 3.1415927F - this.V) * 0.1F;
            this.bs = this.V;
            this.c += 3.1415927F * this.n * 1.5F;
            this.a += (-((float) Math.atan2(var1, this.bq)) * 180.0F / 3.1415927F - this.a) * 0.1F;
        } else {
            this.j = OMathHelper.e(OMathHelper.a(this.h)) * 3.1415927F * 0.25F;
            if (!this.bi.F) {
                this.bp = 0.0D;
                this.bq -= 0.08D;
                this.bq *= 0.9800000190734863D;
                this.br = 0.0D;
            }

            this.a = (float) (this.a + (-90.0F - this.a) * 0.02D);
        }

    }

    @Override
    public void a(float var1, float var2) {
        this.a(this.bp, this.bq, this.br);
    }

    @Override
    protected void d_() {
        ++this.aV;
        if (this.aV > 100) {
            this.o = this.p = this.q = 0.0F;
        } else if (this.bS.nextInt(50) == 0 || !this.bV || this.o == 0.0F && this.p == 0.0F && this.q == 0.0F) {
            float var1 = this.bS.nextFloat() * 3.1415927F * 2.0F;
            this.o = OMathHelper.b(var1) * 0.2F;
            this.p = -0.1F + this.bS.nextFloat() * 0.2F;
            this.q = OMathHelper.a(var1) * 0.2F;
        }

        this.aG();
    }

    @Override
    public boolean l() {
        return this.bn > 45.0D && this.bn < 63.0D && super.l();
    }
}
