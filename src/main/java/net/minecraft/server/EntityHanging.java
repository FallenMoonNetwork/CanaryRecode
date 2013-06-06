package net.minecraft.server;


import java.util.Iterator;
import java.util.List;
import net.canarymod.Canary;
import net.canarymod.api.entity.HangingEntity;
import net.canarymod.hook.entity.HangingEntityDestroyHook;


public abstract class EntityHanging extends Entity {

    private int e;
    public int a;
    public int b;
    public int c;
    public int d;

    public EntityHanging(World world) {
        super(world);
        this.e = 0;
        this.a = 0;
        this.N = 0.0F;
        this.a(0.5F, 0.5F);
    }

    public EntityHanging(World world, int i0, int i1, int i2, int i3) {
        this(world);
        this.b = i0;
        this.c = i1;
        this.d = i2;
    }

    protected void a() {}

    public void a(int i0) {
        this.a = i0;
        this.C = this.A = (float) (i0 * 90);
        float f0 = (float) this.d();
        float f1 = (float) this.g();
        float f2 = (float) this.d();

        if (i0 != 2 && i0 != 0) {
            f0 = 0.5F;
        } else {
            f2 = 0.5F;
            this.A = this.C = (float) (Direction.f[i0] * 90);
        }

        f0 /= 32.0F;
        f1 /= 32.0F;
        f2 /= 32.0F;
        float f3 = (float) this.b + 0.5F;
        float f4 = (float) this.c + 0.5F;
        float f5 = (float) this.d + 0.5F;
        float f6 = 0.5625F;

        if (i0 == 2) {
            f5 -= f6;
        }

        if (i0 == 1) {
            f3 -= f6;
        }

        if (i0 == 0) {
            f5 += f6;
        }

        if (i0 == 3) {
            f3 += f6;
        }

        if (i0 == 2) {
            f3 -= this.b(this.d());
        }

        if (i0 == 1) {
            f5 += this.b(this.d());
        }

        if (i0 == 0) {
            f3 += this.b(this.d());
        }

        if (i0 == 3) {
            f5 -= this.b(this.d());
        }

        f4 += this.b(this.g());
        this.b((double) f3, (double) f4, (double) f5);
        float f7 = -0.03125F;

        this.E.b((double) (f3 - f0 - f7), (double) (f4 - f1 - f7), (double) (f5 - f2 - f7), (double) (f3 + f0 + f7), (double) (f4 + f1 + f7), (double) (f5 + f2 + f7));
    }

    private float b(int i0) {
        return i0 == 32 ? 0.5F : (i0 == 64 ? 0.5F : 0.0F);
    }

    public void l_() {
        if (this.e++ == 100 && !this.q.I) {
            this.e = 0;
            if (!this.M && !this.c()) {
                this.w();
                this.h();
            }
        }
    }

    public boolean c() {
        if (!this.q.a((Entity) this, this.E).isEmpty()) {
            return false;
        } else {
            int i0 = Math.max(1, this.d() / 16);
            int i1 = Math.max(1, this.g() / 16);
            int i2 = this.b;
            int i3 = this.c;
            int i4 = this.d;

            if (this.a == 2) {
                i2 = MathHelper.c(this.u - (double) ((float) this.d() / 32.0F));
            }

            if (this.a == 1) {
                i4 = MathHelper.c(this.w - (double) ((float) this.d() / 32.0F));
            }

            if (this.a == 0) {
                i2 = MathHelper.c(this.u - (double) ((float) this.d() / 32.0F));
            }

            if (this.a == 3) {
                i4 = MathHelper.c(this.w - (double) ((float) this.d() / 32.0F));
            }

            i3 = MathHelper.c(this.v - (double) ((float) this.g() / 32.0F));

            for (int i5 = 0; i5 < i0; ++i5) {
                for (int i6 = 0; i6 < i1; ++i6) {
                    Material material;

                    if (this.a != 2 && this.a != 0) {
                        material = this.q.g(this.b, i3 + i6, i4 + i5);
                    } else {
                        material = this.q.g(i2 + i5, i3 + i6, this.d);
                    }

                    if (!material.a()) {
                        return false;
                    }
                }
            }

            List list = this.q.b((Entity) this, this.E);
            Iterator iterator = list.iterator();

            Entity entity;

            do {
                if (!iterator.hasNext()) {
                    return true;
                }

                entity = (Entity) iterator.next();
            } while (!(entity instanceof EntityHanging));

            return false;
        }
    }

    public boolean K() {
        return true;
    }

    public boolean j(Entity entity) {
        return entity instanceof EntityPlayer ? this.a(DamageSource.a((EntityPlayer) entity), 0) : false;
    }

    public boolean a(DamageSource damagesource, int i0) {
        if (this.aq()) {
            return false;
        } else {
            if (!this.M && !this.q.I) {
                EntityPlayer entityplayer = null;

                if (damagesource.i() instanceof EntityPlayer) {
                    entityplayer = (EntityPlayer) damagesource.i();
                }

                if (entityplayer != null) {
                    // CanaryMod: HangingEntityDestory
                    HangingEntityDestroyHook hook = new HangingEntityDestroyHook((HangingEntity) this.entity, ((EntityPlayerMP) entityplayer).getPlayer(), damagesource.damageSource);
                    Canary.hooks().callHook(hook);
                    if (hook.isCanceled()) {
                        return false;
                    }
                    //
                }

                this.w();
                this.J();

                if (entityplayer != null && entityplayer.ce.d) {
                    return true;
                }

                this.h();
            }

            return true;
        }
    }

    public void d(double d0, double d1, double d2) {
        if (!this.q.I && !this.M && d0 * d0 + d1 * d1 + d2 * d2 > 0.0D) {
            this.w();
            this.h();
        }
    }

    public void g(double d0, double d1, double d2) {
        if (!this.q.I && !this.M && d0 * d0 + d1 * d1 + d2 * d2 > 0.0D) {
            this.w();
            this.h();
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("Direction", (byte) this.a);
        nbttagcompound.a("TileX", this.b);
        nbttagcompound.a("TileY", this.c);
        nbttagcompound.a("TileZ", this.d);
        switch (this.a) {
            case 0:
                nbttagcompound.a("Dir", (byte) 2);
                break;

            case 1:
                nbttagcompound.a("Dir", (byte) 1);
                break;

            case 2:
                nbttagcompound.a("Dir", (byte) 0);
                break;

            case 3:
                nbttagcompound.a("Dir", (byte) 3);
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        if (nbttagcompound.b("Direction")) {
            this.a = nbttagcompound.c("Direction");
        } else {
            switch (nbttagcompound.c("Dir")) {
                case 0:
                    this.a = 2;
                    break;

                case 1:
                    this.a = 1;
                    break;

                case 2:
                    this.a = 0;
                    break;

                case 3:
                    this.a = 3;
            }
        }

        this.b = nbttagcompound.e("TileX");
        this.c = nbttagcompound.e("TileY");
        this.d = nbttagcompound.e("TileZ");
        this.a(this.a);
    }

    public abstract int d();

    public abstract int g();

    public abstract void h();
}
