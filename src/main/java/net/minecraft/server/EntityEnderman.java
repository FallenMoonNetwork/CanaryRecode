package net.minecraft.server;

import java.util.Arrays;
import net.canarymod.Canary;
import net.canarymod.api.entity.living.monster.CanaryEnderman;
import net.canarymod.hook.entity.EndermanDropBlockHook;
import net.canarymod.hook.entity.EndermanPickupBlockHook;

public class EntityEnderman extends EntityMob {

    private static boolean[] d = new boolean[256];
    private int e = 0;
    private int f = 0;

    public EntityEnderman(World world) {
        super(world);
        this.aH = "/mob/enderman.png";
        this.bI = 0.2F;
        this.a(0.6F, 2.9F);
        this.Y = 1.0F;
        this.maxHealth = 40; // CanaryMod: initialize
        this.entity = new CanaryEnderman(this); // CanaryMod: Wrap Entity
    }

    public int aW() {
        return maxHealth; // CanaryMod: custom Max
    }

    protected void a() {
        super.a();
        this.ah.a(16, new Byte((byte) 0));
        this.ah.a(17, new Byte((byte) 0));
        this.ah.a(18, new Byte((byte) 0));
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("carried", (short) this.o());
        nbttagcompound.a("carriedData", (short) this.p());
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.a(nbttagcompound.d("carried"));
        this.s(nbttagcompound.d("carriedData"));
    }

    protected Entity j() {
        EntityPlayer entityplayer = this.q.b(this, 64.0D);

        if (entityplayer != null) {
            if (this.e(entityplayer)) {
                if (this.f == 0) {
                    this.q.a((Entity) entityplayer, "mob.endermen.stare", 1.0F, 1.0F);
                }

                if (this.f++ == 5) {
                    this.f = 0;
                    this.a(true);
                    return entityplayer;
                }
            } else {
                this.f = 0;
            }
        }

        return null;
    }

    private boolean e(EntityPlayer entityplayer) {
        ItemStack itemstack = entityplayer.bK.b[3];

        if (itemstack != null && itemstack.c == Block.be.cz) {
            return false;
        } else {
            Vec3 vec3 = entityplayer.i(1.0F).a();
            Vec3 vec31 = this.q.T().a(this.u - entityplayer.u, this.E.b + (double) (this.P / 2.0F) - (entityplayer.v + (double) entityplayer.e()), this.w - entityplayer.w);
            double d0 = vec31.b();

            vec31 = vec31.a();
            double d1 = vec3.b(vec31);

            return d1 > 1.0D - 0.025D / d0 ? entityplayer.n(this) : false;
        }
    }

