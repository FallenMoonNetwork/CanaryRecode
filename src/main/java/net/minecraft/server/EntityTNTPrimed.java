package net.minecraft.server;

import net.canarymod.api.entity.CanaryTNTPrimed;

public class EntityTNTPrimed extends Entity {

    public int a;
    private EntityLivingBase b;

    public EntityTNTPrimed(World world) {
        super(world);
        this.a = 0;
        this.m = true;
        this.a(0.98F, 0.98F);
        this.N = this.P / 2.0F;
        this.entity = new CanaryTNTPrimed(this); // CanaryMod: Wrap Entity
    }

    public EntityTNTPrimed(World world, double d0, double d1, double d2, EntityLivingBase entitylivingbase) {
        this(world);
        this.b(d0, d1, d2);
        float f0 = (float) (Math.random() * 3.1415927410125732D * 2.0D);

        this.x = (double) (-((float) Math.sin((double) f0)) * 0.02F);
        this.y = 0.20000000298023224D;
        this.z = (double) (-((float) Math.cos((double) f0)) * 0.02F);
        this.a = 80;
        this.r = d0;
        this.s = d1;
        this.t = d2;
        this.b = entitylivingbase;
    }

    protected void a() {}

    protected boolean e_() {
        return false;
    }

    public boolean K() {
        return !this.M;
    }

    public void l_() {
        this.r = this.u;
        this.s = this.v;
        this.t = this.w;
        this.y -= 0.03999999910593033D;
        this.d(this.x, this.y, this.z);
        this.x *= 0.9800000190734863D;
        this.y *= 0.9800000190734863D;
        this.z *= 0.9800000190734863D;
        if (this.F) {
            this.x *= 0.699999988079071D;
            this.z *= 0.699999988079071D;
            this.y *= -0.5D;
        }

        if (this.a-- <= 0) {
            this.w();
            if (!this.q.I) {
                this.d();
            }
        } else {
            this.q.a("smoke", this.u, this.v + 0.5D, this.w, 0.0D, 0.0D, 0.0D);
        }
    }

    private void d() {
        // float f0 = 4.0F;

        this.q.a(this, this.u, this.v, this.w, ((CanaryTNTPrimed) entity).getPower(), true); // CanaryMod: get power level
    }

    protected void b(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("Fuse", (byte) this.a);
    }

    protected void a(NBTTagCompound nbttagcompound) {
        this.a = nbttagcompound.c("Fuse");
    }

    public EntityLivingBase c() {
        return this.b;
    }
}
