package net.minecraft.server;

import net.canarymod.api.entity.living.animal.CanarySquid;

public class EntitySquid extends EntityWaterMob {

    public float d = 0.0F;
    public float e = 0.0F;
    public float f = 0.0F;
    public float g = 0.0F;
    public float h = 0.0F;
    public float i = 0.0F;
    public float j = 0.0F;
    public float bK = 0.0F;
    private float bL = 0.0F;
    private float bM = 0.0F;
    private float bN = 0.0F;
    private float bO = 0.0F;
    private float bP = 0.0F;
    private float bQ = 0.0F;

    public EntitySquid(World world) {
        super(world);
        this.aH = "/mob/squid.png";
        this.a(0.95F, 0.95F);
        this.bM = 1.0F / (this.ab.nextFloat() + 1.0F) * 0.2F;
        this.maxHealth = 10; // CanaryMod: initialize
        this.entity = new CanarySquid(this); // CanaryMod: Wrap Entity
    }

    public int aW() {
        return maxHealth; // CanaryMod: custom Max
    }

    protected String bb() {
        return null;
    }

    protected String bc() {
        return null;
    }

    protected String bd() {
        return null;
    }

    protected float ba() {
        return 0.4F;
    }

    protected int be() {
        return 0;
    }

    protected void a(boolean flag0, int i0) {
        int i1 = this.ab.nextInt(3 + i0) + 1;

        for (int i2 = 0; i2 < i1; ++i2) {
            this.a(new ItemStack(Item.aX, 1, 0), 0.0F);
        }
    }

    public boolean G() {
        return this.q.a(this.E.b(0.0D, -0.6000000238418579D, 0.0D), Material.h, (Entity) this);
    }

    public void c() {
        super.c();
        this.e = this.d;
        this.g = this.f;
        this.i = this.h;
        this.bK = this.j;
        this.h += this.bM;
        if (this.h > 6.2831855F) {
            this.h -= 6.2831855F;
            if (this.ab.nextInt(10) == 0) {
                this.bM = 1.0F / (this.ab.nextFloat() + 1.0F) * 0.2F;
            }
        }

        if (this.G()) {
            float f0;

            if (this.h < 3.1415927F) {
                f0 = this.h / 3.1415927F;
                this.j = MathHelper.a(f0 * f0 * 3.1415927F) * 3.1415927F * 0.25F;
                if ((double) f0 > 0.75D) {
                    this.bL = 1.0F;
                    this.bN = 1.0F;
                } else {
                    this.bN *= 0.8F;
                }
            } else {
                this.j = 0.0F;
                this.bL *= 0.9F;
                this.bN *= 0.99F;
            }

            if (!this.q.I) {
                this.x = (double) (this.bO * this.bL);
                this.y = (double) (this.bP * this.bL);
                this.z = (double) (this.bQ * this.bL);
            }

            f0 = MathHelper.a(this.x * this.x + this.z * this.z);
            this.ay += (-((float) Math.atan2(this.x, this.z)) * 180.0F / 3.1415927F - this.ay) * 0.1F;
            this.A = this.ay;
            this.f += 3.1415927F * this.bN * 1.5F;
            this.d += (-((float) Math.atan2((double) f0, this.y)) * 180.0F / 3.1415927F - this.d) * 0.1F;
        } else {
            this.j = MathHelper.e(MathHelper.a(this.h)) * 3.1415927F * 0.25F;
            if (!this.q.I) {
                this.x = 0.0D;
                this.y -= 0.08D;
                this.y *= 0.9800000190734863D;
                this.z = 0.0D;
            }

            this.d = (float) ((double) this.d + (double) (-90.0F - this.d) * 0.02D);
        }
    }

    public void e(float f0, float f1) {
        this.d(this.x, this.y, this.z);
    }

    protected void bq() {
        ++this.bC;
        if (this.bC > 100) {
            this.bO = this.bP = this.bQ = 0.0F;
        } else if (this.ab.nextInt(50) == 0 || !this.ae || this.bO == 0.0F && this.bP == 0.0F && this.bQ == 0.0F) {
            float f0 = this.ab.nextFloat() * 3.1415927F * 2.0F;

            this.bO = MathHelper.b(f0) * 0.2F;
            this.bP = -0.1F + this.ab.nextFloat() * 0.2F;
            this.bQ = MathHelper.a(f0) * 0.2F;
        }

        this.bn();
    }

    public boolean bv() {
        return this.v > 45.0D && this.v < 63.0D && super.bv();
    }
}
