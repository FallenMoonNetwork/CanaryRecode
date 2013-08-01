package net.minecraft.server;

import java.util.Arrays;
import java.util.UUID;
import net.canarymod.Canary;
import net.canarymod.api.entity.living.monster.CanaryEnderman;
import net.canarymod.config.Configuration;
import net.canarymod.hook.entity.EndermanDropBlockHook;
import net.canarymod.hook.entity.EndermanPickupBlockHook;

public class EntityEnderman extends EntityMob {

    private static final UUID bp = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
    private static final AttributeModifier bq = (new AttributeModifier(bp, "Attacking speed boost", 6.199999809265137D, 0)).a(false);
    // private static boolean[] br = new boolean[256]; //CanaryMod: disabled
    private int bs;
    private int bt;
    private Entity bu;
    private boolean bv;

    public EntityEnderman(World world) {
        super(world);
        this.a(0.6F, 2.9F);
        this.Y = 1.0F;
        this.entity = new CanaryEnderman(this); // CanaryMod: Wrap Entity
    }

    protected void ay() {
        super.ay();
        this.a(SharedMonsterAttributes.a).a(40.0D);
        this.a(SharedMonsterAttributes.d).a(0.30000001192092896D);
        this.a(SharedMonsterAttributes.e).a(7.0D);
    }

    protected void a() {
        super.a();
        this.ah.a(16, new Byte((byte) 0));
        this.ah.a(17, new Byte((byte) 0));
        this.ah.a(18, new Byte((byte) 0));
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("carried", (short) this.bV());
        nbttagcompound.a("carriedData", (short) this.bW());
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.a(nbttagcompound.d("carried"));
        this.c(nbttagcompound.d("carriedData"));
    }

    protected Entity bL() {
        EntityPlayer entityplayer = this.q.b(this, 64.0D);

        if (entityplayer != null) {
            if (this.f(entityplayer)) {
                this.bv = true;
                if (this.bt == 0) {
                    this.q.a((Entity) entityplayer, "mob.endermen.stare", 1.0F, 1.0F);
                }

                if (this.bt++ == 5) {
                    this.bt = 0;
                    this.a(true);
                    return entityplayer;
                }
            } else {
                this.bt = 0;
            }
        }

        return null;
    }

    private boolean f(EntityPlayer entityplayer) {
        ItemStack itemstack = entityplayer.bn.b[3];

        if (itemstack != null && itemstack.d == Block.bf.cF) {
            return false;
        } else {
            Vec3 vec3 = entityplayer.j(1.0F).a();
            Vec3 vec31 = this.q.V().a(this.u - entityplayer.u, this.E.b + (double) (this.P / 2.0F) - (entityplayer.v + (double) entityplayer.f()), this.w - entityplayer.w);
            double d0 = vec31.b();

            vec31 = vec31.a();
            double d1 = vec3.b(vec31);

            return d1 > 1.0D - 0.025D / d0 ? entityplayer.o(this) : false;
        }
    }