    public void c() {
        if (this.F()) {
            this.a(DamageSource.e, 1);
        }

        this.bI = this.a_ != null ? 6.5F : 0.3F;
        int i0;

        if (!this.q.I && this.q.M().b("mobGriefing")) {
            int i1;
            int i2;
            int i3;

            if (this.o() == 0) {
                if (this.ab.nextInt(20) == 0) {
                    i0 = MathHelper.c(this.u - 2.0D + this.ab.nextDouble() * 4.0D);
                    i1 = MathHelper.c(this.v + this.ab.nextDouble() * 3.0D);
                    i2 = MathHelper.c(this.w - 2.0D + this.ab.nextDouble() * 4.0D);
                    i3 = this.q.a(i0, i1, i2);
                    if (d[i3]) {
                        // CanaryMod: call EndermanPickupBlockHook
                        EndermanPickupBlockHook hook = new EndermanPickupBlockHook((CanaryEnderman) entity, ((CanaryEnderman) entity).getWorld().getBlockAt(i0, i1, i2));

                        Canary.hooks().callHook(hook);
                        if (hook.isCanceled()) {
                            return;
                        }
                        //
                        
                        this.a(this.q.a(i0, i1, i2));
                        this.s(this.q.h(i0, i1, i2));
                        this.q.c(i0, i1, i2, 0);
                    }
                }
            } else if (this.ab.nextInt(2000) == 0) {
                i0 = MathHelper.c(this.u - 1.0D + this.ab.nextDouble() * 2.0D);
                i1 = MathHelper.c(this.v + this.ab.nextDouble() * 2.0D);
                i2 = MathHelper.c(this.w - 1.0D + this.ab.nextDouble() * 2.0D);
                i3 = this.q.a(i0, i1, i2);
                int i4 = this.q.a(i0, i1 - 1, i2);

                if (i3 == 0 && i4 > 0 && Block.r[i4].b()) {
                    // CanaryMod: call EndermanDropBlockHook
                    EndermanDropBlockHook hook = new EndermanDropBlockHook((CanaryEnderman)entity, ((CanaryEnderman)entity).getWorld().getBlockAt(i0, i1, i2));

                    Canary.hooks().callHook(hook);
                    if (hook.isCanceled()) {
                        return;
                    }
                    //
                    
                    this.q.f(i0, i1, i2, this.o(), this.p(), 3);
                    this.a(0);
                }
            }
        }

        for (i0 = 0; i0 < 2; ++i0) {
            this.q.a("portal", this.u + (this.ab.nextDouble() - 0.5D) * (double) this.O, this.v + this.ab.nextDouble() * (double) this.P - 0.25D, this.w + (this.ab.nextDouble() - 0.5D) * (double) this.O, (this.ab.nextDouble() - 0.5D) * 2.0D, -this.ab.nextDouble(), (this.ab.nextDouble() - 0.5D) * 2.0D);
        }

        if (this.q.u() && !this.q.I) {
            float f0 = this.c(1.0F);

            if (f0 > 0.5F && this.q.l(MathHelper.c(this.u), MathHelper.c(this.v), MathHelper.c(this.w)) && this.ab.nextFloat() * 30.0F < (f0 - 0.4F) * 2.0F) {
                this.a_ = null;
                this.a(false);
                this.m();
            }
        }

        if (this.F() || this.ae()) {
            this.a_ = null;
            this.a(false);
            this.m();
        }

        this.bG = false;
        if (this.a_ != null) {
            this.a(this.a_, 100.0F, 100.0F);
        }

        if (!this.q.I && this.R()) {
            if (this.a_ != null) {
                if (this.a_ instanceof EntityPlayer && this.e((EntityPlayer) this.a_)) {
                    this.bD = this.bE = 0.0F;
                    this.bI = 0.0F;
                    if (this.a_.e((Entity) this) < 16.0D) {
                        this.m();
                    }

                    this.e = 0;
                } else if (this.a_.e((Entity) this) > 256.0D && this.e++ >= 30 && this.p(this.a_)) {
                    this.e = 0;
                }
            } else {
                this.a(false);
                this.e = 0;
            }
        }

        super.c();
    }

    public boolean m() { // CanaryMod: protected => public
        double d0 = this.u + (this.ab.nextDouble() - 0.5D) * 64.0D;
        double d1 = this.v + (double) (this.ab.nextInt(64) - 32);
        double d2 = this.w + (this.ab.nextDouble() - 0.5D) * 64.0D;

        return this.j(d0, d1, d2);
    }

    protected boolean p(Entity entity) {
        Vec3 vec3 = this.q.T().a(this.u - entity.u, this.E.b + (double) (this.P / 2.0F) - entity.v + (double) entity.e(), this.w - entity.w);

        vec3 = vec3.a();
        double d0 = 16.0D;
        double d1 = this.u + (this.ab.nextDouble() - 0.5D) * 8.0D - vec3.c * d0;
        double d2 = this.v + (double) (this.ab.nextInt(16) - 8) - vec3.d * d0;
        double d3 = this.w + (this.ab.nextDouble() - 0.5D) * 8.0D - vec3.e * d0;

        return this.j(d1, d2, d3);
    }