    public void c() {
        if (this.F()) {
            this.a(DamageSource.e, 1.0F);
        }

        if (this.bu != this.j) {
            AttributeInstance attributeinstance = this.a(SharedMonsterAttributes.d);

            attributeinstance.b(bq);
            if (this.j != null) {
                attributeinstance.a(bq);
            }
        }

        this.bu = this.j;
        int i0;

        if (!this.q.I && this.q.O().b("mobGriefing")) {
            int i1;
            int i2;
            int i3;

            if (this.bV() == 0) {
                if (this.ab.nextInt(20) == 0) {
                    i0 = MathHelper.c(this.u - 2.0D + this.ab.nextDouble() * 4.0D);
                    i1 = MathHelper.c(this.v + this.ab.nextDouble() * 3.0D);
                    i2 = MathHelper.c(this.w - 2.0D + this.ab.nextDouble() * 4.0D);
                    i3 = this.q.a(i0, i1, i2);
                    // CanaryMod: Replace checking static array with checking the world config list or Ender Blocks
                    if (Arrays.asList(Configuration.getWorldConfig(getCanaryWorld().getFqName()).getEnderBlocks()).contains(i3)) {
                        // CanaryMod: call EndermanPickupBlockHook
                        EndermanPickupBlockHook hook = (EndermanPickupBlockHook) new EndermanPickupBlockHook((CanaryEnderman) entity, ((CanaryEnderman) entity).getWorld().getBlockAt(i0, i1, i2)).call();
                        if (!hook.isCanceled()) {
                            this.a(this.q.a(i0, i1, i2));
                            this.c(this.q.h(i0, i1, i2));
                            this.q.c(i0, i1, i2, 0);
                        }
                        //
                    }
                }
            } else if (this.ab.nextInt(2000) == 0) {
                i0 = MathHelper.c(this.u - 1.0D + this.ab.nextDouble() * 2.0D);
                i1 = MathHelper.c(this.v + this.ab.nextDouble() * 2.0D);
                i2 = MathHelper.c(this.w - 1.0D + this.ab.nextDouble() * 2.0D);
                i3 = this.q.a(i0, i1, i2);
                int i4 = this.q.a(i0, i1 - 1, i2);

                if (i3 == 0 && i4 > 0 && Block.s[i4].b()) {
                    // CanaryMod: call EndermanDropBlockHook
                    EndermanDropBlockHook hook = new EndermanDropBlockHook((CanaryEnderman) entity, ((CanaryEnderman) entity).getWorld().getBlockAt(i0, i1, i2));

                    Canary.hooks().callHook(hook);
                    if (!hook.isCanceled()) {
                        this.q.f(i0, i1, i2, this.bV(), this.bW(), 3);
                        this.a(0);
                    }
                    //
                }
            }
        }

        for (i0 = 0; i0 < 2; ++i0) {
            this.q.a("portal", this.u + (this.ab.nextDouble() - 0.5D) * (double) this.O, this.v + this.ab.nextDouble() * (double) this.P - 0.25D, this.w + (this.ab.nextDouble() - 0.5D) * (double) this.O, (this.ab.nextDouble() - 0.5D) * 2.0D, -this.ab.nextDouble(), (this.ab.nextDouble() - 0.5D) * 2.0D);
        }

        if (this.q.v() && !this.q.I) {
            float f0 = this.d(1.0F);

            if (f0 > 0.5F && this.q.l(MathHelper.c(this.u), MathHelper.c(this.v), MathHelper.c(this.w)) && this.ab.nextFloat() * 30.0F < (f0 - 0.4F) * 2.0F) {
                this.j = null;
                this.a(false);
                this.bv = false;
                this.bT();
            }
        }

        if (this.F() || this.ae()) {
            this.j = null;
            this.a(false);
            this.bv = false;
            this.bT();
        }

        if (this.bX() && !this.bv && this.ab.nextInt(100) == 0) {
            this.a(false);
        }

        this.bd = false;
        if (this.j != null) {
            this.a(this.j, 100.0F, 100.0F);
        }

        if (!this.q.I && this.S()) {
            if (this.j != null) {
                if (this.j instanceof EntityPlayer && this.f((EntityPlayer) this.j)) {
                    if (this.j.e((Entity) this) < 16.0D) {
                        this.bT();
                    }

                    this.bs = 0;
                } else if (this.j.e((Entity) this) > 256.0D && this.bs++ >= 30 && this.c(this.j)) {
                    this.bs = 0;
                }
            } else {
                this.a(false);
                this.bs = 0;
            }
        }

        super.c();
    }

    public boolean bT() { // CanaryMod: protected -> public
        double d0 = this.u + (this.ab.nextDouble() - 0.5D) * 64.0D;
        double d1 = this.v + (double) (this.ab.nextInt(64) - 32);
        double d2 = this.w + (this.ab.nextDouble() - 0.5D) * 64.0D;

        return this.j(d0, d1, d2);
    }

    protected boolean c(Entity entity) {
        Vec3 vec3 = this.q.V().a(this.u - entity.u, this.E.b + (double) (this.P / 2.0F) - entity.v + (double) entity.f(), this.w - entity.w);

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
                if (i3 != 0 && Block.s[i3].cU.c()) {
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

    protected String r() {
        return this.bX() ? "mob.endermen.scream" : "mob.endermen.idle";
    }

    protected String aN() {
        return "mob.endermen.hit";
    }

    protected String aO() {
        return "mob.endermen.death";
    }

    protected int s() {
        return Item.bp.cv;
    }

    protected void b(boolean flag0, int i0) {
        int i1 = this.s();

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

    public int bV() {
        return this.ah.a(16);
    }

    public void c(int i0) {
        this.ah.b(17, Byte.valueOf((byte) (i0 & 255)));
    }

    public int bW() {
        return this.ah.a(17);
    }

    public boolean a(DamageSource damagesource, float f0) {
        if (this.aq()) {
            return false;
        } else {
            this.a(true);
            if (damagesource instanceof EntityDamageSource && damagesource.i() instanceof EntityPlayer) {
                this.bv = true;
            }

            if (damagesource instanceof EntityDamageSourceIndirect) {
                this.bv = false;

                for (int i0 = 0; i0 < 64; ++i0) {
                    if (this.bT()) {
                        return true;
                    }
                }

                return false;
            } else {
                return super.a(damagesource, f0);
            }
        }
    }

    public boolean bX() {
        return this.ah.a(18) > 0;
    }

    public void a(boolean flag0) {
        this.ah.b(18, Byte.valueOf((byte) (flag0 ? 1 : 0)));
    }

    /* CanaryMod: Disable all default allowed pick ups
        static {
            // br[Block.z.cF] = true;
            // br[Block.A.cF] = true;
            // br[Block.J.cF] = true;
            // br[Block.K.cF] = true;
            // br[Block.ai.cF] = true;
            // br[Block.aj.cF] = true;
            // br[Block.ak.cF] = true;
            // br[Block.al.cF] = true;
            // br[Block.ar.cF] = true;
            // br[Block.ba.cF] = true;
            // br[Block.bb.cF] = true;
            // br[Block.bf.cF] = true;
            // br[Block.bw.cF] = true;
            // br[Block.bD.cF] = true;
        } */
}