    protected boolean j(double d0, double d1, double d2) {
        double d3 = this.u;
        double d4 = this.v;
        double d5 = this.w;

        this.u = d0;
        this.v = d1;
        this.w = d2;
        boolean flag0 = false;
        int i0 = MathHelper.c(this.u);
        int i1 = MathHelper.c(this.v);
        int i2 = MathHelper.c(this.w);
        int i3;

        if (this.q.f(i0, i1, i2)) {
            boolean flag1 = false;

            while (!flag1 && i1 > 0) {
                i3 = this.q.a(i0, i1 - 1, i2);
                if (i3 != 0 && Block.r[i3].cO.c()) {
                    flag1 = true;
                } else {
                    --this.v;
                    --i1;
                }
            }

            if (flag1) {
                this.b(this.u, this.v, this.w);
                if (this.q.a((Entity) this, this.E).isEmpty() && !this.q.d(this.E)) {
                    flag0 = true;
                }
            }
        }

        if (!flag0) {
            this.b(d3, d4, d5);
            return false;
        } else {
            short short1 = 128;

            for (i3 = 0; i3 < short1; ++i3) {
                double d6 = (double) i3 / ((double) short1 - 1.0D);
                float f0 = (this.ab.nextFloat() - 0.5F) * 0.2F;
                float f1 = (this.ab.nextFloat() - 0.5F) * 0.2F;
                float f2 = (this.ab.nextFloat() - 0.5F) * 0.2F;
                double d7 = d3 + (this.u - d3) * d6 + (this.ab.nextDouble() - 0.5D) * (double) this.O * 2.0D;
                double d8 = d4 + (this.v - d4) * d6 + this.ab.nextDouble() * (double) this.P;
                double d9 = d5 + (this.w - d5) * d6 + (this.ab.nextDouble() - 0.5D) * (double) this.O * 2.0D;

                this.q.a("portal", d7, d8, d9, (double) f0, (double) f1, (double) f2);
            }

            this.q.a(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
            this.a("mob.endermen.portal", 1.0F, 1.0F);
            return true;
        }
    }

    protected String bb() {
        return this.q() ? "mob.endermen.scream" : "mob.endermen.idle";
    }

    protected String bc() {
        return "mob.endermen.hit";
    }

    protected String bd() {
        return "mob.endermen.death";
    }

    protected int be() {
        return Item.bo.cp;
    }

    protected void a(boolean flag0, int i0) {
        int i1 = this.be();

        if (i1 > 0) {
            int i2 = this.ab.nextInt(2 + i0);

            for (int i3 = 0; i3 < i2; ++i3) {
                this.b(i1, 1);
            }
        }
    }

    public void a(int i0) {
        this.ah.b(16, Byte.valueOf((byte) (i0 & 255)));
    }

    public int o() {
        return this.ah.a(16);
    }

    public void s(int i0) {
        this.ah.b(17, Byte.valueOf((byte) (i0 & 255)));
    }

    public int p() {
        return this.ah.a(17);
    }

    public boolean a(DamageSource damagesource, int i0) {
        if (this.aq()) {
            return false;
        } else {
            this.a(true);
            if (damagesource instanceof EntityDamageSourceIndirect) {
                for (int i1 = 0; i1 < 64; ++i1) {
                    if (this.m()) {
                        return true;
                    }
                }

                return false;
            } else {
                return super.a(damagesource, i0);
            }
        }
    }

    public boolean q() {
        return this.ah.a(18) > 0;
    }

    public void a(boolean flag0) {
        this.ah.b(18, Byte.valueOf((byte) (flag0 ? 1 : 0)));
    }

    public int c(Entity entity) {
        return 7;
    }

    static {
        // CanaryMod: Disable all default allowed pick ups
        //d[Block.y.cz] = true;
        //d[Block.z.cz] = true;
        //d[Block.I.cz] = true;
        //d[Block.J.cz] = true;
        //d[Block.ah.cz] = true;
        //d[Block.ai.cz] = true;
        //d[Block.aj.cz] = true;
        //d[Block.ak.cz] = true;
        //d[Block.aq.cz] = true;
        //d[Block.aZ.cz] = true;
        //d[Block.ba.cz] = true;
        //d[Block.be.cz] = true;
        //d[Block.bv.cz] = true;
        //d[Block.bC.cz] = true;
        // CanaryMod: pre-fill the array with false
        Arrays.fill(d, false);
    }
}
